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

package org.apache.uima.ruta.ide.parser.ast;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.Token;
import org.eclipse.dltk.ast.expressions.Expression;

public class ConditionFactory extends AbstractFactory {

  public static final String IMPLICIT = "Implicit";

  public static RutaCondition createCondition(Token type, List exprs) {
    int wholeConditionBounds[] = getBounds(type);
    int nameStart = wholeConditionBounds[0];
    int nameEnd = wholeConditionBounds[1];
    if (exprs == null) {
      exprs = new ArrayList<Expression>();
    }
    if (!exprs.isEmpty()) {
      Expression lastExpr = (Expression) exprs.get(exprs.size() - 1);
      if (lastExpr != null) {
        wholeConditionBounds[1] = Math.max(wholeConditionBounds[1], lastExpr.sourceEnd());
      }
    }
    return new RutaCondition(wholeConditionBounds[0], wholeConditionBounds[1], exprs,
            RutaConditionConstants.CONSTANT_OFFSET + type.getType(), type.getText(), nameStart,
            nameEnd);
  }

  public static RutaCondition createCondition(Expression... exprsArray) {
    List<Expression> exprL = new ArrayList<Expression>();
    if (exprsArray != null) {
      for (int i = 0; i < exprsArray.length; i++) {
        Expression expression = exprsArray[i];
        if (expression != null) {
          exprL.add(expression);
        }
      }
    }
    int[] bounds = getBounds(exprL.get(0), exprL.get(exprL.size() - 1));
    return new RutaCondition(bounds[0], bounds[1], exprL, RutaConditionConstants.CONSTANT_OFFSET,
            IMPLICIT, bounds[0], bounds[0]);
  }

  public static RutaCondition createCondition(Token type, Expression... exprsArray) {
    List<Expression> exprL = new ArrayList<Expression>();
    if (exprsArray != null) {
      for (int i = 0; i < exprsArray.length; i++) {
        Expression expression = exprsArray[i];
        if (expression != null) {
          exprL.add(expression);
        }
      }
    }
    return createCondition(type, exprL);
  }

  public static RutaCondition createEmptyCondition(Token token) {
    int bounds[] = getBounds(token);
    return new RutaCondition(bounds[0], bounds[0], new ArrayList<Expression>(),
            RutaConditionConstants.CONSTANT_OFFSET, "", bounds[0], bounds[0]);
  }

}
