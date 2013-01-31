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
import java.util.Set;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.type.TextMarkerBasic;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class UnmarkAction extends TypeSensitiveAction {

  private List<NumberExpression> list;

  private BooleanExpression allAnchor;

  public UnmarkAction(TypeExpression type, List<NumberExpression> list, BooleanExpression b) {
    super(type);
    this.list = list;
    this.allAnchor = b;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    Type t = type.getType(element.getParent());
    boolean allAtAnchor = false;
    if (allAnchor != null) {
      allAtAnchor = allAnchor.getBooleanValue(element.getParent());
    }
    List<Integer> indexList = getIndexList(element, list);
    List<AnnotationFS> matchedAnnotations = match.getMatchedAnnotations(stream, indexList,
            element.getContainer());
    for (AnnotationFS annotationFS : matchedAnnotations) {
      TextMarkerBasic beginAnchor = stream.getBeginAnchor(annotationFS.getBegin());
      Set<AnnotationFS> beginAnchors = beginAnchor.getBeginAnchors(t);
      if (beginAnchors != null) {
        for (AnnotationFS each : new ArrayList<AnnotationFS>(beginAnchors)) {
          if (allAtAnchor || each.getEnd() == annotationFS.getEnd()) {
            stream.removeAnnotation(each, t);
          }
        }
      }
    }

  }

  public List<NumberExpression> getList() {
    return list;
  }

  public BooleanExpression getAllAnchor() {
    return allAnchor;
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
