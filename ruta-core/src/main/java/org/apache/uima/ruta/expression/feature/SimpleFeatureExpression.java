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

package org.apache.uima.ruta.expression.feature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.expression.type.TypeExpression;

public class SimpleFeatureExpression extends FeatureExpression {

  private TypeExpression typeExpr;
  private List<String> features;

  public SimpleFeatureExpression(TypeExpression te, List<String> featureReferences) {
    super();
    this.typeExpr = te;
    this.features = featureReferences;
  }

  public SimpleFeatureExpression(TypeExpression te, String[] featureReferences) {
    this(te, Arrays.asList(featureReferences));
  }
  
  @Override
  public Feature getFeature(RutaStatement parent) {
    return getFeatures(parent).get(0);
  }
  
  @Override
  public List<Feature> getFeatures(RutaStatement parent) {
    List<Feature> result = new ArrayList<Feature>();
    Type type = typeExpr.getType(parent);
    Feature feature = null;
    for (String each : features) {
      feature = type.getFeatureByBaseName(each);
      result.add(feature);
      type = feature.getRange();
    }
    return result;
  }
  
  public TypeExpression getTypeExpr() {
    return typeExpr;
  }

  public void setTypeExpr(TypeExpression typeExpr) {
    this.typeExpr = typeExpr;
  }

  public List<String> getFeatureStringList() {
    return features;
  }

  public void setFeatures(List<String> features) {
    this.features = features;
  }


  
}
