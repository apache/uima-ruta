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

package org.apache.uima.tm.textruler.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.tm.textmarker.engine.TextMarkerEngine;
import org.apache.uima.tm.textruler.core.TextRulerTarget.MLTargetType;
import org.apache.uima.tm.textruler.extension.TextRulerLearner;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerDelegate;
import org.apache.uima.tm.textruler.tools.MemoryWatch;
import org.apache.uima.util.FileUtils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;


/**
 * 
 * This class provides basic and shared functionality for all the implemented ML algorithms. New
 * algorithms can subclass this class and use the whole framework for faster development.
 * 
 */
public abstract class TextRulerBasicLearner implements TextRulerLearner, CasCacheLoader {

  protected TextRulerLearnerDelegate delegate;

  protected AnalysisEngine ae;

  protected TextRulerExampleDocumentSet exampleDocuments;

  protected String inputDirectory;

  protected String tempDirectory;

  protected String preprocessorTMFile;

  protected Set<String> filterSet;

  protected Set<String> filterSetWithSlotNames;

  protected String[] slotNames;

  protected CasCache casCache;

  protected CAS algTestCAS;

  public TextRulerBasicLearner(String inputDir, String prePropTMFile, String tmpDir,
          String[] slotNames, Set<String> filterSet, TextRulerLearnerDelegate delegate) {
    super();
    this.preprocessorTMFile = prePropTMFile;
    this.tempDirectory = tmpDir;
    this.slotNames = slotNames;
    this.inputDirectory = inputDir;
    this.delegate = delegate;
    this.filterSet = filterSet;
    filterSetWithSlotNames = new HashSet<String>(filterSet);
    for (String s : slotNames) {
      filterSetWithSlotNames.add(s);
      filterSetWithSlotNames.add(TextRulerTarget.getSingleSlotTypeName(
              MLTargetType.SINGLE_LEFT_BOUNDARY, s));
      filterSetWithSlotNames.add(TextRulerTarget.getSingleSlotTypeName(
              MLTargetType.SINGLE_RIGHT_BOUNDARY, s));
    }

    this.casCache = new CasCache(100, this); // TODO make size configurable
    // !? share e.g. 100 places for
    // all running algoritghms ?
  }

  protected String tempDirectory() {
    return TextRulerToolkit.addTrailingSlashToPath(tempDirectory);
  }

  protected boolean shouldAbort() {
    if (delegate != null)
      return delegate.shouldAbort();
    else
      return false;
  }

  public AnalysisEngine getAnalysisEngine() {
    if (ae == null) {
      String descriptorFile = TextRulerToolkit.getEngineDescriptorFromTMSourceFile(new Path(
              preprocessorTMFile));
      sendStatusUpdateToDelegate("loading AE...", TextRulerLearnerState.ML_INITIALIZING, false);
      ae = TextRulerToolkit.loadAnalysisEngine(descriptorFile);

      // set filters to NO filtering so that we can add it manually with
      // the FILTERTYPE expression!
      ae.setConfigParameterValue(TextMarkerEngine.DEFAULT_FILTERED_MARKUPS, new String[0]);
      String tempRulesFileName = getTempRulesFileName();
      IPath path = new Path(tempRulesFileName);
      ae.setConfigParameterValue(TextMarkerEngine.MAIN_SCRIPT, path.removeFileExtension()
              .lastSegment());
      ae.setConfigParameterValue(TextMarkerEngine.SCRIPT_PATHS, new String[] { path
              .removeLastSegments(1).toPortableString() });
      // ae.setConfigParameterValue(TextMarkerEngine.SEEDERS, new String[] {""});
      ae.setConfigParameterValue(TextMarkerEngine.ADDITIONAL_SCRIPTS, new String[0]);
      try {
        ae.reconfigure();
      } catch (ResourceConfigurationException e) {
        e.printStackTrace();
        return null;
      }
    }
    return ae;
  }

  protected boolean checkForMandatoryTypes() {
    // check if all passed slot types are present:
    CAS someCas = getTestCAS();
    TypeSystem ts = someCas.getTypeSystem();
    boolean result = true;
    List<String> missingTypes = new ArrayList<String>();
    for (String s : slotNames) {
      if (ts.getType(s) == null) {
        missingTypes.add(s);
        result = false;
      }
    }
    String missingString = "";
    for (String string : missingTypes) {
      missingString += string + ", ";
    }
    if (!missingString.isEmpty()) {
      missingString = missingString.substring(0, missingString.length() - 2);
    }
    sendStatusUpdateToDelegate("Error: Some Slot- or Helper-Types were not found in TypeSystem: "
            + missingString, TextRulerLearnerState.ML_ERROR, false);
    return result;
  }

  protected boolean createTempDirIfNeccessary() {
    File dir = new File(tempDirectory());
    if (dir.exists() && dir.isDirectory())
      return true;
    else
      return dir.mkdir();
  }

  public void run() {
    if (createTempDirIfNeccessary()) {
      getAnalysisEngine(); // be sure that our AE was created...

      if (!checkForMandatoryTypes()) {

      } else {
        sendStatusUpdateToDelegate("Finding documents...", TextRulerLearnerState.ML_INITIALIZING,
                false);
        exampleDocuments = new TextRulerExampleDocumentSet(inputDirectory, casCache);
        if (!shouldAbort()) {
          sendStatusUpdateToDelegate("Starting...", TextRulerLearnerState.ML_RUNNING, true);

          try {
            doRun();
          } catch (Exception e) {
            e.printStackTrace();
            sendStatusUpdateToDelegate("Aborted due to exception!", TextRulerLearnerState.ML_ERROR,
                    true);
          }

          if (TextRulerToolkit.DEBUG) {
            try {
              File file = new File(tempDirectory() + "results.tm");
              FileUtils.saveString2File(getResultString(), file);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
          cleanUp();
        }
      }
      casCache.clear();
      casCache = null;
      exampleDocuments = null; // clear reference
      if (algTestCAS != null) {
        algTestCAS.reset();
        GlobalCASSource.releaseCAS(algTestCAS); // algTestCAS.release();
        algTestCAS = null;
      }
      if (shouldAbort())
        sendStatusUpdateToDelegate("Aborted!", TextRulerLearnerState.ML_ABORTED, false);
    } else {
      sendStatusUpdateToDelegate("ERROR CREATING TEMPORARY DIRECTORY!",
              TextRulerLearnerState.ML_ERROR, false);
    }
  }

  public CAS loadCAS(String fileName, CAS reuseCAS) {
    return TextRulerToolkit.readCASfromXMIFile(fileName, ae, reuseCAS);
  }

  protected void sendStatusUpdateToDelegate(String statusString, TextRulerLearnerState state,
          boolean ruleBaseChanged) {
    if (delegate != null)
      delegate.algorithmStatusUpdate(this, statusString, state, ruleBaseChanged);
  }

  protected abstract void doRun(); // needs to be implemented by concrete

  // algorithm subclasses !

  protected void cleanUp() {

  }

  public String getTempRulesFileName() {
    return tempDirectory() + "rules.tm";
  }

  public String getIntermediateRulesFileName() {
    return tempDirectory() + "intermediaterules.tm";
  }

  public void compareOriginalDocumentWithTestCAS(TextRulerExampleDocument originalDoc, CAS testCas,
          TextRulerTarget target, TextRulerStatisticsCollector c, boolean collectNegativeExamples) {
    // standard implementation - may be overwritten by concrete subclasses
    // if needed
    List<TextRulerExample> originalPositives = originalDoc.getPositiveExamples();
    List<TextRulerExample> testPositives = originalDoc.createSlotInstancesForCAS(testCas, target,
            false);

    // TODO if you need false negatives (missing annotations), please
    // reactivate
    // the code commented out with FALSENEGATIVES

    for (TextRulerExample e : testPositives) {
      TextRulerExample coveredExample = TextRulerToolkit.exampleListContainsAnnotation(
              originalPositives, e.getAnnotation());
      if (coveredExample != null) {
        c.addCoveredPositive(coveredExample); // add covered example and
        // increment positive
        // counter
        // FALSENEGATIVES originalPositives.remove(coveredExample);
      } else {
        if (collectNegativeExamples) {
          e.setPositive(false);
          c.addCoveredNegative(e);
        } else
          c.incCoveredNegatives(1);
      }
    }

    // FALSENEGATIVES c.incMissingPositives(originalPositives.size());
  }

  public abstract boolean collectNegativeCoveredInstancesWhenTesting();

  public void testRuleOnDocument(final TextRulerRule rule, final TextRulerExampleDocument doc,
          final TextRulerStatisticsCollector c) {
    CAS testCAS = getTestCAS();
    doc.resetAndFillTestCAS(testCAS, rule.getTarget());
    testRuleOnDocument(rule, doc, c, testCAS);
    testCAS.reset();
  }

  public void testRuleOnDocument(final TextRulerRule rule, final TextRulerExampleDocument doc,
          final TextRulerStatisticsCollector c, CAS testCas) {
    if (TextRulerToolkit.DEBUG) {
      MemoryWatch.watch();
    }
    try {
      rule.saveToRulesFile(getTempRulesFileName());
      if (TextRulerToolkit.DEBUG) {
        TextRulerToolkit.writeCAStoXMIFile(testCas, tempDirectory() + "testCas.xmi");
      }
      ae.process(testCas);
      if (TextRulerToolkit.DEBUG) {
        TextRulerToolkit.writeCAStoXMIFile(testCas, tempDirectory() + "testCasProcessed.xmi");
      }
      compareOriginalDocumentWithTestCAS(doc, testCas, rule.getTarget(), c,
              collectNegativeCoveredInstancesWhenTesting());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // if you have many rules to test, please use testRulesOnDocumentSet for
  // performance issues !!
  public void testRuleOnDocumentSet(final TextRulerRule rule,
          final TextRulerExampleDocumentSet documents) {
    TextRulerStatisticsCollector sum = new TextRulerStatisticsCollector();
    TextRulerExampleDocument[] sortedDocs = documents.getSortedDocumentsInCacheOptimizedOrder();

    for (TextRulerExampleDocument theDoc : sortedDocs) {
      testRuleOnDocument(rule, theDoc, sum);
      if (shouldAbort())
        break;
    }
    rule.setCoveringStatistics(sum);
  }

  public CAS applyScriptOnDocument(String script, final TextRulerExampleDocument doc,
          TextRulerTarget target) {
    String tempRulesFileName = getTempRulesFileName();
    try {
      FileUtils.saveString2File(script, new File(tempRulesFileName));
    } catch (IOException e) {
      e.printStackTrace();
    }
    AnalysisEngine analysisEngine = getAnalysisEngine();
    CAS testCAS = getTestCAS();
    doc.resetAndFillTestCAS(testCAS, target);
    try {
      analysisEngine.process(testCAS);
    } catch (AnalysisEngineProcessException e) {
      e.printStackTrace();
    }
    return testCAS;
  }

  public void testRulesOnDocumentSet(final List<? extends TextRulerRule> rules,
          final TextRulerExampleDocumentSet documents) {
    if (rules.isEmpty())
      return;
    List<TextRulerStatisticsCollector> sums = new ArrayList<TextRulerStatisticsCollector>();
    TextRulerExampleDocument[] sortedDocs = documents.getSortedDocumentsInCacheOptimizedOrder();
    TextRulerTarget target = rules.get(0).getTarget();

    for (@SuppressWarnings("unused")
    TextRulerRule r : rules) {
      // crate a collector for each rule
      sums.add(new TextRulerStatisticsCollector());
    }

    CAS theTestCAS = getTestCAS();
    for (TextRulerExampleDocument theDoc : sortedDocs) {
      for (int ruleIndex = 0; ruleIndex < rules.size(); ruleIndex++) {
        TextRulerRule theRule = rules.get(ruleIndex);
        TextRulerStatisticsCollector sumC = sums.get(ruleIndex);

        if (TextRulerToolkit.DEBUG && !target.equals(theRule.getTarget())) {
          TextRulerToolkit
                  .log("[TextRulerBasicLearner.testRulesOnTrainingsSet] ERROR, ALL RULES MUST HAVE THE SAME LEARNING TARGET !");
        }
        theDoc.resetAndFillTestCAS(theTestCAS, target);
        testRuleOnDocument(theRule, theDoc, sumC, theTestCAS);
        if (shouldAbort())
          return;
      }
    }
    theTestCAS.reset();
    // do not release the shared test-cas ! only reset it ! it gets released
    // at the end of the
    // whole algorithm !
    for (int ruleIndex = 0; ruleIndex < rules.size(); ruleIndex++)
      rules.get(ruleIndex).setCoveringStatistics(sums.get(ruleIndex));
  }

  public void testRulesOnDocument(final List<? extends TextRulerRule> rules,
          final TextRulerExampleDocument document) {
    if (rules.isEmpty())
      return;
    List<TextRulerStatisticsCollector> sums = new ArrayList<TextRulerStatisticsCollector>();
    TextRulerTarget target = rules.get(0).getTarget();
    for (@SuppressWarnings("unused")
    TextRulerRule r : rules) {
      // crate a collector for each rule
      sums.add(new TextRulerStatisticsCollector());
    }
    CAS theTestCAS = getTestCAS();
    for (int ruleIndex = 0; ruleIndex < rules.size(); ruleIndex++) {
      TextRulerRule theRule = rules.get(ruleIndex);
      TextRulerStatisticsCollector sumC = sums.get(ruleIndex);

      if (TextRulerToolkit.DEBUG && !target.equals(theRule.getTarget())) {
        TextRulerToolkit
                .log("[TextRulerBasicLearner.testRulesOnTrainingsSet] ERROR, ALL RULES MUST HAVE THE SAME LEARNING TARGET !");
      }
      document.resetAndFillTestCAS(theTestCAS, target);
      testRuleOnDocument(theRule, document, sumC, theTestCAS);
      if (shouldAbort())
        return;
    }
    theTestCAS.reset();
    // do not release the shared test-cas ! only reset it ! it gets released
    // at the end of the
    // whole algorithm !
    for (int ruleIndex = 0; ruleIndex < rules.size(); ruleIndex++)
      rules.get(ruleIndex).setCoveringStatistics(sums.get(ruleIndex));
  }

  public String getTMFileHeaderString() {
    return getTMPackageString() + getTypeSystemImport() + getTMFilterCommandString();
  }

  private String getTypeSystemImport() {
    return "TYPESYSTEM " + getTypeSystemString(preprocessorTMFile) + ";\n\n";
  }

  private String getTypeSystemString(String fileString) {
    File file = new File(fileString);
    // TODO

    return "org.apache.uima.tm.citie.CompleteTypeSystemTypeSystem";
  }

  public String getTMPackageString() {
    return "PACKAGE org.apache.uima.tm;\n\n";
  }

  public String getTMFilterCommandString() {
    if (filterSet != null && filterSet.size() > 0) {
      String fs = "";
      for (String s : filterSet)
        if (fs.length() == 0)
          fs += TextRulerToolkit.getTypeShortName(s);
        else
          fs += ", " + TextRulerToolkit.getTypeShortName(s);

      return "Document{->FILTERTYPE(" + fs + ")};\n\n";
    } else
      return "";
  }

  public CAS getTestCAS() {
    // one big memory problem occured as we .reset+.release old CASes and
    // created new ones
    // for every test and (e.g. in CasCache for every loaded XMI). Maybe
    // this is a
    // UIMA memory issue ? Changing this to an almost static amount of CAS
    // objects and reusing
    // them works without leaking, so we prefer this now since it also
    // brought a performance
    // boost!
    if (algTestCAS == null) {
      try {
        algTestCAS = GlobalCASSource.allocCAS(ae);
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }

    }
    return algTestCAS;
  }

  protected void saveParametersToTempFolder(Map<String, Object> params) {
    if (createTempDirIfNeccessary()) {
      String str = "\nSettings:\n\n";

      str += "inputDir: " + inputDirectory;
      str += "\ntempDir: " + tempDirectory;
      str += "\npreprocessTMFile: " + preprocessorTMFile;
      str += "\n";

      for (Entry<String, Object> e : params.entrySet()) {
        str += e.getKey() + " = " + e.getValue() + "\n";
      }
      if (createTempDirIfNeccessary())
        TextRulerToolkit.appendStringToFile(tempDirectory() + "settings.txt", str);
    }
  }

  public Set<String> getFilterSet() {
    return filterSet;
  }
}
