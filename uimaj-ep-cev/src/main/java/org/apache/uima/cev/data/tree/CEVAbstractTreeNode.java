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

package org.apache.uima.cev.data.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class CEVAbstractTreeNode implements ICEVTreeNode {

  private ICEVTreeNode parent;

  private ArrayList<ICEVTreeNode> children;

  public CEVAbstractTreeNode() {
    this(null);
  }

  public CEVAbstractTreeNode(ICEVTreeNode parent) {
    this.parent = parent;
    children = new ArrayList<ICEVTreeNode>();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.tree.ICEVTreeNode#addChild(org.apache.uima.cev.data.tree.
   * ICEVTreeNode)
   */
  public void addChild(ICEVTreeNode child) {
    children.add(child);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.tree.ICEVTreeNode#getChildren()
   */
  public ICEVTreeNode[] getChildren() {
    return children.toArray(new ICEVTreeNode[] {});
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.tree.ICEVTreeNode#getChildrenIterator()
   */
  public Iterator<ICEVTreeNode> getChildrenIterator() {
    return children.iterator();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.tree.ICEVTreeNode#getParent()
   */
  public ICEVTreeNode getParent() {
    return parent;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.tree.ICEVTreeNode#hasChildren()
   */
  public boolean hasChildren() {
    return children.size() > 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.tree.ICEVTreeNode#getNodes(java.util.LinkedList)
   */
  public void getNodes(LinkedList<ICEVTreeNode> list) {
    Iterator<ICEVTreeNode> iter = getChildrenIterator();

    while (iter.hasNext()) {
      ICEVTreeNode node = iter.next();

      list.add(node);

      node.getNodes(list);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.tree.ICEVTreeNode#sort(java.util.Comparator)
   */
  public void sort(Comparator<ICEVTreeNode> cp) {
    Collections.sort(children, cp);
  }
}
