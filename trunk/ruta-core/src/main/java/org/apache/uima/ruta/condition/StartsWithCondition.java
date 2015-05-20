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

import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.list.TypeListExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class StartsWithCondition extends TypeSentiveCondition {

  public StartsWithCondition(TypeExpression type) {
    super(type);
  }

  public StartsWithCondition(TypeListExpression list) {
    super(list);
  }

  @Override
  public EvaluatedCondition eval(AnnotationFS annotation, RuleElement element, RutaStream stream,
          InferenceCrowd crowd) {

    // TODO rewrite
    if (!isWorkingOnList()) {
      Type t = type.getType(element.getParent());
      boolean result = check(annotation, t, stream);
      return new EvaluatedCondition(this, result);
    } else {
      boolean result = false;
      List<Type> types = getList().getList(element.getParent(), stream);
      for (Type t : types) {
        result |= check(annotation, t, stream);
        if (result == true) {
          return new EvaluatedCondition(this, result);
        }
      }
    }
    boolean result = false;
    return new EvaluatedCondition(this, result);
  }

  private boolean check(AnnotationFS annotation, Type t, RutaStream stream) {
    if (annotation == null) {
      return false;
    }
    RutaBasic beginAnchor = stream.getBeginAnchor(annotation.getBegin());
    if(beginAnchor != null) {
      return beginAnchor.beginsWith(t);
    } else {
      return false;
    }
  }

}
