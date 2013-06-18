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

package org.apache.uima.ruta.textruler.learner.kep;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.ConstraintFactory;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FSMatchConstraint;
import org.apache.uima.cas.FSTypeConstraint;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.textruler.core.GlobalCASSource;
import org.apache.uima.ruta.textruler.core.TextRulerBasicLearner;
import org.apache.uima.ruta.textruler.core.TextRulerExample;
import org.apache.uima.ruta.textruler.core.TextRulerExampleDocument;
import org.apache.uima.ruta.textruler.core.TextRulerRuleItem;
import org.apache.uima.ruta.textruler.core.TextRulerRulePattern;
import org.apache.uima.ruta.textruler.core.TextRulerStatisticsCollector;
import org.apache.uima.ruta.textruler.core.TextRulerTarget;
import org.apache.uima.ruta.textruler.core.TextRulerToolkit;
import org.apache.uima.ruta.textruler.extension.TextRulerLearnerDelegate;
import org.apache.uima.ruta.textruler.learner.kep.KEPRuleItemCondition.Condition;

public class KEPLearner extends TextRulerBasicLearner {

  public static final String MAX_EXPAND_RULES = "maxExpandRules";

  public static final String MAX_INFILLER_RULES = "maxInfillerRules";

  public static final String FILLER_WINDOW = "fillerWindow";

  public static final String MAX_FILLER_LENGTH = "maxFillerLength";
  
  public static final int DEFAULT_MAX_EXPAND_RULES = 50;

  public static final int DEFAULT_MAX_INFILLER_RULES = 10;

  public static final int DEFAULT_FILLER_WINDOW = 5;

  public static final int DEFAULT_MAX_FILLER_LENGTH = 3;

  private int fillerWindow;
  
  private int maxFillerLength;
  
  private int maxInfillerRules;
  
  private int maxExpandRules;
  
  private Map<String, List<KEPRule>> ruleLists = new HashMap<String, List<KEPRule>>();

  private Map<String, List<KEPRule>> correctionRules = new HashMap<String, List<KEPRule>>();

  private Map<String, List<TextRulerExample>> coveredExamples = new HashMap<String, List<TextRulerExample>>();

  private Map<String, Type> blocks = new HashMap<String, Type>();

  private String[] slotNamesWithBoundaries;

  private Map<String, Boolean> hasPerfectRules = new HashMap<String, Boolean>();

  public KEPLearner(String inputDir, String prePropTMFile, String tmpDir, String[] slotNames,
          Set<String> filterSet, boolean skip, TextRulerLearnerDelegate delegate) {
    super(inputDir, prePropTMFile, tmpDir, slotNames, filterSet, skip, delegate);
    supportBoundaries = true;
  }

  @Override
  protected void doRun() {

    long startTime = System.nanoTime();

    this.exampleDocuments.clearCurrentExamples();
    prepareCachedCASesWithBoundaries();
    this.slotNamesWithBoundaries = new String[slotNames.length * 3];
    for (int i = 0; i < this.slotNames.length; i++) {
      this.slotNamesWithBoundaries[i * 3] = slotNames[i];
      this.slotNamesWithBoundaries[i * 3 + 1] = slotNames[i]
              + TextRulerToolkit.LEFT_BOUNDARY_EXTENSION;
      this.slotNamesWithBoundaries[i * 3 + 2] = slotNames[i]
              + TextRulerToolkit.RIGHT_BOUNDARY_EXTENSION;

    }
    for (int i = 0; i < this.slotNamesWithBoundaries.length; i++) {
      if (!filterSetWithSlotNames.contains(slotNamesWithBoundaries[i]))
        this.filterSetWithSlotNames.add(slotNamesWithBoundaries[i]);
      initializeMapEntries(this.slotNamesWithBoundaries[i]);
    }
    for (int i = 0; i < this.slotNamesWithBoundaries.length; i++) {
      runForSlot(this.slotNamesWithBoundaries[i]);
      if (slotNamesWithBoundaries[i].contains(TextRulerToolkit.RIGHT_BOUNDARY_EXTENSION)) {
        if (hasPerfectRules.get(slotNamesWithBoundaries[i - 2]))
          filterSetWithSlotNames.remove(slotNamesWithBoundaries[i - 2]);
        if (hasPerfectRules.get(slotNamesWithBoundaries[i - 1]))
          filterSetWithSlotNames.remove(slotNamesWithBoundaries[i - 1]);
        if (hasPerfectRules.get(slotNamesWithBoundaries[i]))
          filterSetWithSlotNames.remove(slotNamesWithBoundaries[i]);
      }
      if (shouldAbort())
        return;
    }
    removeBadRules();
    for (int i = 0; i < this.slotNamesWithBoundaries.length; i++) {
      List<KEPRule> list = this.ruleLists.get(slotNamesWithBoundaries[i]);
      if (!shouldAbort() && list != null && !list.isEmpty()) {
        this.exampleDocuments.createExamplesForTarget(list.get(0).getTarget());
        if (!hasPerfectRules.get(slotNamesWithBoundaries[i]))
          makeRemovalRules(list.get(0).getTarget());
        list = getOptimalRuleCombination(list);
      }
    }
    removeBadRules();

    long estimatedTime = (System.nanoTime() - startTime) / 1000000000;
    System.out.println(estimatedTime + " seconds needed to learn all rules");
    sendStatusUpdateToDelegate("Done", TextRulerLearnerState.ML_DONE, true);

  }

  /**
   * Execute algorithms for slot denoted by slotName
   * 
   * @param slotName
   *          the name of a slot
   */
  private void runForSlot(String slotName) {

    sendStatusUpdateToDelegate("Working on " + slotName, TextRulerLearnerState.ML_RUNNING, true);
    TextRulerTarget target = new TextRulerTarget(slotName,
            TextRulerTarget.MLTargetType.SINGLE_WHOLE_SLOT, this);
    this.exampleDocuments.createExamplesForTarget(target);
    if (!shouldAbort())
      blocks.put(slotName, getBlocks());
    if (!shouldAbort())
      learnRules(target);
    this.ruleLists.put(slotName, getOptimalRuleCombination(this.ruleLists.get(slotName)));
    sendStatusUpdateToDelegate(slotName + " done", TextRulerLearnerState.ML_RUNNING, true);
  }

  private Type getBlocks() {
    sendStatusUpdateToDelegate("Searching for Blocks", TextRulerLearnerState.ML_RUNNING, false);
    Map<String, List<TextRulerExample>> exampleMap = new HashMap<String, List<TextRulerExample>>();
    Map<String, Double> lengthMap = new HashMap<String, Double>();
    Map<String, Integer> countMap = new HashMap<String, Integer>();
    for (TextRulerExampleDocument exampleDocument : exampleDocuments.getDocuments()) {
      for (AnnotationFS annotation : exampleDocument.getCAS().getAnnotationIndex()) {
        for (TextRulerExample example : exampleDocument.getPositiveExamples()) {
          if (annotation.getBegin() <= example.getAnnotation().getBegin()
                  && annotation.getEnd() >= example.getAnnotation().getEnd()
                  && annotation.getEnd() - annotation.getBegin() > example.getAnnotation().getEnd()
                          - example.getAnnotation().getBegin()
                  && !filterSetWithSlotNames.contains(annotation.getType().getName())) {
            List<TextRulerExample> list = exampleMap.get(annotation.getType().getName());
            if (list == null)
              list = new ArrayList<TextRulerExample>();
            if (!list.contains(example))
              list.add(example);
            exampleMap.put(annotation.getType().getName(), list);
          }
        }
        double aLength = (double) (annotation.getEnd() - annotation.getBegin());
        lengthMap.put(annotation.getType().getName(),
                lengthMap.get(annotation.getType().getName()) == null ? aLength : lengthMap
                        .get(annotation.getType().getName())
                        + aLength);
        countMap.put(annotation.getType().getName(),
                countMap.get(annotation.getType().getName()) == null ? 1 : countMap.get(annotation
                        .getType().getName()) + 1);
      }
    }

    List<Type> result = new ArrayList<Type>();
    for (String typeString : exampleMap.keySet()) {
      if (exampleMap.get(typeString).size() == exampleDocuments.getAllPositiveExamples().size())
        result.add(exampleDocuments.getDocuments().get(0).getCAS().getTypeSystem().getType(
                typeString));
    }
    double exLength = 0;
    for (TextRulerExample ex : exampleDocuments.getAllPositiveExamples()) {
      exLength += (double) (ex.getAnnotation().getEnd() - ex.getAnnotation().getBegin());
    }
    double bestRatio = 0;
    Type bestType = null;
    for (Type type : result) {
      if ((exLength / lengthMap.get(type.getName()) > bestRatio && countMap.get(type.getName()) <= exampleDocuments
              .getAllPositiveExamples().size())
              || (exLength / lengthMap.get(type.getName()) == bestRatio
                      && countMap.get(type.getName()) > countMap.get(bestType.getName()) && countMap
                      .get(type.getName()) <= exampleDocuments.getAllPositiveExamples().size())) {
        bestType = type;
        bestRatio = exLength / lengthMap.get(type.getName());
      }

    }
    sendStatusUpdateToDelegate("Searching for Blocks done", TextRulerLearnerState.ML_RUNNING, true);
    return bestType;
  }

  private void learnRules(TextRulerTarget target) {

    List<KEPRule> ruleList = this.ruleLists.get(target.getSingleSlotTypeName());
    List<TextRulerExample> coveredExamples = this.coveredExamples.get(target
            .getSingleSlotTypeName());
    List<TextRulerExample> positiveExamples = this.exampleDocuments.getAllPositiveExamples();

    for (TextRulerExample e : positiveExamples) {
      if (!coveredExamples.contains(e)) {
        ruleList.addAll(makeInFillerRulesForExample(e));
        // ruleList.addAll(generalizeForRepitition(ruleList));
      }
      for (KEPRule rule : ruleList)
        for (TextRulerExample ex : rule.getCoveringStatistics().getCoveredPositiveExamples())
          if (!coveredExamples.contains(ex))
            coveredExamples.add(ex);

      if (shouldAbort())
        return;
    }
    ruleList.addAll(getCandidateClassificationRules(target));
    ruleList = getBestAndOptimalRules(ruleList);
    ruleList.addAll(makePostFillers(ruleList, true));
    ruleList = getOptimalRuleCombination(ruleList);
    this.ruleLists.put(target.getSingleSlotTypeName(), ruleList);
  }

  private List<KEPRule> makeInFillerRulesForExample(TextRulerExample e) {
    sendStatusUpdateToDelegate("Searching for Infiller Rules for "
            + e.getTarget().getSingleSlotTypeName().substring(
                    e.getTarget().getSingleSlotTypeName().lastIndexOf(".") + 1),
            TextRulerLearnerState.ML_RUNNING, false);
    Collection<KEPRule> rules = new HashSet<KEPRule>();

    rules = new HashSet<KEPRule>();
    rules = expandInFillerRules(e, rules, true);
    if (rules.size() > maxInfillerRules) {
      List<KEPRule> list = new ArrayList<KEPRule>(rules);
      Collections.sort(list, new KEPRuleComparator(e.getDocumentCAS()));
      // TODO this is a parameter!
      rules = new HashSet<KEPRule>(list.subList(0, maxInfillerRules));
    }
    List<KEPRule> result = new ArrayList<KEPRule>(rules);
    if (shouldAbort())
      return result;

    testRulesOnDocumentSet(result, exampleDocuments);
    sendStatusUpdateToDelegate("Searching for Infiller Rules for "
            + e.getTarget().getSingleSlotTypeName().substring(
                    e.getTarget().getSingleSlotTypeName().lastIndexOf(".") + 1) + " done",
            TextRulerLearnerState.ML_RUNNING, true);
    return new ArrayList<KEPRule>(rules);
  }

  private Collection<KEPRule> expandInFillerRules(TextRulerExample e, Collection<KEPRule> rules,
          boolean expanding) {

    if (rules.size() > maxExpandRules) {
      List<KEPRule> list = new ArrayList<KEPRule>(rules);
      Collections.sort(list, new KEPRuleComparator(e.getDocumentCAS()));
      rules = new HashSet<KEPRule>(list.subList(0, maxExpandRules));
    }
    if (!expanding) {
      return rules;
    }

    Collection<KEPRule> expandedRules = new HashSet<KEPRule>();
    if (rules.isEmpty()) {
      List<AnnotationFS> seeds = getAnnotationsStartingAt(e.getDocumentCAS(), e.getAnnotation()
              .getBegin(), e.getAnnotation().getEnd());
      for (AnnotationFS each : seeds) {
        KEPRuleItem item = new KEPRuleItem(each);
        KEPRule rule = new KEPRule(this, e.getTarget());
        rule.addInFillerItem(item);
        expandedRules.add(rule);
      }
    } else {
      expanding = false;
      for (KEPRule eachRule : rules) {
        TextRulerRulePattern inFiller = eachRule.getInFiller();
        KEPRuleItem lastItem = (KEPRuleItem) inFiller.get(inFiller.size() - 1);
        int end = lastItem.getEnd();
        if (end >= e.getAnnotation().getEnd()) {
          if (!expandedRules.contains(eachRule)) {
            expandedRules.add(eachRule);
          }
        } else {
          List<AnnotationFS> annotationsStartingAt = getAnnotationsStartingAt(e.getDocumentCAS(),
                  end, e.getAnnotation().getEnd());
          if (annotationsStartingAt.isEmpty() && !expandedRules.contains(eachRule)) {
            expandedRules.add(eachRule);
          } else {
            expanding = true;
            for (AnnotationFS eachFS : annotationsStartingAt) {
              if (eachFS.getType().getName().equals(lastItem.getType().getName())) {
                lastItem.setAnnotation(eachFS);
                lastItem.setMax(lastItem.getMax() + 1).setReluctant(true);
                expandedRules.add(eachRule);
              } else {
                KEPRule newRule = new KEPRule(eachRule);
                KEPRuleItem newItem = new KEPRuleItem(eachFS);
                newRule.addInFillerItem(newItem);
                expandedRules.add(newRule);
              }
            }
          }
        }
      }
    }
    return expandInFillerRules(e, expandedRules, expanding);
  }

  private List<KEPRule> getCandidateClassificationRules(TextRulerTarget target) {
    sendStatusUpdateToDelegate("Searching for Candidate Classification Rules for "
            + target.getSingleSlotTypeName().substring(
                    target.getSingleSlotTypeName().lastIndexOf(".") + 1),
            TextRulerLearnerState.ML_RUNNING, false);
    List<KEPRule> result = new ArrayList<KEPRule>();
    List<Type> types = getTokensInNExamples(exampleDocuments.getAllPositiveExamples(),
            exampleDocuments.getAllPositiveExamples().size() / 2, true);

    for (Type type : types) {
      result.add(new KEPRule(this, target).addInFillerItem(new KEPRuleItem(type)));
    }
    testRulesOnDocumentSet(result, exampleDocuments);
    // result = getBestAndOptimalRules(result);
    result = addConditions(result, target);
    sendStatusUpdateToDelegate("Searching for Candidate Classification Rules for "
            + target.getSingleSlotTypeName().substring(
                    target.getSingleSlotTypeName().lastIndexOf(".") + 1) + " done",
            TextRulerLearnerState.ML_RUNNING, true);
    return result;
  }

  private List<KEPRule> addConditions(List<KEPRule> rules, TextRulerTarget target) {
    List<KEPRule> result = new ArrayList<KEPRule>();
    List<TextRulerExample> allCoveredExamples = getCoveredExamples(rules);

    List<Type> containedTypes = getTokensInNExamples(exampleDocuments.getAllPositiveExamples(),
            exampleDocuments.getAllPositiveExamples().size() / 3, false);
    if (!containedTypes.isEmpty()) {
      for (KEPRule rule : rules) {
        KEPRuleItem ruleItem = (KEPRuleItem) rule.getInFiller().get(0);
        for (Type type : containedTypes) {
          if (!type.getName().equals(ruleItem.getType().getName())
                  && !ruleItem.containsAndCondition(type)) {
            result.add(new KEPRule(this, target).addInFillerItem(ruleItem.copy().addAndCondition(
                    new KEPRuleItemCondition(type, Condition.CONTAINS, false))));
          }
        }
      }
    }

    testRulesOnDocumentSet(result, exampleDocuments);
    result = getBestAndOptimalRules(result);
    List<KEPRule> toRefine = new ArrayList<KEPRule>();
    List<KEPRule> toRemove = new ArrayList<KEPRule>();
    for (KEPRule rule : result) {
      if (rule.getCoveringStatistics().getCoveredPositivesCount() == 0)
        toRemove.add(rule);
      else if (rule.getCoveringStatistics().getCoveredNegativesCount() > 0
              && rule.getPostFiller().size() < 5)
        toRefine.add(rule);
    }
    result.removeAll(toRemove);
    result.removeAll(toRefine);
    result = getBestAndOptimalRules(result);
    if (getCoveredExamples(result).size() == allCoveredExamples.size()) {
      return result;
    }
    if (toRefine.size() > 0) {
      result.addAll(addConditions(toRefine, target));
    }
    sendStatusUpdateToDelegate("Adding conditions to rules for "
            + target.getSingleSlotTypeName().substring(
                    target.getSingleSlotTypeName().lastIndexOf(".") + 1) + " done",
            TextRulerLearnerState.ML_RUNNING, true);
    return result;
  }

  private List<KEPRule> makePostFillers(List<KEPRule> baseRules, boolean changed) {
    if (!baseRules.isEmpty() && !shouldAbort()) {
      sendStatusUpdateToDelegate("Adding postfillers to rules for "
              + baseRules.get(0).getTarget().getSingleSlotTypeName().substring(
                      baseRules.get(0).getTarget().getSingleSlotTypeName().lastIndexOf(".") + 1),
              TextRulerLearnerState.ML_RUNNING, true);
    } else {
      return new ArrayList<KEPRule>();
    }
//    List<TextRulerExample> allCoveredExamples = getCoveredExamples(baseRules);
    Set<KEPRule> result = new HashSet<KEPRule>();
    for (KEPRule rule : baseRules) {
      for (TextRulerExample e : rule.getCoveringStatistics().getCoveredPositiveExamples()) {
        if (rule.getCoveringStatistics().getCoveredNegativesCount() > 0) {
          KEPRuleItem lastItem = ((KEPRuleItem) rule.getPostFiller().lastItem());
          int end = e.getAnnotation().getEnd();
          if (lastItem != null) {
            end = lastItem.getEnd();
          }
          List<AnnotationFS> annotations = getAnnotationsStartingAt(e.getDocumentCAS(), end, e
                  .getDocumentCAS().getDocumentText().length());
          boolean blockBoundaryHit = false;
          for (AnnotationFS annotationFS : annotations) {
            if (annotationFS.getType().equals(
                    blocks.get(baseRules.get(0).getTarget().getSingleSlotTypeName()))) {
              blockBoundaryHit = true;
              break;
            }
          }
          if (blockBoundaryHit) {
            continue;
          }
          for (AnnotationFS annotationFS : annotations) {
            if (annotationFS.getType().getName().equals(
                    lastItem != null ? lastItem.getType().getName() : null)) {
              lastItem.setReluctant(true).setMax(lastItem.getMax() + 1).setAnnotation(annotationFS);
            } else {
              result.add(rule.copy().addPostFillerItem(new KEPRuleItem(annotationFS)));
            }
            if (rule.getPreFiller().isEmpty())
              result.add(rule);
          }
        } else {
          result.add(rule);
        }
      }
    }
    List<KEPRule> resultList = new ArrayList<KEPRule>(result);
    testRulesOnDocumentSet(resultList, exampleDocuments);
    resultList.addAll(baseRules);
    resultList = getBestAndOptimalRules(resultList);
    if (baseRules.containsAll(resultList)) {
      if (!changed)
        return resultList;
      else
        changed = false;
    } else {
      changed = true;
    }
    resultList.addAll(makePreFillers(resultList, changed));
    sendStatusUpdateToDelegate("Adding postfillers to rules for "
            + baseRules.get(0).getTarget().getSingleSlotTypeName().substring(
                    baseRules.get(0).getTarget().getSingleSlotTypeName().lastIndexOf(".") + 1)
            + " done", TextRulerLearnerState.ML_RUNNING, true);
    return resultList;
  }

  private List<KEPRule> makePreFillers(List<KEPRule> baseRules, boolean changed) {
    if (!baseRules.isEmpty() && !shouldAbort()) {
      sendStatusUpdateToDelegate("Adding prefillers to rules for "
              + baseRules.get(0).getTarget().getSingleSlotTypeName().substring(
                      baseRules.get(0).getTarget().getSingleSlotTypeName().lastIndexOf(".") + 1),
              TextRulerLearnerState.ML_RUNNING, true);
    } else {
      return new ArrayList<KEPRule>();
    }
//    List<TextRulerExample> allCoveredExamples = getCoveredExamples(baseRules);
    Set<KEPRule> result = new HashSet<KEPRule>();
    for (KEPRule rule : baseRules) {
      for (TextRulerExample e : rule.getCoveringStatistics().getCoveredPositiveExamples()) {
        if (rule.getCoveringStatistics().getCoveredNegativesCount() > 0) {
          int begin = e.getAnnotation().getBegin();
          KEPRuleItem firstItem = (KEPRuleItem) rule.getPreFiller().firstItem();
          if (firstItem != null) {
            begin = firstItem.getBegin();
          }
          List<AnnotationFS> annotations = getAnnotationsEndingAt(begin, e.getDocumentCAS());
          boolean blockBoundaryHit = false;
          for (AnnotationFS annotationFS : annotations) {
            if (annotationFS.getType().equals(
                    blocks.get(baseRules.get(0).getTarget().getSingleSlotTypeName()))) {
              blockBoundaryHit = true;
              break;
            }
          }
          if (blockBoundaryHit) {
            continue;
          }
          for (AnnotationFS annotationFS : annotations) {
            if (annotationFS.getType().getName().equals(
                    firstItem != null ? firstItem.getType().getName() : null)) {
              firstItem.setReluctant(true).setMax(firstItem.getMax() + 1).setAnnotation(
                      annotationFS);
            } else {
              result.add(rule.copy().addPreFillerItem(new KEPRuleItem(annotationFS)));
            }
          }
        } else {
          result.add(rule);
        }
      }
    }
    List<KEPRule> resultList = new ArrayList<KEPRule>(result);
    testRulesOnDocumentSet(resultList, exampleDocuments);
    resultList.addAll(baseRules);
    resultList = getBestAndOptimalRules(resultList);
    if (baseRules.containsAll(resultList)) {
      if (!changed)
        return resultList;
      else
        changed = false;
    } else {
      changed = true;
    }
    resultList.addAll(makePostFillers(resultList, changed));

    sendStatusUpdateToDelegate("Adding prefillers to rules for "
            + baseRules.get(0).getTarget().getSingleSlotTypeName().substring(
                    baseRules.get(0).getTarget().getSingleSlotTypeName().lastIndexOf(".") + 1)
            + " done", TextRulerLearnerState.ML_RUNNING, true);
    return resultList;
  }

  private void removeBadRules() {
    for (int i = 0; i < slotNames.length; i++) {
      if (!hasPerfectRules.get(slotNames[i])
              && hasPerfectRules.get(slotNamesWithBoundaries[3 * i + 1])
              && hasPerfectRules.get(slotNamesWithBoundaries[3 * i + 2])) {
        List<KEPRule> list = new ArrayList<KEPRule>();
        for (KEPRule kepRule : ruleLists.get(slotNames[i])) {
          List<TextRulerExample> exList = new ArrayList<TextRulerExample>(kepRule
                  .getCoveringStatistics().getCoveredNegativeExamples());
          exList.removeAll(getCorrectedExamples(slotNames[i]));
          if (exList.size() == 0) {
            list.add(kepRule);
          }
        }
        ruleLists.put(slotNames[i], list);
      } else {
        if (!hasPerfectRules.get(slotNamesWithBoundaries[3 * i + 1])) {
          List<KEPRule> list = new ArrayList<KEPRule>();
          for (KEPRule kepRule : ruleLists.get(slotNamesWithBoundaries[3 * i + 1])) {
            List<TextRulerExample> exList = new ArrayList<TextRulerExample>(kepRule
                    .getCoveringStatistics().getCoveredNegativeExamples());
            exList.removeAll(getCorrectedExamples(slotNamesWithBoundaries[3 * i + 1]));
            if (exList.size() == 0) {
              list.add(kepRule);
            }
          }
        }
        if (!hasPerfectRules.get(slotNamesWithBoundaries[3 * i + 2])) {
          List<KEPRule> list = new ArrayList<KEPRule>();
          for (KEPRule kepRule : ruleLists.get(slotNamesWithBoundaries[3 * i + 2])) {
            List<TextRulerExample> exList = new ArrayList<TextRulerExample>(kepRule
                    .getCoveringStatistics().getCoveredNegativeExamples());
            exList.removeAll(getCorrectedExamples(slotNamesWithBoundaries[3 * i + 2]));
            if (exList.size() == 0) {
              list.add(kepRule);
            }
          }
        }
      }
    }
  }

  private List<KEPRule> makeRemovalRules(TextRulerTarget target) {
    sendStatusUpdateToDelegate("Searching for Removal Rules for "
            + target.getSingleSlotTypeName().substring(
                    target.getSingleSlotTypeName().lastIndexOf(".") + 1),
            TextRulerLearnerState.ML_RUNNING, false);
    if (!hasFalsePositives(target.getSingleSlotTypeName()))
      return new ArrayList<KEPRule>();
    List<KEPRule> result = correctionRules.get(target.getSingleSlotTypeName());
    Type targetType = exampleDocuments.getDocuments().get(0).getCAS().getTypeSystem().getType(
            target.getSingleSlotTypeName());

    List<Type> containedTypes = getTokensInNExamples(exampleDocuments.getAllPositiveExamples(),
            exampleDocuments.getAllPositiveExamples().size(), false);
    List<Type> notContainedTypes = getTokensInNoExample(exampleDocuments.getAllPositiveExamples());
    // notContainedTypes.retainAll(getTokensInNExamples(getFalsePositives(target), 1, false));
    if (!containedTypes.isEmpty()) {
      KEPRuleItem containsRuleItem = new KEPRuleItem(targetType);
      for (Type type : containedTypes) {
        result.add(new KEPRule(this, target).addInFillerItem(
                containsRuleItem.copy().addAndCondition(
                        new KEPRuleItemCondition(type, Condition.CONTAINS, true)))
                .setCorrectionRule(true));
      }
    }
    if (!notContainedTypes.isEmpty()) {
      KEPRuleItem notContainsRuleItem = new KEPRuleItem(targetType);
      for (Type type : notContainedTypes) {
        result.add(new KEPRule(this, target).addInFillerItem(
                notContainsRuleItem.copy().addAndCondition(
                        new KEPRuleItemCondition(type, Condition.CONTAINS, false)))
                .setCorrectionRule(true));
      }
    }
    testCorrectionRules(target);
    List<KEPRule> toRemove = new ArrayList<KEPRule>();
    List<KEPRuleItemCondition> toMerge = new ArrayList<KEPRuleItemCondition>();
    for (KEPRule rule : result) {
      if (!(rule.getCoveringStatistics().getCoveredPositivesCount() == 0)
              || !(rule.getCoveringStatistics().getCoveredNegativesCount() > 0)) {
        toRemove.add(rule);
      } else {
        toMerge.addAll(((KEPRuleItem) rule.getInFiller().get(0)).getConditions().get(0));
        toRemove.add(rule);
      }
    }
    result.removeAll(toRemove);
    if (!toMerge.isEmpty()) {
      result.add(new KEPRule(this, target).addInFillerItem(
              new KEPRuleItem(targetType).addConditions(toMerge)).setCorrectionRule(true));
      testCorrectionRules(target);
    }
    sendStatusUpdateToDelegate("Searching for Removal Rules for "
            + target.getSingleSlotTypeName().substring(
                    target.getSingleSlotTypeName().lastIndexOf(".") + 1) + " done",
            TextRulerLearnerState.ML_RUNNING, true);
    return result;
  }

  private void initializeMapEntries(String slotName) {
    this.ruleLists.put(slotName, new ArrayList<KEPRule>());
    this.correctionRules.put(slotName, new ArrayList<KEPRule>());
    this.coveredExamples.put(slotName, new ArrayList<TextRulerExample>());
    this.hasPerfectRules.put(slotName, false);
  }

  private List<AnnotationFS> getAnnotationsEndingAt(int end, CAS cas) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    FSIterator<AnnotationFS> it = cas.getAnnotationIndex(
            cas.getTypeSystem().getType(TextRulerToolkit.RUTA_ALL_TYPE_NAME)).iterator();
    while (it.isValid() && it.get().getBegin() < end) {
      it.moveToNext();
    }
    do
      it.moveToPrevious();
    while (it.isValid()
            && (it.get().getBegin() >= end || filterSetWithSlotNames.contains(it.get().getType()
                    .getName())));
    if (!it.isValid())
      return result;
    end = it.get().getEnd();
    it = cas.getAnnotationIndex().iterator();
    while (it.isValid() && it.get().getBegin() <= end) {
      if (it.get().getEnd() == end
              && !filterSetWithSlotNames.contains(it.get().getType().getName()))
        result.add(it.get());
      it.moveToNext();
    }
    return result;
  }

  private List<AnnotationFS> getAnnotationsStartingAt(CAS cas, int begin, int till) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    if (begin > cas.getDocumentText().length()) {
      return new ArrayList<AnnotationFS>();
    }
    if (begin == 0) {
      begin++;
    }
    AnnotationFS pointer = cas.createAnnotation(cas.getAnnotationType(), begin - 1, begin);
    FSIterator<AnnotationFS> it = cas.getAnnotationIndex().iterator(pointer);
    FSMatchConstraint constraint = getConstraint(cas);
    FSIterator<AnnotationFS> iterator = cas.createFilteredIterator(it, constraint);
    iterator.moveTo(pointer);

    int firstBegin = -1;
    while (iterator.isValid()) {
      AnnotationFS fs = iterator.get();
      if (firstBegin == -1 && fs.getBegin() >= begin) {
        firstBegin = fs.getBegin();
      }
      if (firstBegin >= 0) {
        if (fs.getBegin() > firstBegin) {
          break;
        } else if (fs.getBegin() == firstBegin && fs.getEnd() <= till) {
          if (!filterSetWithSlotNames.contains(fs.getType().getName())) {
            result.add(fs);
          }
        }
      }
      iterator.moveToNext();
    }
    return result;
  }

  private List<KEPRule> getOptimalRuleCombination(List<KEPRule> rules) {

    if (rules.isEmpty()) {
      return new ArrayList<KEPRule>();
    }
    List<KEPRule> tmpList = new ArrayList<KEPRule>();
    List<TextRulerExample> coveredExamples = new ArrayList<TextRulerExample>();
    List<TextRulerExample> positiveExamples = exampleDocuments.getAllPositiveExamples();
    List<TextRulerExample> correctedExamples = getCorrectedExamples(rules.get(0).getTarget()
            .getSingleSlotTypeName());
    for (KEPRule rule : rules) {
      List<TextRulerExample> uncorrectedExamples = new ArrayList<TextRulerExample>(rule
              .getCoveringStatistics().getCoveredNegativeExamples());
      uncorrectedExamples.removeAll(correctedExamples);
      if (uncorrectedExamples.size() == 0
              && rule.getCoveringStatistics().getCoveredPositivesCount() > 0)
        tmpList.add(rule);
    }
    tmpList = getBestRules(tmpList);
    for (KEPRule rule : tmpList) {
      coveredExamples.addAll(rule.getCoveringStatistics().getCoveredPositiveExamples());
    }
    if (coveredExamples.containsAll(exampleDocuments.getAllPositiveExamples()))
      hasPerfectRules.put(rules.get(0).getTarget().getSingleSlotTypeName(), true);
    else
      hasPerfectRules.put(rules.get(0).getTarget().getSingleSlotTypeName(), false);
    List<KEPRule> bestRules = getBestRules(rules);
    while (!coveredExamples.containsAll(positiveExamples) && !bestRules.isEmpty()) {
      KEPRule bestRule = bestRules.get(0);
      if (!coveredExamples.containsAll(bestRule.getCoveringStatistics()
              .getCoveredPositiveExamples())) {
        coveredExamples.removeAll(bestRule.getCoveringStatistics().getCoveredPositiveExamples());
        coveredExamples.addAll(bestRule.getCoveringStatistics().getCoveredPositiveExamples());
        tmpList.add(bestRule);
      }
      bestRules.remove(0);
    }
    return tmpList;

  }

  private List<KEPRule> getBestRules(List<KEPRule> rules) {
    if (rules.isEmpty())
      return new ArrayList<KEPRule>();
    final class AComparator implements Comparator<KEPRule> {
      public int compare(KEPRule r1, KEPRule r2) {
        if (r1.getCoveringStatistics().getCoveredPositivesCount() < r2.getCoveringStatistics()
                .getCoveredPositivesCount())
          return 1;
        else if (r1.getCoveringStatistics().getCoveredPositivesCount() > r2.getCoveringStatistics()
                .getCoveredPositivesCount())
          return -1;
        else if (r1.getCoveringStatistics().getCoveredNegativesCount() > r2.getCoveringStatistics()
                .getCoveredNegativesCount())
          return 1;
        else if (r1.getCoveringStatistics().getCoveredNegativesCount() < r2.getCoveringStatistics()
                .getCoveredNegativesCount())
          return -1;
        else if (r1.getPreFiller().size() + r1.getInFiller().size() + r1.getPostFiller().size() < r2
                .getPreFiller().size()
                + r2.getInFiller().size() + r2.getPostFiller().size())
          return -1;
        return 0;
      }
    }

    Collections.sort(rules, new AComparator());
    List<KEPRule> result = new ArrayList<KEPRule>();
    List<TextRulerExample> positiveExamples = exampleDocuments.getAllPositiveExamples();
    List<TextRulerExample> coveredExamples = new ArrayList<TextRulerExample>();
    for (int i = 0; i < rules.size(); i++) {
      KEPRule rule = rules.get(i);
      if ((3 * rule.getCoveringStatistics().getCoveredPositivesCount() >= rule
              .getCoveringStatistics().getCoveredNegativesCount())
              && (rule.getCoveringStatistics().getCoveredPositivesCount() >= positiveExamples
                      .size() || !coveredExamples.containsAll(rule.getCoveringStatistics()
                      .getCoveredPositiveExamples()))) {
        result.add(rule);
        coveredExamples.addAll(rule.getCoveringStatistics().getCoveredPositiveExamples());

        if (coveredExamples.containsAll(positiveExamples))
          return result;
      }
    }
    for (int i = 0; i < rules.size(); i++) {
      KEPRule rule = rules.get(i);
      if (rule.getCoveringStatistics().getCoveredPositivesCount() >= positiveExamples.size()
              || !coveredExamples.containsAll(rule.getCoveringStatistics()
                      .getCoveredPositiveExamples())) {
        result.add(rule);
        coveredExamples.addAll(rule.getCoveringStatistics().getCoveredPositiveExamples());

        if (coveredExamples.containsAll(positiveExamples))
          return result;
      }
    }
    return result;
  }

  private List<KEPRule> getBestAndOptimalRules(List<KEPRule> rules) {
    List<KEPRule> result = new ArrayList<KEPRule>();

    result.addAll(getBestRules(rules));
    List<KEPRule> tmp = getOptimalRuleCombination(rules);
    for (KEPRule rule : tmp)
      if (!result.contains(rule))
        result.add(rule);
    return result;
  }

  private List<Type> getTokensInNExamples(List<TextRulerExample> examples, int n,
          boolean countOnlyCoveringTokens) {
    if (examples.isEmpty())
      return new ArrayList<Type>();
    List<Type> result = new ArrayList<Type>();
    Map<String, List<TextRulerExample>> countMap = new HashMap<String, List<TextRulerExample>>();
    for (TextRulerExample example : examples) {
      for (AnnotationFS a : TextRulerToolkit.getAnnotationsWithinBounds(example.getDocumentCAS(),
              example.getAnnotation().getBegin(), example.getAnnotation().getEnd(),
              filterSetWithSlotNames, null)) {
        if (!filterSetWithSlotNames.contains(a.getType().getName()))
          if (((!countOnlyCoveringTokens) && (a.getBegin() >= example.getAnnotation().getBegin() && a
                  .getEnd() <= example.getAnnotation().getEnd()))
                  || (a.getBegin() == example.getAnnotation().getBegin() && a.getEnd() == example
                          .getAnnotation().getEnd())) {
            List<TextRulerExample> list = countMap.get(a.getType().getName());
            if (list == null) {
              list = new ArrayList<TextRulerExample>();
              list.add(example);
            } else if (!list.contains(example))
              list.add(example);
            countMap.put(a.getType().getName(), list);
          }
      }
    }
    for (String typeString : countMap.keySet()) {
      if (countMap.get(typeString).size() >= n)
        result.add(examples.get(0).getDocumentCAS().getTypeSystem().getType(typeString));
    }
    return result;
  }

  private List<Type> getTokensInNoExample(List<TextRulerExample> examples) {
    List<String> types = new ArrayList<String>();
    for (TextRulerExampleDocument doc : exampleDocuments.getDocuments()) {
      for (AnnotationFS a : doc.getCAS().getAnnotationIndex()) {
        if (!types.contains(a.getType().getName())
                && !filterSetWithSlotNames.contains(a.getType().getName()))
          types.add(a.getType().getName());
      }
    }
    List<Type> containedTypes = getTokensInNExamples(examples, 1, false);
    for (Type type : containedTypes) {
      types.remove(type.getName());
    }
    List<Type> result = new ArrayList<Type>();
    for (String typeString : types) {
      result.add(examples.get(0).getDocumentCAS().getTypeSystem().getType(typeString));
    }
    return result;
  }

  public String getResultString() {
    StringBuffer ruleStrings = new StringBuffer();
    if (slotNamesWithBoundaries == null || slotNamesWithBoundaries.length == 0)
      return "No results available yet!";

    for (int i = 0; i < slotNamesWithBoundaries.length; i++) {
      List<KEPRule> ruleList = this.ruleLists.get(slotNamesWithBoundaries[i]);
      Type blockType = blocks.get(slotNamesWithBoundaries[i]);
      if (blockType != null
              && !(i > 0 && blocks.get(slotNamesWithBoundaries[i - 1]) != null && blocks.get(
                      slotNamesWithBoundaries[i - 1]).getName().equals(blockType.getName()))) {
        ruleStrings.append("BLOCK(" + blockType.getShortName() + ") " + blockType.getShortName()
                + "{} { \n");
      }
      if (ruleList == null || ruleList.isEmpty()) {
        if (blockType != null
                && !(i < slotNamesWithBoundaries.length - 1
                        && blocks.get(slotNamesWithBoundaries[i + 1]) != null && blocks.get(
                        slotNamesWithBoundaries[i + 1]).getName().equals(blockType.getName())))
          ruleStrings.append("} \n");
        continue;
      }
      ruleStrings.append("// " + slotNamesWithBoundaries[i] + " RULES \n");
      for (KEPRule rule : new ArrayList<KEPRule>(ruleList)) {
        ruleStrings.append((blockType != null ? "\t" : "") + rule.getRuleString() + "\t// "
                + rule.getCoveringStatistics() + "\n");
      }
      if (blockType != null
              && !(i < slotNamesWithBoundaries.length - 1
                      && blocks.get(slotNamesWithBoundaries[i + 1]) != null && blocks.get(
                      slotNamesWithBoundaries[i + 1]).getName().equals(blockType.getName())))
        ruleStrings.append("}");
      ruleStrings.append("\n");
    }
    StringBuffer boundaryCorrectors = new StringBuffer();
    StringBuffer wholeSlotCorrectors = new StringBuffer();
    boundaryCorrectors.append("\n // BOUNDARY CORRECTION RULES: \n");
    wholeSlotCorrectors.append("\n // CORRECTION RULES: \n");
    for (int i = 0; i < slotNamesWithBoundaries.length; i++) {
      List<KEPRule> ruleList = this.correctionRules.get(slotNamesWithBoundaries[i]);
      if (ruleList == null || ruleList.isEmpty())
        continue;
      for (KEPRule rule : ruleList) {
        if (slotNamesWithBoundaries[i].contains(TextRulerToolkit.LEFT_BOUNDARY_EXTENSION)
                || slotNamesWithBoundaries[i].contains(TextRulerToolkit.RIGHT_BOUNDARY_EXTENSION)) {
          boundaryCorrectors.append(rule.getRuleString() + "\t// " + rule.getCoveringStatistics()
                  + "\n");
        } else {
          wholeSlotCorrectors.append(rule.getRuleString() + "\t// " + rule.getCoveringStatistics()
                  + "\n");
        }
      }
    }
    return getFileHeaderString(true) + ruleStrings + boundaryCorrectors + "\n // CONNECTORS: \n"
            + getConnectorsRuleString() + wholeSlotCorrectors;
  }

  private String getAnnotationRulesString(String slotName) {
    StringBuffer result = new StringBuffer();
    result.append(getPackageString());
    result.append("// " + slotName + " RULES \n");
    Type blockType = blocks.get(slotName);
    if (blockType != null) {
      result.append("BLOCK(" + blockType.getShortName() + ") " + blockType.getShortName()
              + "{} { \n");
    }
    List<KEPRule> ruleList = this.ruleLists.get(slotName);
    if (ruleList != null && !ruleList.isEmpty()) {

      for (KEPRule rule : ruleList) {
        String theRuleString = rule.getRuleString();
        result.append((blockType != null ? "\t" : "") + theRuleString + "\t// "
                + rule.getCoveringStatistics() + "\n");
      }
    }
    if (blockType != null) {
      result.append("}");
    }
    result.append("\n");

    if (!slotName.contains(TextRulerToolkit.LEFT_BOUNDARY_EXTENSION)
            && !slotName.contains(TextRulerToolkit.RIGHT_BOUNDARY_EXTENSION)) {
      result.append(getAnnotationRulesString(slotName + TextRulerToolkit.LEFT_BOUNDARY_EXTENSION));
      result.append(getAnnotationRulesString(slotName + TextRulerToolkit.RIGHT_BOUNDARY_EXTENSION));
      if (blockType != null) {
        result.append("BLOCK(" + blockType.getShortName() + "Correction) "
                + blockType.getShortName() + "{} { \n");
      }
      String shortName = slotName.substring(slotName.lastIndexOf(".") + 1);
      String str = shortName + TextRulerToolkit.LEFT_BOUNDARY_EXTENSION + "{->MARKONCE("
              + shortName + ",1,3)} ANY*? " + shortName + TextRulerToolkit.RIGHT_BOUNDARY_EXTENSION
              + ";" + "\n";
      str += shortName + TextRulerToolkit.LEFT_BOUNDARY_EXTENSION + "{IS(" + shortName
              + TextRulerToolkit.RIGHT_BOUNDARY_EXTENSION + ")->MARKONCE(" + shortName + ")} "
              + ";" + "\n";
      result.append(str);
      if (blockType != null) {
        result.append("}");
      }
    }

    return result.toString();
  }

  private String getConnectorsRuleString() {

    Map<Type, StringBuffer> connectorBlocks = new HashMap<Type, StringBuffer>();
    StringBuffer noBlockConnectorRules = new StringBuffer();
    StringBuffer result = new StringBuffer();

    for (int i = 0; i < this.slotNames.length; i++) {
      Type slotBlock = blocks.get(slotNames[i]);
      String shortName = slotNames[i].substring(slotNames[i].lastIndexOf(".") + 1);
      String str = (slotBlock == null ? "" : "\t") + shortName
              + TextRulerToolkit.LEFT_BOUNDARY_EXTENSION + "{->MARKONCE(" + shortName
              + ",1,3)} ANY*? " + shortName + TextRulerToolkit.RIGHT_BOUNDARY_EXTENSION + ";"
              + "\n";
      str += (slotBlock == null ? "" : "\t") + shortName + TextRulerToolkit.LEFT_BOUNDARY_EXTENSION
              + "{IS(" + shortName + TextRulerToolkit.RIGHT_BOUNDARY_EXTENSION + ")->MARKONCE("
              + shortName + ")} " + ";" + "\n";
      if (slotBlock != null) {
        if (connectorBlocks.get(slotBlock) == null) {
          connectorBlocks.put(slotBlock, new StringBuffer());
        }
        connectorBlocks.get(slotBlock).append(str);
      } else {
        noBlockConnectorRules.append(str);
      }
    }

    for (Type block : connectorBlocks.keySet()) {
      result.append("BLOCK(" + block.getShortName() + "Connectors) " + block.getShortName()
              + "{} { \n" + connectorBlocks.get(block) + "} \n");
    }
    result.append(noBlockConnectorRules);
    return result.toString();
  }

  private List<TextRulerExample> getCoveredExamples(List<KEPRule> rules) {
    List<TextRulerExample> result = new ArrayList<TextRulerExample>();
    for (KEPRule rule : rules)
      for (TextRulerExample ex : rule.getCoveringStatistics().getCoveredPositiveExamples())
        if (!result.contains(ex))
          result.add(ex);
    return result;
  }

  private List<TextRulerExample> getCorrectedExamples(String slotName) {
    List<TextRulerExample> result = new ArrayList<TextRulerExample>();
    for (KEPRule rule : correctionRules.get(slotName))
      for (TextRulerExample ex : rule.getCoveringStatistics().getCoveredNegativeExamples())
        if (!result.contains(ex))
          result.add(ex);
    return result;
  }

  private boolean hasFalsePositives(String singleSlotTypeName) {
    List<KEPRule> list = this.ruleLists.get(singleSlotTypeName);
    if (list == null || list.isEmpty())
      return false;
    for (KEPRule kepRule : list) {
      if (kepRule.getCoveringStatistics().getCoveredNegativesCount() > 0)
        return true;
    }
    return false;
  }

  public void testCorrectionRules(TextRulerTarget target) {
    if (shouldAbort())
      return;
    String rStr = getAnnotationRulesString(target.getSingleSlotTypeName());
    for (TextRulerExampleDocument doc : exampleDocuments.getDocuments()) {
      CAS processedCAS = applyScriptOnDocument(rStr, doc, target);
      TextRulerStatisticsCollector scriptStatistics = new TextRulerStatisticsCollector();
      compareOriginalDocumentWithTestCAS(doc, processedCAS, target, scriptStatistics,
              collectNegativeCoveredInstancesWhenTesting());
      for (KEPRule cRule : correctionRules.get(target.getSingleSlotTypeName())) {
        if (shouldAbort())
          break;
        if (cRule.getCoveringStatistics() == null) {
          cRule.setCoveringStatistics(new TextRulerStatisticsCollector());
        }
        processedCAS = applyScriptOnDocument(rStr, doc, target);
        TextRulerStatisticsCollector correctedStats = new TextRulerStatisticsCollector();
        testRuleOnDocument(cRule, doc, correctedStats, processedCAS);
        for (TextRulerExample ex : scriptStatistics.getCoveredNegativeExamples()) {
          if (!correctedStats.getCoveredNegativeExamples().contains(ex)) {
            cRule.getCoveringStatistics().addCoveredNegative(ex);
          }
        }
        for (TextRulerExample ex : scriptStatistics.getCoveredPositiveExamples()) {
          if (!correctedStats.getCoveredPositiveExamples().contains(ex)) {
            cRule.getCoveringStatistics().addCoveredPositive(ex);
          }
        }
        cRule.getCoveringStatistics().reflectCountsFromCoveredExamples();
      }
      GlobalCASSource.releaseCAS(processedCAS);
    }
  }

  private void prepareCASWithBoundaries(CAS cas) {
    for (String slotName : slotNames)
      if (!(slotName.contains(TextRulerToolkit.LEFT_BOUNDARY_EXTENSION) || slotName
              .contains(TextRulerToolkit.RIGHT_BOUNDARY_EXTENSION)))
        TextRulerExampleDocument.createBoundaryAnnotationsForCas(cas, slotName, filterSet);
  }

  @Override
  public CAS loadCAS(String fileName, CAS reuseCAS) {
    CAS cas = super.loadCAS(fileName, reuseCAS);
    prepareCASWithBoundaries(cas);
    return cas;
  }

  private void prepareCachedCASesWithBoundaries() {
    for (CAS cas : exampleDocuments.getCachedCASes())
      prepareCASWithBoundaries(cas);
  }

  @Override
  public boolean collectNegativeCoveredInstancesWhenTesting() {
    return true;
  }

  public void setParameters(Map<String, Object> params) {
    if (TextRulerToolkit.DEBUG)
      saveParametersToTempFolder(params);

    // TODO try catch
    if (params.containsKey(FILLER_WINDOW))
      fillerWindow = (Integer) params.get(FILLER_WINDOW);

    if (params.containsKey(MAX_EXPAND_RULES))
      maxExpandRules = (Integer) params.get(MAX_EXPAND_RULES);

    if (params.containsKey(MAX_FILLER_LENGTH))
      maxFillerLength = (Integer) params.get(MAX_FILLER_LENGTH);

    if (params.containsKey(MAX_INFILLER_RULES))
      maxInfillerRules = (Integer) params.get(MAX_INFILLER_RULES);

  }

  protected FSMatchConstraint getConstraint(CAS cas) {
    ConstraintFactory cf = cas.getConstraintFactory();
    final FSTypeConstraint constraint = cf.createTypeConstraint();

    for (String each : getFilterSet()) {
      constraint.add(each);
    }
    constraint.add(RutaEngine.BASIC_TYPE);
    // TODO check if this is a legal alternative to "new NotConstraint(constraint)":
    FSMatchConstraint result = new FSMatchConstraint() {
      private static final long serialVersionUID = -6744378612440830298L;

      private final FSTypeConstraint c = constraint;

      public boolean match(FeatureStructure fs) {
        return !c.match(fs);
      }
    };
    return result;
  }

  public class KEPRuleComparator implements Comparator<KEPRule> {

    private CAS cas;

    public KEPRuleComparator(CAS cas) {
      super();
      this.cas = cas;
    }

    public int compare(KEPRule o1, KEPRule o2) {
      ArrayList<TextRulerRuleItem> items1 = o1.getInFiller();
      items1.addAll(o1.getPostFiller());
      items1.addAll(o1.getPreFiller());
      double occ1 = 0;
      double occ2 = 0;
      for (TextRulerRuleItem each : items1) {
        KEPRuleItem eachItem = (KEPRuleItem) each;
        int ratio = exampleDocuments.getAllPositiveExamples().size()
                / cas.getAnnotationIndex(eachItem.getType()).size();
        occ1 += (ratio < 1) ? 1 : ratio;
      }
      ArrayList<TextRulerRuleItem> items2 = o2.getInFiller();
      items2.addAll(o2.getPostFiller());
      items2.addAll(o2.getPreFiller());
      for (TextRulerRuleItem each : items2) {
        KEPRuleItem eachItem = (KEPRuleItem) each;
        int ratio = exampleDocuments.getAllPositiveExamples().size()
                / cas.getAnnotationIndex(eachItem.getType()).size();
        occ2 += (ratio < 1) ? 1 : ratio;
      }
      double v1 = occ1;
      double v2 = occ2;
      if (v1 > v2) {
        return 1;
      } else if (v2 > v1) {
        return -1;
      } else {
        return 0;
      }
    }
  }

}