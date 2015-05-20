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

import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaEnvironment;
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

public class AddAction extends AbstractRutaAction {

  private String var;

  private List<IRutaExpression> elements;

  public AddAction(String var, List<IRutaExpression> list) {
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

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public void execute(RuleMatch match, RuleElement element, RutaStream stream, InferenceCrowd crowd) {
    RutaBlock parent = element.getParent();
    RutaEnvironment environment = parent.getEnvironment();
    List list = environment.getVariableValue(var, List.class);
    // Class<?> vtype = environment.getVariableType(var);
    Class<?> vgtype = environment.getVariableGenericType(var);
    for (IRutaExpression each : elements) {
      if (each instanceof ListExpression) {
        ListExpression l = (ListExpression) each;
        list.addAll(l.getList(parent, stream));
      } else if (vgtype.equals(Boolean.class) && each instanceof IBooleanExpression) {
        list.add(((IBooleanExpression) each).getBooleanValue(parent, match, element, stream));
      } else if (vgtype.equals(Integer.class) && each instanceof INumberExpression) {
        list.add(((INumberExpression) each).getIntegerValue(parent, match, element, stream));
      } else if (vgtype.equals(Double.class) && each instanceof INumberExpression) {
        list.add(((INumberExpression) each).getDoubleValue(parent, match, element, stream));
      } else if (vgtype.equals(Type.class) && each instanceof TypeExpression) {
        list.add(((TypeExpression) each).getType(parent));
      } else if (vgtype.equals(String.class) && each instanceof IStringExpression) {
        list.add(((IStringExpression) each).getStringValue(parent, match, element, stream));
      }
    }
  }
}
