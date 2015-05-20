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
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.uima.cas.Type;
import org.eclipse.core.runtime.IAdaptable;

public interface ITreeNode extends IAdaptable {

  public void addChild(ITreeNode child);

  public ITreeNode[] getChildren();

  public Iterator<ITreeNode> getChildrenIterator();

  public String getName();

  public ITreeNode getParent();

  public boolean hasChildren();

  public Type getType();

  public void getNodes(LinkedList<ITreeNode> list);

  public void sort(Comparator<ITreeNode> cp);
}
