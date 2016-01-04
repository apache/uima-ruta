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

package org.apache.uima.ruta.string;

import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.StringFunctionExpression;
import org.apache.uima.ruta.rule.MatchContext;

public class FirstCharToUpperCaseStringFunction extends StringFunctionExpression {

  private final IStringExpression expr;

  public FirstCharToUpperCaseStringFunction(IStringExpression expr) {
    super();
    this.expr = expr;
  }

  public IStringExpression getExpr() {
    return expr;
  }

  public String getStringValue(MatchContext context, RutaStream stream) {
    StringBuilder sb = new StringBuilder(expr.getStringValue(context, stream));
    sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
    
    return sb.toString();
  }
}
