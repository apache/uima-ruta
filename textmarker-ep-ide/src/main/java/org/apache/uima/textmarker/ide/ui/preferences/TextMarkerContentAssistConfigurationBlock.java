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

import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.preferences.CodeAssistConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class TextMarkerContentAssistConfigurationBlock extends CodeAssistConfigurationBlock {
  public TextMarkerContentAssistConfigurationBlock(PreferencePage mainPreferencePage,
          OverlayPreferenceStore store) {
    super(mainPreferencePage, store);
  }

  @Override
  protected void getOverlayKeys(ArrayList overlayKeys) {
    super.getOverlayKeys(overlayKeys);

    // overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
    // OverlayPreferenceStore.BOOLEAN,
    // TextMarkerPreferenceConstants.CODEASSIST_FILTER_INTERNAL_API));
    //
    overlayKeys.add(new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,
            PreferenceConstants.CODEASSIST_AUTOACTIVATION_TRIGGERS));
  }

  // protected void addAutoActivationSection(Composite composite) {
  // super.addAutoActivationSection(composite);
  // String label = "Auto activation triggers for &TM:";
  // addLabelledTextField(composite, label,
  // PreferenceConstants.CODEASSIST_AUTOACTIVATION_TRIGGERS, 4, 2,
  // false);
  // }

  @Override
  public Control createControl(Composite parent) {
    Composite control = (Composite) super.createControl(parent);

    GridLayout layout = new GridLayout();
    layout.numColumns = 2;

    Composite composite = createSubsection(control, null, "Filtering");
    composite.setLayout(layout);

    return control;
  }

}
