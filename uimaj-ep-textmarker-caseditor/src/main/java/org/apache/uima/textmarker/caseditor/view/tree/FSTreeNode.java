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

import org.apache.uima.cas.ArrayFS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.eclipse.core.runtime.IAdaptable;

public class FSTreeNode extends AbstractTreeNode implements IAdaptable {

  protected FeatureStructure fs;

  public FSTreeNode(FeatureStructure thisFS) {
    super();
    this.fs = thisFS;

    for (Feature f : thisFS.getType().getFeatures()) {
      if (f.getRange().isPrimitive()) {
        addChild(new FeatureTreeNode(this, f, thisFS.getFeatureValueAsString(f)));
      }
    }

  }

  public FSTreeNode(ITreeNode parent, FeatureStructure annotation) {
    super(parent);
    this.fs = annotation;
    for (Feature f : annotation.getType().getFeatures()) {
      addFeatures(this, f, annotation);
    }
  }

  public String getName() {
    return fs.getType().getShortName();
  }

  public Type getType() {
    return fs.getType();
  }

  public void addFeatures(ITreeNode parent, Feature f, FeatureStructure featureStructure) {
    if (f.getRange().isArray()) {
      FeatureStructure featureValue = featureStructure.getFeatureValue(f);
      if (featureValue instanceof ArrayFS) {
        ArrayFS array = (ArrayFS) featureValue;
        if (array != null) {
          String text = "Array" + "[" + array.size() + "]";
          FeatureTreeNode arrayNode = new FeatureTreeNode(this, f, text);
          parent.addChild(arrayNode);

          int size = array.size();

          for (int i = 0; i < size; i++) {
            FeatureStructure fs = array.get(i);
            if (fs instanceof FeatureStructure) {
              Type fsType = fs.getType();
              ITreeNode fsNode;
              if (fs instanceof AnnotationFS) {
                AnnotationFS faa = (AnnotationFS) fs;
                fsNode = new AnnotationTreeNode(arrayNode, faa);
              } else {
                fsNode = new TypeTreeNode(arrayNode, fsType);
              }
              arrayNode.addChild(fsNode);

              // List<Feature> features = fs.getType().getFeatures();
              // for (Feature feature : features) {
              // addFeatures(fsNode, feature, fs);
              // }
            }
          }
        }
      }
    } else if (f.getRange().isPrimitive()) {
      if ("uima.cas.AnnotationBase:sofa".equals(f.getName())) {
      } else {
        parent.addChild(new FeatureTreeNode(this, f, featureStructure.getFeatureValueAsString(f)));
      }
    } else if (f.getRange() instanceof Type) {
      FeatureStructure featureValue = featureStructure.getFeatureValue(f);
      if (featureValue instanceof AnnotationFS) {
        parent.addChild(new AnnotationTreeNode(this, ((AnnotationFS) featureValue)));
      }
    }
  }

  public Object getAdapter(Class adapter) {

    if (FSTreeNode.class.equals(adapter)) {
      return this;
    } else if (FeatureStructure.class.equals(adapter)) {
      return fs;

    }

    return null;
  }

}
