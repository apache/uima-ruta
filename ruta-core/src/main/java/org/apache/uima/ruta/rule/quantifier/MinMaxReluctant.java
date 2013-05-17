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
import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.number.NumberExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberExpression;
import org.apache.uima.ruta.rule.ComposedRuleElementMatch;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleElementMatch;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class MinMaxReluctant implements RuleElementQuantifier {

  private NumberExpression min;

  private NumberExpression max;

  public MinMaxReluctant(NumberExpression min, NumberExpression max, boolean interval) {
    super();
    if (!interval) {
      this.min = min;
      this.max = min;
    } else {
      this.min = min;
      this.max = max;
      if (this.max == null) {
        this.max = new SimpleNumberExpression(Integer.MAX_VALUE);
      }
    }
  }

  public NumberExpression getMin() {
    return min;
  }

  public NumberExpression getMax() {
    return max;
  }

  public List<RuleElementMatch> evaluateMatches(List<RuleElementMatch> matches,
          RutaStatement element, RutaStream stream, InferenceCrowd crowd) {
    int minValue = min.getIntegerValue(element.getParent(), null, stream);
    int maxValue = max.getIntegerValue(element.getParent(), null, stream);
    if (matches.size() > 0) {
      RuleElementMatch ruleElementMatch = matches.get(matches.size() - 1);
      if (!ruleElementMatch.matched()) {
        matches.remove(ruleElementMatch);
      }
    }
    int matchedSize = matches.size();
    boolean result = matchedSize >= minValue && matchedSize <= maxValue;
    if (result) {
      return matches;
    } else {
      return null;
    }
  }

  public boolean continueMatch(boolean after, AnnotationFS annotation, RuleElement ruleElement,
          RuleMatch ruleMatch, ComposedRuleElementMatch containerMatch, RutaStream stream,
          InferenceCrowd crowd) {
    int minValue = min.getIntegerValue(ruleElement.getParent(), annotation, stream);
    int maxValue = max.getIntegerValue(ruleElement.getParent(), annotation, stream);
    List<RuleElementMatch> list = containerMatch.getInnerMatches().get(ruleElement);
    if (list == null && maxValue > 0) {
      return true;
    }

    int matchedSize = list.size();
    if (list == null || list.isEmpty() || matchedSize < minValue) {
      return true;
    }
    RuleElementMatch lastMatch = null;
    if (after) {
      lastMatch = list.get(list.size() - 1);
    } else {
      lastMatch = list.get(0);
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
    boolean nextMatched = (nextList != null && !nextList.isEmpty());

    return (matchedSize < maxValue && matchedSize >= minValue && !nextMatched)
            || (!lastMatch.matched() && matchedSize >= minValue && matchedSize <= maxValue && !nextMatched);
  }

  public boolean isOptional(RutaBlock parent, RutaStream stream) {
    int minValue = min.getIntegerValue(parent, null, stream);
    return minValue == 0;
  }
}
