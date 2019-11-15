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

package org.apache.uima.ruta.explain.inlined;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.ruta.explain.ExplainConstants;
import org.apache.uima.ruta.explain.apply.ApplyTreeLabelProvider;
import org.apache.uima.ruta.explain.apply.ApplyViewPage;
import org.apache.uima.ruta.explain.tree.InlinedRuleBlockNode;
import org.apache.uima.ruta.type.DebugInlinedBlock;
import org.eclipse.swt.graphics.Image;

public class InlinedTreeLabelProvider extends ApplyTreeLabelProvider {

  public InlinedTreeLabelProvider(ApplyViewPage owner) {
    super(owner);
  }

  @Override
  public Image getImage(Object element) {
    if (element instanceof InlinedRuleBlockNode) {
      InlinedRuleBlockNode ruleBlockNode = (InlinedRuleBlockNode) element;
      if (ruleBlockNode.isAsCondition()) {
        if (ruleBlockNode.isMatched()) {
          return owner.getImage(ExplainConstants.INLINED_CONDITION_BLOCK_MATCHED);
        } else {
          return owner.getImage(ExplainConstants.INLINED_CONDITION_BLOCK_FAILED);
        }
      } else {
        return owner.getImage(ExplainConstants.INLINED_ACTION_BLOCK);
      }
    }

    return super.getImage(element);
  }

  @Override
  public String getText(Object element) {
    if (element instanceof InlinedRuleBlockNode) {
      InlinedRuleBlockNode node = (InlinedRuleBlockNode) element;
      FeatureStructure fs = node.getFeatureStructure();
      if (fs instanceof DebugInlinedBlock) {
        return ((DebugInlinedBlock) fs).getElement();
      }
    }

    return super.getText(element);
  }
}
