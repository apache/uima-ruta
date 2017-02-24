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
import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.TypeUsageInformation;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.condition.AbstractRutaCondition;
import org.apache.uima.ruta.condition.AndCondition;
import org.apache.uima.ruta.condition.ConditionFactory;
import org.apache.uima.ruta.expression.annotation.AbstractAnnotationListExpression;
import org.apache.uima.ruta.expression.annotation.AnnotationAddressExpression;
import org.apache.uima.ruta.expression.annotation.AnnotationFeatureExpression;
import org.apache.uima.ruta.expression.annotation.AnnotationLabelExpression;
import org.apache.uima.ruta.expression.annotation.AnnotationListFeatureExpression;
import org.apache.uima.ruta.expression.annotation.AnnotationListVariableExpression;
import org.apache.uima.ruta.expression.annotation.AnnotationVariableExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationListExpression;
import org.apache.uima.ruta.expression.bool.AbstractBooleanListExpression;
import org.apache.uima.ruta.expression.bool.BooleanFeatureExpression;
import org.apache.uima.ruta.expression.bool.BooleanListFeatureExpression;
import org.apache.uima.ruta.expression.bool.BooleanListVariableExpression;
import org.apache.uima.ruta.expression.bool.BooleanNumberExpression;
import org.apache.uima.ruta.expression.bool.BooleanStringExpression;
import org.apache.uima.ruta.expression.bool.BooleanTypeExpression;
import org.apache.uima.ruta.expression.bool.BooleanVariableExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.bool.IBooleanListExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanFunction;
import org.apache.uima.ruta.expression.bool.SimpleBooleanListExpression;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.feature.GenericFeatureExpression;
import org.apache.uima.ruta.expression.feature.SimpleFeatureExpression;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.list.UntypedListExpression;
import org.apache.uima.ruta.expression.number.AbstractNumberListExpression;
import org.apache.uima.ruta.expression.number.ComposedNumberExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.number.INumberListExpression;
import org.apache.uima.ruta.expression.number.NegativeNumberExpression;
import org.apache.uima.ruta.expression.number.NumberFeatureExpression;
import org.apache.uima.ruta.expression.number.NumberListFeatureExpression;
import org.apache.uima.ruta.expression.number.NumberListVariableExpression;
import org.apache.uima.ruta.expression.number.NumberVariableExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberListExpression;
import org.apache.uima.ruta.expression.resource.ExternalWordListExpression;
import org.apache.uima.ruta.expression.resource.ExternalWordTableExpression;
import org.apache.uima.ruta.expression.resource.LiteralWordListExpression;
import org.apache.uima.ruta.expression.resource.LiteralWordTableExpression;
import org.apache.uima.ruta.expression.resource.ReferenceWordListExpression;
import org.apache.uima.ruta.expression.resource.ReferenceWordTableExpression;
import org.apache.uima.ruta.expression.resource.StringWordListExpression;
import org.apache.uima.ruta.expression.resource.StringWordTableExpression;
import org.apache.uima.ruta.expression.resource.WordListExpression;
import org.apache.uima.ruta.expression.resource.WordTableExpression;
import org.apache.uima.ruta.expression.string.AbstractStringExpression;
import org.apache.uima.ruta.expression.string.AbstractStringListExpression;
import org.apache.uima.ruta.expression.string.ComposedStringExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.IStringListExpression;
import org.apache.uima.ruta.expression.string.SimpleStringExpression;
import org.apache.uima.ruta.expression.string.SimpleStringListExpression;
import org.apache.uima.ruta.expression.string.StringFeatureExpression;
import org.apache.uima.ruta.expression.string.StringListFeatureExpression;
import org.apache.uima.ruta.expression.string.StringListVariableExpression;
import org.apache.uima.ruta.expression.string.StringVariableExpression;
import org.apache.uima.ruta.expression.type.AbstractTypeListExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.expression.type.SimpleTypeExpression;
import org.apache.uima.ruta.expression.type.SimpleTypeListExpression;
import org.apache.uima.ruta.expression.type.TypeListVariableExpression;
import org.apache.uima.ruta.expression.type.TypeVariableExpression;

public class ExpressionFactory {

  private TypeUsageInformation typeUsage;

  public ExpressionFactory() {
    this(null);
  }

  public ExpressionFactory(TypeUsageInformation typeUsage) {
    super();
    this.typeUsage = typeUsage;
  }

  public INumberExpression createIntegerExpression(Token number, Token minus) {
    Integer valueOf = Integer.valueOf(number.getText());
    SimpleNumberExpression simpleNumberExpression = new SimpleNumberExpression(valueOf);
    if (minus != null) {
      return new NegativeNumberExpression(simpleNumberExpression);
    } else {
      return simpleNumberExpression;
    }
  }

  public INumberExpression createDoubleExpression(Token number, Token minus) {
    Double valueOf = Double.valueOf(number.getText());
    SimpleNumberExpression simpleNumberExpression = new SimpleNumberExpression(valueOf);
    if (minus != null) {
      return new NegativeNumberExpression(simpleNumberExpression);
    } else {
      return simpleNumberExpression;
    }
  }

  public INumberExpression createReferenceNumberExpression(Token var, Token minus) {
    NumberVariableExpression simpleNumberExpression = new NumberVariableExpression(var.getText());
    if (minus != null) {
      return new NegativeNumberExpression(simpleNumberExpression);
    } else {
      return simpleNumberExpression;
    }
  }

  public INumberExpression createComposedNumberExpression(List<INumberExpression> expressions,
          List<Token> opTokens) {
    List<String> ops = new ArrayList<String>();
    for (Token token : opTokens) {
      ops.add(token.getText());
    }
    return new ComposedNumberExpression(expressions, ops);
  }

  public INumberExpression createComposedNumberExpression(INumberExpression expression,
          Token opToken) {
    List<String> ops = new ArrayList<String>();
    List<INumberExpression> exprList = new ArrayList<INumberExpression>();
    ops.add(opToken.getText());
    exprList.add(expression);
    return new ComposedNumberExpression(exprList, ops);
  }

  public INumberExpression createComposedNumberExpression(INumberExpression expression1,
          Token opToken, INumberExpression expression2) {
    List<String> ops = new ArrayList<String>();
    List<INumberExpression> exprList = new ArrayList<INumberExpression>();
    ops.add(opToken.getText());
    exprList.add(expression1);
    exprList.add(expression2);
    return new ComposedNumberExpression(exprList, ops);
  }

  public AbstractStringExpression createSimpleStringExpression(Token token) {
    String text = token.getText();
    String substring = text.substring(1, text.length() - 1);
    return new SimpleStringExpression(substring);
  }

  public IStringExpression createComposedStringExpression(List<IStringExpression> expressions) {
    return new ComposedStringExpression(expressions);
  }

  public AbstractStringExpression createReferenceStringExpression(Token var) {
    return new StringVariableExpression(var.getText());
  }

  public IBooleanExpression createBooleanNumberExpression(INumberExpression e1, Token op,
          INumberExpression e2) {
    return new BooleanNumberExpression(e1, op.getText(), e2);
  }

  public IBooleanExpression createBooleanStringExpression(IStringExpression e1, Token op,
          IStringExpression e2) {
    return new BooleanStringExpression(e1, op.getText(), e2);
  }

  public IBooleanExpression createSimpleBooleanExpression(Token v) {
    return new SimpleBooleanExpression(Boolean.valueOf(v.getText()));
  }

  public IBooleanExpression createReferenceBooleanExpression(Token id) {
    return new BooleanVariableExpression(id.getText());
  }

  public ITypeExpression createSimpleTypeExpression(Token typeToken, RutaBlock parent) {
    String typeString = typeToken == null ? CAS.TYPE_NAME_DOCUMENT_ANNOTATION : typeToken.getText();
    return new SimpleTypeExpression(typeString);
  }

  public ITypeExpression createReferenceTypeExpression(Token varToken) {
    String varString = varToken == null ? "" : varToken.getText();
    return new TypeVariableExpression(varString);
  }

  public ITypeExpression createSimpleTypeExpression(String typeString, RutaBlock parent) {
    return new SimpleTypeExpression(typeString);
  }

  public IBooleanExpression createBooleanFunction(Token op, IBooleanExpression e1,
          IBooleanExpression e2) {
    return new SimpleBooleanFunction(op.getText(), e1, e2);
  }

  public WordTableExpression createReferenceWordTableExpression(Token id) {
    return new ReferenceWordTableExpression(id.getText());
  }

  public WordListExpression createReferenceWordListExpression(Token id) {
    return new ReferenceWordListExpression(id.getText());
  }

  public WordListExpression createLiteralWordListExpression(Token path) {
    return new LiteralWordListExpression(path.getText());
  }

  public WordTableExpression createLiteralWordTableExpression(Token path) {
    return new LiteralWordTableExpression(path.getText());
  }

  public WordListExpression createStringWordListExpression(IStringExpression expr) {
    return new StringWordListExpression(expr);
  }

  public WordTableExpression createStringWordTableExpression(IStringExpression expr) {
    return new StringWordTableExpression(expr);
  }

  public IBooleanExpression createBooleanTypeExpression(ITypeExpression e1, Token op,
          ITypeExpression e2) {
    return new BooleanTypeExpression(e1, op.getText(), e2);
  }

  public AbstractBooleanListExpression createReferenceBooleanListExpression(Token var) {
    return new BooleanListVariableExpression(var.getText());
  }

  public AbstractStringListExpression createReferenceStringListExpression(Token var) {
    return new StringListVariableExpression(var.getText());
  }

  public AbstractTypeListExpression createReferenceTypeListExpression(Token var) {
    return new TypeListVariableExpression(var.getText());
  }

  public AbstractNumberListExpression createReferenceDoubleListExpression(Token var) {
    return new NumberListVariableExpression(var.getText());
  }

  public AbstractNumberListExpression createReferenceIntListExpression(Token var) {
    return new NumberListVariableExpression(var.getText());
  }

  public AbstractNumberListExpression createReferenceFloatListExpression(Token var) {
    return new NumberListVariableExpression(var.getText());
  }

  public AbstractBooleanListExpression createBooleanListExpression(List<IBooleanExpression> list) {
    return new SimpleBooleanListExpression(list);
  }

  public AbstractNumberListExpression createNumberListExpression(List<INumberExpression> list) {
    return new SimpleNumberListExpression(list);
  }

  public AbstractTypeListExpression createTypeListExpression(List<ITypeExpression> list) {
    return new SimpleTypeListExpression(list);
  }

  public AbstractStringListExpression createStringListExpression(List<IStringExpression> list) {
    return new SimpleStringListExpression(list);
  }

  public FeatureExpression createFeatureExpression(MatchReference mr, RutaBlock env) {
    return new SimpleFeatureExpression(mr);
  }

  public FeatureMatchExpression createFeatureMatchExpression(MatchReference mr, Token opToken,
          IRutaExpression arg, RutaBlock env) {
    String op = "";
    if (opToken != null) {
      op = opToken.getText();
    }
    return new FeatureMatchExpression(mr, op, arg);
  }

  public MatchReference createMatchReference(Token refToken) {
    return createMatchReference(refToken, null, null);
  }

  public MatchReference createMatchReference(Token matchToken, Token comparatorToken,
          IRutaExpression argument) {
    String match = matchToken.getText();
    String comparator = null;
    if (comparatorToken != null) {
      comparator = comparatorToken.getText();
    }
    // TODO , mentions in token?
    return new MatchReference(match, comparator, argument);
  }

  public MatchReference createMatchReference(ITypeExpression expression) {
    return new MatchReference(expression);
  }

  public MatchReference createMatchReference(IAnnotationExpression expression) {
    return new MatchReference(expression);
  }

  public INumberExpression createNumberFeatureExpression(FeatureExpression fe) {
    return new NumberFeatureExpression(fe);
  }

  public AbstractStringExpression createStringFeatureExpression(FeatureExpression fe) {
    return new StringFeatureExpression(fe);
  }

  public IBooleanExpression createBooleanFeatureExpression(FeatureExpression fe) {
    return new BooleanFeatureExpression(fe);
  }

  public GenericFeatureExpression createGenericFeatureExpression(FeatureExpression fe) {
    return new GenericFeatureExpression(fe);
  }

  public ListExpression<Object> createUntypedListExpression(List<IRutaExpression> list) {
    return new UntypedListExpression(list);
  }

  public WordListExpression createExternalWordListExpression(Token name,
          List<IStringExpression> args) {
    return new ExternalWordListExpression(name.getText(), args);
  }

  public WordTableExpression createExternalWordTableExpression(Token name,
          List<IStringExpression> args) {
    return new ExternalWordTableExpression(name.getText(), args);
  }

  public IRutaExpression createNullExpression() {
    return new NullExpression();
  }

  public IAnnotationExpression createAnnotationAddressExpression(Token address) {
    return new AnnotationAddressExpression(address.getText());
  }

  public IRutaExpression createAnnotationLabelExpression(Token label) {
    return new AnnotationLabelExpression(label.getText());
  }

  public IRutaExpression createAnnotationVariableExpression(Token var) {
    return new AnnotationVariableExpression(var.getText());
  }

  public AbstractAnnotationListExpression createAnnotationListVariableExpression(Token var) {
    return new AnnotationListVariableExpression(var.getText());
  }

  public IAnnotationExpression createAnnotationFeatureExpression(
          FeatureExpression featureExpression) {
    return new AnnotationFeatureExpression(featureExpression);
  }

  public IAnnotationListExpression createAnnotationListFeatureExpression(
          FeatureExpression featureExpression) {
    return new AnnotationListFeatureExpression(featureExpression);
  }

  public IBooleanListExpression createBooleanListFeatureExpression(
          FeatureExpression featureExpression) {
    return new BooleanListFeatureExpression(featureExpression);
  }

  public INumberListExpression createNumberListFeatureExpression(
          FeatureExpression featureExpression) {
    return new NumberListFeatureExpression(featureExpression);
  }

  public IStringListExpression createStringListFeatureExpression(
          FeatureExpression featureExpression) {
    return new StringListFeatureExpression(featureExpression);
  }

  public IRutaExpression createGenericExpression(Token ref) {
    MatchReference match = new MatchReference(ref.getText());
    return new AnnotationTypeExpression(match);
  }

  public AnnotationTypeExpression createAnnotationTypeExpression(MatchReference mr) {
    return new AnnotationTypeExpression(mr);
  }
  
  public AnnotationTypeExpression createConditionedAnnotationTypeExpression(MatchReference mr, List<AbstractRutaCondition> conditions) {
    AbstractRutaCondition condition = ConditionFactory.createConditionAnd(conditions, null);
    return new ConditionedAnnotationTypeExpression(mr, condition);
  }

  public IStringExpression createStringListIndexExpression(AbstractStringListExpression sl,
          INumberExpression index) {
    return null;
  }

  public IBooleanExpression createBooleanListIndexExpression(IBooleanListExpression bl,
          INumberExpression index) {
    return null;
  }

  public INumberExpression createNumberListIndexExpression(AbstractNumberListExpression nl,
          INumberExpression index) {
    return null;
  }

  public ITypeExpression createTypeListIndexExpression(AbstractTypeListExpression tl,
          INumberExpression index) {
    return null;
  }

  public IAnnotationExpression createAnnotationListIndexExpression(
          AbstractAnnotationListExpression al, INumberExpression index) {
    return null;
  }

}
