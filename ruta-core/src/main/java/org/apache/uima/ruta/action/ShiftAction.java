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

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.AnnotationComparator;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class ShiftAction extends MarkAction {

  public ShiftAction(TypeExpression type, List<INumberExpression> list) {
    super(type, null, list);
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, RutaStream stream, InferenceCrowd crowd) {
    Type targetType = type.getType(element.getParent());
    List<Integer> indexList = getIndexList(element, list, stream);
    List<AnnotationFS> destinationAnnotationSpans = match.getMatchedAnnotations(indexList,
            element.getContainer());
    List<AnnotationFS> annotationsMatchedByRuleElementofAction = match
            .getMatchedAnnotationsOf(element);
    int size = Math.min(annotationsMatchedByRuleElementofAction.size(),
            destinationAnnotationSpans.size());

    RutaBasic firstBasicOfAll = stream.getFirstBasicOfAll();
    RutaBasic lastBasicOfAll = stream.getLastBasicOfAll();
    int windowBegin = firstBasicOfAll == null ? 0 : firstBasicOfAll.getBegin();
    int windowEnd = lastBasicOfAll == null ? 0 : lastBasicOfAll.getEnd();
    for (int i = 0; i < size; i++) {
      AnnotationFS eachMatched = annotationsMatchedByRuleElementofAction.get(i);
      AnnotationFS eachDestination = destinationAnnotationSpans.get(i);
      Set<AnnotationFS> allAnchoredAnnotations = new TreeSet<AnnotationFS>(
              new AnnotationComparator());
      Collection<AnnotationFS> beginAnchors = stream.getBeginAnchor(eachMatched.getBegin())
              .getBeginAnchors(targetType);
      Collection<AnnotationFS> endAnchors = stream.getEndAnchor(eachMatched.getEnd())
              .getEndAnchors(targetType);
      allAnchoredAnnotations.addAll(beginAnchors);
      allAnchoredAnnotations.addAll(endAnchors);

      for (AnnotationFS eachAnchored : allAnchoredAnnotations) {
        if (eachAnchored.getBegin() >= windowBegin && eachAnchored.getEnd() <= windowEnd) {
          Annotation a = (Annotation) eachAnchored;
          stream.removeAnnotation(a);
          a.setBegin(eachDestination.getBegin());
          a.setEnd(eachDestination.getEnd());
          stream.addAnnotation(a, true, match);
        }
      }
    }
  }

}
