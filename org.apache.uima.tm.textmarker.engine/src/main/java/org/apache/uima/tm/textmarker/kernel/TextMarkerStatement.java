package org.apache.uima.tm.textmarker.kernel;

import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;

public abstract class TextMarkerStatement extends TextMarkerElement {

  private final TextMarkerBlock parent;

  public TextMarkerStatement(TextMarkerBlock parent) {
    super();
    this.parent = parent;
  }

  public TextMarkerBlock getParent() {
    return parent;
  }

  public abstract ScriptApply apply(TextMarkerStream stream, InferenceCrowd crowd);

  public abstract TextMarkerEnvironment getEnvironment();

}
