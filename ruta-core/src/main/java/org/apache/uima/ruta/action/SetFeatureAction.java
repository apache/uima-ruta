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

import java.util.List;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.UIMAConstants;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.feature.GenericFeatureExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.utils.UIMAUtils;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class SetFeatureAction extends AbstractRutaAction {

  private final IStringExpression featureStringExpression;

  private final IRutaExpression expr;

  public SetFeatureAction(IStringExpression feature, IRutaExpression expr) {
    super();
    this.featureStringExpression = feature;
    this.expr = expr;
  }

  public IStringExpression getFeatureStringExpression() {
    return featureStringExpression;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, RutaStream stream, InferenceCrowd crowd) {
    RutaBlock parent = element.getParent();
    String featureString = featureStringExpression.getStringValue(parent, match, element, stream);
    List<AnnotationFS> matchedAnnotations = match.getMatchedAnnotationsOf(element);
    for (AnnotationFS annotationFS : matchedAnnotations) {
      Feature feature = annotationFS.getType().getFeatureByBaseName(featureString);

      if (feature != null) {
        Type range = feature.getRange();
        String rangeName = range.getName();
        stream.getCas().removeFsFromIndexes(annotationFS);
        if (rangeName.equals(UIMAConstants.TYPE_STRING) && expr instanceof IStringExpression) {
          IStringExpression stringExpr = (IStringExpression) expr;
          String string = stringExpr.getStringValue(parent, match, element, stream);
          annotationFS.setStringValue(feature, string);
        } else if (rangeName.equals(UIMAConstants.TYPE_INTEGER)
                && expr instanceof INumberExpression) {
          INumberExpression numberExpr = (INumberExpression) expr;
          int v = numberExpr.getIntegerValue(parent, match, element, stream);
          annotationFS.setIntValue(feature, v);
        } else if (rangeName.equals(UIMAConstants.TYPE_DOUBLE) && expr instanceof INumberExpression) {
          INumberExpression numberExpr = (INumberExpression) expr;
          double v = numberExpr.getDoubleValue(parent, match, element, stream);
          annotationFS.setDoubleValue(feature, v);
        } else if (rangeName.equals(UIMAConstants.TYPE_FLOAT) && expr instanceof INumberExpression) {
          INumberExpression numberExpr = (INumberExpression) expr;
          float v = (float) numberExpr.getFloatValue(parent, match, element, stream);
          annotationFS.setFloatValue(feature, v);
        } else if (rangeName.equals(UIMAConstants.TYPE_BYTE) && expr instanceof INumberExpression) {
          INumberExpression numberExpr = (INumberExpression) expr;
          byte v = (byte) numberExpr.getIntegerValue(parent, match, element, stream);
          annotationFS.setByteValue(feature, v);
        } else if (rangeName.equals(UIMAConstants.TYPE_SHORT) && expr instanceof INumberExpression) {
          INumberExpression numberExpr = (INumberExpression) expr;
          short v = (short) numberExpr.getIntegerValue(parent, match, element, stream);
          annotationFS.setShortValue(feature, v);
        } else if (rangeName.equals(UIMAConstants.TYPE_LONG) && expr instanceof INumberExpression) {
          INumberExpression numberExpr = (INumberExpression) expr;
          long v = numberExpr.getIntegerValue(parent, match, element, stream);
          annotationFS.setLongValue(feature, v);
        } else if (rangeName.equals(UIMAConstants.TYPE_BOOLEAN)
                && expr instanceof IBooleanExpression) {
          IBooleanExpression booleanExpr = (IBooleanExpression) expr;
          boolean v = booleanExpr.getBooleanValue(parent, match, element, stream);
          annotationFS.setBooleanValue(feature, v);
        } else if (expr instanceof ITypeExpression) {
          ITypeExpression typeExpr = (ITypeExpression) expr;
          Type t = typeExpr.getType(parent);
          List<AnnotationFS> inWindow = stream.getAnnotationsInWindow(annotationFS, t);
          if (feature.getRange().isArray()) {
            annotationFS.setFeatureValue(feature, UIMAUtils.toFSArray(stream.getJCas(), inWindow));
          } else {
            if (inWindow != null) {
              AnnotationFS annotation = inWindow.get(0);
              annotationFS.setFeatureValue(feature, annotation);
            } else {
              annotationFS.setFeatureValue(feature, null);
            }
          }
        } else if(expr instanceof GenericFeatureExpression) {
          TypeExpression typeExpr = ((GenericFeatureExpression) expr).getFeatureExpression()
                  .getTypeExpr(parent);
          Type t = typeExpr.getType(parent);
          List<AnnotationFS> inWindow = stream.getAnnotationsInWindow(annotationFS, t);
          if (feature.getRange().isArray()) {
            annotationFS.setFeatureValue(feature, UIMAUtils.toFSArray(stream.getJCas(), inWindow));
          } else {
            AnnotationFS annotation = inWindow.get(0);
            annotationFS.setFeatureValue(feature, annotation);
          }
        }
        stream.getCas().addFsToIndexes(annotationFS);
      } else {
        throw new IllegalArgumentException("Not able to assign feature value (e.g., coveredText).");
      }
    }
  }

  public IRutaExpression getExpr() {
    return expr;
  }
}
