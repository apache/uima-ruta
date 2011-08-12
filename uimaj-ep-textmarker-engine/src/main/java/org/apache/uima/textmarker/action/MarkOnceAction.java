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

import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.rule.TextMarkerRuleElement;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class MarkOnceAction extends MarkAction {

  public MarkOnceAction(TypeExpression type, NumberExpression scoreValue,
          List<NumberExpression> list) {
    super(type, scoreValue, list);
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<Integer> indexList = getIndexList(match, element);
    AnnotationFS matchedAnnotation = match.getMatchedAnnotation(stream, indexList);
    CAS cas = stream.getCas();
    if (matchedAnnotation == null)
      return;
    Type t = type.getType(element.getParent());
    AnnotationFS createAnnotation = cas.createAnnotation(t, matchedAnnotation.getBegin(),
            matchedAnnotation.getEnd());
    boolean contains = false;
    FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex(t).iterator(createAnnotation);
    while (iterator.isValid()
            && ((AnnotationFS) iterator.get()).getEnd() == createAnnotation.getEnd()) {
      AnnotationFS a = (AnnotationFS) iterator.get();
      if (a.getBegin() == createAnnotation.getBegin() && a.getEnd() == createAnnotation.getEnd()
              && a.getType().getName().equals(createAnnotation.getType().getName())) {
        contains = true;
        break;
      }
      iterator.moveToNext();
    }
    if (!contains) {
      super.execute(match, element, stream, crowd);
    }
  }

}
