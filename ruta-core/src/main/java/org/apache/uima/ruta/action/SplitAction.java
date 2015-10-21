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

package org.apache.uima.ruta.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.visitor.InferenceCrowd;
import org.apache.uima.util.CasCopier;

public class SplitAction extends AbstractRutaAction {

  private TypeExpression splitOnType;

  private IBooleanExpression complete;

  private IBooleanExpression appendToBegin;

  private IBooleanExpression appendToEnd;

  public SplitAction(TypeExpression splitOnType, IBooleanExpression complete,
          IBooleanExpression appendToBegin, IBooleanExpression appendToEnd) {
    super();
    this.splitOnType = splitOnType;
    this.complete = complete == null ? new SimpleBooleanExpression(true) : complete;
    this.appendToBegin = appendToBegin == null ? new SimpleBooleanExpression(false) : appendToBegin;
    this.appendToEnd = appendToEnd == null ? new SimpleBooleanExpression(false) : appendToEnd;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, RutaStream stream, InferenceCrowd crowd) {
    List<AnnotationFS> matchedAnnotationsOf = match.getMatchedAnnotationsOf(element);
    RutaBlock parent = element.getParent();
    Type typeToSplit = splitOnType.getType(parent);
    boolean splitOnCompleteAnnotation = complete.getBooleanValue(parent, match, element, stream);
    boolean addToBegin = appendToBegin.getBooleanValue(parent, match, element, stream);
    boolean addToEnd = appendToEnd.getBooleanValue(parent, match, element, stream);
    for (AnnotationFS annotation : matchedAnnotationsOf) {
      splitAnnotation(annotation, typeToSplit, splitOnCompleteAnnotation, addToBegin, addToEnd,
              match, stream);
    }
  }

  private void splitAnnotation(AnnotationFS annotation, Type typeToSplit,
          boolean splitOnCompleteAnnotation, boolean addToBegin, boolean addToEnd, RuleMatch match,
          RutaStream stream) {

    if (annotation instanceof Annotation) {

      if (splitOnCompleteAnnotation) {
        splitAnnotationOnComplete((Annotation) annotation, typeToSplit, addToBegin, addToEnd,
                match, stream);
      } else {
        splitAnnotationOnBoundary((Annotation) annotation, typeToSplit, addToBegin, addToEnd,
                match, stream);
      }
    }
  }

  private void splitAnnotationOnComplete(Annotation annotation, Type typeToSplit,
          boolean addToBegin, boolean addToEnd, RuleMatch match, RutaStream stream) {
    List<AnnotationFS> annotationsInWindow = stream.getAnnotationsInWindow(annotation, typeToSplit);
    if (annotationsInWindow == null || annotationsInWindow.isEmpty()) {
      return;
    }

    CAS cas = annotation.getCAS();
    CasCopier cc = new CasCopier(cas, cas);

    cas.removeFsFromIndexes(annotation);

    int overallEnd = annotation.getEnd();
    Annotation first = annotation;

    for (AnnotationFS each : annotationsInWindow) {
      int firstEnd = addToEnd ? each.getEnd() : each.getBegin();
      first.setEnd(firstEnd);
      boolean valid = trimInvisible(first, stream);
      if (valid) {
        stream.addAnnotation(first, true, true, match);
      }

      Annotation second = (Annotation) cc.copyFs(first);
      int secondBegin = addToBegin ? each.getBegin() : each.getEnd();
      second.setBegin(secondBegin);
      second.setEnd(overallEnd);
      valid = trimInvisible(second, stream);
      if (valid) {
        stream.addAnnotation(second, true, true, match);
      }
      first = second;
    }

  }

  private void splitAnnotationOnBoundary(Annotation annotation, Type typeToSplit,
          boolean addToBegin, boolean addToEnd, RuleMatch match, RutaStream stream) {
    Collection<RutaBasic> basics = stream.getAllBasicsInWindow(annotation);

    CAS cas = annotation.getCAS();
    CasCopier cc = new CasCopier(cas, cas);

    cas.removeFsFromIndexes(annotation);

    int overallEnd = annotation.getEnd();
    Annotation first = annotation;

    for (RutaBasic eachBasic : basics) {
      if (stream.isVisible(eachBasic)) {
        boolean beginsWith = eachBasic.beginsWith(typeToSplit);
        boolean endsWith = eachBasic.endsWith(typeToSplit);
        if (beginsWith || endsWith) {
          int firstEnd = beginsWith ? eachBasic.getBegin() : eachBasic.getEnd();
          first.setEnd(firstEnd);
          boolean valid = trimInvisible(first, stream);
          if (valid) {
            stream.addAnnotation(first, true, true, match);
          }

          Annotation second = (Annotation) cc.copyFs(first);
          int secondBegin = endsWith ? eachBasic.getEnd() : eachBasic.getBegin();
          second.setBegin(secondBegin);
          second.setEnd(overallEnd);
          valid = trimInvisible(second, stream);
          if (valid) {
            stream.addAnnotation(second, true, true, match);
          }
          first = second;
        }
      }
    }

  }

  private boolean trimInvisible(Annotation annotation, RutaStream stream) {
    List<RutaBasic> basics = new ArrayList<>(stream.getAllBasicsInWindow(annotation));
    
    int min = annotation.getEnd();
    int max = annotation.getBegin();

    if(min <= max) {
      return false;
    }

    for (RutaBasic each : basics) {
      if (stream.isVisible(each)) {
        min = Math.min(min, each.getBegin());
        break;
      }
    }
    Collections.reverse(basics);
    for (RutaBasic each : basics) {
      if (stream.isVisible(each)) {
        max = Math.max(max, each.getEnd());
        break;
      }
    }
    if (min < max) {
      annotation.setBegin(min);
      annotation.setEnd(max);
      return true;
    }
    return false;
  }

}
