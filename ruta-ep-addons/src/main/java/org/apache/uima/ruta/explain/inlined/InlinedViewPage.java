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

package org.apache.uima.ruta.explain.inlined;

import org.apache.uima.caseditor.editor.AnnotationEditor;
import org.apache.uima.ruta.explain.apply.ApplyView;
import org.apache.uima.ruta.explain.apply.ApplyViewPage;
import org.apache.uima.ruta.explain.element.ElementView;
import org.apache.uima.ruta.explain.failed.FailedView;
import org.apache.uima.ruta.explain.matched.MatchedView;
import org.apache.uima.ruta.explain.rulelist.RuleListView;
import org.apache.uima.ruta.explain.selection.ExplainSelectionView;
import org.apache.uima.ruta.explain.tree.ExplainAbstractTreeNode;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

public class InlinedViewPage extends ApplyViewPage implements ISelectionListener {

  public InlinedViewPage(AnnotationEditor editor) {
    super(editor);
  }

  @Override
  public void createControl(Composite parent) {
    viewer = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);
    viewer.setContentProvider(new InlinedTreeContentProvider());
    viewer.setLabelProvider(new InlinedTreeLabelProvider(this));

    document.addChangeListener(this);

    viewer.setAutoExpandLevel(2);
    viewer.addDoubleClickListener(this);
    getSite().setSelectionProvider(viewer);
    getSite().getPage().addSelectionListener(this);
  }

  public void inputChange(Object newInput) {
    if (newInput == null || viewer == null || newInput == viewer.getInput()) {
      return;
    }
    this.viewer.setInput(newInput);
    this.viewer.refresh();
  }

  @Override
  public void selectionChanged(IWorkbenchPart part, ISelection selection) {
    if (selection instanceof TreeSelection
            && (part instanceof ApplyView || part instanceof RuleListView
                    || part instanceof ExplainSelectionView || part instanceof MatchedView
                    || part instanceof FailedView || part instanceof ElementView)) {
      TreeSelection ts = (TreeSelection) selection;
      Object firstElement = ts.getFirstElement();

      if (firstElement instanceof ExplainAbstractTreeNode) {
        inputChange(((ExplainAbstractTreeNode) firstElement).getInlined());
      }
    }
  }

}
