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
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.UimaContext;
import org.apache.uima.UimaContextAdmin;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.internal.ResourceManagerFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.ruta.FilterManager;
import org.apache.uima.ruta.RutaConstants;
import org.apache.uima.ruta.RutaEnvironment;
import org.apache.uima.ruta.RutaModule;
import org.apache.uima.ruta.RutaScriptFactory;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.TypeUsageInformation;
import org.apache.uima.ruta.action.ActionFactory;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.condition.ConditionFactory;
import org.apache.uima.ruta.expression.ExpressionFactory;
import org.apache.uima.ruta.extensions.IRutaExtension;
import org.apache.uima.ruta.extensions.RutaExternalFactory;
import org.apache.uima.ruta.parser.RutaLexer;
import org.apache.uima.ruta.parser.RutaParser;
import org.apache.uima.ruta.resource.CSVTable;
import org.apache.uima.ruta.resource.RutaResourceLoader;
import org.apache.uima.ruta.seed.RutaAnnotationSeeder;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.type.TokenSeed;
import org.apache.uima.ruta.verbalize.RutaVerbalizer;
import org.apache.uima.ruta.visitor.CreatedByVisitor;
import org.apache.uima.ruta.visitor.DebugInfoCollectorVisitor;
import org.apache.uima.ruta.visitor.InferenceCrowd;
import org.apache.uima.ruta.visitor.RutaInferenceVisitor;
import org.apache.uima.ruta.visitor.StatisticsVisitor;
import org.apache.uima.ruta.visitor.TimeProfilerVisitor;
import org.apache.uima.util.Level;
import org.springframework.core.io.Resource;

public class RutaEngine extends JCasAnnotator_ImplBase {

  public static final String SCRIPT_FILE_EXTENSION = ".ruta";

  public static final String SEPARATOR_VAR_VALUES = ",";

  public static final String SOURCE_DOCUMENT_INFORMATION = "org.apache.uima.examples.SourceDocumentInformation";

  public static final String BASIC_TYPE = "org.apache.uima.ruta.type.RutaBasic";

  public static final String OPTIONAL_TYPE = "org.apache.uima.ruta.type.RutaOptional";

  public static final String FRAME_TYPE = "org.apache.uima.ruta.type.RutaFrame";

  /**
   * A String parameter representing the rule that should be applied by the analysis engine. If set,
   * it replaces the content of file specified by the {@code mainScript} parameter.
   */
  public static final String PARAM_RULES = "rules";

  @ConfigurationParameter(name = PARAM_RULES, mandatory = false)
  private String rules;

  /**
   * This parameter specifies the name of the non-existing script if the parameter 'rules' is used.
   */
  public static final String PARAM_RULES_SCRIPT_NAME = "rulesScriptName";

  @ConfigurationParameter(name = PARAM_RULES_SCRIPT_NAME, mandatory = true, defaultValue = RutaConstants.ANONYMOUS_SCRIPT)
  private String rulesScriptName;

  /**
   * Load script in Java notation, with "{@code .}" as package separator and no extension. File
   * needs to be located in the path specified below with ending {@code .ruta}.
   */
  public static final String PARAM_MAIN_SCRIPT = "mainScript";

  @ConfigurationParameter(name = PARAM_MAIN_SCRIPT, mandatory = false)
  private String mainScript;

  /**
   * This parameter specifies the encoding of the rule files. Its default value is "UTF-8".
   */
  public static final String PARAM_SCRIPT_ENCODING = "scriptEncoding";

  @ConfigurationParameter(name = PARAM_SCRIPT_ENCODING, mandatory = false, defaultValue = "UTF-8")
  private String scriptEncoding;

  /**
   * The parameter scriptPaths refers to a list of String values, which specify the possible
   * locations of script files. The given locations are absolute paths. A typical value for this
   * parameter is, for example, "C:/Ruta/MyProject/script/". If the parameter mainScript is set to
   * org.apache.uima.Main, then the absolute path of the script file has to be
   * "C:/Ruta/MyProject/script/org/apache/uima/Main.ruta". This parameter can contain multiple
   * values, as the main script can refer to multiple projects similar to a class path in Java.
   */
  public static final String PARAM_SCRIPT_PATHS = "scriptPaths";

  @ConfigurationParameter(name = PARAM_SCRIPT_PATHS, mandatory = false)
  private String[] scriptPaths;

  /**
   * This parameter specifies the possible locations for descriptors like analysis engines or type
   * systems, similar to the parameter scriptPaths for the script files. A typical value for this
   * parameter is for example "C:/Ruta/MyProject/descriptor/". The relative values of the parameter
   * additionalEngines are resolved to these absolute locations. This parameter can contain multiple
   * values, as the main script can refer to multiple projects similar to a class path in Java.
   */
  public static final String PARAM_DESCRIPTOR_PATHS = "descriptorPaths";

  @ConfigurationParameter(name = PARAM_DESCRIPTOR_PATHS, mandatory = false, defaultValue = {})
  private String[] descriptorPaths;

  /**
   * This parameter specifies the possible locations of additional resources like word lists or CSV
   * tables. The string values have to contain absolute locations, for example,
   * "C:/Ruta/MyProject/resources/".
   */
  public static final String PARAM_RESOURCE_PATHS = "resourcePaths";

  @ConfigurationParameter(name = PARAM_RESOURCE_PATHS, mandatory = false, defaultValue = {})
  private String[] resourcePaths;

  /**
   * The parameter additionalScripts is defined as a list of string values and contains script
   * files, which are additionally loaded by the analysis engine. These script files are specified
   * by their complete namespace, exactly like the value of the parameter mainScript and can be
   * refered to by language elements, e.g., by executing the containing rules. An exemplary value of
   * this parameter is "org.apache.uima.SecondaryScript". In this example, the main script could
   * import this script file by the declaration "SCRIPT org.apache.uima.SecondaryScript;" and then
   * could execute it with the rule "CALL(SecondaryScript);".
   */
  public static final String PARAM_ADDITIONAL_SCRIPTS = "additionalScripts";

  @ConfigurationParameter(name = PARAM_ADDITIONAL_SCRIPTS, mandatory = false, defaultValue = {})
  private String[] additionalScripts;

  /**
   * This optional parameter contains a list of additional analysis engines, which can be executed
   * by the UIMA Ruta rules. The single values are given by the name of the analysis engine with
   * their complete namespace and have to be located relative to one value of the parameter
   * descriptorPaths or classpath, the location where the analysis engine searches for the
   * descriptor file. An example for one value of the parameter is "utils.HtmlAnnotator", which
   * points to the descriptor "HtmlAnnotator.xml" in the folder "utils". This optional list can be
   * used if no import is specified in the main script.
   */
  public static final String PARAM_ADDITIONAL_ENGINES = "additionalEngines";

  @ConfigurationParameter(name = PARAM_ADDITIONAL_ENGINES, mandatory = false, defaultValue = {})
  private String[] additionalEngines;

  /**
   * Optional list of additional uimaFIT analysis engines, which are loaded without descriptor. This
   * optional list can be used if no import is specified in the main script.
   */
  public static final String PARAM_ADDITIONAL_UIMAFIT_ENGINES = "additionalUimafitEngines";

  @ConfigurationParameter(name = PARAM_ADDITIONAL_UIMAFIT_ENGINES, mandatory = false, defaultValue = {})
  private String[] additionalUimafitEngines;

  /**
   * This parameter specifies optional extensions of the UIMA Ruta language. The elements of the
   * string list have to implement the interface "org.apache.uima.ruta.extensions.IRutaExtension".
   * With these extensions, application-specific conditions and actions can be added to the set of
   * provided ones.
   */
  public static final String PARAM_ADDITIONAL_EXTENSIONS = "additionalExtensions";

  @ConfigurationParameter(name = PARAM_ADDITIONAL_EXTENSIONS, mandatory = false, defaultValue = {})
  private String[] additionalExtensions;

  /**
   * This boolean parameter indicates whether the script or resource files should be reloaded when
   * processing a CAS. The default value is set to false. In this case, the script files are loaded
   * when the analysis engine is initialized. If script files or resource files are extended, e.g.,
   * a dictionary is filled yet when a collection of documents are processed, then the parameter is
   * needed to be set to true in order to include the changes.
   */
  public static final String PARAM_RELOAD_SCRIPT = "reloadScript";

  @ConfigurationParameter(name = PARAM_RELOAD_SCRIPT, mandatory = false, defaultValue = "false")
  private Boolean reloadScript;

  /**
   * This list of string values refers to implementations of the interface
   * "org.apache.uima.ruta.seed.RutaAnnotationSeeder", which can be used to automatically add
   * annotations to the CAS. The default value of the parameter is a single seeder, namely
   * "org.apache.uima.ruta.seed.DefaultSeeder" that adds annotations for token classes like CW,
   * MARKUP or SEMICOLON. Remember that additional annotations can also be added with an additional
   * engine that is executed by a UIMA Ruta rule.
   */
  public static final String PARAM_SEEDERS = "seeders";

  @ConfigurationParameter(name = PARAM_SEEDERS, mandatory = false, defaultValue = {
      "org.apache.uima.ruta.seed.DefaultSeeder" })
  private String[] seeders;

  /**
   * This parameter specifies a list of types, which are filtered by default when executing a script
   * file. Using the default values of this parameter, whitespaces, line breaks and markup elements
   * are not visible to Ruta rules. The visibility of annotations and, therefore, the covered text
   * can be changed using the actions FILTERTYPE and RETAINTYPE.
   */
  public static final String PARAM_DEFAULT_FILTERED_TYPES = "defaultFilteredTypes";

  @ConfigurationParameter(name = PARAM_DEFAULT_FILTERED_TYPES, mandatory = false, defaultValue = {
      "org.apache.uima.ruta.type.SPACE", "org.apache.uima.ruta.type.BREAK",
      "org.apache.uima.ruta.type.MARKUP" })
  private String[] defaultFilteredTypes;

  /**
   * This parameter specifies whether the inference annotations created by the analysis engine
   * should be removed after processing the CAS. The default value is set to false.
   */
  public static final String PARAM_REMOVE_BASICS = "removeBasics";

  @ConfigurationParameter(name = PARAM_REMOVE_BASICS, mandatory = false, defaultValue = "false")
  private Boolean removeBasics;

  /**
   * If this parameter is set to true, then the Ruta rules are not forced to start to match with the
   * first rule element. Rather, the rule element referring to the most rare type is chosen. This
   * option can be utilized to optimize the performance. Please mind that the matching result can
   * vary in some cases when greedy rule elements are applied. The default value is set to false.
   */
  public static final String PARAM_DYNAMIC_ANCHORING = "dynamicAnchoring";

  @ConfigurationParameter(name = PARAM_DYNAMIC_ANCHORING, mandatory = false, defaultValue = "false")
  private Boolean dynamicAnchoring;

  /**
   * This parameter specifies whether the memory consumption should be reduced. This parameter
   * should be set to true for very large CAS documents (e.g., &gt; 500k tokens), but it also
   * reduces the performance. The default value is set to false.
   */
  public static final String PARAM_LOW_MEMORY_PROFILE = "lowMemoryProfile";

  @ConfigurationParameter(name = PARAM_LOW_MEMORY_PROFILE, mandatory = false, defaultValue = "false")
  private Boolean lowMemoryProfile;

  /**
   * This parameter specifies whether a different inference strategy for composed rule elements
   * should be applied. This option is only necessary when the composed rule element is expected to
   * match very often, e.g., a rule element like (ANY ANY)+. The default value of this parameter is
   * set to false.
   */
  public static final String PARAM_SIMPLE_GREEDY_FOR_COMPOSED = "simpleGreedyForComposed";

  @ConfigurationParameter(name = PARAM_SIMPLE_GREEDY_FOR_COMPOSED, mandatory = false, defaultValue = "false")
  private Boolean simpleGreedyForComposed;

  /**
   * If this parameter is set to true, then start positions already matched by the same rule element
   * will be ignored. This situation occurs mostly for rules that start with a quantifier. The
   * following rule, for example, matches only once, if this parameter is set to true: {@code ANY+;}
   */
  public static final String PARAM_GREEDY_RULE_ELEMENT = "greedyRuleElement";

  @ConfigurationParameter(name = PARAM_GREEDY_RULE_ELEMENT, mandatory = false, defaultValue = "false")
  private Boolean greedyRuleElement = false;

  /**
   * If this parameter is set to true, then start positions already matched by the rule will be
   * ignored and only positions not part of an match will be considered.
   */
  public static final String PARAM_GREEDY_RULE = "greedyRule";

  @ConfigurationParameter(name = PARAM_GREEDY_RULE, mandatory = false, defaultValue = "false")
  private Boolean greedyRule = false;

  /**
   * If this parameter is set to true, then additional information about the execution of a rule
   * script is added to the CAS. The actual information is specified by the following parameters.
   * The default value of this parameter is set to false.
   */
  public static final String PARAM_DEBUG = "debug";

  @ConfigurationParameter(name = PARAM_DEBUG, mandatory = false, defaultValue = "false")
  private Boolean debug;

  /**
   * This parameter specifies whether the match information (covered text) of the rules should be
   * stored in the CAS. The default value of this parameter is set to false.
   */
  public static final String PARAM_DEBUG_WITH_MATCHES = "debugWithMatches";

  @ConfigurationParameter(name = PARAM_DEBUG_WITH_MATCHES, mandatory = false, defaultValue = "false")
  private Boolean debugWithMatches;

  /**
   * This parameter specifies a list of rule-ids that enumerate the rule for which debug information
   * should be created. No specific ids are given by default.
   */
  public static final String PARAM_DEBUG_ONLY_FOR = "debugOnlyFor";

  @ConfigurationParameter(name = PARAM_DEBUG_ONLY_FOR, mandatory = false, defaultValue = {})
  private String[] debugOnlyFor;

  /**
   * If this parameter is set to true, then additional information about the runtime of applied
   * rules is added to the CAS. The default value of this parameter is set to false.
   */
  public static final String PARAM_PROFILE = "profile";

  @ConfigurationParameter(name = PARAM_PROFILE, mandatory = false, defaultValue = "false")
  private Boolean profile;

  /**
   * If this parameter is set to true, then additional information about the runtime of UIMA Ruta
   * language elements like conditions and actions is added to the CAS. The default value of this
   * parameter is set to false.
   */
  public static final String PARAM_STATISTICS = "statistics";

  @ConfigurationParameter(name = PARAM_STATISTICS, mandatory = false, defaultValue = "false")
  private Boolean statistics;

  /**
   * If this parameter is set to true, then additional information about what annotation was created
   * by which rule is added to the CAS. The default value of this parameter is set to false.
   */
  public static final String PARAM_CREATED_BY = "createdBy";

  @ConfigurationParameter(name = PARAM_CREATED_BY, mandatory = false, defaultValue = "false")
  private Boolean createdBy;

  /**
   * If this parameter is set to true, then only types in declared type systems are available by
   * their short name.
   */
  public static final String PARAM_STRICT_IMPORTS = "strictImports";

  @ConfigurationParameter(name = PARAM_STRICT_IMPORTS, mandatory = false, defaultValue = "false")
  private Boolean strictImports = false;

  /**
   * If this parameter is set to true, then whitespaces are removed when dictionaries are loaded.
   */
  public static final String PARAM_DICT_REMOVE_WS = "dictRemoveWS";

  @ConfigurationParameter(name = PARAM_DICT_REMOVE_WS, mandatory = false, defaultValue = "false")
  private Boolean dictRemoveWS = false;

  /**
   * If this parameter is set to any String value then this String/token is used to split columns in
   * CSV tables
   */
  public static final String PARAM_CSV_SEPARATOR = "csvSeparator";

  @ConfigurationParameter(name = PARAM_CSV_SEPARATOR, mandatory = false, defaultValue = CSVTable.DEFAULT_CSV_SEPARATOR)
  private String csvSeparator = CSVTable.DEFAULT_CSV_SEPARATOR;

  /**
   * This parameter specifies the names of variables and is used in combination with the parameter
   * varValues, which contains the values of the corresponding variables. The n-th entry of this
   * string array specifies the variable of the n-th entry of the string array of the parameter
   * varValues. If the variables is defined in the root of a script, then the name of the variable
   * suffices. If the variable is defined in a BLOCK or imported script, then the the name must
   * contain the namespaces of the blocks as a prefix, e.g., InnerBlock.varName or
   * OtherScript.SomeBlock.varName
   */
  public static final String PARAM_VAR_NAMES = "varNames";

  @ConfigurationParameter(name = PARAM_VAR_NAMES, mandatory = false, defaultValue = {})
  private String[] varNames;

  /**
   * This parameter specifies the values of variables as string values in an string array. It is
   * used in combination with the parameter varNames, which contains the names of the corresponding
   * variables. The n-th entry of this string array specifies the value of the n-th entry of the
   * string array of the parameter varNames. Only value of the kinds string, boolean, int, double
   * and float are allowed.
   */
  public static final String PARAM_VAR_VALUES = "varValues";

  @ConfigurationParameter(name = PARAM_VAR_VALUES, mandatory = false, defaultValue = {})
  private String[] varValues;

  /**
   * This parameter specifies the annotation types which should be reindexed for ruta's internal
   * annotations. All annotation types that changed since the last call of a ruta script need to be
   * listed here. The value of this parameter needs only be adapted for performance optimization in
   * pipelines that contains several ruta analysis engines. Default value is uima.tcas.Annotation
   */
  public static final String PARAM_REINDEX_ONLY = "reindexOnly";

  @ConfigurationParameter(name = PARAM_REINDEX_ONLY, mandatory = false, defaultValue = {
      "uima.tcas.Annotation" })
  private String[] reindexOnly;

  /**
   * If this parameter is activated, then only annotations of types are internally reindexed at
   * beginning that are mentioned with in the rules. This parameter overrides the values of the
   * parameter 'reindexOnly' with the types that are mentioned in the rules.
   */
  public static final String PARAM_REINDEX_ONLY_MENTIONED_TYPES = "reindexOnlyMentionedTypes";

  @ConfigurationParameter(name = PARAM_REINDEX_ONLY_MENTIONED_TYPES, mandatory = true, defaultValue = "false")
  private boolean reindexOnlyMentionedTypes;

  /**
   * If this parameter is activated, then only annotations of types are internally indexed that are
   * mentioned with in the rules. This optimization of the internal indexing can improve the speed
   * and reduce the memory footprint. However, several features of the rule matching require the
   * indexing of types that are not mentioned in the rules, e.g., literal rule matches, wildcards
   * and actions like MARKFAST, MARKTABLE, TRIE.
   */
  public static final String PARAM_INDEX_ONLY_MENTIONED_TYPES = "indexOnlyMentionedTypes";

  @ConfigurationParameter(name = PARAM_INDEX_ONLY_MENTIONED_TYPES, mandatory = true, defaultValue = "false")
  private boolean indexOnlyMentionedTypes;

  /**
   * This parameter specifies annotation types (resolvable mentions are also supported) that should
   * be index additionally to types mentioned in the rules. This parameter is only used if the
   * parameter 'indexOnlyMentionedTypes' is activated.
   * 
   */
  public static final String PARAM_INDEX_ADDITONALLY = "indexAdditionally";

  @ConfigurationParameter(name = PARAM_INDEX_ADDITONALLY, mandatory = false, defaultValue = {})
  private String[] indexAdditionally;

  /**
   * This parameter determines positions as invisible if the internal indexing of the corresponding
   * RutaBasic annotation is empty.
   */
  public static final String PARAM_EMPTY_IS_INVISIBLE = "emptyIsInvisible";

  @ConfigurationParameter(name = PARAM_EMPTY_IS_INVISIBLE, mandatory = false, defaultValue = {
      "true" })
  private boolean emptyIsInvisible;

  /**
   * Option to extend the datapath by the descriptorPaths
   */
  public static final String PARAM_MODIFY_DATAPATH = "modifyDataPath";

  @ConfigurationParameter(name = PARAM_MODIFY_DATAPATH, mandatory = false, defaultValue = "false")
  private boolean modifyDataPath;

  /**
   * This parameter specifies optional class names implementing the interface
   * <code>org.apache.uima.ruta.visitor.RutaInferenceVisitor</code>, which will be notified during
   * applying the rules.
   * 
   */
  public static final String PARAM_INFERENCE_VISITORS = "inferenceVisitors";

  @ConfigurationParameter(name = PARAM_INFERENCE_VISITORS, mandatory = false, defaultValue = {})
  private String[] inferenceVisitors;

  /**
   * Maximum amount of allowed matches of a single rule.
   */
  public static final String PARAM_MAX_RULE_MATCHES = "maxRuleMatches";

  @ConfigurationParameter(name = PARAM_MAX_RULE_MATCHES, mandatory = false, defaultValue = ""
          + Integer.MAX_VALUE)
  private int maxRuleMatches;

  /**
   * Maximum amount of allowed matches of a single rule element.
   */
  public static final String PARAM_MAX_RULE_ELEMENT_MATCHES = "maxRuleElementMatches";

  @ConfigurationParameter(name = PARAM_MAX_RULE_ELEMENT_MATCHES, mandatory = false, defaultValue = ""
          + Integer.MAX_VALUE)
  private int maxRuleElementMatches;

  private UimaContext context;

  private RutaModule script;

  private RutaExternalFactory externalFactory;

  private RutaVerbalizer verbalizer;

  private boolean initialized = false;

  private boolean analysisEnginesAlreadyInitialized = false;

  private List<Type> seedTypes;

  private TypeUsageInformation typeUsageInformation;

  private TypeSystem lastTypeSystem;

  private ResourceManager resourceManager = null;

  private RutaResourceLoader scriptRutaResourceLoader;

  private RutaResourceLoader descriptorRutaResourceLoader;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    if (aContext == null && context != null) {
      aContext = context;
    }

    this.context = aContext;

    externalFactory = new RutaExternalFactory();
    externalFactory.setContext(aContext);
    verbalizer = new RutaVerbalizer();

    // reinitialize analysis engines if this one is configured
    analysisEnginesAlreadyInitialized = false;

    initializeResourceManager();
    handleDataPath();

    scriptRutaResourceLoader = new RutaResourceLoader(scriptPaths,
            resourceManager.getExtensionClassLoader());
    descriptorRutaResourceLoader = new RutaResourceLoader(descriptorPaths,
            resourceManager.getExtensionClassLoader());

    if (!externalFactory.isInitialized()) {
      initializeExtensionWithClassPath();
    }
    if (!reloadScript) {
      try {
        initializeScript(CAS.NAME_DEFAULT_SOFA);
        initialized = false;
      } catch (AnalysisEngineProcessException e) {
        throw new ResourceInitializationException(e);
      }
    }
  }

  @Override
  public void process(JCas jcas) throws AnalysisEngineProcessException {

    CAS cas = jcas.getCas();

    if (reloadScript || (!initialized && !cas.getViewName().equals(CAS.NAME_DEFAULT_SOFA))) {
      initializeScript(cas.getViewName());
    } else {
      resetEnvironments(cas);
      initializeVariableValues();
    }
    boolean typeSystemChanged = lastTypeSystem != cas.getTypeSystem();
    if (!initialized || reloadScript || typeSystemChanged) {
      initializeTypes(script, cas, new ArrayList<String>());
      if (typeUsageInformation != null) {
        typeUsageInformation.resolveTypes(script, cas.getTypeSystem());
      }
      initialized = true;
      lastTypeSystem = cas.getTypeSystem();
    }
    InferenceCrowd crowd = initializeCrowd();
    RutaStream stream = initializeStream(cas, crowd);
    stream.setDynamicAnchoring(dynamicAnchoring);
    stream.setGreedyRuleElement(greedyRuleElement);
    stream.setGreedyRule(greedyRule);
    stream.setMaxRuleMatches(maxRuleMatches);
    stream.setMaxRuleElementMatches(maxRuleElementMatches);
    try {
      script.apply(stream, crowd);
    } catch (Throwable e) {
      throw new AnalysisEngineProcessException(AnalysisEngineProcessException.ANNOTATOR_EXCEPTION,
              new Object[] {}, e);
    }
    crowd.finished(stream);

    if (removeBasics) {
      jcas.removeAllIncludingSubtypes(RutaBasic.type);
      jcas.removeAllIncludingSubtypes(TokenSeed.type);

      List<AnnotationFS> toRemove = new ArrayList<AnnotationFS>();
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

  private void initializeResourceManager() throws ResourceInitializationException {
    if (context instanceof UimaContextAdmin) {
      UimaContextAdmin uca = (UimaContextAdmin) context;
      ResourceManager rm = uca.getResourceManager();
      if (rm != null) {
        resourceManager = rm;
      }
    }
    if (resourceManager == null) {
      resourceManager = ResourceManagerFactory.newResourceManager();
    }
  }

  private void handleDataPath() throws ResourceInitializationException {
    String dataPath = context.getDataPath();
    String[] singleDataPaths = dataPath.split(File.pathSeparator);

    String[] clonedDescriptorPath = null;
    if (descriptorPaths != null) {
      clonedDescriptorPath = descriptorPaths.clone();
    }

    if (!StringUtils.isBlank(dataPath)) {
      scriptPaths = ArrayUtils.addAll(scriptPaths, singleDataPaths);
      descriptorPaths = ArrayUtils.addAll(descriptorPaths, singleDataPaths);
      resourcePaths = ArrayUtils.addAll(resourcePaths, singleDataPaths);
    }

    if (modifyDataPath && clonedDescriptorPath != null) {
      if (!dataPath.endsWith(File.pathSeparator)) {
        dataPath += File.pathSeparator;
      }
      for (String path : clonedDescriptorPath) {
        dataPath += path + File.pathSeparator;
      }
      try {
        resourceManager.setDataPath(dataPath);
      } catch (MalformedURLException e) {
        throw new ResourceInitializationException(e);
      }
    }
  }

  private void resetEnvironments(CAS cas) {
    resetEnvironment(script, cas, new HashSet<RutaModule>());
  }

  private void resetEnvironment(RutaModule module, CAS cas,
          Collection<RutaModule> alreadyResetted) {
    if (alreadyResetted.contains(module)) {
      // avoid loop in recursion
      return;
    }
    alreadyResetted.add(module);

    // reset all blocks
    RutaBlock block = module.getBlock(null);
    block.getEnvironment().reset(cas);
    Collection<RutaBlock> blocks = module.getBlocks().values();
    for (RutaBlock each : blocks) {
      each.getEnvironment().reset(cas);
    }

    // reset imported scripts
    Collection<RutaModule> scripts = module.getScripts().values();
    for (RutaModule eachModule : scripts) {
      resetEnvironment(eachModule, cas, alreadyResetted);
    }
  }

  private void initializeTypes(RutaModule script, CAS cas, List<String> initialized) {
    // TODO find a better solution for telling everyone about the types!
    RutaBlock mainRootBlock = script.getBlock(null);
    mainRootBlock.getEnvironment().setResourceManager(resourceManager);
    Collection<Entry<String, RutaModule>> values = script.getScripts().entrySet();
    for (Entry<String, RutaModule> eachImport : values) {
      String name = eachImport.getKey();
      if (!initialized.contains(name)) {
        RutaModule eachModule = eachImport.getValue();
        relinkEnvironments(eachModule, mainRootBlock, new ArrayList<RutaModule>());
        initializeTypes(eachModule, cas, initialized);
        initialized.add(name);
      }
    }
    mainRootBlock.getEnvironment().initializeTypes(cas, strictImports);

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
        Class<?> forName = getClassLoader().loadClass(each);
        if (IRutaExtension.class.isAssignableFrom(forName)) {
          IRutaExtension extension = (IRutaExtension) forName.newInstance();
          verbalizer.addExternalVerbalizers(extension);
          for (String name : extension.getKnownExtensions()) {
            externalFactory.addExtension(name, extension);
          }
        }
      } catch (Exception e) {
        getLogger().log(Level.WARNING, "Failed to initialize extension " + each);
      }
    }
  }

  private ClassLoader getClassLoader() {
    ClassLoader extensionClassLoader = resourceManager.getExtensionClassLoader();
    if (extensionClassLoader == null) {
      return this.getClass().getClassLoader();
    }
    return extensionClassLoader;

  }

  private InferenceCrowd initializeCrowd() {
    List<RutaInferenceVisitor> visitors = new ArrayList<RutaInferenceVisitor>();
    if (debug) {
      visitors.add(new DebugInfoCollectorVisitor(debug, debugWithMatches,
              Arrays.asList(debugOnlyFor), verbalizer));
    }
    if (profile) {
      visitors.add(new TimeProfilerVisitor());
    }
    if (statistics) {
      visitors.add(new StatisticsVisitor(verbalizer));
    }
    if (createdBy) {
      visitors.add(new CreatedByVisitor(verbalizer));
    }
    if (inferenceVisitors != null && inferenceVisitors.length != 0) {
      for (String eachClassName : inferenceVisitors) {
        try {
          Class<?> forName = getClassLoader().loadClass(eachClassName);
          if (RutaInferenceVisitor.class.isAssignableFrom(forName)) {
            RutaInferenceVisitor visitor = (RutaInferenceVisitor) forName.newInstance();
            visitors.add(visitor);
          }
        } catch (Exception e) {
          getLogger().log(Level.WARNING, "Failed to initialize inference visitor " + eachClassName);
        }
      }
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
    FilterManager filter = new FilterManager(filterTypes, typeUsageInformation == null, cas);
    Type basicType = typeSystem.getType(BASIC_TYPE);
    seedTypes = seedAnnotations(cas);
    RutaStream stream = new RutaStream(cas, basicType, filter, lowMemoryProfile,
            simpleGreedyForComposed, emptyIsInvisible, typeUsageInformation, crowd);

    stream.initalizeBasics(reindexOnly, reindexOnlyMentionedTypes);
    return stream;
  }

  private List<Type> seedAnnotations(CAS cas) throws AnalysisEngineProcessException {
    List<Type> result = new ArrayList<Type>();
    if (seeders != null) {
      for (String seederClass : seeders) {
        Class<?> loadClass = null;
        try {
          loadClass = getClassLoader().loadClass(seederClass);
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
    if (rules != null) {
      try {
        script = loadScriptByString(rules);
      } catch (RecognitionException e) {
        throw new AnalysisEngineProcessException(e);
      }
    } else if (mainScript != null) {
      Resource scriptResource = scriptRutaResourceLoader.getResourceWithDotNotation(mainScript,
              SCRIPT_FILE_EXTENSION);
      if (scriptResource == null || !scriptResource.exists()) {
        throw new AnalysisEngineProcessException(new FileNotFoundException("Script [" + mainScript
                + "] cannot be found at [" + StringUtils.join(scriptPaths, File.pathSeparatorChar)
                + "] or classpath with extension " + SCRIPT_FILE_EXTENSION));
      }
      try {
        script = loadScript(scriptResource, getModuleName(mainScript));
      } catch (RecognitionException | IOException e) {
        throw new AnalysisEngineProcessException(e);
      }
    }
    if (script == null) {
      return;
    }

    Map<String, RutaModule> additionalScriptMap = new HashMap<String, RutaModule>();
    Map<String, AnalysisEngine> additionalUimafitEngineMap = new HashMap<String, AnalysisEngine>();
    Map<String, AnalysisEngine> additionalDescriptorEngineMap = new HashMap<String, AnalysisEngine>();

    // add configuration parameter values
    for (String each : additionalUimafitEngines) {
      String classString = each;
      List<String> configurationData = new ArrayList<>();
      String[] split = each.split("[\\[\\]]");
      if (split.length == 2) {
        classString = split[0];
        configurationData = Arrays.asList(StringUtils.split(split[1], ","));
      }
      script.addUimafitEngine(classString, null);
      if (!configurationData.isEmpty()) {
        script.addConfigurationData(classString, configurationData);
      }
    }
    for (String each : additionalEngines) {
      script.addDescriptorEngine(each, null);
    }
    for (String each : additionalScripts) {
      script.addScript(each, null);
    }

    initializeEngines(script, viewName, additionalUimafitEngineMap, additionalDescriptorEngineMap);

    for (String add : script.getScripts().keySet()) {
      recursiveLoadScript(add, additionalScriptMap, additionalDescriptorEngineMap,
              additionalUimafitEngineMap, viewName);
    }

    analysisEnginesAlreadyInitialized = true;

    for (RutaModule each : additionalScriptMap.values()) {
      each.setScriptDependencies(additionalScriptMap);
    }
    script.setScriptDependencies(additionalScriptMap);

    for (RutaModule each : additionalScriptMap.values()) {
      each.setDescriptorEngineDependencies(additionalDescriptorEngineMap);
      each.setUimafitEngineDependencies(additionalUimafitEngineMap);
    }
    script.setDescriptorEngineDependencies(additionalDescriptorEngineMap);
    script.setUimafitEngineDependencies(additionalUimafitEngineMap);

    initializeVariableValues();
  }

  private void recursiveLoadScript(String toLoad, Map<String, RutaModule> additionalScripts,
          Map<String, AnalysisEngine> additionalDescriptorEngineMap,
          Map<String, AnalysisEngine> additionalUimafitEngineMap, String viewName)
          throws AnalysisEngineProcessException {

    Resource scriptResource = scriptRutaResourceLoader.getResourceWithDotNotation(toLoad,
            SCRIPT_FILE_EXTENSION);
    if (scriptResource == null) {
      throw new AnalysisEngineProcessException(new FileNotFoundException("Script [" + mainScript
              + "] cannot be found at [" + StringUtils.join(scriptPaths, File.pathSeparatorChar)
              + "] or classpath with extension " + SCRIPT_FILE_EXTENSION));
    }
    RutaModule eachScript = null;
    try {
      eachScript = loadScript(scriptResource, getModuleName(toLoad));
    } catch (RecognitionException | IOException e) {
      throw new AnalysisEngineProcessException(e);
    }
    additionalScripts.put(toLoad, eachScript);

    for (String add : eachScript.getScripts().keySet()) {
      if (!additionalScripts.containsKey(add)) {
        recursiveLoadScript(add, additionalScripts, additionalDescriptorEngineMap,
                additionalUimafitEngineMap, viewName);
      }
    }

    initializeEngines(eachScript, viewName, additionalUimafitEngineMap,
            additionalDescriptorEngineMap);
  }

  private void initializeEngines(RutaModule module, String viewName,
          Map<String, AnalysisEngine> additionalUimafitEngineMap,
          Map<String, AnalysisEngine> additionalDescriptorEngineMap)
          throws AnalysisEngineProcessException {
    if (!analysisEnginesAlreadyInitialized) {
      for (String eachUimafitEngine : module.getUimafitEngines().keySet()) {
        addUimafitAnalysisEngine(module, additionalUimafitEngineMap, eachUimafitEngine);
      }
    }
    if (!analysisEnginesAlreadyInitialized) {
      for (String eachEngineLocation : module.getDescriptorEngines().keySet()) {
        Resource descriptorResource = descriptorRutaResourceLoader
                .getResourceWithDotNotation(eachEngineLocation, ".xml");
        if (descriptorResource == null) {
          throw new AnalysisEngineProcessException(
                  new FileNotFoundException("Engine at [" + eachEngineLocation
                          + "] cannot be found in [" + StringUtils.join(descriptorPaths, ',')
                          + "] with extension .xml (from mainScript=" + mainScript + " in "
                          + StringUtils.join(scriptPaths, ',')));
        }
        try {
          AnalysisEngine eachEngine = Ruta.wrapAnalysisEngine(descriptorResource.getURL(), viewName,
                  resourceManager);
          addAnalysisEngineToMap(additionalDescriptorEngineMap, eachEngineLocation, eachEngine);
        } catch (Exception e) {
          throw new AnalysisEngineProcessException(e);
        }
      }
    }
  }

  private void addAnalysisEngineToMap(Map<String, AnalysisEngine> additionalEnginesMap,
          String eachEngineLocation, AnalysisEngine eachEngine) {
    String engineName = getModuleName(eachEngineLocation);
    additionalEnginesMap.put(eachEngineLocation, eachEngine);
    additionalEnginesMap.put(engineName, eachEngine);
  }

  private void addUimafitAnalysisEngine(RutaModule script,
          Map<String, AnalysisEngine> additionalEnginesMap, String eachUimafitEngine)
          throws AnalysisEngineProcessException {
    AnalysisEngine eachEngine = null;
    try {
      @SuppressWarnings("unchecked")
      Class<? extends AnalysisComponent> uimafitClass = (Class<? extends AnalysisComponent>) getClassLoader()
              .loadClass(eachUimafitEngine);
      List<String> configurationData = script.getConfigurationData(eachUimafitEngine);
      AnalysisEngineDescription aed = AnalysisEngineFactory.createEngineDescription(uimafitClass,
              configurationData.toArray());
      eachEngine = UIMAFramework.produceAnalysisEngine(aed, resourceManager, null);
    } catch (ClassNotFoundException | ResourceInitializationException e) {
      throw new AnalysisEngineProcessException(e);
    }
    addAnalysisEngineToMap(additionalEnginesMap, eachUimafitEngine, eachEngine);
  }

  private String getModuleName(String completeNamespace) {
    int lastIndexOf = completeNamespace.lastIndexOf('.');
    if (lastIndexOf != -1) {
      return completeNamespace.substring(lastIndexOf + 1, completeNamespace.length());
    }
    return completeNamespace;
  }

  private void initializeVariableValues() {
    if (varNames == null || varValues == null) {
      return;
    }
    if (varNames.length != varValues.length) {
      throw new IllegalArgumentException(
              "The parameters varNames and varValues must contain the same amount of entries.");
    }

    for (int i = 0; i < varNames.length; i++) {
      String longName = varNames[i];
      String value = varValues[i];

      int lastIndexOf = longName.lastIndexOf('.');
      String shortName = longName;
      String blockName = script.getRootBlock().getName();
      if (lastIndexOf != -1) {
        blockName = longName.substring(0, lastIndexOf);
        shortName = longName.substring(lastIndexOf + 1, longName.length());
      }
      RutaBlock block = script.getBlock(blockName);
      if (block == null) {
        continue;
      }

      RutaEnvironment environment = block.getEnvironment();
      if (!environment.ownsVariable(shortName)) {
        continue;
      }
      Object valueObj = null;
      Class<?> variableType = environment.getVariableType(shortName);

      if (variableType == null) {
        throw new IllegalArgumentException(
                "Variable " + shortName + " is not known in block: " + blockName);
      }

      if (List.class.equals(variableType)) {
        valueObj = getListVariableValueFromString(value, shortName, environment);
      } else {
        valueObj = getVariableValueFromString(value, variableType);
      }

      if (value == null) {
        throw new IllegalArgumentException("Cannot determine value " + value + " of variable "
                + shortName + "  in block: " + blockName + ". Null values are not allowed");
      }

      environment.setVariableValue(shortName, valueObj);
    }

  }

  private Object getVariableValueFromString(String value, Class<?> variableType) {

    if (Integer.class.equals(variableType)) {
      return Integer.parseInt(value);
    } else if (Double.class.equals(variableType)) {
      return Double.parseDouble(value);
    } else if (Float.class.equals(variableType)) {
      return Float.parseFloat(value);
    } else if (String.class.equals(variableType)) {
      return value;
    } else if (Boolean.class.equals(variableType)) {
      return Boolean.parseBoolean(value);
    } else if (Type.class.equals(variableType)) {
      if (typeUsageInformation != null) {
        typeUsageInformation.addMentionedType(value);
      }
      return value;
    }
    return null;
  }

  private List<?> getListVariableValueFromString(String value, String shortName,
          RutaEnvironment environment) {
    List<Object> result = new ArrayList<>();
    Class<?> genericType = environment.getVariableGenericType(shortName);
    String[] split = StringUtils.split(value, RutaEngine.SEPARATOR_VAR_VALUES);
    for (String string : split) {
      result.add(getVariableValueFromString(string.trim(), genericType));
    }
    return result;
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

  protected RutaModule loadScriptByString(String rules) throws RecognitionException {
    CharStream st = new ANTLRStringStream(rules);
    RutaLexer lexer = new RutaLexer(st);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    RutaParser parser = createParser(tokens);
    RutaModule script = parser.file_input(rulesScriptName);
    return script;
  }

  protected RutaModule loadScript(Resource scriptResource, String name)
          throws IOException, RecognitionException {
    InputStream scriptInputStream = scriptResource.getInputStream();
    CharStream st = new ANTLRInputStream(scriptInputStream, scriptEncoding);
    RutaLexer lexer = new RutaLexer(st);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    RutaParser parser = createParser(tokens);
    RutaModule script = parser.file_input(name);
    return script;
  }

  private RutaParser createParser(CommonTokenStream tokens) {
    RutaParser parser = new RutaParser(tokens);
    initializeTypeUsageInformation();
    ActionFactory actionFactory = new ActionFactory(typeUsageInformation);
    ConditionFactory conditionFactory = new ConditionFactory(typeUsageInformation);
    ExpressionFactory expressionFactory = new ExpressionFactory(typeUsageInformation);
    RutaScriptFactory scriptFactory = new RutaScriptFactory(expressionFactory,
            typeUsageInformation);
    scriptFactory.setContext(context);

    parser.setScriptFactory(scriptFactory);
    parser.setExpressionFactory(expressionFactory);
    parser.setActionFactory(actionFactory);
    parser.setConditionFactory(conditionFactory);
    parser.setExternalFactory(externalFactory);
    parser.setContext(context);
    parser.setResourcePaths(resourcePaths);
    parser.setResourceManager(resourceManager);

    return parser;
  }

  private void initializeTypeUsageInformation() {
    if (typeUsageInformation == null && indexOnlyMentionedTypes) {
      typeUsageInformation = new TypeUsageInformation();
      if (defaultFilteredTypes != null) {
        for (String typeName : defaultFilteredTypes) {
          typeUsageInformation.addMentionedType(typeName);
        }
      }
      if (indexAdditionally != null) {
        for (String typeName : indexAdditionally) {
          typeUsageInformation.addMentionedType(typeName);
        }
      }
    }
  }

  protected RutaExternalFactory getFactory() {
    return externalFactory;
  }

  protected TypeUsageInformation getTypeUsageInfomation() {
    return typeUsageInformation;
  }

  @Override
  public void batchProcessComplete() throws AnalysisEngineProcessException {
    super.batchProcessComplete();
    if (script != null) {
      Collection<AnalysisEngine> values = script.getAllEngines().values();
      for (AnalysisEngine each : values) {
        each.batchProcessComplete();
      }
    }
  }

  @Override
  public void collectionProcessComplete() throws AnalysisEngineProcessException {
    super.collectionProcessComplete();
    if (script != null) {
      Collection<AnalysisEngine> values = script.getAllEngines().values();
      for (AnalysisEngine each : values) {
        each.collectionProcessComplete();
      }
    }
  }

  @Override
  public void destroy() {
    super.destroy();
    if (script != null) {
      Collection<AnalysisEngine> values = script.getAllEngines().values();
      for (AnalysisEngine each : values) {
        each.destroy();
      }
    }
  }

}
