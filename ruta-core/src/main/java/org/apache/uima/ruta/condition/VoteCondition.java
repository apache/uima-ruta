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

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class VoteCondition extends TerminalRutaCondition {

  private final ITypeExpression type1;

  private final ITypeExpression type2;

  public VoteCondition(ITypeExpression type1, ITypeExpression type2) {
    super();
    this.type1 = type1;
    this.type2 = type2;
  }

  @Override
  public EvaluatedCondition eval(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
    AnnotationFS annotation = context.getAnnotation();
    Type t1 = type1.getType(context, stream);
    Type t2 = type2.getType(context, stream);

    if (t1 == null || t2 == null) {
      return new EvaluatedCondition(this, false);
    }

    int count1 = stream.getAnnotations(t1, annotation).size();
    int count2 = stream.getAnnotations(t2, annotation).size();
    return new EvaluatedCondition(this, count1 > count2);
  }

  public ITypeExpression getType1() {
    return type1;
  }

  public ITypeExpression getType2() {
    return type2;
  }
}
