package org.apache.uima.tm.textmarker.kernel.expression.number;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;

public class SimpleNumberExpression extends NumberExpression {

  private final Number number;

  public SimpleNumberExpression(Number number) {
    super();
    this.number = number;
  }

  @Override
  public double getDoubleValue(TextMarkerStatement parent) {
    return number.doubleValue();
  }

  @Override
  public int getIntegerValue(TextMarkerStatement parent) {
    return number.intValue();
  }

  public Number getNumber() {
    return number;
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    boolean floating = number.intValue() != number.doubleValue();
    if (floating) {
      return "" + getDoubleValue(parent);
    } else {
      return "" + getIntegerValue(parent);
    }
  }

}
