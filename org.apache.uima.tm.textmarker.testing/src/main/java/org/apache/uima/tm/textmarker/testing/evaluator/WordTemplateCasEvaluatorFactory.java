package org.apache.uima.tm.textmarker.testing.evaluator;

public class WordTemplateCasEvaluatorFactory implements ICasEvaluatorFactory {

  public ICasEvaluator createEvaluator() {
    return new WordTemplateCasEvaluator();
  }

  public String getDescription() {
    return "Complex feature structures that provide at least one annotation as a feature value are compared. A template feature structure is a true positive if all all feature values are correct. Here, the word level evaluator is applied for the comparisionof the feature values.";
  }

}
