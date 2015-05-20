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

import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaEnvironment;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class RutaRule extends AbstractRule {

  private ComposedRuleElement root;

  public RutaRule(List<RuleElement> elements, RutaBlock parent, int id) {
    super(parent, id);
    this.root = new ComposedRuleElement(elements, null, null, null, null, parent);
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
    return root == null ? "<empty>": root.toString();
  }

  public final List<RuleElement> getRuleElements() {
    return root.getRuleElements();
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
  }

  public ComposedRuleElement getRoot() {
    return root;
  }

}
