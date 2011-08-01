package org.apache.uima.tm.cev.preferences;

import org.apache.uima.tm.cev.CEVPlugin;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


/**
 * PreferencePage zum Einstellen der Baumordnung in den Views - nach Typ geordnet - nach Position
 * hierarchisch geordnet
 * 
 * @author Marco Nehmeier
 */
public class CEVViewerTreeViewPreferencePage extends FieldEditorPreferencePage implements
        IWorkbenchPreferencePage {

  /**
   * Konstruktor
   */
  public CEVViewerTreeViewPreferencePage() {
    super(GRID);
    setPreferenceStore(CEVPlugin.getDefault().getPreferenceStore());
  }

  /**
   * Erzeugt die Editor-Felder
   */
  @Override
  public void createFieldEditors() {
    RadioGroupFieldEditor editor = new RadioGroupFieldEditor(
            CEVPreferenceConstants.P_ANNOTATION_BROWSER_TREE_ORDER,
            "Default tree order for the Annotation Browser", 1,
            CEVPreferenceConstants.P_ANNOTATION_BROWSER_TREE_ORDER_VALUE, getFieldEditorParent());
    addField(editor);
    editor.setEnabled(false, getFieldEditorParent());

    addField(new RadioGroupFieldEditor(CEVPreferenceConstants.P_ANNOTATION_REPR,
            "Default annotation representation in Annotation Browser and Selection Browser", 1,
            CEVPreferenceConstants.P_ANNOTATION_REPR_VALUES, getFieldEditorParent()));
    addField(new BooleanFieldEditor(CEVPreferenceConstants.P_ANNOTATION_EDITOR_TRIM,
            "Trim selected text in annotation editor", getFieldEditorParent()));
    addField(new BooleanFieldEditor(CEVPreferenceConstants.P_AUTO_REFRESH, "Reload changed CAS",
            getFieldEditorParent()));
    addField(new BooleanFieldEditor(CEVPreferenceConstants.P_SELECT_ONLY,
            "Initiate the creation of annotations with the selection of text",
            getFieldEditorParent()));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
   */
  public void init(IWorkbench workbench) {
  }

}