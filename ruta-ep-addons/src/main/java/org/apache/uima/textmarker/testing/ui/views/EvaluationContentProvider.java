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

package org.apache.uima.textmarker.testing.ui.views;

import org.apache.uima.textmarker.caseditor.view.tree.AnnotationTreeNode;
import org.apache.uima.textmarker.caseditor.view.tree.ITreeNode;
import org.apache.uima.textmarker.caseditor.view.tree.TypeTreeNode;
import org.apache.uima.textmarker.testing.ui.views.tree.TestEvaluationTree;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class EvaluationContentProvider implements ITreeContentProvider {

  private Object[] empty = new Object[] {};

  private String type;

  public EvaluationContentProvider(String type) {
    super();
    this.type = type;
  }

  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof TestEvaluationTree) {
      TypeTreeNode root = (TypeTreeNode) ((TestEvaluationTree) parentElement).getRoot();
      if (root == null) {
        return empty;
      }
      for (ITreeNode node : root.getChildren()) {
        if (node instanceof TypeTreeNode) {
          if (((TypeTreeNode) node).getType().getName().equals(type)) {
            return node.getChildren();
          }
        }
      }
    }
    if (parentElement instanceof TypeTreeNode) {
      TypeTreeNode node = (TypeTreeNode) parentElement;
      return node.getChildren();
    }
    if (parentElement instanceof AnnotationTreeNode) {
      AnnotationTreeNode node = (AnnotationTreeNode) parentElement;
      return node.getChildren();
    }
    return empty;
  }

  public Object getParent(Object element) {
    if (element instanceof ITreeNode) {
      return ((ITreeNode) element).getParent();
    }
    return null;
  }

  public boolean hasChildren(Object element) {
    if (element instanceof TestEvaluationTree) {
      TypeTreeNode root = (TypeTreeNode) ((TestEvaluationTree) element).getRoot();
      return root.hasChildren();

    }
    if (element instanceof ITreeNode) {
      return ((ITreeNode) element).hasChildren();
    }
    return false;
  }

  public Object[] getElements(Object inputElement) {
    return getChildren(inputElement);
  }

  public void dispose() {

  }

  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

  }

}
