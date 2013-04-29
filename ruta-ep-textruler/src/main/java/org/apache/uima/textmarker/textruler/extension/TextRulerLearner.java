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

import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;

/**
 * If you want to add an own algorithm to the TextRuler-System, you can do this by providing two
 * classes:
 * 
 * One algorithm class that implements the interface
 * 
 * TextRulerLearner
 * 
 * And one factory class that can create an instance of that algorithm. This factory has to
 * implement the interface
 * 
 * MLAlgoriothmFactory
 * 
 * which gets created and used by adding an extension point to
 * 
 * org.apache.uima.textmarker.textruler.algorithms
 * 
 */
public interface TextRulerLearner {

  enum TextRulerLearnerState {
    ML_UNDEFINED, ML_INITIALIZING, ML_RUNNING, ML_ERROR, ML_ABORTED, ML_DONE
  };

  /**
   * There the magic has to be placed...
   */
  void run();

  /**
   * this method gets called from the UI and passes a hashMap with key value coded parameters that
   * your corresponding TextRulerLearnerFactory declared by its getAlgorithmParameters method.
   */
  void setParameters(Map<String, Object> params);

  /**
   * If any TextMarker-Rules result is available (yet), the system asks your algorithm for it by
   * calling this method.
   */
  String getResultString();

  /**
   * 
   * @return
   */
  AnalysisEngine getAnalysisEngine();

  /**
   * 
   * @return
   */
  CAS getTestCAS();
}
