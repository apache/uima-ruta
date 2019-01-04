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

package org.apache.uima.ruta.caseditor.view.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.uima.cas.CAS;

public abstract class AbstractTreeNode implements ITreeNode {

  private static final ITreeNode[] emptyArray = new ITreeNode[0];

  private ITreeNode parent;

  protected List<ITreeNode> children;

  /**
   * not used yet, may be null
   */
  protected final CAS cas;

  public AbstractTreeNode(CAS cas) {
    this(cas, null);
  }

  public AbstractTreeNode(CAS cas, ITreeNode parent) {
    this.parent = parent;
    this.cas = cas;
  }

  @Override
  public void addChild(ITreeNode child) {
    if(children == null) {
      children = new ArrayList<>();
    }
    children.add(child);
  }

  @Override
  public ITreeNode[] getChildren() {
    if(children == null) {
      return emptyArray;
    }
    return children.toArray(emptyArray);
  }
  
  @Override
  public ITreeNode getParent() {
    return parent;
  }

  @Override
  public boolean hasChildren() {
    if(children == null) {
      return false;
    }
    return children.size() > 0;
  }

  @Override
  public void sort(Comparator<ITreeNode> cp) {
    Collections.sort(children, cp);
  }
}
