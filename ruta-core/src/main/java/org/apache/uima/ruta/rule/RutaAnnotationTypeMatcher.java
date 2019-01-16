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

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.expression.AnnotationTypeExpression;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.type.RutaBasic;

public class RutaAnnotationTypeMatcher implements RutaMatcher {

  private static final boolean CHECK_ON_FEATURE = true;

  private AnnotationTypeExpression expression;

  public RutaAnnotationTypeMatcher(AnnotationTypeExpression expression) {
    super();
    this.expression = expression;
  }

  @Override
  public Collection<? extends AnnotationFS> getMatchingAnnotations(RutaBlock parent,
          RutaStream stream) {
    Collection<AnnotationFS> result = null;
    MatchContext context = new MatchContext(parent);
    // just for forcing expression top initialize
    // TODO this is maybe a bit expensive sometimes
    expression.getType(context, stream);
    if (expression.getAnnotationExpression() != null) {
      result = new ArrayList<>(1);
      result.add(expression.getAnnotation(context, stream));
    } else if (expression.getAnnotationListExpression() != null) {
      result = expression.getAnnotationList(context, stream);
    } else {

      // TODO defer to getter of expression?
      List<Type> types = null;
      if (expression.getTypeListExpression() != null) {
        types = expression.getTypeListExpression().getTypeList(context, stream);
      } else {
        Type type = getType(context.getParent(), stream);
        types = new ArrayList<>(1);
        if (type != null) {
          types.add(type);
        }
      }
      result = new ArrayList<>();
      for (Type type : types) {
        if (type != null) {

          Type overallDAType = stream.getCas().getDocumentAnnotation().getType();
          String name = type.getName();
          if (StringUtils.equals(CAS.TYPE_NAME_DOCUMENT_ANNOTATION, name)
                  || "org.apache.uima.ruta.type.Document".equals(name)
                  || overallDAType.equals(type)) {
            // TODO what about dynamic windowing?
            result.add(stream.getDocumentAnnotation());
          } else {
            result.addAll(stream.getAnnotations(type));
          }
          if (expression.getFeatureExpression() != null) {
            result = new ArrayList<>(expression.getFeatureExpression().getAnnotations(result,
                    CHECK_ON_FEATURE, context, stream));
          }
        }
      }

    }
    return result;
  }

  @Override
  public Collection<? extends AnnotationFS> getAnnotationsAfter(RutaRuleElement ruleElement,
          AnnotationFS annotation, RutaBlock parent, RutaStream stream) {
    if (annotation.getEnd() == stream.getDocumentAnnotation().getEnd()) {
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
    if (end == stream.getDocumentAnnotation().getBegin()) {
      // non existing wildcard match
      stream.moveToFirst();
    } else if (annotation.getEnd() > 0) {
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
      // just for forcing expression top initialize
      expression.getType(context, stream);
      if (expression.getAnnotationExpression() != null) {

        AnnotationFS ref = expression.getAnnotation(context, stream);
        if (ref != null) {
          boolean beginsWith = nextBasic.getBegin() == ref.getBegin();
          if (beginsWith) {
            Collection<AnnotationFS> result = new ArrayList<>(1);
            result.add(ref);
            return result;
          }
        }
      } else if (expression.getAnnotationListExpression() != null) {
        List<AnnotationFS> annotations = expression.getAnnotationList(context, stream);
        Collection<AnnotationFS> result = new ArrayList<>();
        for (AnnotationFS each : annotations) {
          boolean beginsWith = nextBasic.getBegin() == each.getBegin();
          if (beginsWith) {
            result.add(each);
          }
        }
        return result;
      } else {
        List<Type> types = null;
        if (expression.getTypeListExpression() != null) {
          types = expression.getTypeListExpression().getTypeList(context, stream);
        } else {
          Type type = getType(context.getParent(), stream);
          types = new ArrayList<>(1);
          types.add(type);
        }
        List<AnnotationFS> annotations = new ArrayList<>();
        for (Type type : types) {
          Collection<AnnotationFS> anchors = new ArrayList<>();
          Collection<AnnotationFS> beginAnchors = nextBasic.getBeginAnchors(type);
          if (beginAnchors != null) {
            for (AnnotationFS afs : beginAnchors) {
              if (afs.getBegin() >= stream.getDocumentAnnotation().getBegin()
                      && afs.getEnd() <= stream.getDocumentAnnotation().getEnd()) {
                anchors.add(afs);
              }
            }
          }
          if (expression.getFeatureExpression() != null) {
            annotations.addAll(expression.getFeatureExpression().getAnnotations(anchors,
                    CHECK_ON_FEATURE, context, stream));
          } else {
            annotations.addAll(anchors);
          }

        }
        return annotations;
      }

    }
    return Collections.emptyList();
  }

  @Override
  public Collection<? extends AnnotationFS> getAnnotationsBefore(RutaRuleElement ruleElement,
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
    if (firstBasic.getBegin() == stream.getDocumentAnnotation().getEnd()) {
      // non existing wildcard match
      stream.moveToLast();
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
      // just for forcing expression top initialize
      expression.getType(context, stream);
      if (expression.getAnnotationExpression() != null) {
        AnnotationFS ref = expression.getAnnotationExpression().getAnnotation(context, stream);
        boolean endsWith = nextBasic.getEnd() == ref.getEnd();
        if (endsWith) {
          Collection<AnnotationFS> result = new ArrayList<>(1);
          result.add(ref);
          return result;
        }
      } else if (expression.getAnnotationListExpression() != null) {
        List<AnnotationFS> annotations = expression.getAnnotationListExpression()
                .getAnnotationList(context, stream);
        for (AnnotationFS each : annotations) {
          boolean endsWith = nextBasic.getEnd() == each.getEnd();
          if (endsWith) {
            Collection<AnnotationFS> result = new ArrayList<>();
            result.add(each);
            return result;
          }
        }
      } else {
        List<Type> types = null;
        if (expression.getTypeListExpression() != null) {
          types = expression.getTypeListExpression().getTypeList(context, stream);
        } else {
          Type type = getType(context.getParent(), stream);
          types = new ArrayList<>(1);
          types.add(type);
        }
        List<AnnotationFS> annotations = new ArrayList<>();
        for (Type type : types) {
          Collection<AnnotationFS> anchors = new ArrayList<>();
          Collection<AnnotationFS> endAnchors = nextBasic.getEndAnchors(type);
          if (endAnchors != null) {
            for (AnnotationFS afs : endAnchors) {
              if (afs.getBegin() >= stream.getDocumentAnnotation().getBegin()) {
                anchors.add(afs);
              }
            }
          }
          if (expression.getFeatureExpression() != null) {
            annotations.addAll(expression.getFeatureExpression().getAnnotations(anchors,
                    CHECK_ON_FEATURE, context, stream));
          } else {
            annotations.addAll(anchors);
          }
        }
        return annotations;
      }
    }
    return Collections.emptyList();
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

  @Override
  public IRutaExpression getExpression() {
    return expression;
  }

  protected Type getType(AnnotationTypeExpression expression, RutaBlock parent, RutaStream stream,
          boolean resolveDocumentAnnotation) {
    MatchContext context = new MatchContext(parent);
    Type type = expression.getType(context, stream);
    if (resolveDocumentAnnotation && type != null
            && StringUtils.equals(CAS.TYPE_NAME_DOCUMENT_ANNOTATION, type.getName())) {
      return stream.getDocumentAnnotationType();
    }
    return type;
  }

  @Override
  public long estimateAnchors(RutaBlock parent, RutaStream stream) {
    if (expression.getAnnotationExpression() != null) {
      return 1;
    } else if (expression.getAnnotationListExpression() != null) {
      return 1;
    } else {
      return stream.getHistogram(getType(expression, parent, stream, true));
    }
  }

  @Override
  public Type getType(RutaBlock parent, RutaStream stream) {
    MatchContext context = new MatchContext(parent);
    if (expression.getAnnotationExpression() != null) {
      AnnotationFS annotation = expression.getAnnotationExpression().getAnnotation(context, stream);
      if (annotation != null) {
        return annotation.getType();
      }
    } else if (expression.getAnnotationListExpression() != null) {
      List<AnnotationFS> annotations = expression.getAnnotationListExpression()
              .getAnnotationList(context, stream);
      List<Type> types = new ArrayList<Type>();
      for (AnnotationFS each : annotations) {
        types.add(each.getType());
      }
      return stream.getSharedParentType(types);
    } else {
      return getType(expression, parent, stream, false);
    }

    return null;
  }

}
