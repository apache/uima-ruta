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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class RemoveDuplicateAction extends AbstractRutaAction {

  private String var;

  public RemoveDuplicateAction(String var) {
    super();
    this.var = var;
  }

  public String getListExpr() {
    return var;
  }

  @SuppressWarnings({ "rawtypes" })
  @Override
  public void execute(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
    RuleElement element = context.getElement();
    List list = element.getParent().getEnvironment().getVariableValue(var, List.class, stream);
    Collection<Object> values = new HashSet<Object>();
    List<Object> result = new ArrayList<Object>();
    for (Object each : list) {
      Object obj = getValue(each, context, stream);
      if (!values.contains(obj)) {
        result.add(each);
        values.add(obj);
      }
    }

    element.getParent().getEnvironment().setVariableValue(var, result);

  }

  private Object getValue(Object obj, MatchContext context, RutaStream stream) {
    if (obj instanceof INumberExpression) {
      return ((INumberExpression) obj).getDoubleValue(context, stream);
    } else if (obj instanceof IBooleanExpression) {
      return ((IBooleanExpression) obj).getBooleanValue(context, stream);
    } else if (obj instanceof ITypeExpression) {
      return ((ITypeExpression) obj).getType(context, stream);
    } else if (obj instanceof IStringExpression) {
      return ((IStringExpression) obj).getStringValue(context, stream);
    }
    return obj;
  }
}
