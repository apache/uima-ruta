package org.apache.uima.tm.textmarker.kernel.expression.bool;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;

public class SimpleBooleanFunction extends BooleanExpression {

  private String op;

  private BooleanExpression e1;

  private BooleanExpression e2;

  public SimpleBooleanFunction(String text, BooleanExpression e1, BooleanExpression e2) {
    super();
    this.op = text;
    this.e1 = e1;
    this.e2 = e2;
  }

  @Override
  public boolean getBooleanValue(TextMarkerStatement parent) {
    boolean b1 = e1.getBooleanValue(parent);
    boolean b2 = e2.getBooleanValue(parent);
    if ("XOR".equals(op)) {
      return (b1 || b2) && !(b1 && b2);
    } else if ("==".equals(op)) {
      return b1 == b2;
    } else if ("!=".equals(op)) {
      return b1 != b2;
    }
    return false;
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    return e1.getStringValue(parent) + " " + op + " " + e2.getStringValue(parent);
  }

}
