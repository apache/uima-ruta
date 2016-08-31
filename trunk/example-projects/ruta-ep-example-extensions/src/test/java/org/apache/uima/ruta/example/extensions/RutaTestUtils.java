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

package org.apache.uima.ruta.example.extensions;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;

public class RutaTestUtils {

  public static class TestFeature {
    public String name;

    public String description;

    public String range;

    public TestFeature(String name, String description, String range) {
      super();
      this.name = name;
      this.description = description;
      this.range = range;
    }
  }

  public static final String TYPE = "org.apache.uima.T";

  public static CAS process(String ruleFileName, String textFileName, int amount)
          throws URISyntaxException, IOException, InvalidXMLException,
          ResourceInitializationException, AnalysisEngineProcessException,
          ResourceConfigurationException {
    return process(ruleFileName, textFileName, amount, false, false, null, null, null, null);
  }

  public static CAS process(String ruleFileName, String textFileName, int amount,
          boolean dynamicAnchoring, boolean simpleGreedyForComposed,
          Map<String, String> complexTypes, String resourceDirName) throws URISyntaxException,
          IOException, InvalidXMLException, ResourceInitializationException,
          AnalysisEngineProcessException, ResourceConfigurationException {
    return process(ruleFileName, textFileName, amount, dynamicAnchoring, simpleGreedyForComposed,
            complexTypes, null, resourceDirName, null);
  }

  public static CAS process(String ruleFileName, String textFileName, int amount,
          boolean dynamicAnchoring, boolean simpleGreedyForComposed,
          Map<String, String> complexTypes, Map<String, List<TestFeature>> features,
          String resourceDirName) throws URISyntaxException, IOException, InvalidXMLException,
          ResourceInitializationException, AnalysisEngineProcessException,
          ResourceConfigurationException {
    return process(ruleFileName, textFileName, amount, dynamicAnchoring, simpleGreedyForComposed,
            complexTypes, features, resourceDirName, null);
  }

  public static CAS process(String ruleFileName, String textFileName, int amount,
          boolean dynamicAnchoring, boolean simpleGreedyForComposed,
          Map<String, String> complexTypes, Map<String, List<TestFeature>> features,
          String resourceDirName, CAS cas) throws URISyntaxException, IOException,
          InvalidXMLException, ResourceInitializationException, AnalysisEngineProcessException,
          ResourceConfigurationException {
    URL ruleURL = RutaTestUtils.class.getClassLoader().getResource(ruleFileName);
    File ruleFile = new File(ruleURL.toURI());
    URL textURL = RutaTestUtils.class.getClassLoader().getResource(textFileName);
    File textFile = new File(textURL.toURI());
    File resourceFile = null;
    if (resourceDirName != null) {
      URL resourceURL = RutaTestUtils.class.getClassLoader().getResource(resourceDirName);
      resourceFile = new File(resourceURL.toURI());
    }
    URL url = RutaEngine.class.getClassLoader().getResource("BasicEngine.xml");
    if (url == null) {
      url = RutaTestUtils.class.getClassLoader().getResource(
              "org/apache/uima/ruta/example/extensions/TestEngine.xml");
    }
    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngineDescription aed = (AnalysisEngineDescription) specifier;

    TypeSystemDescription basicTypeSystem = aed.getAnalysisEngineMetaData().getTypeSystem();
    for (int i = 1; i <= amount; i++) {
      basicTypeSystem.addType(TYPE + i, "Type for Testing", "uima.tcas.Annotation");
    }

    if (complexTypes != null) {
      Set<Entry<String, String>> entrySet = complexTypes.entrySet();
      for (Entry<String, String> entry : entrySet) {
        String name = entry.getKey();
        TypeDescription addType = basicTypeSystem.addType(name, "Type for Testing",
                entry.getValue());
        if (features != null) {
          List<TestFeature> list = features.get(name);
          for (TestFeature f : list) {
            addType.addFeature(f.name, f.description, f.range);
          }
        }
      }
    }

    Collection<TypeSystemDescription> tsds = new ArrayList<TypeSystemDescription>();
    tsds.add(basicTypeSystem);
    TypeSystemDescription mergeTypeSystems = CasCreationUtils.mergeTypeSystems(tsds);
    aed.getAnalysisEngineMetaData().setTypeSystem(mergeTypeSystems);

    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    ae.setConfigParameterValue(RutaEngine.PARAM_SCRIPT_PATHS, new String[] { ruleFile
            .getParentFile().getPath() });
    String name = ruleFile.getName();
    if (name.endsWith(RutaEngine.SCRIPT_FILE_EXTENSION)) {
      name = name.substring(0, name.length() - 5);
    }
    ae.setConfigParameterValue(RutaEngine.PARAM_MAIN_SCRIPT, name);
    ae.setConfigParameterValue(RutaEngine.PARAM_DYNAMIC_ANCHORING, dynamicAnchoring);
    ae.setConfigParameterValue(RutaEngine.PARAM_SIMPLE_GREEDY_FOR_COMPOSED, simpleGreedyForComposed);
    if (resourceFile != null) {
      ae.setConfigParameterValue(RutaEngine.PARAM_RESOURCE_PATHS,
              new String[] { resourceFile.getPath() });
    }

    ae.reconfigure();
    if (cas == null) {
      cas = ae.newCAS();
      cas.setDocumentText(FileUtils.file2String(textFile, "UTF-8"));
    }
    ae.process(cas);

    ae.destroy();
    return cas;
  }

  public static Type getTestType(CAS cas, int i) {
    if (cas == null)
      return null;
    return cas.getTypeSystem().getType(TYPE + i);
  }
}
