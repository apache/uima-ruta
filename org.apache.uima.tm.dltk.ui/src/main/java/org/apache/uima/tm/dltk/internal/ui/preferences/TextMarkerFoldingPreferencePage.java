package org.apache.uima.tm.dltk.internal.ui.preferences;

import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.preferences.PreferencesMessages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


/**
 * The page for setting the editor options.
 */
public final class TextMarkerFoldingPreferencePage extends AbstractConfigurationBlockPreferencePage {

  /*
   * @see org.eclipse.ui.internal.editors.text.AbstractConfigureationBlockPreferencePage#getHelpId()
   */
  @Override
  protected String getHelpId() {
    // return IScriptHelpContextIds.JAVA_EDITOR_PREFERENCE_PAGE;
    return null;
  }

  /*
   * @see
   * org.eclipse.ui.internal.editors.text.AbstractConfigurationBlockPreferencePage#setDescription()
   */
  @Override
  protected void setDescription() {
    String description = PreferencesMessages.EditorPreferencePage_folding_title;
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
    return new TextMarkerFoldingConfigurationBlock(overlayPreferenceStore, this);
  }
}
