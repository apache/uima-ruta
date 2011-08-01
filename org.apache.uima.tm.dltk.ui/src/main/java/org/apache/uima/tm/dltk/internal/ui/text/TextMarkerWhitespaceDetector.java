package org.apache.uima.tm.dltk.internal.ui.text;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class TextMarkerWhitespaceDetector implements IWhitespaceDetector {

  /*
   * (non-Javadoc) Method declared on IWhitespaceDetector
   */
  public boolean isWhitespace(char character) {
    return Character.isWhitespace(character);
  }
}
