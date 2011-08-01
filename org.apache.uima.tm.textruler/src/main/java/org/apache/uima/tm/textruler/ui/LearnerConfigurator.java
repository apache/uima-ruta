package org.apache.uima.tm.textruler.ui;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.tm.textruler.TextRulerPlugin;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerController;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerFactory;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerParameter;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Control;

public class LearnerConfigurator {

  Map<String, Control> paramControls = new HashMap<String, Control>();

  private TextRulerLearnerController algorithmController;

  public LearnerConfigurator(TextRulerLearnerController algController) {
    algorithmController = algController;
    // createWidgetsForAlgorithm();
  }

  public TextRulerLearnerController getController() {
    return algorithmController;
  }

  // protected void createWidgetsForAlgorithm() {
  // TextRulerLearnerFactory f = algorithmController.getFactory();
  // TextRulerLearnerParameter[] params = f.getAlgorithmParameters();
  // if (params == null)
  // return;
  //
  // for (TextRulerLearnerParameter p : params) {
  // Label l = new Label(this, SWT.NONE);
  // l.setText(p.name);
  // Control theControl = null;
  // switch (p.type) {
  // case ML_BOOL_PARAM: {
  // theControl = new Button(this, SWT.CHECK | SWT.LEFT);
  // break;
  // }
  //
  // case ML_FLOAT_PARAM:
  // case ML_INT_PARAM:
  // case ML_STRING_PARAM: {
  // theControl = new Text(this, SWT.SINGLE | SWT.BORDER);
  // ((Text) theControl).addModifyListener(new ModifyListener() {
  // public void modifyText(ModifyEvent e) {
  // // without that listener, the text fields forget the
  // // last change when leaving with tab! don't know why!
  // // we also MUST call getText() otherwise the changes in
  // // the field are lost (what is this???!!)
  // Text t = (Text) e.widget;
  // t.getText();
  // }
  // });
  // GridData d = new GridData();
  // d.widthHint = 150;
  // d.minimumWidth = 40;
  // theControl.setLayoutData(d);
  // break;
  // }
  //
  // case ML_SELECT_PARAM: {
  // Combo combo = new Combo(this, SWT.READ_ONLY);
  // if (p.selectValues != null)
  // for (MLParameterSelectValue v : p.selectValues) {
  // combo.add(v.name);
  // }
  // theControl = combo;
  // break;
  // }
  // }
  // if (theControl != null) {
  // theControl.setData(p);
  // }
  // paramControls.put(p.id, theControl);
  // }
  // }

  public String getID() {
    return algorithmController.getID();
  }

  // public Map<String, Object> getCurrentParameterSettings() {
  // Map<String, Object> result = new HashMap<String, Object>();
  // for (String key : paramControls.keySet()) {
  // Control c = paramControls.get(key);
  // TextRulerLearnerParameter p = (TextRulerLearnerParameter) c.getData();
  // try {
  // switch (p.type) {
  // case ML_BOOL_PARAM: {
  // result.put(key, new Boolean(((Button) c).getSelection()));
  // break;
  // }
  // case ML_INT_PARAM: {
  // result.put(key, new Integer(((Text) c).getText()));
  // break;
  // }
  // case ML_FLOAT_PARAM: {
  // result.put(key, new Float(((Text) c).getText()));
  // break;
  // }
  // case ML_STRING_PARAM: {
  // result.put(key, new String(((Text) c).getText()));
  // break;
  // }
  // case ML_SELECT_PARAM: {
  // int index = ((Combo) c).getSelectionIndex();
  // if (index >= 0 && p.selectValues != null && index < p.selectValues.length) {
  // result.put(key, new String(p.selectValues[index].id));
  // }
  // break;
  // }
  // }
  // } catch (NumberFormatException e) {
  // // todo !
  // }
  // }
  // return result;
  // }

  public Map<String, Object> getCurrentParameterSettings() {
    Map<String, Object> result = new HashMap<String, Object>();
    IPreferenceStore store = TextRulerPlugin.getDefault().getPreferenceStore();
    TextRulerLearnerFactory f = algorithmController.getFactory();
    TextRulerLearnerParameter[] params = f.getAlgorithmParameters();
    if (params != null) {
      for (int i = 0; i < params.length; i++) {
        TextRulerLearnerParameter p = params[i];
        String id = algorithmController.getID() + "." + p.id;
        switch (p.type) {
          case ML_BOOL_PARAM: {
            result.put(p.id, store.getBoolean(id));
            break;
          }
          case ML_FLOAT_PARAM: {
            result.put(p.id, store.getFloat(id));
            break;
          }
          case ML_INT_PARAM: {
            result.put(p.id, store.getInt(id));
            break;
          }
          case ML_STRING_PARAM: {
            result.put(p.id, store.getString(id));
            break;
          }
            // case ML_SELECT_PARAM: {
            // break;
            // }
          case ML_SELECT_PARAM:
            break;
        }
      }
    }
    return result;
  }

  // public void setCurrentParameterSettings(Map<String, Object> params) {
  // if (params == null)
  // return;
  // for (String key : paramControls.keySet())
  // if (params.containsKey(key)) {
  // Control c = paramControls.get(key);
  // TextRulerLearnerParameter p = (TextRulerLearnerParameter) c.getData();
  // switch (p.type) {
  // case ML_BOOL_PARAM: {
  // ((Button) c).setSelection(((Boolean) params.get(key)).booleanValue());
  // break;
  // }
  //
  // case ML_INT_PARAM:
  // case ML_FLOAT_PARAM:
  // case ML_STRING_PARAM: {
  // ((Text) c).setText(params.get(key).toString());
  // break;
  // }
  //
  // case ML_SELECT_PARAM: {
  // if (p.selectValues != null) {
  // String value = (String) params.get(key);
  // for (int i = 0; i < p.selectValues.length; i++) {
  // if (p.selectValues[i].id.equals(value)) {
  // ((Combo) c).select(i);
  // break;
  // }
  // }
  // }
  // }
  // }
  // }
  // }
  //
  // @Override
  // public void setEnabled(boolean flag) {
  // super.setEnabled(flag);
  // for (Control c : paramControls.values())
  // c.setEnabled(flag);
  // }

}
