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

package org.apache.uima.textmarker.testing.ui.views;

import org.apache.uima.textmarker.caseditor.view.tree.AnnotationTreeNode;
import org.apache.uima.textmarker.caseditor.view.tree.FeatureTreeNode;
import org.apache.uima.textmarker.caseditor.view.tree.TextUtils;
import org.apache.uima.textmarker.caseditor.view.tree.TypeTreeNode;
import org.apache.uima.textmarker.testing.evaluator.ICasEvaluator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class EvaluationLabelProvider extends LabelProvider implements ILabelProvider {

  private EvaluationViewPage owner;

  public EvaluationLabelProvider() {
    super();
  }

  public EvaluationLabelProvider(EvaluationViewPage owner) {
    super();
    this.owner = owner;
  }

  @Override
  public String getText(Object element) {
    if (element instanceof AnnotationTreeNode) {
      AnnotationTreeNode fnNode = (AnnotationTreeNode) element;
      if (fnNode.getAnnotation() != null) {
        String typeName = fnNode.getAnnotation().getType().getName();
        String coveredText = fnNode.getAnnotation().getCoveredText();
        coveredText = coveredText.replaceAll("[\\n]", "").replaceAll("[\\r]", "");
        if (typeName.equals(ICasEvaluator.FALSE_POSITIVE)
                || typeName.equals(ICasEvaluator.FALSE_NEGATIVE)
                || typeName.equals(ICasEvaluator.TRUE_POSITIVE)) {
          return coveredText;
        }
        String name = TextUtils.shrinkNamespace(fnNode.getAnnotation().getType().getName());
        return (name + ": " + coveredText);
      }
    }
    if (element instanceof TypeTreeNode) {
      TypeTreeNode testNode = (TypeTreeNode) element;
      return TextUtils.shrinkNamespace(testNode.getType().getName());
    }
    if (element instanceof FeatureTreeNode) {
      FeatureTreeNode fNode = (FeatureTreeNode) element;
      return fNode.getName();
    }

    return "error";
  }

  @Override
  public Image getImage(Object element) {
    // if (element instanceof TypeTreeNode) {
    // return owner.getCurrentData().getIcon(((TypeTreeNode) element).getType());
    // }
    // if (element instanceof AnnotationTreeNode) {
    // return owner.getCurrentData().getIcon(
    // ((AnnotationTreeNode) element).getAnnotation().getType());
    // }
    return null;
  }
}
