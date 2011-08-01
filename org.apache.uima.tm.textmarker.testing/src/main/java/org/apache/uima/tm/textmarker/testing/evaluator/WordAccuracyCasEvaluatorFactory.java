package org.apache.uima.tm.textmarker.testing.evaluator;

public class WordAccuracyCasEvaluatorFactory implements ICasEvaluatorFactory {

  public ICasEvaluator createEvaluator() {
    return new WordAccuracyCasEvaluator();
  }

  public String getDescription() {
    return "Compare the 'labels' of all words/numbers in an annotation, whereas the label equals the type of the annoation. This has the consequence, for example, that each word or number that is not part of the annotation is counted as a single false negative.";
  }

}
