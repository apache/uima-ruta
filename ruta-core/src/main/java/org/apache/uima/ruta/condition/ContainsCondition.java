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
import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationListExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.bool.IBooleanListExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanExpression;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.number.INumberListExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.IStringListExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.expression.type.ITypeListExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class ContainsCondition extends TypeSentiveCondition {

  private final INumberExpression min;

  private final INumberExpression max;

  private final IBooleanExpression percent;

  private IRutaExpression arg;

  @SuppressWarnings("rawtypes")
  private ListExpression argList;

  public ContainsCondition(ITypeExpression type, INumberExpression min, INumberExpression max,
          IBooleanExpression percent) {
    super(type);
    this.min = min == null ? new SimpleNumberExpression(Integer.valueOf(1)) : min;
    this.max = max == null ? new SimpleNumberExpression(Integer.MAX_VALUE) : max;
    this.percent = percent == null ? new SimpleBooleanExpression(false) : percent;
  }

  @SuppressWarnings("rawtypes")
  public ContainsCondition(ListExpression list, IRutaExpression a, INumberExpression min,
          INumberExpression max, IBooleanExpression percent) {
    super((ITypeExpression) null);
    this.min = min == null ? new SimpleNumberExpression(Integer.valueOf(1)) : min;
    this.max = max == null ? new SimpleNumberExpression(Integer.MAX_VALUE) : max;
    this.percent = percent == null ? new SimpleBooleanExpression(false) : percent;
    this.argList = list;
    this.arg = a;
  }

  @Override
  public EvaluatedCondition eval(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
    AnnotationFS annotation = context.getAnnotation();

    boolean usePercentage = percent.getBooleanValue(context, stream);
    int minIntValue = min.getIntegerValue(context, stream);
    int maxIntValue = max.getIntegerValue(context, stream);

    int basicCount = 0;
    int anchorCount = 0;
    int totalCount = 0;

    boolean usePredefinedBoundaries = minIntValue == 1 && maxIntValue == Integer.MAX_VALUE ? false
            : true;

    if (type != null) {
      Type t = type.getType(context, stream);
      if (annotation != null && t != null) {
        if (!usePredefinedBoundaries && !usePercentage) {
          // shortcut for simple CONTAINS(Type)
          boolean annotationExsits = checkExistingAnnotation(t, annotation, stream);
          return new EvaluatedCondition(this, annotationExsits);
        } else {
          List<RutaBasic> annotations = stream.getBasicsInWindow(annotation);
          for (RutaBasic each : annotations) {
            totalCount++;
            if (each.beginsWith(t) || stream.getCas().getTypeSystem().subsumes(t, each.getType())) {
              Collection<AnnotationFS> beginAnchors = each.getBeginAnchors(t);
              anchorCount = incrementAnchorsWithinStrictBoundaries(annotation, anchorCount,
                      beginAnchors);
              basicCount++;
            } else if (each.isPartOf(t)) {
              basicCount++;
            }
          }
        }
      }
    } else {
      List<?> list = argList.getList(context, stream);
      totalCount = list.size();
      Object sniff = null;
      if (totalCount > 0) {
        sniff = list.get(0);
      }
      if (check(sniff, Boolean.class) && arg instanceof IBooleanExpression
              && argList instanceof IBooleanListExpression) {
        IBooleanExpression e = (IBooleanExpression) arg;
        IBooleanListExpression le = (IBooleanListExpression) argList;
        boolean v = e.getBooleanValue(context, stream);
        List<Boolean> l = new ArrayList<Boolean>(le.getBooleanList(context, stream));
        while (l.remove(v)) {
          basicCount++;
        }
      } else if (check(sniff, Number.class) && arg instanceof INumberExpression
              && argList instanceof INumberListExpression) {
        INumberExpression e = (INumberExpression) arg;
        INumberListExpression le = (INumberListExpression) argList;
        Number v = e.getDoubleValue(context, stream);
        List<Number> l = new ArrayList<Number>(le.getNumberList(context, stream));
        while (l.remove(v)) {
          basicCount++;
        }
      } else if (check(sniff, String.class) && arg instanceof IStringExpression
              && argList instanceof IStringListExpression) {
        IStringExpression e = (IStringExpression) arg;
        IStringListExpression le = (IStringListExpression) argList;
        String v = e.getStringValue(context, stream);
        List<String> l = new ArrayList<String>(le.getStringList(context, stream));
        while (l.remove(v)) {
          basicCount++;
        }
      } else if (check(sniff, Type.class) && arg instanceof ITypeExpression
              && argList instanceof ITypeListExpression) {
        ITypeExpression e = (ITypeExpression) arg;
        ITypeListExpression le = (ITypeListExpression) argList;
        Type v = e.getType(context, stream);
        List<Type> l = new ArrayList<Type>(le.getTypeList(context, stream));
        while (l.remove(v)) {
          basicCount++;
        }
      } else if (check(sniff, AnnotationFS.class) && arg instanceof IAnnotationExpression
              && argList instanceof IAnnotationListExpression) {
        IAnnotationExpression e = (IAnnotationExpression) arg;
        IAnnotationListExpression le = (IAnnotationListExpression) argList;
        AnnotationFS v = e.getAnnotation(context, stream);
        List<AnnotationFS> l = new ArrayList<AnnotationFS>(le.getAnnotationList(context, stream));
        while (l.remove(v)) {
          basicCount++;
        }
      }
      anchorCount = basicCount;
    }

    if (usePercentage) {
      double percentValue = 0;
      if (totalCount != 0) {
        percentValue = (((double) basicCount) / ((double) totalCount)) * 100;
      }
      boolean value = percentValue >= min.getDoubleValue(context, stream)
              && percentValue <= max.getDoubleValue(context, stream);
      return new EvaluatedCondition(this, value);
    } else {
      boolean value = anchorCount >= minIntValue && anchorCount <= maxIntValue;
      return new EvaluatedCondition(this, value);
    }
  }

  private int incrementAnchorsWithinStrictBoundaries(AnnotationFS annotation, int anchorCount,
          Collection<AnnotationFS> beginAnchors) {
    for (AnnotationFS eachBegin : beginAnchors) {
      if (eachBegin.getEnd() <= annotation.getEnd()) {
        anchorCount++;
      }
    }
    return anchorCount;
  }

  private boolean checkExistingAnnotation(Type type, AnnotationFS annotation, RutaStream stream) {
    int begin = annotation.getBegin();
    int end = annotation.getEnd();

    FSIterator<AnnotationFS> it = stream.getCas().getAnnotationIndex(type).iterator();
    it.moveTo(annotation);
    if (!it.isValid()) {
      it.moveToLast();
      if (!it.isValid()) {
        return false;
      }
    }

    boolean moved = false;
    while (it.isValid() && (it.get()).getBegin() >= begin) {
      it.moveToPrevious();
      moved = true;
    }
    if (moved) {
      it.moveToNext();
    }
    if (!it.isValid()) {
      it.moveToFirst();
    }
    while (it.isValid() && (it.get()).getBegin() < begin) {
      it.moveToNext();
    }

    while (it.isValid()) {
      AnnotationFS a = it.get();
      if (a.getBegin() > end) {
        return false;
      }
      it.moveToNext();
      if (a.getEnd() > end) {
        continue;
      }
      if (stream.isVisible(a)) {
        return true;
      }

    }
    return false;
  }

  private boolean check(Object sniff, Class<?> clazz) {
    if (sniff == null) {
      return true;
    } else if (clazz.isAssignableFrom(sniff.getClass())) {
      return true;
    }
    return false;
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

  @SuppressWarnings("rawtypes")
  public ListExpression getArgList() {
    return argList;
  }

}
