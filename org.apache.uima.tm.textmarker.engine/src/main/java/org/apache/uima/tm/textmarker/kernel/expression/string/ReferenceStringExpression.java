package org.apache.uima.tm.textmarker.kernel.expression.string;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;

public class ReferenceStringExpression extends LiteralStringExpression {

  private final String var;

  public ReferenceStringExpression(String var) {
    super();
    this.var = var;
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    String variableValue = parent.getEnvironment().getVariableValue(getVar(), String.class);
    return variableValue;
  }

  public String getVar() {
    return var;
  }

}
