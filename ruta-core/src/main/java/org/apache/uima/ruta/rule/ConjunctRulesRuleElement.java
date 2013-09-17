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
import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class ConjunctRulesRuleElement extends ComposedRuleElement {

  public ConjunctRulesRuleElement(List<RuleElement> elements, RuleElementContainer container,
          RutaBlock parent) {
    super(elements, null, null, null, container, parent);
  }

  public void setElements(List<RuleElement> elements) {
    this.elements = elements;
  }

  public List<RuleMatch> startMatch(RuleMatch ruleMatch, RuleApply ruleApply,
          ComposedRuleElementMatch containerMatch, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd) {
    List<RuleMatch> result = new ArrayList<RuleMatch>();
    ComposedRuleElementMatch composedMatch = createComposedMatch(ruleMatch, containerMatch, stream);
    boolean allMatched = true;
    for (RuleElement each : elements) {
      List<RuleMatch> startMatch = each.startMatch(ruleMatch, null, composedMatch, each, stream, crowd);
      boolean oneMatched = false;;
      for (RuleMatch eachRuleMatch : startMatch) {
        boolean matched = eachRuleMatch.matched();
        if(matched) {
          oneMatched = true;
          break;
        }
      }
      allMatched &= oneMatched;
      result.addAll(startMatch);
    }
    
    for (RuleMatch each : result) {
      if (!each.isApplied()) {
        ruleApply.add(each);
        if (each.matched() && allMatched) {
          each.getRule().getRoot().applyRuleElements(each, stream, crowd);
        }
        each.setApplied(true);
      }
    }
    return result;
  }

  public List<RuleMatch> continueMatch(boolean after, AnnotationFS annotation, RuleMatch ruleMatch,
          RuleApply ruleApply, ComposedRuleElementMatch containerMatch,
          RutaRuleElement sideStepOrigin, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd) {
    throw new UnsupportedOperationException();
  }

  public List<RuleMatch> continueOwnMatch(boolean after, AnnotationFS annotation,
          RuleMatch ruleMatch, RuleApply ruleApply, ComposedRuleElementMatch containerMatch,
          RutaRuleElement sideStepOrigin, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd) {
    throw new UnsupportedOperationException();
  }

  public Collection<AnnotationFS> getAnchors(RutaStream symbolStream) {
    throw new UnsupportedOperationException();
  }

  public long estimateAnchors(RutaStream stream) {
    throw new UnsupportedOperationException();
  }

  public List<RuleElement> getRuleElements() {
    return elements;
  }

  public RuleElement getFirstElement() {
    return null;
  }

  public RuleElement getLastElement() {
    return null;
  }

  public RuleElement getAnchoringRuleElement(RutaStream stream) {
    return null;
  }

  public RuleElement getNextElement(boolean after, RuleElement ruleElement) {
    return null;
  }

  public boolean hasAncestor(boolean after) {
    return false;
  }

}
