package org.apache.uima.tm.dltk.internal.ui.search;

import org.apache.uima.tm.dltk.core.TextMarkerLanguageToolkit;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.ui.search.ScriptSearchPage;


public class TextMarkerSearchPage extends ScriptSearchPage {
  @Override
  protected IDLTKLanguageToolkit getLanguageToolkit() {
    return TextMarkerLanguageToolkit.getDefault();
  }
}
