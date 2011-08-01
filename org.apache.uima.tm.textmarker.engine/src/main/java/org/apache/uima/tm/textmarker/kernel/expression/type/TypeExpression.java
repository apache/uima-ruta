package org.apache.uima.tm.textmarker.kernel.expression.type;

import org.apache.uima.cas.Type;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;


public abstract class TypeExpression extends StringExpression {

  public abstract Type getType(TextMarkerStatement parent);

}
