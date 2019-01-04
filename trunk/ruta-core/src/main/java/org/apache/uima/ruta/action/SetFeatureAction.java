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
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
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
  public void execute(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
    RuleMatch match = context.getRuleMatch();
    RuleElement element = context.getElement();
    element.getParent();
    String featureString = featureStringExpression.getStringValue(context, stream);
    List<AnnotationFS> matchedAnnotations = match.getMatchedAnnotationsOfElement(element);
    for (AnnotationFS annotationFS : matchedAnnotations) {
      Feature feature = annotationFS.getType().getFeatureByBaseName(featureString);
      if (feature != null) {
        stream.getCas().removeFsFromIndexes(annotationFS);
        stream.assignFeatureValue(annotationFS, feature, expr, context);
        stream.getCas().addFsToIndexes(annotationFS);
      } else {
        throw new IllegalArgumentException("Not able to assign feature value (e.g., coveredText) in script "+context.getParent().getName());
      }
    }
  }

  public IRutaExpression getExpr() {
    return expr;
  }
}
