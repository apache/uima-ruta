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

package org.apache.uima.ruta.rule.quantifier;

import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.rule.ComposedRuleElementMatch;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleElementMatch;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class QuestionReluctant extends AbstractRuleElementQuantifier {

  @Override
  public List<RuleElementMatch> evaluateMatches(List<RuleElementMatch> matches,
          RutaBlock parent, RutaStream stream, InferenceCrowd crowd) {
    boolean result = true;
    for (RuleElementMatch match : matches) {
      result &= match.matched() || match.getTextsMatched().isEmpty();
    }
    if (!result) {
      matches.remove(0);
      result = true;
    }
    if (result) {
      return matches;
    } else {
      return null;
    }
  }

  @Override
  public boolean continueMatch(boolean after, AnnotationFS annotation, RuleElement ruleElement,
          RuleMatch ruleMatch, ComposedRuleElementMatch containerMatch, RutaStream stream,
          InferenceCrowd crowd) {
    if(annotation == null) {
      // do not try to continue a match that totally failed
      return false;
    }
    List<RuleElementMatch> ownList = containerMatch.getInnerMatches().get(ruleElement);
    if (ownList != null && !ownList.isEmpty()) {
      return false;
    }

    RuleElement nextElement = ruleElement.getContainer().getNextElement(after, ruleElement);
    if (nextElement == null) {
      return false;
    }
    ComposedRuleElementMatch extendedContainerMatch = containerMatch.copy();
    RuleMatch extendedMatch = ruleMatch.copy(extendedContainerMatch, after);
    
    List<RuleMatch> continueMatch = nextElement.continueMatch(after, annotation, extendedMatch,
            null, extendedContainerMatch, null, nextElement, stream, crowd);
    boolean result = !nextElementMatched(nextElement, continueMatch);
    return result;
  }

  @Override
  public boolean isOptional(RutaBlock parent, RutaStream stream) {
    return true;
  }
}
