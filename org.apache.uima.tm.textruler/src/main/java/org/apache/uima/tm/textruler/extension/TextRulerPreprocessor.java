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

package org.apache.uima.tm.textruler.extension;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.tm.textruler.core.GlobalCASSource;
import org.apache.uima.tm.textruler.core.TextRulerToolkit;
import org.eclipse.core.runtime.Path;


/**
 * This "algorithm" gets executed right before the real algorithms get started. It preprocesses the
 * input XMI files with the given TextMarker preprocessing file and stores the results in a
 * temporary folder. Those new XMI files are then passed as input documents to the MLAlgorithms.
 * 
 */
public class TextRulerPreprocessor {

  public String run(String inFolder, String tmFile, String tmpDir,
          TextRulerPreprocessorDelegate delegate) {
    AnalysisEngine ae = TextRulerToolkit.loadAnalysisEngine(TextRulerToolkit
            .getEngineDescriptorFromTMSourceFile(new Path(tmFile)));

    // preprocess input XMIs
    File inputFolder = new File(inFolder);
    File outputFolder = new File(tmpDir + "input");
    File[] files = inputFolder.listFiles(new FilenameFilter() {
      public boolean accept(File dir, String name) {
        return (name.endsWith(".xmi"));
      }
    });

    try {
      outputFolder.mkdir();
      // FileUtils.saveString2File("PACKAGE de.uniwue.ml;\n\n", new
      // File(tmpDir+"preprocess.tm"));
      // ae.setConfigParameterValue(TextMarkerEngine.SCRIPT_LOCATION,
      // tmpDir+"preprocess.tm");
      // IPath path = new Path(tmFile);
      // IPath scriptRoot = new Path(tmFile);
      // while (!scriptRoot.lastSegment().equals(TextMarkerProjectUtils.getDefaultScriptLocation()))
      // {
      // scriptRoot = scriptRoot.removeLastSegments(1);
      // }
      // IPath makeRelativeTo = path.makeRelativeTo(scriptRoot);
      //
      // String replaceAll =
      // makeRelativeTo.removeFileExtension().toPortableString().replaceAll("[/]",
      // ".");
      // Object configParameterValue = ae.getConfigParameterValue(TextMarkerEngine.MAIN_SCRIPT);
      // ae.setConfigParameterValue(TextMarkerEngine.MAIN_SCRIPT, replaceAll);
      // ae.setConfigParameterValue(TextMarkerEngine.SCRIPT_PATHS, new String[] { scriptRoot
      // .toPortableString() });
      // ae.setConfigParameterValue(TextMarkerEngine.USE_SCANNER, true);
      // ae.reconfigure();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

    CAS cas = null;
    for (File file : files) {
      if (delegate != null && delegate.shouldAbort()) {
        TextRulerToolkit.log("[PREPROCESSOR] ABORT");
        break;
      }
      TextRulerToolkit.log("Load INPUT XMI file: " + file.getName());
      if (delegate != null)
        delegate.preprocessorStatusUpdate(this, "Loading input XMI file: " + file.getName());
      cas = TextRulerToolkit.readCASfromXMIFile(file, ae, cas);
      System.out.print("Processing...");
      try {
        ae.process(cas);
        TextRulerToolkit.log(" OK");
        TextRulerToolkit.writeCAStoXMIFile(cas, outputFolder + "/processed_" + file.getName());
      } catch (Exception e) {
        e.printStackTrace();
        TextRulerToolkit.log(" ERROR!");
      }
      cas.reset();
      if (delegate != null)
        delegate.preprocessorStatusUpdate(this, "Done");
    }
    if (cas != null) {
      cas.reset();
      GlobalCASSource.releaseCAS(cas);
    }
    return tmpDir + "input";
  }

}
