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

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.UIMAConstants;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.bool.BooleanExpression;
import org.apache.uima.ruta.expression.number.NumberExpression;
import org.apache.uima.ruta.expression.string.StringExpression;

public class FeatureMatchExpression extends SimpleFeatureExpression {

  public static final String EQUAL = "==";

  public static final String NOT_EQUAL = "!=";

  private RutaExpression arg;

  private String op;

  public FeatureMatchExpression(FeatureExpression f, String op, RutaExpression arg) {
    super(f.getTypeExpr(), f.getFeatureStringList());
    this.op = op;
    this.arg = arg;
  }

  public RutaExpression getArg() {
    return arg;
  }

  public void setArg(RutaExpression arg) {
    this.arg = arg;
  }

  public String getOp() {
    return op;
  }

  public void setOp(String op) {
    this.op = op;
  }

  public boolean checkFeatureValue(AnnotationFS afs, RutaStream stream, RutaBlock parent) {
    Feature feature = getFeature(parent);
    return checkFeatureValue(afs, feature, stream, parent);
  }

  public boolean checkFeatureValue(AnnotationFS afs, Feature feature, RutaStream stream,
          RutaBlock parent) {
    String rn = feature.getRange().getName();
    if (rn.equals(UIMAConstants.TYPE_BOOLEAN)) {
      boolean v1 = afs.getBooleanValue(feature);
      if (arg instanceof BooleanExpression) {
        BooleanExpression expr = (BooleanExpression) arg;
        boolean v2 = expr.getBooleanValue(parent, afs, stream);
        return v1 == v2;
      }
    } else if (rn.equals(UIMAConstants.TYPE_INTEGER) || rn.equals(UIMAConstants.TYPE_BYTE)
            || rn.equals(UIMAConstants.TYPE_SHORT) || rn.equals(UIMAConstants.TYPE_LONG)) {
      Integer v1 = afs.getIntValue(feature);
      if (arg instanceof NumberExpression) {
        NumberExpression expr = (NumberExpression) arg;
        Integer v2 = expr.getIntegerValue(parent, afs, stream);
        return v1.equals(v2);
      }
    } else if (rn.equals(UIMAConstants.TYPE_DOUBLE)) {
      Double v1 = afs.getDoubleValue(feature);
      if (arg instanceof NumberExpression) {
        NumberExpression expr = (NumberExpression) arg;
        Double v2 = expr.getDoubleValue(parent, afs, stream);
        return v1.equals(v2);
      }
    } else if (rn.equals(UIMAConstants.TYPE_FLOAT)) {
      Float v1 = afs.getFloatValue(feature);
      if (arg instanceof NumberExpression) {
        NumberExpression expr = (NumberExpression) arg;
        Float v2 = expr.getFloatValue(parent, afs, stream);
        return v1.equals(v2);
      }
    } else if (rn.equals(UIMAConstants.TYPE_STRING)) {
      String v1 = afs.getStringValue(feature);
      if (arg instanceof StringExpression) {
        StringExpression expr = (StringExpression) arg;
        String v2 = expr.getStringValue(parent, afs, stream);
        return v1 != null && v1.equals(v2);
      }
    }
    return false;
  }

}
