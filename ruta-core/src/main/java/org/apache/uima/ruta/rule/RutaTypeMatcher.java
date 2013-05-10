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
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.constraint.BasicTypeConstraint;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.type.RutaBasic;

public class RutaTypeMatcher implements RutaMatcher {

  protected final TypeExpression expression;

  protected AnnotationComparator comparator;

  public RutaTypeMatcher(TypeExpression expression) {
    super();
    this.expression = expression;
    this.comparator = new AnnotationComparator();
  }

  public Collection<AnnotationFS> getMatchingAnnotations(RutaStream stream,
          RutaBlock parent) {
    // TODO what about the matching direction?
    Collection<AnnotationFS> result = new TreeSet<AnnotationFS>(comparator);
    List<Type> types = getTypes(parent, stream);
    for (Type type : types) {
      if(type == null) {
        continue;
      }
      String name = type.getName();
      RutaBasic firstBasicOfAll = stream.getFirstBasicOfAll();
      if ("uima.tcas.DocumentAnnotation".equals(name)
              || "org.apache.uima.ruta.type.Document".equals(name)
              || (stream.getDocumentAnnotationType().getName().equals(name) && (firstBasicOfAll != null && firstBasicOfAll
                      .beginsWith(type)))) {
        // TODO what about dynamic windowing?
        result.add(stream.getDocumentAnnotation());
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
                  result.add(afs);
                }
              }
            }
          }
          stream.moveToNext();
        }
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
    if(stream.isVisible(lastBasic)) {
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
      return anchors;
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
    if(stream.isVisible(firstBasic)) {
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
      return anchors;

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

  @Override
  public String toString() {
    return expression.toString();
  }

  public RutaExpression getExpression() {
    return expression;
  }

  protected Type getType(TypeExpression expression, RutaBlock parent, RutaStream stream) {
    Type type = expression.getType(parent);
    if (type != null && "uima.tcas.DocumentAnnotation".equals(type.getName())) {
      return stream.getDocumentAnnotationType();
    }
    return type;
  }

  public int estimateAnchors(RutaBlock parent, RutaStream stream) {
    return stream.getHistogram(getType(expression, parent, stream));
  }

  public List<Type> getTypes(RutaBlock parent, RutaStream stream) {
    List<Type> result = new ArrayList<Type>(1);
    Type type = getType(expression, parent, stream);
    result.add(type);
    return result;
  }

}
