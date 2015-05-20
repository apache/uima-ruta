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

package org.apache.uima.ruta.expression;

import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;

public class NullExpression extends FeatureExpression implements IStringExpression, ITypeExpression{

  @Override
  public String getStringValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    return null;
  }

  @Override
  public String getStringValue(RutaBlock parent, RuleMatch match, RuleElement element,
          RutaStream stream) {
    return null;
  }

  @Override
  public Feature getFeature(RutaBlock parent) {
    return null;
  }

  @Override
  public List<Feature> getFeatures(RutaBlock parent) {
    return null;
  }

  @Override
  public List<String> getFeatureStringList(RutaBlock parent) {
    return null;
  }

  @Override
  public TypeExpression getTypeExpr(RutaBlock parent) {
    return null;
  }

  @Override
  public Collection<AnnotationFS> getFeatureAnnotations(Collection<AnnotationFS> annotations,
          RutaStream stream, RutaBlock parent, boolean checkOnFeatureValue) {
    return null;
  }

  @Override
  public Type getType(RutaBlock parent) {
    return null;
  }

}
