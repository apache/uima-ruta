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

public class PlusGreedy implements RuleElementQuantifier {

  public PlusGreedy() {
    super();
  }

  @Override
  public List<RuleElementMatch> evaluateMatches(List<RuleElementMatch> matches,
          TextMarkerStatement element, InferenceCrowd crowd) {
    boolean result = true;
    boolean allEmpty = true;
    if (matches == null) {
      return null;
    }
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
          RuleMatch extendedMatch, ComposedRuleElementMatch containerMatch,
          TextMarkerStream stream, InferenceCrowd crowd) {
    List<List<RuleElementMatch>> matchInfo = extendedMatch.getMatchInfo(ruleElement);
    List<RuleElementMatch> matches;
    if (after) {
      matches = matchInfo.get(matchInfo.size() - 1);
    } else {
      matches = matchInfo.get(0);
    }
    boolean result = true;
    boolean allEmpty = true;
    if (matches == null) {
      return true;
    }

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
    return result;

  }

  @Override
  public boolean isOptional(TextMarkerBlock parent) {
    return false;
  }
}
