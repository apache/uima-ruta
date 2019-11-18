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

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.apache.uima.cas.ArrayFS;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CommonArrayFS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.eclipse.core.runtime.IAdaptable;

public class FSTreeNode extends AbstractTreeNode implements IAdaptable {

  protected FeatureStructure fs;

  public FSTreeNode(CAS cas, ITreeNode parent, FeatureStructure annotation) {
    this(cas, parent, annotation, new Stack<Type>());
  }

  public FSTreeNode(CAS cas, ITreeNode parent, FeatureStructure annotation,
          Stack<Type> parentTypes) {
    super(cas, parent);
    this.fs = annotation;
    parentTypes.push(fs.getType());
    for (Feature f : annotation.getType().getFeatures()) {
      addFeatures(this, f, annotation, cas, parentTypes);
    }
    parentTypes.pop();
  }

  @Override
  public String getName() {
    return fs.getType().getShortName();
  }

  @Override
  public Type getType() {
    return fs.getType();
  }

  public void addFeatures(ITreeNode parent, Feature f, FeatureStructure featureStructure, CAS cas,
          Stack<Type> parentTypes) {
    if (f.getRange().isArray()) {
      // handle all kinds of arrays
      FeatureStructure featureValue = featureStructure.getFeatureValue(f);
      // ArrayFS is a special kind of CommonArrayFS
      if (featureValue instanceof ArrayFS) {
        ArrayFS array = (ArrayFS) featureValue;
        if (array != null) {
          String text = "Array" + "[" + array.size() + "]";
          PrimitiveFeatureTreeNode arrayNode = new PrimitiveFeatureTreeNode(this, f, text);
          parent.addChild(arrayNode);
          int size = array.size();
          for (int i = 0; i < size; i++) {
            FeatureStructure fs = array.get(i);
            if (fs instanceof FeatureStructure) {
              Type fsType = fs.getType();
              if (expandable(fsType, parentTypes)) {
                ITreeNode fsNode;
                if (fs instanceof AnnotationFS) {
                  AnnotationFS faa = (AnnotationFS) fs;
                  fsNode = new AnnotationTreeNode(cas, arrayNode, faa, parentTypes);
                } else {
                  fsNode = new TypeTreeNode(cas, arrayNode, fsType);
                }
                arrayNode.addChild(fsNode);
              }
            }
          }
        }
      } else if (featureValue instanceof CommonArrayFS) {
        // handle all other kind of CommonArrayFS nodes (ArrayFS handled above)
        CommonArrayFS array = (CommonArrayFS) featureValue;
        String text = "Array" + "[" + array.size() + "]";
        PrimitiveFeatureTreeNode arrayNode = new PrimitiveFeatureTreeNode(this, f, text);
        parent.addChild(arrayNode);
        String[] stringArray = array.toStringArray();
        for (String string : stringArray) {
          PrimitiveTreeNode stringNode = new PrimitiveTreeNode(arrayNode, string);
          arrayNode.addChild(stringNode);
        }
      }
    } else if (f.getRange().isPrimitive()) {
      if ("uima.cas.AnnotationBase:sofa".equals(f.getName())) {
      } else {
        parent.addChild(
                new PrimitiveFeatureTreeNode(this, f, featureStructure.getFeatureValueAsString(f)));
      }
    } else if (f.getRange() instanceof Type) {
      FeatureStructure featureValue = featureStructure.getFeatureValue(f);
      if (featureValue instanceof AnnotationFS && expandable(featureValue.getType(), parentTypes)) {
        parent.addChild(new FSFeatureTreeNode(cas, this, f, featureValue, parentTypes));
      }
    }
  }

  private boolean expandable(Type type, List<Type> parentTypes) {
    int frequency = Collections.frequency(parentTypes, type);
    return frequency < 2;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
    if (FSTreeNode.class.equals(adapter)) {
      return this;
    } else if (FeatureStructure.class.equals(adapter)) {
      return fs;

    }
    return null;
  }

}
