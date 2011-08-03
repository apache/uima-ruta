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

package org.apache.uima.tm.textmarker.testing.ui.views.fn;

import java.util.List;
import java.util.Map;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.cev.data.CEVData;
import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVView;
import org.apache.uima.tm.cev.views.CEVAnnotationTreeViewDragListener;
import org.apache.uima.tm.textmarker.testing.ui.views.tree.TestEvaluationTree;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.Page;


public class FalseNegativeViewPage extends Page implements IFalseNegativeViewPage,
        IDoubleClickListener, ICEVView, ISelectionChangedListener {

  private CEVViewer casViewer;

  private CEVDocument casDoc;

  private CheckboxTreeViewer viewer;

  int current = 0;

  private Map<String, Image> images;

  public FalseNegativeViewPage(CEVViewer casViewer, CEVDocument casDoc, int index) {
    super();
    this.casViewer = casViewer;
    this.casDoc = casDoc;
    this.current = index;
  }

  @Override
  public Control getControl() {
    return viewer.getControl();
  }

  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
  }

  @Override
  public void init(IPageSite pageSite) {
    super.init(pageSite);
  }

  public TreeViewer getTreeViewer() {
    return viewer;
  }

  public CEVData getCurrentCEVData() {
    return casDoc.getCASData(current);
  }

  @Override
  public void createControl(Composite parent) {
    viewer = new CheckboxTreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);
    viewer.setContentProvider(new FalseNegativeContentProvider());
    viewer.setLabelProvider(new FalseNegativeLabelProvider(this));

    viewer.addCheckStateListener(casDoc.getCASData(current));
    viewer.addDoubleClickListener(this);
    viewer.addSelectionChangedListener(this);
    TestEvaluationTree tree = new TestEvaluationTree();
    tree.createTree(getCurrentCEVData().getCAS());

    int ops = DND.DROP_COPY | DND.DROP_MOVE;
    Transfer[] transfers = new Transfer[] { TextTransfer.getInstance() };
    viewer.addDragSupport(ops, transfers, new CEVAnnotationTreeViewDragListener(viewer));

    viewer.setInput(tree);
    viewer.refresh();
  }

  public void doubleClick(DoubleClickEvent event) {

  }

  @Override
  public void dispose() {
    super.dispose();
    if (images != null) {
      for (Image each : images.values()) {
        each.dispose();
      }
    }
  }

  public void mouseDown(final MouseEvent event) {

  }

  public void mouseUp(final MouseEvent event) {

  }

  public void mouseDoubleClick(final MouseEvent event) {

  }

  public void viewChanged(int newIndex) {
    getCurrentCEVData().removeAnnotationListener(this);
    current = newIndex;
    getCurrentCEVData().addAnnotationListener(this);

    TestEvaluationTree tree = new TestEvaluationTree();
    tree.createTree(getCurrentCEVData().getCAS());
    viewer.setInput(tree.getRoot());
    viewer.refresh();

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

  public void newSelection(int offset) {

  }

  public void selectionChanged(SelectionChangedEvent event) {
    ISelection selection = event.getSelection();
    if (selection instanceof ITreeSelection) {
      ITreeSelection struct = (ITreeSelection) selection;
      Object firstElement = struct.getFirstElement();

    }
  }

  public void casChanged(CEVDocument casDocument) {
    this.casDoc = casDocument;
  }
}
