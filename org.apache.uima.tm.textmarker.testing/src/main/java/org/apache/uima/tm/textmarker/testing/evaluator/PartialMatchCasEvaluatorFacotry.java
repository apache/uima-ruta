package org.apache.uima.tm.textmarker.testing.evaluator;

public class PartialMatchCasEvaluatorFacotry implements ICasEvaluatorFactory {

  @Override
  public ICasEvaluator createEvaluator() {
    
    return new PartialMatchCasEvaluator();
  }

  @Override
  public String getDescription() {
    return "Compares the offsets of the given annotations.\n" +
    "Allows partial matching if the begin or end\n" + 
    "character sequenze of the annotations match.";
    
  }

}
