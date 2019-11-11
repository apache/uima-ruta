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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.cas.CAS;
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

    MatchContext context = new MatchContext(parent);
    String text = windowAnnotation.getCoveredText();
    String literal = expression.getStringValue(context, stream);

    int indexOf = 0;
    while ((indexOf = text.indexOf(literal, indexOf)) >= 0) {

      int begin = indexOf + windowAnnotation.getBegin();
      int end = begin + literal.length();
      indexOf = end;

      AnnotationFS annotation = getAnnotation(begin, end, stream);
      if (stream.isVisible(annotation)) {
        result.add(annotation);
      }
    }
    return result;
  }

  private AnnotationFS getAnnotation(int begin, int end, RutaStream stream) {
    RutaBasic beginAnchor = stream.getBeginAnchor(begin);
    if (beginAnchor.getEnd() == end) {
      return beginAnchor;
    }
    CAS cas = stream.getCas();
    return cas.createAnnotation(cas.getAnnotationType(), begin, end);
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

    RutaBasic basicNextTo = stream.getBasicNextTo(false, annotation);
    if (basicNextTo == null) {
      return Collections.emptyList();
    }

    MatchContext context = new MatchContext(parent);
    String literal = expression.getStringValue(context, stream);

    if (!StringUtils.startsWith(literal, basicNextTo.getCoveredText())) {
      return Collections.emptyList();
    }
    AnnotationFS windowAnnotation = stream.getDocumentAnnotation();
    int begin = basicNextTo.getBegin();
    int end = begin + literal.length();
    if (begin < windowAnnotation.getBegin() || end > windowAnnotation.getEnd()) {
      return Collections.emptyList();
    }

    String substring = windowAnnotation.getCoveredText().substring(begin, end);
    if (StringUtils.equals(literal, substring)) {
      AnnotationFS matchedAnnotation = getAnnotation(begin, end, stream);
      if (stream.isVisible(matchedAnnotation)) {
        return Arrays.asList(matchedAnnotation);
      }
    }
    return Collections.emptyList();
  }

  @Override
  public Collection<AnnotationFS> getAnnotationsBefore(RutaRuleElement ruleElement,
          AnnotationFS annotation, RutaBlock parent, RutaStream stream) {
    RutaBasic basicNextTo = stream.getBasicNextTo(true, annotation);
    if (basicNextTo == null) {
      return Collections.emptyList();
    }

    MatchContext context = new MatchContext(parent);
    String literal = expression.getStringValue(context, stream);

    if (!StringUtils.endsWith(literal, basicNextTo.getCoveredText())) {
      return Collections.emptyList();
    }
    AnnotationFS windowAnnotation = stream.getDocumentAnnotation();
    int begin = basicNextTo.getEnd() - literal.length();
    int end = basicNextTo.getEnd();
    if (begin < windowAnnotation.getBegin() || end > windowAnnotation.getEnd()) {
      return Collections.emptyList();
    }

    String substring = windowAnnotation.getCoveredText().substring(begin, end);
    if (StringUtils.equals(literal, substring)) {
      AnnotationFS matchedAnnotation = getAnnotation(begin, end, stream);
      if (stream.isVisible(matchedAnnotation)) {
        return Arrays.asList(matchedAnnotation);
      }
    }
    return Collections.emptyList();
  }

  @Override
  public Type getType(RutaBlock parent, RutaStream stream) {
    return stream.getCas().getAnnotationType();
  }

}
