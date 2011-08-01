package org.apache.uima.tm.dltk.internal.ui.preferences;

import org.apache.uima.tm.dltk.internal.ui.text.folding.TextMarkerFoldingPreferenceBlock;
import org.eclipse.dltk.ui.preferences.FoldingConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.text.folding.IFoldingPreferenceBlock;
import org.eclipse.jface.preference.PreferencePage;


public class TextMarkerFoldingConfigurationBlock extends FoldingConfigurationBlock {

  public TextMarkerFoldingConfigurationBlock(OverlayPreferenceStore store, PreferencePage p) {
    super(store, p);
  }

  @Override
  protected IFoldingPreferenceBlock createFoldingPreferenceBlock() {
    return new TextMarkerFoldingPreferenceBlock(fStore, getPreferencePage());
  }
}
