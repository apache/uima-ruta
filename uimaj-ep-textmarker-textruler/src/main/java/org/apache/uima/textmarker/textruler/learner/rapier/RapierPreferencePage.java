/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.textmarker.textruler.learner.rapier;

import java.util.ArrayList;
import java.util.Map;

import org.apache.uima.textmarker.textruler.TextRulerPlugin;
import org.apache.uima.textmarker.textruler.extension.TextRulerController;
import org.apache.uima.textmarker.textruler.extension.TextRulerLearnerController;
import org.apache.uima.textmarker.textruler.extension.TextRulerLearnerFactory;
import org.apache.uima.textmarker.textruler.extension.TextRulerLearnerParameter;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class RapierPreferencePage extends FieldEditorPreferencePage implements
        IWorkbenchPreferencePage {

  public static String ID = "org.apache.uima.textmarker.textruler.algorithmPages";

  private TextRulerLearnerController algorithmController;

  private IPreferenceStore store;

  private ArrayList<FieldEditor> fields = new ArrayList<FieldEditor>();

  public RapierPreferencePage() {
    super(FieldEditorPreferencePage.GRID);
    TextRulerLearnerController ctrl = TextRulerController
            .getControllerForID("org.apache.uima.textmarker.textruler.rapier");
    this.algorithmController = ctrl;
    store = TextRulerPlugin.getDefault().getPreferenceStore();
    setPreferenceStore(store);
  }

  @Override
  public void init(IWorkbench workbench) {
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
          case ML_SELECT_PARAM:
            break;
        }
      }
    }
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
