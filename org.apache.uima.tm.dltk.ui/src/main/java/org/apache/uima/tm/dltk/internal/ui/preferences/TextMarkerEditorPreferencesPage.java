package org.apache.uima.tm.dltk.internal.ui.preferences;

import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.EditorConfigurationBlock;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


public class TextMarkerEditorPreferencesPage extends AbstractConfigurationBlockPreferencePage {

  /*
   * @see org.eclipse.ui.internal.editors.text.AbstractConfigureationBlockPreferencePage#getHelpId()
   */
  @Override
  protected String getHelpId() {
    return "";
  }

  /*
   * @see
   * org.eclipse.ui.internal.editors.text.AbstractConfigurationBlockPreferencePage#setDescription()
   */
  @Override
  protected void setDescription() {
    String description = TextMarkerPreferencesMessages.TextMarkerEditorPreferencePage_general;
    setDescription(description);
  }

  @Override
  protected Label createDescriptionLabel(Composite parent) {
    return null;
  }

  /*
   * @seeorg.org.eclipse.ui.internal.editors.text.AbstractConfigurationBlockPreferencePage#
   * setPreferenceStore()
   */
  @Override
  protected void setPreferenceStore() {
    setPreferenceStore(TextMarkerUI.getDefault().getPreferenceStore());
  }

  /*
   * @seeorg.eclipse.ui.internal.editors.text.AbstractConfigureationBlockPreferencePage#
   * createConfigurationBlock(org.eclipse.ui.internal.editors.text.OverlayPreferenceStore)
   */
  @Override
  protected IPreferenceConfigurationBlock createConfigurationBlock(
          OverlayPreferenceStore overlayPreferenceStore) {
    return new EditorConfigurationBlock(this, overlayPreferenceStore);
  }
}
