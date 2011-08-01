package org.apache.uima.tm.dltk.internal.ui.preferences;

import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;


public class TextMarkerGlobalPreferencePage extends AbstractConfigurationBlockPreferencePage {

  @Override
  protected IPreferenceConfigurationBlock createConfigurationBlock(
          OverlayPreferenceStore overlayPreferenceStore) {
    return new TextMarkerGlobalConfigurationBlock(overlayPreferenceStore, this);
  }

  @Override
  protected String getHelpId() {
    return null;
  }

  @Override
  protected void setDescription() {
    String description = TextMarkerPreferencesMessages.TextMarkerGlobalPreferencePage_description;
    setDescription(description);
  }

  @Override
  protected void setPreferenceStore() {
    setPreferenceStore(TextMarkerUI.getDefault().getPreferenceStore());
  }

}
