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

package org.apache.uima.textmarker.explain.failed;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.explain.tree.RuleMatchNode;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


public class FailedTreeLabelProvider extends LabelProvider implements ILabelProvider {

  private FailedViewPage owner;

  public FailedTreeLabelProvider(FailedViewPage owner) {
    super();
    this.owner = owner;
  }

  @Override
  public Image getImage(Object element) {
    if (element instanceof RuleMatchNode) {
      RuleMatchNode ruleMatchNode = (RuleMatchNode) element;
      FeatureStructure fs = ruleMatchNode.getFeatureStructure();
      if (fs != null) {
        String name = fs.getType().getName();
        return owner.getImage(name);
      }
    }
    return null;
  }

  @Override
  public String getText(Object element) {
    if (element instanceof RuleMatchNode) {
      RuleMatchNode debugNode = (RuleMatchNode) element;
      FeatureStructure fs = debugNode.getFeatureStructure();
      if (fs != null) {
        String s = ((AnnotationFS) fs).getCoveredText();
        s = s.replaceAll("[\\n\\r]", "");
        return s;
      }
    }

    return element.toString();
  }
}
