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

import org.apache.uima.cas.ConstraintFactory;
import org.apache.uima.cas.FSMatchConstraint;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.UIMAConstants;
import org.apache.uima.ruta.constraint.BasicTypeConstraint;
import org.apache.uima.ruta.expression.MatchReference;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.bool.BooleanExpression;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.number.NumberExpression;
import org.apache.uima.ruta.expression.string.StringExpression;
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
      String name = type.getName();
      RutaBasic firstBasicOfAll = stream.getFirstBasicOfAll();
      if ("uima.tcas.DocumentAnnotation".equals(name)
              || "org.apache.uima.ruta.type.Document".equals(name)
              || (stream.getDocumentAnnotationType().getName().equals(name) && (firstBasicOfAll != null && firstBasicOfAll
                      .beginsWith(type)))) {
        // TODO what about dynamic windowing?
        annotations.add(stream.getDocumentAnnotation());
      } else {
        stream.moveToFirst();
        while (stream.isValid()) {
          RutaBasic nextBasic = (RutaBasic) stream.get();
          List<Type> allTypes = stream.getCas().getTypeSystem().getProperlySubsumedTypes(type);
          allTypes.add(type);
          for (Type eachType : allTypes) {
            Collection<AnnotationFS> beginAnchors = nextBasic.getBeginAnchors(eachType);
            if (beginAnchors != null) {
              for (AnnotationFS afs : beginAnchors) {
                if (afs.getEnd() <= stream.getDocumentAnnotation().getEnd()) {
                  annotations.add(afs);
                }
              }
            }
          }
          stream.moveToNext();
        }
      }
    }
    FeatureExpression featureExpression = mr.getFeatureExpression(parent, stream);
    if (featureExpression != null) {
      return getFeatureAnnotations(annotations, featureExpression, stream, parent);
    } else {
      return annotations;
    }
  }

  private Collection<AnnotationFS> getFeatureAnnotations(Collection<AnnotationFS> annotations,
          FeatureExpression featureExpression, RutaStream stream, RutaBlock parent) {
    Collection<AnnotationFS> result = new TreeSet<AnnotationFS>(comparator);
    List<Feature> features = featureExpression.getFeatures(parent);
    for (AnnotationFS eachBase : annotations) {
      AnnotationFS afs = eachBase;
      for (Feature feature : features) {
        if (feature.getRange().isPrimitive() && featureExpression instanceof FeatureMatchExpression) {
          FeatureMatchExpression fme = (FeatureMatchExpression) featureExpression;
          RutaExpression arg = fme.getArg();
          if (CHECK_ON_FEATURE) {
            if (checkFeatureValue(afs, feature, arg, stream, parent)) {
              result.add(afs);
            }
          } else {
            result.add(afs);
          }
          break;
        } else {
          FeatureStructure value = afs.getFeatureValue(feature);
          afs = (AnnotationFS) value;
        }
      }
      if (!(featureExpression instanceof FeatureMatchExpression)) {
        result.add(afs);
      }
    }
    return result;
  }

  public Collection<AnnotationFS> getAnnotationsAfter(RutaRuleElement ruleElement,
          AnnotationFS annotation, RutaStream stream, RutaBlock parent) {
    RutaBasic lastBasic = stream.getEndAnchor(annotation.getEnd());
    if (lastBasic == null) {
      return Collections.emptyList();
    }
    stream.moveTo(lastBasic);
    if (stream.isVisible(lastBasic)) {
      stream.moveToNext();
    }
    if (stream.isValid()) {
      RutaBasic nextBasic = (RutaBasic) stream.get();
      List<Type> reTypes = getTypes(parent, stream);
      Collection<AnnotationFS> anchors = new TreeSet<AnnotationFS>(comparator);
      for (Type eachMatchType : reTypes) {
        List<Type> types = stream.getCas().getTypeSystem().getProperlySubsumedTypes(eachMatchType);
        types.add(eachMatchType);
        for (Type eachType : types) {
          Collection<AnnotationFS> beginAnchors = nextBasic.getBeginAnchors(eachType);
          if (beginAnchors != null) {
            for (AnnotationFS afs : beginAnchors) {
              if (afs.getEnd() <= stream.getDocumentAnnotation().getEnd()) {
                anchors.add(afs);
              }
            }
          }
        }
      }
      FeatureExpression fm = mr.getFeatureExpression(parent, stream);
      if (fm != null) {
        return getFeatureAnnotations(anchors, fm, stream, parent);
      } else {
        return anchors;
      }
    }
    return Collections.emptyList();
  }

  public Collection<AnnotationFS> getAnnotationsBefore(RutaRuleElement ruleElement,
          AnnotationFS annotation, RutaStream stream, RutaBlock parent) {
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
      List<Type> reTypes = getTypes(parent, stream);
      Collection<AnnotationFS> anchors = new TreeSet<AnnotationFS>(comparator);
      for (Type eachMatchType : reTypes) {
        List<Type> types = stream.getCas().getTypeSystem().getProperlySubsumedTypes(eachMatchType);
        types.add(eachMatchType);
        for (Type eachType : types) {
          Collection<AnnotationFS> endAnchors = nextBasic.getEndAnchors(eachType);
          if (endAnchors != null) {
            for (AnnotationFS afs : endAnchors) {
              if (afs.getBegin() >= stream.getDocumentAnnotation().getBegin()) {
                anchors.add(afs);
              }
            }
          }
        }
      }
      FeatureExpression fm = mr.getFeatureExpression(parent, stream);
      if (fm != null) {
        return getFeatureAnnotations(anchors, fm, stream, parent);
      } else {
        return anchors;
      }
    }
    return Collections.emptyList();
  }

  public FSMatchConstraint createAnchorConstraints(RutaBlock block, RutaStream stream) {
    ConstraintFactory cf = stream.getCas().getConstraintFactory();
    List<Type> types = getTypes(block, stream);
    FSMatchConstraint result = null;

    for (Type eachType : types) {
      BasicTypeConstraint anchorConstraint = new BasicTypeConstraint(cf.createTypeConstraint(),
              eachType);
      anchorConstraint.add(eachType);
      if (result != null) {
        result = cf.or(result, anchorConstraint);
      } else {
        result = anchorConstraint;
      }
    }
    return result;
  }

  public boolean match(AnnotationFS annotation, RutaStream stream, RutaBlock parent) {
    if (annotation == null) {
      return false;
    }
    FeatureExpression featureExpression = mr.getFeatureExpression(parent, stream);
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
    FeatureExpression fe = mr.getFeatureExpression(parent, stream);
    Feature feature = fe.getFeature(parent);
    if (fe instanceof FeatureMatchExpression) {
      FeatureMatchExpression fme = (FeatureMatchExpression) fe;
      boolean checkFeatureValue = checkFeatureValue(annotation, feature, fme.getArg(), stream,
              parent);
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

  public int estimateAnchors(RutaBlock parent, RutaStream stream) {
    TypeExpression typeExpression = mr.getTypeExpression(parent, stream);
    return stream.getHistogram(getType(typeExpression, parent, stream));
  }

  public List<Type> getTypes(RutaBlock parent, RutaStream stream) {
    List<Type> result = new ArrayList<Type>(1);
    TypeExpression typeExpression = mr.getTypeExpression(parent, stream);
    Type type = getType(typeExpression, parent, stream);
    result.add(type);
    return result;
  }

  private boolean checkFeatureValue(AnnotationFS afs, Feature feature, RutaExpression arg,
          RutaStream stream, RutaBlock parent) {
    String rn = feature.getRange().getName();
    if (rn.equals(UIMAConstants.TYPE_BOOLEAN)) {
      boolean v1 = afs.getBooleanValue(feature);
      if (arg instanceof BooleanExpression) {
        BooleanExpression expr = (BooleanExpression) arg;
        boolean v2 = expr.getBooleanValue(parent);
        return v1 == v2;
      }
    } else if (rn.equals(UIMAConstants.TYPE_INTEGER) || rn.equals(UIMAConstants.TYPE_BYTE)
            || rn.equals(UIMAConstants.TYPE_SHORT) || rn.equals(UIMAConstants.TYPE_LONG)) {
      Integer v1 = afs.getIntValue(feature);
      if (arg instanceof NumberExpression) {
        NumberExpression expr = (NumberExpression) arg;
        Integer v2 = expr.getIntegerValue(parent);
        return v1.equals(v2);
      }
    } else if (rn.equals(UIMAConstants.TYPE_DOUBLE)) {
      Double v1 = afs.getDoubleValue(feature);
      if (arg instanceof NumberExpression) {
        NumberExpression expr = (NumberExpression) arg;
        Double v2 = expr.getDoubleValue(parent);
        return v1.equals(v2);
      }
    } else if (rn.equals(UIMAConstants.TYPE_FLOAT)) {
      Float v1 = afs.getFloatValue(feature);
      if (arg instanceof NumberExpression) {
        NumberExpression expr = (NumberExpression) arg;
        Float v2 = expr.getFloatValue(parent);
        return v1.equals(v2);
      }
    } else if (rn.equals(UIMAConstants.TYPE_STRING)) {
      String v1 = afs.getStringValue(feature);
      if (arg instanceof StringExpression) {
        StringExpression expr = (StringExpression) arg;
        String v2 = expr.getStringValue(parent);
        return v1.equals(v2);
      }
    }
    return false;
  }
}
