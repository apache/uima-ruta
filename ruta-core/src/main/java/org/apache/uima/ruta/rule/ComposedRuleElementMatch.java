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
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;

public class ComposedRuleElementMatch extends RuleElementMatch {

  private Map<RuleElement, List<RuleElementMatch>> innerMatches;

  private boolean textsMatchedUpdated = false;

  public ComposedRuleElementMatch(ComposedRuleElement ruleElement,
          ComposedRuleElementMatch containerMatch) {
    super(ruleElement, containerMatch);
    baseConditionMatched = false;
    ComposedRuleElement cre = (ComposedRuleElement) ruleElement;
    innerMatches = new TreeMap<RuleElement, List<RuleElementMatch>>(new RuleElementComparator(cre));
    List<RuleElement> ruleElements = cre.getRuleElements();
    for (RuleElement eachRuleElement : ruleElements) {
      innerMatches.put(eachRuleElement, null);
    }
  }

  protected void enforceUpdate() {
    textsMatchedUpdated = false;
    ComposedRuleElementMatch cm = getContainerMatch();
    if (cm != null) {
      cm.enforceUpdate();
    }
  }

  private void setInnerMatches(Map<RuleElement, List<RuleElementMatch>> innerMatches) {
    this.innerMatches = innerMatches;
    enforceUpdate();
  }

  public Map<RuleElement, List<RuleElementMatch>> getInnerMatches() {
    return innerMatches;
  }

  public void addInnerMatch(RuleElement ruleElement, RuleElementMatch ruleElementMatch,
          RutaStream stream) {
    addInnerMatch(ruleElement, ruleElementMatch, true, stream);
  }

  public void addInnerMatch(RuleElement ruleElement, RuleElementMatch ruleElementMatch,
          boolean included, RutaStream stream) {
    List<RuleElementMatch> list = innerMatches.get(ruleElement);
    if (list == null) {
      list = new ArrayList<RuleElementMatch>();
      innerMatches.put(ruleElement, list);
    }
    list.add(ruleElementMatch);
    evaluateInnerMatches(included, stream);
    enforceUpdate();
  }

  public void evaluateInnerMatches(boolean included, RutaStream stream) {
    boolean allDone = true;
    boolean oneDone = false;
    Set<Entry<RuleElement, List<RuleElementMatch>>> entrySet = innerMatches.entrySet();
    for (Entry<RuleElement, List<RuleElementMatch>> entry : entrySet) {
      RuleElement element = entry.getKey();
      List<RuleElementMatch> value = entry.getValue();
      allDone &= (element.getQuantifier().isOptional(element.getParent(), stream) || value != null);
      if (value != null && !value.isEmpty() && included) {
        for (RuleElementMatch ruleElementMatch : value) {
          allDone &= ruleElementMatch.matched();
          oneDone |= ruleElementMatch.matched();
        }
      }
    }
    ComposedRuleElement cre = (ComposedRuleElement) ruleElement;
    if (cre.getConjunct() != null && !cre.getConjunct()) {
      baseConditionMatched = oneDone;
    } else {
      baseConditionMatched = allDone;
    }
  }

  public ComposedRuleElementMatch copy() {
    ComposedRuleElementMatch copy = new ComposedRuleElementMatch((ComposedRuleElement) ruleElement,
            containerMatch);
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

  public ComposedRuleElementMatch copy(ComposedRuleElementMatch extendedContainerMatch,
          boolean after) {
    ComposedRuleElementMatch copy = new ComposedRuleElementMatch((ComposedRuleElement) ruleElement,
            containerMatch);
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
            each.setContainerMatch(copy);
            if (each instanceof ComposedRuleElementMatch) {
              newValue.add(((ComposedRuleElementMatch) each).copy(extendedContainerMatch, after));
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

  public ComposedRuleElementMatch copy2(ComposedRuleElementMatch extendedContainerMatch,
          boolean after) {
    ComposedRuleElementMatch copy = new ComposedRuleElementMatch((ComposedRuleElement) ruleElement,
            containerMatch);
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
        extendedContainerMatch.setContainerMatch(copy);
        if (value != null) {
          List<RuleElementMatch> newValue = new ArrayList<RuleElementMatch>();
          newValue.addAll(value);
          newValue.set(newValue.size() - 1, extendedContainerMatch);
          newMap.put(extendedContainerMatch.getRuleElement(), newValue);
        }
      } else {
        if (value != null && !value.isEmpty()) {
          List<RuleElementMatch> newValue = new ArrayList<RuleElementMatch>();
          int counter = 0;
          for (RuleElementMatch each : value) {
            each.setContainerMatch(copy);
            // really need to copy all?
            boolean isCurrentOne = false;
            if (counter == value.size() - 1) {
              isCurrentOne = true;
            }
            // should not be neccessary because empty matches are listed last
            // if (after && counter == value.size() - 1) {
            // isCurrentOne = true;
            // }
            // if (!after && counter == 0) {
            // isCurrentOne = true;
            // }
            if (each instanceof ComposedRuleElementMatch && isCurrentOne) {
              newValue.add(((ComposedRuleElementMatch) each).copy2(extendedContainerMatch, after));
            } else {
              newValue.add(each.copy());
            }
            counter++;
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

  public void update(ComposedRuleElementMatch extendedContainerMatch) {
    for (Entry<RuleElement, List<RuleElementMatch>> entry : innerMatches.entrySet()) {
      RuleElement key = entry.getKey();
      List<RuleElementMatch> value = entry.getValue();
      if (key.equals(extendedContainerMatch.getRuleElement())) {
        extendedContainerMatch.setContainerMatch(this);
        if (value != null) {
          value.set(value.size() - 1, extendedContainerMatch);
        }
      } else {
        if (value != null) {
          for (RuleElementMatch each : value) {
            if (each instanceof ComposedRuleElementMatch) {
              ((ComposedRuleElementMatch) each).update(extendedContainerMatch);
            }
          }
        }
      }
    }
    enforceUpdate();
  }

  public String toString() {
    return "CREM" + innerMatches.toString();
  }

  public List<AnnotationFS> getTextsMatched() {
    if (!textsMatchedUpdated || textsMatched == null) {
      Collection<AnnotationFS> set = new TreeSet<AnnotationFS>(new AnnotationComparator());
      Collection<List<RuleElementMatch>> values = innerMatches.values();
      for (List<RuleElementMatch> list : values) {
        if (list != null) {
          for (RuleElementMatch ruleElementMatch : list) {
            set.addAll(ruleElementMatch.getTextsMatched());
          }
        }
      }
      textsMatched = new ArrayList<AnnotationFS>(set);
      textsMatchedUpdated = true;
    }
    return textsMatched;
  }

  public void setConditionInfo(List<EvaluatedCondition> evaluatedConditions) {
    conditions = evaluatedConditions;
    for (EvaluatedCondition each : conditions) {
      conditionsMatched = conditionsMatched && each.isValue();
    }

  }

}
