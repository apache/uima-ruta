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
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.UIMAConstants;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.expression.feature.GenericFeatureExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.utils.UIMAUtils;

public abstract class AbstractStructureAction extends AbstractRutaAction {

  public AbstractStructureAction() {
    super();
  }

  protected void fillFeatures(TOP structure, Map<IStringExpression, IRutaExpression> features,
          AnnotationFS matchedAnnotation, RuleElement element, RutaStream stream) {
    Map<String, IRutaExpression> map = new HashMap<String, IRutaExpression>();
    RutaBlock parent = element.getParent();
    for (Entry<IStringExpression, IRutaExpression> each : features.entrySet()) {
      String value = each.getKey().getStringValue(parent, matchedAnnotation, stream);
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
                  type.getType(parent));
          if (annotationsInWindow != null && !annotationsInWindow.isEmpty()) {
            AnnotationFS annotation = annotationsInWindow.get(0);
            structure.setStringValue(targetFeature, annotation.getCoveredText());
          }
        } else if (valueObject instanceof GenericFeatureExpression && !range.isPrimitive()) {
          GenericFeatureExpression gfe = (GenericFeatureExpression) valueObject;
          FeatureExpression fe = gfe.getFeatureExpression();
          TypeExpression type = fe.getTypeExpr(parent);
          List<AnnotationFS> annotationsInWindow = stream.getAnnotationsInWindow(matchedAnnotation,
                  type.getType(parent));
          List<AnnotationFS> featureAnnotations = annotationsInWindow;
          if (fe.getFeatures(parent) != null) {
            featureAnnotations = new ArrayList<AnnotationFS>(fe.getFeatureAnnotations(
                    annotationsInWindow, stream, parent, false));
          }
          if (typeSystem.subsumes(jcas.getCasType(FSArray.type), range)) {
            structure.setFeatureValue(targetFeature, UIMAUtils.toFSArray(jcas, featureAnnotations));
          } else if (typeSystem.subsumes(range, type.getType(parent))
                  && !featureAnnotations.isEmpty()) {
            AnnotationFS annotation = featureAnnotations.get(0);
            structure.setFeatureValue(targetFeature, annotation);
          }
        } else if (valueObject instanceof IStringExpression
                && range.getName().equals(UIMAConstants.TYPE_STRING)) {
          structure.setStringValue(targetFeature, ((IStringExpression) valueObject).getStringValue(
                  parent, matchedAnnotation, stream));

        } else if (valueObject instanceof INumberExpression) {
         if (range.getName().equals(UIMAConstants.TYPE_DOUBLE)) {
          structure.setDoubleValue(targetFeature, ((INumberExpression) valueObject)
                  .getDoubleValue(parent, matchedAnnotation, stream));
         } else if (range.getName().equals(UIMAConstants.TYPE_INTEGER)) {
            structure.setIntValue(targetFeature, ((INumberExpression) valueObject).getIntegerValue(
                    parent, matchedAnnotation, stream));
          } else if (range.getName().equals(UIMAConstants.TYPE_FLOAT)) {
            structure.setFloatValue(targetFeature, ((INumberExpression) valueObject).getFloatValue(
                    parent, matchedAnnotation, stream));
          } else if (range.getName().equals(UIMAConstants.TYPE_BYTE)) {
            structure.setByteValue(targetFeature, (byte) ((INumberExpression) valueObject)
                    .getIntegerValue(parent, matchedAnnotation, stream));
          } else if (range.getName().equals(UIMAConstants.TYPE_SHORT)) {
            structure.setShortValue(targetFeature, (short) ((INumberExpression) valueObject)
                    .getIntegerValue(parent, matchedAnnotation, stream));
          } else if (range.getName().equals(UIMAConstants.TYPE_LONG)) {
            structure.setLongValue(targetFeature, (long) ((INumberExpression) valueObject)
                    .getIntegerValue(parent, matchedAnnotation, stream));
          }
        } else if (valueObject instanceof IBooleanExpression
                && range.getName().equals(UIMAConstants.TYPE_BOOLEAN)) {
          structure.setBooleanValue(targetFeature, ((IBooleanExpression) valueObject)
                  .getBooleanValue(parent, matchedAnnotation, stream));
        } else if (valueObject instanceof TypeExpression) {
          TypeExpression type = (TypeExpression) valueObject;
          List<AnnotationFS> annotationsInWindow = stream.getAnnotationsInWindow(matchedAnnotation,
                  type.getType(parent));
          if (typeSystem.subsumes(jcas.getCasType(FSArray.type), range)) {
            structure
                    .setFeatureValue(targetFeature, UIMAUtils.toFSArray(jcas, annotationsInWindow));
          } else if (typeSystem.subsumes(range, type.getType(parent))
                  && !annotationsInWindow.isEmpty()) {
            AnnotationFS annotation = annotationsInWindow.get(0);
            structure.setFeatureValue(targetFeature, annotation);
          }
        }
      }

    }
  }
}
