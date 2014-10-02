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

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.UIMAConstants;
import org.apache.uima.ruta.expression.feature.FeatureExpression;

public class NumberFeatureExpression extends AbstractNumberExpression {

  private FeatureExpression fe;

  public NumberFeatureExpression(FeatureExpression fe) {
    super();
    this.fe = fe;
  }

  public int getIntegerValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    Number v = getNumberValue(parent, annotation, stream);
    return v == null ? 0 : v.intValue();
  }

  public double getDoubleValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    Number v = getNumberValue(parent, annotation, stream);
    return v == null ? 0 : v.doubleValue();
  }

  public float getFloatValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    Number v = getNumberValue(parent, annotation, stream);
    return v == null ? 0 : v.floatValue();
  }

  private Number getNumberValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    Number result = null;
    Type type = fe.getTypeExpr(parent).getType(parent);
    Feature feature = fe.getFeature(parent);
    Type range = feature.getRange();
    List<AnnotationFS> list = getTargetAnnotation(annotation, type, stream);
    Collection<AnnotationFS> featureAnnotations = fe.getFeatureAnnotations(list, stream, parent,
            false);
    if (!featureAnnotations.isEmpty()) {
      AnnotationFS next = featureAnnotations.iterator().next();
      if (UIMAConstants.TYPE_BYTE.equals(range.getName())) {
        result = next.getByteValue(feature);
      } else if (UIMAConstants.TYPE_DOUBLE.equals(range.getName())) {
        result = next.getDoubleValue(feature);
      } else if (UIMAConstants.TYPE_FLOAT.equals(range.getName())) {
        result = next.getFloatValue(feature);
      } else if (UIMAConstants.TYPE_INTEGER.equals(range.getName())) {
        result = next.getIntValue(feature);
      } else if (UIMAConstants.TYPE_LONG.equals(range.getName())) {
        result = next.getLongValue(feature);
      } else if (UIMAConstants.TYPE_SHORT.equals(range.getName())) {
        result = next.getShortValue(feature);
      }
    }
    return result;
  }

  @Override
  public String getStringValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    return "" + getNumberValue(parent, annotation, stream);
  }

  public FeatureExpression getFe() {
    return fe;
  }

  public void setFe(FeatureExpression fe) {
    this.fe = fe;
  }

}
