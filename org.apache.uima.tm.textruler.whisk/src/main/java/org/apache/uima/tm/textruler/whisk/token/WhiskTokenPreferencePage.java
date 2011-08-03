package org.apache.uima.tm.textruler.whisk.token;

import java.util.ArrayList;
import java.util.Map;

import org.apache.uima.tm.textruler.TextRulerPlugin;
import org.apache.uima.tm.textruler.extension.TextRulerController;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerController;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerFactory;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerParameter;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


public class WhiskTokenPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

  public static String ID = "org.apache.uima.tm.textruler.algorithmPages";

  private TextRulerLearnerController algorithmController;

  private IPreferenceStore store;

  private ArrayList<FieldEditor> fields = new ArrayList<FieldEditor>();

  public WhiskTokenPreferencePage() {
    TextRulerLearnerController ctrl = TextRulerController.getControllerForID("org.apache.uima.tm.textruler.whisk.token");
    this.algorithmController = ctrl;
    store = TextRulerPlugin.getDefault().getPreferenceStore();
    setPreferenceStore(store);
  }
  
  @Override
  protected Control createContents(Composite parent) {
    Composite top = new Composite(parent, SWT.LEFT);
    top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    top.setLayout(new GridLayout());
    
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
            l = new BooleanFieldEditor(id, p.name, top);
            fields.add(l);
            store.setDefault(id, (Boolean) values.get(p.id));
            l.setPreferenceStore(store);
            l.load();
            break;
          }

          case ML_FLOAT_PARAM:
          case ML_INT_PARAM:
          case ML_STRING_PARAM: {
            l = new StringFieldEditor(id, p.name, top);
            fields.add(l);
            store.setDefault(id, values.get(p.id).toString());
            l.setPreferenceStore(store);
            l.load();
            break;
          }
          case ML_SELECT_PARAM:
            break;
        }
      }
    }
    return top;
  }
  
  @Override
  public void init(IWorkbench workbench) { 
  }

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
