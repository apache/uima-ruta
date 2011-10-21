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

package org.apache.uima.textmarker.rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import org.apache.uima.cas.ConstraintFactory;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FSMatchConstraint;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.TextMarkerBlock;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.constraint.BasicTypeConstraint;
import org.apache.uima.textmarker.expression.TextMarkerExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.type.TextMarkerBasic;

public class TextMarkerTypeMatcher implements TextMarkerMatcher {

  private final TypeExpression expression;

  private AnnotationComparator comparator;

  public TextMarkerTypeMatcher(TypeExpression expression) {
    super();
    this.expression = expression;
    this.comparator = new AnnotationComparator();
  }

  public Collection<AnnotationFS> getMatchingAnnotations(TextMarkerStream stream,
          TextMarkerBlock parent) {

    Collection<AnnotationFS> result = new TreeSet<AnnotationFS>(comparator);
    List<Type> types = getTypes(parent, stream);
    for (Type type : types) {
      String name = type.getName();
      if ("uima.tcas.DocumentAnnotation".equals(name)
              || "org.apache.uima.textmarker.type.Document".equals(name)
              || (stream.getDocumentAnnotationType().getName().equals(name) && stream
                      .getFirstBasicOfAll().beginsWith(type))) {
        // TODO what about dynamic windowing?
        result.add(stream.getDocumentAnnotation());

      } else {
        FSIterator<AnnotationFS> iterator = stream.getFilter().createFilteredIterator(
                stream.getCas(), type);

        // AnnotationIndex<AnnotationFS> annotationIndex = stream.getCas().getAnnotationIndex(type);
        // stream.getCas().createFilteredIterator(annotationIndex.iterator(),
        // stream.getFilter().createFilteredIterator(null, stream, type));
        // FSMatchConstraint anchorConstraint = createAnchorConstraints(parent, stream);
        // FSIterator<AnnotationFS> iterator = stream.getFilteredBasicIterator(anchorConstraint);
        // iterator.moveToFirst();
        while (iterator.isValid()) {
          AnnotationFS annotation = iterator.get();
          result.add(annotation);
          iterator.moveToNext();
        }
      }
    }
    return result;
  }

  @Override
  public Collection<AnnotationFS> getAnnotationsAfter(TextMarkerRuleElement ruleElement,
          AnnotationFS annotation, TextMarkerStream stream, TextMarkerBlock parent) {
    TextMarkerBasic lastBasic = stream.getEndAnchor(annotation.getEnd());
    stream.moveTo(lastBasic);
    stream.moveToNext();
    if (stream.isValid()) {
      TextMarkerBasic nextBasic = (TextMarkerBasic) stream.get();
      // TODO also child types!
      List<Type> reTypes = ruleElement.getMatcher().getTypes(parent, stream);

      Collection<AnnotationFS> anchors = new TreeSet<AnnotationFS>(new AnnotationComparator());
      for (Type eachMatchType : reTypes) {

        List<Type> types = stream.getCas().getTypeSystem().getProperlySubsumedTypes(eachMatchType);
        types.add(eachMatchType);
        for (Type eachType : types) {
          Collection<AnnotationFS> beginAnchors = nextBasic.getBeginAnchors(eachType);
          if (beginAnchors != null) {
            anchors.addAll(beginAnchors);
          }
        }
      }
      return anchors;
    }
    return Collections.emptyList();
  }

  @Override
  public Collection<AnnotationFS> getAnnotationsBefore(TextMarkerRuleElement ruleElement,
          AnnotationFS annotation, TextMarkerStream stream, TextMarkerBlock parent) {
    TextMarkerBasic firstBasic = stream.getBeginAnchor(annotation.getBegin());
    stream.moveTo(firstBasic);
    stream.moveToPrevious();
    if (stream.isValid()) {
      TextMarkerBasic nextBasic = (TextMarkerBasic) stream.get();
      List<Type> reTypes = ruleElement.getMatcher().getTypes(parent, stream);
      Collection<AnnotationFS> anchors = new TreeSet<AnnotationFS>(new AnnotationComparator());
      for (Type eachMatchType : reTypes) {
        List<Type> types = stream.getCas().getTypeSystem().getProperlySubsumedTypes(eachMatchType);
        types.add(eachMatchType);
        for (Type eachType : types) {
          Collection<AnnotationFS> endAnchors = nextBasic.getEndAnchors(eachType);
          if (endAnchors != null) {
            anchors.addAll(endAnchors);
          }
        }
      }
      return anchors;

    }
    return Collections.emptyList();
  }

  public FSMatchConstraint createAnchorConstraints(TextMarkerBlock block, TextMarkerStream stream) {
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

  public boolean match(AnnotationFS annotation, TextMarkerStream stream, TextMarkerBlock parent) {
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

  public TextMarkerExpression getExpression() {
    return expression;
  }

  protected Type getType(TypeExpression expression, TextMarkerBlock parent, TextMarkerStream stream) {
    Type type = expression.getType(parent);
    if ("uima.tcas.DocumentAnnotation".equals(type.getName())) {
      return stream.getDocumentAnnotationType();
    }
    return type;
  }

  @Override
  public int estimateAnchors(TextMarkerBlock parent, TextMarkerStream stream) {
    return stream.getHistogram(getType(expression, parent, stream));
  }

  @Override
  public List<Type> getTypes(TextMarkerBlock parent, TextMarkerStream stream) {
    List<Type> result = new ArrayList<Type>(1);
    Type type = getType(expression, parent, stream);
    result.add(type);
    return result;
  }

}
