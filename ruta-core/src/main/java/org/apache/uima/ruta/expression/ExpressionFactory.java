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

package org.apache.uima.ruta.expression;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.Token;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.expression.bool.BooleanFeatureExpression;
import org.apache.uima.ruta.expression.bool.BooleanNumberExpression;
import org.apache.uima.ruta.expression.bool.BooleanTypeExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.bool.ReferenceBooleanExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanFunction;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.feature.GenericFeatureExpression;
import org.apache.uima.ruta.expression.feature.SimpleFeatureExpression;
import org.apache.uima.ruta.expression.list.BooleanListExpression;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.list.NumberListExpression;
import org.apache.uima.ruta.expression.list.ReferenceBooleanListExpression;
import org.apache.uima.ruta.expression.list.ReferenceNumberListExpression;
import org.apache.uima.ruta.expression.list.ReferenceStringListExpression;
import org.apache.uima.ruta.expression.list.ReferenceTypeListExpression;
import org.apache.uima.ruta.expression.list.SimpleBooleanListExpression;
import org.apache.uima.ruta.expression.list.SimpleNumberListExpression;
import org.apache.uima.ruta.expression.list.SimpleStringListExpression;
import org.apache.uima.ruta.expression.list.SimpleTypeListExpression;
import org.apache.uima.ruta.expression.list.StringListExpression;
import org.apache.uima.ruta.expression.list.TypeListExpression;
import org.apache.uima.ruta.expression.list.UntypedListExpression;
import org.apache.uima.ruta.expression.number.ComposedNumberExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.number.NegativeNumberExpression;
import org.apache.uima.ruta.expression.number.NumberFeatureExpression;
import org.apache.uima.ruta.expression.number.ReferenceNumberExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberExpression;
import org.apache.uima.ruta.expression.resource.ExternalWordListExpression;
import org.apache.uima.ruta.expression.resource.ExternalWordTableExpression;
import org.apache.uima.ruta.expression.resource.LiteralWordListExpression;
import org.apache.uima.ruta.expression.resource.LiteralWordTableExpression;
import org.apache.uima.ruta.expression.resource.ReferenceWordListExpression;
import org.apache.uima.ruta.expression.resource.ReferenceWordTableExpression;
import org.apache.uima.ruta.expression.resource.WordListExpression;
import org.apache.uima.ruta.expression.resource.WordTableExpression;
import org.apache.uima.ruta.expression.string.AbstractStringExpression;
import org.apache.uima.ruta.expression.string.ComposedStringExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.ReferenceStringExpression;
import org.apache.uima.ruta.expression.string.SimpleStringExpression;
import org.apache.uima.ruta.expression.string.StringFeatureExpression;
import org.apache.uima.ruta.expression.type.ReferenceTypeExpression;
import org.apache.uima.ruta.expression.type.SimpleTypeExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;

public class ExpressionFactory {

  private ExpressionFactory() {
  }

  public static INumberExpression createIntegerExpression(Token number, Token minus) {
    Integer valueOf = Integer.valueOf(number.getText());
    SimpleNumberExpression simpleNumberExpression = new SimpleNumberExpression(valueOf);
    if (minus != null) {
      return new NegativeNumberExpression(simpleNumberExpression);
    } else {
      return simpleNumberExpression;
    }
  }

  public static INumberExpression createDoubleExpression(Token number, Token minus) {
    Double valueOf = Double.valueOf(number.getText());
    SimpleNumberExpression simpleNumberExpression = new SimpleNumberExpression(valueOf);
    if (minus != null) {
      return new NegativeNumberExpression(simpleNumberExpression);
    } else {
      return simpleNumberExpression;
    }
  }

  public static INumberExpression createReferenceNumberExpression(Token var, Token minus) {
    ReferenceNumberExpression simpleNumberExpression = new ReferenceNumberExpression(var.getText());
    if (minus != null) {
      return new NegativeNumberExpression(simpleNumberExpression);
    } else {
      return simpleNumberExpression;
    }
  }

  public static INumberExpression createComposedNumberExpression(List<INumberExpression> expressions,
          List<Token> opTokens) {
    List<String> ops = new ArrayList<String>();
    for (Token token : opTokens) {
      ops.add(token.getText());
    }
    return new ComposedNumberExpression(expressions, ops);
  }

  public static INumberExpression createComposedNumberExpression(INumberExpression expression,
          Token opToken) {
    List<String> ops = new ArrayList<String>();
    List<INumberExpression> exprList = new ArrayList<INumberExpression>();
    ops.add(opToken.getText());
    exprList.add(expression);
    return new ComposedNumberExpression(exprList, ops);
  }
  
  public static INumberExpression createComposedNumberExpression(INumberExpression expression1,
          Token opToken, INumberExpression expression2) {
    List<String> ops = new ArrayList<String>();
    List<INumberExpression> exprList = new ArrayList<INumberExpression>();
    ops.add(opToken.getText());
    exprList.add(expression1);
    exprList.add(expression2);
    return new ComposedNumberExpression(exprList, ops);
  }

  public static AbstractStringExpression createSimpleStringExpression(Token token) {
    String text = token.getText();
    String substring = text.substring(1, text.length() - 1);
    return new SimpleStringExpression(substring);
  }

  public static IStringExpression createComposedStringExpression(List<IStringExpression> expressions) {
    return new ComposedStringExpression(expressions);
  }

  public static AbstractStringExpression createReferenceStringExpression(Token var) {
    return new ReferenceStringExpression(var.getText());
  }

  public static IBooleanExpression createBooleanNumberExpression(INumberExpression e1, Token op,
          INumberExpression e2) {
    return new BooleanNumberExpression(e1, op.getText(), e2);
  }

  public static IBooleanExpression createSimpleBooleanExpression(Token v) {
    return new SimpleBooleanExpression(Boolean.valueOf(v.getText()));
  }

  public static IBooleanExpression createReferenceBooleanExpression(Token id) {
    return new ReferenceBooleanExpression(id.getText());
  }

  public static TypeExpression createSimpleTypeExpression(Token typeToken, RutaBlock parent) {
    String typeString = typeToken == null ? "uima.tcas.DocumentAnnotation" : typeToken.getText();
    return new SimpleTypeExpression(typeString);
  }

  public static TypeExpression createReferenceTypeExpression(Token varToken) {
    String varString = varToken == null ? "" : varToken.getText();
    return new ReferenceTypeExpression(varString);
  }

  public static TypeExpression createSimpleTypeExpression(String typeString, RutaBlock parent) {
    return new SimpleTypeExpression(typeString);
  }

  public static IBooleanExpression createBooleanFunction(Token op, IBooleanExpression e1,
          IBooleanExpression e2) {
    return new SimpleBooleanFunction(op.getText(), e1, e2);
  }

  public static WordTableExpression createReferenceWordTableExpression(Token id) {
    return new ReferenceWordTableExpression(id.getText());
  }

  public static WordListExpression createReferenceWordListExpression(Token id) {
    return new ReferenceWordListExpression(id.getText());
  }

  public static WordListExpression createLiteralWordListExpression(Token path) {
    return new LiteralWordListExpression(path.getText());
  }

  public static WordTableExpression createLiteralWordTableExpression(Token path) {
    return new LiteralWordTableExpression(path.getText());
  }

  public static IBooleanExpression createBooleanTypeExpression(TypeExpression e1, Token op,
          TypeExpression e2) {
    return new BooleanTypeExpression(e1, op.getText(), e2);
  }

  public static BooleanListExpression createReferenceBooleanListExpression(Token var) {
    return new ReferenceBooleanListExpression(var.getText());
  }

  public static StringListExpression createReferenceStringListExpression(Token var) {
    return new ReferenceStringListExpression(var.getText());
  }

  public static TypeListExpression createReferenceTypeListExpression(Token var) {
    return new ReferenceTypeListExpression(var.getText());
  }

  public static NumberListExpression createReferenceDoubleListExpression(Token var) {
    return new ReferenceNumberListExpression(var.getText());
  }

  public static NumberListExpression createReferenceIntListExpression(Token var) {
    return new ReferenceNumberListExpression(var.getText());
  }

  public static NumberListExpression createReferenceFloatListExpression(Token var) {
    return new ReferenceNumberListExpression(var.getText());
  }

  public static BooleanListExpression createBooleanListExpression(List<IBooleanExpression> list) {
    return new SimpleBooleanListExpression(list);
  }

  public static NumberListExpression createNumberListExpression(List<INumberExpression> list) {
    return new SimpleNumberListExpression(list);
  }

  public static TypeListExpression createTypeListExpression(List<TypeExpression> list) {
    return new SimpleTypeListExpression(list);
  }

  public static StringListExpression createStringListExpression(List<IStringExpression> list) {
    return new SimpleStringListExpression(list);
  }

  public static FeatureExpression createFeatureExpression(MatchReference mr, RutaBlock env) {
    return new SimpleFeatureExpression(mr);
  }
  
  public static FeatureMatchExpression createFeatureMatchExpression(MatchReference mr, RutaBlock env) {
    return new FeatureMatchExpression(mr, env);
  }

  public static MatchReference createMatchReference(Token refToken, Token opToken,
          IRutaExpression arg) {
    String match = refToken.getText();
    String op = null;
    if (opToken != null) {
      op = opToken.getText();
    }
    return new MatchReference(match, op, arg);
  }

  public static INumberExpression createNumberFeatureExpression(FeatureExpression fe) {
    return new NumberFeatureExpression(fe);
  }

  public static AbstractStringExpression createStringFeatureExpression(FeatureExpression fe) {
    return new StringFeatureExpression(fe);
  }

  public static IBooleanExpression createBooleanFeatureExpression(FeatureExpression fe) {
    return new BooleanFeatureExpression(fe);
  }

  public static GenericFeatureExpression createGenericFeatureExpression(FeatureExpression fe) {
    return new GenericFeatureExpression(fe);
  }

  public static ListExpression<Object> createUntypedListExpression(List<IRutaExpression> list) {
    return new UntypedListExpression(list);
  }

  public static WordListExpression createExternalWordListExpression(Token name,
          List<IStringExpression> args) {
    return new ExternalWordListExpression(name.getText(), args);
  }
  
  public static WordTableExpression createExternalWordTableExpression(Token name,
          List<IStringExpression> args) {
    return new ExternalWordTableExpression(name.getText(), args);
  }

  public static IRutaExpression createNullExpression() {
    return new NullExpression();
  }
  


}
