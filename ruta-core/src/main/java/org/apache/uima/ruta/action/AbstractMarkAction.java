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
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;

public abstract class AbstractMarkAction extends TypeSensitiveAction {

  public AbstractMarkAction(ITypeExpression type) {
    super(type);
  }

  protected Annotation createAnnotation(AnnotationFS annotation, MatchContext context,
          RutaStream stream) {
    Type t = type.getType(context, stream);
    if (t == null) {
      return null;
    }

    AnnotationFS newAnnotationFS = stream.getCas().createAnnotation(t, annotation.getBegin(),
            annotation.getEnd());
    Annotation newAnnotation = null;
    if (newAnnotationFS instanceof Annotation) {
      newAnnotation = (Annotation) newAnnotationFS;
      newAnnotation.addToIndexes();
    } else {
      return null;
    }
    stream.addAnnotation(newAnnotation, context.getRuleMatch());
    addAnnotationToLabel(newAnnotation, context);
    return newAnnotation;
  }

  @Override
  public String toString() {
    return super.toString() + "," + type.getClass().getSimpleName();
  }

  boolean getDictWSParamValue(MatchContext context) {
    Object configParameterValue = context.getParent().getContext()
                                         .getConfigParameterValue(RutaEngine.PARAM_DICT_REMOVE_WS);
    if (configParameterValue instanceof Boolean) {
      return (Boolean) configParameterValue;
    }
    return false;
  }

  protected List<Integer> getIndexList(MatchContext context, List<INumberExpression> list,
          RutaStream stream) {
    RuleElement element = context.getElement();
    List<Integer> indexList = new ArrayList<Integer>();
    if (list == null || list.isEmpty()) {
      int self = element.getContainer().getRuleElements().indexOf(element) + 1;
      indexList.add(self);
      return indexList;
    }
    int last = Integer.MAX_VALUE - 1;
    for (INumberExpression each : list) {
      // no feature matches allowed
      int value = each.getIntegerValue(context, stream);
      for (int i = Math.min(value, last + 1); i < value; i++) {
        indexList.add(i);
      }
      indexList.add(value);
    }
    return indexList;
  }

}
