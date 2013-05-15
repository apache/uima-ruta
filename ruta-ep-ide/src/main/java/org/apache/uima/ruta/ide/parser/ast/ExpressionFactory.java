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

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.apache.uima.ruta.parser.RutaLexer;
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
   *          see {@link RutaExpressionConstants}
   * @return
   */
  private static VariableReference newVariableReference(Token ref, int kind) {
    int bounds[] = getBounds(ref);
    return new RutaVariableReference(bounds[0], bounds[1], ref.getText(), kind);
  }

  public static VariableReference createGenericVariableReference(Token ref) {
    return newVariableReference(ref, RutaTypeConstants.RUTA_TYPE_G);
  }

  public static RutaQuantifierLiteralExpression createQuantifierLiteralExpression(Token q,
          Token q2) {
    int bounds[] = getBounds(q);
    if (q2 != null) {
      bounds[1] = Math.max(bounds[1], getBounds(q2)[1]);
    }
    return new RutaQuantifierLiteralExpression(bounds[0], bounds[1], q.getText());
  }

  // =====> BOOLEAN-EXPRESSIONS <======
  public static Expression createBooleanExpression(Expression e) {
    if (e == null)
      return null;
    return new RutaExpression(e.sourceStart(), e.sourceEnd(), e, RutaTypeConstants.RUTA_TYPE_B);
  }

  public static RutaBooleanNumberExpression createBooleanNumberExpression(Expression e1,
          Token op, Expression e2) {
    int lexerOpID = op.getType(); // Integer.valueOf(op.getText());
    int operatorID = 0;
    // convert lexer-opId to dltk-opId:
    switch (lexerOpID) {
      case RutaLexer.LESS:
        operatorID = E_LT;
        break;
      case RutaLexer.LESSEQUAL:
        operatorID = E_LE;
        break;
      case RutaLexer.GREATER:
        operatorID = E_GT;
        break;
      case RutaLexer.GREATEREQUAL:
        operatorID = E_GE;
        break;
      case RutaLexer.EQUAL:
        operatorID = E_EQUAL;
        break;
      case RutaLexer.NOTEQUAL:
        operatorID = E_NOT_EQUAL;
        break;
      default:
        break;
    }
    return new RutaBooleanNumberExpression(e1.sourceStart(), e2.sourceEnd(), operatorID, e1,
            e2);
  }

  public static VariableReference createBooleanVariableReference(Token variableId) {
    // int bounds[] = getBounds(variableId);
    return newVariableReference(variableId, RutaTypeConstants.RUTA_TYPE_B);
  }

  public static BooleanLiteral createSimpleBooleanExpression(Token bToken) {
    int bounds[] = getBounds(bToken);
    boolean value = Boolean.valueOf(bToken.getText());
    return new BooleanLiteral(bounds[0], bounds[1], value);
  }

  // =====> TYPE-EXPRESSIONS <======
  public static Expression createTypeExpression(Expression e) {
    if(e != null) {
      return new RutaExpression(e.sourceStart(), e.sourceEnd(), e, RutaTypeConstants.RUTA_TYPE_AT);
    }
    return null;
  }

  public static Expression createEmptyTypeExpression(Token token) {
    int bounds[] = getBounds(token);
    return new RutaVariableReference(bounds[0], bounds[0], "", RutaTypeConstants.RUTA_TYPE_AT);
  }
  public static Expression createEmptyStringExpression(Token token) {
    int bounds[] = getBounds(token);
    return new RutaVariableReference(bounds[0], bounds[0], "", RutaTypeConstants.RUTA_TYPE_S);
  }
  public static Expression createEmptyNumberExpression(Token token) {
    int bounds[] = getBounds(token);
    return new RutaVariableReference(bounds[0], bounds[0], "", RutaTypeConstants.RUTA_TYPE_N);
  }
  public static Expression createEmptyBooleanExpression(Token token) {
    int bounds[] = getBounds(token);
    return new RutaVariableReference(bounds[0], bounds[0], "", RutaTypeConstants.RUTA_TYPE_B);
  }
  // public static Expression createSimpleTypeExpression(Token at, RutaBlock env) {
  // int bounds[] = getBounds(at);
  // return new RutaSimpleTypeExpression(bounds[0], bounds[1], at.getText());
  // }

  public static VariableReference createAnnotationTypeVariableReference(Token atRef) {
    return newVariableReference(atRef, RutaTypeConstants.RUTA_TYPE_AT);
  }

  public static Expression createAnnotationTypeConstantReference(Token atBasic) {
    int bounds[] = getBounds(atBasic);
    return new RutaVariableReference(bounds[0], bounds[1], atBasic.getText(),
            RutaTypeConstants.RUTA_TYPE_AT);
    // RutaBasicAnnotationType(atBasic.getText(),bounds[0],bounds[1],bounds[0],bounds[1]);
  }

  // =====> STRING-EXPRESSIONS <======
  public static RutaStringExpression createStringExpression(List<Expression> exprList) {
    if (exprList == null) {
      exprList = new ArrayList<Expression>();
    }
    int start = 0;
    int end = 0;
    if (!exprList.isEmpty()) {
      start = exprList.get(0).sourceStart();
      end = exprList.get(exprList.size() - 1).sourceEnd();
    }
    return new RutaStringExpression(start, end, exprList);
  }

  public static StringLiteral createSimpleString(Token stringToken) {
    int bounds[] = getBounds(stringToken);
    return new StringLiteral(bounds[0], bounds[1], stringToken.getText());
  }

  // public static RessourceReference createRessourceReference

  public static VariableReference createStringVariableReference(Token variableId) {
    return newVariableReference(variableId, RutaTypeConstants.RUTA_TYPE_S);
  }

  // =====> NUMBER-EXPRESSIONS <======
  public static RutaExpression createNumberExpression(Expression e) {
    return new RutaExpression(e.sourceStart(), e.sourceEnd(), e, RutaTypeConstants.RUTA_TYPE_N);
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
    return newVariableReference(numVarRef, RutaTypeConstants.RUTA_TYPE_N);
  }

  public static Expression createNegatedNumberExpression(Token minus, Expression expr) {
    int bounds[] = getSurroundingBounds(expr, (List<?>) null);
    if (minus != null) {
      bounds[0] = ((CommonToken) minus).getStartIndex();
    }
    return new RutaExpression(bounds[0], bounds[1], expr, RutaTypeConstants.RUTA_TYPE_N);
  }

  public static RutaBinaryArithmeticExpression createBinaryArithmeticExpr(Expression exprA,
          Expression exprB, Token op) {
    int bounds[] = getBounds(exprA, exprB);
    int lexerOpID = op.getType();
    int operatorID = 0;
    // convert lexer-opId to dltk-opId:
    switch (lexerOpID) {
      case RutaLexer.STAR:
        operatorID = ExpressionConstants.E_MULT;
        break;
      case RutaLexer.SLASH:
        operatorID = ExpressionConstants.E_DIV;
        break;
      case RutaLexer.PERCENT:
        operatorID = ExpressionConstants.E_MOD;
        break;
      case RutaLexer.PLUS:
        operatorID = ExpressionConstants.E_PLUS;
        break;
      case RutaLexer.MINUS:
        operatorID = ExpressionConstants.E_MINUS;
        break;
      default:
        break;
    }
    return new RutaBinaryArithmeticExpression(bounds[0], bounds[1], exprA, exprB, operatorID);
  }

  public static Expression createUnaryArithmeticExpr(Expression expr, Token op) {
    int bounds[] = getBounds(op);
    if (expr != null) {
      bounds[1] = expr.sourceEnd();
    } else {
      System.out.println("debug::expr==null->null pointer Excptn");
    }
    int opID = convertOpToInt(op);
    return new RutaUnaryArithmeticExpression(bounds[0], bounds[1], expr, opID);
  }

  private static int convertOpToInt(Token opToken) {
    return RutaExpressionConstants.opIDs.get(opToken.getText());
  }

  // TODO
  public static Expression createBooleanFunction(Token op, Expression e1, Expression e2) {
    return new RutaExpression(e1.sourceStart(), e2.sourceEnd(), null,
            RutaTypeConstants.RUTA_TYPE_B);
  }

  public static Expression createListVariableReference(Token id) {
    return newVariableReference(id, RutaTypeConstants.RUTA_TYPE_WL);
  }

  public static Expression createTableVariableReference(Token id) {
    return newVariableReference(id, RutaTypeConstants.RUTA_TYPE_WT);
  }

  public static Expression createRessourceReference(Token path) {
    int bounds[] = getBounds(path);
    String pathWithoutQuotes = path.getText();
    pathWithoutQuotes = pathWithoutQuotes.substring(1, pathWithoutQuotes.length() - 1);
    return new RutaRessourceReference(bounds[0], bounds[1], pathWithoutQuotes);
  }

  public static Expression createInnerListExpression(Token lBrak, List<String> inner, Token rBrak) {
    int boundsA[] = getBounds(lBrak, rBrak);
    StringBuffer s = new StringBuffer();
    s.append(lBrak);
    for (String el : inner) {
      s.append(el);
    }
    s.append(rBrak);
    return new RutaInnerListExpression(boundsA[0], boundsA[1], s.toString());
  }

  public static Expression createBooleanTypeExpression(Expression e1, Token op, Expression e2) {
    int lexerOpID = op.getType(); // Integer.valueOf(op.getText());
    int operatorID = 0;
    // convert lexer-opId to dltk-opId:
    switch (lexerOpID) {
      case RutaLexer.EQUAL:
        operatorID = E_EQUAL;
        break;
      case RutaLexer.NOTEQUAL:
        operatorID = E_NOT_EQUAL;
        break;
      default:
        break;
    }
    if (e1 != null && e2 != null) {
      return new RutaBooleanTypeExpression(e1.sourceStart(), e2.sourceEnd(), operatorID, e1,
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
    return new RutaListExpression(start, end, exprList, type);
  }

  public static Expression createListExpression(Token var, int type) {
    return newVariableReference(var, type);
  }

  public static Expression createStringFunction(Token name, Expression var, List<Expression> list) {
    list.add(0, var);
    return createStringFunction(name, list);
  }


  public static Expression createFeatureMatch(Token feature, Token comp, Expression value) {
    int bounds[] = getBounds(feature);
    return new FeatureMatchExpression(bounds[0], value.sourceEnd(), feature, comp, value);
  }

  public static Expression createBooleanFunction(Token id, List<Expression> args) {
    return createFunction(id, args, RutaTypeConstants.RUTA_TYPE_B);
  }
  
  public static Expression createNumberFunction(Token id, List<Expression> args) {
    return createFunction(id, args, RutaTypeConstants.RUTA_TYPE_N);
  }
  
  public static Expression createStringFunction(Token id, List<Expression> args) {
    return createFunction(id, args, RutaTypeConstants.RUTA_TYPE_S);
  }
  
  public static Expression createTypeFunction(Token id, List<Expression> args) {
    return createFunction(id, args, RutaTypeConstants.RUTA_TYPE_AT);
  }
  
  public static RutaFunction createFunction(Token type, List<Expression> exprsRaw, int kind) {
    int bounds[] = getBounds(type);
    int nameStart = bounds[0];
    int nameEnd = bounds[1];
    List<Expression> exprs = new ArrayList<Expression>();
    if (exprsRaw != null) {
      for (Object expressionObj : exprsRaw) {
        Expression expr = (Expression) expressionObj;
        if (expr != null) {
          exprs.add(expr);
        }
      }
      if (!exprs.isEmpty()) {
        Expression lastExpr = (Expression) exprs.get(exprs.size() - 1);
        bounds[1] = Math.max(bounds[1], lastExpr.sourceEnd());
      }
    }
    return new RutaFunction(bounds[0], bounds[1], exprs,
            kind, type.getText(), nameStart,
            nameEnd);
  }
  

}
