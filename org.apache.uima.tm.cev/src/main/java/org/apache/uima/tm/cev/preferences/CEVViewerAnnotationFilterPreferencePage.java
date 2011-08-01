package org.apache.uima.tm.cev.preferences;

import org.apache.uima.tm.cev.CEVPlugin;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


/**
 * PreferencePage zum Einstellen von Filterfunktionen fuer die Annotationen
 * 
 * @author Marco Nehmeier
 */
public class CEVViewerAnnotationFilterPreferencePage extends FieldEditorPreferencePage implements
        IWorkbenchPreferencePage {

  /**
   * Konstruktor
   */
  public CEVViewerAnnotationFilterPreferencePage() {
    super(GRID);
    setPreferenceStore(CEVPlugin.getDefault().getPreferenceStore());
  }

  /**
   * Erzeugt die Editor-Felder
   */
  @Override
  public void createFieldEditors() {
    addField(new CEVAnnotationFilterEditor(CEVPreferenceConstants.P_ANNOTATION_FILTER,
            "Annotation Types", getFieldEditorParent()));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
   */
  public void init(IWorkbench workbench) {
  }

}