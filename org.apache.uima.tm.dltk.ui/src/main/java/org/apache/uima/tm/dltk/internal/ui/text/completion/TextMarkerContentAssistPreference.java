package org.apache.uima.tm.dltk.internal.ui.text.completion;

import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;


public class TextMarkerContentAssistPreference extends ContentAssistPreference {
  static TextMarkerContentAssistPreference sDefault;

  @Override
  protected ScriptTextTools getTextTools() {
    return TextMarkerUI.getDefault().getTextTools();
  }

  public static ContentAssistPreference getDefault() {
    if (sDefault == null) {
      sDefault = new TextMarkerContentAssistPreference();
    }
    return sDefault;
  }
}
