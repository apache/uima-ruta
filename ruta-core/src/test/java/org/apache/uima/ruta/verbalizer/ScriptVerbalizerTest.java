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

package org.apache.uima.ruta.verbalizer;

import java.util.Collection;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.type.DebugBlockApply;
import org.apache.uima.ruta.type.DebugRuleApply;
import org.junit.Assert;
import org.junit.Test;


public class ScriptVerbalizerTest {

  @Test
  public void testRuleVerbalization() throws Exception{
    JCas jcas = RutaTestUtils.getCAS("Some text.").getJCas();
    
    assertRuleVerbalization(jcas, "MARK(T1);");
    assertRuleVerbalization(jcas, "W{->T1} W{->T2};");
    assertRuleVerbalization(jcas, "T1<-{W PERIOD;};");
    assertRuleVerbalization(jcas, "T1->{W{->T1} PERIOD;};");
    assertRuleVerbalization(jcas, "T1<-{W PERIOD;}->{W{->T1} PERIOD;};");
    assertRuleVerbalization(jcas, "W W? W?? W+ W+? W* W*? W[1,2] W[1,2]? #;");
    assertRuleVerbalization(jcas, "Document{CONTAINS(T1)->T2};");
    assertRuleVerbalization(jcas, "ADDFILTERTYPE(CW);");
    
    jcas.release();
  }

  private void assertRuleVerbalization(JCas jcas, String rule) throws AnalysisEngineProcessException, ResourceInitializationException {
    assertRuleVerbalization(jcas, rule, rule);
  }
  
  private void assertRuleVerbalization(JCas jcas, String rule, String expected) throws AnalysisEngineProcessException, ResourceInitializationException {
    Ruta.applyRule(jcas, rule, RutaEngine.PARAM_DEBUG, true, RutaEngine.PARAM_DEBUG_WITH_MATCHES, true);
    Collection<DebugBlockApply> blockApplies = JCasUtil.select(jcas, DebugBlockApply.class);
    Assert.assertEquals(1, blockApplies.size());
    DebugBlockApply blockApply =  blockApplies.iterator().next();
    Assert.assertEquals(1, blockApply.getInnerApply().size());
    DebugRuleApply ruleApply = (DebugRuleApply) blockApply.getInnerApply(0);
    Assert.assertEquals(expected, ruleApply.getElement());
    Ruta.removeDebugInformation(jcas);
  }
  
  @Test
  public void testScriptBlockVerbalization() throws Exception{
    
  }
  
}
