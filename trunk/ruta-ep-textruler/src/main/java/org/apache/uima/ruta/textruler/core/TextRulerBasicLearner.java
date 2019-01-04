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

package org.apache.uima.ruta.textruler.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.apache.uima.ruta.textruler.TextRulerPlugin;
import org.apache.uima.ruta.textruler.core.TextRulerTarget.MLTargetType;
import org.apache.uima.ruta.textruler.extension.TextRulerLearner;
import org.apache.uima.ruta.textruler.extension.TextRulerLearnerDelegate;
import org.apache.uima.ruta.textruler.preferences.TextRulerPreferences;
import org.apache.uima.ruta.textruler.tools.MemoryWatch;
import org.apache.uima.util.FileUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.preference.IPreferenceStore;

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

  protected String preprocessorFile;

  protected Set<String> filterSet;

  protected Set<String> filterSetWithSlotNames;

  protected String[] slotNames;

  protected CasCache casCache;

  protected CAS algTestCAS;

  private boolean skip;

  protected boolean useDynamicAnchoring = false;

  private boolean useDefaultFiltering;

  protected boolean supportBoundaries = false;

  private double maxErrorRate = 5;

  private Map<String, TextRulerStatisticsCollector> inducedRules = new TreeMap<String, TextRulerStatisticsCollector>();

  public TextRulerBasicLearner(String inputDir, String prePropTMFile, String tmpDir,
          String[] slotNames, Set<String> filterSet, boolean skip, TextRulerLearnerDelegate delegate) {
    super();
    this.preprocessorFile = prePropTMFile;
    this.tempDirectory = tmpDir;
    this.slotNames = slotNames;
    this.inputDirectory = inputDir;
    this.skip = skip;
    this.delegate = delegate;
    this.filterSet = filterSet;
    filterSetWithSlotNames = new HashSet<String>(filterSet);
    filterSetWithSlotNames.add(RutaEngine.BASIC_TYPE);
    for (String s : slotNames) {
      filterSetWithSlotNames.add(s);
      filterSetWithSlotNames.add(TextRulerTarget.getSingleSlotTypeName(
              MLTargetType.SINGLE_LEFT_BOUNDARY, s));
      filterSetWithSlotNames.add(TextRulerTarget.getSingleSlotTypeName(
              MLTargetType.SINGLE_RIGHT_BOUNDARY, s));
    }

    useDefaultFiltering = true;
    useDefaultFiltering &= filterSet.size() == 4;
    useDefaultFiltering &= filterSet.contains("org.apache.uima.ruta.type.SPACE");
    useDefaultFiltering &= filterSet.contains("org.apache.uima.ruta.type.BREAK");
    useDefaultFiltering &= filterSet.contains("org.apache.uima.ruta.type.NBSP");
    useDefaultFiltering &= filterSet.contains("org.apache.uima.ruta.type.MARKUP");

    IPreferenceStore store = TextRulerPlugin.getDefault().getPreferenceStore();
    maxErrorRate = store.getInt(TextRulerPreferences.MAX_ERROR_RATE);
    int casChacheSize = store.getInt(TextRulerPreferences.CAS_CACHE);
    this.casCache = new CasCache(casChacheSize, this);
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
      updateAE();
    }
    return ae;
  }

  private void updateAE() {
    IPath analysisEngineDescriptorPath = null;
    try {
      analysisEngineDescriptorPath = RutaProjectUtils
              .getAnalysisEngineDescriptorPath(preprocessorFile);
    } catch (CoreException e1) {
      sendStatusUpdateToDelegate("Failed to locate descriptor.",
              TextRulerLearnerState.ML_INITIALIZING, false);
    }
    String descriptorFile = analysisEngineDescriptorPath.toPortableString();
    sendStatusUpdateToDelegate("loading AE...", TextRulerLearnerState.ML_INITIALIZING, false);

    AnalysisEngineDescription description = TextRulerToolkit
            .getAnalysisEngineDescription(descriptorFile);
    if (description == null) {
      sendStatusUpdateToDelegate("Failed to load descriptor. Please rebuild the project.",
              TextRulerLearnerState.ML_INITIALIZING, false);
      return;
    }
    TextRulerToolkit.addBoundaryTypes(description, slotNames);
    ae = TextRulerToolkit.loadAnalysisEngine(description);

    // set filters to NO filtering so that we can add it manually with
    // the FILTERTYPE expression!
    String tempRulesFileName = getTempRulesFileName();
    IPath path = new Path(tempRulesFileName);
    ae.setConfigParameterValue(RutaEngine.PARAM_MAIN_SCRIPT, path.removeFileExtension()
            .lastSegment());
    String portableString = path.removeLastSegments(1).toPortableString();
    ae.setConfigParameterValue(RutaEngine.PARAM_SCRIPT_PATHS, new String[] { portableString });
    ae.setConfigParameterValue(RutaEngine.PARAM_ADDITIONAL_SCRIPTS, new String[0]);
    ae.setConfigParameterValue(RutaEngine.PARAM_RELOAD_SCRIPT, true);
    if (useDynamicAnchoring) {
      ae.setConfigParameterValue(RutaEngine.PARAM_DYNAMIC_ANCHORING, true);
    }
    IPreferenceStore store = TextRulerPlugin.getDefault().getPreferenceStore();
    boolean lowMemoryProfile = store.getBoolean(TextRulerPreferences.LOW_MEMORY_PROFILE);
    boolean removeBasics = store.getBoolean(TextRulerPreferences.REMOVE_BASICS);
    ae.setConfigParameterValue(RutaEngine.PARAM_LOW_MEMORY_PROFILE, lowMemoryProfile);
    ae.setConfigParameterValue(RutaEngine.PARAM_REMOVE_BASICS, removeBasics);

    try {
      ae.reconfigure();
    } catch (ResourceConfigurationException e) {
      TextRulerPlugin.error(e);
    }
  }

  protected boolean checkForMandatoryTypes() {
    // check if all passed slot types are present:
    CAS someCas = getTestCAS();
    if (someCas == null) {
      return false;
    }
    TypeSystem ts = someCas.getTypeSystem();
    // GlobalCASSource.releaseCAS(someCas);
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
    if (!StringUtils.isEmpty(missingString)) {
      missingString = missingString.substring(0, missingString.length() - 2);
    }
    if (!result) {
      sendStatusUpdateToDelegate("Error: Some Slot- or Helper-Types were not found in TypeSystem: "
              + missingString, TextRulerLearnerState.ML_ERROR, false);
    }
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
      updateAE();
      if (ae == null) {
        return;
      }
      inducedRules.clear();
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
            TextRulerPlugin.error(e);
            sendStatusUpdateToDelegate("Aborted due to exception!", TextRulerLearnerState.ML_ERROR,
                    true);
          }

          if (TextRulerToolkit.DEBUG) {
            try {
              File file = new File(tempDirectory() + "results" + RutaEngine.SCRIPT_FILE_EXTENSION);
              FileUtils.saveString2File(getResultString(), file);
            } catch (Exception e) {
              TextRulerPlugin.error(e);
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
    return tempDirectory() + "rules" + RutaEngine.SCRIPT_FILE_EXTENSION;
  }

  public String getIntermediateRulesFileName() {
    return tempDirectory() + "intermediaterules" + RutaEngine.SCRIPT_FILE_EXTENSION;
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
    // GlobalCASSource.releaseCAS(testCAS);
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
      TextRulerPlugin.error(e);
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
      TextRulerPlugin.error(e);
    }
    AnalysisEngine analysisEngine = getAnalysisEngine();
    CAS testCAS = getTestCAS();
    doc.resetAndFillTestCAS(testCAS, target);
    try {
      analysisEngine.process(testCAS);
    } catch (AnalysisEngineProcessException e) {
      TextRulerPlugin.error(e);
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
    for (int ruleIndex = 0; ruleIndex < rules.size(); ruleIndex++) {
      TextRulerRule theRule = rules.get(ruleIndex);
      String ruleString = theRule.getRuleString();
      System.out.println("testing: " + ruleString);
      if (inducedRules.containsKey(ruleString)) {
        theRule.setCoveringStatistics(inducedRules.get(ruleString));
        System.out.println("skipped with " + inducedRules.get(ruleString));
      } else {
        TextRulerStatisticsCollector sumC = sums.get(ruleIndex);
        for (TextRulerExampleDocument theDoc : sortedDocs) {
          theDoc.resetAndFillTestCAS(theTestCAS, target);
          testRuleOnDocument(theRule, theDoc, sumC, theTestCAS);
          double errorRate = sumC.n / Math.max(sumC.p, 1);
          if (errorRate > maxErrorRate) {
            System.out.println("stopped:" + sumC);
            break;
          }
          if (shouldAbort())
            return;
        }
        inducedRules.put(ruleString, sumC);
      }
    }
    theTestCAS.reset();
    // GlobalCASSource.releaseCAS(theTestCAS);
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
    // GlobalCASSource.releaseCAS(theTestCAS);
    // do not release the shared test-cas ! only reset it ! it gets released
    // at the end of the
    // whole algorithm !
    for (int ruleIndex = 0; ruleIndex < rules.size(); ruleIndex++)
      rules.get(ruleIndex).setCoveringStatistics(sums.get(ruleIndex));
  }

  public String getFileHeaderString(boolean complete) {
    return getPackageString() + getScriptImport(complete) + getFilterCommandString()
            + getUseDynamicAnchoring(complete) + getBoundaryDeclarations(complete);
  }

  private String getBoundaryDeclarations(boolean complete) {
    if (complete && supportBoundaries && slotNames.length > 0) {
      StringBuilder sb = new StringBuilder();
      sb.append("DECLARE ");
      int count = 0;
      for (String slot : slotNames) {
        String[] split = slot.split("[.]");
        String shortName = split[split.length - 1];
        sb.append(shortName);
        sb.append("START");
        sb.append(", ");
        sb.append(shortName);
        sb.append("END");
        if (count < slotNames.length - 1) {
          sb.append(", ");
        }
        count++;
      }
      sb.append(";\n");
      return sb.toString();
    }
    return "";
  }

  private String getUseDynamicAnchoring(boolean complete) {
    if (useDynamicAnchoring && complete) {
      return "Document{-> DYNAMICANCHORING(true)};\n";
    } else {
      return "";
    }
  }

  private String getScriptImport(boolean complete) {
    if (complete) {
      IPath path = Path.fromOSString(preprocessorFile);
      IPath removeLastSegments = path.removeLastSegments(1);
      IContainer containerForLocation = ResourcesPlugin.getWorkspace().getRoot()
              .getContainerForLocation(removeLastSegments);
      IProject project = containerForLocation.getProject();
      String scriptWithPackage = null;
      try {
        scriptWithPackage = RutaProjectUtils.getScriptWithPackage(path, project);
      } catch (CoreException e) {
      }
      String moduleName = RutaProjectUtils.getModuleName(path);
      if (scriptWithPackage != null) {
        String importString = "SCRIPT " + scriptWithPackage + ";\n";
        if (!skip) {
          importString += "Document{-> CALL(" + moduleName + ")};\n";
        }
        return importString;
      }
    }
    return "";
  }

  public String getPackageString() {
    IPath path = Path.fromOSString(preprocessorFile);
    IPath removeLastSegments = path.removeLastSegments(1);
    IContainer containerForLocation = ResourcesPlugin.getWorkspace().getRoot()
            .getContainerForLocation(removeLastSegments);
    IPath removeFirstSegments = containerForLocation.getProjectRelativePath()
            .removeFirstSegments(1);
    String replaceAll = removeFirstSegments.toPortableString().replaceAll("/", ".");
    return "PACKAGE " + replaceAll + ";\n\n";
  }

  public String getFilterCommandString() {
    if (filterSet != null && filterSet.size() > 0 && !isDefaultFiltering()) {
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

  private boolean isDefaultFiltering() {
    return useDefaultFiltering;
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
        TextRulerPlugin.error(e);
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
      str += "\npreprocessTMFile: " + preprocessorFile;
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
