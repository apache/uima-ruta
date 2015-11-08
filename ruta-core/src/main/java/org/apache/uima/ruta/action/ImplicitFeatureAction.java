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
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.AnnotationComparator;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class ImplicitFeatureAction extends AbstractRutaAction {

  private FeatureMatchExpression expr;

  private Comparator<? super AnnotationFS> comp = new AnnotationComparator();

  public ImplicitFeatureAction(FeatureMatchExpression expr) {
    super();
    this.expr = expr;
  }

  @Override
  public void execute(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
    RuleMatch match = context.getRuleMatch();
    RuleElement element = context.getElement();
    TypeExpression typeExpr = expr.getTypeExpr(context, stream);
    Type type = typeExpr.getType(context, stream);
    List<AnnotationFS> matchedAnnotations = match.getMatchedAnnotationsOfElement(element);
    Collection<AnnotationFS> annotations = new TreeSet<AnnotationFS>(comp);
    for (AnnotationFS annotation : matchedAnnotations) {
      annotations.addAll(getAnnotations(annotation, type, expr, stream));
    }
    for (AnnotationFS each : annotations) {
      stream.getCas().removeFsFromIndexes(each);
    }
    Collection<AnnotationFS> featureAnnotations = expr.getFeatureAnnotations(annotations, stream,
            context, false);
    if (featureAnnotations.isEmpty()) {
      // null value in feature, but we require the host
      featureAnnotations = annotations;
    }
    Feature feature = expr.getFeature(context, stream);
    IRutaExpression arg = expr.getArg();
    for (AnnotationFS each : featureAnnotations) {
      stream.assignFeatureValue(each, feature, arg, context);
    }
    for (AnnotationFS each : annotations) {
      stream.getCas().addFsToIndexes(each);
    }
  }


  private List<AnnotationFS> getAnnotations(AnnotationFS annotation, Type type,
          FeatureMatchExpression fme, RutaStream stream) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    TypeSystem typeSystem = stream.getCas().getTypeSystem();
    if (typeSystem.subsumes(type, annotation.getType())) {
      result.add(annotation);
    } else {
      Collection<AnnotationFS> beginAnchors = stream.getBeginAnchor(annotation.getBegin())
              .getBeginAnchors(type);
      Collection<AnnotationFS> endAnchors = stream.getEndAnchor(annotation.getEnd()).getEndAnchors(
              type);
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
