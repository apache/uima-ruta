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

package org.apache.uima.tm.cev.preferences;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.preference.ListEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class CEVAnnotationFilterEditor extends ListEditor {

  public CEVAnnotationFilterEditor(String name, String labelText, Composite parent) {
    super(name, labelText, parent);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.ListEditor#createList(java.lang.String[])
   */
  @Override
  protected String createList(String[] items) {
    StringBuffer strBuffer = new StringBuffer();

    for (int i = 0; i < items.length; i++) {
      strBuffer.append(items[i]);
      if (i < items.length - 1)
        strBuffer.append('|');
    }

    return strBuffer.toString();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.ListEditor#getNewInputObject()
   */
  @Override
  protected String getNewInputObject() {
    InputDialog dlg = new InputDialog(Display.getCurrent().getActiveShell(), "Annotation Type",
            "Enter Annotation Type Name", null, null);
    if (dlg.open() == Window.OK) {
      return dlg.getValue();
    }

    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.preference.ListEditor#parseString(java.lang.String)
   */
  @Override
  protected String[] parseString(String stringList) {
    return stringList.split("\\|");
  }

}
