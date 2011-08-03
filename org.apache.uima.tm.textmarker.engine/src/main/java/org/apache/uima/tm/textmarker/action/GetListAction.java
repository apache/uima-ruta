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
import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


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
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    String op = opExpr.getStringValue(element.getParent());
    List<Type> list = new ArrayList<Type>();
    int indexOf = match.getRule().getElements().indexOf(element);
    List<Integer> indexes = new ArrayList<Integer>();
    indexes.add(indexOf+1);
    AnnotationFS matched = match.getMatchedAnnotation(stream, indexes);
    TextMarkerBasic firstBasic = stream.getFirstBasicInWindow(matched);
    Collection<AnnotationFS> anchors = firstBasic.getAnchors();
    if (TYPES_AT_BEGIN.equals(op)) {
      for (AnnotationFS each : anchors) {
        list.add(each.getType());
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
          if(each.getBegin() > matched.getBegin() || each.getEnd() < matched.getEnd()) {
            break;
          }
        }
      }
    }
    element.getParent().getEnvironment().setVariableValue(var, list);
  }

}
