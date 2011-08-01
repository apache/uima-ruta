package org.apache.uima.tm.textmarker.testing.ui.views.evalDataTable;

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

  @Override
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
