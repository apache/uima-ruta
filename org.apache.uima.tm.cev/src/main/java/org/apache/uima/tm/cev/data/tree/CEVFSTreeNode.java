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

import org.apache.uima.cas.ArrayFS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;

public class CEVFSTreeNode extends CEVAbstractTreeNode {

  protected FeatureStructure fs;

  public CEVFSTreeNode(FeatureStructure thisFS) {
    super();
    this.fs = thisFS;

    for (Feature f : thisFS.getType().getFeatures()) {
      if (f.getRange().isPrimitive()) {
        addChild(new CEVFeatureTreeNode(this, f, thisFS.getFeatureValueAsString(f)));
      }
    }

  }

  public CEVFSTreeNode(ICEVTreeNode parent, FeatureStructure annotation) {
    super(parent);
    this.fs = annotation;
    for (Feature f : annotation.getType().getFeatures()) {
      addFeatures(this, f, annotation);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#getName()
   */
  public String getName() {
    return fs.getType().getShortName();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#getType()
   */
  public Type getType() {
    return fs.getType();
  }

  public void addFeatures(ICEVTreeNode parent, Feature f, FeatureStructure featureStructure) {
    if (f.getRange().isArray()) {
      FeatureStructure featureValue = featureStructure.getFeatureValue(f);
      if (featureValue instanceof ArrayFS) {
        ArrayFS array = (ArrayFS) featureValue;
        if (array != null) {
          String text = "Array" + "[" + array.size() + "]";
          CEVFeatureTreeNode arrayNode = new CEVFeatureTreeNode(this, f, text);
          parent.addChild(arrayNode);

          int size = array.size();

          for (int i = 0; i < size; i++) {
            FeatureStructure fs = array.get(i);
            if (fs instanceof FeatureStructure) {
              Type fsType = fs.getType();
              ICEVTreeNode fsNode;
              if (fs instanceof AnnotationFS) {
                AnnotationFS faa = (AnnotationFS) fs;
                fsNode = new CEVAnnotationTreeNode(arrayNode, faa);
              } else {
                fsNode = new CEVTypeTreeNode(arrayNode, fsType);
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
        parent.addChild(new CEVFeatureTreeNode(this, f, featureStructure.getFeatureValueAsString(f)));
      }
    } else if (f.getRange() instanceof Type) {
      FeatureStructure featureValue = featureStructure.getFeatureValue(f);
      if (featureValue instanceof AnnotationFS) {
        parent.addChild(new CEVAnnotationTreeNode(this, ((AnnotationFS) featureValue)));
      }
    }
  }
}
