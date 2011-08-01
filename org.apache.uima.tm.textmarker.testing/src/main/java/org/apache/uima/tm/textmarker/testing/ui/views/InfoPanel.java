package org.apache.uima.tm.textmarker.testing.ui.views;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class InfoPanel extends Composite {

  private final Image fErrorIcon = TestViewPage.createImage("/icons/error_ovr.gif"); //$NON-NLS-1$

  private final Image fFailureIcon = TestViewPage.createImage("/icons/failed_ovr.gif"); //$NON-NLS-1$

  Text fileLabel;

  Text runLabel;

  Text fpLabel;

  Text fnLabel;

  Text fMLabel;

  Combo comboBox;

  public InfoPanel(Composite parent) {
    super(parent, SWT.WRAP);
    GridLayout gridLayout = new GridLayout();
    gridLayout.numColumns = 4;
    setLayout(gridLayout);

    Composite fileInfoComposite = new Composite(this, SWT.WRAP);
    GridLayout fileInfoLayout = new GridLayout();
    fileInfoLayout.numColumns = 3;
    fileInfoComposite.setLayout(fileInfoLayout);

    GridData fileInfoData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
    // fileInfoData.grabExcessHorizontalSpace = true;
    fileInfoComposite.setLayoutData(fileInfoData);
    fileLabel = createLabel("Script :", null, "--", fileInfoComposite);

    comboBox = new Combo(this, SWT.DROP_DOWN);
    comboBox.add("_InitialView");
    comboBox.select(0);

    Composite testInfoComposite = new Composite(this, SWT.WRAP);
    GridData data = new GridData();
    data.horizontalSpan = 2;
    testInfoComposite.setLayoutData(data);

    GridLayout testInfoLayout = new GridLayout();
    testInfoLayout.numColumns = 12;

    GridData testInfoData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
            | GridData.FILL_HORIZONTAL);
    testInfoComposite.setLayoutData(testInfoData);
    testInfoComposite.setLayout(testInfoLayout);

    runLabel = createLabel("TP :", null, "0", testInfoComposite);
    fpLabel = createLabel("FP :", fErrorIcon, "0", testInfoComposite);
    fnLabel = createLabel("FN :", fFailureIcon, "0", testInfoComposite);
    fMLabel = createLabel("F1 :", null, "0", testInfoComposite);
  }

  public void dispose() {
    super.dispose();
    fErrorIcon.dispose();
    fFailureIcon.dispose();
  }

  private Text createLabel(String name, Image image, String init, Composite parent) {

    Label label = new Label(parent, SWT.NONE);
    if (image != null) {
      image.setBackground(label.getBackground());
      label.setImage(image);
    }
    label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));

    label = new Label(parent, SWT.NONE);
    label.setText(name);
    label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
    // label.setFont(JFaceResources.getBannerFont());

    Text value = new Text(parent, SWT.READ_ONLY);
    value.setText(init);
    // bug: 39661 Junit test counters do not repaint correctly [JUnit]
    value.setBackground(getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
    value
            .setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
                    | GridData.FILL_HORIZONTAL));
    return value;
  }

  public void setRuns(int x) {
    this.runLabel.setText(String.valueOf(x));
  }

  public void setFP(int x) {
    fpLabel.setText(String.valueOf(x));

  }

  public void setFN(int x) {
    fnLabel.setText(String.valueOf(x));
  }

  public void setFilename(String filename) {
    fileLabel.setText(filename);
  }

  public void setFMeasure(double f) {
    fMLabel.setText(String.valueOf(f));
  }

  public Combo getComboBox() {
    return comboBox;
  }

  public String getSelectedViewCasName() {
    return comboBox.getText();
  }

  public void addCASViewNamesToCombo(List<String> list) {
    for (String s : list) {
      comboBox.add(s);
    }
  }

}
