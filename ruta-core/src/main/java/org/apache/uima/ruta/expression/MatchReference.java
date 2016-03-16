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

import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaEnvironment;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.annotation.AnnotationLabelExpression;
import org.apache.uima.ruta.expression.annotation.AnnotationListLabelExpression;
import org.apache.uima.ruta.expression.annotation.AnnotationListVariableExpression;
import org.apache.uima.ruta.expression.annotation.AnnotationListVariableIndexExpression;
import org.apache.uima.ruta.expression.annotation.AnnotationVariableExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationListExpression;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.expression.feature.SimpleFeatureExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.expression.type.SimpleTypeExpression;
import org.apache.uima.ruta.expression.type.TypeVariableExpression;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.utils.IndexedReference;
import org.apache.uima.ruta.utils.ParsingUtils;

public class MatchReference extends RutaExpression {

  private String reference;

  private ITypeExpression typeExpression;

  private IAnnotationExpression annotationExpression;

  private IAnnotationListExpression annotationListExpression;

  private FeatureExpression featureExpression;

  private List<String> features = Collections.emptyList();

  private boolean initialized = false;

  public MatchReference(String reference) {
    super();
    this.reference = reference;
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
        featureExpression = new SimpleFeatureExpression(this);
        features = tail;
      }
    }
    initialized = true;
    if (typeExpression == null && annotationExpression == null && annotationListExpression == null) {
      throw new IllegalArgumentException("Not able to resolve annotation/type expression: "
              + reference);
    }
  }

  private boolean buildTypeOrAnnotationExpression(String candidate, RutaEnvironment e,
        MatchContext context, RutaStream stream) {
    
    IndexedReference indexedReference = ParsingUtils.parseIndexedReference(candidate);    
    if (indexedReference.index >= 0) {
      if (e.isVariableOfType(candidate, "ANNOTATIONLIST")) {
        annotationExpression = new AnnotationListVariableIndexExpression(indexedReference.reference, indexedReference.index);
        return true;
      }

    } else {

      if (e.isVariableOfType(candidate, "TYPE")) {
        typeExpression = new TypeVariableExpression(candidate);
        return true;
      } else if (e.isVariableOfType(candidate, "ANNOTATION")) {
        annotationExpression = new AnnotationVariableExpression(candidate);
        return true;
      } else if (e.isVariableOfType(candidate, "ANNOTATIONLIST")) {
        annotationListExpression = new AnnotationListVariableExpression(candidate);
        return true;
      } else if (e.getType(candidate) != null) {
        typeExpression = new SimpleTypeExpression(candidate);
        return true;
      } else if (context.getRuleMatch() != null) {
        RuleMatch ruleMatch = context.getRuleMatch();
        RuleElement ruleElementWithLabel = ruleMatch.getRule().getRuleElementWithLabel(candidate);
        if (ruleElementWithLabel != null) {
          annotationExpression = new AnnotationLabelExpression(candidate);
          annotationListExpression = new AnnotationListLabelExpression(candidate);
          return true;
        }
      }
    }
    return false;
  }

  public ITypeExpression getTypeExpression(MatchContext context, RutaStream stream) {
    resolve(context, stream);
    return typeExpression;
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

  public String toString() {
    return reference;
  }

  public String getMatch() {
    return reference;
  }

  public List<String> getFeatureList() {
    return features;
  }

}
