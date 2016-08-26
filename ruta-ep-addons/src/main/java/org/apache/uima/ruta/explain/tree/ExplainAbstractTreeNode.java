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

package org.apache.uima.ruta.explain.tree;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.eclipse.core.runtime.IAdaptable;

public abstract class ExplainAbstractTreeNode implements IExplainTreeNode, IAdaptable {

  private IExplainTreeNode parent;

  private List<IExplainTreeNode> children;

  private FeatureStructure fs;

  private TypeSystem ts;

  public ExplainAbstractTreeNode(IExplainTreeNode parent, FeatureStructure fs, TypeSystem ts) {
    this.parent = parent;
    this.children = new ArrayList<IExplainTreeNode>();
    this.fs = fs;
    this.ts = ts;
  }

  public IExplainTreeNode getParent() {
    return parent;
  }

  public List<IExplainTreeNode> getChildren() {
    return children;
  }

  public boolean hasChildren() {
    if (children.size() > 0) {
      return true;
    }
    return false;
  }

  public void addChild(IExplainTreeNode child) {
    children.add(child);
  }

  public boolean removeChild(IExplainTreeNode child) {
    return children.remove(child);
  }

  public FeatureStructure getFeatureStructure() {
    return fs;
  }

  public TypeSystem getTypeSystem() {
    return ts;
  }

  @Override
  public String toString() {
    return fs.toString();
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Object getAdapter(Class adapter) {

    if (FeatureStructure.class.equals(adapter)) {
      return getFeatureStructure();
    } else if (AnnotationFS.class.equals(adapter) && getFeatureStructure() instanceof AnnotationFS) {
      return getFeatureStructure();
    }

    return null;
  }
}
