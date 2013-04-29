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

import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.bool.BooleanExpression;
import org.apache.uima.ruta.expression.number.NumberExpression;
import org.apache.uima.ruta.expression.string.StringExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
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
  public void execute(RuleMatch match, RuleElement element, RutaStream stream,
          InferenceCrowd crowd) {
    List list = element.getParent().getEnvironment().getVariableValue(var, List.class);   
    Collection<Object> values = new HashSet<Object>();
    List<Object> result = new ArrayList<Object>();
    for (Object each : list) {
      Object obj = getValue(each,element.getParent());
      if(!values.contains(obj)){
        result.add(each);
        values.add(obj);
      }
    }
    
    element.getParent().getEnvironment().setVariableValue(var, result);

  }

  private Object getValue(Object obj, RutaStatement parent) {
    if(obj instanceof NumberExpression) {
      return ((NumberExpression)obj).getDoubleValue(parent);
    } else if(obj instanceof BooleanExpression) {
      return ((BooleanExpression)obj).getBooleanValue(parent);
    } else if(obj instanceof TypeExpression) {
      return ((TypeExpression)obj).getType(parent);
    } else if(obj instanceof StringExpression) {
      return ((StringExpression)obj).getStringValue(parent);
    }
    return null;
  }
}
