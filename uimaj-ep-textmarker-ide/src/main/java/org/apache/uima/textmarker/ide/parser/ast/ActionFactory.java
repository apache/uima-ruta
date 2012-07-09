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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.Token;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.ExpressionConstants;

public class ActionFactory extends AbstractFactory {

  public static TextMarkerAction createAction(Token type, List exprsRaw) {
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
    return new TextMarkerAction(bounds[0], bounds[1], exprs,
            ExpressionConstants.USER_EXPRESSION_START + type.getType(), type.getText(), nameStart,
            nameEnd);
  }

  public static TextMarkerAction createAction(Token type, Expression... exprsArray) {
    List<Expression> listOfExpressions = new ArrayList<Expression>();
    if (exprsArray != null) {
      for (int i = 0; i < exprsArray.length; i++) {
        Expression expression = exprsArray[i];
        if (expression != null) {
          listOfExpressions.add(expression);
        }
      }
    }
    return createAction(type, listOfExpressions);
  }

  public static TextMarkerAction createEmptyAction(Token token) {
    int bounds[] = getBounds(token);
    return new TextMarkerAction(bounds[0], bounds[0], new ArrayList<Expression>(),
            TMConditionConstants.CONSTANT_OFFSET, "", bounds[0], bounds[0]);
  }
  
  public static TextMarkerAction createAction(Token type, Map<Expression, Expression> map,
          Expression... exprsArray) {
    List<Expression> listOfExpressions = new ArrayList<Expression>();
    // TODO add map
    if (exprsArray != null) {
      for (int i = 0; i < exprsArray.length; i++) {
        Expression expression = exprsArray[i];
        if (expression != null) {
          listOfExpressions.add(expression);
        }
      }
    }
    return createAction(type, listOfExpressions);
  }

  public static TextMarkerAction createCallAction(Token callToken, ComponentReference ref) {
    return createAction(callToken, ref);
  }

  public static TextMarkerAction createCallAction(Token callToken, ComponentReference ref,
          Expression list) {
    return createAction(callToken, ref, list);
  }

  public static TextMarkerAction createStructureAction(Token type, Expression structure,
          List<Expression> indexes, List<Expression> left, List<Expression> right) {
    List<Expression> args = new ArrayList<Expression>();
    if (indexes != null) {
      args.addAll(indexes);
    }
    return createStructureAction(type, args, left, right, structure);
  }

  /**
   * @param left
   * @param bounds
   * @param exprs
   */
  private static void filterNullObjsAndSetBounds(List left, int[] bounds, List<Expression> exprs) {
    if (left != null) {
      for (Object expressionObj : left) {
        Expression expr = (Expression) expressionObj;
        if (expr != null) {
          exprs.add(expr);
        }
      }
      if (!exprs.isEmpty()) {
        Expression lastExpr = exprs.get(exprs.size() - 1);
        bounds[1] = Math.max(bounds[1], lastExpr.sourceEnd());
      }
    }
  }

  public static TextMarkerAction createLogAction(Token type, Expression logString, Token level) {
    int[] nameBounds = getBounds(type);
    int[] bounds;
    int levelBounds[] = new int[] { -1, -1 };
    if (level != null) {
      levelBounds = getBounds(level);
      bounds = getBounds(type, level);
    } else {
      bounds = getBounds(type);
    }
    if (logString != null) {
      bounds[1] = Math.max(bounds[1], logString.sourceEnd());
    }
    List exprs = new ArrayList();
    exprs.add(logString);
    return new TextMarkerLogAction(bounds[0], bounds[1], type.getText(), nameBounds[0],
            nameBounds[1], exprs, levelBounds[0], levelBounds[1]);
  }

  public static TextMarkerAction createStructureAction(Token type, Expression structure,
          Expression index, Expression table, List left, List right) {
    int bounds[] = getBounds(type);
    int nameStart = bounds[0];
    int nameEnd = bounds[1];
    List<Expression> numExprs = new ArrayList<Expression>();
    Map<Expression, Expression> assignments = new LinkedHashMap<Expression, Expression>();
    List<Expression> indexes = new ArrayList<Expression>();
    indexes.add(table);
    indexes.add(index);
    indexes.add(structure);
    filterNullObjsAndSetBounds(indexes, bounds, numExprs);
    if (left != null && right != null) {
      Iterator<Expression> keysIt = left.iterator();
      Iterator<Expression> valsIt = right.iterator();
      Expression val = null;
      while (keysIt.hasNext()) {
        Expression key = keysIt.next();
        if (!valsIt.hasNext()) {
          break;
        }
        val = valsIt.next();
        assignments.put(key, val);
      }
      if (val != null) {
        bounds[1] = val.sourceEnd();
      }
    }
    return new TextMarkerStructureAction(bounds[0], bounds[1], numExprs,
            ExpressionConstants.USER_EXPRESSION_START + type.getType(), type.getText(), nameStart,
            nameEnd, assignments, structure);
  }

  public static TextMarkerAction createAction(Token name, Expression f, List<Expression> list) {
    List<Expression> list2 = new ArrayList<Expression>();
    list2.add(f);
    list2.addAll(list);
    return createAction(name, list2);
  }

  public static TextMarkerAction createAction(Token name, Expression a1, Expression a2,
          List<Expression> list) {
    List<Expression> complete = new ArrayList<Expression>();
    complete.add(a1);
    complete.add(a2);
    complete.addAll(list);
    return createAction(name, complete);
  }

  public static TextMarkerAction createConfigureAction(Token name, ComponentReference ns,
          List<Expression> left, List<Expression> right) {
    List<Expression> exprs = new ArrayList<Expression>();
    exprs.add(ns);
    if (left != null && right != null) {
      for (int i = 0; i < Math.min(left.size(), right.size()); i++) {
        exprs.add(left.get(i));
        exprs.add(right.get(i));
      }
    }
    return createAction(name, exprs);
  }

  public static TextMarkerAction createStructureAction(Token name, List<Expression> args,
          List<Expression> left, List<Expression> right, Expression structure) {
    int bounds[] = getBounds(name);
    int nameStart = bounds[0];
    int nameEnd = bounds[1];
    List<Expression> numExprs = new ArrayList<Expression>();
    Map<Expression, Expression> assignments = new LinkedHashMap<Expression, Expression>();
    filterNullObjsAndSetBounds(args, bounds, numExprs);
    if (left != null && right != null) {
      Iterator<Expression> keysIt = left.iterator();
      Iterator<Expression> valsIt = right.iterator();
      Expression val = null;
      while (keysIt.hasNext()) {
        Expression key = keysIt.next();
        if (!valsIt.hasNext()) {
          break;
        }
        val = valsIt.next();
        assignments.put(key, val);
      }
      if (val != null) {
        bounds[1] = val.sourceEnd();
      }
    }
    return new TextMarkerStructureAction(bounds[0], bounds[1], numExprs,
            ExpressionConstants.USER_EXPRESSION_START + name.getType(), name.getText(), nameStart,
            nameEnd, assignments, structure);
  }

}
