package org.apache.uima.tm.textmarker.testing.evaluator;

import java.util.Collection;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CASRuntimeException;

public interface ICasEvaluator {

  public static final String FALSE_NEGATIVE = "org.apache.uima.tm.textmarker.kernel.type.FalseNegative";

  public static final String FALSE_POSITIVE = "org.apache.uima.tm.textmarker.kernel.type.FalsePositive";

  public static final String TRUE_POSITIVE = "org.apache.uima.tm.textmarker.kernel.type.TruePositive";

  public static final String ORIGINAL = "original";

  CAS evaluate(CAS test, CAS run, Collection<String> excludedTypes) throws CASRuntimeException,
          CASException;

}
