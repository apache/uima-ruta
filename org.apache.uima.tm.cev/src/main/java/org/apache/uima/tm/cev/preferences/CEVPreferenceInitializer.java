package org.apache.uima.tm.cev.preferences;

import org.apache.uima.tm.cev.CEVPlugin;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;


/**
 * Setzt die Default-Werte bei den Preferences
 * 
 * @author Marco Nehmeier
 */
public class CEVPreferenceInitializer extends AbstractPreferenceInitializer {

  /*
   * (non-Javadoc)
   * 
   * @seeorg.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
   * initializeDefaultPreferences()
   */
  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore store = CEVPlugin.getDefault().getPreferenceStore();

    // Textrepresentation auf nur Text (HTML-Tags filtern)
    store.setDefault(CEVPreferenceConstants.P_ANNOTATION_REPR,
            CEVPreferenceConstants.P_ANNOTATION_REPR_TEXT);

    // Baumordnung nach Typen
    store.setDefault(CEVPreferenceConstants.P_ANNOTATION_BROWSER_TREE_ORDER,
            CEVPreferenceConstants.P_ANNOTATION_BROWSER_TREE_ORDER_TYPE);

    store.setDefault(CEVPreferenceConstants.P_ANNOTATION_EDITOR_TRIM, true);
    store.setDefault(CEVPreferenceConstants.P_AUTO_REFRESH, true);
    store.setDefault(CEVPreferenceConstants.P_SELECT_ONLY, false);
  }

}
