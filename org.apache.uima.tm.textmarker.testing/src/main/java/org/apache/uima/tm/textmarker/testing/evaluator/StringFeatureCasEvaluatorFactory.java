package org.apache.uima.tm.textmarker.testing.evaluator;

public class StringFeatureCasEvaluatorFactory implements ICasEvaluatorFactory {

  public ICasEvaluator createEvaluator() {
    return new StringFeatureCasEvaluator();
  }

  public String getDescription() {
    return "Annotations with at least one feature of the type string are compared. An annotation is a true positive if the offsets are correct and the string-based feature values are identical.";
  }

}
