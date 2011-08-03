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

package org.apache.uima.tm.textmarker.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerAnnotation;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class MarkAction extends AbstractMarkAction {

  protected final NumberExpression score;

  protected final List<NumberExpression> list;

  public MarkAction(TypeExpression type, NumberExpression scoreValue, List<NumberExpression> list) {
    super(type);
    this.score = scoreValue;
    this.list = list;
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<Integer> indexList = getIndexList(match, element);
    AnnotationFS matchedAnnotation = match.getMatchedAnnotation(stream, indexList);
    if (matchedAnnotation == null) {
      return;
    }
    if (score == null) {
      createAnnotation(match, element, stream, matchedAnnotation);
    } else {
      double deltaScore = score.getDoubleValue(element.getParent());
      updateHeuristicAnnotation(match, element, stream, matchedAnnotation, deltaScore);
    }
  }

  protected void updateHeuristicAnnotation(RuleMatch match, TextMarkerRuleElement element,
          TextMarkerStream stream, AnnotationFS matchedAnnotation, double deltaScore) {
    Type heuristicType = stream.getJCas().getCasType(TextMarkerAnnotation.type);
    TextMarkerAnnotation heuristicAnnotation = (TextMarkerAnnotation) stream.getCas()
            .createAnnotation(heuristicType, matchedAnnotation.getBegin(),
                    matchedAnnotation.getEnd());
    Annotation newAnnotation = (Annotation) stream.getCas().createAnnotation(
            type.getType(element.getParent()), heuristicAnnotation.getBegin(),
            heuristicAnnotation.getEnd());
    heuristicAnnotation.setScore(deltaScore);
    heuristicAnnotation.setAnnotation(newAnnotation);
    List<AnnotationFS> annotationsInWindow = stream.getAnnotationsInWindow(heuristicAnnotation,
            heuristicType);

    if (annotationsInWindow.isEmpty()) {
      heuristicAnnotation.addToIndexes();
      newAnnotation.addToIndexes();
      TextMarkerBasic first = stream.getFirstBasicInWindow(newAnnotation);
      stream.addAnnotation(first, newAnnotation);
    } else {
      TextMarkerAnnotation tma = stream.getCorrectTMA(annotationsInWindow, heuristicAnnotation);
      if (tma != null) {
        tma.removeFromIndexes();
        double newScore = tma.getScore() + deltaScore;
        tma.setScore(newScore);
        tma.addToIndexes();
      } else {
        heuristicAnnotation.addToIndexes();
        newAnnotation.addToIndexes();
        TextMarkerBasic first = stream.getFirstBasicInWindow(newAnnotation);
        stream.addAnnotation(first, newAnnotation);
      }
    }

  }

  protected List<Integer> getIndexList(RuleMatch match, TextMarkerRuleElement element) {
    List<Integer> indexList = new ArrayList<Integer>();
    if (list == null || list.isEmpty()) {
      int self = match.getRule().getElements().indexOf(element) + 1;
      indexList.add(self);
      return indexList;
    }
    int last = Integer.MAX_VALUE - 1;
    for (NumberExpression each : list) {
      int value = each.getIntegerValue(element.getParent());
      for (int i = Math.min(value, last + 1); i < value; i++) {
        indexList.add(i);
      }
      indexList.add(value);
    }
    return indexList;
  }

  public NumberExpression getScore() {
    return score;
  }

  public List<NumberExpression> getList() {
    return list;
  }
}
