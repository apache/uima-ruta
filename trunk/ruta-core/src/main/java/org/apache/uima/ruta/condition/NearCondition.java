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

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class NearCondition extends TypeSentiveCondition {

  private final INumberExpression min;

  private final INumberExpression max;

  private final IBooleanExpression forward;

  private final IBooleanExpression filtered;

  public NearCondition(TypeExpression type, INumberExpression min, INumberExpression max,
          IBooleanExpression forward, IBooleanExpression filtered) {
    super(type);
    this.min = min == null ? new SimpleNumberExpression(1) : min;
    this.max = max == null ? new SimpleNumberExpression(1) : max;
    this.forward = forward == null ? new SimpleBooleanExpression(true) : forward;
    this.filtered = filtered == null ? new SimpleBooleanExpression(false) : filtered;
  }

  @Override
  public EvaluatedCondition eval(AnnotationFS annotation, RuleElement element, RutaStream stream,
          InferenceCrowd crowd) {
    int maxValue = max.getIntegerValue(element.getParent(), annotation, stream);
    int minValue = min.getIntegerValue(element.getParent(), annotation, stream);
    boolean forwardValue = forward.getBooleanValue(element.getParent(), annotation, stream);

    FSIterator<AnnotationFS> it = filtered.getBooleanValue(element.getParent(), annotation, stream) ? stream
            : stream.getUnfilteredBasicIterator();
    AnnotationFS pointer = null;
    if (forwardValue) {
      pointer = stream.getEndAnchor(annotation.getEnd());
    } else {
      pointer = stream.getBeginAnchor(annotation.getBegin());
    }
    it.moveTo(pointer);
    int count = 0;
    while (count <= maxValue) {
      if (count >= minValue && it.isValid()) {
        FeatureStructure featureStructure = it.get();
        if (featureStructure instanceof RutaBasic) {
          RutaBasic each = (RutaBasic) featureStructure;
          if (each.isPartOf(type.getType(element.getParent()))) {
            return new EvaluatedCondition(this, true);
          }
        }
      }
      if (forwardValue) {
        it.moveToNext();
      } else {
        it.moveToPrevious();
      }
      count++;
    }
    return new EvaluatedCondition(this, false);
  }

  public INumberExpression getMin() {
    return min;
  }

  public INumberExpression getMax() {
    return max;
  }

  public IBooleanExpression getForward() {
    return forward;
  }

  public IBooleanExpression getFiltered() {
    return filtered;
  }
}
