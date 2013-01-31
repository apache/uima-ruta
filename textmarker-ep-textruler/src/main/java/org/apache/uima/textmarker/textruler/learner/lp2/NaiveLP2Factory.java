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

package org.apache.uima.textmarker.textruler.learner.lp2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.uima.textmarker.textruler.extension.TextRulerLearner;
import org.apache.uima.textmarker.textruler.extension.TextRulerLearnerDelegate;
import org.apache.uima.textmarker.textruler.extension.TextRulerLearnerFactory;
import org.apache.uima.textmarker.textruler.extension.TextRulerLearnerParameter;
import org.apache.uima.textmarker.textruler.extension.TextRulerLearnerParameter.MLAlgorithmParamType;

public class NaiveLP2Factory implements TextRulerLearnerFactory {

  public TextRulerLearner createAlgorithm(String inputFolderPath, String additionalFolderPath,
          String prePropTMFile, String tempFolderPath, String[] fullSlotTypeNames,
          Set<String> filterSet, TextRulerLearnerDelegate delegate) {
    return new NaiveLP2(inputFolderPath, prePropTMFile, tempFolderPath, fullSlotTypeNames,
            filterSet, delegate);
  }

  public TextRulerLearnerParameter[] getAlgorithmParameters() {
    TextRulerLearnerParameter[] result = new TextRulerLearnerParameter[5];

    result[0] = new TextRulerLearnerParameter(BasicLP2.WINDOW_SIZE_KEY,
            "Context Window Size (to the left and right)", MLAlgorithmParamType.ML_INT_PARAM);
    result[1] = new TextRulerLearnerParameter(BasicLP2.CURRENT_BEST_RULES_SIZE_KEY,
            "Best Rules List Size", MLAlgorithmParamType.ML_INT_PARAM);
    result[2] = new TextRulerLearnerParameter(BasicLP2.MIN_COVERED_POSITIVES_PER_RULE_KEY,
            "Minimum Covered Positives per Rule", MLAlgorithmParamType.ML_INT_PARAM);
    result[3] = new TextRulerLearnerParameter(BasicLP2.MAX_ERROR_THRESHOLD_KEY,
            "Maximum Error Threshold", MLAlgorithmParamType.ML_FLOAT_PARAM);
    result[4] = new TextRulerLearnerParameter(BasicLP2.CURRENT_CONTEXTUAL_RULES_SIZE_KEY,
            "Contextual Rules List Size", MLAlgorithmParamType.ML_INT_PARAM);

    return result;
  }

  public Map<String, Object> getAlgorithmParameterStandardValues() {
    Map<String, Object> result = new HashMap<String, Object>();
    result.put(BasicLP2.WINDOW_SIZE_KEY, BasicLP2.STANDARD_WINDOW_SIZE);
    result
            .put(BasicLP2.CURRENT_BEST_RULES_SIZE_KEY,
                    BasicLP2.STANDARD_MAX_CURRENT_BEST_RULES_COUNT);
    result.put(BasicLP2.MIN_COVERED_POSITIVES_PER_RULE_KEY,
            BasicLP2.STANDARD_MIN_COVERED_POSITIVES_PER_RULE);
    result.put(BasicLP2.MAX_ERROR_THRESHOLD_KEY, BasicLP2.STANDARD_MAX_ERROR_THRESHOLD);
    result.put(BasicLP2.CURRENT_CONTEXTUAL_RULES_SIZE_KEY,
            BasicLP2.STANDARD_MAX_CONTEXTUAL_RULES_COUNT);
    return result;
  }

}
