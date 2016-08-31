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

package org.apache.uima.ruta.caseditor.view.html;

import org.apache.uima.ruta.caseditor.RutaCasEditorPlugin;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class HtmlViewPreferencePage extends FieldEditorPreferencePage implements
        IWorkbenchPreferencePage {
  
  private StringFieldEditor htmlSource;

  public HtmlViewPreferencePage() {
    setPreferenceStore(RutaCasEditorPlugin.getDefault().getPreferenceStore());
    setDescription("Html View Preferences.");
  }

  @Override
  protected void createFieldEditors() {
    htmlSource = new StringFieldEditor(HtmlView.HTML_SOURCE,
            "Html source: ", getFieldEditorParent());
    addField(htmlSource);

  }

  public void init(IWorkbench workbench) {
  }

}
