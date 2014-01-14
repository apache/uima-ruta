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

import java.util.List;

import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.visitor.InferenceCrowd;

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
    } else if(indexOf == -1) {
      return container.getRuleElements().get(container.getRuleElements().size() - 1);
    }
    return null;
  }

  public List<RuleElement> getRuleElements() {
    return container.getRuleElements();
  }

  public RuleElement getAnchoringRuleElement(RutaStream stream) {
    List<RuleElement> ruleElements = container.getRuleElements();
    if (ruleElements.size() == 1 
            //|| containsLiteralMatcher(ruleElements)
            ) {
      return ruleElements.get(0);
    }
    for (RuleElement ruleElement : ruleElements) {
      if (ruleElement.isStartAnchor()) {
        return ruleElement;
      }
    }

    if (stream.isDynamicAnchoring()) {
      long min = Long.MAX_VALUE;
      RuleElement minElement = null;
      int i = 1;
      for (RuleElement each : ruleElements) {
        long estimate = each.estimateAnchors(stream);
        if(estimate == 0) {
          return each;
        }
        double factor = stream.getIndexPenalty();
        if(factor != 0) {
          estimate = (long) (Math.log(estimate) * (i * factor));
        }
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


  public RuleElement getFirstElement() {
    List<RuleElement> ruleElements = container.getRuleElements();
    return ruleElements.get(0);
  }

  public RuleElement getLastElement() {
    List<RuleElement> ruleElements = container.getRuleElements();
    return ruleElements.get(ruleElements.size() - 1);
  }

  public void applyRuleElements(RuleMatch ruleMatch, RutaStream stream, InferenceCrowd crowd) {
    for (RuleElement eachElement : getRuleElements()) {
      eachElement.apply(ruleMatch, stream, crowd);
    }
  }

  public RutaRule getRule() {
    return container.getRule();
  }

  public RuleElement getNextElement(boolean after, RuleElement ruleElement) {
    if (after) {
      return getElementAfter(ruleElement);
    } else {
      return getElementBefore(ruleElement);
    }
  }
}
