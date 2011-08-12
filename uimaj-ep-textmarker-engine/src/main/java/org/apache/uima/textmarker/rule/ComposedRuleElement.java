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
import java.util.List;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.TextMarkerBlock;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.rule.quantifier.RuleElementQuantifier;
import org.apache.uima.textmarker.type.TextMarkerBasic;
import org.apache.uima.textmarker.visitor.InferenceCrowd;


public class ComposedRuleElement extends AbstractRuleElement {

  private List<RuleElement> elements;

  private TextMarkerBlock parent;

  public ComposedRuleElement(List<RuleElement> elements, RuleElementQuantifier quantifier,
          TextMarkerBlock parent) {
    super(quantifier);
    this.elements = elements;
    this.parent = parent;

  }

  public void apply(RuleMatch matchInfos, TextMarkerStream symbolStream, InferenceCrowd crowd) {
    for (RuleElement each : elements) {
      each.apply(matchInfos, symbolStream, crowd);
    }
  }

  public List<TextMarkerBasic> getAnchors(TextMarkerStream symbolStream) {
    return elements.get(0).getAnchors(symbolStream);
  }

  public FSIterator<AnnotationFS> getAnchors2(TextMarkerStream symbolStream) {
    return elements.get(0).getAnchors2(symbolStream);
  }

  public TextMarkerMatcher getMatcher() {
    return elements.get(0).getMatcher();
  }

  public TextMarkerBlock getParent() {
    return parent;
  }

  public RuleElementMatch match(TextMarkerBasic anchor, TextMarkerStream stream,
          InferenceCrowd crowd) {
    RuleElementMatch result = new RuleElementMatch(this);
    TextMarkerBasic current = anchor;
    List<RuleElementMatch> innerMatches = new ArrayList<RuleElementMatch>();
    for (RuleElement each : elements) {
      RuleElementMatch match = each.match(current, stream, crowd);
      current = stream.nextAnnotation(match);
      innerMatches.add(match);
    }
    result.setInnerMatches(innerMatches);
    return result;
  }

  public List<RuleElement> getElements() {
    return elements;
  }
}
