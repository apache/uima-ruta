package org.apache.uima.tm.textmarker.kernel;

public abstract class ScriptApply {

  public static int count = 0;

  private final TextMarkerStatement tme;

  public ScriptApply(TextMarkerStatement tme) {
    super();
    this.tme = tme;
  }

  public TextMarkerStatement getElement() {
    return tme;
  }

}
