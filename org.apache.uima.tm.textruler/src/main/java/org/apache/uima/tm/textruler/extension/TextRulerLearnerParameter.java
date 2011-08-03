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

/**
 * With your TextRulerLearnerFactory-class you can specify some parameters your algorithm has (e.g.
 * a window size for extracting features around a token or such). This is done with an array of
 * 
 * TextRulerLearnerParameter
 * 
 * objects. In the TextRuler UI in Eclipse some widgets get automatically created for those
 * parameters if the algorithm is selected in the algorithm list.
 */
public class TextRulerLearnerParameter {

  public enum MLAlgorithmParamType {
    ML_STRING_PARAM, ML_BOOL_PARAM, ML_INT_PARAM, ML_FLOAT_PARAM, ML_SELECT_PARAM
  };

  public static class MLParameterSelectValue {
    public String id;

    public String name;

    public MLParameterSelectValue(String id, String name) {
      this.id = id;
      this.name = name;
    }
  }

  public String id;

  public String name;

  public MLAlgorithmParamType type;

  public MLParameterSelectValue[] selectValues;

  public TextRulerLearnerParameter(String id, String name, MLAlgorithmParamType type) {
    this.id = id;
    this.name = name;
    this.type = type;
    selectValues = null;
  }

  public TextRulerLearnerParameter(String id, String name, MLAlgorithmParamType type,
          MLParameterSelectValue[] values) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.selectValues = values;
  }

}
