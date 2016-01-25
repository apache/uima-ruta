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

package org.apache.uima.ruta;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.util.InvalidXMLException;
import org.junit.Test;

public class QuantifierTest {
  
  @Test
  public void testRightToLeftMinMaxReluctantToLiteral() throws CASException, ResourceInitializationException, InvalidXMLException,
          IOException, AnalysisEngineProcessException {
    
    JCas jcas = RutaTestUtils.getCAS("a b c d e.").getJCas();
    Assert.assertEquals("a b c d e.", Ruta.select(jcas, "\"a\" W[0,5]? @PERIOD;").get(0).getCoveredText());
    Assert.assertEquals("b c d e.", Ruta.select(jcas, "\"b\" W[0,5]? @PERIOD;").get(0).getCoveredText());
    Assert.assertEquals("c d e.", Ruta.select(jcas, "\"c\" W[0,5]? @PERIOD;").get(0).getCoveredText());
    Assert.assertEquals("d e.", Ruta.select(jcas, "\"d\" W[0,5]? @PERIOD;").get(0).getCoveredText());
    Assert.assertEquals("e.", Ruta.select(jcas, "\"e\" W[0,5]? @PERIOD;").get(0).getCoveredText());
    
    jcas.release();
  }
  
  @Test
  public void testRightToLeftMinMaxGreedyToLiteral() throws CASException, ResourceInitializationException, InvalidXMLException,
          IOException, AnalysisEngineProcessException {
    
    JCas jcas = RutaTestUtils.getCAS("a b c d e.").getJCas();
    Assert.assertEquals("a b c d e.", Ruta.select(jcas, "\"a\" W[0,4] @PERIOD;").get(0).getCoveredText());
    Assert.assertEquals("b c d e.", Ruta.select(jcas, "\"b\" W[0,3] @PERIOD;").get(0).getCoveredText());
    Assert.assertEquals("c d e.", Ruta.select(jcas, "\"c\" W[0,2] @PERIOD;").get(0).getCoveredText());
    Assert.assertEquals("d e.", Ruta.select(jcas, "\"d\" W[0,1] @PERIOD;").get(0).getCoveredText());
    Assert.assertEquals("e.", Ruta.select(jcas, "\"e\" W[0,0] @PERIOD;").get(0).getCoveredText());
    
    jcas.release();
  }
  
  
}
