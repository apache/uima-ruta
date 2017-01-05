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

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationListExpression;
import org.apache.uima.ruta.type.RutaBasic;

public class RutaAnnotationMatcher implements RutaMatcher {

  private IAnnotationExpression annotationExpression;

  private IAnnotationListExpression annotationListExpression;

  public RutaAnnotationMatcher(IAnnotationExpression expression) {
    super();
    this.annotationExpression = expression;
  }

  public RutaAnnotationMatcher(IAnnotationListExpression expression) {
    super();
    this.annotationListExpression = expression;
  }

  @Override
  public Collection<AnnotationFS> getMatchingAnnotations(RutaBlock parent, RutaStream stream) {
    // TODO what about the matching direction?
    MatchContext context = new MatchContext(parent);
    if (annotationExpression != null) {
      AnnotationFS annotation = annotationExpression.getAnnotation(context, stream);
      if (annotation == null) {
        return Collections.emptyList();
      }
      return Collections.singletonList(annotation);
    } else if (annotationListExpression != null) {
      return annotationListExpression.getAnnotationList(context, stream);
    }
    return Collections.emptyList();
  }

  @Override
  public Collection<AnnotationFS> getAnnotationsAfter(RutaRuleElement ruleElement,
          AnnotationFS annotation, RutaBlock parent, RutaStream stream) {
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
        boolean beginsWith = nextBasic.getBegin() == ref.getBegin();
        if (beginsWith) {
          Collection<AnnotationFS> result = new ArrayList<>(1);
          result.add(ref);
          return result;
        }
      } else if (annotationListExpression != null) {
        List<AnnotationFS> annotations = annotationListExpression.getAnnotationList(context,
                stream);
        Collection<AnnotationFS> result = new ArrayList<>();
        for (AnnotationFS each : annotations) {
          boolean beginsWith = nextBasic.getBegin() == each.getBegin();
          if (beginsWith) {
            result.add(each);
          }
        }
        return result;
      }

    }
    return Collections.emptyList();
  }

  @Override
  public Collection<AnnotationFS> getAnnotationsBefore(RutaRuleElement ruleElement,
          AnnotationFS annotation, RutaBlock parent, RutaStream stream) {
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
        boolean endsWith = nextBasic.getEnd() == ref.getEnd();
        if (endsWith) {
          Collection<AnnotationFS> result = new ArrayList<>(1);
          result.add(ref);
          return result;
        }
      } else if (annotationListExpression != null) {
        List<AnnotationFS> annotations = annotationListExpression.getAnnotationList(context,
                stream);
        for (AnnotationFS each : annotations) {
          boolean endsWith = nextBasic.getEnd() == each.getEnd();
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

  @Override
  public Type getType(RutaBlock parent, RutaStream stream) {
    MatchContext context = new MatchContext(parent);
    if (annotationExpression != null) {
      AnnotationFS ref = annotationExpression.getAnnotation(context, stream);
      if (ref == null) {
        return null;
      }
      return ref.getType();
    } else if (annotationListExpression != null) {
      List<AnnotationFS> annotations = annotationListExpression.getAnnotationList(context, stream);
      List<Type> types = new ArrayList<Type>();
      for (AnnotationFS each : annotations) {
        types.add(each.getType());
      }
      return stream.getSharedParentType(types);
    }
    return null;
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

  @Override
  public String toString() {
    IRutaExpression expression = getExpression();
    if (expression != null) {
      return expression.toString();
    } else {
      return "";
    }
  }

}
