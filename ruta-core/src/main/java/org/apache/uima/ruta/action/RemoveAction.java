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
import java.util.List;

import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class RemoveAction extends AbstractRutaAction {

  private String var;

  private List<IRutaExpression> elements;

  public RemoveAction(String var, List<IRutaExpression> list) {
    super();
    this.var = var;
    this.elements = list;
  }

  public String getListExpr() {
    return var;
  }

  public List<IRutaExpression> getElements() {
    return elements;
  }

  @SuppressWarnings({ "rawtypes" })
  @Override
  public void execute(RuleMatch match, RuleElement element, RutaStream stream, InferenceCrowd crowd) {
    RutaBlock parent = element.getParent();
    List list = parent.getEnvironment().getVariableValue(var, List.class);
    List<Object> toRemove = new ArrayList<Object>();
    for (Object entry : list) {
      Object value1 = getValue(entry, parent, stream, match, element);
      for (IRutaExpression arg : elements) {
        if (arg instanceof ListExpression) {
          ListExpression l = (ListExpression) arg;
          List list2 = l.getList(parent, stream);
          for (Object object : list2) {
            Object value2 = getValue(object, parent, stream, match, element);
            if (value1.equals(value2)) {
              toRemove.add(entry);
            }
          }
        } else {
          Object value2 = getValue(arg, parent, stream, match, element);
          if (value1.equals(value2)) {
            toRemove.add(entry);
          }
        }
      }
    }
    for (Object object : toRemove) {
      list.remove(object);
    }
    parent.getEnvironment().setVariableValue(var, list);
  }

  private Object getValue(Object obj, RutaBlock parent, RutaStream stream, RuleMatch match, RuleElement element) {
    if (obj instanceof INumberExpression) {
      return ((INumberExpression) obj).getDoubleValue(parent, match, element, stream);
    } else if (obj instanceof IBooleanExpression) {
      return ((IBooleanExpression) obj).getBooleanValue(parent, match, element, stream);
    } else if (obj instanceof TypeExpression) {
      return ((TypeExpression) obj).getType(parent);
    } else if (obj instanceof IStringExpression) {
      return ((IStringExpression) obj).getStringValue(parent, match, element, stream);
    }
    return null;
  }

}
