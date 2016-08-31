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

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;

public class TypeOrderedRootTreeNode extends AbstractTreeNode implements IRootTreeNode {

  private Map<Type, TypeTreeNode> typeMap = new HashMap<Type, TypeTreeNode>();

  public TypeOrderedRootTreeNode(CAS cas) {
    super(cas);
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public Type getType() {
    return null;
  }

  public TypeTreeNode getTreeNode(Type type, CAS cas) {
    TypeTreeNode typeTreeNode = typeMap.get(type);
    if (typeTreeNode == null) {
      typeTreeNode = new TypeTreeNode(cas, this, type);
      typeMap.put(type, typeTreeNode);
      addChild(typeTreeNode);
    }
    return typeTreeNode;
  }

  @Override
  public void insertFS(FeatureStructure fs, CAS cas, boolean withParents) {
    Type type = fs.getType();
    if (type.getShortName().equals("RutaBasic") || type.getShortName().equals("DebugBlockApply")
            || type.getShortName().equals("DebugMatchedRuleMatch")
            || type.getShortName().equals("DebugFailedRuleMatch")
            || type.getShortName().equals("DebugScriptApply")
            || type.getShortName().equals("DebugRuleElementMatches")
            || type.getShortName().equals("DebugRuleElementMatch")) {
      return;
    }
    insertFS(fs, type, cas, withParents);
  }

  private void insertFS(FeatureStructure fs, Type type, CAS cas, boolean withParents) {
    TypeTreeNode typeTreeNode = getTreeNode(type, cas);

    FSTreeNode node = createFSNode(typeTreeNode, fs);
    typeTreeNode.addChild(node);
    if (withParents) {
      Type parent = fs.getCAS().getTypeSystem().getParent(type);
      if (parent != null) {
        insertFS(fs, parent, cas, withParents);
      }
    }
  }

  private FSTreeNode createFSNode(ITreeNode parent, FeatureStructure fs) {
    if (fs instanceof AnnotationFS) {
      return new AnnotationTreeNode(cas, parent, (AnnotationFS) fs, new Stack<Type>());
    }
    return new FSTreeNode(cas, parent, fs);
  }

  @Override
  public void sort() {
    sort(new TreeComparator());
  }

  @Override
  @SuppressWarnings("unchecked")
  public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {

    if (TypeTreeNode.class.equals(adapter)) {
      return this;
    } else if (Type.class.equals(adapter)) {
      return null;

    }

    return null;
  }

}
