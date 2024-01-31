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

package org.apache.uima.ruta.expression;

import java.util.List;

import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.rule.MatchContext;

public class GenericComposedExpression extends RutaExpression implements INumberExpression {

  private final List<IRutaExpression> expressions;

  public GenericComposedExpression(List<IRutaExpression> expressions) {
    super();
    this.expressions = expressions;
  }

  @Override
  public String getStringValue(MatchContext context, RutaStream stream) {
    if (expressions == null) {
      return null;
    }
    if (expressions.size() == 1) {
      IRutaExpression first = expressions.get(0);
      if (first instanceof IStringExpression) {
        return ((IStringExpression) first).getStringValue(context, stream);
      }
      return null;
    }
    StringBuilder result = new StringBuilder();
    for (IRutaExpression each : expressions) {
      if (each instanceof IStringExpression) {
        result.append(((IStringExpression) each).getStringValue(context, stream));
      }
    }
    return result.toString();
  }

  @Override
  public int getIntegerValue(MatchContext context, RutaStream stream) {
    return (int) getDoubleValue(context, stream);
  }

  @Override
  public double getDoubleValue(MatchContext context, RutaStream stream) {
    double result = 0;
    for (IRutaExpression each : expressions) {
      if (each instanceof INumberExpression) {
        result += ((INumberExpression) each).getDoubleValue(context, stream);
      }
    }
    return result;
  }

  @Override
  public float getFloatValue(MatchContext context, RutaStream stream) {
    return (float) getDoubleValue(context, stream);
  }

  public List<IRutaExpression> getExpressions() {
    return expressions;
  }
}
