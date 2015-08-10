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

package org.apache.uima.ruta.action;

import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.type.RutaAnnotation;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class MarkAction extends AbstractMarkAction {

  protected final INumberExpression score;

  protected final List<INumberExpression> list;

  public MarkAction(TypeExpression type, INumberExpression scoreValue, List<INumberExpression> list) {
    super(type);
    this.score = scoreValue;
    this.list = list;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, RutaStream stream, InferenceCrowd crowd) {
    List<Integer> indexList = getIndexList(element, list, stream);
    List<AnnotationFS> matchedAnnotations = match.getMatchedAnnotations(indexList,
            element.getContainer());
    for (AnnotationFS matchedAnnotation : matchedAnnotations) {
      if (matchedAnnotation == null) {
        return;
      }
      if (score == null) {
        createAnnotation(matchedAnnotation, element, stream, match);
      } else {
        double deltaScore = score.getDoubleValue(element.getParent(), match, element, stream);
        updateHeuristicAnnotation(match, element, stream, matchedAnnotation, deltaScore);
      }
    }

  }

  protected void updateHeuristicAnnotation(RuleMatch match, RuleElement element, RutaStream stream,
          AnnotationFS matchedAnnotation, double deltaScore) {
    Type heuristicType = stream.getJCas().getCasType(RutaAnnotation.type);
    RutaAnnotation heuristicAnnotation = (RutaAnnotation) stream.getCas().createAnnotation(
            heuristicType, matchedAnnotation.getBegin(), matchedAnnotation.getEnd());
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
      stream.addAnnotation(newAnnotation, match);
    } else {
      RutaAnnotation tma = stream.getCorrectTMA(annotationsInWindow, heuristicAnnotation);
      if (tma != null) {
        tma.removeFromIndexes();
        double newScore = tma.getScore() + deltaScore;
        tma.setScore(newScore);
        tma.addToIndexes();
      } else {
        heuristicAnnotation.addToIndexes();
        newAnnotation.addToIndexes();
        stream.addAnnotation(newAnnotation, match);
      }
    }

  }

  public INumberExpression getScore() {
    return score;
  }

  public List<INumberExpression> getList() {
    return list;
  }
}
