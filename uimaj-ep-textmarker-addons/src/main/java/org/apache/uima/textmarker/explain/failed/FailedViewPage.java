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

package org.apache.uima.textmarker.explain.failed;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.caseditor.editor.AnnotationEditor;
import org.apache.uima.caseditor.editor.ICasDocument;
import org.apache.uima.textmarker.addons.TextMarkerAddonsPlugin;
import org.apache.uima.textmarker.explain.ExplainConstants;
import org.apache.uima.textmarker.explain.apply.ApplyView;
import org.apache.uima.textmarker.explain.rulelist.RuleListView;
import org.apache.uima.textmarker.explain.selection.ExplainSelectionView;
import org.apache.uima.textmarker.explain.tree.BlockApplyNode;
import org.apache.uima.textmarker.explain.tree.FailedRootNode;
import org.apache.uima.textmarker.explain.tree.IExplainTreeNode;
import org.apache.uima.textmarker.explain.tree.RuleApplyNode;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.Page;

public class FailedViewPage extends Page implements ISelectionListener {

  private IExplainTreeNode node;

  private CheckboxTreeViewer treeView;

  private int current;

  private Map<String, Image> images;

  private AnnotationEditor editor;

  private ICasDocument document;

  public FailedViewPage(AnnotationEditor editor) {
    super();
    this.editor = editor;
    this.document = editor.getDocument();
  }

  @Override
  public void dispose() {
    super.dispose();
    getSite().getPage().removeSelectionListener(this);
    if (images != null) {
      for (Image each : images.values()) {
        each.dispose();
      }
    }
  }

  private void initImages() {
    images = new HashMap<String, Image>();
    ImageDescriptor desc;
    Image image;
    String name;

    desc = TextMarkerAddonsPlugin.getImageDescriptor("/icons/accept.png");
    image = desc.createImage();
    name = "matched";
    images.put(name, image);

    desc = TextMarkerAddonsPlugin.getImageDescriptor("/icons/cancel.png");
    image = desc.createImage();
    name = "failed";
    images.put(name, image);

    desc = TextMarkerAddonsPlugin.getImageDescriptor("/icons/font_add.png");
    image = desc.createImage();
    name = ExplainConstants.MATCHED_RULE_MATCH_TYPE;
    images.put(name, image);

    desc = TextMarkerAddonsPlugin.getImageDescriptor("/icons/font_delete.png");
    image = desc.createImage();
    name = ExplainConstants.FAILED_RULE_MATCH_TYPE;
    images.put(name, image);
  }

  public Image getImage(String name) {
    if (images == null) {
      initImages();
    }
    return images.get(name);
  }

  @Override
  public void createControl(Composite parent) {
    treeView = new CheckboxTreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);
    treeView.setContentProvider(new FailedTreeContentProvider());
    treeView.setLabelProvider(new FailedTreeLabelProvider(this));
    treeView.setInput(node);
    getSite().setSelectionProvider(treeView);
    getSite().getPage().addSelectionListener(this);
  }

  @Override
  public Control getControl() {
    return treeView.getControl();
  }

  @Override
  public void setFocus() {
    treeView.getControl().setFocus();
  }

  public void inputChange(Object newInput) {
    if (newInput != null && newInput instanceof FailedRootNode && treeView != null
            && newInput != treeView.getInput()) {
      this.treeView.setInput(newInput);
      this.treeView.refresh();
    }
  }

  public void selectionChanged(IWorkbenchPart part, ISelection selection) {
    if (selection instanceof TreeSelection
            && (part instanceof ApplyView || part instanceof RuleListView || part instanceof ExplainSelectionView)) {
      TreeSelection ts = (TreeSelection) selection;
      Object firstElement = ts.getFirstElement();

      if (firstElement instanceof BlockApplyNode) {
        BlockApplyNode block = (BlockApplyNode) firstElement;
        inputChange(block.getBlockRuleNode().getFailedNode());
      } else if (firstElement instanceof RuleApplyNode) {
        RuleApplyNode rule = (RuleApplyNode) firstElement;
        inputChange(rule.getFailedNode());
      }

    }
  }
}
