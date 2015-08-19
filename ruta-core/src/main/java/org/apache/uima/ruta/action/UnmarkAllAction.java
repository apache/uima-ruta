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
import java.util.LinkedList;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.list.TypeListExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleElementMatch;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class UnmarkAllAction extends TypeSensitiveAction {

  private final TypeListExpression list;

  public UnmarkAllAction(TypeExpression type, TypeListExpression list) {
    super(type);
    this.list = list;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, RutaStream stream, InferenceCrowd crowd) {
    List<Type> retainList = new ArrayList<Type>();
    if (list != null) {
      retainList = list.getList(element.getParent(), stream);
    }
    Type t = type.getType(element.getParent());
    TypeSystem typeSystem = stream.getCas().getTypeSystem();
    List<AnnotationFS> toRemove = new LinkedList<AnnotationFS>();
    List<List<RuleElementMatch>> matchInfo = match.getMatchInfo(element);
    for (List<RuleElementMatch> l : matchInfo) {
      RuleElementMatch ruleElementMatch = l.get(0);
      List<AnnotationFS> textsMatched = ruleElementMatch.getTextsMatched();
      for (AnnotationFS each : textsMatched) {
        RutaBasic beginAnchor = stream.getBeginAnchor(each.getBegin());
        Collection<AnnotationFS> beginAnchors = beginAnchor.getBeginAnchors(t);
        for (AnnotationFS annotationFS : beginAnchors) {
          if ((annotationFS.getEnd() == each.getEnd() || annotationFS.getBegin() == each.getBegin())
                  && !retainType(annotationFS.getType(), retainList, typeSystem)) {
            toRemove.add(annotationFS);
          }
        }
      }
    }
    for (AnnotationFS annotationFS : toRemove) {
      stream.removeAnnotation(annotationFS);
    }
  }

  private boolean retainType(Type type, List<Type> retainList, TypeSystem typeSystem) {
    for (Type each : retainList) {
      if (typeSystem.subsumes(each, type)) {
        return true;
      }
    }
    return false;
  }

  public TypeListExpression getList() {
    return list;
  }

}
