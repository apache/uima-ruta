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

package org.apache.uima.tm.textruler.tools;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.tm.textruler.core.CasCache;
import org.apache.uima.tm.textruler.core.CasCacheLoader;
import org.apache.uima.tm.textruler.core.GlobalCASSource;
import org.apache.uima.tm.textruler.core.TextRulerExample;
import org.apache.uima.tm.textruler.core.TextRulerExampleDocument;
import org.apache.uima.tm.textruler.core.TextRulerTarget;
import org.apache.uima.tm.textruler.core.TextRulerToolkit;

public class F1Scorer implements CasCacheLoader {

  private AnalysisEngine ae;

  ArrayList<TextRulerExampleDocument> trainingDocuments;

  String[] testFileNames;

  public static class Score {
    public String slotName;

    public int tp; // true positives: correct extracted slots

    public int fp; // false positives: spurious fillers that do not occur in

    // the original document
    public int fn; // false negatives: missing slots that occur in the

    // document but not in the result

    public void logInfo() {
      TextRulerToolkit.log(toString());
    }

    @Override
    public String toString() {
      String str = "Slot: '" + slotName + "';  tp=" + tp + "\tfp=" + fp + "\tfn=" + fn + "\n";
      str += "\tPrecision: \t" + precision() + "\n";
      str += "\tRecall:    \t" + recall() + "\n";
      str += "\tF1 Score:  \t" + f1Score() + "\n";
      return str;
    }

    public double f1Score() {
      return f1Score(precision(), recall());
    }

    public static double f1Score(double P, double R) {
      if (P + R == 0)
        return 0;
      else
        return (2 * P * R) / (P + R);
    }

    public double precision() {
      double v = tp + fp;
      if (v == 0)
        return 0;
      else
        return (tp) / v;
    }

    public double recall() {
      double v = tp + fn;
      if (v == 0)
        return 0;
      else
        return (tp) / v;
    }
  }

  public static String[] getXMIfileNames(String folderName) {
    File folder = new File(folderName);
    File[] files = folder.listFiles(new FilenameFilter() {
      public boolean accept(File dir, String name) {
        return (name.endsWith(".xmi"));
      }
    });
    ArrayList<String> resultList = new ArrayList<String>();
    for (File f : files)
      resultList.add(f.getAbsolutePath());

    Comparator<String> cmp = new Comparator<String>() {

      public int compare(String o1, String o2) {
        return o1.compareTo(o2);
      }

    };
    Collections.sort(resultList, cmp);
    return resultList.toArray(new String[0]);
  }

  public F1Scorer(String descriptorFile, String trainingFolder, String testFolder) {
    this(TextRulerToolkit.loadAnalysisEngine(descriptorFile), trainingFolder, testFolder);
  }

  public F1Scorer(AnalysisEngine ae, String trainingFolder, String testFolder) {
    this.ae = ae;

    trainingDocuments = new ArrayList<TextRulerExampleDocument>();
    CasCache casCache = new CasCache(5, this);

    for (String fileName : getXMIfileNames(trainingFolder))
      trainingDocuments.add(new TextRulerExampleDocument(fileName, casCache));
    testFileNames = getXMIfileNames(testFolder);
  }

  public Score scoreSlot(String slotName) {
    Score result = new Score();
    // TODO set a learner here... or something else...
    TextRulerTarget target = new TextRulerTarget(slotName, null);
    result.slotName = slotName;
    int index = 0;
    CAS testCAS = null;
    for (TextRulerExampleDocument doc : trainingDocuments) {
      CAS exampleCAS = doc.getCAS();
      if (index >= testFileNames.length) {
        TextRulerToolkit.log("ABORTING DUE TO NON TEST-EXISTENT FILE! ");
        break;
      }
      testCAS = loadCAS(testFileNames[index], testCAS);
      if (!testCAS.getDocumentText().equals((exampleCAS.getDocumentText()))) {
        TextRulerToolkit.log("ERROR, EXAMPLE AND TEST DOCUMENT MISMATCH! WRONG FILE ORDER?");
        return null;
      }

      doc.clearCurrentExamples();
      doc.createExamplesForTarget(target);
      compareOriginalDocumentWithTestCAS(doc, testCAS, target, result);
      testCAS.reset();
      index++;
    }
    testCAS.reset();
    GlobalCASSource.releaseCAS(testCAS); // testCAS.release();
    return result;
  }

  // based on TextRulerBasicLearner's method:
  protected void compareOriginalDocumentWithTestCAS(TextRulerExampleDocument originalDoc,
          CAS testCas, TextRulerTarget target, Score theScore) {
    List<TextRulerExample> originalPositives = originalDoc.getPositiveExamples();
    List<TextRulerExample> testPositives = originalDoc.createSlotInstancesForCAS(testCas, target,
            true);

    for (TextRulerExample e : testPositives) {
      TextRulerExample coveredExample = TextRulerToolkit.exampleListContainsAnnotation(
              originalPositives, e.getAnnotation());
      if (coveredExample != null) {
        originalPositives.remove(coveredExample);
        theScore.tp++;
      } else
        theScore.fp++;
    }

    // all remaining positives are missing positives (false negatives)
    theScore.fn += originalPositives.size();
  }

  public static void main(String[] args) {
    // RAPIER
    // TextRulerToolkit.log("stime = "+Score.f1Score(93.9, 92.9));
    // TextRulerToolkit.log("etime = "+Score.f1Score(95.8, 94.6));
    // TextRulerToolkit.log("loc   = "+Score.f1Score(91.0, 60.5));
    // TextRulerToolkit.log("speak = "+Score.f1Score(80.9, 39.4));

    // WHISK
    // TextRulerToolkit.log("stime = "+Score.f1Score(86.2, 100.0));
    // TextRulerToolkit.log("etime = "+Score.f1Score(85.0, 87.2));
    // TextRulerToolkit.log("loc   = "+Score.f1Score(83.6, 55.4));
    // TextRulerToolkit.log("speak = "+Score.f1Score(52.6, 11.1));

    // // SRV
    // TextRulerToolkit.log("stime = "+Score.f1Score(98.4, 98.3));
    // TextRulerToolkit.log("etime = "+Score.f1Score(66.7, 92.6));
    // TextRulerToolkit.log("loc   = "+Score.f1Score(73.8, 69.5));
    // TextRulerToolkit.log("speak = "+Score.f1Score(54.9, 58.3));

    F1Scorer scorer = new F1Scorer(
            // "/testinput/f1test2/desc/lp2ergebnisrandomgiantEngine.xml",
            // "/testinput/f1test2/original",
            // // "/testinput/f1test/fromSystem"
            // "/Users/tobi/Documents/runtime-EclipseApplication/MLSandBox/output"
            "/Users/tobi/Documents/runtime-EclipseApplication/MLSandBox/descriptor/de.uniwue.ml/lp2validateEngine.xml",
            "/Users/tobi/Documents/UniLaptop/Diplomarbeit/TestDataSets/withPosTags/Subset100/5fold/2/testing/withtags",
            "/Users/tobi/Documents/runtime-EclipseApplication/MLSandBox/output");
    Score score = scorer.scoreSlot("de.uniwue.ml.types.stime");
    // Score score = scorer.scoreSlot("de.uniwue.ml.types.location");
    if (score != null)
      score.logInfo();
  }

  public CAS loadCAS(String fileName, CAS reuseCAS) {
    return TextRulerToolkit.readCASfromXMIFile(fileName, ae, reuseCAS);
  }

}
