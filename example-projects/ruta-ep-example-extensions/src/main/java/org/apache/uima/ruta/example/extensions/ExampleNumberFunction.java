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

package org.apache.uima.ruta.example.extensions;

import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.expression.number.NumberFunctionExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;

public class ExampleNumberFunction extends NumberFunctionExpression {

  private final TypeExpression expr;

  public ExampleNumberFunction(TypeExpression expr) {
    super();
    this.expr = expr;
  }

  public TypeExpression getExpr() {
    return expr;
  }

  public String getStringValue(RutaStatement parent) {
    return expr.getType(parent).getShortName();
  }

  public int getIntegerValue(RutaStatement parent) {
    return getFeatureAmount(parent);
  }

  public double getDoubleValue(RutaStatement parent) {
    return getFeatureAmount(parent);
  }

  public float getFloatValue(RutaStatement parent) {
    return getFeatureAmount(parent);
  }

  private int getFeatureAmount(RutaStatement parent) {
    return expr.getType(parent).getFeatures().size();
  }

}
