package org.apache.uima.tm.textmarker.kernel.expression.number;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;

public class ReferenceIntegerExpression extends NumberExpression {
  private final String var;

  public ReferenceIntegerExpression(String var) {
    super();
    this.var = var;
  }

  @Override
  public double getDoubleValue(TextMarkerStatement parent) {
    Double variableValue = parent.getEnvironment().getVariableValue(getVar(), Double.class);
    return variableValue;
  }

  @Override
  public int getIntegerValue(TextMarkerStatement parent) {
    Integer variableValue = parent.getEnvironment().getVariableValue(getVar(), Integer.class);
    return variableValue;
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    Class<?> variableType = parent.getEnvironment().getVariableType(getVar());
    if (variableType.equals(Integer.class)) {
      return "" + getIntegerValue(parent);
    } else {
      return "" + getDoubleValue(parent);
    }
  }

  public String getVar() {
    return var;
  }

}
