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

package org.apache.uima.textmarker.explain.apply;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.caseditor.editor.AnnotationEditor;
import org.apache.uima.caseditor.editor.ICasDocument;
import org.apache.uima.textmarker.addons.TextMarkerAddonsPlugin;
import org.apache.uima.textmarker.explain.ExplainConstants;
import org.apache.uima.textmarker.explain.tree.ExplainTree;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.Page;

public class ApplyViewPage extends Page implements ISelectionListener {

  protected TreeViewer viewer;

  protected int current = 0;

  protected Map<String, Image> images;

  protected AnnotationEditor editor;

  protected ICasDocument document;

  public ApplyViewPage(AnnotationEditor editor) {
    super();
    this.editor = editor;
    this.document = editor.getDocument();
  }

  private void initImages() {
    images = new HashMap<String, Image>();
    ImageDescriptor desc;
    Image image;
    String name;

    desc = TextMarkerAddonsPlugin.getImageDescriptor("/icons/arrow_refresh.png");
    image = desc.createImage();
    name = ExplainConstants.BLOCK_APPLY_TYPE;
    images.put(name, image);

    desc = TextMarkerAddonsPlugin.getImageDescriptor("/icons/arrow_right.png");
    image = desc.createImage();
    name = ExplainConstants.RULE_APPLY_TYPE;
    images.put(name, image);

    desc = TextMarkerAddonsPlugin.getImageDescriptor("/icons/arrow_branch.png");
    image = desc.createImage();
    name = ExplainConstants.RULE_APPLY_TYPE + "Delegate";
    images.put(name, image);
  }

  public Image getImage(String name) {
    if (images == null) {
      initImages();
    }
    return images.get(name);
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
    viewer = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);
    viewer.setContentProvider(new ApplyTreeContentProvider());
    viewer.setLabelProvider(new ApplyTreeLabelProvider(this));

    ExplainTree tree = new ExplainTree(document.getCAS());
    viewer.setInput(tree.getRoot());
    getSite().setSelectionProvider(viewer);
    getSite().getPage().addSelectionListener(this);
    viewer.refresh();
  }

  public void doubleClick(DoubleClickEvent event) {

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

  public void mouseDown(final MouseEvent event) {

  }

  public void mouseUp(final MouseEvent event) {

  }

  public void mouseDoubleClick(final MouseEvent event) {

  }

  @Override
  public void selectionChanged(IWorkbenchPart part, ISelection selection) {

  }

}
