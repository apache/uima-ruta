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

package org.apache.uima.textmarker.rule.quantifier;

import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.TextMarkerBlock;
import org.apache.uima.textmarker.TextMarkerStatement;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.rule.ComposedRuleElementMatch;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleElementMatch;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class PlusReluctant implements RuleElementQuantifier {
  // @Override
  // public boolean continueMatch(int index, List<RuleElement> elements, TextMarkerBasic next,
  // RuleElementMatch match, List<RuleElementMatch> matches, TextMarkerStream stream,
  // InferenceCrowd crowd) {
  // boolean nextMatched = false;
  // if (index == elements.size() - 1 && match != null) {
  // // reluctant = minimal ... last element needs to match only once.
  // return false;
  // }
  // if (index + 1 < elements.size()) {
  // RuleElement element = elements.get(index + 1);
  // // RuleElementMatch nextMatch = element.match(next, null, stream, crowd);
  // // if (nextMatch.matched()) {
  // // nextMatched = true;
  // // }
  // }
  // return match == null || (!nextMatched && next != null);
  // }
  @Override
  public List<RuleElementMatch> evaluateMatches(List<RuleElementMatch> matches,
          TextMarkerStatement element, InferenceCrowd crowd) {
    boolean result = true;
    boolean allEmpty = true;
    for (RuleElementMatch match : matches) {
      allEmpty &= match.getTextsMatched().isEmpty();
      result &= match.getTextsMatched().isEmpty() || match.matched();
    }
    if (!result && matches.size() > 1) {
      matches.remove(matches.size() - 1);
      result = true;
    }
    if (matches.size() < 1 || allEmpty) {
      result = false;
    }
    if (result) {
      return matches;
    } else {
      return null;
    }
  }

  @Override
  public boolean continueMatch(boolean after, AnnotationFS annotation, RuleElement ruleElement,
          RuleMatch ruleMatch, ComposedRuleElementMatch containerMatch, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<RuleElementMatch> ownList = containerMatch.getInnerMatches().get(ruleElement);
    if (ownList == null || ownList.isEmpty()) {
      return true;
    }

    RuleElement nextElement = ruleElement.getContainer().getNextElement(after, ruleElement);
    if (nextElement == null) {
      return false;
    }
    ComposedRuleElementMatch extendedContainerMatch = containerMatch.copy();
    RuleMatch extendedMatch = ruleMatch.copy(extendedContainerMatch);
    nextElement.continueMatch(after, annotation, extendedMatch, null, extendedContainerMatch, null,
            nextElement, stream, crowd);
    List<RuleElementMatch> nextList = extendedContainerMatch.getInnerMatches().get(nextElement);
    return nextList == null || nextList.isEmpty();
  }

  @Override
  public boolean isOptional(TextMarkerBlock parent) {
    return false;
  }
}
