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

package org.apache.uima.textmarker.caseditor.view.tree;

import java.util.Collection;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.caseditor.editor.AbstractAnnotationDocumentListener;
import org.apache.uima.caseditor.editor.AnnotationEditor;
import org.apache.uima.caseditor.editor.ICasDocument;
import org.apache.uima.caseditor.editor.ICasEditorInputListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;

public class AnnotationTreeContentProvider extends AbstractAnnotationDocumentListener implements
        ITreeContentProvider, ICasEditorInputListener {

  private ICasDocument document;

  private final AnnotationTreeViewPage page;

  private AnnotationEditor editor;

  public AnnotationTreeContentProvider(AnnotationEditor editor, AnnotationTreeViewPage page) {
    super();
    this.editor = editor;
    this.document = editor.getDocument();
    this.page = page;
    editor.addCasEditorInputListener(this);
  }

  public void dispose() {
    document.removeChangeListener(this);
    editor.removeCasEditorInputListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang. Object)
   */
  public Object[] getChildren(Object element) {
    if (element instanceof ITreeNode)
      return ((ITreeNode) element).getChildren();
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object )
   */
  public Object getParent(Object element) {
    if (element instanceof ITreeNode)
      return ((ITreeNode) element).getParent();

    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang. Object)
   */
  public boolean hasChildren(Object element) {
    return (element instanceof ITreeNode) && ((ITreeNode) element).hasChildren();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java .lang.Object)
   */
  public Object[] getElements(Object inputElement) {
    if (inputElement instanceof IRootTreeNode)
      return ((IRootTreeNode) inputElement).getChildren();

    return null;
  }

  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // changed();
  }

  @Override
  public void changed() {
    Display.getDefault().syncExec(new Runnable() {
      public void run() {
        page.reloadTree();
      }
    });
  }

  @Override
  public void viewChanged(String oldViewName, String newViewName) {
    changed();
  }

  @Override
  protected void addedAnnotation(Collection<AnnotationFS> annotations) {
    changed();
  }

  @Override
  protected void removedAnnotation(Collection<AnnotationFS> annotations) {
    changed();
  }

  @Override
  protected void updatedAnnotation(Collection<AnnotationFS> annotations) {
    changed();
  }


  public void casDocumentChanged(IEditorInput oldInput, ICasDocument oldDocument,
          IEditorInput newInput, ICasDocument newDocument) {
//    document.removeChangeListener(this);
//    document = newDocument;
//    document.addChangeListener(this);
//    changed();
  }

}
