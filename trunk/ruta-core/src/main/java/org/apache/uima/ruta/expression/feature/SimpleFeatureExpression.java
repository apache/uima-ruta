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
import org.apache.uima.ruta.expression.MatchReference;
import org.apache.uima.ruta.expression.annotation.IAnnotationExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.rule.AnnotationComparator;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.utils.IndexedReference;
import org.apache.uima.ruta.utils.ParsingUtils;

public class SimpleFeatureExpression extends FeatureExpression {

  private MatchReference mr;

  protected AnnotationComparator comparator = new AnnotationComparator();

  public SimpleFeatureExpression(MatchReference mr) {
    super();
    this.mr = mr;
  }

  @Override
  public Feature getFeature(MatchContext context, RutaStream stream) {
    List<Feature> features = getFeatures(context, stream);
    if (features != null && !features.isEmpty()) {
      Feature feature = features.get(features.size() - 1);
      if (feature instanceof LazyFeature) {
        LazyFeature lazyFeature = (LazyFeature) feature;
        AnnotationFS annotation = context.getAnnotation();
        List<AnnotationFS> targetAnnotation = getTargetAnnotation(annotation, this, context, stream);
        if(targetAnnotation != null && !targetAnnotation.isEmpty()) {
          annotation = targetAnnotation.get(0);
        }
        feature = lazyFeature.initialize(annotation);
      }
      return feature;
    } else {
      return null;
    }
  }

  @Override
  public List<Feature> getFeatures(MatchContext context, RutaStream stream) {
    List<Feature> result = new ArrayList<Feature>();
    Type type = getInitialType(context, stream);
    Feature feature = null;
    for (String each : getFeatureStringList(context, stream)) {
      IndexedReference indexedReference = ParsingUtils.parseIndexedReference(each);
      if (indexedReference.index != -1) {
        Feature delegate = type.getFeatureByBaseName(indexedReference.reference);
        if (delegate != null) {
          feature = new IndexedFeature(delegate, indexedReference.index);
        } else {
          throw new IllegalArgumentException(
                  "Not able to access feature " + each + " of type " + type.getName());
        }
      } else if (StringUtils.equals(each, UIMAConstants.FEATURE_COVERED_TEXT)
              || StringUtils.equals(each, UIMAConstants.FEATURE_COVERED_TEXT_SHORT)) {
        if(type != null) {
          feature = type.getFeatureByBaseName(each);
        }
        if (feature == null) {
          // there is no explicit feature for coveredText
          feature = new CoveredTextFeature();
        }
      } else if (type == null || type.isArray()) {
        // lazy check of range
        feature = new LazyFeature(each);
      } else {
        feature = type.getFeatureByBaseName(each);
        if (feature == null) {
          throw new IllegalArgumentException(
                  "Not able to access feature " + each + " of type " + type.getName());
        }
      }
      result.add(feature);
      if (feature instanceof LazyFeature) {
        type = null;
      } else if (feature != null) {
        type = feature.getRange();
      }
    }
    return result;
  }

  @Override
  public Type getInitialType(MatchContext context, RutaStream stream) {
    ITypeExpression typeExpression = mr.getTypeExpression(context, stream);
    if (typeExpression != null) {
      return typeExpression.getType(context, stream);
    } else {
      IAnnotationExpression annotationExpression = mr.getAnnotationExpression(context, stream);
      if (annotationExpression != null) {
        AnnotationFS annotation = annotationExpression.getAnnotation(context, stream);
        if (annotation != null) {
          return annotation.getType();
        }
      }
    }
    return null;
  }

  public List<String> getFeatureStringList(MatchContext context, RutaStream stream) {
    return mr.getFeatureList();
  }

  public Collection<AnnotationFS> getFeatureAnnotations(Collection<AnnotationFS> annotations,
          RutaStream stream, MatchContext context, boolean checkOnFeatureValue) {

    Collection<AnnotationFS> result = new TreeSet<AnnotationFS>(comparator);
    List<Feature> features = getFeatures(context, stream);
    if (features != null && !features.isEmpty()) {
      collectFeatureAnnotations(annotations, features, checkOnFeatureValue, result, stream,
              context);
      return result;
    } else {
      return annotations;
    }
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
      if (currentFeature instanceof LazyFeature) {
        LazyFeature lazyFeature = (LazyFeature) currentFeature;
        Feature delegate = lazyFeature.initialize(annotation);
        if (delegate == null) {
          throw new RuntimeException("Invalid feature! Feature '" + lazyFeature.getFeatureName()
                  + "' is not defined for type '" + annotation.getType() + "'.");
        } else {
          currentFeature = delegate;
        }
      }
      tail = features.subList(1, features.size());
    }

    if (currentFeature == null || currentFeature instanceof CoveredTextFeature
            || currentFeature.getRange().isPrimitive()) {
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
      collectFeatureAnnotations(annotation, currentFeature, tail, checkOnFeatureValue, result,
              stream, context);
    }
  }

  private void collectFeatureAnnotations(AnnotationFS annotation, Feature currentFeature,
          List<Feature> tail, boolean checkOnFeatureValue, Collection<AnnotationFS> result,
          RutaStream stream, MatchContext context) {
    // stop early for match expressions
    if (this instanceof FeatureMatchExpression && (tail == null || tail.isEmpty())) {
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

    int index = -1;
    if (currentFeature instanceof IndexedFeature) {
      IndexedFeature indexedFeature = (IndexedFeature) currentFeature;
      currentFeature = indexedFeature.getDelegate();
      index = indexedFeature.getIndex();
    }

    FeatureStructure value = annotation.getFeatureValue(currentFeature);
    if (value instanceof AnnotationFS) {
      AnnotationFS next = (AnnotationFS) value;
      collectFeatureAnnotations(next, tail, checkOnFeatureValue, result, stream, context);
    } else if (value instanceof FSArray && index >= 0) {
      FSArray array = (FSArray) value;
      if (index < array.size()) {
        FeatureStructure fs = array.get(index);
        if (fs instanceof AnnotationFS) {
          AnnotationFS next = (AnnotationFS) fs;
          collectFeatureAnnotations(next, tail, checkOnFeatureValue, result, stream, context);
        }
      }
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
      result.add(annotation);
      // primitive? -> return last annotation for further processing
      // throw new IllegalArgumentException(value.getType()
      // + " is not supported in a feature match expression (" + mr.getMatch() + ").");
    }
  }

  public MatchReference getMatchReference() {
    return mr;
  }

  public String toString() {
    return mr.getMatch();
  }

}
