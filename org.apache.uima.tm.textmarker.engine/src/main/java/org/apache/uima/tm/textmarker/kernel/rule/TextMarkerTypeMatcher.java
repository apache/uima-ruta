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

package org.apache.uima.tm.textmarker.kernel.rule;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.ConstraintFactory;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FSTypeConstraint;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.tm.textmarker.kernel.TextMarkerBlock;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.constraint.BasicTypeConstraint;
import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;


public class TextMarkerTypeMatcher implements TextMarkerMatcher {

  private final TypeExpression expression;

  public TextMarkerTypeMatcher(TypeExpression expression) {
    super();
    this.expression = expression;
  }

  public FSIterator<AnnotationFS> getMatchingBasics2(TextMarkerStream stream, TextMarkerBlock parent) {
    String name = getType(parent, stream).getName();
    if ("uima.tcas.DocumentAnnotation".equals(name)
            || "uima.tcas.DocumentAnnotation".equals(name)
            || (stream.getDocumentAnnotationType().getName().equals(name) && stream
                    .getFirstBasicOfAll().isAnchorOf(name))) {
      TextMarkerBasic firstBasic = stream.getFirstBasicOfAll();
      if (firstBasic != null) {
        return new DummyFSIterator(firstBasic);
      } else {
        FSIterator<AnnotationFS> it = stream.getUnfilteredBasicIterator();
        it.moveToFirst();
        if (it.isValid()) {
          TextMarkerBasic first = (TextMarkerBasic) it.get();
          return new DummyFSIterator(first);
        }
      }
      return new DummyFSIterator(null);
    } else {
      FSTypeConstraint anchorConstraint = createAnchorConstraints(parent, stream);
      FSIterator<AnnotationFS> iterator = stream.getFilteredBasicIterator(anchorConstraint);
      return iterator;
    }
  }

  public List<TextMarkerBasic> getMatchingBasics(TextMarkerStream stream, TextMarkerBlock parent) {
    List<TextMarkerBasic> result = new ArrayList<TextMarkerBasic>();
    String name = getType(parent, stream).getName();
    if ("uima.tcas.DocumentAnnotation".equals(name)
            || "uima.tcas.DocumentAnnotation".equals(name)
            || (stream.getDocumentAnnotationType().getName().equals(name) && stream
                    .getFirstBasicOfAll().isAnchorOf(name))) {
      TextMarkerBasic firstBasic = stream.getFirstBasicOfAll();
      if (firstBasic != null) {
        result.add(firstBasic);
      } else {
        FSIterator<AnnotationFS> it = stream.getUnfilteredBasicIterator();
        it.moveToFirst();
        if (it.isValid()) {
          TextMarkerBasic first = (TextMarkerBasic) it.get();
          result.add(first);
        }
      }

    } else {
      FSTypeConstraint anchorConstraint = createAnchorConstraints(parent, stream);
      FSIterator<AnnotationFS> iterator = stream.getFilteredBasicIterator(anchorConstraint);
      iterator.moveToFirst();
      while (iterator.isValid()) {
        TextMarkerBasic annotation = (TextMarkerBasic) iterator.get();
        result.add(annotation);
        iterator.moveToNext();
      }
    }
    return result;
  }

  public FSTypeConstraint createAnchorConstraints(TextMarkerBlock block, TextMarkerStream stream) {
    ConstraintFactory cf = stream.getCas().getConstraintFactory();
    Type type = getType(block, stream);
    BasicTypeConstraint anchorConstraint = new BasicTypeConstraint(cf.createTypeConstraint(), type
            .getName(), null);
    anchorConstraint.add(type);
    return anchorConstraint;
  }

  public List<Annotation> match(List<Annotation> annotations, TextMarkerBlock parent,
          TextMarkerStream stream) {
    List<Annotation> result = new ArrayList<Annotation>();
    for (Annotation annotation : annotations) {
      if (annotation.getType().equals(getType(parent, stream))) {
        result.add(annotation);
      }
    }
    return result;
  }

  public boolean match(TextMarkerBasic annotation, TextMarkerStream stream, TextMarkerBlock parent) {
    if (annotation == null) {
      return false;
    }
    Type type = getType(parent, stream);
    String name = type.getName();

    if ("uima.tcas.DocumentAnnotation".equals(name)
            || stream.getDocumentAnnotationType().getName().equals(name)) {
      return true;
    }
    // if (annotation instanceof TextMarkerBasic) {
    TextMarkerBasic basic = annotation;
    return basic.isAnchorOf(name)
            || stream.getJCas().getTypeSystem().subsumes(type, basic.getType());
    // }
    // TypeSystem typeSystem = stream.getJCas().getTypeSystem();
    // Type basic = stream.getJCas().getCasType(TextMarkerBasic.type);
    // if (basic != null && typeSystem.subsumes(basic, annotation.getType())) {
    // TextMarkerBasic b = annotation;
    // return b.isAnchorOf(name) || b.isPartOf(name);
    // }
    // return typeSystem.subsumes(type, annotation.getType());
  }

  @Override
  public String toString() {
    return expression.toString();
  }

  public TextMarkerExpression getExpression() {
    return expression;
  }

  public Type getType(TextMarkerBlock parent, TextMarkerStream stream) {
    Type type = expression.getType(parent);
    if ("uima.tcas.DocumentAnnotation".equals(type.getName())) {
      return stream.getDocumentAnnotationType();
    }
    return type;
  }

}
