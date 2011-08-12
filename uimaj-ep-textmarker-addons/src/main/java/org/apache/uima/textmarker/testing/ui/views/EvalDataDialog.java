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

package org.apache.uima.textmarker.testing.ui.views;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class EvalDataDialog extends ApplicationWindow {
  
  Composite overlay;
  String data;
  public EvalDataDialog(Shell parentShell, String data) {
    super(parentShell);
    this.data=data;
    // TODO Auto-generated constructor stub
  }
  
  protected Control createContents (Composite parent) {
    GridLayout layout = new GridLayout();
    parent.setLayout(layout);
    parent.setLayoutData(new GridData(GridData.FILL_BOTH));
    
    
    
    Text text = new Text (parent, SWT.READ_ONLY | SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
    text.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
    text.setText("No data aviable");
    text.setLayoutData(new GridData(GridData.FILL_BOTH));
    
    if (text != null) {
      text.setText(data);
    }
    
    return parent;
    
    
  }

}
