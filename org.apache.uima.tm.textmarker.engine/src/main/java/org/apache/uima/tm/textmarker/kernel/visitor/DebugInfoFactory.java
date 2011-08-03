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

package org.apache.uima.tm.textmarker.kernel.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.tm.textmarker.kernel.BlockApply;
import org.apache.uima.tm.textmarker.kernel.ScriptApply;
import org.apache.uima.tm.textmarker.kernel.TextMarkerBlock;
import org.apache.uima.tm.textmarker.kernel.TextMarkerElement;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.rule.EvaluatedCondition;
import org.apache.uima.tm.textmarker.kernel.rule.RuleApply;
import org.apache.uima.tm.textmarker.kernel.rule.RuleElement;
import org.apache.uima.tm.textmarker.kernel.rule.RuleElementMatch;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerMatcher;
import org.apache.uima.tm.textmarker.kernel.type.DebugBlockApply;
import org.apache.uima.tm.textmarker.kernel.type.DebugEvaluatedCondition;
import org.apache.uima.tm.textmarker.kernel.type.DebugFailedRuleMatch;
import org.apache.uima.tm.textmarker.kernel.type.DebugMatchedRuleMatch;
import org.apache.uima.tm.textmarker.kernel.type.DebugRuleApply;
import org.apache.uima.tm.textmarker.kernel.type.DebugRuleElementMatch;
import org.apache.uima.tm.textmarker.kernel.type.DebugRuleElementMatches;
import org.apache.uima.tm.textmarker.kernel.type.DebugRuleMatch;
import org.apache.uima.tm.textmarker.kernel.type.DebugScriptApply;
import org.apache.uima.tm.textmarker.utils.UIMAUtils;
import org.apache.uima.tm.textmarker.verbalize.TextMarkerVerbalizer;


public class DebugInfoFactory {

  private TextMarkerVerbalizer verbalizer;

  public DebugInfoFactory(TextMarkerVerbalizer verbalizer) {
    super();
    this.verbalizer = verbalizer;
  }

  public DebugBlockApply createDummyBlockApply(RuleMatch ruleMatch, TextMarkerStream stream,
          boolean addToIndex, boolean withMatches, Map<TextMarkerElement, Long> timeInfo) {
    JCas cas = stream.getJCas();
    DebugBlockApply dba = new DebugBlockApply(cas);
    AnnotationFS matchedAnnotation = ruleMatch.getMatchedAnnotation(stream, null);
    dba.setElement(matchedAnnotation.getCoveredText());
    dba.setBegin(matchedAnnotation.getBegin());
    dba.setEnd(matchedAnnotation.getEnd());
    return dba;
  }

  public DebugBlockApply createDebugBlockApply(BlockApply blockApply, TextMarkerStream stream,
          boolean addToIndex, boolean withMatches, Map<TextMarkerElement, Long> timeInfo) {
    JCas cas = stream.getJCas();
    DebugBlockApply dba = new DebugBlockApply(cas);
    List<DebugScriptApply> innerApply = new ArrayList<DebugScriptApply>();
    // TODO refactor and remove counting hotfix
    int applied = blockApply.getRuleApply().getApplied();
    if (applied > 1) {
      List<ScriptApply> innerApplies = blockApply.getInnerApplies();
      List<List<ScriptApply>> loops = new ArrayList<List<ScriptApply>>();
      for (int i = 0; i < applied; i++) {
        loops.add(new ArrayList<ScriptApply>());
      }
      int counter = 0;
      int size = innerApplies.size();
      int parts = size / applied;
      for (ScriptApply each : innerApplies) {
        int listIndex = Math.max(0, counter / parts);
        List<ScriptApply> list = loops.get(listIndex);
        list.add(each);
        counter++;
      }
      counter = 0;
      for (List<ScriptApply> list : loops) {
        RuleMatch ruleMatch = blockApply.getRuleApply().getList().get(counter);
        DebugBlockApply dummyBlockApply = createDummyBlockApply(ruleMatch, stream, addToIndex,
                withMatches, timeInfo);
        List<DebugRuleMatch> ruleMatches = new ArrayList<DebugRuleMatch>();
        ruleMatches.add(createDebugRuleMatch(ruleMatch, stream, addToIndex, withMatches, timeInfo));
        dummyBlockApply.setApplied(1);
        dummyBlockApply.setTried(1);
        dummyBlockApply.setRules(UIMAUtils.toFSArray(cas, ruleMatches));
        List<DebugScriptApply> innerInnerApply = new ArrayList<DebugScriptApply>();
        for (ScriptApply each : list) {
          DebugScriptApply eachInnerInner = createDebugScriptApply(each, stream, addToIndex,
                  withMatches, timeInfo);
          innerInnerApply.add(eachInnerInner);
        }
        dummyBlockApply.setInnerApply(UIMAUtils.toFSArray(cas, innerInnerApply));
        innerApply.add(dummyBlockApply);
        counter++;
      }
      dba.setInnerApply(UIMAUtils.toFSArray(cas, innerApply));
      dba.setElement(verbalizer.verbalize((TextMarkerBlock) blockApply.getElement(), false));
      DebugRuleApply ruleApply = createDebugRuleApply(blockApply.getRuleApply(), stream,
              addToIndex, withMatches, timeInfo);
      dba.setApplied(ruleApply.getApplied());
      dba.setTried(ruleApply.getTried());
      dba.setRules(ruleApply.getRules());
      dba.setBegin(ruleApply.getBegin());
      dba.setEnd(ruleApply.getEnd());
      if (timeInfo != null) {
        long time = timeInfo.get(blockApply.getElement());
        dba.setTime(time);
      }
      if (addToIndex)
        dba.addToIndexes();
      return dba;
    } else {

      for (ScriptApply each : blockApply.getInnerApplies()) {
        innerApply.add(createDebugScriptApply(each, stream, addToIndex, withMatches, timeInfo));
      }
      dba.setInnerApply(UIMAUtils.toFSArray(cas, innerApply));
      dba.setElement(verbalizer.verbalize((TextMarkerBlock) blockApply.getElement(), false));
      DebugRuleApply ruleApply = createDebugRuleApply(blockApply.getRuleApply(), stream,
              addToIndex, withMatches, timeInfo);
      dba.setApplied(ruleApply.getApplied());
      dba.setTried(ruleApply.getTried());
      dba.setRules(ruleApply.getRules());
      dba.setBegin(ruleApply.getBegin());
      dba.setEnd(ruleApply.getEnd());
      if (timeInfo != null) {
        long time = timeInfo.get(blockApply.getElement());
        dba.setTime(time);
      }
      if (addToIndex)
        dba.addToIndexes();
      return dba;
    }
  }

  public DebugRuleApply createDebugRuleApply(RuleApply ruleApply, TextMarkerStream stream,
          boolean addToIndex, boolean withMatches, Map<TextMarkerElement, Long> timeInfo) {
    JCas cas = stream.getJCas();
    DebugRuleApply dra = new DebugRuleApply(cas);
    List<DebugRuleMatch> ruleMatches = new ArrayList<DebugRuleMatch>();
    int begin = Integer.MAX_VALUE;
    int end = 0;
    if (withMatches) {
      for (RuleMatch match : ruleApply.getList()) {
        DebugRuleMatch debugRuleMatch = createDebugRuleMatch(match, stream, addToIndex,
                withMatches, timeInfo);
        begin = Math.min(begin, debugRuleMatch.getBegin());
        end = Math.max(end, debugRuleMatch.getEnd());
        ruleMatches.add(debugRuleMatch);
      }
    }
    if (begin >= end) {
      begin = end;
    }
    dra.setRules(UIMAUtils.toFSArray(cas, ruleMatches));
    dra.setElement(verbalizer.verbalize(ruleApply.getElement()));
    dra.setApplied(ruleApply.getApplied());
    dra.setTried(ruleApply.getTried());
    dra.setBegin(begin);
    dra.setEnd(end);
    if (timeInfo != null) {
      long time = timeInfo.get(ruleApply.getElement());
      dra.setTime(time);
    }
    if (addToIndex)
      dra.addToIndexes();
    return dra;
  }

  public DebugRuleMatch createDebugRuleMatch(RuleMatch match, TextMarkerStream stream,
          boolean addToIndex, boolean withMatches, Map<TextMarkerElement, Long> timeInfo) {
    JCas cas = stream.getJCas();
    DebugRuleMatch drm = null;
    if (match.matched()) {
      drm = new DebugMatchedRuleMatch(cas);
    } else {
      drm = new DebugFailedRuleMatch(cas);
    }
    List<DebugRuleElementMatches> ruleMatches = new ArrayList<DebugRuleElementMatches>();
    for (RuleElement re : match.getMatchInfos().keySet()) {
      List<RuleElementMatch> rems = match.getMatchInfos().get(re);
      ruleMatches.add(createDebugRuleElementMatches(re, rems, stream, addToIndex));
    }
    drm.setElements(UIMAUtils.toFSArray(cas, ruleMatches));
    drm.setMatched(match.matched());
    if (match.matched()) {
      List<DebugScriptApply> delegates = new ArrayList<DebugScriptApply>();
      for (ScriptApply rem : match.getDelegateApply().values()) {
        delegates.add(createDebugScriptApply(rem, stream, addToIndex, withMatches, timeInfo));
      }
      drm.setDelegates(UIMAUtils.toFSArray(cas, delegates));
    }
    if (timeInfo != null) {
      long time = timeInfo.get(match.getRule());
      drm.setTime(time);
    }
    AnnotationFS matchedAnnotation = match.getMatchedAnnotation(stream, null);
    if (matchedAnnotation != null) {
      drm.setBegin(matchedAnnotation.getBegin());
      drm.setEnd(matchedAnnotation.getEnd());
      if (addToIndex || withMatches)
        drm.addToIndexes();
    }
    return drm;
  }

  public DebugRuleElementMatches createDebugRuleElementMatches(RuleElement re,
          List<RuleElementMatch> rems, TextMarkerStream stream, boolean addToIndex) {
    JCas cas = stream.getJCas();
    DebugRuleElementMatches drems = new DebugRuleElementMatches(cas);
    drems.setElement(verbalizer.verbalize(re));
    List<DebugRuleElementMatch> remList = new ArrayList<DebugRuleElementMatch>();

    for (RuleElementMatch each : rems) {
      remList.add(createDebugRuleElementMatch(each, stream, addToIndex));
    }
    drems.setMatches(UIMAUtils.toFSArray(cas, remList));
    if (addToIndex)
      drems.addToIndexes();
    return drems;
  }

  public DebugRuleElementMatch createDebugRuleElementMatch(RuleElementMatch rem,
          TextMarkerStream stream, boolean addToIndex) {
    JCas cas = stream.getJCas();
    DebugRuleElementMatch drem = new DebugRuleElementMatch(cas);

    DebugEvaluatedCondition base = new DebugEvaluatedCondition(cas);
    base.setValue(rem.isBaseConditionMatched());

    TextMarkerMatcher matcher = rem.getRuleElement().getMatcher();
    String baseString = verbalizer.verbalize(matcher.getExpression());
    base.setElement(baseString);
    drem.setBaseCondition(base);
    drem.setConditions(createEvaluatedConditions(rem, stream, addToIndex));
    List<AnnotationFS> annotations = rem.getTextsMatched();
    if (!annotations.isEmpty()) {
      drem.setBegin(annotations.get(0).getBegin());
      drem.setEnd(annotations.get(annotations.size() - 1).getEnd());
    }
    if (addToIndex)
      drem.addToIndexes();
    return drem;
  }

  private FSArray createEvaluatedConditions(RuleElementMatch rem, TextMarkerStream stream,
          boolean addToIndex) {
    JCas cas = stream.getJCas();
    List<DebugEvaluatedCondition> ecs = new ArrayList<DebugEvaluatedCondition>();
    if (rem.getConditions() != null) {
      for (EvaluatedCondition each : rem.getConditions()) {
        DebugEvaluatedCondition ec = new DebugEvaluatedCondition(cas);
        ec.setValue(each.isValue());
        ec.setElement(verbalizer.verbalize(each.getCondition()));
        ec.setConditions(createEvaluatedConditions(each, stream, addToIndex));
        ecs.add(ec);
      }
    }
    FSArray result = UIMAUtils.toFSArray(cas, ecs);
    return result;
  }

  private FSArray createEvaluatedConditions(EvaluatedCondition eval, TextMarkerStream stream,
          boolean addToIndex) {
    JCas cas = stream.getJCas();
    List<DebugEvaluatedCondition> ecs = new ArrayList<DebugEvaluatedCondition>();
    for (EvaluatedCondition each : eval.getConditions()) {
      DebugEvaluatedCondition ec = new DebugEvaluatedCondition(cas);
      ec.setValue(each.isValue());
      ec.setElement(verbalizer.verbalize(each.getCondition()));
      ec.setConditions(createEvaluatedConditions(each, stream, addToIndex));
      ecs.add(ec);
    }
    FSArray result = UIMAUtils.toFSArray(cas, ecs);
    return result;
  }

  public DebugScriptApply createDebugScriptApply(ScriptApply apply, TextMarkerStream stream,
          boolean addToIndex, boolean withMatches, Map<TextMarkerElement, Long> timeInfo) {
    DebugScriptApply debug = null;
    if (apply instanceof BlockApply) {
      debug = createDebugBlockApply((BlockApply) apply, stream, addToIndex, withMatches, timeInfo);
    } else if (apply instanceof RuleApply) {
      debug = createDebugRuleApply((RuleApply) apply, stream, addToIndex, withMatches, timeInfo);
    }
    if (addToIndex)
      debug.addToIndexes();
    return debug;
  }

}
