package org.apache.uima.ruta;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.resource.metadata.impl.TypeSystemDescription_impl;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.InvalidXMLException;

/**
 * A RUTA analysis engine builder.
 */
public class RutaEngineBuilder {
  /**
   * Pattern to find the package declaration of a script.
   *
   * The package name is in the first group of the match.
   */
  private final static Pattern PACKAGE_PATTERN =
          Pattern.compile("PACKAGE\\s+(\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*(\\.\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*)*)\\s*;");
  /**
   * Pattern to extract the module name from a ruta script file name.
   *
   * The first group is the directory of the script and the second is the module name.
   */
  private final static Pattern MODULE_PATTERN = Pattern.compile("(?i)^(.*/)?([^/]+).ruta$");
  /**
   * RUTA script.
   */
  private final String script;
  /**
   * RUTA script path.
   */
  private final String[] scriptPaths;
  /**
   * Module name.
   */
  private final String moduleName;
  /**
   * Package name.
   */
  private final String packageName;
  /**
   * Type system for the RUTA script.
   */
  private final TypeSystemDescription tsd;

  /**
   * @param script RUTA script, url from the classpath.
   * @throws IOException When there is a problem reading the script.
   */
  public RutaEngineBuilder(String script) throws IOException {
    final URL url = getClass().getResource(script);
    if (url == null) {
      throw new IllegalArgumentException("Cannot find " + script + " in the classpath");
    }

    this.script = script.replaceFirst(RutaEngine.SCRIPT_FILE_EXTENSION + "$", "").replaceFirst("^/", "");
    this.scriptPaths = new String[]{};
    this.tsd = new TypeSystemDescription_impl();
    this.moduleName = extractModuleNameFromUrl(url);
    this.packageName = extractPackageNameFromScript(url);
  }

  public RutaEngineBuilder(File script) throws IOException {
    final URL url = script.toURI().toURL();

    this.script = script.getName().replaceFirst(RutaEngine.SCRIPT_FILE_EXTENSION + "$", "");
    this.scriptPaths = new String[]{script.getParent()};
    this.tsd = new TypeSystemDescription_impl();
    this.moduleName = extractModuleNameFromUrl(url);
    this.packageName = extractPackageNameFromScript(url);
  }

  /**
   * Read the content of an url in a string.
   *
   * @param url to read.
   * @return Content.
   */
  public static String toString(URL url) throws IOException {
    final String content;
    final InputStream in = url.openStream();

    try {
      content = IOUtils.toString(in);
    } finally {
      IOUtils.closeQuietly(in);
    }
    return content;
  }

  /**
   * @param scriptUrl Script's url.
   * @return Script moduleName name.
   */
  private String extractModuleNameFromUrl(URL scriptUrl) {
    final String filename = scriptUrl.getFile();
    final Matcher matcher = MODULE_PATTERN.matcher(filename);
    if (!matcher.matches()) {
      throw new IllegalArgumentException("Cannot extract module name from script " + scriptUrl);
    }

    return matcher.group(2);
  }

  /**
   * @param scriptUrl Script's url.
   * @return Script package name.
   * @throws IOException When there is a problem reading the script.
   */
  private String extractPackageNameFromScript(URL scriptUrl) throws IOException {
    final String content = toString(scriptUrl);

    final Matcher matcher = PACKAGE_PATTERN.matcher(content);
    if (!matcher.find()) {
      throw new IllegalArgumentException("Cannot find package name in script " + scriptUrl);
    }

    return matcher.group(1);
  }

  /**
   * @return The script's type system.
   */
  //public TypeSystemDescription getTsd() {
  //  return tsd;
  //}
  public TypeDescription declareType(String shortName, String description, String superType) {
    return tsd.addType(packageName + "." + moduleName + "." + shortName, description, superType);
  }

  /**
   * Create the analysis engine for the RUTA script.
   *
   * @return The engine.
   */
  public AnalysisEngineDescription createDescriptor(Object... configurationData) throws IOException, InvalidXMLException, ResourceInitializationException {
    // create engine
    AnalysisEngineDescription description = AnalysisEngineFactory.createEngineDescription("org.apache.uima.ruta.engine.BasicEngine",
            RutaEngine.PARAM_MAIN_SCRIPT, script,
            RutaEngine.PARAM_SCRIPT_PATHS, scriptPaths/*,
              configurationData*/);

    // add typesystems
    final Collection<TypeSystemDescription> tsds = new ArrayList<TypeSystemDescription>();
    tsds.add(description.getAnalysisEngineMetaData().getTypeSystem());
    tsds.add(tsd);
    final TypeSystemDescription mergeTypeSystems = CasCreationUtils.mergeTypeSystems(tsds);
    description.getAnalysisEngineMetaData().setTypeSystem(mergeTypeSystems);

    return description;
  }
}
