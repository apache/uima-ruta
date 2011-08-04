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

package org.apache.uima.tm.textruler.whisk.token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textruler.core.TextRulerAnnotation;
import org.apache.uima.tm.textruler.core.TextRulerBasicLearner;
import org.apache.uima.tm.textruler.core.TextRulerExample;
import org.apache.uima.tm.textruler.core.TextRulerExampleDocument;
import org.apache.uima.tm.textruler.core.TextRulerRule;
import org.apache.uima.tm.textruler.core.TextRulerRuleList;
import org.apache.uima.tm.textruler.core.TextRulerRulePattern;
import org.apache.uima.tm.textruler.core.TextRulerSlotPattern;
import org.apache.uima.tm.textruler.core.TextRulerStatisticsCollector;
import org.apache.uima.tm.textruler.core.TextRulerTarget;
import org.apache.uima.tm.textruler.core.TextRulerToolkit;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerDelegate;
import org.apache.uima.tm.textruler.whisk.token.WhiskRuleItem.MLWhiskOtherConstraint;

public class Whisk extends TextRulerBasicLearner {

  public final static String WINDOSIZE_KEY = "windowSize";

  public final static String ERROR_THRESHOLD_KEY = "errorThreshold";

  public final static String POSTAG_ROOTTYPE_KEY = "posTagRootType";

  public final static int STANDARD_WINDOWSIZE = 5;

  public final static float STANDARD_ERROR_THRESHOLD = 0.1f;

  public final static String STANDARD_POSTAG_ROOTTYPE = "org.apache.uima.ml.ML.postag";

  TextRulerRuleList ruleList;

  protected Set<TextRulerExample> coveredExamples;

  protected int windowSize = STANDARD_WINDOWSIZE;

  protected double errorThreshold = STANDARD_ERROR_THRESHOLD;

  protected String posTagRootTypeName = STANDARD_POSTAG_ROOTTYPE;

  int roundNumber = 0;

  int allExamplesCount = 0;

  private Map<String, TextRulerStatisticsCollector> cachedTestedRuleStatistics = new HashMap<String, TextRulerStatisticsCollector>();

  public Whisk(String inputDir, String prePropTmFile, String tmpDir, String[] slotNames,
          Set<String> filterSet, TextRulerLearnerDelegate delegate) {
    super(inputDir, prePropTmFile, tmpDir, slotNames, filterSet, delegate);
  }

  @Override
  public boolean collectNegativeCoveredInstancesWhenTesting() {
    return false;
  }

  @Override
  protected void doRun() {

    // we don't use the same overall structure like the original WHISK since
    // we do not
    // repeat the whole process for some new training documents at the
    // user's request, we
    // learn like the other algorithms from the whole training set, so we
    // for example do not
    // need to test the intermediate rule base on a newly "incoming"
    // training document since we
    // tested all rules already on all training documents !

    // this version of whisk is not tested for mutli slot learning since the
    // seminar announcements
    // are not quite suitable for this task: they do not all contain all 4
    // slots and some of them
    // occur more than once in one document ! And the order of them is not
    // always the same as well!
    // so this is now made only tested for the single slot case even if it
    // is built capable of multislot
    // examples!

    // this is the inner loop of the WHISK pseudo-code:
    // For each inst in Training
    // for each tag

    cachedTestedRuleStatistics.clear();
    ruleList = new TextRulerRuleList();
    coveredExamples = new HashSet<TextRulerExample>();

    sendStatusUpdateToDelegate("Creating examples...", TextRulerLearnerState.ML_RUNNING, false);
    TextRulerTarget target = new TextRulerTarget(slotNames[0], this); // only
    // single-slot-target
    // for now
    exampleDocuments.createExamplesForTarget(target);

    TextRulerExampleDocument[] docs = exampleDocuments.getSortedDocumentsInCacheOptimizedOrder();

    allExamplesCount = exampleDocuments.getAllPositiveExamples().size();

    for (TextRulerExampleDocument inst : docs) {
      List<TextRulerExample> tags = inst.getPositiveExamples();

      // for each uncovered example -> induce a new rule:
      for (TextRulerExample tag : tags) {
        if (!coveredExamples.contains(tag)) {
          roundNumber++;
          WhiskRule newRule = growRule(inst, tag);
          if (shouldAbort())
            break;
          // if (newRule == null)
          // break;
          // else
          if (newRule != null
                  && (newRule.getCoveringStatistics().getCoveredNegativesCount() == 00 || newRule
                          .getLaplacian() <= errorThreshold)) {
            ruleList.addRule(newRule);
            coveredExamples.addAll(newRule.getCoveringStatistics().getCoveredPositiveExamples());
            sendStatusUpdateToDelegate("New Rule added...", TextRulerLearnerState.ML_RUNNING, true);
          }
        }
      }
      if (shouldAbort())
        return;
    }
    sendStatusUpdateToDelegate("Done", TextRulerLearnerState.ML_DONE, true);
    cachedTestedRuleStatistics.clear();
  }

  protected WhiskRule growRule(TextRulerExampleDocument doc, TextRulerExample example) {
    sendStatusUpdateToDelegate("Creating new rule from seed...", TextRulerLearnerState.ML_RUNNING,
            false);
    WhiskRule theRule = new WhiskRule(this, example.getTarget(), example);
    int numberOfSlotsInTag = example.getAnnotations().length;
    for (int i = 0; i < numberOfSlotsInTag; i++)
      theRule.getPatterns().add(new TextRulerSlotPattern());

    List<WhiskRuleItem> allTerms = getAllTermsOfExample(example);

    sendStatusUpdateToDelegate("Creating new rule: anchoring...", TextRulerLearnerState.ML_RUNNING,
            false);
    for (int i = 0; i < numberOfSlotsInTag; i++) {
      theRule = anchor(theRule, doc, example, allTerms, i);
      if (shouldAbort())
        return null;
    }

    sendStatusUpdateToDelegate("Creating new rule: extending...", TextRulerLearnerState.ML_RUNNING,
            false);
    if (theRule != null) {
      double oldLaplacian = theRule.getLaplacian();
      int subRoundNumber = 0;
      // repeat while we still make errors...
      while (theRule.getCoveringStatistics().getCoveredNegativesCount() > 0) {
        WhiskRule extendedRule = extendRule(theRule, doc, example, allTerms, subRoundNumber);
        if (extendedRule == null) {
          // this way we get the previous rule
          // as the best rule...
          break;
        }
        theRule = extendedRule;
        TextRulerToolkit.log("----------------------------");
        TextRulerToolkit.log("BEST EXTENSION IS: " + theRule.getRuleString());
        TextRulerToolkit.log("Laplacian: " + theRule.getLaplacian() + "    ; "
                + theRule.getCoveringStatistics());
        subRoundNumber++;

        double newLaplacian = theRule.getLaplacian();
        if (newLaplacian >= oldLaplacian) {
          break;
        }
        oldLaplacian = newLaplacian;
      }
      TextRulerToolkit.log("----------------------------");
      TextRulerToolkit.log("FINAL RULE IS : " + theRule.getRuleString());
    }
    return theRule;
  }

  protected WhiskRule extendRule(WhiskRule rule, TextRulerExampleDocument doc,
          TextRulerExample example, List<WhiskRuleItem> allTerms, int subRoundNumber) {
    WhiskRule bestRule = null;
    double bestL = 1.0;
    int bestRuleConstraintPoints = -1;
    if (rule.getLaplacian() <= errorThreshold) {
      bestRule = rule;
      bestL = rule.getLaplacian();
    }

    List<WhiskRuleItem> slotTerms = getTermsWithinBounds(allTerms,
            example.getAnnotations()[0].getBegin(), example.getAnnotations()[0].getEnd());
    WhiskRuleItem firstSlotTerm = slotTerms.get(0);
    WhiskRuleItem lastSlotTerm = slotTerms.get(slotTerms.size() - 1);

    List<TextRulerRule> rulesToTest = new ArrayList<TextRulerRule>();
    for (WhiskRuleItem term : allTerms) {
      if (rule.containsTerm(term)) {
        continue;
      }

      boolean rejectTerm = false;
      // for now this works only for slot 0 (no multislot stuff here yet!)
      if (term.getTermNumberInExample() < firstSlotTerm.getTermNumberInExample())
        rejectTerm = firstSlotTerm.getTermNumberInExample() - term.getTermNumberInExample() > windowSize;
      else if (term.getTermNumberInExample() > lastSlotTerm.getTermNumberInExample())
        rejectTerm = term.getTermNumberInExample() - firstSlotTerm.getTermNumberInExample() > windowSize;

      if (rejectTerm) {
        // out of window scope -> skip to next...
        continue;
      }

      WhiskRule proposedRule = createNewRuleByAddingTerm(rule, term);
      WhiskRuleItem t = proposedRule.searchItemWithTermNumber(term.getTermNumberInExample());

      if (!rulesToTest.contains(proposedRule))
        rulesToTest.add(proposedRule);

      // add a second version where we remove the exact token content if
      // it is a regexp item:
      WhiskRule proposedRule2 = null;
      WhiskRuleItem t2 = null;
      if (t.getWordConstraint().isRegExpConstraint()) {
        proposedRule2 = proposedRule.copy();
        t2 = proposedRule2.searchItemWithTermNumber(term.getTermNumberInExample());
        t2.setHideRegExp(true);
        proposedRule2.setNeedsCompile(true);
        if (!rulesToTest.contains(proposedRule2)) {
          rulesToTest.add(proposedRule2);
        }
      }

      // and now, for WHISK performance testing purposes, we also add POS
      // tags:
      // this is not very nice code and not dynamic feature capable, but
      // for testpurposes
      // in order to test WHISK with PosTag Terms...
      if (posTagRootTypeName != null && posTagRootTypeName.length() > 0) {
        TextRulerAnnotation tokenAnnotation = term.getWordConstraint().getTokenAnnotation();
        CAS cas = example.getDocumentCAS();
        TypeSystem ts = cas.getTypeSystem();
        Type posTagsRootType = ts.getType(posTagRootTypeName);
        if (ts != null) {
          // POS-Tags created by our test hmm tagger.
          List<AnnotationFS> posTagAnnotations = TextRulerToolkit.getAnnotationsWithinBounds(cas,
                  tokenAnnotation.getBegin(), tokenAnnotation.getEnd(), null, posTagsRootType);
          if (posTagAnnotations.size() > 0) {
            AnnotationFS posTag = posTagAnnotations.get(0);
            if (posTag.getBegin() == tokenAnnotation.getBegin()
                    && posTag.getEnd() == tokenAnnotation.getEnd()) {
              TextRulerAnnotation posTagAnnotation = new TextRulerAnnotation(posTag, doc);

              // 1. most specific term with all constraints we
              // have:
              WhiskRule proposedRule3 = proposedRule.copy();
              WhiskRuleItem t3 = proposedRule3.searchItemWithTermNumber(term
                      .getTermNumberInExample());
              t3.addOtherConstraint(new MLWhiskOtherConstraint(tokenAnnotation, posTagAnnotation));
              proposedRule3.setNeedsCompile(true);
              if (!rulesToTest.contains(proposedRule3))
                rulesToTest.add(proposedRule3);

              // 2. the same without the regexp thingy:
              if (proposedRule2 != null) {
                WhiskRule proposedRule4 = proposedRule2.copy();
                WhiskRuleItem t4 = proposedRule4.searchItemWithTermNumber(term
                        .getTermNumberInExample());
                t4.addOtherConstraint(new MLWhiskOtherConstraint(tokenAnnotation, posTagAnnotation));
                proposedRule4.setNeedsCompile(true);
                if (!rulesToTest.contains(proposedRule4))
                  rulesToTest.add(proposedRule4);
              }

              // 3. last but not least: a rule with only the pos
              // tag constraint:
              WhiskRule proposedRule5 = proposedRule.copy();
              WhiskRuleItem t5 = proposedRule5.searchItemWithTermNumber(term
                      .getTermNumberInExample());
              t5.addOtherConstraint(new MLWhiskOtherConstraint(tokenAnnotation, posTagAnnotation));
              t5.setWordConstraint(null);
              proposedRule5.setNeedsCompile(true);
              if (!rulesToTest.contains(proposedRule5))
                rulesToTest.add(proposedRule5);
            }
          }
        }
      }

    }
    if (rulesToTest.size() == 0)
      return bestRule;

    sendStatusUpdateToDelegate(
            "Round "
                    + roundNumber
                    + "."
                    + subRoundNumber
                    + " - Testing "
                    + rulesToTest.size()
                    + " rules... "
                    + " - uncovered examples: "
                    + (allExamplesCount - coveredExamples.size() + " / " + allExamplesCount
                            + " ; cs=" + cachedTestedRuleStatistics.size()),
            TextRulerLearnerState.ML_RUNNING, false);

    TextRulerToolkit.log("Testing " + rulesToTest.size() + " rules on training set...");
    for (TextRulerRule r : rulesToTest)
      TextRulerToolkit.log(r.getRuleString());
    testRulesIfNotCached(rulesToTest); // testRulesOnDocumentSet(rulesToTest,
    // exampleDocuments);
    if (shouldAbort())
      return null;
    for (TextRulerRule r : rulesToTest) {
      WhiskRule wr = (WhiskRule) r;
      if (wr.getLaplacian() < bestL) {
        bestL = wr.getLaplacian();
        bestRule = wr;
        bestRuleConstraintPoints = bestRule.totalConstraintPoints();
      } else if (wr.getLaplacian() == bestL && bestRuleConstraintPoints >= 0) {
        TextRulerToolkit.log("Same Laplacian! So prefer more general rule!");
        if (wr.totalConstraintPoints() < bestRuleConstraintPoints) {
          TextRulerToolkit.log("\tYes, prefered!");
          bestL = wr.getLaplacian();
          bestRule = wr;
          bestRuleConstraintPoints = bestRule.totalConstraintPoints();
        }
      }
    }
    return bestRule;
  }

  protected WhiskRule createNewRuleByAddingTerm(WhiskRule baseRule, WhiskRuleItem term) {
    WhiskRule newRule = baseRule.copy();
    int foundSlotNumber = -1; // debug info
    String foundSlotPattern = "";
    int termNumber = term.getTermNumberInExample();
    // determine, where this term is located relatively to the slots we
    // have...
    TextRulerRulePattern targetPattern = null;
    TextRulerRulePattern previousSlotPostFillerPattern = null;
    for (int i = 0; i < newRule.getPatterns().size(); i++) {
      TextRulerSlotPattern slotPattern = newRule.getPatterns().get(i);
      WhiskRuleItem it = (WhiskRuleItem) slotPattern.preFillerPattern.lastItem(); // look at the
      // prefiller
      // pattern
      if (it != null && termNumber <= it.getTermNumberInExample())
        targetPattern = slotPattern.preFillerPattern;
      if (targetPattern == null && slotPattern.fillerPattern.size() > 0) // now
      // look
      // at
      // the
      // filler
      // pattern
      {
        it = (WhiskRuleItem) slotPattern.fillerPattern.firstItem();
        if (termNumber < it.getTermNumberInExample()) // it's still for
          // the prefiller
          // pattern but it
          // seems to be
          // emtpy so we
          // could not find
          // that out above!
          targetPattern = slotPattern.preFillerPattern;
        else {
          it = (WhiskRuleItem) slotPattern.fillerPattern.lastItem();
          if (termNumber <= it.getTermNumberInExample()) {
            targetPattern = slotPattern.fillerPattern;
          }
        }
      }
      if (targetPattern == null && slotPattern.postFillerPattern.size() > 0) // now look at
      // the
      // postfiller
      // pattern
      {
        it = (WhiskRuleItem) slotPattern.postFillerPattern.firstItem();
        if (termNumber < it.getTermNumberInExample()) // it's still for
          // the filler
          // pattern but it
          // seems to be
          // emtpy so we
          // could not find
          // that out above!
          targetPattern = slotPattern.fillerPattern;
        else {
          it = (WhiskRuleItem) slotPattern.postFillerPattern.lastItem();
          if (termNumber <= it.getTermNumberInExample())
            targetPattern = slotPattern.postFillerPattern;
        }
      }
      if (targetPattern == null) {
        targetPattern = previousSlotPostFillerPattern;
        if (i > 0) {
          TextRulerSlotPattern prevSlotPattern = newRule.getPatterns().get(i - 1);
          foundSlotPattern = targetPattern == prevSlotPattern.preFillerPattern ? "PRE FILLER"
                  : (targetPattern == prevSlotPattern.fillerPattern ? "FILLER" : "POST FILLER");
          foundSlotNumber = i - 1;
        }
      } else {
        foundSlotPattern = targetPattern == slotPattern.preFillerPattern ? "PRE FILLER"
                : (targetPattern == slotPattern.fillerPattern ? "FILLER" : "POST FILLER");
        foundSlotNumber = i;
      }
      previousSlotPostFillerPattern = slotPattern.postFillerPattern;
    }

    if (targetPattern == null) {
      targetPattern = previousSlotPostFillerPattern;
      foundSlotNumber = newRule.getPatterns().size() - 1;
      foundSlotPattern = "POST FILLER";
    }

    if (targetPattern == null) {
      TextRulerToolkit.log("ERROR, NO TARGET PATTERN FOR NEW RULE TERM FOUND !");
    } else {
      // TextRulerToolkit.log("Ok, found for Rule: "+newRule.getRuleString());
      // TextRulerToolkit.log("Term: "+term.getTermNumberInExample()+" ; "+term);
      // TextRulerToolkit.log("Slot "+foundSlotNumber+" - Pattern: "+foundSlotPattern);
      // now put that term into the rule:
      int indexInPattern = -1;
      if (targetPattern.size() == 0) {
        targetPattern.add(term.copy());
        indexInPattern = 0;
      } else {
        // 1. search if the term would replace a wildcard:
        WhiskRuleItem wildCard = newRule.searchItemWithTermNumber(termNumber);
        if (wildCard != null) {
          if (!wildCard.isStarWildCard()) {
            TextRulerToolkit
                    .log("ERROR, FOUND A TERM WITH THE SAME NUMBER THAT IS NOT A WILDCARD! HOW IS THAT???");
            return null;
          }
          if (!targetPattern.contains(wildCard)) {
            TextRulerToolkit.log("EVEN WORSE, THAT MUST NOT BE AT ALL!");
            return null;
          }
          indexInPattern = targetPattern.indexOf(wildCard);
          targetPattern.set(indexInPattern, term.copy());
        } else {
          // not a wildcard, so search for the insertion point:
          for (int i = 0; i < targetPattern.size(); i++) {
            WhiskRuleItem it = (WhiskRuleItem) targetPattern.get(i);
            if (termNumber < it.getTermNumberInExample()) {
              indexInPattern = i;
              break;
            }
          }
          if (indexInPattern < 0) {
            indexInPattern = targetPattern.size();
            targetPattern.add(term.copy());
          } else
            targetPattern.add(indexInPattern, term.copy());
        }
      }
      // ok, now we have replaced a wildcard with the term or added the
      // term between two other items.
      // we now have to check the neighbors of the new term: if it is a
      // direct neighbor (according to the termNumber),
      // we have nothing special to do. but if it is not a direct
      // neighbor, we have to add a wildcard between the two items (if the
      // neighbor item
      // is not a wildcard itself!
      WhiskRuleItem newTerm = (WhiskRuleItem) targetPattern.get(indexInPattern);

      // look at left neighbor:
      WhiskRuleItem left = newRule.searchNeighborOfItem(newTerm, true);
      if (left != null) {
        // TextRulerToolkit.log("LEFT NEIGHBOR FOUND!");

        // so we have a left neighbor. let's see if it also is the
        // neighbor in our seed token stream:
        if (left.getTermNumberInExample() < newTerm.getTermNumberInExample() - 1
                && !left.isStarWildCard()) { // no direct neighbor and
          // no wildcard yet,
          // so insert a wildcard between us!
          targetPattern.add(indexInPattern,
                  WhiskRuleItem.newWildCardItem(left.getTermNumberInExample() + 1));
          indexInPattern++;
        }
      }

      // look at right neighbor:
      WhiskRuleItem right = newRule.searchNeighborOfItem(newTerm, false);
      if (right != null) {
        // TextRulerToolkit.log("RIGHT NEIGHBOR FOUND!");
        // so we have a right neighbor. let's see if it also is the
        // neighbor in our seed token stream:
        if (right.getTermNumberInExample() > newTerm.getTermNumberInExample() + 1
                && !right.isStarWildCard()) { // no direct neighbor and
          // no wildcard yet,
          // so insert a wildcard between us!
          WhiskRuleItem wc = WhiskRuleItem.newWildCardItem(newTerm.getTermNumberInExample() + 1);
          if (indexInPattern + 1 < targetPattern.size())
            targetPattern.add(indexInPattern + 1, wc);
          else
            targetPattern.add(wc);
        }
      }

      newRule.setNeedsCompile(true);
      // TextRulerToolkit.log("BEFORE: "+baseRule.getRuleString());
      // TextRulerToolkit.log("AFTER : "+newRule.getRuleString());
      // TextRulerToolkit.log("");
    }
    if (newRule.getRuleString().equals(baseRule.getRuleString())) // this
      // must
      // not be!
      return null;
    else
      return newRule;
  }

  protected WhiskRule anchor(WhiskRule rule, TextRulerExampleDocument doc,
          TextRulerExample example, List<WhiskRuleItem> allTerms, int slotIndex) {
    TextRulerAnnotation slotAnnotation = example.getAnnotations()[slotIndex];
    List<WhiskRuleItem> inside = getTermsWithinBounds(allTerms, slotAnnotation.getBegin(),
            slotAnnotation.getEnd());

    if (rule == null || inside.isEmpty()) {
      return null;
    }
    // create base 1 and base 2:
    WhiskRule base1 = rule.copy(); // slot filler rule
    TextRulerSlotPattern slotPattern = base1.getPatterns().get(slotIndex);
    for (int i = 0; i < inside.size(); i++)
      if (i == 0 || (i == inside.size() - 1))
        slotPattern.fillerPattern.add(inside.get(i).copy());
      else if (inside.size() > 2 && i < 2)
        slotPattern.fillerPattern.add(WhiskRuleItem.newWildCardItem(inside.get(i)
                .getTermNumberInExample()));

    WhiskRule base2 = rule.copy(); // slot context rule
    slotPattern = base2.getPatterns().get(slotIndex);

    int firstOfSlot = allTerms.indexOf(inside.get(0));
    int lastOfSlot = allTerms.indexOf(inside.get(inside.size() - 1));
    if (firstOfSlot > 0)
      slotPattern.preFillerPattern.add(allTerms.get(firstOfSlot - 1));
    slotPattern.fillerPattern.add(WhiskRuleItem.newWildCardItem(inside.get(0)
            .getTermNumberInExample()));
    if (lastOfSlot + 1 < allTerms.size())
      slotPattern.postFillerPattern.add(allTerms.get(lastOfSlot + 1));

    TextRulerToolkit.log("base1: " + base1.getRuleString());
    TextRulerToolkit.log("base2: " + base2.getRuleString());
    List<TextRulerRule> testRules = new ArrayList<TextRulerRule>();
    testRules.add(base1);
    testRules.add(base2);
    // testRulesOnDocumentSet(testRules, exampleDocuments);
    testRulesIfNotCached(testRules);
    if (shouldAbort())
      return null;
    TextRulerToolkit.log("\tbase1: " + base1.getCoveringStatistics() + " --> laplacian = "
            + base1.getLaplacian());
    TextRulerToolkit.log("\tbase2: " + base2.getCoveringStatistics() + " --> laplacian = "
            + base2.getLaplacian());
    if (base2.getCoveringStatistics().getCoveredPositivesCount() > base1.getCoveringStatistics()
            .getCoveredPositivesCount())
      return base2;
    else
      return base1;
  }

  public String getResultString() {
    if (ruleList != null)
      return getTMFileHeaderString() + ruleList.getRulesString("");
    else
      return "No results available yet!";
  }

  public void setParameters(Map<String, Object> params) {
    if (TextRulerToolkit.DEBUG)
      saveParametersToTempFolder(params);

    // TODO try catch
    if (params.containsKey(WINDOSIZE_KEY))
      windowSize = (Integer) params.get(WINDOSIZE_KEY);

    if (params.containsKey(ERROR_THRESHOLD_KEY))
      errorThreshold = (Float) params.get(ERROR_THRESHOLD_KEY);

    if (params.containsKey(POSTAG_ROOTTYPE_KEY))
      posTagRootTypeName = (String) params.get(POSTAG_ROOTTYPE_KEY);

  }

  public List<WhiskRuleItem> getAllTermsOfExample(TextRulerExample example) {
    CAS cas = example.getDocumentCAS();
    Type tokensRootType = cas.getTypeSystem().getType(TextRulerToolkit.TM_ANY_TYPE_NAME);
    List<AnnotationFS> all = TextRulerToolkit.getAnnotationsWithinBounds(cas, 0, cas
            .getDocumentText().length() + 1, TextRulerToolkit.getFilterSetWithSlotNames(slotNames,
            filterSet), tokensRootType);

    List<WhiskRuleItem> result = new ArrayList<WhiskRuleItem>();
    int i = 0;
    for (AnnotationFS afs : all) {
      WhiskRuleItem term = new WhiskRuleItem(new TextRulerAnnotation(afs, example.getDocument()));
      term.setTermNumberInExample(i);
      i++;
      result.add(term);
    }
    return result;
  }

  public List<WhiskRuleItem> getTermsWithinBounds(List<WhiskRuleItem> allTerms, int startPos,
          int endPos) {
    List<WhiskRuleItem> result = new ArrayList<WhiskRuleItem>();
    for (WhiskRuleItem term : allTerms) {
      TextRulerAnnotation a = term.getWordConstraint().getTokenAnnotation();
      if (a.getBegin() >= startPos && a.getEnd() <= endPos)
        result.add(term);
      if (a.getEnd() > endPos)
        break;
    }
    return result;
  }

  // TODO share this between algorithms (e.g. LP2 and RAPIER, WHISK ?) and
  // make a maximum size of the cache, etc. like CasCache?
  protected void testRulesIfNotCached(List<TextRulerRule> rules) {
    List<TextRulerRule> rulesToTest = new ArrayList<TextRulerRule>();

    for (TextRulerRule r : rules) {
      String key = r.getRuleString();
      if (cachedTestedRuleStatistics.containsKey(key)) {
        r.setCoveringStatistics(cachedTestedRuleStatistics.get(key).copy());
        TextRulerToolkit.log("CACHE HIT !");
      } else
        rulesToTest.add(r);
    }

    if (rulesToTest.size() > 0) {
      testRulesOnDocumentSet(rulesToTest, exampleDocuments);
      if (shouldAbort())
        return;
      for (TextRulerRule r : rulesToTest) {
        String key = r.getRuleString();
        cachedTestedRuleStatistics.put(key, r.getCoveringStatistics().copy());
      }
    }
  }

}
