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
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.type.RutaBasic;

public class RutaLiteralMatcher implements RutaMatcher {

  private final IStringExpression expression;

  public RutaLiteralMatcher(IStringExpression expression) {
    super();
    this.expression = expression;
  }

  @Override
  public List<AnnotationFS> getMatchingAnnotations(RutaBlock parent, RutaStream stream) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    AnnotationFS windowAnnotation = stream.getDocumentAnnotation();
    List<RutaBasic> list = stream.getBasicsInWindow(windowAnnotation);
    // TODO improve matching on literal strings
    for (RutaBasic each : list) {
      MatchContext context = new MatchContext(each, null, null, true);
      context.setParent(parent);
      if (each.getCoveredText().equals(expression.getStringValue(context, stream))) {
        result.add(each);
      }
    }
    return result;
  }

  @Override
  public String toString() {
    return "\"" + expression.toString() + "\"";
  }

  @Override
  public IStringExpression getExpression() {
    return expression;
  }

  @Override
  public long estimateAnchors(RutaBlock parent, RutaStream stream) {
    return Integer.MAX_VALUE;
  }

  @Override
  public Collection<AnnotationFS> getAnnotationsAfter(RutaRuleElement ruleElement,
          AnnotationFS annotation, RutaBlock parent, RutaStream stream) {
    return getNextAnnotations(false, annotation, stream, parent);
  }

  @Override
  public Collection<AnnotationFS> getAnnotationsBefore(RutaRuleElement ruleElement,
          AnnotationFS annotation, RutaBlock parent, RutaStream stream) {
    return getNextAnnotations(true, annotation, stream, parent);
  }

  private Collection<AnnotationFS> getNextAnnotations(boolean before, AnnotationFS annotation,
          RutaStream stream, RutaBlock parent) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>(1);
    RutaBasic basicNextTo = stream.getBasicNextTo(before, annotation);
    if (basicNextTo == null) {
      return result;
    }
    MatchContext context = new MatchContext(annotation, null, null, !before);
    context.setParent(parent);
    String stringValue = expression.getStringValue(context, stream);
    if (stringValue.equals(basicNextTo.getCoveredText())) {
      result.add(basicNextTo);
    }
    return result;
  }

  @Override
  public List<Type> getTypes(RutaBlock parent, RutaStream stream) {
    return new ArrayList<Type>();
  }

}
