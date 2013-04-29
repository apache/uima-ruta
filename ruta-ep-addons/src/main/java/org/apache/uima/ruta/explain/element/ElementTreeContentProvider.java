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

import org.apache.uima.textmarker.explain.tree.IExplainTreeNode;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;


public class ElementTreeContentProvider implements ITreeContentProvider {

  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof IExplainTreeNode) {
      IExplainTreeNode debugNode = (IExplainTreeNode) parentElement;
      return debugNode.getChildren().toArray();
    }
    return null;
  }

  public Object getParent(Object element) {
    if (element instanceof IExplainTreeNode) {
      return ((IExplainTreeNode) element).getParent();
    }
    return null;

  }

  public Object[] getElements(Object element) {
    return getChildren(element);
  }

  public boolean hasChildren(Object parentElement) {
    if (parentElement instanceof IExplainTreeNode) {
      IExplainTreeNode debugNode = (IExplainTreeNode) parentElement;
      return debugNode.hasChildren();
    }
    return false;
  }

  public void dispose() {
  }

  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
  }

}
