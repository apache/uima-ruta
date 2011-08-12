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

package org.apache.uima.textmarker.textruler.extension;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.apache.uima.textmarker.textruler.core.TextRulerToolkit;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

/**
 * The static class TextRulerController is the heart of the eclipse plug-in extension. It cares
 * about the extension point and creates TextRulerLearnerController objects for the found algorithms
 * and is the link to a UI that can build upon this controller (see .ui package).
 * 
 * A UI (or whoever) is notified about e.g. status updates via the TextRulerControllerDelegate
 * interface.
 */
public class TextRulerController {

  protected static ArrayList<TextRulerLearnerController> learners = null;

  protected static String currentTempDir = null;

  protected static String[] currentSlotNames = null;

  protected static Set<String> currentFilters = null;

  protected static TextRulerControllerDelegate currentDelegate = null;

  protected static Thread preprocessorThread = null;

  protected static boolean shouldAbort = false;

  protected static String currentPreprocessorTMFile = null;

  protected static Map<String, Map<String, Object>> currentAlgorithmParams;

  private static boolean anAlgorithmIsRunning() {
    for (TextRulerLearnerController i : getAvailableControllers()) {
      if (i.isRunning())
        return true;
    }

    return false;
  }

  public static int enabledAlgorithmsCount() {
    int cnt = 0;
    for (TextRulerLearnerController c : learners) {
      if (c.isEnabled())
        cnt++;
    }
    return cnt;
  }

  private static void preprocessorDidEnd(String algorithmsInputFolder, String additionalFolder) {
    if (shouldAbort()) {
      if (currentDelegate != null)
        currentDelegate.globalStatusUpdate("Aborted!");
      cleanUp();
    } else {
      if (currentDelegate != null)
        currentDelegate.globalStatusUpdate("Running...");
      // start algorithms
      for (TextRulerLearnerController c : learners) {
        if (c.isEnabled())
          c.runInNewThread(algorithmsInputFolder, additionalFolder, currentPreprocessorTMFile,
                  currentSlotNames, currentFilters, currentTempDir, currentDelegate,
                  currentAlgorithmParams.get(c.getID()));
      }

    }
    preprocessorThread = null;
  }

  private static void cleanUp() {
    // cleaning up
    // TODO delete all temporary files and directories?! this data are good
    // for debugging...
    currentTempDir = null;
    currentPreprocessorTMFile = null;
    currentSlotNames = null;
    currentFilters = null;
    currentDelegate = null;
    currentAlgorithmParams = null;
    shouldAbort = false;
  }

  protected static void saveParametersToTempFolder(String inFolder, boolean skipPreprocessing) {
    String str = "\nSettings:\n\n";

    str += "inputDir: " + inFolder;
    str += "\npreprocessTMFile: " + currentPreprocessorTMFile;
    str += "\nslotnames : ";
    for (String s : currentSlotNames)
      str += s + "; ";
    str += "\nfilters: " + currentFilters;
    str += "\nskip preproc: " + skipPreprocessing;
    str += "\n";

    TextRulerToolkit.appendStringToFile(TextRulerToolkit.addTrailingSlashToPath(currentTempDir)
            + "settings.txt", str);
  }

  public static boolean start(final String inFolder, final String additionalFolder,
          final String preprocessorTMFile, final String[] slotNames, Set<String> filters,
          final TextRulerControllerDelegate delegate, Map<String, Map<String, Object>> algParams,
          boolean skipPreprocessing) {
    if (isRunning() || enabledAlgorithmsCount() == 0)
      return false;

    shouldAbort = false;

    String outFolder = null;
    try {
      currentTempDir = TextRulerToolkit.createTemporaryDirectory();
      outFolder = currentTempDir + "input";
      File outputFolder = new File(outFolder);
      outputFolder.mkdir();
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }

    if (TextRulerToolkit.getEngineDescriptorFromTMSourceFile(new Path(preprocessorTMFile)).length() == 0)
      return false;

    currentPreprocessorTMFile = preprocessorTMFile;
    currentSlotNames = slotNames;
    currentFilters = filters;
    currentDelegate = delegate;
    currentAlgorithmParams = algParams;

    saveParametersToTempFolder(inFolder, skipPreprocessing);
    for (TextRulerLearnerController c : learners)
      c.resetStatusString();

    if (skipPreprocessing)
      preprocessorDidEnd(inFolder, additionalFolder); // just use the original input folder
    // as real input folder!
    else {
      preprocessorThread = new Thread(new Runnable() {
        public void run() {
          TextRulerPreprocessor p = new TextRulerPreprocessor();
          String algorithmsInputFolder = p.run(inFolder, currentPreprocessorTMFile, currentTempDir,
                  new TextRulerPreprocessorDelegate() {

                    public void preprocessorStatusUpdate(TextRulerPreprocessor p,
                            String statusString) {
                      currentDelegate.preprocessorStatusUpdate(p, statusString);
                    }

                    public boolean shouldAbort() {
                      return TextRulerController.shouldAbort();
                    }

                  });
          preprocessorDidEnd(algorithmsInputFolder, additionalFolder);
        }
      });
      if (currentDelegate != null)
        currentDelegate.globalStatusUpdate("Preprocessing...");

      preprocessorThread.setPriority(Thread.NORM_PRIORITY);
      preprocessorThread.start();
    }
    return true;
  }

  public static TextRulerLearnerController getControllerForID(String id) {
    for (TextRulerLearnerController c : getAvailableControllers())
      if (c.getID().equals(id))
        return c;
    return null;
  }

  public static ArrayList<TextRulerLearnerController> getAvailableControllers() {
    if (learners == null) {
      learners = new ArrayList<TextRulerLearnerController>();
      IExtensionRegistry reg = Platform.getExtensionRegistry();
      if (reg == null)
        return null;
      IConfigurationElement[] extensions = reg
              .getConfigurationElementsFor("org.apache.uima.textmarker.textruler.learners");
      for (IConfigurationElement element : extensions) {
        try {
          TextRulerLearnerFactory factory = (TextRulerLearnerFactory) element
                  .createExecutableExtension("class");
          learners.add(new TextRulerLearnerController(element.getAttribute("id"), element
                  .getAttribute("name"), factory));
        } catch (CoreException e) {
          e.printStackTrace();
        }
      }
    }
    return learners;
  }

  // gets called from TextRulerLearnerController when the algorithm thread did
  // end
  public static void anAlgorithmDidEnd(TextRulerLearnerController algController) {
    if (!anAlgorithmIsRunning()) // all algorithms have finished
    {
      if (currentDelegate != null)
        currentDelegate.globalStatusUpdate(shouldAbort ? "Aborted!" : "Done.");
      cleanUp();
    }
  }

  public static boolean isRunning() {
    return preprocessorThread != null || anAlgorithmIsRunning();
  }

  public static synchronized boolean shouldAbort() {
    return shouldAbort;
  }

  public static synchronized void abort() {
    shouldAbort = true;
  }
}
