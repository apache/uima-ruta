package org.apache.uima.tm.dltk.internal.core.parsers;

import org.antlr.runtime.Token;
import org.eclipse.dltk.ast.DLTKToken;

public class DLTKTokenConverter {
  String content;

  CodeModel model;

  private static class CodeModel {
    private String[] codeLines;

    private int[] codeLineLengths;

    public CodeModel(String code) {
      this.codeLines = code.split("\n");
      int count = this.codeLines.length;

      this.codeLineLengths = new int[count];

      int sum = 0;
      for (int i = 0; i < count; ++i) {
        this.codeLineLengths[i] = sum;
        sum += this.codeLines[i].length() + 1;
      }
    }

    public int[] getBounds(int lineNumber) {
      String codeLine = codeLines[lineNumber];

      int start = codeLineLengths[lineNumber];
      int end = start + codeLine.length();

      return new int[] { start, end };
    }
  }

  public DLTKTokenConverter(char[] content0) {
    this.content = new String(content0);
    this.model = new CodeModel(content);
  }

  /**
   * @param token
   * @return
   */
  @Deprecated
  public DLTKToken convert(Token token) {
    if (token == null) {
      DLTKToken t = new DLTKToken(0, "");
      t.setLine(1);
      return t;
    }
    int line = token.getLine() - 1;
    if (line < 0) {
      line = 0;
    }
    int[] bounds = this.model.getBounds(line);
    DLTKToken t = new DLTKToken(token.getType(), token.getText());
    int offset = token.getCharPositionInLine();
    if (offset < 0) {
      offset = 0;
    }
    t.setColumn(bounds[0] + offset);
    t.setLine(line);
    return t;
  }

  public int convert(int line, int offset) {
    if (line > 0) {
      int[] bounds = this.model.getBounds(line - 1);
      return bounds[0] + offset;
    }
    return offset;
  }

  @Deprecated
  public int length() {
    return this.content.length();
  }
}
