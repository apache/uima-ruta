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

package org.apache.uima.textmarker.textruler.learner.whisk.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.uima.textmarker.textruler.extension.TextRulerLearner;
import org.apache.uima.textmarker.textruler.extension.TextRulerLearnerDelegate;
import org.apache.uima.textmarker.textruler.extension.TextRulerLearnerFactory;
import org.apache.uima.textmarker.textruler.extension.TextRulerLearnerParameter;
import org.apache.uima.textmarker.textruler.extension.TextRulerLearnerParameter.MLAlgorithmParamType;

public class WhiskFactory implements TextRulerLearnerFactory {

  public TextRulerLearner createAlgorithm(String inputFolderPath, String additionalFolderPath,
          String preprocessorTMfile, String tempFolderPath, String[] fullSlotTypeNames,
          Set<String> filterSet, TextRulerLearnerDelegate delegate) {
    return new Whisk(inputFolderPath, preprocessorTMfile, tempFolderPath, fullSlotTypeNames,
            filterSet, delegate);
  }

  public Map<String, Object> getAlgorithmParameterStandardValues() {
    Map<String, Object> result = new HashMap<String, Object>();
    result.put(Whisk.WINDOWSIZE_KEY, Whisk.STANDARD_WINDOWSIZE);
    result.put(Whisk.ERROR_THRESHOLD_KEY, Whisk.STANDARD_ERROR_THRESHOLD);
    result.put(Whisk.POSTAG_ROOTTYPE_KEY, Whisk.STANDARD_POSTAG_ROOTTYPE);
    return result;
  }

  public TextRulerLearnerParameter[] getAlgorithmParameters() {
    TextRulerLearnerParameter[] result = new TextRulerLearnerParameter[3];

    result[0] = new TextRulerLearnerParameter(Whisk.WINDOWSIZE_KEY, "Window Size",
            MLAlgorithmParamType.ML_INT_PARAM);
    result[1] = new TextRulerLearnerParameter(Whisk.ERROR_THRESHOLD_KEY, "Maximum Error Threshold",
            MLAlgorithmParamType.ML_FLOAT_PARAM);
    result[2] = new TextRulerLearnerParameter(Whisk.POSTAG_ROOTTYPE_KEY, "PosTag Root Type",
            MLAlgorithmParamType.ML_STRING_PARAM);

    return result;
  }

}
