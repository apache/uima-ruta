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
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.feature.FeatureExpression;

public class StringFeatureExpression extends AbstractStringExpression {

  private FeatureExpression fe;

  public StringFeatureExpression(FeatureExpression fe) {
    super();
    this.fe = fe;
  }

  @Override
  public String getStringValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    Type type = fe.getTypeExpr(parent).getType(parent);
    Feature feature = fe.getFeature(parent);
    List<AnnotationFS> list = getTargetAnnotation(annotation, type, stream);
    Collection<AnnotationFS> featureAnnotations = fe.getFeatureAnnotations(list, stream, parent,
            false);
    if (!featureAnnotations.isEmpty()) {
      AnnotationFS next = featureAnnotations.iterator().next();
      if(feature == null) {
        // feature == coveredText
        return next.getCoveredText();
      } else {
        return next.getStringValue(feature);
      }
    }
    return null;
  }

  public FeatureExpression getFe() {
    return fe;
  }

  public void setFe(FeatureExpression fe) {
    this.fe = fe;
  }

}
