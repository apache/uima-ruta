package org.apache.uima.tm.dltk.internal.ui.text;

import org.eclipse.jface.text.rules.IWordDetector;

class TextMarkerDecoratorDetector implements IWordDetector {

  public boolean isWordStart(char c) {
    return c == '@';
  }

  public boolean isWordPart(char c) {
    return c != '\n' && c != '\r' && c != '(';
  }

}
