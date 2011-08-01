package org.apache.uima.tm.textmarker.kernel.expression.bool;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;

public abstract class BooleanExpression extends StringExpression {

  public abstract boolean getBooleanValue(TextMarkerStatement parent);

}
