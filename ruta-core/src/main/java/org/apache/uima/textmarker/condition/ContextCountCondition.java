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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.number.SimpleNumberExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.EvaluatedCondition;
import org.apache.uima.textmarker.rule.RuleElement;
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
  public EvaluatedCondition eval(AnnotationFS annotation, RuleElement element,
          TextMarkerStream stream, InferenceCrowd crowd) {
    Type contextType = type.getType(element.getParent());
    stream.moveToFirst();
    List<AnnotationFS> visibleContexts = new ArrayList<AnnotationFS>();
    while (stream.isValid()) {
      TextMarkerBasic each = (TextMarkerBasic) stream.get();
      if (each.beginsWith(contextType)) {
        visibleContexts.addAll(each.getBeginAnchors(contextType));
      }
      stream.moveToNext();
    }
    List<AnnotationFS> overlappingContexts = new ArrayList<AnnotationFS>();
    for (AnnotationFS eachContext : visibleContexts) {
      if (eachContext.getBegin() <= annotation.getBegin()
              && eachContext.getEnd() >= annotation.getEnd()) {
        overlappingContexts.add(eachContext);
      }
    }

    boolean result = false;
    for (AnnotationFS eachContext : overlappingContexts) {
      int index = 0;
      int counter = 0;
      List<TextMarkerBasic> basicsInWindow = stream.getBasicsInWindow(eachContext);
      for (TextMarkerBasic eachBasic : basicsInWindow) {
        Set<AnnotationFS> beginAnchors = eachBasic.getBeginAnchors(annotation.getType());
        if (beginAnchors != null) {
          for (AnnotationFS each : beginAnchors) {
            counter++;
            if (each.getBegin() == annotation.getBegin()
                    && each.getEnd() == annotation.getEnd()
                    && (each.getType().equals(annotation.getType()) || stream.getCas()
                            .getTypeSystem().subsumes(annotation.getType(), each.getType()))) {
              index = counter;
            }
          }
        }
      }

      if (var != null) {
        element.getParent().getEnvironment().setVariableValue(var, index);
      }
      boolean value = index >= min.getIntegerValue(element.getParent())
              && index <= max.getIntegerValue(element.getParent());
      result |= value;
      if (result) {
        break;
      }
    }

    return new EvaluatedCondition(this, result);
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
