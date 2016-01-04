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

public class ReplaceAllStringFunction extends StringFunctionExpression {

  private final IStringExpression text;
  private final IStringExpression searchTerm;
  private final IStringExpression replacement;

  public ReplaceAllStringFunction(IStringExpression expr, IStringExpression searchTerm,
          IStringExpression replacement) {
    super();
    this.text = expr;
    this.searchTerm = searchTerm;
    this.replacement = replacement;
  }

  public IStringExpression getExpr() {
    return text;
  }

  public String getStringValue(MatchContext context, RutaStream stream) {
    return text.getStringValue(context, stream).replaceAll(
            searchTerm.getStringValue(context, stream),
            replacement.getStringValue(context, stream));
  }
}