package org.apache.uima.tm.cev.preferences;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.preference.ListEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class CEVAnnotationFilterEditor extends ListEditor {

  public CEVAnnotationFilterEditor(String name, String labelText, Composite parent) {
    super(name, labelText, parent);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.ListEditor#createList(java.lang.String[])
   */
  @Override
  protected String createList(String[] items) {
    StringBuffer strBuffer = new StringBuffer();

    for (int i = 0; i < items.length; i++) {
      strBuffer.append(items[i]);
      if (i < items.length - 1)
        strBuffer.append('|');
    }

    return strBuffer.toString();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.ListEditor#getNewInputObject()
   */
  @Override
  protected String getNewInputObject() {
    InputDialog dlg = new InputDialog(Display.getCurrent().getActiveShell(), "Annotation Type",
            "Enter Annotation Type Name", null, null);
    if (dlg.open() == Window.OK) {
      return dlg.getValue();
    }

    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.ListEditor#parseString(java.lang.String)
   */
  @Override
  protected String[] parseString(String stringList) {
    return stringList.split("\\|");
  }

}
