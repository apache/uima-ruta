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

import java.util.List;

import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class RuleElementCaretaker implements RuleElementContainer {

  private final RuleElementContainer container;

  public RuleElementCaretaker(RuleElementContainer container) {
    super();
    this.container = container;
  }

  public RuleElement getElementAfter(RuleElement element) {
    int indexOf = container.getRuleElements().indexOf(element);
    if (indexOf < container.getRuleElements().size() - 1) {
      RuleElement ruleElement = container.getRuleElements().get(indexOf + 1);
      return ruleElement;
    }
    return null;
  }

  public RuleElement getElementBefore(RuleElement element) {
    int indexOf = container.getRuleElements().indexOf(element);
    if (indexOf > 0) {
      RuleElement ruleElement = container.getRuleElements().get(indexOf - 1);
      return ruleElement;
    }
    return null;
  }

  @Override
  public List<RuleElement> getRuleElements() {
    return container.getRuleElements();
  }

  @Override
  public RuleElement getAnchoringRuleElement(TextMarkerStream stream) {
    List<RuleElement> ruleElements = container.getRuleElements();
    if (ruleElements.size() == 1) {
      return ruleElements.get(0);
    }

    if (stream.isDynamicAnchoring()) {
      // TODO activate dynamic anchoring
      int min = Integer.MAX_VALUE;
      RuleElement minElement = null;
      int i = 1;
      for (RuleElement each : ruleElements) {
        int estimate = each.estimateAnchors(stream);
        // TODO what about this formula?
        double factor = stream.getIndexPenalty();
        estimate = (int) (Math.log(estimate) * (i * factor));
        if (estimate < min) {
          min = estimate;
          minElement = each;
        }
        i++;
      }
      return minElement;
    }
    return ruleElements.get(0);

  }

  @Override
  public RuleElement getFirstElement() {
    List<RuleElement> ruleElements = container.getRuleElements();
    return ruleElements.get(0);
  }

  @Override
  public RuleElement getLastElement() {
    List<RuleElement> ruleElements = container.getRuleElements();
    return ruleElements.get(ruleElements.size() - 1);
  }

  @Override
  public void applyRuleElements(RuleMatch ruleMatch, TextMarkerStream stream, InferenceCrowd crowd) {
    for (RuleElement eachElement : getRuleElements()) {
      eachElement.apply(ruleMatch, stream, crowd);
    }
  }

  @Override
  public TextMarkerRule getRule() {
    return container.getRule();
  }

  @Override
  public RuleElement getNextElement(boolean after, RuleElement ruleElement) {
    if (after) {
      return getElementAfter(ruleElement);
    } else {
      return getElementBefore(ruleElement);
    }
  }
}
