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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaElement;
import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.ScriptApply;
import org.apache.uima.ruta.action.AbstractRutaAction;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.condition.AbstractRutaCondition;
import org.apache.uima.ruta.rule.quantifier.NormalQuantifier;
import org.apache.uima.ruta.rule.quantifier.RuleElementQuantifier;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public abstract class AbstractRuleElement extends RutaElement implements RuleElement {

  protected RuleElementQuantifier quantifier;

  protected List<AbstractRutaCondition> conditions;

  protected List<AbstractRutaAction> actions;

  private boolean startAnchor;

  private String label;

  private RuleElementContainer container;

  protected RutaBlock parent;

  @SuppressWarnings("unchecked")
  protected final InferenceCrowd emptyCrowd = new InferenceCrowd(Collections.EMPTY_LIST);

  protected List<List<RutaStatement>> inlinedConditionRuleBlocks = new ArrayList<>();

  protected List<List<RutaStatement>> inlinedActionRuleBlocks = new ArrayList<>();

  public AbstractRuleElement(RuleElementQuantifier quantifier,
          List<AbstractRutaCondition> conditions, List<AbstractRutaAction> actions,
          RuleElementContainer container, RutaBlock parent) {
    super();
    this.quantifier = quantifier;
    this.conditions = conditions;
    this.actions = actions;
    this.container = container;
    this.parent = parent;
    if (this.conditions == null) {
      this.conditions = new ArrayList<AbstractRutaCondition>();
    }
    if (this.actions == null) {
      this.actions = new ArrayList<AbstractRutaAction>();
    }
    if (this.quantifier == null) {
      this.quantifier = new NormalQuantifier();
    }
  }

  protected void doneMatching(RuleMatch ruleMatch, RuleApply ruleApply, RutaStream stream,
          InferenceCrowd crowd) {
    if (!ruleMatch.isApplied()) {
      ruleApply.add(ruleMatch, stream);
      RutaRule rule = ruleMatch.getRule();
      Collection<String> localVariables = rule.getLabels();
      if (ruleMatch.matchedCompletely()) {
        rule.getEnvironment().acceptTempVariableValues(localVariables);
        rule.getRoot().applyRuleElements(ruleMatch, stream, crowd);
      } else {
        rule.getEnvironment().clearTempVariables(localVariables);
      }
      ruleMatch.setApplied(true);
    }
  }

  protected List<List<ScriptApply>> processInlinedActionRules(RuleMatch ruleMatch,
          RutaStream stream, InferenceCrowd crowd) {
    if (inlinedActionRuleBlocks != null && !inlinedActionRuleBlocks.isEmpty()) {
      return processInlinedRules(inlinedActionRuleBlocks, ruleMatch, stream, crowd);
    }
    return null;
  }

  protected List<List<ScriptApply>> processInlinedConditionRules(RuleMatch ruleMatch,
          RutaStream stream, InferenceCrowd crowd) {
    if (inlinedConditionRuleBlocks != null && !inlinedConditionRuleBlocks.isEmpty()) {
      return processInlinedRules(inlinedConditionRuleBlocks, ruleMatch, stream, crowd);
    }
    return null;
  }

  protected List<List<ScriptApply>> processInlinedRules(List<List<RutaStatement>> inlinedRuleBlocks,
          RuleMatch ruleMatch, RutaStream stream, InferenceCrowd crowd) {
    List<List<ScriptApply>> result = new ArrayList<>();
    List<AnnotationFS> matchedAnnotationsOf = ruleMatch.getMatchedAnnotationsOfElement(this);
    // TODO where to implement the explanation of inlined rules?
    // BlockApply blockApply = new BlockApply(this);
    // RuleApply dummyRuleApply = getDummyRuleApply(ruleMatch);
    // blockApply.setRuleApply(dummyRuleApply);
    // ruleMatch.addDelegateApply(this, blockApply);
    for (AnnotationFS annotationFS : matchedAnnotationsOf) {
      RutaStream windowStream = stream.getWindowStream(annotationFS, annotationFS.getType());
      for (List<RutaStatement> inlinedRules : inlinedRuleBlocks) {
        List<ScriptApply> blockResult = new ArrayList<>();
        for (RutaStatement each : inlinedRules) {
          ScriptApply apply = each.apply(windowStream, crowd);
          // blockApply.add(apply);
          ruleMatch.addDelegateApply(this, apply);
          blockResult.add(apply);
        }
        result.add(blockResult);
      }
    }
    return result;
  }

  @Override
  public void apply(RuleMatch ruleMatch, RutaStream stream, InferenceCrowd crowd) {
    for (AbstractRutaAction action : actions) {
      crowd.beginVisit(action, null);
      action.execute(new MatchContext(this, ruleMatch), stream, crowd);
      crowd.endVisit(action, null);
    }
    processInlinedActionRules(ruleMatch, stream, crowd);
  }

  protected boolean matchInnerRules(RuleMatch ruleMatch, RutaStream stream, InferenceCrowd crowd) {

    List<List<ScriptApply>> blockResults = processInlinedConditionRules(ruleMatch, stream, crowd);
    if (blockResults == null) {
      return true;
    }

    boolean matched = true;
    for (List<ScriptApply> list : blockResults) {
      matched &= atLeastOneRuleMatched(list);
    }
    return matched;
  }

  private boolean atLeastOneRuleMatched(List<ScriptApply> list) {
    for (ScriptApply scriptApply : list) {
      if (scriptApply instanceof RuleApply) {
        RuleApply ra = (RuleApply) scriptApply;
        if (ra.applied > 0) {
          return true;
        }
      }
    }
    return false;
  }

  protected List<RuleElementMatch> getMatch(RuleMatch ruleMatch,
          ComposedRuleElementMatch containerMatch) {
    List<RuleElementMatch> matchInfo;
    if (containerMatch != null) {
      matchInfo = containerMatch.getInnerMatches().get(this);
    } else {
      matchInfo = ruleMatch.getMatchInfo(this).get(0);
    }
    return matchInfo;
  }

  @Override
  public List<RuleElementMatch> evaluateMatches(List<RuleElementMatch> matches,
          MatchContext context, RutaStream stream) {
    return quantifier.evaluateMatches(matches, context, stream, emptyCrowd);
  }

  @Override
  public List<Integer> getSelfIndexList() {
    List<Integer> result = new ArrayList<Integer>(1);
    if (getContainer() == null) {
      return null;
    }
    int indexOf = getContainer().getRuleElements().indexOf(this);
    result.add(indexOf + 1);
    return result;
  }

  @Override
  public boolean hasAncestor(boolean after) {
    RuleElementContainer c = getContainer();
    if (c == null) {
      return false;
    }
    RuleElement nextElement = c.getNextElement(after, this);
    if (nextElement != null) {
      return true;
    }
    if (c instanceof ComposedRuleElement) {
      return ((ComposedRuleElement) c).hasAncestor(after);
    }
    return false;
  }

  protected boolean earlyExit(AnnotationFS eachAnchor, RuleApply ruleApply, RutaStream stream) {
    if (stream.isGreedyAnchoring() && ruleApply != null
            && isAlreadyCovered(eachAnchor, ruleApply, stream)) {
      // skip if next matched should not overlap
      return true;
    }
    if (stream.isOnlyOnce() && ruleApply != null && ruleApply.getApplied() > 0) {
      // skip if the rule should only be applied once, on the first successful match
      return true;
    }
    return false;
  }

  private boolean isAlreadyCovered(AnnotationFS eachAnchor, RuleApply ruleApply,
          RutaStream stream) {
    if (eachAnchor == null) {
      return false;
    }

    List<AbstractRuleMatch<? extends AbstractRule>> list = ruleApply.getList();
    Collections.reverse(list);
    for (AbstractRuleMatch<? extends AbstractRule> each : list) {
      if (each instanceof RuleMatch) {
        RuleMatch rm = (RuleMatch) each;
        List<AnnotationFS> matchedAnnotationsOf = Collections.emptyList();
        if (stream.isGreedyRule()) {
          matchedAnnotationsOf = rm.getMatchedAnnotationsOfRoot();
        } else if (stream.isGreedyRuleElement()) {
          matchedAnnotationsOf = rm.getMatchedAnnotationsOfElement(this);
        }
        for (AnnotationFS annotationFS : matchedAnnotationsOf) {
          if (eachAnchor.getBegin() >= annotationFS.getBegin()
                  && eachAnchor.getEnd() <= annotationFS.getEnd()) {
            return true;
          } else if (eachAnchor.getBegin() < annotationFS.getEnd()) {
            // overlapping annotations of composed rule elements with null ruleApply lookahead
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public RuleElementQuantifier getQuantifier() {
    return quantifier;
  }

  @Override
  public RutaBlock getParent() {
    return parent;
  }

  @Override
  public List<AbstractRutaCondition> getConditions() {
    return conditions;
  }

  public void setConditions(List<AbstractRutaCondition> conditions) {
    this.conditions = conditions;
  }

  @Override
  public List<AbstractRutaAction> getActions() {
    return actions;
  }

  public void setActions(List<AbstractRutaAction> actions) {
    this.actions = actions;
  }

  public void setQuantifier(RuleElementQuantifier quantifier) {
    this.quantifier = quantifier;
  }

  @Override
  public RutaRule getRule() {
    return container.getRule();
  }

  @Override
  public RuleElementContainer getContainer() {
    return container;
  }

  @Override
  public void setContainer(RuleElementContainer container) {
    this.container = container;
  }

  @Override
  public void setStartAnchor(boolean start) {
    this.startAnchor = start;
  }

  @Override
  public boolean isStartAnchor() {
    return startAnchor;
  }

  @Override
  public String getLabel() {
    return label;
  }

  @Override
  public void setLabel(String label) {
    this.label = label;
  }

  @Override
  public void addInlinedConditionRules(List<RutaStatement> innerRules) {
    this.inlinedConditionRuleBlocks.add(innerRules);
  }

  @Override
  public List<List<RutaStatement>> getInlinedConditionRuleBlocks() {
    return inlinedConditionRuleBlocks;
  }

  @Override
  public void addInlinedActionRules(List<RutaStatement> innerRules) {
    this.inlinedActionRuleBlocks.add(innerRules);
  }

  @Override
  public List<List<RutaStatement>> getInlinedActionRuleBlocks() {
    return inlinedActionRuleBlocks;
  }

}
