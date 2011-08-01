package org.apache.uima.tm.dltk.internal.ui.actions;

import org.apache.uima.tm.dltk.internal.ui.TextMarkerUILanguageToolkit;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.actions.OpenTypeAction;


public class TextMarkerOpenTypeAction extends OpenTypeAction {
  @Override
  protected IDLTKUILanguageToolkit getUILanguageToolkit() {
    return TextMarkerUILanguageToolkit.getInstance();
  }
}
