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

package org.apache.uima.textmarker.testing.ui.views.evalDataTable;

public class TypeEvalData implements Comparable{
  
  private String scriptName;
  private int truePositives;
  private int falsePositives;
  private int falseNegatives;
  private double precision;
  private double recall;
  private double FOne;
  
  
  public TypeEvalData (String scriptName, int truePositives, int falsePositives, int falseNegatives) {
    this.scriptName = scriptName;
    this.truePositives = truePositives;
    this.falsePositives = falsePositives;
    this.falseNegatives = falseNegatives;
  }
  
  
  
  public String getTypeName() {
    return scriptName;
  }
  
  public int getTruePositives() {
    return truePositives;
  }
  
  public int getFalsePositives() {
    return falsePositives;
  }
  
  public int getFalseNegatives() {
    return falseNegatives;
  }
  
  public void setFalseNegatives(int falseNegatives) {
    this.falseNegatives = falseNegatives;
  }
  
  public void setFalsePositives(int falsePositives) {
    this.falsePositives = falsePositives;
  }
  
  public void setScriptName(String scriptName) {
    this.scriptName = scriptName;
  }
  
  public void setTruePositives(int truePositives) {
    this.truePositives = truePositives;
  }

  public double getPrecision() {
    return precision;
  }
  public double getRecall() {
    return recall;
  }
  
  public double getFOne() {
    return FOne;
  }

  public int compareTo(Object o) {
    if (o instanceof TypeEvalData) {
      String name = ((TypeEvalData) o).getTypeName();
      if (scriptName.equals(name)){
        return 1;
      }
    }
    return 0;
  }
  
  public void calcFOne () {
    
    double tp = truePositives;
    double fp = falsePositives;
    double fn = falseNegatives;
    
    
    if (truePositives == 0) {
      precision = 0;
      recall = 0;
      FOne = 0;
    } else {
    precision = tp / (tp + fp);
    recall = tp / (tp + fn);
    FOne = 2 * (precision * recall) / (precision + recall);
    
    precision = precision * 1000;
    precision = Math.round(precision);
    precision = precision / 1000;
    
    recall = recall * 1000;
    recall = Math.round(recall);
    recall = recall / 1000;
    
    FOne = FOne * 1000;
    FOne = Math.round(FOne);
    FOne = FOne / 1000;
    
    }
  }
}
