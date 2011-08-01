package org.apache.uima.tm.dltk.internal.ui.wizards;

import org.eclipse.osgi.util.NLS;

public class TextMarkerWizardMessages extends NLS {

  private static final String BUNDLE_NAME = "org.apache.uima.tm.dltk.internal.ui.wizards.TextMarkerWizardMessages";//$NON-NLS-1$

  private TextMarkerWizardMessages() {
  }

  public static String ProjectCreationWizard_title;

  public static String ProjectCreationWizardFirstPage_title;

  public static String ProjectCreationWizardFirstPage_description;

  static {
    NLS.initializeMessages(BUNDLE_NAME, TextMarkerWizardMessages.class);
  }
}
