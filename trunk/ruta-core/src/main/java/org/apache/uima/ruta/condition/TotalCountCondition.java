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

import java.util.Iterator;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class TotalCountCondition extends TypeSentiveCondition {
  private final INumberExpression min;

  private final INumberExpression max;

  private final String var;

  public TotalCountCondition(TypeExpression type, INumberExpression min, INumberExpression max,
          String var) {
    super(type);
    this.min = min == null ? new SimpleNumberExpression(Integer.MIN_VALUE) : min;
    this.max = max == null ? new SimpleNumberExpression(Integer.MAX_VALUE) : max;
    this.var = var;
  }

  @Override
  public EvaluatedCondition eval(AnnotationFS annotation, RuleElement element, RutaStream stream,
          InferenceCrowd crowd) {
    int count = 0;
    Iterator<?> it = stream.getJCas().getAnnotationIndex(type.getType(element.getParent()))
            .iterator();
    while (it.hasNext()) {
      it.next();
      count++;
    }
    if (var != null) {
      element.getParent().getEnvironment().setVariableValue(var, count);
    }
    boolean value = count >= min.getIntegerValue(element.getParent(), annotation, stream)
            && count <= max.getIntegerValue(element.getParent(), annotation, stream);
    return new EvaluatedCondition(this, value);
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
