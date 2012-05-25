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
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.textmarker.ScriptApply;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.action.AbstractTextMarkerAction;

public class RuleMatch {

  private static class RuleMatchComparator implements Comparator<RuleElementMatch> {

    public int compare(RuleElementMatch rem1, RuleElementMatch rem2) {
      AnnotationFS fs1 = rem1.getTextsMatched().get(0);
      AnnotationFS fs2 = rem2.getTextsMatched().get(0);

      return fs1.getBegin() < fs2.getBegin() ? -1 : 1;
    }
  }

  private RuleMatchComparator ruleElementComparator = new RuleMatchComparator();

  private boolean matched = true;

  private boolean applied = false;

  private final TextMarkerRule rule;

  private Map<AbstractTextMarkerAction, ScriptApply> delegateApply;

  // private Map<RuleElement, List<RuleElementMatch>> map;

  private ComposedRuleElementMatch rootMatch;

  public RuleMatch(TextMarkerRule rule) {
    super();
    this.rule = rule;
    // map = new TreeMap<RuleElement, List<RuleElementMatch>>(
    // new RuleElementComparator(rule.getRoot()));
    delegateApply = new HashMap<AbstractTextMarkerAction, ScriptApply>(0);
  }

  public boolean processMatchInfo(RuleElement ruleElement,
          List<RuleElementMatch> ruleElementMatches, TextMarkerStream stream) {
    // return true, if you changed the matches -> current basic needs to be set correctly
    // TODO remove this here?!
    boolean result = false;
    // List<RuleElementMatch> evaluatedMatches = ruleElement.evaluateMatches(ruleElementMatches,
    // ruleElement.getParent());
    // if (evaluatedMatches != null) {
    // ruleElementMatches = evaluatedMatches;
    // result = true;
    // }
    //
    // matched = evaluatedMatches != null;

    return result;
  }

  public boolean matched() {
    return matched;
  }

  public boolean matchedCompletely() {
    return matched && rootMatch.matched();
  }

  public List<AnnotationFS> getMatchedAnnotationsOf(RuleElement element, TextMarkerStream stream) {
    return getMatchedAnnotations(stream, element.getSelfIndexList(), element.getContainer());
  }

  public List<AnnotationFS> getMatchedAnnotations(TextMarkerStream stream, List<Integer> indexes,
          RuleElementContainer container) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();

    if (container == null) {
      container = rule.getRoot();
    }

    // TODO refactor this!
    if (indexes == null) {
      List<RuleElement> ruleElements = container.getRuleElements();
      indexes = new ArrayList<Integer>();
      for (RuleElement ruleElement : ruleElements) {
        indexes.add(ruleElements.indexOf(ruleElement) + 1);
      }
    }
    // TODO refactor this
    // this method should not be called by elements that need the actual annotation, e.g, for
    // accessing theirs features.
    // this is a hotfix for that case...
    if (indexes.size() == 1) {
      RuleElement ruleElement = container.getRuleElements().get(indexes.get(0) - 1);
      List<List<RuleElementMatch>> matchInfo = getMatchInfo(ruleElement);
      if (matchInfo.size() == 1) {
        List<RuleElementMatch> list = matchInfo.get(0);
        if (list != null && list.size() == 1) {
          List<AnnotationFS> textsMatched = list.get(0).getTextsMatched();
          if (textsMatched.size() == 1) {
            return textsMatched;
          }
        }
      }
    }

    List<List<List<RuleElementMatch>>> reverseList = new ArrayList<List<List<RuleElementMatch>>>();
    for (Integer index : indexes) {
      if (index > container.getRuleElements().size()) {
        continue;
      }
      RuleElement ruleElement = container.getRuleElements().get(index - 1);
      List<List<RuleElementMatch>> matchInfo = getMatchInfo(ruleElement);
      int i = 0;
      for (List<RuleElementMatch> list : matchInfo) {
        if (reverseList.size() <= i) {
          reverseList.add(new ArrayList<List<RuleElementMatch>>());
        }
        List<List<RuleElementMatch>> l = reverseList.get(i);
        l.add(list);
        i++;
      }
    }
    for (List<List<RuleElementMatch>> list : reverseList) {
      int begin = Integer.MAX_VALUE;
      int end = 0;
      for (List<RuleElementMatch> list2 : list) {
        if (list2 != null) {
          for (RuleElementMatch ruleElementMatch : list2) {

            List<AnnotationFS> textsMatched = ruleElementMatch.getTextsMatched();
            if (textsMatched != null && !textsMatched.isEmpty()) {
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
        result.add(annotation);
      }
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append((matched ? "Matched : " : "Not Matched :"));
    result.append(getRootMatch());
    return result.toString();
  }

  public Map<AbstractTextMarkerAction, ScriptApply> getDelegateApply() {
    return delegateApply;
  }

  public void addDelegateApply(AbstractTextMarkerAction action, ScriptApply scriptApply) {
    delegateApply.put(action, scriptApply);
  }

  public void setMatched(boolean matched) {
    this.matched = matched;
  }

  public RuleMatch copy(ComposedRuleElementMatch extendedContainerMatch) {
    RuleMatch copy = new RuleMatch(rule);
    copy.setMatched(matched);
    if (extendedContainerMatch.getContainerMatch() == null) {
      copy.setRootMatch(extendedContainerMatch);
    } else {
      copy.setRootMatch(rootMatch.copy(extendedContainerMatch));
    }

    Map<AbstractTextMarkerAction, ScriptApply> newDelegateApply = new HashMap<AbstractTextMarkerAction, ScriptApply>(
            delegateApply);
    copy.setDelegateApply(newDelegateApply);
    return copy;
  }
  
  public void update(ComposedRuleElementMatch extendedContainerMatch) {
    if (extendedContainerMatch.getContainerMatch() == null) {
      setRootMatch(extendedContainerMatch);
    } else {
      rootMatch.update(extendedContainerMatch);
    }
  }

  public void setDelegateApply(Map<AbstractTextMarkerAction, ScriptApply> delegateApply) {
    this.delegateApply = delegateApply;
  }

  public boolean isApplied() {
    return applied;
  }

  public void setApplied(boolean applied) {
    this.applied = applied;
  }

  public TextMarkerRule getRule() {
    return rule;
  }

  public ComposedRuleElementMatch getRootMatch() {
    return rootMatch;
  }

  public void setRootMatch(ComposedRuleElementMatch rootMatch) {
    this.rootMatch = rootMatch;
  }

  public List<List<RuleElementMatch>> getMatchInfo(RuleElement element) {
    return getMatchInfo(rootMatch, element);
  }

  public List<List<RuleElementMatch>> getMatchInfo(RuleElementMatch rootMatch, RuleElement element) {
    List<List<RuleElementMatch>> result = new ArrayList<List<RuleElementMatch>>();
    RuleElement root = rootMatch.getRuleElement();
    if (element.equals(root)) {
      List<RuleElementMatch> list = new ArrayList<RuleElementMatch>(1);
      list.add(rootMatch);
      result.add(list);
    } else if (rootMatch instanceof ComposedRuleElementMatch) {
      ComposedRuleElementMatch crem = (ComposedRuleElementMatch) rootMatch;
      Set<Entry<RuleElement, List<RuleElementMatch>>> entrySet = crem.getInnerMatches().entrySet();
      for (Entry<RuleElement, List<RuleElementMatch>> entry : entrySet) {
        List<RuleElementMatch> value = entry.getValue();
        if (element.equals(entry.getKey())) {
          result.add(value);
        } else {
          if (value != null) {
            for (RuleElementMatch eachMatch : value) {
              result.addAll(getMatchInfo(eachMatch, element));
            }
          }
        }
      }
    }
    return result;
  }
}
