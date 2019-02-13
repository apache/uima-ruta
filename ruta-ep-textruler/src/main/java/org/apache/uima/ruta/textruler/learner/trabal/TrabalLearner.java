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

package org.apache.uima.ruta.textruler.learner.trabal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.ruta.textruler.core.TextRulerBasicLearner;
import org.apache.uima.ruta.textruler.core.TextRulerExample;
import org.apache.uima.ruta.textruler.core.TextRulerExampleDocument;
import org.apache.uima.ruta.textruler.core.TextRulerExampleDocumentSet;
import org.apache.uima.ruta.textruler.core.TextRulerStatisticsCollector;
import org.apache.uima.ruta.textruler.core.TextRulerTarget;
import org.apache.uima.ruta.textruler.core.TextRulerToolkit;
import org.apache.uima.ruta.textruler.extension.TextRulerLearnerDelegate;
import org.apache.uima.ruta.textruler.tools.MemoryWatch;
import org.apache.uima.util.CasCopier;

public class TrabalLearner extends TextRulerBasicLearner {

  public final static String ANNOTATION_TYPE_BASIC = "org.apache.uima.ruta.type.RutaBasic";

  public final static String ANNOTATION_TYPE_ANY = "org.apache.uima.ruta.type.ANY";

  public final static String ANNOTATION_TYPE_FRAME = "org.apache.uima.ruta.type.RutaFrame";

  public final static String PACKAGE = "org.apache.uima";

  public final static String TYPESYSTEM = "de.uniwue.tm.citie.CompleteTypeSystemTypeSystem";

  public final static List<String> FILTERED_FEATURES;

  static {
    ArrayList<String> tmp = new ArrayList<String>();
    tmp.add("sofa");
    tmp.add("begin");
    tmp.add("end");
    FILTERED_FEATURES = tmp;
  }

  public final static String MAX_NUMBER_OF_BASIC_RULES_KEY = "maxNumberOfBasicRules";

  public final static String MAX_NUMBER_OF_RULES_KEY = "maxNumberOfRules";

  public final static String MAX_NUMBER_OF_ITERATIONS_KEY = "maxNumberOfIterations";

  public final static String ALGORITHM_ITERATIONS_KEY = "algorithmIterations";

  public final static String MAX_ERROR_RATE_KEY = "maxErrorRate";

  public final static String ENABLE_FEATURES_KEY = "enableFeatures";

  public final static int MAX_NUMBER_OF_BASIC_RULES = 80;

  public final static int MAX_NUMBER_OF_RULES = 150;

  public final static int MAX_NUMBER_OF_ITERATIONS = 2;

  public final static int ALGORITHM_ITERATIONS = 3;

  public final static double MAX_ERROR_RATE = 0.4;

  public final static boolean ENABLE_FEATURES = false;

  private int maxNumberOfBasicRules = MAX_NUMBER_OF_BASIC_RULES;

  private int maxNumberOfRules = MAX_NUMBER_OF_RULES;

  private int maxNumberOfIterations = MAX_NUMBER_OF_ITERATIONS;

  private int algorithmIterations = ALGORITHM_ITERATIONS;

  private double maxErrorRate = MAX_ERROR_RATE;

  public boolean enableFeatures = ENABLE_FEATURES;

  private String result = ""; // actualResult + tempRules

  private String actualResult = "";

  private List<TrabalRule> bestRulesForStatus = new ArrayList<TrabalRule>();

  private String inputDirectory;

  private String additionalFolderPath;

  private TextRulerExampleDocumentSet additionalDocuments;

  private List<AnnotationError> errors;

  private Map<String, RankedList> positiveExamples;

  private Map<String, Double> idf;

  private Map<String, TextRulerStatisticsCollector> inducedRules = new TreeMap<String, TextRulerStatisticsCollector>();

  public TrabalLearner(String inputFolderPath, String additionalFolderPath,
          String preprocessorTMfile, String tempFolderPath, String[] fullSlotTypeNames,
          Set<String> filterSet, boolean skip, TextRulerLearnerDelegate delegate) {
    super(inputFolderPath, preprocessorTMfile, tempFolderPath, fullSlotTypeNames, filterSet, skip,
            delegate);
    this.inputDirectory = inputFolderPath;
    this.additionalFolderPath = additionalFolderPath;
  }

  @Override
  protected void doRun() {
    try {
      getAnalysisEngine();
      getAdditionalDocuments();
      if (additionalDocuments == null)
        throw new Exception("Error: Additional data is missing!");
      if (exampleDocuments.getDocuments().size() != additionalDocuments.getDocuments().size())
        throw new Exception("Error: Training data doesn't match additional data!");
      sendStatusUpdateToDelegate("Loading documents...", TextRulerLearnerState.ML_INITIALIZING,
              true);
      sendStatusUpdateToDelegate("Comparing documents...", TextRulerLearnerState.ML_RUNNING, true);
      for (int i = 0; i < algorithmIterations; i++) {
        actualResult += "BLOCK(Iteration_" + (i + 1) + ") Document{} {\n";
        sendStatusUpdateToDelegate("Comparing documents...", TextRulerLearnerState.ML_RUNNING,
                true);
        idf = createIDF();
        Map<String, List<AnnotationError>> errorGrps = createErrorGroups();
        List<TrabalRule> rules = runAlgorithm(errorGrps);
        if (rules.size() == 0) {
          actualResult = actualResult.replace("BLOCK(Iteration_" + (i + 1) + ") Document{} {", "");
          break;
        }
        actualResult += "}\n\n";
        result = actualResult;
        sendStatusUpdateToDelegate("Finished " + (i + 1) + ". iteration.",
                TextRulerLearnerState.ML_DONE, true);
      }
      Map<String, List<AnnotationError>> errorGrps = createErrorGroups();
      if (errorGrps.size() > 0) {
        actualResult += "// Remaining errors \n";
        for (List<AnnotationError> list : errorGrps.values()) {
          for (AnnotationError err : list) {
            actualResult += "\n// " + err;
          }
        }
      }
      if (new File(tempDirectory).isDirectory())
        new File(tempDirectory).delete();
      result = actualResult;
      sendStatusUpdateToDelegate("Done!", TextRulerLearnerState.ML_DONE, true);
    } catch (Exception e) {
      sendStatusUpdateToDelegate(e.getLocalizedMessage(), TextRulerLearnerState.ML_ERROR, true);
      e.printStackTrace();
    }
  }

  private void removeBasics() {
    for (TextRulerExampleDocument doc : additionalDocuments.getDocuments()) {
      CAS cas = doc.getCAS();
      List<AnnotationFS> basics = new ArrayList<AnnotationFS>();
      for (AnnotationFS a : cas.getAnnotationIndex()) {
        if (a.getType().getName().equals(ANNOTATION_TYPE_BASIC)) {
          basics.add(a);
        }
      }
      for (AnnotationFS a : basics)
        cas.removeFsFromIndexes(a);
    }
  }

  private void removeBasics(CAS cas) {
    List<AnnotationFS> basics = new ArrayList<AnnotationFS>();
    for (AnnotationFS a : cas.getAnnotationIndex()) {
      if (a.getType().getName().equals(ANNOTATION_TYPE_BASIC)) {
        basics.add(a);
      }
    }
    for (AnnotationFS a : basics)
      cas.removeFsFromIndexes(a);
  }

  private Map<String, Double> createIDF() {
    Map<String, Double> result = new HashMap<String, Double>();
    List<TextRulerExampleDocument> documents = additionalDocuments.getDocuments();
    // int counter = 0;
    Map<String, Integer> annotations = new HashMap<String, Integer>();
    for (int i = 0; i < documents.size(); i++) {
      AnnotationIndex<AnnotationFS> index = documents.get(i).getCAS().getAnnotationIndex();
      FSIterator<AnnotationFS> iterator = index.iterator();
      while (iterator.isValid()) {
        AnnotationFS annotation = iterator.next();
        if (isSlotType(annotation.getType())) {
          TrabalAnnotation a = new TrabalAnnotation(annotation, enableFeatures);
          if (annotations.containsKey(a.getType().getShortName())) {
            annotations.put(a.getType().getShortName(),
                    new Integer(annotations.get(a.getType().getShortName()).intValue() + 1));
          } else {
            annotations.put(a.getType().getShortName(), new Integer(1));
          }
          // counter++;
        }
      }
      for (String key : annotations.keySet()) {
        double d = annotations.get(key).doubleValue();
        result.put(key, new Double(1 / d));
      }
    }
    return result;
  }

  private Map<String, List<AnnotationError>> createErrorGroups() {
    errors = createErrorList();
    Collections.sort(errors);
    Map<String, List<AnnotationError>> result = new HashMap<String, List<AnnotationError>>();
    for (AnnotationError error : errors) {
      String truthFeatures = "";
      String errorFeatures = "";
      if (error.getError() != null && error.getTruth() != null) {
        if (enableFeatures) {
          truthFeatures = listFeatures(error.getTruth());
          errorFeatures = listFeatures(error.getError());
        }
        String key = error.getType() + "_" + error.getError().getAnnotation().getType().toString()
                + errorFeatures + "_" + error.getTruth().getAnnotation().getType().toString()
                + truthFeatures;
        if (result.get(key) == null) {
          result.put(key, new ArrayList<AnnotationError>());
        }
        result.get(key).add(error);
      } else if (error.getTruth() != null) {
        if (enableFeatures)
          truthFeatures = listFeatures(error.getTruth());
        String key = error.getType() + "_" + error.getTruth().getAnnotation().getType().toString()
                + truthFeatures;
        if (result.get(key) == null) {
          result.put(key, new ArrayList<AnnotationError>());
        }
        result.get(key).add(error);
      } else {
        if (enableFeatures)
          errorFeatures = listFeatures(error.getError());
        String key = error.getType() + "_" + error.getError().getAnnotation().getType().toString()
                + errorFeatures;
        if (result.get(key) == null) {
          result.put(key, new ArrayList<AnnotationError>());
        }
        result.get(key).add(error);
      }
    }
    return result;
  }

  private String listFeatures(TextRulerExample example) {
    String result = "";
    for (Feature f : example.getAnnotation().getType().getFeatures()) {
      if (!FILTERED_FEATURES.contains(f.getShortName()))
        result += "_" + f.toString();
    }
    return result;
  }

  private List<AnnotationError> createErrorList() {
    positiveExamples = new HashMap<String, RankedList>();
    List<AnnotationError> result = new ArrayList<AnnotationError>();
    List<TrabalAnnotation> matches;
    Iterator<TrabalAnnotation> iterator;
    Iterator<TrabalAnnotation> docIterator;
    List<TextRulerExampleDocument> documents;
    List<TextRulerExampleDocument> goldStandard;
    documents = additionalDocuments.getDocuments();
    goldStandard = exampleDocuments.getDocuments();
    for (int i = 0; i < goldStandard.size(); i++) {
      if (shouldAbort())
        break;
      matches = new ArrayList<TrabalAnnotation>();
      AnnotationIndex<AnnotationFS> index = goldStandard.get(i).getCAS().getAnnotationIndex();
      List<TrabalAnnotation> gold = new ArrayList<TrabalAnnotation>();
      List<TrabalAnnotation> docs = new ArrayList<TrabalAnnotation>();
      for (AnnotationFS a : index) {
        if (isSlotType(a.getType())) {
          gold.add(new TrabalAnnotation(a, goldStandard.get(i), enableFeatures));
        }
      }
      AnnotationIndex<AnnotationFS> docIndex = documents.get(i).getCAS().getAnnotationIndex();
      for (AnnotationFS b : docIndex) {
        if (isSlotType(b.getType())) {
          docs.add(new TrabalAnnotation(b, documents.get(i), enableFeatures));
        }
      }
      iterator = gold.iterator();
      docIterator = docs.iterator();
      TrabalAnnotation a;
      TrabalAnnotation b;
      // find correct annotated elements
      int exampleIndex = 0;
      while (iterator.hasNext()) {
        if (shouldAbort())
          break;
        exampleIndex++;
        sendStatusUpdateToDelegate(
                "Comparing documents " + (i + 1) + " of " + goldStandard.size() + ": example "
                        + exampleIndex + " of " + gold.size(),
                TextRulerLearnerState.ML_RUNNING, false);
        a = iterator.next();
        docIterator = docs.iterator();
        while (docIterator.hasNext()) {
          b = docIterator.next();
          if (b.equals(a)) {
            matches.add(a);
            matches.add(b);
            if (positiveExamples.containsKey(a.getType().getShortName())) {
              RankedList list = positiveExamples.get(a.getType().getShortName());
              list.addAll(createConditions(a));
              positiveExamples.put(a.getType().getShortName(), list);
            } else {
              RankedList list = new RankedList(idf);
              list.addAll(createConditions(a));
              positiveExamples.put(a.getType().getShortName(), list);
            }
            break;
          }
        }
      }
      // create correction type errors
      iterator = gold.iterator();
      docIterator = docs.iterator();
      while (iterator.hasNext()) {
        a = iterator.next();
        docIterator = docs.iterator();
        while (!matches.contains(a) && docIterator.hasNext()) {
          b = docIterator.next();
          if (!matches.contains(b)) {
            if (b.getBegin() == a.getBegin() && b.getEnd() == a.getEnd()) {
              TextRulerTarget target = new TextRulerTarget(b.getType().getName(), this);
              TextRulerExample error = new TextRulerExample(b.getDocument(), b, false, target);
              TextRulerExample truth = new TextRulerExample(a.getDocument(), a, true, target);
              result.add(new AnnotationError(error, truth, AnnotationErrorType.CORRECTION));
              matches.add(a);
              matches.add(b);
              break;
            }
          }
        }
      }
      // create shifting type errors
      iterator = gold.iterator();
      docIterator = docs.iterator();
      List<AnnotationError> tempErrors;
      int distance;
      while (iterator.hasNext()) {
        a = iterator.next();
        docIterator = docs.iterator();
        tempErrors = new ArrayList<AnnotationError>();
        while (!matches.contains(a) && docIterator.hasNext()) {
          b = docIterator.next();
          if (!matches.contains(b)) {
            if (b.getType().getShortName().equals(a.getType().getShortName())
                    && b.getEnd() >= a.getBegin() && b.getBegin() <= a.getEnd()) {
              TextRulerTarget target = new TextRulerTarget(a.getType().getName(), this);
              TextRulerExample error = new TextRulerExample(b.getDocument(), b, false, target);
              TextRulerExample truth = new TextRulerExample(a.getDocument(), a, true, target);
              AnnotationErrorType type;
              if ((b.getBegin() == a.getBegin() && b.getEnd() < a.getEnd())
                      || (b.getBegin() > a.getBegin() && b.getEnd() == a.getEnd())) {
                type = AnnotationErrorType.EXPANSION;
              } else if (b.getBegin() > a.getBegin() || b.getEnd() > a.getEnd()) {
                type = AnnotationErrorType.SHIFTING_LEFT;
              } else {
                type = AnnotationErrorType.SHIFTING_RIGHT;
              }
              AnnotationError err = new AnnotationError(error, truth, type);
              if (!tempErrors.contains(err))
                tempErrors.add(err);
            }
          }
        }
        if (tempErrors.size() > 0) {
          AnnotationError err = tempErrors.get(0);
          int begin = err.getError().getAnnotation().getBegin();
          int end = err.getError().getAnnotation().getEnd();
          distance = Math.abs(begin - a.getBegin()) + Math.abs(end - a.getEnd());
          for (int j = 1; j < tempErrors.size(); j++) {
            int begin2 = tempErrors.get(j).getError().getAnnotation().getBegin();
            int end2 = tempErrors.get(j).getError().getAnnotation().getEnd();
            if (Math.abs(begin2 - a.getBegin()) + Math.abs(end2 - a.getEnd()) < distance) {
              distance = Math.abs(begin2 - a.getBegin()) + Math.abs(end2 - a.getEnd());
              err = tempErrors.get(j);
            }
          }
          result.add(err);
          matches.add((TrabalAnnotation) err.getTruth().getAnnotation());
          matches.add((TrabalAnnotation) err.getError().getAnnotation());
        }
      }
      // creating shifting + correction errors
      iterator = gold.iterator();
      docIterator = docs.iterator();
      while (iterator.hasNext()) {
        a = iterator.next();
        docIterator = docs.iterator();
        tempErrors = new ArrayList<AnnotationError>();
        while (!matches.contains(a) && docIterator.hasNext()) {
          b = docIterator.next();
          if (!matches.contains(b)) {
            if (b.getEnd() >= a.getBegin() && b.getBegin() <= a.getEnd()) {
              TextRulerTarget target = new TextRulerTarget(b.getType().getName(), this);
              TextRulerExample error = new TextRulerExample(b.getDocument(), b, false, target);
              TextRulerExample truth = new TextRulerExample(a.getDocument(), a, true, target);
              AnnotationErrorType type;
              if ((b.getBegin() == a.getBegin() && b.getEnd() < a.getEnd())
                      || (b.getBegin() > a.getBegin() && b.getEnd() == a.getEnd())) {
                type = AnnotationErrorType.EXPANSION;
              } else if (b.getBegin() > a.getBegin() || b.getEnd() > a.getEnd()) {
                type = AnnotationErrorType.SHIFTING_LEFT;
              } else {
                type = AnnotationErrorType.SHIFTING_RIGHT;
              }
              AnnotationError err = new AnnotationError(error, truth, type);
              if (!tempErrors.contains(err))
                tempErrors.add(err);
            }
          }
        }
        if (tempErrors.size() > 0) {
          AnnotationError err = tempErrors.get(0);
          int begin = err.getError().getAnnotation().getBegin();
          int end = err.getError().getAnnotation().getEnd();
          distance = Math.abs(begin - a.getBegin()) + Math.abs(end - a.getEnd());
          for (int j = 1; j < tempErrors.size(); j++) {
            int begin2 = tempErrors.get(j).getError().getAnnotation().getBegin();
            int end2 = tempErrors.get(j).getError().getAnnotation().getEnd();
            if (Math.abs(begin2 - a.getBegin()) + Math.abs(end2 - a.getEnd()) < distance) {
              distance = Math.abs(begin2 - a.getBegin()) + Math.abs(end2 - a.getEnd());
              err = tempErrors.get(j);
            }
          }
          result.add(err);
          matches.add((TrabalAnnotation) err.getTruth().getAnnotation());
          matches.add((TrabalAnnotation) err.getError().getAnnotation());
        }
      }
      // create deleting type errors
      docIterator = docs.iterator();
      while (docIterator.hasNext()) {
        b = docIterator.next();
        if (!matches.contains(b)) {
          TextRulerTarget target = new TextRulerTarget(b.getType().getName(), this);
          TextRulerExample error = new TextRulerExample(b.getDocument(), b, false, target);
          result.add(new AnnotationError(error, null, AnnotationErrorType.DELETION));
          matches.add(b);
        }
      }
      // create annotation type errors
      iterator = gold.iterator();
      while (iterator.hasNext()) {
        a = iterator.next();
        if (!matches.contains(a)) {
          TextRulerTarget target = new TextRulerTarget(a.getType().getName(), this);
          TextRulerExample truth = new TextRulerExample(a.getDocument(), a, true, target);
          result.add(new AnnotationError(null, truth, AnnotationErrorType.ANNOTATION));
          matches.add(a);
        }
      }
    }
    return result;
  }

  private List<TrabalRule> runAlgorithm(Map<String, List<AnnotationError>> errorGrps) {
    removeBasics();
    inducedRules.clear();

    List<TrabalRule> rules = new ArrayList<TrabalRule>();
    bestRulesForStatus.clear();
    int i = 1;
    for (List<AnnotationError> each : errorGrps.values()) {
      if (shouldAbort()) {
        break;
      }
      Collections.sort(each);
      String status = each.get(0).toString();
      sendStatusUpdateToDelegate("Creating basic rules: " + status,
              TextRulerLearnerState.ML_RUNNING, false);
      List<TrabalRule> basicRules = removeDuplicateRules(createBasicRules(each));
      if (basicRules.size() > maxNumberOfBasicRules) {
        basicRules = basicRules.subList(0, maxNumberOfBasicRules);
      }
      sendStatusUpdateToDelegate("Testing basic rules: " + status, TextRulerLearnerState.ML_RUNNING,
              false);
      basicRules = testTrabalRulesOnDocumentSet(basicRules, exampleDocuments, additionalDocuments,
              "basic rules (" + i + " of " + errorGrps.size() + ")");
      if (basicRules.size() > 0) {
        Collections.sort(basicRules, basicComparator);
        bestRulesForStatus.add(basicRules.get(0));
      }
      result = actualResult + getRuleStrings(bestRulesForStatus);
      sendStatusUpdateToDelegate("Testing basic rules: " + status, TextRulerLearnerState.ML_RUNNING,
              true);
      List<TrabalRule> learntRules = new ArrayList<TrabalRule>();
      for (TrabalRule rule : basicRules) {
        if (rule.getCoveringStatistics().getCoveredPositivesCount() > 0
                && rule.getCoveringStatistics().getCoveredNegativesCount() == 0) {
          learntRules.add(rule);
        }
      }
      // Collections.sort(learntRules, basicComparator);
      List<TrabalRule> enhancedRules = new ArrayList<TrabalRule>();
      int rank = 1;
      for (TrabalRule rule : basicRules) {
        if (rule.getCoveringStatistics().getCoveredPositivesCount() > 0
                && rule.getCoveringStatistics().getCoveredNegativesCount() > 0) {
          if (learntRules.size() > 0) {
            if (rule.getCoveringStatistics().getCoveredPositivesCount() > learntRules.get(0)
                    .getCoveringStatistics().getCoveredPositivesCount()) {
              rule.setRating(rank);
              enhancedRules.add(rule);
              rank++;
            }
          } else {
            rule.setRating(rank);
            enhancedRules.add(rule);
            rank++;
          }
        }
      }
      basicRules.clear();
      try {
        enhancedRules = enhanceRules(enhancedRules, maxNumberOfIterations, new RankedList(idf));
        Collections.sort(enhancedRules);
        if (enhancedRules.size() > maxNumberOfRules) {
          enhancedRules = enhancedRules.subList(0, maxNumberOfRules);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      sendStatusUpdateToDelegate("Testing enhanced rules: " + status,
              TextRulerLearnerState.ML_RUNNING, false);
      enhancedRules = testTrabalRulesOnDocumentSet(enhancedRules, exampleDocuments,
              additionalDocuments, "enhanced rules (" + i + " of " + errorGrps.size() + ")");
      for (TrabalRule rule : enhancedRules) {
        if (rule.getErrorRate() <= maxErrorRate) {
          learntRules.add(rule);
        }
      }
      enhancedRules.clear();
      learntRules = removeDuplicateRules(learntRules);
      if (learntRules.size() > 0) {
        Collections.sort(learntRules, enhancedComparator);
        bestRulesForStatus.remove(bestRulesForStatus.size() - 1); // TODO
        bestRulesForStatus.add(learntRules.get(0));
        result = actualResult + getRuleStrings(bestRulesForStatus);
      }
      sendStatusUpdateToDelegate("Testing optimized rules: " + status,
              TextRulerLearnerState.ML_RUNNING, true);
      if (learntRules.size() > maxNumberOfRules)
        learntRules = learntRules.subList(0, maxNumberOfRules);
      rules.addAll(learntRules);
      i++;
    }
    return getBest(rules);
  }

  private List<TrabalRule> enhanceRules(List<TrabalRule> learntRules, int iterations,
          RankedList conditions) throws Exception {
    if (shouldAbort() || learntRules.size() == 0)
      return learntRules;
    sendStatusUpdateToDelegate("Adding conditions to rules (" + iterations + ". Iteration)... ",
            TextRulerLearnerState.ML_RUNNING, false);
    if (iterations > 0) {
      List<TrabalRule> rules = new ArrayList<TrabalRule>();
      List<TrabalRule> newRules = new ArrayList<TrabalRule>();
      if (conditions.size() == 0)
        conditions = createConditions(learntRules);
      for (int i = 0; i < learntRules.size(); i++) {
        rules.add(learntRules.get(i));
        // TODO amount of conditions? parameter for 50!
        for (int j = 0; j < conditions.size() && j < 50; j++) {
          TrabalRule newRule = learntRules.get(i).copy();
          if (!newRule.getConditions().contains(conditions.get(j))) {
            newRule.addCondition(conditions.get(j), (j + 1));
            newRule.getRuleString();
            newRules.add(newRule);
          }
        }
      }
      rules.addAll(enhanceRules(newRules, iterations - 1, conditions));
      return rules;
    }
    return learntRules;
  }

  private List<TrabalRule> getBest(List<TrabalRule> rules) {
    List<TrabalRule> result = new ArrayList<TrabalRule>();
    Collections.sort(rules, enhancedComparator);
    while (rules.size() > 0) {
      List<TrabalRule> testRules = new ArrayList<TrabalRule>();
      List<TrabalRule> newRules = new ArrayList<TrabalRule>();
      TrabalRule bestRule = rules.get(0);
      if (bestRule.getErrorRate() > maxErrorRate) {
        rules.remove(0);
        continue;
      }
      if (bestRule.getCoveringStatistics().getCoveredPositivesCount() == 0) {
        break;
      }
      sendStatusUpdateToDelegate("Chosen rule: " + bestRule, TextRulerLearnerState.ML_RUNNING,
              false);
      result.add(bestRule);
      rules.remove(0);
      Set<TextRulerExample> coveredExamples = bestRule.getCoveringStatistics()
              .getCoveredPositiveExamples();
      for (TrabalRule rule : rules) {
        TextRulerStatisticsCollector collector = rule.getCoveringStatistics();
        Iterator<TextRulerExample> iterator = coveredExamples.iterator();
        while (iterator.hasNext()) {
          TextRulerExample example = iterator.next();
          if (collector.getCoveredPositiveExamples().contains(example)) {
            collector.getCoveredPositiveExamples().remove(example);
            // if (rule.getErrorType() == ErrorType.ANNOTATION
            // || rule.getErrorType() == ErrorType.DELETING) {
            // collector.incCoveredPositives(-1);
            // } else {
            collector.incCoveredPositives(-1);
            // }
          }
          if (collector.getCoveredPositivesCount() <= 0) {
            break;
          }
        }
        if (rule.getErrorRate() > maxErrorRate
                || rule.getCoveringStatistics().getCoveredPositivesCount() <= 0) {
          if (bestRulesForStatus.contains(rule))
            bestRulesForStatus.remove(rule);
          this.result = actualResult + getRuleStrings(bestRulesForStatus);
          continue;
        } else if (rule.contains(bestRule.getAnnotation())
                || rule.contains(bestRule.getTargetAnnotation())) {
          testRules.add(rule.copy());
        } else {
          newRules.add(rule);
        }
      }
      rules = new ArrayList<TrabalRule>();
      updateDocumentData(bestRule);
      removeBasics();
      testRules = testTrabalRulesOnDocumentSet(testRules, exampleDocuments, additionalDocuments,
              "final rules ");
      for (TrabalRule rule : testRules) {
        if (rule.getErrorRate() <= maxErrorRate
                && rule.getCoveringStatistics().getCoveredPositivesCount() > 0) {
          rules.add(rule);
        }
      }
      rules.addAll(newRules);
      Collections.sort(rules, enhancedComparator);
    }
    this.result = actualResult;
    return result;
  }

  private void updateDocumentData(TrabalRule rule) {
    try {
      sendStatusUpdateToDelegate("Writing rules...", TextRulerLearnerState.ML_RUNNING, false);
      rule.saveToRulesFile(getTempRulesFileName());
      for (TextRulerExampleDocument doc : additionalDocuments.getDocuments()) {
        ae.process(doc.getCAS());
      }
      if (bestRulesForStatus.contains(rule)) {
        bestRulesForStatus.remove(rule);
      }
      actualResult += rule.toString();
      result = actualResult + getRuleStrings(bestRulesForStatus);
      sendStatusUpdateToDelegate("Writing rules...", TextRulerLearnerState.ML_RUNNING, true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List<TrabalRule> createBasicRules(List<AnnotationError> errors) {
    List<TrabalRule> result = new ArrayList<TrabalRule>();
    for (AnnotationError each : errors) {
      switch (each.getType()) {
        case SHIFTING_LEFT:
        case SHIFTING_RIGHT:
        case EXPANSION:
          result.addAll(createShiftingRules(each));
          break;
        case CORRECTION:
          result.addAll(createCorrectionRules(each));
          break;
        case ANNOTATION:
          result.addAll(createAnnotationRules(each));
          break;
        case DELETION:
          result.addAll(createDeletionRules(each));
          break;
      }
    }
    return getBestBasicRule(result);
  }

  private List<TrabalRule> getBestBasicRule(List<TrabalRule> rules) {
    if (rules.size() < maxNumberOfBasicRules)
      return rules;
    List<TrabalRule> result = new ArrayList<TrabalRule>();
    sendStatusUpdateToDelegate("Choosing basic rules... ", TextRulerLearnerState.ML_RUNNING, false);
    Map<Integer, Integer> distribution = new HashMap<Integer, Integer>();
    for (TrabalRule rule : rules) {
      Integer key = new Integer(rule.hashCode());
      if (distribution.containsKey(key)) {
        distribution.put(key, new Integer(distribution.get(key).intValue() + 1));
      } else {
        distribution.put(key, new Integer(1));
      }
    }
    List<TrabalRule> newRules;
    while (result.size() < maxNumberOfBasicRules && distribution.size() > 0) {
      if (shouldAbort())
        break;
      int max = 0;
      Integer maxKey = new Integer(0);
      for (Integer key : distribution.keySet()) {
        if (distribution.get(key).intValue() > max) {
          max = distribution.get(key).intValue();
          maxKey = key;
        }
      }
      distribution.remove(maxKey);
      newRules = new ArrayList<TrabalRule>();
      TrabalRule example = null;
      for (TrabalRule rule : rules) {
        Integer key = new Integer(rule.hashCode());
        if (distribution.containsKey(key)) {
          if (distribution.get(key).intValue() == max) {
            result.add(rule);
            if (example == null)
              example = rule;
          }
        }
      }
      for (TrabalRule rule : rules) {
        newRules.add(rule);
      }
      rules = newRules;
    }
    if (result.size() > maxNumberOfBasicRules)
      return result.subList(0, maxNumberOfBasicRules);
    return result;
  }

  private List<TrabalRule> createShiftingRules(AnnotationError each) {
    List<TrabalRule> result = new ArrayList<TrabalRule>();
    TextRulerExample error = each.getError();
    TextRulerExample truth = each.getTruth();
    int errorBegin = error.getAnnotation().getBegin();
    int errorEnd = error.getAnnotation().getEnd();
    int truthBegin = truth.getAnnotation().getBegin();
    int truthEnd = truth.getAnnotation().getEnd();

    if ((errorBegin > truthBegin && errorEnd > truthEnd) // shift both borders to left
            || (errorBegin < truthBegin && errorEnd < truthEnd)) { // shift both borders to right
      result.addAll(createShiftAllRules(error, truth));
    } else if (errorBegin > truthBegin // expand to left
            || errorEnd < truthEnd) { // expand to right
      result.addAll(createExpansionRules(error, truth));
    } else {
      result.addAll(createShiftAllRules(error, truth)); // reduce the annotation
    }
    return result;
  }

  private List<TrabalRule> createShiftAllRules(TextRulerExample error, TextRulerExample truth) {
    List<TrabalRule> result = new ArrayList<TrabalRule>();
    TextRulerExampleDocument document = error.getDocument();
    CAS cas = error.getDocumentCAS();
    List<TrabalRuleItem> truthLeftBorder = getBorderOfExample(truth, document, cas, true);
    List<TrabalRuleItem> truthRightBorder = getBorderOfExample(truth, document, cas, false);
    List<TrabalRuleItem> errorLeftBorder = getBorderOfExample(error, document, cas, true);
    TextRulerTarget target = error.getTarget();
    TrabalAnnotation errorAnnotation = (TrabalAnnotation) error.getAnnotation();
    TrabalAnnotation truthAnnotation = (TrabalAnnotation) truth.getAnnotation();
    // boolean shiftToLeft = error.getAnnotation().getBegin() > truth.getAnnotation().getBegin();
    // if (shiftToLeft) {
    for (TrabalRuleItem a : truthLeftBorder) {
      for (TrabalRuleItem b : errorLeftBorder) {
        for (TrabalRuleItem c : truthRightBorder) {
          ShiftAllRule newRule;
          if (truth.getAnnotation().getEnd() < error.getAnnotation().getEnd())
            newRule = new ShiftAllRule(this, target, errorAnnotation, truthAnnotation,
                    AnnotationErrorType.SHIFTING_LEFT);
          else
            newRule = new ShiftAllRule(this, target, errorAnnotation, truthAnnotation,
                    AnnotationErrorType.SHIFTING_RIGHT);
          newRule.setFrontBoundaryItem(a);
          if (b.getAnnotation().getBegin() != a.getAnnotation().getBegin()) {
            newRule.setErrorBoundaryItem(b);
          }
          newRule.setRearBoundaryItem(c);
          result.add(newRule);
        }
      }
    }
    // } else {
    // for (TrabalRuleItem a : errorLeftBorder) {
    // for (TrabalRuleItem b : truthLeftBorder) {
    // for (TrabalRuleItem c : truthRightBorder) {
    // ShiftingRule newRule = new ShiftingRule(this, target, errorAnnotation, truthAnnotation,
    // AnnotationErrorType.SHIFTING_RIGHT);
    // newRule.addFillerItem(a);
    // if (b.getAnnotation().getBegin() != a.getAnnotation().getBegin()) {
    // newRule.addFillerItem(b);
    // }
    // newRule.addFillerItem(c);
    // result.add(newRule);
    // }
    // }
    // }
    // }
    return result;
  }

  private List<TrabalRule> createExpansionRules(TextRulerExample error, TextRulerExample truth) {
    List<TrabalRule> result = new ArrayList<TrabalRule>();
    TextRulerExampleDocument document = error.getDocument();
    CAS cas = error.getDocumentCAS();
    boolean shiftToLeft = error.getAnnotation().getBegin() > truth.getAnnotation().getBegin();
    List<TrabalRuleItem> border;
    if (shiftToLeft) {
      border = getBorderOfExample(truth, document, cas, true);
    } else {
      border = getBorderOfExample(truth, document, cas, false);
    }
    for (TrabalRuleItem item : border) {
      ExpansionRule newRule = new ExpansionRule(this, error.getTarget(),
              (TrabalAnnotation) error.getAnnotation(), (TrabalAnnotation) truth.getAnnotation(),
              AnnotationErrorType.EXPANSION);
      if (shiftToLeft)
        newRule.setFrontBoundaryItem(item);
      else
        newRule.setRearBoundaryItem(item);
      if (!result.contains(newRule))
        result.add(newRule);
    }
    return result;
  }

  private List<TrabalRule> createAnnotationRules(AnnotationError each) {
    List<TrabalRule> result = new ArrayList<TrabalRule>();
    TextRulerExample truth = each.getTruth();
    TextRulerExampleDocument document = additionalDocuments.getDocuments()
            .get(exampleDocuments.getDocuments().indexOf(truth.getDocument()));
    CAS cas = document.getCAS();
    List<TrabalRuleItem> truthLeftBorder = getBorderOfExample(truth, document, cas, true);
    List<TrabalRuleItem> truthRightBorder = getBorderOfExample(truth, document, cas, false);
    TextRulerTarget target = truth.getTarget();
    TrabalAnnotation truthAnnotation = (TrabalAnnotation) truth.getAnnotation();
    for (TrabalRuleItem front : truthLeftBorder) {
      for (TrabalRuleItem rear : truthRightBorder) {
        AnnotationRule newRule = new AnnotationRule(this, target, truthAnnotation);
        newRule.setFrontBoundary(front);
        newRule.setRearBoundary(rear);
        if (!result.contains(newRule))
          result.add(newRule);
      }
    }
    return result;
  }

  private List<TrabalRule> createDeletionRules(AnnotationError each) {
    List<TrabalRule> result = new ArrayList<TrabalRule>();
    TextRulerExample error = each.getError();
    TrabalRule blankRule = new DeletionRule(this, error.getTarget(),
            (TrabalAnnotation) error.getAnnotation());
    result.add(blankRule);
    return result;
  }

  private List<TrabalRule> createCorrectionRules(AnnotationError each) {
    List<TrabalRule> result = new ArrayList<TrabalRule>();
    TextRulerExample error = each.getError();
    TextRulerExample truth = each.getTruth();
    TrabalRule blankRule = new CorrectionRule(this, error.getTarget(),
            (TrabalAnnotation) error.getAnnotation(), (TrabalAnnotation) truth.getAnnotation());
    result.add(blankRule);
    return result;
  }

  private List<TrabalRuleItem> getBorderOfExample(TextRulerExample example,
          TextRulerExampleDocument document, CAS cas, boolean examineLeftBorder) {
    List<TrabalRuleItem> result = new ArrayList<TrabalRuleItem>();
    int begin = example.getAnnotation().getBegin();
    int end = example.getAnnotation().getEnd();
    TrabalRuleItem ruleItem = new TrabalRuleItem(begin, end, cas, enableFeatures);
    Type frameType = cas.getTypeSystem().getType(ANNOTATION_TYPE_FRAME);
    AnnotationFS pointer = cas.createAnnotation(frameType, begin, end);
    FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex().subiterator(pointer);
    if (examineLeftBorder) {
      result.addAll(getTermsBefore(ruleItem, document));
      iterator.moveToFirst();
      while (iterator.isValid()) {
        FeatureStructure fs = iterator.get();
        AnnotationFS a = (AnnotationFS) fs;
        if (a.getBegin() == begin && a.getEnd() < end) {
          if (!filterSet.contains(a.getType().getName())
                  && !a.getType().getName().equals(ANNOTATION_TYPE_BASIC)) {
            result.add(new TrabalRuleItem(new TrabalAnnotation(a, document, enableFeatures)));
          }
        } else {
          if (a.getBegin() > begin) {
            break;
          }
        }
        iterator.moveToNext();
      }
    } else {
      iterator.moveToLast();
      while (iterator.isValid()) {
        FeatureStructure fs = iterator.get();
        AnnotationFS a = (AnnotationFS) fs;
        if (a.getBegin() > begin && a.getEnd() == end) {
          if (!filterSet.contains(a.getType().getName())
                  && !a.getType().getName().equals(ANNOTATION_TYPE_BASIC)) {
            result.add(new TrabalRuleItem(new TrabalAnnotation(a, document, enableFeatures)));
          }
        } else {
          break;
        }
        iterator.moveToPrevious();
      }
      result.addAll(getTermsAfter(ruleItem, document));
    }
    return result;
  }

  public RankedList createConditions(List<TrabalRule> rules) {
    if (rules.size() == 0)
      return new RankedList(idf);
    RankedList result;
    RankedList error = new RankedList(idf);
    RankedList truth = new RankedList(idf);
    switch (rules.get(0).getErrorType()) {
      case SHIFTING_LEFT:
      case SHIFTING_RIGHT:
      case EXPANSION:
        for (TrabalRule rule : rules) {
          error.addAll(createConditions(rule.getAnnotation()));
          truth.addAll(createConditions(rule.getTargetAnnotation()));
        }
        result = error.subtract(
                truth.unite(getPositiveExamplesFor(rules.get(0).getTargetAnnotation().getType())));
        setNegative(result);
        result.addAll(
                truth.cut(getPositiveExamplesFor(rules.get(0).getTargetAnnotation().getType()))
                        .subtract(error));
        return result;
      case CORRECTION:
        for (TrabalRule rule : rules) {
          truth.addAll(createConditions(rule.getAnnotation()));
        }
        result = getPositiveExamplesFor(rules.get(0).getAnnotation().getType()).subtract(
                truth.unite(getPositiveExamplesFor(rules.get(0).getTargetAnnotation().getType())));
        setNegative(result);
        if (getPositiveExamplesFor(rules.get(0).getTargetAnnotation().getType()).size() > 0) {
          result.addAll(truth
                  .cut(getPositiveExamplesFor(rules.get(0).getTargetAnnotation().getType()))
                  .subtract(getPositiveExamplesFor(rules.get(0).getAnnotation().getType())));
        } else {
          result.addAll(truth);
        }
        return result;
      case ANNOTATION:
        for (TrabalRule rule : rules) {
          truth.addAll(createConditions(rule.getFrontBoundary(), rule.getRearBoundary(),
                  rule.getTargetAnnotation()));
        }
        if (getPositiveExamplesFor(rules.get(0).getTargetAnnotation().getType()).size() > 0) {
          return truth.cut(getPositiveExamplesFor(rules.get(0).getTargetAnnotation().getType()));
        }
        return truth;
      case DELETION:
        for (TrabalRule rule : rules) {
          error.addAll(createConditions(rule.getAnnotation()));
        }
        result = getPositiveExamplesFor(rules.get(0).getAnnotation().getType()).subtract(error);
        setNegative(result);
        result.addAll(createConditions(rules.get(0).getAnnotation())
                .subtract(getPositiveExamplesFor(rules.get(0).getAnnotation().getType())));
        return result;
    }
    return null;
  }

  public RankedList getPositiveExamplesFor(Type type) {
    if (positiveExamples.containsKey(type.getShortName())) {
      return positiveExamples.get(type.getShortName());
    } else {
      return new RankedList(idf);
    }
  }

  public void setNegative(RankedList list) {
    for (Condition element : list.getRanking().keySet()) {
      element.setNegative();
    }
    for (Condition element : list) {
      element.setNegative();
    }
  }

  private RankedList createConditions(TrabalRuleItem frontBoundary, TrabalRuleItem rearBoundary,
          TrabalAnnotation truth) {
    RankedList result = new RankedList(idf);
    TextRulerExampleDocument doc = additionalDocuments.getDocuments()
            .get(exampleDocuments.getDocuments().indexOf(truth.getDocument()));
    CAS cas = doc.getCAS();
    for (TrabalRuleItem item : getTermsBefore(frontBoundary, doc)) {
      result.add(new Condition(ConditionType.AFTER, item));
    }
    for (TrabalRuleItem item : getTermsAfter(rearBoundary, doc)) {
      result.add(new Condition(ConditionType.BEFORE, item));
    }
    for (TrabalRuleItem item : getConsumingTerms(frontBoundary, doc)) {
      if (!item.getAnnotation().getType().equals(cas.getDocumentAnnotation().getType())) {
        result.add(new Condition(ConditionType.PARTOF, item));
      }
    }
    if (frontBoundary.getAnnotation().getBegin() < truth.getBegin()) {
      for (TrabalRuleItem item : getTermsAfter(frontBoundary, doc)) {
        result.add(new Condition(ConditionType.STARTSWITH, item));
      }
    } else {
      for (List<TrabalRuleItem> list : getFirstTermsWithinBounds(
              frontBoundary.getAnnotation().getBegin(), rearBoundary.getAnnotation().getEnd(), doc,
              cas, 1)) {
        result.add(new Condition(ConditionType.STARTSWITH, list.get(0)));
      }
    }
    if (rearBoundary.getAnnotation().getEnd() > truth.getEnd()) {
      for (TrabalRuleItem item : getTermsBefore(rearBoundary, doc)) {
        result.add(new Condition(ConditionType.ENDSWITH, item));
      }
    } else {
      for (List<TrabalRuleItem> list : getLastTermsWithinBounds(
              rearBoundary.getAnnotation().getBegin(), frontBoundary.getAnnotation().getEnd(), doc,
              cas, 1)) {
        result.add(new Condition(ConditionType.ENDSWITH, list.get(0)));
      }
    }
    return result;
  }

  private RankedList createConditions(TrabalAnnotation annotation) {
    RankedList result = new RankedList(idf);
    TrabalRuleItem ruleItem = new TrabalRuleItem(annotation);
    TextRulerExampleDocument doc = annotation.getDocument();
    CAS cas = doc.getCAS();
    for (TrabalRuleItem item : getTermsBefore(ruleItem, doc)) {
      result.add(new Condition(ConditionType.AFTER, item));
    }
    for (TrabalRuleItem item : getTermsAfter(ruleItem, doc)) {
      result.add(new Condition(ConditionType.BEFORE, item));
    }
    for (TrabalRuleItem item : getConsumingTerms(ruleItem, doc)) {
      if (!item.getAnnotation().getType().equals(cas.getDocumentAnnotation().getType())) {
        result.add(new Condition(ConditionType.PARTOF, item));
      }
    }
    for (List<TrabalRuleItem> list : getFirstTermsWithinBounds(ruleItem.getAnnotation().getBegin(),
            ruleItem.getAnnotation().getEnd(), doc, cas, 1)) {
      result.add(new Condition(ConditionType.STARTSWITH, list.get(0)));
    }
    for (List<TrabalRuleItem> list : getLastTermsWithinBounds(ruleItem.getAnnotation().getBegin(),
            ruleItem.getAnnotation().getEnd(), doc, cas, 1)) {
      result.add(new Condition(ConditionType.ENDSWITH, list.get(0)));
    }
    for (TrabalRuleItem item : getSingleTermsWithinBounds(ruleItem.getAnnotation().getBegin(),
            ruleItem.getAnnotation().getEnd(), doc, cas)) {
      result.add(new Condition(ConditionType.CONTAINS, item));
    }
    return result;
  }

  private List<TrabalRuleItem> getTermsBefore(TrabalRuleItem ruleItem,
          TextRulerExampleDocument document) {
    List<TrabalRuleItem> result = new ArrayList<TrabalRuleItem>();
    int begin = ruleItem.getAnnotation().getBegin();
    CAS cas = document.getCAS();
    Type frameType = cas.getTypeSystem().getType(ANNOTATION_TYPE_FRAME);
    AnnotationFS pointer = cas.createAnnotation(frameType, begin, begin);
    FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex().iterator(pointer);
    int nextEnd = -1;

    while (iterator.isValid()) {
      FeatureStructure fs = iterator.get();
      if (fs instanceof AnnotationFS) {
        AnnotationFS a = (AnnotationFS) fs;
        if (!filterSet.contains(a.getType().getName())
                && !a.getType().getName().equals(ANNOTATION_TYPE_BASIC)) {
          if (a.getEnd() > begin) {
            iterator.moveToPrevious();
            continue;
          }
          if (nextEnd == -1) {
            nextEnd = a.getEnd();
          }
          if (a.getEnd() >= nextEnd && a.getEnd() <= begin) {
            TrabalRuleItem term = new TrabalRuleItem(
                    new TrabalAnnotation(a, document, enableFeatures));
            result.add(term);
          }
        }
      }
      iterator.moveToPrevious();
    }
    return result;
  }

  private List<TrabalRuleItem> getTermsAfter(TrabalRuleItem ruleItem,
          TextRulerExampleDocument document) {
    List<TrabalRuleItem> result = new ArrayList<TrabalRuleItem>();
    int end = ruleItem.getAnnotation().getEnd();
    CAS cas = document.getCAS();
    Type frameType = cas.getTypeSystem().getType(ANNOTATION_TYPE_FRAME);
    AnnotationFS pointer = cas.createAnnotation(frameType, end, Integer.MAX_VALUE);
    FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex().iterator(pointer);
    int nextBegin = -1;
    while (iterator.isValid()) {
      FeatureStructure fs = iterator.get();
      if (fs instanceof AnnotationFS) {
        AnnotationFS a = (AnnotationFS) fs;
        if (!filterSet.contains(a.getType().getName())
                && !a.getType().getName().equals(ANNOTATION_TYPE_BASIC)) {
          if (nextBegin == -1) {
            nextBegin = a.getBegin();
          }
          if (a.getBegin() <= nextBegin && a.getBegin() >= end) {
            TrabalRuleItem term = new TrabalRuleItem(
                    new TrabalAnnotation(a, document, enableFeatures));
            result.add(term);
          }
        }
      }
      iterator.moveToNext();
    }
    return result;
  }

  private List<List<TrabalRuleItem>> getFirstTermsWithinBounds(int startPos, int endPos,
          TextRulerExampleDocument document, CAS cas, int numberOfSideItems) {
    List<List<TrabalRuleItem>> preItems = new ArrayList<List<TrabalRuleItem>>();
    Type frameType = cas.getTypeSystem().getType(ANNOTATION_TYPE_FRAME);
    AnnotationFS pointer = cas.createAnnotation(frameType, startPos, endPos);
    FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex().iterator(pointer);
    List<AnnotationFS> startAs = new ArrayList<AnnotationFS>();
    int firstBegin = -1;
    while (iterator.isValid()) {
      FeatureStructure fs = iterator.get();
      AnnotationFS a = (AnnotationFS) fs;

      if (a.getBegin() >= startPos && a.getEnd() <= endPos) {
        if (!filterSet.contains(a.getType().getName())
                && !a.getType().getName().equals(ANNOTATION_TYPE_BASIC)) {
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

    for (AnnotationFS each : startAs) {
      List<TrabalRuleItem> startList = new ArrayList<TrabalRuleItem>();
      TrabalRuleItem term = new TrabalRuleItem(
              new TrabalAnnotation(each, document, enableFeatures));
      startList.add(term);
      preItems.add(startList);
    }
    preItems = addFollowing(preItems, endPos, document, cas, 1, numberOfSideItems);
    return preItems;
  }

  private List<List<TrabalRuleItem>> getLastTermsWithinBounds(int startPos, int endPos,
          TextRulerExampleDocument document, CAS cas, int numberOfSideItems) {
    List<List<TrabalRuleItem>> postItems = new ArrayList<List<TrabalRuleItem>>();
    Type frameType = cas.getTypeSystem().getType(ANNOTATION_TYPE_FRAME);
    AnnotationFS pointer = cas.createAnnotation(frameType, startPos, endPos);
    FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex().iterator(pointer);
    iterator.moveToLast();
    List<AnnotationFS> endAs = new ArrayList<AnnotationFS>();
    int lastEnd = -1;
    while (iterator.isValid()) {
      FeatureStructure fs = iterator.get();
      AnnotationFS a = (AnnotationFS) fs;

      if (a.getEnd() <= endPos && a.getBegin() >= startPos) {
        if (!filterSet.contains(a.getType().getName())
                && !a.getType().getName().equals(ANNOTATION_TYPE_BASIC)) {
          if (lastEnd == -1) {
            lastEnd = a.getEnd();
          }
          if (a.getEnd() == lastEnd)
            endAs.add(a);
        }
        iterator.moveToPrevious();
      } else {
        iterator.moveToPrevious();
      }
    }

    for (AnnotationFS each : endAs) {
      List<TrabalRuleItem> endList = new ArrayList<TrabalRuleItem>();
      TrabalRuleItem term = new TrabalRuleItem(
              new TrabalAnnotation(each, document, enableFeatures));
      endList.add(term);
      postItems.add(endList);
    }
    postItems = addPreceding(postItems, startPos, document, cas, 1, numberOfSideItems);
    return postItems;
  }

  private List<List<TrabalRuleItem>> addPreceding(List<List<TrabalRuleItem>> lists, int till,
          TextRulerExampleDocument document, CAS cas, int index, int maxNumberOfItems) {
    if (index >= maxNumberOfItems) {
      return lists;
    }
    List<List<TrabalRuleItem>> result = new ArrayList<List<TrabalRuleItem>>();
    for (List<TrabalRuleItem> list : lists) {
      if (shouldAbort()) {
        break;
      }
      List<List<TrabalRuleItem>> newLists = new ArrayList<List<TrabalRuleItem>>();
      TrabalRuleItem first = list.get(0);
      List<TrabalRuleItem> termsBefore = getTermsBefore(first, document);
      if (termsBefore.isEmpty()) {
        continue;
      }
      for (TrabalRuleItem eachBefore : termsBefore) {
        if (eachBefore.getAnnotation().getBegin() >= till) {
          List<TrabalRuleItem> newList = new ArrayList<TrabalRuleItem>();
          newList.addAll(list);
          newList.add(0, eachBefore);
          newLists.add(newList);
          newLists = addPreceding(newLists, till, document, cas, index + 1, maxNumberOfItems);
        }
      }
      result.addAll(newLists);
    }
    if (result.isEmpty()) {
      return lists;
    }
    return result;
  }

  private List<List<TrabalRuleItem>> addFollowing(List<List<TrabalRuleItem>> lists, int till,
          TextRulerExampleDocument document, CAS cas, int index, int maxNumberOfItems) {
    if (index >= maxNumberOfItems) {
      return lists;
    }
    List<List<TrabalRuleItem>> result = new ArrayList<List<TrabalRuleItem>>();
    for (List<TrabalRuleItem> list : lists) {
      if (shouldAbort()) {
        break;
      }
      List<List<TrabalRuleItem>> newLists = new ArrayList<List<TrabalRuleItem>>();
      TrabalRuleItem last = list.get(list.size() - 1);
      List<TrabalRuleItem> termsAfter = getTermsAfter(last, document);
      if (termsAfter.isEmpty()) {
        continue;
      }
      for (TrabalRuleItem eachAfter : termsAfter) {
        if (eachAfter.getAnnotation().getEnd() <= till) {
          List<TrabalRuleItem> newList = new ArrayList<TrabalRuleItem>();
          newList.addAll(list);
          newList.add(eachAfter);
          newLists.add(newList);
          newLists = addFollowing(newLists, till, document, cas, index + 1, maxNumberOfItems);
        }
      }
      result.addAll(newLists);
    }
    if (result.isEmpty()) {
      return lists;
    }
    return result;
  }

  private List<TrabalRuleItem> getConsumingTerms(TrabalRuleItem ruleItem,
          TextRulerExampleDocument document) {
    CAS cas = document.getCAS();
    List<TrabalRuleItem> result = new ArrayList<TrabalRuleItem>();
    int begin = ruleItem.getAnnotation().getBegin();
    Type frameType = cas.getTypeSystem().getType(ANNOTATION_TYPE_FRAME);
    AnnotationFS pointer = cas.createAnnotation(frameType, 0, begin);
    FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex().iterator(pointer);
    while (iterator.hasNext()) {
      TrabalAnnotation annotation = new TrabalAnnotation(iterator.next(), enableFeatures);
      if (annotation.getBegin() >= begin) {
        break;
      }
      if (annotation.getEnd() >= ruleItem.getAnnotation().getEnd()) {
        result.add(new TrabalRuleItem(annotation));
      }
    }
    return result;
  }

  private List<TrabalRuleItem> getSingleTermsWithinBounds(int begin, int end,
          TextRulerExampleDocument doc, CAS cas) {
    Set<TrabalRuleItem> result = new HashSet<TrabalRuleItem>();
    Type frameType = cas.getTypeSystem().getType(ANNOTATION_TYPE_FRAME);
    AnnotationFS pointer = cas.createAnnotation(frameType, begin, end);
    FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex().iterator(pointer);
    while (iterator.hasNext()) {
      TrabalAnnotation annotation = new TrabalAnnotation(iterator.next(), enableFeatures);
      if (annotation.getBegin() > end) {
        break;
      }
      if (!filterSet.contains(annotation.getType().getName())
              && !annotation.getType().getName().equals(ANNOTATION_TYPE_BASIC)) {
        result.add(new TrabalRuleItem(annotation));
      }
    }
    return new ArrayList<TrabalRuleItem>(result);
  }

  public List<TrabalRule> testTrabalRulesOnDocumentSet(List<TrabalRule> rules,
          final TextRulerExampleDocumentSet documents,
          final TextRulerExampleDocumentSet additionalDocuments, String ruleSet) {

    if (rules.isEmpty())
      return rules;

    List<TextRulerStatisticsCollector> sums = new ArrayList<TextRulerStatisticsCollector>();
    for (TrabalRule each : rules) {
      sums.add(new TextRulerStatisticsCollector());
    }
    List<TextRulerExampleDocument> goldDocs;
    List<TextRulerExampleDocument> additionalDocs;
    goldDocs = documents.getDocuments();
    additionalDocs = additionalDocuments.getDocuments();
    CAS theTestCAS = getTestCAS();
    int counter = 0;
    for (TrabalRule rule : rules) {
      counter++;
      String ruleString = rule.getRuleString();
      String ruleInfo = getRuleInfo(rule);
      System.out.println("testing: " + ruleString);
      if (inducedRules.containsKey(ruleString)) {
        rule.setCoveringStatistics(inducedRules.get(ruleString));
        System.out.println("skipped with " + inducedRules.get(ruleString));
      } else {
        for (int i = 0; i < goldDocs.size(); i++) {
          TextRulerExampleDocument goldDoc = goldDocs.get(i);
          TextRulerExampleDocument additionalDoc = additionalDocs.get(i);
          sendStatusUpdateToDelegate(
                  "Testing " + ruleSet + ruleInfo + " on document " + (i + 1) + " of "
                          + goldDocs.size() + " : rule " + counter + " of " + rules.size(),
                  TextRulerLearnerState.ML_RUNNING, false);
          TextRulerStatisticsCollector sumC = new TextRulerStatisticsCollector();
          prepareTestCas(theTestCAS, goldDoc, additionalDoc);
          testRuleOnDocument(rule, goldDoc, additionalDoc, sumC, theTestCAS);
          sums.get(counter - 1).add(sumC);
          int n = sumC.getCoveredNegativesCount();
          int p = sumC.getCoveredPositivesCount();
          int pnorm = p;
          if (pnorm == 0) {
            pnorm = 1;
          }
          if (n / pnorm > maxErrorRate) {
            System.out.println("stopped:" + sumC);
            break;
          }

          if (shouldAbort())
            return rules;
        }
        TextRulerStatisticsCollector c = sums.get(counter - 1);
        rule.setCoveringStatistics(sums.get(counter - 1));
        inducedRules.put(ruleString, c);
      }
    }
    for (int ruleIndex = 0; ruleIndex < rules.size(); ruleIndex++) {
      rules.get(ruleIndex).setCoveringStatistics(sums.get(ruleIndex));
    }
//    GlobalCASSource.releaseCAS(theTestCAS);
    sums.clear();
    return rules;
  }

  private String getRuleInfo(TrabalRule rule) {
    String ruleInfo;
    if (rule.getAnnotation() != null && rule.getTargetAnnotation() != null) {
      ruleInfo = " " + rule.getAnnotation().getType().getShortName() + "("
              + rule.getAnnotation().getBegin() + "," + rule.getAnnotation().getEnd() + ") -> "
              + rule.getTargetAnnotation().getType().getShortName() + "("
              + rule.getTargetAnnotation().getBegin() + "," + rule.getTargetAnnotation().getEnd()
              + ")";
    } else if (rule.getTargetAnnotation() != null) {
      ruleInfo = " Annotate " + rule.getTargetAnnotation().getType().getShortName() + "("
              + rule.getTargetAnnotation().getBegin() + "," + rule.getTargetAnnotation().getEnd()
              + ")";
    } else {
      ruleInfo = " Delete " + rule.getAnnotation().getType().getShortName() + "("
              + rule.getAnnotation().getBegin() + "," + rule.getAnnotation().getEnd() + ")";
    }
    return ruleInfo;
  }

  private void prepareTestCas(CAS testCas, TextRulerExampleDocument goldDoc,
          TextRulerExampleDocument additionalDoc) {
    testCas.reset();
    CAS goldCas = goldDoc.getCAS();
    CAS additionalCas = additionalDoc.getCAS();
    testCas.setDocumentText(goldCas.getDocumentText());

    CasCopier.copyCas(additionalCas, testCas, testCas.getDocumentText() == null);

//    for (AnnotationFS fs : additionalCas.getAnnotationIndex()) {
//      Type t = testCas.getTypeSystem().getType(fs.getType().getName());
//      if (t != null) {
//        // TODO what about the features!!
//        AnnotationFS createAnnotation = testCas.createAnnotation(t, fs.getBegin(), fs.getEnd());
//        testCas.addFsToIndexes(createAnnotation);
//      } else {
//        TextRulerToolkit.log("Type " + fs.getType().getName() + "is unknown in test CAS");
//      }
//    }
  }

  private void testRuleOnDocument(final TrabalRule rule, final TextRulerExampleDocument goldDoc,
          final TextRulerExampleDocument additionalDoc, final TextRulerStatisticsCollector c,
          CAS testCas) {
    if (TextRulerToolkit.DEBUG) {
      MemoryWatch.watch();
    }
    try {
      rule.saveToRulesFile(getTempRulesFileName());
      if (TextRulerToolkit.DEBUG) {
        TextRulerToolkit.writeCAStoXMIFile(testCas, tempDirectory() + "testCas.xmi");
      }
      ae.process(testCas);
      removeBasics(testCas);
      if (TextRulerToolkit.DEBUG) {
        TextRulerToolkit.writeCAStoXMIFile(testCas, tempDirectory() + "testCasProcessed.xmi");
      }
      if (rule.getAnnotation() != null && rule.getTargetAnnotation() != null) {
        compareOriginalDocumentWithTestCAS(goldDoc, additionalDoc, testCas,
                new TextRulerTarget(rule.getAnnotation().getType().getName(), this), c, false);
        if (rule.getAnnotation().getType() != rule.getTargetAnnotation().getType()) {
          compareOriginalDocumentWithTestCAS(goldDoc, additionalDoc, testCas,
                  new TextRulerTarget(rule.getTargetAnnotation().getType().getName(), this), c,
                  false);
        }
      } else if (rule.getTargetAnnotation() != null) {
        compareOriginalDocumentWithTestCAS(goldDoc, additionalDoc, testCas,
                new TextRulerTarget(rule.getTargetAnnotation().getType().getName(), this), c,
                false);
      } else {
        compareOriginalDocumentWithTestCAS(goldDoc, additionalDoc, testCas,
                new TextRulerTarget(rule.getAnnotation().getType().getName(), this), c, false);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void compareOriginalDocumentWithTestCAS(TextRulerExampleDocument goldDoc,
          TextRulerExampleDocument additionalDoc, CAS testCas, TextRulerTarget target,
          TextRulerStatisticsCollector c, boolean collectNegativeExamples) {

    List<TextRulerExample> goldPositives = goldDoc.createSlotInstancesForCAS(goldDoc.getCAS(),
            target, true);
    List<TextRulerExample> additionalPositives = additionalDoc
            .createSlotInstancesForCAS(additionalDoc.getCAS(), target, true);
    List<TextRulerExample> testPositives = goldDoc.createSlotInstancesForCAS(testCas, target,
            false);

    List<TextRulerExample> baseFP = new ArrayList<TextRulerExample>();
    for (TextRulerExample e : additionalPositives) {
      TextRulerExample example = TextRulerToolkit.exampleListContainsAnnotation(goldPositives,
              e.getAnnotation());
      if (example == null) {
        baseFP.add(e);
      }
    }
    List<TextRulerExample> baseFN = new ArrayList<TextRulerExample>();
    for (TextRulerExample e : goldPositives) {
      TextRulerExample example = TextRulerToolkit.exampleListContainsAnnotation(additionalPositives,
              e.getAnnotation());
      if (example == null) {
        baseFN.add(e);
      }
    }

    List<TextRulerExample> testFP = new ArrayList<TextRulerExample>();
    for (TextRulerExample e : testPositives) {
      TextRulerExample example = TextRulerToolkit.exampleListContainsAnnotation(goldPositives,
              e.getAnnotation());
      if (example == null) {
        testFP.add(e);
      }
    }
    List<TextRulerExample> testFN = new ArrayList<TextRulerExample>();
    for (TextRulerExample e : goldPositives) {
      TextRulerExample example = TextRulerToolkit.exampleListContainsAnnotation(testPositives,
              e.getAnnotation());
      if (example == null) {
        testFN.add(e);
      }
    }

    for (TextRulerExample e : baseFP) {
      TextRulerExample example = TextRulerToolkit.exampleListContainsAnnotation(testFP,
              e.getAnnotation());
      if (example == null) {
        c.addCoveredPositive(e);
      }
    }

    for (TextRulerExample e : baseFN) {
      TextRulerExample example = TextRulerToolkit.exampleListContainsAnnotation(testFN,
              e.getAnnotation());
      TextRulerExample coveredExample = TextRulerToolkit
              .exampleListContainsAnnotation(goldPositives, e.getAnnotation());
      if (example == null) {
        c.addCoveredPositive(coveredExample);
      }
    }

    for (TextRulerExample e : testFN) {
      TextRulerExample example = TextRulerToolkit.exampleListContainsAnnotation(baseFN,
              e.getAnnotation());
      if (example == null) {
        if (collectNegativeExamples) {
          e.setPositive(false);
          c.addCoveredNegative(e);
        } else {
          c.incCoveredNegatives(1);
        }
      }
    }

    for (TextRulerExample e : testFP) {
      TextRulerExample example = TextRulerToolkit.exampleListContainsAnnotation(baseFP,
              e.getAnnotation());
      if (example == null) {
        c.incCoveredNegatives(1);
      }
    }
  }

  public static List<TrabalRule> removeDuplicateRules(List<TrabalRule> rules) {
    return new ArrayList<TrabalRule>(new HashSet<TrabalRule>(rules));
  }

  public static String getRuleStrings(List<TrabalRule> rules) {
    String result = "";
    for (TrabalRule r : rules) {
      result += r.toString();
    }
    return result;
  }

  public boolean isSlotType(Type type) {
    for (String slot : slotNames) {
      if (slot.equals(type.getName())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean collectNegativeCoveredInstancesWhenTesting() {
    return false;
  }

  @Override
  public String getResultString() {
    return getFileHeaderString(true) + result;
  }

  public TextRulerExampleDocumentSet getAdditionalDocuments() {
    if (additionalDocuments == null) {
      if (!StringUtils.isBlank(additionalFolderPath)) {
        additionalDocuments = new TextRulerExampleDocumentSet(additionalFolderPath, casCache);
      }
    }
    return additionalDocuments;
  }

  public boolean getEnableFeatures() {
    return enableFeatures;
  }

  @Override
  public void setParameters(Map<String, Object> params) {
    if (TextRulerToolkit.DEBUG)
      saveParametersToTempFolder(params);
    if (params.containsKey(MAX_NUMBER_OF_BASIC_RULES_KEY))
      maxNumberOfBasicRules = (Integer) params.get(MAX_NUMBER_OF_BASIC_RULES_KEY);
    if (params.containsKey(MAX_NUMBER_OF_RULES_KEY))
      maxNumberOfRules = (Integer) params.get(MAX_NUMBER_OF_RULES_KEY);
    if (params.containsKey(MAX_NUMBER_OF_ITERATIONS_KEY))
      maxNumberOfIterations = (Integer) params.get(MAX_NUMBER_OF_ITERATIONS_KEY);
    if (params.containsKey(ALGORITHM_ITERATIONS_KEY))
      algorithmIterations = (Integer) params.get(ALGORITHM_ITERATIONS_KEY);
    if (params.containsKey(MAX_ERROR_RATE_KEY))
      maxErrorRate = (Double) params.get(MAX_ERROR_RATE_KEY);
    if (params.containsKey(ENABLE_FEATURES_KEY))
      enableFeatures = (Boolean) params.get(ENABLE_FEATURES_KEY);
  }

  public void getErrorsAsCSV(String filePath) throws Exception {
    String result = "";
    if (exampleDocuments == null) {
      exampleDocuments = new TextRulerExampleDocumentSet(inputDirectory, casCache);
    }
    getAdditionalDocuments();
    errors = createErrorList();
    result += "'ErrorType';'FileName';'AnnotationType';'Annotation';'TargetAnnotationType';'TargetAnnotation';\n";
    for (AnnotationError each : errors) {
      if (each.getAnnotation() != null && each.getTargetAnnotation() != null) {
        result += "'" + each.getType() + "';'"
                + new File(each.getAnnotation().getDocument().getCasFileName()).getName() + "';'"
                + each.getAnnotation().getType().getShortName() + "';'"
                + each.getAnnotation().getCoveredText() + "';'"
                + each.getTargetAnnotation().getType().getShortName() + "';'"
                + each.getTargetAnnotation().getCoveredText() + "';\n";
      } else if (each.getAnnotation() != null) {
        result += "'" + each.getType() + "';'"
                + new File(each.getAnnotation().getDocument().getCasFileName()).getName() + "';'"
                + each.getAnnotation().getType().getShortName() + "';'"
                + each.getAnnotation().getCoveredText() + "';'';'';\n";
      } else {
        result += "'" + each.getType() + "';'"
                + new File(each.getTargetAnnotation().getDocument().getCasFileName()).getName()
                + "';'';'';'" + each.getTargetAnnotation().getType().getShortName() + "';'"
                + each.getTargetAnnotation().getCoveredText() + "';\n";
      }
    }
    if (errors.size() > 0) {
      File csv = new File(filePath);
      FileWriter fstream = new FileWriter(csv);
      BufferedWriter out = new BufferedWriter(fstream);
      out.write(result);
      out.close();
    }
  }

  protected Comparator<TrabalRule> basicComparator = new Comparator<TrabalRule>() {
    @Override
    public int compare(TrabalRule o1, TrabalRule o2) {
      // coveredPositives
      if (o1.getCoveringStatistics().getCoveredPositivesCount() > o2.getCoveringStatistics()
              .getCoveredPositivesCount())
        return -1;
      if (o1.getCoveringStatistics().getCoveredPositivesCount() < o2.getCoveringStatistics()
              .getCoveredPositivesCount())
        return 1;
      // coveredNegatives
      if (o1.getCoveringStatistics().getCoveredNegativesCount() < o2.getCoveringStatistics()
              .getCoveredNegativesCount())
        return -1;
      if (o1.getCoveringStatistics().getCoveredNegativesCount() > o2.getCoveringStatistics()
              .getCoveredNegativesCount())
        return 1;
      // complexity of rules
      if (o1.getConditions().size() < o2.getConditions().size())
        return -1;
      if (o1.getConditions().size() > o2.getConditions().size())
        return 1;
      // ruleString
      return o1.getRuleString().compareTo(o2.getRuleString());
    }
  };

  protected Comparator<TrabalRule> enhancedComparator = new Comparator<TrabalRule>() {
    @Override
    public int compare(TrabalRule o1, TrabalRule o2) {
      // positives - negatives
      if (o1.getCoveringStatistics().getCoveredPositivesCount() - o1.getCoveringStatistics()
              .getCoveredNegativesCount() > o2.getCoveringStatistics().getCoveredPositivesCount()
                      - o2.getCoveringStatistics().getCoveredNegativesCount())
        return -1;
      if (o1.getCoveringStatistics().getCoveredPositivesCount() - o1.getCoveringStatistics()
              .getCoveredNegativesCount() < o2.getCoveringStatistics().getCoveredPositivesCount()
                      - o2.getCoveringStatistics().getCoveredNegativesCount())
        return 1;
      // coveredPositives
      if (o1.getCoveringStatistics().getCoveredPositivesCount() > o2.getCoveringStatistics()
              .getCoveredPositivesCount())
        return -1;
      if (o1.getCoveringStatistics().getCoveredPositivesCount() < o2.getCoveringStatistics()
              .getCoveredPositivesCount())
        return 1;
      // coveredNegatives
      if (o1.getCoveringStatistics().getCoveredNegativesCount() < o2.getCoveringStatistics()
              .getCoveredNegativesCount())
        return -1;
      if (o1.getCoveringStatistics().getCoveredNegativesCount() > o2.getCoveringStatistics()
              .getCoveredNegativesCount())
        return 1;
      // complexity of rules
      if (o1.getConditions().size() < o2.getConditions().size())
        return -1;
      if (o1.getConditions().size() > o2.getConditions().size())
        return 1;
      // ruleString
      return o1.getRuleString().compareTo(o2.getRuleString());
    }
  };
}
