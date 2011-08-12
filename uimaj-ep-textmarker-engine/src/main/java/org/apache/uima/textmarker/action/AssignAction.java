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

package org.apache.uima.textmarker.action;

import org.apache.uima.cas.Type;
import org.apache.uima.textmarker.TextMarkerBlock;
import org.apache.uima.textmarker.TextMarkerEnvironment;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.TextMarkerExpression;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.rule.TextMarkerRuleElement;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class AssignAction extends AbstractTextMarkerAction {

  private String var;

  private TextMarkerExpression expression;

  public AssignAction(String var, TextMarkerExpression e) {
    super();
    this.var = var;
    this.expression = e;
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    TextMarkerBlock parent = element.getParent();
    TextMarkerEnvironment environment = parent.getEnvironment();
    Class<?> clazz = environment.getVariableType(var);
    if (clazz.equals(Integer.class) && expression instanceof NumberExpression) {
      int v = ((NumberExpression) expression).getIntegerValue(parent);
      environment.setVariableValue(var, v);
    } else if (clazz.equals(Double.class) && expression instanceof NumberExpression) {
      double v = ((NumberExpression) expression).getDoubleValue(parent);
      environment.setVariableValue(var, v);
    } else if (clazz.equals(Type.class) && expression instanceof TypeExpression) {
      Type v = ((TypeExpression) expression).getType(parent);
      environment.setVariableValue(var, v);
    } else if (clazz.equals(Boolean.class) && expression instanceof BooleanExpression) {
      boolean v = ((BooleanExpression) expression).getBooleanValue(parent);
      environment.setVariableValue(var, v);
    } else if (clazz.equals(String.class) && expression instanceof StringExpression) {
      String v = ((StringExpression) expression).getStringValue(parent);
      environment.setVariableValue(var, v);
    }
  }

  public String getVar() {
    return var;
  }

  public TextMarkerExpression getExpression() {
    return expression;
  }

}
