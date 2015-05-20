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

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.MatchReference;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class FeatureCondition extends AbstractRutaCondition {

  private final IStringExpression featureStringExpression;

  private IRutaExpression argExpr;

  public FeatureCondition(IStringExpression feature, IRutaExpression argExpr) {
    super();
    this.featureStringExpression = feature;
    this.argExpr = argExpr;
  }


  @Override
  public EvaluatedCondition eval(AnnotationFS annotation, RuleElement element, RutaStream stream,
          InferenceCrowd crowd) {
    RutaBlock parent = element.getParent();
    String typeWithFeature = annotation.getType().getName()+"."+featureStringExpression.getStringValue(parent, annotation, stream);
    MatchReference mf = new MatchReference(typeWithFeature, "==", argExpr);
    FeatureExpression featureExpression = mf.getFeatureExpression(parent);
    if(featureExpression instanceof FeatureMatchExpression) {
      FeatureMatchExpression fme = (FeatureMatchExpression) featureExpression;
      boolean checkFeatureValue = fme.checkFeatureValue(annotation, stream, parent);
      return new EvaluatedCondition(this, checkFeatureValue);
    }
    return new EvaluatedCondition(this, false);
  }

  public IStringExpression getFeatureStringExpression() {
    return featureStringExpression;
  }

  public IRutaExpression getArgExpr() {
    return argExpr;
  }


}
