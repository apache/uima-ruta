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

package org.apache.uima.textmarker.engine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.metadata.ConfigurationParameter;
import org.apache.uima.resource.metadata.ConfigurationParameterDeclarations;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.textmarker.FilterManager;
import org.apache.uima.textmarker.TextMarkerBlock;
import org.apache.uima.textmarker.TextMarkerModule;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.extensions.IEngineLoader;
import org.apache.uima.textmarker.extensions.ITextMarkerExtension;
import org.apache.uima.textmarker.extensions.TextMarkerEngineLoader;
import org.apache.uima.textmarker.extensions.TextMarkerExternalFactory;
import org.apache.uima.textmarker.parser.TextMarkerLexer;
import org.apache.uima.textmarker.parser.TextMarkerParser;
import org.apache.uima.textmarker.seed.TextMarkerAnnotationSeeder;
import org.apache.uima.textmarker.verbalize.TextMarkerVerbalizer;
import org.apache.uima.textmarker.visitor.DebugInfoCollectorVisitor;
import org.apache.uima.textmarker.visitor.InferenceCrowd;
import org.apache.uima.textmarker.visitor.StatisticsVisitor;
import org.apache.uima.textmarker.visitor.TextMarkerInferenceVisitor;
import org.apache.uima.textmarker.visitor.TimeProfilerVisitor;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;

public class TextMarkerEngine extends JCasAnnotator_ImplBase {

  public static final String SOURCE_DOCUMENT_INFORMATION = "org.apache.uima.examples.SourceDocumentInformation";

  public static final String BASIC_TYPE = "org.apache.uima.textmarker.type.TextMarkerBasic";

  public static final String SEEDERS = "seeders";

  public static final String REMOVE_BASICS = "removeBasics";

  public static final String SCRIPT_PATHS = "scriptPaths";

  public static final String DESCRIPTOR_PATHS = "descriptorPaths";

  public static final String MAIN_SCRIPT = "mainScript";

  public static final String ADDITIONAL_SCRIPTS = "additionalScripts";

  public static final String ADDITIONAL_ENGINES = "additionalEngines";

  public static final String ADDITIONAL_EXTENSIONS = "additionalExtensions";

  public static final String ADDITIONAL_ENGINE_LOADERS = "additionalEngineLoaders";

  public static final String CREATE_DEBUG_INFO = "debug";

  public static final String CREATE_DEBUG_INFO_ONLY_FOR = "debugOnlyFor";

  public static final String CREATE_PROFILING_INFO = "profile";

  public static final String CREATE_STATISTIC_INFO = "statistics";

  public static final String CREATE_MATCH_DEBUG_INFO = "debugWithMatches";

  public static final String RESOURCE_PATHS = "resourcePaths";

  public static final String SCRIPT_ENCODING = "scriptEncoding";

  public static final String DEFAULT_FILTERED_TYPES = "defaultFilteredTypes";

  public static final String DYNAMIC_ANCHORING = "dynamicAnchoring";

  public static final String RELOAD_SCRIPT = "reloadScript";

  private String[] seeders;

  private Boolean createDebugInfo;

  private String[] createDebugOnlyFor;

  private Boolean createProfilingInfo;

  private Boolean createStatisticInfo;

  private Boolean withMatches;

  private String[] resourcePaths;

  private String scriptEncoding;

  private UimaContext context;

  private TextMarkerModule script;

  private String[] additionalScriptLocations;

  private String[] additionalEngineLocations;

  private String[] additionalExtensions;

  private String[] additionalEngineLoaders;

  private TextMarkerExternalFactory factory;

  private TextMarkerEngineLoader engineLoader;

  private String[] defaultFilteredTypes;

  private String mainScript;

  private String[] scriptPaths;

  private String[] descriptorPaths;

  private TextMarkerVerbalizer verbalizer;

  private Boolean removeBasics;

  private Map<String, TypeSystemDescription> localTSDMap;

  private Boolean dynamicAnchoring;

  private Boolean reloadScript;

  private boolean initialized = false;

  private List<Type> seedTypes;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    if (aContext == null && context != null) {
      aContext = context;
    }
    seeders = (String[]) aContext.getConfigParameterValue(SEEDERS);
    removeBasics = (Boolean) aContext.getConfigParameterValue(REMOVE_BASICS);
    scriptPaths = (String[]) aContext.getConfigParameterValue(SCRIPT_PATHS);
    descriptorPaths = (String[]) aContext.getConfigParameterValue(DESCRIPTOR_PATHS);
    mainScript = (String) aContext.getConfigParameterValue(MAIN_SCRIPT);
    additionalScriptLocations = (String[]) aContext.getConfigParameterValue(ADDITIONAL_SCRIPTS);
    additionalEngineLocations = (String[]) aContext.getConfigParameterValue(ADDITIONAL_ENGINES);
    additionalExtensions = (String[]) aContext.getConfigParameterValue(ADDITIONAL_EXTENSIONS);
    additionalEngineLoaders = (String[]) aContext
            .getConfigParameterValue(ADDITIONAL_ENGINE_LOADERS);

    createDebugInfo = (Boolean) aContext.getConfigParameterValue(CREATE_DEBUG_INFO);
    createDebugOnlyFor = (String[]) aContext.getConfigParameterValue(CREATE_DEBUG_INFO_ONLY_FOR);
    createProfilingInfo = (Boolean) aContext.getConfigParameterValue(CREATE_PROFILING_INFO);
    createStatisticInfo = (Boolean) aContext.getConfigParameterValue(CREATE_STATISTIC_INFO);
    withMatches = (Boolean) aContext.getConfigParameterValue(CREATE_MATCH_DEBUG_INFO);

    resourcePaths = (String[]) aContext.getConfigParameterValue(RESOURCE_PATHS);
    scriptEncoding = (String) aContext.getConfigParameterValue(SCRIPT_ENCODING);
    defaultFilteredTypes = (String[]) aContext.getConfigParameterValue(DEFAULT_FILTERED_TYPES);
    dynamicAnchoring = (Boolean) aContext.getConfigParameterValue(DYNAMIC_ANCHORING);
    reloadScript = (Boolean) aContext.getConfigParameterValue(RELOAD_SCRIPT);

    removeBasics = removeBasics == null ? false : removeBasics;
    createDebugInfo = createDebugInfo == null ? false : createDebugInfo;
    createDebugOnlyFor = createDebugOnlyFor == null ? new String[0] : createDebugOnlyFor;
    createProfilingInfo = createProfilingInfo == null ? false : createProfilingInfo;
    createStatisticInfo = createStatisticInfo == null ? false : createStatisticInfo;
    withMatches = withMatches == null ? true : withMatches;

    scriptEncoding = scriptEncoding == null ? "UTF-8" : scriptEncoding;
    defaultFilteredTypes = defaultFilteredTypes == null ? new String[0] : defaultFilteredTypes;
    dynamicAnchoring = dynamicAnchoring == null ? false : dynamicAnchoring;
    reloadScript = reloadScript == null ? false : reloadScript;

    this.context = aContext;

    factory = new TextMarkerExternalFactory();
    engineLoader = new TextMarkerEngineLoader();
    verbalizer = new TextMarkerVerbalizer();

    localTSDMap = new HashMap<String, TypeSystemDescription>();

    if (!factory.isInitialized()) {
      initializeExtensionWithClassPath();
    }
    if (!engineLoader.isInitialized()) {
      initializeEngineLoaderWithClassPath();
    }
    if (!reloadScript) {
      try {
        initializeScript();
      } catch (AnalysisEngineProcessException e) {
        throw new ResourceInitializationException(e);
      }
    }
  }

  @Override
  public void process(JCas jcas) throws AnalysisEngineProcessException {
    CAS cas = jcas.getCas();
    if (reloadScript) {
      initializeScript();
    } else {
      resetEnvironments(cas);
    }
    if (!initialized || reloadScript) {
      initializeTypes(script, cas);
      initialized = true;
    }
    TextMarkerStream stream = initializeStream(cas);
    stream.setDynamicAnchoring(dynamicAnchoring);
    InferenceCrowd crowd = initializeCrowd();
    try {
      script.apply(stream, crowd);
    } catch (Throwable e) {
      throw new AnalysisEngineProcessException(AnalysisEngineProcessException.ANNOTATOR_EXCEPTION,
              new Object[] {}, e);
    }
    crowd.finished(stream);

    if (removeBasics) {
      List<AnnotationFS> toRemove = new ArrayList<AnnotationFS>();
      Type basicType = cas.getTypeSystem().getType(BASIC_TYPE);
      AnnotationIndex<AnnotationFS> basicIndex = cas.getAnnotationIndex(basicType);
      for (AnnotationFS fs : basicIndex) {
        toRemove.add(fs);
      }
      for (Type seedType : seedTypes) {
        AnnotationIndex<AnnotationFS> seedIndex = cas.getAnnotationIndex(seedType);
        for (AnnotationFS fs : seedIndex) {
          toRemove.add(fs);
        }
      }
      for (AnnotationFS annotationFS : toRemove) {
        cas.removeFsFromIndexes(annotationFS);
      }
    }
  }

  private void resetEnvironments(CAS cas) {
    resetEnvironment(script, cas);
    Collection<TextMarkerModule> scripts = script.getScripts().values();
    for (TextMarkerModule module : scripts) {
      resetEnvironment(module, cas);
    }
  }

  private void resetEnvironment(TextMarkerModule module, CAS cas) {
    TextMarkerBlock block = module.getBlock(null);
    block.getEnvironment().reset(cas);
    Collection<TextMarkerBlock> blocks = module.getBlocks().values();
    for (TextMarkerBlock each : blocks) {
      each.getEnvironment().reset(cas);
    }
  }

  private void initializeTypes(TextMarkerModule script, CAS cas) {
    // TODO find a better solution for telling everyone about the types!
    TextMarkerBlock mainRootBlock = script.getBlock(null);
    mainRootBlock.getEnvironment().initializeTypes(cas);
    Collection<TextMarkerModule> values = script.getScripts().values();
    for (TextMarkerModule eachModule : values) {
      relinkEnvironments(eachModule, mainRootBlock);
      // initializeTypes(eachModule, cas);
    }
  }

  private void relinkEnvironments(TextMarkerModule script, TextMarkerBlock mainRootBlock) {
    TextMarkerBlock block = script.getBlock(null);
    block.setParent(mainRootBlock);
    Collection<TextMarkerModule> innerScripts = script.getScripts().values();
    for (TextMarkerModule textMarkerModule : innerScripts) {
      relinkEnvironments(textMarkerModule, mainRootBlock);
    }
  }

  private void initializeExtensionWithClassPath() {
    if (additionalExtensions == null) {
      return;
    }
    for (String each : additionalExtensions) {
      try {
        Class<?> forName = Class.forName(each);
        if (ITextMarkerExtension.class.isAssignableFrom(forName)) {
          ITextMarkerExtension extension = (ITextMarkerExtension) forName.newInstance();
          verbalizer.addExternalVerbalizers(extension);
          for (String name : extension.getKnownExtensions()) {
            factory.addExtension(name, extension);
          }
        }
      } catch (Exception e) {
        // System.out.println("EXTENSION ERROR: " + each);
      }
    }
  }

  private void initializeEngineLoaderWithClassPath() {
    if (additionalEngineLoaders == null) {
      return;
    }
    for (String each : additionalEngineLoaders) {
      try {
        Class<?> forName = Class.forName(each);
        if (IEngineLoader.class.isAssignableFrom(forName)) {
          IEngineLoader loader = (IEngineLoader) forName.newInstance();
          for (String name : loader.getKnownEngines()) {
            engineLoader.addLoader(name, loader);
          }
        }
      } catch (Exception e) {
        // System.out.println("LOADER ERROR: " + each);
      }
    }
  }

  private InferenceCrowd initializeCrowd() {
    List<TextMarkerInferenceVisitor> visitors = new ArrayList<TextMarkerInferenceVisitor>();
    if (createDebugInfo) {
      visitors.add(new DebugInfoCollectorVisitor(createDebugInfo, withMatches, Arrays
              .asList(createDebugOnlyFor), verbalizer));
    }
    if (createProfilingInfo) {
      visitors.add(new TimeProfilerVisitor());
    }
    if (createStatisticInfo) {
      visitors.add(new StatisticsVisitor(verbalizer));
    }
    return new InferenceCrowd(visitors);
  }

  private TextMarkerStream initializeStream(CAS cas) throws AnalysisEngineProcessException {
    Collection<Type> filterTypes = new ArrayList<Type>();
    TypeSystem typeSystem = cas.getTypeSystem();
    for (String each : defaultFilteredTypes) {
      Type type = typeSystem.getType(each);
      if (type != null) {
        filterTypes.add(type);
      }
    }
    FilterManager filter = new FilterManager(filterTypes, cas);
    Type basicType = typeSystem.getType(BASIC_TYPE);
    seedTypes = seedAnnotations(cas);
    TextMarkerStream stream = new TextMarkerStream(cas, basicType, filter);
    stream.initalizeBasics();
    return stream;
  }

  private List<Type> seedAnnotations(CAS cas) throws AnalysisEngineProcessException {
    List<Type> result = new ArrayList<Type>();
    if (seeders != null) {
      for (String seederClass : seeders) {
        Class<?> loadClass = null;
        try {
          loadClass = Class.forName(seederClass);
        } catch (ClassNotFoundException e) {
          throw new AnalysisEngineProcessException(e);
        }
        Object newInstance = null;
        try {
          newInstance = loadClass.newInstance();
        } catch (Exception e) {
          throw new AnalysisEngineProcessException(e);
        }
        try {
          TextMarkerAnnotationSeeder seeder = (TextMarkerAnnotationSeeder) newInstance;
          result.add(seeder.seed(cas.getDocumentText(), cas));
        } catch (Exception e) {
          throw new AnalysisEngineProcessException(e);
        }
      }
    }
    return result;
  }

  private void initializeScript() throws AnalysisEngineProcessException {
    String scriptLocation = locate(mainScript, scriptPaths, ".tm");
    if (scriptLocation == null) {
      // if someone loads an empty analysis engine and then reconfigures it
      return;
    }
    try {
      script = loadScript(scriptLocation, null);
    } catch (Exception e) {
      throw new AnalysisEngineProcessException(e);
    }
    Map<String, TextMarkerModule> additionalScripts = new HashMap<String, TextMarkerModule>();
    Map<String, AnalysisEngine> additionalEngines = new HashMap<String, AnalysisEngine>();

    if (additionalEngineLocations != null) {
      for (String eachEngineLocation : additionalEngineLocations) {
        String location = locate(eachEngineLocation, descriptorPaths, ".xml");
        try {
          AnalysisEngine eachEngine = engineLoader.loadEngine(location);
          configureEngine(eachEngine);
          additionalEngines.put(eachEngineLocation, eachEngine);
        } catch (Exception e) {
          throw new AnalysisEngineProcessException(e);
        }
      }
    }

    if (additionalScriptLocations != null) {
      for (String add : additionalScriptLocations) {
        recursiveLoadScript(add, additionalScripts, additionalEngines);
      }
    }
    for (TextMarkerModule each : additionalScripts.values()) {
      each.setScriptDependencies(additionalScripts);
    }
    script.setScriptDependencies(additionalScripts);

    for (TextMarkerModule each : additionalScripts.values()) {
      each.setEngineDependencies(additionalEngines);
    }
    script.setEngineDependencies(additionalEngines);
  }

  private void configureEngine(AnalysisEngine engine) throws ResourceConfigurationException {
    ConfigurationParameterDeclarations configurationParameterDeclarations = engine
            .getAnalysisEngineMetaData().getConfigurationParameterDeclarations();
    ConfigurationParameter configurationParameter = configurationParameterDeclarations
            .getConfigurationParameter(null, DESCRIPTOR_PATHS);
    if (configurationParameter != null) {
      engine.setConfigParameterValue(DESCRIPTOR_PATHS, descriptorPaths);
      engine.reconfigure();
    }
    configurationParameter = configurationParameterDeclarations.getConfigurationParameter(null,
            StyleMapCreator.STYLE_MAP);
    if (configurationParameter != null) {
      engine.setConfigParameterValue(StyleMapCreator.STYLE_MAP, mainScript + "StyleMap");
      engine.reconfigure();
    }

  }

  public static void addSourceDocumentInformation(CAS cas, File each) {
    Type sdiType = cas.getTypeSystem()
            .getType("org.apache.uima.examples.SourceDocumentInformation");
    if (sdiType != null) {
      if (cas.getAnnotationIndex(sdiType).size() == 0) {
        AnnotationFS sdi = cas.createAnnotation(sdiType, cas.getDocumentAnnotation().getBegin(),
                cas.getDocumentAnnotation().getEnd());
        Feature uriFeature = sdiType.getFeatureByBaseName("uri");
        sdi.setStringValue(uriFeature, each.toURI().getPath());
        cas.addFsToIndexes(sdi);
      }
    }
  }

  public static void removeSourceDocumentInformation(CAS cas) {
    Type sdiType = cas.getTypeSystem()
            .getType("org.apache.uima.examples.SourceDocumentInformation");
    if (sdiType != null) {
      AnnotationIndex<AnnotationFS> annotationIndex = cas.getAnnotationIndex(sdiType);
      List<AnnotationFS> toRemove = new ArrayList<AnnotationFS>();
      for (AnnotationFS annotationFS : annotationIndex) {
        toRemove.add(annotationFS);
      }
      for (AnnotationFS annotationFS : toRemove) {
        cas.removeFsFromIndexes(annotationFS);
      }
    }
  }

  public static String locate(String name, String[] paths, String suffix) {
    return locate(name, paths, suffix, true);
  }

  public static String locate(String name, String[] paths, String suffix, boolean mustExist) {
    if (name == null || paths == null) {
      return null;
    }
    name = name.replaceAll("[.]", "/");
    for (String each : paths) {
      File file = new File(each, name + suffix);
      if (!mustExist || file.exists()) {
        return file.getAbsolutePath();
      }
    }
    return null;
  }

  private void recursiveLoadScript(String toLoad, Map<String, TextMarkerModule> additionalScripts,
          Map<String, AnalysisEngine> additionalEngines) throws AnalysisEngineProcessException {
    String location = locate(toLoad, scriptPaths, ".tm");
    try {
      TypeSystemDescription localTSD = getLocalTSD(toLoad);
      TextMarkerModule eachScript = loadScript(location, localTSD);
      additionalScripts.put(toLoad, eachScript);
      for (String add : eachScript.getScripts().keySet()) {
        if (!additionalScripts.containsKey(add)) {
          recursiveLoadScript(add, additionalScripts, additionalEngines);
        }
      }
      Set<String> engineKeySet = eachScript.getEngines().keySet();
      for (String eachEngineLocation : engineKeySet) {
        if (!additionalEngines.containsKey(eachEngineLocation)) {
          String engineLocation = locate(eachEngineLocation, descriptorPaths, ".xml");
          try {
            AnalysisEngine eachEngine = engineLoader.loadEngine(engineLocation);
            additionalEngines.put(eachEngineLocation, eachEngine);
          } catch (Exception e) {
            throw new AnalysisEngineProcessException(e);
          }
        }
      }
    } catch (IOException e) {
      throw new AnalysisEngineProcessException(e);
    } catch (RecognitionException e) {
      throw new AnalysisEngineProcessException(e);
    } catch (InvalidXMLException e) {
      throw new AnalysisEngineProcessException(e);
    }
  }

  private TypeSystemDescription getLocalTSD(String toLoad) throws InvalidXMLException, IOException {
    TypeSystemDescription localTSD = localTSDMap.get(toLoad);
    if (localTSD == null) {
      String locateTSD = locate(toLoad, descriptorPaths, "TypeSystem.xml", true);
      if (locateTSD != null) {
        localTSD = UIMAFramework.getXMLParser().parseTypeSystemDescription(
                new XMLInputSource(locateTSD));
        ResourceManager resMgr = UIMAFramework.newDefaultResourceManager();
        resMgr.setDataPath(getDataPath());
        localTSD.resolveImports(resMgr);
        localTSDMap.put(toLoad, localTSD);
      }
    }
    return localTSD;
  }

  private String getDataPath() {
    String result = "";
    String sep = System.getProperty("path.separator");
    for (String each : descriptorPaths) {
      result += each + sep;
    }
    result = result.substring(0, result.length() - 1);
    return result;
  }

  private TextMarkerModule loadScript(String scriptLocation, TypeSystemDescription localTSD)
          throws IOException, RecognitionException {
    File scriptFile = new File(scriptLocation);
    CharStream st = new ANTLRFileStream(scriptLocation, scriptEncoding);
    TextMarkerLexer lexer = new TextMarkerLexer(st);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    TextMarkerParser parser = new TextMarkerParser(tokens);
    parser.setLocalTSD(localTSD);
    parser.setExternalFactory(factory);
    parser.setResourcePaths(resourcePaths);
    String name = scriptFile.getName();
    int lastIndexOf = name.lastIndexOf(".tm");
    name = name.substring(0, lastIndexOf);
    TextMarkerModule script = parser.file_input(name);
    return script;
  }

  public TextMarkerExternalFactory getFactory() {
    return factory;
  }

  public TextMarkerEngineLoader getEngineLoader() {
    return engineLoader;
  }

}
