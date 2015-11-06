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

package org.apache.uima.ruta.string.bool;

import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.bool.BooleanFunctionExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.rule.MatchContext;

public class StartsWithBooleanFunction extends BooleanFunctionExpression {

  private IStringExpression text;

  private IStringExpression start;

  public StartsWithBooleanFunction(IStringExpression text, IStringExpression start) {
    this.text = text;
    this.start = start;

  }

  public IStringExpression getExpr() {
    return text;
  }

  @Override
  public boolean getBooleanValue(MatchContext context, RutaStream stream) {
    return text.getStringValue(context, stream).startsWith(
            start.getStringValue(context, stream));
  }

  @Override
  public String getStringValue(MatchContext context, RutaStream stream) {
    Boolean endsWith = text.getStringValue(context, stream).startsWith(
            start.getStringValue(context, stream));
    return endsWith.toString();
  }
}
