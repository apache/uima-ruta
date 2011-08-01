package org.apache.uima.tm.dltk.internal.ui.preferences;

import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


public class TextMarkerEditorAssistancePreferencePage extends
        AbstractConfigurationBlockPreferencePage {

  /*
   * @see org.eclipse.ui.internal.editors.text.AbstractConfigureationBlockPreferencePage#getHelpId()
   */
  @Override
  protected String getHelpId() {

    return null;
  }

  /*
   * @see
   * org.eclipse.ui.internal.editors.text.AbstractConfigurationBlockPreferencePage#setDescription()
   */
  @Override
  protected void setDescription() {
    String description = "&Code Assistance";
    setDescription(description);
  }

  /*
   * @seeorg.org.eclipse.ui.internal.editors.text.AbstractConfigurationBlockPreferencePage#
   * setPreferenceStore()
   */
  @Override
  protected void setPreferenceStore() {
    setPreferenceStore(TextMarkerUI.getDefault().getPreferenceStore());
  }

  @Override
  protected Label createDescriptionLabel(Composite parent) {
    return null; // no description for new look.
  }

  /*
   * @seeorg.eclipse.ui.internal.editors.text.AbstractConfigureationBlockPreferencePage#
   * createConfigurationBlock(org.eclipse.ui.internal.editors.text.OverlayPreferenceStore)
   */
  @Override
  protected IPreferenceConfigurationBlock createConfigurationBlock(
          OverlayPreferenceStore overlayPreferenceStore) {
    return new TextMarkerContentAssistConfigurationBlock(this, overlayPreferenceStore);
  }
}
