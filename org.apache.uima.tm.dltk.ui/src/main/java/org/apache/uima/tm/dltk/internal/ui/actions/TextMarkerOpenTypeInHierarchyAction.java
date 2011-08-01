package org.apache.uima.tm.dltk.internal.ui.actions;

import org.apache.uima.tm.dltk.internal.ui.TextMarkerUILanguageToolkit;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.actions.OpenTypeInHierarchyAction;


public class TextMarkerOpenTypeInHierarchyAction extends OpenTypeInHierarchyAction {
  @Override
  protected IDLTKUILanguageToolkit getLanguageToolkit() {
    return TextMarkerUILanguageToolkit.getInstance();
  }
}
