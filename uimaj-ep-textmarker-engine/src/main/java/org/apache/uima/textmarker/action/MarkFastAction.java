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

package org.apache.uima.textmarker.action;

import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.bool.SimpleBooleanExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.number.SimpleNumberExpression;
import org.apache.uima.textmarker.expression.resource.WordListExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.resource.TextMarkerWordList;
import org.apache.uima.textmarker.resource.TreeWordList;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.type.TextMarkerBasic;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class MarkFastAction extends AbstractMarkAction {

  private WordListExpression list;

  private BooleanExpression ignore;

  private NumberExpression ignoreLength;

  public MarkFastAction(TypeExpression type, WordListExpression list, BooleanExpression ignore,
          NumberExpression ignoreLength) {
    super(type);
    this.list = list;
    this.ignore = ignore == null ? new SimpleBooleanExpression(false) : ignore;
    this.ignoreLength = ignoreLength == null ? new SimpleNumberExpression(Integer.valueOf(0))
            : ignoreLength;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<AnnotationFS> matchedAnnotationsOf = match.getMatchedAnnotationsOf(element, stream);
    for (AnnotationFS annotationFS : matchedAnnotationsOf) {
      TextMarkerStream windowStream = stream.getWindowStream(annotationFS, annotationFS.getType());
      TextMarkerWordList wl = list.getList(element.getParent());
      if (wl instanceof TreeWordList) {
        Collection<AnnotationFS> found = wl.find(windowStream,
                ignore.getBooleanValue(element.getParent()),
                ignoreLength.getIntegerValue(element.getParent()), null, 0);
        for (AnnotationFS annotation : found) {
          TextMarkerBasic anchor = windowStream.getFirstBasicInWindow(annotation);
          createAnnotation(anchor, element, windowStream, annotation);
        }
      }
    }

    // if(list.contains(matchedAnnotation.getCoveredText(), ignore, 0)) {
    // createAnnotation(match, stream, matchedAnnotation);
    // } else {
    // List<TextMarkerBasic> annotationsInWindow =
    // stream.getBasicAnnotationsInWindow(matchedAnnotation,
    // TextMarkerBasic.class);
    // for (TextMarkerBasic textMarkerBasic : annotationsInWindow) {
    // if(list.contains(textMarkerBasic.getCoveredText(), ignore, 0)) {
    // createAnnotation(match, stream, textMarkerBasic);
    // }
    // }
    // }
  }

  public WordListExpression getList() {
    return list;
  }

  public BooleanExpression isIgnore() {
    return ignore;
  }

}
