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

import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.string.AbstractStringExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;

public abstract class AbstractNumberExpression extends AbstractStringExpression implements INumberExpression {

  public int getIntegerValue(RutaBlock parent, RuleMatch match, RuleElement element,
          RutaStream stream) {
    List<AnnotationFS> matchedAnnotationsOf = match.getMatchedAnnotationsOf(element);
    // TODO: do we need to select the correct annotation?
    AnnotationFS annotation = null;
    if (!matchedAnnotationsOf.isEmpty()) {
      annotation = matchedAnnotationsOf.get(0);
    }
    return getIntegerValue(parent, annotation, stream);
  }

  public double getDoubleValue(RutaBlock parent, RuleMatch match, RuleElement element,
          RutaStream stream) {
    List<AnnotationFS> matchedAnnotationsOf = match.getMatchedAnnotationsOf(element);
    // TODO: do we need to select the correct annotation?
    AnnotationFS annotation = null;
    if (!matchedAnnotationsOf.isEmpty()) {
      annotation = matchedAnnotationsOf.get(0);
    }
    return getDoubleValue(parent, annotation, stream);
  }

  public float getFloatValue(RutaBlock parent, RuleMatch match, RuleElement element,
          RutaStream stream) {
    List<AnnotationFS> matchedAnnotationsOf = match.getMatchedAnnotationsOf(element);
    // TODO: do we need to select the correct annotation?
    AnnotationFS annotation = null;
    if (!matchedAnnotationsOf.isEmpty()) {
      annotation = matchedAnnotationsOf.get(0);
    }
    return getFloatValue(parent, annotation, stream);
  }
  
  protected double calculate(double t1, double t2, String op) {
    if ("+".equals(op)) {
      return t1 + t2;
    } else if ("-".equals(op)) {
      return t1 - t2;
    } else if ("*".equals(op)) {
      return t1 * t2;
    } else if ("/".equals(op)) {
      return t1 / t2;
    } else if ("%".equals(op)) {
      return t1 % t2;
    } else if ("EXP".equals(op)) {
      return Math.exp(t1);
    } else if ("LOGN".equals(op)) {
      return Math.log(t1);
    } else if ("SIN".equals(op)) {
      return Math.sin(t1);
    } else if ("COS".equals(op)) {
      return Math.cos(t1);
    } else if ("TAN".equals(op)) {
      return Math.tan(t1);
    } else if ("POW".equals(op)) {
      return Math.pow(t1, t2);
    }
    return 0;
  }

  protected float calculate(float t1, float t2, String op) {
    if ("+".equals(op)) {
      return t1 + t2;
    } else if ("-".equals(op)) {
      return t1 - t2;
    } else if ("*".equals(op)) {
      return t1 * t2;
    } else if ("/".equals(op)) {
      return t1 / t2;
    } else if ("%".equals(op)) {
      return t1 % t2;
    } else if ("EXP".equals(op)) {
      return (float) Math.exp(t1);
    } else if ("LOG".equals(op)) {
      return (float) Math.log(t1);
    } else if ("SIN".equals(op)) {
      return (float) Math.sin(t1);
    } else if ("COS".equals(op)) {
      return (float) Math.cos(t1);
    } else if ("TAN".equals(op)) {
      return (float) Math.tan(t1);
    } else if ("POW".equals(op)) {
      return (float) Math.pow(t1, t2);
    }
    return 0;
  }

  protected int calculate(int t1, int t2, String op) {
    if ("+".equals(op)) {
      return t1 + t2;
    } else if ("-".equals(op)) {
      return t1 - t2;
    } else if ("*".equals(op)) {
      return t1 * t2;
    } else if ("/".equals(op)) {
      return t1 / t2;
    } else if ("%".equals(op)) {
      return t1 % t2;
    } else if ("EXP".equals(op)) {
      return (int) Math.exp(t1);
    } else if ("LOG".equals(op)) {
      return (int) Math.log(t1);
    } else if ("SIN".equals(op)) {
      return (int) Math.sin(t1);
    } else if ("COS".equals(op)) {
      return (int) Math.cos(t1);
    } else if ("TAN".equals(op)) {
      return (int) Math.tan(t1);
    } else if ("POW".equals(op)) {
      return (int) Math.pow(t1, t2);
    }
    return 0;
  }

}
