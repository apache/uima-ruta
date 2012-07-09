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
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.TextMarkerBlock;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.TextMarkerExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;

public class TextMarkerDisjunctiveMatcher implements TextMarkerMatcher {
  private final List<TextMarkerExpression> expressions;
  
  private List<TextMarkerMatcher> matchers ;
  
  public TextMarkerDisjunctiveMatcher(List<TextMarkerExpression> expressions) {
    super();
    this.expressions = expressions;
    matchers = new ArrayList<TextMarkerMatcher>();
    for (TextMarkerExpression each : expressions) {
      if(each instanceof TypeExpression) {
        matchers.add(new TextMarkerTypeMatcher((TypeExpression) each));
      } else if(each instanceof StringExpression) {
        matchers.add(new TextMarkerLiteralMatcher((StringExpression) each));
      }
    }
    
    
  }

  public Collection<AnnotationFS> getMatchingAnnotations(TextMarkerStream stream,
          TextMarkerBlock parent) {
    Collection<AnnotationFS> result = new ArrayList<AnnotationFS>();
    for (TextMarkerMatcher each : matchers) {
      result.addAll(each.getMatchingAnnotations(stream, parent));
    }
    return result;
  }

  public boolean match(AnnotationFS annotation, TextMarkerStream stream, TextMarkerBlock parent) {
    for (TextMarkerMatcher each : matchers) {
      boolean match = each.match(annotation, stream, parent);
      if(match) {
        return true;
      }
    }
    return false;
  }

  public List<Type> getTypes(TextMarkerBlock parent, TextMarkerStream stream) {
    List<Type> result = new ArrayList<Type>();
    for (TextMarkerMatcher each : matchers) {
      result.addAll(each.getTypes(parent, stream));
    }
    return result;
  }

  public TextMarkerExpression getExpression() {
    return null;
  }

  public int estimateAnchors(TextMarkerBlock parent, TextMarkerStream stream) {
    int result = 0;
    for (TextMarkerMatcher each : matchers) {
      result += each.estimateAnchors(parent, stream);
    }
    return result;
  }

  public Collection<AnnotationFS> getAnnotationsAfter(TextMarkerRuleElement ruleElement,
          AnnotationFS annotation, TextMarkerStream stream, TextMarkerBlock parent) {
    Collection<AnnotationFS> result = new ArrayList<AnnotationFS>();
    for (TextMarkerMatcher each : matchers) {
      result.addAll(each.getAnnotationsAfter(ruleElement, annotation, stream, parent));
    }
    return result;
  }

  public Collection<AnnotationFS> getAnnotationsBefore(TextMarkerRuleElement ruleElement,
          AnnotationFS annotation, TextMarkerStream stream, TextMarkerBlock parent) {
    Collection<AnnotationFS> result = new ArrayList<AnnotationFS>();
    for (TextMarkerMatcher each : matchers) {
      result.addAll(each.getAnnotationsBefore(ruleElement, annotation, stream, parent));
    }
    return result;
  }

  public List<TextMarkerExpression> getExpressions() {
    return expressions;
  }

 
}
