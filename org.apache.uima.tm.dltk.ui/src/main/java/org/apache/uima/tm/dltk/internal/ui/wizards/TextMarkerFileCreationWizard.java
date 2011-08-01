package org.apache.uima.tm.dltk.internal.ui.wizards;

import org.apache.uima.tm.dltk.internal.ui.TextMarkerImages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.wizards.NewSourceModulePage;
import org.eclipse.dltk.ui.wizards.NewSourceModuleWizard;


public class TextMarkerFileCreationWizard extends NewSourceModuleWizard {
  public static final String ID_WIZARD = "org.apache.uima.tm.dltk.internal.ui.wizards.TextMarkerFileCreationWizard";

  public TextMarkerFileCreationWizard() {
    setDefaultPageImageDescriptor(TextMarkerImages.DESC_WIZBAN_FILE_CREATION);
    setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
    setWindowTitle("Create TextMarker File");
  }

  @Override
  protected NewSourceModulePage createNewSourceModulePage() {
    return new TextMarkerFileCreationPage();
  }
}
