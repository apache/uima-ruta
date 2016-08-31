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
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.AnnotationTypeExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class UnmarkAction extends TypeSensitiveAction {

  private List<INumberExpression> list;

  private IBooleanExpression allAnchor;

  private AnnotationTypeExpression expression;

  public UnmarkAction(ITypeExpression type, List<INumberExpression> list, IBooleanExpression b) {
    super(type);
    this.list = list;
    this.allAnchor = b;
  }

  public UnmarkAction(AnnotationTypeExpression expression) {
    super(null);
    this.expression = expression;
  }

  @Override
  public void execute(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
    RuleMatch match = context.getRuleMatch();
    RuleElement element = context.getElement();

    if (expression != null) {
      List<AnnotationFS> annotationList = expression.getAnnotationList(context, stream);
      if(expression.getTypeExpression() != null && expression.getFeatureExpression() == null && expression.getAnnotationExpression() == null && expression.getAnnotationListExpression()== null) {
        // type-based like old behavior
        Type t = expression.getTypeExpression().getType(context, stream);
        removeTypeBased(context, stream, match, element, t);
      } else {
      for (AnnotationFS annotationFS : annotationList) {
        stream.removeAnnotation(annotationFS);
      }
      }
    } else {
      Type t = type.getType(context, stream);
      removeTypeBased(context, stream, match, element, t);
    }
  }

  private void removeTypeBased(MatchContext context, RutaStream stream, RuleMatch match,
          RuleElement element, Type t) {
    boolean allAtAnchor = false;
    if (allAnchor != null) {
      allAtAnchor = allAnchor.getBooleanValue(context, stream);
    }
    List<Integer> indexList = getIndexList(context, list, stream);
    List<AnnotationFS> matchedAnnotations = match.getMatchedAnnotations(indexList,
            element.getContainer());
    for (AnnotationFS annotationFS : matchedAnnotations) {
      Type matchedType = annotationFS.getType();
      boolean subsumes = stream.getCas().getTypeSystem().subsumes(t, matchedType);
      if (subsumes && !allAtAnchor) {
        stream.removeAnnotation(annotationFS, matchedType);
      } else {
        RutaBasic beginAnchor = stream.getBeginAnchor(annotationFS.getBegin());
        Collection<AnnotationFS> beginAnchors = beginAnchor.getBeginAnchors(t);
        if (beginAnchors != null) {
          for (AnnotationFS each : new ArrayList<AnnotationFS>(beginAnchors)) {
            if (allAtAnchor || each.getEnd() == annotationFS.getEnd()) {
              stream.removeAnnotation(each, t);
            }
          }
        }
      }

    }
  }

  public List<INumberExpression> getList() {
    return list;
  }

  public IBooleanExpression getAllAnchor() {
    return allAnchor;
  }

  protected List<Integer> getIndexList(MatchContext context, List<INumberExpression> list,
          RutaStream stream) {
    RuleElement element = context.getElement();
    List<Integer> indexList = new ArrayList<Integer>();
    if (list == null || list.isEmpty()) {
      int self = element.getContainer().getRuleElements().indexOf(element) + 1;
      indexList.add(self);
      return indexList;
    }
    int last = Integer.MAX_VALUE - 1;
    for (INumberExpression each : list) {
      // not allowed for feature matches
      int value = each.getIntegerValue(context, stream);
      for (int i = Math.min(value, last + 1); i < value; i++) {
        indexList.add(i);
      }
      indexList.add(value);
    }
    return indexList;
  }

  public AnnotationTypeExpression getExpression() {
    return expression;
  }

}
