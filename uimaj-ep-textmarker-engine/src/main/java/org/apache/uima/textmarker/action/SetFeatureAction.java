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

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.rule.TextMarkerRuleElement;
import org.apache.uima.textmarker.utils.UIMAUtils;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class SetFeatureAction extends AbstractTextMarkerAction {

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
  public void execute(RuleMatch match, RuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<Type> types = new ArrayList<Type>();
    if (element instanceof TextMarkerRuleElement) {
      types = ((TextMarkerRuleElement) element).getMatcher().getTypes(element.getParent(), stream);
    }
    if (types == null)
      return;

    for (Type type : types) {
      String featureString = featureStringExpression.getStringValue(element.getParent());
      Feature featureByBaseName = type.getFeatureByBaseName(featureString);
      List<AnnotationFS> matchedAnnotations = match.getMatchedAnnotationsOf(element, stream);
      for (AnnotationFS annotationFS : matchedAnnotations) {

        if (annotationFS.getType().getFeatureByBaseName(featureString) == null) {
          System.out.println("Can't access feature " + featureString
                  + ", because it's not defined in the matched type: " + annotationFS.getType());
          return;
        }
        stream.getCas().removeFsFromIndexes(annotationFS);
        if (stringExpr != null) {
          String string = stringExpr.getStringValue(element.getParent());
          annotationFS.setStringValue(featureByBaseName, string);
        } else if (numberExpr != null) {
          String range = featureByBaseName.getRange().getName();
          if (range.equals("uima.cas.Integer")) {
            int v = numberExpr.getIntegerValue(element.getParent());
            annotationFS.setIntValue(featureByBaseName, v);
          } else if (range.equals("uima.cas.Double")) {
            double v = numberExpr.getDoubleValue(element.getParent());
            annotationFS.setDoubleValue(featureByBaseName, v);
          }
        } else if (booleanExpr != null) {
          boolean v = booleanExpr.getBooleanValue(element.getParent());
          annotationFS.setBooleanValue(featureByBaseName, v);
        } else if (typeExpr != null) {
          Type t = typeExpr.getType(element.getParent());
          List<AnnotationFS> inWindow = stream.getAnnotationsInWindow(annotationFS, t);
          if (featureByBaseName.getRange().isArray()) {
            annotationFS.setFeatureValue(featureByBaseName,
                    UIMAUtils.toFSArray(stream.getJCas(), inWindow));
          } else {
            AnnotationFS annotation = inWindow.get(0);
            annotationFS.setFeatureValue(featureByBaseName, annotation);
          }
        }
        stream.getCas().addFsToIndexes(annotationFS);
      }
    }
  }

}
