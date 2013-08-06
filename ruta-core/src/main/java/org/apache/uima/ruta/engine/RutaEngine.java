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

package org.apache.uima.ruta.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.FilterManager;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaModule;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.extensions.IEngineLoader;
import org.apache.uima.ruta.extensions.IRutaExtension;
import org.apache.uima.ruta.extensions.RutaEngineLoader;
import org.apache.uima.ruta.extensions.RutaExternalFactory;
import org.apache.uima.ruta.parser.RutaLexer;
import org.apache.uima.ruta.parser.RutaParser;
import org.apache.uima.ruta.seed.RutaAnnotationSeeder;
import org.apache.uima.ruta.verbalize.RutaVerbalizer;
import org.apache.uima.ruta.visitor.CreatedByVisitor;
import org.apache.uima.ruta.visitor.DebugInfoCollectorVisitor;
import org.apache.uima.ruta.visitor.InferenceCrowd;
import org.apache.uima.ruta.visitor.RutaInferenceVisitor;
import org.apache.uima.ruta.visitor.StatisticsVisitor;
import org.apache.uima.ruta.visitor.TimeProfilerVisitor;
import org.apache.uima.util.InvalidXMLException;

public class RutaEngine extends JCasAnnotator_ImplBase {

  public static final String SCRIPT_FILE_EXTENSION = ".ruta";

  public static final String SOURCE_DOCUMENT_INFORMATION = "org.apache.uima.examples.SourceDocumentInformation";

  public static final String BASIC_TYPE = "org.apache.uima.ruta.type.RutaBasic";

  public static final String OPTIONAL_TYPE = "org.apache.uima.ruta.type.RutaOptional";

  public static final String SEEDERS = "seeders";

  public static final String REMOVE_BASICS = "removeBasics";

  public static final String SCRIPT_PATHS = "scriptPaths";

  public static final String DESCRIPTOR_PATHS = "descriptorPaths";

  public static final String MAIN_SCRIPT = "mainScript";

  public static final String ADDITIONAL_SCRIPTS = "additionalScripts";

  public static final String ADDITIONAL_ENGINES = "additionalEngines";

  public static final String ADDITIONAL_UIMAFIT_ENGINES = "additionalUimafitEngines";

  public static final String ADDITIONAL_EXTENSIONS = "additionalExtensions";

  public static final String ADDITIONAL_ENGINE_LOADERS = "additionalEngineLoaders";

  public static final String CREATE_DEBUG_INFO = "debug";

  public static final String CREATE_DEBUG_INFO_ONLY_FOR = "debugOnlyFor";

  public static final String CREATE_PROFILING_INFO = "profile";

  public static final String CREATE_STATISTIC_INFO = "statistics";

  public static final String CREATE_CREATED_BY_INFO = "createdBy";

  public static final String CREATE_MATCH_DEBUG_INFO = "debugWithMatches";

  public static final String RESOURCE_PATHS = "resourcePaths";

  public static final String SCRIPT_ENCODING = "scriptEncoding";

  public static final String DEFAULT_FILTERED_TYPES = "defaultFilteredTypes";

  public static final String DYNAMIC_ANCHORING = "dynamicAnchoring";

  public static final String RELOAD_SCRIPT = "reloadScript";

  public static final String LOW_MEMORY_PROFILE = "lowMemoryProfile";

  public static final String SIMPLE_GREEDY_FOR_COMPOSED = "simpleGreedyForComposed";

  private String[] seeders;

  private Boolean createDebugInfo;

  private String[] createDebugOnlyFor;

  private Boolean createProfilingInfo;

  private Boolean createStatisticInfo;

  private Boolean withMatches;

  private String[] resourcePaths;

  private String scriptEncoding;

  private UimaContext context;

  private RutaModule script;

  private String[] additionalScriptLocations;

  private String[] additionalEngineLocations;

  private String[] additionalUimafitEngines;

  private String[] additionalExtensions;

  private String[] additionalEngineLoaders;

  private RutaExternalFactory factory;

  private RutaEngineLoader engineLoader;

  private String[] defaultFilteredTypes;

  private String mainScript;

  private String[] scriptPaths;

  private String[] descriptorPaths;

  private RutaVerbalizer verbalizer;

  private Boolean removeBasics;

  private Boolean dynamicAnchoring;

  private Boolean reloadScript;

  private Boolean lowMemoryProfile;

  private Boolean simpleGreedyForComposed;

  private Boolean createCreatedByInfo;

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
    additionalUimafitEngines = (String[]) aContext
            .getConfigParameterValue(ADDITIONAL_UIMAFIT_ENGINES);
    additionalExtensions = (String[]) aContext.getConfigParameterValue(ADDITIONAL_EXTENSIONS);
    additionalEngineLoaders = (String[]) aContext
            .getConfigParameterValue(ADDITIONAL_ENGINE_LOADERS);

    createDebugInfo = (Boolean) aContext.getConfigParameterValue(CREATE_DEBUG_INFO);
    createDebugOnlyFor = (String[]) aContext.getConfigParameterValue(CREATE_DEBUG_INFO_ONLY_FOR);
    createProfilingInfo = (Boolean) aContext.getConfigParameterValue(CREATE_PROFILING_INFO);
    createStatisticInfo = (Boolean) aContext.getConfigParameterValue(CREATE_STATISTIC_INFO);
    createCreatedByInfo = (Boolean) aContext.getConfigParameterValue(CREATE_CREATED_BY_INFO);
    withMatches = (Boolean) aContext.getConfigParameterValue(CREATE_MATCH_DEBUG_INFO);

    resourcePaths = (String[]) aContext.getConfigParameterValue(RESOURCE_PATHS);
    scriptEncoding = (String) aContext.getConfigParameterValue(SCRIPT_ENCODING);
    defaultFilteredTypes = (String[]) aContext.getConfigParameterValue(DEFAULT_FILTERED_TYPES);
    dynamicAnchoring = (Boolean) aContext.getConfigParameterValue(DYNAMIC_ANCHORING);
    reloadScript = (Boolean) aContext.getConfigParameterValue(RELOAD_SCRIPT);
    lowMemoryProfile = (Boolean) aContext.getConfigParameterValue(LOW_MEMORY_PROFILE);
    simpleGreedyForComposed = (Boolean) aContext
            .getConfigParameterValue(SIMPLE_GREEDY_FOR_COMPOSED);

    removeBasics = removeBasics == null ? false : removeBasics;
    createDebugInfo = createDebugInfo == null ? false : createDebugInfo;
    createDebugOnlyFor = createDebugOnlyFor == null ? new String[0] : createDebugOnlyFor;
    createProfilingInfo = createProfilingInfo == null ? false : createProfilingInfo;
    createStatisticInfo = createStatisticInfo == null ? false : createStatisticInfo;
    createCreatedByInfo = createCreatedByInfo == null ? false : createCreatedByInfo;
    withMatches = withMatches == null ? true : withMatches;

    scriptEncoding = scriptEncoding == null ? "UTF-8" : scriptEncoding;
    defaultFilteredTypes = defaultFilteredTypes == null ? new String[0] : defaultFilteredTypes;
    dynamicAnchoring = dynamicAnchoring == null ? false : dynamicAnchoring;
    reloadScript = reloadScript == null ? false : reloadScript;
    lowMemoryProfile = lowMemoryProfile == null ? false : lowMemoryProfile;
    simpleGreedyForComposed = simpleGreedyForComposed == null ? false : simpleGreedyForComposed;

    this.context = aContext;

    factory = new RutaExternalFactory();
    engineLoader = new RutaEngineLoader();
    verbalizer = new RutaVerbalizer();

    if (!factory.isInitialized()) {
      initializeExtensionWithClassPath();
    }
    if (!engineLoader.isInitialized()) {
      initializeEngineLoaderWithClassPath();
    }
    if (!reloadScript) {
      try {
        initializeScript(CAS.NAME_DEFAULT_SOFA);
      } catch (AnalysisEngineProcessException e) {
        throw new ResourceInitializationException(e);
      }
    }

  }

  @Override
  public void process(JCas jcas) throws AnalysisEngineProcessException {
    CAS cas = jcas.getCas();
    if (reloadScript || !cas.getViewName().equals(CAS.NAME_DEFAULT_SOFA)) {
      initializeScript(cas.getViewName());
    } else {
      resetEnvironments(cas);
    }
    if (!initialized || reloadScript) {
      initializeTypes(script, cas);
      initialized = true;
    }
    InferenceCrowd crowd = initializeCrowd();
    RutaStream stream = initializeStream(cas, crowd);
    stream.setDynamicAnchoring(dynamicAnchoring);
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
    Collection<RutaModule> scripts = script.getScripts().values();
    for (RutaModule module : scripts) {
      resetEnvironment(module, cas);
    }
  }

  private void resetEnvironment(RutaModule module, CAS cas) {
    RutaBlock block = module.getBlock(null);
    block.getEnvironment().reset(cas);
    Collection<RutaBlock> blocks = module.getBlocks().values();
    for (RutaBlock each : blocks) {
      each.getEnvironment().reset(cas);
    }
  }

  private void initializeTypes(RutaModule script, CAS cas) {
    // TODO find a better solution for telling everyone about the types!
    RutaBlock mainRootBlock = script.getBlock(null);
    mainRootBlock.getEnvironment().initializeTypes(cas);
    Collection<RutaModule> values = script.getScripts().values();
    for (RutaModule eachModule : values) {
      relinkEnvironments(eachModule, mainRootBlock, new ArrayList<RutaModule>());
      // initializeTypes(eachModule, cas);
    }
  }

  private void relinkEnvironments(RutaModule script, RutaBlock mainRootBlock,
          Collection<RutaModule> processed) {
    if (!processed.contains(script)) {
      processed.add(script);
      RutaBlock block = script.getBlock(null);
      block.setParent(mainRootBlock);
      Collection<RutaModule> innerScripts = script.getScripts().values();
      for (RutaModule module : innerScripts) {
        relinkEnvironments(module, mainRootBlock, processed);
      }
    }
  }

  private void initializeExtensionWithClassPath() {
    if (additionalExtensions == null) {
      return;
    }
    for (String each : additionalExtensions) {
      try {
        Class<?> forName = Class.forName(each);
        if (IRutaExtension.class.isAssignableFrom(forName)) {
          IRutaExtension extension = (IRutaExtension) forName.newInstance();
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
    List<RutaInferenceVisitor> visitors = new ArrayList<RutaInferenceVisitor>();
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
    if (createCreatedByInfo) {
      visitors.add(new CreatedByVisitor(verbalizer));
    }
    return new InferenceCrowd(visitors);
  }

  private RutaStream initializeStream(CAS cas, InferenceCrowd crowd)
          throws AnalysisEngineProcessException {
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
    RutaStream stream = new RutaStream(cas, basicType, filter, lowMemoryProfile,
            simpleGreedyForComposed, crowd);

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
          RutaAnnotationSeeder seeder = (RutaAnnotationSeeder) newInstance;
          result.add(seeder.seed(cas.getDocumentText(), cas));
        } catch (Exception e) {
          throw new AnalysisEngineProcessException(e);
        }
      }
    }
    return result;
  }

  private void initializeScript(String viewName) throws AnalysisEngineProcessException {
    if (mainScript == null) {
      return;
    }
    String scriptLocation = locate(mainScript, scriptPaths, SCRIPT_FILE_EXTENSION);
    if (scriptLocation == null) {
      try {
        String mainScriptPath = mainScript.replaceAll("\\.", "/") + SCRIPT_FILE_EXTENSION;
        script = loadScriptIS(mainScriptPath);
      } catch (IOException e) {
        throw new AnalysisEngineProcessException(new FileNotFoundException("Script [" + mainScript
                + "] cannot be found at [" + collectionToString(scriptPaths)
                + "] with extension .ruta"));
      } catch (RecognitionException e) {
        throw new AnalysisEngineProcessException(new FileNotFoundException("Script [" + mainScript
                + "] cannot be found at [" + collectionToString(scriptPaths)
                + "] with extension .ruta"));
      }
    } else {
      try {
        script = loadScript(scriptLocation);
      } catch (Exception e) {
        throw new AnalysisEngineProcessException(e);
      }
    }

    Map<String, RutaModule> additionalScripts = new HashMap<String, RutaModule>();
    Map<String, AnalysisEngine> additionalEngines = new HashMap<String, AnalysisEngine>();

    if (additionalUimafitEngines != null) {
      for (String eachUimafitEngine : additionalUimafitEngines) {
        AnalysisEngine eachEngine = null;
        try {
          @SuppressWarnings("unchecked")
          // Class clazz = this.getClass().getClassLoader().loadClass(eachUimafitEngine) ;
          Class<? extends AnalysisComponent> uimafitClass = (Class<? extends AnalysisComponent>) Class
                  .forName(eachUimafitEngine);
          eachEngine = AnalysisEngineFactory.createEngine(uimafitClass);
        } catch (ClassNotFoundException e) {
          throw new AnalysisEngineProcessException(e);
        } catch (ResourceInitializationException e) {
          throw new AnalysisEngineProcessException(e);
        }
        try {
          additionalEngines.put(eachUimafitEngine, eachEngine);
          String[] eachEngineLocationPartArray = eachUimafitEngine.split("\\.");
          if (eachEngineLocationPartArray.length > 1) {
            String shortEachEngineLocation = eachEngineLocationPartArray[eachEngineLocationPartArray.length - 1];
            additionalEngines.put(shortEachEngineLocation, eachEngine);
          }
        } catch (Exception e) {
          throw new AnalysisEngineProcessException(e);
        }
      }
    }
    if (additionalEngineLocations != null) {
      for (String eachEngineLocation : additionalEngineLocations) {
        AnalysisEngine eachEngine;
        String location = locate(eachEngineLocation, descriptorPaths, ".xml");
        if (location == null) {
          String locationIS = locateIS(eachEngineLocation, descriptorPaths, ".xml");
          try {
            eachEngine = engineLoader.loadEngineIS(locationIS, viewName);
          } catch (InvalidXMLException e) {
            throw new AnalysisEngineProcessException(new FileNotFoundException("Engine at ["
                    + eachEngineLocation + "] cannot be found in ["
                    + collectionToString(descriptorPaths)
                    + "] with extension .xml (from mainScript=" + mainScript + " in "
                    + collectionToString(scriptPaths)));
          } catch (ResourceInitializationException e) {
            throw new AnalysisEngineProcessException(new FileNotFoundException("Engine at ["
                    + eachEngineLocation + "] cannot be found in ["
                    + collectionToString(descriptorPaths)
                    + "] with extension .xml (from mainScript=" + mainScript + " in "
                    + collectionToString(scriptPaths)));
          } catch (IOException e) {
            throw new AnalysisEngineProcessException(new FileNotFoundException("Engine at ["
                    + eachEngineLocation + "] cannot be found in ["
                    + collectionToString(descriptorPaths)
                    + "] with extension .xml (from mainScript=" + mainScript + " in "
                    + collectionToString(scriptPaths)));
          } catch (ResourceConfigurationException e) {
            throw new AnalysisEngineProcessException(e);
          } catch (URISyntaxException e) {
            throw new AnalysisEngineProcessException(e);
          }
        } else {
          try {
            eachEngine = engineLoader.loadEngine(location, viewName);
          } catch (Exception e) {
            throw new AnalysisEngineProcessException(e);
          }
        }
        try {
          additionalEngines.put(eachEngineLocation, eachEngine);
          String[] eachEngineLocationPartArray = eachEngineLocation.split("\\.");
          if (eachEngineLocationPartArray.length > 1) {
            String shortEachEngineLocation = eachEngineLocationPartArray[eachEngineLocationPartArray.length - 1];
            additionalEngines.put(shortEachEngineLocation, eachEngine);
          }
        } catch (Exception e) {
          throw new AnalysisEngineProcessException(e);
        }
      }
    }

    if (additionalScriptLocations != null) {
      for (String add : additionalScriptLocations) {
        recursiveLoadScript(add, additionalScripts, additionalEngines, viewName);
      }
    }

    for (RutaModule each : additionalScripts.values()) {
      each.setScriptDependencies(additionalScripts);
    }
    script.setScriptDependencies(additionalScripts);

    for (RutaModule each : additionalScripts.values()) {
      each.setEngineDependencies(additionalEngines);
    }
    script.setEngineDependencies(additionalEngines);
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

  public static String locateIS(String name, String[] paths, String suffix) {
    return locateIS(name, paths, suffix, true);
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

  public static String locateIS(String name, String[] paths, String suffix, boolean mustExist) {
    if (name == null) {
      return null;
    }
    name = name.replaceAll("[.]", "/");
    return name + suffix;
  }

  private void recursiveLoadScript(String toLoad, Map<String, RutaModule> additionalScripts,
          Map<String, AnalysisEngine> additionalEngines, String viewName)
          throws AnalysisEngineProcessException {
    String location = locate(toLoad, scriptPaths, SCRIPT_FILE_EXTENSION);
    try {
      RutaModule eachScript = loadScript(location);
      additionalScripts.put(toLoad, eachScript);
      for (String add : eachScript.getScripts().keySet()) {
        if (!additionalScripts.containsKey(add)) {
          recursiveLoadScript(add, additionalScripts, additionalEngines, viewName);
        }
      }
      Set<String> engineKeySet = eachScript.getEngines().keySet();
      for (String eachEngineLocation : engineKeySet) {
        if (!additionalEngines.containsKey(eachEngineLocation)) {
          String engineLocation = locate(eachEngineLocation, descriptorPaths, ".xml");
          if (engineLocation == null) {
            String engineLocationIS = locateIS(eachEngineLocation, descriptorPaths, ".xml");
            try {
              AnalysisEngine eachEngine = engineLoader.loadEngineIS(engineLocationIS, viewName);
              additionalEngines.put(eachEngineLocation, eachEngine);
            } catch (Exception e) {
              // uimaFit engine?
              try {
                @SuppressWarnings("unchecked")
                Class<? extends AnalysisComponent> uimafitClass = (Class<? extends AnalysisComponent>) Class
                        .forName(eachEngineLocation);
                AnalysisEngine eachEngine = AnalysisEngineFactory.createEngine(uimafitClass);
                additionalEngines.put(eachEngineLocation, eachEngine);
              } catch (Exception e1) {
                throw new AnalysisEngineProcessException(e1);
              }
            }
          } else {
            try {
              AnalysisEngine eachEngine = engineLoader.loadEngine(engineLocation, viewName);
              additionalEngines.put(eachEngineLocation, eachEngine);
            } catch (Exception e) {
              throw new AnalysisEngineProcessException(e);
            }
          } 
        }
      }
    } catch (IOException e) {
      throw new AnalysisEngineProcessException(e);
    } catch (RecognitionException e) {
      throw new AnalysisEngineProcessException(e);
    }
  }

  private RutaModule loadScript(String scriptLocation) throws IOException, RecognitionException {
    File scriptFile = new File(scriptLocation);
    CharStream st = new ANTLRFileStream(scriptLocation, scriptEncoding);
    RutaLexer lexer = new RutaLexer(st);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    RutaParser parser = new RutaParser(tokens);
    parser.setExternalFactory(factory);
    parser.setResourcePaths(resourcePaths);
    String name = scriptFile.getName();
    int lastIndexOf = name.lastIndexOf(SCRIPT_FILE_EXTENSION);
    name = name.substring(0, lastIndexOf);
    RutaModule script = parser.file_input(name);
    return script;
  }

  private RutaModule loadScriptIS(String scriptLocation) throws IOException, RecognitionException {
    InputStream scriptInputStream = getClass().getClassLoader().getResourceAsStream(scriptLocation);
    CharStream st = new ANTLRInputStream(scriptInputStream, scriptEncoding);
    RutaLexer lexer = new RutaLexer(st);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    RutaParser parser = new RutaParser(tokens);
    parser.setExternalFactory(factory);
    parser.setResourcePaths(resourcePaths);
    String name = scriptLocation;
    int lastIndexOf = name.lastIndexOf(SCRIPT_FILE_EXTENSION);
    name = name.substring(0, lastIndexOf);
    RutaModule script = parser.file_input(name);
    return script;
  }

  public RutaExternalFactory getFactory() {
    return factory;
  }

  public RutaEngineLoader getEngineLoader() {
    return engineLoader;
  }

  private String collectionToString(Collection<?> collection) {
    StringBuilder collectionSB = new StringBuilder();
    collectionSB.append("{");
    for (Object element : collection) {
      collectionSB.append("[").append(element.toString()).append("]");
    }
    collectionSB.append("}");
    return collectionSB.toString();
  }

  private String collectionToString(Object[] collection) {
    return collectionToString(Arrays.asList(collection));
  }

  public void batchProcessComplete() throws AnalysisEngineProcessException {
    super.batchProcessComplete();
    Collection<AnalysisEngine> values = script.getEngines().values();
    for (AnalysisEngine each : values) {
      each.batchProcessComplete();
    }
  }

  public void collectionProcessComplete() throws AnalysisEngineProcessException {
    super.collectionProcessComplete();
    Collection<AnalysisEngine> values = script.getEngines().values();
    for (AnalysisEngine each : values) {
      each.collectionProcessComplete();
    }
  }

}
