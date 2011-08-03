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

package org.apache.uima.tm.cev.views.fsBrowser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.cev.data.CEVData;
import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.data.tree.CEVTypeOrderedRootTreeNode;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVView;
import org.apache.uima.tm.cev.views.CEVAnnotationTreeViewContentProvider;
import org.apache.uima.tm.cev.views.CEVAnnotationTreeViewLableProvider;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.Page;

public class CEVFSBrowserPage extends Page implements ICEVFSBrowserPage, ICEVView {

  private Image icon;

  private CEVDocument casDocument;

  private CEVData casData;

  private CheckboxTreeViewer treeView;

  private CEVAnnotationTreeViewLableProvider lableProvider;

  public CEVFSBrowserPage(CEVViewer casView, CEVDocument casDocument, int index) {
    super();
    this.casDocument = casDocument;

    this.casData = casDocument.getCASData(index);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.uniwue.casviewer.views.CASAnnotationTreeViewPage#createControl(org
   * .eclipse.swt.widgets.Composite)
   */
  @Override
  public void createControl(Composite parent) {
    treeView = new CheckboxTreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

    treeView.setContentProvider(new CEVAnnotationTreeViewContentProvider());
    lableProvider = new CEVAnnotationTreeViewLableProvider(casData);
    treeView.setLabelProvider(lableProvider);

    treeView.addCheckStateListener(casData);
    treeView.setInput(getFSTree());
  }

  private Object getFSTree() {
    CEVTypeOrderedRootTreeNode root = new CEVTypeOrderedRootTreeNode();
    Collection<FeatureStructure> fss = getFSs();
    for (FeatureStructure each : fss) {
      root.insertFS(each);
    }
    root.sort();
    return root;
  }

  private Collection<FeatureStructure> getFSs() {
    Collection<FeatureStructure> result = new ArrayList<FeatureStructure>();
    CAS cas = casData.getCAS();
    TypeSystem typeSystem = cas.getTypeSystem();
    Type annotationBaseType = typeSystem.getType("uima.cas.AnnotationBase");
    List<?> types = typeSystem.getProperlySubsumedTypes(typeSystem.getTopType());
    for (Object object : types) {
      if (object instanceof Type) {
        Type type = (Type) object;
        if (!typeSystem.subsumes(cas.getAnnotationType(), type)
                && !typeSystem.subsumes(annotationBaseType, type)) {
          FSIterator iterator = cas.getIndexRepository().getAllIndexedFS(type);
          while (iterator.isValid()) {
            FeatureStructure fs = iterator.get();
            result.add(fs);
            iterator.moveToNext();
          }
        }
      }
    }
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.views.CEVAnnotationTreeViewPage#casDataChanged(int)
   */
  public void viewChanged(int index) {
    casData = casDocument.getCASData(index);
    reloadTree();
  }

  /**
   * Baum neu laden
   */
  private void reloadTree() {
    treeView.setInput(getFSTree());
    treeView.refresh();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.uniwue.casviewer.views.CASAnnotationTreeViewPage#dispose()
   */
  @Override
  public void dispose() {
    // Icon freigeben
    if (icon != null)
      icon.dispose();

    super.dispose();
  }

  public void newSelection(int offset) {

  }

  @Override
  public Control getControl() {
    return treeView.getControl();
  }

  @Override
  public void setFocus() {
    getControl().setFocus();
  }

  public void casChanged(CEVDocument casDocument) {
    this.casDocument = casDocument;
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
}
