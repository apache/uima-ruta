package org.apache.uima.tm.textmarker.kernel.expression.number;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;

public class ReferenceDoubleExpression extends NumberExpression {

  private final String var;

  public ReferenceDoubleExpression(String var) {
    super();
    this.var = var;
  }

  @Override
  public double getDoubleValue(TextMarkerStatement parent) {
    Object value = parent.getEnvironment().getVariableValue(getVar());
    double variableValue = 0;
    if (value instanceof Number) {
      variableValue = ((Number) value).doubleValue();
    }
    return variableValue;
  }

  @Override
  public int getIntegerValue(TextMarkerStatement parent) {
    Object value = parent.getEnvironment().getVariableValue(getVar());
    int variableValue = 0;
    if (value instanceof Number) {
      variableValue = ((Number) value).intValue();
    }
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
