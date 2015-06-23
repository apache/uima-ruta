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

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.ExpressionFactory;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;

public class GenericFeatureExpression extends RutaExpression implements INumberExpression, IBooleanExpression, IStringExpression {

  private FeatureExpression featureExpression;

  private INumberExpression numberExpression;
  
  private IStringExpression stringExpression;
  
  private IBooleanExpression booleanExpression;
  
  public GenericFeatureExpression(FeatureExpression fe) {
    super();
    this.featureExpression = fe;
  }
  
  
  public String getStringValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    if(stringExpression == null) {
      stringExpression = ExpressionFactory.createStringFeatureExpression(featureExpression);
    }
    return stringExpression.getStringValue(parent, annotation, stream);
  }

  public String getStringValue(RutaBlock parent, RuleMatch match, RuleElement element,
          RutaStream stream) {
    if(stringExpression == null) {
      stringExpression = ExpressionFactory.createStringFeatureExpression(featureExpression);
    }
    return stringExpression.getStringValue(parent, match, element, stream);
  }

  public boolean getBooleanValue(RutaBlock parent, RuleMatch match, RuleElement element,
          RutaStream stream) {
    if(booleanExpression == null) {
      booleanExpression = ExpressionFactory.createBooleanFeatureExpression(featureExpression);
    }
    return booleanExpression.getBooleanValue(parent, match, element, stream);
  }

  public boolean getBooleanValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    if(booleanExpression == null) {
      booleanExpression = ExpressionFactory.createBooleanFeatureExpression(featureExpression);
    }
    return booleanExpression.getBooleanValue(parent, annotation, stream);
  }

  public int getIntegerValue(RutaBlock parent, RuleMatch match, RuleElement element,
          RutaStream stream) {
    if(numberExpression == null) {
      numberExpression = ExpressionFactory.createNumberFeatureExpression(featureExpression);
    }
    return numberExpression.getIntegerValue(parent, match, element, stream);
  }

  public double getDoubleValue(RutaBlock parent, RuleMatch match, RuleElement element,
          RutaStream stream) {
    if(numberExpression == null) {
      numberExpression = ExpressionFactory.createNumberFeatureExpression(featureExpression);
    }
    return numberExpression.getDoubleValue(parent, match, element, stream);
  }

  public float getFloatValue(RutaBlock parent, RuleMatch match, RuleElement element,
          RutaStream stream) {
    if(numberExpression == null) {
      numberExpression = ExpressionFactory.createNumberFeatureExpression(featureExpression);
    }
    return numberExpression.getFloatValue(parent, match, element, stream);
  }

  public int getIntegerValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    if(numberExpression == null) {
      numberExpression = ExpressionFactory.createNumberFeatureExpression(featureExpression);
    }
    return numberExpression.getIntegerValue(parent, annotation, stream);
  }

  public double getDoubleValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    if(numberExpression == null) {
      numberExpression = ExpressionFactory.createNumberFeatureExpression(featureExpression);
    }
    return numberExpression.getDoubleValue(parent, annotation, stream);
  }

  public float getFloatValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    if(numberExpression == null) {
      numberExpression = ExpressionFactory.createNumberFeatureExpression(featureExpression);
    }
    return numberExpression.getFloatValue(parent, annotation, stream);
  }


  public FeatureExpression getFeatureExpression() {
    return featureExpression;
  }


  public void setFeatureExpression(FeatureExpression featureExpression) {
    this.featureExpression = featureExpression;
  }

}
