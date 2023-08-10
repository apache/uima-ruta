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

import static org.assertj.core.api.Assertions.assertThat;

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
import org.apache.uima.ruta.type.DebugInlinedBlock;
import org.apache.uima.ruta.type.DebugMatchedRuleMatch;
import org.apache.uima.ruta.type.DebugRuleApply;
import org.apache.uima.ruta.type.DebugRuleElementMatch;
import org.apache.uima.ruta.type.DebugRuleElementMatches;
import org.apache.uima.ruta.type.DebugRuleMatch;
import org.apache.uima.ruta.type.DebugScriptApply;
import org.junit.jupiter.api.Test;

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
    assertThat(debugScriptApplies).hasSize(1);
    DebugScriptApply debugScriptApply = debugScriptApplies.iterator().next();
    assertThat(debugScriptApply instanceof DebugBlockApply).isTrue();
    DebugBlockApply debugBlockApply = (DebugBlockApply) debugScriptApply;

    FSArray<DebugScriptApply> innerApply = debugBlockApply.getInnerApply();
    assertThat(innerApply).hasSize(1);
    FeatureStructure innerApplyFS = innerApply.get(0);
    assertThat(debugScriptApply instanceof DebugRuleApply).isTrue();
    DebugRuleApply debugRuleApply = (DebugRuleApply) innerApplyFS;

    FSArray<DebugRuleMatch> rules = debugRuleApply.getRules();
    assertThat(rules).hasSize(1);
    FeatureStructure ruleMatchFS = rules.get(0);
    assertThat(ruleMatchFS instanceof DebugMatchedRuleMatch).isTrue();
    DebugMatchedRuleMatch debugMatchedRuleMatch = (DebugMatchedRuleMatch) ruleMatchFS;
    assertThat(debugMatchedRuleMatch.getDelegates()).hasSize(0);

    FSArray<DebugRuleElementMatches> reMatchesArray = debugMatchedRuleMatch.getElements();
    assertThat(reMatchesArray).hasSize(2);
    FeatureStructure reMatches1FS = reMatchesArray.get(0);
    FeatureStructure reMatches2FS = reMatchesArray.get(1);
    assertThat(reMatches1FS instanceof DebugRuleElementMatches).isTrue();
    assertThat(reMatches2FS instanceof DebugRuleElementMatches).isTrue();
    DebugRuleElementMatches re1Matches = (DebugRuleElementMatches) reMatches1FS;
    DebugRuleElementMatches re2Matches = (DebugRuleElementMatches) reMatches2FS;

    FSArray<DebugInlinedBlock> inlinedActionRules1 = re1Matches.getInlinedActionBlocks();
    FSArray<DebugInlinedBlock> inlinedActionRules2 = re2Matches.getInlinedActionBlocks();

    assertThat(inlinedActionRules1).hasSize(1);
    assertThat(inlinedActionRules2).hasSize(2);

    DebugRuleElementMatch re1Match = (DebugRuleElementMatch) re1Matches.getMatches().get(0);
    DebugRuleElementMatch re2Match = (DebugRuleElementMatch) re2Matches.getMatches().get(0);

    FSArray<DebugInlinedBlock> inlinedConditionRules1 = re1Match.getInlinedConditionBlocks();
    FSArray<DebugInlinedBlock> inlinedConditionRules2 = re2Match.getInlinedConditionBlocks();

    assertThat(inlinedConditionRules1).hasSize(2);
    assertThat(inlinedConditionRules2).hasSize(1);

  }
}
