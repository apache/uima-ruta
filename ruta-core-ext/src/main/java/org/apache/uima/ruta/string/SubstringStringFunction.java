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
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.StringFunctionExpression;
import org.apache.uima.ruta.rule.MatchContext;

public class SubstringStringFunction extends StringFunctionExpression {

  private final IStringExpression expr;

  private final INumberExpression from;

  private final INumberExpression to; // excluded

  public SubstringStringFunction(IStringExpression expr, INumberExpression from,
          INumberExpression to) {
    super();
    this.expr = expr;
    this.from = from;
    this.to = to;
  }

  public IStringExpression getExpr() {
    return expr;
  }

  public String getStringValue(MatchContext context, RutaStream stream) {
    String text = expr.getStringValue(context, stream);
    if (text.length() >= this.to.getIntegerValue(context, stream)){
      return text.substring(from.getIntegerValue(context, stream),
              to.getIntegerValue(context, stream));
    }

    else
      return null;
  }

}
