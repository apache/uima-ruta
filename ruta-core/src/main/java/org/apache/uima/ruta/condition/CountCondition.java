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

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.list.BooleanListExpression;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.list.NumberListExpression;
import org.apache.uima.ruta.expression.list.StringListExpression;
import org.apache.uima.ruta.expression.list.TypeListExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class CountCondition extends TypeSentiveCondition {

  private final INumberExpression min;

  private final INumberExpression max;

  private final String var;

  @SuppressWarnings("rawtypes")
  private ListExpression list;

  private IRutaExpression arg;

  public CountCondition(ITypeExpression type, INumberExpression min, INumberExpression max,
          String var) {
    super(type);
    this.min = min == null ? new SimpleNumberExpression(Integer.MIN_VALUE) : min;
    this.max = max == null ? new SimpleNumberExpression(Integer.MAX_VALUE) : max;
    this.var = var;
  }

  public CountCondition(@SuppressWarnings("rawtypes") ListExpression list, IRutaExpression a, INumberExpression min,
          INumberExpression max, String var) {
    super((ITypeExpression) null);
    this.list = list;
    this.arg = a;
    this.min = min == null ? new SimpleNumberExpression(Integer.MIN_VALUE) : min;
    this.max = max == null ? new SimpleNumberExpression(Integer.MAX_VALUE) : max;
    this.var = var;
  }

  @Override
  public EvaluatedCondition eval(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
    AnnotationFS annotation = context.getAnnotation();
    RuleElement element = context.getElement();

    if (arg == null) {
      List<AnnotationFS> annotationsInWindow = stream.getAnnotationsInWindow(annotation,
              type.getType(context, stream));
      int count = annotationsInWindow.size();
      if (var != null) {
        element.getParent().getEnvironment().setVariableValue(var, count);
      }
      boolean value = count >= min.getIntegerValue(context, stream)
              && count <= max.getIntegerValue(context, stream);
      return new EvaluatedCondition(this, value);
    } else {
      int count = 0;
      if (arg instanceof IBooleanExpression && list instanceof BooleanListExpression) {
        IBooleanExpression e = (IBooleanExpression) arg;
        BooleanListExpression le = (BooleanListExpression) list;
        boolean v = e.getBooleanValue(context, stream);
        List<Boolean> l = new ArrayList<Boolean>(le.getList(context, stream));
        while (l.remove(v)) {
          count++;
        }
      } else if (arg instanceof INumberExpression && list instanceof NumberListExpression) {
        INumberExpression e = (INumberExpression) arg;
        NumberListExpression le = (NumberListExpression) list;
        Number v = e.getDoubleValue(context, stream);
        List<Number> l = new ArrayList<Number>(le.getList(context, stream));
        while (l.remove(v)) {
          count++;
        }
      } else if (arg instanceof IStringExpression && list instanceof StringListExpression) {
        IStringExpression e = (IStringExpression) arg;
        StringListExpression le = (StringListExpression) list;
        String v = e.getStringValue(context, stream);
        List<String> l = new ArrayList<String>(le.getList(context, stream));
        while (l.remove(v)) {
          count++;
        }
      } else if (arg instanceof ITypeExpression && list instanceof TypeListExpression) {
        ITypeExpression e = (ITypeExpression) arg;
        TypeListExpression le = (TypeListExpression) list;
        Type v = e.getType(context, stream);
        List<Type> l = new ArrayList<Type>(le.getList(context, stream));
        while (l.remove(v)) {
          count++;
        }
      }
      if (var != null) {
        element.getParent().getEnvironment().setVariableValue(var, count);
      }
      boolean value = count >= min.getIntegerValue(context, stream)
              && count <= max.getIntegerValue(context, stream);
      return new EvaluatedCondition(this, value);
    }
  }

  public INumberExpression getMin() {
    return min;
  }

  public INumberExpression getMax() {
    return max;
  }

  public String getVar() {
    return var;
  }

  @SuppressWarnings("rawtypes")
  public ListExpression getArgList() {
    return list;
  }

  public IRutaExpression getArg() {
    return arg;
  }
}
