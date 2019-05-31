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
import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.annotation.IAnnotationExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationListExpression;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.expression.type.ITypeListExpression;
import org.apache.uima.ruta.rule.MatchContext;

public class AnnotationTypeExpression extends RutaExpression
        implements ITypeExpression, IAnnotationExpression, IAnnotationListExpression {

  private MatchReference reference;

  private ITypeExpression typeExpression;

  private ITypeListExpression typeListExpression;

  private IAnnotationExpression annotationExpression;

  private IAnnotationListExpression annotationListExpression;

  private FeatureExpression featureExpression;

  private boolean initialized = false;

  public AnnotationTypeExpression(MatchReference reference) {
    super();
    this.reference = reference;
  }

  private void initialize(MatchContext context, RutaStream stream) {
    annotationExpression = reference.getAnnotationExpression(context, stream);
    annotationListExpression = reference.getAnnotationListExpression(context, stream);
    featureExpression = reference.getFeatureExpression(context, stream);
    typeExpression = reference.getTypeExpression(context, stream);
    typeListExpression = reference.getTypeListExpression(context, stream);
    initialized = true;
  }

  @Override
  public AnnotationFS getAnnotation(MatchContext context, RutaStream stream) {
    if (!initialized) {
      initialize(context, stream);
    }
    if (annotationExpression != null) {
      AnnotationFS annotation = annotationExpression.getAnnotation(context, stream);
      if (featureExpression != null) {
        List<AnnotationFS> annotations = new ArrayList<>(1);
        annotations.add(annotation);
        Collection<? extends AnnotationFS> result = featureExpression.getAnnotations(annotations,
                true, context, stream);
        if (result != null && !result.isEmpty()) {
          return result.iterator().next();
        }
        return null;
      } else {
        return annotation;
      }
    } else if (annotationListExpression != null) {
      List<AnnotationFS> annotations = annotationListExpression.getAnnotationList(context, stream);

      if (annotations != null && !annotations.isEmpty()) {
        if (featureExpression != null) {
          Collection<? extends AnnotationFS> result = featureExpression.getAnnotations(annotations,
                  true, context, stream);
          if (result.isEmpty()) {
            return null;
          }
          annotations = new ArrayList<>(result);
        }
        if (context.getDirection()) {
          return annotations.get(annotations.size() - 1);
        } else {
          return annotations.get(0);
        }
      }
    } else {

      List<Type> types = null;
      if (typeListExpression != null) {
        types = typeListExpression.getTypeList(context, stream);
      } else {
        Type type = getType(context, stream);
        types = new ArrayList<>(1);
        if (type != null) {
          types.add(type);
        }
      }
      for (Type type : types) {

        if (type != null) {

          List<AnnotationFS> bestGuessedAnnotations = null;

          if (featureExpression instanceof FeatureMatchExpression) {
            // allow more matches for feature matches
            bestGuessedAnnotations = stream.getAnnotationsByTypeInContext(type, context);
          } else if (featureExpression != null) {
            bestGuessedAnnotations = stream.getBestGuessedAnnotationsAt(context.getAnnotation(),
                    type);
          } else {
            bestGuessedAnnotations = stream.getBestGuessedAnnotationsAt(context.getAnnotation(),
                    type);
            if (bestGuessedAnnotations.isEmpty()) {
              bestGuessedAnnotations = new ArrayList<>(1);
              bestGuessedAnnotations.add(stream.getSingleAnnotationByTypeInContext(type, context));
            }
          }

          if (featureExpression != null) {
            Collection<AnnotationFS> annotations = new ArrayList<>();
            annotations.addAll(bestGuessedAnnotations);
            Collection<? extends AnnotationFS> featureAnnotations = featureExpression
                    .getAnnotations(annotations, true, context, stream);
            if (featureAnnotations != null && !featureAnnotations.isEmpty()) {
              return featureAnnotations.iterator().next();
            }
          }
          if (bestGuessedAnnotations != null && !bestGuessedAnnotations.isEmpty()) {
            return bestGuessedAnnotations.get(0);
          }
        }
      }
    }
    return null;
  }

  @Override
  public FeatureStructure getFeatureStructure(MatchContext context, RutaStream stream) {
    if (annotationExpression != null) {
      return annotationExpression.getFeatureStructure(context, stream);
    }
    return getAnnotation(context, stream);
  }

  @Override
  public Type getType(MatchContext context, RutaStream stream) {
    if (!initialized) {
      initialize(context, stream);
    }

    if (typeExpression == null && typeListExpression == null && annotationExpression == null
            && annotationListExpression == null) {
      return null;
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
    if (annotationListExpression != null) {
      return annotationListExpression.getStringValue(context, stream);
    } else if (annotationExpression != null) {
      return annotationExpression.getStringValue(context, stream);
    } else if (typeListExpression != null) {
      return typeListExpression.getStringValue(context, stream);
    } else if (typeExpression != null) {
      return typeExpression.getStringValue(context, stream);
    }
    return "";
  }

  @Override
  public List<AnnotationFS> getAnnotationList(MatchContext context, RutaStream stream) {
    if (!initialized) {
      initialize(context, stream);
    }
    if (annotationListExpression != null) {
      List<AnnotationFS> result = annotationListExpression.getAnnotationList(context, stream);
      if (featureExpression != null) {
        return new ArrayList<>(featureExpression.getAnnotations(result, true, context, stream));
      } else {
        return result;
      }
    } else if (annotationExpression != null) {
      AnnotationFS annotation = annotationExpression.getAnnotation(context, stream);
      List<AnnotationFS> result = new ArrayList<AnnotationFS>(1);
      result.add(annotation);
      if (featureExpression != null) {
        return new ArrayList<>(featureExpression.getAnnotations(result, true, context, stream));
      } else {
        return result;
      }
    } else {

      List<Type> types = null;
      if (typeListExpression != null) {
        types = typeListExpression.getTypeList(context, stream);
      } else {
        Type type = getType(context, stream);
        types = new ArrayList<>(1);
        types.add(type);
      }

      List<AnnotationFS> annotations = new ArrayList<>();

      for (Type type : types) {
        if (type != null) {

          List<AnnotationFS> bestGuessedAnnotations = null;

          if (featureExpression instanceof FeatureMatchExpression) {
            // allow more matches for feature matches
            bestGuessedAnnotations = stream.getAnnotationsByTypeInContext(type, context);
          } else if (featureExpression != null) {
            bestGuessedAnnotations = stream.getBestGuessedAnnotationsAt(context.getAnnotation(),
                    type);
            if (bestGuessedAnnotations.isEmpty()) {
              bestGuessedAnnotations = stream.getAnnotationsByTypeInContext(type, context);
            }
          } else {
            bestGuessedAnnotations = stream.getAnnotationsByTypeInContext(type, context);
          }

          if (featureExpression != null) {
            Collection<? extends AnnotationFS> featureAnnotations = featureExpression
                    .getAnnotations(bestGuessedAnnotations, true, context, stream);
            if (featureAnnotations != null && !featureAnnotations.isEmpty()) {
              annotations.addAll(featureAnnotations);
            }
          } else {
            if (bestGuessedAnnotations != null && !bestGuessedAnnotations.isEmpty()) {
              annotations.addAll(bestGuessedAnnotations);
            }
          }
        }
      }

      return annotations;
    }
  }

  public MatchReference getReference() {
    return reference;
  }

  public void setReference(MatchReference reference) {
    this.reference = reference;
  }

  public ITypeExpression getTypeExpression() {
    return typeExpression;
  }

  public ITypeListExpression getTypeListExpression() {
    return typeListExpression;
  }

  public IAnnotationExpression getAnnotationExpression() {
    return annotationExpression;
  }

  public IAnnotationListExpression getAnnotationListExpression() {
    return annotationListExpression;
  }

  public FeatureExpression getFeatureExpression() {
    return featureExpression;
  }

  @Override
  public String toString() {
    return reference.toString();
  }

}
