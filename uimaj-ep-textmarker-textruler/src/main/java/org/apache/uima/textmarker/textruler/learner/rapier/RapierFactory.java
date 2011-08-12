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

package org.apache.uima.textmarker.textruler.learner.rapier;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.uima.textmarker.textruler.extension.TextRulerLearner;
import org.apache.uima.textmarker.textruler.extension.TextRulerLearnerDelegate;
import org.apache.uima.textmarker.textruler.extension.TextRulerLearnerFactory;
import org.apache.uima.textmarker.textruler.extension.TextRulerLearnerParameter;
import org.apache.uima.textmarker.textruler.extension.TextRulerLearnerParameter.MLAlgorithmParamType;

public class RapierFactory implements TextRulerLearnerFactory {

  public TextRulerLearner createAlgorithm(String inputFolderPath, String additionalFolderPath,
          String prePropTMFile, String tempFolderPath, String[] fullSlotTypeNames,
          Set<String> filterSet, TextRulerLearnerDelegate delegate) {
    return new Rapier(inputFolderPath, prePropTMFile, tempFolderPath, fullSlotTypeNames, filterSet,
            delegate);
  }

  public TextRulerLearnerParameter[] getAlgorithmParameters() {
    TextRulerLearnerParameter[] result = new TextRulerLearnerParameter[8];

    result[0] = new TextRulerLearnerParameter(Rapier.COMPRESSION_FAIL_MAX_COUNT_KEY,
            "Maximum Compression Fail Count", MLAlgorithmParamType.ML_INT_PARAM);
    result[1] = new TextRulerLearnerParameter(Rapier.RULELIST_SIZE_KEY, "Internal Rules List Size",
            MLAlgorithmParamType.ML_INT_PARAM);
    result[2] = new TextRulerLearnerParameter(Rapier.PAIR_COUNT_KEY, "Rule Pairs for Generalizing",
            MLAlgorithmParamType.ML_INT_PARAM);
    result[3] = new TextRulerLearnerParameter(Rapier.LIM_NO_IMPROVEMENTS_KEY,
            "Maximum 'No improvement' Count", MLAlgorithmParamType.ML_INT_PARAM);
    result[4] = new TextRulerLearnerParameter(Rapier.NOISE_THESHOLD_KEY, "Maximum Noise Threshold",
            MLAlgorithmParamType.ML_FLOAT_PARAM);
    result[5] = new TextRulerLearnerParameter(Rapier.MIN_COVERED_POSITIVES_KEY,
            "Minimum Covered Positives Per Rule", MLAlgorithmParamType.ML_INT_PARAM);
    result[6] = new TextRulerLearnerParameter(Rapier.POSTAG_ROOTTYPE_KEY, "PosTag Root Type",
            MLAlgorithmParamType.ML_STRING_PARAM);
    result[7] = new TextRulerLearnerParameter(Rapier.USE_ALL_GENSETS_AT_SPECIALIZATION_KEY,
            "Use All 3 GenSets at Specialization", MLAlgorithmParamType.ML_BOOL_PARAM);
    return result;
  }

  public Map<String, Object> getAlgorithmParameterStandardValues() {
    Map<String, Object> result = new HashMap<String, Object>();
    result.put(Rapier.COMPRESSION_FAIL_MAX_COUNT_KEY, Rapier.STANDARD_COMPRESSION_FAIL_MAX_COUNT);
    result.put(Rapier.RULELIST_SIZE_KEY, Rapier.STANDARD_RULELIST_SIZE);
    result.put(Rapier.PAIR_COUNT_KEY, Rapier.STANDARD_PAIR_COUNT);
    result.put(Rapier.LIM_NO_IMPROVEMENTS_KEY, Rapier.STANDARD_LIM_NO_IMPROVEMENTS);
    result.put(Rapier.NOISE_THESHOLD_KEY, Rapier.STANDARD_NOISE_THREHSOLD);
    result.put(Rapier.POSTAG_ROOTTYPE_KEY, Rapier.STANDARD_POSTAG_ROOTTYPE);
    result.put(Rapier.MIN_COVERED_POSITIVES_KEY, Rapier.STANDARD_MIN_COVERED_POSITIVES);
    result.put(Rapier.USE_ALL_GENSETS_AT_SPECIALIZATION_KEY,
            Rapier.STANDARD_USE_ALL_GENSETS_AT_SPECIALIZATION);
    return result;
  }
}
