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

import org.apache.uima.cas.Type;

public class TypeTreeNode extends AbstractTreeNode {

  private Type type;

  public TypeTreeNode(Type type) {
    this(null, type);
  }

  public TypeTreeNode(ITreeNode parent, Type type) {
    super(parent);
    this.type = type;
  }

  public String getName() {
    return type.getName();
  }

  public Type getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null)
      return false;
    if (o instanceof TypeTreeNode) {
      return type.equals(((TypeTreeNode) o).getType());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return type.hashCode();
  }

  public Object getAdapter(Class adapter) {

    if (TypeTreeNode.class.equals(adapter)) {
      return this;
    } else if (Type.class.equals(adapter)) {
      return type;

    }

    return null;
  }

}
