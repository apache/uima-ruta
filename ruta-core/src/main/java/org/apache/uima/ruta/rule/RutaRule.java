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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaEnvironment;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class RutaRule extends AbstractRule {

  private ComposedRuleElement root;

  private Map<String, RuleElement> label2Element;

  public RutaRule(List<RuleElement> elements, RutaBlock parent, int id) {
    super(parent, id);
    this.root = new ComposedRuleElement(elements, null, null, null, null, parent);
    this.label2Element = new HashMap<>();
  }

  @Override
  public RuleApply apply(RutaStream stream, InferenceCrowd crowd) {
    return apply(stream, crowd, stream.isGreedyAnchoring());
  }

  public RuleApply apply(RutaStream stream, InferenceCrowd crowd, boolean remember) {
    RuleApply ruleApply = new RuleApply(this, remember);
    crowd.beginVisit(this, ruleApply);
    RuleMatch ruleMatch = new RuleMatch(this);
    root.startMatch(ruleMatch, ruleApply, null, null, stream, crowd);
    crowd.endVisit(this, ruleApply);
    return ruleApply;
  }

  @Override
  public String toString() {
    return root == null ? "<empty>" : root.toString();
  }

  public final List<RuleElement> getRuleElements() {
    return root.getRuleElements();
  }

  public RuleElement getRuleElementWithLabel(String label) {
    return label2Element.get(label);
  }

  @Override
  public RutaEnvironment getEnvironment() {
    return getParent().getEnvironment();
  }

  public void setRuleElements(List<RuleElement> elements) {
    if (elements != null && elements.size() == 1
            && elements.get(0) instanceof ConjunctRulesRuleElement) {
      root = (ComposedRuleElement) elements.get(0);
    } else {
      root.setRuleElements(elements);
    }
    if (elements != null) {
      // update label map
      for (RuleElement ruleElement : elements) {
        fillLabelMap(ruleElement);
      }
    }

  }

  private void fillLabelMap(RuleElement ruleElement) {
    if (!StringUtils.isBlank(ruleElement.getLabel())) {
      label2Element.put(ruleElement.getLabel(), ruleElement);
    }
    if (ruleElement instanceof ComposedRuleElement) {
      ComposedRuleElement cre = (ComposedRuleElement) ruleElement;
      List<RuleElement> ruleElements = cre.getRuleElements();
      for (RuleElement each : ruleElements) {
        fillLabelMap(each);
      }
    }
  }

  public ComposedRuleElement getRoot() {
    return root;
  }

}
