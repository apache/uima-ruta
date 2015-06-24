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
import java.util.Collections;
import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaElement;
import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.ScriptApply;
import org.apache.uima.ruta.action.AbstractRutaAction;
import org.apache.uima.ruta.condition.AbstractRutaCondition;
import org.apache.uima.ruta.rule.quantifier.NormalQuantifier;
import org.apache.uima.ruta.rule.quantifier.RuleElementQuantifier;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public abstract class AbstractRuleElement extends RutaElement implements RuleElement {

  protected RuleElementQuantifier quantifier;

  protected List<AbstractRutaCondition> conditions;

  protected List<AbstractRutaAction> actions;

  private boolean startAnchor;

  private RuleElementContainer container;

  protected RutaBlock parent;

  @SuppressWarnings("unchecked")
  protected final InferenceCrowd emptyCrowd = new InferenceCrowd(Collections.EMPTY_LIST);

  protected List<RutaStatement> inlinedConditionRules;

  protected List<RutaStatement> inlinedActionRules;
  
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
      ruleApply.add(ruleMatch);
      if (ruleMatch.matchedCompletely()) {
        ruleMatch.getRule().getRoot().applyRuleElements(ruleMatch, stream, crowd);
      }
      ruleMatch.setApplied(true);
    }
  }

  protected List<ScriptApply> processInlinedActionRules(RuleMatch ruleMatch, RutaStream stream,
          InferenceCrowd crowd) {
    if (inlinedActionRules != null && !inlinedActionRules.isEmpty()) {
      return processInlinedRules(inlinedActionRules, ruleMatch, stream, crowd);
    }
    return null;
  }

  protected List<ScriptApply> processInlinedConditionRules(RuleMatch ruleMatch, RutaStream stream,
          InferenceCrowd crowd) {
    if (inlinedConditionRules != null && !inlinedConditionRules.isEmpty()) {
      return processInlinedRules(inlinedConditionRules, ruleMatch, stream, crowd);
    }
    return null;
  }
  
  protected List<ScriptApply> processInlinedRules(List<RutaStatement> inlinedRules, RuleMatch ruleMatch, RutaStream stream,
          InferenceCrowd crowd) {
    List<ScriptApply> result = new ArrayList<ScriptApply>();
    List<AnnotationFS> matchedAnnotationsOf = ruleMatch.getMatchedAnnotationsOf(this);
    // TODO where to implement the explanation of inlined rules?
    // BlockApply blockApply = new BlockApply(this);
    // RuleApply dummyRuleApply = getDummyRuleApply(ruleMatch);
    // blockApply.setRuleApply(dummyRuleApply);
    // ruleMatch.addDelegateApply(this, blockApply);
    for (AnnotationFS annotationFS : matchedAnnotationsOf) {
      RutaStream windowStream = stream.getWindowStream(annotationFS, annotationFS.getType());
      for (RutaStatement each : inlinedRules) {
        ScriptApply apply = each.apply(windowStream, crowd);
        // blockApply.add(apply);
        ruleMatch.addDelegateApply(this, apply);
        result.add(apply);
      }
    }
    return result;
  }

  

  public void apply(RuleMatch ruleMatch, RutaStream stream, InferenceCrowd crowd) {
    for (AbstractRutaAction action : actions) {
      crowd.beginVisit(action, null);
      action.execute(ruleMatch, this, stream, crowd);
      crowd.endVisit(action, null);
    }
    processInlinedActionRules(ruleMatch, stream, crowd);
  }

  protected boolean matchInnerRules(RuleMatch ruleMatch, RutaStream stream, InferenceCrowd crowd) {
    boolean inlinedRulesMatched = true;
    List<ScriptApply> list = processInlinedConditionRules(ruleMatch, stream, crowd);
    if (list != null) {
      inlinedRulesMatched = false;
      for (ScriptApply scriptApply : list) {
        if (scriptApply instanceof RuleApply) {
          RuleApply ra = (RuleApply) scriptApply;
          if (ra.applied > 0) {
            inlinedRulesMatched = true;
          }
        }
      }
    }
    return inlinedRulesMatched;
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

  public List<RuleElementMatch> evaluateMatches(List<RuleElementMatch> matches, RutaBlock parent,
          RutaStream stream) {
    return quantifier.evaluateMatches(matches, parent, stream, emptyCrowd);
  }

  public List<Integer> getSelfIndexList() {
    List<Integer> result = new ArrayList<Integer>(1);
    if (getContainer() == null) {
      return null;
    }
    int indexOf = getContainer().getRuleElements().indexOf(this);
    result.add(indexOf + 1);
    return result;
  }

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

  private boolean isAlreadyCovered(AnnotationFS eachAnchor, RuleApply ruleApply, RutaStream stream) {
    List<AbstractRuleMatch<? extends AbstractRule>> list = ruleApply.getList();
    Collections.reverse(list);
    for (AbstractRuleMatch<? extends AbstractRule> each : list) {
      if (each instanceof RuleMatch) {
        RuleMatch rm = (RuleMatch) each;
        List<AnnotationFS> matchedAnnotationsOf = Collections.emptyList();
        if (stream.isGreedyRule()) {
          matchedAnnotationsOf = rm.getMatchedAnnotationsOfRoot();
        } else if (stream.isGreedyRuleElement()) {
          matchedAnnotationsOf = rm.getMatchedAnnotationsOf(this);
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

  public RuleElementQuantifier getQuantifier() {
    return quantifier;
  }

  public RutaBlock getParent() {
    return parent;
  }

  public List<AbstractRutaCondition> getConditions() {
    return conditions;
  }

  public void setConditions(List<AbstractRutaCondition> conditions) {
    this.conditions = conditions;
  }

  public List<AbstractRutaAction> getActions() {
    return actions;
  }

  public void setActions(List<AbstractRutaAction> actions) {
    this.actions = actions;
  }

  public void setQuantifier(RuleElementQuantifier quantifier) {
    this.quantifier = quantifier;
  }

  public RutaRule getRule() {
    return container.getRule();
  }

  public RuleElementContainer getContainer() {
    return container;
  }

  public void setContainer(RuleElementContainer container) {
    this.container = container;
  }

  public void setStartAnchor(boolean start) {
    this.startAnchor = start;
  }

  public boolean isStartAnchor() {
    return startAnchor;
  }

  public void setInlinedConditionRules(List<RutaStatement> innerRules) {
    this.inlinedConditionRules = innerRules;
  }
  
  public List<RutaStatement> getInlinedConditionRules() {
    return inlinedConditionRules;
  }
  
  public void setInlinedActionRules(List<RutaStatement> innerRules) {
    this.inlinedActionRules = innerRules;
  }
  
  public List<RutaStatement> getInlinedActionRules() {
    return inlinedActionRules;
  }

}
