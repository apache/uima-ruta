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
import java.util.LinkedList;
import java.util.List;

import org.apache.uima.ruta.caseditor.view.tree.TypeTreeNode;

public class AnnotationCheckTreeNode implements IAnnotationCheckTreeNode {

  protected List<AnnotationCheckTreeNode> children;

  private IAnnotationCheckTreeNode parent;

  private CheckElement element;

  private LinkedList<TypeTreeNode> typeTreeNodes;

  public AnnotationCheckTreeNode(IAnnotationCheckTreeNode parent, CheckElement element) {
    super();
    this.parent = parent;
    this.children = new ArrayList<AnnotationCheckTreeNode>();
    this.element = element;
    this.typeTreeNodes = new LinkedList<TypeTreeNode>();
  }

  @Override
  public void addChild(AnnotationCheckTreeNode child) {
    children.add(child);
  }

  @Override
  public AnnotationCheckTreeNode[] getChildren() {
    if (children.isEmpty()) {
      return new FeatureCheckTreeNode[0];
    }
    if (children.get(0) instanceof FeatureCheckTreeNode) {
      return children.toArray(new FeatureCheckTreeNode[0]);
    }
    return children.toArray(new AnnotationCheckTreeNode[0]);
  }

  @Override
  public Iterator<AnnotationCheckTreeNode> getChildrenIterator() {
    return children.iterator();
  }

  @Override
  public String getName() {
    return "AbstractAnnotationCheckTreeNode";
  }

  @Override
  public IAnnotationCheckTreeNode getParent() {
    return parent;
  }

  @Override
  public CheckElement getElement() {
    return element;
  }

  @Override
  public boolean hasChildren() {
    return !children.isEmpty();
  }

  public void setChildren(List<AnnotationCheckTreeNode> children) {
    this.children = children;
  }

  public void swapChilds(AnnotationCheckTreeNode child1, AnnotationCheckTreeNode child2) {
    int index1 = children.indexOf(child1);
    int index2 = children.indexOf(child2);
    if (index1 > -1 && index2 > -1) {
      children.set(index1, child2);
      children.set(index2, child1);
    }
  }

  public void addTypeChildNode(TypeTreeNode typeTreeNode) {
    if (!typeTreeNodes.contains(typeTreeNode)) {
      typeTreeNodes.add(typeTreeNode);
    }
  }
}
