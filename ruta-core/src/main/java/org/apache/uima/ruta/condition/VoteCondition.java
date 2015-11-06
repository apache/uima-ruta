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
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class VoteCondition extends TerminalRutaCondition {

  private final TypeExpression type1;

  private final TypeExpression type2;

  public VoteCondition(TypeExpression type1, TypeExpression type2) {
    super();
    this.type1 = type1;
    this.type2 = type2;
  }

  @Override
  public EvaluatedCondition eval(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
		AnnotationFS annotation = context.getAnnotation();
		RuleElement element = context.getElement();
    int count1 = 0;
    int count2 = 0;
    if (annotation != null) {
      List<RutaBasic> annotations = stream.getBasicsInWindow(annotation);
      Type t1 = type1.getType(element.getParent());
      Type t2 = type2.getType(element.getParent());
      for (RutaBasic each : annotations) {
        if (each.beginsWith(t1)) {
          count1++;
        }
        if (each.beginsWith(t2)) {
          count2++;
        }
      }
    }
    return new EvaluatedCondition(this, count1 > count2);
  }

  public TypeExpression getType1() {
    return type1;
  }

  public TypeExpression getType2() {
    return type2;
  }
}
