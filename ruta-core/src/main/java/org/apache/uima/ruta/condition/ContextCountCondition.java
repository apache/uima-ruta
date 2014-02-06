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

package org.apache.uima.ruta.condition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class ContextCountCondition extends TypeSentiveCondition {

  private final INumberExpression min;

  private final INumberExpression max;

  private final String var;

  public ContextCountCondition(TypeExpression type, INumberExpression min, INumberExpression max,
          String var) {
    super(type);
    this.min = min == null ? new SimpleNumberExpression(Integer.MIN_VALUE) : min;
    this.max = max == null ? new SimpleNumberExpression(Integer.MAX_VALUE) : max;
    this.var = var;
  }

  @Override
  public EvaluatedCondition eval(AnnotationFS annotation, RuleElement element, RutaStream stream,
          InferenceCrowd crowd) {
    Type contextType = type.getType(element.getParent());
    stream.moveToFirst();
    List<AnnotationFS> visibleContexts = new ArrayList<AnnotationFS>();
    while (stream.isValid()) {
      RutaBasic each = (RutaBasic) stream.get();
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
      List<RutaBasic> basicsInWindow = stream.getBasicsInWindow(eachContext);
      for (RutaBasic eachBasic : basicsInWindow) {
        Collection<AnnotationFS> beginAnchors = eachBasic.getBeginAnchors(annotation.getType());
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
      boolean value = index >= min.getIntegerValue(element.getParent(), annotation, stream)
              && index <= max.getIntegerValue(element.getParent(), annotation, stream);
      result |= value;
      if (result) {
        break;
      }
    }

    return new EvaluatedCondition(this, result);
  }

  public INumberExpression getMin() {
    return min;
  }

  public INumberExpression getMax() {
    return max;
  }

  public String getVar() {
    return var;
  }

}
