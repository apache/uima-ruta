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
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.analysis_engine.metadata.AnalysisEngineMetaData;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.impl.FeatureStructureImpl;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.resource.metadata.ConfigurationParameterSettings;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.type.DebugBlockApply;
import org.apache.uima.ruta.type.DebugMatchedRuleMatch;
import org.apache.uima.ruta.type.DebugRuleApply;
import org.apache.uima.ruta.type.DebugRuleMatch;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;

public class Ruta {

  /**
   * This applies the given rule on the given JCas object
   * 
   * @param jcas
   *          - the current document
   * @param rule
   *          - String containing one or more rules in valid ruta syntax
   * @param configurationData
   *          - additional configuration parameter pairs
   * @throws AnalysisEngineProcessException
   *           - problem while processing
   * @throws ResourceInitializationException
   *           - problem while initializing
   */
  public static void applyRule(JCas jcas, String rule, Object... configurationData)
          throws ResourceInitializationException, AnalysisEngineProcessException {
    AnalysisEngine ae = null;
    if (configurationData == null || configurationData.length == 0) {
      ae = AnalysisEngineFactory.createEngine(RutaEngine.class, RutaEngine.PARAM_RULES, rule);
    } else {
      Object[] config = ArrayUtils.addAll(configurationData, RutaEngine.PARAM_RULES, rule);
      ae = AnalysisEngineFactory.createEngine(RutaEngine.class, config);
    }
    ae.process(jcas);
    ae.destroy();
  }

  public static void apply(CAS cas, String script, Map<String, Object> parameters)
          throws IOException, InvalidXMLException, ResourceInitializationException,
          ResourceConfigurationException, AnalysisEngineProcessException, URISyntaxException {
    String viewName = cas.getViewName();
    URL aedesc = RutaEngine.class.getResource("BasicEngine.xml");
    AnalysisEngine ae = wrapAnalysisEngine(aedesc, viewName, null);

    File scriptFile = File.createTempFile("Ruta", RutaEngine.SCRIPT_FILE_EXTENSION);
    scriptFile.deleteOnExit();
    FileUtils.saveString2File(script, scriptFile, "UTF-8");
    ae.setConfigParameterValue(RutaEngine.PARAM_SCRIPT_PATHS,
            new String[] { scriptFile.getParentFile().getAbsolutePath() });
    String name = scriptFile.getName().substring(0, scriptFile.getName().length() - 5);
    ae.setConfigParameterValue(RutaEngine.PARAM_MAIN_SCRIPT, name);
    if (parameters != null) {
      for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
        ae.setConfigParameterValue(parameter.getKey(), parameter.getValue());
      }
    }
    ae.reconfigure();
    ae.process(cas);
    scriptFile.delete();
    ae.destroy();
  }

  public static void apply(CAS cas, String script)
          throws IOException, InvalidXMLException, ResourceInitializationException,
          ResourceConfigurationException, AnalysisEngineProcessException, URISyntaxException {
    apply(cas, script, null);
  }

  private static List<Annotation> getRuleMatches(JCas jcas) {
    List<Annotation> result = new LinkedList<>();
    Collection<DebugBlockApply> blockApplies = JCasUtil.select(jcas, DebugBlockApply.class);
    for (DebugBlockApply debugBlockApply : blockApplies) {
      collectRuleMatches(debugBlockApply, result);
    }
    return result;
  }

  private static int countRuleApplies(JCas jcas) {
    int result = 0;
    Collection<DebugBlockApply> blockApplies = JCasUtil.select(jcas, DebugBlockApply.class);
    for (DebugBlockApply debugBlockApply : blockApplies) {
      result += countRuleApplies(debugBlockApply);
    }
    return result;
  }

  private static int countRuleApplies(Annotation annotation) {
    int result = 0;
    if (annotation instanceof DebugBlockApply) {
      DebugBlockApply dba = (DebugBlockApply) annotation;
      FSArray innerApply = dba.getInnerApply();
      for (int i = 0; i < innerApply.size(); i++) {
        Annotation each = (Annotation) innerApply.get(i);
        result += countRuleApplies(each);
      }
    } else if (annotation instanceof DebugRuleApply) {
      DebugRuleApply dra = (DebugRuleApply) annotation;
      result += dra.getApplied();
    }
    return result;
  }

  private static void collectRuleMatches(Annotation annotation, List<Annotation> result) {
    if (annotation instanceof DebugBlockApply) {
      DebugBlockApply dba = (DebugBlockApply) annotation;
      FSArray innerApply = dba.getInnerApply();
      for (int i = 0; i < innerApply.size(); i++) {
        Annotation each = (Annotation) innerApply.get(i);
        collectRuleMatches(each, result);
      }
    } else if (annotation instanceof DebugRuleApply) {
      DebugRuleApply dra = (DebugRuleApply) annotation;
      FSArray rules = dra.getRules();
      for (int i = 0; i < rules.size(); i++) {
        Annotation each = (Annotation) rules.get(i);
        collectRuleMatches(each, result);
      }
    } else if (annotation instanceof DebugMatchedRuleMatch) {
      DebugMatchedRuleMatch dmrm = (DebugMatchedRuleMatch) annotation;
      result.add(dmrm);
    }
  }

  /**
   * Removes all debug annotations from the index.
   * 
   * @param jcas
   *          - the current document
   */
  public static void removeDebugInformation(JCas jcas) {
    jcas.removeAllIncludingSubtypes(DebugBlockApply.type);
    jcas.removeAllIncludingSubtypes(DebugRuleApply.type);
    jcas.removeAllIncludingSubtypes(DebugRuleMatch.type);
  }

  public static String inject(String script, String placeholder, int[] addresses) {
    // TODO provide a robust implementation considering also other $, e.g., in strings
    String quote = Pattern.quote(placeholder);
    Pattern pattern = Pattern.compile(quote);
    Matcher matcher = pattern.matcher(script);
    StringBuilder sb = new StringBuilder();
    int start = 0;
    int counter = 0;
    while (matcher.find(start)) {
      String group = matcher.group();
      sb.append(script.substring(start, matcher.start()));
      if (counter < addresses.length) {
        sb.append("$" + addresses[0]);
      } else {
        sb.append("$" + group);
      }
      start = matcher.end();
    }
    if (start < script.length()) {
      sb.append(script.substring(start, script.length()));
    }
    return sb.toString();
  }

  public static String inject(String script, FeatureStructureImpl... annotations) {
    return inject(script, "$", getAddresses(annotations));
  }

  private static int[] getAddresses(FeatureStructureImpl[] annotations) {
    int[] result = new int[annotations.length];
    for (int i = 0; i < annotations.length; i++) {
      result[i] = annotations[i].getAddress();

    }
    return result;
  }

  /**
   * This method returns the spans of successful rule applies.
   * 
   * @param jcas
   *          - the current document
   * @param rule
   *          - String containing one or more rules in valid ruta syntax
   * @param configurationData
   *          - additional configuration parameter pairs
   * @return list of successful rule matches
   * @throws AnalysisEngineProcessException
   *           - problem while processing
   * @throws ResourceInitializationException
   *           - problem while initializing
   */
  public static List<Annotation> select(JCas jcas, String rule, Object... configurationData)
          throws AnalysisEngineProcessException, ResourceInitializationException {
    Object[] config = ArrayUtils.addAll(configurationData, RutaEngine.PARAM_DEBUG, true,
            RutaEngine.PARAM_DEBUG_WITH_MATCHES, true);
    applyRule(jcas, rule, config);
    List<Annotation> ruleMatches = getRuleMatches(jcas);
    removeDebugInformation(jcas);
    return ruleMatches;
  }

  /**
   * This method returns true if the rule (or one of the rules) was able to match.
   * 
   * @param jcas
   *          - the current document
   * @param rule
   *          - String containing one or more rules in valid ruta syntax
   * @param configurationData
   *          - additional configuration parameter pairs
   * @return true if matched, false otherwise
   * @throws AnalysisEngineProcessException
   *           - problem while processing
   * @throws ResourceInitializationException
   *           - problem while initializing
   */
  public static boolean matches(JCas jcas, String rule, Object... configurationData)
          throws AnalysisEngineProcessException, ResourceInitializationException {
    Object[] config = ArrayUtils.addAll(configurationData, RutaEngine.PARAM_DEBUG, true);
    applyRule(jcas, rule, config);
    int applies = countRuleApplies(jcas);
    removeDebugInformation(jcas);
    return applies > 0;
  }

  public static AnalysisEngine wrapAnalysisEngine(URL descriptorUrl, String viewName,
          ResourceManager resourceManager) throws ResourceInitializationException,
          ResourceConfigurationException, InvalidXMLException, IOException, URISyntaxException {
    return wrapAnalysisEngine(descriptorUrl, viewName, false, Charset.defaultCharset().name(),
            resourceManager);
  }

  public static AnalysisEngine wrapAnalysisEngine(URL descriptorUrl, String viewName,
          boolean rutaEngine, String encoding, ResourceManager resourceManager)
          throws ResourceInitializationException, ResourceConfigurationException,
          InvalidXMLException, IOException, URISyntaxException {
    if (viewName.equals(CAS.NAME_DEFAULT_SOFA)) {
      XMLInputSource in = new XMLInputSource(descriptorUrl);
      ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
      AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier, resourceManager, null);
      return ae;
    } else {
      InputStream inputStream = null;
      if (rutaEngine) {
        inputStream = Ruta.class.getResourceAsStream("AAEDBasicEngine.xml");
      } else {
        inputStream = Ruta.class.getResourceAsStream("AAED.xml");
      }
      String aaedString = IOUtils.toString(inputStream, encoding);
      String absolutePath = descriptorUrl.toExternalForm();
      aaedString = aaedString.replaceAll("\\$\\{sofaName\\}", viewName);
      aaedString = aaedString.replaceAll("\\$\\{descriptorLocation\\}", absolutePath);
      File tempFile = File.createTempFile("RutaAAED", ".xml");
      FileUtils.saveString2File(aaedString, tempFile);
      XMLInputSource in = new XMLInputSource(tempFile);
      ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
      AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier, resourceManager, null);
      tempFile.delete();
      return ae;
    }
  }

  @Deprecated
  public static AnalysisEngineDescription createAnalysisEngineDescription(String script)
          throws IOException, InvalidXMLException, ResourceInitializationException {
    return createAnalysisEngineDescription(script, (TypeSystemDescription[]) null);
  }

  @Deprecated
  public static AnalysisEngineDescription createAnalysisEngineDescription(String script,
          TypeSystemDescription... tsds)
          throws IOException, InvalidXMLException, ResourceInitializationException {
    File scriptFile = File.createTempFile("Ruta", RutaEngine.SCRIPT_FILE_EXTENSION);
    scriptFile.deleteOnExit();
    if (!script.startsWith("PACKAGE")) {
      script = "PACKAGE org.apache.uima.ruta;\n" + script;
    }
    FileUtils.saveString2File(script, scriptFile);
    URL url = RutaEngine.class.getResource("BasicEngine.xml");
    XMLInputSource in = new XMLInputSource(url);
    AnalysisEngineDescription aed = (AnalysisEngineDescription) UIMAFramework.getXMLParser()
            .parseResourceSpecifier(in);
    AnalysisEngineMetaData metaData = aed.getAnalysisEngineMetaData();
    ConfigurationParameterSettings settings = metaData.getConfigurationParameterSettings();
    settings.setParameterValue(RutaEngine.PARAM_SCRIPT_PATHS,
            new String[] { scriptFile.getParentFile().getAbsolutePath() });
    String name = scriptFile.getName().substring(0, scriptFile.getName().length() - 5);
    settings.setParameterValue(RutaEngine.PARAM_MAIN_SCRIPT, name);
    if (tsds != null) {
      List<TypeSystemDescription> tsdList = new ArrayList<TypeSystemDescription>();
      tsdList.add(metaData.getTypeSystem());
      tsdList.addAll(Arrays.asList(tsds));
      TypeSystemDescription typeSystemDescription = CasCreationUtils.mergeTypeSystems(tsdList);
      metaData.setTypeSystem(typeSystemDescription);
    }
    return aed;
  }

}
