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

package org.apache.uima.textmarker;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
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
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.textmarker.engine.TextMarkerEngine;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;

public class TextMarkerTestUtils {

  private static final String TYPE = "org.apache.uima.T";

  public static CAS process(String ruleFileName, String textFileName, int amount)
          throws URISyntaxException, IOException, InvalidXMLException,
          ResourceInitializationException, AnalysisEngineProcessException,
          ResourceConfigurationException {
    return process(ruleFileName, textFileName, amount, false, null);
  }

  public static CAS process(String ruleFileName, String textFileName, int amount,
          boolean dynamicAnchoring, Map<String, String> complexTypes) throws URISyntaxException,
          IOException, InvalidXMLException, ResourceInitializationException,
          AnalysisEngineProcessException, ResourceConfigurationException {
    URL resource = TextMarkerTestUtils.class.getClassLoader().getResource(ruleFileName);
    File ruleFile = new File(resource.toURI());
    resource = TextMarkerTestUtils.class.getClassLoader().getResource(textFileName);
    File textFile = new File(resource.toURI());
    URL url = TextMarkerEngine.class.getClassLoader().getResource("BasicEngine.xml");
    if (url == null) {
      url = TextMarkerTestUtils.class.getClassLoader().getResource(
              "org/apache/uima/textmarker/TestEngine.xml");
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
        basicTypeSystem.addType(entry.getKey(), "Type for Testing", entry.getValue());
      }
    }

    Collection<TypeSystemDescription> tsds = new ArrayList<TypeSystemDescription>();
    tsds.add(basicTypeSystem);
    TypeSystemDescription mergeTypeSystems = CasCreationUtils.mergeTypeSystems(tsds);
    aed.getAnalysisEngineMetaData().setTypeSystem(mergeTypeSystems);

    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    ae.setConfigParameterValue(TextMarkerEngine.SCRIPT_PATHS, new String[] { ruleFile
            .getParentFile().getPath() });
    String name = ruleFile.getName();
    if (name.endsWith(".tm")) {
      name = name.substring(0, name.length() - 3);
    }
    ae.setConfigParameterValue(TextMarkerEngine.MAIN_SCRIPT, name);
    ae.setConfigParameterValue(TextMarkerEngine.DYNAMIC_ANCHORING, dynamicAnchoring);
    ae.reconfigure();
    CAS cas = ae.newCAS();
    cas.setDocumentText(FileUtils.file2String(textFile, "UTF-8"));
    ae.process(cas);
    return cas;
  }

  public static Type getTestType(CAS cas, int i) {
    if (cas == null)
      return null;
    return cas.getTypeSystem().getType(TYPE + i);
  }
}
