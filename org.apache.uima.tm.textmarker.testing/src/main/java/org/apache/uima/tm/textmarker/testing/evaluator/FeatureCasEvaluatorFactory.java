package org.apache.uima.tm.textmarker.testing.evaluator;

public class FeatureCasEvaluatorFactory implements ICasEvaluatorFactory {

  public ICasEvaluator createEvaluator() {
    return new FeatureCasEvaluator();
  }

  public String getDescription() {
    return "Complex feature structures are compared. A feature structure is a true positive if all annotation and string feature values are correct.";
  }

}
