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

package org.apache.uima.textmarker.verbalizer;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.textmarker.condition.AbstractTextMarkerCondition;
import org.apache.uima.textmarker.condition.AfterCondition;
import org.apache.uima.textmarker.condition.AndCondition;
import org.apache.uima.textmarker.condition.BeforeCondition;
import org.apache.uima.textmarker.condition.ContainsCondition;
import org.apache.uima.textmarker.condition.ContextCountCondition;
import org.apache.uima.textmarker.condition.CountCondition;
import org.apache.uima.textmarker.condition.CurrentCountCondition;
import org.apache.uima.textmarker.condition.EndsWithCondition;
import org.apache.uima.textmarker.condition.FeatureCondition;
import org.apache.uima.textmarker.condition.IfCondition;
import org.apache.uima.textmarker.condition.InListCondition;
import org.apache.uima.textmarker.condition.IsCondition;
import org.apache.uima.textmarker.condition.LastCondition;
import org.apache.uima.textmarker.condition.MOfNCondition;
import org.apache.uima.textmarker.condition.NearCondition;
import org.apache.uima.textmarker.condition.NotCondition;
import org.apache.uima.textmarker.condition.OrCondition;
import org.apache.uima.textmarker.condition.ParseCondition;
import org.apache.uima.textmarker.condition.PartOfCondition;
import org.apache.uima.textmarker.condition.PartOfNeqCondition;
import org.apache.uima.textmarker.condition.PositionCondition;
import org.apache.uima.textmarker.condition.RegExpCondition;
import org.apache.uima.textmarker.condition.ScoreCondition;
import org.apache.uima.textmarker.condition.SizeCondition;
import org.apache.uima.textmarker.condition.StartsWithCondition;
import org.apache.uima.textmarker.condition.TotalCountCondition;
import org.apache.uima.textmarker.condition.VoteCondition;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.bool.SimpleBooleanExpression;
import org.apache.uima.textmarker.expression.list.SimpleStringListExpression;
import org.apache.uima.textmarker.expression.list.SimpleTypeListExpression;
import org.apache.uima.textmarker.expression.list.StringListExpression;
import org.apache.uima.textmarker.expression.list.TypeListExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.number.ReferenceNumberExpression;
import org.apache.uima.textmarker.expression.number.SimpleNumberExpression;
import org.apache.uima.textmarker.expression.string.SimpleStringExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.type.ReferenceTypeExpression;
import org.apache.uima.textmarker.expression.type.SimpleTypeExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.verbalize.TextMarkerVerbalizer;
import org.junit.Test;

public class ConditionVerbalizerTest {

  @Test
  public void test() {
    TextMarkerVerbalizer v = new TextMarkerVerbalizer();
    List<TypeExpression> typeExprList = new ArrayList<TypeExpression>();
    List<StringExpression> stringExprList = new ArrayList<StringExpression>();
    String var = "anyVar";
    TypeExpression typeExpr1 = new SimpleTypeExpression("Type1");
    TypeExpression typeExpr2 = new ReferenceTypeExpression("typeVar");
    typeExprList.add(typeExpr1);
    typeExprList.add(typeExpr2);
    NumberExpression numExpr1 = new SimpleNumberExpression(4);
    NumberExpression numExpr2 = new ReferenceNumberExpression("numVar");
    BooleanExpression boolExpr1 = new SimpleBooleanExpression(true);
    StringExpression stringExpr = new SimpleStringExpression("string");
    stringExprList.add(stringExpr);
    TypeListExpression typeListExpr = new SimpleTypeListExpression(typeExprList);
    StringListExpression stringListExpr = new SimpleStringListExpression(stringExprList);
    AbstractTextMarkerCondition c = null;
    String s = null;

    // AFTER
    c = new AfterCondition(typeExpr1);
    s = v.verbalize(c);
    assertEquals("AFTER(Type1)", s);

    c = new AfterCondition(typeExpr2);
    s = v.verbalize(c);
    assertEquals("AFTER(typeVar)", s);

    c = new AfterCondition(typeListExpr);
    s = v.verbalize(c);
    assertEquals("AFTER({Type1, typeVar})", s);

    // AND
    List<AbstractTextMarkerCondition> conds = new ArrayList<AbstractTextMarkerCondition>();
    AbstractTextMarkerCondition c1 = new AfterCondition(typeExpr2);
    AbstractTextMarkerCondition c2 = new AfterCondition(typeListExpr);
    conds.add(c1);
    conds.add(c2);
    c = new AndCondition(conds);
    s = v.verbalize(c);
    assertEquals("AND(AFTER(typeVar), AFTER({Type1, typeVar}))", s);

    // BEFORE
    c = new BeforeCondition(typeExpr1);
    s = v.verbalize(c);
    assertEquals("BEFORE(Type1)", s);

    c = new BeforeCondition(typeExpr2);
    s = v.verbalize(c);
    assertEquals("BEFORE(typeVar)", s);

    c = new BeforeCondition(typeListExpr);
    s = v.verbalize(c);
    assertEquals("BEFORE({Type1, typeVar})", s);

    // CONTAINS
    c = new ContainsCondition(typeExpr1, numExpr1, numExpr2, boolExpr1);
    s = v.verbalize(c);
    assertEquals("CONTAINS(Type1, 4, numVar, true)", s);

    c = new ContainsCondition(typeExpr2, null, null, null);
    s = v.verbalize(c);
    assertEquals("CONTAINS(typeVar)", s);

    c = new ContainsCondition(typeListExpr, typeExpr2, null, null, null);
    s = v.verbalize(c);
    assertEquals("CONTAINS({Type1, typeVar}, typeVar)", s);

    // CONTEXTCOUNT
    c = new ContextCountCondition(typeExpr1, numExpr1, numExpr2, var);
    s = v.verbalize(c);
    assertEquals("CONTEXTCOUNT(Type1, 4, numVar, anyVar)", s);

    // COUNT
    c = new CountCondition(typeExpr1, numExpr1, numExpr2, var);
    s = v.verbalize(c);
    assertEquals("COUNT(Type1, 4, numVar, anyVar)", s);

    c = new CountCondition(typeListExpr, typeExpr2, null, null, null);
    s = v.verbalize(c);
    assertEquals("COUNT({Type1, typeVar}, typeVar)", s);

    // CURRENTCOUNT
    c = new CurrentCountCondition(typeExpr1, numExpr1, numExpr2, var);
    s = v.verbalize(c);
    assertEquals("CURRENTCOUNT(Type1, 4, numVar, anyVar)", s);

    // ENDSWITH
    c = new EndsWithCondition(typeExpr1);
    s = v.verbalize(c);
    assertEquals("ENDSWITH(Type1)", s);

    c = new EndsWithCondition(typeListExpr);
    s = v.verbalize(c);
    assertEquals("ENDSWITH({Type1, typeVar})", s);

    // FEATURE
    c = new FeatureCondition(stringExpr, stringExpr);
    s = v.verbalize(c);
    assertEquals("FEATURE(\"string\", \"string\")", s);

    // IF
    c = new IfCondition(boolExpr1);
    s = v.verbalize(c);
    assertEquals("IF(true)", s);

    // INLIST
    c = new InListCondition(stringListExpr, numExpr1, boolExpr1);
    s = v.verbalize(c);
    assertEquals("INLIST({\"string\"}, 4, true)", s);

    // IS
    c = new IsCondition(typeExpr1);
    s = v.verbalize(c);
    assertEquals("IS(Type1)", s);

    c = new IsCondition(typeListExpr);
    s = v.verbalize(c);
    assertEquals("IS({Type1, typeVar})", s);

    // LAST
    c = new LastCondition(typeExpr1);
    s = v.verbalize(c);
    assertEquals("LAST(Type1)", s);

    // MOFN
    c = new MOfNCondition(conds, numExpr1, numExpr2);
    s = v.verbalize(c);
    assertEquals("MOFN(4, numVar, AFTER(typeVar), AFTER({Type1, typeVar}))", s);

    // NEAR
    c = new NearCondition(typeExpr1, numExpr1, numExpr2, boolExpr1, boolExpr1);
    s = v.verbalize(c);
    assertEquals("NEAR(Type1, 4, numVar, true, true)", s);

    // NOT
    c = new NotCondition(c1);
    s = v.verbalize(c);
    assertEquals("-AFTER(typeVar)", s);

    // OR
    c = new OrCondition(conds);
    s = v.verbalize(c);
    assertEquals("OR(AFTER(typeVar), AFTER({Type1, typeVar}))", s);

    // PARSE
    c = new ParseCondition(var);
    s = v.verbalize(c);
    assertEquals("PARSE(anyVar)", s);

    // PARTOF
    c = new PartOfCondition(typeExpr1);
    s = v.verbalize(c);
    assertEquals("PARTOF(Type1)", s);

    c = new PartOfCondition(typeListExpr);
    s = v.verbalize(c);
    assertEquals("PARTOF({Type1, typeVar})", s);

    // PARTOFNEQ
    c = new PartOfNeqCondition(typeExpr1);
    s = v.verbalize(c);
    assertEquals("PARTOFNEQ(Type1)", s);

    c = new PartOfNeqCondition(typeListExpr);
    s = v.verbalize(c);
    assertEquals("PARTOFNEQ({Type1, typeVar})", s);

    // POSITION
    c = new PositionCondition(typeExpr1, numExpr1, boolExpr1);
    s = v.verbalize(c);
    assertEquals("POSITION(Type1, 4, true)", s);

    // REGEXP
    c = new RegExpCondition(stringExpr, boolExpr1);
    s = v.verbalize(c);
    assertEquals("REGEXP(\"string\", true)", s);

    c = new RegExpCondition(var, stringExpr, boolExpr1);
    s = v.verbalize(c);
    assertEquals("REGEXP(anyVar, \"string\", true)", s);

    // SCORE
    c = new ScoreCondition(numExpr1, numExpr2, var);
    s = v.verbalize(c);
    assertEquals("SCORE(4, numVar, anyVar)", s);

    // SIZE
    c = new SizeCondition(typeListExpr, numExpr1, numExpr2, var);
    s = v.verbalize(c);
    assertEquals("SIZE({Type1, typeVar}, 4, numVar, anyVar)", s);

    // STARTSWITH
    c = new StartsWithCondition(typeExpr1);
    s = v.verbalize(c);
    assertEquals("STARTSWITH(Type1)", s);

    c = new StartsWithCondition(typeListExpr);
    s = v.verbalize(c);
    assertEquals("STARTSWITH({Type1, typeVar})", s);

    // TOTALCOUNT
    c = new TotalCountCondition(typeExpr1, numExpr1, numExpr2, var);
    s = v.verbalize(c);
    assertEquals("TOTALCOUNT(Type1, 4, numVar, anyVar)", s);

    // VOTE
    c = new VoteCondition(typeExpr1, typeExpr2);
    s = v.verbalize(c);
    assertEquals("VOTE(Type1, typeVar)", s);

  }
}
