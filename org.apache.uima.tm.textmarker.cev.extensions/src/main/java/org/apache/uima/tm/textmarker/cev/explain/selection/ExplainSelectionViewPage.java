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

package org.apache.uima.tm.textmarker.cev.explain.selection;

import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.textmarker.cev.explain.apply.ApplyTreeContentProvider;
import org.apache.uima.tm.textmarker.cev.explain.apply.ApplyTreeLabelProvider;
import org.apache.uima.tm.textmarker.cev.explain.apply.ApplyViewPage;
import org.apache.uima.tm.textmarker.cev.explain.tree.ApplyRootNode;
import org.apache.uima.tm.textmarker.cev.explain.tree.ExplainTree;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;


public class ExplainSelectionViewPage extends ApplyViewPage implements IExplainSelectionViewPage {

  private int offset = -1;

  public ExplainSelectionViewPage(CEVViewer casViewer, CEVDocument casDoc, int index) {
    super(casViewer, casDoc, index);
  }

  @Override
  public void createControl(Composite parent) {
    viewer = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);
    viewer.setContentProvider(new ApplyTreeContentProvider());
    viewer.setLabelProvider(new ApplyTreeLabelProvider(this));

    viewer.addDoubleClickListener(this);
    viewer.addSelectionChangedListener(this);
    viewer.setInput(new ApplyRootNode(null, getCurrentCEVData().getCAS().getTypeSystem()));
  }

  public void viewChanged(int newIndex) {
    getCurrentCEVData().removeAnnotationListener(this);
    current = newIndex;
    getCurrentCEVData().addAnnotationListener(this);
    newSelection(offset);
  }

  public void newSelection(int offset) {
    this.offset = offset;

    if (offset >= 0) {
      ExplainTree tree = new ExplainTree(getCurrentCEVData(), offset);
      viewer.setInput(tree.getRoot());
      viewer.refresh();
    }
  }

}
