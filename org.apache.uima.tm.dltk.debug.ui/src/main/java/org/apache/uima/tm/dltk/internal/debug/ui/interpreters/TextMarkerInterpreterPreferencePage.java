package org.apache.uima.tm.dltk.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.ScriptInterpreterPreferencePage;

public class TextMarkerInterpreterPreferencePage extends ScriptInterpreterPreferencePage {

  @Override
  public InterpretersBlock createInterpretersBlock() {
    return new TextMarkerInterpretersBlock();
  }
}
