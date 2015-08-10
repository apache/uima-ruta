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
import org.apache.uima.ruta.expression.bool.SimpleBooleanExpression;
import org.apache.uima.ruta.expression.list.BooleanListExpression;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.list.NumberListExpression;
import org.apache.uima.ruta.expression.list.StringListExpression;
import org.apache.uima.ruta.expression.list.TypeListExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class ContainsCondition extends TypeSentiveCondition {

  private final INumberExpression min;

  private final INumberExpression max;

  private final IBooleanExpression percent;

  private IRutaExpression arg;

  private ListExpression argList;

  public ContainsCondition(TypeExpression type, INumberExpression min, INumberExpression max,
          IBooleanExpression percent) {
    super(type);
    this.min = min == null ? new SimpleNumberExpression(Integer.valueOf(1)) : min;
    this.max = max == null ? new SimpleNumberExpression(Integer.MAX_VALUE) : max;
    this.percent = percent == null ? new SimpleBooleanExpression(false) : percent;
  }

  public ContainsCondition(ListExpression list, IRutaExpression a, INumberExpression min,
          INumberExpression max, IBooleanExpression percent) {
    super((TypeExpression) null);
    this.min = min == null ? new SimpleNumberExpression(Integer.valueOf(1)) : min;
    this.max = max == null ? new SimpleNumberExpression(Integer.MAX_VALUE) : max;
    this.percent = percent == null ? new SimpleBooleanExpression(false) : percent;
    this.argList = list;
    this.arg = a;
  }

  @Override
  public EvaluatedCondition eval(AnnotationFS annotation, RuleElement element, RutaStream stream,
          InferenceCrowd crowd) {
    int basicCount = 0;
    int anchorCount = 0;
    int totalCount = 0;

    if (type != null) {
      if (annotation != null) {
        List<RutaBasic> annotations = stream.getBasicsInWindow(annotation);
        for (RutaBasic each : annotations) {
          totalCount++;
          Type t = type.getType(element.getParent());
          if (each.beginsWith(t) || stream.getCas().getTypeSystem().subsumes(t, each.getType())) {
            anchorCount += each.getBeginAnchors(t).size();
            basicCount++;
          } else if (each.isPartOf(t)) {
            basicCount++;
          }
        }
      }
    } else {
      totalCount = argList.getList(element.getParent(), stream).size();
      if (arg instanceof IBooleanExpression && argList instanceof BooleanListExpression) {
        IBooleanExpression e = (IBooleanExpression) arg;
        BooleanListExpression le = (BooleanListExpression) argList;
        boolean v = e.getBooleanValue(element.getParent(), annotation, stream);
        List<Boolean> l = new ArrayList<Boolean>(le.getList(element.getParent(), stream));
        while (l.remove(v)) {
          basicCount++;
        }
      } else if (arg instanceof INumberExpression && argList instanceof NumberListExpression) {
        INumberExpression e = (INumberExpression) arg;
        NumberListExpression le = (NumberListExpression) argList;
        Number v = e.getDoubleValue(element.getParent(), annotation, stream);
        List<Number> l = new ArrayList<Number>(le.getList(element.getParent(), stream));
        while (l.remove(v)) {
          basicCount++;
        }
      } else if (arg instanceof IStringExpression && argList instanceof StringListExpression) {
        IStringExpression e = (IStringExpression) arg;
        StringListExpression le = (StringListExpression) argList;
        String v = e.getStringValue(element.getParent(), annotation, stream);
        List<String> l = new ArrayList<String>(le.getList(element.getParent(), stream));
        while (l.remove(v)) {
          basicCount++;
        }
      } else if (arg instanceof TypeExpression && argList instanceof TypeListExpression) {
        TypeExpression e = (TypeExpression) arg;
        TypeListExpression le = (TypeListExpression) argList;
        Type v = e.getType(element.getParent());
        List<Type> l = new ArrayList<Type>(le.getList(element.getParent(), stream));
        while (l.remove(v)) {
          basicCount++;
        }
      }
      anchorCount = basicCount;
    }
    if (percent.getBooleanValue(element.getParent(), annotation, stream)) {
      double percentValue = 0;
      if (totalCount != 0) {
        percentValue = (((double) basicCount) / ((double) totalCount)) * 100;
      }
      boolean value = percentValue >= min.getDoubleValue(element.getParent(), annotation, stream)
              && percentValue <= max.getDoubleValue(element.getParent(), annotation, stream);
      return new EvaluatedCondition(this, value);
    } else {
      boolean value = anchorCount >= min.getIntegerValue(element.getParent(), annotation, stream)
              && anchorCount <= max.getIntegerValue(element.getParent(), annotation, stream);
      return new EvaluatedCondition(this, value);
    }
  }

  public INumberExpression getMin() {
    return min;
  }

  public INumberExpression getMax() {
    return max;
  }

  public IBooleanExpression getPercent() {
    return percent;
  }

  public IRutaExpression getArg() {
    return arg;
  }

  public ListExpression getArgList() {
    return argList;
  }

}
