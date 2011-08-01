package org.apache.uima.tm.textmarker.kernel.expression.string;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;

public abstract class StringExpression extends TextMarkerExpression {

  public abstract String getStringValue(TextMarkerStatement parent);

}
