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

import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanExpression;
import org.apache.uima.ruta.expression.list.StringListExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberExpression;
import org.apache.uima.ruta.expression.resource.WordListExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.resource.RutaWordList;
import org.apache.uima.ruta.resource.TreeWordList;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class MarkFastAction extends AbstractMarkAction {

  private WordListExpression list;

  private StringListExpression stringList;

  private IBooleanExpression ignore;

  private INumberExpression ignoreLength;

  private IBooleanExpression ignoreWS;

  public MarkFastAction(TypeExpression type, WordListExpression list, IBooleanExpression ignore,
          INumberExpression ignoreLength, IBooleanExpression ignoreWS) {
    super(type);
    this.list = list;
    this.ignore = ignore == null ? new SimpleBooleanExpression(false) : ignore;
    this.ignoreLength = ignoreLength == null ? new SimpleNumberExpression(Integer.valueOf(0))
            : ignoreLength;
    this.ignoreWS = ignoreWS == null ? new SimpleBooleanExpression(true) : ignoreWS;
  }

  public MarkFastAction(TypeExpression type, StringListExpression list, IBooleanExpression ignore,
          INumberExpression ignoreLength, IBooleanExpression ignoreWS) {
    super(type);
    this.stringList = list;
    this.ignore = ignore == null ? new SimpleBooleanExpression(false) : ignore;
    this.ignoreLength = ignoreLength == null ? new SimpleNumberExpression(Integer.valueOf(0))
            : ignoreLength;
    this.ignoreWS = ignoreWS == null ? new SimpleBooleanExpression(true) : ignoreWS;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, RutaStream stream, InferenceCrowd crowd) {
    List<AnnotationFS> matchedAnnotationsOf = match.getMatchedAnnotationsOf(element);
    for (AnnotationFS annotationFS : matchedAnnotationsOf) {
      RutaStream windowStream = stream.getWindowStream(annotationFS, annotationFS.getType());
      RutaWordList wl = null;
      RutaBlock parent = element.getParent();
      if (list != null) {
        wl = list.getList(parent);
      } else if (stringList != null) {
        wl = new TreeWordList(stringList.getList(parent, stream), false);
      }
      if (wl instanceof TreeWordList) {
        Collection<AnnotationFS> found = wl.find(windowStream,
                ignore.getBooleanValue(parent, match, element, stream),
                ignoreLength.getIntegerValue(parent, match, element, stream), null, 0,
                ignoreWS.getBooleanValue(parent, match, element, stream));
        for (AnnotationFS annotation : found) {
          createAnnotation(annotation, element, windowStream, match);
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

  public IBooleanExpression getIgnore() {
    return ignore;
  }

  public INumberExpression getIgnoreLength() {
    return ignoreLength;
  }

  public IBooleanExpression getIgnoreWS() {
    return ignoreWS;
  }

}
