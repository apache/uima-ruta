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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import org.apache.uima.ruta.cde.utils.CDEComparatorFactory;
import org.apache.uima.ruta.cde.utils.DocumentData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

public class ResultViewComposite extends Composite implements ISelectionListener {

  private Clipboard clipboard;

  private TableViewer tableViewer;

  private Table table;

  private TableColumn tc1;

  private TableColumn tc2;

  private CDEComparatorFactory comparatorFactory;

  public ResultViewComposite(Composite parent, int style) {
    super(parent, style);
    initGui();
    comparatorFactory = new CDEComparatorFactory();
    clipboard = new Clipboard(parent.getDisplay());
  }

  public void initGui() {
    this.setLayout(new FormLayout());
    tableViewer = new TableViewer(this, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL
            | SWT.FULL_SELECTION);
    tableViewer.setContentProvider(new ResultViewContentProvder());
    tableViewer.setLabelProvider(new ResultViewLabelProvider());
    table = tableViewer.getTable();

    FormData tableFormData = new FormData();
    tableFormData.top = new FormAttachment(0, 5);
    tableFormData.left = new FormAttachment(0, 5);
    tableFormData.bottom = new FormAttachment(100, -5);
    tableFormData.right = new FormAttachment(100, -5);
    table.setLayoutData(tableFormData);

    table.addKeyListener(new KeyListener() {
      public void keyPressed(KeyEvent e) {
        if (((e.stateMask & SWT.CTRL) == SWT.CTRL) && (e.keyCode == 'c')) {
          String output = "";
          TableItem[] items = table.getSelection();
          for (TableItem item : items) {
            String[] data = (String[]) item.getData();
            output = output + data[0] + ", " + data[1] + ", \n";
          }
          clipboard.setContents(new Object[] { output },
                  new Transfer[] { TextTransfer.getInstance() });
        }
      }

      public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
      }
    });

    tc1 = new TableColumn(table, SWT.LEFT);
    tc1.setText("Constraint ");
    tc1.setWidth(160);
    tc1.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        ArrayList<String[]> data = (ArrayList<String[]>) tableViewer.getInput();
        Comparator comparator = comparatorFactory.getComparator(tc1);
        Collections.sort(data, comparator);
        tableViewer.refresh();
      }
    });

    tc2 = new TableColumn(table, SWT.LEFT);
    tc2.setText("Result");
    tc2.setWidth(120);
    tc2.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        ArrayList<String[]> data = (ArrayList<String[]>) tableViewer.getInput();
        Comparator comparator = comparatorFactory.getComparator(tc2);
        Collections.sort(data, comparator);
        tableViewer.refresh();
      }
    });

    tableViewer.refresh();
    table.setHeaderVisible(true);
    table.setLinesVisible(true);

  }

  public void updateInput(Object input) {
    tableViewer.setInput(input);
    tableViewer.refresh();
  }

  public void selectionChanged(IWorkbenchPart part, ISelection selection) {
    if (selection instanceof IStructuredSelection) {
      IStructuredSelection strucSel = (IStructuredSelection) selection;
      Iterator<?> iter = strucSel.iterator();
      if (iter.hasNext()) {
        Object o = iter.next();
        if (o instanceof DocumentData) {
          DocumentData data = (DocumentData) o;
          ArrayList<String[]> results = data.getResults();
          tableViewer.setInput(results);
          tableViewer.refresh();
        }
      }
    }
  }

  public TableViewer getViewer() {
    return tableViewer;
  }

}
