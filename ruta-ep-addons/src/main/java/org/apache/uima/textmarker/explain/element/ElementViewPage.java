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

package org.apache.uima.textmarker.explain.element;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.caseditor.editor.AnnotationEditor;
import org.apache.uima.caseditor.editor.ICasDocument;
import org.apache.uima.textmarker.addons.TextMarkerAddonsPlugin;
import org.apache.uima.textmarker.explain.ExplainConstants;
import org.apache.uima.textmarker.explain.failed.FailedView;
import org.apache.uima.textmarker.explain.matched.MatchedView;
import org.apache.uima.textmarker.explain.tree.IExplainTreeNode;
import org.apache.uima.textmarker.explain.tree.RuleElementRootNode;
import org.apache.uima.textmarker.explain.tree.RuleMatchNode;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.Page;

public class ElementViewPage extends Page implements ISelectionListener {

  private IExplainTreeNode node;

  private TreeViewer treeView;

  private Map<String, Image> images;

  private AnnotationEditor editor;

  private ICasDocument document;

  public ElementViewPage(AnnotationEditor editor) {
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

    desc = TextMarkerAddonsPlugin.getImageDescriptor("/icons/chart_organisation_add.png");
    image = desc.createImage();
    name = ExplainConstants.RULE_ELEMENT_MATCHES_TYPE + "true";
    images.put(name, image);

    desc = TextMarkerAddonsPlugin.getImageDescriptor("/icons/chart_organisation_delete.png");
    image = desc.createImage();
    name = ExplainConstants.RULE_ELEMENT_MATCHES_TYPE + "false";
    images.put(name, image);

    desc = TextMarkerAddonsPlugin.getImageDescriptor("/icons/chart_organisation_delete.png");
    image = desc.createImage();
    name = "element";
    images.put(name, image);

    desc = TextMarkerAddonsPlugin.getImageDescriptor("/icons/font_add.png");
    image = desc.createImage();
    name = ExplainConstants.RULE_ELEMENT_MATCH_TYPE + "true";
    images.put(name, image);

    desc = TextMarkerAddonsPlugin.getImageDescriptor("/icons/font_delete.png");
    image = desc.createImage();
    name = ExplainConstants.RULE_ELEMENT_MATCH_TYPE + "false";
    images.put(name, image);

    desc = TextMarkerAddonsPlugin.getImageDescriptor("/icons/accept.png");
    image = desc.createImage();
    name = ExplainConstants.EVAL_CONDITION_TYPE + "true";
    images.put(name, image);

    desc = TextMarkerAddonsPlugin.getImageDescriptor("/icons/cancel.png");
    image = desc.createImage();
    name = ExplainConstants.EVAL_CONDITION_TYPE + "false";
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
    // treeView = new CheckboxTreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL
    // | SWT.V_SCROLL);
    treeView = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);
    treeView.setContentProvider(new ElementTreeContentProvider());
    treeView.setLabelProvider(new ElementTreeLabelProvider(this));
    treeView.setInput(node);
    // treeView.addCheckStateListener(getCurrentCEVData());
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
    if (treeView == null) {
      return;
    }
    Object oldInput = treeView.getInput();
    if ((oldInput == null && newInput == null) || (oldInput != null && oldInput.equals(newInput)))
      return;

    if (newInput != null && newInput instanceof RuleElementRootNode) {
      treeView.setInput(newInput);
      treeView.expandAll();
      treeView.refresh();
    } else {
      treeView.setInput(null);
    }
  }

  public void selectionChanged(IWorkbenchPart part, ISelection selection) {
    if (selection instanceof TreeSelection
            && (part instanceof MatchedView || part instanceof FailedView)) {
      TreeSelection ts = (TreeSelection) selection;
      Object firstElement = ts.getFirstElement();

      if (firstElement instanceof RuleMatchNode) {
        RuleMatchNode match = (RuleMatchNode) firstElement;
        if (match.hasChildren()) {
          inputChange(match.getChildren().get(0));
        }
      }

    }
  }

}
