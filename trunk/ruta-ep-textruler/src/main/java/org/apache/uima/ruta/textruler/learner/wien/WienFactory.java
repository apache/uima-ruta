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

package org.apache.uima.ruta.textruler.learner.wien;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.uima.ruta.textruler.extension.TextRulerLearner;
import org.apache.uima.ruta.textruler.extension.TextRulerLearnerDelegate;
import org.apache.uima.ruta.textruler.extension.TextRulerLearnerFactory;
import org.apache.uima.ruta.textruler.extension.TextRulerLearnerParameter;

public class WienFactory implements TextRulerLearnerFactory {

  public TextRulerLearner createAlgorithm(String inputFolderPath, String additionalFolderPath,
          String preprocessorTMfile, String tempFolderPath, String[] fullSlotTypeNames,
          Set<String> filterSet, boolean skip, TextRulerLearnerDelegate delegate) {
    return new Wien(inputFolderPath, preprocessorTMfile, tempFolderPath, fullSlotTypeNames,
            filterSet, skip,delegate);
  }

  public Map<String, Object> getAlgorithmParameterStandardValues() {
    return new HashMap<String, Object>();
  }

  public TextRulerLearnerParameter[] getAlgorithmParameters() {
    TextRulerLearnerParameter[] result = new TextRulerLearnerParameter[0];
    return result;
  }

}
