package org.apache.uima.tm.textruler.lp2;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textruler.core.TextRulerAnnotation;
import org.apache.uima.tm.textruler.core.TextRulerExample;
import org.apache.uima.tm.textruler.core.TextRulerRule;
import org.apache.uima.tm.textruler.core.TextRulerRuleList;
import org.apache.uima.tm.textruler.core.TextRulerStatisticsCollector;
import org.apache.uima.tm.textruler.core.TextRulerTarget;
import org.apache.uima.tm.textruler.core.TextRulerToolkit;
import org.apache.uima.tm.textruler.core.TextRulerTarget.MLTargetType;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerDelegate;
import org.apache.uima.tm.textruler.lp2.LP2RuleItem.MLLP2ContextConstraint;
import org.apache.uima.tm.textruler.lp2.LP2RuleItem.MLLP2OtherConstraint;
import org.apache.uima.util.FileUtils;


public class OptimizedLP2 extends BasicLP2 {

  public static final boolean SAVE_DEBUG_INFO_IN_TEMPFOLDER = false;

  private Map<String, TextRulerStatisticsCollector> cachedTestedStartRuleStatistics = new HashMap<String, TextRulerStatisticsCollector>();

  private long cachedTestedStartRuleStatisticsHitCounter = 0;

  public OptimizedLP2(String inputDir, String prePropTMFile, String tmpDir, String[] slotNames,
          Set<String> filterSet, TextRulerLearnerDelegate delegate) {
    super(inputDir, prePropTMFile, tmpDir, slotNames, filterSet, delegate);
  }

  @Override
  protected TextRulerRuleList learnTaggingRules(TextRulerTarget target,
          TextRulerRuleList contextualRules) {
    cachedTestedStartRuleStatisticsHitCounter = 0;
    cachedTestedStartRuleStatistics.clear();
    TextRulerRuleList result = super.learnTaggingRules(target, contextualRules);
    TextRulerToolkit
            .log("[OptimizedLP2.learnTaggingRules] saved rule testings due to start rule results cache: "
                    + cachedTestedStartRuleStatisticsHitCounter);
    TextRulerToolkit.log("[OptimizedLP2.learnTaggingRules] cacheSize at end of induction: "
            + cachedTestedStartRuleStatistics.size());
    cachedTestedStartRuleStatistics.clear();
    return result;
  }

  @Override
  protected void induceRulesFromExample(final TextRulerExample e, final int roundNumber) {
    // in order to get cache optimized contextual start rules testing, we
    // simply create
    // and add the ctxStartRules HERE (not when we want to create a
    // contextual rule!) to
    // all startRules, test those startRules + ctxStartRules cacheOptimized
    // on the trainings-Set
    // and then have their results in our covering-statistics-cache for
    // later ctx rules creation!
    List<LP2Rule> startRules = createStartRulesForExample(e);
    if (startRules.size() < 1)
      return;
    List<LP2Rule> ctxStartRules = new ArrayList<LP2Rule>();
    // TODO set isCorrectionRuleMode = true ?!
    if (!(e.getTarget().isLeftCorrection() || e.getTarget().isRightCorrection()))
      ctxStartRules = createContextStartRulesForStartRule(startRules.get(0));
    List<LP2Rule> rulesToTest = new ArrayList<LP2Rule>(ctxStartRules.size() + startRules.size());
    rulesToTest.addAll(startRules);
    rulesToTest.addAll(ctxStartRules);

    sendStatusUpdateToDelegate("Round " + roundNumber + " - Testing " + rulesToTest.size()
            + " start rules... " + " - uncovered examples: "
            + (examples.size() - coveredExamples.size() + " / " + examples.size()) + " cs: "
            + cachedTestedStartRuleStatistics.size(), TextRulerLearnerState.ML_RUNNING, false);
    testStartRulesIfNotCached(rulesToTest);
    if (shouldAbort())
      return;
    // int i=0;
    // for (LP2Rule r : startRules)
    // {
    // i++;
    // sendStatusUpdateToDelegate("Round "+roundNumber+" - Testing start rule "+i+"/"+startRules.size()+
    // "     - uncovered examples: "+
    // (examples.size()-coveredExamples.size() + " / "+examples.size())+
    // "cs:"+cachedTestedStartRuleStatistics.size(),
    // TextRulerLearnerState.ML_RUNNING, false);
    // testStartRuleIfNotCached(r);
    // if (shouldAbort())
    // return;
    // if (TextRulerToolkit.DEBUG)
    // {
    // if
    // (!r.getCoveringStatistics().getCoveredPositiveExamples().contains(e))
    // {
    // TextRulerToolkit.log("A START RULE MUST (!) COVER ITs POSITIVE EXAMPLE! OTHEREWISE, THERE IS SOMETHING WRONG!!");
    // }
    // }
    // }

    Comparator<LP2Rule> cmp = new Comparator<LP2Rule>() {

      public int compare(LP2Rule o1, LP2Rule o2) {
        return o1.getCoveringStatistics().getCoveredPositivesCount()
                - o2.getCoveringStatistics().getCoveredPositivesCount();
      }

    };
    // sort from low to high positive coverage in order to higher the
    // pruning probability while recursion:
    Collections.sort(startRules, cmp);
    Collections.sort(ctxStartRules, cmp);

    while ((startRules.size() > 0)
            && (startRules.get(0).getCoveringStatistics().getCoveredPositivesCount() < minCoveredPositives))
      startRules.remove(0);

    while ((ctxStartRules.size() > 0)
            && (ctxStartRules.get(0).getCoveringStatistics().getCoveredPositivesCount() < minCoveredPositives))
      ctxStartRules.remove(0);

    sendStatusUpdateToDelegate("Round " + roundNumber + " - Creating all generalizations..."
            + "     - uncovered examples: "
            + (examples.size() - coveredExamples.size() + " / " + examples.size()),
            TextRulerLearnerState.ML_RUNNING, false);

    // only for debugging purposes: List<LP2Rule> resultRules = new
    // ArrayList<LP2Rule>();

    ArrayList<LP2Rule> debugRuleCollector = null;

    if (TextRulerToolkit.DEBUG && SAVE_DEBUG_INFO_IN_TEMPFOLDER) {
      debugRuleCollector = new ArrayList<LP2Rule>();
    }

    if (!recursiveCreateAllRuleCombinations(startRules, ctxStartRules, 0, new ArrayList<LP2Rule>(),
            null, debugRuleCollector))
      return; // aborted!

    if (TextRulerToolkit.DEBUG && debugRuleCollector != null && SAVE_DEBUG_INFO_IN_TEMPFOLDER) {
      // TextRulerToolkit.log("all combinations: "+debugRuleCollector.size());

      Collections.sort(debugRuleCollector, new Comparator<LP2Rule>() {

        public int compare(LP2Rule o1, LP2Rule o2) {
          return o1.getRuleString().compareTo(o2.getRuleString());
        }

      });
      String startend = e.getTarget().type == MLTargetType.SINGLE_LEFT_BOUNDARY ? "left_"
              : "right_";
      File file = new File(tempDirectory() + startend + "generalizations" + roundNumber + ".tm");
      StringBuffer str = new StringBuffer();
      for (TextRulerRule rule : debugRuleCollector) {
        str.append(rule.getCoveringStatistics() + "\t\t" + rule.getRuleString() + "\n");
      }
      try {
        FileUtils.saveString2File(str.toString(), file);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
      // TextRulerToolkit.log("----");
    }
  }

  protected void testStartRulesIfNotCached(List<LP2Rule> startRules) {
    List<TextRulerRule> rulesToTest = new ArrayList<TextRulerRule>();

    for (LP2Rule r : startRules) {
      String key = r.getRuleString();
      if (cachedTestedStartRuleStatistics.containsKey(key)) {
        r.setCoveringStatistics(cachedTestedStartRuleStatistics.get(key).copy());
        cachedTestedStartRuleStatisticsHitCounter++;
      } else
        rulesToTest.add(r);
    }

    if (rulesToTest.size() > 0) {
      testRulesOnDocumentSet(rulesToTest, exampleDocuments);
      if (shouldAbort())
        return;
      for (TextRulerRule r : rulesToTest) {
        String key = r.getRuleString();
        cachedTestedStartRuleStatistics.put(key, r.getCoveringStatistics().copy());
      }
    }
  }

  protected LP2Rule combineRulesToOneRule(List<LP2Rule> ruleList,
          TextRulerStatisticsCollector covering) {
    // TextRulerToolkit.log("----------------------------------------------");
    // for (LP2Rule r : ruleList)
    // TextRulerToolkit.log(r+" ; "+r.getCoveringStatistics());

    LP2Rule rule = new LP2Rule(this, ruleList.get(0).getTarget());

    int maxPreCount = 0;
    int maxPostCount = 0;
    for (LP2Rule r : ruleList) {
      if (r.getPreFillerPattern().size() > maxPreCount)
        maxPreCount = r.getPreFillerPattern().size();
      if (r.getPostFillerPattern().size() > maxPostCount)
        maxPostCount = r.getPostFillerPattern().size();
    }

    for (int i = 0; i < maxPreCount; i++) {
      LP2RuleItem newItem = new LP2RuleItem();
      for (LP2Rule r : ruleList)
        if (i < r.getPreFillerPattern().size()) {
          LP2RuleItem rItem = (LP2RuleItem) r.getPreFillerPattern().get(
                  r.getPreFillerPattern().size() - i - 1);
          if (rItem.getWordConstraint() != null)
            newItem.setWordConstraint(rItem.getWordConstraint().copy());
          if (rItem.getContextConstraint() != null)
            newItem.setContextConstraint(rItem.getContextConstraint().copy());
          // for (String key : rItem.getOtherConstraints().keySet())
          // newItem.setOtherConstraint(key,
          // rItem.getOtherConstraints().get(key).copy());
          for (MLLP2OtherConstraint c : rItem.getOtherConstraints())
            newItem.addOtherConstraint(c.copy());

        }
      rule.addPreFillerItem(newItem);
    }

    for (int i = 0; i < maxPostCount; i++) {
      LP2RuleItem newItem = new LP2RuleItem();
      for (LP2Rule r : ruleList)
        if (i < r.getPostFillerPattern().size()) {
          LP2RuleItem rItem = (LP2RuleItem) r.getPostFillerPattern().get(i);
          if (rItem.getWordConstraint() != null)
            newItem.setWordConstraint(rItem.getWordConstraint().copy());
          if (rItem.getContextConstraint() != null)
            newItem.setContextConstraint(rItem.getContextConstraint().copy());
          // for (String key : rItem.getOtherConstraints().keySet())
          // newItem.setOtherConstraint(key,
          // rItem.getOtherConstraints().get(key).copy());
          for (MLLP2OtherConstraint c : rItem.getOtherConstraints())
            newItem.addOtherConstraint(c.copy());
        }
      rule.addPostFillerItem(newItem);
    }

    rule.setCoveringStatistics(covering.copy());

    return rule;
  }

  protected boolean recursiveCreateAllRuleCombinations(final List<LP2Rule> startRules,
          final List<LP2Rule> ctxStartRules, final int index, final List<LP2Rule> currentRuleTuple,
          final TextRulerStatisticsCollector currentCovering, final List<LP2Rule> debugRuleCollector) {
    if (index > startRules.size() - 1) {
      if (shouldAbort())
        return false;
      if (currentRuleTuple.size() > 0) {
        LP2Rule newRule = createAndCheckRuleFromStartRules(currentRuleTuple, currentCovering,
                ctxStartRules);
        if (debugRuleCollector != null)
          debugRuleCollector.add(newRule);
      }
    } else {
      // recurse WITHOUT and WITH this start rule:
      if (!recursiveCreateAllRuleCombinations(startRules, ctxStartRules, index + 1,
              currentRuleTuple, currentCovering, debugRuleCollector))
        return false;

      // only recurse WITH this rule, if the constraint it adds does
      // really create a new rule!
      // if a word constraint of the same item is already present, we do
      // not need to add any other constraint of that
      // token, since it does not make sense to add more constraints to
      // the obviously most specific rule !

      LP2Rule candidateRule = startRules.get(index);
      boolean isPre = candidateRule.isPreFillerStartRule();
      boolean containsWordConstraint = false;
      for (LP2Rule r : currentRuleTuple)
        if (r.isPreFillerStartRule() == isPre) {
          if (isPre)
            containsWordConstraint = r.getPreFillerPattern().size() == candidateRule
                    .getPreFillerPattern().size();
          else
            containsWordConstraint = r.getPostFillerPattern().size() == candidateRule
                    .getPostFillerPattern().size();
          if (containsWordConstraint)
            break;
        }
      if (!containsWordConstraint) {
        // and calculate intersection of coverings:
        TextRulerStatisticsCollector newCovering;
        if (currentCovering != null)
          newCovering = getCoveringIntersection(currentCovering, candidateRule
                  .getCoveringStatistics());
        else
          newCovering = candidateRule.getCoveringStatistics();

        // prune all rules that go below our minCoveredPositives
        // threshold!
        if (newCovering.getCoveredPositivesCount() >= minCoveredPositives) {
          // add rule to configuration tuple
          currentRuleTuple.add(candidateRule);

          if (!recursiveCreateAllRuleCombinations(startRules, ctxStartRules, index + 1,
                  currentRuleTuple, newCovering, debugRuleCollector))
            return false;
          currentRuleTuple.remove(currentRuleTuple.size() - 1);
        }
      }
    }
    return true;
  }

  protected TextRulerStatisticsCollector getCoveringIntersection(
          final TextRulerStatisticsCollector c1, final TextRulerStatisticsCollector c2) {
    // calculate intersections of coverings:
    TextRulerStatisticsCollector resultC = new TextRulerStatisticsCollector(c1);

    resultC.getCoveredPositiveExamples().retainAll(c2.getCoveredPositiveExamples());
    resultC.getCoveredNegativeExamples().retainAll(c2.getCoveredNegativeExamples());
    resultC.reflectCountsFromCoveredExamples();

    return resultC;
  }

  protected LP2Rule createAndCheckRuleFromStartRules(final List<LP2Rule> startRules,
          final TextRulerStatisticsCollector covering, final List<LP2Rule> ctxStartRules) {
    LP2Rule newRule = combineRulesToOneRule(startRules, covering);
    // TextRulerToolkit.log("COMBINED RULE: "+newRule.getRuleString()+" ; "+newRule.getCoveringStatistics());
    boolean tooFewPositives = newRule.getCoveringStatistics().getCoveredPositivesCount() < minCoveredPositives;
    boolean tooManyErrors = newRule.getErrorRate() > maxErrorThreshold;
    boolean isBestRule = !(tooFewPositives || tooManyErrors);

    if (TextRulerToolkit.DEBUG && SAVE_DEBUG_INFO_IN_TEMPFOLDER)
      TextRulerToolkit.appendStringToFile(tempDirectory() + "bestcandidates.tm", newRule
              .getRuleString()
              + "\n");

    if (isBestRule) {
      currentBestRules.add(newRule);
      currentBestRules.removeSubsumedRules();
      currentBestRules.cutToMaxSize();
    } else if (!tooFewPositives && (ctxStartRules.size() > 0)) {
      // new: use precalculated ctx startrules:
      for (LP2Rule ctxStartRule : ctxStartRules) {

        MLLP2ContextConstraint ctxConstraint = ctxStartRule.getMarkingRuleItem()
                .getContextConstraint();
        LP2Rule newCTXRule = newRule.copy();
        newCTXRule.setIsContextualRule(true);
        newCTXRule.getMarkingRuleItem().setContextConstraint(ctxConstraint.copy());
        newCTXRule.setNeedsCompile(true);
        newCTXRule.compileRuleString();
        newCTXRule.setCoveringStatistics(getCoveringIntersection(newRule.getCoveringStatistics(),
                ctxStartRule.getCoveringStatistics()));
        // if
        // (newCTXRule.getCoveringStatistics().getCoveredPositivesCount()
        // < 1)
        // {
        // TextRulerToolkit.log("ERROR!");
        // }

        boolean ctxTooFewPositives = newCTXRule.getCoveringStatistics().getCoveredPositivesCount() < minCoveredPositives;
        boolean ctxTooManyErrors = newCTXRule.getErrorRate() > maxErrorThreshold;
        boolean isGoodCTXRule = !(ctxTooFewPositives || ctxTooManyErrors);
        // TextRulerToolkit.log("CTXRULE :  "+newCTXRule.getRuleString()
        // + " ; "+newCTXRule.getCoveringStatistics());

        if (TextRulerToolkit.DEBUG && SAVE_DEBUG_INFO_IN_TEMPFOLDER)
          TextRulerToolkit.appendStringToFile(tempDirectory() + "ctxcandidates.tm", newCTXRule
                  .getRuleString()
                  + "\n");

        if (isGoodCTXRule) {
          currentContextualRules.add(newCTXRule);
          currentContextualRules.removeSubsumedRules();
          currentContextualRules.cutToMaxSize();
        }
      }
    }

    return newRule;
  }

  protected LP2Rule createStartRuleForConstraint(final TextRulerTarget target,
          final int contextSize, final boolean isLeftContext, final LP2RuleItem constraintItem) {
    LP2Rule newRule = new LP2Rule(this, target);

    // add contextSize-1 ANY items
    for (int j = 0; j < contextSize - 1; j++)
      if (isLeftContext)
        newRule.addPreFillerItem(new LP2RuleItem());
      else
        newRule.addPostFillerItem(new LP2RuleItem());

    // add 1 constraint item:
    if (isLeftContext)
      newRule.addPreFillerItem(constraintItem);
    else
      newRule.addPostFillerItem(constraintItem);

    // if we are building the left context start rules for LEFT BOUNDARIES,
    // we need at least ONE
    // empty ANY item as the marking item:
    if (isLeftContext
            && (target.type == MLTargetType.SINGLE_LEFT_BOUNDARY || target.type == MLTargetType.SINGLE_LEFT_CORRECTION))
      newRule.addPostFillerItem(new LP2RuleItem());
    // otherwise, if we build the right context rules for RIGHT BOUNDARY
    // RULES, we need at least ONE
    // empty ANY item on the LEFT as the marking item:
    else if (!isLeftContext
            && (target.type == MLTargetType.SINGLE_RIGHT_BOUNDARY || target.type == MLTargetType.SINGLE_RIGHT_CORRECTION))
      newRule.addPreFillerItem(new LP2RuleItem());

    newRule.setIsPreFillerStartRule(isLeftContext);
    // if (isLeftContext)
    // newRule.setStartRuleItemIndex(0);
    // else
    // newRule.setStartRuleItemIndex(newRule.getPreFillerPattern().size()+newRule.getPostFillerPattern().size()-1);
    return newRule;
  }

  protected List<LP2Rule> createContextStartRulesForStartRule(final LP2Rule aStartRule) {
    List<LP2Rule> result = new ArrayList<LP2Rule>();

    // TODO make all other tags contextual tags here. for now we take only
    // the counterpart
    // tag of the current learning process: (opening/closing tags)

    LP2RuleItem ctxItem = new LP2RuleItem();
    MLLP2ContextConstraint ctxConstraint = new MLLP2ContextConstraint(slotMaximumTokenCount,
            aStartRule);
    ctxItem.setContextConstraint(ctxConstraint);
    LP2Rule ctxStartRule = new LP2Rule(this, aStartRule.getTarget());
    ctxStartRule.setIsContextualRule(true);
    if (aStartRule.getTarget().type == MLTargetType.SINGLE_LEFT_BOUNDARY)
      ctxStartRule.addPostFillerItem(ctxItem);
    else
      ctxStartRule.addPreFillerItem(ctxItem);
    result.add(ctxStartRule);
    return result;
  }

  protected List<LP2Rule> createStartRulesForExample(final TextRulerExample example) {
    TextRulerTarget target = example.getTarget();
    List<LP2Rule> result = new ArrayList<LP2Rule>();
    CAS docCas = example.getDocumentCAS();
    TextRulerAnnotation exampleAnnotation = example.getAnnotation();
    TypeSystem ts = docCas.getTypeSystem();
    Type tokensRootType = ts.getType(TextRulerToolkit.TM_ANY_TYPE_NAME);

    boolean isLeftBoundary = (target.type == MLTargetType.SINGLE_LEFT_BOUNDARY || target.type == MLTargetType.SINGLE_LEFT_CORRECTION);
    int thePosition = isLeftBoundary ? exampleAnnotation.getBegin() : exampleAnnotation.getEnd();
    List<AnnotationFS> leftContext = TextRulerToolkit.getAnnotationsBeforePosition(docCas,
            thePosition, windowSize, TextRulerToolkit.getFilterSetWithSlotNames(slotNames,
                    filterSet), tokensRootType);

    List<AnnotationFS> rightContext;
    if (target.type == MLTargetType.SINGLE_LEFT_CORRECTION
            || target.type == MLTargetType.SINGLE_RIGHT_CORRECTION) {
      rightContext = TextRulerToolkit.getAnnotationsAfterPosition(docCas, thePosition,
              windowSize + 1, TextRulerToolkit.getFilterSetWithSlotNames(slotNames, filterSet),
              tokensRootType);
      rightContext.remove(0);
    } else {
      rightContext = TextRulerToolkit.getAnnotationsAfterPosition(docCas, thePosition, windowSize,
              TextRulerToolkit.getFilterSetWithSlotNames(slotNames, filterSet), tokensRootType);
    }

    int totalCount = leftContext.size() + rightContext.size();

    // LEFT CONTEXT (PRE FILLER PATTERN)
    // result.add(createStartRuleForConstraint(example, 0, true, null));

    for (int index = 0; index < totalCount; index++) {
      boolean isPre = index < leftContext.size();
      int prePostIndex = isPre ? index : index - leftContext.size();
      AnnotationFS tokenAFS = isPre ? leftContext.get(leftContext.size() - 1 - prePostIndex)
              : rightContext.get(prePostIndex);
      TextRulerAnnotation tokenAnnotation = new TextRulerAnnotation(tokenAFS, example.getDocument());
      LP2RuleItem wordItem = new LP2RuleItem();

      // one rule with only the word constraint:
      wordItem.setWordConstraint(tokenAnnotation);
      result.add(createStartRuleForConstraint(example.getTarget(), prePostIndex + 1, isPre,
              wordItem));

      if (wordItem.getWordConstraint().isRegExpConstraint()) {
        LP2RuleItem basicItem = new LP2RuleItem();
        // basicItem.setOtherConstraint("basicTM", new
        // MLLP2OtherConstraint(tokenAnnotation, tokenAnnotation));
        basicItem.addOtherConstraint(new MLLP2OtherConstraint(tokenAnnotation, tokenAnnotation));
        result.add(createStartRuleForConstraint(example.getTarget(), prePostIndex + 1, isPre,
                basicItem));
      }

      // // POS-Tags created by our test hmm tagger.
      // Type posTagsRootType = ts.getType("de.uniwue.ml.ML.postag");
      // if (posTagsRootType != null)
      // {
      // List<AnnotationFS> posTagAnnotations =
      // TextRulerToolkit.getAnnotationsWithinBounds(example.getDocumentCAS(),
      // tokenAnnotation.getBegin(), tokenAnnotation.getEnd(), null,
      // posTagsRootType);
      // if (posTagAnnotations.size()>0)
      // {
      // if (TextRulerToolkit.DEBUG && posTagAnnotations.size()>1)
      // {
      // TextRulerToolkit.logIfDebug("HOW CAN ONE TOKEN HAVE MORE THAN ONE POS TAG ?? "+tokenAnnotation.getBegin()+":"+tokenAnnotation.getEnd()+"="+tokenAnnotation.getCoveredText());
      // for (AnnotationFS afs : posTagAnnotations)
      // {
      // System.out.print(afs.getType().getShortName()+":"+afs.getCoveredText()+" "+afs.getBegin()+":"+afs.getEnd()+"\n");
      // }
      // TextRulerToolkit.logIfDebug("");
      // }
      //					
      // TextRulerAnnotation posTagAnnotation = new
      // TextRulerAnnotation(posTagAnnotations.get(0),
      // example.getDocument());
      // LP2RuleItem basicItem = new LP2RuleItem();
      // basicItem.setOtherConstraint("postag", new
      // MLLP2OtherConstraint(posTagAnnotation, posTagAnnotation));
      // result.add(createStartRuleForConstraint(example.getTarget(),
      // prePostIndex+1, isPre, basicItem));
      // }
      // }

      // new dynamic system: grab everything we get from the annotation
      // index that lies over this token:
      // (annotations WITHIN (with smaller bounds than the token itself)
      // are ignored for now! we could
      // add using them with the CONTAINS constraint. but our
      // MLLP2OtherConstraint is not yet capable of this!

      List<AnnotationFS> featureAnnotations = TextRulerToolkit.getNonTMAnnoationsOverToken(docCas,
              tokenAFS, filterSetWithSlotNames);
      if (TextRulerToolkit.DEBUG && featureAnnotations.size() > 1) {
        TextRulerToolkit.log("FOUND MORE THAN ONE EXTRA TOKEN FEATURE ANNOTATION !");
        for (AnnotationFS featA : featureAnnotations)
          TextRulerToolkit.log(featA.toString());
        TextRulerToolkit.log("--------------------------------");
      }
      for (AnnotationFS featA : featureAnnotations) {
        TextRulerAnnotation featureAnnot = new TextRulerAnnotation(featA, example.getDocument());
        LP2RuleItem basicItem = new LP2RuleItem();
        basicItem.addOtherConstraint(new MLLP2OtherConstraint(tokenAnnotation, featureAnnot));
        result.add(createStartRuleForConstraint(example.getTarget(), prePostIndex + 1, isPre,
                basicItem));
      }

    }

    // for (TextRulerRule r : result)
    // {
    // TextRulerToolkit.log("STARTRULE = "+r.getRuleString());
    // }

    return result;
  }

  @Override
  public boolean collectNegativeCoveredInstancesWhenTesting() {
    return true;
  }

}
