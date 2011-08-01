package org.apache.uima.tm.textruler.whisk.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textruler.core.TextRulerAnnotation;
import org.apache.uima.tm.textruler.core.TextRulerBasicLearner;
import org.apache.uima.tm.textruler.core.TextRulerExample;
import org.apache.uima.tm.textruler.core.TextRulerExampleDocument;
import org.apache.uima.tm.textruler.core.TextRulerRule;
import org.apache.uima.tm.textruler.core.TextRulerRuleItem;
import org.apache.uima.tm.textruler.core.TextRulerRuleList;
import org.apache.uima.tm.textruler.core.TextRulerRulePattern;
import org.apache.uima.tm.textruler.core.TextRulerSlotPattern;
import org.apache.uima.tm.textruler.core.TextRulerStatisticsCollector;
import org.apache.uima.tm.textruler.core.TextRulerTarget;
import org.apache.uima.tm.textruler.core.TextRulerToolkit;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerDelegate;
import org.apache.uima.tm.textruler.whisk.generic.WhiskRuleItem.MLWhiskOtherConstraint;

public class Whisk extends TextRulerBasicLearner {

  public final static String WINDOWSIZE_KEY = "windowSize";

  public final static String ERROR_THRESHOLD_KEY = "errorThreshold";

  public final static String POSTAG_ROOTTYPE_KEY = "posTagRootType";

  public final static int STANDARD_WINDOWSIZE = 5;

  public final static float STANDARD_ERROR_THRESHOLD = 0.1f;

  public final static String STANDARD_POSTAG_ROOTTYPE = "de.uniwue.ml.ML.postag";

  TextRulerRuleList ruleList;

  protected Set<TextRulerExample> coveredExamples;

  protected int windowSize = STANDARD_WINDOWSIZE;

  protected double errorThreshold = STANDARD_ERROR_THRESHOLD;

  protected String posTagRootTypeName = STANDARD_POSTAG_ROOTTYPE;

  int roundNumber = 0;

  int allExamplesCount = 0;

  private Map<String, TextRulerStatisticsCollector> cachedTestedRuleStatistics = new HashMap<String, TextRulerStatisticsCollector>();

  public Whisk(String inputDir, String prePropTmFile, String tmpDir,
      String[] slotNames, Set<String> filterSet,
      TextRulerLearnerDelegate delegate) {
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

    sendStatusUpdateToDelegate("Creating examples...",
        TextRulerLearnerState.ML_RUNNING, false);
    for (int i = 0; i < slotNames.length; i++) {
      TextRulerTarget target = new TextRulerTarget(slotNames[i], this);
      exampleDocuments.createExamplesForTarget(target);

      TextRulerExampleDocument[] docs = exampleDocuments
          .getSortedDocumentsInCacheOptimizedOrder();

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
              coveredExamples.addAll(newRule.getCoveringStatistics()
                  .getCoveredPositiveExamples());
              sendStatusUpdateToDelegate("New Rule added...",
                  TextRulerLearnerState.ML_RUNNING, true);
            }
          }
        }
        if (shouldAbort())
          return;
      }
    }
    sendStatusUpdateToDelegate("Done", TextRulerLearnerState.ML_DONE, true);
    cachedTestedRuleStatistics.clear();
  }

  protected WhiskRule growRule(TextRulerExampleDocument doc,
      TextRulerExample example) {
    sendStatusUpdateToDelegate("Creating new rule from seed...",
        TextRulerLearnerState.ML_RUNNING, false);
    WhiskRule theRule = new WhiskRule(this, example.getTarget(), example);
    int numberOfSlotsInTag = example.getAnnotations().length;
    for (int i = 0; i < numberOfSlotsInTag; i++)
      theRule.getPatterns().add(new TextRulerSlotPattern());

    sendStatusUpdateToDelegate("Creating new rule: anchoring...",
        TextRulerLearnerState.ML_RUNNING, false);
    for (int i = 0; i < numberOfSlotsInTag; i++) {
      theRule = anchor(theRule, doc, example, i);
      if (shouldAbort())
        return null;
    }

    sendStatusUpdateToDelegate("Creating new rule: extending...",
        TextRulerLearnerState.ML_RUNNING, false);
    if (theRule != null) {
      double oldLaplacian = theRule.getLaplacian();
      int subRoundNumber = 0;
      // repeat while we still make errors...
      while (theRule.getCoveringStatistics().getCoveredNegativesCount() > 0) {
        WhiskRule extendedRule = extendRule(theRule, doc, example,
            subRoundNumber);
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
      TextRulerExample example, int subRoundNumber) {
    WhiskRule bestRule = null;
    double bestL = 1.0;
    int bestRuleConstraintPoints = -1;
    if (rule.getLaplacian() <= errorThreshold) {
      bestRule = rule;
      bestL = rule.getLaplacian();
    }

    List<List<WhiskRuleItem>> slotTerms = getTermsWithinBounds(example
        .getAnnotations()[0].getBegin(), example.getAnnotations()[0].getEnd(),
        example);
    List<List<WhiskRuleItem>> windowTerms = getTermsWithinWindow(slotTerms,
        example, 0);

    List<TextRulerRule> rulesToTest = new ArrayList<TextRulerRule>();
    for (List<WhiskRuleItem> eachList : windowTerms) {
      for (WhiskRuleItem term : eachList) {

        if (rule.containsTerm(term)) {
          continue;
        }

        WhiskRule proposedRule = createNewRuleByAddingTerm(rule, term);
        if (proposedRule == null)
          continue;
        WhiskRuleItem t = term;

        if (!rulesToTest.contains(proposedRule))
          rulesToTest.add(proposedRule);

        // add a second version where we remove the exact token content if
        // it is a regexp item:
        WhiskRule proposedRule2 = null;
        WhiskRuleItem t2 = null;
        if (t.getWordConstraint().isRegExpConstraint()) {
          proposedRule2 = proposedRule.copy();
          t2 = term;
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
          TextRulerAnnotation tokenAnnotation = term.getWordConstraint()
              .getTokenAnnotation();
          CAS cas = example.getDocumentCAS();
          TypeSystem ts = cas.getTypeSystem();
          Type posTagsRootType = ts.getType(posTagRootTypeName);
          if (ts != null) {
            // POS-Tags created by our test hmm tagger.
            List<AnnotationFS> posTagAnnotations = TextRulerToolkit
                .getAnnotationsWithinBounds(cas, tokenAnnotation.getBegin(),
                    tokenAnnotation.getEnd(), null, posTagsRootType);
            if (posTagAnnotations.size() > 0) {
              AnnotationFS posTag = posTagAnnotations.get(0);
              if (posTag.getBegin() == tokenAnnotation.getBegin()
                  && posTag.getEnd() == tokenAnnotation.getEnd()) {
                TextRulerAnnotation posTagAnnotation = new TextRulerAnnotation(
                    posTag, doc);

                // 1. most specific term with all constraints we
                // have:
                WhiskRule proposedRule3 = proposedRule.copy();
                WhiskRuleItem t3 = term;
                t3.addOtherConstraint(new MLWhiskOtherConstraint(
                    tokenAnnotation, posTagAnnotation));
                proposedRule3.setNeedsCompile(true);
                if (!rulesToTest.contains(proposedRule3))
                  rulesToTest.add(proposedRule3);

                // 2. the same without the regexp thingy:
                if (proposedRule2 != null) {
                  WhiskRule proposedRule4 = proposedRule2.copy();
                  WhiskRuleItem t4 = term;
                  t4.addOtherConstraint(new MLWhiskOtherConstraint(
                      tokenAnnotation, posTagAnnotation));
                  proposedRule4.setNeedsCompile(true);
                  if (!rulesToTest.contains(proposedRule4))
                    rulesToTest.add(proposedRule4);
                }

                // 3. last but not least: a rule with only the pos
                // tag constraint:
                WhiskRule proposedRule5 = proposedRule.copy();
                WhiskRuleItem t5 = term;
                t5.addOtherConstraint(new MLWhiskOtherConstraint(
                    tokenAnnotation, posTagAnnotation));
                t5.setWordConstraint(null);
                proposedRule5.setNeedsCompile(true);
                if (!rulesToTest.contains(proposedRule5))
                  rulesToTest.add(proposedRule5);
              }
            }
          }
        }
      }
    }
    if (rulesToTest.size() == 0)
      return bestRule;

    sendStatusUpdateToDelegate("Round "
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

    TextRulerToolkit.log("Testing " + rulesToTest.size()
        + " rules on training set...");
    for (TextRulerRule r : rulesToTest)
      TextRulerToolkit.log(r.getRuleString());
    testRulesIfNotCached(rulesToTest);

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

  private List<List<WhiskRuleItem>> getTermsWithinWindow(
      List<List<WhiskRuleItem>> slotTerms, TextRulerExample example, int steps) {
    if (steps == windowSize)
      return slotTerms;
    List<List<WhiskRuleItem>> result = new ArrayList<List<WhiskRuleItem>>();

    for (List<WhiskRuleItem> list : slotTerms) {
      List<WhiskRuleItem> termsBefore = getTermsBefore(list.get(0), example);
      List<WhiskRuleItem> termsAfter = getTermsAfter(list.get(list.size() - 1),
          example);
      if (!termsBefore.isEmpty()) {
        for (WhiskRuleItem before : termsBefore) {
          for (WhiskRuleItem after : termsAfter) {
            List<WhiskRuleItem> newList = new ArrayList<WhiskRuleItem>();
            newList.add(before);
            newList.addAll(list);
            newList.add(after);
            result.add(newList);
          }
        }
      } else {
        for (WhiskRuleItem after : termsAfter) {
          List<WhiskRuleItem> newList = new ArrayList<WhiskRuleItem>();
          newList.addAll(list);
          newList.add(after);
          result.add(newList);
        }
      }
    }
    result = getTermsWithinWindow(result, example, ++steps);
    return result;
  }

  protected WhiskRule createNewRuleByAddingTerm(WhiskRule baseRule,
      WhiskRuleItem term) {
    if (term == null)
      return null;
    if (term.isStarWildCard() || term.getWordConstraint() == null)
      return null;
    WhiskRule newRule = baseRule.copy();
    // int foundSlotNumber = -1; // debug info
    // String foundSlotPattern = "";
    int termBeginNumber = term.getWordConstraint().getTokenAnnotation()
        .getBegin();
    int termEndNumber = term.getWordConstraint().getTokenAnnotation().getEnd();
    TextRulerRulePattern targetPattern = null;
    TextRulerRulePattern previousSlotPostFillerPattern = null;
    for (int i = 0; i < newRule.getPatterns().size(); i++) {
      TextRulerSlotPattern slotPattern = newRule.getPatterns().get(i);
      WhiskRuleItem it = (WhiskRuleItem) slotPattern.preFillerPattern
          .lastItem(); // look at the
      // prefiller
      // pattern
      if (it != null
          && it.getWordConstraint() != null
          && termEndNumber <= it.getWordConstraint().getTokenAnnotation()
              .getBegin())
        targetPattern = slotPattern.preFillerPattern;
      if (targetPattern == null && slotPattern.fillerPattern.size() > 0) // now
      // look
      // at
      // the
      // filler
      // pattern
      {
        it = (WhiskRuleItem) slotPattern.fillerPattern.firstItem();
        if (it.getWordConstraint() != null
            && termEndNumber <= it.getWordConstraint().getTokenAnnotation()
                .getBegin()) // it's
          // still
          // for
          // the prefiller
          // pattern but it
          // seems to be
          // emtpy so we
          // could not find
          // that out above!
          targetPattern = slotPattern.preFillerPattern;
        else {
          it = (WhiskRuleItem) slotPattern.fillerPattern.lastItem();
          if (it.getWordConstraint() != null
              && termEndNumber <= it.getWordConstraint().getTokenAnnotation()
                  .getBegin()) {
            targetPattern = slotPattern.fillerPattern;
          }
        }
      }
      if (targetPattern == null && slotPattern.postFillerPattern.size() > 0) // now
                                                                             // look
                                                                             // at
      // the
      // postfiller
      // pattern
      {
        it = (WhiskRuleItem) slotPattern.postFillerPattern.firstItem();
        if (it.getWordConstraint() != null
            && termEndNumber <= it.getWordConstraint().getTokenAnnotation()
                .getBegin()) // it's
          // still
          // for
          // the filler
          // pattern but it
          // seems to be
          // emtpy so we
          // could not find
          // that out above!
          targetPattern = slotPattern.fillerPattern;
        else {
          it = (WhiskRuleItem) slotPattern.postFillerPattern.lastItem();
          if (it.getWordConstraint() != null
              && termEndNumber <= it.getWordConstraint().getTokenAnnotation()
                  .getBegin())
            targetPattern = slotPattern.postFillerPattern;
        }
      }
      if (targetPattern == null) {
        targetPattern = previousSlotPostFillerPattern;
        // debug info
        // if (i > 0) {
        // TextRulerSlotPattern prevSlotPattern = newRule.getPatterns().get(i -
        // 1);
        // foundSlotPattern = targetPattern == prevSlotPattern.preFillerPattern
        // ? "PRE FILLER"
        // : (targetPattern == prevSlotPattern.fillerPattern ? "FILLER" :
        // "POST FILLER");
        // foundSlotNumber = i - 1;
        // }
        // } else {
        // foundSlotPattern = targetPattern == slotPattern.preFillerPattern ?
        // "PRE FILLER"
        // : (targetPattern == slotPattern.fillerPattern ? "FILLER" :
        // "POST FILLER");
        // foundSlotNumber = i;
      }
      previousSlotPostFillerPattern = slotPattern.postFillerPattern;
    }

    if (targetPattern == null) {
      targetPattern = previousSlotPostFillerPattern;
      // debug info
      // foundSlotNumber = newRule.getPatterns().size() - 1;
      // foundSlotPattern = "POST FILLER";
    }

    if (targetPattern == null) {
      TextRulerToolkit
          .log("ERROR, NO TARGET PATTERN FOR NEW RULE TERM FOUND !");
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
        WhiskRuleItem wildCard = null;
        for (TextRulerRuleItem i : newRule.getPatterns().get(0).preFillerPattern) {
          if (((WhiskRuleItem) i).isStarWildCard()) {
            WhiskRuleItem left = newRule.searchNeighborOfItem(
                ((WhiskRuleItem) i), true);
            WhiskRuleItem right = newRule.searchNeighborOfItem(
                ((WhiskRuleItem) i), false);
            if (left.getWordConstraint().getTokenAnnotation().getEnd() <= termBeginNumber
                && right.getWordConstraint().getTokenAnnotation().getBegin() >= termEndNumber)
              wildCard = (WhiskRuleItem) i;
          }
        }
        if (wildCard == null) {
          for (TextRulerRuleItem i : newRule.getPatterns().get(0).fillerPattern) {
            if (((WhiskRuleItem) i).isStarWildCard()) {
              WhiskRuleItem left = newRule.searchNeighborOfItem(
                  ((WhiskRuleItem) i), true);
              WhiskRuleItem right = newRule.searchNeighborOfItem(
                  ((WhiskRuleItem) i), false);
              if (left != null
                  && left.getWordConstraint().getTokenAnnotation().getEnd() <= termBeginNumber
                  && right.getWordConstraint().getTokenAnnotation().getBegin() >= termEndNumber)
                wildCard = (WhiskRuleItem) i;
            }
          }
        }
        if (wildCard == null) {
          for (TextRulerRuleItem i : newRule.getPatterns().get(0).postFillerPattern) {
            if (((WhiskRuleItem) i).isStarWildCard()) {
              WhiskRuleItem left = newRule.searchNeighborOfItem(
                  ((WhiskRuleItem) i), true);
              WhiskRuleItem right = newRule.searchNeighborOfItem(
                  ((WhiskRuleItem) i), false);
              if (left.getWordConstraint().getTokenAnnotation().getEnd() <= termBeginNumber
                  && right.getWordConstraint().getTokenAnnotation().getBegin() >= termEndNumber)
                wildCard = (WhiskRuleItem) i;
            }
          }
        }
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
            if (it.getWordConstraint() != null
                && termEndNumber <= it.getWordConstraint().getTokenAnnotation()
                    .getBegin()) {
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
      if (left != null && left.getWordConstraint() != null) {
        // TextRulerToolkit.log("LEFT NEIGHBOR FOUND!");

        // so we have a left neighbor. let's see if it also is the
        // neighbor in our seed token stream:
        if (!left.isStarWildCard()) { // no direct neighbor and
          // no wildcard yet,
          // so insert a wildcard between us!
          boolean isValid = isNextValidNeighbor(left, newTerm, newRule
              .getSeedExample());
          if (!isValid) {
            targetPattern.add(indexInPattern, WhiskRuleItem
                .newWildCardItem(getElementIndex(newRule, left) + 1));
            indexInPattern++;
          }
        }
      }

      // look at right neighbor:
      WhiskRuleItem right = newRule.searchNeighborOfItem(newTerm, false);
      if (right != null && right.getWordConstraint() != null) {
        // TextRulerToolkit.log("RIGHT NEIGHBOR FOUND!");
        // so we have a right neighbor. let's see if it also is the
        // neighbor in our seed token stream:
        if (!right.isStarWildCard()) {
          // no direct neighbor and
          // no wildcard yet,
          // so insert a wildcard between us!
          boolean isValid = isNextValidNeighbor(newTerm, right, newRule
              .getSeedExample());
          if (!isValid) {
            WhiskRuleItem wc = WhiskRuleItem.newWildCardItem(getElementIndex(
                newRule, newTerm) + 1);
            if (indexInPattern + 1 < targetPattern.size())
              targetPattern.add(indexInPattern + 1, wc);
            else
              targetPattern.add(wc);
          }
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
      TextRulerExample example, int slotIndex) {
    List<WhiskRule> result = new ArrayList<WhiskRule>();
    TextRulerAnnotation slotAnnotation = example.getAnnotations()[slotIndex];
    List<List<WhiskRuleItem>> window = getTermsWithinBounds(slotAnnotation
        .getBegin(), slotAnnotation.getEnd(), example);

    for (List<WhiskRuleItem> inside : window) {

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
          slotPattern.fillerPattern.add(WhiskRuleItem
              .newWildCardItem(getElementIndex(base1, inside.get(i))));

      List<WhiskRuleItem> beforeList = getTermsBefore(inside.get(0), example);
      List<WhiskRuleItem> afterList = getTermsAfter(inside
          .get(inside.size() - 1), example);
      Collection<WhiskRule> tempRules = new HashSet<WhiskRule>();

      if (!beforeList.isEmpty()) {
        if (!afterList.isEmpty()) {
          for (WhiskRuleItem eachBefore : beforeList) {
            for (WhiskRuleItem eachAfter : afterList) {
              WhiskRule copy = rule.copy();
              TextRulerSlotPattern textRulerSlotPattern = copy.getPatterns()
                  .get(slotIndex);
              textRulerSlotPattern.preFillerPattern.add(eachBefore);
              textRulerSlotPattern.fillerPattern.add(WhiskRuleItem
                  .newWildCardItem(getElementIndex(copy, inside.get(0))));
              textRulerSlotPattern.postFillerPattern.add(eachAfter);
              tempRules.add(copy);
            }
          }
        } else {
          for (WhiskRuleItem eachBefore : beforeList) {
            WhiskRule copy = rule.copy();
            TextRulerSlotPattern textRulerSlotPattern = copy.getPatterns().get(
                slotIndex);
            textRulerSlotPattern.fillerPattern.add(WhiskRuleItem
                .newWildCardItem(getElementIndex(copy, inside.get(0))));
            textRulerSlotPattern.preFillerPattern.add(eachBefore);
            tempRules.add(copy);
          }
        }
      } else {
        for (WhiskRuleItem eachAfter : afterList) {
          WhiskRule copy = rule.copy();
          TextRulerSlotPattern textRulerSlotPattern = copy.getPatterns().get(
              slotIndex);
          textRulerSlotPattern.fillerPattern.add(WhiskRuleItem
              .newWildCardItem(getElementIndex(copy, inside.get(0))));
          textRulerSlotPattern.postFillerPattern.add(eachAfter);
          tempRules.add(copy);
        }
      }
      ArrayList<TextRulerRule> rules = new ArrayList<TextRulerRule>(tempRules);
      testRulesIfNotCached(rules);
      TextRulerRule best = null;
      for (TextRulerRule each : rules) {
        if (best == null) {
          best = each;
        } else {
          if (each.getCoveringStatistics().getCoveredPositivesCount() > best
              .getCoveringStatistics().getCoveredPositivesCount()) {
            best = each;
          }
        }
      }
      WhiskRule base2 = (WhiskRule) best;

      TextRulerToolkit.log("base1: " + base1.getRuleString());
      TextRulerToolkit.log("base2: " + base2.getRuleString());
      List<TextRulerRule> testRules = new ArrayList<TextRulerRule>();
      testRules.add(base1);
      testRules.add(base2);
      testRulesIfNotCached(testRules);
      if (shouldAbort())
        return null;
      TextRulerToolkit.log("\tbase1: " + base1.getCoveringStatistics()
          + " --> laplacian = " + base1.getLaplacian());
      TextRulerToolkit.log("\tbase2: " + base2.getCoveringStatistics()
          + " --> laplacian = " + base2.getLaplacian());
      if (base2.getCoveringStatistics().getCoveredPositivesCount() > base1
          .getCoveringStatistics().getCoveredPositivesCount())
        result.add(base2);
      else
        result.add(base1);
    }
    TextRulerRule best = null;
    for (TextRulerRule each : result) {
      if (best == null) {
        best = each;
      } else {
        if (each.getCoveringStatistics().getCoveredPositivesCount() > best
            .getCoveringStatistics().getCoveredPositivesCount()) {
          best = each;
        }
      }
    }

    return (WhiskRule) best;
  }

  private List<WhiskRuleItem> getTermsAfter(WhiskRuleItem whiskRuleItem,
      TextRulerExample example) {
    List<WhiskRuleItem> result = new ArrayList<WhiskRuleItem>();
    int end = whiskRuleItem.getWordConstraint().getTokenAnnotation().getEnd();
    CAS cas = example.getDocumentCAS();
    Type frameType = cas.getTypeSystem().getType(
        "de.uniwue.tm.textmarker.kernel.type.TextMarkerFrame");
    AnnotationFS pointer = cas.createAnnotation(frameType, end,
        Integer.MAX_VALUE);
    FSIterator iterator = cas.getAnnotationIndex().iterator(pointer);
    int nextBegin = -1;
    while (iterator.isValid()) {
      FeatureStructure fs = iterator.get();
      if (fs instanceof AnnotationFS) {
        AnnotationFS a = (AnnotationFS) fs;
        if (!filterSetWithSlotNames.contains(a.getType().getName())) {
          if (nextBegin == -1) {
            nextBegin = a.getBegin();
          }
          if (a.getBegin() <= nextBegin && a.getBegin() >= end) {
            WhiskRuleItem term = new WhiskRuleItem(new TextRulerAnnotation(a,
                example.getDocument()));
            result.add(term);
          }
        }
      }
      iterator.moveToNext();
    }
    return result;
  }

  private List<WhiskRuleItem> getTermsBefore(WhiskRuleItem whiskRuleItem,
      TextRulerExample example) {
    List<WhiskRuleItem> result = new ArrayList<WhiskRuleItem>();
    int begin = whiskRuleItem.getWordConstraint().getTokenAnnotation()
        .getBegin();
    CAS cas = example.getDocumentCAS();
    Type frameType = cas.getTypeSystem().getType(
        "de.uniwue.tm.textmarker.kernel.type.TextMarkerFrame");
    AnnotationFS pointer = cas.createAnnotation(frameType, begin, begin);
    FSIterator iterator = cas.getAnnotationIndex().iterator(pointer);
    int nextEnd = -1;

    // ???
    iterator.moveToPrevious();
    iterator.moveToPrevious();
    while (iterator.isValid()) {
      FeatureStructure fs = iterator.get();
      if (fs instanceof AnnotationFS) {
        AnnotationFS a = (AnnotationFS) fs;
        if (!filterSetWithSlotNames.contains(a.getType().getName())) {
          if (a.getEnd() > example.getAnnotation().getEnd()) {
            iterator.moveToPrevious();
            continue;
          }
          if (nextEnd == -1) {
            nextEnd = a.getEnd();
          }
          if (a.getEnd() >= nextEnd && a.getEnd() <= begin) {
            WhiskRuleItem term = new WhiskRuleItem(new TextRulerAnnotation(a,
                example.getDocument()));
            result.add(term);
          }
        }
      }
      iterator.moveToPrevious();
    }
    return result;
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
    if (params.containsKey(WINDOWSIZE_KEY))
      windowSize = (Integer) params.get(WINDOWSIZE_KEY);

    if (params.containsKey(ERROR_THRESHOLD_KEY))
      errorThreshold = (Float) params.get(ERROR_THRESHOLD_KEY);

    if (params.containsKey(POSTAG_ROOTTYPE_KEY))
      posTagRootTypeName = (String) params.get(POSTAG_ROOTTYPE_KEY);

  }

  public List<List<WhiskRuleItem>> getTermsWithinBounds(int startPos,
      int endPos, TextRulerExample example) {
    List<List<WhiskRuleItem>> result = new ArrayList<List<WhiskRuleItem>>();
    CAS cas = example.getDocumentCAS();
    Type frameType = cas.getTypeSystem().getType(
        "de.uniwue.tm.textmarker.kernel.type.TextMarkerFrame");
    AnnotationFS pointer = cas.createAnnotation(frameType, startPos, endPos);
    FSIterator iterator = cas.getAnnotationIndex().iterator(pointer);
    List<AnnotationFS> startAs = new ArrayList<AnnotationFS>();
    int firstBegin = -1;
    while (iterator.isValid()) {
      FeatureStructure fs = iterator.get();
      AnnotationFS a = (AnnotationFS) fs;

      // TODO change for multislot rules!
      if (a.getBegin() >= startPos && a.getEnd() <= endPos) {
        if (!filterSetWithSlotNames.contains(a.getType().getName())) {
          if (firstBegin == -1) {
            firstBegin = a.getBegin();
          }
          if (a.getBegin() == firstBegin)
            startAs.add(a);
        }
        iterator.moveToNext();
      } else {
        iterator.moveToNext();
      }
    }

    for (AnnotationFS annotation : startAs) {
      List<WhiskRuleItem> startList = new ArrayList<WhiskRuleItem>();
      WhiskRuleItem term = new WhiskRuleItem(new TextRulerAnnotation(
          annotation, example.getDocument()));
      startList.add(term);
      result.add(startList);
    }

    result = addFollowing(result, endPos, example);
    return result;
  }

  private List<List<WhiskRuleItem>> addFollowing(
      List<List<WhiskRuleItem>> lists, int till, TextRulerExample example) {
    List<List<WhiskRuleItem>> result = new ArrayList<List<WhiskRuleItem>>();
    for (List<WhiskRuleItem> list : lists) {
      WhiskRuleItem last = list.get(list.size() - 1);
      List<WhiskRuleItem> termsAfter = getTermsAfter(last, example);
      if (termsAfter.isEmpty()) {
        return lists;
      }
      for (WhiskRuleItem eachAfter : termsAfter) {
        if (eachAfter.getWordConstraint().getTokenAnnotation().getEnd() <= till) {
          List<WhiskRuleItem> newList = new ArrayList<WhiskRuleItem>();
          newList.addAll(list);
          newList.add(eachAfter);
          result.add(newList);
          result = addFollowing(result, till, example);
        } else {
          return lists;
        }
      }
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

  private int getElementIndex(WhiskRule proposedRule, WhiskRuleItem term) {
    if (term == null)
      return -1;
    int index = 0;
    int result = -1;
    for (TextRulerRuleItem i : proposedRule.getPatterns().get(0).preFillerPattern) {
      if (((WhiskRuleItem) i).equals(term)) {
        result = index;
      }
      index++;
    }
    for (TextRulerRuleItem i : proposedRule.getPatterns().get(0).fillerPattern) {
      if (((WhiskRuleItem) i).equals(term)) {
        result = index;
      }
      index++;
    }
    for (TextRulerRuleItem i : proposedRule.getPatterns().get(0).postFillerPattern) {
      if (((WhiskRuleItem) i).equals(term)) {
        result = index;
      }
      index++;
    }
    return result;
  }

  private boolean isNextValidNeighbor(WhiskRuleItem left, WhiskRuleItem right,
      TextRulerExample example) {
    CAS cas = example.getDocumentCAS();
    Type frameType = cas.getTypeSystem().getType(
        "de.uniwue.tm.textmarker.kernel.type.TextMarkerFrame");
    int begin = left.getWordConstraint().getTokenAnnotation().getEnd();
    int end = right.getWordConstraint().getTokenAnnotation().getBegin();
    AnnotationFS pointer = cas.createAnnotation(frameType, begin, end);
    FSIterator iterator = cas.getAnnotationIndex().iterator(pointer);
    while (iterator.isValid()) {
      FeatureStructure fs = iterator.get();
      AnnotationFS a = (AnnotationFS) fs;
      if (a.getBegin() >= begin && a.getEnd() <= end) {
        if (!filterSetWithSlotNames.contains(a.getType().getName())) {
          return false;
        }
      }
      iterator.moveToNext();
    }
    return true;
  }
}
