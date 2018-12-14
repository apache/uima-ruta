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

package org.apache.uima.ruta.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.ruta.RutaEnvironment;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.ScriptApply;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.AbstractStringExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.utils.UIMAUtils;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class RegExpRule extends AbstractRule {

  private Map<ITypeExpression, INumberExpression> typeMap;

  private IStringExpression regexpExpr;

  private Map<ITypeExpression, Map<IStringExpression, IRutaExpression>> featureAssignments;

  public RegExpRule(AbstractStringExpression regexp,
          Map<ITypeExpression, INumberExpression> typeMap, int id, RutaBlock parent) {
    super(parent, id);
    this.regexpExpr = regexp;
    this.typeMap = typeMap;
  }

  @Override
  public ScriptApply apply(RutaStream stream, InferenceCrowd crowd) {
    RuleApply ruleApply = new RuleApply(this, false);
    crowd.beginVisit(this, ruleApply);
    MatchContext context = new MatchContext(getParent());
    String regexpString = regexpExpr.getStringValue(context, stream);
    AnnotationFS documentAnnotation = stream.getDocumentAnnotation();
    String document = documentAnnotation.getCoveredText();
    int delta = documentAnnotation.getBegin();

    Map<Integer, List<Type>> groupTypes = getGroup2Types(context, stream);
    Map<Integer, Map<Type, Map<String, Object>>> fa = getFeatureAssignmentMap(stream);

    Pattern pattern = Pattern.compile(regexpString, Pattern.MULTILINE | Pattern.DOTALL);
    Matcher matcher = pattern.matcher(document);
    int groupCount = matcher.groupCount();
    while (matcher.find()) {
      RegExpRuleMatch ruleMatch = new RegExpRuleMatch(this);
      MatchResult matchResult = matcher.toMatchResult();
      for (int i = 0; i <= groupCount; i++) {
        int begin = matchResult.start(i);
        int end = matchResult.end(i);
        List<Type> types = groupTypes.get(i);
        if (types != null) {
          createAnnotations(i, delta, begin, end, types, fa, matchResult, ruleMatch, stream);
        } else if (i == 0) {
          CAS cas = stream.getCas();
          AnnotationFS afs = cas.createAnnotation(cas.getAnnotationType(), delta + begin,
                  delta + end);
          ruleMatch.addMatched(0, afs);
        }
      }
      List<AnnotationFS> matchedAnnotationsOfRoot = ruleMatch.getMatchedAnnotationsOfRoot();
      if (matchedAnnotationsOfRoot != null && !matchedAnnotationsOfRoot.isEmpty()) {
        ruleApply.add(ruleMatch);
      }
    }

    crowd.endVisit(this, ruleApply);
    return ruleApply;
  }

  private Map<Integer, Map<Type, Map<String, Object>>> getFeatureAssignmentMap(RutaStream stream) {
    Map<Integer, Map<Type, Map<String, Object>>> result = new HashMap<Integer, Map<Type, Map<String, Object>>>();
    Set<Entry<ITypeExpression, Map<IStringExpression, IRutaExpression>>> entrySet = featureAssignments
            .entrySet();
    MatchContext context = new MatchContext(getParent());
    for (Entry<ITypeExpression, Map<IStringExpression, IRutaExpression>> entry : entrySet) {
      ITypeExpression key = entry.getKey();
      Type type = key.getType(context, stream);
      Map<IStringExpression, IRutaExpression> value = entry.getValue();
      INumberExpression cgExpr = typeMap.get(key);
      int cg = cgExpr == null ? 0 : cgExpr.getIntegerValue(context, stream);
      Map<Type, Map<String, Object>> map = result.get(cg);
      if (map == null) {
        map = new HashMap<Type, Map<String, Object>>();
        result.put(cg, map);
      }
      Map<String, Object> typeMap = map.get(type);
      if (typeMap == null) {
        typeMap = new HashMap<String, Object>();
        map.put(type, typeMap);
      }
      Set<Entry<IStringExpression, IRutaExpression>> entrySet2 = value.entrySet();
      for (Entry<IStringExpression, IRutaExpression> entry2 : entrySet2) {
        IStringExpression key2 = entry2.getKey();
        IRutaExpression value2 = entry2.getValue();
        String stringValue = key2.getStringValue(context, stream);
        typeMap.put(stringValue, value2);
      }
    }
    return result;
  }

  private Map<Integer, List<Type>> getGroup2Types(MatchContext context, RutaStream stream) {
    Map<Integer, List<Type>> groupTypes = new TreeMap<Integer, List<Type>>();
    Set<Entry<ITypeExpression, INumberExpression>> entrySet = typeMap.entrySet();
    for (Entry<ITypeExpression, INumberExpression> entry : entrySet) {
      Type type = entry.getKey().getType(context, stream);
      INumberExpression value = entry.getValue();
      int group = value == null ? 0 : value.getIntegerValue(context, stream);
      List<Type> list = groupTypes.get(group);
      if (list == null) {
        list = new ArrayList<Type>();
        groupTypes.put(group, list);
      }
      list.add(type);
    }
    return groupTypes;
  }

  private void createAnnotations(int group, int delta, int begin, int end, List<Type> globalTypes,
          Map<Integer, Map<Type, Map<String, Object>>> fa, MatchResult matchResult,
          RegExpRuleMatch match, RutaStream stream) {
    CAS cas = stream.getCas();
    if (begin < end) {
      for (Type type : globalTypes) {
        AnnotationFS afs = cas.createAnnotation(type, delta + begin, delta + end);
        fillFeatures(group, afs, fa, delta, matchResult, stream);
        match.addMatched(group, afs);
        stream.addAnnotation(afs, true, true, match);
      }
    }
  }

  private void fillFeatures(int group, AnnotationFS afs,
          Map<Integer, Map<Type, Map<String, Object>>> fa, int delta, MatchResult matchResult,
          RutaStream stream) {
    Type type = afs.getType();
    JCas jcas = null;
    CAS cas = stream.getCas();
    try {
      jcas = cas.getJCas();
    } catch (CASException e) {
    }
    MatchContext context = new MatchContext(null, null, null, true);
    TypeSystem typeSystem = cas.getTypeSystem();
    Map<Type, Map<String, Object>> typeMap = fa.get(group);
    if (typeMap != null) {
      Map<String, Object> map = typeMap.get(type);
      if (map != null) {
        for (Entry<String, Object> eachEntry : map.entrySet()) {
          String featureName = eachEntry.getKey();
          Feature feature = type.getFeatureByBaseName(featureName);
          if (feature != null) {
            Object argExpr = eachEntry.getValue();
            Type range = feature.getRange();
            if (argExpr instanceof INumberExpression) {
              INumberExpression ne = (INumberExpression) argExpr;
              int cg = ne.getIntegerValue(context, stream);
              if (typeSystem.subsumes(typeSystem.getType(CAS.TYPE_NAME_STRING), range)) {
                String s = matchResult.group(cg);
                afs.setStringValue(feature, s);
              } else if (range.getName().equals(CAS.TYPE_NAME_BOOLEAN)) {
              } else if (range.getName().equals(CAS.TYPE_NAME_BYTE)) {
              } else if (range.getName().equals(CAS.TYPE_NAME_DOUBLE)) {
              } else if (range.getName().equals(CAS.TYPE_NAME_FLOAT)) {
              } else if (range.getName().equals(CAS.TYPE_NAME_INTEGER)) {
              } else if (range.getName().equals(CAS.TYPE_NAME_LONG)) {
              } else if (range.getName().equals(CAS.TYPE_NAME_SHORT)) {
              } else {
                if (typeSystem.subsumes(jcas.getCasType(FSArray.type), range)) {
                  // TODO add functionality for fsarrays
                  // AnnotationFS a = null;
                  // List<AnnotationFS> annotations = new ArrayList<AnnotationFS>(1);
                  // annotations.add(a);
                  // afs.setFeatureValue(feature, UIMAUtils.toFSArray(jcas, annotations));
                } else {
                  int begin = delta + matchResult.start(cg);
                  int end = delta + matchResult.end(cg);
                  if (begin < end) {
                    AnnotationFS a = cas.createAnnotation(range, begin, end);
                    afs.setFeatureValue(feature, a);
                  }
                }
              }
            } else {
              if (argExpr instanceof ITypeExpression
                      && typeSystem.subsumes(typeSystem.getType(CAS.TYPE_NAME_STRING), range)) {
                ITypeExpression typeExpr = (ITypeExpression) argExpr;
                List<AnnotationFS> annotationsInWindow = stream.getAnnotationsInWindow(afs,
                        typeExpr.getType(context, stream));
                if (annotationsInWindow != null && !annotationsInWindow.isEmpty()) {
                  AnnotationFS annotation = annotationsInWindow.get(0);
                  afs.setStringValue(feature, annotation.getCoveredText());
                }
              } else if (argExpr instanceof AbstractStringExpression
                      && typeSystem.subsumes(typeSystem.getType(CAS.TYPE_NAME_STRING), range)) {
                afs.setStringValue(feature,
                        ((AbstractStringExpression) argExpr).getStringValue(context, stream));
                // numbers are reserved for capturing groups
                //
                // } else if (argExpr instanceof NumberExpression) {
                // if (range.getName().equals(CAS.TYPE_NAME_INTEGER)) {
                // afs.setIntValue(feature,
                // ((NumberExpression) argExpr).getIntegerValue(getParent()));
                // } else if (range.getName().equals(CAS.TYPE_NAME_DOUBLE)) {
                // afs.setDoubleValue(feature,
                // ((NumberExpression) argExpr).getDoubleValue(getParent()));
                // } else if (range.getName().equals(CAS.TYPE_NAME_FLOAT)) {
                // afs.setFloatValue(feature,
                // ((NumberExpression) argExpr).getFloatValue(getParent()));
                // } else if (range.getName().equals(CAS.TYPE_NAME_BYTE)) {
                // afs.setByteValue(feature,
                // (byte) ((NumberExpression) argExpr).getIntegerValue(getParent()));
                // } else if (range.getName().equals(CAS.TYPE_NAME_SHORT)) {
                // afs.setShortValue(feature,
                // (short) ((NumberExpression) argExpr).getIntegerValue(getParent()));
                // } else if (range.getName().equals(CAS.TYPE_NAME_LONG)) {
                // afs.setLongValue(feature,
                // (long) ((NumberExpression) argExpr).getIntegerValue(getParent()));
                // }
              } else if (argExpr instanceof IBooleanExpression
                      && range.getName().equals(CAS.TYPE_NAME_BOOLEAN)) {
                afs.setBooleanValue(feature,
                        ((IBooleanExpression) argExpr).getBooleanValue(context, stream));
              } else if (argExpr instanceof ITypeExpression) {
                ITypeExpression typeExpr = (ITypeExpression) argExpr;
                List<AnnotationFS> annotationsInWindow = stream.getAnnotationsInWindow(afs,
                        typeExpr.getType(context, stream));
                if (typeSystem.subsumes(jcas.getCasType(FSArray.type), range)) {
                  afs.setFeatureValue(feature, UIMAUtils.toFSArray(jcas, annotationsInWindow));
                } else if (typeSystem.subsumes(range, typeExpr.getType(context, stream))
                        && !annotationsInWindow.isEmpty()) {
                  AnnotationFS annotation = annotationsInWindow.get(0);
                  afs.setFeatureValue(feature, annotation);
                }
              }
            }
          }
        }
      }
    }
  }

  @Override
  public RutaEnvironment getEnvironment() {
    return getParent().getEnvironment();
  }

  public Map<ITypeExpression, INumberExpression> getTypeMap() {
    return typeMap;
  }

  public void setTypeMap(Map<ITypeExpression, INumberExpression> typeMap) {
    this.typeMap = typeMap;
  }

  public IStringExpression getRegExp() {
    return regexpExpr;
  }

  public void setRegExp(IStringExpression regexp) {
    this.regexpExpr = regexp;
  }

  public void setFeatureAssignments(
          Map<ITypeExpression, Map<IStringExpression, IRutaExpression>> fa) {
    this.featureAssignments = fa;
  }

  public Map<ITypeExpression, Map<IStringExpression, IRutaExpression>> getFeatureAssignments() {
    return featureAssignments;
  }

}
