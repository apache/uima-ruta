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

package org.apache.uima.ruta.textruler.learner.lp2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.textruler.TextRulerPlugin;
import org.apache.uima.ruta.textruler.core.TextRulerAnnotation;
import org.apache.uima.ruta.textruler.core.TextRulerBasicLearner;
import org.apache.uima.ruta.textruler.core.TextRulerExample;
import org.apache.uima.ruta.textruler.core.TextRulerExampleDocument;
import org.apache.uima.ruta.textruler.core.TextRulerRule;
import org.apache.uima.ruta.textruler.core.TextRulerRuleList;
import org.apache.uima.ruta.textruler.core.TextRulerShiftExample;
import org.apache.uima.ruta.textruler.core.TextRulerStatisticsCollector;
import org.apache.uima.ruta.textruler.core.TextRulerTarget;
import org.apache.uima.ruta.textruler.core.TextRulerTarget.MLTargetType;
import org.apache.uima.ruta.textruler.core.TextRulerToolkit;
import org.apache.uima.ruta.textruler.extension.TextRulerLearnerDelegate;
import org.apache.uima.util.FileUtils;

public abstract class BasicLP2 extends TextRulerBasicLearner {

  public static final String WINDOW_SIZE_KEY = "windowSize";

  public static final String CURRENT_BEST_RULES_SIZE_KEY = "currentBestRulesSize";

  public static final String CURRENT_CONTEXTUAL_RULES_SIZE_KEY = "currentContextualRulesSize";

  public static final String MIN_COVERED_POSITIVES_PER_RULE_KEY = "minCoveredPositivesPerRule";

  public static final String MAX_ERROR_THRESHOLD_KEY = "maxErrorThreshold";

  public static final int STANDARD_WINDOW_SIZE = 2;

  public static final int STANDARD_MAX_CURRENT_BEST_RULES_COUNT = 4;

  public static final int STANDARD_MAX_CONTEXTUAL_RULES_COUNT = 4;

  public static final int STANDARD_MIN_COVERED_POSITIVES_PER_RULE = 1;

  public static final float STANDARD_MAX_ERROR_THRESHOLD = 0.1f;

  public static final String CORRECTION_ANNOTATION_NAME = "lp2shift";

  private static final int STANDARD_SHIFT_SIZE = 2;

  protected int maxCurrentBestRulesCount = STANDARD_MAX_CURRENT_BEST_RULES_COUNT;

  protected int maxCurrentContextualRulesCount = STANDARD_MAX_CONTEXTUAL_RULES_COUNT;

  protected int windowSize = STANDARD_WINDOW_SIZE;

  protected int shiftSize = STANDARD_SHIFT_SIZE;

  protected int minCoveredPositives = STANDARD_MIN_COVERED_POSITIVES_PER_RULE;

  protected float maxErrorThreshold = STANDARD_MAX_ERROR_THRESHOLD;

  protected List<TextRulerExample> examples;

  protected Set<TextRulerExample> coveredExamples;

  protected Map<String, Integer> slotMaximumTokenCountMap = new TreeMap<String, Integer>();

  protected LP2CurrentBestRulesQueue currentBestRules;

  protected LP2CurrentBestRulesQueue currentContextualRules;

  protected Map<String, TextRulerRuleList> bestRulesPoolMap = new TreeMap<String, TextRulerRuleList>();

  protected Map<String, TextRulerRuleList> contextRulesPoolMap = new TreeMap<String, TextRulerRuleList>();

  protected Map<String, String> leftBoundaryBestRulesMap = new TreeMap<String, String>();

  protected Map<String, String> rightBoundaryBestRulesMap = new TreeMap<String, String>();

  protected Map<String, String> leftBoundaryContextualRulesMap = new TreeMap<String, String>();

  protected Map<String, String> rightBoundaryContextualRulesMap = new TreeMap<String, String>();

  public BasicLP2(String inputDir, String prePropTMFile, String tmpDir, String[] slotNames,
          Set<String> filterSet, boolean skip, TextRulerLearnerDelegate delegate) {
    super(inputDir, prePropTMFile, tmpDir, slotNames, filterSet, skip, delegate);
    supportBoundaries = true;
  }

  protected TextRulerRuleList learnTaggingRules(TextRulerTarget target,
          TextRulerRuleList contextualRules) {
    if (target.type == MLTargetType.SINGLE_LEFT_BOUNDARY)
      sendStatusUpdateToDelegate("Creating Left-Boundary Examples...",
              TextRulerLearnerState.ML_RUNNING, false);
    else if (target.type == MLTargetType.SINGLE_RIGHT_BOUNDARY)
      sendStatusUpdateToDelegate("Creating Right-Boundary Examples...",
              TextRulerLearnerState.ML_RUNNING, false);
    else if (target.type == MLTargetType.SINGLE_LEFT_CORRECTION)
      sendStatusUpdateToDelegate("Creating Left Correction Examples...",
              TextRulerLearnerState.ML_RUNNING, false);
    else
      // if (target.type == MLTargetType.SINGLE_RIGHT_CORRECTION)
      sendStatusUpdateToDelegate("Creating Right Correction Examples...",
              TextRulerLearnerState.ML_RUNNING, false);
    exampleDocuments.clearCurrentExamples();
    exampleDocuments.createExamplesForTarget(target);
    examples = exampleDocuments.getAllPositiveExamples();

    if (shouldAbort())
      return null;
    TextRulerRuleList bestRulesPool = new TextRulerRuleList();
    TextRulerRuleList contextRulesPool = new TextRulerRuleList();
    String slotName = target.getSingleSlotRawTypeName();
    bestRulesPoolMap.put(slotName, bestRulesPool);
    contextRulesPoolMap.put(slotName, contextRulesPool);

    coveredExamples = new HashSet<TextRulerExample>();
    int roundNumber = 0;
    for (TextRulerExample e : examples)
      if (!coveredExamples.contains(e)) {
        if (shouldAbort())
          break;
        roundNumber++;
        currentBestRules = new LP2CurrentBestRulesQueue(maxCurrentBestRulesCount);
        currentContextualRules = new LP2CurrentBestRulesQueue(maxCurrentContextualRulesCount);
        // TextRulerToolkit.log("Example: "+e.getAnnotation().getBegin()+" : "+e.getAnnotation().getEnd());

        induceRulesFromExample(e, roundNumber);

        // TextRulerToolkit.log("Best Rules from this Seed: "+currentBestRules.size());
        // if (TextRulerToolkit.DEBUG && currentBestRules.size()>1)
        // {
        // for (TextRulerRule r : currentBestRules)
        // {
        // TextRulerToolkit.log("\tp="+r.getCoveringStatistics().getCoveredPositivesCount()+"; n="+r.getCoveringStatistics().getCoveredNegativesCount()+";  "+r.getRuleString());
        // for (TextRulerExample ex :
        // r.getCoveringStatistics().getCoveredPositiveExamples())
        // {
        // TextRulerToolkit.log("\t\te="+ex.getAnnotation().getBegin());
        //
        // }
        // }
        // }
        for (LP2Rule bestRule : currentBestRules) {
          addToFinalBestRulesPool(bestRule);
        }
        for (LP2Rule ctxRule : currentContextualRules) {
          addToFinalContextRulesPool(ctxRule);
        }
        sendStatusUpdateToDelegate("New Rules added.", TextRulerLearnerState.ML_RUNNING, true);
      }
    if (TextRulerToolkit.DEBUG) {
      bestRulesPool.saveToRulesFile(getIntermediateRulesFileName(), getFileHeaderString(true));
      // for (TextRulerRule r : bestRulesPool)
      // {
      // TextRulerToolkit.log("p="+r.getCoveringStatistics().getCoveredPositivesCount()+"; n="+r.getCoveringStatistics().getCoveredNegativesCount()+";  "+r.getRuleString());
      // }
    }

    TextRulerRuleList result = bestRulesPool;
    if (contextualRules != null)
      for (TextRulerRule r : contextRulesPool)
        contextualRules.add(r);
    return result;
  }

  @Override
  public CAS loadCAS(String fileName, CAS reuseCAS) {
    CAS cas = super.loadCAS(fileName, reuseCAS);
    prepareCASWithBoundaries(cas);
    return cas;
  }

  public void prepareCASWithBoundaries(CAS cas) {
    for (String slotName : slotNames)
      TextRulerExampleDocument.createBoundaryAnnotationsForCas(cas, slotName, filterSet);
  }

  public void prepareCachedCASesWithBoundaries() {
    for (CAS cas : exampleDocuments.getCachedCASes())
      prepareCASWithBoundaries(cas);
  }

  @Override
  protected void cleanUp() {
    super.cleanUp();
    examples = null;
    coveredExamples = null;
    currentBestRules = null;
    currentContextualRules = null;
    bestRulesPoolMap.clear();
    contextRulesPoolMap.clear();
  }

  @Override
  protected void doRun() {
    TextRulerToolkit.logIfDebug("--- LP2 START");

    prepareCachedCASesWithBoundaries(); // if some cases are already loaded,
    // prepare them! all others get prepared when loaded (see loadCAS)

    for (int i = 0; i < slotNames.length; i++) {
      runForSlotName(slotNames[i]);
    }

    sendStatusUpdateToDelegate("Done", TextRulerLearnerState.ML_DONE, true);
    TextRulerToolkit.logIfDebug("--- LP2 END");
  }

  protected void runForSlotName(String slotName) {
    // 1. get slot length histogram in order to find maximum slot length
    // (counted in tokens)

    sendStatusUpdateToDelegate("Creating slot length histogram...",
            TextRulerLearnerState.ML_RUNNING, false);
    List<Integer> histogram = exampleDocuments.getTokenCountHistogrammForSlotName(slotName,
            TextRulerToolkit.getFilterSetWithSlotNames(slotNames, filterSet));
    if (shouldAbort())
      return;
    slotMaximumTokenCountMap.put(slotName, histogram.size() - 1); // -1 since the
    // zero-histogram point
    // also needs a place!

    TextRulerRuleList ctxRules = new TextRulerRuleList();
    TextRulerRuleList bestRules = learnTaggingRules(new TextRulerTarget(slotName,
            MLTargetType.SINGLE_LEFT_BOUNDARY, this), ctxRules); // learn
    // left
    // boundary
    // best
    // rules
    if (bestRules != null) {
      leftBoundaryBestRulesMap.put(slotName, bestRules.getRulesString(""));
      leftBoundaryContextualRulesMap.put(slotName, ctxRules.getRulesString("\t"));
      bestRules.clear(); // free som memory/references
    }
    if (shouldAbort())
      return;
    ctxRules.clear();
    bestRules = learnTaggingRules(new TextRulerTarget(slotName, MLTargetType.SINGLE_RIGHT_BOUNDARY,
            this), ctxRules); // learn
    // right
    // boundary best
    // rules
    if (bestRules != null) {
      rightBoundaryBestRulesMap.put(slotName, bestRules.getRulesString(""));
      rightBoundaryContextualRulesMap.put(slotName, ctxRules.getRulesString("\t"));
    }

    // TODO add correction rule learn stuff
    // testTaggingRulesAndCreateCorrectionRulesExamples(null, STANDARD_MAX_CONTEXTUAL_RULES_COUNT)

    // correct left start
    TextRulerTarget lsTarget = new TextRulerTarget(slotName, MLTargetType.SINGLE_LEFT_CORRECTION,
            this);
    lsTarget.setMaxShiftDistance(shiftSize);
    TextRulerRuleList correctLeftRules = learnTaggingRules(lsTarget, null);

    // resultString = "CAP{REGEXP(\"PM\")} ALL{->MARKONCE(stimeEND)};";
    // try {
    // FileUtils.saveString2File(resultString, file);
    // } catch (IOException e) {
    // }

    // correct right start
    // TextRulerTarget rsTarget = new TextRulerTarget(slotName,
    // MLTargetType.SINGLE_RIGHT_CORRECTION,
    // this);
    // rsTarget.setMaxShiftDistance(shiftSize);
    // TextRulerRuleList correctRightRules = learnTaggingRules(rsTarget, null);
    //
    sendStatusUpdateToDelegate("SLOT Done", TextRulerLearnerState.ML_RUNNING, true);
    TextRulerToolkit.logIfDebug("--- LP2 END FOR SLOT:" + slotName);
  }

  protected abstract void induceRulesFromExample(TextRulerExample e, int roundNumber);

  protected void addToFinalContextRulesPool(LP2Rule rule) {
    if (TextRulerToolkit.DEBUG)
      TextRulerToolkit.appendStringToFile(tempDirectory() + "ctxpool"
              + RutaEngine.SCRIPT_FILE_EXTENSION, rule.getRuleString() + "\n");
    String slotName = rule.getTarget().getSingleSlotRawTypeName();
    if (!contextRulesPoolMap.get(slotName).contains(rule)) {
      contextRulesPoolMap.get(slotName).add(rule);
      // TextRulerToolkit.log("CONTEXT RULE: "+rule.getRuleString()+" ; "+rule.getCoveringStatistics());
    } else {
      if (TextRulerToolkit.DEBUG) {
        TextRulerToolkit.appendStringToFile(tempDirectory() + "ctxpool"
                + RutaEngine.SCRIPT_FILE_EXTENSION, "\tDUPLICATE\n");
      }
    }

  }

  protected void addToFinalBestRulesPool(LP2Rule rule) {
    if (TextRulerToolkit.DEBUG && false)
      TextRulerToolkit.appendStringToFile(tempDirectory() + "bestpool"
              + RutaEngine.SCRIPT_FILE_EXTENSION, rule.getRuleString() + "\n");
    String slotName = rule.getTarget().getSingleSlotRawTypeName();
    if (!bestRulesPoolMap.get(slotName).contains(rule)) {
      bestRulesPoolMap.get(slotName).add(rule);
      // TextRulerToolkit.log("BEST RULE: "+rule.getRuleString());
      // add all covered positives to covering set
      coveredExamples.addAll(rule.getCoveringStatistics().getCoveredPositiveExamples());
      if (TextRulerToolkit.DEBUG)
        bestRulesPoolMap.get(slotName).saveToRulesFile(getIntermediateRulesFileName(),
                getFileHeaderString(false));
    } else {
      if (TextRulerToolkit.DEBUG && false) {
        TextRulerToolkit.log("KANN SOWAS PASSIEREN ??");
        TextRulerToolkit.appendStringToFile(tempDirectory() + "bestpool"
                + RutaEngine.SCRIPT_FILE_EXTENSION, "\tDUPLICATE\n");
      }
    }

  }

  public String getResultString() {
    StringBuilder sb = new StringBuilder();
    String header = getFileHeaderString(true);
    sb.append(header);

    for (String eachSlot : slotNames) {

      String leftBoundaryBestRulesString = leftBoundaryBestRulesMap.get(eachSlot);
      String rightBoundaryBestRulesString = rightBoundaryBestRulesMap.get(eachSlot);
      String leftBoundaryContextualRulesString = leftBoundaryContextualRulesMap.get(eachSlot);
      String rightBoundaryContextualRulesString = rightBoundaryContextualRulesMap.get(eachSlot);
      TextRulerRuleList bestRulesPool = bestRulesPoolMap.get(eachSlot);
      TextRulerRuleList contextRulesPool = contextRulesPoolMap.get(eachSlot);

      sb.append("\n// Slot: " + TextRulerToolkit.getTypeShortName(eachSlot) + "\n");
      sb.append("// LEFT BOUNDARY RULES:\n");
      if (leftBoundaryBestRulesString != null) {
        sb.append(leftBoundaryBestRulesString);
        sb.append("\n// RIGHT BOUNDARY RULES:\n");
        if (rightBoundaryBestRulesString != null)
          sb.append(rightBoundaryBestRulesString);
        else if (bestRulesPool != null)
          sb.append(bestRulesPool.getRulesString(""));

        sb.append("\nBLOCK(contextualRules_" + TextRulerToolkit.getTypeShortName(eachSlot)
                + ") Document{} {\n"
                + "\tDocument{->ASSIGN(redoContextualRules, false)}; // reset flag\n");
        sb.append("\n\t// LEFT BOUNDARY CONTEXTUAL RULES:\n");
        sb.append(leftBoundaryContextualRulesString);

        sb.append("\n\t// RIGHT BOUNDARY CONTEXTUAL RULES:\n");
        if (rightBoundaryBestRulesString != null)
          sb.append(rightBoundaryContextualRulesString);
        else if (contextRulesPool != null)
          sb.append(contextRulesPool.getRulesString("\t"));

        sb.append("\n\t//Document{IF(redoContextualRules)->CALL(thisFile.contextualRules_"
                + TextRulerToolkit.getTypeShortName(eachSlot) + ")};\n}\n");
      } else if (bestRulesPool != null) {
        sb.append(bestRulesPool.getRulesString(""));
        sb.append("\n\t// LEFT BOUNDARY CONTEXTUAL RULES:\n");
        if (contextRulesPool != null)
          sb.append(contextRulesPool.getRulesString(""));
      }
    }

    for (String eachSlot : slotNames) {
      String leftBoundary = TextRulerToolkit.getTypeShortName((new TextRulerTarget(eachSlot,
              MLTargetType.SINGLE_LEFT_BOUNDARY, this)).getSingleSlotTypeName());
      String rightBoundary = TextRulerToolkit.getTypeShortName((new TextRulerTarget(eachSlot,
              MLTargetType.SINGLE_RIGHT_BOUNDARY, this)).getSingleSlotTypeName());
      String slotMarkName = TextRulerToolkit.getTypeShortName(eachSlot);
      int maxInnerLength = (getMaxTokens(eachSlot) * 3) - 2;
      sb.append("\n//slot-building rules:\n");
      sb.append(leftBoundary + "{IS(" + rightBoundary + ")->UNMARK(" + leftBoundary + "), UNMARK("
              + rightBoundary + "), MARKONCE(" + slotMarkName + ")};\n");
      sb.append(leftBoundary + "{->UNMARK(" + leftBoundary + ")} ");
      if (maxInnerLength > 0) {
        sb.append("ANY[0, " + maxInnerLength + "]? ");
        sb.append(rightBoundary + "{->UNMARK(" + rightBoundary + "), MARKONCE(" + slotMarkName
                + ", 1, 3)};\n");
      } else
        sb.append(rightBoundary + "{->UNMARK(" + rightBoundary + "), MARKONCE(" + slotMarkName
                + ", 1, 2)};\n");

      sb.append("\n//cleaning up:\n" + leftBoundary + "{->UNMARK(" + leftBoundary + ")};\n"
              + rightBoundary + "{->UNMARK(" + rightBoundary + ")};\n");
    }

    return sb.toString();
  }

  private Integer getMaxTokens(String slot) {
    if (slotMaximumTokenCountMap.get(slot) == null) {
      return 0;
    }
    return slotMaximumTokenCountMap.get(slot);
  }

  public void setParameters(Map<String, Object> params) {
    if (TextRulerToolkit.DEBUG)
      saveParametersToTempFolder(params);

    // TODO try catch
    if (params.containsKey(WINDOW_SIZE_KEY))
      windowSize = (Integer) params.get(WINDOW_SIZE_KEY);

    if (params.containsKey(CURRENT_BEST_RULES_SIZE_KEY))
      maxCurrentBestRulesCount = (Integer) params.get(CURRENT_BEST_RULES_SIZE_KEY);

    if (params.containsKey(CURRENT_CONTEXTUAL_RULES_SIZE_KEY))
      maxCurrentContextualRulesCount = (Integer) params.get(CURRENT_CONTEXTUAL_RULES_SIZE_KEY);

    if (params.containsKey(MIN_COVERED_POSITIVES_PER_RULE_KEY))
      minCoveredPositives = (Integer) params.get(MIN_COVERED_POSITIVES_PER_RULE_KEY);

    if (params.containsKey(MAX_ERROR_THRESHOLD_KEY))
      maxErrorThreshold = (Float) params.get(MAX_ERROR_THRESHOLD_KEY);
  }

  protected String correctionRulesInputDirectory(TextRulerTarget target) {
    if (target.isLeftBoundary())
      return tempDirectory() + "leftCorrectionDocs";
    else
      return tempDirectory() + "rightCorrectionDocs";
  }

  protected boolean testTaggingRulesAndCreateCorrectionRulesExamples(TextRulerTarget target,
          int maxDistance) {
    try {
      File dir = new File(correctionRulesInputDirectory(target));
      if (!dir.exists())
        dir.mkdir();
      exampleDocuments.clearCurrentExamples();
      exampleDocuments.createExamplesForTarget(target);
      examples = exampleDocuments.getAllPositiveExamples();

      TextRulerExampleDocument[] sortedDocs = exampleDocuments
              .getSortedDocumentsInCacheOptimizedOrder();
      TypeSystem ts = sortedDocs[0].getCAS().getTypeSystem();
      Type tokensRootType = ts.getType(TextRulerToolkit.RUTA_ANY_TYPE_NAME);

      // String allRulesContent = getResultString();
      String allRulesContent = FileUtils.file2String(new File("/testinput/testrules/rules"
              + RutaEngine.SCRIPT_FILE_EXTENSION));
      FileUtils.saveString2File(allRulesContent, new File(getTempRulesFileName()));

      CAS testCAS = getTestCAS();
      for (TextRulerExampleDocument doc : sortedDocs) {
        TextRulerStatisticsCollector c = new TextRulerStatisticsCollector();
        doc.resetAndFillTestCAS(testCAS, target);
        CAS docCAS = doc.getCAS();
        ae.process(testCAS);
        compareOriginalDocumentWithTestCAS(doc, testCAS, target, c, true); // test whole ruleset and
        // collect negative
        // examples

        // now we have some covered positive examples that are good, and
        // maybe some negative examples
        // for that we might create Correction Rules... in order to do
        // that we have to create
        // ShiftExamples and map negative examples (incorrect inserted
        // boundaries) with a specific
        // distance to an original positive example...

        // TODO should that be done in both directions ? left and right
        // ?! what happes if we
        // find two potential examples, one left, one right ? --> for
        // now: use the nearer one. if
        // exactly the same distance, use the one where the wrong tag
        // would be IN the slot filler!
        List<TextRulerExample> correctTags = doc.getPositiveExamples();
        List<TextRulerExample> wrongTags = new ArrayList<TextRulerExample>(
                c.getCoveredNegativeExamples());
        List<TextRulerShiftExample> newExamples = new ArrayList<TextRulerShiftExample>();
        for (TextRulerExample wrongTag : wrongTags) {
          // test, if there's a corresponding positive example
          // somewhere around (within maxDistance)
          List<AnnotationFS> left = TextRulerToolkit.getAnnotationsBeforePosition(docCAS, wrongTag
                  .getAnnotation().getBegin(), maxDistance, TextRulerToolkit
                  .getFilterSetWithSlotNames(slotNames, filterSet), tokensRootType);
          List<AnnotationFS> right = TextRulerToolkit.getAnnotationsAfterPosition(docCAS, wrongTag
                  .getAnnotation().getEnd(), maxDistance, TextRulerToolkit
                  .getFilterSetWithSlotNames(slotNames, filterSet), tokensRootType);

          // TODO stop after the first found match or create one bad
          // example for each found occurence ??!!
          // for now: stop after one ! so create only ONE bad
          // example...
          int leftDistance = 0;
          TextRulerExample leftCorrectTag = null;
          for (int i = left.size() - 1; i >= 0; i--) {
            leftDistance++;
            TextRulerAnnotation needle = TextRulerToolkit.convertToTargetAnnotation(left.get(i),
                    doc, target, docCAS.getTypeSystem());
            leftCorrectTag = TextRulerToolkit.exampleListContainsAnnotation(correctTags, needle);
            if (leftCorrectTag != null)
              break;
          }

          int rightDistance = 0;
          TextRulerExample rightCorrectTag = null;
          for (AnnotationFS fs : right) {
            rightDistance++;
            TextRulerAnnotation needle = TextRulerToolkit.convertToTargetAnnotation(fs, doc,
                    target, docCAS.getTypeSystem());
            rightCorrectTag = TextRulerToolkit.exampleListContainsAnnotation(correctTags, needle);
            if (rightCorrectTag != null)
              break;
          }

          TextRulerExample theCorrectTag = null;
          if (rightDistance < leftDistance && rightCorrectTag != null)
            theCorrectTag = rightCorrectTag;
          else if (rightDistance > leftDistance && leftCorrectTag != null)
            theCorrectTag = leftCorrectTag;
          else // use the one that would lie in the slot filler:
          {
            if (target.type == MLTargetType.SINGLE_LEFT_BOUNDARY && rightCorrectTag != null)
              theCorrectTag = rightCorrectTag;
            else
              theCorrectTag = leftCorrectTag;
          }

          if (theCorrectTag != null) {
            TextRulerToolkit.log("FOUND BAD EXAMPLE FOR SHIFTING !!");
            TextRulerShiftExample shiftExample = new TextRulerShiftExample(doc,
                    wrongTag.getAnnotation(), theCorrectTag.getAnnotation(), true, target);
            newExamples.add(shiftExample);
          }
        }
        TextRulerToolkit
                .writeCAStoXMIFile(testCAS, dir + File.pathSeparator + doc.getCasFileName());
      }
      testCAS.reset();
    } catch (Exception e) {
      TextRulerPlugin.error(e);
      return false;
    }

    return true;
  }

  @Override
  public String getFileHeaderString(boolean complete) {
    return super.getFileHeaderString(complete) + "BOOLEAN redoContextualRules;\n\n";
  }

  @Override
  protected boolean checkForMandatoryTypes() {
    if (!super.checkForMandatoryTypes()) {
      return false;
    }

    CAS someCas = getTestCAS();
    TypeSystem ts = someCas.getTypeSystem();
//    GlobalCASSource.releaseCAS(someCas);
    // check if all helper types are present:
    List<String> list = new ArrayList<String>();

    for (String eachSlot : slotNames) {
      list.add(new TextRulerTarget(eachSlot, MLTargetType.SINGLE_LEFT_BOUNDARY, this)
              .getSingleSlotTypeName());
      list.add(new TextRulerTarget(eachSlot, MLTargetType.SINGLE_RIGHT_BOUNDARY, this)
              .getSingleSlotTypeName());
    }

    boolean result = true;
    List<String> missingTypes = new ArrayList<String>();
    for (String s : list) {
      if (ts.getType(s) == null) {
        missingTypes.add(s);
        result = false;
      }
    }
    String missingString = "";
    for (String string : missingTypes) {
      missingString += string + ", ";
    }
    if (!StringUtils.isEmpty(missingString)) {
      missingString = missingString.substring(0, missingString.length() - 2);
    }
    if (!result) {
      sendStatusUpdateToDelegate("Error: Some Slot- or Helper-Types were not found in TypeSystem: "
              + missingString, TextRulerLearnerState.ML_ERROR, false);
    }
    return result;
  }

}
