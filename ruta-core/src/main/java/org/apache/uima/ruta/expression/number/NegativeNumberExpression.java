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

package org.apache.uima.ruta.expression.number;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;

public class NegativeNumberExpression extends NumberExpression {

  private final NumberExpression ne;

  public NegativeNumberExpression(NumberExpression simpleNumberExpression) {
    super();
    this.ne = simpleNumberExpression;
  }

  @Override
  public double getDoubleValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    return -ne.getDoubleValue(parent, annotation, stream);
  }

  @Override
  public float getFloatValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    return -ne.getFloatValue(parent, annotation, stream);
  }

  @Override
  public int getIntegerValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    return -ne.getIntegerValue(parent, annotation, stream);
  }

  @Override
  public String getStringValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    return "-" + ne.getStringValue(parent, annotation, stream);
  }

  public NumberExpression getExpression() {
    return ne;
  }

}
