package org.apache.uima.tm.dltk.core;

public class CodeModel {
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
    String codeLine = this.codeLines[lineNumber];

    int start = this.codeLineLengths[lineNumber];
    int end = start + codeLine.length();

    return new int[] { start, end };
  }

  public int getLineNumber(int start, int end) {
    int len = this.codeLines.length;
    for (int i = 0; i < len; ++i) {
      String codeLine = this.codeLines[i];

      int s = this.codeLineLengths[i];
      int e = start + codeLine.length();

      if (start <= s && end <= e) {
        return i + 1;
      }
    }
    return this.codeLines.length;
  }
}
