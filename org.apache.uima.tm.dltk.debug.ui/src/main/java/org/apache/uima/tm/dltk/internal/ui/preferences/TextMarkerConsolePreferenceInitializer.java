package org.apache.uima.tm.dltk.internal.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import org.apache.uima.tm.dltk.textmarker.console.TextMarkerConsoleConstants;
import org.apache.uima.tm.dltk.internal.debug.ui.TextMarkerDebugUIPlugin;

public class TextMarkerConsolePreferenceInitializer extends AbstractPreferenceInitializer {

  public TextMarkerConsolePreferenceInitializer() {
  }

  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore store = TextMarkerDebugUIPlugin.getDefault().getPreferenceStore();
    store.setDefault(TextMarkerConsoleConstants.PREF_NEW_PROMPT,
            TextMarkerConsoleConstants.DEFAULT_NEW_PROMPT);
    store.setDefault(TextMarkerConsoleConstants.PREF_CONTINUE_PROMPT,
            TextMarkerConsoleConstants.DEFAULT_CONTINUE_PROMPT);
  }

}
