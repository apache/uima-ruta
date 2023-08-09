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

package org.apache.uima.ruta.verbalizer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.ruta.condition.AbstractRutaCondition;
import org.apache.uima.ruta.condition.AfterCondition;
import org.apache.uima.ruta.condition.AndCondition;
import org.apache.uima.ruta.condition.BeforeCondition;
import org.apache.uima.ruta.condition.ContainsCondition;
import org.apache.uima.ruta.condition.ContextCountCondition;
import org.apache.uima.ruta.condition.CountCondition;
import org.apache.uima.ruta.condition.CurrentCountCondition;
import org.apache.uima.ruta.condition.EndsWithCondition;
import org.apache.uima.ruta.condition.FeatureCondition;
import org.apache.uima.ruta.condition.IfCondition;
import org.apache.uima.ruta.condition.InListCondition;
import org.apache.uima.ruta.condition.IsCondition;
import org.apache.uima.ruta.condition.LastCondition;
import org.apache.uima.ruta.condition.MOfNCondition;
import org.apache.uima.ruta.condition.NearCondition;
import org.apache.uima.ruta.condition.NotCondition;
import org.apache.uima.ruta.condition.OrCondition;
import org.apache.uima.ruta.condition.ParseCondition;
import org.apache.uima.ruta.condition.PartOfCondition;
import org.apache.uima.ruta.condition.PartOfNeqCondition;
import org.apache.uima.ruta.condition.PositionCondition;
import org.apache.uima.ruta.condition.RegExpCondition;
import org.apache.uima.ruta.condition.ScoreCondition;
import org.apache.uima.ruta.condition.SizeCondition;
import org.apache.uima.ruta.condition.StartsWithCondition;
import org.apache.uima.ruta.condition.TotalCountCondition;
import org.apache.uima.ruta.condition.VoteCondition;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.number.NumberVariableExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberExpression;
import org.apache.uima.ruta.expression.string.AbstractStringListExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.SimpleStringExpression;
import org.apache.uima.ruta.expression.string.SimpleStringListExpression;
import org.apache.uima.ruta.expression.type.AbstractTypeListExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.expression.type.SimpleTypeExpression;
import org.apache.uima.ruta.expression.type.SimpleTypeListExpression;
import org.apache.uima.ruta.expression.type.TypeVariableExpression;
import org.apache.uima.ruta.verbalize.RutaVerbalizer;
import org.junit.jupiter.api.Test;

public class ConditionVerbalizerTest {

  @Test
  public void test() {
    RutaVerbalizer v = new RutaVerbalizer();
    List<ITypeExpression> typeExprList = new ArrayList<ITypeExpression>();
    List<IStringExpression> stringExprList = new ArrayList<IStringExpression>();
    String var = "anyVar";
    IStringExpression varExpr = new NumberVariableExpression(var);
    ITypeExpression typeExpr1 = new SimpleTypeExpression("Type1");
    ITypeExpression typeExpr2 = new TypeVariableExpression("typeVar");
    typeExprList.add(typeExpr1);
    typeExprList.add(typeExpr2);
    INumberExpression numExpr1 = new SimpleNumberExpression(4);
    INumberExpression numExpr2 = new NumberVariableExpression("numVar");
    IBooleanExpression boolExpr1 = new SimpleBooleanExpression(true);
    IStringExpression stringExpr = new SimpleStringExpression("string");
    stringExprList.add(stringExpr);
    AbstractTypeListExpression typeListExpr = new SimpleTypeListExpression(typeExprList);
    AbstractStringListExpression stringListExpr = new SimpleStringListExpression(stringExprList);
    AbstractRutaCondition c = null;
    String s = null;

    // AFTER
    c = new AfterCondition(typeExpr1);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("AFTER(Type1)");

    c = new AfterCondition(typeExpr2);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("AFTER(typeVar)");

    c = new AfterCondition(typeListExpr);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("AFTER({Type1, typeVar})");

    // AND
    List<AbstractRutaCondition> conds = new ArrayList<AbstractRutaCondition>();
    AbstractRutaCondition c1 = new AfterCondition(typeExpr2);
    AbstractRutaCondition c2 = new AfterCondition(typeListExpr);
    conds.add(c1);
    conds.add(c2);
    c = new AndCondition(conds);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("AND(AFTER(typeVar), AFTER({Type1, typeVar}))");

    // BEFORE
    c = new BeforeCondition(typeExpr1);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("BEFORE(Type1)");

    c = new BeforeCondition(typeExpr2);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("BEFORE(typeVar)");

    c = new BeforeCondition(typeListExpr);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("BEFORE({Type1, typeVar})");

    // CONTAINS
    c = new ContainsCondition(typeExpr1, numExpr1, numExpr2, boolExpr1);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("CONTAINS(Type1, 4, numVar, true)");

    c = new ContainsCondition(typeExpr2, null, null, null);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("CONTAINS(typeVar)");

    c = new ContainsCondition(typeListExpr, typeExpr2, null, null, null);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("CONTAINS({Type1, typeVar}, typeVar)");

    // CONTEXTCOUNT
    c = new ContextCountCondition(typeExpr1, numExpr1, numExpr2, var);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("CONTEXTCOUNT(Type1, 4, numVar, anyVar)");

    // COUNT
    c = new CountCondition(typeExpr1, numExpr1, numExpr2, var);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("COUNT(Type1, 4, numVar, anyVar)");

    c = new CountCondition(typeListExpr, typeExpr2, null, null, null);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("COUNT({Type1, typeVar}, typeVar)");

    // CURRENTCOUNT
    c = new CurrentCountCondition(typeExpr1, numExpr1, numExpr2, var);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("CURRENTCOUNT(Type1, 4, numVar, anyVar)");

    // ENDSWITH
    c = new EndsWithCondition(typeExpr1);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("ENDSWITH(Type1)");

    c = new EndsWithCondition(typeListExpr);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("ENDSWITH({Type1, typeVar})");

    // FEATURE
    c = new FeatureCondition(stringExpr, stringExpr);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("FEATURE(\"string\", \"string\")");

    // IF
    c = new IfCondition(boolExpr1);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("IF(true)");

    // INLIST
    c = new InListCondition(stringListExpr, stringExpr);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("INLIST({\"string\"}, \"string\")");

    // IS
    c = new IsCondition(typeExpr1);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("IS(Type1)");

    c = new IsCondition(typeListExpr);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("IS({Type1, typeVar})");

    // LAST
    c = new LastCondition(typeExpr1);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("LAST(Type1)");

    // MOFN
    c = new MOfNCondition(conds, numExpr1, numExpr2);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("MOFN(4, numVar, AFTER(typeVar), AFTER({Type1, typeVar}))");

    // NEAR
    c = new NearCondition(typeExpr1, numExpr1, numExpr2, boolExpr1, boolExpr1);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("NEAR(Type1, 4, numVar, true, true)");

    // NOT
    c = new NotCondition(c1);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("-AFTER(typeVar)");

    // OR
    c = new OrCondition(conds);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("OR(AFTER(typeVar), AFTER({Type1, typeVar}))");

    // PARSE
    c = new ParseCondition(var);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("PARSE(anyVar)");

    // PARTOF
    c = new PartOfCondition(typeExpr1);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("PARTOF(Type1)");

    c = new PartOfCondition(typeListExpr);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("PARTOF({Type1, typeVar})");

    // PARTOFNEQ
    c = new PartOfNeqCondition(typeExpr1);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("PARTOFNEQ(Type1)");

    c = new PartOfNeqCondition(typeListExpr);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("PARTOFNEQ({Type1, typeVar})");

    // POSITION
    c = new PositionCondition(typeExpr1, numExpr1, boolExpr1);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("POSITION(Type1, 4, true)");

    // REGEXP
    c = new RegExpCondition(stringExpr, boolExpr1);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("REGEXP(\"string\", true)");

    c = new RegExpCondition(varExpr, stringExpr, boolExpr1);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("REGEXP(anyVar, \"string\", true)");

    // SCORE
    c = new ScoreCondition(numExpr1, numExpr2, var);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("SCORE(4, numVar, anyVar)");

    // SIZE
    c = new SizeCondition(typeListExpr, numExpr1, numExpr2, var);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("SIZE({Type1, typeVar}, 4, numVar, anyVar)");

    // STARTSWITH
    c = new StartsWithCondition(typeExpr1);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("STARTSWITH(Type1)");

    c = new StartsWithCondition(typeListExpr);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("STARTSWITH({Type1, typeVar})");

    // TOTALCOUNT
    c = new TotalCountCondition(typeExpr1, numExpr1, numExpr2, var);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("TOTALCOUNT(Type1, 4, numVar, anyVar)");

    // VOTE
    c = new VoteCondition(typeExpr1, typeExpr2);
    s = v.verbalize(c);
    assertThat(s).isEqualTo("VOTE(Type1, typeVar)");

  }
}
