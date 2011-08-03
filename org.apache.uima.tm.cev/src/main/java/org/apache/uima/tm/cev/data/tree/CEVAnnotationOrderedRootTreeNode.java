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

package org.apache.uima.tm.cev.data.tree;

import java.util.LinkedList;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;

public class CEVAnnotationOrderedRootTreeNode extends CEVAnnotationOrderedTreeNode implements
        ICEVRootTreeNode {

  public CEVAnnotationOrderedRootTreeNode() {
    super(null, null);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.CEVAnnotationTreeNode#getName()
   */
  @Override
  public String getName() {
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.CEVAnnotationTreeNode#getType()
   */
  @Override
  public Type getType() {
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.CEVAnnotationTreeNode#getAnnotation()
   */
  @Override
  public AnnotationFS getAnnotation() {
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVRootTreeNode#getNodes()
   */
  public LinkedList<ICEVTreeNode> getNodes() {
    LinkedList<ICEVTreeNode> list = new LinkedList<ICEVTreeNode>();

    getNodes(list);

    return list;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVRootTreeNode#getNodes(org.apache.uima.cas.Type)
   */
  public LinkedList<ICEVTreeNode> getNodes(Type type) {
    LinkedList<ICEVTreeNode> list = new LinkedList<ICEVTreeNode>();

    getNodes(list, type);

    return list;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.apache.uima.tm.cev.data.tree.ICEVRootTreeNode#getNodes(org.apache.uima.cas.text.AnnotationFS
   * )
   */
  public LinkedList<ICEVTreeNode> getNodes(AnnotationFS annot) {
    LinkedList<ICEVTreeNode> list = new LinkedList<ICEVTreeNode>();

    getNodes(list, annot);

    return list;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVRootTreeNode#sort()
   */
  public void sort() {
    sort(new CEVTreeComparator());
  }
}
