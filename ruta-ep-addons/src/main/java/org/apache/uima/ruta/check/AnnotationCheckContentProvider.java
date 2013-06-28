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

package org.apache.uima.ruta.check;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class AnnotationCheckContentProvider  implements ITreeContentProvider {

  public AnnotationCheckContentProvider() {
    super();
  }

  public void dispose() {

  }

  public void inputChanged(Viewer arg0, Object arg1, Object arg2) {

  }

  public Object[] getChildren(Object node) {
    if (node instanceof IAnnotationCheckTreeNode) {
      List<Object> result = new ArrayList<Object>();
      IAnnotationCheckTreeNode n = (IAnnotationCheckTreeNode) node;
      AnnotationCheckTreeNode[] children = n.getChildren();
      for (AnnotationCheckTreeNode each : children) {
        if (!(each instanceof AnnotationCheckRootNode)) {
          result.add(each);
        }
      }
      return result.toArray();
    }
    return null;
  }

  public Object[] getElements(Object node) {
    return getChildren(node);
  }

  public Object getParent(Object node) {
    if(node instanceof IAnnotationCheckTreeNode) {
      IAnnotationCheckTreeNode n = (IAnnotationCheckTreeNode) node;
      return n.getParent();
    }
    return null;
  }

  public boolean hasChildren(Object node) {
    if(node instanceof IAnnotationCheckTreeNode) {
      IAnnotationCheckTreeNode n = (IAnnotationCheckTreeNode) node;
      return n.hasChildren();
    }
    return false;
  }


}
