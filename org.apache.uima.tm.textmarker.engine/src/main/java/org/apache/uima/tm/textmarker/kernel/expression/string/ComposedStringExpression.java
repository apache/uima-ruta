package org.apache.uima.tm.textmarker.kernel.expression.string;

import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;


public class ComposedStringExpression extends LiteralStringExpression {

  private final List<StringExpression> epxressions;

  public ComposedStringExpression(List<StringExpression> expressions) {
    super();
    this.epxressions = expressions;
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    StringBuilder result = new StringBuilder();
    for (StringExpression each : getExpressions()) {
      result.append(each.getStringValue(parent));
    }
    return result.toString();
  }

  public List<StringExpression> getExpressions() {
    return epxressions;
  }

}
