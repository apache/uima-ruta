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

package org.apache.uima.tm.textruler.ui;

import java.awt.Toolkit;
import java.util.ArrayList;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class SelectTypesDialog extends Dialog implements Listener {

  private Shell shell;

  private Text typeNameUI;

  private Table matchingTypesUI;

  // private Table nameSpacesUI;

  public String typeName = "error-TypeName-never-set";

  private ArrayList<String> types;

  private Button okButton;

  private Button cancelButton;

  private AddRemoveList list;

  public SelectTypesDialog(Shell shell, ArrayList<String> types, AddRemoveList list) {
    super(shell);
    this.shell = shell;
    this.types = types;
    this.list = list;
    createDialogArea();
    init();
  }

  protected Control createDialogArea() {
    Composite mainArea = (Composite) createDialogArea(shell);
    createWideLabel(mainArea, "  Type Name:");

    typeNameUI = newText(mainArea, SWT.SINGLE, "Specify the type name");
    typeNameUI.addListener(SWT.Modify, this);

    createWideLabel(mainArea, "  Matching Types:");

    matchingTypesUI = newTable(mainArea, SWT.MULTI);
    ((GridData) matchingTypesUI.getLayoutData()).heightHint = 250;
    ((GridData) matchingTypesUI.getLayoutData()).minimumHeight = 100;
    typeNameUI.addListener(SWT.Selection, this);

    displayFilteredTypes("");
    createButtonBar(mainArea);
    return mainArea;
  }

  @Override
  protected Control createDialogArea(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
    layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
    layout.verticalSpacing = 3;
    layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
    composite.setLayout(layout);
    composite.setLayoutData(new GridData(GridData.FILL_BOTH));
    applyDialogFont(composite);
    return composite;
  }

  @Override
  protected Control createButtonBar(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.numColumns = 0;
    layout.makeColumnsEqualWidth = true;
    layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
    layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
    layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
    layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
    composite.setLayout(layout);
    GridData data = new GridData(SWT.CENTER, SWT.CENTER, false, false);
    composite.setLayoutData(data);

    createButtonsForButtonBar(composite);
    return composite;
  }

  @Override
  protected void createButtonsForButtonBar(Composite parent) {
    okButton = createButton(parent, IDialogConstants.OK_ID, "OK", true);
    cancelButton = createButton(parent, IDialogConstants.CANCEL_ID, "    Cancel    ", false);
    okButton.setEnabled(false);
  }

  private void init() {

    okButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        TableItem[] types = matchingTypesUI.getSelection();
        ArrayList<String> selection = new ArrayList<String>();
        for (TableItem item : types) {
          selection.add(item.getText());
        }
        list.addAll(selection);
        shell.dispose();
      }
    });

    cancelButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        shell.dispose();
      }
    });

    Display display = Display.getDefault();
    int width = 300;
    int height = 400;
    shell.setSize(width, height);
    shell.setMinimumSize(200, 250);
    shell.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2, (Toolkit
            .getDefaultToolkit().getScreenSize().height - height) / 2);
    shell.setLayout(new FillLayout());
    shell.layout();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
  }

  protected Label createWideLabel(Composite parent, String message) {
    Label label = null;
    label = new Label(parent, SWT.WRAP);
    label.setText(null != message ? message : "");
    GridData data = new GridData(GridData.FILL_HORIZONTAL);
    data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
    data.verticalAlignment = SWT.CENTER;
    label.setLayoutData(data);
    return label;
  }

  protected Text newText(Composite parent, int style, String tip) {
    Text t = new Text(parent, style | SWT.BORDER);
    t.setToolTipText(tip);
    t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    t.addListener(SWT.KeyUp, this);
    t.addListener(SWT.MouseUp, this);
    return t;
  }

  protected Table newTable(Composite parent, int style) {
    Table table = new Table(parent, style | SWT.BORDER);
    GridData gd = new GridData(GridData.FILL_BOTH);
    table.setLayoutData(gd);
    table.addListener(SWT.Selection, this);
    table.addListener(SWT.KeyUp, this);
    return table;
  }

  private void displayFilteredTypes(String aTypeName) {
    matchingTypesUI.setRedraw(false);
    matchingTypesUI.removeAll();
    String topEntry = "";
    aTypeName = aTypeName.toLowerCase();
    for (String type : types) {
      String candidateTypeName = type.toLowerCase();
      if (aTypeName.trim().equals("") || candidateTypeName.indexOf(aTypeName) != -1) {

        if (topEntry.equals("")) {
          topEntry = type;
        }
        TableItem item = new TableItem(matchingTypesUI, SWT.NULL);
        item.setText(type);
      }
    }
    // if (matchingTypesUI.getItemCount() > 0) {
    // matchingTypesUI.select(0);
    // displayNameSpacesForSelectedItem(topEntry);
    // }
    matchingTypesUI.setRedraw(true);
  }

  // private void displayNameSpacesForSelectedItem(String topEntry) {
  // nameSpacesUI.removeAll();
  // TableItem item = new TableItem(nameSpacesUI, SWT.NULL);
  // item.setText(topEntry);
  // if (nameSpacesUI.getItemCount() > 0) {
  // nameSpacesUI.select(0);
  // }
  // nameSpacesUI.setRedraw(true);
  // }

  public void handleEvent(Event event) {
    if (event.widget == typeNameUI && event.type == SWT.Modify) {
      displayFilteredTypes(typeNameUI.getText());
    }

    else if (event.widget == matchingTypesUI && event.type == SWT.Selection) {
      okButton.setEnabled(0 < matchingTypesUI.getSelectionCount());
    }
  }

  public boolean isValid() {
    return true;
  }
}
