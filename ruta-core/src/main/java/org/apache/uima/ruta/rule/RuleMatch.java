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
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaElement;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.ScriptApply;
import org.apache.uima.ruta.engine.RutaEngine;

public class RuleMatch extends AbstractRuleMatch<RutaRule> {

  private static class RuleMatchComparator implements Comparator<RuleElementMatch> {

    public int compare(RuleElementMatch rem1, RuleElementMatch rem2) {
      AnnotationFS fs1 = rem1.getTextsMatched().get(0);
      AnnotationFS fs2 = rem2.getTextsMatched().get(0);

      return fs1.getBegin() < fs2.getBegin() ? -1 : 1;
    }
  }

  private boolean applied = false;

  private Map<RutaElement, ScriptApply> delegateApply;

  // private Map<RuleElement, List<RuleElementMatch>> map;

  private ComposedRuleElementMatch rootMatch;

  public RuleMatch(RutaRule rule) {
    super(rule);
    delegateApply = new HashMap<RutaElement, ScriptApply>(0);
  }

  public boolean matchedCompletely() {
    return matched && rootMatch.matched();
  }

  public List<AnnotationFS> getMatchedAnnotationsOf(RuleElement element) {
    return getMatchedAnnotations(element.getSelfIndexList(), element.getContainer());
  }

  public AnnotationFS getLastMatchedAnnotation(RuleElement element, boolean direction,
          AnnotationFS annotation, RutaBlock parent, RutaStream stream) {
    List<AnnotationFS> matchedAnnotations = getMatchedAnnotationsOf(element);
    if (matchedAnnotations.isEmpty()) {
      if (element.getQuantifier().isOptional(parent, stream)) {
        return annotation;
      } else {
        return null;
      }
    }
    if (direction) {
      return matchedAnnotations.get(matchedAnnotations.size() - 1);
    } else {
      return matchedAnnotations.get(0);
    }
  }

  @Override
  public List<AnnotationFS> getMatchedAnnotationsOfRoot() {
    return getMatchedAnnotationsOf(((RutaRule) getRule()).getRoot());
  }

  public List<AnnotationFS> getMatchedAnnotations(List<Integer> indexes,
          RuleElementContainer container) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    indexes = extendIndexes(indexes);
    if (container == null) {
      container = ((RutaRule) rule).getRoot();
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
            AnnotationFS firstNormal = getFirstNormal(textsMatched);
            if (firstNormal != null) {
              return textsMatched;
            }
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
    CAS cas = null;
    for (List<List<RuleElementMatch>> list : reverseList) {
      int begin = Integer.MAX_VALUE;
      int end = 0;
      for (List<RuleElementMatch> list2 : list) {
        if (list2 != null) {
          for (RuleElementMatch ruleElementMatch : list2) {
            List<AnnotationFS> textsMatched = ruleElementMatch.getTextsMatched();
            if (textsMatched != null && !textsMatched.isEmpty()) {
              AnnotationFS first = getFirstNormal(textsMatched);
              if (first != null) {
                begin = Math.min(first.getBegin(), begin);
              }
              AnnotationFS last = getLastNormal(textsMatched);
              if (last != null) {
                end = Math.max(last.getEnd(), end);
              }
              if (cas == null && first != null) {
                cas = first.getCAS();
              }
            }
          }
        }
      }
      if (cas != null && end != 0) {
        AnnotationFS annotation = cas.createAnnotation(cas.getAnnotationType(), begin, end);
        result.add(annotation);
      }
    }
    return result;
  }

  private AnnotationFS getFirstNormal(List<AnnotationFS> textsMatched) {
    // hotfix for invisible dummy matches
    int pointer = 0;
    AnnotationFS annotationFS = null;

    if (textsMatched.size() == 0) {
      return null;
    } else if (textsMatched.size() == 1) {
      AnnotationFS fs = textsMatched.get(0);
      if (fs.getType().getName().equals(RutaEngine.OPTIONAL_TYPE)) {
        return null;
      }
    }

    while (pointer < textsMatched.size() && (annotationFS = textsMatched.get(pointer)) != null
            && annotationFS.getType().getName().equals(RutaEngine.OPTIONAL_TYPE)) {
      pointer++;
    }
    if (pointer < textsMatched.size()) {
      return annotationFS;
    }
    return null;
  }

  private AnnotationFS getLastNormal(List<AnnotationFS> textsMatched) {
    // hotfix for invisible dummy matches
    int pointer = textsMatched.size() - 1;
    AnnotationFS annotationFS = null;
    while (pointer >= 0 && (annotationFS = textsMatched.get(pointer)) != null
            && annotationFS.getType().getName().equals(RutaEngine.OPTIONAL_TYPE)) {
      pointer--;
    }
    if (pointer >= 0) {
      return annotationFS;
    }
    return null;
  }

  public static List<Integer> extendIndexes(List<Integer> indexes) {
    if (indexes == null || indexes.size() <= 1) {
      return indexes;
    }
    List<Integer> result = new ArrayList<Integer>();
    int pointer = indexes.get(0);
    for (Integer each : indexes) {
      while (pointer < each - 1) {
        pointer++;
        result.add(pointer);
      }
      result.add(each);
      pointer = each;
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

  public Map<RutaElement, ScriptApply> getDelegateApply() {
    return delegateApply;
  }

  public void addDelegateApply(RutaElement element, ScriptApply scriptApply) {
    delegateApply.put(element, scriptApply);
  }

  public void setMatched(boolean matched) {
    this.matched = matched;
  }

  public RuleMatch copy(ComposedRuleElementMatch extendedContainerMatch, boolean after) {
    RuleMatch copy = new RuleMatch(rule);
    copy.setMatched(matched);
    if (extendedContainerMatch.getContainerMatch() == null) {
      copy.setRootMatch(extendedContainerMatch);
    } else {
      copy.setRootMatch(rootMatch.copy2(extendedContainerMatch, after));
    }

    Map<RutaElement, ScriptApply> newDelegateApply = new HashMap<RutaElement, ScriptApply>(
            delegateApply);
    copy.setDelegateApply(newDelegateApply);
    return copy;
  }

  public RuleMatch copy() {
    RuleMatch copy = new RuleMatch(rule);
    copy.setMatched(matched);
    copy.setRootMatch(rootMatch.copy());
    Map<RutaElement, ScriptApply> newDelegateApply = new HashMap<RutaElement, ScriptApply>(
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

  public void setDelegateApply(Map<RutaElement, ScriptApply> delegateApply) {
    this.delegateApply = delegateApply;
  }

  public boolean isApplied() {
    return applied;
  }

  public void setApplied(boolean applied) {
    this.applied = applied;
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

  public RuleElementMatch getLastMatch(RuleElement ruleElement, boolean direction) {
    List<List<RuleElementMatch>> matchInfo = getMatchInfo(ruleElement);
    if (matchInfo == null || matchInfo.isEmpty()) {
      return null;
    }
    if (direction) {
      List<RuleElementMatch> list = matchInfo.get(matchInfo.size() - 1);
      if (list != null && !list.isEmpty()) {
        return list.get(list.size() - 1);
      }
    } else {
      List<RuleElementMatch> list = matchInfo.get(0);
      if (list != null && !list.isEmpty()) {
        return list.get(0);
      }
    }
    return null;
  }

}
