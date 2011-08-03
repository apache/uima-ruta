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

package org.apache.uima.tm.textmarker.engine;

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
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.tm.textmarker.kernel.FilterManager;
import org.apache.uima.tm.textmarker.kernel.TextMarkerModule;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.extensions.IEngineLoader;
import org.apache.uima.tm.textmarker.kernel.extensions.ITextMarkerExtension;
import org.apache.uima.tm.textmarker.kernel.extensions.TextMarkerEngineLoader;
import org.apache.uima.tm.textmarker.kernel.extensions.TextMarkerExternalFactory;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.DebugInfoCollectorVisitor;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;
import org.apache.uima.tm.textmarker.kernel.visitor.StatisticsVisitor;
import org.apache.uima.tm.textmarker.kernel.visitor.TextMarkerInferenceVisitor;
import org.apache.uima.tm.textmarker.kernel.visitor.TimeProfilerVisitor;
import org.apache.uima.tm.textmarker.parser.TextMarkerLexer;
import org.apache.uima.tm.textmarker.parser.TextMarkerParser;
import org.apache.uima.tm.textmarker.seed.TextMarkerAnnotationSeeder;
import org.apache.uima.tm.textmarker.verbalize.TextMarkerVerbalizer;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;


public class TextMarkerEngine extends JCasAnnotator_ImplBase {

  public static final String BASIC_TYPE = "org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic";

  public static final String SEEDERS = "seeders";

  public static final String USE_BASICS = "useBasics";

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

  public static final String CREATE_STYLE_MAP = "style";

  public static final String STYLE_MAP = "styleMap";

  public static final String RESOURCE_PATHS = "resourcePaths";

  public static final String SCRIPT_ENCODING = "scriptEncoding";

  public static final String MODIFIED_SOFA = "modified";

  public static final String DEFAULT_FILTERED_TYPES = "defaultFilteredTypes";

  public static final String DEFAULT_FILTERED_MARKUPS = "defaultFilteredMarkups";

  private String[] seeders;

  private String useBasics;

  private Boolean createDebugInfo;

  private String[] createDebugOnlyFor;

  private Boolean createProfilingInfo;

  private Boolean createStatisticInfo;

  private String styleMapLocation;

  private Boolean createStyleMap;

  private Boolean withMatches;

  private String[] resourcePaths;

  private String scriptEncoding;

  private UimaContext context;

  private TextMarkerModule script;

  private StyleMapFactory styleMapFactory;

  private String[] additionalScriptLocations;

  private String[] additionalEngineLocations;

  private String[] additionalExtensions;

  private String[] additionalEngineLoaders;

  private TextMarkerExternalFactory factory;

  private TextMarkerEngineLoader engineLoader;

  private String[] defaultFilteredTypes;

  private String[] defaultFilteredMarkups;

  private String mainScript;

  private String[] scriptPaths;

  private String[] descriptorPaths;

  private TextMarkerVerbalizer verbalizer;

  private Boolean removeBasics;

  private Map<String, TypeSystemDescription> localTSDMap;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    if (aContext == null && context != null) {
      aContext = context;
    }
    seeders = (String[]) aContext.getConfigParameterValue(SEEDERS);
    useBasics = (String) aContext.getConfigParameterValue(USE_BASICS);
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
    createStyleMap = (Boolean) aContext.getConfigParameterValue(CREATE_STYLE_MAP);
    styleMapLocation = (String) aContext.getConfigParameterValue(STYLE_MAP);
    resourcePaths = (String[]) aContext.getConfigParameterValue(RESOURCE_PATHS);
    scriptEncoding = (String) aContext.getConfigParameterValue(SCRIPT_ENCODING);
    defaultFilteredTypes = (String[]) aContext.getConfigParameterValue(DEFAULT_FILTERED_TYPES);
    defaultFilteredMarkups = (String[]) aContext.getConfigParameterValue(DEFAULT_FILTERED_MARKUPS);

    styleMapFactory = new StyleMapFactory();

    removeBasics = removeBasics == null ? false : removeBasics;
    createDebugInfo = createDebugInfo == null ? false : createDebugInfo;
    createDebugOnlyFor = createDebugOnlyFor == null ? new String[0] : createDebugOnlyFor;
    createProfilingInfo = createProfilingInfo == null ? false : createProfilingInfo;
    createStatisticInfo = createStatisticInfo == null ? false : createStatisticInfo;
    withMatches = withMatches == null ? true : withMatches;
    createStyleMap = createStyleMap == null ? true : createStyleMap;
    scriptEncoding = scriptEncoding == null ? "UTF-8" : scriptEncoding;
    defaultFilteredTypes = defaultFilteredTypes == null ? new String[0] : defaultFilteredTypes;
    defaultFilteredMarkups = defaultFilteredMarkups == null ? new String[0]
            : defaultFilteredMarkups;

    this.context = aContext;

    factory = new TextMarkerExternalFactory();
    engineLoader = new TextMarkerEngineLoader();
    verbalizer = new TextMarkerVerbalizer();

    localTSDMap = new HashMap<String, TypeSystemDescription>();
  }

  @Override
  public void process(JCas cas) throws AnalysisEngineProcessException {
    // TODO fix single/multi view stuff
    if (cas.getViewName() == null) {
      try {
        cas = cas.getView(CAS.NAME_DEFAULT_SOFA);
      } catch (CASException e) {
        throw new AnalysisEngineProcessException(e);
      }
    }

    if (!factory.isInitialized()) {
      initializeExtensionWithClassPath();
    }
    if (!engineLoader.isInitialized()) {
      initializeEngineLoaderWithClassPath();
    }
    initializeScript(cas.getCas());
    TextMarkerStream stream = initializeStream(cas.getCas());

    InferenceCrowd crowd = initializeCrowd();
    try {
      script.apply(stream, crowd);
    } catch (Throwable e) {
      throw new AnalysisEngineProcessException(AnalysisEngineProcessException.ANNOTATOR_EXCEPTION,
              new Object[] {}, e);
    }
    crowd.finished(stream);

    if (createStyleMap) {
      try {
        String locate = locate(styleMapLocation, descriptorPaths, ".xml", false);
        if (locate != null) {
          styleMapFactory.createStyleMap(locate, stream);
        }
      } catch (IOException e) {
        throw new AnalysisEngineProcessException(e);
      }
    }
    if (removeBasics) {
      List<AnnotationFS> toRemove = new ArrayList<AnnotationFS>();
      Type type = cas.getTypeSystem().getType(BASIC_TYPE);
      FSIterator<AnnotationFS> iterator = cas.getCas().getAnnotationIndex(type).iterator();
      while (iterator.isValid()) {
        AnnotationFS fs = iterator.get();
        toRemove.add(fs);
        iterator.moveToNext();
      }
      for (AnnotationFS annotationFS : toRemove) {
        cas.removeFsFromIndexes(annotationFS);
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
    Collection<String> filterTags = Arrays.asList(defaultFilteredMarkups);

    for (String each : defaultFilteredTypes) {
      Type type = cas.getTypeSystem().getType(each);
      if (type != null) {
        filterTypes.add(type);
      }
    }

    FilterManager filter = new FilterManager(filterTypes, filterTags, cas);

    Type basicType = cas.getTypeSystem().getType(BASIC_TYPE);
    if (seeders != null) {
      // not already contains that basics:
      if (cas.getAnnotationIndex(basicType).size() <= 0) {

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
            seeder.seed(cas.getDocumentText(), cas);

          } catch (Exception e) {
            throw new AnalysisEngineProcessException(e);
          }
        }

      }

    } else if (useBasics != null) {
      // thats the case if predefined annotation should be used for inference
      Type givenType = cas.getTypeSystem().getType(useBasics);
      FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex(givenType).iterator();
      while (iterator.isValid()) {
        AnnotationFS fs = iterator.get();
        AnnotationFS createAnnotation = cas.createAnnotation(basicType, fs.getBegin(), fs.getEnd());
        cas.addFsToIndexes(createAnnotation);
        iterator.moveToNext();
      }
    }

    FSIterator<AnnotationFS> it = cas.getAnnotationIndex(basicType).iterator();

    TextMarkerStream stream = new TextMarkerStream(cas, it, basicType, filter);

    // TODO find a better solution for this!! -> feature request open
    FSIterator<AnnotationFS> others = cas.getAnnotationIndex().iterator();
    while (others.isValid()) {
      AnnotationFS a = (AnnotationFS) others.get();
      TextMarkerBasic anchor = stream.getFirstBasicInWindow(a, stream.getUnfilteredBasicIterator());
      stream.addAnnotation(anchor, a);
      others.moveToNext();
    }
    return stream;
  }

  private void initializeScript(CAS cas) throws AnalysisEngineProcessException {
    String scriptLocation = locate(mainScript, scriptPaths, ".tm");
    try {
      script = loadScript(scriptLocation, cas, null);
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
          additionalEngines.put(eachEngineLocation, eachEngine);
        } catch (Exception e) {
          throw new AnalysisEngineProcessException(e);
        }
      }
    }

    if (additionalScriptLocations != null) {
      for (String add : additionalScriptLocations) {
        recursiveLoadScript(add, additionalScripts, additionalEngines, cas);
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
    if (name == null) {
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
          Map<String, AnalysisEngine> additionalEngines, CAS cas)
          throws AnalysisEngineProcessException {
    String location = locate(toLoad, scriptPaths, ".tm");
    try {
      TypeSystemDescription localTSD = getLocalTSD(toLoad);
      TextMarkerModule eachScript = loadScript(location, cas, localTSD);
      additionalScripts.put(toLoad, eachScript);
      for (String add : eachScript.getScripts().keySet()) {
        if (!additionalScripts.containsKey(add)) {
          recursiveLoadScript(add, additionalScripts, additionalEngines, cas);
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

  private TextMarkerModule loadScript(String scriptLocation, CAS cas, TypeSystemDescription localTSD)
          throws IOException, RecognitionException {
    File scriptFile = new File(scriptLocation);
    CharStream st = new ANTLRFileStream(scriptLocation, scriptEncoding);
    TextMarkerLexer lexer = new TextMarkerLexer(st);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    TextMarkerParser parser = new TextMarkerParser(tokens);
    parser.setCAS(cas);
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
