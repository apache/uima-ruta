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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.CollectionUtils;
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
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.number.AbstractNumberListExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleElementMatch;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.type.RutaFrame;
import org.apache.uima.ruta.utils.UIMAUtils;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class GatherAction extends AbstractStructureAction {

  private ITypeExpression structureType;

  private Map<IStringExpression, IRutaExpression> features;

  private List<INumberExpression> indexes;

  public GatherAction(ITypeExpression structureType,
          Map<IStringExpression, IRutaExpression> features, List<INumberExpression> indexes) {
    super();
    this.structureType = structureType;
    this.features = features == null ? new HashMap<IStringExpression, IRutaExpression>() : features;
    this.indexes = (indexes == null || indexes.isEmpty()) ? null : indexes;
  }

  @Override
  public void execute(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
    RuleMatch match = context.getRuleMatch();
    RuleElement element = context.getElement();
    Type type = structureType.getType(context, stream);

    if (type == null) {
      return;
    }

    List<Integer> indexList = getIndexList(indexes, context, stream);
    List<AnnotationFS> matchedAnnotations = match.getMatchedAnnotations(indexList,
            element.getContainer());
    for (AnnotationFS matchedAnnotation : matchedAnnotations) {
      if (matchedAnnotation == null) {
        return;
      }
      FeatureStructure newFS = stream.getCas().createFS(type);
      if (newFS instanceof Annotation) {
        Annotation a = (Annotation) newFS;
        a.setBegin(matchedAnnotation.getBegin());
        a.setEnd(matchedAnnotation.getEnd());
        stream.addAnnotation(a, match);
        addAnnotationToLabel(a, context);
      }
      TOP newStructure = null;
      if (newFS instanceof TOP) {
        newStructure = (TOP) newFS;
        gatherFeatures(newStructure, features, matchedAnnotation, context, stream);
        newStructure.addToIndexes();
      }
    }

  }

  private void gatherFeatures(TOP structure, Map<IStringExpression, IRutaExpression> features,
          AnnotationFS matchedAnnotation, MatchContext context, RutaStream stream) {
    Map<String, List<Number>> map = new HashMap<String, List<Number>>();
    for (Entry<IStringExpression, IRutaExpression> each : features.entrySet()) {
      context.getParent();
      String value = each.getKey().getStringValue(context, stream);
      IRutaExpression expr = each.getValue();
      List<Number> ints = new ArrayList<Number>();
      if (expr instanceof INumberExpression) {
        INumberExpression ne = (INumberExpression) expr;
        ints.add(ne.getIntegerValue(context, stream));
        map.put(value, ints);
      } else if (expr instanceof AbstractNumberListExpression) {
        AbstractNumberListExpression ne = (AbstractNumberListExpression) expr;
        map.put(value, ne.getList(context, stream));
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

        List<RuleElementMatch> tms = getMatchInfo(context.getRuleMatch(), context.getElement(),
                reIndexes);
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
            } else {
              // search for
              Collection<AnnotationFS> beginAnchors = stream.getBeginAnchor(fs.getBegin())
                      .getBeginAnchors(range);
              Collection<AnnotationFS> endAnchors = stream.getEndAnchor(fs.getEnd())
                      .getEndAnchors(range);
              @SuppressWarnings("unchecked")
              Collection<AnnotationFS> intersection = CollectionUtils.intersection(beginAnchors,
                      endAnchors);
              if (intersection.size() >= 1) {
                structure.setFeatureValue(targetFeature, intersection.iterator().next());
              }
            }
          }
        } else {
          List<AnnotationFS> textsMatched = getMatchedText(tms);
          if (typeSystem.subsumes(jcas.getCasType(FSArray.type), range)) {
            structure.setFeatureValue(targetFeature, UIMAUtils.toFSArray(jcas, textsMatched));
          } else {
            int begin = textsMatched.get(0).getBegin();
            int end = textsMatched.get(textsMatched.size() - 1).getEnd();
            RutaFrame frame = new RutaFrame(jcas, begin, end);
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
        if (list != null) {
          result.addAll(list);
        }
      }
    }
    return result;
  }

  public ITypeExpression getStructureType() {
    return structureType;
  }

  public Map<IStringExpression, IRutaExpression> getFeatures() {
    return features;
  }

  public List<INumberExpression> getIndexes() {
    return indexes;
  }
}
