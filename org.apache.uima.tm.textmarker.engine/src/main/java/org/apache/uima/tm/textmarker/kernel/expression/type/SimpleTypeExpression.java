package org.apache.uima.tm.textmarker.kernel.expression.type;

import org.apache.uima.cas.Type;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;


public class SimpleTypeExpression extends TypeExpression {

  private final Type type;

  public SimpleTypeExpression(Type type) {
    super();
    this.type = type;
  }

  @Override
  public Type getType(TextMarkerStatement parent) {
    return type;
  }

  @Override
  public String toString() {
    return type.getShortName();
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    return type.getName();
  }

}
