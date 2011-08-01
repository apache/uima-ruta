package org.apache.uima.tm.textmarker.kernel.expression.resource;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.resource.TextMarkerWordList;

public class LiteralWordListExpression extends WordListExpression {

  private final String text;

  public LiteralWordListExpression(String text) {
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
  public TextMarkerWordList getList(TextMarkerStatement element) {
    TextMarkerWordList list = element.getEnvironment().getWordList(getText());
    return list;
  }

  public String getText() {
    return text;
  }

}
