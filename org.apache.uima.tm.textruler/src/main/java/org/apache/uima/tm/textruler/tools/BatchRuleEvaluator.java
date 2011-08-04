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
import java.io.IOException;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.tm.textmarker.engine.TextMarkerEngine;
import org.apache.uima.tm.textruler.core.TextRulerToolkit;
import org.apache.uima.util.FileUtils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

public class BatchRuleEvaluator {

  private static AnalysisEngine ae;

  private static String engineFile;

  // private static String preprocessorTMFile;
  private static String tempDir;

  private static String foldRootDirectory;

  private static int foldCount = 0;

  private static CAS sharedCAS;

  public static AnalysisEngine getAnalysisEngine() {
    if (ae == null) {
      // String descriptorFile =
      // TextRulerToolkit.getEngineDescriptorFromTMSourceFile(new
      // Path(preprocessorTMFile));
      String descriptorFile = engineFile;
      TextRulerToolkit.log("loading AE...");
      ae = TextRulerToolkit.loadAnalysisEngine(descriptorFile);

      // set filters to NO filtering so that we can add it manually with
      // the FILTERTYPE expression!
      ae.setConfigParameterValue(TextMarkerEngine.DEFAULT_FILTERED_MARKUPS, new String[0]);
      IPath path = new Path(tempDir + "/results.tm");
      ae.setConfigParameterValue(TextMarkerEngine.MAIN_SCRIPT, path.removeFileExtension()
              .lastSegment());
      ae.setConfigParameterValue(TextMarkerEngine.SCRIPT_PATHS, new String[] { path
              .removeLastSegments(1).toPortableString() });
      // ae.setConfigParameterValue(TextMarkerEngine.SEEDERS, new String[] {});
      try {
        ae.reconfigure();
      } catch (ResourceConfigurationException e) {
        e.printStackTrace();
        return null;
      }
    }
    return ae;
  }

  private static File[] getXMIFileFromFolder(String folderName) {
    File folder = new File(folderName);
    File[] files = folder.listFiles(new FilenameFilter() {
      public boolean accept(File dir, String name) {
        return (name.endsWith(".xmi"));
      }
    });
    return files;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {

    tempDir = "/testinput/temp/";
    engineFile = "/Users/tobi/Documents/UniLaptop/Diplomarbeit/TestDataSets/withPosTags/Subset100/10fold/desc/lp2ergebnisrandomgiantEngine.xml";
    foldRootDirectory = "/Users/tobi/Documents/UniLaptop/Diplomarbeit/TestDataSets/withPosTags/9010_middle/";// mit
    foldCount = 1;
    String slotNames[] = { "org.apache.uima.tm.ml.types.etime",
        "org.apache.uima.tm.ml.types.stime", "org.apache.uima.tm.ml.types.location",
        "org.apache.uima.tm.ml.types.speaker" };
    String algIDs[] = { "optimizedLP2"// ,
    };

    // TODO back to 0 !
    for (int foldNumber = 0; foldNumber < foldCount; foldNumber++) {
      for (String slotName : slotNames) {
        for (String algID : algIDs) {
          runRules(foldNumber, slotName, algID);
        }
      }
    }

  }

  public static void runRules(int foldNumber, String slotName, String algorithmID) {
    getAnalysisEngine();

    TextRulerToolkit.log("Testing Fold Number " + foldNumber + "\t  Slot: " + slotName
            + "\t  Algorithm: " + algorithmID);

    // Input: 0/testing/withouttags/*.xmi
    String inputFolder = foldRootDirectory + foldNumber + "/testing/withouttags/";
    String rulesFile = foldRootDirectory + foldNumber + "/learnResults/" + slotName + "/"
            + algorithmID + "/results.tm";
    String scriptFile = tempDir + "results.tm";
    File oldScriptFile = new File(scriptFile);
    if (oldScriptFile.exists()) {
      if (!oldScriptFile.delete()) {
        TextRulerToolkit.log("ERROR DELETING OLD SCRIPT FILE: " + scriptFile);
        return;
      }
    }

    if (!new File(rulesFile).exists()) {
      TextRulerToolkit.log("\tSKIPPED, no rules file not found!");
      return;
    }

    try {
      FileUtils.copyFile(new File(rulesFile), new File(tempDir));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return;
    }

    String outputFolder1 = foldRootDirectory + foldNumber + "/testing/markedFromRules";
    String outputFolder2 = foldRootDirectory + foldNumber + "/testing/markedFromRules/" + slotName;
    String outputFolder = foldRootDirectory + foldNumber + "/testing/markedFromRules/" + slotName
            + "/" + algorithmID;
    new File(outputFolder1).mkdir();
    new File(outputFolder2).mkdir();
    new File(outputFolder).mkdir();

    File[] inputFiles = getXMIFileFromFolder(inputFolder);

    for (File inputFile : inputFiles) {
      // TextRulerToolkit.log("FILE TO TEST: "+inputFile.getAbsolutePath());
      // TextRulerToolkit.log(" RESULT FILE: "+TextRulerToolkit.addTrailingSlashToPath(outputFolder)+"fromRules_"+inputFile.getName());

      sharedCAS = TextRulerToolkit.readCASfromXMIFile(inputFile, ae, sharedCAS);

      try {
        ae.process(sharedCAS);
      } catch (AnalysisEngineProcessException e) {
        e.printStackTrace();
        return;
      }
      TextRulerToolkit.writeCAStoXMIFile(
              sharedCAS,
              TextRulerToolkit.addTrailingSlashToPath(outputFolder) + "fromRules_"
                      + inputFile.getName());
    }
  }

}
