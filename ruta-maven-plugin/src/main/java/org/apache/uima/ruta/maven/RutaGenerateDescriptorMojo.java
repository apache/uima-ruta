/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.uima.ruta.maven;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.maven.plugins.annotations.LifecyclePhase.GENERATE_RESOURCES;
import static org.apache.maven.plugins.annotations.ResolutionScope.TEST;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.antlr.runtime.RecognitionException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.artifact.resolver.filter.ScopeArtifactFilter;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.FileSet;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.descriptor.RutaBuildOptions;
import org.apache.uima.ruta.descriptor.RutaDescriptorFactory;
import org.apache.uima.ruta.descriptor.RutaDescriptorInformation;
import org.apache.uima.ruta.extensions.IRutaExtension;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLizable;
import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.codehaus.plexus.util.xml.Xpp3DomBuilder;
import org.codehaus.plexus.util.xml.Xpp3DomWriter;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.sonatype.plexus.build.incremental.BuildContext;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.xml.sax.SAXException;

/**
 * Generate descriptors from UIMA Ruta script files.
 *
 */
@Mojo(name = "generate", defaultPhase = GENERATE_RESOURCES, requiresDependencyResolution = TEST, requiresDependencyCollection = TEST, threadSafe = true)
public class RutaGenerateDescriptorMojo extends AbstractMojo {
  private static final String RUTA_BUILD_VARS = "RUTA_BUILD_VARS";

  private static final String DEFAULT_TARGET_DIR = "${project.build.directory}/generated-sources/ruta/descriptor";

  private static final String RUTA_NATURE = "org.apache.uima.ruta.ide.nature";

  @Parameter(defaultValue = "${project}", readonly = true)
  private MavenProject project;

  @Component
  private BuildContext buildContext;

  @Parameter(defaultValue = "${session}", readonly = true)
  private MavenSession session;

  /**
   * The source files for the multi tree word list.
   */
  @Parameter(required = false)
  private FileSet scriptFiles;

  /**
   * The directory where the generated type system descriptors will be written.
   */
  @Parameter(defaultValue = DEFAULT_TARGET_DIR, required = true)
  private File typeSystemOutputDirectory;

  /**
   * The directory where the generated analysis engine descriptors will be written.
   */
  @Parameter(defaultValue = DEFAULT_TARGET_DIR, required = true)
  private File analysisEngineOutputDirectory;

  /**
   * The template descriptor for the generated type system.
   */
  @Parameter(required = false)
  private File typeSystemTemplate;

  /**
   * The template descriptor for the generated analysis engine.
   */
  @Parameter(required = false)
  private File analysisEngineTemplate;

  /**
   * Script paths of the generated analysis engine descriptor.
   */
  @Parameter(required = false)
  private String[] scriptPaths;

  /**
   * Descriptor paths of the generated analysis engine descriptor.
   */
  @Parameter(required = false)
  private String[] descriptorPaths;

  /**
   * Resource paths of the generated analysis engine descriptor.
   */
  @Parameter(required = false)
  private String[] resourcePaths;

  /**
   * Suffix used for the generated type system descriptors.
   */
  @Parameter(defaultValue = "TypeSystem", required = true)
  private String typeSystemSuffix;

  /**
   * Suffix used for the generated analysis engine descriptors.
   */
  @Parameter(defaultValue = "Engine", required = true)
  private String analysisEngineSuffix;

  /**
   * Source file encoding.
   */
  @Parameter(defaultValue = "${project.build.sourceEncoding}", required = true)
  private String encoding;

  /**
   * Type of type system imports. default false = import by location.
   */
  @Parameter(defaultValue = "false", required = false)
  private boolean importByName;

  /**
   * Option to resolve imports while building.
   */
  @Parameter(defaultValue = "false", required = false)
  private boolean resolveImports;

  /**
   * Amount of retries for building dependent descriptors.
   */
  @Parameter(defaultValue = "-1", required = false)
  private int maxBuildRetries;

  /**
   * List of packages with language extensions
   */
  @Parameter(defaultValue = "org.apache.uima.ruta", required = false)
  private String[] extensionPackages;

  /**
   * Add UIMA Ruta nature to .project
   */
  @Parameter(defaultValue = "true", required = false)
  private boolean addRutaNature;

  /**
   * Script source paths for the UIMA Ruta build path.
   */
  @Parameter(required = false)
  private String[] buildPaths;

  /**
   * Scope threshold to include. The default is "compile" (which implies compile, provided and
   * system dependencies). Can also be changed to "test" (which implies all dependencies).
   */
  @Parameter(defaultValue = "compile", required = true)
  private String includeScope;

  /**
   * Fail on error.
   */
  @Parameter(defaultValue = "true", required = true)
  private boolean failOnError;

  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {

    if (!typeSystemOutputDirectory.exists()) {
      typeSystemOutputDirectory.mkdirs();
      buildContext.refresh(typeSystemOutputDirectory);
    }

    if (!analysisEngineOutputDirectory.exists()) {
      analysisEngineOutputDirectory.mkdirs();
      buildContext.refresh(analysisEngineOutputDirectory);
    }

    project.addCompileSourceRoot(typeSystemOutputDirectory.getPath());
    project.addCompileSourceRoot(analysisEngineOutputDirectory.getPath());

    String[] files = null;
    if (scriptFiles != null) {
      try {
        files = Utils.getFilePathArray(scriptFiles, buildContext);
      } catch (Exception e) {
        getLog().warn("Error accessing script files.", e);
      }
    } else {
      files = FileUtils.getFilesFromExtension(project.getBuild().getOutputDirectory(),
              new String[] { "ruta" });
    }

    if (addRutaNature) {
      addRutaNature();
      addRutaBuildPath();
    }

    if (files == null) {
      getLog().info("UIMA Ruta Building: Skipped, since no script files were selected.");
      return;
    }

    List<File> filesToBuild = new ArrayList<>();
    for (String each : files) {
      File file = new File(each);

      // TODO should check the correct package!
      List<File> possibleDescriptors = getPossibleDescriptors(file);
      if (possibleDescriptors == null) {
        filesToBuild.add(file);
        continue;
      }

      long scriptModified = file.lastModified();
      for (File eachDescriptor : possibleDescriptors) {
        long descModified = eachDescriptor.lastModified();
        if (scriptModified > descModified) {
          filesToBuild.add(file);
          break;
        }
      }
    }

    if (filesToBuild.isEmpty()) {
      getLog().info("UIMA Ruta Building: Skipped, since no changes were detected.");
      return;
    }

    RutaDescriptorFactory factory = new RutaDescriptorFactory();
    if (typeSystemTemplate != null) {
      try {
        factory.setDefaultTypeSystem(typeSystemTemplate.toURI().toURL());
      } catch (MalformedURLException e) {
        handleError("Failed to get URL of " + analysisEngineTemplate, e);
      }
    }
    if (analysisEngineTemplate != null) {
      try {
        factory.setDefaultEngine(analysisEngineTemplate.toURI().toURL());
      } catch (MalformedURLException e) {
        handleError("Failed to get URL of " + analysisEngineTemplate, e);
      }
    }

    URLClassLoader classloader = getClassloader(project, getLog(), includeScope);

    RutaBuildOptions options = new RutaBuildOptions();
    options.setTypeSystemSuffix(typeSystemSuffix);
    options.setAnalysisEngineSuffix(analysisEngineSuffix);
    options.setEncoding(encoding);
    options.setResolveImports(resolveImports);
    options.setImportByName(importByName);
    options.setClassLoader(classloader);

    List<String> extensions = getExtensionsFromClasspath(classloader);
    options.setLanguageExtensions(extensions);

    if (maxBuildRetries == -1) {
      maxBuildRetries = filesToBuild.size() * 3;
    }

    Queue<RutaDescriptorInformation> toBuild = new LinkedList<>();

    for (File file : filesToBuild) {
      try {
        RutaDescriptorInformation descriptorInformation = factory.parseDescriptorInformation(file,
                options);
        toBuild.add(descriptorInformation);
      } catch (RecognitionException re) {
        handleError("Failed to parse UIMA Ruta script file: " + file.getAbsolutePath(), re);
      } catch (IOException ioe) {
        handleError("Failed to load UIMA Ruta script file: " + file.getAbsolutePath(), ioe);
      }
    }

    int count = 0;
    while (!toBuild.isEmpty() && count <= maxBuildRetries) {
      RutaDescriptorInformation descriptorInformation = toBuild.poll();
      String scriptName = descriptorInformation.getScriptName();
      try {
        createDescriptors(factory, options, descriptorInformation);
      } catch (RecognitionException re) {
        getLog().warn("Failed to parse UIMA Ruta script: " + scriptName, re);
      } catch (IOException ioe) {
        toBuild.add(descriptorInformation);
        getLog().warn("Tried to build " + scriptName
                + ", but failed (dependency probably not yet build): " + ioe.getMessage());
        count++;
      } catch (SAXException saxe) {
        getLog().warn("Failed to write descriptor: " + scriptName, saxe);
      } catch (URISyntaxException urise) {
        getLog().warn("Failed to get uri: " + scriptName, urise);
      } catch (ResourceInitializationException rie) {
        getLog().warn("Failed initialize resource: " + scriptName, rie);
      } catch (InvalidXMLException ixmle) {
        getLog().warn("Invalid XML while building descriptor: " + scriptName, ixmle);
      }
    }

    for (RutaDescriptorInformation eachFailed : toBuild) {
      String scriptName = eachFailed.getScriptName();
      handleError("Failed to build UIMA Ruta script: " + scriptName);
    }

  }

  @SuppressWarnings("unchecked")
  private List<File> getPossibleDescriptors(File file) {
    List<File> result = new ArrayList<>();
    String scriptName = file.getName().substring(0, file.getName().length() - 5);

    String aeName = "**/" + scriptName + analysisEngineSuffix + ".xml";
    List<File> aeFiles = null;
    try {
      aeFiles = FileUtils.getFiles(analysisEngineOutputDirectory, aeName, null);
      result.addAll(aeFiles);
    } catch (IOException e) {
      return null;
    }
    if (aeFiles == null || aeFiles.size() == 0) {
      return null;
    }

    String tsName = "**/" + scriptName + typeSystemSuffix + ".xml";
    List<File> tsFiles;
    try {
      tsFiles = FileUtils.getFiles(typeSystemOutputDirectory, tsName, null);
      result.addAll(tsFiles);
    } catch (IOException e) {
      return null;
    }

    if (tsFiles == null || tsFiles.size() == 0) {
      return null;
    }

    return result;
  }

  private List<String> getExtensionsFromClasspath(ClassLoader classloader) {
    List<String> result = new ArrayList<>();
    ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(
            true);
    ResourceLoader resourceLoader = new DefaultResourceLoader(classloader);
    provider.setResourceLoader(resourceLoader);
    provider.addIncludeFilter(new AssignableTypeFilter(IRutaExtension.class));

    for (String basePackage : extensionPackages) {
      Set<BeanDefinition> components = provider.findCandidateComponents(basePackage);
      for (BeanDefinition component : components) {
        String beanClassName = component.getBeanClassName();
        result.add(beanClassName);
      }
    }
    return result;

  }

  private void createDescriptors(RutaDescriptorFactory factory, RutaBuildOptions options,
          RutaDescriptorInformation descriptorInformation) throws IOException, RecognitionException,
          InvalidXMLException, ResourceInitializationException, URISyntaxException, SAXException {
    String packageString = "";
    if (!StringUtils.isBlank(descriptorInformation.getPackageString())) {
      packageString = descriptorInformation.getPackageString().replaceAll("[.]", "/").concat("/");
    }
    String engineOutput = new File(analysisEngineOutputDirectory,
            packageString + descriptorInformation.getScriptName() + analysisEngineSuffix + ".xml")
                    .getAbsolutePath();
    String typeSystemOutput = new File(typeSystemOutputDirectory,
            packageString + descriptorInformation.getScriptName() + typeSystemSuffix + ".xml")
                    .getAbsolutePath();
    Pair<AnalysisEngineDescription, TypeSystemDescription> descriptions = factory
            .createDescriptions(engineOutput, typeSystemOutput, descriptorInformation, options,
                    scriptPaths, descriptorPaths, resourcePaths);
    write(descriptions.getKey(), engineOutput);
    write(descriptions.getValue(), typeSystemOutput);
    buildContext.refresh(analysisEngineOutputDirectory);
    buildContext.refresh(typeSystemOutputDirectory);
  }

  private void write(XMLizable desc, String aFilename) throws SAXException, IOException {
    OutputStream os = null;
    try {
      File out = new File(aFilename);
      out.getParentFile().mkdirs();
      os = buildContext.newFileOutputStream(out);
      out.getParentFile().mkdirs();
      getLog().debug("Writing descriptor to: " + out);
      desc.toXML(os);
      buildContext.refresh(out);
    } finally {
      IOUtils.closeQuietly(os);
    }
  }

  public static URLClassLoader getClassloader(MavenProject project, Log aLog,
          String includeScopeThreshold) throws MojoExecutionException {

    List<URL> urls = new ArrayList<>();

    for (String element : project.getCompileSourceRoots()) {
      try {
        urls.add(new File(element).toURI().toURL());
        if (aLog != null) {
          aLog.debug("Classpath entry: " + element);
        }
      } catch (MalformedURLException e) {
        throw new MojoExecutionException(
                "Unable to assemble classpath: " + ExceptionUtils.getRootCauseMessage(e), e);
      }
    }

    for (Resource element : project.getResources()) {
      try {
        String directory = element.getDirectory();
        urls.add(new File(directory).toURI().toURL());
        if (aLog != null) {
          aLog.debug("Classpath entry: " + directory);
        }
      } catch (MalformedURLException e) {
        throw new MojoExecutionException(
                "Unable to assemble classpath: " + ExceptionUtils.getRootCauseMessage(e), e);
      }
    }

    try {
      for (Object object : project.getCompileClasspathElements()) {
        String path = (String) object;
        if (aLog != null) {
          aLog.debug("Classpath entry: " + object);
        }
        urls.add(new File(path).toURI().toURL());
      }
    } catch (IOException e) {
      throw new MojoExecutionException(
              "Unable to assemble classpath: " + ExceptionUtils.getRootCauseMessage(e), e);
    } catch (DependencyResolutionRequiredException e) {
      throw new MojoExecutionException(
              "Unable to resolve dependencies: " + ExceptionUtils.getRootCauseMessage(e), e);
    }

    ScopeArtifactFilter filter = new ScopeArtifactFilter(includeScopeThreshold);
    for (Artifact dep : project.getArtifacts()) {
      try {
        if (!filter.include(dep)) {
          aLog.debug("Not generating classpath entry for out-of-scope artifact: " + dep.getGroupId()
                  + ":" + dep.getArtifactId() + ":" + dep.getVersion() + " (" + dep.getScope()
                  + ")");
          continue;
        }

        if (dep.getFile() == null) {
          aLog.debug("Not generating classpath entry for unresolved artifact: " + dep.getGroupId()
                  + ":" + dep.getArtifactId() + ":" + dep.getVersion() + " (" + dep.getScope()
                  + ")");
          // Unresolved file because it is in the wrong scope (e.g. test?)
          continue;
        }

        aLog.debug("Classpath entry: " + dep.getGroupId() + ":" + dep.getArtifactId() + ":"
                + dep.getVersion() + " -> " + dep.getFile());
        urls.add(dep.getFile().toURI().toURL());
      } catch (Exception e) {
        throw new MojoExecutionException("Unable get dependency artifact location for "
                + dep.getGroupId() + ":" + dep.getArtifactId() + ":" + dep.getVersion()
                + ExceptionUtils.getRootCauseMessage(e), e);
      }
    }

    return new URLClassLoader(urls.toArray(new URL[] {}),
            RutaGenerateDescriptorMojo.class.getClassLoader());
  }

  private void addRutaNature() throws MojoExecutionException {

    File projectDir = project.getFile().getParentFile();
    File projectFile = new File(projectDir, ".project");
    if (projectFile.exists()) {
      Xpp3Dom project = null;
      try {
        project = Xpp3DomBuilder.build(new FileReader(projectFile));
      } catch (XmlPullParserException | IOException e) {
        getLog().warn("Failed to access .project file", e);
      }
      if (project == null) {
        return;
      }

      Xpp3Dom naturesNode = project.getChild("natures");
      if (naturesNode != null) {
        for (int i = 0; i < naturesNode.getChildCount(); ++i) {
          Xpp3Dom natureEntry = naturesNode.getChild(i);
          if (natureEntry != null && StringUtils.equals(natureEntry.getValue(), RUTA_NATURE)) {
            return;
          }
        }
      }
      Xpp3Dom rutaNatureNode = new Xpp3Dom("nature");
      rutaNatureNode.setValue(RUTA_NATURE);
      naturesNode.addChild(rutaNatureNode);

      StringWriter sw = new StringWriter();
      Xpp3DomWriter.write(sw, project);
      String string = sw.toString();
      // Xpp3DomWriter creates empty string with file writer, check before writing to file
      if (!StringUtils.isBlank(string)) {
        try (Writer os = new OutputStreamWriter(new FileOutputStream(projectFile), UTF_8)) {
          os.write(string);
        } catch (IOException e) {
          handleError("Failed to write .project file", e);
        }
      }
      buildContext.refresh(projectFile);
    }
  }

  private void addRutaBuildPath() throws MojoExecutionException {
    File projectDir = project.getFile().getParentFile();
    Path projectPath = Paths.get(projectDir.toURI());

    if (buildPaths == null || buildPaths.length == 0) {
      return;
    }

    File buildpathFile = new File(projectDir, ".buildpath");
    Xpp3Dom buildpathNode = new Xpp3Dom("buildpath");
    for (String eachBP : buildPaths) {

      String[] split = StringUtils.split(eachBP, ":", 2);
      String type = "script";
      String path = eachBP;

      if (split.length == 2) {
        type = split[0];
        path = split[1];
      }
      Path bpPath = Paths.get(path);
      String bp = path;
      if (bpPath.isAbsolute()) {
        bp = projectPath.relativize(bpPath).toString();
      }
      addBuildPathEntry(buildpathNode, type, bp);
    }
    Xpp3Dom buildpathentry = new Xpp3Dom("buildpathentry");
    buildpathentry.setAttribute("kind", "con");
    buildpathentry.setAttribute("path", "org.eclipse.dltk.launching.INTERPRETER_CONTAINER");
    buildpathNode.addChild(buildpathentry);

    addRutabuildVars(buildpathNode);

    StringWriter sw = new StringWriter();
    Xpp3DomWriter.write(sw, buildpathNode);
    String string = sw.toString();
    // Xpp3DomWriter creates empty string with file writer, check before writing to file
    if (!StringUtils.isBlank(string)) {
      try (Writer os = new OutputStreamWriter(new FileOutputStream(buildpathFile), UTF_8)) {
        os.write(string);
      } catch (IOException e) {
        handleError("Failed to write .buildpath file", e);
      }
    }
    buildContext.refresh(buildpathFile);
  }

  private void addRutabuildVars(Xpp3Dom buildpathNode) {
    Xpp3Dom varEntry = new Xpp3Dom("buildpathentry");
    varEntry.setAttribute("kind", "con");
    varEntry.setAttribute("path", RUTA_BUILD_VARS);
    Xpp3Dom attributes = new Xpp3Dom("attributes");
    varEntry.addChild(attributes);
    Xpp3Dom tsAttribute = new Xpp3Dom("attribute");
    tsAttribute.setAttribute("name", "typeSystemSuffix");
    tsAttribute.setAttribute("value", typeSystemSuffix);
    attributes.addChild(tsAttribute);
    Xpp3Dom aeAttribute = new Xpp3Dom("attribute");
    aeAttribute.setAttribute("name", "analysisEngineSuffix");
    aeAttribute.setAttribute("value", analysisEngineSuffix);
    attributes.addChild(aeAttribute);
    buildpathNode.addChild(varEntry);

  }

  private void addBuildPathEntry(Xpp3Dom buildpathNode, String type, String path) {
    Xpp3Dom buildpathentry = new Xpp3Dom("buildpathentry");
    buildpathentry.setAttribute("kind", "src");
    buildpathentry.setAttribute("path", path);
    Xpp3Dom attributes = new Xpp3Dom("attributes");
    buildpathentry.addChild(attributes);
    Xpp3Dom attribute = new Xpp3Dom("attribute");
    attribute.setAttribute("name", "ruta");
    attribute.setAttribute("value", type);
    attributes.addChild(attribute);
    buildpathNode.addChild(buildpathentry);
  }

  private void handleError(String message, Exception e) throws MojoExecutionException {
    if (failOnError) {
      throw new MojoExecutionException(message, e);
    }

    getLog().error(message, e);
  }

  private void handleError(String message) throws MojoExecutionException {
    if (failOnError) {
      throw new MojoExecutionException(message);
    }

    getLog().error(message);
  }
}
