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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class AbstractTreeNode implements ITreeNode {

  private ITreeNode parent;

  private ArrayList<ITreeNode> children;

  public AbstractTreeNode() {
    this(null);
  }

  public AbstractTreeNode(ITreeNode parent) {
    this.parent = parent;
    children = new ArrayList<ITreeNode>();
  }

  public void addChild(ITreeNode child) {
    children.add(child);
  }

  public ITreeNode[] getChildren() {
    return children.toArray(new ITreeNode[] {});
  }

  public Iterator<ITreeNode> getChildrenIterator() {
    return children.iterator();
  }

  public ITreeNode getParent() {
    return parent;
  }

  public boolean hasChildren() {
    return children.size() > 0;
  }

  public void getNodes(LinkedList<ITreeNode> list) {
    Iterator<ITreeNode> iter = getChildrenIterator();

    while (iter.hasNext()) {
      ITreeNode node = iter.next();

      list.add(node);

      node.getNodes(list);
    }
  }

  public void sort(Comparator<ITreeNode> cp) {
    Collections.sort(children, cp);
  }
}
