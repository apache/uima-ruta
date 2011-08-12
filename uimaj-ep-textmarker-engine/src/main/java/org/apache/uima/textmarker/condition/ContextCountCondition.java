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

package org.apache.uima.textmarker.condition;

import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.number.SimpleNumberExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.EvaluatedCondition;
import org.apache.uima.textmarker.rule.TextMarkerRuleElement;
import org.apache.uima.textmarker.type.TextMarkerBasic;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class ContextCountCondition extends TypeSentiveCondition {

  private final NumberExpression min;

  private final NumberExpression max;

  private final String var;

  public ContextCountCondition(TypeExpression type, NumberExpression min, NumberExpression max,
          String var) {
    super(type);
    this.min = min == null ? new SimpleNumberExpression(Integer.MIN_VALUE) : min;
    this.max = max == null ? new SimpleNumberExpression(Integer.MAX_VALUE) : max;
    this.var = var;
  }

  @Override
  public EvaluatedCondition eval(TextMarkerBasic basic, Type matchedType,
          TextMarkerRuleElement element, TextMarkerStream stream, InferenceCrowd crowd) {
    List<TextMarkerBasic> annotationsInWindow = null;
    if (stream.getDocumentAnnotation().getType().equals(type.getType(element.getParent()))) {
      annotationsInWindow = stream.getBasicsInWindow(stream.getDocumentAnnotation());
    } else {
      annotationsInWindow = stream.getAnnotationsOverlappingWindow(basic,
              type.getType(element.getParent()));
    }
    int counter = 0;
    int count = 0;
    for (TextMarkerBasic eachBasic : annotationsInWindow) {
      if (eachBasic.isAnchorOf(matchedType.getName())
              || stream.getCas().getTypeSystem().subsumes(matchedType, eachBasic.getType())) {
        counter++;
        if (eachBasic.equals(basic)) {
          count = counter;
          break;
        }
      }
    }

    if (var != null) {
      element.getParent().getEnvironment().setVariableValue(var, count);
    }
    boolean value = count >= min.getIntegerValue(element.getParent())
            && count <= max.getIntegerValue(element.getParent());
    return new EvaluatedCondition(this, value);
  }

  public NumberExpression getMin() {
    return min;
  }

  public NumberExpression getMax() {
    return max;
  }

  public String getVar() {
    return var;
  }

}
