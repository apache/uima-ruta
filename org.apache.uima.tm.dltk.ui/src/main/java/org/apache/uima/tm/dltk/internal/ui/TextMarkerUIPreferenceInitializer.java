package org.apache.uima.tm.dltk.internal.ui;

import org.apache.uima.tm.dltk.ui.TextMarkerPreferenceConstants;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.dltk.compiler.util.Util;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.editors.text.EditorsUI;


public class TextMarkerUIPreferenceInitializer extends AbstractPreferenceInitializer {
  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences
   * ()
   */
  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore store = TextMarkerUI.getDefault().getPreferenceStore();

    EditorsUI.useAnnotationsPreferencePage(store);
    EditorsUI.useQuickDiffPreferencePage(store);
    TextMarkerPreferenceConstants.initializeDefaultValues(store);
    store.setDefault(TextMarkerPreferenceConstants.FORMATTER_ID, Util.EMPTY_STRING);
  }

}
