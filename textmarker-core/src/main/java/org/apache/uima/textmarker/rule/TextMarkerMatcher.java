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

import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.TextMarkerBlock;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.TextMarkerExpression;

public interface TextMarkerMatcher {

  Collection<AnnotationFS> getMatchingAnnotations(TextMarkerStream stream, TextMarkerBlock parent);

  boolean match(AnnotationFS annotation, TextMarkerStream stream, TextMarkerBlock parent);

  List<Type> getTypes(TextMarkerBlock parent, TextMarkerStream stream);

  TextMarkerExpression getExpression();

  int estimateAnchors(TextMarkerBlock parent, TextMarkerStream stream);

  Collection<AnnotationFS> getAnnotationsAfter(TextMarkerRuleElement ruleElement,
          AnnotationFS annotation, TextMarkerStream stream, TextMarkerBlock parent);

  Collection<AnnotationFS> getAnnotationsBefore(TextMarkerRuleElement ruleElement,
          AnnotationFS annotation, TextMarkerStream stream, TextMarkerBlock parent);

}
