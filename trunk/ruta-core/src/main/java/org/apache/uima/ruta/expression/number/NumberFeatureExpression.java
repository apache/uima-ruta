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

package org.apache.uima.ruta.expression.number;

import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.rule.MatchContext;

public class NumberFeatureExpression extends AbstractNumberExpression {

  private FeatureExpression fe;

  public NumberFeatureExpression(FeatureExpression fe) {
    super();
    this.fe = fe;
  }

  @Override
  public int getIntegerValue(MatchContext context, RutaStream stream) {

    Number v = this.getNumberValue(context, stream);
    return v == null ? 0 : v.intValue();
  }

  @Override
  public double getDoubleValue(MatchContext context, RutaStream stream) {

    Number v = this.getNumberValue(context, stream);
    return v == null ? 0 : v.doubleValue();
  }

  @Override
  public float getFloatValue(MatchContext context, RutaStream stream) {

    Number v = this.getNumberValue(context, stream);
    return v == null ? 0 : v.floatValue();
  }

  private Number getNumberValue(MatchContext context, RutaStream stream) {

    AnnotationFS annotation = context.getAnnotation();
    Number result = null;
    List<AnnotationFS> list = this.getTargetAnnotation(annotation, this.fe, context, stream);
    Collection<? extends FeatureStructure> featureStructures = this.fe.getFeatureStructures(list,
            false, context, stream);
    if (!featureStructures.isEmpty()) {
      Feature feature = this.fe.getFeature(context, stream);
      Type range = feature.getRange();
      FeatureStructure next = featureStructures.iterator().next();
      if (next instanceof AnnotationFS && !next.getType().equals(annotation.getType())) {
        feature = this.fe.getFeature(new MatchContext((AnnotationFS) next, context.getElement(),
                context.getRuleMatch(), context.getDirection()), stream);
      }
      if (CAS.TYPE_NAME_BYTE.equals(range.getName())) {
        result = next.getByteValue(feature);
      } else if (CAS.TYPE_NAME_DOUBLE.equals(range.getName())) {
        result = next.getDoubleValue(feature);
      } else if (CAS.TYPE_NAME_FLOAT.equals(range.getName())) {
        result = next.getFloatValue(feature);
      } else if (CAS.TYPE_NAME_INTEGER.equals(range.getName())) {
        result = next.getIntValue(feature);
      } else if (CAS.TYPE_NAME_LONG.equals(range.getName())) {
        result = next.getLongValue(feature);
      } else if (CAS.TYPE_NAME_SHORT.equals(range.getName())) {
        result = next.getShortValue(feature);
      }
    }
    return result;
  }

  @Override
  public String getStringValue(MatchContext context, RutaStream stream) {

    return "" + this.getNumberValue(context, stream);
  }

  public FeatureExpression getFe() {

    return this.fe;
  }

  public void setFe(FeatureExpression fe) {

    this.fe = fe;
  }

}
