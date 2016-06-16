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

package org.apache.uima.ruta.explain.apply;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.ruta.explain.ExplainConstants;
import org.apache.uima.ruta.explain.tree.IExplainTreeNode;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class ApplyTreeLabelProvider extends LabelProvider implements ILabelProvider {

  private DecimalFormat dfTime = new DecimalFormat("###,###,##0.000",
          DecimalFormatSymbols.getInstance(Locale.ENGLISH));;

  private DecimalFormat dfPercentage = new DecimalFormat("##0.00",
          DecimalFormatSymbols.getInstance(Locale.ENGLISH));;

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

    StringBuilder result = new StringBuilder();
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
        result.append("[");
        result.append(v1);
        result.append("/");
        result.append(v2);
        result.append("] ");

        Feature f3 = ruleType.getFeatureByBaseName(ExplainConstants.ELEMENT);
        String v3 = fs.getStringValue(f3);
        v3 = v3.replaceAll("[\\n\\r]", "");
        if (v3.length() > 150) {
          v3 = v3.substring(0, 148) + "...";
        }
        result.append(v3);

        Feature f4 = ruleType.getFeatureByBaseName(ExplainConstants.TIME);
        long v4 = fs.getLongValue(f4);
        if (v4 > 0) {
          double took = v4 / 1000.0;
          String s = dfTime.format(took);
          result.append(" [");
          result.append(s);
          result.append("s");

          double percent = 100;
          IExplainTreeNode parentNode = debugNode.getParent();
          if (parentNode != null) {
            FeatureStructure parent = parentNode.getFeatureStructure();
            if (parent != null) {
              long parentTime = parent.getLongValue(f4);
              percent = (took / (parentTime / 1000.0)) * 100.0;
            }
          }
          result.append("|");
          result.append(dfPercentage.format(percent));
          result.append("%");
          result.append("]");
        }
      }
    }
    return result.toString();

  }

  public static String escape(String str) {
    String result = str.replaceAll("\\\\", "\\\\\\\\");
    return result;
  }

}
