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

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationListExpression;
import org.apache.uima.ruta.type.RutaBasic;

public class RutaAnnotationMatcher implements RutaMatcher {

  protected AnnotationComparator comparator;

  private IAnnotationExpression annotationExpression;

  private IAnnotationListExpression annotationListExpression;

  public RutaAnnotationMatcher(IAnnotationExpression expression) {
    super();
    this.annotationExpression = expression;
    this.comparator = new AnnotationComparator();
  }

  public RutaAnnotationMatcher(IAnnotationListExpression expression) {
    super();
    this.annotationListExpression = expression;
    this.comparator = new AnnotationComparator();
  }

  public Collection<AnnotationFS> getMatchingAnnotations(RutaStream stream, RutaBlock parent) {
    // TODO what about the matching direction?
    MatchContext context = new MatchContext(parent);
    if (annotationExpression != null) {
      Collection<AnnotationFS> annotations = new TreeSet<AnnotationFS>(comparator);
      AnnotationFS annotation = annotationExpression.getAnnotation(context, stream);
      if (annotation != null) {
        annotations.add(annotation);
      }
      return annotations;
    } else if (annotationListExpression != null) {
      return annotationListExpression.getAnnotations(context, stream);
    }
    return Collections.emptyList();
  }

  public Collection<AnnotationFS> getAnnotationsAfter(RutaRuleElement ruleElement,
          AnnotationFS annotation, RutaStream stream, RutaBlock parent) {
    if (annotation.getEnd() == stream.getDocumentAnnotation().getEnd()) {
      return Collections.emptyList();
    }
    // TODO refactor
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
      if (stream.isVisible(lastBasic) && stream.isValid()
              && stream.get().getEnd() == lastBasic.getEnd()) {
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
      MatchContext context = new MatchContext(parent);
      if (annotationExpression != null) {

        AnnotationFS ref = annotationExpression.getAnnotation(context, stream);
        boolean beginsWith = nextBasic.beginsWith(ref.getType());
        if (beginsWith) {
          Collection<AnnotationFS> result = new ArrayList<>(1);
          result.add(ref);
          return result;
        }
      } else if (annotationListExpression != null) {
        List<AnnotationFS> annotations = annotationListExpression.getAnnotations(context, stream);
        Collection<AnnotationFS> result = new ArrayList<>();
        for (AnnotationFS each : annotations) {
          boolean beginsWith = nextBasic.beginsWith(each.getType());
          if (beginsWith) {
            result.add(each);
          }
        }
        return result;
      }

    }
    return Collections.emptyList();
  }

  public Collection<AnnotationFS> getAnnotationsBefore(RutaRuleElement ruleElement,
          AnnotationFS annotation, RutaStream stream, RutaBlock parent) {
    if (annotation.getBegin() == stream.getDocumentAnnotation().getBegin()) {
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
      MatchContext context = new MatchContext(parent);
      if (annotationExpression != null) {
        AnnotationFS ref = annotationExpression.getAnnotation(context, stream);
        boolean endsWith = nextBasic.beginsWith(ref.getType());
        if (endsWith) {
          Collection<AnnotationFS> result = new ArrayList<>(1);
          result.add(ref);
          return result;
        }
      } else if (annotationListExpression != null) {
        List<AnnotationFS> annotations = annotationListExpression.getAnnotations(context, stream);
        for (AnnotationFS each : annotations) {
          boolean endsWith = nextBasic.beginsWith(each.getType());
          if (endsWith) {
            Collection<AnnotationFS> result = new ArrayList<>();
            result.add(each);
            return result;
          }
        }
      }
    }
    return Collections.emptyList();
  }

  public boolean match(AnnotationFS annotation, RutaStream stream, RutaBlock parent) {
    if (annotation == null) {
      return false;
    }
    MatchContext context = new MatchContext(parent);
    if (annotationExpression != null) {
      AnnotationFS ref = annotationExpression.getAnnotation(context, stream);

      return annotation.equals(ref);
    } else if (annotationListExpression != null) {
      List<AnnotationFS> annotations = annotationListExpression.getAnnotations(context, stream);
      for (AnnotationFS each : annotations) {
        if (each.equals(annotation)) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public List<Type> getTypes(RutaBlock parent, RutaStream stream) {
    MatchContext context = new MatchContext(parent);
    if(annotationExpression != null) {
      
    AnnotationFS ref = annotationExpression.getAnnotation(context, stream);
    if (ref == null) {
      return Collections.emptyList();
    }
    List<Type> result = new ArrayList<Type>(1);
    result.add(ref.getType());
    return result;
    } else if(annotationListExpression != null) {
      List<AnnotationFS> annotations = annotationListExpression.getAnnotations(context, stream);
      List<Type> result = new ArrayList<Type>();
      for (AnnotationFS each : annotations) {
        result.add(each.getType());
      }
      return result;
    }
    return Collections.emptyList();
  }

  @Override
  public IRutaExpression getExpression() {
    if (annotationExpression != null) {
      return annotationExpression;
    } else if (annotationListExpression != null) {
      return annotationListExpression;
    }
    return null;
  }

  @Override
  public long estimateAnchors(RutaBlock parent, RutaStream stream) {
    return 1;
  }
}
