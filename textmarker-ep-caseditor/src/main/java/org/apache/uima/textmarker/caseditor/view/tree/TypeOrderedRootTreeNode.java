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
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;

public class TypeOrderedRootTreeNode extends AbstractTreeNode implements IRootTreeNode {

  public TypeOrderedRootTreeNode() {
    super();
  }

  public String getName() {
    return null;
  }

  public Type getType() {
    return null;
  }

  public void insertFS(FeatureStructure fs) {
    // TODO hotfix for explanation types...
    if (fs.getType().getShortName().equals("DebugBlockApply")
            || fs.getType().getShortName().equals("DebugMatchedRuleMatch")
            || fs.getType().getShortName().equals("DebugFailedRuleMatch")
            || fs.getType().getShortName().equals("DebugScriptApply")
            || fs.getType().getShortName().equals("DebugRuleElementMatches")
            || fs.getType().getShortName().equals("DebugRuleElementMatch")) {
      return;
    }

    Iterator<ITreeNode> iter = getChildrenIterator();

    while (iter.hasNext()) {
      TypeTreeNode typeNode = (TypeTreeNode) iter.next();

      if (typeNode.getType().equals(fs.getType())) {
        FSTreeNode node = createFSNode(typeNode, fs);
        typeNode.addChild(node);
        return;
      }
    }

    TypeTreeNode typeNode = new TypeTreeNode(this, fs.getType());
    addChild(typeNode);

    FSTreeNode node = createFSNode(typeNode, fs);
    typeNode.addChild(node);
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
