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

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.action.MatchedTextAction;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.EvaluatedCondition;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.TextMarkerRuleElement;
import org.apache.uima.textmarker.type.TextMarkerBasic;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class PositionCondition extends TypeSentiveCondition {

  private final NumberExpression position;

  private final BooleanExpression relative;

  public PositionCondition(TypeExpression type, NumberExpression position,
          BooleanExpression relative) {
    super(type);
    this.position = position;
    this.relative = relative;
  }

  @Override
  public EvaluatedCondition eval(AnnotationFS annotation, RuleElement element,
          TextMarkerStream stream, InferenceCrowd crowd) {
    Type t = type.getType(element.getParent());

    TextMarkerBasic beginAnchor = stream.getBeginAnchor(annotation.getBegin());
    TextMarkerBasic endAnchor = stream.getEndAnchor(annotation.getEnd());
    if (!beginAnchor.isPartOf(t) || !endAnchor.isPartOf(t)) {
      return new EvaluatedCondition(this, false);
    }

    boolean relatively = relative == null ? true : relative.getBooleanValue(element.getParent());

    FSIterator<AnnotationFS> iterator = stream.getCas().getAnnotationIndex(t)
            .iterator(beginAnchor);
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
    if(element instanceof TextMarkerRuleElement) {
      TextMarkerRuleElement re = (TextMarkerRuleElement) element;
      targetTypes.addAll(re.getMatcher().getTypes(element.getParent(), stream));
    } else {
      targetTypes.add(annotation.getType());
    }
    
    if (window == null) {
      return new EvaluatedCondition(this, false);
    }
    int integerValue = position.getIntegerValue(element.getParent());
    if (relatively) {
      int counter = 0;
      List<TextMarkerBasic> inWindow = stream.getBasicsInWindow(window);
      for (TextMarkerBasic each : inWindow) {
        if(beginsWith(each, targetTypes)) {
          counter++;
          if(counter == integerValue) {
            if(each.getBegin() == beginAnchor.getBegin()) {
              return new EvaluatedCondition(this, true);
            } else {
              return new EvaluatedCondition(this, false);
            }
          } else if(counter > integerValue) {
            return new EvaluatedCondition(this, false);
          }
        }
      }
      return new EvaluatedCondition(this, false);
    } else {
      int counter = 0;
      List<TextMarkerBasic> inWindow = stream.getBasicsInWindow(window);
      for (TextMarkerBasic each : inWindow) {
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

  private boolean beginsWith(TextMarkerBasic each, List<Type> targetTypes) {
    for (Type type : targetTypes) {
      if(each.beginsWith(type)) {
        return true;
      }
    }
    return false;
  }

  public NumberExpression getPosition() {
    return position;
  }

  public BooleanExpression getRelative() {
    return relative;
  }

}
