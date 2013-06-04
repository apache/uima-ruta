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

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.ruta.cde.RutaGEConstraint;
import org.apache.uima.ruta.cde.RutaRuleListConstraint;
import org.apache.uima.ruta.cde.SimpleRutaRuleConstraint;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ConstraintDialog extends Dialog {

  private Text ruleText;

  private Text descriptionText;

  private String rule;

  private String description;

  public ConstraintDialog(Shell parent) {
    super(parent);
  }

  public void create() {
    super.create();
  }

  public void create(SimpleRutaRuleConstraint constraint) {
    super.create();
    rule = constraint.getData();
    description = constraint.getDescription();

    ruleText.setText(rule);
    descriptionText.setText(description);
  }

  public void create(RutaRuleListConstraint constraint) {
    super.create();
    rule = constraint.getData();
    description = constraint.getDescription();

    ruleText.setText(rule);
    descriptionText.setText(description);
  }
  
  public void create (RutaGEConstraint constraint) {
    super.create();
    rule = constraint.getData();
    description = constraint.getDescription();

    ruleText.setText(rule);
    descriptionText.setText(description);
  }

  protected Control createDialogArea(Composite parent) {

    GridLayout layout = new GridLayout();
    layout.numColumns = 3;
    parent.setLayout(layout);

    Label label1 = new Label(parent, SWT.FILL);
    label1.setText("Constraint Rule :");

    GridData labelGridData = new GridData();
    labelGridData.grabExcessVerticalSpace = true;
    labelGridData.horizontalAlignment = GridData.FILL;
    labelGridData.verticalAlignment = GridData.FILL;
    labelGridData.horizontalSpan = 3;
    label1.setLayoutData(labelGridData);

    GridData TextGridData = new GridData();
    TextGridData.grabExcessHorizontalSpace = true;
    TextGridData.grabExcessVerticalSpace = true;
    TextGridData.horizontalAlignment = GridData.FILL;
    TextGridData.verticalAlignment = GridData.FILL;
    TextGridData.horizontalSpan = 3;
    TextGridData.minimumHeight = 100;
    TextGridData.minimumWidth = 400;
    ruleText = new Text(parent, SWT.MULTI | SWT.BORDER| SWT.V_SCROLL | SWT.H_SCROLL);
    ruleText.setLayoutData(TextGridData);

    Label label2 = new Label(parent, SWT.FILL);
    label2.setText("Constraint Description :");

    label2.setLayoutData(labelGridData);

    descriptionText = new Text(parent, SWT.SINGLE | SWT.BORDER);
    descriptionText.setLayoutData(TextGridData);
    return parent;
  }

  private boolean isValidInput() {
   return !StringUtils.isBlank(ruleText.getText());
  }

  protected boolean isResizable() {
    return true;
  }

  private void saveInput() {
    this.rule = ruleText.getText();
    this.description = descriptionText.getText();
  }

  @Override
  protected void okPressed() {
    saveInput();
    super.okPressed();
  }

  public String getRule() {
    return rule;
  }

  public String getDescription() {
    return description;
  }

  protected void createButtonsForButtonBar(Composite parent) {
    GridData gridData = new GridData();
    gridData.verticalAlignment = GridData.FILL;
    gridData.horizontalSpan = 3;
    gridData.grabExcessHorizontalSpace = true;
    gridData.grabExcessVerticalSpace = true;
    gridData.horizontalAlignment = SWT.CENTER;

    parent.setLayoutData(gridData);
    createButton(parent, OK, "Ok", true);
    Button cancelButton = createButton(parent, CANCEL, "Cancel", false);
    cancelButton.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        setReturnCode(CANCEL);
        close();
      }
    });
  }

  protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
    // increment the number of columns in the button bar
    ((GridLayout) parent.getLayout()).numColumns++;
    Button button = new Button(parent, SWT.PUSH);
    button.setText(label);
    button.setFont(JFaceResources.getDialogFont());
    button.setData(new Integer(id));
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        if (isValidInput()) {
          okPressed();
        }
      }
    });
    if (defaultButton) {
      Shell shell = parent.getShell();
      if (shell != null) {
        shell.setDefaultButton(button);
      }
    }
    setButtonLayoutData(button);
    return button;
  }

}
