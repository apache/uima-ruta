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

package org.apache.uima.tm.cev.views;

import org.apache.uima.tm.cev.data.tree.ICEVRootTreeNode;
import org.apache.uima.tm.cev.data.tree.ICEVTreeNode;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class CEVAnnotationTreeViewContentProvider implements ITreeContentProvider {

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang. Object)
   */
  public Object[] getChildren(Object element) {
    if (element instanceof ICEVTreeNode)
      return ((ICEVTreeNode) element).getChildren();
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object )
   */
  public Object getParent(Object element) {
    if (element instanceof ICEVTreeNode)
      return ((ICEVTreeNode) element).getParent();

    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang. Object)
   */
  public boolean hasChildren(Object element) {
    return (element instanceof ICEVTreeNode) && ((ICEVTreeNode) element).hasChildren();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java .lang.Object)
   */
  public Object[] getElements(Object inputElement) {
    if (inputElement instanceof ICEVRootTreeNode)
      return ((ICEVRootTreeNode) inputElement).getChildren();

    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.IContentProvider#dispose()
   */
  public void dispose() {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface .viewers.Viewer,
   * java.lang.Object, java.lang.Object)
   */
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
  }
}
