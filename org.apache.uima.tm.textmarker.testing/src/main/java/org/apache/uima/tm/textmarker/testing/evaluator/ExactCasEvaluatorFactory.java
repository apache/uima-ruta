package org.apache.uima.tm.textmarker.testing.evaluator;

public class ExactCasEvaluatorFactory implements ICasEvaluatorFactory {

  public ICasEvaluator createEvaluator() {
    return new ExactCasEvaluator();
  }

  public String getDescription() {
    return "Compare the offsets of the given annotations.";
  }

}
