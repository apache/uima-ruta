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
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.list.TypeListExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class TrimAction extends AbstractRutaAction {

  private TypeListExpression typeList;

  private List<TypeExpression> types;

  public TrimAction(List<TypeExpression> types, TypeListExpression typeList) {
    super();
    this.types = types;
    this.typeList = typeList;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, RutaStream stream, InferenceCrowd crowd) {
    List<AnnotationFS> matchedAnnotationsOf = match.getMatchedAnnotationsOf(element);
    List<Type> typesToTrim = getTypes(element.getParent(), stream);
    for (AnnotationFS annotationFS : matchedAnnotationsOf) {
      trimAnnotation(annotationFS, typesToTrim, match, stream);
    }
  }

  private void trimAnnotation(AnnotationFS annotation, List<Type> typesToTrim, RuleMatch match,
          RutaStream stream) {
    int oldBegin = annotation.getBegin();
    int oldEnd = annotation.getEnd();
    int newBegin = oldBegin;
    int newEnd = oldEnd;

    RutaBasic beginBasic = stream.getBeginAnchor(oldBegin);
    while (beginBasic != null && isPartof(beginBasic, typesToTrim)
            && beginBasic.getBegin() < oldEnd) {
      beginBasic = stream.getBasicNextTo(false, beginBasic);
    }
    if (beginBasic != null) {
      newBegin = beginBasic.getBegin();
    } else {
      stream.removeAnnotation(annotation);
      return;
    }

    RutaBasic endBasic = stream.getEndAnchor(oldEnd);
    while (endBasic != null && isPartof(endBasic, typesToTrim) && endBasic.getEnd() > newBegin) {
      endBasic = stream.getBasicNextTo(true, endBasic);
    }
    if (endBasic != null) {
      newEnd = endBasic.getEnd();
    } else {
      stream.removeAnnotation(annotation);
      return;
    }

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

  private boolean isPartof(RutaBasic basic, List<Type> typesToTrim) {
    for (Type type : typesToTrim) {
      boolean partOf = basic.isPartOf(type);
      if (partOf) {
        return true;
      }
    }
    return false;
  }

  private List<Type> getTypes(RutaBlock parent, RutaStream stream) {
    List<Type> result = new ArrayList<Type>();
    if (types != null) {
      for (TypeExpression each : types) {
        result.add(each.getType(parent));
      }
    } else if (typeList != null) {
      result = typeList.getList(parent, stream);
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
