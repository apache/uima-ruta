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

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.junit.Test;

public class StackedScriptsTest {

  private static final String DOC_TEXT = "This is a simple test.";

  private static final int LINES = 1;

  String rules1 = "CW{->T1};";

  String rules2 = "T1 W{->T2} W{->T3};";

  String rules3 = "W{PARTOF({T1,T2,T3})->T4};";


  @Test
  public void testWithUimaFitAggregated() throws ResourceInitializationException,
          InvalidXMLException, IOException, AnalysisEngineProcessException,
          ResourceConfigurationException {

    AnalysisEngine aae = createEngine(createEngineDescription(
            createEngineDescription(RutaEngine.class, RutaEngine.PARAM_RULES, rules1),
            createEngineDescription(RutaEngine.class, RutaEngine.PARAM_RULES, rules2),
            createEngineDescription(RutaEngine.class, RutaEngine.PARAM_RULES, rules3)));

    CAS cas = getCAS();

    aae.process(cas);
    
    checkResult(cas);
  }

  @Test
  public void testWithoutUimaFit() throws ResourceInitializationException, InvalidXMLException,
          IOException, AnalysisEngineProcessException, ResourceConfigurationException {

    AnalysisEngine rutaAE1 = createAnalysisEngine(rules1);
    AnalysisEngine rutaAE2 = createAnalysisEngine(rules2);
    AnalysisEngine rutaAE3 = createAnalysisEngine(rules3);

    processAndTest(rutaAE1, rutaAE2, rutaAE3);

  }

  private void processAndTest(AnalysisEngine rutaAE1, AnalysisEngine rutaAE2, AnalysisEngine rutaAE3)
          throws ResourceInitializationException, IOException, InvalidXMLException,
          AnalysisEngineProcessException {
    CAS cas = getCAS();

    rutaAE1.process(cas);
    rutaAE2.process(cas);
    rutaAE3.process(cas);

    checkResult(cas);
  }

  private void checkResult(CAS cas) {
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "This");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "is");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "a");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 3, "This", "is", "a");
  }

  private CAS getCAS() throws ResourceInitializationException, IOException, InvalidXMLException {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < LINES; i++) {
      sb.append(DOC_TEXT);
      sb.append("\n");
    }
    CAS cas = RutaTestUtils.getCAS(sb.toString());
    return cas;
  }

  private AnalysisEngine createAnalysisEngine(String rules) throws IOException,
          InvalidXMLException, ResourceInitializationException, ResourceConfigurationException {
    URL url = RutaEngine.class.getClassLoader().getResource("BasicEngine.xml");
    if (url == null) {
      url = RutaTestUtils.class.getClassLoader().getResource("org/apache/uima/ruta/TestEngine.xml");
    }
    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngineDescription aed = (AnalysisEngineDescription) specifier;

    TypeSystemDescription basicTypeSystem = aed.getAnalysisEngineMetaData().getTypeSystem();
    for (int i = 1; i <= 50; i++) {
      basicTypeSystem.addType(RutaTestUtils.TYPE + i, "Type for Testing", "uima.tcas.Annotation");
    }
    Collection<TypeSystemDescription> tsds = new ArrayList<TypeSystemDescription>();
    tsds.add(basicTypeSystem);
    TypeSystemDescription mergeTypeSystems = CasCreationUtils.mergeTypeSystems(tsds);
    aed.getAnalysisEngineMetaData().setTypeSystem(mergeTypeSystems);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aed);

    ae.setConfigParameterValue(RutaEngine.PARAM_RULES, rules);
    ae.reconfigure();
    return ae;

  }

}
