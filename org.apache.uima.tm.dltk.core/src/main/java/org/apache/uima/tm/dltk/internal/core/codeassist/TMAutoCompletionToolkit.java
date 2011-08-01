package org.apache.uima.tm.dltk.internal.core.codeassist;

public class TMAutoCompletionToolkit {

  private int wordOffset;

  private int currentOffset;

  private int relativeReplacementStart;

  private String wordPrefix;

  public TMAutoCompletionToolkit(String text, int offset) {
    this.currentOffset = offset;
    wordOffset = getWordOffset(text, offset) + 1;
    wordPrefix = text.substring(wordOffset, offset);
    relativeReplacementStart = offset - wordOffset;

  }

  private int getWordOffset(String text, int offset) {
    for (int i = offset - 1; i > 0; i--) {
      switch (text.charAt(i)) {
        case ' ': {
          return i;
        }
        case '\n': {
          return i;
        }
        case '\r': {
          return i;
        }
        case '\t': {
          return i;
        }
        case '[': {
          return i;
        }
        case ']': {
          return i;
        }
        case '(': {
          return i;
        }
        case ')': {
          return i;
        }
        case '{': {
          return i;
        }
        case '}': {
          return i;
        }
        case ',': {
          return i;
        }
        case ';': {
          return i;
        }
      }
    }
    return -1;
  }

  public int getWordOffset() {
    return this.wordOffset;
  }

  public int getCurrentOffset() {
    return this.currentOffset;
  }

  public int getRelativeReplacementStart() {
    return relativeReplacementStart;
  }

  public String getWordPrefix() {
    return wordPrefix;
  }

}