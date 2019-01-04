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

package org.apache.uima.ruta.expression.bool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.cas.BooleanArrayFS;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.rule.MatchContext;

/**
 * An expression referring to booleans (BooleanArrayFS) stored in a feature.
 *
 */
public class BooleanListFeatureExpression extends AbstractBooleanListExpression {

  private FeatureExpression fe;

  public BooleanListFeatureExpression(FeatureExpression fe) {
    super();
    this.fe = fe;
  }

  @Override
  public List<Boolean> getList(MatchContext context, RutaStream stream) {

    AnnotationFS annotation = context.getAnnotation();
    Feature feature = this.fe.getFeature(context, stream);
    if (feature == null || !feature.getRange().isArray()
            || !StringUtils.equals(feature.getRange().getName(), CAS.TYPE_NAME_BOOLEAN_ARRAY)) {
      // throw runtime exception?
      return Collections.emptyList();
    }
    List<AnnotationFS> list = this.getTargetAnnotation(annotation, this.fe, context, stream);
    Collection<? extends FeatureStructure> featureStructures = this.fe.getFeatureStructures(list,
            false, context, stream);
    List<Boolean> result = new ArrayList<>();

    for (FeatureStructure each : featureStructures) {
      if (each instanceof AnnotationFS && !each.getType().equals(annotation.getType())) {
        feature = this.fe.getFeature(new MatchContext((AnnotationFS) each, context.getElement(),
                context.getRuleMatch(), context.getDirection()), stream);
      }
      FeatureStructure featureValue = each.getFeatureValue(feature);
      if (featureValue instanceof BooleanArrayFS) {
        BooleanArrayFS array = (BooleanArrayFS) featureValue;
        for (int i = 0; i < array.size(); i++) {
          Boolean b = array.get(i);
          result.add(b);
        }
      }
    }

    return result;
  }

  public FeatureExpression getFeatureExpression() {

    return this.fe;
  }

  public void setFeatureExpression(FeatureExpression fe) {

    this.fe = fe;
  }

}
