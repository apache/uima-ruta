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

package org.apache.uima.ruta.expression.type;

import org.apache.uima.cas.Type;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.rule.MatchContext;

public class TypeFeatureExpression extends AbstractTypeExpression {

  private FeatureExpression fe;

  public TypeFeatureExpression(FeatureExpression fe) {

    super();
    this.fe = fe;
  }

  @Override
  public Type getType(MatchContext context, RutaStream stream) {

    return null;
  }

  public FeatureExpression getFe() {

    return this.fe;
  }

  public void setFe(FeatureExpression fe) {

    this.fe = fe;
  }

  @Override
  public String getStringValue(MatchContext context, RutaStream stream) {
    Type type = getType(context, stream);
    if (type != null) {
      return type.getName();
    }
    return null;
  }

}
