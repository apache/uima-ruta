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

package org.apache.uima.tm.textruler.lp2;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textruler.core.TextRulerAnnotation;
import org.apache.uima.tm.textruler.core.TextRulerExample;
import org.apache.uima.tm.textruler.core.TextRulerRule;
import org.apache.uima.tm.textruler.core.TextRulerRuleItem;
import org.apache.uima.tm.textruler.core.TextRulerRulePattern;
import org.apache.uima.tm.textruler.core.TextRulerTarget;
import org.apache.uima.tm.textruler.core.TextRulerToolkit;
import org.apache.uima.tm.textruler.core.TextRulerTarget.MLTargetType;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerDelegate;
import org.apache.uima.tm.textruler.lp2.LP2RuleItem.MLLP2ContextConstraint;
import org.apache.uima.tm.textruler.lp2.LP2RuleItem.MLLP2OtherConstraint;
import org.apache.uima.util.FileUtils;

public class NaiveLP2 extends BasicLP2 {

  public NaiveLP2(String inputDir, String prePropTMFile, String tmpDir, String[] slotNames,
          Set<String> filterSet, TextRulerLearnerDelegate delegate) {
    super(inputDir, prePropTMFile, tmpDir, slotNames, filterSet, delegate);
  }

  public static final boolean SAVE_DEBUG_INFO_IN_TEMPFOLDER = false;

  @Override
  protected void induceRulesFromExample(TextRulerExample e, int roundNumber) {
    LP2Rule baseRule = createInitialRuleForPositiveExample(e);
    List<LP2Rule> genRules = generalizeRule(baseRule);

    if (shouldAbort())
      return;

    List<LP2Rule> test = new ArrayList<LP2Rule>();

    // int i=1;
    // for (LP2Rule newRule : genRules)
    // {
    // if (shouldAbort())
    // return;
    // sendStatusUpdateToDelegate("Round "+roundNumber+" - Testing proposed generalization "+i+"/"+(genRules.size())+
    // "    - uncovered examples: "+
    // (examples.size()-coveredExamples.size() + " / "+examples.size()),
    // TextRulerLearnerState.ML_RUNNING, false);
    // i++;
    // testRuleOnDocumentSet(newRule, exampleDocuments);
    //
    // checkAndHandleNewRule(newRule);
    //			
    // if (TextRulerToolkit.DEBUG)
    // test.add(newRule);
    // }
    // new cache and testCAS optimized rule testing:

    sendStatusUpdateToDelegate("Round " + roundNumber + " - Testing " + (genRules.size())
            + "generalizations... - uncovered examples: "
            + (examples.size() - coveredExamples.size() + " / " + examples.size()),
            TextRulerLearnerState.ML_RUNNING, false);
    testRulesOnDocumentSet(new ArrayList<TextRulerRule>(genRules), exampleDocuments);

    for (LP2Rule newRule : genRules) {
      checkAndHandleNewRule(newRule);
      if (TextRulerToolkit.DEBUG)
        test.add(newRule);
    }

    if (TextRulerToolkit.DEBUG && SAVE_DEBUG_INFO_IN_TEMPFOLDER) {
      Collections.sort(test, new Comparator<LP2Rule>() {

        public int compare(LP2Rule o1, LP2Rule o2) {
          return o1.getRuleString().compareTo(o2.getRuleString());
        }

      });

      String startend = e.getTarget().type == MLTargetType.SINGLE_LEFT_BOUNDARY ? "left_"
              : "right_";
      File file = new File(tempDirectory() + startend + "generalizations" + roundNumber + ".tm");
      StringBuffer str = new StringBuffer();
      for (TextRulerRule rule : test) {
        str.append(rule.getCoveringStatistics() + "\t\t" + rule.getRuleString() + "\n");
      }
      try {
        FileUtils.saveString2File(str.toString(), file);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }

  }

  protected void checkAndHandleNewRule(LP2Rule rule) {
    boolean tooFewPositives = rule.getCoveringStatistics().getCoveredPositivesCount() < minCoveredPositives;
    boolean tooManyErrors = rule.getErrorRate() > maxErrorThreshold;

    boolean isBestRule = !(tooFewPositives || tooManyErrors);

    if (TextRulerToolkit.DEBUG && SAVE_DEBUG_INFO_IN_TEMPFOLDER)
      TextRulerToolkit.appendStringToFile(tempDirectory() + "bestcandidates.tm", rule
              .getRuleString()
              + "\n");

    if (isBestRule) {
      currentBestRules.add(rule);
      currentBestRules.removeSubsumedRules();
      currentBestRules.cutToMaxSize();
    } else if (!tooFewPositives) {

      // test in context
      // in our TM representation, we simply can add a NEAR condition in
      // the MARKing rule item and retest it on the
      // corpus. we should do that for all kinds of tags we have, but
      // currently we only do it for the corresponding opening/closing
      // tag, since we do not have any information about other slots yet!
      // // TODO use all other slot tags! (see optimized version as well)

      if (true) {
        rule = rule.copy();
        LP2RuleItem item = rule.getMarkingRuleItem();
        // TextRulerToolkit.log("CONTEXTUAL RULE CANDIDATE: "+rule.getRuleString()+"  ;  "+rule.getCoveringStatistics());
        item.setContextConstraint(new MLLP2ContextConstraint(slotMaximumTokenCount, rule));
        rule.setIsContextualRule(true);

        rule.setNeedsCompile(true);

        if (TextRulerToolkit.DEBUG && SAVE_DEBUG_INFO_IN_TEMPFOLDER)
          TextRulerToolkit.appendStringToFile(tempDirectory() + "ctxcandidates.tm", rule
                  .getRuleString());

        testRuleOnDocumentSet(rule, exampleDocuments); // not very
        // fast... but
        // works!
        boolean ctxTooFewPositives = rule.getCoveringStatistics().getCoveredPositivesCount() < minCoveredPositives;
        boolean ctxTooManyErrors = rule.getErrorRate() > maxErrorThreshold;
        boolean isGoodContextRule = !(ctxTooFewPositives || ctxTooManyErrors);
        if (isGoodContextRule) {
          currentContextualRules.add(rule);
          currentContextualRules.removeSubsumedRules();
          currentContextualRules.cutToMaxSize();
        }
      }

    }
  }

  protected List<LP2Rule> generalizeRule(LP2Rule baseRule) {
    List<LP2Rule> result = new ArrayList<LP2Rule>();
    TextRulerRulePattern rulePattern = new TextRulerRulePattern();
    TextRulerRulePattern prePattern = baseRule.getPreFillerPattern();

    for (int i = prePattern.size() - 1; i >= 0; i--) // we have to reverse
    // the order again!
    {
      rulePattern.add(prePattern.get(i));
    }
    rulePattern.addAll(baseRule.getPostFillerPattern());

    recursiveGeneralizeRule(baseRule, rulePattern, new TextRulerRulePattern(), result);
    TextRulerToolkit.log("GENERALIZATIONS: " + result.size());

    for (LP2Rule r : result)
      removeOutermostWildCardItemsFromRule(r);

    // for (LP2Rule r : result)
    // {
    // TextRulerToolkit.log("NEWRULE = "+r.getRuleString());
    // }

    return result;
  }

  protected LP2Rule createInitialRuleForPositiveExample(TextRulerExample example) {
    TextRulerTarget target = example.getTarget();
    LP2Rule rule = new LP2Rule(this, example.getTarget());
    CAS docCas = example.getDocumentCAS();
    TextRulerAnnotation exampleAnnotation = example.getAnnotation();
    TypeSystem ts = docCas.getTypeSystem();
    Type tokensRootType = ts.getType(TextRulerToolkit.TM_ANY_TYPE_NAME);
    int thePosition = target.type == MLTargetType.SINGLE_LEFT_BOUNDARY ? exampleAnnotation
            .getBegin() : exampleAnnotation.getEnd();

    List<AnnotationFS> leftContext = TextRulerToolkit.getAnnotationsBeforePosition(docCas,
            thePosition, windowSize, TextRulerToolkit.getFilterSetWithSlotNames(slotNames,
                    filterSet), tokensRootType);
    List<AnnotationFS> rightContext = TextRulerToolkit.getAnnotationsAfterPosition(docCas,
            thePosition, windowSize, TextRulerToolkit.getFilterSetWithSlotNames(slotNames,
                    filterSet), tokensRootType);

    // the left context has to be reversed since we get the arrayList from
    // the slot's point of view!
    for (int i = leftContext.size() - 1; i >= 0; i--) {
      TextRulerAnnotation annot = new TextRulerAnnotation(leftContext.get(i), example.getDocument());
      LP2RuleItem item = new LP2RuleItem();
      item.setWordConstraint(annot);
      if (item.getWordConstraint().isRegExpConstraint())
        item.addOtherConstraint(new MLLP2OtherConstraint(annot, annot));
      rule.addPreFillerItem(item);
    }

    for (AnnotationFS afs : rightContext) {
      TextRulerAnnotation annot = new TextRulerAnnotation(afs, example.getDocument());
      LP2RuleItem item = new LP2RuleItem();
      item.setWordConstraint(annot);
      if (item.getWordConstraint().isRegExpConstraint())
        item.addOtherConstraint(new MLLP2OtherConstraint(annot, annot));

      rule.addPostFillerItem(item);
    }
    TextRulerToolkit.log("INITIAL RULE: " + rule.getRuleString());
    return rule;
  }

  protected void recursiveGeneralizeRule(LP2Rule baseRule, TextRulerRulePattern allItems,
          TextRulerRulePattern currentPattern, List<LP2Rule> resultList) {
    if (currentPattern.size() == allItems.size()) {
      // create new Rule
      LP2Rule newRule = new LP2Rule(this, baseRule.getTarget());
      int preCount = baseRule.getPreFillerPattern().size();
      for (int i = 0; i < currentPattern.size(); i++) {
        if (i < preCount)
          newRule.addPreFillerItem(currentPattern.get(i));
        else
          newRule.addPostFillerItem(currentPattern.get(i));
      }
      // TextRulerToolkit.log("GEN: "+newRule.getRuleString());
      if (newRule.totalInnerConstraintCount() > 0) // skip the ANY ANY ANY
        // ANY... rule ! this
        // makes no sense in no
        // application!!
        resultList.add(newRule);
    } else {
      int index = currentPattern.size();
      TextRulerRuleItem baseItem = allItems.get(index);
      List<TextRulerRuleItem> itemGeneralizations = generalizeRuleItem((LP2RuleItem) baseItem);
      for (TextRulerRuleItem newItem : itemGeneralizations) {
        currentPattern.add(newItem);
        recursiveGeneralizeRule(baseRule, allItems, currentPattern, resultList);
        currentPattern.remove(currentPattern.size() - 1);
      }
    }
  }

  protected void recursiveGeneralizeRuleItem(LP2RuleItem baseItem,
          List<MLLP2OtherConstraint> otherConstraints, int currentConstraintIndex,
          List<MLLP2OtherConstraint> currentConstraintTuple, List<TextRulerRuleItem> result) {
    if (currentConstraintIndex > otherConstraints.size() - 1) {
      LP2RuleItem newItem;
      newItem = new LP2RuleItem();
      for (MLLP2OtherConstraint c : currentConstraintTuple)
        newItem.addOtherConstraint(c.copy());
      result.add(newItem);
    } else {
      MLLP2OtherConstraint currentConstraint = otherConstraints.get(currentConstraintIndex);
      // recurse WITH and WITHOUT this key:
      recursiveGeneralizeRuleItem(baseItem, otherConstraints, currentConstraintIndex + 1,
              currentConstraintTuple, result);
      currentConstraintTuple.add(currentConstraint);
      recursiveGeneralizeRuleItem(baseItem, otherConstraints, currentConstraintIndex + 1,
              currentConstraintTuple, result);
      currentConstraintTuple.remove(currentConstraintTuple.size() - 1);
    }
  }

  protected List<TextRulerRuleItem> generalizeRuleItem(LP2RuleItem baseItem) {
    List<TextRulerRuleItem> result = new ArrayList<TextRulerRuleItem>();

    // one with word constraint
    if (baseItem.getWordConstraint() != null) {
      LP2RuleItem newItem = new LP2RuleItem();
      newItem.setWordConstraint(baseItem.getWordConstraint().copy());
      result.add(newItem);
    }

    // all other combinations without word constraint
    // List<String> keys = new
    // ArrayList<String>(baseItem.getOtherConstraints().keySet());
    List<MLLP2OtherConstraint> constraints = baseItem.getOtherConstraints();
    recursiveGeneralizeRuleItem(baseItem, constraints, 0, new ArrayList<MLLP2OtherConstraint>(),
            result);
    return result;
  }

  protected void removeOutermostWildCardItemsFromRule(LP2Rule rule) {
    while (true) {
      LP2RuleItem item = (LP2RuleItem) rule.getOutermostPreFillerItem();
      if (item == null) // no more items left
        break;

      // if this rule is a RIGHT BOUNDARY rule, we must not remove the
      // last remaining pre filler item,
      // since this is used for marking the SLOT END BOUNDARY (= RIGHT
      // BOUNDARY)
      if ((rule.getTarget().type == MLTargetType.SINGLE_RIGHT_BOUNDARY)
              && (rule.getPreFillerPattern().size() == 1))
        break;

      if (item.totalConstraintCount() == 0)
        rule.removeOutermostPreFillerItem();
      else
        break;
    }
    while (true) {
      LP2RuleItem item = (LP2RuleItem) rule.getOutermostPostFillerItem();
      if (item == null) // no more items left
        break;

      // if this rule is a LEFT BOUNDARY rule, we must not remove the last
      // remaining post filler item,
      // since this is used for marking the SLOT START BOUNDARY (= LEFT
      // BOUNDARY)
      if ((rule.getTarget().type == MLTargetType.SINGLE_LEFT_BOUNDARY)
              && (rule.getPostFillerPattern().size() == 1))
        break;

      if (item.totalConstraintCount() == 0)
        rule.removeOutermostPostFillerItem();
      else
        break;
    }
  }

  @Override
  public boolean collectNegativeCoveredInstancesWhenTesting() {
    return false;
  }

}
