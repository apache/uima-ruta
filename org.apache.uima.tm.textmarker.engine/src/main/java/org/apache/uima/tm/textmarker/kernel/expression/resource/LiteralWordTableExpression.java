package org.apache.uima.tm.textmarker.kernel.expression.resource;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.resource.TextMarkerTable;

public class LiteralWordTableExpression extends WordTableExpression {

  private final String text;

  public LiteralWordTableExpression(String text) {
    super();
    if (text.startsWith("\'") && text.endsWith("\'")) {
      text = text.substring(1, text.length() - 1);
    }
    this.text = stripEscapes(text); // hotfix for the escaping problem
  }

  public static String stripEscapes(String str) {
    String result = str.replaceAll("\\\\\\\\", "\\\\");
    return result.replaceAll("\\\\\\\"", "\\\"");
  }

  @Override
  public TextMarkerTable getTable(TextMarkerStatement element) {
    TextMarkerTable table = element.getEnvironment().getWordTable(getText());
    return table;
  }

  public String getText() {
    return text;
  }

}
