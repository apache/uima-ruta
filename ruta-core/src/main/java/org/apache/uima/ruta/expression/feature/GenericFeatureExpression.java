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

import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.ExpressionFactory;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.rule.MatchContext;

public class GenericFeatureExpression extends RutaExpression implements INumberExpression,
        IBooleanExpression, IStringExpression {

  private FeatureExpression featureExpression;

  private INumberExpression numberExpression;

  private IStringExpression stringExpression;

  private IBooleanExpression booleanExpression;

  public GenericFeatureExpression(FeatureExpression fe) {
    super();
    this.featureExpression = fe;
  }

  @Override
  public String getStringValue(MatchContext context, RutaStream stream) {
    if (stringExpression == null) {
      stringExpression = ExpressionFactory.createStringFeatureExpression(featureExpression);
    }
    return stringExpression.getStringValue(context, stream);
  }

  @Override
  public boolean getBooleanValue(MatchContext context, RutaStream stream) {
    if (booleanExpression == null) {
      booleanExpression = ExpressionFactory.createBooleanFeatureExpression(featureExpression);
    }
    return booleanExpression.getBooleanValue(context, stream);
  }

  @Override
  public int getIntegerValue(MatchContext context, RutaStream stream) {
    if (numberExpression == null) {
      numberExpression = ExpressionFactory.createNumberFeatureExpression(featureExpression);
    }
    return numberExpression.getIntegerValue(context, stream);
  }

  @Override
  public double getDoubleValue(MatchContext context, RutaStream stream) {
    if (numberExpression == null) {
      numberExpression = ExpressionFactory.createNumberFeatureExpression(featureExpression);
    }
    return numberExpression.getDoubleValue(context, stream);
  }

  @Override
  public float getFloatValue(MatchContext context, RutaStream stream) {
    if (numberExpression == null) {
      numberExpression = ExpressionFactory.createNumberFeatureExpression(featureExpression);
    }
    return numberExpression.getFloatValue(context, stream);
  }

  public FeatureExpression getFeatureExpression() {
    return featureExpression;
  }

  public void setFeatureExpression(FeatureExpression featureExpression) {
    this.featureExpression = featureExpression;
  }

}
