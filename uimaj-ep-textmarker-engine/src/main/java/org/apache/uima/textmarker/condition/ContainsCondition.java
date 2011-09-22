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
import org.apache.uima.textmarker.expression.bool.SimpleBooleanExpression;
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
import org.apache.uima.textmarker.type.TextMarkerBasic;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class ContainsCondition extends TypeSentiveCondition {

  private final NumberExpression min;

  private final NumberExpression max;

  private final BooleanExpression percent;

  private TextMarkerExpression arg;

  private ListExpression argList;

  public ContainsCondition(TypeExpression type, NumberExpression min, NumberExpression max,
          BooleanExpression percent) {
    super(type);
    this.min = min == null ? new SimpleNumberExpression(Integer.valueOf(1)) : min;
    this.max = max == null ? new SimpleNumberExpression(Integer.MAX_VALUE) : max;
    this.percent = percent == null ? new SimpleBooleanExpression(false) : percent;
  }

  public ContainsCondition(ListExpression list, TextMarkerExpression a, NumberExpression min,
          NumberExpression max, BooleanExpression percent) {
    super((TypeExpression) null);
    this.min = min == null ? new SimpleNumberExpression(Integer.valueOf(1)) : min;
    this.max = max == null ? new SimpleNumberExpression(Integer.MAX_VALUE) : max;
    this.percent = percent == null ? new SimpleBooleanExpression(false) : percent;
    this.argList = list;
    this.arg = a;
  }

  @Override
  public EvaluatedCondition eval(AnnotationFS annotation, RuleElement element,
          TextMarkerStream stream, InferenceCrowd crowd) {
    int basicCount = 0;
    int anchorCount = 0;
    int totalCount = 0;

    if (type != null) {
      if (annotation != null) {
        List<TextMarkerBasic> annotations = stream.getBasicsInWindow(annotation);
        for (TextMarkerBasic each : annotations) {
          totalCount++;
          Type t = type.getType(element.getParent());
          if (each.beginsWith(t) || stream.getCas().getTypeSystem().subsumes(t, each.getType())) {
            anchorCount++;
            basicCount++;
          } else if (each.isPartOf(t)) {
            basicCount++;
          }
        }
      }
    } else {
      totalCount = argList.getList(element.getParent()).size();
      if (arg instanceof BooleanExpression && argList instanceof BooleanListExpression) {
        BooleanExpression e = (BooleanExpression) arg;
        BooleanListExpression le = (BooleanListExpression) argList;
        boolean v = e.getBooleanValue(element.getParent());
        List<Boolean> l = new ArrayList<Boolean>(le.getList(element.getParent()));
        while (l.remove(v)) {
          basicCount++;
        }
      } else if (arg instanceof NumberExpression && argList instanceof NumberListExpression) {
        NumberExpression e = (NumberExpression) arg;
        NumberListExpression le = (NumberListExpression) argList;
        Number v = e.getDoubleValue(element.getParent());
        List<Number> l = new ArrayList<Number>(le.getList(element.getParent()));
        while (l.remove(v)) {
          basicCount++;
        }
      } else if (arg instanceof StringExpression && argList instanceof StringListExpression) {
        StringExpression e = (StringExpression) arg;
        StringListExpression le = (StringListExpression) argList;
        String v = e.getStringValue(element.getParent());
        List<String> l = new ArrayList<String>(le.getList(element.getParent()));
        while (l.remove(v)) {
          basicCount++;
        }
      } else if (arg instanceof TypeExpression && argList instanceof TypeListExpression) {
        TypeExpression e = (TypeExpression) arg;
        TypeListExpression le = (TypeListExpression) argList;
        Type v = e.getType(element.getParent());
        List<Type> l = new ArrayList<Type>(le.getList(element.getParent()));
        while (l.remove(v)) {
          basicCount++;
        }
      }
      anchorCount = basicCount;
    }
    if (percent.getBooleanValue(element.getParent())) {
      double percentValue = 0;
      if (totalCount != 0) {
        percentValue = (((double) basicCount) / ((double) totalCount)) * 100;
      }
      boolean value = percentValue >= min.getDoubleValue(element.getParent())
              && percentValue <= max.getDoubleValue(element.getParent());
      return new EvaluatedCondition(this, value);
    } else {
      boolean value = anchorCount >= min.getIntegerValue(element.getParent())
              && anchorCount <= max.getIntegerValue(element.getParent());
      return new EvaluatedCondition(this, value);
    }
  }

  public NumberExpression getMin() {
    return min;
  }

  public NumberExpression getMax() {
    return max;
  }

  public BooleanExpression getPercent() {
    return percent;
  }

  public TextMarkerExpression getArg() {
    return arg;
  }

  public ListExpression getArgList() {
    return argList;
  }

}
