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

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;
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
  public void execute(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
    RuleElement element = context.getElement();
    RutaBlock parent = element.getParent();
    List list = parent.getEnvironment().getVariableValue(var, List.class);
    Class<?> vgtype = parent.getEnvironment().getVariableGenericType(var);
    List<Object> toRemove = new ArrayList<Object>();
    for (Object entry : list) {
      Object value1 = getValue(entry, vgtype, context, stream);
      for (IRutaExpression arg : elements) {
        if (arg instanceof ListExpression) {
          ListExpression l = (ListExpression) arg;
          List list2 = l.getList(context, stream);
          for (Object object : list2) {
            Object value2 = getValue(object, vgtype, context, stream);
            if (value1.equals(value2)) {
              toRemove.add(entry);
            }
          }
        } else {
          Object value2 = getValue(arg, vgtype, context, stream);
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

  private Object getValue(Object obj, Class<?> vgtype, MatchContext context, RutaStream stream) {
    if (obj instanceof INumberExpression) {
      return ((INumberExpression) obj).getDoubleValue(context, stream);
    } else if (obj instanceof IBooleanExpression) {
      return ((IBooleanExpression) obj).getBooleanValue(context, stream);
    } else if (vgtype.equals(Type.class) && obj instanceof ITypeExpression) {
      return ((ITypeExpression) obj).getType(context, stream);
    } else if (vgtype.equals(AnnotationFS.class) && obj instanceof IAnnotationExpression) {
      return ((IAnnotationExpression) obj).getAnnotation(context, stream);
    } else if (obj instanceof IStringExpression) {
      return ((IStringExpression) obj).getStringValue(context, stream);
    }
    return obj;
  }

}
