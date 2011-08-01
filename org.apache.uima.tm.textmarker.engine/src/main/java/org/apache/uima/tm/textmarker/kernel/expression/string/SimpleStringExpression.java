package org.apache.uima.tm.textmarker.kernel.expression.string;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;

public class SimpleStringExpression extends LiteralStringExpression {

  private final String value;

  public static String stripEscapes(String str) {
    String result = str.replaceAll("\\\\\\\\", "\\\\");
    return result.replaceAll("\\\\\\\"", "\\\"");
  }

  public SimpleStringExpression(String value) {
    super();
    if (value.startsWith("\"") && value.endsWith("\"")) {
      value = value.substring(1, value.length() - 1);
    }
    this.value = stripEscapes(value); // hotfix for the escaping problem
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    return getValue();
  }

  public String getValue() {
    return value;
  }

}
