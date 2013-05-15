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
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.UIMAConstants;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.bool.BooleanExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.number.NumberExpression;
import org.apache.uima.ruta.expression.string.StringExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.AnnotationComparator;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.utils.UIMAUtils;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class ImplicitFeatureAction extends AbstractRutaAction {

  private FeatureMatchExpression expr;

  private Comparator<? super AnnotationFS> comp = new AnnotationComparator();

  public ImplicitFeatureAction(FeatureMatchExpression expr) {
    super();
    this.expr = expr;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, RutaStream stream, InferenceCrowd crowd) {
    TypeExpression typeExpr = expr.getTypeExpr();
    Type type = typeExpr.getType(element.getParent());
    List<AnnotationFS> matchedAnnotations = match.getMatchedAnnotationsOf(element, stream);
    Collection<AnnotationFS> annotations = new TreeSet<AnnotationFS>(comp);
    for (AnnotationFS annotation : matchedAnnotations) {
      annotations.addAll(getAnnotations(annotation, type, expr, stream));
    }
    for (AnnotationFS each : annotations) {
      stream.getCas().removeFsFromIndexes(each);
    }
    Collection<AnnotationFS> featureAnnotations = expr.getFeatureAnnotations(annotations, stream,
            element.getParent(), false);
    Feature feature = expr.getFeature(element.getParent());
    RutaExpression arg = expr.getArg();
    for (AnnotationFS each : featureAnnotations) {
      setFeatureValue(each, feature, arg, element, stream);
    }
    for (AnnotationFS each : annotations) {
      stream.getCas().addFsToIndexes(each);
    }
  }

  private void setFeatureValue(AnnotationFS a, Feature feature, RutaExpression argExpr,
          RuleElement element, RutaStream stream) {
    String range = feature.getRange().getName();
    if (range.equals(UIMAConstants.TYPE_STRING)) {
      if (argExpr instanceof StringExpression) {
        StringExpression stringExpr = (StringExpression) argExpr;
        String string = stringExpr.getStringValue(element.getParent());
        a.setStringValue(feature, string);
      }
    } else if (argExpr instanceof NumberExpression
            && (range.equals(UIMAConstants.TYPE_INTEGER) || range.equals(UIMAConstants.TYPE_LONG)
                    || range.equals(UIMAConstants.TYPE_SHORT) || range
                      .equals(UIMAConstants.TYPE_BYTE))) {
      NumberExpression numberExpr = (NumberExpression) argExpr;
      int v = numberExpr.getIntegerValue(element.getParent());
      a.setIntValue(feature, v);
    } else if (argExpr instanceof NumberExpression && (range.equals(UIMAConstants.TYPE_DOUBLE))) {
      NumberExpression numberExpr = (NumberExpression) argExpr;
      double v = numberExpr.getDoubleValue(element.getParent());
      a.setDoubleValue(feature, v);
    } else if (argExpr instanceof NumberExpression && (range.equals(UIMAConstants.TYPE_FLOAT))) {
      NumberExpression numberExpr = (NumberExpression) argExpr;
      float v = numberExpr.getFloatValue(element.getParent());
      a.setFloatValue(feature, v);
    } else if (argExpr instanceof BooleanExpression && (range.equals(UIMAConstants.TYPE_BOOLEAN))) {
      BooleanExpression booleanExpr = (BooleanExpression) argExpr;
      boolean v = booleanExpr.getBooleanValue(element.getParent());
      a.setBooleanValue(feature, v);
    } else if (argExpr instanceof BooleanExpression && (range.equals(UIMAConstants.TYPE_BOOLEAN))) {
      BooleanExpression booleanExpr = (BooleanExpression) argExpr;
      boolean v = booleanExpr.getBooleanValue(element.getParent());
      a.setBooleanValue(feature, v);
    } else if (argExpr instanceof TypeExpression && !feature.getRange().isPrimitive()) {
      TypeExpression typeExpr = (TypeExpression) argExpr;
      Type t = typeExpr.getType(element.getParent());
      List<AnnotationFS> inWindow = stream.getAnnotationsInWindow(a, t);
      if (feature.getRange().isArray()) {
        a.setFeatureValue(feature, UIMAUtils.toFSArray(stream.getJCas(), inWindow));
      } else {
        AnnotationFS annotation = inWindow.get(0);
        a.setFeatureValue(feature, annotation);
      }
    }
  }

  private List<AnnotationFS> getAnnotations(AnnotationFS annotation, Type type,
          FeatureMatchExpression fme, RutaStream stream) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    TypeSystem typeSystem = stream.getCas().getTypeSystem();
    if (typeSystem.subsumes(type, annotation.getType())) {
      result.add(annotation);
    } else {
      Set<AnnotationFS> beginAnchors = stream.getBeginAnchor(annotation.getBegin())
              .getBeginAnchors(type);
      Set<AnnotationFS> endAnchors = stream.getEndAnchor(annotation.getEnd()).getEndAnchors(type);
      @SuppressWarnings("unchecked")
      Collection<AnnotationFS> intersection = CollectionUtils
              .intersection(beginAnchors, endAnchors);
      result.addAll(intersection);
    }
    return result;
  }

  public FeatureMatchExpression getExpr() {
    return expr;
  }

  public void setExpr(FeatureMatchExpression expr) {
    this.expr = expr;
  }

}
