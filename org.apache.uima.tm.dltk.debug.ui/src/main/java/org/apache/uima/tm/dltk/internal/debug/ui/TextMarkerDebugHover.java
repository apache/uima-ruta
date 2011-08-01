package org.apache.uima.tm.dltk.internal.debug.ui;

import org.eclipse.dltk.debug.ui.ScriptDebugModelPresentation;
import org.eclipse.dltk.internal.debug.ui.ScriptDebugHover;
import org.eclipse.jface.preference.IPreferenceStore;

public class TextMarkerDebugHover extends ScriptDebugHover {

  @Override
  protected ScriptDebugModelPresentation getModelPresentation() {
    return new TextMarkerDebugModelPresentation();
  }

  public void setPreferenceStore(IPreferenceStore store) {

  }

}
