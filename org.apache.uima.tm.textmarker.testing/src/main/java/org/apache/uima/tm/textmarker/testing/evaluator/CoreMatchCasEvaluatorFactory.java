package org.apache.uima.tm.textmarker.testing.evaluator;

public class CoreMatchCasEvaluatorFactory implements ICasEvaluatorFactory {

  @Override
  public ICasEvaluator createEvaluator() {
    
    return new CoreMatchCasEvaluator();
  }

  @Override
  public String getDescription() {
    return "Compares the offsets of two annotations, allowes partial matching, defines core terms and checks if at least these match (partially) with the other annotation";
    
  }

}
