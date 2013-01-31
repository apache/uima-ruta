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

package org.apache.uima.textmarker.expression;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.Token;
import org.apache.uima.textmarker.TextMarkerBlock;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.bool.BooleanNumberExpression;
import org.apache.uima.textmarker.expression.bool.BooleanTypeExpression;
import org.apache.uima.textmarker.expression.bool.ReferenceBooleanExpression;
import org.apache.uima.textmarker.expression.bool.SimpleBooleanExpression;
import org.apache.uima.textmarker.expression.bool.SimpleBooleanFunction;
import org.apache.uima.textmarker.expression.list.BooleanListExpression;
import org.apache.uima.textmarker.expression.list.NumberListExpression;
import org.apache.uima.textmarker.expression.list.ReferenceBooleanListExpression;
import org.apache.uima.textmarker.expression.list.ReferenceNumberListExpression;
import org.apache.uima.textmarker.expression.list.ReferenceStringListExpression;
import org.apache.uima.textmarker.expression.list.ReferenceTypeListExpression;
import org.apache.uima.textmarker.expression.list.SimpleBooleanListExpression;
import org.apache.uima.textmarker.expression.list.SimpleNumberListExpression;
import org.apache.uima.textmarker.expression.list.SimpleStringListExpression;
import org.apache.uima.textmarker.expression.list.SimpleTypeListExpression;
import org.apache.uima.textmarker.expression.list.StringListExpression;
import org.apache.uima.textmarker.expression.list.TypeListExpression;
import org.apache.uima.textmarker.expression.number.ComposedNumberExpression;
import org.apache.uima.textmarker.expression.number.NegativeNumberExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.number.ReferenceNumberExpression;
import org.apache.uima.textmarker.expression.number.SimpleNumberExpression;
import org.apache.uima.textmarker.expression.resource.LiteralWordListExpression;
import org.apache.uima.textmarker.expression.resource.LiteralWordTableExpression;
import org.apache.uima.textmarker.expression.resource.ReferenceWordListExpression;
import org.apache.uima.textmarker.expression.resource.ReferenceWordTableExpression;
import org.apache.uima.textmarker.expression.resource.WordListExpression;
import org.apache.uima.textmarker.expression.resource.WordTableExpression;
import org.apache.uima.textmarker.expression.string.ComposedStringExpression;
import org.apache.uima.textmarker.expression.string.ReferenceStringExpression;
import org.apache.uima.textmarker.expression.string.SimpleStringExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.type.ReferenceTypeExpression;
import org.apache.uima.textmarker.expression.type.SimpleTypeExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;

public class ExpressionFactory {

  private ExpressionFactory() {
  }

  public static NumberExpression createIntegerExpression(Token number, Token minus) {
    Integer valueOf = Integer.valueOf(number.getText());
    SimpleNumberExpression simpleNumberExpression = new SimpleNumberExpression(valueOf);
    if (minus != null) {
      return new NegativeNumberExpression(simpleNumberExpression);
    } else {
      return simpleNumberExpression;
    }
  }

  public static NumberExpression createDoubleExpression(Token number, Token minus) {
    Double valueOf = Double.valueOf(number.getText());
    SimpleNumberExpression simpleNumberExpression = new SimpleNumberExpression(valueOf);
    if (minus != null) {
      return new NegativeNumberExpression(simpleNumberExpression);
    } else {
      return simpleNumberExpression;
    }
  }

  public static NumberExpression createReferenceNumberExpression(Token var, Token minus) {
    ReferenceNumberExpression simpleNumberExpression = new ReferenceNumberExpression(var.getText());
    if (minus != null) {
      return new NegativeNumberExpression(simpleNumberExpression);
    } else {
      return simpleNumberExpression;
    }
  }

  public static NumberExpression createComposedNumberExpression(List<NumberExpression> expressions,
          List<Token> opTokens) {
    List<String> ops = new ArrayList<String>();
    for (Token token : opTokens) {
      ops.add(token.getText());
    }
    return new ComposedNumberExpression(expressions, ops);
  }

  public static NumberExpression createComposedNumberExpression(NumberExpression expression,
          Token opToken) {
    List<String> ops = new ArrayList<String>();
    List<NumberExpression> exprList = new ArrayList<NumberExpression>();
    ops.add(opToken.getText());
    exprList.add(expression);
    return new ComposedNumberExpression(exprList, ops);
  }

  public static StringExpression createSimpleStringExpression(Token token) {
    String text = token.getText();
    String substring = text.substring(1, text.length() - 1);
    return new SimpleStringExpression(substring);
  }

  public static StringExpression createComposedStringExpression(List<StringExpression> expressions) {
    return new ComposedStringExpression(expressions);
  }

  public static StringExpression createReferenceStringExpression(Token var) {
    return new ReferenceStringExpression(var.getText());
  }

  public static BooleanExpression createBooleanNumberExpression(NumberExpression e1, Token op,
          NumberExpression e2) {
    return new BooleanNumberExpression(e1, op.getText(), e2);
  }

  public static BooleanExpression createSimpleBooleanExpression(Token v) {
    return new SimpleBooleanExpression(Boolean.valueOf(v.getText()));
  }

  public static BooleanExpression createReferenceBooleanExpression(Token id) {
    return new ReferenceBooleanExpression(id.getText());
  }

  public static TypeExpression createSimpleTypeExpression(Token typeToken, TextMarkerBlock parent) {
    String typeString = typeToken == null ? "uima.tcas.DocumentAnnotation" : typeToken.getText();
    return new SimpleTypeExpression(typeString);
  }

  public static TypeExpression createReferenceTypeExpression(Token varToken) {
    String varString = varToken == null ? "" : varToken.getText();
    return new ReferenceTypeExpression(varString);
  }

  public static TypeExpression createSimpleTypeExpression(String typeString, TextMarkerBlock parent) {
    return new SimpleTypeExpression(typeString);
  }

  public static BooleanExpression createBooleanFunction(Token op, BooleanExpression e1,
          BooleanExpression e2) {
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

  public static BooleanExpression createBooleanTypeExpression(TypeExpression e1, Token op,
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
  
  public static BooleanListExpression createBooleanListExpression(List<BooleanExpression> list) {
    return new SimpleBooleanListExpression(list);
  }

  public static NumberListExpression createNumberListExpression(List<NumberExpression> list) {
    return new SimpleNumberListExpression(list);
  }

  public static TypeListExpression createTypeListExpression(List<TypeExpression> list) {
    return new SimpleTypeListExpression(list);
  }

  public static StringListExpression createStringListExpression(List<StringExpression> list) {
    return new SimpleStringListExpression(list);
  }



}
