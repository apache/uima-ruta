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
import java.util.HashMap;
import java.util.Map;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.resource.WordListExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.resource.TextMarkerWordList;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class TrieAction extends AbstractTextMarkerAction {

  private final WordListExpression list;

  private final Map<StringExpression, TypeExpression> map;

  private final BooleanExpression ignoreCase;

  private final NumberExpression ignoreLength;

  private final BooleanExpression edit;

  private final NumberExpression distance;

  private final StringExpression ignoreChar;

  public TrieAction(WordListExpression list, Map<StringExpression, TypeExpression> map,
          BooleanExpression ignoreCase, NumberExpression ignoreLength, BooleanExpression edit,
          NumberExpression distance, StringExpression ignoreChar) {
    super();
    this.list = list;
    this.map = map;
    this.ignoreCase = ignoreCase;
    this.ignoreLength = ignoreLength;
    this.edit = edit;
    this.distance = distance;
    this.ignoreChar = ignoreChar;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {

    Map<String, Type> typeMap = new HashMap<String, Type>();
    for (StringExpression eachKey : map.keySet()) {
      String stringValue = eachKey.getStringValue(element.getParent());
      TypeExpression typeExpression = map.get(eachKey);
      if (typeExpression != null) {
        Type typeValue = typeExpression.getType(element.getParent());
        typeMap.put(stringValue, typeValue);
      }
    }
    boolean ignoreCaseValue = ignoreCase.getBooleanValue(element.getParent());
    int ignoreLengthValue = ignoreLength.getIntegerValue(element.getParent());
    boolean editValue = edit.getBooleanValue(element.getParent());
    double distanceValue = distance.getDoubleValue(element.getParent());
    String ignoreCharValue = ignoreChar.getStringValue(element.getParent());

    TextMarkerWordList wl = list.getList(element.getParent());
    Collection<AnnotationFS> found = wl.find(stream, typeMap, ignoreCaseValue, ignoreLengthValue,
            editValue, distanceValue, ignoreCharValue);

    if (found != null) {
      for (AnnotationFS annotation : found) {
        stream.addAnnotation(annotation, match);
        stream.getCas().addFsToIndexes(annotation);
      }
    }

  }

  public WordListExpression getList() {
    return list;
  }

  public Map<StringExpression, TypeExpression> getMap() {
    return map;
  }

  public BooleanExpression getIgnoreCase() {
    return ignoreCase;
  }

  public NumberExpression getIgnoreLength() {
    return ignoreLength;
  }

  public BooleanExpression getEdit() {
    return edit;
  }

  public NumberExpression getDistance() {
    return distance;
  }

  public StringExpression getIgnoreChar() {
    return ignoreChar;
  }

}
