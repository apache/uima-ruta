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
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;

public class FeatureTreeNode implements ITreeNode {

  private ITreeNode parent;

  private Feature f;

  private String value;

  private ArrayList<ITreeNode> children;

  public FeatureTreeNode(ITreeNode parent, Feature f, String value) {
    this.parent = parent;
    this.f = f;
    this.value = value;
    this.children = new ArrayList<ITreeNode>();
  }

  public void addChild(ITreeNode child) {
    this.children.add(child);
  }

  public ITreeNode[] getChildren() {
    return this.children.toArray(new ITreeNode[] {});
  }

  public Iterator<ITreeNode> getChildrenIterator() {
    return this.children.iterator();
  }

  public String getName() {
    return f.getShortName() + " : " + value;
  }

  public void getNodes(LinkedList<ITreeNode> list) {
  }

  public ITreeNode getParent() {
    return parent;
  }

  public Type getType() {
    return f.getRange();
  }

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

  public void sort(Comparator<ITreeNode> cp) {
    // nothing to do
  }

  public Object getAdapter(Class adapter) {

    if (FeatureTreeNode.class.equals(adapter)) {
      return this;
    } else if (Feature.class.equals(adapter)) {
      return f;

    }

    return null;
  }

}
