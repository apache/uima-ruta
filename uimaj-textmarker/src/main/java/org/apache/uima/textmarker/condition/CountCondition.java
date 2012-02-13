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
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.TextMarkerExpression;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.list.BooleanListExpression;
import org.apache.uima.textmarker.expression.list.ListExpression;
import org.apache.uima.textmarker.expression.list.NumberListExpression;
import org.apache.uima.textmarker.expression.list.StringListExpression;
import org.apache.uima.textmarker.expression.list.TypeListExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.number.SimpleNumberExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.EvaluatedCondition;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class CountCondition extends TypeSentiveCondition {

  private final NumberExpression min;

  private final NumberExpression max;

  private final String var;

  private ListExpression list;

  private TextMarkerExpression arg;

  public CountCondition(TypeExpression type, NumberExpression min, NumberExpression max, String var) {
    super(type);
    this.min = min == null ? new SimpleNumberExpression(Integer.MIN_VALUE) : min;
    this.max = max == null ? new SimpleNumberExpression(Integer.MAX_VALUE) : max;
    this.var = var;
  }

  public CountCondition(ListExpression list, TextMarkerExpression a, NumberExpression min,
          NumberExpression max, String var) {
    super((TypeExpression) null);
    this.list = list;
    this.arg = a;
    this.min = min == null ? new SimpleNumberExpression(Integer.MIN_VALUE) : min;
    this.max = max == null ? new SimpleNumberExpression(Integer.MAX_VALUE) : max;
    this.var = var;
  }

  @Override
  public EvaluatedCondition eval(AnnotationFS annotation, RuleElement element,
          TextMarkerStream stream, InferenceCrowd crowd) {
    if (arg == null) {
      List<AnnotationFS> annotationsInWindow = stream.getAnnotationsInWindow(annotation,
              type.getType(element.getParent()));
      int count = annotationsInWindow.size();
      if (var != null) {
        element.getParent().getEnvironment().setVariableValue(var, count);
      }
      boolean value = count >= min.getIntegerValue(element.getParent())
              && count <= max.getIntegerValue(element.getParent());
      return new EvaluatedCondition(this, value);
    } else {
      int count = 0;
      if (arg instanceof BooleanExpression && list instanceof BooleanListExpression) {
        BooleanExpression e = (BooleanExpression) arg;
        BooleanListExpression le = (BooleanListExpression) list;
        boolean v = e.getBooleanValue(element.getParent());
        List<Boolean> l = new ArrayList<Boolean>(le.getList(element.getParent()));
        while (l.remove(v)) {
          count++;
        }
      } else if (arg instanceof NumberExpression && list instanceof NumberListExpression) {
        NumberExpression e = (NumberExpression) arg;
        NumberListExpression le = (NumberListExpression) list;
        Number v = e.getDoubleValue(element.getParent());
        List<Number> l = new ArrayList<Number>(le.getList(element.getParent()));
        while (l.remove(v)) {
          count++;
        }
      } else if (arg instanceof StringExpression && list instanceof StringListExpression) {
        StringExpression e = (StringExpression) arg;
        StringListExpression le = (StringListExpression) list;
        String v = e.getStringValue(element.getParent());
        List<String> l = new ArrayList<String>(le.getList(element.getParent()));
        while (l.remove(v)) {
          count++;
        }
      } else if (arg instanceof TypeExpression && list instanceof TypeListExpression) {
        TypeExpression e = (TypeExpression) arg;
        TypeListExpression le = (TypeListExpression) list;
        Type v = e.getType(element.getParent());
        List<Type> l = new ArrayList<Type>(le.getList(element.getParent()));
        while (l.remove(v)) {
          count++;
        }
      }
      boolean value = count >= min.getIntegerValue(element.getParent())
              && count <= max.getIntegerValue(element.getParent());
      return new EvaluatedCondition(this, value);
    }
  }

  public NumberExpression getMin() {
    return min;
  }

  public NumberExpression getMax() {
    return max;
  }

  public String getVar() {
    return var;
  }

  public ListExpression getArgList() {
    return list;
  }

  public TextMarkerExpression getArg() {
    return arg;
  }
}
