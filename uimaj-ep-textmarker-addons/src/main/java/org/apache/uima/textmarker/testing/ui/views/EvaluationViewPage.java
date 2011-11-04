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

import java.util.Collection;
import java.util.Map;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.caseditor.editor.AnnotationEditor;
import org.apache.uima.caseditor.editor.ICasDocument;
import org.apache.uima.caseditor.editor.ICasDocumentListener;
import org.apache.uima.caseditor.editor.ICasEditorInputListener;
import org.apache.uima.textmarker.caseditor.view.tree.AnnotationTreeViewDragListener;
import org.apache.uima.textmarker.testing.ui.views.tree.TestEvaluationTree;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.Page;

public class EvaluationViewPage extends Page implements ICasDocumentListener, ISelectionListener,
        ICasEditorInputListener {

  private CheckboxTreeViewer viewer;

  int current = 0;

  private Map<String, Image> images;

  private AnnotationEditor editor;

  private ICasDocument document;

  private String type;

  public EvaluationViewPage(String type, AnnotationEditor editor) {
    super();
    this.type = type;
    this.editor = editor;
    this.document = editor.getDocument();
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

  @Override
  public void createControl(Composite parent) {
    viewer = new CheckboxTreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);
    viewer.setContentProvider(new EvaluationContentProvider(type));
    viewer.setLabelProvider(new EvaluationLabelProvider(this));

    int ops = DND.DROP_COPY | DND.DROP_MOVE;
    Transfer[] transfers = new Transfer[] { TextTransfer.getInstance() };
    viewer.addDragSupport(ops, transfers, new AnnotationTreeViewDragListener(viewer));

    getSite().setSelectionProvider(viewer);
    getSite().getPage().addSelectionListener(this);

    document.addChangeListener(this);
    editor.addCasEditorInputListener(this);
    reloadTree();
  }

  private void reloadTree() {
    TestEvaluationTree tree = new TestEvaluationTree();
    tree.createTree(document.getCAS());
    viewer.setInput(tree);
    viewer.refresh();
  }

  @Override
  public void dispose() {
    super.dispose();
    getSite().getPage().removeSelectionListener(this);
    document.removeChangeListener(this);
    editor.removeCasEditorInputListener(this);
    if (images != null) {
      for (Image each : images.values()) {
        each.dispose();
      }
    }
  }

  @Override
  public void selectionChanged(IWorkbenchPart part, ISelection selection) {
    // TODO Auto-generated method stub
  }

  @Override
  public void added(FeatureStructure newFeatureStructure) {

  }

  @Override
  public void added(Collection<FeatureStructure> newFeatureStructure) {

  }

  @Override
  public void removed(FeatureStructure deletedFeatureStructure) {

  }

  @Override
  public void removed(Collection<FeatureStructure> deletedFeatureStructure) {

  }

  @Override
  public void updated(FeatureStructure featureStructure) {

  }

  @Override
  public void updated(Collection<FeatureStructure> featureStructure) {

  }

  @Override
  public void changed() {
    reloadTree();

  }

  @Override
  public void viewChanged(String oldViewName, String newViewName) {
    reloadTree();
  }

  @Override
  public void casDocumentChanged(ICasDocument oldDocument, ICasDocument newDocument) {
    document.removeChangeListener(this);
    document = newDocument;
    document.addChangeListener(this);
    changed();
  }

}
