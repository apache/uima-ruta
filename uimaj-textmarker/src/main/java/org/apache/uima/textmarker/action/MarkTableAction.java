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
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.textmarker.TextMarkerBlock;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.UIMAConstants;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.resource.WordTableExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.resource.TextMarkerTable;
import org.apache.uima.textmarker.resource.TextMarkerWordList;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class MarkTableAction extends AbstractTextMarkerAction {

  private final TypeExpression typeExpr;

  private final WordTableExpression tableExpr;

  private final Map<StringExpression, NumberExpression> featureMap;

  private final NumberExpression indexExpr;
  
  private final BooleanExpression ignoreCase;

  private final NumberExpression ignoreLength;

  private final StringExpression ignoreChar;

  private final NumberExpression maxIgnoreChar;

  public MarkTableAction(TypeExpression typeExpr, NumberExpression indexExpr,
          WordTableExpression tableExpr, Map<StringExpression, NumberExpression> featureMap, BooleanExpression ignoreCase, NumberExpression ignoreLength, StringExpression ignoreChar, NumberExpression maxIgnoreChar) {
    super();
    this.typeExpr = typeExpr;
    this.indexExpr = indexExpr;
    this.tableExpr = tableExpr;
    this.featureMap = featureMap;
    this.ignoreCase = ignoreCase;
    this.ignoreLength = ignoreLength;
    this.ignoreChar = ignoreChar;
    this.maxIgnoreChar = maxIgnoreChar;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    TextMarkerBlock block = element.getParent();
    TextMarkerTable table = tableExpr.getTable(block);
    int index = indexExpr.getIntegerValue(block);
    Type type = typeExpr.getType(block);
    Map<String, Integer> map = new HashMap<String, Integer>();
    for (StringExpression each : featureMap.keySet()) {
      map.put(each.getStringValue(block), featureMap.get(each).getIntegerValue(block));
    }
    
    boolean ignoreCaseValue = ignoreCase != null ? ignoreCase.getBooleanValue(element.getParent()) : false;
    int ignoreLengthValue = ignoreLength != null ? ignoreLength.getIntegerValue(element.getParent()) : 0;
    String ignoreCharValue = ignoreChar != null ? ignoreChar.getStringValue(element.getParent()) : "";
    int maxIgnoreCharValue = maxIgnoreChar != null ? maxIgnoreChar.getIntegerValue(element.getParent()) : 0;
    
    TextMarkerWordList wordList = table.getWordList(index);
    Collection<AnnotationFS> found = wordList.find(stream, ignoreCaseValue, ignoreLengthValue, ignoreCharValue.toCharArray(), maxIgnoreCharValue, true);
    for (AnnotationFS annotationFS : found) {
      List<String> rowWhere = table.getRowWhere(index - 1, annotationFS.getCoveredText());
      FeatureStructure newFS = stream.getCas().createFS(type);
      if (newFS instanceof Annotation) {
        Annotation a = (Annotation) newFS;
        a.setBegin(annotationFS.getBegin());
        a.setEnd(annotationFS.getEnd());
        stream.addAnnotation(a, match);
      }
      TOP newStructure = null;
      if (newFS instanceof TOP) {
        newStructure = (TOP) newFS;
        fillFeatures(newStructure, map, annotationFS, element, rowWhere, stream);
        newStructure.addToIndexes();
      }
    }
  }

  private void fillFeatures(TOP structure, Map<String, Integer> map, AnnotationFS annotationFS,
          RuleElement element, List<String> row, TextMarkerStream stream) {
    List<?> featuresList = structure.getType().getFeatures();
    for (int i = 0; i < featuresList.size(); i++) {
      Feature targetFeature = (Feature) featuresList.get(i);
      String name = targetFeature.getName();
      String shortFName = name.substring(name.indexOf(":") + 1, name.length());
      Integer entryIndex = map.get(shortFName);
      Type range = targetFeature.getRange();
      if (entryIndex != null && row.size() >= entryIndex) {
        String value = row.get(entryIndex - 1);
        if (range.getName().equals(UIMAConstants.TYPE_STRING)) {
          structure.setStringValue(targetFeature, value);
        } else if (range.getName().equals(UIMAConstants.TYPE_INTEGER)) {
          Integer integer = Integer.parseInt(value);
          structure.setIntValue(targetFeature, integer);
        } else if (range.getName().equals(UIMAConstants.TYPE_DOUBLE)) {
          Double d = Double.parseDouble(value);
          structure.setDoubleValue(targetFeature, d);
        } else if (range.getName().equals(UIMAConstants.TYPE_FLOAT)) {
          Float d = Float.parseFloat(value);
          structure.setFloatValue(targetFeature, d);
        } else if (range.getName().equals(UIMAConstants.TYPE_BYTE)) {
          Byte d = Byte.parseByte(value);
          structure.setByteValue(targetFeature, d);
        } else if (range.getName().equals(UIMAConstants.TYPE_SHORT)) {
          Short d = Short.parseShort(value);
          structure.setShortValue(targetFeature, d);
        } else if (range.getName().equals(UIMAConstants.TYPE_LONG)) {
          Long d = Long.parseLong(value);
          structure.setLongValue(targetFeature, d);
        } else if (range.getName().equals(UIMAConstants.TYPE_BOOLEAN)) {
          Boolean b = Boolean.parseBoolean(value);
          structure.setBooleanValue(targetFeature, b);
        } else {
        }
      }

    }
  }
}
