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
import java.util.Iterator;
import java.util.List;

public class AnnotationCheckTreeNode implements IAnnotationCheckTreeNode {

  private List<AnnotationCheckTreeNode> children;

  private IAnnotationCheckTreeNode parent;

  private CheckElement element;

  public AnnotationCheckTreeNode(IAnnotationCheckTreeNode parent, CheckElement element) {
    super();
    this.parent = parent;
    this.children = new ArrayList<AnnotationCheckTreeNode>();
    this.element = element;
  }

  public void addChild(AnnotationCheckTreeNode child) {
    children.add(child);
  }

  public AnnotationCheckTreeNode[] getChildren() {
    return children.toArray(new AnnotationCheckTreeNode[0]);
  }

  public Iterator<AnnotationCheckTreeNode> getChildrenIterator() {
    return children.iterator();
  }

  public String getName() {
    return "AbstractAnnotationCheckTreeNode";
  }

  public IAnnotationCheckTreeNode getParent() {
    return parent;
  }

  public boolean hasChildren() {
    return !children.isEmpty();
  }

  public CheckElement getElement() {
    return element;
  }

  public void setChildren(List<AnnotationCheckTreeNode> annotationList) {
    this.children = annotationList;
  }
  
}
