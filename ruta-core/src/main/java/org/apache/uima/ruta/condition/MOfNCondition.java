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
import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class MOfNCondition extends ComposedRutaCondition {

  private final INumberExpression min;

  private final INumberExpression max;

  public MOfNCondition(List<AbstractRutaCondition> conditions, INumberExpression min,
          INumberExpression max) {
    super(conditions);
    this.min = min;
    this.max = max;
  }

  @Override
  public EvaluatedCondition eval(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
		AnnotationFS annotation = context.getAnnotation();
		RuleElement element = context.getElement();
    int result = 0;
    List<EvaluatedCondition> evals = new ArrayList<EvaluatedCondition>();
    for (AbstractRutaCondition each : conditions) {
      crowd.beginVisit(each, null);
      EvaluatedCondition eval = each.eval(context, stream, crowd);
      crowd.endVisit(each, null);
      evals.add(eval);
      if (eval.isValue()) {
        result++;
      }
    }
    boolean value = result >= min.getIntegerValue(context, stream)
            && result <= max.getIntegerValue(context, stream);
    return new EvaluatedCondition(this, value, evals);
  }

  public INumberExpression getMin() {
    return min;
  }

  public INumberExpression getMax() {
    return max;
  }
}
