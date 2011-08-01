package org.apache.uima.tm.textmarker.testing.evaluator;

public interface ICasEvaluatorFactory {

  ICasEvaluator createEvaluator();

  String getDescription();

}
