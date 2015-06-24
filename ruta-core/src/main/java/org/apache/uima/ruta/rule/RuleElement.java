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

package org.apache.uima.ruta.rule;

import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.action.AbstractRutaAction;
import org.apache.uima.ruta.condition.AbstractRutaCondition;
import org.apache.uima.ruta.rule.quantifier.RuleElementQuantifier;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public interface RuleElement {

  void apply(RuleMatch match, RutaStream stream, InferenceCrowd crowd);

  List<RuleMatch> startMatch(RuleMatch ruleMatch, RuleApply ruleApply,
          ComposedRuleElementMatch containerMatch, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd);

  List<RuleMatch> continueMatch(boolean after, AnnotationFS annotation, RuleMatch ruleMatch,
          RuleApply ruleApply, ComposedRuleElementMatch containerMatch,
          RutaRuleElement sideStepOrigin, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd);

  List<RuleMatch> continueOwnMatch(boolean after, AnnotationFS annotation, RuleMatch ruleMatch,
          RuleApply ruleApply, ComposedRuleElementMatch containerMatch,
          RutaRuleElement sideStepOrigin, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd);

  List<RuleElementMatch> evaluateMatches(List<RuleElementMatch> matches, RutaBlock parent,
          RutaStream stream);

  Collection<AnnotationFS> getAnchors(RutaStream symbolStream);

  RutaBlock getParent();

  RutaRule getRule();

  RuleElementContainer getContainer();

  void setContainer(RuleElementContainer container);

  RuleElementQuantifier getQuantifier();

  long estimateAnchors(RutaStream stream);

  List<Integer> getSelfIndexList();

  List<AbstractRutaCondition> getConditions();

  List<AbstractRutaAction> getActions();

  boolean hasAncestor(boolean after);

  void setStartAnchor(boolean start);

  boolean isStartAnchor();

  void setInlinedActionRules(List<RutaStatement> innerRules);
  
  void setInlinedConditionRules(List<RutaStatement> innerRules);

}
