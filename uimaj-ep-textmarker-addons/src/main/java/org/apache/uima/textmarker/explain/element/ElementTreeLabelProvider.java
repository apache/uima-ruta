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

package org.apache.uima.textmarker.explain.element;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.explain.ExplainConstants;
import org.apache.uima.textmarker.explain.tree.ConditionNode;
import org.apache.uima.textmarker.explain.tree.ExplainTree;
import org.apache.uima.textmarker.explain.tree.IExplainTreeNode;
import org.apache.uima.textmarker.explain.tree.RuleElementMatchNode;
import org.apache.uima.textmarker.explain.tree.RuleElementMatchesNode;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class ElementTreeLabelProvider extends LabelProvider implements ILabelProvider {

  private ElementViewPage owner;

  public ElementTreeLabelProvider(ElementViewPage owner) {
    super();
    this.owner = owner;
  }

  @Override
  public Image getImage(Object element) {
    if (element instanceof RuleElementMatchNode) {
      RuleElementMatchNode rem = (RuleElementMatchNode) element;
      boolean matched = rem.matched();
      return owner.getImage(ExplainConstants.RULE_ELEMENT_MATCH_TYPE + matched);
    } else if (element instanceof RuleElementMatchesNode) {
      RuleElementMatchesNode rems = (RuleElementMatchesNode) element;
      boolean matched = rems.matched();
      return owner.getImage(ExplainConstants.RULE_ELEMENT_MATCHES_TYPE + matched);
    } else if (element instanceof ConditionNode) {
      ConditionNode rems = (ConditionNode) element;
      boolean matched = rems.matched();
      return owner.getImage(ExplainConstants.EVAL_CONDITION_TYPE + matched);
    }
    return owner.getImage("element");
  }

  @Override
  public String getText(Object element) {
    if (element instanceof IExplainTreeNode) {
      IExplainTreeNode debugNode = (IExplainTreeNode) element;
      TypeSystem ts = debugNode.getTypeSystem();

      if (element instanceof RuleElementMatchesNode) {
        Type type = ts.getType(ExplainConstants.RULE_ELEMENT_MATCHES_TYPE);
        FeatureStructure fs = debugNode.getFeatureStructure();
        Feature f = type.getFeatureByBaseName(ExplainTree.ELEMENT);
        if (f != null) {
          String v = fs.getStringValue(f);
          return v;
        }
      } else if (element instanceof RuleElementMatchNode) {
        FeatureStructure fs = debugNode.getFeatureStructure();
        if (fs instanceof AnnotationFS) {
          String s = ((AnnotationFS) fs).getCoveredText();
          s = s.replaceAll("[\\n\\r]", "");
          return s;
        }
      } else if (element instanceof ConditionNode) {
        Type type = ts.getType(ExplainConstants.EVAL_CONDITION_TYPE);
        FeatureStructure fs = debugNode.getFeatureStructure();
        Feature f = type.getFeatureByBaseName(ExplainTree.ELEMENT);
        if (f != null) {
          String v = fs.getStringValue(f);
          return v;
        }
      }
    }
    return element.toString();
  }
}
