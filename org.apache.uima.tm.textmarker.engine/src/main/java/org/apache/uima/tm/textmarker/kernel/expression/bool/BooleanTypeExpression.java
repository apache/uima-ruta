package org.apache.uima.tm.textmarker.kernel.expression.bool;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;

public class BooleanTypeExpression extends BooleanExpression {

  private final TypeExpression e1;

  private final String op;

  private final TypeExpression e2;

  public BooleanTypeExpression(TypeExpression e1, String op, TypeExpression e2) {
    super();
    this.e1 = e1;
    this.op = op;
    this.e2 = e2;
  }

  @Override
  public boolean getBooleanValue(TextMarkerStatement parent) {
    String first = getFristExpression().getType(parent).getName();
    String second = getSecondExpression().getType(parent).getName();
    return eval(first, getOperator(), second);
  }

  private boolean eval(String t1, String op, String t2) {
    if ("==".equals(op)) {
      return t1.equals(t2);
    } else if ("!=".equals(op)) {
      return !t1.equals(t2);
    }
    return false;
  }

  public TypeExpression getFristExpression() {
    return e1;
  }

  public String getOperator() {
    return op;
  }

  public TypeExpression getSecondExpression() {
    return e2;
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    return e1.getStringValue(parent) + " " + op + " " + e2.getStringValue(parent);
  }

}
