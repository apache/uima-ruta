package org.apache.uima.tm.textmarker.kernel.expression.bool;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;

public class ReferenceBooleanExpression extends BooleanExpression {

  private final String var;

  public ReferenceBooleanExpression(String var) {
    super();
    this.var = var;
  }

  @Override
  public boolean getBooleanValue(TextMarkerStatement parent) {
    Boolean variableValue = parent.getEnvironment().getVariableValue(var, Boolean.class);
    if (variableValue == null) {
      return false;
    }
    return variableValue;
  }

  public String getVar() {
    return var;
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    return getBooleanValue(parent) ? "true" : "false";
  }

}
