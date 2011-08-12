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

import org.apache.uima.cas.Type;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.rule.EvaluatedCondition;
import org.apache.uima.textmarker.rule.TextMarkerRuleElement;
import org.apache.uima.textmarker.type.TextMarkerBasic;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class MOfNCondition extends ComposedTextMarkerCondition {

  private final NumberExpression min;

  private final NumberExpression max;

  public MOfNCondition(List<AbstractTextMarkerCondition> conditions, NumberExpression min,
          NumberExpression max) {
    super(conditions);
    this.min = min;
    this.max = max;
  }

  @Override
  public EvaluatedCondition eval(TextMarkerBasic basic, Type matchedType,
          TextMarkerRuleElement element, TextMarkerStream stream, InferenceCrowd crowd) {
    int result = 0;
    List<EvaluatedCondition> evals = new ArrayList<EvaluatedCondition>();
    for (AbstractTextMarkerCondition each : conditions) {
      crowd.beginVisit(each, null);
      EvaluatedCondition eval = each.eval(basic, matchedType, element, stream, crowd);
      crowd.endVisit(each, null);
      evals.add(eval);
      if (eval.isValue()) {
        result++;
      }
    }
    boolean value = result >= min.getIntegerValue(element.getParent())
            && result <= max.getIntegerValue(element.getParent());
    return new EvaluatedCondition(this, value, evals);
  }

  public NumberExpression getMin() {
    return min;
  }

  public NumberExpression getMax() {
    return max;
  }
}
