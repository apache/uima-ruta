package org.apache.uima.tm.cev.preferences;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.preference.ListEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * ListEditor fuer Filterung der Annotationen
 * 
 * Ein Filterstring wird in RegEx editiert. Die einzelnen Filterstrings werden mit "|" (oder in
 * RegEx) zusammengefuegt und in den Preferences gespeichert
 * 
 * @author Marco Nehmeier
 * 
 */
public class CEVAnnotationFilterEditor extends ListEditor {

  /**
   * Konstruktor
   * 
   * @param name
   *          Name
   * @param labelText
   *          Labletext
   * @param parent
   *          Uebergeordnetes Composite
   */
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

    // Strings mit "|" zusammenhaengen
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
    // Inputdialog zum eingeben des Strings
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
    // bei den "|" aufspliten
    return stringList.split("\\|");
  }

}
