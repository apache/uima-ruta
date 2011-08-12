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

import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.EvaluatedCondition;
import org.apache.uima.textmarker.rule.TextMarkerRuleElement;
import org.apache.uima.textmarker.type.TextMarkerBasic;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class PositionCondition extends TypeSentiveCondition {

  private final NumberExpression position;

  public NumberExpression getPosition() {
    return position;
  }

  public PositionCondition(TypeExpression type, NumberExpression position) {
    super(type);
    this.position = position;
  }

  @Override
  public EvaluatedCondition eval(TextMarkerBasic basic, Type matchedType,
          TextMarkerRuleElement element, TextMarkerStream stream, InferenceCrowd crowd) {
    Type t = type.getType(element.getParent());
    if (!basic.isPartOf(t.getName())) {
      return new EvaluatedCondition(this, false);
    }
    if (matchedType == null)
      return new EvaluatedCondition(this, false);
    int counter = 0;
    List<TextMarkerBasic> annotationsInWindow = null;
    if (t.getName().equals("uima.tcas.DocumentAnnotation")
            || stream.getDocumentAnnotation().getType().equals(t)) {
      annotationsInWindow = stream.getBasicsInWindow(stream.getDocumentAnnotation());
    } else {
      annotationsInWindow = stream.getAnnotationsOverlappingWindow(basic, t);
    }
    for (TextMarkerBasic eachBasic : annotationsInWindow) {
      if (eachBasic.isAnchorOf(matchedType.getName())
              || stream.getCas().getTypeSystem().subsumes(matchedType, eachBasic.getType())) {
        counter++;
        if (eachBasic.equals(basic)) {
          if (counter == position.getIntegerValue(element.getParent())) {
            return new EvaluatedCondition(this, true);
          }
        }
      }
    }
    return new EvaluatedCondition(this, false);
  }

}
