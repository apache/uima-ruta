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

import java.util.Iterator;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.number.SimpleNumberExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.EvaluatedCondition;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class CurrentCountCondition extends TypeSentiveCondition {
  private final NumberExpression min;

  private final NumberExpression max;

  private final String var;

  public CurrentCountCondition(TypeExpression type, NumberExpression min, NumberExpression max,
          String var) {
    super(type);
    this.min = min == null ? new SimpleNumberExpression(Integer.MIN_VALUE) : min;
    this.max = max == null ? new SimpleNumberExpression(Integer.MAX_VALUE) : max;
    this.var = var;
  }

  @Override
  public EvaluatedCondition eval(AnnotationFS annotation, RuleElement element,
          TextMarkerStream stream, InferenceCrowd crowd) {
    int count = 0;
    Iterator<AnnotationFS> it = stream.getCas().getAnnotationIndex(type.getType(element.getParent()))
            .iterator();
    while (it.hasNext()) {
      AnnotationFS next = it.next();
      if(next.getBegin() < annotation.getBegin()) {
        count++;
      } else {
        break;
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
