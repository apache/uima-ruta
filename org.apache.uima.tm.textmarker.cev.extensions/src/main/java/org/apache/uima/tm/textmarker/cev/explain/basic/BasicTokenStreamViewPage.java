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

package org.apache.uima.tm.textmarker.cev.explain.basic;

import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.cev.data.CEVData;
import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVView;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.Page;


public class BasicTokenStreamViewPage extends Page implements IBasicTokenStreamViewPage, ICEVView,
        IDoubleClickListener {

  private TableViewer tableViewer;

  private CEVViewer casViewer;

  private CEVDocument casDoc;

  private int current = 0;

  private BasicTokenStreamTableContentProvider provider;

  public BasicTokenStreamViewPage(CEVViewer casViewer, CEVDocument casDoc, int index) {
    this.casDoc = casDoc;
    this.casViewer = casViewer;
    this.current = index;
  }

  @Override
  public void createControl(Composite parent) {
    tableViewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL
            | SWT.FULL_SELECTION);
    provider = new BasicTokenStreamTableContentProvider(getCurrentCEVData());
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

  public CEVData getCurrentCEVData() {
    return casDoc.getCASData(current);
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

  public void viewChanged(int newIndex) {
    getCurrentCEVData().removeAnnotationListener(this);
    current = newIndex;
    getCurrentCEVData().addAnnotationListener(this);
    provider.init(getCurrentCEVData());
    tableViewer.refresh();
  }

  public void annotationsAdded(List<AnnotationFS> annots) {

  }

  public void annotationsRemoved(List<AnnotationFS> annots) {

  }

  public void annotationStateChanged(Type type) {

  }

  public void annotationStateChanged(AnnotationFS annot) {

  }

  public void colorChanged(Type type) {

  }

  public void doubleClick(DoubleClickEvent event) {
    if (event.getSelection() != null && event.getSelection() instanceof IStructuredSelection) {
      Object entry = ((IStructuredSelection) event.getSelection()).getFirstElement();
      if (entry instanceof BasicTokenEntry) {
        casViewer.moveToAnnotation(((BasicTokenEntry) entry).getAnnotation());
      }
    }
  }

  public void casChanged(CEVDocument casDocument) {
    this.casDoc = casDocument;
  }
}
