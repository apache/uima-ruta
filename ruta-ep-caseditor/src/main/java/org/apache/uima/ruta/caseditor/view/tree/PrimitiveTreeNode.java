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

import java.util.Comparator;

import org.apache.uima.cas.Type;

public class PrimitiveTreeNode implements ITreeNode {

  private static final ITreeNode[] EMPTY_NODES = new ITreeNode[0];

  private ITreeNode parent;

  private String value;

  public PrimitiveTreeNode(ITreeNode parent, String value) {
    this.parent = parent;
    this.value = value;
  }

  @Override
  public void addChild(ITreeNode child) {
    // nothing to do
  }

  @Override
  public ITreeNode[] getChildren() {
    return EMPTY_NODES;
  }

  @Override
  public String getName() {
    return value;
  }

  @Override
  public ITreeNode getParent() {
    return parent;
  }

  @Override
  public Type getType() {
    return null;
  }

  @Override
  public boolean hasChildren() {
    return false;
  }

  @Override
  public void sort(Comparator<ITreeNode> cp) {
    // nothing to do
  }

  @Override
  @SuppressWarnings("unchecked")
  public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {

    if (PrimitiveTreeNode.class.equals(adapter)) {
      return this;
    }

    return null;
  }

}
