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

package org.apache.uima.ruta.rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.MatchReference;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.type.RutaBasic;

public class RutaTypeMatcher implements RutaMatcher {

  private static final boolean CHECK_ON_FEATURE = false;

  protected final MatchReference mr;

  protected AnnotationComparator comparator;

  public RutaTypeMatcher(MatchReference mr) {
    super();
    this.mr = mr;
    this.comparator = new AnnotationComparator();
  }

  public Collection<AnnotationFS> getMatchingAnnotations(RutaStream stream, RutaBlock parent) {
    // TODO what about the matching direction?
    Collection<AnnotationFS> annotations = new TreeSet<AnnotationFS>(comparator);
    List<Type> types = getTypes(parent, stream);
    for (Type type : types) {
      if (type == null) {
        continue;
      }
      Type currentDAType = stream.getCas().getDocumentAnnotation().getType();
      String name = type.getName();
      RutaBasic firstBasicOfAll = stream.getFirstBasicOfAll();
      if ("uima.tcas.DocumentAnnotation".equals(name)
              || "org.apache.uima.ruta.type.Document".equals(name)
              || currentDAType.equals(type)
//              || (stream.getDocumentAnnotationType().getName().equals(name) && (firstBasicOfAll != null && firstBasicOfAll
//                      .beginsWith(type)))
                      ) {
        // TODO what about dynamic windowing?
        annotations.add(stream.getDocumentAnnotation());
      } else {
        annotations.addAll(stream.getAnnotations(type));
      }
    }
    FeatureExpression featureExpression = mr.getFeatureExpression(parent);
    if (featureExpression != null) {
      return featureExpression.getFeatureAnnotations(annotations, stream, parent, CHECK_ON_FEATURE);
    } else {
      return annotations;
    }
  }

  public Collection<AnnotationFS> getAnnotationsAfter(RutaRuleElement ruleElement,
          AnnotationFS annotation, RutaStream stream, RutaBlock parent) {
    if(annotation.getEnd() == stream.getDocumentAnnotation().getEnd()) {
      return Collections.emptyList();
    }
    RutaBasic lastBasic = stream.getEndAnchor(annotation.getEnd());
    int end = 0;
    if (lastBasic == null) {
      if (annotation.getEnd() != 0) {
        return Collections.emptyList();
      }
    } else {
      end = lastBasic.getEnd();
    }
    if (annotation.getEnd() > 0) {
      stream.moveTo(lastBasic);
      if (stream.isVisible(lastBasic) && stream.isValid() && stream.get().getEnd() == lastBasic.getEnd()) {
        stream.moveToNext();
      }
    } else {
      stream.moveToFirst();
    }

    if (stream.isValid()) {
      RutaBasic nextBasic = (RutaBasic) stream.get();
      // TODO HOTFIX for annotation of length 0
      while (stream.isValid() && nextBasic.getBegin() < end) {
        stream.moveToNext();
        if (stream.isValid()) {
          nextBasic = (RutaBasic) stream.get();
        }
      }
      List<Type> reTypes = getTypes(parent, stream);
      Collection<AnnotationFS> anchors = new TreeSet<AnnotationFS>(comparator);
      for (Type eachMatchType : reTypes) {
        Collection<AnnotationFS> beginAnchors = nextBasic.getBeginAnchors(eachMatchType);
        if (beginAnchors != null) {
          for (AnnotationFS afs : beginAnchors) {
            if (afs.getBegin() >= stream.getDocumentAnnotation().getBegin() && afs.getEnd() <= stream.getDocumentAnnotation().getEnd()) {
              anchors.add(afs);
            }
          }
        }
      }
      FeatureExpression fm = mr.getFeatureExpression(parent);
      if (fm != null) {
        return fm.getFeatureAnnotations(anchors, stream, parent, CHECK_ON_FEATURE);
      } else {
        return anchors;
      }
    }
    return Collections.emptyList();
  }

  public Collection<AnnotationFS> getAnnotationsBefore(RutaRuleElement ruleElement,
          AnnotationFS annotation, RutaStream stream, RutaBlock parent) {
    if(annotation.getBegin() == stream.getDocumentAnnotation().getBegin()) {
      return Collections.emptyList();
    }
    RutaBasic firstBasic = stream.getBeginAnchor(annotation.getBegin());
    if (firstBasic == null) {
      return Collections.emptyList();
    }
    stream.moveTo(firstBasic);
    if (stream.isVisible(firstBasic)) {
      stream.moveToPrevious();
    }

    if (stream.isValid()) {
      RutaBasic nextBasic = (RutaBasic) stream.get();
      // TODO HOTFIX for annotation of length 0
      while (stream.isValid() && nextBasic.getEnd() > firstBasic.getBegin()) {
        stream.moveToPrevious();
        if (stream.isValid()) {
          nextBasic = (RutaBasic) stream.get();
        }
      }
      List<Type> reTypes = getTypes(parent, stream);
      Collection<AnnotationFS> anchors = new TreeSet<AnnotationFS>(comparator);
      for (Type eachMatchType : reTypes) {
        Collection<AnnotationFS> endAnchors = nextBasic.getEndAnchors(eachMatchType);
        if (endAnchors != null) {
          for (AnnotationFS afs : endAnchors) {
            if (afs.getBegin() >= stream.getDocumentAnnotation().getBegin()) {
              anchors.add(afs);
            }
          }
        }
      }
      FeatureExpression fm = mr.getFeatureExpression(parent);
      if (fm != null) {
        return fm.getFeatureAnnotations(anchors, stream, parent, CHECK_ON_FEATURE);
      } else {
        return anchors;
      }
    }
    return Collections.emptyList();
  }

  public boolean match(AnnotationFS annotation, RutaStream stream, RutaBlock parent) {
    if (annotation == null) {
      return false;
    }
    FeatureExpression featureExpression = mr.getFeatureExpression(parent);
    if (featureExpression == null) {
      boolean b = checkType(annotation, stream, parent);
      if (b) {
        return true;
      }
    } else {
      boolean b = checkFeature(annotation, stream, parent);
      if (b) {
        return true;
      }
    }

    return false;

  }

  private boolean checkType(AnnotationFS annotation, RutaStream stream, RutaBlock parent) {
    List<Type> types = getTypes(parent, stream);
    for (Type type : types) {
      String name = type.getName();
      if ("uima.tcas.DocumentAnnotation".equals(name)
              || stream.getDocumentAnnotationType().getName().equals(name)) {
        return true;
      }
      boolean b = stream.getJCas().getTypeSystem().subsumes(type, annotation.getType());
      if (b) {
        return true;
      }
    }
    return false;
  }

  private boolean checkFeature(AnnotationFS annotation, RutaStream stream, RutaBlock parent) {
    FeatureExpression fe = mr.getFeatureExpression(parent);
    Feature feature = fe.getFeature(parent);
    if (fe instanceof FeatureMatchExpression) {
      FeatureMatchExpression fme = (FeatureMatchExpression) fe;
      boolean checkFeatureValue = fme.checkFeatureValue(annotation, stream, parent);
      if (checkFeatureValue) {
        return true;
      }
    } else {
      TypeSystem typeSystem = stream.getCas().getTypeSystem();
      boolean subsumes = typeSystem.subsumes(feature.getRange(), annotation.getType());
      if (subsumes) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return mr.toString();
  }

  public RutaExpression getExpression() {
    return mr;
  }

  protected Type getType(TypeExpression expression, RutaBlock parent, RutaStream stream) {
    Type type = expression.getType(parent);
    if (type != null && "uima.tcas.DocumentAnnotation".equals(type.getName())) {
      return stream.getDocumentAnnotationType();
    }
    return type;
  }

  public long estimateAnchors(RutaBlock parent, RutaStream stream) {
    TypeExpression typeExpression = mr.getTypeExpression(parent);
    return stream.getHistogram(getType(typeExpression, parent, stream));
  }

  public List<Type> getTypes(RutaBlock parent, RutaStream stream) {
    List<Type> result = new ArrayList<Type>(1);
    TypeExpression typeExpression = mr.getTypeExpression(parent);
    Type type = getType(typeExpression, parent, stream);
    result.add(type);
    return result;
  }

}
