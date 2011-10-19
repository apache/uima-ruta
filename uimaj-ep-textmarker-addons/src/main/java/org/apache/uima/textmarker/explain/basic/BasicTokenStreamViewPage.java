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

package org.apache.uima.textmarker.explain.basic;

import org.apache.uima.caseditor.editor.AnnotationEditor;
import org.apache.uima.caseditor.editor.ICasDocument;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.Page;

public class BasicTokenStreamViewPage extends Page implements IDoubleClickListener {

  private TableViewer tableViewer;

  private int current = 0;

  private BasicTokenStreamTableContentProvider provider;

  private AnnotationEditor editor;

  private ICasDocument document;

  public BasicTokenStreamViewPage(AnnotationEditor editor) {
    super();
    this.editor = editor;
    this.document = editor.getDocument();
  }

  @Override
  public void createControl(Composite parent) {
    tableViewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL
            | SWT.FULL_SELECTION);
    provider = new BasicTokenStreamTableContentProvider(document.getCAS());
    tableViewer.setContentProvider(provider);
    BasicTokenStreamTableLabelProvider columnLabels = new BasicTokenStreamTableLabelProvider();
    columnLabels.createColumns(tableViewer);
    // Set the header to visible
    tableViewer.getTable().setHeaderVisible(true);
    // Set the line of the table visible
    tableViewer.getTable().setLinesVisible(true);
    tableViewer.setInput(getSite());
    tableViewer.addDoubleClickListener(this);
  }

  @Override
  public Control getControl() {
    return tableViewer.getControl();
  }

  @Override
  public void setFocus() {
    tableViewer.getControl().setFocus();
  }

  public TableViewer getViewer() {
    return this.tableViewer;
  }

  public void newSelection(int offset) {
    Object obj = getElementAt(offset);
    StructuredSelection selection = new StructuredSelection(obj);
    tableViewer.setSelection(selection, true);
  }

  private Object getElementAt(int offset) {
    if (provider != null) {
      return provider.getEntryAt(offset);
    }
    return null;
  }

  public void doubleClick(DoubleClickEvent event) {
    if (event.getSelection() != null && event.getSelection() instanceof IStructuredSelection) {
      Object entry = ((IStructuredSelection) event.getSelection()).getFirstElement();
      if (entry instanceof BasicTokenEntry) {
        // TODO
        // casViewer.moveToAnnotation(((BasicTokenEntry) entry).getAnnotation());
      }
    }
  }

}
