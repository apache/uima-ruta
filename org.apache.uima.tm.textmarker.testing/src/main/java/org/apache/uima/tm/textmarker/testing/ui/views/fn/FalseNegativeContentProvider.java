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

package org.apache.uima.tm.textmarker.testing.ui.views.fn;

import org.apache.uima.tm.cev.data.tree.CEVAnnotationTreeNode;
import org.apache.uima.tm.cev.data.tree.CEVTypeTreeNode;
import org.apache.uima.tm.cev.data.tree.ICEVTreeNode;
import org.apache.uima.tm.textmarker.testing.ui.views.tree.TestEvaluationTree;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;


public class FalseNegativeContentProvider implements ITreeContentProvider {

  private Object[] empty = new Object[] {};

  @Override
  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof TestEvaluationTree) {
      CEVTypeTreeNode root = (CEVTypeTreeNode) ((TestEvaluationTree) parentElement).getRoot();
      if (root == null) {
        return empty;
      }
      for (ICEVTreeNode node : root.getChildren()) {
        if (node instanceof CEVTypeTreeNode) {
          if (((CEVTypeTreeNode) node).getType().getName().equals(
                  "org.apache.uima.tm.textmarker.kernel.type.FalseNegative")) {
            return node.getChildren();
          }
        }
      }
    }
    if (parentElement instanceof CEVTypeTreeNode) {

      CEVTypeTreeNode node = (CEVTypeTreeNode) parentElement;
      return node.getChildren();
    }
    if (parentElement instanceof CEVAnnotationTreeNode) {
      CEVAnnotationTreeNode node = (CEVAnnotationTreeNode) parentElement;
      return node.getChildren();
    }
    return empty;
  }

  @Override
  public Object getParent(Object element) {
    if (element instanceof ICEVTreeNode) {
      return ((ICEVTreeNode) element).getParent();
    }
    return null;
  }

  @Override
  public boolean hasChildren(Object element) {
    if (element instanceof TestEvaluationTree) {
      CEVTypeTreeNode root = (CEVTypeTreeNode) ((TestEvaluationTree) element).getRoot();
      return root.hasChildren();

    }
    if (element instanceof ICEVTreeNode) {
      return ((ICEVTreeNode) element).hasChildren();
    }
    return false;
  }

  @Override
  public Object[] getElements(Object inputElement) {
    return getChildren(inputElement);
  }

  @Override
  public void dispose() {

  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

  }

}
