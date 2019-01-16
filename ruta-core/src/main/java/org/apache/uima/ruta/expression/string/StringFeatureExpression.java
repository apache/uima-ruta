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

package org.apache.uima.ruta.expression.string;

import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.feature.CoveredTextFeature;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.expression.feature.LazyFeature;
import org.apache.uima.ruta.rule.MatchContext;

public class StringFeatureExpression extends AbstractStringExpression {

  private FeatureExpression fe;

  public StringFeatureExpression(FeatureExpression fe) {

    super();
    this.fe = fe;
  }

  @Override
  public String getStringValue(MatchContext context, RutaStream stream) {

    AnnotationFS annotation = context.getAnnotation();
    Feature feature = this.fe.getFeature(context, stream);
    List<AnnotationFS> list = this.getTargetAnnotation(annotation, this.fe, context, stream);
    Collection<? extends FeatureStructure> featureStructures = this.fe.getFeatureStructures(list,
            false, context, stream);
    if (!featureStructures.isEmpty()) {
      FeatureStructure next = featureStructures.iterator().next();
      // if (next instanceof AnnotationFS && !next.getType().equals(annotation.getType())) {
      // feature = this.fe.getFeature(new MatchContext((AnnotationFS) next, context.getElement(),
      // context.getRuleMatch(), context.getDirection()), stream);
      // }
      if (feature instanceof LazyFeature) {
        LazyFeature lazyFeature = (LazyFeature) feature;
        feature = lazyFeature.initialize(next);
      }
      if (next instanceof AnnotationFS && feature instanceof CoveredTextFeature) {
        return ((AnnotationFS) next).getCoveredText();
      } else {
        return next.getStringValue(feature);
      }
    }
    return null;
  }

  public FeatureExpression getFe() {

    return this.fe;
  }

  public void setFe(FeatureExpression fe) {

    this.fe = fe;
  }

}
