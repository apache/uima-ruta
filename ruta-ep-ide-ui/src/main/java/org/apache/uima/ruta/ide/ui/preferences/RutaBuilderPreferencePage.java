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

import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.apache.uima.ruta.ide.core.RutaCorePreferences;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Preference page to manage preferences for the ide plugin.
 */
public class RutaBuilderPreferencePage extends FieldEditorPreferencePage
        implements IWorkbenchPreferencePage {

  private BooleanFieldEditor builderImport;

  private FieldEditor builderResolve;

  private BooleanFieldEditor builderShortNames;

  private BooleanFieldEditor compressWordLists;

  public RutaBuilderPreferencePage() {
    setPreferenceStore(RutaIdeUIPlugin.getDefault().getPreferenceStore());
    setDescription("Builder");
  }

  @Override
  protected void createFieldEditors() {
    builderResolve = new BooleanFieldEditor(RutaCorePreferences.BUILDER_RESOLVE_IMPORTS,
            RutaPreferencesMessages.BuilderResolveImports, getFieldEditorParent());
    addField(builderResolve);

    builderImport = new BooleanFieldEditor(RutaCorePreferences.BUILDER_IMPORT_BY_NAME,
            RutaPreferencesMessages.BuilderImportByName, getFieldEditorParent());
    addField(builderImport);

    builderShortNames = new BooleanFieldEditor(
            RutaCorePreferences.BUILDER_IGNORE_DUPLICATE_SHORTNAMES,
            RutaPreferencesMessages.BuilderIgnoreDuplicateShortnames, getFieldEditorParent());
    addField(builderShortNames);

    compressWordLists = new BooleanFieldEditor(RutaCorePreferences.COMPRESS_WORDLISTS,
            RutaPreferencesMessages.CompressWordLists, getFieldEditorParent());

    compressWordLists = new BooleanFieldEditor(RutaCorePreferences.DICT_REMOVE_WS,
            RutaPreferencesMessages.DictRemoveWS, getFieldEditorParent());
    addField(compressWordLists);
  }

  @Override
  public void init(IWorkbench workbench) {
  }

}