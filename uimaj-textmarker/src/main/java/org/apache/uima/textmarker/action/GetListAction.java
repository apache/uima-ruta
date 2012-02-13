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
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.type.TextMarkerBasic;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class GetListAction extends AbstractTextMarkerAction {

  private static final String TYPES = "Types";

  private static final String TYPES_AT_END = "Types:End";

  private static final String TYPES_AT_BEGIN = "Types:Begin";

  private String var;

  private StringExpression opExpr;

  public GetListAction(String var, StringExpression op) {
    super();
    this.var = var;
    this.opExpr = op;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    String op = opExpr.getStringValue(element.getParent());
    List<Type> list = new ArrayList<Type>();

    int indexOf = element.getContainer().getRuleElements().indexOf(element);
    List<Integer> indexes = new ArrayList<Integer>();
    indexes.add(indexOf + 1);
    List<AnnotationFS> matchedAnnotations = match.getMatchedAnnotations(stream, indexes,
            element.getContainer());
    for (AnnotationFS matched : matchedAnnotations) {

      TextMarkerBasic firstBasic = stream.getFirstBasicInWindow(matched);
      Collection<Set<AnnotationFS>> values = firstBasic.getBeginMap().values();
      if (TYPES_AT_BEGIN.equals(op)) {
        for (Set<AnnotationFS> set : values) {
          for (AnnotationFS annotationFS : set) {
            list.add(annotationFS.getType());
          }
        }
      } else {
        Type annotationType = stream.getCas().getAnnotationType();
        if (TYPES_AT_END.equals(op)) {
          List<AnnotationFS> inWindow = stream.getAnnotationsInWindow(matched, annotationType);
          for (AnnotationFS each : inWindow) {
            if (each.getEnd() == matched.getEnd()) {
              list.add(each.getType());
            }
          }
        } else if (TYPES.equals(op)) {
          List<AnnotationFS> inWindow = stream.getAnnotationsInWindow(matched, annotationType);
          for (AnnotationFS each : inWindow) {
            if (each.getBegin() == matched.getBegin() && each.getEnd() == matched.getEnd()) {
              list.add(each.getType());
            }
            if (each.getBegin() > matched.getBegin() || each.getEnd() < matched.getEnd()) {
              break;
            }
          }
        }
      }
    }
    element.getParent().getEnvironment().setVariableValue(var, list);
  }
}
