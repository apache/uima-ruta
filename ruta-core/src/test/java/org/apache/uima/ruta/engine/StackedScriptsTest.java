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

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.XMLInputSource;
import org.junit.Test;

public class StackedScriptsTest {

  private static final String DOC_TEXT = "This is a simple test.";

  private static final int LINES = 1;

  private static final int MANY_LINES = 100;

  String rules1 = "CW{->T1};";

  String rules2 = "T1 W{->T2} W{->T3};";

  String rules3 = "W{PARTOF({T1,T2,T3})->T4};";

  @Test
  public void testWithUimaFitAggregated() throws Exception {

    AnalysisEngine aae = createEngine(createEngineDescription(
            createEngineDescription(RutaEngine.class, RutaEngine.PARAM_RULES, rules1),
            createEngineDescription(RutaEngine.class, RutaEngine.PARAM_RULES, rules2),
            createEngineDescription(RutaEngine.class, RutaEngine.PARAM_RULES, rules3)));

    CAS cas = getCAS(LINES);

    aae.process(cas);

    checkResult(cas, LINES);

    cas.release();
  }

  @Test
  public void testWithoutUimaFit() throws Exception {

    AnalysisEngine rutaAE1 = createAnalysisEngine(rules1, null);
    AnalysisEngine rutaAE2 = createAnalysisEngine(rules2, null);
    AnalysisEngine rutaAE3 = createAnalysisEngine(rules3, null);

    processAndTest(rutaAE1, rutaAE2, rutaAE3);

  }

  private void processAndTest(AnalysisEngine rutaAE1, AnalysisEngine rutaAE2,
          AnalysisEngine rutaAE3) throws Exception {
    CAS cas = getCAS(LINES);

    rutaAE1.process(cas);
    rutaAE2.process(cas);
    rutaAE3.process(cas);

    checkResult(cas, LINES);

    cas.release();
  }

  private void checkResult(CAS cas, int lines) {

    String[] t1 = new String[lines];
    String[] t2 = new String[lines];
    String[] t3 = new String[lines];
    String[] t4 = new String[lines * 3];
    for (int i = 0; i < lines; i++) {
      t1[i] = "This";
    }
    for (int i = 0; i < lines; i++) {
      t2[i] = "is";
    }
    for (int i = 0; i < lines; i++) {
      t3[i] = "a";
    }
    for (int i = 0; i < lines * 3; i++) {
      t4[i] = "This";
      i++;
      t4[i] = "is";
      i++;
      t4[i] = "a";
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1 * lines, t1);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1 * lines, t2);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1 * lines, t3);
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 3 * lines, t4);
  }

  private CAS getCAS(int lines) throws Exception {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < lines; i++) {
      sb.append(DOC_TEXT);
      sb.append("\n");
    }
    CAS cas = RutaTestUtils.getCAS(sb.toString());
    return cas;
  }

  private AnalysisEngine createAnalysisEngine(String rules, String[] reindexOnly) throws Exception {
    URL url = RutaEngine.class.getClassLoader().getResource("BasicEngine.xml");
    if (url == null) {
      url = RutaTestUtils.class.getClassLoader()
              .getResource("org/apache/uima/ruta/engine/BasicEngine.xml");
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
    if (reindexOnly != null) {
      ae.setConfigParameterValue(RutaEngine.PARAM_REINDEX_ONLY, reindexOnly);
    }
    ae.reconfigure();
    return ae;

  }

  @Test
  public void testPerformanceOfReindexOnly() throws Exception {
    AnalysisEngine aaeNoReindex = createEngine(createEngineDescription(
            createEngineDescription(RutaEngine.class, RutaEngine.PARAM_RULES, rules1),
            createEngineDescription(RutaEngine.class, RutaEngine.PARAM_RULES, rules2),
            createEngineDescription(RutaEngine.class, RutaEngine.PARAM_RULES, rules3)));
    AnalysisEngine aaeReindex = createEngine(createEngineDescription(
            createEngineDescription(RutaEngine.class, RutaEngine.PARAM_RULES, rules1,
                    RutaEngine.PARAM_REINDEX_ONLY, new String[] { CAS.TYPE_NAME_ANNOTATION }),
            createEngineDescription(RutaEngine.class, RutaEngine.PARAM_RULES, rules2,
                    RutaEngine.PARAM_REINDEX_ONLY, new String[] { RutaTestUtils.TYPE + "1" }),
            createEngineDescription(RutaEngine.class, RutaEngine.PARAM_RULES, rules3,
                    RutaEngine.PARAM_REINDEX_ONLY,
                    new String[] { RutaTestUtils.TYPE + "2", RutaTestUtils.TYPE + "3" })));

    long start = 0;
    long end = 0;
    CAS cas = null;

    cas = getCAS(MANY_LINES);
    start = System.currentTimeMillis();
    aaeNoReindex.process(cas);
    end = System.currentTimeMillis();
    checkResult(cas, MANY_LINES);
    cas.release();
    System.out.printf("Reindexing all... \t took %d ms %n", end - start);

    cas = getCAS(MANY_LINES);
    start = System.currentTimeMillis();
    aaeReindex.process(cas);
    end = System.currentTimeMillis();
    checkResult(cas, MANY_LINES);
    cas.release();
    System.out.printf("Reindexing selected... \t took %d ms %n", end - start);
  }

}
