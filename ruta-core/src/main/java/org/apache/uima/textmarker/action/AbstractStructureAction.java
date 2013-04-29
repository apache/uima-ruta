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

package org.apache.uima.textmarker.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.UIMAConstants;
import org.apache.uima.textmarker.expression.TextMarkerExpression;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.utils.UIMAUtils;

public abstract class AbstractStructureAction extends AbstractTextMarkerAction {

  public AbstractStructureAction() {
    super();
  }

  protected void fillFeatures(TOP structure, Map<StringExpression, TextMarkerExpression> features,
          AnnotationFS matchedAnnotation, RuleElement element, TextMarkerStream stream) {
    Map<String, TextMarkerExpression> map = new HashMap<String, TextMarkerExpression>();
    for (Entry<StringExpression, TextMarkerExpression> each : features.entrySet()) {
      String value = each.getKey().getStringValue(element.getParent());
      map.put(value, each.getValue());
    }

    TypeSystem typeSystem = stream.getCas().getTypeSystem();
    JCas jcas = stream.getJCas();
    List<?> featuresList = structure.getType().getFeatures();
    for (int i = 0; i < featuresList.size(); i++) {
      Feature targetFeature = (Feature) featuresList.get(i);
      String name = targetFeature.getName();
      String shortFName = name.substring(name.indexOf(":") + 1, name.length());
      Object valueObject = map.get(shortFName);
      Type range = targetFeature.getRange();
      if (valueObject != null) {
        if (valueObject instanceof TypeExpression
                && range.getName().equals(UIMAConstants.TYPE_STRING)) {
          TypeExpression type = (TypeExpression) valueObject;
          List<AnnotationFS> annotationsInWindow = stream.getAnnotationsInWindow(matchedAnnotation,
                  type.getType(element.getParent()));
          if (annotationsInWindow != null && !annotationsInWindow.isEmpty()) {
            AnnotationFS annotation = annotationsInWindow.get(0);
            structure.setStringValue(targetFeature, annotation.getCoveredText());
          }
        } else if (valueObject instanceof StringExpression
                && range.getName().equals(UIMAConstants.TYPE_STRING)) {
          structure.setStringValue(targetFeature,
                  ((StringExpression) valueObject).getStringValue(element.getParent()));

        } else if (valueObject instanceof NumberExpression) {
          if (range.getName().equals(UIMAConstants.TYPE_INTEGER)) {
            structure.setIntValue(targetFeature,
                    ((NumberExpression) valueObject).getIntegerValue(element.getParent()));
          } else if (range.getName().equals(UIMAConstants.TYPE_DOUBLE)) {
            structure.setDoubleValue(targetFeature,
                    ((NumberExpression) valueObject).getDoubleValue(element.getParent()));
          } else if (range.getName().equals(UIMAConstants.TYPE_FLOAT)) {
            structure.setFloatValue(targetFeature,
                    ((NumberExpression) valueObject).getFloatValue(element.getParent()));
          } else if (range.getName().equals(UIMAConstants.TYPE_BYTE)) {
            structure.setByteValue(targetFeature,
                    (byte) ((NumberExpression) valueObject).getIntegerValue(element.getParent()));
          } else if (range.getName().equals(UIMAConstants.TYPE_SHORT)) {
            structure.setShortValue(targetFeature,
                    (short) ((NumberExpression) valueObject).getIntegerValue(element.getParent()));
          } else if (range.getName().equals(UIMAConstants.TYPE_LONG)) {
            structure.setLongValue(targetFeature,
                    (long) ((NumberExpression) valueObject).getIntegerValue(element.getParent()));
          }
        } else if (valueObject instanceof BooleanExpression
                && range.getName().equals(UIMAConstants.TYPE_BOOLEAN)) {
          structure.setBooleanValue(targetFeature,
                  ((BooleanExpression) valueObject).getBooleanValue(element.getParent()));
        } else if (valueObject instanceof TypeExpression) {
          TypeExpression type = (TypeExpression) valueObject;
          List<AnnotationFS> annotationsInWindow = stream.getAnnotationsInWindow(matchedAnnotation,
                  type.getType(element.getParent()));
          if (typeSystem.subsumes(jcas.getCasType(FSArray.type), range)) {
            structure
                    .setFeatureValue(targetFeature, UIMAUtils.toFSArray(jcas, annotationsInWindow));
          } else if (typeSystem.subsumes(range, type.getType(element.getParent()))
                  && !annotationsInWindow.isEmpty()) {
            AnnotationFS annotation = annotationsInWindow.get(0);
            structure.setFeatureValue(targetFeature, annotation);
          }
        }
      }

    }
  }
}
