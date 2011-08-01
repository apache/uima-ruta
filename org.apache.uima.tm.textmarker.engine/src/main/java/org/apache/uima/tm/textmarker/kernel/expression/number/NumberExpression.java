package org.apache.uima.tm.textmarker.kernel.expression.number;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;

public abstract class NumberExpression extends StringExpression {

  public abstract int getIntegerValue(TextMarkerStatement parent);

  public abstract double getDoubleValue(TextMarkerStatement parent);

}
