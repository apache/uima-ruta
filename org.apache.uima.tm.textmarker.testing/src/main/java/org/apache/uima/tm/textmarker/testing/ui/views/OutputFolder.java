package org.apache.uima.tm.textmarker.testing.ui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

public class OutputFolder extends Composite {

  public OutputFolder(Composite parent) {
    super(parent, SWT.WRAP);
    GridLayout gridLayout = new GridLayout();
    gridLayout.numColumns = 1;
    setLayout(gridLayout);

    TabFolder tFolder = new TabFolder(this, SWT.WRAP);

    // TabItem failures = new TabItem(tFolder, SWT.NONE);
    // failures.setText("Failures");
    // Text failuresText = new Text(tFolder, SWT.BORDER | SWT.MULTI);
    // failuresText.setText("Failures Info");
    // failures.setControl(failuresText);
    //		
    // TabItem falsePositives = new TabItem (tFolder,SWT.NONE);
    // falsePositives.setText("False Positives");
    // Text fPositivesText = new Text (tFolder, SWT.BORDER | SWT.MULTI);
    // fPositivesText.setText("False Positives");
    // falsePositives.setControl(fPositivesText);
    //		
    // TabItem falseNegatives = new TabItem (tFolder,SWT.NONE);
    // falseNegatives.setText("False Negatives");
    // Text fNegativesText = new Text(tFolder, SWT.BORDER | SWT.MULTI);
    // fNegativesText.setText("False Negatives");
    // falseNegatives.setControl(fNegativesText);

    TabItem failuresTab = createTab(tFolder, "Failures", "Insert Failures here");
    TabItem fPositivesTab = createTab(tFolder, "FPositives", "Insert false positives here");
    TabItem fNegativesTab = createTab(tFolder, "FNegatives", "Insert false negatives here");

    GridData tFolderData = new GridData();
    tFolderData.horizontalAlignment = GridData.FILL;
    tFolderData.verticalAlignment = GridData.FILL;
    tFolderData.horizontalSpan = 1;
    tFolderData.grabExcessHorizontalSpace = true;
    tFolderData.grabExcessVerticalSpace = true;
    tFolder.setLayoutData(tFolderData);
  }

  private TabItem createTab(TabFolder tFolder, String name, String input) {
    TabItem tab = new TabItem(tFolder, SWT.NONE);
    tab.setText(name);
    Text text = new Text(tFolder, SWT.BORDER | SWT.MULTI);
    text.setText(input);
    tab.setControl(text);
    return tab;
  }
}
