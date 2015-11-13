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

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.annotation.IAnnotationExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.rule.MatchContext;

public class AnnotationTypeExpression extends RutaExpression implements ITypeExpression, IAnnotationExpression {
 
  
  private MatchReference reference;
  
  private ITypeExpression typeExpression;
  
  private IAnnotationExpression annotationExpression;

  private boolean initialized = false;
  
  public AnnotationTypeExpression(MatchReference reference) {
    super();
    this.reference = reference;
  }

  private void initialize(MatchContext context, RutaStream stream) {
    annotationExpression = reference.getAnnotationExpression(context, stream);
    typeExpression = reference.getTypeExpression(context, stream);
    initialized = true;
  }
  
  
  @Override
  public AnnotationFS getAnnotation(MatchContext context, RutaStream stream) {
    if (!initialized) {
      initialize(context, stream);
    }
    return annotationExpression.getAnnotation(context, stream);
  }

  @Override
  public Type getType(MatchContext context, RutaStream stream) {
    if (!initialized) {
      initialize(context, stream);
    }
    if(typeExpression != null) {
      return typeExpression.getType(context, stream);
    } else {
      return getAnnotation(context, stream).getType();
    }
  }

  @Override
  public String getStringValue(MatchContext context, RutaStream stream) {
    if (!initialized) {
      initialize(context, stream);
    }
    if(annotationExpression != null) {
      return annotationExpression.getStringValue(context, stream);
    } else {
      return typeExpression.getStringValue(context, stream);
    }
  }

  
  
  
}
