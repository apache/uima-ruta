package org.apache.uima.tm.textmarker.kernel.expression.number;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;

public class NegativeNumberExpression extends NumberExpression {

  private final NumberExpression ne;

  public NegativeNumberExpression(NumberExpression simpleNumberExpression) {
    super();
    this.ne = simpleNumberExpression;
  }

  @Override
  public double getDoubleValue(TextMarkerStatement parent) {
    return -ne.getDoubleValue(parent);
  }

  @Override
  public int getIntegerValue(TextMarkerStatement parent) {
    return -ne.getIntegerValue(parent);
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    return "-" + ne.getStringValue(parent);
  }

  public NumberExpression getExpression() {
    return ne;
  }

}
