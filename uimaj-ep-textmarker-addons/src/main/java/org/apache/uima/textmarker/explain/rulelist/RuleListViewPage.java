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

package org.apache.uima.textmarker.explain.rulelist;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.caseditor.editor.AnnotationEditor;
import org.apache.uima.textmarker.explain.ExplainConstants;
import org.apache.uima.textmarker.explain.apply.ApplyTreeContentProvider;
import org.apache.uima.textmarker.explain.apply.ApplyTreeLabelProvider;
import org.apache.uima.textmarker.explain.apply.ApplyViewPage;
import org.apache.uima.textmarker.explain.tree.ApplyRootNode;
import org.apache.uima.textmarker.explain.tree.ExplainTree;
import org.apache.uima.textmarker.explain.tree.IExplainTreeNode;
import org.apache.uima.textmarker.explain.tree.RuleApplyNode;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;

public class RuleListViewPage extends ApplyViewPage implements Listener {

  protected Text filterTextField;

  protected String manualFilter = "";

  private Composite overlay;

  private int offset = -1;

  public RuleListViewPage(AnnotationEditor editor) {
    super(editor);
  }

  @Override
  public void createControl(Composite parent) {
    this.overlay = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.horizontalSpacing = 0;
    layout.verticalSpacing = 0;
    layout.marginWidth = 0;
    layout.marginHeight = 0;
    // FillLayout layout = new FillLayout(SWT.VERTICAL);
    overlay.setLayout(layout);

    filterTextField = new Text(overlay, SWT.SINGLE | SWT.BORDER);
    GridData gd = new GridData();
    gd.grabExcessHorizontalSpace = true;
    gd.horizontalAlignment = GridData.FILL;
    gd.horizontalSpan = 1;
    filterTextField.setLayoutData(gd);
    filterTextField.setToolTipText("Retain types that contain...");
    filterTextField.addListener(SWT.KeyUp, this);
    filterTextField.addListener(SWT.MouseUp, this);
    filterTextField.addListener(SWT.Modify, this);
    filterTextField.setMessage("Only rules with...");

    viewer = new CheckboxTreeViewer(overlay, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
    gd = new GridData(GridData.FILL_BOTH);
    viewer.getTree().setLayoutData(gd);
    viewer.setContentProvider(new ApplyTreeContentProvider());
    viewer.setLabelProvider(new ApplyTreeLabelProvider(this));
    viewer.setInput(new ApplyRootNode(null, document.getCAS().getTypeSystem()));

    getSite().setSelectionProvider(viewer);
    getSite().getPage().addSelectionListener(this);

  }

  private void filterTree(ExplainTree tree) {
    Type ruleType = document.getCAS().getTypeSystem().getType(ExplainConstants.RULE_APPLY_TYPE);
    IExplainTreeNode root = tree.getRoot();
    List<IExplainTreeNode> children = new ArrayList<IExplainTreeNode>(root.getChildren());
    for (IExplainTreeNode each : children) {
      if (each instanceof RuleApplyNode) {
        RuleApplyNode ran = (RuleApplyNode) each;
        Feature f = ruleType.getFeatureByBaseName(ExplainConstants.ELEMENT);
        if (f != null) {
          String v = ran.getFeatureStructure().getStringValue(f);
          if (manualFilter != null && !"".equals(manualFilter) && v.indexOf(manualFilter) == -1) {
            root.removeChild(ran);
          }
        }
      }
    }
  }

  public void handleEvent(Event event) {
    if (event.widget == filterTextField && event.type == SWT.Modify) {
      manualFilter = filterTextField.getText();
      reloadTree();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#dispose()
   */
  @Override
  public void dispose() {
    super.dispose();
    overlay.dispose();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#getControl()
   */
  @Override
  public Control getControl() {
    return overlay;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#setFocus()
   */
  @Override
  public void setFocus() {
    overlay.setFocus();
  }

  @Override
  public void selectionChanged(IWorkbenchPart part, ISelection selection) {
    if (selection instanceof StructuredSelection && part instanceof AnnotationEditor) {
      offset = editor.getCaretOffset();
      if (offset >= 0) {
        reloadTree();
      }
    }
  }

  private void reloadTree() {
    ExplainTree tree = new ExplainTree(document.getCAS(), offset, true);
    filterTree(tree);
    viewer.setInput(tree.getRoot());
    viewer.refresh();
  }
}
