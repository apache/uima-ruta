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

package org.apache.uima.ruta.expression;

import java.util.Arrays;
import java.util.List;

import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaEnvironment;
import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.feature.SimpleFeatureExpression;
import org.apache.uima.ruta.expression.type.ReferenceTypeExpression;
import org.apache.uima.ruta.expression.type.SimpleTypeExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;

public class MatchReference extends RutaExpression {

  private String match;

  private String op;

  private IRutaExpression arg;

  private TypeExpression typeExpression;

  private FeatureExpression featureExpression;

  public MatchReference(String match, String op, IRutaExpression arg) {
    super();
    this.match = match;
    this.op = op;
    this.arg = arg;
  }

  public MatchReference(TypeExpression expression) {
    super();
    this.typeExpression = expression;
  }

  private void resolve(RutaBlock parent) {
    if (typeExpression != null) {
      return;
    }
    RutaEnvironment e = parent.getEnvironment();
    typeExpression = buildTypeExpression(match, e);
    if (typeExpression == null) {
      String[] elements = match.split("[.]");
      StringBuilder sb = new StringBuilder();
      List<String> tail = null;
      int counter = 0;
      for (String eachPart : elements) {
        if (sb.length() != 0) {
          sb.append('.');
        }
        sb.append(eachPart);
        String head = sb.toString();
        typeExpression = buildTypeExpression(head, e);
        if (typeExpression != null) {
          tail = Arrays.asList(elements).subList(counter + 1, elements.length);
          break;
        }
        counter++;
      }
      if (tail != null) {
        if (op == null) {
          featureExpression = new SimpleFeatureExpression(typeExpression, tail);
        } else {
          SimpleFeatureExpression expr = new SimpleFeatureExpression(typeExpression, tail);
          featureExpression = new FeatureMatchExpression(expr, op, arg, parent);
        }
      }
    }
    if(typeExpression == null || typeExpression.getType(parent) == null) {
      throw new IllegalArgumentException("Not able to resolve type of expression: "+ match);
    }
  }

  private TypeExpression buildTypeExpression(String candidate, RutaEnvironment e) {
    if (e.isVariableOfType(candidate, "TYPE")) {
      return new ReferenceTypeExpression(candidate);
    } else if (e.getType(candidate) != null) {
      return new SimpleTypeExpression(candidate);
    }
    return null;
  }

  public TypeExpression getTypeExpression(RutaBlock parent) {
    resolve(parent);
    return typeExpression;
  }

  public FeatureExpression getFeatureExpression(RutaBlock parent) {
    resolve(parent);
    return featureExpression;
  }

  public String getOp() {
    return op;
  }

  public IRutaExpression getArg() {
    return arg;
  }

  public RutaExpression getRawTypeExpression() {
    return typeExpression;
  }

  public RutaExpression getRawFeatureExpression() {
    return featureExpression;
  }

  public String toString() {
    String tail = "";
    if (op != null) {
      tail += op;
    }
    if (arg != null) {
      tail += arg.toString();
    }
    return match + tail;
  }

  public String getMatch() {
    return match;
  }

}
