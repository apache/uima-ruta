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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.uima.cas.text.AnnotationFS;

public class ComposedRuleElementMatch extends RuleElementMatch {

  private Map<RuleElement, List<RuleElementMatch>> innerMatches;

  public ComposedRuleElementMatch(RuleElement ruleElement, ComposedRuleElementMatch containerMatch) {
    super(ruleElement, containerMatch);
    baseConditionMatched = false;
    ComposedRuleElement cre = (ComposedRuleElement) ruleElement;
    innerMatches = new TreeMap<RuleElement, List<RuleElementMatch>>(new RuleElementComparator(cre));
    List<RuleElement> ruleElements = cre.getRuleElements();
    for (RuleElement eachRuleElement : ruleElements) {
      innerMatches.put(eachRuleElement, null);
    }
  }

  private void setInnerMatches(Map<RuleElement, List<RuleElementMatch>> innerMatches) {
    this.innerMatches = innerMatches;
    // textsMatched = new ArrayList<AnnotationFS>();
    // boolean matched = true;
    // for (Entry<RuleElement, List<RuleElementMatch>> entry : innerMatches.entrySet()) {
    // for (RuleElementMatch ruleElementMatch : entry.getValue()) {
    // List<AnnotationFS> list = ruleElementMatch.getTextsMatched();
    // textsMatched.addAll(list);
    // matched &= ruleElementMatch.matched();
    // }
    // }
    // baseConditionMatched = matched;
    // conditionsMatched = matched;
  }

  public Map<RuleElement, List<RuleElementMatch>> getInnerMatches() {
    return innerMatches;
  }

  public void addInnerMatch(RuleElement ruleElement, RuleElementMatch ruleElementMatch) {
    List<RuleElementMatch> list = innerMatches.get(ruleElement);
    if (list == null) {
      list = new ArrayList<RuleElementMatch>();
      innerMatches.put(ruleElement, list);
    }
    list.add(ruleElementMatch);
    textsMatched.addAll(ruleElementMatch.getTextsMatched());

    evaluateInnerMatches();
  }

  public void evaluateInnerMatches() {
    boolean allDone = true;
    Set<Entry<RuleElement, List<RuleElementMatch>>> entrySet = innerMatches.entrySet();
    for (Entry<RuleElement, List<RuleElementMatch>> entry : entrySet) {
      RuleElement element = entry.getKey();
      allDone &= (element.getQuantifier().isOptional(element.getParent()) || entry.getValue() != null);
    }
    baseConditionMatched = allDone;
  }

  public ComposedRuleElementMatch copy() {
    ComposedRuleElementMatch copy = new ComposedRuleElementMatch(ruleElement, containerMatch);
    copy.setBaseConditionMatched(baseConditionMatched);
    copy.setConditions(conditions);
    copy.setConditionsMatched(conditionsMatched);
    copy.setTextsMatched(textsMatched);
    Map<RuleElement, List<RuleElementMatch>> newMap = new TreeMap<RuleElement, List<RuleElementMatch>>(
            new RuleElementComparator((ComposedRuleElement) ruleElement));
    for (Entry<RuleElement, List<RuleElementMatch>> entry : innerMatches.entrySet()) {
      List<RuleElementMatch> value = entry.getValue();
      if (value != null) {
        List<RuleElementMatch> newValue = new ArrayList<RuleElementMatch>();
        for (RuleElementMatch each : value) {
          newValue.add(each.copy());
        }
        newMap.put(entry.getKey(), newValue);
      } else {
        newMap.put(entry.getKey(), null);
      }
    }
    copy.setInnerMatches(newMap);
    return copy;
  }

  public ComposedRuleElementMatch copy(ComposedRuleElementMatch extendedContainerMatch) {
    ComposedRuleElementMatch copy = new ComposedRuleElementMatch(ruleElement, containerMatch);
    copy.setBaseConditionMatched(baseConditionMatched);
    copy.setConditions(conditions);
    copy.setConditionsMatched(conditionsMatched);
    copy.setTextsMatched(textsMatched);
    Map<RuleElement, List<RuleElementMatch>> newMap = new TreeMap<RuleElement, List<RuleElementMatch>>(
            new RuleElementComparator((ComposedRuleElement) ruleElement));
    for (Entry<RuleElement, List<RuleElementMatch>> entry : innerMatches.entrySet()) {
      RuleElement key = entry.getKey();
      List<RuleElementMatch> value = entry.getValue();
      if (key.equals(extendedContainerMatch.getRuleElement())) {
        // if (value.isEmpty()) {
        extendedContainerMatch.setContainerMatch(copy);
        if (value != null) {
          List<RuleElementMatch> newValue = new ArrayList<RuleElementMatch>();
          newValue.addAll(value);
          newValue.set(newValue.size() - 1, extendedContainerMatch);
          newMap.put(extendedContainerMatch.getRuleElement(), newValue);
        }
        // } else {
        // List<RuleElementMatch> newValue = new ArrayList<RuleElementMatch>();
        // newValue.addAll(value);
        // newValue.add(extendedContainerMatch);
        // newMap.put(extendedContainerMatch.getRuleElement(), newValue);
        // }
      } else {
        if (value != null) {
          List<RuleElementMatch> newValue = new ArrayList<RuleElementMatch>();
          for (RuleElementMatch each : value) {
            if (each instanceof ComposedRuleElementMatch) {
              newValue.add(((ComposedRuleElementMatch) each).copy(extendedContainerMatch));
            } else {
              newValue.add(each.copy());
            }
          }
          newMap.put(entry.getKey(), newValue);
        } else {
          newMap.put(entry.getKey(), null);
        }
      }
    }
    copy.setInnerMatches(newMap);
    return copy;
  }

  public String toString() {
    return "CREM" + innerMatches.toString();
  }

  public List<AnnotationFS> getTextsMatched() {
    // TODO find a better solution for this
    Collection<AnnotationFS> set = new TreeSet<AnnotationFS>(new AnnotationComparator());
    Collection<List<RuleElementMatch>> values = innerMatches.values();
    for (List<RuleElementMatch> list : values) {
      if (list != null) {
        for (RuleElementMatch ruleElementMatch : list) {
          set.addAll(ruleElementMatch.getTextsMatched());
        }
      }
    }
    return Arrays.asList(set.toArray(new AnnotationFS[0]));
  }

  public void setConditionInfo(List<EvaluatedCondition> evaluatedConditions) {
    conditions = evaluatedConditions;
    for (EvaluatedCondition each : conditions) {
      conditionsMatched &= each.isValue();
    }

  }

}
