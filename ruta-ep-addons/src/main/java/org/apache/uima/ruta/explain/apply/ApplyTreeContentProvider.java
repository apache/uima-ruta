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

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.textmarker.explain.tree.ExplainRootNode;
import org.apache.uima.textmarker.explain.tree.IExplainTreeNode;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ApplyTreeContentProvider implements ITreeContentProvider {

  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof IExplainTreeNode) {
      List<Object> result = new ArrayList<Object>();
      IExplainTreeNode debugNode = (IExplainTreeNode) parentElement;
      for (IExplainTreeNode each : debugNode.getChildren()) {
        if (!(each instanceof ExplainRootNode)) {
          result.add(each);
        }
      }
      return result.toArray();
    }
    return null;
  }

  public Object getParent(Object element) {
    if (element instanceof IExplainTreeNode) {
      return ((IExplainTreeNode) element).getParent();
    }
    return null;
  }

  public Object[] getElements(Object parentElement) {
    return getChildren(parentElement);
  }

  public boolean hasChildren(Object parentElement) {
    if (parentElement instanceof IExplainTreeNode) {
      IExplainTreeNode debugNode = (IExplainTreeNode) parentElement;
      for (Object each : debugNode.getChildren()) {
        if (!(each instanceof ExplainRootNode)) {
          return true;
        }
      }
    }
    return false;
  }

  public void dispose() {
  }

  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
  }

}
