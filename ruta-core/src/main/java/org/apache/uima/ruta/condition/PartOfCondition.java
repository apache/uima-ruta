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

package org.apache.uima.ruta.condition;

import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.list.TypeListExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class PartOfCondition extends TypeSentiveCondition {

  public PartOfCondition(TypeExpression type) {
    super(type);
  }

  public PartOfCondition(TypeListExpression list) {
    super(list);
  }

  @Override
  public EvaluatedCondition eval(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
		AnnotationFS annotation = context.getAnnotation();
		RuleElement element = context.getElement();
    if (!isWorkingOnList()) {
      Type t = type.getType(context, stream);
      boolean result = check(t, annotation, element, stream);
      return new EvaluatedCondition(this, result);
    } else {
      boolean result = false;
      List<Type> types = getList().getList(context, stream);
      for (Type t : types) {
        result |= check(t, annotation, element, stream);
        if (result == true) {
          break;
        }
      }
      return new EvaluatedCondition(this, result);
    }
  }

  private boolean check(Type t, AnnotationFS annotation, RuleElement element, RutaStream stream) {
    RutaBasic beginAnchor = stream.getBeginAnchor(annotation.getBegin());
    if(beginAnchor!= null && beginAnchor.isPartOf(t)) {
      return true;
    }
    RutaBasic endAnchor = stream.getEndAnchor(annotation.getEnd());
    if(endAnchor!= null && endAnchor.isPartOf(t)) {
      return true;
    }
    // TODO: do we really need to check again on the anchors?
    Collection<AnnotationFS> beginAnchors = beginAnchor.getBeginAnchors(t);
    if(beginAnchors != null && !beginAnchors.isEmpty()) {
      return true;
    }
    Collection<AnnotationFS> endAnchors = beginAnchor.getEndAnchors(t);
    if(endAnchors != null && !endAnchors.isEmpty()) {
      return true;
    }
    return false;
  }

}
