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

package org.apache.uima.ruta.descriptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.ResourceSpecifierFactory;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.impl.ResourceManager_impl;
import org.apache.uima.resource.metadata.Capability;
import org.apache.uima.resource.metadata.ConfigurationParameterSettings;
import org.apache.uima.resource.metadata.FsIndexDescription;
import org.apache.uima.resource.metadata.Import;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.resource.metadata.impl.Import_impl;
import org.apache.uima.ruta.UIMAConstants;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.apache.uima.util.XMLSerializer;
import org.apache.uima.util.XMLizable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class RutaDescriptorBuilder {

  private static ResourceSpecifierFactory uimaFactory = UIMAFramework.getResourceSpecifierFactory();

  private final URL defaultTypeSystem;

  private final URL defaultAnalysisEngine;

  public RutaDescriptorBuilder(URL defaultTypeSystem, URL defaultAnalysisEngine)
          throws InvalidXMLException, IOException {
    super();
    this.defaultTypeSystem = defaultTypeSystem;
    this.defaultAnalysisEngine = defaultAnalysisEngine;
  }

  public TypeSystemDescription createTypeSystemDescription(RutaDescriptorInformation desc,
          String typeSystemOutput, RutaBuildOptions options, String[] enginePaths)
          throws InvalidXMLException, ResourceInitializationException, IOException,
          URISyntaxException {

    TypeSystemDescription typeSystemDescription = uimaFactory.createTypeSystemDescription();

    ResourceManager rm = UIMAFramework.newDefaultResourceManager();
    if (options.getClassLoader() != null) {
      rm = new ResourceManager_impl(options.getClassLoader());
    }
    if (enginePaths != null) {
      String dataPath = "";
      for (String string : enginePaths) {
        dataPath += string + File.pathSeparator;
      }
      rm.setDataPath(dataPath);
    }
    Map<String, String> typeNameMap = new HashMap<String, String>();
    TypeSystemDescription initialTypeSystem = UIMAFramework.getXMLParser()
            .parseTypeSystemDescription(new XMLInputSource(defaultTypeSystem));
    CAS cas = CasCreationUtils.createCas(initialTypeSystem, null, new FsIndexDescription[0]);
    fillTypeNameMap(typeNameMap, cas.getTypeSystem());
    cas.release();
    List<TypeSystemDescription> toInclude = new ArrayList<TypeSystemDescription>();
    List<Import> importList = new ArrayList<Import>();
    Import_impl import_impl = new Import_impl();
    if (options.isImportByName()) {
      String name = initialTypeSystem.getName();
      import_impl.setName(name);
    } else if (options.isResolveImports()) {
      String absoluteLocation = initialTypeSystem.getSourceUrlString();
      import_impl.setLocation(absoluteLocation);
    } else {
      URI uri = null;
      try {
        uri = defaultTypeSystem.toURI();
      } catch (URISyntaxException e) {
        // do nothing
      }
      if (uri != null) {
        String relativeLocation = getRelativeLocation(uri, typeSystemOutput);
        if (relativeLocation != null) {
          import_impl.setLocation(relativeLocation);
        } else {
          toInclude.add(initialTypeSystem);
        }
      } else {
        toInclude.add(initialTypeSystem);
      }
    }
    if (import_impl.getLocation() != null || import_impl.getName() != null) {
      importList.add(import_impl);
    }
    for (String eachName : desc.getImportedTypeSystems()) {
      String locate = RutaEngine.locate(eachName, enginePaths, ".xml");
      URL url = null;
      boolean include = false;
      if (locate != null) {
        File file = new File(locate);
        url = file.toURI().toURL();
      }
      if (url == null) {
        url = checkImportExistence(eachName, ".xml", options.getClassLoader());
        include = true;
        if (url == null) {
          throw new FileNotFoundException("Build process can't find " + eachName + " in "
                  + desc.getScriptName());
        }
      }
      TypeSystemDescription each = getTypeSystemDescriptor(url, options, rm);
      if (each != null) {
        fillTypeNameMap(typeNameMap, each);
        if (include) {
          // need to include the complete type system because an import is not possible
          each.resolveImports(rm);
          toInclude.add(each);
        } else {
          import_impl = new Import_impl();
          if (options.isImportByName()) {
            import_impl.setName(eachName);
          } else if (options.isResolveImports()) {
            String absoluteLocation = each.getSourceUrlString();
            import_impl.setLocation(absoluteLocation);
          } else {
            String relativeLocation = getRelativeLocation(url.toURI(), typeSystemOutput);
            File parentFile = new File(typeSystemOutput).getParentFile();
            File targetFile = new File(parentFile, relativeLocation);
            boolean ableToFindFile = targetFile.exists();
            if (!ableToFindFile) {
              // hotfix for different partitions making trouble for the relative path
              import_impl.setName(eachName);
            } else {
              import_impl.setLocation(relativeLocation);
            }
          }
          importList.add(import_impl);
        }
      } else {
        throw new FileNotFoundException("Build process can't find " + eachName + " in "
                + desc.getScriptName());
      }
    }
    for (String eachName : desc.getImportedScripts()) {
      String locate = RutaEngine.locate(eachName, enginePaths, options.getTypeSystemSuffix()
              + ".xml");
      URL url = null;
      if (locate != null) {
        File file = new File(locate);
        url = file.toURI().toURL();
      }
      if (url == null) {
        url = checkImportExistence(eachName, options.getTypeSystemSuffix() + ".xml",
                options.getClassLoader());
        if (url == null) {
          throw new FileNotFoundException("Build process can't find " + eachName
                  + options.getTypeSystemSuffix() + ".xml" + " in " + desc.getScriptName());
        }
      }
      TypeSystemDescription each = getTypeSystemDescriptor(url, options, rm);
      if (each != null) {
        fillTypeNameMap(typeNameMap, each);
        import_impl = new Import_impl();
        if (options.isImportByName()) {
          import_impl.setName(eachName + options.getTypeSystemSuffix());
        } else if (options.isResolveImports()) {
          String absoluteLocation = each.getSourceUrlString();
          import_impl.setLocation(absoluteLocation);
        } else {
          String relativeLocation = getRelativeLocation(url.toURI(), typeSystemOutput);
          import_impl.setLocation(relativeLocation);
        }
        importList.add(import_impl);
      } else {
        throw new FileNotFoundException("Build process can't find " + eachName + " in "
                + desc.getScriptName());
      }
    }
    typeSystemDescription = CasCreationUtils.mergeTypeSystems(toInclude, rm);
    if (!importList.isEmpty()) {
      Import[] newImports = importList.toArray(new Import[0]);
      typeSystemDescription.setImports(newImports);
    }
    if (options.isResolveImports()) {
      typeSystemDescription.resolveImports(rm);
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
      typeTriple = resolveType(typeTriple, typeNameMap, desc.getScriptName());
      if (typeSystemDescription.getType(typeTriple.getName()) != null) {
        continue;
      }
      if (!finalTypes.contains(typeTriple.getParent())) {
        TypeDescription newType = typeSystemDescription.addType(typeTriple.getName(),
                typeTriple.getDescription(), typeTriple.getParent());

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
    Collection<TypeDescription> types = new HashSet<TypeDescription>();
    for (TypeDescription each : typeSystemDescription.getTypes()) {
      String name = each.getName();
      if (!names.contains(name)) {
        names.add(name);
        types.add(each);
      }
    }

    TypeDescription[] presentTypes = typeSystemDescription.getTypes();

    types.addAll(Arrays.asList(presentTypes));
    typeSystemDescription.setTypes(types.toArray(new TypeDescription[0]));
    typeSystemDescription.setName(desc.getPackageString() + "." + desc.getScriptName() + options.getTypeSystemSuffix());
    if (typeSystemOutput != null) {
      File typeSystemFile = getFile(typeSystemOutput);
      typeSystemDescription.setSourceUrl(typeSystemFile.toURI().toURL());
    }
    return typeSystemDescription;
  }

  public AnalysisEngineDescription createAnalysisEngineDescription(RutaDescriptorInformation desc,
          TypeSystemDescription typeSystemDescription, String typeSystemOutput,
          String engineOutput, RutaBuildOptions options, String[] scriptPaths,
          String[] enginePaths, String[] resourcePaths) throws InvalidXMLException, IOException {
    TypeSystemDescription aets = uimaFactory.createTypeSystemDescription();
    Import_impl import_impl = new Import_impl();
    if (options.isImportByName()) {
      if (typeSystemDescription != null) {
        import_impl.setName(typeSystemDescription.getName());
      }
    } else {
      if (typeSystemOutput != null) {
        String relativeLocation = getRelativeLocation(new File(typeSystemOutput).toURI(),
                engineOutput);
        import_impl.setLocation(relativeLocation);
      }
    }

    return configureEngine(desc, engineOutput, options, scriptPaths, enginePaths, resourcePaths,
            import_impl, aets);
  }

  public void build(RutaDescriptorInformation desc, String typeSystemOutput, String engineOutput,
          RutaBuildOptions options, String[] scriptPaths, String[] enginePaths)
          throws SAXException, InvalidXMLException, IOException, ResourceInitializationException,
          URISyntaxException {

    TypeSystemDescription typeSystemDescription = createTypeSystemDescription(desc,
            typeSystemOutput, options, enginePaths);

    AnalysisEngineDescription analysisEngineDescription = createAnalysisEngineDescription(desc,
            typeSystemDescription, typeSystemOutput, engineOutput, options, scriptPaths,
            enginePaths, null);

    File analysisEngineFile = getFile(engineOutput);
    File typeSystemFile = getFile(typeSystemOutput);
    toFile(typeSystemDescription, typeSystemFile);
    toFile(analysisEngineDescription, analysisEngineFile);
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
      return UIMAConstants.TYPE_STRING;
    } else if (name.equals("INT")) {
      return UIMAConstants.TYPE_INTEGER;
    } else if (name.equals("DOUBLE")) {
      return UIMAConstants.TYPE_DOUBLE;
    } else if (name.equals("FLOAT")) {
      return UIMAConstants.TYPE_FLOAT;
    } else if (name.equals("BOOLEAN")) {
      return UIMAConstants.TYPE_BOOLEAN;
    } else if (name.equals("TYPE")) {
      return UIMAConstants.TYPE_STRING;
    }
    return name;
  }

  private AnalysisEngineDescription configureEngine(RutaDescriptorInformation desc,
          String engineOutput, RutaBuildOptions option, String[] scriptPaths,
          String[] descriptorPaths, String[] resourcePaths, Import_impl import_impl,
          TypeSystemDescription aets) throws InvalidXMLException, IOException {

    AnalysisEngineDescription analysisEngineDescription = UIMAFramework.getXMLParser()
            .parseAnalysisEngineDescription(new XMLInputSource(defaultAnalysisEngine));
    aets.setImports(new Import[] { import_impl });
    analysisEngineDescription.getAnalysisEngineMetaData().setTypeSystem(aets);

    File file = getFile(engineOutput);
    analysisEngineDescription.setSourceUrl(file.toURI().toURL());

    if (!desc.getTypeShortNames().isEmpty()) {
      Capability capability = uimaFactory.createCapability();
      for (StringTriple typeTriple : desc.getTypeTriples()) {
        capability.addInputType(typeTriple.getName(), false);
        capability.addOutputType(typeTriple.getName(), false);
      }
      Capability[] capabilities = analysisEngineDescription.getAnalysisEngineMetaData()
              .getCapabilities();
      Capability[] newArray = new Capability[capabilities.length + 1];
      System.arraycopy(capabilities, 0, newArray, 0, capabilities.length);
      newArray[capabilities.length] = capability;
      analysisEngineDescription.getAnalysisEngineMetaData().setCapabilities(newArray);
    }

    String mainScript = desc.getScriptName();
    if (!StringUtils.isBlank(desc.getPackageString())) {
      mainScript = desc.getPackageString().concat(".").concat(mainScript);
    }
    analysisEngineDescription.getAnalysisEngineMetaData().getConfigurationParameterSettings()
            .setParameterValue(RutaEngine.PARAM_MAIN_SCRIPT, mainScript);
    if (scriptPaths != null) {
      analysisEngineDescription.getAnalysisEngineMetaData().getConfigurationParameterSettings()
              .setParameterValue(RutaEngine.PARAM_SCRIPT_PATHS, scriptPaths);
    }
    if (descriptorPaths != null) {
      analysisEngineDescription.getAnalysisEngineMetaData().getConfigurationParameterSettings()
              .setParameterValue(RutaEngine.PARAM_DESCRIPTOR_PATHS, descriptorPaths);
    }
    String[] parameterValue = (String[]) analysisEngineDescription.getAnalysisEngineMetaData()
            .getConfigurationParameterSettings().getParameterValue(RutaEngine.PARAM_RESOURCE_PATHS);
    Set<String> resourceLocations = new HashSet<String>();

    if (parameterValue != null && parameterValue.length != 0) {
      resourceLocations.addAll(Arrays.asList(parameterValue));
    }
    if (resourcePaths != null) {
      resourceLocations.addAll(Arrays.asList(resourcePaths));
    }

    analysisEngineDescription
            .getAnalysisEngineMetaData()
            .getConfigurationParameterSettings()
            .setParameterValue(RutaEngine.PARAM_RESOURCE_PATHS,
                    resourceLocations.toArray(new String[0]));

    String[] additionalScriptsArray = desc.getImportedScripts().toArray(new String[] {});
    analysisEngineDescription.getAnalysisEngineMetaData().getConfigurationParameterSettings()
            .setParameterValue(RutaEngine.PARAM_ADDITIONAL_SCRIPTS, additionalScriptsArray);

    String[] additionalEnginesArray = desc.getImportedEngines().toArray(new String[] {});
    analysisEngineDescription.getAnalysisEngineMetaData().getConfigurationParameterSettings()
            .setParameterValue(RutaEngine.PARAM_ADDITIONAL_ENGINES, additionalEnginesArray);

    String[] additionalUimafitEnginesArray = desc.getImportedUimafitEngines().toArray(
            new String[] {});
    analysisEngineDescription
            .getAnalysisEngineMetaData()
            .getConfigurationParameterSettings()
            .setParameterValue(RutaEngine.PARAM_ADDITIONAL_UIMAFIT_ENGINES,
                    additionalUimafitEnginesArray);

    analysisEngineDescription.getAnalysisEngineMetaData().setTypeSystem(aets);

    configureExtensions(analysisEngineDescription, option);
    return analysisEngineDescription;
  }

  private String getRelativeLocation(URI target, String base) {
    if (base == null) {
      return null;
    }
    Path basePath = Paths.get(base);
    if (!basePath.toFile().isDirectory()) {
      basePath = basePath.getParent();
    }
    Path targetPath = null;
    try {
      targetPath = Paths.get(target);
    } catch (Exception e) {
      return null;
    }
    Path relativePath = null;
    try {
      relativePath = basePath.relativize(targetPath);
    } catch (Exception e) {
      return null;
    }
    // HOTFIX: avoid windows paths. No generic solution to access a portable string found yet for
    // Path
    String result = relativePath.toString().replaceAll("\\\\", "/");
    return result;
  }

  private void configureExtensions(AnalysisEngineDescription analysisEngineDescription,
          RutaBuildOptions options) {
    ConfigurationParameterSettings configurationParameterSettings = analysisEngineDescription
            .getAnalysisEngineMetaData().getConfigurationParameterSettings();

    List<String> languageExtensions = options.getLanguageExtensions();

    String[] extensions = (String[]) configurationParameterSettings
            .getParameterValue(RutaEngine.PARAM_ADDITIONAL_EXTENSIONS);
    String[] loaders = (String[]) configurationParameterSettings
            .getParameterValue(RutaEngine.PARAM_ADDITIONAL_ENGINE_LOADERS);

    List<String> es = new ArrayList<String>();
    List<String> ls = new ArrayList<String>();
    if (extensions != null) {
      es.addAll(Arrays.asList(extensions));
    }
    es.addAll(languageExtensions);
    if (loaders != null) {
      ls.addAll(Arrays.asList(loaders));
    }
    ls.addAll(options.getEngineLoaders());

    configurationParameterSettings.setParameterValue(RutaEngine.PARAM_ADDITIONAL_EXTENSIONS,
            es.toArray(new String[0]));
    configurationParameterSettings.setParameterValue(RutaEngine.PARAM_ADDITIONAL_ENGINE_LOADERS,
            ls.toArray(new String[0]));
  }

  private TypeSystemDescription getTypeSystemDescriptor(URL url, RutaBuildOptions option,
          ResourceManager rm) throws InvalidXMLException, IOException {
    TypeSystemDescription tsdesc = UIMAFramework.getXMLParser().parseTypeSystemDescription(
            new XMLInputSource(url));
    if (option.isResolveImports()) {
      tsdesc.resolveImports(rm);
    }
    return tsdesc;
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

  public static URL checkImportExistence(String candidate, String extension, ClassLoader classloader)
          throws IOException {
    String p = candidate.replaceAll("[.]", "/");
    p += extension;
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(
            classloader);
    String prefix = "classpath*:";
    String pattern = prefix + p;
    Resource[] resources = resolver.getResources(pattern);
    if (resources == null || resources.length == 0) {
      return null;
    } else {
      Resource resource = resources[0];
      URL url = resource.getURL();
      return url;
    }
  }
}
