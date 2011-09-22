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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.TextMarkerExpression;
import org.apache.uima.textmarker.expression.list.NumberListExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleElementMatch;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.type.TextMarkerFrame;
import org.apache.uima.textmarker.utils.UIMAUtils;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class GatherAction extends AbstractStructureAction {

  private TypeExpression structureType;

  private Map<StringExpression, TextMarkerExpression> features;

  private List<NumberExpression> indexes;

  public GatherAction(TypeExpression structureType,
          Map<StringExpression, TextMarkerExpression> features, List<NumberExpression> indexes) {
    super();
    this.structureType = structureType;
    this.features = features == null ? new HashMap<StringExpression, TextMarkerExpression>()
            : features;
    this.indexes = (indexes == null || indexes.isEmpty()) ? null : indexes;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<Integer> indexList = getIndexList(match, element);
    List<AnnotationFS> matchedAnnotations = match.getMatchedAnnotations(stream, indexList,
            element.getContainer());
    for (AnnotationFS matchedAnnotation : matchedAnnotations) {
      if (matchedAnnotation == null) {
        return;
      }
      Type type = structureType.getType(element.getParent());
      FeatureStructure newFS = stream.getCas().createFS(type);
      if (newFS instanceof Annotation) {
        Annotation a = (Annotation) newFS;
        a.setBegin(matchedAnnotation.getBegin());
        a.setEnd(matchedAnnotation.getEnd());
        stream.addAnnotation(a);
      }
      TOP newStructure = null;
      if (newFS instanceof TOP) {
        newStructure = (TOP) newFS;
        gatherFeatures(newStructure, features, matchedAnnotation, element, match, stream);
        newStructure.addToIndexes();
      }
    }

  }

  private void gatherFeatures(TOP structure, Map<StringExpression, TextMarkerExpression> features,
          AnnotationFS matchedAnnotation, RuleElement element, RuleMatch match,
          TextMarkerStream stream) {
    Map<String, List<Number>> map = new HashMap<String, List<Number>>();
    for (Entry<StringExpression, TextMarkerExpression> each : features.entrySet()) {
      String value = each.getKey().getStringValue(element.getParent());
      TextMarkerExpression expr = each.getValue();
      List<Number> ints = new ArrayList<Number>();
      if (expr instanceof NumberExpression) {
        NumberExpression ne = (NumberExpression) expr;
        ints.add(ne.getIntegerValue(element.getParent()));
        map.put(value, ints);
      } else if (expr instanceof NumberListExpression) {
        NumberListExpression ne = (NumberListExpression) expr;
        map.put(value, ne.getList(element.getParent()));
      }
    }

    TypeSystem typeSystem = stream.getCas().getTypeSystem();
    JCas jcas = stream.getJCas();
    List<?> featuresList = structure.getType().getFeatures();
    for (int i = 0; i < featuresList.size(); i++) {
      Feature targetFeature = (Feature) featuresList.get(i);
      String name = targetFeature.getName();
      String shortFName = name.substring(name.indexOf(":") + 1, name.length());
      List<Number> reIndexes = map.get(shortFName);
      if (reIndexes != null && !reIndexes.isEmpty()) {
        Type range = targetFeature.getRange();

        List<RuleElementMatch> tms = getMatchInfo(match, element, reIndexes);
        if (tms.size() == 0) {// do nothing

        } else if (tms.size() == 1) {
          RuleElementMatch tm = tms.get(0);
          List<AnnotationFS> textsMatched = tm.getTextsMatched();
          if (textsMatched.size() == 1) {
            AnnotationFS fs = textsMatched.get(0);
            if (typeSystem.subsumes(jcas.getCasType(FSArray.type), range)) {
              List<AnnotationFS> list = new ArrayList<AnnotationFS>();
              list.add(fs);
              structure.setFeatureValue(targetFeature, UIMAUtils.toFSArray(jcas, list));
            } else if (typeSystem.subsumes(range, fs.getType())) {
              structure.setFeatureValue(targetFeature, fs);
            }
          }
        } else {
          List<AnnotationFS> textsMatched = getMatchedText(tms);
          if (typeSystem.subsumes(jcas.getCasType(FSArray.type), range)) {
            structure.setFeatureValue(targetFeature, UIMAUtils.toFSArray(jcas, textsMatched));
          } else {
            int begin = textsMatched.get(0).getBegin();
            int end = textsMatched.get(textsMatched.size() - 1).getEnd();
            TextMarkerFrame frame = new TextMarkerFrame(jcas, begin, end);
            FSIterator<Annotation> iterator = jcas.getAnnotationIndex(range).iterator(frame);
            AnnotationFS newA = null;
            while (iterator.isValid()) {
              Annotation a = iterator.get();
              if (a.getBegin() == begin && a.getEnd() == end
                      && jcas.getTypeSystem().subsumes(range, a.getType())) {
                newA = a;
              } else if (a.getBegin() > begin || a.getEnd() < end) {
                break;
              }
              iterator.moveToNext();
            }
            if (newA == null) {
              newA = jcas.getCas().createAnnotation(range, begin, end);
            }
            structure.setFeatureValue(targetFeature, newA);
          }
        }
      }
    }
  }

  private List<AnnotationFS> getMatchedText(List<RuleElementMatch> tms) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    for (RuleElementMatch each : tms) {
      result.addAll(each.getTextsMatched());
    }
    return result;
  }

  private List<RuleElementMatch> getMatchInfo(RuleMatch match, RuleElement element,
          List<Number> reIndexes) {
    List<RuleElementMatch> result = new ArrayList<RuleElementMatch>();
    List<RuleElement> elements = element.getContainer().getRuleElements();
    for (Number eachNumber : reIndexes) {
      int eachInt = eachNumber.intValue();
      RuleElement ruleElement = elements.get(eachInt - 1);
      List<List<RuleElementMatch>> matchInfo = match.getMatchInfo(ruleElement);
      for (List<RuleElementMatch> list : matchInfo) {
        result.addAll(list);
      }
    }
    return result;
  }

  // TODO refactor duplicate methods -> MarkAction
  protected List<Integer> getIndexList(RuleMatch match, RuleElement element) {
    List<Integer> indexList = new ArrayList<Integer>();
    if (indexes == null || indexes.isEmpty()) {
      int self = element.getContainer().getRuleElements().indexOf(element) + 1;
      indexList.add(self);
      return indexList;
    }
    int last = Integer.MAX_VALUE - 1;
    for (NumberExpression each : indexes) {
      int value = each.getIntegerValue(element.getParent());
      for (int i = Math.min(value, last + 1); i < value; i++) {
        indexList.add(i);
      }
      indexList.add(value);
    }
    return indexList;
  }

  public TypeExpression getStructureType() {
    return structureType;
  }

  public Map<StringExpression, TextMarkerExpression> getFeatures() {
    return features;
  }

  public List<NumberExpression> getIndexes() {
    return indexes;
  }
}
