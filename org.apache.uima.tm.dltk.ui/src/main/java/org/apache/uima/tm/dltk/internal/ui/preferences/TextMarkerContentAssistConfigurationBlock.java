package org.apache.uima.tm.dltk.internal.ui.preferences;

import java.util.ArrayList;

import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.preferences.CodeAssistConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class TextMarkerContentAssistConfigurationBlock extends CodeAssistConfigurationBlock {
  public TextMarkerContentAssistConfigurationBlock(PreferencePage mainPreferencePage,
          OverlayPreferenceStore store) {
    super(mainPreferencePage, store);
  }

  @Override
  protected void getOverlayKeys(ArrayList overlayKeys) {
    super.getOverlayKeys(overlayKeys);

    // overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
    // OverlayPreferenceStore.BOOLEAN,
    // TextMarkerPreferenceConstants.CODEASSIST_FILTER_INTERNAL_API));
    //
    overlayKeys.add(new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,
            PreferenceConstants.CODEASSIST_AUTOACTIVATION_TRIGGERS));
  }

  // protected void addAutoActivationSection(Composite composite) {
  // super.addAutoActivationSection(composite);
  // String label = "Auto activation triggers for &TM:";
  // addLabelledTextField(composite, label,
  // PreferenceConstants.CODEASSIST_AUTOACTIVATION_TRIGGERS, 4, 2,
  // false);
  // }

  @Override
  public Control createControl(Composite parent) {
    Composite control = (Composite) super.createControl(parent);

    GridLayout layout = new GridLayout();
    layout.numColumns = 2;

    Composite composite = createSubsection(control, null, "Filtering");
    composite.setLayout(layout);

    return control;
  }

}
