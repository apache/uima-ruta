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

package org.apache.uima.textmarker.textruler.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.textruler.core.TextRulerTarget.MLTargetType;


/**
 * 
 * TextRulerExampleDocument stands for one document usually loaded from an XMI file. It uses the
 * given CasCache for storing its CAS with the XMI filename as the key.
 * 
 * It holds ArrayLists for positive and negative MLExamples which can be filled on demand for a
 * given learning target. E.g. single slot algorithms learn rules for each slot separately, so the
 * work-flow is to clear the current examples and create new for the next slot target. The same is
 * with single slot boundary algorithms like LP2: It first creates all left boundary examples,
 * learns from them, clears the examples and creates the right boundary examples and so on.
 * 
 * This class also provides the functionality extract and created MLExmaples of a given document or
 * test CAS for a given TextRulerTarget.
 * 
 * Especially for boundary algorithms you can call createBoundaryAnnotationsForCas to get boundary
 * annotations at the beginnings and endings of an example slot.
 * 
 * Caution (this is quite a bit inconvenient at the moment!): If a CAS gets loaded from the
 * casCache, you have to call createBoundaryAnnotationsForCas again, so your casLoader must be aware
 * of that (see BasicLP2 for an example) !
 * 
 *         hint: this could be renamed to MLDocument instead of TextRulerExampleDocument ?
 */
public class TextRulerExampleDocument {

  protected String casFileName;

  protected CasCache casCache;

  protected List<TextRulerExample> positiveExamples = new ArrayList<TextRulerExample>();

  protected List<TextRulerExample> negativeExamples = new ArrayList<TextRulerExample>();

  public TextRulerExampleDocument(String casFileName, CasCache casCache) {
    this.casCache = casCache;
    this.casFileName = casFileName;
  }

  public CAS getCAS() {
    // ask CACHE
    return casCache.getCAS(casFileName);
  }

  public List<TextRulerExample> getPositiveExamples() {
    return positiveExamples;
  }

  public List<TextRulerExample> getNegativeExamples() {
    return negativeExamples;
  }

  protected void createPositiveExamplesForTarget(TextRulerTarget target) {
    positiveExamples = createSlotInstancesForCAS(getCAS(), target, true);
  }

  public List<TextRulerExample> createSlotInstancesForCAS(CAS aCas, TextRulerTarget target,
          boolean createFromRawTypeName) {
    List<TextRulerExample> result = new ArrayList<TextRulerExample>();

    if (target.isMultiSlot()) {
      TypeSystem ts = aCas.getTypeSystem();
      int currentSlotIndex = 0;
      TextRulerAnnotation[] currentAnnotations = new TextRulerAnnotation[target.slotNames.length];
      List<Type> slotTypes = new ArrayList<Type>();
      for (String s : target.slotNames)
        slotTypes.add(ts.getType(s));

      for (FSIterator<AnnotationFS> it = aCas.getAnnotationIndex().iterator(true); it.isValid(); it
              .moveToNext()) {
        AnnotationFS fs = (AnnotationFS) it.get();
        Type theType = fs.getType();
        if (slotTypes.contains(theType)) {
          int idx = slotTypes.indexOf(theType);
          if (idx < currentSlotIndex) // the previous example was not
          // complete, so we have to write
          // it down:
          {
            result.add(new TextRulerExample(this, currentAnnotations, true, target));
            currentAnnotations = new TextRulerAnnotation[target.slotNames.length];
          }
          currentAnnotations[idx] = new TextRulerAnnotation(fs, this);
          if (idx >= target.slotNames.length - 1) {
            result.add(new TextRulerExample(this, currentAnnotations, true, target));
            currentAnnotations = new TextRulerAnnotation[target.slotNames.length];
            currentSlotIndex = 0;
          } else
            currentSlotIndex = idx + 1;
        }
      }
      if (currentSlotIndex > 0) {
        result.add(new TextRulerExample(this, currentAnnotations, true, target));
      }

    } else if (target.isLeftCorrection() || target.isRightCorrection()) {
      // TODO
      TextRulerBasicLearner learner = target.getLearner();
      Set<String> filterSet = learner.getFilterSet();
      CAS testCAS = learner.getTestCAS();
      TextRulerStatisticsCollector c = new TextRulerStatisticsCollector();
      resetAndFillTestCAS(testCAS, target);
      CAS docCAS = getCAS();
      TypeSystem ts = docCAS.getTypeSystem();
      Type tokensRootType = ts.getType(TextRulerToolkit.TM_ANY_TYPE_NAME);
      AnalysisEngine analysisEngine = learner.getAnalysisEngine();
      try {
        analysisEngine.process(testCAS);
      } catch (AnalysisEngineProcessException e) {
        // TODO add log here
      }
      TextRulerTarget newTarget = new TextRulerTarget(target.slotNames, target.getLearner());
      if (target.isLeftCorrection()) {
        newTarget.type = TextRulerTarget.MLTargetType.SINGLE_LEFT_BOUNDARY;
      } else {
        newTarget.type = TextRulerTarget.MLTargetType.SINGLE_RIGHT_BOUNDARY;
      }
      createExamplesForTarget(newTarget);
      learner.compareOriginalDocumentWithTestCAS(this, testCAS, newTarget, c, true);
      List<TextRulerExample> correctTags = getPositiveExamples();
      List<TextRulerExample> wrongTags = new ArrayList<TextRulerExample>(c
              .getCoveredNegativeExamples());
      for (TextRulerExample wrongTag : wrongTags) {
        // test, if there's a corresponding positive example
        // somewhere around (within maxDistance)
        List<AnnotationFS> left = TextRulerToolkit.getAnnotationsBeforePosition(docCAS, wrongTag
                .getAnnotation().getBegin(), target.getMaxShiftDistance(), TextRulerToolkit
                .getFilterSetWithSlotNames(target.slotNames, filterSet), tokensRootType);
        List<AnnotationFS> right = TextRulerToolkit.getAnnotationsAfterPosition(docCAS, wrongTag
                .getAnnotation().getEnd(), target.getMaxShiftDistance() + 1, TextRulerToolkit
                .getFilterSetWithSlotNames(target.slotNames, filterSet), tokensRootType);

        right.remove(0);

        // TODO stop after the first found match or create one bad
        // example for each found occurence ??!!
        // for now: stop after one ! so create only ONE bad
        // example...
        int leftDistance = 0;
        TextRulerExample leftCorrectTag = null;
        for (int i = left.size() - 1; i >= 0; i--) {
          leftDistance++;
          TextRulerAnnotation needle = TextRulerToolkit.convertToTargetAnnotation(left.get(i),
                  this, target, docCAS.getTypeSystem());
          // Only checks the beginning of needle
          leftCorrectTag = TextRulerExampleDocument.exampleListContainsAnnotation(correctTags,
                  needle);
          if (leftCorrectTag != null)
            break;
        }

        int rightDistance = 0;
        TextRulerExample rightCorrectTag = null;
        for (AnnotationFS fs : right) {
          rightDistance++;
          TextRulerAnnotation needle = TextRulerToolkit.convertToTargetAnnotation(fs, this, target,
                  docCAS.getTypeSystem());
          // Only checks the beginning of needle
          rightCorrectTag = TextRulerExampleDocument.exampleListContainsAnnotation(correctTags,
                  needle);
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
          TextRulerShiftExample shiftExample = new TextRulerShiftExample(this, wrongTag
                  .getAnnotation(), theCorrectTag.getAnnotation(), true, target);
          result.add(shiftExample);
        }
      }

    } else {
      List<AnnotationFS> slots = TextRulerToolkit.extractAnnotationsForSlotName(aCas,
              createFromRawTypeName ? target.getSingleSlotRawTypeName() : target
                      .getSingleSlotTypeName()); // do not use
      // boundary type
      // here since we
      // seek for the
      // orignial slot
      // !
      for (AnnotationFS a : slots) {
        result.add(new TextRulerExample(this, TextRulerToolkit.convertToTargetAnnotation(a, this,
                target, aCas.getTypeSystem()), true, target));
      }
    }
    return result;
  }

  protected void createNegativeExamplesForTarget(TextRulerTarget target) {
    // the default implementation does not support negative examples,
    // subclasses can overwrite
    // this if needed... or we could pass this as an argument to the
    // constructor....
  }

  public void createExamplesForTarget(TextRulerTarget target) {
    createPositiveExamplesForTarget(target);
    createNegativeExamplesForTarget(target);
  }

  public void clearCurrentExamples() {
    positiveExamples.clear();
    negativeExamples.clear();
  }

  // pass your test CAS object and the corresponding learning target to get a
  // filled
  // test-CAS for testing e.g. rule or rule set..
  // caution: testCas gets reset fist!
  public void resetAndFillTestCAS(CAS testCas, TextRulerTarget target) {
    testCas.reset();
    CAS docCas = getCAS();
    testCas.setDocumentText(docCas.getDocumentText());

    // copy all annotations except the target-annotations:
    TypeSystem ts = docCas.getTypeSystem();

    List<Type> slotTypes = new ArrayList<Type>();

    for (String s : target.getSlotTypeNames())
      slotTypes.add(ts.getType(s));

    if (target.isBoundary()) {
      // add the base types (without START and END markers) also !
      for (String s : target.slotNames)
        slotTypes.add(ts.getType(s));
    }

    for (FSIterator<AnnotationFS> it = docCas.getAnnotationIndex().iterator(true); it.isValid(); it
            .moveToNext()) {
      AnnotationFS fs = it.get();
      if (!slotTypes.contains(fs.getType())) {
        Type t = testCas.getTypeSystem().getType(fs.getType().getName());
        if (t != null) {
          AnnotationFS createAnnotation = testCas.createAnnotation(t, fs.getBegin(), fs.getEnd());
          testCas.addFsToIndexes(createAnnotation);
        } else {
          TextRulerToolkit.log("Type " + fs.getType().getName() + "is unknown in test CAS");
        }
      }
    }
  }

  public String getCasFileName() {
    return casFileName;
  }

  public static void createBoundaryAnnotationsForCas(CAS aCas, String slotName,
          Set<String> tokenFilterSet) {
    List<AnnotationFS> slots = TextRulerToolkit.extractAnnotationsForSlotName(aCas, slotName);
    TypeSystem ts = aCas.getTypeSystem();
    for (AnnotationFS a : slots) {

      List<AnnotationFS> slotTokens = TextRulerToolkit.getAnnotationsWithinBounds(aCas, a
              .getBegin(), a.getEnd(), TextRulerToolkit.getFilterSetWithSlotName(slotName,
              tokenFilterSet), ts.getType(TextRulerToolkit.TM_ANY_TYPE_NAME));
      AnnotationFS first = slotTokens.get(0);
      AnnotationFS last = slotTokens.get(slotTokens.size() - 1);
      Type typeLB = ts.getType(slotName + TextRulerToolkit.LEFT_BOUNDARY_EXTENSION);
      aCas.addFsToIndexes(aCas.createAnnotation(typeLB, first.getBegin(), first.getEnd()));
      Type typeRB = ts.getType(slotName + TextRulerToolkit.RIGHT_BOUNDARY_EXTENSION);
      aCas.addFsToIndexes(aCas.createAnnotation(typeRB, last.getBegin(), last.getEnd()));
    }
  }

  public static void removeBoundaryAnnotationsFromCas(CAS aCas, String slotName) {
    // this method is not tested yet!
    TypeSystem ts = aCas.getTypeSystem();
    Type startType = ts.getType(slotName + TextRulerToolkit.LEFT_BOUNDARY_EXTENSION);
    Type endType = ts.getType(slotName + TextRulerToolkit.RIGHT_BOUNDARY_EXTENSION);
    List<AnnotationFS> removeList = new ArrayList<AnnotationFS>();
    for (FSIterator<AnnotationFS> it = aCas.getAnnotationIndex(startType).iterator(true); it
            .isValid(); it.moveToNext()) {
      AnnotationFS fs = it.get();
      removeList.add(fs);
    }
    for (FSIterator<AnnotationFS> it = aCas.getAnnotationIndex(endType).iterator(true); it
            .isValid(); it.moveToNext()) {
      AnnotationFS fs = it.get();
      removeList.add(fs);
    }
    for (AnnotationFS fs : removeList)
      aCas.removeFsFromIndexes(fs);
  }

  public static synchronized TextRulerExample exampleListContainsAnnotation(
          List<TextRulerExample> list, TextRulerAnnotation ann) {
    TextRulerExample needle = new TextRulerExample(null, ann, true, null);

    int index = Collections.binarySearch(list, needle, new Comparator<TextRulerExample>() {
      public int compare(TextRulerExample o1, TextRulerExample o2) {
        TextRulerAnnotation afs1 = o1.getAnnotation();
        TextRulerAnnotation afs2 = o2.getAnnotation();
        if (afs1.getBegin() < afs2.getBegin())
          return -1;
        else if (afs1.getBegin() > afs2.getBegin())
          return 1;
        else
          return 0;
      }
    });
    if (index >= 0)
      return list.get(index);
    else
      return null;
  }

}
