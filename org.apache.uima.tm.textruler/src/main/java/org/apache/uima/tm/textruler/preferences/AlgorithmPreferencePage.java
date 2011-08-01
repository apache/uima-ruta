package org.apache.uima.tm.textruler.preferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.uima.tm.textruler.TextRulerPlugin;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerController;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerFactory;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerParameter;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


public class AlgorithmPreferencePage extends FieldEditorPreferencePage implements
        IPreferencePage {

  public static String ID = "org.apache.uima.tm.textruler.algorithmPages";

  private TextRulerLearnerController algorithmController;

  private IPreferenceStore store;

  private ArrayList<FieldEditor> fields = new ArrayList<FieldEditor>();

  public AlgorithmPreferencePage(TextRulerLearnerController algorithmController) {
    super(FieldEditorPreferencePage.GRID);
    this.algorithmController = algorithmController;
    store = TextRulerPlugin.getDefault().getPreferenceStore();
    setPreferenceStore(store);
  }

  protected void createFieldEditors() {
    TextRulerLearnerFactory f = algorithmController.getFactory();
    TextRulerLearnerParameter[] params = f.getAlgorithmParameters();
    Map<String, Object> values = f.getAlgorithmParameterStandardValues();
    if (params != null) {
      for (int i = 0; i < params.length; i++) {
        TextRulerLearnerParameter p = params[i];
        String id = algorithmController.getID() + "." + p.id;
        FieldEditor l = null;
        switch (p.type) {
          case ML_BOOL_PARAM: {
            l = new BooleanFieldEditor(id, p.name, getFieldEditorParent());
            fields.add(l);
            addField(l);
            store.setDefault(id, (Boolean) values.get(p.id));
            l.setPreferenceStore(store);
            break;
          }

          case ML_FLOAT_PARAM:
          case ML_INT_PARAM:
          case ML_STRING_PARAM: {
            l = new StringFieldEditor(id, p.name, getFieldEditorParent());
            fields.add(l);
            addField(l);
            store.setDefault(id, values.get(p.id).toString());
            l.setPreferenceStore(store);
            break;
          }
            // case ML_SELECT_PARAM: {
            // Combo combo = new Combo(top, SWT.READ_ONLY);
            // if (p.selectValues != null)
            // for (MLParameterSelectValue v : p.selectValues) {
            // combo.add(v.name);
            // }
            // c = combo;
            // break;
            // }
          case ML_SELECT_PARAM:
            break;
        }
      }
    }
  }

//  public Map<String, Object> getCurrentAlgorithmParameters() {
//    // Map<String, Map<String, Object>> results = new HashMap<String, Map<String, Object>>();
//    Map<String, Object> result = new HashMap<String, Object>();
//
//    TextRulerLearnerFactory f = algorithmController.getFactory();
//    TextRulerLearnerParameter[] params = f.getAlgorithmParameters();
//    if (params != null) {
//      for (int i = 0; i < params.length; i++) {
//        TextRulerLearnerParameter p = params[i];
//        String id = algorithmController.getID() + "." + p.id;
//        switch (p.type) {
//          case ML_BOOL_PARAM: {
//            // Map<String, Object> result = new HashMap<String, Object>();
//            result.put(p.id, store.getBoolean(id));
//            // results.put(p.id, result);
//            break;
//          }
//
//          case ML_FLOAT_PARAM:
//          case ML_INT_PARAM:
//          case ML_STRING_PARAM: {
//            // Map<String, Object> result = new HashMap<String, Object>();
//            result.put(p.id, store.getString(id));
//            // results.put(p.id, result);
//            break;
//          }
//            // case ML_SELECT_PARAM: {
//            // break;
//            // }
//          case ML_SELECT_PARAM:
//            break;
//        }
//      }
//    }
//    return result;
//  }

  @Override
  protected void performDefaults() {
    for (FieldEditor f : fields)
      f.loadDefault();
    // super.performDefaults();
  }

  @Override
  public boolean performOk() {
    for (FieldEditor f : fields)
      f.store();
    // return super.performOk();
    return true;
  }
}
