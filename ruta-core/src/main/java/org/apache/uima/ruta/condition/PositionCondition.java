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

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RutaRuleElement;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class PositionCondition extends TypeSentiveCondition {

  private final INumberExpression position;

  private final IBooleanExpression relative;

  public PositionCondition(TypeExpression type, INumberExpression position,
          IBooleanExpression relative) {
    super(type);
    this.position = position;
    this.relative = relative;
  }

  @Override
  public EvaluatedCondition eval(AnnotationFS annotation, RuleElement element, RutaStream stream,
          InferenceCrowd crowd) {
    Type t = type.getType(element.getParent());

    RutaBasic beginAnchor = stream.getBeginAnchor(annotation.getBegin());
    RutaBasic endAnchor = stream.getEndAnchor(annotation.getEnd());
    if (beginAnchor == null || endAnchor == null || !beginAnchor.isPartOf(t)
            || !endAnchor.isPartOf(t)) {
      return new EvaluatedCondition(this, false);
    }

    boolean relatively = relative == null ? true : relative.getBooleanValue(element.getParent(),
            annotation, stream);

    FSIterator<AnnotationFS> iterator = stream.getCas().getAnnotationIndex(t).iterator(beginAnchor);
    if (!iterator.isValid()) {
      iterator.moveToNext();
    }
    if (!iterator.isValid()) {
      iterator.moveToLast();
    }
    AnnotationFS window = null;
    while (iterator.isValid()) {
      AnnotationFS annotationFS = iterator.get();
      if (annotationFS.getBegin() <= annotation.getBegin()
              && annotationFS.getEnd() >= annotation.getEnd()) {
        window = annotationFS;
        break;
      }
      iterator.moveToPrevious();
    }

    List<Type> targetTypes = new ArrayList<Type>();
    if (element instanceof RutaRuleElement) {
      RutaRuleElement re = (RutaRuleElement) element;
      targetTypes.addAll(re.getMatcher().getTypes(element.getParent(), stream));
    } else {
      targetTypes.add(annotation.getType());
    }

    if (window == null) {
      return new EvaluatedCondition(this, false);
    }
    int integerValue = position.getIntegerValue(element.getParent(), annotation, stream);
    if (relatively) {
      int counter = 0;
      List<RutaBasic> inWindow = stream.getBasicsInWindow(window);
      for (RutaBasic each : inWindow) {
        if (beginsWith(each, targetTypes)) {
          counter++;
          if (counter == integerValue) {
            if (each.getBegin() == beginAnchor.getBegin()) {
              return new EvaluatedCondition(this, true);
            } else {
              return new EvaluatedCondition(this, false);
            }
          } else if (counter > integerValue) {
            return new EvaluatedCondition(this, false);
          }
        }
      }
      return new EvaluatedCondition(this, false);
    } else {
      int counter = 0;
      List<RutaBasic> inWindow = stream.getBasicsInWindow(window);
      for (RutaBasic each : inWindow) {
        counter++;
        boolean beginsWith = beginsWith(each, targetTypes);
        if (each.getBegin() == beginAnchor.getBegin() && beginsWith && counter == integerValue) {
          return new EvaluatedCondition(this, true);
        } else if (counter > integerValue) {
          return new EvaluatedCondition(this, false);
        }
      }
      return new EvaluatedCondition(this, false);
    }
  }

  private boolean beginsWith(RutaBasic each, List<Type> targetTypes) {
    for (Type type : targetTypes) {
      if (each.beginsWith(type)) {
        return true;
      }
    }
    return false;
  }

  public INumberExpression getPosition() {
    return position;
  }

  public IBooleanExpression getRelative() {
    return relative;
  }

}
