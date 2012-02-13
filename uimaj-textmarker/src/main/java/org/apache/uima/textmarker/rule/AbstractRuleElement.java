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

package org.apache.uima.textmarker.rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.uima.textmarker.TextMarkerBlock;
import org.apache.uima.textmarker.TextMarkerElement;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.action.AbstractTextMarkerAction;
import org.apache.uima.textmarker.condition.AbstractTextMarkerCondition;
import org.apache.uima.textmarker.rule.quantifier.NormalQuantifier;
import org.apache.uima.textmarker.rule.quantifier.RuleElementQuantifier;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public abstract class AbstractRuleElement extends TextMarkerElement implements RuleElement {

  protected RuleElementQuantifier quantifier;

  protected List<AbstractTextMarkerCondition> conditions;

  protected List<AbstractTextMarkerAction> actions;

  private RuleElementContainer container;

  protected TextMarkerBlock parent;

  public AbstractRuleElement(RuleElementQuantifier quantifier,
          List<AbstractTextMarkerCondition> conditions, List<AbstractTextMarkerAction> actions,
          RuleElementContainer container, TextMarkerBlock parent) {
    super();
    this.quantifier = quantifier;
    this.conditions = conditions;
    this.actions = actions;
    this.container = container;
    this.parent = parent;
    if (this.conditions == null) {
      this.conditions = new ArrayList<AbstractTextMarkerCondition>();
    }
    if (this.actions == null) {
      this.actions = new ArrayList<AbstractTextMarkerAction>();
    }
    if (this.quantifier == null) {
      this.quantifier = new NormalQuantifier();
    }
  }

  @SuppressWarnings("unchecked")
  protected final InferenceCrowd emptyCrowd = new InferenceCrowd(Collections.EMPTY_LIST);

  protected void doneMatching(RuleMatch ruleMatch, RuleApply ruleApply, TextMarkerStream stream,
          InferenceCrowd crowd) {
    if (!ruleMatch.isApplied()) {
      ruleApply.add(ruleMatch);
      if (ruleMatch.matchedCompletely()) {
        ruleMatch.getRule().getRoot().applyRuleElements(ruleMatch, stream, crowd);
      }
      ruleMatch.setApplied(true);
    }
  }

  public void apply(RuleMatch ruleMatch, TextMarkerStream stream, InferenceCrowd crowd) {
    for (AbstractTextMarkerAction action : actions) {
      crowd.beginVisit(action, null);
      action.execute(ruleMatch, this, stream, crowd);
      crowd.endVisit(action, null);
    }
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

  public List<RuleElementMatch> evaluateMatches(List<RuleElementMatch> matches,
          TextMarkerBlock parent) {
    return quantifier.evaluateMatches(matches, parent, emptyCrowd);
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

  public RuleElementQuantifier getQuantifier() {
    return quantifier;
  }

  public TextMarkerBlock getParent() {
    return parent;
  }

  public List<AbstractTextMarkerCondition> getConditions() {
    return conditions;
  }

  public void setConditions(List<AbstractTextMarkerCondition> conditions) {
    this.conditions = conditions;
  }

  public List<AbstractTextMarkerAction> getActions() {
    return actions;
  }

  public void setActions(List<AbstractTextMarkerAction> actions) {
    this.actions = actions;
  }

  public void setQuantifier(RuleElementQuantifier quantifier) {
    this.quantifier = quantifier;
  }

  public TextMarkerRule getRule() {
    return container.getRule();
  }

  public RuleElementContainer getContainer() {
    return container;
  }

}
