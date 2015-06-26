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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class ImplicitCondition extends AbstractRutaCondition {

  private IRutaExpression expr;

  public ImplicitCondition(IRutaExpression expr) {
    super();
    this.expr = expr;
  }

  @Override
  public EvaluatedCondition eval(AnnotationFS annotation, RuleElement element, RutaStream stream,
          InferenceCrowd crowd) {
    if (expr instanceof IBooleanExpression) {
      IBooleanExpression be = (IBooleanExpression) expr;
      return new EvaluatedCondition(this, be.getBooleanValue(element.getParent(), annotation, stream));
    } else if (expr instanceof FeatureMatchExpression) {
      FeatureMatchExpression fme = (FeatureMatchExpression) expr;
      TypeExpression typeExpr = fme.getTypeExpr(element.getParent());
      Type type = typeExpr.getType(element.getParent());
      List<AnnotationFS> annotations = getAnnotationsToCheck(annotation, type, fme, stream);
      Collection<AnnotationFS> featureAnnotations = fme.getFeatureAnnotations(annotations, stream,
              element.getParent(), true);
      return new EvaluatedCondition(this, !featureAnnotations.isEmpty());
    }
    return new EvaluatedCondition(this, false);
  }

  private List<AnnotationFS> getAnnotationsToCheck(AnnotationFS annotation, Type type,
          FeatureMatchExpression fme, RutaStream stream) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    TypeSystem typeSystem = stream.getCas().getTypeSystem();
    if (typeSystem.subsumes(type, annotation.getType())) {
      result.add(annotation);
    } else {
      Collection<AnnotationFS> beginAnchors = stream.getBeginAnchor(annotation.getBegin())
              .getBeginAnchors(type);
      Collection<AnnotationFS> endAnchors = stream.getEndAnchor(annotation.getEnd()).getEndAnchors(type);
      @SuppressWarnings("unchecked")
      Collection<AnnotationFS> intersection = CollectionUtils
              .intersection(beginAnchors, endAnchors);
      result.addAll(intersection);
    }
    return result;
  }

  public IRutaExpression getExpr() {
    return expr;
  }

  public void setExpr(IRutaExpression expr) {
    this.expr = expr;
  }
}
