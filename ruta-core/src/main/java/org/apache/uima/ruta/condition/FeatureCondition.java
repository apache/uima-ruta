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

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.UIMAConstants;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class FeatureCondition extends AbstractRutaCondition {

  private final IStringExpression featureStringExpression;

  private IStringExpression stringExpr;

  private INumberExpression numberExpr;

  private IBooleanExpression booleanExpr;

  private TypeExpression typeExpr;

  protected FeatureCondition(IStringExpression feature) {
    super();
    this.featureStringExpression = feature;
  }

  public FeatureCondition(IStringExpression feature, IStringExpression stringExpr) {
    this(feature);
    this.stringExpr = stringExpr;
  }

  public FeatureCondition(IStringExpression feature, INumberExpression numberExpr) {
    this(feature);
    this.numberExpr = numberExpr;
  }

  public FeatureCondition(IStringExpression feature, IBooleanExpression booleanExpr) {
    this(feature);
    this.booleanExpr = booleanExpr;
  }

  public FeatureCondition(IStringExpression feature, TypeExpression typeExpr, String variable,
          RuleElement re) {
    this(feature);
    this.typeExpr = typeExpr;
  }

  @Override
  public EvaluatedCondition eval(AnnotationFS annotation, RuleElement element, RutaStream stream,
          InferenceCrowd crowd) {
    String stringValue = featureStringExpression.getStringValue(element.getParent(), annotation,
            stream);
    Feature featureByBaseName = annotation.getType().getFeatureByBaseName(stringValue);

    if (stringExpr != null) {
      String value = annotation.getStringValue(featureByBaseName);
      String string = stringExpr.getStringValue(element.getParent(), null, stream);
      boolean result = string != null && string.equals(value);
      return new EvaluatedCondition(this, result);
    } else if (numberExpr != null) {
      String range = featureByBaseName.getRange().getName();
      boolean result = false;
      if (range.equals(UIMAConstants.TYPE_INTEGER)) {
        int value = annotation.getIntValue(featureByBaseName);
        int v = numberExpr.getIntegerValue(element.getParent(), annotation, stream);
        result = value == v;
      } else if (range.equals(UIMAConstants.TYPE_DOUBLE)) {
        double value = annotation.getDoubleValue(featureByBaseName);
        double v = numberExpr.getDoubleValue(element.getParent(), annotation, stream);
        result = value == v;
      } else if (range.equals(UIMAConstants.TYPE_FLOAT)) {
        float value = annotation.getFloatValue(featureByBaseName);
        float v = numberExpr.getFloatValue(element.getParent(), annotation, stream);
        result = value == v;
      } else if (range.equals(UIMAConstants.TYPE_BYTE)) {
        byte value = annotation.getByteValue(featureByBaseName);
        byte v = (byte) numberExpr.getIntegerValue(element.getParent(), annotation, stream);
        result = value == v;
      } else if (range.equals(UIMAConstants.TYPE_SHORT)) {
        short value = annotation.getShortValue(featureByBaseName);
        short v = (short) numberExpr.getIntegerValue(element.getParent(), annotation, stream);
        result = value == v;
      } else if (range.equals(UIMAConstants.TYPE_LONG)) {
        long value = annotation.getLongValue(featureByBaseName);
        long v = numberExpr.getIntegerValue(element.getParent(), annotation, stream);
        result = value == v;
      }
      return new EvaluatedCondition(this, result);
    } else if (booleanExpr != null) {
      boolean value = annotation.getBooleanValue(featureByBaseName);
      boolean v = booleanExpr.getBooleanValue(element.getParent(), annotation, stream);
      boolean result = value == v;
      return new EvaluatedCondition(this, result);
    } else if (typeExpr != null) {
      // String value = expandAnchor.getStringValue(featureByBaseName);
      // String string = stringExpr.getStringValue(element.getParent());
      // boolean result = string != null && string.equals(value);
      // return new EvaluatedCondition(this, result);
    }
    return new EvaluatedCondition(this, false);
  }

  public IStringExpression getFeatureStringExpression() {
    return featureStringExpression;
  }

  public IStringExpression getStringExpr() {
    return stringExpr;
  }

  public INumberExpression getNumberExpr() {
    return numberExpr;
  }

  public IBooleanExpression getBooleanExpr() {
    return booleanExpr;
  }

  public TypeExpression getTypeExpr() {
    return typeExpr;
  }

}
