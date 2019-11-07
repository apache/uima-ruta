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

package org.apache.uima.ruta.textruler.extension;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.apache.uima.ruta.textruler.TextRulerPlugin;
import org.apache.uima.ruta.textruler.core.GlobalCASSource;
import org.apache.uima.ruta.textruler.core.TextRulerToolkit;
import org.eclipse.core.runtime.CoreException;

/**
 * This "algorithm" gets executed right before the real algorithms get started. It preprocesses the
 * input XMI files with the given Ruta preprocessing file and stores the results in a temporary
 * folder. Those new XMI files are then passed as input documents to the MLAlgorithms.
 * 
 */
public class TextRulerPreprocessor {

  public String run(String inFolder, String rutaFile, String tmpDir, String[] currentSlotNames,
          TextRulerPreprocessorDelegate delegate) {
    return run(inFolder, "input", rutaFile, tmpDir, currentSlotNames, delegate);
  }

  public String run(String inFolder, String docType, String rutaFile, String tmpDir,
          String[] currentSlotNames, TextRulerPreprocessorDelegate delegate) {
    if (StringUtils.isBlank(inFolder)) {
      return inFolder;
    }

    AnalysisEngineDescription analysisEngineDescription = null;
    try {
      analysisEngineDescription = TextRulerToolkit.getAnalysisEngineDescription(
              RutaProjectUtils.getAnalysisEngineDescriptorPath(rutaFile).toPortableString());
    } catch (CoreException e) {
      TextRulerPlugin.error(e);
    }
    if (analysisEngineDescription == null) {
      delegate.preprocessorStatusUpdate(this, "Descriptor is missing. Please rebuild the project.");
      return null;
    }

    // we want to reuse these cases, so extend the type system in case a boundary-based learner is
    // called
    TextRulerToolkit.addBoundaryTypes(analysisEngineDescription, currentSlotNames);
    AnalysisEngine ae = TextRulerToolkit.loadAnalysisEngine(analysisEngineDescription);

    // preprocess input XMIs
    File inputFolder = new File(inFolder);
    File outputFolder = new File(tmpDir + docType);
    File[] files = inputFolder.listFiles(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return (name.endsWith(".xmi"));
      }
    });

    try {
      outputFolder.mkdir();
    } catch (Exception e) {
      TextRulerPlugin.error(e);
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
        delegate.preprocessorStatusUpdate(this,
                "Loading input XMI file (" + docType + "): " + file.getName());
      cas = TextRulerToolkit.readCASfromXMIFile(file, ae, cas);
      try {
        ae.process(cas);
        TextRulerToolkit.log(" OK");
        TextRulerToolkit.writeCAStoXMIFile(cas, outputFolder + "/processed_" + file.getName());
      } catch (Exception e) {
        TextRulerPlugin.error(e);
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
    return tmpDir + docType;
  }

}
