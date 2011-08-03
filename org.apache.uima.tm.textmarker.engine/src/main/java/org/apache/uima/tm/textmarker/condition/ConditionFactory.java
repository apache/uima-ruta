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

package org.apache.uima.tm.textmarker.condition;

import java.util.List;

import org.antlr.runtime.Token;
import org.apache.uima.tm.textmarker.kernel.TextMarkerBlock;
import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;
import org.apache.uima.tm.textmarker.kernel.expression.bool.BooleanExpression;
import org.apache.uima.tm.textmarker.kernel.expression.list.ListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.list.StringListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.list.TypeListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.resource.WordListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;


public class ConditionFactory {

  private ConditionFactory() {
  }

  public static AbstractTextMarkerCondition createConditionAnd(
          List<AbstractTextMarkerCondition> conds, TextMarkerBlock parent) {
    return new AndCondition(conds);
  }

  public static AbstractTextMarkerCondition createConditionOr(
          List<AbstractTextMarkerCondition> conds, TextMarkerBlock parent) {
    return new OrCondition(conds);
  }

  public static AbstractTextMarkerCondition createConditionNot(AbstractTextMarkerCondition cond,
          TextMarkerBlock parent) {
    return new NotCondition(cond);
  }

  public static AbstractTextMarkerCondition createConditionContains(TypeExpression typeExpr,
          NumberExpression min, NumberExpression max, BooleanExpression percent,
          TextMarkerBlock parent) {
    return new ContainsCondition(typeExpr, min, max, percent);
  }

  public static AbstractTextMarkerCondition createConditionContextCount(TypeExpression typeExpr,
          NumberExpression min, NumberExpression max, Token var, TextMarkerBlock parent) {
    String varString = null;
    if (var != null) {
      varString = var.getText();
    }
    return new ContextCountCondition(typeExpr, min, max, varString);
  }

  public static AbstractTextMarkerCondition createConditionCurrentCount(TypeExpression typeExpr,
          NumberExpression min, NumberExpression max, Token var, TextMarkerBlock parent) {
    String varString = null;
    if (var != null) {
      varString = var.getText();
    }
    return new CurrentCountCondition(typeExpr, min, max, varString);
  }

  public static AbstractTextMarkerCondition createConditionCount(TypeExpression typeExpr,
          NumberExpression min, NumberExpression max, Token var, TextMarkerBlock parent) {
    String varString = null;
    if (var != null) {
      varString = var.getText();
    }
    return new CountCondition(typeExpr, min, max, varString);
  }

  public static AbstractTextMarkerCondition createConditionTotalCount(TypeExpression typeExpr,
          NumberExpression min, NumberExpression max, Token var, TextMarkerBlock parent) {
    String varString = null;
    if (var != null) {
      varString = var.getText();
    }
    return new TotalCountCondition(typeExpr, min, max, varString);
  }

  public static AbstractTextMarkerCondition createConditionInList(WordListExpression listExpr,
          NumberExpression dist, BooleanExpression rel, TextMarkerBlock parent) {
    return new InListCondition(listExpr, dist, rel);
  }

  public static AbstractTextMarkerCondition createConditionIsInTag(StringExpression id,
          List<StringExpression> list1, List<StringExpression> list2, TextMarkerBlock parent) {
    return new IsInTagCondition(id, list1, list2);
  }

  public static AbstractTextMarkerCondition createConditionMOfN(
          List<AbstractTextMarkerCondition> conds, NumberExpression min, NumberExpression max,
          TextMarkerBlock parent) {
    return new MOfNCondition(conds, min, max);
  }

  public static AbstractTextMarkerCondition createConditionNear(TypeExpression typeExpr,
          NumberExpression min, NumberExpression max, BooleanExpression direction,
          BooleanExpression filtered, TextMarkerBlock parent) {
    return new NearCondition(typeExpr, min, max, direction, filtered);
  }

  public static AbstractTextMarkerCondition createConditionPartOf(TypeExpression type,
          TypeListExpression list, TextMarkerBlock parent) {
    if (type != null) {
      return new PartOfCondition(type);
    } else {
      return new PartOfCondition(list);
    }
  }

  public static AbstractTextMarkerCondition createConditionPosition(TypeExpression typeExpr,
          NumberExpression pos, TextMarkerBlock parent) {
    return new PositionCondition(typeExpr, pos);
  }

  public static AbstractTextMarkerCondition createConditionRegExp(StringExpression patternExpr,
          BooleanExpression ignoreCase, TextMarkerBlock parent) {
    return new RegExpCondition(patternExpr, ignoreCase);
  }

  public static AbstractTextMarkerCondition createConditionScore(NumberExpression min,
          NumberExpression max, Token var, TextMarkerBlock parent) {
    String varString = null;
    if (var != null) {
      varString = var.getText();
    }
    return new ScoreCondition(min, max, varString);
  }

  public static AbstractTextMarkerCondition createConditionVote(TypeExpression type1Expr,
          TypeExpression type2Expr, TextMarkerBlock parent) {
    return new VoteCondition(type1Expr, type2Expr);
  }

  public static AbstractTextMarkerCondition createConditionLast(TypeExpression typeExpr,
          TextMarkerBlock parent) {
    return new LastCondition(typeExpr);
  }

  public static AbstractTextMarkerCondition createConditionIf(BooleanExpression e,
          TextMarkerBlock parent) {
    return new IfCondition(e);
  }

  public static AbstractTextMarkerCondition createConditionFeature(StringExpression se, Object v,
          TextMarkerBlock parent) {
    if (v instanceof NumberExpression) {
      return new FeatureCondition(se, (NumberExpression) v);
    } else if (v instanceof StringExpression) {
      return new FeatureCondition(se, (StringExpression) v);
    } else if (v instanceof BooleanExpression) {
      return new FeatureCondition(se, (BooleanExpression) v);
    }
    return null;
  }

  public static AbstractTextMarkerCondition createConditionParse(Token id, TextMarkerBlock env) {
    String var = id == null ? "" : id.getText();
    return new ParseCondition(var);
  }

  public static AbstractTextMarkerCondition createConditionVariable(Token id) {
    return new VariableCondition(id.getText());
  }

  public static AbstractTextMarkerCondition createConditionIs(TypeExpression type,
          TypeListExpression list, TextMarkerBlock env) {
    if (type != null) {
      return new IsCondition(type);
    } else {
      return new IsCondition(list);
    }
  }

  public static AbstractTextMarkerCondition createConditionAfter(TypeExpression type,
          TypeListExpression list, TextMarkerBlock env) {
    if (type != null) {
      return new AfterCondition(type);
    } else {
      return new AfterCondition(list);
    }
  }

  public static AbstractTextMarkerCondition createConditionBefore(TypeExpression type,
          TypeListExpression list, TextMarkerBlock env) {
    if (type != null) {
      return new BeforeCondition(type);
    } else {
      return new BeforeCondition(list);
    }
  }

  public static AbstractTextMarkerCondition createConditionEndsWith(TypeExpression type,
          TypeListExpression list, TextMarkerBlock env) {
    if (type != null) {
      return new EndsWithCondition(type);
    } else {
      return new EndsWithCondition(list);
    }
  }

  public static AbstractTextMarkerCondition createConditionStartsWith(TypeExpression type,
          TypeListExpression list, TextMarkerBlock env) {
    if (type != null) {
      return new StartsWithCondition(type);
    } else {
      return new StartsWithCondition(list);
    }
  }

  public static AbstractTextMarkerCondition createConditionPartOfNeq(TypeExpression type,
          TypeListExpression list, TextMarkerBlock env) {
    if (type != null) {
      return new PartOfNeqCondition(type);
    } else {
      return new PartOfNeqCondition(list);
    }
  }

  public static AbstractTextMarkerCondition createConditionSize(ListExpression<?> list,
          NumberExpression min, NumberExpression max, Token var, TextMarkerBlock env) {
    return new SizeCondition(list, min, max, var == null ? null : var.getText());
  }

  public static AbstractTextMarkerCondition createConditionInList(StringListExpression list,
          NumberExpression dist, BooleanExpression rel, TextMarkerBlock env) {
    return new InListCondition(list, dist, rel);
  }

  public static AbstractTextMarkerCondition createConditionCount(ListExpression<Object> type,
          TextMarkerExpression a, NumberExpression min, NumberExpression max, Token var,
          TextMarkerBlock env) {
    return new CountCondition(type, a, min, max, var == null ? null : var.getText());
  }

  public static AbstractTextMarkerCondition createConditionContains(ListExpression list,
          TextMarkerExpression a, NumberExpression min, NumberExpression max,
          BooleanExpression percent, TextMarkerBlock env) {
    return new ContainsCondition(list, a, min, max, percent);
  }

}
