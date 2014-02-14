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

package org.apache.uima.ruta.expression.bool;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.type.TypeExpression;

public class BooleanTypeExpression extends AbstractBooleanExpression {

  private final TypeExpression e1;

  private final String op;

  private final TypeExpression e2;

  public BooleanTypeExpression(TypeExpression e1, String op, TypeExpression e2) {
    super();
    this.e1 = e1;
    this.op = op;
    this.e2 = e2;
  }

  @Override
  public boolean getBooleanValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    Type type1 = getFristExpression().getType(parent);
    String first = type1.getName();
    Type type2 = getSecondExpression().getType(parent);
    String second = type2.getName();
    return eval(first, getOperator(), second);
  }

  private boolean eval(String t1, String op, String t2) {
    if ("==".equals(op)) {
      return t1.equals(t2);
    } else if ("!=".equals(op)) {
      return !t1.equals(t2);
    }
    return false;
  }

  public TypeExpression getFristExpression() {
    return e1;
  }

  public String getOperator() {
    return op;
  }

  public TypeExpression getSecondExpression() {
    return e2;
  }

  @Override
  public String getStringValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    return e1.getStringValue(parent, annotation, stream) + " " + op + " "
            + e2.getStringValue(parent, annotation, stream);
  }

}
