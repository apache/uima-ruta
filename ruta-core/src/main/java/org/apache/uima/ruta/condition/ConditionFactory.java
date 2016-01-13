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
import java.util.Map;

import org.antlr.runtime.Token;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.resource.WordListExpression;
import org.apache.uima.ruta.expression.string.AbstractStringListExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.AbstractTypeListExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.extensions.RutaParseRuntimeException;

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

  @SuppressWarnings("rawtypes")
  public static AbstractRutaCondition createConditionContains(List<IRutaExpression> args,
          RutaBlock parent) {
    if (args.size() == 1) {
      IRutaExpression arg = args.get(0);
      if (arg instanceof ITypeExpression) {
        return createConditionContains((ITypeExpression) arg, null, null, null, parent);
      }
    } else if (args.size() == 2) {
      IRutaExpression arg1 = args.get(0);
      IRutaExpression arg2 = args.get(1);
      if (arg1 instanceof ListExpression) {
        return createConditionContains((ListExpression) arg1, arg2, null, null, null, parent);
      }
    } else if (args.size() == 3) {
      IRutaExpression arg1 = args.get(0);
      IRutaExpression arg2 = args.get(1);
      IRutaExpression arg3 = args.get(2);
      if (arg1 instanceof ITypeExpression && arg2 instanceof INumberExpression
              && arg3 instanceof INumberExpression) {
        return createConditionContains((ITypeExpression) arg1, (INumberExpression) arg2,
                (INumberExpression) arg3, null, parent);
      }
    } else if (args.size() == 4) {
      IRutaExpression arg1 = args.get(0);
      IRutaExpression arg2 = args.get(1);
      IRutaExpression arg3 = args.get(2);
      IRutaExpression arg4 = args.get(3);
      if (arg1 instanceof ITypeExpression && arg2 instanceof INumberExpression
              && arg3 instanceof INumberExpression && arg4 instanceof IBooleanExpression) {
        return createConditionContains((ITypeExpression) arg1, (INumberExpression) arg2,
                (INumberExpression) arg3, (IBooleanExpression) arg4, parent);
      }
    } else if (args.size() == 5) {
      IRutaExpression arg1 = args.get(0);
      IRutaExpression arg2 = args.get(1);
      IRutaExpression arg3 = args.get(2);
      IRutaExpression arg4 = args.get(3);
      IRutaExpression arg5 = args.get(3);
      if (arg1 instanceof ListExpression && arg3 instanceof INumberExpression
              && arg4 instanceof INumberExpression && arg5 instanceof IBooleanExpression) {
        return createConditionContains((ListExpression) arg1, arg2, (INumberExpression) arg3,
                (INumberExpression) arg4, (IBooleanExpression) arg5, parent);
      }
    }

    return null;
  }

  public static AbstractRutaCondition createConditionContains(ITypeExpression typeExpr,
          INumberExpression min, INumberExpression max, IBooleanExpression percent, RutaBlock parent) {
    return new ContainsCondition(typeExpr, min, max, percent);
  }

  @SuppressWarnings("rawtypes")
  public static AbstractRutaCondition createConditionContains(ListExpression list,
          IRutaExpression a, INumberExpression min, INumberExpression max,
          IBooleanExpression percent, RutaBlock env) {
    return new ContainsCondition(list, a, min, max, percent);
  }

  public static AbstractRutaCondition createConditionContextCount(ITypeExpression typeExpr,
          INumberExpression min, INumberExpression max, Token var, RutaBlock parent) {
    String varString = null;
    if (var != null) {
      varString = var.getText();
    }
    return new ContextCountCondition(typeExpr, min, max, varString);
  }

  public static AbstractRutaCondition createConditionCurrentCount(ITypeExpression typeExpr,
          INumberExpression min, INumberExpression max, Token var, RutaBlock parent) {
    String varString = null;
    if (var != null) {
      varString = var.getText();
    }
    return new CurrentCountCondition(typeExpr, min, max, varString);
  }

  public static AbstractRutaCondition createConditionCount(ITypeExpression typeExpr,
          INumberExpression min, INumberExpression max, Token var, RutaBlock parent) {
    String varString = null;
    if (var != null) {
      varString = var.getText();
    }
    return new CountCondition(typeExpr, min, max, varString);
  }

  public static AbstractRutaCondition createConditionTotalCount(ITypeExpression typeExpr,
          INumberExpression min, INumberExpression max, Token var, RutaBlock parent) {
    String varString = null;
    if (var != null) {
      varString = var.getText();
    }
    return new TotalCountCondition(typeExpr, min, max, varString);
  }

  public static AbstractRutaCondition createConditionInList(WordListExpression listExpr,
          IStringExpression arg, RutaBlock parent) {
    return new InListCondition(listExpr, arg);
  }

  public static AbstractRutaCondition createConditionMOfN(List<AbstractRutaCondition> conds,
          INumberExpression min, INumberExpression max, RutaBlock parent) {
    return new MOfNCondition(conds, min, max);
  }

  public static AbstractRutaCondition createConditionNear(ITypeExpression typeExpr,
          INumberExpression min, INumberExpression max, IBooleanExpression direction,
          IBooleanExpression filtered, RutaBlock parent) {
    return new NearCondition(typeExpr, min, max, direction, filtered);
  }

  public static AbstractRutaCondition createConditionPartOf(ITypeExpression type,
          AbstractTypeListExpression list, RutaBlock parent) {
    if (type != null) {
      return new PartOfCondition(type);
    } else {
      return new PartOfCondition(list);
    }
  }

  public static AbstractRutaCondition createConditionPosition(ITypeExpression typeExpr,
          INumberExpression pos, IBooleanExpression rel, RutaBlock parent) {
    return new PositionCondition(typeExpr, pos, rel);
  }

  public static AbstractRutaCondition createConditionRegExp(IStringExpression patternExpr,
          IBooleanExpression ignoreCase, RutaBlock parent) {
    return new RegExpCondition(patternExpr, ignoreCase);
  }

  public static AbstractRutaCondition createConditionRegExp(IStringExpression v,
          IStringExpression patternExpr, IBooleanExpression ignoreCase, RutaBlock parent) {
    return new RegExpCondition(v, patternExpr, ignoreCase);
  }

  public static AbstractRutaCondition createConditionScore(INumberExpression min,
          INumberExpression max, Token var, RutaBlock parent) {
    String varString = null;
    if (var != null) {
      varString = var.getText();
    }
    return new ScoreCondition(min, max, varString);
  }

  public static AbstractRutaCondition createConditionVote(ITypeExpression type1Expr,
          ITypeExpression type2Expr, RutaBlock parent) {
    return new VoteCondition(type1Expr, type2Expr);
  }

  public static AbstractRutaCondition createConditionLast(ITypeExpression typeExpr, RutaBlock parent) {
    return new LastCondition(typeExpr);
  }

  public static AbstractRutaCondition createConditionIf(IBooleanExpression e, RutaBlock parent) {
    return new IfCondition(e);
  }

  public static AbstractRutaCondition createConditionFeature(IStringExpression se, Object v,
          RutaBlock parent) {
    if (v instanceof INumberExpression) {
      return new FeatureCondition(se, (INumberExpression) v);
    } else if (v instanceof IBooleanExpression) {
      return new FeatureCondition(se, (IBooleanExpression) v);
    } else if (v instanceof IStringExpression) {
      return new FeatureCondition(se, (IStringExpression) v);
    }
    return null;
  }

  public static AbstractRutaCondition createConditionParse(Token id, IStringExpression localeExpr,
          RutaBlock env) {
    String var = id == null ? "" : id.getText();
    return new ParseCondition(var, localeExpr);
  }

  public static AbstractRutaCondition createConditionVariable(Token id) {
    return new VariableCondition(id.getText());
  }

  public static AbstractRutaCondition createConditionIs(ITypeExpression type,
          AbstractTypeListExpression list, RutaBlock env) {
    if (type != null) {
      return new IsCondition(type);
    } else {
      return new IsCondition(list);
    }
  }

  public static AbstractRutaCondition createConditionAfter(ITypeExpression type,
          AbstractTypeListExpression list, RutaBlock env) {
    if (type != null) {
      return new AfterCondition(type);
    } else {
      return new AfterCondition(list);
    }
  }

  public static AbstractRutaCondition createConditionBefore(ITypeExpression type,
          AbstractTypeListExpression list, RutaBlock env) {
    if (type != null) {
      return new BeforeCondition(type);
    } else {
      return new BeforeCondition(list);
    }
  }

  public static AbstractRutaCondition createConditionEndsWith(ITypeExpression type,
          AbstractTypeListExpression list, RutaBlock env) {
    if (type != null) {
      return new EndsWithCondition(type);
    } else {
      return new EndsWithCondition(list);
    }
  }

  public static AbstractRutaCondition createConditionStartsWith(ITypeExpression type,
          AbstractTypeListExpression list, RutaBlock env) {
    if (type != null) {
      return new StartsWithCondition(type);
    } else {
      return new StartsWithCondition(list);
    }
  }

  public static AbstractRutaCondition createConditionPartOfNeq(ITypeExpression type,
          AbstractTypeListExpression list, RutaBlock env) {
    if (type != null) {
      return new PartOfNeqCondition(type);
    } else {
      return new PartOfNeqCondition(list);
    }
  }

  public static AbstractRutaCondition createConditionSize(ListExpression<?> list,
          INumberExpression min, INumberExpression max, Token var, RutaBlock env) {
    return new SizeCondition(list, min, max, var == null ? null : var.getText());
  }

  public static AbstractRutaCondition createConditionInList(AbstractStringListExpression list,
          IStringExpression arg, RutaBlock env) {
    return new InListCondition(list, arg);
  }

  public static AbstractRutaCondition createConditionCount(ListExpression<Object> type,
          IRutaExpression a, INumberExpression min, INumberExpression max, Token var, RutaBlock env) {
    return new CountCondition(type, a, min, max, var == null ? null : var.getText());
  }

  public static AbstractRutaCondition createImplicitCondition(IRutaExpression expr) {
    return new ImplicitCondition(expr);
  }

  public static AbstractRutaCondition createMacroCondition(Token id, List<IRutaExpression> args, RutaBlock env) {
    String name = id.getText();
    Pair<Map<String, String>, List<AbstractRutaCondition>> macroConditionDefinition = env
            .getEnvironment().getMacroCondition(name);
    if (macroConditionDefinition == null) {
      return null;
    }
    Map<String, String> definition = macroConditionDefinition.getKey();
    List<AbstractRutaCondition> conditions = macroConditionDefinition.getValue();
    if (definition.size() != args.size()) {
      throw new RutaParseRuntimeException("Arguments of macro action '" + name
              + "' do not match its definition: " + definition.values());
    }

    return new MacroCondition(name, definition, conditions, args);
  }

}
