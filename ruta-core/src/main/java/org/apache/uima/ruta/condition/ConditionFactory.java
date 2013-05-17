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

package org.apache.uima.ruta.condition;

import java.util.List;

import org.antlr.runtime.Token;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.bool.BooleanExpression;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.list.StringListExpression;
import org.apache.uima.ruta.expression.list.TypeListExpression;
import org.apache.uima.ruta.expression.number.NumberExpression;
import org.apache.uima.ruta.expression.resource.WordListExpression;
import org.apache.uima.ruta.expression.string.StringExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;

public class ConditionFactory {

  private ConditionFactory() {
  }

  public static AbstractRutaCondition createConditionAnd(List<AbstractRutaCondition> conds,
          RutaBlock parent) {
    return new AndCondition(conds);
  }

  public static AbstractRutaCondition createConditionOr(List<AbstractRutaCondition> conds,
          RutaBlock parent) {
    return new OrCondition(conds);
  }

  public static AbstractRutaCondition createConditionNot(AbstractRutaCondition cond,
          RutaBlock parent) {
    return new NotCondition(cond);
  }

  public static AbstractRutaCondition createConditionContains(TypeExpression typeExpr,
          NumberExpression min, NumberExpression max, BooleanExpression percent, RutaBlock parent) {
    return new ContainsCondition(typeExpr, min, max, percent);
  }

  public static AbstractRutaCondition createConditionContextCount(TypeExpression typeExpr,
          NumberExpression min, NumberExpression max, Token var, RutaBlock parent) {
    String varString = null;
    if (var != null) {
      varString = var.getText();
    }
    return new ContextCountCondition(typeExpr, min, max, varString);
  }

  public static AbstractRutaCondition createConditionCurrentCount(TypeExpression typeExpr,
          NumberExpression min, NumberExpression max, Token var, RutaBlock parent) {
    String varString = null;
    if (var != null) {
      varString = var.getText();
    }
    return new CurrentCountCondition(typeExpr, min, max, varString);
  }

  public static AbstractRutaCondition createConditionCount(TypeExpression typeExpr,
          NumberExpression min, NumberExpression max, Token var, RutaBlock parent) {
    String varString = null;
    if (var != null) {
      varString = var.getText();
    }
    return new CountCondition(typeExpr, min, max, varString);
  }

  public static AbstractRutaCondition createConditionTotalCount(TypeExpression typeExpr,
          NumberExpression min, NumberExpression max, Token var, RutaBlock parent) {
    String varString = null;
    if (var != null) {
      varString = var.getText();
    }
    return new TotalCountCondition(typeExpr, min, max, varString);
  }

  public static AbstractRutaCondition createConditionInList(WordListExpression listExpr,
          NumberExpression dist, BooleanExpression rel, RutaBlock parent) {
    return new InListCondition(listExpr, dist, rel);
  }

  public static AbstractRutaCondition createConditionMOfN(List<AbstractRutaCondition> conds,
          NumberExpression min, NumberExpression max, RutaBlock parent) {
    return new MOfNCondition(conds, min, max);
  }

  public static AbstractRutaCondition createConditionNear(TypeExpression typeExpr,
          NumberExpression min, NumberExpression max, BooleanExpression direction,
          BooleanExpression filtered, RutaBlock parent) {
    return new NearCondition(typeExpr, min, max, direction, filtered);
  }

  public static AbstractRutaCondition createConditionPartOf(TypeExpression type,
          TypeListExpression list, RutaBlock parent) {
    if (type != null) {
      return new PartOfCondition(type);
    } else {
      return new PartOfCondition(list);
    }
  }

  public static AbstractRutaCondition createConditionPosition(TypeExpression typeExpr,
          NumberExpression pos, BooleanExpression rel, RutaBlock parent) {
    return new PositionCondition(typeExpr, pos, rel);
  }

  public static AbstractRutaCondition createConditionRegExp(StringExpression patternExpr,
          BooleanExpression ignoreCase, RutaBlock parent) {
    return new RegExpCondition(patternExpr, ignoreCase);
  }

  public static AbstractRutaCondition createConditionRegExp(Token var,
          StringExpression patternExpr, BooleanExpression ignoreCase, RutaBlock parent) {
    return new RegExpCondition(var == null ? null : var.getText(), patternExpr, ignoreCase);
  }

  public static AbstractRutaCondition createConditionScore(NumberExpression min,
          NumberExpression max, Token var, RutaBlock parent) {
    String varString = null;
    if (var != null) {
      varString = var.getText();
    }
    return new ScoreCondition(min, max, varString);
  }

  public static AbstractRutaCondition createConditionVote(TypeExpression type1Expr,
          TypeExpression type2Expr, RutaBlock parent) {
    return new VoteCondition(type1Expr, type2Expr);
  }

  public static AbstractRutaCondition createConditionLast(TypeExpression typeExpr, RutaBlock parent) {
    return new LastCondition(typeExpr);
  }

  public static AbstractRutaCondition createConditionIf(BooleanExpression e, RutaBlock parent) {
    return new IfCondition(e);
  }

  public static AbstractRutaCondition createConditionFeature(StringExpression se, Object v,
          RutaBlock parent) {
    if (v instanceof NumberExpression) {
      return new FeatureCondition(se, (NumberExpression) v);
    } else if (v instanceof BooleanExpression) {
      return new FeatureCondition(se, (BooleanExpression) v);
    } else if (v instanceof StringExpression) {
      return new FeatureCondition(se, (StringExpression) v);
    }
    return null;
  }

  public static AbstractRutaCondition createConditionParse(Token id, RutaBlock env) {
    String var = id == null ? "" : id.getText();
    return new ParseCondition(var);
  }

  public static AbstractRutaCondition createConditionVariable(Token id) {
    return new VariableCondition(id.getText());
  }

  public static AbstractRutaCondition createConditionIs(TypeExpression type,
          TypeListExpression list, RutaBlock env) {
    if (type != null) {
      return new IsCondition(type);
    } else {
      return new IsCondition(list);
    }
  }

  public static AbstractRutaCondition createConditionAfter(TypeExpression type,
          TypeListExpression list, RutaBlock env) {
    if (type != null) {
      return new AfterCondition(type);
    } else {
      return new AfterCondition(list);
    }
  }

  public static AbstractRutaCondition createConditionBefore(TypeExpression type,
          TypeListExpression list, RutaBlock env) {
    if (type != null) {
      return new BeforeCondition(type);
    } else {
      return new BeforeCondition(list);
    }
  }

  public static AbstractRutaCondition createConditionEndsWith(TypeExpression type,
          TypeListExpression list, RutaBlock env) {
    if (type != null) {
      return new EndsWithCondition(type);
    } else {
      return new EndsWithCondition(list);
    }
  }

  public static AbstractRutaCondition createConditionStartsWith(TypeExpression type,
          TypeListExpression list, RutaBlock env) {
    if (type != null) {
      return new StartsWithCondition(type);
    } else {
      return new StartsWithCondition(list);
    }
  }

  public static AbstractRutaCondition createConditionPartOfNeq(TypeExpression type,
          TypeListExpression list, RutaBlock env) {
    if (type != null) {
      return new PartOfNeqCondition(type);
    } else {
      return new PartOfNeqCondition(list);
    }
  }

  public static AbstractRutaCondition createConditionSize(ListExpression<?> list,
          NumberExpression min, NumberExpression max, Token var, RutaBlock env) {
    return new SizeCondition(list, min, max, var == null ? null : var.getText());
  }

  public static AbstractRutaCondition createConditionInList(StringListExpression list,
          NumberExpression dist, BooleanExpression rel, RutaBlock env) {
    return new InListCondition(list, dist, rel);
  }

  public static AbstractRutaCondition createConditionCount(ListExpression<Object> type,
          RutaExpression a, NumberExpression min, NumberExpression max, Token var, RutaBlock env) {
    return new CountCondition(type, a, min, max, var == null ? null : var.getText());
  }

  public static AbstractRutaCondition createConditionContains(ListExpression list,
          RutaExpression a, NumberExpression min, NumberExpression max, BooleanExpression percent,
          RutaBlock env) {
    return new ContainsCondition(list, a, min, max, percent);
  }

  public static AbstractRutaCondition createImplicitCondition(RutaExpression expr) {
    return new ImplicitCondition(expr);
  }

}
