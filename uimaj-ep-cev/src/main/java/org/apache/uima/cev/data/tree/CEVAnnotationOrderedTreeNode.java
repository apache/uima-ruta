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

import java.util.Iterator;
import java.util.LinkedList;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;

public class CEVAnnotationOrderedTreeNode extends CEVAnnotationTreeNode {

  public CEVAnnotationOrderedTreeNode(ICEVTreeNode parent, AnnotationFS annotation) {
    super(parent, annotation);
  }

  public void insertFS(FeatureStructure fs) {
    boolean processed = false;

    if (!(fs instanceof AnnotationFS))
      return;

    AnnotationFS annotation = (AnnotationFS) fs;
    Iterator<ICEVTreeNode> iter = getChildrenIterator();

    while (iter.hasNext()) {
      CEVAnnotationOrderedTreeNode childNode = (CEVAnnotationOrderedTreeNode) iter.next();

      if (isChild(childNode, annotation)) {
        childNode.insertFS(annotation);
        processed = true;
      }
    }

    if (!processed) {
      CEVAnnotationOrderedTreeNode node = new CEVAnnotationOrderedTreeNode(this, annotation);
      addChild(node);
    }
  }

  private boolean isChild(CEVAnnotationOrderedTreeNode node, AnnotationFS annotation) {
    return (node.getAnnotation().getBegin() < annotation.getBegin() && node.getAnnotation()
            .getEnd() >= annotation.getEnd())
            || (node.getAnnotation().getBegin() <= annotation.getBegin() && node.getAnnotation()
                    .getEnd() > annotation.getEnd());
  }

  public void getNodes(LinkedList<ICEVTreeNode> list, Type type) {
    Iterator<ICEVTreeNode> iter = getChildrenIterator();

    while (iter.hasNext()) {
      CEVAnnotationOrderedTreeNode node = (CEVAnnotationOrderedTreeNode) iter.next();

      if (node.getType().equals(type))
        list.add(node);

      node.getNodes(list, type);
    }
  }

  public void getNodes(LinkedList<ICEVTreeNode> list, AnnotationFS annot) {
    Iterator<ICEVTreeNode> iter = getChildrenIterator();

    while (iter.hasNext()) {
      CEVAnnotationOrderedTreeNode node = (CEVAnnotationOrderedTreeNode) iter.next();

      if (node.getAnnotation().equals(annot))
        list.add(node);
      else if (isChild(node, annot))
        node.getNodes(list, annot);
    }
  }

}
