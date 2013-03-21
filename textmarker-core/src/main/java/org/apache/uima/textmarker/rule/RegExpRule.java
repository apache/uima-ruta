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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.ScriptApply;
import org.apache.uima.textmarker.TextMarkerBlock;
import org.apache.uima.textmarker.TextMarkerEnvironment;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class RegExpRule extends AbstractRule {

  private Map<TypeExpression, NumberExpression> typeMap;

  private StringExpression regexpExpr;

  public RegExpRule(StringExpression regexp, Map<TypeExpression, NumberExpression> typeMap, int id,
          TextMarkerBlock parent) {
    super(parent, id);
    this.regexpExpr = regexp;
    this.typeMap = typeMap;
  }

  @Override
  public ScriptApply apply(TextMarkerStream stream, InferenceCrowd crowd) {
    RuleApply ruleApply = new RuleApply(this, false);
    crowd.beginVisit(this, ruleApply);

    String regexpString = regexpExpr.getStringValue(getParent());
    AnnotationFS documentAnnotation = stream.getDocumentAnnotation();
    String document = documentAnnotation.getCoveredText();
    int delta = documentAnnotation.getBegin();
    
    Map<Integer, List<Type>> groupTypes = getGroup2Types();

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
          createAnnotations(i, delta, begin, end, types, ruleMatch, stream);
        } else if(i==0) {
          CAS cas = stream.getCas();
          AnnotationFS afs = cas.createAnnotation(cas.getAnnotationType(), delta + begin, delta + end);
          ruleMatch.addMatched(0, afs);
        }
      }
      if(!ruleMatch.getMatchedAnnotationsOfRoot(stream).isEmpty()) {
        ruleApply.add(ruleMatch);
      }
    }

    crowd.endVisit(this, ruleApply);
    return ruleApply;
  }

  private Map<Integer, List<Type>> getGroup2Types() {
    Map<Integer, List<Type>> groupTypes = new TreeMap<Integer, List<Type>>();

    Set<Entry<TypeExpression, NumberExpression>> entrySet = typeMap.entrySet();
    for (Entry<TypeExpression, NumberExpression> entry : entrySet) {
      Type type = entry.getKey().getType(getParent());
      NumberExpression value = entry.getValue();
      int group = value == null ? 0 : value.getIntegerValue(getParent());
      List<Type> list = groupTypes.get(group);
      if (list == null) {
        list = new ArrayList<Type>();
        groupTypes.put(group, list);
      }
      list.add(type);
    }
    return groupTypes;
  }

  private void createAnnotations(int group, int delta, int begin, int end, List<Type> globalTypes, RegExpRuleMatch match, TextMarkerStream stream) {
    CAS cas = stream.getCas();
    if (begin < end) {
      for (Type type : globalTypes) {
        AnnotationFS afs = cas.createAnnotation(type, delta + begin, delta + end);
        match.addMatched(group, afs);
        stream.addAnnotation(afs, true, true, match);
      }
    }
  }

  @Override
  public TextMarkerEnvironment getEnvironment() {
    return getParent().getEnvironment();
  }

  public Map<TypeExpression, NumberExpression> getTypeMap() {
    return typeMap;
  }

  public void setTypeMap(Map<TypeExpression, NumberExpression> typeMap) {
    this.typeMap = typeMap;
  }

  public StringExpression getRegExp() {
    return regexpExpr;
  }

  public void setRegExp(StringExpression regexpExpr) {
    this.regexpExpr = regexpExpr;
  }


}
