package org.apache.uima.tm.dltk.internal.ui.preferences;

import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class TextMarkerGlobalConfigurationBlock extends AbstractConfigurationBlock {

  public TextMarkerGlobalConfigurationBlock(OverlayPreferenceStore store,
          PreferencePage mainPreferencePage) {
    super(store, mainPreferencePage);
  }

  public Control createControl(Composite parent) {
    initializeDialogUnits(parent);

    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout());

    return composite;
  }
}
