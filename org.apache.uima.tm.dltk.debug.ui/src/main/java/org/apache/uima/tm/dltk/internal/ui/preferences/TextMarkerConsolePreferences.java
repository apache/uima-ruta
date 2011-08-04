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

package org.apache.uima.tm.dltk.internal.ui.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import org.apache.uima.tm.dltk.console.TextMarkerConsoleConstants;
import org.apache.uima.tm.dltk.internal.debug.ui.TextMarkerDebugUIPlugin;

public class TextMarkerConsolePreferences extends PreferencePage implements
        IWorkbenchPreferencePage {

  private Text newPrompt;

  private Text appendPrompt;

  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    return TextMarkerDebugUIPlugin.getDefault().getPreferenceStore();
  }

  protected void createPrompt(Composite parent, Object data) {
    Font font = parent.getFont();

    Group group = new Group(parent, SWT.NONE);
    group.setLayoutData(data);
    group.setFont(font);
    group.setText("Prompt");

    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    group.setLayout(layout);

    // New command
    Label newPromptLabel = new Label(group, SWT.NONE);
    newPromptLabel.setFont(font);
    newPromptLabel.setText("New command:");

    newPrompt = new Text(group, SWT.BORDER);
    newPrompt.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        validateValues();
      }
    });
    newPrompt.setLayoutData(new GridData(GridData.FILL, SWT.NONE, true, false));

    // Append command
    Label appendCommandLabel = new Label(group, SWT.NONE);
    appendCommandLabel.setFont(font);
    appendCommandLabel.setText("Append command:");

    appendPrompt = new Text(group, SWT.BORDER);
    appendPrompt.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        validateValues();
      }
    });
    appendPrompt.setLayoutData(new GridData(GridData.FILL, SWT.NONE, true, false));
  }

  @Override
  protected Control createContents(Composite parent) {
    Composite top = new Composite(parent, SWT.NONE);

    GridLayout layout = new GridLayout();
    layout.numColumns = 1;

    top.setLayout(layout);

    createPrompt(top, new GridData(GridData.FILL, SWT.NONE, true, false));

    initializeValues();
    validateValues();

    return top;
  }

  protected void initializeValues() {
    IPreferenceStore store = getPreferenceStore();

    newPrompt.setText(store.getString(TextMarkerConsoleConstants.PREF_NEW_PROMPT));

    appendPrompt.setText(store.getString(TextMarkerConsoleConstants.PREF_CONTINUE_PROMPT));
  }

  protected void validateValues() {
    if ("".equals(newPrompt.getText())) {
      setErrorMessage("Empty prompt");
      setValid(false);
    } else if ("".equals(appendPrompt.getText())) {
      setErrorMessage("Empty prompt");
      setValid(false);
    } else {
      setErrorMessage(null);
      setValid(true);
    }
  }

  public void init(IWorkbench workbench) {

  }

  @Override
  protected void performDefaults() {
    newPrompt.setText("=>");
    appendPrompt.setText("->");
  }

  @Override
  public boolean performOk() {
    IPreferenceStore store = getPreferenceStore();

    store.setValue(TextMarkerConsoleConstants.PREF_NEW_PROMPT, newPrompt.getText());
    store.setValue(TextMarkerConsoleConstants.PREF_CONTINUE_PROMPT, appendPrompt.getText());

    return true;
  }
}
