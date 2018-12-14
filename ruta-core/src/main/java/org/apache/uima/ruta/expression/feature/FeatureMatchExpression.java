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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.MatchReference;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.rule.MatchContext;

public class FeatureMatchExpression extends SimpleFeatureExpression {

  public static final String EQUAL = "==";

  public static final String NOT_EQUAL = "!=";

  private IRutaExpression arg;

  private String op;

  public FeatureMatchExpression(MatchReference mr, String op, IRutaExpression arg) {
    super(mr);
    this.op = op;
    this.arg = arg;
  }

  public IRutaExpression getArg() {
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

  public boolean checkFeatureValue(FeatureStructure fs, MatchContext context, RutaStream stream) {
    Feature feature = getFeature(context, stream);
    return checkFeatureValue(fs, feature, context, stream);
  }

  public boolean checkFeatureValue(FeatureStructure fs, Feature feature, MatchContext context,
          RutaStream stream) {
    Type featureRangeType = null;
    TypeSystem typeSystem = stream.getCas().getTypeSystem();
    if (feature instanceof CoveredTextFeature) {
      featureRangeType = typeSystem.getType(CAS.TYPE_NAME_STRING);
    } else if (feature != null) {
      featureRangeType = feature.getRange();
    }
    String rangeName = featureRangeType.getName();
    if (rangeName.equals(CAS.TYPE_NAME_BOOLEAN)) {
      Boolean v1 = fs.getBooleanValue(feature);
      if (getArg() instanceof IBooleanExpression) {
        IBooleanExpression expr = (IBooleanExpression) getArg();
        Boolean v2 = expr.getBooleanValue(context, stream);
        return compare(v1, v2);
      }
    } else if (rangeName.equals(CAS.TYPE_NAME_INTEGER) || rangeName.equals(CAS.TYPE_NAME_BYTE)
            || rangeName.equals(CAS.TYPE_NAME_SHORT) || rangeName.equals(CAS.TYPE_NAME_LONG)) {
      Integer v1 = fs.getIntValue(feature);
      if (getArg() instanceof INumberExpression) {
        INumberExpression expr = (INumberExpression) getArg();
        Integer v2 = expr.getIntegerValue(context, stream);
        return compare(v1, v2);
      }
    } else if (rangeName.equals(CAS.TYPE_NAME_DOUBLE)) {
      Double v1 = fs.getDoubleValue(feature);
      if (getArg() instanceof INumberExpression) {
        INumberExpression expr = (INumberExpression) getArg();
        Double v2 = expr.getDoubleValue(context, stream);
        return compare(v1, v2);
      }
    } else if (rangeName.equals(CAS.TYPE_NAME_FLOAT)) {
      Float v1 = fs.getFloatValue(feature);
      if (getArg() instanceof INumberExpression) {
        INumberExpression expr = (INumberExpression) getArg();
        Float v2 = expr.getFloatValue(context, stream);
        return compare(v1, v2);
      }
    } else if (typeSystem.subsumes(typeSystem.getType(CAS.TYPE_NAME_STRING), featureRangeType)) {
      String v1 = null;
      // null is possibly coveredText
      if (feature instanceof CoveredTextFeature && fs instanceof AnnotationFS) {
        v1 = ((AnnotationFS) fs).getCoveredText();
      } else if (feature != null) {
        v1 = fs.getStringValue(feature);
      }
      if (getArg() instanceof IStringExpression) {
        IStringExpression expr = (IStringExpression) getArg();
        String v2 = expr.getStringValue(context, stream);
        return compare(v1, v2);
      }
    } else if (!feature.getRange().isPrimitive() && getArg() instanceof FeatureExpression) {
      FeatureExpression fe = (FeatureExpression) getArg();
      List<FeatureStructure> list = new ArrayList<>(1);
      list.add(fs);
      Collection<? extends FeatureStructure> featureAnnotations = fe.getFeatureStructures(list,
              false, context, stream);
      return compare(fs.getFeatureValue(feature), featureAnnotations);
    }
    return false;
  }

  private boolean compare(Object v1, Object v2) {
    if (v1 == null || v2 == null) {
      if (v1 == null && v2 == null) {
        if (getOp().equals("==")) {
          return true;
        } else if (getOp().equals("!=")) {
          return false;
        }
      } else {
        if (getOp().equals("==")) {
          return false;
        } else if (getOp().equals("!=")) {
          return true;
        }
      }
    } else if (v1 instanceof Number && v2 instanceof Number) {
      Number n1 = (Number) v1;
      Number n2 = (Number) v2;
      int compareTo = 0;
      try {
        // TODO can throw exception in rule inference!!
        // TODO replace by something stable and correct
        compareTo = new BigDecimal(n1.toString()).compareTo(new BigDecimal(n2.toString()));
      } catch (Exception e) {
        // TODO: handle exception
      }
      if (getOp().equals("==")) {
        return compareTo == 0;
      } else if (getOp().equals("!=")) {
        return compareTo != 0;
      } else if (getOp().equals(">=")) {
        return compareTo >= 0;
      } else if (getOp().equals(">")) {
        return compareTo > 0;
      } else if (getOp().equals("<=")) {
        return compareTo <= 0;
      } else if (getOp().equals("<")) {
        return compareTo < 0;
      }
    } else if (v1 != null && v2 != null) {
      if (getOp().equals("==")) {
        return v1.equals(v2);
      } else if (getOp().equals("!=")) {
        return !v1.equals(v2);
      }
    }
    return false;
  }

  @Override
  public String toString() {
    String result = super.toString();
    if (op != null) {
      result += op;
    }
    if (arg != null) {
      result += arg.toString();
    }
    return result;
  }

}
