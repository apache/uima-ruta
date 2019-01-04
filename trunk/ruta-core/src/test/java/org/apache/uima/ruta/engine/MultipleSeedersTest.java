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
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.type.ALL;
import org.apache.uima.ruta.type.TruePositive;
import org.junit.Assert;
import org.junit.Test;

public class MultipleSeedersTest {

  @Test
  public void test() throws ResourceInitializationException, AnalysisEngineProcessException {
    
    AnalysisEngine ae = AnalysisEngineFactory.createEngine(RutaEngine.class, 
            RutaEngine.PARAM_RULES, "TokenSeed{-> TruePositive};",
            RutaEngine.PARAM_SEEDERS, new String[] {DummySeeder.class.getName(),DummySeeder.class.getName()});
    
    JCas jcas = ae.newJCas();
    jcas.setDocumentText("Dummy text.");
    ae.process(jcas);
    
    Assert.assertEquals(2, JCasUtil.select(jcas, TruePositive.class).size());
    Assert.assertEquals(0, JCasUtil.select(jcas, ALL.class).size());
    
  }
  
}
