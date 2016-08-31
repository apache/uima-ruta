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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.uima.ruta.textruler.extension.TextRulerLearner;
import org.apache.uima.ruta.textruler.extension.TextRulerLearnerDelegate;
import org.apache.uima.ruta.textruler.extension.TextRulerLearnerFactory;
import org.apache.uima.ruta.textruler.extension.TextRulerLearnerParameter;
import org.apache.uima.ruta.textruler.extension.TextRulerLearnerParameter.MLAlgorithmParamType;

public class TrabalFactory implements TextRulerLearnerFactory {

  public TrabalFactory() {
  }

  public Map<String, Object> getAlgorithmParameterStandardValues() {
    Map<String, Object> values = new HashMap<String, Object>();
    values.put(TrabalLearner.ALGORITHM_ITERATIONS_KEY, TrabalLearner.ALGORITHM_ITERATIONS);
    values.put(TrabalLearner.MAX_NUMBER_OF_BASIC_RULES_KEY, TrabalLearner.MAX_NUMBER_OF_BASIC_RULES);
    values.put(TrabalLearner.MAX_NUMBER_OF_RULES_KEY, TrabalLearner.MAX_NUMBER_OF_RULES);
    values.put(TrabalLearner.MAX_NUMBER_OF_ITERATIONS_KEY, TrabalLearner.MAX_NUMBER_OF_ITERATIONS);
    values.put(TrabalLearner.MAX_ERROR_RATE_KEY, TrabalLearner.MAX_ERROR_RATE);
    values.put(TrabalLearner.ENABLE_FEATURES_KEY, TrabalLearner.ENABLE_FEATURES);
    return values;
  }

  public TextRulerLearner createAlgorithm(String inputFolderPath, String additionalFolderPath,
          String preprocessorRutaFile, String tempFolderPath, String[] fullSlotTypeNames,
          Set<String> filterSet, boolean skip, TextRulerLearnerDelegate delegate) {
    return new TrabalLearner(inputFolderPath, additionalFolderPath, preprocessorRutaFile,
            tempFolderPath, fullSlotTypeNames, filterSet, skip, delegate);
  }

  public TextRulerLearnerParameter[] getAlgorithmParameters() {
    TextRulerLearnerParameter[] result = new TextRulerLearnerParameter[6];
    result[0] = new TextRulerLearnerParameter(TrabalLearner.ALGORITHM_ITERATIONS_KEY,
            "Number of times, the algorithm iterates.", MLAlgorithmParamType.ML_INT_PARAM);
    result[1] = new TextRulerLearnerParameter(TrabalLearner.MAX_NUMBER_OF_BASIC_RULES_KEY,
            "Number of basic rules to be created for one example.",
            MLAlgorithmParamType.ML_INT_PARAM);
    result[2] = new TextRulerLearnerParameter(TrabalLearner.MAX_NUMBER_OF_RULES_KEY,
            "Number of optimized rules to be created for one example.",
            MLAlgorithmParamType.ML_INT_PARAM);
    result[3] = new TextRulerLearnerParameter(TrabalLearner.MAX_NUMBER_OF_ITERATIONS_KEY,
            "Maximum number of iterations, when optimizing rules.",
            MLAlgorithmParamType.ML_INT_PARAM);
    result[4] = new TextRulerLearnerParameter(TrabalLearner.MAX_ERROR_RATE_KEY,
            "Maximum allowed error rate.", MLAlgorithmParamType.ML_DOUBLE_PARAM);
    result[5] = new TextRulerLearnerParameter(TrabalLearner.ENABLE_FEATURES_KEY,
            "Correct features in rules and conditions.", MLAlgorithmParamType.ML_BOOL_PARAM);
    return result;
  }

}
