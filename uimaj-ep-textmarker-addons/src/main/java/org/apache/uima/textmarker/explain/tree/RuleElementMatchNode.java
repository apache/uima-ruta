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

package org.apache.uima.textmarker.explain.tree;

import org.apache.uima.cas.ArrayFS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cev.data.tree.ICEVAnnotationNode;


public class RuleElementMatchNode extends ExplainAbstractTreeNode implements IEvaluatedNode,
        ICEVAnnotationNode {

  private boolean matched;

  public RuleElementMatchNode(IExplainTreeNode parent, FeatureStructure fs, TypeSystem ts) {
    super(parent, fs, ts);
    Feature f = fs.getType().getFeatureByBaseName(ExplainTree.BASE_CONDITION);
    FeatureStructure baseFS = fs.getFeatureValue(f);
    Feature baseFeat = baseFS.getType().getFeatureByBaseName(ExplainTree.VALUE);
    matched = baseFS.getBooleanValue(baseFeat);

    f = fs.getType().getFeatureByBaseName(ExplainTree.CONDITIONS);
    ArrayFS value = (ArrayFS) fs.getFeatureValue(f);
    FeatureStructure[] fsarray = value.toArray();
    for (FeatureStructure each : fsarray) {
      Feature eachFeat = each.getType().getFeatureByBaseName(ExplainTree.VALUE);
      boolean eachValue = each.getBooleanValue(eachFeat);
      matched &= eachValue;
    }
  }

  public boolean matched() {
    return matched;
  }

  public AnnotationFS getAnnotation() {
    FeatureStructure fs = getFeatureStructure();
    if (fs instanceof AnnotationFS) {
      return (AnnotationFS) fs;
    }
    return null;
  }

}
