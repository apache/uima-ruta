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

package org.apache.uima.textmarker.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.type.TextMarkerBasic;

public abstract class AbstractMarkAction extends TypeSensitiveAction {

  public AbstractMarkAction(TypeExpression type) {
    super(type);
  }

  protected void createAnnotation(AnnotationFS matchedAnnotation, RuleElement element,
          TextMarkerStream stream, RuleMatch match) {
    TextMarkerBasic first = stream.getFirstBasicInWindow(matchedAnnotation);
    createAnnotation(first, element, stream, matchedAnnotation, match);
  }

  protected Annotation createAnnotation(TextMarkerBasic anchor, RuleElement element,
          TextMarkerStream stream, AnnotationFS matchedAnnotation, RuleMatch match) {
    Type t = type.getType(element.getParent());
    AnnotationFS newAnnotationFS = stream.getCas().createAnnotation(t,
            matchedAnnotation.getBegin(), matchedAnnotation.getEnd());
    Annotation newAnnotation = null;
    if (newAnnotationFS instanceof Annotation) {
      newAnnotation = (Annotation) newAnnotationFS;
      newAnnotation.addToIndexes();
    } else {
      return null;
    }
    stream.addAnnotation(newAnnotation, match);
    return newAnnotation;
  }

  @Override
  public String toString() {
    return super.toString() + "," + type.getClass().getSimpleName();
  }

  protected List<Integer> getIndexList(RuleElement element, List<NumberExpression> list) {
    List<Integer> indexList = new ArrayList<Integer>();
    if (list == null || list.isEmpty()) {
      int self = element.getContainer().getRuleElements().indexOf(element) + 1;
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

}
