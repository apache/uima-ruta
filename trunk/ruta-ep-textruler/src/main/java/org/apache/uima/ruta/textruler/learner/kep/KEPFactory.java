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

package org.apache.uima.ruta.textruler.learner.kep;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.uima.ruta.textruler.extension.TextRulerLearner;
import org.apache.uima.ruta.textruler.extension.TextRulerLearnerDelegate;
import org.apache.uima.ruta.textruler.extension.TextRulerLearnerFactory;
import org.apache.uima.ruta.textruler.extension.TextRulerLearnerParameter;
import org.apache.uima.ruta.textruler.extension.TextRulerLearnerParameter.MLAlgorithmParamType;

public class KEPFactory implements TextRulerLearnerFactory {

  public KEPFactory() {
  }

  public TextRulerLearner createAlgorithm(String inputFolderPath, String additionalFolderPath,
          String preprocessorTMfile, String tempFolderPath, String[] fullSlotTypeNames,
          Set<String> filterSet, boolean skip, TextRulerLearnerDelegate delegate) {
    return new KEPLearner(inputFolderPath, preprocessorTMfile, tempFolderPath, fullSlotTypeNames,
            filterSet, skip, delegate);
  }

  public Map<String, Object> getAlgorithmParameterStandardValues() {
    Map<String, Object> result = new HashMap<String, Object>();
//    result.put(KEPLearner.FILLER_WINDOW, KEPLearner.DEFAULT_FILLER_WINDOW);
//    result.put(KEPLearner.MAX_FILLER_LENGTH, KEPLearner.DEFAULT_MAX_FILLER_LENGTH);
    result.put(KEPLearner.MAX_EXPAND_RULES, KEPLearner.DEFAULT_MAX_EXPAND_RULES);
    result.put(KEPLearner.MAX_INFILLER_RULES, KEPLearner.DEFAULT_MAX_INFILLER_RULES);
    return result;
  }

  public TextRulerLearnerParameter[] getAlgorithmParameters() {
    TextRulerLearnerParameter[] result = new TextRulerLearnerParameter[2];
//    result[0] = new TextRulerLearnerParameter(KEPLearner.FILLER_WINDOW,
//            "fillerWindow", MLAlgorithmParamType.ML_INT_PARAM);
//    result[1] = new TextRulerLearnerParameter(KEPLearner.MAX_FILLER_LENGTH,
//            "maxFillerLength", MLAlgorithmParamType.ML_INT_PARAM);
    result[0] = new TextRulerLearnerParameter(KEPLearner.MAX_EXPAND_RULES,
            "Maximum number of \"Expand Rules\"", MLAlgorithmParamType.ML_INT_PARAM);
    result[1] = new TextRulerLearnerParameter(KEPLearner.MAX_INFILLER_RULES,
            "Maximum number of \"Infiller Rules\"", MLAlgorithmParamType.ML_INT_PARAM);
    return result;
  }

}
