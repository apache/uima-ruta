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

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.annotation.IAnnotationExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationListExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.rule.MatchContext;

public class AnnotationTypeExpression extends RutaExpression implements ITypeExpression,
        IAnnotationExpression, IAnnotationListExpression {

  private MatchReference reference;

  private ITypeExpression typeExpression;

  private IAnnotationExpression annotationExpression;

  private IAnnotationListExpression annotationListExpression;

  private boolean initialized = false;

  public AnnotationTypeExpression(MatchReference reference) {
    super();
    this.reference = reference;
  }

  private void initialize(MatchContext context, RutaStream stream) {
    annotationExpression = reference.getAnnotationExpression(context, stream);
    annotationListExpression = reference.getAnnotationListExpression(context, stream);
    typeExpression = reference.getTypeExpression(context, stream);
    initialized = true;
  }

  @Override
  public AnnotationFS getAnnotation(MatchContext context, RutaStream stream) {
    if (!initialized) {
      initialize(context, stream);
    }
    if (annotationExpression != null) {
      return annotationExpression.getAnnotation(context, stream);
    } else if (annotationListExpression != null) {
      List<AnnotationFS> annotations = annotationListExpression.getAnnotationList(context, stream);
      if (annotations != null && !annotations.isEmpty())
        return annotations.get(0);
    } else {
      Type type = getType(context, stream);
      if (type != null) {
        return stream.getSingleAnnotationByTypeInContext(type, context);
      }
    }
    return null;
  }

  @Override
  public Type getType(MatchContext context, RutaStream stream) {
    if (!initialized) {
      initialize(context, stream);
    }
    if (typeExpression != null) {
      return typeExpression.getType(context, stream);
    } else {
      AnnotationFS annotation = getAnnotation(context, stream);
      if (annotation != null) {
        return annotation.getType();
      }
    }
    return null;
  }

  @Override
  public String getStringValue(MatchContext context, RutaStream stream) {
    if (!initialized) {
      initialize(context, stream);
    }
    if (annotationExpression != null) {
      return annotationExpression.getStringValue(context, stream);
    } else {
      return typeExpression.getStringValue(context, stream);
    }
  }

  @Override
  public List<AnnotationFS> getAnnotationList(MatchContext context, RutaStream stream) {
    if (!initialized) {
      initialize(context, stream);
    }
    if (annotationListExpression != null) {
      return annotationListExpression.getAnnotationList(context, stream);
    } else if (annotationExpression != null) {
      List<AnnotationFS> result = new ArrayList<AnnotationFS>(1);
      result.add(annotationExpression.getAnnotation(context, stream));
      return result;
    } else {
      Type type = getType(context, stream);
      if (type != null) {
        return stream.getAnnotationsByTypeInContext(type, context);
      }
    }
    return null;
  }

  public MatchReference getReference() {
    return reference;
  }

  public void setReference(MatchReference reference) {
    this.reference = reference;
  }

}
