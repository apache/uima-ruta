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
import org.apache.uima.textmarker.expression.list.StringListExpression;
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

  private StringListExpression stringList;

  private BooleanExpression ignore;

  private NumberExpression ignoreLength;

  private BooleanExpression ignoreWS;

  public MarkFastAction(TypeExpression type, WordListExpression list, BooleanExpression ignore,
          NumberExpression ignoreLength, BooleanExpression ignoreWS) {
    super(type);
    this.list = list;
    this.ignore = ignore == null ? new SimpleBooleanExpression(false) : ignore;
    this.ignoreLength = ignoreLength == null ? new SimpleNumberExpression(Integer.valueOf(0))
            : ignoreLength;
    this.ignoreWS = ignoreWS == null ? new SimpleBooleanExpression(true) : ignoreWS;
  }

  public MarkFastAction(TypeExpression type, StringListExpression list, BooleanExpression ignore,
          NumberExpression ignoreLength, BooleanExpression ignoreWS) {
    super(type);
    this.stringList = list;
    this.ignore = ignore == null ? new SimpleBooleanExpression(false) : ignore;
    this.ignoreLength = ignoreLength == null ? new SimpleNumberExpression(Integer.valueOf(0))
            : ignoreLength;
    this.ignoreWS = ignoreWS == null ? new SimpleBooleanExpression(true) : ignoreWS;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<AnnotationFS> matchedAnnotationsOf = match.getMatchedAnnotationsOf(element, stream);
    for (AnnotationFS annotationFS : matchedAnnotationsOf) {
      TextMarkerStream windowStream = stream.getWindowStream(annotationFS, annotationFS.getType());
      TextMarkerWordList wl = null;
      if (list != null) {
        wl = list.getList(element.getParent());
      } else if (stringList != null) {
        wl = new TreeWordList(stringList.getList(element.getParent()));
      }
      if (wl instanceof TreeWordList) {
        Collection<AnnotationFS> found = wl.find(windowStream,
                ignore.getBooleanValue(element.getParent()),
                ignoreLength.getIntegerValue(element.getParent()), null, 0,
                ignoreWS.getBooleanValue(element.getParent()));
        for (AnnotationFS annotation : found) {
          TextMarkerBasic anchor = windowStream.getFirstBasicInWindow(annotation);
          createAnnotation(anchor, element, windowStream, annotation, match);
        }
      }
    }
  }

  public WordListExpression getList() {
    return list;
  }

  public StringListExpression getStringList() {
    return stringList;
  }

  public BooleanExpression getIgnore() {
    return ignore;
  }

  public NumberExpression getIgnoreLength() {
    return ignoreLength;
  }

  public BooleanExpression getIgnoreWS() {
    return ignoreWS;
  }

}
