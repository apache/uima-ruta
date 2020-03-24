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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.uima.ruta.RutaConstants;
import org.apache.uima.ruta.RutaEnvironment;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.expression.annotation.AnnotationListDelegateVariableExpression;
import org.apache.uima.ruta.expression.annotation.AnnotationListVariableExpression;
import org.apache.uima.ruta.expression.annotation.AnnotationListVariableIndexExpression;
import org.apache.uima.ruta.expression.annotation.AnnotationVariableExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationListExpression;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.feature.SimpleFeatureExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.expression.type.ITypeListExpression;
import org.apache.uima.ruta.expression.type.SimpleTypeExpression;
import org.apache.uima.ruta.expression.type.TypeListVariableExpression;
import org.apache.uima.ruta.expression.type.TypeVariableExpression;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.utils.IndexedReference;
import org.apache.uima.ruta.utils.ParsingUtils;

public class MatchReference extends RutaExpression {

  private String reference;

  private String comparator;

  private IRutaExpression argument;

  private ITypeExpression typeExpression;

  private ITypeListExpression typeListExpression;

  private IAnnotationExpression annotationExpression;

  private IAnnotationListExpression annotationListExpression;

  private FeatureExpression featureExpression;

  private List<String> features = Collections.emptyList();

  private boolean initialized = false;

  public MatchReference(String reference) {
    this(reference, null, null);
  }

  public MatchReference(String reference, String comparator, IRutaExpression argument) {
    super();
    this.reference = reference;
    this.comparator = comparator;
    this.argument = argument;
  }

  public MatchReference(ITypeExpression expression) {
    super();
    this.typeExpression = expression;
    initialized = true;
  }

  public MatchReference(ITypeListExpression expression) {
    super();
    this.typeListExpression = expression;
    initialized = true;
  }

  public MatchReference(IAnnotationExpression expression) {
    super();
    this.annotationExpression = expression;
    initialized = true;
  }

  public MatchReference(IAnnotationListExpression expression) {
    super();
    this.annotationListExpression = expression;
    initialized = true;
  }

  private void resolve(MatchContext context, RutaStream stream) {
    if (initialized) {
      return;
    }
    RutaBlock parent = context.getParent();
    RutaEnvironment e = parent.getEnvironment();

    boolean success = buildTypeOrAnnotationExpression(reference, e, context, stream);
    if (!success) {
      String[] elements = reference.split("[.]");
      StringBuilder sb = new StringBuilder();
      List<String> tail = null;
      int counter = 0;
      for (String eachPart : elements) {
        if (sb.length() != 0) {
          sb.append('.');
        }
        sb.append(eachPart);
        String head = sb.toString();
        success = buildTypeOrAnnotationExpression(head, e, context, stream);
        if (success) {
          tail = Arrays.asList(elements).subList(counter + 1, elements.length);
          break;
        }
        counter++;
      }
      if (tail != null) {
        if (comparator != null && argument != null) {
          featureExpression = new FeatureMatchExpression(this, comparator, argument);
        } else {
          featureExpression = new SimpleFeatureExpression(this);
        }
        features = tail;
      }
    }
    if (featureExpression == null && comparator != null && argument != null) {
      featureExpression = new FeatureMatchExpression(this, comparator, argument);
    }

    initialized = true;
    if (typeExpression == null && typeListExpression == null && annotationExpression == null
            && annotationListExpression == null) {
      throw new IllegalArgumentException("Not able to resolve annotation/type expression: "
              + reference + " in script " + context.getParent().getName());
    }
  }

  private boolean buildTypeOrAnnotationExpression(String candidate, RutaEnvironment environment,
          MatchContext context, RutaStream stream) {

    IndexedReference indexedReference = ParsingUtils.parseIndexedReference(candidate);
    if (indexedReference.index >= 0) {
      if (environment.isVariableOfType(candidate, RutaConstants.RUTA_VARIABLE_ANNOTATION_LIST)) {
        annotationExpression = new AnnotationListVariableIndexExpression(indexedReference.reference,
                indexedReference.index);
        return true;
      }

    } else {
      if (environment.isVariableOfType(candidate, RutaConstants.RUTA_VARIABLE_TYPE)) {
        typeExpression = new TypeVariableExpression(candidate);
        return true;
      } else if (environment.isVariableOfType(candidate, RutaConstants.RUTA_VARIABLE_TYPE_LIST)) {
        typeListExpression = new TypeListVariableExpression(candidate);
        return true;
      } else if (environment.isVariableOfType(candidate, RutaConstants.RUTA_VARIABLE_ANNOTATION)) {
        annotationExpression = new AnnotationVariableExpression(candidate);
        annotationListExpression = new AnnotationListDelegateVariableExpression(candidate);
        return true;
      } else if (environment.isVariableOfType(candidate,
              RutaConstants.RUTA_VARIABLE_ANNOTATION_LIST)) {
        annotationListExpression = new AnnotationListVariableExpression(candidate);
        return true;
      } else if (environment.getType(candidate) != null) {
        typeExpression = new SimpleTypeExpression(candidate);
        return true;
      }
    }
    return false;
  }

  public ITypeExpression getTypeExpression(MatchContext context, RutaStream stream) {
    resolve(context, stream);
    return typeExpression;
  }

  public ITypeListExpression getTypeListExpression(MatchContext context, RutaStream stream) {
    resolve(context, stream);
    return typeListExpression;
  }

  public IAnnotationExpression getAnnotationExpression(MatchContext context, RutaStream stream) {
    resolve(context, stream);
    return annotationExpression;
  }

  public IAnnotationListExpression getAnnotationListExpression(MatchContext context,
          RutaStream stream) {
    resolve(context, stream);
    return annotationListExpression;
  }

  public FeatureExpression getFeatureExpression(MatchContext context, RutaStream stream) {
    resolve(context, stream);
    return featureExpression;
  }

  @Override
  public String toString() {
    if (reference != null) {
      if (comparator != null && argument != null) {
        return reference + comparator + argument.toString();
      }
      return reference;
    }
    if (typeExpression != null) {
      return typeExpression.toString();
    }
    if (typeListExpression != null) {
      return typeListExpression.toString();
    }
    if (annotationExpression != null) {
      return annotationExpression.toString();
    }
    if (annotationListExpression != null) {
      return annotationListExpression.toString();
    }
    return super.toString();
  }

  public String getMatch() {
    return reference;
  }

  public List<String> getFeatureList() {
    return features;
  }

  public IRutaExpression getArgument() {
    return argument;
  }

  public String getComparator() {
    return comparator;
  }

}
