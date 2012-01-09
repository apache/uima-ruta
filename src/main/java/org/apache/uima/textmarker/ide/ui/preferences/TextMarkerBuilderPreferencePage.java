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

package org.apache.uima.textmarker.ide.ui.preferences;

import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.apache.uima.textmarker.ide.core.TextMarkerCorePreferences;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Preference page to manage preferences for the ide plugin.
 */
public class TextMarkerBuilderPreferencePage extends FieldEditorPreferencePage
        implements IWorkbenchPreferencePage {

  private BooleanFieldEditor builderImport;
  private FieldEditor builderResolve;
  private BooleanFieldEditor builderShortNames;
  
  public TextMarkerBuilderPreferencePage() {
    setPreferenceStore(TextMarkerIdePlugin.getDefault().getPreferenceStore());
    setDescription("Builder");
  }

  @Override
  protected void createFieldEditors() {
    builderResolve = new BooleanFieldEditor(TextMarkerCorePreferences.BUILDER_RESOLVE_IMPORTS,
            TextMarkerPreferencesMessages.BuilderResolveImports, getFieldEditorParent());
    addField(builderResolve);
    
    builderImport = new BooleanFieldEditor(TextMarkerCorePreferences.BUILDER_IMPORT_BY_NAME,
            TextMarkerPreferencesMessages.BuilderImportByName, getFieldEditorParent());
    addField(builderImport);
    
    builderShortNames = new BooleanFieldEditor(TextMarkerCorePreferences.BUILDER_IGNORE_DUPLICATE_SHORTNAMES,
            TextMarkerPreferencesMessages.BuilderIgnoreDuplicateShortnames, getFieldEditorParent());
    addField(builderShortNames);
  }

  public void init(IWorkbench workbench) {
  }
  
 
}