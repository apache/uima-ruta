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

package org.apache.uima.tm.textmarker.cev.explain.element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.cev.data.CEVData;
import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.data.tree.ICEVAnnotationNode;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVView;
import org.apache.uima.tm.textmarker.cev.TextMarkerCEVPlugin;
import org.apache.uima.tm.textmarker.cev.explain.tree.IExplainTreeNode;
import org.apache.uima.tm.textmarker.cev.explain.tree.RuleElementRootNode;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.Page;


public class ElementViewPage extends Page implements IElementViewPage, ICEVView,
        IDoubleClickListener {

  private IExplainTreeNode node;

  // private CheckboxTreeViewer treeView;
  private TreeViewer treeView;

  private CEVDocument casDoc;

  private int current;

  private CEVViewer casViewer;

  private Map<String, Image> images;

  public ElementViewPage(CEVViewer casViewer, CEVDocument casDoc, int index) {
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

    desc = TextMarkerCEVPlugin.getImageDescriptor("/icons/chart_organisation_add.png");
    image = desc.createImage();
    name = TextMarkerCEVPlugin.RULE_ELEMENT_MATCHES_TYPE + "true";
    images.put(name, image);

    desc = TextMarkerCEVPlugin.getImageDescriptor("/icons/chart_organisation_delete.png");
    image = desc.createImage();
    name = TextMarkerCEVPlugin.RULE_ELEMENT_MATCHES_TYPE + "false";
    images.put(name, image);

    desc = TextMarkerCEVPlugin.getImageDescriptor("/icons/chart_organisation_delete.png");
    image = desc.createImage();
    name = "element";
    images.put(name, image);

    desc = TextMarkerCEVPlugin.getImageDescriptor("/icons/font_add.png");
    image = desc.createImage();
    name = TextMarkerCEVPlugin.RULE_ELEMENT_MATCH_TYPE + "true";
    images.put(name, image);

    desc = TextMarkerCEVPlugin.getImageDescriptor("/icons/font_delete.png");
    image = desc.createImage();
    name = TextMarkerCEVPlugin.RULE_ELEMENT_MATCH_TYPE + "false";
    images.put(name, image);

    desc = TextMarkerCEVPlugin.getImageDescriptor("/icons/accept.png");
    image = desc.createImage();
    name = TextMarkerCEVPlugin.EVAL_CONDITION_TYPE + "true";
    images.put(name, image);

    desc = TextMarkerCEVPlugin.getImageDescriptor("/icons/cancel.png");
    image = desc.createImage();
    name = TextMarkerCEVPlugin.EVAL_CONDITION_TYPE + "false";
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
    treeView.addDoubleClickListener(this);
  }

  public void doubleClick(DoubleClickEvent event) {
    if (event.getSelection() != null && event.getSelection() instanceof ITreeSelection) {
      Object treeNode = ((ITreeSelection) event.getSelection()).getFirstElement();
      if (treeNode instanceof ICEVAnnotationNode) {
        casViewer.moveToAnnotation(((ICEVAnnotationNode) treeNode).getAnnotation());
      }
    }
  }

  public CEVData getCurrentCEVData() {
    return casDoc.getCASData(current);
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

  public void casChanged(CEVDocument casDocument) {
    this.casDoc = casDocument;
  }
}
