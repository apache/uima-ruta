package org.apache.uima.tm.textruler.rapier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textruler.core.TextRulerAnnotation;
import org.apache.uima.tm.textruler.core.TextRulerBasicLearner;
import org.apache.uima.tm.textruler.core.TextRulerExample;
import org.apache.uima.tm.textruler.core.TextRulerRule;
import org.apache.uima.tm.textruler.core.TextRulerRuleItem;
import org.apache.uima.tm.textruler.core.TextRulerRuleList;
import org.apache.uima.tm.textruler.core.TextRulerRulePattern;
import org.apache.uima.tm.textruler.core.TextRulerStatisticsCollector;
import org.apache.uima.tm.textruler.core.TextRulerTarget;
import org.apache.uima.tm.textruler.core.TextRulerToolkit;
import org.apache.uima.tm.textruler.core.TextRulerWordConstraint;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerDelegate;


public class Rapier extends TextRulerBasicLearner {

  public final static String COMPRESSION_FAIL_MAX_COUNT_KEY = "compressionFailMaxCount";

  public final static String RULELIST_SIZE_KEY = "ruleListSize";

  public final static String PAIR_COUNT_KEY = "pairCount";

  public final static String LIM_NO_IMPROVEMENTS_KEY = "limNoImprovements";

  public final static String NOISE_THESHOLD_KEY = "noiseThreshold";

  public final static String POSTAG_ROOTTYPE_KEY = "posTagRootType";

  public final static String MIN_COVERED_POSITIVES_KEY = "minCoveredPositives";

  public final static String USE_ALL_GENSETS_AT_SPECIALIZATION_KEY = "useAllGenSetsAtSpecialization";

  public final static int STANDARD_COMPRESSION_FAIL_MAX_COUNT = 3;

  public final static int STANDARD_RULELIST_SIZE = 50;

  public final static int STANDARD_PAIR_COUNT = 4;

  public final static int STANDARD_LIM_NO_IMPROVEMENTS = 3;

  public final static float STANDARD_NOISE_THREHSOLD = 0.9f;

  public final static String STANDARD_POSTAG_ROOTTYPE = "de.uniwue.ml.ML.postag";

  public final static int STANDARD_MIN_COVERED_POSITIVES = 1;

  public final static boolean STANDARD_USE_ALL_GENSETS_AT_SPECIALIZATION = true;

  private int compressionFailMaxCount = STANDARD_COMPRESSION_FAIL_MAX_COUNT;

  private int ruleListSize = STANDARD_RULELIST_SIZE;

  private int pairCount = STANDARD_PAIR_COUNT;

  private int limNoImprovements = STANDARD_LIM_NO_IMPROVEMENTS;

  private float noiseThreshold = STANDARD_NOISE_THREHSOLD;

  private String posTagRootTypeName = STANDARD_POSTAG_ROOTTYPE;

  private int minCoveredPositives = STANDARD_MIN_COVERED_POSITIVES;

  private boolean useAllGenSetsAtSpecialization = STANDARD_USE_ALL_GENSETS_AT_SPECIALIZATION;

  private Map<String, TextRulerStatisticsCollector> cachedTestedRuleStatistics = new HashMap<String, TextRulerStatisticsCollector>();

  private int initialRuleBaseSize;

  private List<TextRulerExample> examples;

  private TextRulerRuleList slotRules;

  private RapierRulePriorityQueue ruleList;

  private String currentSlotName;

  public Rapier(String inputDir, String prePropTMFile, String tmpDir, String[] slotNames,
          Set<String> filterSet, TextRulerLearnerDelegate delegate) {
    super(inputDir, prePropTMFile, tmpDir, slotNames, filterSet, delegate);
  }

  @Override
  protected void doRun() {
	for(int i = 0; i < slotNames.length; i++) {
		int compressionFailCount = 0;
		
		// only working for one slot yet !
		currentSlotName = slotNames[i];
		cachedTestedRuleStatistics.clear();
		exampleDocuments.createExamplesForTarget(new TextRulerTarget(currentSlotName, this));
		examples = exampleDocuments.getAllPositiveExamples();
		
		if (shouldAbort())
		  return;
		
		slotRules = new TextRulerRuleList();
		ruleList = new RapierRulePriorityQueue(ruleListSize);
		
		TextRulerToolkit.log("--- RAPIER START for Slot " + currentSlotName);
		
		sendStatusUpdateToDelegate("Creating initial rule base...",
		        TextRulerLearnerState.ML_INITIALIZING, false);
		
		fillSlotRulesWithMostSpecificRules();
		
		updateCompressionStatusString();
		
		if (TextRulerToolkit.DEBUG) {
		  slotRules.saveToRulesFile(getIntermediateRulesFileName(), getTMFileHeaderString());
		}
		
		while (compressionFailCount < compressionFailMaxCount) {
		  TextRulerToolkit.log("***** NEW COMPRESSION ROUND; FailCount = " + compressionFailCount);
		  if (shouldAbort()) {
		    return;
		  }
		
		  RapierRule bestRule = findNewRule();
		  if (bestRule != null
		          && (bestRule.getCoveringStatistics().getCoveredPositivesCount() >= minCoveredPositives)
		          && (bestRule.noiseValue() >= noiseThreshold) && (!slotRules.contains(bestRule))) {
		    addRuleAndRemoveEmpiricallySubsumedRules(bestRule);
		    if (TextRulerToolkit.DEBUG)
		      slotRules.saveToRulesFile(getIntermediateRulesFileName(), getTMFileHeaderString());
		  } else {
		    compressionFailCount++;
		  }
		}
		
		if (TextRulerToolkit.DEBUG) {
		  slotRules.saveToRulesFile(getIntermediateRulesFileName(), getTMFileHeaderString());
}
	}

    sendStatusUpdateToDelegate("Done", TextRulerLearnerState.ML_DONE, true);
    cachedTestedRuleStatistics.clear();
    TextRulerToolkit.log("--- RAPIER END");

  }

  private void updateCompressionStatusString() {
    double percent = Math.round((slotRules.size() / (double) initialRuleBaseSize) * 100.0);
    sendStatusUpdateToDelegate("Compressing... (Rules = " + slotRules.size() + "/"
            + initialRuleBaseSize + "  = " + percent + " % ratio)",
            TextRulerLearnerState.ML_RUNNING, true);
    // TODO also show round numbers and compression fail count and such
    // things!
  }

  private void addAvailablePosTagConstraintToItem(RapierRuleItem item,
          AnnotationFS tokenAnnotation, TextRulerExample example) {

    if (posTagRootTypeName != null && posTagRootTypeName.length() > 0) {
      CAS cas = example.getDocumentCAS();
      TypeSystem ts = cas.getTypeSystem();
      Type posTagsRootType = ts.getType(posTagRootTypeName);
      if (ts != null) {
        List<AnnotationFS> posTagAnnotations = TextRulerToolkit.getAnnotationsWithinBounds(cas,
                tokenAnnotation.getBegin(), tokenAnnotation.getEnd(), null, posTagsRootType);
        if (posTagAnnotations.size() > 0) {
          AnnotationFS posTag = posTagAnnotations.get(0);
          if (posTag.getBegin() == tokenAnnotation.getBegin()
                  && posTag.getEnd() == tokenAnnotation.getEnd())
            item.addTagConstraint(posTag.getType().getShortName());
        }
      }
    }
  }

  private void fillSlotRulesWithMostSpecificRules() {
    slotRules.clear();
    for (TextRulerExample example : examples) {
      RapierRule rule = new RapierRule(this, example.getTarget());
      TextRulerAnnotation slotAnnotation = example.getAnnotation();
      CAS docCas = example.getDocumentCAS();
      TypeSystem ts = docCas.getTypeSystem();
      Type tokensRootType = ts.getType(TextRulerToolkit.TM_ANY_TYPE_NAME);

      // first, get all words/tokens:
      List<AnnotationFS> before = TextRulerToolkit.getAnnotationsBeforePosition(example
              .getDocumentCAS(), slotAnnotation.getBegin(), -1, TextRulerToolkit
              .getFilterSetWithSlotNames(slotNames, filterSet), tokensRootType);
      List<AnnotationFS> after = TextRulerToolkit.getAnnotationsAfterPosition(example
              .getDocumentCAS(), slotAnnotation.getEnd(), -1, TextRulerToolkit
              .getFilterSetWithSlotNames(slotNames, filterSet), tokensRootType);
      List<AnnotationFS> inside = TextRulerToolkit.getAnnotationsWithinBounds(example
              .getDocumentCAS(), slotAnnotation.getBegin(), slotAnnotation.getEnd(),
              TextRulerToolkit.getFilterSetWithSlotNames(slotNames, filterSet), tokensRootType);

      // the before annotations have to be reversed:
      for (int i = before.size() - 1; i >= 0; i--) {
        AnnotationFS afs = before.get(i);
        RapierRuleItem ruleItem = new RapierRuleItem();
        ruleItem.addWordConstraint(new TextRulerWordConstraint(new TextRulerAnnotation(afs, example
                .getDocument())));
        addAvailablePosTagConstraintToItem(ruleItem, afs, example);
        rule.addPreFillerItem(ruleItem);
      }

      for (AnnotationFS afs : inside) {
        RapierRuleItem ruleItem = new RapierRuleItem();
        ruleItem.addWordConstraint(new TextRulerWordConstraint(new TextRulerAnnotation(afs, example
                .getDocument())));
        addAvailablePosTagConstraintToItem(ruleItem, afs, example);
        rule.addFillerItem(ruleItem);
      }
      for (AnnotationFS afs : after) {
        RapierRuleItem ruleItem = new RapierRuleItem();
        ruleItem.addWordConstraint(new TextRulerWordConstraint(new TextRulerAnnotation(afs, example
                .getDocument())));
        addAvailablePosTagConstraintToItem(ruleItem, afs, example);
        rule.addPostFillerItem(ruleItem);
      }

      // TextRulerToolkit.log("RULE: "+rule.getRuleString());
      // testRuleOnTrainingsSet(rule, exampleDocuments.getDocuments());

      // this rule has to at least cover its seed example!!
      TextRulerStatisticsCollector c = new TextRulerStatisticsCollector();
      c.addCoveredPositive(example);
      rule.setCoveringStatistics(c);
      slotRules.add(rule);
    }
    initialRuleBaseSize = slotRules.size();
  }

  protected void addRuleAndRemoveEmpiricallySubsumedRules(RapierRule rule) {
    if (!slotRules.contains(rule)) {
      List<TextRulerRule> rulesToRemove = new ArrayList<TextRulerRule>();
      Set<TextRulerExample> coveredExamples = rule.getCoveringStatistics()
              .getCoveredPositiveExamples();
      for (TextRulerRule r : slotRules) {
        if (coveredExamples.containsAll(r.getCoveringStatistics().getCoveredPositiveExamples()))
          rulesToRemove.add(r);
      }
      for (TextRulerRule removeR : rulesToRemove)
        slotRules.remove(removeR);
      slotRules.add(rule);
      updateCompressionStatusString();
    }
  }

  protected RapierRule findNewRule() {
    Random rand = new Random(System.currentTimeMillis());

    Set<RapierRule> generalizations = new HashSet<RapierRule>();
    // 0. initialization
    ruleList.clear();

    if (slotRules.size() <= 1)
      return null;

    List<RapierRule> uncompressedRules = new ArrayList<RapierRule>();
    for (TextRulerRule r : slotRules) {
      if (((RapierRule) r).isInitialRule())
        uncompressedRules.add((RapierRule) r);
    }

    // 1. get generalizations of the two slot filler patterns:

    // create pairs and prefer still uncompressed rules when choosing
    // "randomly":
    int pairsLeft = pairCount;
    if (uncompressedRules.size() == 1) {
      RapierRule rule1 = uncompressedRules.get(0);
      RapierRule rule2 = null;
      while (rule2 == null || rule1 == rule2) {
        rule2 = (RapierRule) slotRules.get(rand.nextInt(slotRules.size()));
      }
      generalizations.addAll(getFillerGeneralizationsForRulePair(rule1, rule2));
      if (shouldAbort())
        return null;
      pairsLeft--;
    } else if (uncompressedRules.size() == 2) {
      RapierRule rule1 = uncompressedRules.get(0);
      RapierRule rule2 = uncompressedRules.get(1);
      generalizations.addAll(getFillerGeneralizationsForRulePair(rule1, rule2));
      if (shouldAbort())
        return null;
      pairsLeft--;
    } else if (uncompressedRules.size() > 2) {
      int uPairCount = pairCount;
      if (uPairCount > uncompressedRules.size())
        uPairCount /= 2;
      for (int i = 0; i < uPairCount; i++) {
        RapierRule rule1 = uncompressedRules.get(rand
                .nextInt(uncompressedRules.size()));
        RapierRule rule2 = null;
        while (rule2 == null || rule1 == rule2) {
          rule2 = uncompressedRules.get(rand.nextInt(uncompressedRules.size()));
        }
        generalizations.addAll(getFillerGeneralizationsForRulePair(rule1, rule2));
        pairsLeft--;
      }
    }

    for (int i = 0; i < pairsLeft; i++) {
      // TODO optimize !! don't call the machinery with the same rule pair
      // two times in one session !!!
      // randomly pick two rules:
      RapierRule rule1 = (RapierRule) slotRules.get(rand.nextInt(slotRules.size()));
      RapierRule rule2 = null;
      while (rule2 == null || rule1 == rule2) {
        rule2 = (RapierRule) slotRules.get(rand.nextInt(slotRules.size()));
      }
      generalizations.addAll(getFillerGeneralizationsForRulePair(rule1, rule2));

      if (shouldAbort())
        return null;
    }

    // if (TextRulerToolkit.DEBUG)
    // {
    // TextRulerToolkit.log("Rule Generalizations created: " +
    // generalizations.size());
    // for (RapierRule newRule : generalizations)
    // TextRulerToolkit.log("Rule = "+newRule.getRuleString());
    // }

    // 2. evaluate an enque to priority list:
    List<RapierRule> testRules = new ArrayList<RapierRule>(generalizations);

    for (RapierRule r : testRules) {
      r.combineSenselessPatternListItems();
    }

    testRulesIfNotCached(testRules);
    if (shouldAbort())
      return null;

    for (RapierRule newRule : generalizations) {
      if (TextRulerToolkit.DEBUG) {
        if (!RapierDebugHelper.debugCheckIfRuleCoversItsSeedRuleCoverings(newRule)) {
          TextRulerToolkit
                  .log("------------------------------------------------------------------------------------------");
          TextRulerToolkit
                  .log("ERROR, A RULE HAS TO COVER AT LEAST EVERY POSITIVE EXAMPLE OF ITS TWO SEED RULES!!!");
          TextRulerToolkit.log("\t RULE: " + newRule.getRuleString());
          TextRulerToolkit.log("\t Parent1: " + newRule.getParent1().getRuleString());
          TextRulerToolkit.log("\t Parent2: " + newRule.getParent2().getRuleString());
          TextRulerToolkit.log("--------");
          TextRulerToolkit.log("+RuleCovering: "
                  + newRule.getCoveringStatistics().getCoveredPositiveExamples());
          TextRulerToolkit.log("+P1Covering  : "
                  + newRule.getParent1().getCoveringStatistics().getCoveredPositiveExamples());
          TextRulerToolkit.log("+P2Covering  : "
                  + newRule.getParent2().getCoveringStatistics().getCoveredPositiveExamples());

        }
      }
      ruleList.add(newRule);
    }

    // 3. specialize pre and post fillers:
    int n = 0;
    double bestValue = Double.MAX_VALUE;
    int noImprovementCounter = 0;
    while (true) {
      n++;
      TextRulerToolkit.log(" --- NEW SPECIALIZATOIN ROUND; n = " + n + "  noImprovementCounter = "
              + noImprovementCounter);
      List<RapierRule> newRuleList = new ArrayList<RapierRule>();
      for (RapierRule curRule : ruleList) {

        List<RapierRule> specTestRules = new ArrayList<RapierRule>(specializePreFiller(curRule, n));

        for (RapierRule r : specTestRules)
          r.combineSenselessPatternListItems();

        testRulesIfNotCached(specTestRules);
        if (shouldAbort())
          return null;

        for (RapierRule r : specTestRules)
          newRuleList.add(r);
      }
      ruleList.addAll(newRuleList);

      newRuleList.clear();
      for (RapierRule curRule : ruleList) {

        List<RapierRule> specTestRules = new ArrayList<RapierRule>(specializePostFiller(curRule, n));

        for (RapierRule r : specTestRules)
          r.combineSenselessPatternListItems();

        testRulesIfNotCached(specTestRules);
        if (shouldAbort())
          return null;

        for (RapierRule r : specTestRules)
          newRuleList.add(r);
      }
      ruleList.addAll(newRuleList);

      RapierRule bestRule = ruleList.peek();

      if (TextRulerToolkit.DEBUG) {
        // for (RapierRule r: ruleList)
        // TextRulerToolkit.log("value="+r.getPriority()+" rule = "+r.getRuleString());
        TextRulerToolkit.log("------------------------------------");
        TextRulerToolkit.log("BEST RULE FOR THIS SESSION: " + bestRule.getCoveringStatistics());
        TextRulerToolkit.log(bestRule.getRuleString());
        TextRulerToolkit.log("------------------------------------");
      }
      if (bestRule.producesOnlyValidFillers())
        break; // todo: horizon effects ??

      if (bestRule.getPriority() < bestValue) {
        noImprovementCounter = 0;
        bestValue = bestRule.getPriority();
      } else {
        noImprovementCounter++;
        if (noImprovementCounter > limNoImprovements)
          break;
      }
    }

    RapierRule bestRule = ruleList.peek();
    return bestRule;
  }

  private List<RapierRule> getFillerGeneralizationsForRulePair(RapierRule rule1, RapierRule rule2) {
    TextRulerToolkit
            .log("------------------------------------------------------------------------------------------");
    TextRulerToolkit.log("getFillerGeneralizationsForRulePair:");
    TextRulerToolkit.log("Rule1: " + rule1.getRuleString());
    TextRulerToolkit.log("Rule2: " + rule2.getRuleString());

    List<RapierRule> result = new ArrayList<RapierRule>();
    List<TextRulerRulePattern> genList = RapierGeneralizationHelper
            .getGeneralizationsForRuleItemPatterns(rule1.getFillerPattern(), rule2
                    .getFillerPattern());
    // create rules:
    for (TextRulerRulePattern pattern : genList) {
      RapierRule newRule = new RapierRule(this, rule1.getTarget());
      for (TextRulerRuleItem patternItem : pattern)
        newRule.addFillerItem(patternItem.copy());
      newRule.setParent1(rule1.copy());
      newRule.setParent1PreFiller_n(0);
      newRule.setParent1PostFiller_n(0);
      newRule.setParent2(rule2.copy());
      newRule.setParent2PreFiller_n(0);
      newRule.setParent2PostFiller_n(0);
      result.add(newRule);
      newRule.setNeedsCompile(true);
      // TextRulerToolkit.log("newRule: "+newRule.getRuleString());
    }
    TextRulerToolkit.log("   getGeneralizationsForRulePair result list size = " + result.size());
    return result;
  }

  public List<RapierRule> specializePreFiller(RapierRule curRule, int n) {
    RapierRule baseRule1 = curRule.getParent1();
    RapierRule baseRule2 = curRule.getParent2();
    int n1 = curRule.getParent1PreFiller_n();
    int n2 = curRule.getParent2PreFiller_n();
    TextRulerRulePattern preFiller1 = baseRule1.getPreFillerPattern();
    TextRulerRulePattern preFiller2 = baseRule2.getPreFillerPattern();
    int preFiller1MaxIndex = preFiller1.size() - n1 - 1;
    int preFiller2MaxIndex = preFiller2.size() - n2 - 1;

    // generate 3 different possible sets for generalizations:

    // 1. n vs. n-1 (n elements of baserule1, n-1 of baserule2)
    TextRulerRulePattern consideredPreFiller1 = new TextRulerRulePattern();
    TextRulerRulePattern consideredPreFiller2 = new TextRulerRulePattern();
    for (int i = preFiller1.size() - n; i >= 0 && i <= preFiller1MaxIndex; i++)
      consideredPreFiller1.add(preFiller1.get(i));
    for (int i = preFiller2.size() - n + 1; i >= 0 && i <= preFiller2MaxIndex; i++)
      consideredPreFiller2.add(preFiller2.get(i));
    List<TextRulerRulePattern> genList1 = null;
    if (consideredPreFiller1.size() + consideredPreFiller2.size() > 0)
      genList1 = RapierGeneralizationHelper.getGeneralizationsForRuleItemPatterns(
              consideredPreFiller1, consideredPreFiller2);

    List<TextRulerRulePattern> genList2 = null;
    List<TextRulerRulePattern> genList3 = null;

    if (useAllGenSetsAtSpecialization) // due to performance reasons the
    // user can switch this off
    {
      // 2. n-1 vs. n (n-1 elements of baserule1, n of baserule2)
      consideredPreFiller1.clear();
      consideredPreFiller2.clear();
      for (int i = preFiller1.size() - n + 1; i >= 0 && i <= preFiller1MaxIndex; i++)
        consideredPreFiller1.add(preFiller1.get(i));
      for (int i = preFiller2.size() - n; i >= 0 && i <= preFiller2MaxIndex; i++)
        consideredPreFiller2.add(preFiller2.get(i));

      if (consideredPreFiller1.size() + consideredPreFiller2.size() > 0)
        genList2 = RapierGeneralizationHelper.getGeneralizationsForRuleItemPatterns(
                consideredPreFiller1, consideredPreFiller2);

      // 3. n vs. n (n elements of baserule1, n of baserule2)
      consideredPreFiller1.clear();
      consideredPreFiller2.clear();
      for (int i = preFiller1.size() - n; i >= 0 && i <= preFiller1MaxIndex; i++)
        consideredPreFiller1.add(preFiller1.get(i));
      for (int i = preFiller2.size() - n; i >= 0 && i <= preFiller2MaxIndex; i++)
        consideredPreFiller2.add(preFiller2.get(i));
      if (consideredPreFiller1.size() + consideredPreFiller2.size() > 0)
        genList3 = RapierGeneralizationHelper.getGeneralizationsForRuleItemPatterns(
                consideredPreFiller1, consideredPreFiller2);
    }

    // TODO optimize and don't store all 3 genLists ! but for debugging
    // purposes we keep them for now !
    Set<TextRulerRulePattern> genSet = new HashSet<TextRulerRulePattern>();
    if (genList1 != null)
      genSet.addAll(genList1);
    if (genList2 != null)
      genSet.addAll(genList2);
    if (genList3 != null)
      genSet.addAll(genList3);

    List<RapierRule> resultRules = new ArrayList<RapierRule>();

    for (TextRulerRulePattern l : genSet) {
      RapierRule newRule = curRule.copy();
      for (int i = l.size() - 1; i >= 0; i--)
        newRule.addPreFillerItem(l.get(i));
      newRule.setParent1PreFiller_n(n);
      newRule.setParent2PreFiller_n(n);
      resultRules.add(newRule);
    }
    return resultRules;
  }

  // n = 1..maxN
  public List<RapierRule> specializePostFiller(RapierRule curRule, int n) {
    if (n == 0) {
      TextRulerToolkit.log("ERROR ! N SHOULD NOT BE 0!");
    }
    RapierRule baseRule1 = curRule.getParent1();
    RapierRule baseRule2 = curRule.getParent2();
    int n1 = curRule.getParent1PostFiller_n();
    int n2 = curRule.getParent2PostFiller_n();
    TextRulerRulePattern postFiller1 = baseRule1.getPostFillerPattern();
    TextRulerRulePattern postFiller2 = baseRule2.getPostFillerPattern();
    int postFiller1MinIndex = n1;
    int postFiller2MinIndex = n2;

    // generate 3 different possible sets for generalizations:

    // 1. n vs. n-1 (n elements of baserule1, n-1 of baserule2)
    TextRulerRulePattern consideredPostFiller1 = new TextRulerRulePattern();
    TextRulerRulePattern consideredPostFiller2 = new TextRulerRulePattern();
    for (int i = postFiller1MinIndex; i < postFiller1.size() && i < n; i++)
      consideredPostFiller1.add(postFiller1.get(i));
    for (int i = postFiller2MinIndex; i < postFiller2.size() && i < n - 1; i++)
      consideredPostFiller2.add(postFiller2.get(i));
    List<TextRulerRulePattern> genList1 = null;
    if (consideredPostFiller1.size() + consideredPostFiller2.size() > 0)
      genList1 = RapierGeneralizationHelper.getGeneralizationsForRuleItemPatterns(
              consideredPostFiller1, consideredPostFiller2);

    // 2. n-1 vs. n (n-1 elements of baserule1, n of baserule2)
    consideredPostFiller1.clear();
    consideredPostFiller2.clear();
    for (int i = postFiller1MinIndex; i < postFiller1.size() && i < n - 1; i++)
      consideredPostFiller1.add(postFiller1.get(i));
    for (int i = postFiller2MinIndex; i < postFiller2.size() && i < n; i++)
      consideredPostFiller2.add(postFiller2.get(i));
    List<TextRulerRulePattern> genList2 = null;
    if (consideredPostFiller1.size() + consideredPostFiller2.size() > 0)
      genList2 = RapierGeneralizationHelper.getGeneralizationsForRuleItemPatterns(
              consideredPostFiller1, consideredPostFiller2);

    // 3. n vs. n (n elements of baserule1, n of baserule2)
    consideredPostFiller1.clear();
    consideredPostFiller2.clear();
    for (int i = postFiller1MinIndex; i < postFiller1.size() && i < n; i++)
      consideredPostFiller1.add(postFiller1.get(i));
    for (int i = postFiller2MinIndex; i < postFiller2.size() && i < n; i++)
      consideredPostFiller2.add(postFiller2.get(i));
    List<TextRulerRulePattern> genList3 = null;
    if (consideredPostFiller1.size() + consideredPostFiller2.size() > 0)
      genList3 = RapierGeneralizationHelper.getGeneralizationsForRuleItemPatterns(
              consideredPostFiller1, consideredPostFiller2);

    // TODO optimize and don't store all 3 genLists ! but for debugging
    // purposes we keep them for now !
    Set<TextRulerRulePattern> genSet = new HashSet<TextRulerRulePattern>();
    if (genList1 != null)
      genSet.addAll(genList1);
    if (genList2 != null)
      genSet.addAll(genList2);
    if (genList3 != null)
      genSet.addAll(genList3);

    List<RapierRule> resultRules = new ArrayList<RapierRule>();

    for (TextRulerRulePattern l : genSet) {
      RapierRule newRule = curRule.copy();
      for (TextRulerRuleItem t : l)
        newRule.addPostFillerItem(t);
      newRule.setParent1PostFiller_n(n);
      newRule.setParent2PostFiller_n(n);
      resultRules.add(newRule);
    }
    return resultRules;
  }

  @Override
  public boolean collectNegativeCoveredInstancesWhenTesting() {
    return false;
  }

  public String getResultString() {
    if (slotRules != null)
      return slotRules.getTMFileString(getTMFileHeaderString(), 1000); // if
    // a
    // rule
    // is
    // >100
    // characters,
    // it
    // gets
    // replaced
    // by
    // a
    // placeholder
    else
      return "No results available yet!";
  }

  public void setParameters(Map<String, Object> params) {
    if (TextRulerToolkit.DEBUG)
      saveParametersToTempFolder(params);

    // TODO add try catch
    if (params.containsKey(COMPRESSION_FAIL_MAX_COUNT_KEY))
      compressionFailMaxCount = (Integer) params.get(COMPRESSION_FAIL_MAX_COUNT_KEY);

    if (params.containsKey(RULELIST_SIZE_KEY))
      ruleListSize = (Integer) params.get(RULELIST_SIZE_KEY);

    if (params.containsKey(PAIR_COUNT_KEY))
      pairCount = (Integer) params.get(PAIR_COUNT_KEY);

    if (params.containsKey(LIM_NO_IMPROVEMENTS_KEY))
      limNoImprovements = (Integer) params.get(LIM_NO_IMPROVEMENTS_KEY);

    if (params.containsKey(NOISE_THESHOLD_KEY))
      noiseThreshold = (Float) params.get(NOISE_THESHOLD_KEY);

    if (params.containsKey(POSTAG_ROOTTYPE_KEY))
      posTagRootTypeName = (String) params.get(POSTAG_ROOTTYPE_KEY);

    if (params.containsKey(MIN_COVERED_POSITIVES_KEY))
      minCoveredPositives = (Integer) params.get(MIN_COVERED_POSITIVES_KEY);

    if (params.containsKey(USE_ALL_GENSETS_AT_SPECIALIZATION_KEY))
      useAllGenSetsAtSpecialization = (Boolean) params.get(USE_ALL_GENSETS_AT_SPECIALIZATION_KEY);
  }

  // TODO share this between algorithms (e.g. LP2 and RAPIER ?) and make a
  // maximum size of the cache, etc. like CasCache?
  protected void testRulesIfNotCached(List<RapierRule> rules) {
    List<TextRulerRule> rulesToTest = new ArrayList<TextRulerRule>();

    for (RapierRule r : rules) {
      String key = r.getRuleString();
      if (cachedTestedRuleStatistics.containsKey(key)) {
        r.setCoveringStatistics(cachedTestedRuleStatistics.get(key).copy());
        TextRulerToolkit.log("CACHE HIT; size=" + cachedTestedRuleStatistics.size());
      } else
        rulesToTest.add(r);
    }

    if (rulesToTest.size() > 0) {
      testRulesOnDocumentSet(rulesToTest, exampleDocuments);
      if (shouldAbort())
        return;
      while (cachedTestedRuleStatistics.size() + rulesToTest.size() > 10000) // TODO
      // lohnt
      // sich
      // das
      // ?
      // speicher
      // beobachten
      // !!
      {
        Iterator<String> it = cachedTestedRuleStatistics.keySet().iterator();
        if (!it.hasNext())
          break;
        String removeKey = cachedTestedRuleStatistics.keySet().iterator().next();
        cachedTestedRuleStatistics.remove(removeKey);
      }

      for (TextRulerRule r : rulesToTest) {
        String key = r.getRuleString();
        cachedTestedRuleStatistics.put(key, r.getCoveringStatistics().copy());
      }
    }
  }

}
