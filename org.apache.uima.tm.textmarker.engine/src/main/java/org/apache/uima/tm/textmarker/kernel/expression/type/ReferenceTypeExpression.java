package org.apache.uima.tm.textmarker.kernel.expression.type;

import org.apache.uima.cas.Type;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;


public class ReferenceTypeExpression extends TypeExpression {

  private final String var;

  public ReferenceTypeExpression(String varString) {
    super();
    this.var = varString;
  }

  @Override
  public String toString() {
    return getVar();
  }

  public String getVar() {
    return var;
  }

  @Override
  public Type getType(TextMarkerStatement parent) {
    return parent.getEnvironment().getVariableValue(var, Type.class);
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    Type type = getType(parent);
    return type != null ? type.getName() : "null";
  }

}
