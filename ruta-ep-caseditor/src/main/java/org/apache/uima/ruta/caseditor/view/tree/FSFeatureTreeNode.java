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

import java.util.Stack;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.eclipse.core.runtime.IAdaptable;

public class FSFeatureTreeNode extends FSTreeNode implements IAnnotationNode, IAdaptable {

  private Feature feature;

  public FSFeatureTreeNode(ITreeNode parent, Feature feature, FeatureStructure fs) {
    super(parent, fs);
    this.feature = feature;
  }

  public FSFeatureTreeNode(ITreeNode parent,Feature feature, FeatureStructure fs,
          Stack<Type> parentTypes) {
    super(parent, fs, parentTypes);
    this.feature = feature;
  }

  public AnnotationFS getAnnotation() {
    return (AnnotationFS) fs;
  }

  @Override
  public String getName() {
    return feature.getShortName() + ": " + getAnnotation().getCoveredText();
  }

  public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
    if (FSTreeNode.class.equals(adapter)) {
      return this;
    } else if (AnnotationFS.class.equals(adapter) || FeatureStructure.class.equals(adapter)) {
      return fs;
    }
    return null;
  }
}
