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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;

public class TypeOrderedRootTreeNode extends AbstractTreeNode implements IRootTreeNode {

  private Map<Type, TypeTreeNode> typeMap = new HashMap<Type, TypeTreeNode>();

  public TypeOrderedRootTreeNode() {
    super();
  }

  public String getName() {
    return null;
  }

  public Type getType() {
    return null;
  }

  /**
   * Gets a tree node for a type from cache; may create a new tree node.
   * 
   * @param type
   * @return
   */
  public TypeTreeNode getTreeNode(Type type) {
    TypeTreeNode typeTreeNode = typeMap.get(type);
    if (typeTreeNode == null) {
      typeTreeNode = new TypeTreeNode(this, type);
      typeMap.put(type, typeTreeNode);
      addChild(typeTreeNode);
    }
    return typeTreeNode;
  }

  public void insertFS(FeatureStructure fs, boolean withParents) {
    // TODO hotfix for explanation types...
    Type type = fs.getType();
    if (type.getShortName().equals("DebugBlockApply")
            || type.getShortName().equals("DebugMatchedRuleMatch")
            || type.getShortName().equals("DebugFailedRuleMatch")
            || type.getShortName().equals("DebugScriptApply")
            || type.getShortName().equals("DebugRuleElementMatches")
            || type.getShortName().equals("DebugRuleElementMatch")) {
      return;
    }
    insertFS(fs, type, withParents);
  }

  private void insertFS(FeatureStructure fs, Type type, boolean withParents) {
    TypeTreeNode typeTreeNode = getTreeNode(type);

    FSTreeNode node = createFSNode(typeTreeNode, fs);
    typeTreeNode.addChild(node);
    if (withParents) {
      Type parent = fs.getCAS().getTypeSystem().getParent(type);
      if (parent != null) {
        insertFS(fs, parent, withParents);
      }
    }
  }

  private FSTreeNode createFSNode(ITreeNode parent, FeatureStructure fs) {
    if (fs instanceof AnnotationFS) {
      return new AnnotationTreeNode(parent, (AnnotationFS) fs, new ArrayList<Type>());
    }
    return new FSTreeNode(parent, fs);
  }

  public LinkedList<ITreeNode> getNodes() {
    LinkedList<ITreeNode> list = new LinkedList<ITreeNode>();
    getNodes(list);
    return list;
  }

  public LinkedList<ITreeNode> getNodes(Type type) {
    Iterator<ITreeNode> iter = getChildrenIterator();

    LinkedList<ITreeNode> list = new LinkedList<ITreeNode>();

    while (iter.hasNext()) {
      TypeTreeNode typeNode = (TypeTreeNode) iter.next();

      if (typeNode.getType().equals(type)) {
        Iterator<ITreeNode> children = typeNode.getChildrenIterator();

        list.add(typeNode);

        while (children.hasNext())
          list.add(children.next());
      }
    }

    return list;
  }

  public LinkedList<ITreeNode> getNodes(AnnotationFS annot) {
    Iterator<ITreeNode> iter = getChildrenIterator();

    LinkedList<ITreeNode> list = new LinkedList<ITreeNode>();

    while (iter.hasNext()) {
      TypeTreeNode typeNode = (TypeTreeNode) iter.next();

      if (typeNode.getType().equals(annot.getType())) {
        Iterator<ITreeNode> children = typeNode.getChildrenIterator();

        while (children.hasNext()) {
          AnnotationTreeNode node = (AnnotationTreeNode) children.next();

          if (node.getAnnotation().equals(annot)) {
            list.add(node);
            return list;
          }
        }
      }
    }

    return list;
  }

  public void sort() {
    sort(new TreeComparator());
  }

  public Object getAdapter(Class adapter) {

    if (TypeTreeNode.class.equals(adapter)) {
      return this;
    } else if (Type.class.equals(adapter)) {
      return null;

    }

    return null;
  }

}
