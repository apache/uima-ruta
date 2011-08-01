package org.apache.uima.tm.dltk.internal.ui.text.completion;

import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposal;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Image;


public class TextMarkerScriptCompletionProposal extends ScriptCompletionProposal {

  public TextMarkerScriptCompletionProposal(String replacementString, int replacementOffset,
          int replacementLength, Image image, String displayString, int relevance) {
    super(replacementString, replacementOffset, replacementLength, image, displayString, relevance);
  }

  public TextMarkerScriptCompletionProposal(String replacementString, int replacementOffset,
          int replacementLength, Image image, String displayString, int relevance, boolean isInDoc) {
    super(replacementString, replacementOffset, replacementLength, image, displayString, relevance,
            isInDoc);
  }

  @Override
  protected boolean isSmartTrigger(char trigger) {
    if (trigger == '.') {
      return true;
    }
    return false;
  }

  @Override
  protected boolean isCamelCaseMatching() {
    return true;
  }

  @Override
  protected boolean insertCompletion() {
    IPreferenceStore preference = TextMarkerUI.getDefault().getPreferenceStore();
    return preference.getBoolean(PreferenceConstants.CODEASSIST_INSERT_COMPLETION);
  }
}
