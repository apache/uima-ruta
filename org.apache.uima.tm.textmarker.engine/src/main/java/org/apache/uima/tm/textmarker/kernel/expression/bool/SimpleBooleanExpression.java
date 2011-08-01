package org.apache.uima.tm.textmarker.kernel.expression.bool;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;

public class SimpleBooleanExpression extends BooleanExpression {

  private final boolean value;

  public SimpleBooleanExpression(boolean value) {
    super();
    this.value = value;
  }

  @Override
  public boolean getBooleanValue(TextMarkerStatement parent) {
    return getPrimitiveValue();
  }

  public boolean getPrimitiveValue() {
    return value;
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    return getBooleanValue(parent) ? "true" : "false";
  }

}
