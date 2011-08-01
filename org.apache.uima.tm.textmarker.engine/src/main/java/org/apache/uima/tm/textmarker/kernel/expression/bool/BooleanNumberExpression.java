package org.apache.uima.tm.textmarker.kernel.expression.bool;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;

public class BooleanNumberExpression extends BooleanExpression {

  private final NumberExpression e1;

  private final String op;

  private final NumberExpression e2;

  public BooleanNumberExpression(NumberExpression e1, String op, NumberExpression e2) {
    super();
    this.e1 = e1;
    this.op = op;
    this.e2 = e2;
  }

  @Override
  public boolean getBooleanValue(TextMarkerStatement parent) {
    double doubleValue1 = getFristExpression().getDoubleValue(parent);
    double doubleValue2 = getSecondExpression().getDoubleValue(parent);
    return eval(doubleValue1, getOperator(), doubleValue2);
  }

  private boolean eval(double t1, String op, double t2) {
    if ("==".equals(op)) {
      return t1 == t2;
    } else if ("!=".equals(op)) {
      return t1 != t2;
    } else if ("<".equals(op)) {
      return t1 < t2;
    } else if ("<=".equals(op)) {
      return t1 <= t2;
    } else if (">".equals(op)) {
      return t1 > t2;
    } else if (">=".equals(op)) {
      return t1 >= t2;
    }
    return false;
  }

  public NumberExpression getFristExpression() {
    return e1;
  }

  public String getOperator() {
    return op;
  }

  public NumberExpression getSecondExpression() {
    return e2;
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    return e1.getStringValue(parent) + " " + op + " " + e2.getStringValue(parent);
  }

}
