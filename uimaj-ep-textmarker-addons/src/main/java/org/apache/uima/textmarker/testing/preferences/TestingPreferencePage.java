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

package org.apache.uima.textmarker.testing.preferences;

import org.apache.uima.textmarker.addons.TextMarkerAddonsPlugin;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class TestingPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

  public static String ID = "org.apache.uima.textmarker.testing.preferences";

  private Text desc;

  private Label evalHeadline;

  private DescriptionRadioGroupFieldEditor evaluators;

  private BooleanFieldEditor sync;

  private BooleanFieldEditor oldResults;

  public TestingPreferencePage() {
    IPreferenceStore store = TextMarkerAddonsPlugin.getDefault().getPreferenceStore();
    setPreferenceStore(store);
  }

  @Override
  public void init(IWorkbench workbench) {
    // TODO Auto-generated method stub

  }

  @Override
  protected Control createContents(Composite parent) {
    Composite top = new Composite(parent, SWT.LEFT);
    top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    top.setLayout(new GridLayout());

    sync = new BooleanFieldEditor(TestingPreferenceConstants.AUTOMATED_FILE_SYNCRONIZATION,
            "Synchronize Test-Files and Workspace automatically", top);
    sync.setPage(this);
    sync.setPreferenceStore(getPreferenceStore());
    sync.load();

    oldResults = new BooleanFieldEditor(TestingPreferenceConstants.LOAD_OLD_TEST_RESULTS,
            "Load test results from previous sessions", top);
    oldResults.setPage(this);
    oldResults.setPreferenceStore(getPreferenceStore());
    oldResults.load();

    evaluators = new DescriptionRadioGroupFieldEditor(TestingPreferenceConstants.EVALUATOR_FACTORY,
            "Evaluator :", 1, TestingPreferenceConstants.EVALUATORS, top);

    evaluators.setPage(this);
    evaluators.setPreferenceStore(getPreferenceStore());
    evaluators.load();

    IDescriptionChangedListener listener = new IDescriptionChangedListener() {
      public void descriptionChanged(String s) {
        String text = TextMarkerAddonsPlugin.getCasEvaluatorFactoryById(s).getDescription();
        desc.setText(text);
        desc.update();
        desc.redraw();
      }
    };

    evaluators.setDescriptionChangedListener(listener);
    evalHeadline = new Label(top, SWT.NULL);
    evalHeadline.setText("Description:");

    desc = new Text(top, SWT.READ_ONLY | SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
    desc.setText(getEvalDesc());
    desc.setBackground(this.getControl().getDisplay().getSystemColor(SWT.COLOR_WHITE));
    desc.setLayoutData(new GridData(GridData.FILL_BOTH));

    return top;
  }

  public String getEvalDesc() {
    return TextMarkerAddonsPlugin.getCasEvaluatorFactoryById(
            getPreferenceStore().getString(TestingPreferenceConstants.EVALUATOR_FACTORY))
            .getDescription();
  }

  protected void loadDefaults() {
    sync.loadDefault();
    oldResults.loadDefault();
    evaluators.loadDefault();
    super.performDefaults();
  }

  @Override
  public boolean performOk() {
    sync.store();
    oldResults.store();
    evaluators.store();
    return super.performOk();
  }
}
