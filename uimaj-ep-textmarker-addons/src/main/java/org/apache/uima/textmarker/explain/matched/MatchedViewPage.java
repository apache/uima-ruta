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

package org.apache.uima.textmarker.explain.matched;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cev.data.CEVData;
import org.apache.uima.cev.data.CEVDocument;
import org.apache.uima.cev.data.tree.ICEVAnnotationNode;
import org.apache.uima.cev.editor.CEVViewer;
import org.apache.uima.cev.extension.ICEVView;
import org.apache.uima.textmarker.addons.TextMarkerAddonsPlugin;
import org.apache.uima.textmarker.explain.ExplainConstants;
import org.apache.uima.textmarker.explain.element.ElementViewPage;
import org.apache.uima.textmarker.explain.element.IElementViewPage;
import org.apache.uima.textmarker.explain.tree.IExplainTreeNode;
import org.apache.uima.textmarker.explain.tree.MatchedRootNode;
import org.apache.uima.textmarker.explain.tree.RuleMatchNode;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.Page;

public class MatchedViewPage extends Page implements IMatchedViewPage, ICEVView,
        IDoubleClickListener, ISelectionChangedListener {

  private IExplainTreeNode node;

  // private CASViewer casViewer;
  private CheckboxTreeViewer treeView;

  // private Tree tree;
  private CEVDocument casDoc;

  private int current;

  private CEVViewer casViewer;

  private Map<String, Image> images;

  public MatchedViewPage(CEVViewer casViewer, CEVDocument casDoc, int index) {
    super();
    this.casViewer = casViewer;
    this.casDoc = casDoc;
    this.current = index;
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
    treeView.setContentProvider(new MatchedTreeContentProvider());
    treeView.setLabelProvider(new MatchedTreeLabelProvider(this));
    treeView.setInput(node);
    treeView.addCheckStateListener(getCurrentCEVData());
    treeView.addSelectionChangedListener(this);
    treeView.addDoubleClickListener(this);
  }

  public CEVData getCurrentCEVData() {
    return casDoc.getCASData(current);
  }

  public void doubleClick(DoubleClickEvent event) {
    if (event.getSelection() != null && event.getSelection() instanceof ITreeSelection) {
      Object treeNode = ((ITreeSelection) event.getSelection()).getFirstElement();
      if (treeNode instanceof ICEVAnnotationNode) {
        casViewer.moveToAnnotation(((ICEVAnnotationNode) treeNode).getAnnotation());
      }
    }
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
    if (newInput != null && newInput instanceof MatchedRootNode && treeView != null) {
      this.treeView.setInput(newInput);
      this.treeView.refresh();
    }
    Object elementPage = casViewer.getAdapter(IElementViewPage.class);
    if (elementPage instanceof ElementViewPage) {
      ((ElementViewPage) elementPage).inputChange(null);
    }
  }

  public void viewChanged(int newIndex) {
    getCurrentCEVData().removeAnnotationListener(this);
    current = newIndex;
    getCurrentCEVData().addAnnotationListener(this);
    inputChange(null);
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
      if (firstElement instanceof RuleMatchNode) {
        RuleMatchNode node = (RuleMatchNode) firstElement;
        Object elementPage = casViewer.getAdapter(IElementViewPage.class);
        if (elementPage instanceof ElementViewPage && node.hasChildren()) {
          ((ElementViewPage) elementPage).inputChange(node.getChildren().get(0));
        }
      }
    }
  }

  public void casChanged(CEVDocument casDocument) {
    this.casDoc = casDocument;
  }

}
