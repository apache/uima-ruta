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

package org.apache.uima.ruta.expression.feature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.ExpressionFactory;
import org.apache.uima.ruta.expression.annotation.IAnnotationExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationListExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.bool.IBooleanListExpression;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.number.INumberListExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.IStringListExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.rule.MatchContext;

public class GenericFeatureExpression extends ListExpression<Object> implements INumberExpression,
        IBooleanExpression, IStringExpression, IAnnotationExpression, IAnnotationListExpression,
        IBooleanListExpression, INumberListExpression, IStringListExpression, ITypeExpression {

  private FeatureExpression featureExpression;

  private ITypeExpression typeExpression;

  private INumberExpression numberExpression;

  private IStringExpression stringExpression;

  private IBooleanExpression booleanExpression;

  private IAnnotationExpression annotationExpression;

  private INumberListExpression numberListExpression;

  private IStringListExpression stringListExpression;

  private IBooleanListExpression booleanListExpression;

  private IAnnotationListExpression annotationListExpression;

  private ExpressionFactory expressionFactory;

  public GenericFeatureExpression(FeatureExpression fe) {
    super();
    featureExpression = fe;
    expressionFactory = new ExpressionFactory();
  }

  @Override
  public String getStringValue(MatchContext context, RutaStream stream) {
    if (stringExpression == null) {
      stringExpression = expressionFactory.createStringFeatureExpression(featureExpression);
    }
    return stringExpression.getStringValue(context, stream);
  }

  @Override
  public boolean getBooleanValue(MatchContext context, RutaStream stream) {
    if (booleanExpression == null) {
      booleanExpression = expressionFactory.createBooleanFeatureExpression(featureExpression);
    }
    return booleanExpression.getBooleanValue(context, stream);
  }

  @Override
  public int getIntegerValue(MatchContext context, RutaStream stream) {
    if (numberExpression == null) {
      numberExpression = expressionFactory.createNumberFeatureExpression(featureExpression);
    }
    return numberExpression.getIntegerValue(context, stream);
  }

  @Override
  public double getDoubleValue(MatchContext context, RutaStream stream) {
    if (numberExpression == null) {
      numberExpression = expressionFactory.createNumberFeatureExpression(featureExpression);
    }
    return numberExpression.getDoubleValue(context, stream);
  }

  @Override
  public float getFloatValue(MatchContext context, RutaStream stream) {
    if (numberExpression == null) {
      numberExpression = expressionFactory.createNumberFeatureExpression(featureExpression);
    }
    return numberExpression.getFloatValue(context, stream);
  }

  @Override
  public AnnotationFS getAnnotation(MatchContext context, RutaStream stream) {
    if (annotationExpression == null) {
      annotationExpression = expressionFactory.createAnnotationFeatureExpression(featureExpression);
    }
    return annotationExpression.getAnnotation(context, stream);
  }

  @Override
  public FeatureStructure getFeatureStructure(MatchContext context, RutaStream stream) {
    if (annotationExpression == null) {
      annotationExpression = expressionFactory.createAnnotationFeatureExpression(featureExpression);
    }
    return annotationExpression.getFeatureStructure(context, stream);
  }

  @Override
  public List<FeatureStructure> getFeatureStructureList(MatchContext context, RutaStream stream) {
    if (annotationListExpression == null) {
      annotationListExpression = expressionFactory
              .createAnnotationListFeatureExpression(featureExpression);
    }
    return annotationListExpression.getFeatureStructureList(context, stream);
  }

  @Override
  public Type getType(MatchContext context, RutaStream stream) {
    if (typeExpression == null) {
      typeExpression = expressionFactory.createTypeFeatureExpression(featureExpression);
    }
    if (typeExpression != null) {
      return typeExpression.getType(context, stream);
    }
    // special case where an argument is interpreted as a type expression
    return featureExpression.getInitialType(context, stream);
  }

  public FeatureExpression getFeatureExpression() {
    return featureExpression;
  }

  public void setFeatureExpression(FeatureExpression featureExpression) {
    this.featureExpression = featureExpression;
  }

  @Override
  public List<String> getStringList(MatchContext context, RutaStream stream) {
    if (stringListExpression == null) {
      stringListExpression = expressionFactory.createStringListFeatureExpression(featureExpression);
    }
    return stringListExpression.getStringList(context, stream);
  }

  @Override
  public List<Number> getNumberList(MatchContext context, RutaStream stream) {
    if (numberListExpression == null) {
      numberListExpression = expressionFactory.createNumberListFeatureExpression(featureExpression);
    }
    return numberListExpression.getNumberList(context, stream);
  }

  @Override
  public List<Boolean> getBooleanList(MatchContext context, RutaStream stream) {
    if (booleanListExpression == null) {
      booleanListExpression = expressionFactory
              .createBooleanListFeatureExpression(featureExpression);
    }
    return booleanListExpression.getBooleanList(context, stream);
  }

  @Override
  public List<AnnotationFS> getAnnotationList(MatchContext context, RutaStream stream) {
    if (annotationListExpression == null) {
      annotationListExpression = expressionFactory
              .createAnnotationListFeatureExpression(featureExpression);
    }
    return annotationListExpression.getAnnotationList(context, stream);
  }

  @Override
  public List<Object> getList(MatchContext context, RutaStream stream) {
    Feature feature = featureExpression.getFeature(context, stream);
    Type range = feature.getRange();
    if (!range.isArray()) {
      return Collections.emptyList();
    }
    List<Object> result = new ArrayList<>();
    if (StringUtils.equals(range.getName(), CAS.TYPE_NAME_FS_ARRAY)) {
      result.addAll(getAnnotationList(context, stream));
    } else if (StringUtils.equals(range.getName(), CAS.TYPE_NAME_BOOLEAN_ARRAY)) {
      result.addAll(getBooleanList(context, stream));
    } else if (StringUtils.equals(range.getName(), CAS.TYPE_NAME_STRING_ARRAY)) {
      result.addAll(getStringList(context, stream));
    } else if (StringUtils.equals(range.getName(), CAS.TYPE_NAME_INTEGER_ARRAY)
            || StringUtils.equals(range.getName(), CAS.TYPE_NAME_DOUBLE_ARRAY)
            || StringUtils.equals(range.getName(), CAS.TYPE_NAME_FLOAT_ARRAY)) {
      result.addAll(getNumberList(context, stream));
    }
    return result;
  }

}
