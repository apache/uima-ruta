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

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.number.INumberExpression;

public class BooleanNumberExpression extends AbstractBooleanExpression {

  private final INumberExpression e1;

  private final String op;

  private final INumberExpression e2;

  public BooleanNumberExpression(INumberExpression e1, String op, INumberExpression e2) {
    super();
    this.e1 = e1;
    this.op = op;
    this.e2 = e2;
  }

  @Override
  public boolean getBooleanValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    double doubleValue1 = getFristExpression().getDoubleValue(parent, annotation, stream);
    double doubleValue2 = getSecondExpression().getDoubleValue(parent, annotation, stream);
    return eval(doubleValue1, getOperator(), doubleValue2);
  }

  private boolean eval(double t1, String op, double t2) {
    if ("==".equals(op)) {
      return t1 == t2;
    } else if ("!=".equals(op)) {
      return t1 != t2;
    } else if ("<".equals(op)) {
      return t1 < t2;
    } else if ("<=".equals(op)) {
      return t1 <= t2;
    } else if (">".equals(op)) {
      return t1 > t2;
    } else if (">=".equals(op)) {
      return t1 >= t2;
    }
    return false;
  }

  public INumberExpression getFristExpression() {
    return e1;
  }

  public String getOperator() {
    return op;
  }

  public INumberExpression getSecondExpression() {
    return e2;
  }

  @Override
  public String getStringValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    return e1.getStringValue(parent, annotation, stream) + " " + op + " "
            + e2.getStringValue(parent, annotation, stream);
  }

}
