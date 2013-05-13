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

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.UIMAConstants;
import org.apache.uima.ruta.expression.bool.BooleanExpression;
import org.apache.uima.ruta.expression.number.NumberExpression;
import org.apache.uima.ruta.expression.string.StringExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.rule.RutaRuleElement;
import org.apache.uima.ruta.utils.UIMAUtils;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class SetFeatureAction extends AbstractRutaAction {

  private final StringExpression featureStringExpression;

  private StringExpression stringExpr;

  private NumberExpression numberExpr;

  private BooleanExpression booleanExpr;

  private TypeExpression typeExpr;

  protected SetFeatureAction(StringExpression feature) {
    super();
    this.featureStringExpression = feature;
  }

  public SetFeatureAction(StringExpression feature, StringExpression stringExpr) {
    this(feature);
    this.stringExpr = stringExpr;
  }

  public SetFeatureAction(StringExpression feature, NumberExpression numberExpr) {
    this(feature);
    this.numberExpr = numberExpr;
  }

  public SetFeatureAction(StringExpression feature, BooleanExpression booleanExpr) {
    this(feature);
    this.booleanExpr = booleanExpr;
  }

  public SetFeatureAction(StringExpression feature, TypeExpression typeExpr) {
    this(feature);
    this.typeExpr = typeExpr;
  }

  public StringExpression getFeatureStringExpression() {
    return featureStringExpression;
  }

  public StringExpression getStringExpr() {
    return stringExpr;
  }

  public NumberExpression getNumberExpr() {
    return numberExpr;
  }

  public BooleanExpression getBooleanExpr() {
    return booleanExpr;
  }

  public TypeExpression getTypeExpr() {
    return typeExpr;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, RutaStream stream,
          InferenceCrowd crowd) {
    String featureString = featureStringExpression.getStringValue(element.getParent());
    List<AnnotationFS> matchedAnnotations = match.getMatchedAnnotationsOf(element, stream);
    for (AnnotationFS annotationFS : matchedAnnotations) {
      Feature feature = annotationFS.getType().getFeatureByBaseName(featureString);
      if(feature != null) {
        stream.getCas().removeFsFromIndexes(annotationFS);
        if (stringExpr != null) {
          String string = stringExpr.getStringValue(element.getParent());
          annotationFS.setStringValue(feature, string);
        } else if (numberExpr != null) {
          String range = feature.getRange().getName();
          if (range.equals(UIMAConstants.TYPE_INTEGER)) {
            int v = numberExpr.getIntegerValue(element.getParent());
            annotationFS.setIntValue(feature, v);
          } else if (range.equals(UIMAConstants.TYPE_DOUBLE)) {
            double v = numberExpr.getDoubleValue(element.getParent());
            annotationFS.setDoubleValue(feature, v);
          } else if (range.equals(UIMAConstants.TYPE_FLOAT)) {
            float v = (float) numberExpr.getFloatValue(element.getParent());
            annotationFS.setFloatValue(feature, v);
          } else if (range.equals(UIMAConstants.TYPE_BYTE)) {
            byte v = (byte) numberExpr.getIntegerValue(element.getParent());
            annotationFS.setByteValue(feature, v);
          } else if (range.equals(UIMAConstants.TYPE_SHORT)) {
            short v = (short) numberExpr.getIntegerValue(element.getParent());
            annotationFS.setShortValue(feature, v);
          } else if (range.equals(UIMAConstants.TYPE_LONG)) {
            long v = numberExpr.getIntegerValue(element.getParent());
            annotationFS.setLongValue(feature, v);
          }
        } else if (booleanExpr != null) {
          boolean v = booleanExpr.getBooleanValue(element.getParent());
          annotationFS.setBooleanValue(feature, v);
        } else if (typeExpr != null) {
          Type t = typeExpr.getType(element.getParent());
          List<AnnotationFS> inWindow = stream.getAnnotationsInWindow(annotationFS, t);
          if (feature.getRange().isArray()) {
            annotationFS.setFeatureValue(feature,
                    UIMAUtils.toFSArray(stream.getJCas(), inWindow));
          } else {
            AnnotationFS annotation = inWindow.get(0);
            annotationFS.setFeatureValue(feature, annotation);
          }
        }
        stream.getCas().addFsToIndexes(annotationFS);
      }
    }
  }
}
