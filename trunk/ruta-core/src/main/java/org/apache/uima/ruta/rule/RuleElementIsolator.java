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

public class RuleElementIsolator implements RuleElementContainer {

  private RuleElementContainer container;

  public RuleElementIsolator() {
    super();
  }

  public RuleElementIsolator(RuleElementContainer container) {
    super();
    this.container = container;
  }

  public List<RuleElement> getRuleElements() {
    return container.getRuleElements();
  }

  public RuleElement getAnchoringRuleElement(RutaStream stream) {
    return getRuleElement();
  }

  public RuleElement getFirstElement() {
    return getRuleElement();
  }

  public RuleElement getLastElement() {
    return getRuleElement();
  }

  public void applyRuleElements(RuleMatch ruleMatch, RutaStream stream, InferenceCrowd crowd) {
    getRuleElement().apply(ruleMatch, stream, crowd);
  }

  public RuleElementContainer getContainer() {
    return container;
  }

  public void setContainer(RutaRule container) {
    this.container = container.getRoot();
  }

  private RuleElement getRuleElement() {
    return container.getRuleElements().get(0);
  }

  public RutaRule getRule() {
    return container.getRule();
  }

  public RuleElement getNextElement(boolean after, RuleElement ruleElement) {
    return null;
  }

}
