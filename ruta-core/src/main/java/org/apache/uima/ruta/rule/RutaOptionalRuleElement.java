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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaProcessRuntimeException;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.action.AbstractRutaAction;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.condition.AbstractRutaCondition;
import org.apache.uima.ruta.rule.quantifier.NormalQuantifier;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class RutaOptionalRuleElement extends RutaRuleElement {

  public RutaOptionalRuleElement(List<AbstractRutaCondition> conditions,
          List<AbstractRutaAction> actions, RuleElementContainer container, RutaBlock parent) {

    super(null, new NormalQuantifier(), conditions, actions, container, parent);
  }

  @Override
  public Collection<? extends AnnotationFS> getAnchors(RutaStream stream) {
    throw new RutaProcessRuntimeException(
            "Using an optional rule lement as anchor is not allowed!");
  }

  @Override
  public List<RuleMatch> startMatch(RuleMatch ruleMatch, RuleApply ruleApply,
          ComposedRuleElementMatch containerMatch, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd) {
    throw new RutaProcessRuntimeException(
            "Using an optional rule lement as anchor is not allowed!");
  }

  @Override
  protected void doMatch(boolean after, AnnotationFS annotation, RuleMatch ruleMatch,
          ComposedRuleElementMatch containerMatch, boolean ruleAnchor, RutaStream stream,
          InferenceCrowd crowd) {
    RuleElementMatch result = new RuleElementMatch(this, containerMatch);
    result.setRuleAnchor(ruleAnchor);
    List<EvaluatedCondition> evaluatedConditions = new ArrayList<EvaluatedCondition>(
            conditions.size());
    boolean base = true;
    MatchContext context = new MatchContext(annotation, this, ruleMatch, after);

    List<AnnotationFS> textsMatched = new ArrayList<AnnotationFS>(1);
    if (annotation != null) {
      textsMatched.add(annotation);
    }
    // already set the matched text and inform others
    result.setMatchInfo(base, textsMatched, stream);
    context.getParent().getEnvironment().addMatchToVariable(ruleMatch, this, context, stream);
    if (base) {
      for (AbstractRutaCondition condition : conditions) {
        crowd.beginVisit(condition, null);
        EvaluatedCondition eval = condition.eval(context, stream, crowd);
        crowd.endVisit(condition, null);
        evaluatedConditions.add(eval);
        if (!eval.isValue()) {
          break;
        }
      }
    }
    result.setConditionInfo(base, evaluatedConditions);
    boolean inlinedRulesMatched = matchInnerRules(ruleMatch, stream, crowd);
    result.setInlinedRulesMatched(inlinedRulesMatched);
    ruleMatch.setMatched(ruleMatch.matched() && result.matched());
  }

  @Override
  protected boolean isNotConsumable(Collection<? extends AnnotationFS> nextAnnotations) {
    return false;
  }

  @Override
  public String toString() {
    return "_";
  }

  @Override
  public long estimateAnchors(RutaStream stream) {
    return Integer.MAX_VALUE;
  }

  @Override
  public Collection<? extends AnnotationFS> getNextAnnotations(boolean after,
          AnnotationFS annotation, RutaStream stream) {
    return Arrays.asList(stream.getBasicNextTo(!after, annotation));
  }

}
