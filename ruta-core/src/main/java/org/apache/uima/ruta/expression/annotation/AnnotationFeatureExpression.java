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

package org.apache.uima.ruta.expression.annotation;

import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.rule.MatchContext;

/**
 * An expression referring to an annotation stored in a feature.
 *
 */
public class AnnotationFeatureExpression extends AbstractAnnotationExpression {

  private FeatureExpression fe;

  public AnnotationFeatureExpression(FeatureExpression fe) {
    super();
    this.fe = fe;
  }
  
  @Override
  public AnnotationFS getAnnotation(MatchContext context, RutaStream stream) {
    AnnotationFS annotation = context.getAnnotation();
    Type type = fe.getTypeExpr(context, stream).getType(context, stream);
    Feature feature = fe.getFeature(context, stream);
    List<AnnotationFS> list = getTargetAnnotation(annotation, type, stream);
    Collection<AnnotationFS> featureAnnotations = fe.getFeatureAnnotations(list, stream, context,
            false);
    if (!featureAnnotations.isEmpty()) {
      AnnotationFS next = featureAnnotations.iterator().next();
      FeatureStructure featureValue = next.getFeatureValue(feature);
      if(featureValue instanceof AnnotationFS) {
        return (AnnotationFS) featureValue;
      }
    }
    return null;
  }


  public FeatureExpression getFeatureExpression() {
    return fe;
  }

  public void setFeatureExpression(FeatureExpression fe) {
    this.fe = fe;
  }

  

}
