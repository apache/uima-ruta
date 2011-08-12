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
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;

public class CEVFeatureTreeNode implements ICEVTreeNode {

  private ICEVTreeNode parent;

  private Feature f;

  private String value;

  private ArrayList<ICEVTreeNode> children;

  public CEVFeatureTreeNode(ICEVTreeNode parent, Feature f, String value) {
    this.parent = parent;
    this.f = f;
    this.value = value;
    this.children = new ArrayList<ICEVTreeNode>();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.tree.ICEVTreeNode#addChild(org.apache.uima.cev.data
   * .tree.ICEVTreeNode)
   */
  public void addChild(ICEVTreeNode child) {
    this.children.add(child);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.tree.ICEVTreeNode#getChildren()
   */
  public ICEVTreeNode[] getChildren() {
    return this.children.toArray(new ICEVTreeNode[] {});
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.tree.ICEVTreeNode#getChildrenIterator()
   */
  public Iterator<ICEVTreeNode> getChildrenIterator() {
    return this.children.iterator();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.tree.ICEVTreeNode#getName()
   */
  public String getName() {
    return f.getShortName() + " : " + value;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.tree.ICEVTreeNode#getNodes(java.util.LinkedList)
   */
  public void getNodes(LinkedList<ICEVTreeNode> list) {
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
   * @see org.apache.uima.cev.data.tree.ICEVTreeNode#getType()
   */
  public Type getType() {
    return f.getRange();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.tree.ICEVTreeNode#hasChildren()
   */
  public boolean hasChildren() {
    if (children.size() > 0) {
      return true;
    }
    return false;
  }

  public Feature getFeature() {
    return this.f;
  }

  public String getValue() {
    return value;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.tree.ICEVTreeNode#sort(java.util.Comparator)
   */
  public void sort(Comparator<ICEVTreeNode> cp) {
    // nothing to do
  }

}
