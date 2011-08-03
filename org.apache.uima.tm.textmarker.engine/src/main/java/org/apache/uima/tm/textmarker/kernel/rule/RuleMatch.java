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

package org.apache.uima.tm.textmarker.kernel.rule;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.tm.textmarker.action.AbstractTextMarkerAction;
import org.apache.uima.tm.textmarker.kernel.ScriptApply;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;


public class RuleMatch {

  private static class RuleElementComparator implements Comparator<RuleElement> {
    private final TextMarkerRule rule;

    public RuleElementComparator(TextMarkerRule rule) {
      this.rule = rule;
    }

    public int compare(RuleElement re1, RuleElement re2) {
      List<RuleElement> elements = rule.getElements();
      int i1 = elements.indexOf(re1);
      int i2 = elements.indexOf(re2);
      if (i1 == i2)
        return 0;
      return i1 < i2 ? -1 : 1;
    }
  }

  private boolean matched;

  private TextMarkerBasic first;

  private final TextMarkerRule rule;

  private Map<AbstractTextMarkerAction, ScriptApply> delegateApply;

  private Map<RuleElement, List<RuleElementMatch>> map;

  public RuleMatch(TextMarkerRule rule) {
    super();
    this.rule = rule;
    map = new TreeMap<RuleElement, List<RuleElementMatch>>(new RuleElementComparator(rule));
    delegateApply = new HashMap<AbstractTextMarkerAction, ScriptApply>(0);
  }

  public boolean processMatchInfo(RuleElement ruleElement,
          List<RuleElementMatch> ruleElementMatches, TextMarkerStream stream) {
    // return true, if you changed the matches -> current basic needs to be set correctly
    boolean result = false;
    List<RuleElementMatch> evaluatedMatches = ruleElement.evaluateMatches(ruleElementMatches,
            ruleElement.getParent());
    if (evaluatedMatches != null) {
      ruleElementMatches = evaluatedMatches;
      result = true;
    }
    map.put(ruleElement, ruleElementMatches);
    matched = evaluatedMatches != null;

    if (first == null && !ruleElementMatches.isEmpty()) {
      List<AnnotationFS> textsMatched = ruleElementMatches.get(0).getTextsMatched();
      if (!textsMatched.isEmpty()) {
        AnnotationFS annotation = textsMatched.get(0);
        if (annotation instanceof TextMarkerBasic) {
          first = (TextMarkerBasic) annotation;
        } else if (annotation.getBegin() == stream.getDocumentAnnotation().getBegin()
                && annotation.getEnd() == stream.getDocumentAnnotation().getEnd()) {
          first = stream.getFirstBasicOfAll();
        } else {
          first = stream.getFirstBasicInWindow(annotation);
        }
      }
    }
    return result;
  }

  public Map<RuleElement, List<RuleElementMatch>> getMatchInfos() {
    return map;
  }

  public List<RuleElementMatch> getMatchInfo(TextMarkerRuleElement element) {
    return map.get(element);
  }

  public boolean matched() {
    return matched;
  }

  public AnnotationFS getMatchedAnnotation(TextMarkerStream stream, List<Integer> indexes) {
    int begin = Integer.MAX_VALUE;
    int end = 0;
    for (RuleElement element : map.keySet()) {
      int indexOf = rule.getElements().indexOf(element) + 1;
      if (indexes == null || indexes.isEmpty() || indexes.contains(indexOf)) {
        for (RuleElementMatch rem : map.get(element)) {
          List<AnnotationFS> textsMatched = rem.getTextsMatched();
          if (!textsMatched.isEmpty()) {
            begin = Math.min(textsMatched.get(0).getBegin(), begin);
            end = Math.max(textsMatched.get(textsMatched.size() - 1).getEnd(), end);
          }
        }
      }
    }
    if (stream.getJCas() != null && end != 0) {
      Annotation annotation = new Annotation(stream.getJCas());
      annotation.setBegin(begin);
      annotation.setEnd(end);
      return annotation;
    } else {
      return null;
    }
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append((matched ? "Matched : " : "Not Matched :"));
    result.append(getMatchInfos());
    return result.toString();
  }

  public TextMarkerBasic getFirstBasic() {
    return first;
  }

  public final TextMarkerRule getRule() {
    return rule;
  }

  public Map<AbstractTextMarkerAction, ScriptApply> getDelegateApply() {
    return delegateApply;
  }

  public void addDelegateApply(AbstractTextMarkerAction action, ScriptApply scriptApply) {
    delegateApply.put(action, scriptApply);
  }

}
