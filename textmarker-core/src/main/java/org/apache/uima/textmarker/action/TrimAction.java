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
import org.apache.uima.textmarker.TextMarkerElement;
import org.apache.uima.textmarker.TextMarkerStatement;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.list.TypeListExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.type.TextMarkerBasic;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class TrimAction extends AbstractTextMarkerAction {

  private TypeListExpression typeList;

  private List<TypeExpression> types;

  public TrimAction(List<TypeExpression> types, TypeListExpression typeList) {
    super();
    this.types = types;
    this.typeList = typeList;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<AnnotationFS> matchedAnnotationsOf = match.getMatchedAnnotationsOf(element, stream);
    List<Type> typesToTrim = getTypes(element.getParent());
    for (AnnotationFS annotationFS : matchedAnnotationsOf) {
      trimAnnotation(annotationFS, typesToTrim, match, stream);
    }
  }

  private void trimAnnotation(AnnotationFS annotation, List<Type> typesToTrim, RuleMatch match,
          TextMarkerStream stream) {
    int oldBegin = annotation.getBegin();
    int oldEnd = annotation.getEnd();
    int newBegin = oldBegin;
    int newEnd = oldEnd;

    TextMarkerBasic beginBasic = stream.getBeginAnchor(oldBegin);
    while (isPartof(beginBasic, typesToTrim) && beginBasic.getBegin() < oldEnd) {
      beginBasic = stream.getBasicNextTo(false, beginBasic);
    }
    newBegin = beginBasic.getBegin();

    TextMarkerBasic endBasic = stream.getEndAnchor(oldEnd);
    while (isPartof(endBasic, typesToTrim) && endBasic.getEnd() > newBegin) {
      endBasic = stream.getBasicNextTo(true, endBasic);
    }
    newEnd = endBasic.getEnd();

    if (oldBegin != newBegin || newEnd != oldEnd) {
      stream.removeAnnotation(annotation);
      if (annotation instanceof Annotation) {
        Annotation a = (Annotation) annotation;
        a.setBegin(newBegin);
        a.setEnd(newEnd);
      }
      if (newBegin < newEnd) {
        stream.addAnnotation(annotation, true, match);
      }
    }
  }

  private boolean isPartof(TextMarkerBasic basic, List<Type> typesToTrim) {
    for (Type type : typesToTrim) {
      boolean partOf = basic.isPartOf(type);
      if (partOf) {
        return true;
      }
    }
    return false;
  }

  private List<Type> getTypes(TextMarkerStatement parent) {
    List<Type> result = new ArrayList<Type>();
    if (types != null) {
      for (TypeExpression each : types) {
        result.add(each.getType(parent));
      }
    } else if (typeList != null) {
      result = typeList.getList(parent);
    }
    return result;
  }

  public TypeListExpression getTypeList() {
    return typeList;
  }

  public List<TypeExpression> getTypes() {
    return types;
  }

}
