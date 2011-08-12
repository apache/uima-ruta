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

package org.apache.uima.textmarker.ide.ui.preferences;

import java.util.ArrayList;

import org.apache.uima.textmarker.ide.core.TextMarkerCorePreferences;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore.OverlayKey;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


public class TextMarkerBuilderConfigurationBlock extends AbstractConfigurationBlock {

  public TextMarkerBuilderConfigurationBlock(PreferencePage mainPreferencePage,
          OverlayPreferenceStore store, String nature) {
    super(store, mainPreferencePage);
    getPreferenceStore().addKeys(createOverlayStoreKeys());
  }

  private OverlayKey[] createOverlayStoreKeys() {
    ArrayList keys = new ArrayList();
    keys.add(new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,
            TextMarkerCorePreferences.BUILDER_IMPORT_BY_NAME));
    keys.add(new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,
            TextMarkerCorePreferences.BUILDER_RESOLVE_IMPORTS));
    keys.add(new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,
            TextMarkerCorePreferences.BUILDER_IGNORE_DUPLICATE_SHORTNAMES));
    return (OverlayPreferenceStore.OverlayKey[]) keys
            .toArray(new OverlayPreferenceStore.OverlayKey[keys.size()]);
  }

  /**
   * Creates page for appearance preferences.
   * 
   * @param parent
   *          the parent composite
   * @return the control for the preference page
   */
  public Control createControl(Composite parent) {
    initializeDialogUnits(parent);

    Composite control = new Composite(parent, SWT.NONE);
    control.setLayout(new GridLayout());

    Composite composite = createSubsection(control, null, "Settings");
    createSettingsGroup(composite);

    return control;
  }

  private Control createSettingsGroup(Composite composite) {
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    composite.setLayout(layout);

    Button button = addCheckBox(composite, TextMarkerPreferencesMessages.BuilderResolveImports,
            TextMarkerCorePreferences.BUILDER_RESOLVE_IMPORTS, 0);
    // button.setEnabled(false);
    button = addCheckBox(composite, TextMarkerPreferencesMessages.BuilderImportByName,
            TextMarkerCorePreferences.BUILDER_IMPORT_BY_NAME, 0);
    button = addCheckBox(composite,
            TextMarkerPreferencesMessages.BuilderIgnoreDuplicateShortnames,
            TextMarkerCorePreferences.BUILDER_IGNORE_DUPLICATE_SHORTNAMES, 0);
    // button.setEnabled(false);
    return composite;
  }

  @Override
  public void initialize() {

    super.initialize();

  }

  @Override
  public void performDefaults() {
    super.performDefaults();

  }
}
