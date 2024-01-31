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
import java.util.Arrays;
import java.util.List;

import org.apache.uima.ruta.expression.GenericComposedExpression;
import org.apache.uima.ruta.expression.MatchReference;
import org.apache.uima.ruta.expression.annotation.AnnotationLabelExpression;
import org.apache.uima.ruta.expression.annotation.AnnotationVariableExpression;
import org.apache.uima.ruta.expression.bool.AbstractBooleanListExpression;
import org.apache.uima.ruta.expression.bool.BooleanListVariableExpression;
import org.apache.uima.ruta.expression.bool.BooleanNumberExpression;
import org.apache.uima.ruta.expression.bool.BooleanTypeExpression;
import org.apache.uima.ruta.expression.bool.BooleanVariableExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanListExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.feature.GenericFeatureExpression;
import org.apache.uima.ruta.expression.feature.SimpleFeatureExpression;
import org.apache.uima.ruta.expression.number.AbstractNumberListExpression;
import org.apache.uima.ruta.expression.number.ComposedNumberExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.number.NumberListVariableExpression;
import org.apache.uima.ruta.expression.number.NumberVariableExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberListExpression;
import org.apache.uima.ruta.expression.string.AbstractStringExpression;
import org.apache.uima.ruta.expression.string.AbstractStringListExpression;
import org.apache.uima.ruta.expression.string.ComposedStringExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.SimpleStringExpression;
import org.apache.uima.ruta.expression.string.SimpleStringListExpression;
import org.apache.uima.ruta.expression.string.StringListVariableExpression;
import org.apache.uima.ruta.expression.string.StringVariableExpression;
import org.apache.uima.ruta.expression.type.AbstractTypeListExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.expression.type.SimpleTypeExpression;
import org.apache.uima.ruta.expression.type.SimpleTypeListExpression;
import org.apache.uima.ruta.expression.type.TypeListVariableExpression;
import org.apache.uima.ruta.expression.type.TypeVariableExpression;
import org.apache.uima.ruta.verbalize.RutaVerbalizer;
import org.junit.jupiter.api.Test;

public class ExpressionVerbalizerTest {

  @Test
  public void test() {
    RutaVerbalizer v = new RutaVerbalizer();

    String s = null;
    String var = "anyVar";
    ITypeExpression typeExpr1 = new SimpleTypeExpression("Type1");
    ITypeExpression typeExpr2 = new TypeVariableExpression("typeVar");

    List<INumberExpression> numExprList1 = new ArrayList<>();
    List<INumberExpression> numExprList2 = new ArrayList<>();
    List<String> opList1 = new ArrayList<>();
    List<String> opList2 = new ArrayList<>();
    INumberExpression numExpr1 = new SimpleNumberExpression(4);
    INumberExpression numExpr2 = new NumberVariableExpression("numVar");
    INumberExpression numExpr3 = new NumberVariableExpression("4.9");
    INumberExpression numExpr4 = new NumberVariableExpression("-4");
    numExprList1.add(numExpr1);
    numExprList1.add(numExpr2);
    opList1.add("+");
    opList2.add("*");
    INumberExpression numExpr5 = new ComposedNumberExpression(numExprList1, opList1);
    numExprList2.add(numExpr3);
    numExprList2.add(numExpr5);
    INumberExpression numExpr6 = new ComposedNumberExpression(numExprList2, opList2);

    s = v.verbalize(numExpr1);
    assertThat(s).isEqualTo("4");
    s = v.verbalize(numExpr2);
    assertThat(s).isEqualTo("numVar");
    s = v.verbalize(numExpr3);
    assertThat(s).isEqualTo("4.9");
    s = v.verbalize(numExpr4);
    assertThat(s).isEqualTo("-4");
    s = v.verbalize(numExpr5);
    assertThat(s).isEqualTo("4 + numVar");
    s = v.verbalize(numExpr6);
    assertThat(s).isEqualTo("4.9 * 4 + numVar");

    IBooleanExpression boolExpr1 = new SimpleBooleanExpression(true);
    IBooleanExpression boolExpr2 = new SimpleBooleanExpression(false);
    IBooleanExpression boolExpr3 = new BooleanVariableExpression(var);
    IBooleanExpression boolExpr4 = new BooleanNumberExpression(numExpr1, "==", numExpr2);
    IBooleanExpression boolExpr5 = new BooleanNumberExpression(numExpr1, "!=", numExpr2);
    IBooleanExpression boolExpr6 = new BooleanNumberExpression(numExpr1, "<=", numExpr2);
    IBooleanExpression boolExpr7 = new BooleanNumberExpression(numExpr1, ">=", numExpr2);
    IBooleanExpression boolExpr8 = new BooleanNumberExpression(numExpr1, "<", numExpr2);
    IBooleanExpression boolExpr9 = new BooleanNumberExpression(numExpr1, ">", numExpr2);
    IBooleanExpression boolExpr10 = new BooleanTypeExpression(typeExpr1, "==", typeExpr2);
    IBooleanExpression boolExpr11 = new BooleanTypeExpression(typeExpr1, "!=", typeExpr2);

    s = v.verbalize(boolExpr1);
    assertThat(s).isEqualTo("true");
    s = v.verbalize(boolExpr2);
    assertThat(s).isEqualTo("false");
    s = v.verbalize(boolExpr3);
    assertThat(s).isEqualTo("anyVar");
    s = v.verbalize(boolExpr4);
    assertThat(s).isEqualTo("4 == numVar");
    s = v.verbalize(boolExpr5);
    assertThat(s).isEqualTo("4 != numVar");
    s = v.verbalize(boolExpr6);
    assertThat(s).isEqualTo("4 <= numVar");
    s = v.verbalize(boolExpr7);
    assertThat(s).isEqualTo("4 >= numVar");
    s = v.verbalize(boolExpr8);
    assertThat(s).isEqualTo("4 < numVar");
    s = v.verbalize(boolExpr9);
    assertThat(s).isEqualTo("4 > numVar");
    s = v.verbalize(boolExpr10);
    assertThat(s).isEqualTo("Type1 == typeVar");
    s = v.verbalize(boolExpr11);
    assertThat(s).isEqualTo("Type1 != typeVar");

    List<IStringExpression> stringExprList = new ArrayList<>();
    AbstractStringExpression stringExpr1 = new SimpleStringExpression("string");
    AbstractStringExpression stringExpr2 = new StringVariableExpression(var);
    stringExprList.add(stringExpr1);
    stringExprList.add(stringExpr2);
    AbstractStringExpression stringExpr3 = new ComposedStringExpression(stringExprList);

    s = v.verbalize(stringExpr1);
    assertThat(s).isEqualTo("\"string\"");
    s = v.verbalize(stringExpr2);
    assertThat(s).isEqualTo("anyVar");
    s = v.verbalize(stringExpr3);
    assertThat(s).isEqualTo("\"string\" + anyVar");

    AbstractStringListExpression sle1 = new SimpleStringListExpression(stringExprList);
    AbstractStringListExpression sle2 = new StringListVariableExpression(var);
    s = v.verbalize(sle1);
    assertThat(s).isEqualTo("{\"string\", anyVar}");
    s = v.verbalize(sle2);
    assertThat(s).isEqualTo("anyVar");

    List<IBooleanExpression> boolExprList = new ArrayList<>();
    boolExprList.add(boolExpr1);
    boolExprList.add(boolExpr3);
    AbstractBooleanListExpression ble1 = new SimpleBooleanListExpression(boolExprList);
    AbstractBooleanListExpression ble2 = new BooleanListVariableExpression(var);
    s = v.verbalize(ble1);
    assertThat(s).isEqualTo("{true, anyVar}");
    s = v.verbalize(ble2);
    assertThat(s).isEqualTo("anyVar");

    List<INumberExpression> numExprList = new ArrayList<>();
    numExprList.add(numExpr1);
    numExprList.add(numExpr3);
    AbstractNumberListExpression nle1 = new SimpleNumberListExpression(numExprList);
    AbstractNumberListExpression nle2 = new NumberListVariableExpression(var);
    s = v.verbalize(nle1);
    assertThat(s).isEqualTo("{4, 4.9}");
    s = v.verbalize(nle2);
    assertThat(s).isEqualTo("anyVar");

    List<ITypeExpression> typeExprList = new ArrayList<>();
    typeExprList.add(typeExpr1);
    typeExprList.add(typeExpr2);
    AbstractTypeListExpression tle1 = new SimpleTypeListExpression(typeExprList);
    AbstractTypeListExpression tle2 = new TypeListVariableExpression(var);
    s = v.verbalize(tle1);
    assertThat(s).isEqualTo("{Type1, typeVar}");
    s = v.verbalize(tle2);
    assertThat(s).isEqualTo("anyVar");

  }

  @Test
  public void testAnnotationExpression() {
    RutaVerbalizer v = new RutaVerbalizer();
    assertThat(v.verbalize(new AnnotationLabelExpression("l"))).isEqualTo("l");
    assertThat(v.verbalize(new AnnotationVariableExpression("l"))).isEqualTo("l");
  }

  @Test
  public void testGenericFeatureExpression() {
    RutaVerbalizer v = new RutaVerbalizer();
    assertThat(v.verbalize(
            new GenericFeatureExpression(new SimpleFeatureExpression(new MatchReference("abc.d")))))
                    .isEqualTo("abc.d");
    assertThat(v.verbalize(
            new GenericFeatureExpression(new FeatureMatchExpression(new MatchReference("abc.d"),
                    "==", new SimpleStringExpression("y"))))).isEqualTo("abc.d");
    assertThat(v.verbalize(new GenericFeatureExpression(null))).isEqualTo("");
  }

  @Test
  public void testGenericComposedExpression() {
    RutaVerbalizer v = new RutaVerbalizer();
    assertThat(v.verbalize(new GenericComposedExpression(
            Arrays.asList(new SimpleStringExpression("a"), new SimpleStringExpression("b")))))
                    .isEqualTo("\"a\"+\"b\"");
    assertThat(v.verbalize(new GenericComposedExpression(
            Arrays.asList(new SimpleNumberExpression(1), new SimpleNumberExpression(2)))))
                    .isEqualTo("1+2");
    assertThat(v.verbalize(new GenericComposedExpression(
            Arrays.asList(new SimpleStringExpression("a"), new SimpleNumberExpression(2)))))
                    .isEqualTo("\"a\"+2");
    assertThat(v.verbalize(new GenericComposedExpression(
            Arrays.asList(new SimpleFeatureExpression(new MatchReference("abc.d")),
                    new AnnotationVariableExpression("l"))))).isEqualTo("abc.d+l");
    assertThat(v.verbalize(new GenericFeatureExpression(null))).isEqualTo("");
  }

}
