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

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.UIMAConstants;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.MatchReference;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;

public class FeatureMatchExpression extends SimpleFeatureExpression {

  public static final String EQUAL = "==";

  public static final String NOT_EQUAL = "!=";

  private IRutaExpression arg;

  private String op;

  public FeatureMatchExpression(FeatureExpression f, String op, IRutaExpression arg,
          RutaBlock parent) {
    super(f.getTypeExpr(parent), f.getFeatureStringList(parent));
    this.op = op;
    this.arg = arg;
  }

  public FeatureMatchExpression(MatchReference mr, RutaBlock env) {
    super(mr);
  }

  public IRutaExpression getArg() {
    if (getMatchReference() != null && arg == null) {
      arg = getMatchReference().getArg();
    }
    return arg;
  }

  public void setArg(RutaExpression arg) {
    this.arg = arg;
  }

  public String getOp() {
    if (getMatchReference() != null && op == null) {
      op = getMatchReference().getOp();
    }
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
    // null is possibly coveredText
    String rn = feature == null ? UIMAConstants.TYPE_STRING : feature.getRange().getName();
    if (rn.equals(UIMAConstants.TYPE_BOOLEAN)) {
      Boolean v1 = afs.getBooleanValue(feature);
      if (getArg() instanceof IBooleanExpression) {
        IBooleanExpression expr = (IBooleanExpression) getArg();
        Boolean v2 = expr.getBooleanValue(parent, afs, stream);
        return compare(v1, v2);
      }
    } else if (rn.equals(UIMAConstants.TYPE_INTEGER) || rn.equals(UIMAConstants.TYPE_BYTE)
            || rn.equals(UIMAConstants.TYPE_SHORT) || rn.equals(UIMAConstants.TYPE_LONG)) {
      Integer v1 = afs.getIntValue(feature);
      if (getArg() instanceof INumberExpression) {
        INumberExpression expr = (INumberExpression) getArg();
        Integer v2 = expr.getIntegerValue(parent, afs, stream);
        return compare(v1, v2);
      }
    } else if (rn.equals(UIMAConstants.TYPE_DOUBLE)) {
      Double v1 = afs.getDoubleValue(feature);
      if (getArg() instanceof INumberExpression) {
        INumberExpression expr = (INumberExpression) getArg();
        Double v2 = expr.getDoubleValue(parent, afs, stream);
        return compare(v1, v2);
      }
    } else if (rn.equals(UIMAConstants.TYPE_FLOAT)) {
      Float v1 = afs.getFloatValue(feature);
      if (getArg() instanceof INumberExpression) {
        INumberExpression expr = (INumberExpression) getArg();
        Float v2 = expr.getFloatValue(parent, afs, stream);
        return compare(v1, v2);
      }
    } else if (rn.equals(UIMAConstants.TYPE_STRING)) {
      String v1 = afs.getCoveredText();
      // null is possibly coveredText
      if (feature != null) {
        v1 = afs.getStringValue(feature);
      }
      if (getArg() instanceof IStringExpression) {
        IStringExpression expr = (IStringExpression) getArg();
        String v2 = expr.getStringValue(parent, afs, stream);
        return compare(v1, v2);
      }
    } else if (!feature.getRange().isPrimitive() && getArg() instanceof FeatureExpression) {
      FeatureExpression fe = (FeatureExpression) getArg();
      List<AnnotationFS> list = new ArrayList<AnnotationFS>(1);
      list.add(afs);
      Collection<AnnotationFS> featureAnnotations = fe.getFeatureAnnotations(list, stream, parent,
              false);
      return compare(afs.getFeatureValue(feature), featureAnnotations);
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

}
