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

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.jcas.JCas;
import org.junit.jupiter.api.Test;
public class DifferentSeederTest {

  @Test
  public void test() throws Exception {
    CAS cas = RutaTestUtils.getCAS("This is a test.");
    JCas jcas = cas.getJCas();
    
    String script = "";
    script += "ANY{-> T5};";
    script += "CW{-> T6};";
    script += "T1{-> T7};";
    script += "T2 T3{-> T8};";
    script += "T1 # T4{-> T9};";
    
    AnalysisEngine ae = AnalysisEngineFactory.createEngine(RutaEngine.class, 
            RutaEngine.PARAM_RULES, script,
            RutaEngine.PARAM_SEEDERS, new String[] {DifferentSeeder.class.getName()});
    
    ae.process(jcas);
    
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "This");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 2, "is", "is");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "a");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "test");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 7, 1, "This");
    RutaTestUtils.assertAnnotationsEquals(cas, 8, 1, "a");
    RutaTestUtils.assertAnnotationsEquals(cas, 9, 1, "test");
    
  }
  
}
