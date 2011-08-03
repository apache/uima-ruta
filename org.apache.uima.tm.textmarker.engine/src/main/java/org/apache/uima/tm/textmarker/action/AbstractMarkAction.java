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

package org.apache.uima.tm.textmarker.action;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;


public abstract class AbstractMarkAction extends TypeSensitiveAction {

  public AbstractMarkAction(TypeExpression type) {
    super(type);
  }

  protected void createAnnotation(RuleMatch match, TextMarkerRuleElement element,
          TextMarkerStream stream, AnnotationFS matchedAnnotation) {
    TextMarkerBasic first = stream.getFirstBasicInWindow(matchedAnnotation);
    if (first == null) {
      first = match.getFirstBasic();
    }
    createAnnotation(first, element, stream, matchedAnnotation);
  }

  protected Annotation createAnnotation(TextMarkerBasic anchor, TextMarkerRuleElement element,
          TextMarkerStream stream, AnnotationFS matchedAnnotation) {
    Type t = type.getType(element.getParent());
    AnnotationFS newAnnotationFS = stream.getCas().createAnnotation(t,
            matchedAnnotation.getBegin(), matchedAnnotation.getEnd());
    Annotation newAnnotation = null;
    if (newAnnotationFS instanceof Annotation) {
      newAnnotation = (Annotation) newAnnotationFS;
      newAnnotation.addToIndexes();
    } else {
      return null;
    }
    stream.addAnnotation(anchor, newAnnotation);
    return newAnnotation;
  }

  @Override
  public String toString() {
    return super.toString() + "," + type.getClass().getSimpleName();
  }

}
