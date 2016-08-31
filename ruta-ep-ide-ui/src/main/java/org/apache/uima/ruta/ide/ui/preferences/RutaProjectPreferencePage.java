/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.ruta.ide.ui.preferences;

import org.apache.uima.cas.SerialFormat;
import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.apache.uima.ruta.ide.core.RutaCorePreferences;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Preference page to manage preferences for the ide plugin.
 */
public class RutaProjectPreferencePage extends FieldEditorPreferencePage
        implements IWorkbenchPreferencePage {

  private BooleanFieldEditor clearOutput;

  private BooleanFieldEditor noVM;

  private BooleanFieldEditor addSDI;

  private ComboFieldEditor defaultCasSerializationFormat;

  public RutaProjectPreferencePage() {
    setPreferenceStore(RutaIdeUIPlugin.getDefault().getPreferenceStore());
    setDescription("Project Management");
  }

  @Override
  protected void createFieldEditors() {
    clearOutput = new BooleanFieldEditor(RutaCorePreferences.PROJECT_CLEAR_OUTPUT,
            RutaPreferencesMessages.ProjectClearOutput, getFieldEditorParent());
    addField(clearOutput);

    noVM = new BooleanFieldEditor(RutaCorePreferences.NO_VM_IN_DEV_MODE,
            RutaPreferencesMessages.NoVMInDevMode, getFieldEditorParent());
    addField(noVM);

    addSDI = new BooleanFieldEditor(RutaCorePreferences.ADD_SDI, RutaPreferencesMessages.AddSDI,
            getFieldEditorParent());
    addField(addSDI);

    String[][] values = new String[7][];
    int index = 0;
    addFormat(values, index++, SerialFormat.XMI);
    addFormat(values, index++, SerialFormat.XCAS);
    addFormat(values, index++, SerialFormat.BINARY);
    addFormat(values, index++, SerialFormat.COMPRESSED);
    addFormat(values, index++, SerialFormat.COMPRESSED_FILTERED);
    addFormat(values, index++, SerialFormat.SERIALIZED);
    addFormat(values, index++, SerialFormat.SERIALIZED_TSI);
    

    defaultCasSerializationFormat = new ComboFieldEditor(
            RutaCorePreferences.DEFAULT_CAS_SERIALIZATION_FORMAT,
            RutaPreferencesMessages.DefaultCasSerializationFormat, values, getFieldEditorParent());
    addField(defaultCasSerializationFormat);

  }

  private void addFormat(String[][] values, int index, SerialFormat serialFormat) {
    values[index] = new String[] {
        serialFormat.toString() + " (" + serialFormat.getDefaultFileExtension() + ")",
        serialFormat.toString() };
  }

  public void init(IWorkbench workbench) {
  }

}