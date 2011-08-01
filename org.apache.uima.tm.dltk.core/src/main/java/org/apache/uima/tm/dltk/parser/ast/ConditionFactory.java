package org.apache.uima.tm.dltk.parser.ast;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.Token;
import org.apache.uima.tm.dltk.parser.ast.conditions.TextMarkerCondition;
import org.eclipse.dltk.ast.expressions.Expression;


public class ConditionFactory extends AbstractFactory {

  public static TextMarkerCondition createCondition(Token type, List exprs) {
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
    return new TextMarkerCondition(wholeConditionBounds[0], wholeConditionBounds[1], exprs,
            TMConditionConstants.CONSTANT_OFFSET + type.getType(), type.getText(), nameStart,
            nameEnd);
  }

  public static TextMarkerCondition createCondition(Token type, Expression... exprsArray) {
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

  // public static TextMarkerCondition createConditionAnd(int start, int end,
  // DLTKToken token, List<TextMarkerCondition> conds,
  // TextMarkerBlock parent) {
  // return new TextMarkerCondition(start, end);
  // }

  // public static TextMarkerCondition createConditionContains(
  // Expression typeExpr, Token name, Expression min, Expression max,
  // Expression percent) {
  // int bounds[];
  // bounds = getBounds(name);
  // List<Expression> list = new ArrayList<Expression>();
  // if (min != null && max != null) {
  // list.add(min);
  // list.add(max);
  // bounds[1] = max.sourceEnd();
  // }
  // boolean percentSet;
  // if (percent == null) {
  // percentSet = false;
  // } else {
  // percentSet = true;
  // bounds[1] = percent.sourceEnd();
  // }
  // return new TextMarkerContainsCondition(bounds[0], bounds[1], list,
  // percentSet);
  // }
  //
  // public static TextMarkerCondition createConditionCount(int start, int end,
  // String text, Expression text2, Expression text3,
  // TextMarkerBlock parent) {
  // return new TextMarkerCondition(start, end);
  // }
  //
  // public static TextMarkerCondition createConditionContextCount(int start,
  // int end, String text, Expression text2, Expression text3,
  // TextMarkerBlock parent) {
  // return new TextMarkerCondition(start, end);
  // }
  //
  // public static TextMarkerCondition createConditionInList(int start, int end,
  // String wordList, Expression text, Expression text2,
  // TextMarkerBlock parent) {
  // return new TextMarkerCondition(start, end);
  // }
  //
  // public static TextMarkerCondition createConditionInList(int start, int end,
  // List<String> list, Expression text, Expression text2,
  // TextMarkerBlock parent) {
  // return new TextMarkerCondition(start, end);
  // }
  //
  // public static TextMarkerCondition createConditionIsInTag(int start,
  // int end, Expression text, List<Expression> list1,
  // List<Expression> list2, TextMarkerBlock parent) {
  // return new TextMarkerCondition(start, end);
  // }
  //
  // public static TextMarkerCondition createConditionMOfN(int start, int end,
  // List<TextMarkerCondition> conds, Expression text, Expression text2,
  // TextMarkerBlock parent) {
  // return new TextMarkerCondition(start, end);
  // }
  //
  // public static TextMarkerCondition createConditionNear(int start, int end,
  // Expression text, Expression text2, Expression text3,
  // TextMarkerBlock parent) {
  // return new TextMarkerCondition(start, end);
  // }
  //
  // public static TextMarkerCondition createConditionNot(int start, int end,
  // TextMarkerCondition c, TextMarkerBlock parent) {
  // return new TextMarkerCondition(start, end);
  // }
  //
  // public static TextMarkerCondition createConditionOr(int start, int end,
  // List<TextMarkerCondition> conds, TextMarkerBlock parent) {
  // return new TextMarkerCondition(start, end);
  // }
  //
  // public static TextMarkerCondition createConditionPartOf(int start, int end,
  // String text, TextMarkerBlock parent) {
  // return new TextMarkerCondition(start, end);
  // }
  //
  // public static TextMarkerCondition createConditionPosition(int start,
  // int end, String text, Expression text2, TextMarkerBlock parent) {
  // return new TextMarkerCondition(start, end);
  // }
  //
  // public static TextMarkerCondition createConditionRegExp(int start, int end,
  // Expression text, TextMarkerBlock parent) {
  // return new TextMarkerCondition(start, end);
  // }
  //
  // public static TextMarkerCondition createConditionScore(int start, int end,
  // String text, Expression text2, Expression text3,
  // TextMarkerBlock parent) {
  // return new TextMarkerCondition(start, end);
  // }
  //
  // public static TextMarkerCondition createConditionVote(int start, int end,
  // String text, String text2, TextMarkerBlock parent) {
  // return new TextMarkerCondition(start, end);
  // }
  //
  // public static TextMarkerCondition createConditionLast(int start, int end,
  // String text, TextMarkerBlock parent) {
  // return new TextMarkerCondition(start, end);
  // }
  //
  // public static TextMarkerCondition createConditionIf(int start, int end,
  // Expression e, TextMarkerBlock parent) {
  // return new TextMarkerCondition(start, end);
  // }
  //
  // public static TextMarkerCondition createConditionFeature(int start,
  // int end, TextMarkerBlock parent) {
  // return new TextMarkerCondition(start, end);
  // }
  //
  // public static TextMarkerCondition createConditionParse(int start, int end,
  // TextMarkerBlock env) {
  // return new TextMarkerCondition(start, end);
  // }

}
