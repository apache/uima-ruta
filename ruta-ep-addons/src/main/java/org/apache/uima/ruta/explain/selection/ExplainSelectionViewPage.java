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

package org.apache.uima.textmarker.explain.selection;

import org.apache.uima.caseditor.editor.AnnotationEditor;
import org.apache.uima.textmarker.explain.apply.ApplyTreeContentProvider;
import org.apache.uima.textmarker.explain.apply.ApplyTreeLabelProvider;
import org.apache.uima.textmarker.explain.apply.ApplyViewPage;
import org.apache.uima.textmarker.explain.tree.ApplyRootNode;
import org.apache.uima.textmarker.explain.tree.ExplainTree;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

public class ExplainSelectionViewPage extends ApplyViewPage {

  private int offset = -1;

  public ExplainSelectionViewPage(AnnotationEditor editor) {
    super(editor);
  }

  @Override
  public void createControl(Composite parent) {
    viewer = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);
    viewer.setContentProvider(new ApplyTreeContentProvider());
    viewer.setLabelProvider(new ApplyTreeLabelProvider(this));

    viewer.setInput(new ApplyRootNode(null, document.getCAS().getTypeSystem()));

    getSite().setSelectionProvider(viewer);
    getSite().getPage().addSelectionListener(this);
  }

  @Override
  public void selectionChanged(IWorkbenchPart part, ISelection selection) {
    if (selection instanceof StructuredSelection && part instanceof AnnotationEditor) {
      offset = editor.getCaretOffset();
      if (offset >= 0) {
        ExplainTree tree = new ExplainTree(document.getCAS(), offset);
        viewer.setInput(tree.getRoot());
        viewer.refresh();
      }
    }
  }

}
