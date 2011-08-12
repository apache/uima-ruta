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

package org.apache.uima.textmarker.testing.ui.views.fp;

import org.apache.uima.cev.data.tree.CEVAnnotationTreeNode;
import org.apache.uima.cev.data.tree.CEVFeatureTreeNode;
import org.apache.uima.cev.data.tree.CEVTypeTreeNode;
import org.apache.uima.cev.views.TextUtils;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class FalsePositiveLabelProvider extends LabelProvider implements ILabelProvider {

  private FalsePositiveViewPage owner;

  public FalsePositiveLabelProvider() {
    super();
  }

  public FalsePositiveLabelProvider(FalsePositiveViewPage owner) {
    super();
    this.owner = owner;
  }

  @Override
  public String getText(Object element) {
    if (element instanceof CEVAnnotationTreeNode) {
      CEVAnnotationTreeNode fnNode = (CEVAnnotationTreeNode) element;
      if (fnNode.getAnnotation() != null) {
        String typeName = fnNode.getAnnotation().getType().getName();
        String coveredText = fnNode.getAnnotation().getCoveredText();
        coveredText = coveredText.replaceAll("[\\n]", "").replaceAll("[\\r]", "");
        if (typeName.equals("org.apache.uima.textmarker.type.FalsePositive")
                || typeName.equals("org.apache.uima.textmarker.type.FalseNegative")
                || typeName.equals("org.apache.uima.textmarker.type.TruePositive")) {
          return coveredText;
        }
        String name = TextUtils.shrinkNamespace(fnNode.getAnnotation().getType().getName());
        return (name + ": " + coveredText);
      }
    }
    if (element instanceof CEVTypeTreeNode) {
      CEVTypeTreeNode testNode = (CEVTypeTreeNode) element;
      return TextUtils.shrinkNamespace(testNode.getType().getName());
    }
    if (element instanceof CEVFeatureTreeNode) {
      CEVFeatureTreeNode fNode = (CEVFeatureTreeNode) element;
      return fNode.getName();
    }

    return "error";
  }

  @Override
  public Image getImage(Object element) {
    if (element instanceof CEVTypeTreeNode) {
      return owner.getCurrentCEVData().getIcon(((CEVTypeTreeNode) element).getType());
    }
    if (element instanceof CEVAnnotationTreeNode) {
      return owner.getCurrentCEVData().getIcon(
              ((CEVAnnotationTreeNode) element).getAnnotation().getType());
    }
    return null;
  }
}
