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

package org.apache.uima.textmarker.explain.apply;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.textmarker.explain.ExplainConstants;
import org.apache.uima.textmarker.explain.tree.IExplainTreeNode;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class ApplyTreeLabelProvider extends LabelProvider implements ILabelProvider {

  private ApplyViewPage owner;

  ImageDescriptor blockApply;

  public ApplyTreeLabelProvider(ApplyViewPage owner) {
    super();
    this.owner = owner;
  }

  @Override
  public Image getImage(Object element) {
    if (element instanceof IExplainTreeNode) {
      IExplainTreeNode debugNode = (IExplainTreeNode) element;
      FeatureStructure fs = debugNode.getFeatureStructure();
      if (fs != null) {
        String name = fs.getType().getName();
        if (name.equals(ExplainConstants.RULE_APPLY_TYPE)) {
          if (debugNode.getChildren().size() >= 3) {
            name += "Delegate";
          }
        }
        return owner.getImage(name);
      }
    }
    return null;
  }

  @Override
  public String getText(Object element) {

    String result = "error";
    if (element instanceof IExplainTreeNode) {
      IExplainTreeNode debugNode = (IExplainTreeNode) element;
      TypeSystem ts = debugNode.getTypeSystem();

      Type ruleType = ts.getType(ExplainConstants.RULE_APPLY_TYPE);
      FeatureStructure fs = debugNode.getFeatureStructure();
      if (fs != null && ts.subsumes(ruleType, fs.getType())) {
        Feature f1 = ruleType.getFeatureByBaseName(ExplainConstants.APPLIED);
        int v1 = fs.getIntValue(f1);
        Feature f2 = ruleType.getFeatureByBaseName(ExplainConstants.TRIED);
        int v2 = fs.getIntValue(f2);
        Feature f3 = ruleType.getFeatureByBaseName(ExplainConstants.ELEMENT);
        String v3 = fs.getStringValue(f3);
        v3 = v3.replaceAll("[\\n\\r]", "");
        Feature f4 = ruleType.getFeatureByBaseName(ExplainConstants.TIME);
        long v4 = fs.getLongValue(f4);
        String time = "";
        if (v4 > 0.0) {
          double took = v4 / 1000.0;
          time = " [" + took + "s";
          IExplainTreeNode parentNode = debugNode.getParent();
          if (parentNode != null) {
            FeatureStructure parent = parentNode.getFeatureStructure();
            if (parent != null) {
              long parentTime = parent.getLongValue(f4);
              double percent = (took / (parentTime / 1000.0)) * 100.0;
              percent = Math.round(percent * 100.0) / 100.0;
              time += "|" + percent + "%]";
            } else {
              time += "]";
            }
          } else {
            time += "]";
          }

        }
        result = "[" + v1 + "/" + v2 + "] " + v3 + time;
      }
    }
    return result;

  }

  public static String escape(String str) {
    String result = str.replaceAll("\\\\", "\\\\\\\\");
    return result;
  }

}
