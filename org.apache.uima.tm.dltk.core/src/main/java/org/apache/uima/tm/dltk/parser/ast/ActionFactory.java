package org.apache.uima.tm.dltk.parser.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.Token;
import org.apache.uima.tm.dltk.parser.ast.actions.TextMarkerAction;
import org.apache.uima.tm.dltk.parser.ast.actions.TextMarkerLogAction;
import org.apache.uima.tm.dltk.parser.ast.actions.TextMarkerStructureAction;
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
          List indexes, List<Expression> left, List<Expression> right) {
    int bounds[] = getBounds(type);
    int nameStart = bounds[0];
    int nameEnd = bounds[1];
    List<Expression> numExprs = new ArrayList<Expression>();
    Map<Expression, Expression> assignments = new LinkedHashMap<Expression, Expression>();
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

}
