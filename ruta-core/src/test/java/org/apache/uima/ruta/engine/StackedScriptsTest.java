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

import java.io.IOException;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.InvalidXMLException;
import org.junit.Test;

public class StackedScriptsTest {

  @Test
  public void test() throws ResourceInitializationException, InvalidXMLException, IOException, AnalysisEngineProcessException {
    String rules1 = "CW{->T1};";
    String rules2 = "T1 W{->T2} W{->T3};";
    String rules3 = "W{PARTOF({T1,T2,T3})->T4};";

    AnalysisEngine rutaAE1 = createEngine(RutaEngine.class, RutaEngine.PARAM_RULES, rules1);
    AnalysisEngine rutaAE2 = createEngine(RutaEngine.class, RutaEngine.PARAM_RULES, rules2);
    AnalysisEngine rutaAE3 = createEngine(RutaEngine.class, RutaEngine.PARAM_RULES, rules3);

    CAS cas = RutaTestUtils.getCAS("This is a simple test.");

    rutaAE1.process(cas);
    rutaAE2.process(cas);
    rutaAE3.process(cas);
    
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "This");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "is");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "a");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 3, "This", "is", "a");
    
    
  }

}
