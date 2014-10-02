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

import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;

public abstract class FeatureExpression extends RutaExpression {

  public abstract Feature getFeature(RutaBlock parent);

  public abstract List<Feature> getFeatures(RutaBlock parent);

  public abstract List<String> getFeatureStringList(RutaBlock parent);

  public abstract TypeExpression getTypeExpr(RutaBlock parent);

  public abstract Collection<AnnotationFS> getFeatureAnnotations(
          Collection<AnnotationFS> annotations, RutaStream stream, RutaBlock parent,
          boolean checkOnFeatureValue);

}
