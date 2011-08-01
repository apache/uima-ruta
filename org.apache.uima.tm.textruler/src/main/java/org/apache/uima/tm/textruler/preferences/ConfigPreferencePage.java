package org.apache.uima.tm.textruler.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ConfigPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

  public static String ID = "org.apache.uima.tm.textruler.config";

  private Label evalHeadline;

  public ConfigPreferencePage() {

  }

  @Override
  protected Control createContents(Composite parent) {
    Composite top = new Composite(parent, SWT.LEFT);
    top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    top.setLayout(new GridLayout());

    evalHeadline = new Label(top, SWT.NULL);
    evalHeadline.setText("General settings for the different TextRuler methods.");

    return top;
  }

  @Override
  public void init(IWorkbench workbench) {
    
  }
}
