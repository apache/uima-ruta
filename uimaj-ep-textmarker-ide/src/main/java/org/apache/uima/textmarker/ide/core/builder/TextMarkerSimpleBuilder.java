/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.textmarker.ide.core.builder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.uima.ResourceSpecifierFactory;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.Capability;
import org.apache.uima.resource.metadata.ConfigurationParameterSettings;
import org.apache.uima.resource.metadata.FsIndexDescription;
import org.apache.uima.resource.metadata.Import;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.resource.metadata.impl.Import_impl;
import org.apache.uima.textmarker.engine.TextMarkerEngine;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.apache.uima.util.XMLSerializer;
import org.apache.uima.util.XMLizable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class TextMarkerSimpleBuilder {

  private static ResourceSpecifierFactory uimaFactory = UIMAFramework.getResourceSpecifierFactory();

  private TypeSystemDescription typeSystemDescription;

  private AnalysisEngineDescription analysisEngineDescription;

  private final String defaultTypeSystem;

  public TextMarkerSimpleBuilder(String defaultTypeSystem, String defaultEngine)
          throws InvalidXMLException, IOException {
    super();
    this.defaultTypeSystem = defaultTypeSystem;
    initialize(defaultEngine);
  }

  private void initialize(String defaultEngine) throws InvalidXMLException, IOException {
    typeSystemDescription = uimaFactory.createTypeSystemDescription();
    analysisEngineDescription = UIMAFramework.getXMLParser().parseAnalysisEngineDescription(
            new XMLInputSource(new File(defaultEngine)));
  }

  public void build(DescriptorManager desc, String typeSystemOutput, String engineOutput,
          TextMarkerBuildOptions option, String mainScript, String[] scriptPaths,
          String[] enginePaths) throws SAXException, TextMarkerBuildException, InvalidXMLException,
          IOException, ResourceInitializationException {
    Map<String, String> typeNameMap = new HashMap<String, String>();
    Capability capability = uimaFactory.createCapability();
    // String defaultTypeSystem2 = defaultTypeSystem.replaceAll("/", "\\\\");
    File defaultTypeSystemFile = new File(defaultTypeSystem);
    if (!defaultTypeSystemFile.exists()) {
      System.out.println("Does not exist: " + defaultTypeSystemFile.getName());
    }
    TypeSystemDescription initialTypeSystem = UIMAFramework.getXMLParser()
            .parseTypeSystemDescription(new XMLInputSource(defaultTypeSystemFile));
    CAS cas = CasCreationUtils.createCas(initialTypeSystem, null, new FsIndexDescription[0]);
    fillTypeNameMap(typeNameMap, cas.getTypeSystem());
    cas.release();
    List<Import> importList = new ArrayList<Import>();
    Import_impl import_impl = new Import_impl();
    if (option.isImportByName()) {
      String name = initialTypeSystem.getName();
      import_impl.setName(name);
    } else if (option.isResolveImports()) {
      String absoluteLocation = initialTypeSystem.getSourceUrlString();
      import_impl.setLocation(absoluteLocation);
    } else {
      String relativeLocation = getRelativeLocation(defaultTypeSystemFile.getAbsolutePath(),
              typeSystemOutput);
      import_impl.setLocation(relativeLocation);
    }
    importList.add(import_impl);
    for (String eachName : desc.getImportedTypeSystems()) {
      String locate = TextMarkerEngine.locate(eachName, enginePaths, ".xml");
      File file = new File(locate);
      TypeSystemDescription each = getTypeSystemDescriptor(file, option);
      if (each != null) {
        fillTypeNameMap(typeNameMap, each);
        import_impl = new Import_impl();
        if (option.isImportByName()) {
          import_impl.setName(eachName);
        } else if (option.isResolveImports()) {
          String absoluteLocation = each.getSourceUrlString();
          import_impl.setLocation(absoluteLocation);
        } else {
          String relativeLocation = getRelativeLocation(file.getAbsolutePath(), typeSystemOutput);
          import_impl.setLocation(relativeLocation);
        }
        importList.add(import_impl);
      } else {
        throw new FileNotFoundException("Build process can't find " + eachName + " in "
                + mainScript);
      }
    }
    for (String eachName : desc.getImportedScripts()) {
      String locate = TextMarkerEngine.locate(eachName, enginePaths, "TypeSystem.xml");
      File file = new File(locate);
      TypeSystemDescription each = getTypeSystemDescriptor(file, option);
      if (each != null) {
        fillTypeNameMap(typeNameMap, each);
        import_impl = new Import_impl();
        if (option.isImportByName()) {
          import_impl.setName(eachName + "TypeSystem");
        } else if (option.isResolveImports()) {
          String absoluteLocation = each.getSourceUrlString();
          import_impl.setLocation(absoluteLocation);
        } else {
          String relativeLocation = getRelativeLocation(file.getAbsolutePath(), typeSystemOutput);
          import_impl.setLocation(relativeLocation);
        }
        importList.add(import_impl);
      } else {
        throw new FileNotFoundException("Build process can't find " + eachName + " in "
                + mainScript);
      }
    }

    Import[] newImports = importList.toArray(new Import[0]);
    typeSystemDescription.setImports(newImports);
    if (option.isResolveImports()) {
      try {
        typeSystemDescription.resolveImports();
      } catch (InvalidXMLException e) {
        throw new TextMarkerBuildException("Failed to resolve imported Type Systems", e);
      }
    }

    // TODO hotfixes: where do I get the final types??
    Set<String> finalTypes = new HashSet<String>();
    finalTypes.addAll(Arrays.asList(new String[] { "uima.cas.Boolean", "uima.cas.Byte",
        "uima.cas.Short", "uima.cas.Integer", "uima.cas.Long", "uima.cas.Float", "uima.cas.Double",
        "uima.cas.BooleanArray", "uima.cas.ByteArray", "uima.cas.ShortArray",
        "uima.cas.IntegerArray", "uima.cas.LongArray", "uima.cas.FloatArray",
        "uima.cas.DoubleArray", "uima.cas.StringArray", "uima.cas.FSArray" }));

    int typeIndex = 0;
    for (String eachType : desc.getTypeShortNames()) {
      StringTriple typeTriple = desc.getTypeTriples().get(typeIndex);
      typeTriple = resolveType(typeTriple, typeNameMap, mainScript);
      if (typeSystemDescription.getType(typeTriple.getName()) != null) {
        continue;
      }
      if (!finalTypes.contains(typeTriple.getParent())) {
        TypeDescription newType = typeSystemDescription.addType(typeTriple.getName(),
                typeTriple.getDescription(), typeTriple.getParent());
        capability.addInputType(typeTriple.getName(), false);
        capability.addOutputType(typeTriple.getName(), false);
        Collection<StringTriple> collection = desc.getFeatures().get(eachType);
        if (collection != null) {
          for (StringTriple eachFeature : collection) {
            eachFeature = resolveFeature(eachFeature, typeNameMap);
            newType.addFeature(eachFeature.getName(), eachFeature.getDescription(),
                    eachFeature.getParent());
            // capability.addInputFeature(eachFeature.getName());
            // capability.addOutputFeature(eachFeature.getName());
          }
        }
      }
      typeIndex++;
    }

    Set<String> names = new HashSet<String>();
    List<TypeDescription> types = new ArrayList<TypeDescription>();
    for (TypeDescription each : typeSystemDescription.getTypes()) {
      String name = each.getName();
      if (!names.contains(name)) {
        names.add(name);
        types.add(each);
      }
    }

    typeSystemDescription.setTypes(types.toArray(new TypeDescription[0]));
    File typeSystemFile = getFile(typeSystemOutput);
    typeSystemDescription.setName(mainScript + "TypeSystem");
    typeSystemDescription.setSourceUrl(typeSystemFile.toURI().toURL());
    TypeSystemDescription aets = uimaFactory.createTypeSystemDescription();
    import_impl = new Import_impl();
    if (option.isImportByName()) {
      import_impl.setName(typeSystemDescription.getName());
    } else {
      String relativeLocation = getRelativeLocation(engineOutput, typeSystemOutput);
      import_impl.setLocation(relativeLocation);
    }

    File engineFile = configureEngine(desc, engineOutput, option, mainScript, scriptPaths,
            enginePaths, capability, import_impl, aets);

    toFile(typeSystemDescription, typeSystemFile);
    toFile(analysisEngineDescription, engineFile);
  }

  private void fillTypeNameMap(Map<String, String> typeNameMap, TypeSystem typeSystem) {
    Iterator<Type> typeIterator = typeSystem.getTypeIterator();
    while (typeIterator.hasNext()) {
      Type type = (Type) typeIterator.next();
      String shortName = type.getShortName();
      String name = type.getName();
      typeNameMap.put(shortName, name);
    }
  }

  private boolean fillTypeNameMap(Map<String, String> typeNameMap, TypeSystemDescription desc) {
    boolean contained = false;
    for (TypeDescription each : desc.getTypes()) {
      String name = each.getName();
      int lastIndexOf = name.lastIndexOf(".");
      String shortName = name.substring(lastIndexOf + 1, name.length());
      typeNameMap.put(shortName, name);
    }
    return contained;
  }

  private StringTriple resolveFeature(StringTriple eachFeature, Map<String, String> types) {
    String parent = eachFeature.getParent();
    String name = eachFeature.getName();
    parent = translate(parent);
    if (parent.indexOf(".") == -1 && types.containsKey(parent)) {
      parent = types.get(parent);
    }
    return new StringTriple(name, eachFeature.getDescription(), parent);
  }

  private StringTriple resolveType(StringTriple typeTriple, Map<String, String> types,
          String packageName) {
    String parent = typeTriple.getParent();
    String name = typeTriple.getName();
    if (parent == null) {
      parent = "uima.tcas.Annotation";
    }
    parent = translate(parent);
    name = translate(name);
    if (parent.indexOf(".") == -1 && types.containsKey(parent)) {
      parent = types.get(parent);
    }
    if (name.indexOf(".") == -1) {
      if (types.containsKey(name)) {
        name = types.get(name);
      } else {
        name = packageName + "." + name;
      }
    }
    int lastIndexOf = name.lastIndexOf(".");
    String shortName = name.substring(lastIndexOf + 1, name.length());
    types.put(shortName, name);
    return new StringTriple(name, typeTriple.getDescription(), parent);
  }

  private String translate(String name) {
    if (name == null) {
      return null;
    }
    if (name.equals("Annotation")) {
      return "uima.tcas.Annotation";
    } else if (name.equals("STRING")) {
      return "uima.cas.String";
    } else if (name.equals("INT")) {
      return "uima.cas.Integer";
    } else if (name.equals("DOUBLE")) {
      return "uima.cas.Double";
    } else if (name.equals("BOOLEAN")) {
      return "uima.cas.Boolean";
    } else if (name.equals("TYPE")) {
      return "uima.cas.String";
    }
    return name;
  }

  private File configureEngine(DescriptorManager desc, String engineOutput,
          TextMarkerBuildOptions option, String mainScript, String[] scriptPaths,
          String[] descriptorPaths, Capability capability, Import_impl import_impl,
          TypeSystemDescription aets) throws MalformedURLException {
    aets.setImports(new Import[] { import_impl });
    analysisEngineDescription.getAnalysisEngineMetaData().setTypeSystem(aets);
    File file = getFile(engineOutput);
    analysisEngineDescription.setSourceUrl(file.toURI().toURL());

    if (!desc.getTypeShortNames().isEmpty()) {
      Capability[] capabilities = analysisEngineDescription.getAnalysisEngineMetaData()
              .getCapabilities();
      Capability[] newArray = new Capability[capabilities.length + 1];
      System.arraycopy(capabilities, 0, newArray, 0, capabilities.length);
      newArray[capabilities.length] = capability;
      analysisEngineDescription.getAnalysisEngineMetaData().setCapabilities(newArray);
    }

    analysisEngineDescription.getAnalysisEngineMetaData().getConfigurationParameterSettings()
            .setParameterValue(TextMarkerEngine.MAIN_SCRIPT, mainScript);
    analysisEngineDescription.getAnalysisEngineMetaData().getConfigurationParameterSettings()
            .setParameterValue(TextMarkerEngine.SCRIPT_PATHS, scriptPaths);
    analysisEngineDescription.getAnalysisEngineMetaData().getConfigurationParameterSettings()
            .setParameterValue(TextMarkerEngine.DESCRIPTOR_PATHS, descriptorPaths);
    String[] parameterValue = (String[]) analysisEngineDescription.getAnalysisEngineMetaData()
            .getConfigurationParameterSettings().getParameterValue(TextMarkerEngine.RESOURCE_PATHS);
    Set<String> resourceLocations = new HashSet<String>();

    if (parameterValue != null && parameterValue.length != 0) {
      resourceLocations.addAll(Arrays.asList(parameterValue));
    }
    for (String string : descriptorPaths) {
      File descDir = new File(string);
      File defaultResourceDir = new File(descDir.getParent(),
              TextMarkerProjectUtils.getDefaultResourcesLocation());
      resourceLocations.add(defaultResourceDir.getAbsolutePath());
    }
    analysisEngineDescription
            .getAnalysisEngineMetaData()
            .getConfigurationParameterSettings()
            .setParameterValue(TextMarkerEngine.RESOURCE_PATHS,
                    resourceLocations.toArray(new String[0]));

    String[] additionalScriptsArray = desc.getImportedScripts().toArray(new String[] {});
    analysisEngineDescription.getAnalysisEngineMetaData().getConfigurationParameterSettings()
            .setParameterValue(TextMarkerEngine.ADDITIONAL_SCRIPTS, additionalScriptsArray);

    String[] additionalEnginesArray = desc.getImportedEngines().toArray(new String[] {});
    analysisEngineDescription.getAnalysisEngineMetaData().getConfigurationParameterSettings()
            .setParameterValue(TextMarkerEngine.ADDITIONAL_ENGINES, additionalEnginesArray);

    String styleMapLocation = getStyleMapLocation(mainScript, engineOutput);
    analysisEngineDescription.getAnalysisEngineMetaData().getConfigurationParameterSettings()
            .setParameterValue(TextMarkerEngine.STYLE_MAP, styleMapLocation);
    analysisEngineDescription.getAnalysisEngineMetaData().setTypeSystem(typeSystemDescription);

    configureExtensions(option);
    return file;
  }

  private String getRelativeLocation(String target, String base) {
    IPath targetPath = Path.fromOSString(target);
    IPath basePath = Path.fromPortableString(base);
    IPath result = targetPath.makeRelativeTo(basePath);
    // TODO remove the first part! Should be correct in first place!
    result = result.removeFirstSegments(1);
    return result.toPortableString();
  }

  private void configureExtensions(TextMarkerBuildOptions options) {
    ConfigurationParameterSettings configurationParameterSettings = analysisEngineDescription
            .getAnalysisEngineMetaData().getConfigurationParameterSettings();

    List<String> languageExtensions = options.getLanguage();

    configurationParameterSettings.setParameterValue(TextMarkerEngine.ADDITIONAL_EXTENSIONS,
            languageExtensions.toArray(new String[0]));
    configurationParameterSettings.setParameterValue(TextMarkerEngine.ADDITIONAL_ENGINE_LOADERS,
            options.getEngines().toArray(new String[0]));
  }

  private String getPackage(String eachName) {
    if (eachName.endsWith(".tm")) {
      int indexOf = eachName.lastIndexOf(".tm");
      eachName = eachName.substring(0, indexOf);
    }
    int lastIndexOf = eachName.lastIndexOf(".");
    if (lastIndexOf != -1) {
      eachName = eachName.substring(0, lastIndexOf);
      return eachName;
    }
    return "";
  }

  private String getModuleName(String eachName) {
    if (eachName.endsWith(".tm")) {
      int indexOf = eachName.lastIndexOf(".tm");
      eachName = eachName.substring(0, indexOf);
    }
    int lastIndexOf = eachName.lastIndexOf(".");
    if (lastIndexOf != -1) {
      eachName = eachName.substring(lastIndexOf + 1, eachName.length());
    }
    return eachName;
  }

  private TypeSystemDescription getTypeSystemDescriptor(File file, TextMarkerBuildOptions option)
          throws InvalidXMLException, IOException {
    TypeSystemDescription tsdesc = UIMAFramework.getXMLParser().parseTypeSystemDescription(
            new XMLInputSource(file));
    if (option.isResolveImports()) {
      tsdesc.resolveImports();
    }
    return tsdesc;
  }

  private String getStyleMapLocation(String scriptLocation, String engineDescriptorOutputLocation) {
    String module = getModuleName(scriptLocation);
    String pack = getPackage(scriptLocation);
    return pack + "." + module + "StyleMap";
  }

  private File getFile(String location) {
    return new File(location);
  }

  private void toFile(XMLizable desc, File destination) throws SAXException, FileNotFoundException {
    destination.getParentFile().mkdirs();
    OutputStream out = new FileOutputStream(destination);
    XMLSerializer sax = new XMLSerializer(out);
    ContentHandler ch = sax.getContentHandler();
    ch.startDocument();
    desc.toXML(ch);
    ch.endDocument();
  }

}
