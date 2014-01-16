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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class GetListAction extends AbstractRutaAction {

  private static final String TYPES = "Types";

  private static final String TYPES_AT_END = "Types:End";

  private static final String TYPES_AT_BEGIN = "Types:Begin";

  private String var;

  private IStringExpression opExpr;

  public GetListAction(String var, IStringExpression op) {
    super();
    this.var = var;
    this.opExpr = op;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, RutaStream stream, InferenceCrowd crowd) {
    RutaBlock parent = element.getParent();
    String op = opExpr.getStringValue(parent, match, element, stream);
    List<Type> list = new ArrayList<Type>();

    int indexOf = element.getContainer().getRuleElements().indexOf(element);
    List<Integer> indexes = new ArrayList<Integer>();
    indexes.add(indexOf + 1);
    List<AnnotationFS> matchedAnnotations = match.getMatchedAnnotations(indexes,
            element.getContainer());
    for (AnnotationFS matched : matchedAnnotations) {

      if (TYPES_AT_BEGIN.equals(op)) {
        RutaBasic beginAnchor = stream.getBeginAnchor(matched.getBegin());
        Collection<?>[] beginMap = beginAnchor.getBeginMap();
        Set<AnnotationFS> aset = new HashSet<AnnotationFS>();
        for (Collection<?> set : beginMap) {
          if(set != null) {
            aset.addAll((Collection<? extends AnnotationFS>) set);
          }
        }
        for (AnnotationFS annotationFS : aset) {
          list.add(annotationFS.getType());
        }
      } else if (TYPES_AT_END.equals(op)) {
        RutaBasic endAnchor = stream.getEndAnchor(matched.getEnd());
//        Collection<Set<AnnotationFS>> values = endAnchor.getEndMap().values();
        Collection<?>[] endMap = endAnchor.getEndMap();
        Set<AnnotationFS> aset = new HashSet<AnnotationFS>();
        for (Collection<?> set : endMap) {
          if(set != null) {
            aset.addAll((Collection<? extends AnnotationFS>) set);
          }
        }
        for (AnnotationFS annotationFS : aset) {
          list.add(annotationFS.getType());
        }
      } else if (TYPES.equals(op)) {
        Type annotationType = stream.getCas().getAnnotationType();
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
    parent.getEnvironment().setVariableValue(var, list);
  }

  public String getVar() {
    return var;
  }

  public IStringExpression getOpExpr() {
    return opExpr;
  }

}
