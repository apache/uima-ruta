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
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.type.RutaBasic;

public class RutaLiteralMatcher implements RutaMatcher {

  private final IStringExpression expression;

  public RutaLiteralMatcher(IStringExpression expression) {
    super();
    this.expression = expression;
  }

  public List<AnnotationFS> getMatchingAnnotations(RutaStream stream, RutaBlock parent) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    AnnotationFS windowAnnotation = stream.getDocumentAnnotation();
    List<RutaBasic> list = stream.getBasicsInWindow(windowAnnotation);
    for (RutaBasic each : list) {
      if (each.getCoveredText().equals(expression.getStringValue(parent, null, stream))) {
        result.add(each);
      }
    }
    return result;
  }

  public boolean match(AnnotationFS annotation, RutaStream stream, RutaBlock parent) {
    if (annotation == null) {
      return false;
    }
    return annotation.getCoveredText().equals(expression.getStringValue(parent, null, stream));
  }

  @Override
  public String toString() {
    return "\"" + expression.toString() + "\"";
  }

  public IStringExpression getExpression() {
    return expression;
  }

  public long estimateAnchors(RutaBlock parent, RutaStream stream) {
    return Integer.MAX_VALUE;
  }

  public Collection<AnnotationFS> getAnnotationsAfter(RutaRuleElement ruleElement,
          AnnotationFS annotation, RutaStream stream, RutaBlock parent) {
    return getNextAnnotations(false, annotation, stream, parent);
  }

  public Collection<AnnotationFS> getAnnotationsBefore(RutaRuleElement ruleElement,
          AnnotationFS annotation, RutaStream stream, RutaBlock parent) {
    return getNextAnnotations(true, annotation, stream, parent);
  }

  private Collection<AnnotationFS> getNextAnnotations(boolean before, AnnotationFS annotation,
          RutaStream stream, RutaBlock parent) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>(1);
    RutaBasic basicNextTo = stream.getBasicNextTo(before, annotation);
    if (basicNextTo == null) {
      return result;
    }
    String stringValue = expression.getStringValue(parent, annotation, stream);
    if (stringValue.equals(basicNextTo.getCoveredText())) {
      result.add(basicNextTo);
    }
    return result;
  }

  public List<Type> getTypes(RutaBlock parent, RutaStream stream) {
    return new ArrayList<Type>();
  }

}
