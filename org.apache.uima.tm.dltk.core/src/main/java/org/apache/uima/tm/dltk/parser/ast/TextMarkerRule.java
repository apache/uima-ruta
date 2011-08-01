package org.apache.uima.tm.dltk.parser.ast;

import java.util.List;

public class TextMarkerRule extends TextMarkerStatement {

  public TextMarkerRule(List expressions) {
    super(expressions);
  }

  public String toString() {
    return this.getClass().getSimpleName() + " : " + super.toString();
  }
}
