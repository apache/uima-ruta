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

package org.apache.uima.textmarker.ide.parser.ast;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.apache.uima.textmarker.parser.TextMarkerLexer;
import org.eclipse.dltk.ast.expressions.BooleanLiteral;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.ExpressionConstants;
import org.eclipse.dltk.ast.expressions.FloatNumericLiteral;
import org.eclipse.dltk.ast.expressions.NumericLiteral;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.VariableReference;

public class ExpressionFactory extends AbstractFactory implements ExpressionConstants {

  /**
   * @param ref
   * @param kind
   *          see {@link TMExpressionConstants}
   * @return
   */
  private static VariableReference newVariableReference(Token ref, int kind) {
    int bounds[] = getBounds(ref);
    return new TextMarkerVariableReference(bounds[0], bounds[1], ref.getText(), kind);
  }

  public static VariableReference createGenericVariableReference(Token ref) {
    return newVariableReference(ref, TMTypeConstants.TM_TYPE_G);
  }

  public static TextMarkerQuantifierLiteralExpression createQuantifierLiteralExpression(Token q,
          Token q2) {
    int bounds[] = getBounds(q);
    if (q2 != null) {
      bounds[1] = Math.max(bounds[1], getBounds(q2)[1]);
    }
    return new TextMarkerQuantifierLiteralExpression(bounds[0], bounds[1], q.getText());
  }

  // =====> BOOLEAN-EXPRESSIONS <======
  public static Expression createBooleanExpression(Expression e) {
    if (e == null)
      return null;
    return new TextMarkerExpression(e.sourceStart(), e.sourceEnd(), e, TMTypeConstants.TM_TYPE_B);
  }

  public static TextMarkerBooleanNumberExpression createBooleanNumberExpression(Expression e1,
          Token op, Expression e2) {
    int lexerOpID = op.getType(); // Integer.valueOf(op.getText());
    int operatorID = 0;
    // convert lexer-opId to dltk-opId:
    switch (lexerOpID) {
      case TextMarkerLexer.LESS:
        operatorID = E_LT;
        break;
      case TextMarkerLexer.LESSEQUAL:
        operatorID = E_LE;
        break;
      case TextMarkerLexer.GREATER:
        operatorID = E_GT;
        break;
      case TextMarkerLexer.GREATEREQUAL:
        operatorID = E_GE;
        break;
      case TextMarkerLexer.EQUAL:
        operatorID = E_EQUAL;
        break;
      case TextMarkerLexer.NOTEQUAL:
        operatorID = E_NOT_EQUAL;
        break;
      default:
        break;
    }
    return new TextMarkerBooleanNumberExpression(e1.sourceStart(), e2.sourceEnd(), operatorID, e1,
            e2);
  }

  public static VariableReference createBooleanVariableReference(Token variableId) {
    // int bounds[] = getBounds(variableId);
    return newVariableReference(variableId, TMTypeConstants.TM_TYPE_B);
  }

  public static BooleanLiteral createSimpleBooleanExpression(Token bToken) {
    int bounds[] = getBounds(bToken);
    boolean value = Boolean.valueOf(bToken.getText());
    return new BooleanLiteral(bounds[0], bounds[1], value);
  }

  // =====> TYPE-EXPRESSIONS <======
  public static Expression createTypeExpression(Expression e) {
    return new TextMarkerExpression(e.sourceStart(), e.sourceEnd(), e, TMTypeConstants.TM_TYPE_AT);
  }

  public static Expression createEmptyTypeExpression(Token token) {
    int bounds[] = getBounds(token);
    return new TextMarkerVariableReference(bounds[0], bounds[0], "", TMTypeConstants.TM_TYPE_AT);
  }
  public static Expression createEmptyStringExpression(Token token) {
    int bounds[] = getBounds(token);
    return new TextMarkerVariableReference(bounds[0], bounds[0], "", TMTypeConstants.TM_TYPE_S);
  }
  public static Expression createEmptyNumberExpression(Token token) {
    int bounds[] = getBounds(token);
    return new TextMarkerVariableReference(bounds[0], bounds[0], "", TMTypeConstants.TM_TYPE_N);
  }
  public static Expression createEmptyBooleanExpression(Token token) {
    int bounds[] = getBounds(token);
    return new TextMarkerVariableReference(bounds[0], bounds[0], "", TMTypeConstants.TM_TYPE_B);
  }
  // public static Expression createSimpleTypeExpression(Token at, TextMarkerBlock env) {
  // int bounds[] = getBounds(at);
  // return new TextMarkerSimpleTypeExpression(bounds[0], bounds[1], at.getText());
  // }

  public static VariableReference createAnnotationTypeVariableReference(Token atRef) {
    return newVariableReference(atRef, TMTypeConstants.TM_TYPE_AT);
  }

  public static Expression createAnnotationTypeConstantReference(Token atBasic) {
    int bounds[] = getBounds(atBasic);
    return new TextMarkerVariableReference(bounds[0], bounds[1], atBasic.getText(),
            TMTypeConstants.TM_TYPE_AT);
    // TextMarkerBasicAnnotationType(atBasic.getText(),bounds[0],bounds[1],bounds[0],bounds[1]);
  }

  // =====> STRING-EXPRESSIONS <======
  public static TextMarkerStringExpression createStringExpression(List<Expression> exprList) {
    if (exprList == null) {
      exprList = new ArrayList<Expression>();
    }
    int start = 0;
    int end = 0;
    if (!exprList.isEmpty()) {
      start = exprList.get(0).sourceStart();
      end = exprList.get(exprList.size() - 1).sourceEnd();
    }
    return new TextMarkerStringExpression(start, end, exprList);
  }

  public static StringLiteral createSimpleString(Token stringToken) {
    int bounds[] = getBounds(stringToken);
    return new StringLiteral(bounds[0], bounds[1], stringToken.getText());
  }

  // public static RessourceReference createRessourceReference

  public static VariableReference createStringVariableReference(Token variableId) {
    return newVariableReference(variableId, TMTypeConstants.TM_TYPE_S);
  }

  // =====> NUMBER-EXPRESSIONS <======
  public static TextMarkerExpression createNumberExpression(Expression e) {
    return new TextMarkerExpression(e.sourceStart(), e.sourceEnd(), e, TMTypeConstants.TM_TYPE_N);
  }

  public static NumericLiteral createDecimalLiteral(Token decLit, Token minus) {
    int bounds[] = getBounds(decLit);
    int value = Integer.valueOf(decLit.getText()); // .getInteger(decLit.getText());
    if (minus != null) {
      value = -value;
      bounds[0] = ((CommonToken) minus).getStartIndex();
    }
    return new NumericLiteral(bounds[0], bounds[1], value);
  }

  public static FloatNumericLiteral createFloatingPointLiteral(Token fpLit, Token minus) {
    int bounds[] = getBounds(fpLit);
    double value;
    try {
      value = Double.parseDouble(fpLit.getText());
    } catch (NumberFormatException e) {
      value = 0.0;
    }
    if (minus != null) {
      value = -value;
      bounds[0] = ((CommonToken) minus).getStartIndex();
    }
    return new FloatNumericLiteral(bounds[0], bounds[1], value);
  }

  /**
   * Creates (local) NumberVariableReference
   * 
   * @param numVarRef
   * @return
   */
  public static VariableReference createNumberVariableReference(Token numVarRef) {
    return newVariableReference(numVarRef, TMTypeConstants.TM_TYPE_N);
  }

  public static Expression createNegatedNumberExpression(Token minus, Expression expr) {
    int bounds[] = getSurroundingBounds(expr, (List<?>) null);
    if (minus != null) {
      bounds[0] = ((CommonToken) minus).getStartIndex();
    }
    return new TextMarkerExpression(bounds[0], bounds[1], expr, TMTypeConstants.TM_TYPE_N);
  }

  public static TextMarkerBinaryArithmeticExpression createBinaryArithmeticExpr(Expression exprA,
          Expression exprB, Token op) {
    int bounds[] = getBounds(exprA, exprB);
    int lexerOpID = op.getType();
    int operatorID = 0;
    // convert lexer-opId to dltk-opId:
    switch (lexerOpID) {
      case TextMarkerLexer.STAR:
        operatorID = ExpressionConstants.E_MULT;
        break;
      case TextMarkerLexer.SLASH:
        operatorID = ExpressionConstants.E_DIV;
        break;
      case TextMarkerLexer.PERCENT:
        operatorID = ExpressionConstants.E_MOD;
        break;
      case TextMarkerLexer.PLUS:
        operatorID = ExpressionConstants.E_PLUS;
        break;
      case TextMarkerLexer.MINUS:
        operatorID = ExpressionConstants.E_MINUS;
        break;
      default:
        break;
    }
    return new TextMarkerBinaryArithmeticExpression(bounds[0], bounds[1], exprA, exprB, operatorID);
  }

  public static Expression createUnaryArithmeticExpr(Expression expr, Token op) {
    int bounds[] = getBounds(op);
    if (expr != null) {
      bounds[1] = expr.sourceEnd();
    } else {
      System.out.println("debug::expr==null->null pointer Excptn");
    }
    int opID = convertOpToInt(op);
    return new TextMarkerUnaryArithmeticExpression(bounds[0], bounds[1], expr, opID);
  }

  private static int convertOpToInt(Token opToken) {
    return TMExpressionConstants.opIDs.get(opToken.getText());
  }

  // TODO
  public static Expression createBooleanFunction(Token op, Expression e1, Expression e2) {
    return new TextMarkerExpression(e1.sourceStart(), e2.sourceEnd(), null,
            TMTypeConstants.TM_TYPE_B);
  }

  public static Expression createListVariableReference(Token id) {
    return newVariableReference(id, TMTypeConstants.TM_TYPE_WL);
  }

  public static Expression createTableVariableReference(Token id) {
    return newVariableReference(id, TMTypeConstants.TM_TYPE_WT);
  }

  public static Expression createRessourceReference(Token path) {
    int bounds[] = getBounds(path);
    String pathWithoutQuotes = path.getText();
    pathWithoutQuotes = pathWithoutQuotes.substring(1, pathWithoutQuotes.length() - 1);
    return new TextMarkerRessourceReference(bounds[0], bounds[1], pathWithoutQuotes);
  }

  public static Expression createInnerListExpression(Token lBrak, List<String> inner, Token rBrak) {
    int boundsA[] = getBounds(lBrak, rBrak);
    StringBuffer s = new StringBuffer();
    s.append(lBrak);
    for (String el : inner) {
      s.append(el);
    }
    s.append(rBrak);
    return new TextMarkerInnerListExpression(boundsA[0], boundsA[1], s.toString());
  }

  public static Expression createBooleanTypeExpression(Expression e1, Token op, Expression e2) {
    int lexerOpID = op.getType(); // Integer.valueOf(op.getText());
    int operatorID = 0;
    // convert lexer-opId to dltk-opId:
    switch (lexerOpID) {
      case TextMarkerLexer.EQUAL:
        operatorID = E_EQUAL;
        break;
      case TextMarkerLexer.NOTEQUAL:
        operatorID = E_NOT_EQUAL;
        break;
      default:
        break;
    }
    if (e1 != null && e2 != null) {
      return new TextMarkerBooleanTypeExpression(e1.sourceStart(), e2.sourceEnd(), operatorID, e1,
              e2);
    }
    return null;
  }

  public static Expression createListExpression(List<Expression> exprList, int type) {
    if (exprList == null) {
      exprList = new ArrayList<Expression>();
    }
    int start = 0;
    int end = 0;
    if (!exprList.isEmpty()) {
      start = exprList.get(0).sourceStart();
      Expression expression = exprList.get(exprList.size() - 1);
      if (expression != null) {
        end = expression.sourceEnd();
      } else {
        exprList.get(0).sourceEnd();
      }
    }
    return new TextMarkerListExpression(start, end, exprList, type);
  }

  public static Expression createListExpression(Token var, int type) {
    return newVariableReference(var, type);
  }

  public static Expression createStringFunction(Token name, Expression var, List<Expression> list) {
    list.add(0, var);
    return createStringExpression(list);
  }

  

}
