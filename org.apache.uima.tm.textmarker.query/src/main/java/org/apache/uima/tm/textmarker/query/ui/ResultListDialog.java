package org.apache.uima.tm.textmarker.query.ui;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ResultListDialog extends ApplicationWindow {

  Composite overlay;

  String data;

  public ResultListDialog(Shell parentShell, String data) {
    super(parentShell);
    this.data = data;
  }

  protected Control createContents(Composite parent) {
    GridLayout layout = new GridLayout();
    parent.setLayout(layout);
    parent.setLayoutData(new GridData(GridData.FILL_BOTH));

    Text text = new Text(parent, SWT.READ_ONLY | SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
    text.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
    text.setText("No data aviable");
    text.setLayoutData(new GridData(GridData.FILL_BOTH));

    if (text != null) {
      text.setText(data);
    }
    return parent;
  }

}
