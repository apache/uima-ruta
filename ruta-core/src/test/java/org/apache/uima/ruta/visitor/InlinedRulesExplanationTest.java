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

package org.apache.uima.ruta.visitor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.type.DebugBlockApply;
import org.apache.uima.ruta.type.DebugMatchedRuleMatch;
import org.apache.uima.ruta.type.DebugRuleApply;
import org.apache.uima.ruta.type.DebugRuleElementMatch;
import org.apache.uima.ruta.type.DebugRuleElementMatches;
import org.apache.uima.ruta.type.DebugScriptApply;
import org.junit.Assert;
import org.junit.Test;

public class InlinedRulesExplanationTest {

  @Test
  public void test() throws Exception {

    String document = "This is a test.";
    String script = "";
    script += "CW<-{ANY;W;}<-{ANY;CW;}->{Document;} SW<-{ANY; SW;}->{Document; ANY;}->{ANY; NUM;};";

    CAS cas = RutaTestUtils.getCAS(document);
    Map<String, Object> parameters = new HashMap<>();
    parameters.put(RutaEngine.PARAM_DEBUG, Boolean.TRUE);
    parameters.put(RutaEngine.PARAM_DEBUG_WITH_MATCHES, Boolean.TRUE);

    Ruta.apply(cas, script, parameters);

    JCas jcas = cas.getJCas();

    Collection<DebugScriptApply> debugScriptApplies = JCasUtil.select(jcas, DebugScriptApply.class);
    Assert.assertEquals(1, debugScriptApplies.size());
    DebugScriptApply debugScriptApply = debugScriptApplies.iterator().next();
    Assert.assertTrue(debugScriptApply instanceof DebugBlockApply);
    DebugBlockApply debugBlockApply = (DebugBlockApply) debugScriptApply;

    FSArray innerApply = debugBlockApply.getInnerApply();
    Assert.assertEquals(1, innerApply.size());
    FeatureStructure innerApplyFS = innerApply.get(0);
    Assert.assertTrue(debugScriptApply instanceof DebugRuleApply);
    DebugRuleApply debugRuleApply = (DebugRuleApply) innerApplyFS;

    FSArray rules = debugRuleApply.getRules();
    Assert.assertEquals(1, rules.size());
    FeatureStructure ruleMatchFS = rules.get(0);
    Assert.assertTrue(ruleMatchFS instanceof DebugMatchedRuleMatch);
    DebugMatchedRuleMatch debugMatchedRuleMatch = (DebugMatchedRuleMatch) ruleMatchFS;
    Assert.assertEquals(0, debugMatchedRuleMatch.getDelegates().size());

    FSArray reMatchesArray = debugMatchedRuleMatch.getElements();
    Assert.assertEquals(2, reMatchesArray.size());
    FeatureStructure reMatches1FS = reMatchesArray.get(0);
    FeatureStructure reMatches2FS = reMatchesArray.get(1);
    Assert.assertTrue(reMatches1FS instanceof DebugRuleElementMatches);
    Assert.assertTrue(reMatches2FS instanceof DebugRuleElementMatches);
    DebugRuleElementMatches re1Matches = (DebugRuleElementMatches) reMatches1FS;
    DebugRuleElementMatches re2Matches = (DebugRuleElementMatches) reMatches2FS;

    FSArray inlinedActionRules1 = re1Matches.getInlinedActionRules();
    FSArray inlinedActionRules2 = re2Matches.getInlinedActionRules();

    Assert.assertEquals(1, inlinedActionRules1.size());
    Assert.assertEquals(2, inlinedActionRules2.size());

    DebugRuleElementMatch re1Match = (DebugRuleElementMatch) re1Matches.getMatches().get(0);
    DebugRuleElementMatch re2Match = (DebugRuleElementMatch) re2Matches.getMatches().get(0);

    FSArray inlinedConditionRules1 = re1Match.getInlinedConditionRules();
    FSArray inlinedConditionRules2 = re2Match.getInlinedConditionRules();

    Assert.assertEquals(2, inlinedConditionRules1.size());
    Assert.assertEquals(1, inlinedConditionRules2.size());

  }
}
