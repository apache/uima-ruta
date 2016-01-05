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
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.UIMAConstants;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.MatchReference;
import org.apache.uima.ruta.expression.NullExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.rule.AnnotationComparator;
import org.apache.uima.ruta.rule.MatchContext;

public class SimpleFeatureExpression extends FeatureExpression {

  private MatchReference mr;

  private ITypeExpression typeExpr;

  private List<String> features;

  protected AnnotationComparator comparator = new AnnotationComparator();

  public SimpleFeatureExpression(ITypeExpression te, List<String> featureReferences) {
    super();
    this.typeExpr = te;
    this.features = featureReferences;
  }

  public SimpleFeatureExpression(MatchReference mr) {
    super();
    this.mr = mr;
  }

  @Override
  public Feature getFeature(MatchContext context, RutaStream stream) {
    List<Feature> features = getFeatures(context, stream);
    if (features != null && !features.isEmpty()) {
      return features.get(features.size() - 1);
    } else {
      return null;
    }
  }

  @Override
  public List<Feature> getFeatures(MatchContext context, RutaStream stream) {
    if (mr != null) {
      typeExpr = mr.getTypeExpression(context, stream);
      FeatureExpression featureExpression = mr.getFeatureExpression(context, stream);
      if (featureExpression == null) {
        return null;
      }
      features = featureExpression.getFeatureStringList(context, stream);
    }
    List<Feature> result = new ArrayList<Feature>();
    Type type = typeExpr.getType(context, stream);
    Feature feature = null;
    for (String each : features) {
      if (StringUtils.equals(each, UIMAConstants.FEATURE_COVERED_TEXT)) {
        // there is no explicit feature for coveredText
        feature = null;
      } else if(type.isArray()) {
        // lazy check of range
        feature = null;
      } else {
        feature = type.getFeatureByBaseName(each);
        if (feature == null) {
          if (!StringUtils.equals(each, UIMAConstants.FEATURE_COVERED_TEXT_SHORT))
            throw new IllegalArgumentException("Not able to access feature " + each + " of type "
                    + type.getName());
        }
      }
      result.add(feature);
      if (feature != null) {
        type = feature.getRange();
      }
    }
    return result;
  }

  public ITypeExpression getTypeExpr(MatchContext context, RutaStream stream) {
    if (mr != null) {
      return mr.getTypeExpression(context, stream);
    }
    return typeExpr;
  }

  public void setTypeExpr(ITypeExpression typeExpr) {
    this.typeExpr = typeExpr;
  }

  public List<String> getFeatureStringList(MatchContext context, RutaStream stream) {
    if (mr != null) {
      features = mr.getFeatureExpression(context, stream).getFeatureStringList(context, stream);
    }
    return features;
  }

  public void setFeatures(List<String> features) {
    this.features = features;
  }

  public Collection<AnnotationFS> getFeatureAnnotations(Collection<AnnotationFS> annotations,
          RutaStream stream, MatchContext context, boolean checkOnFeatureValue) {

    Collection<AnnotationFS> result = new TreeSet<AnnotationFS>(comparator);
    List<Feature> features = getFeatures(context, stream);
    collectFeatureAnnotations(annotations, features, checkOnFeatureValue, result, stream, context);
    return result;
  }

  private void collectFeatureAnnotations(Collection<AnnotationFS> annotations,
          List<Feature> features, boolean checkOnFeatureValue, Collection<AnnotationFS> result,
          RutaStream stream, MatchContext context) {
    for (AnnotationFS each : annotations) {
      collectFeatureAnnotations(each, features, checkOnFeatureValue, result, stream, context);
    }
  }

  private void collectFeatureAnnotations(AnnotationFS annotation, List<Feature> features,
          boolean checkOnFeatureValue, Collection<AnnotationFS> result, RutaStream stream,
          MatchContext context) {
    if (annotation == null) {
      return;
    }

    Feature currentFeature = null;
    List<Feature> tail = null;

    if (features != null && !features.isEmpty()) {
      currentFeature = features.get(0);
      tail = features.subList(1, features.size());
    }

    if (currentFeature == null || currentFeature.getRange().isPrimitive()) {
      // feature == null -> this is the coveredText "feature"
      if (this instanceof FeatureMatchExpression) {
        FeatureMatchExpression fme = (FeatureMatchExpression) this;
        if (checkOnFeatureValue) {
          if (fme.checkFeatureValue(annotation, context, currentFeature, stream)) {
            result.add(annotation);
          }
        } else {
          result.add(annotation);
        }
      } else {
        result.add(annotation);
      }
    } else {
      collectFeatureAnnotations(annotation, currentFeature, tail, checkOnFeatureValue, result, stream, context);
    }
  }

  private void collectFeatureAnnotations(AnnotationFS annotation, Feature currentFeature,
          List<Feature> tail, boolean checkOnFeatureValue, Collection<AnnotationFS> result,
          RutaStream stream, MatchContext context) {
    // stop early for match expressions
    if (this instanceof FeatureMatchExpression && (tail== null || tail.isEmpty())) {
      FeatureMatchExpression fme = (FeatureMatchExpression) this;
      if (checkOnFeatureValue) {
        if (fme.checkFeatureValue(annotation, context, currentFeature, stream)) {
          result.add(annotation);
        }
      } else {
        result.add(annotation);
      }
      return;
    }
    
    FeatureStructure value = annotation.getFeatureValue(currentFeature);
    if (value instanceof AnnotationFS) {
      AnnotationFS next = (AnnotationFS) value;
      collectFeatureAnnotations(next, tail, checkOnFeatureValue, result, stream, context);
    } else if (value instanceof FSArray) {
      FSArray array = (FSArray) value;
      for (int i = 0; i < array.size(); i++) {
        // TODO: also feature structures or only annotations?
        FeatureStructure fs = array.get(i);
        if (fs instanceof AnnotationFS) {
          AnnotationFS next = (AnnotationFS) fs;
          collectFeatureAnnotations(next, tail, checkOnFeatureValue, result, stream, context);
        }
      }
    } else if (value != null) {
      throw new IllegalArgumentException(value.getType()
              + " is not supported in a feature match expression (" + mr.getMatch() + ").");
    }
  }

  public MatchReference getMatchReference() {
    return mr;
  }

  public String toString() {
    return mr.getMatch();
  }

}
