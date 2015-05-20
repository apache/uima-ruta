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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.list.UntypedListExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.resource.WordListExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.resource.RutaWordList;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class TrieAction extends AbstractRutaAction {

  private final WordListExpression list;

  private final Map<IStringExpression, IRutaExpression> map;

  private final IBooleanExpression ignoreCase;

  private final INumberExpression ignoreLength;

  private final IBooleanExpression edit;

  private final INumberExpression distance;

  private final IStringExpression ignoreChar;

  public TrieAction(WordListExpression list, Map<IStringExpression, IRutaExpression> map,
          IBooleanExpression ignoreCase, INumberExpression ignoreLength, IBooleanExpression edit,
          INumberExpression distance, IStringExpression ignoreChar) {
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
  public void execute(RuleMatch match, RuleElement element, RutaStream stream, InferenceCrowd crowd) {

    Map<String, Object> typeMap = new HashMap<String, Object>();
    RutaBlock parent = element.getParent();
    for (IStringExpression eachKey : map.keySet()) {
      String stringValue = eachKey.getStringValue(parent, match, element, stream);
      IRutaExpression expression = map.get(eachKey);
      if (expression instanceof TypeExpression) {
        Type typeValue = ((TypeExpression) expression).getType(parent);
        typeMap.put(stringValue, typeValue);
      } else if (expression instanceof UntypedListExpression) {
        List<Object> innerList = ((UntypedListExpression) expression).getList(parent, stream);
        typeMap.put(stringValue, innerList);
      }
    }
    boolean ignoreCaseValue = ignoreCase.getBooleanValue(parent, match, element, stream);
    int ignoreLengthValue = ignoreLength.getIntegerValue(parent, match, element, stream);
    boolean editValue = edit.getBooleanValue(parent, match, element, stream);
    double distanceValue = distance.getDoubleValue(parent, match, element, stream);
    String ignoreCharValue = ignoreChar.getStringValue(parent, match, element, stream);

    RutaWordList wl = list.getList(parent);
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

  public Map<IStringExpression, IRutaExpression> getMap() {
    return map;
  }

  public IBooleanExpression getIgnoreCase() {
    return ignoreCase;
  }

  public INumberExpression getIgnoreLength() {
    return ignoreLength;
  }

  public IBooleanExpression getEdit() {
    return edit;
  }

  public INumberExpression getDistance() {
    return distance;
  }

  public IStringExpression getIgnoreChar() {
    return ignoreChar;
  }

}
