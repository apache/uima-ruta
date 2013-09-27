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

package org.apache.uima.ruta.cde.ui;

import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class CDEPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

  private IntegerFieldEditor editor1;

  private IntegerFieldEditor editor2;

  public CDEPreferencePage() {
    IPreferenceStore store = RutaAddonsPlugin.getDefault().getPreferenceStore();
    setPreferenceStore(store);
  }

  public CDEPreferencePage(String title) {
    super(title);
    // TODO Auto-generated constructor stub
  }

  public CDEPreferencePage(String title, ImageDescriptor image) {
    super(title, image);
    // TODO Auto-generated constructor stub
  }

  public void init(IWorkbench arg0) {

  }

  @Override
  protected Control createContents(Composite parent) {
    Composite top = new Composite(parent, SWT.LEFT);
    top.setLayout(new GridLayout());
    top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    editor1 = new IntegerFieldEditor(CDEPreferenceConstants.GOOD_RESULT_THRESHOLD,
            "Good result threshold", top);
    editor1.setPage(this);
    editor1.setPreferenceStore(getPreferenceStore());
    editor1.load();

    editor2 = new IntegerFieldEditor(CDEPreferenceConstants.AVERAGE_RESULT_THRESHOLD,
            "Average result threshold", top);
    editor2.setPage(this);
    editor2.setPreferenceStore(getPreferenceStore());
    editor2.load();

    return top;
  }

  public boolean performOk() {
    editor1.store();
    editor2.store();
    return super.performOk();
  }

}
