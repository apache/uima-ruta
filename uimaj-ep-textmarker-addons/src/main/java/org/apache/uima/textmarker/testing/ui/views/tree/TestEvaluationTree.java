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

package org.apache.uima.textmarker.testing.ui.views.tree;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.caseditor.view.tree.AnnotationTreeNode;
import org.apache.uima.textmarker.caseditor.view.tree.ITreeNode;
import org.apache.uima.textmarker.caseditor.view.tree.TypeTreeNode;
import org.apache.uima.textmarker.testing.evaluator.ICasEvaluator;

public class TestEvaluationTree {

  private TypeTreeNode root;

  public TestEvaluationTree() {

  }

  public void createTree(CAS cas) {
    Type falsePositiveType = cas.getTypeSystem().getType(ICasEvaluator.FALSE_POSITIVE);
    Type falseNegativeType = cas.getTypeSystem().getType(ICasEvaluator.FALSE_NEGATIVE);
    Type truePositiveType = cas.getTypeSystem().getType(ICasEvaluator.TRUE_POSITIVE);

    if (falsePositiveType == null || falseNegativeType == null) {
      return;
    }
    // Creating RootNode and children that function as root nodes
    // for the FalsePositive /FalseNegative subtrees
    root = new TypeTreeNode(null, cas.getAnnotationType());
    boolean containsEvalInfos = false;

    TypeTreeNode fproot = new TypeTreeNode(root, falsePositiveType);
    TypeTreeNode fnroot = new TypeTreeNode(root, falseNegativeType);
    TypeTreeNode tproot = new TypeTreeNode(root, truePositiveType);

    // Iterating through CAS and adding nodes to according subtrees

    addEvalNodes(cas, falsePositiveType, fproot);
    addEvalNodes(cas, falseNegativeType, fnroot);
    addEvalNodes(cas, truePositiveType, tproot);

    // if (containsEvalInfos) {
    root.addChild(fproot);
    root.addChild(fnroot);
    root.addChild(tproot);
    // }
  }

  private void addEvalNodes(CAS cas, Type falsePositiveType, TypeTreeNode fproot) {
    FSIterator<AnnotationFS> iter = cas.getAnnotationIndex(falsePositiveType).iterator();
    while (iter.isValid()) {
      FeatureStructure fs = iter.get();
      if (fs instanceof AnnotationFS) {
        AnnotationFS a = (AnnotationFS) fs;
        Feature original = fs.getType().getFeatureByBaseName(ICasEvaluator.ORIGINAL);
        FeatureStructure originalfs = fs.getFeatureValue(original);
        TypeTreeNode parentTypeNode = containsTypeNode(fproot, originalfs);
        if (parentTypeNode == null && originalfs != null) {
          parentTypeNode = new TypeTreeNode(fproot, originalfs.getType());
          fproot.addChild(parentTypeNode);
        }
        AnnotationTreeNode newNode = new AnnotationTreeNode(parentTypeNode, a);
        parentTypeNode.addChild(newNode);
      }
      iter.moveToNext();
    }
  }

  private TypeTreeNode containsTypeNode(TypeTreeNode fproot, FeatureStructure originalfs) {
    ITreeNode[] children = fproot.getChildren();
    for (ITreeNode each : children) {
      if (each instanceof TypeTreeNode) {
        TypeTreeNode node = (TypeTreeNode) each;
        if (node.getType().equals(originalfs.getType())) {
          return node;
        }
      }
    }
    return null;
  }

  public ITreeNode getRoot() {
    return this.root;
  }

}
