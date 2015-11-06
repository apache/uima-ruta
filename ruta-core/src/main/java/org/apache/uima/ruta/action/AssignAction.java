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

package org.apache.uima.ruta.action;

import org.apache.uima.cas.Type;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaEnvironment;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class AssignAction extends AbstractRutaAction {

  private String var;

  private IRutaExpression expression;

  public AssignAction(String var, IRutaExpression e) {
    super();
    this.var = var;
    this.expression = e;
  }

  @Override
  public void execute(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
    RuleElement element = context.getElement();
    RutaBlock parent = element.getParent();
    RutaEnvironment environment = parent.getEnvironment();
    Class<?> clazz = environment.getVariableType(var);
    if (clazz.equals(Double.class) && expression instanceof INumberExpression) {
      double v = ((INumberExpression) expression).getDoubleValue(context, stream);
      environment.setVariableValue(var, v);
    } else if (clazz.equals(Integer.class) && expression instanceof INumberExpression) {
      int v = ((INumberExpression) expression).getIntegerValue(context, stream);
      environment.setVariableValue(var, v);
    } else if (clazz.equals(Type.class) && expression instanceof TypeExpression) {
      Type v = ((TypeExpression) expression).getType(context, stream);
      environment.setVariableValue(var, v);
    } else if (clazz.equals(Boolean.class) && expression instanceof IBooleanExpression) {
      boolean v = ((IBooleanExpression) expression).getBooleanValue(context, stream);
      environment.setVariableValue(var, v);
    } else if (clazz.equals(String.class) && expression instanceof IStringExpression) {
      String v = ((IStringExpression) expression).getStringValue(context, stream);
      environment.setVariableValue(var, v);
    }
  }

  public String getVar() {
    return var;
  }

  public IRutaExpression getExpression() {
    return expression;
  }

}
