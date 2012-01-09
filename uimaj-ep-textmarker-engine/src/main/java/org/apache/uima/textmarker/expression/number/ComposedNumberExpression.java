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

package org.apache.uima.textmarker.expression.number;

import java.util.List;

import org.apache.uima.textmarker.TextMarkerStatement;


public class ComposedNumberExpression extends AbstractNumberExpression {

  private final List<NumberExpression> expressions;

  private final List<String> ops;

  public ComposedNumberExpression(List<NumberExpression> expressions, List<String> ops) {
    super();
    this.expressions = expressions;
    this.ops = ops;
  }

  @Override
  public double getDoubleValue(TextMarkerStatement parent) {
    NumberExpression numberExpression = getExpressions().get(0);
    if (numberExpression == null) {
      return 0;
    }
    double result = numberExpression.getDoubleValue(parent);
    for (int i = 0; i < getOperators().size(); i++) {
      double second = 0;
      if (getExpressions().size() > i + 1) {
        second = getExpressions().get(i + 1).getDoubleValue(parent);
      }
      result = calculate(result, second, getOperators().get(i));
    }
    return result;
  }
  
  @Override
  public double getFloatValue(TextMarkerStatement parent) {
    NumberExpression numberExpression = getExpressions().get(0);
    if (numberExpression == null) {
      return 0;
    }
    double result = numberExpression.getFloatValue(parent);
    for (int i = 0; i < getOperators().size(); i++) {
      double second = 0;
      if (getExpressions().size() > i + 1) {
        second = getExpressions().get(i + 1).getFloatValue(parent);
      }
      result = calculate(result, second, getOperators().get(i));
    }
    return result;
  }
  
  @Override
  public int getIntegerValue(TextMarkerStatement parent) {
    int result = getExpressions().get(0).getIntegerValue(parent);
    for (int i = 0; i < getOperators().size(); i++) {
      int second = 0;
      if (getExpressions().size() > i + 1) {
        second = getExpressions().get(i + 1).getIntegerValue(parent);
      }
      result = calculate(result, second, getOperators().get(i));
    }
    return result;
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    return "" + getDoubleValue(parent);
  }

  public List<NumberExpression> getExpressions() {
    return expressions;
  }

  public List<String> getOperators() {
    return ops;
  }

 

}
