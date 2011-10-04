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

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.ArrayFS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cev.data.CEVData;
import org.apache.uima.textmarker.explain.ExplainConstants;

public class ExplainTree {

  public static final String BASE_CONDITION = "baseCondition";

  public static final String MATCHES = "matches";

  public static final String ELEMENTS = "elements";

  public static final String MATCHED = "matched";

  public static final String DELEGATES = "delegates";

  public static final String RULES = "rules";

  public static final String INNER_APPLY = "innerApply";

  public static final String CONDITIONS = "conditions";

  public static final String VALUE = "value";

  public static final String APPLIED = "applied";

  public static final String TRIED = "tried";

  public static final String ELEMENT = "element";

  public static final String RULE_ANCHOR = "ruleAnchor";

  public static final String TIME = "time";

  private IExplainTreeNode root;

  private Type ruleMatchType;

  private Type blockApplyType;

  private Type ruleApplyType;

  private Type ruleElementMatchType;

  private Type ruleElementMatchesType;

  private Type evaluatedConditionType;

  private Type matchedRuleMatchType;

  private Type failedRuleMatchType;

  public ExplainTree(CEVData casData) {
    this(casData, -1);
  }

  public ExplainTree(CEVData casData, int offset) {
    this(casData, offset, false);
    if (offset >= 0) {
      prune(root);
    }
  }

  public ExplainTree(CEVData casData, int offset, boolean onlyRules) {
    createTree(casData, offset, onlyRules);
  }

  public IExplainTreeNode getRoot() {
    return root;
  }

  private void createTree(CEVData casData, int offset, boolean onlyRules) {
    TypeSystem ts = casData.getCAS().getTypeSystem();
    Type scriptApply = ts.getType(ExplainConstants.SCRIPT_APPLY_TYPE);

    blockApplyType = ts.getType(ExplainConstants.BLOCK_APPLY_TYPE);
    ruleApplyType = ts.getType(ExplainConstants.RULE_APPLY_TYPE);
    ruleMatchType = ts.getType(ExplainConstants.RULE_MATCH_TYPE);
    matchedRuleMatchType = ts.getType(ExplainConstants.MATCHED_RULE_MATCH_TYPE);
    failedRuleMatchType = ts.getType(ExplainConstants.FAILED_RULE_MATCH_TYPE);
    ruleElementMatchType = ts.getType(ExplainConstants.RULE_ELEMENT_MATCH_TYPE);
    ruleElementMatchesType = ts.getType(ExplainConstants.RULE_ELEMENT_MATCHES_TYPE);
    evaluatedConditionType = ts.getType(ExplainConstants.EVAL_CONDITION_TYPE);

    if (scriptApply == null)
      return;
    FSIterator<AnnotationFS> it = casData.getCAS().getAnnotationIndex(scriptApply).iterator();
    root = new ApplyRootNode(null, ts);

    if (it.isValid()) {
      it.moveToFirst();
      while (it.isValid()) {
        AnnotationFS fs = it.get();
        buildTree(fs, root, ts, offset, onlyRules);
        it.moveToNext();
      }
    }

  }

  private void buildTree(FeatureStructure fs, IExplainTreeNode parent, TypeSystem ts, int offset,
          boolean onlyRules) {
    if (blockApplyType != null && blockApplyType.equals(fs.getType())) {
      processBlockApply((AnnotationFS) fs, parent, ts, offset, onlyRules);
    } else if (ruleApplyType != null && ruleApplyType.equals(fs.getType())) {
      processRuleApply((AnnotationFS) fs, parent, ts, offset, onlyRules);
    } else if ((matchedRuleMatchType != null && matchedRuleMatchType.equals(fs.getType()))
            || (failedRuleMatchType != null && failedRuleMatchType.equals(fs.getType()))) {
      processRuleMatch((AnnotationFS) fs, parent, ts, offset, onlyRules);
    } else if (ruleElementMatchesType != null && ruleElementMatchesType.equals(fs.getType())) {
      processRuleElementMatches(fs, parent, ts, offset, onlyRules);
    } else if (ruleElementMatchType != null && ruleElementMatchType.equals(fs.getType())) {
      processRuleElementMatch((AnnotationFS) fs, parent, ts, offset, onlyRules);
    } else if (evaluatedConditionType != null && evaluatedConditionType.equals(fs.getType())) {
      processEvaluatedCondition(fs, parent, ts, offset, onlyRules);
    }
  }

  private void processBlockApply(AnnotationFS fs, IExplainTreeNode parent, TypeSystem ts,
          int offset, boolean onlyRules) {
    if (offset >= 0 && (fs.getBegin() >= offset || fs.getEnd() <= offset)) {
      return;
    }
    BlockApplyNode blockNode = null;
    if (!onlyRules) {
      blockNode = new BlockApplyNode(parent, fs, ts);
      parent.addChild(blockNode);
      processBlockRuleApply(fs, blockNode, ts, offset, onlyRules);
    }
    Feature feature = blockApplyType.getFeatureByBaseName(INNER_APPLY);
    FeatureStructure featureValue = fs.getFeatureValue(feature);
    ArrayFS value = (ArrayFS) featureValue;
    FeatureStructure[] fsarray = value.toArray();
    for (FeatureStructure each : fsarray) {
      if (!onlyRules) {
        buildTree(each, blockNode, ts, offset, onlyRules);
      } else {
        buildTree(each, parent, ts, offset, onlyRules);
      }

    }
  }

  private void processBlockRuleApply(AnnotationFS fs, BlockApplyNode parent, TypeSystem ts,
          int offset, boolean onlyRules) {
    if (offset >= 0 && (fs.getBegin() >= offset || fs.getEnd() <= offset)) {
      return;
    }
    RuleApplyNode ruleNode = new RuleApplyNode(parent, fs, ts);
    parent.setBlockRuleApply(ruleNode);

    Feature feature = ruleApplyType.getFeatureByBaseName(RULES);
    ArrayFS value = (ArrayFS) fs.getFeatureValue(feature);
    if (value == null)
      return;
    FeatureStructure[] fsarray = value.toArray();

    MatchedRootNode matched = new MatchedRootNode(ruleNode, ts);
    FailedRootNode failed = new FailedRootNode(ruleNode, ts);
    ruleNode.addChild(matched);
    ruleNode.addChild(failed);

    for (FeatureStructure eachRuleMatch : fsarray) {
      Feature f = eachRuleMatch.getType().getFeatureByBaseName(MATCHED);
      boolean matchedValue = eachRuleMatch.getBooleanValue(f);
      if (matchedValue) {
        buildTree(eachRuleMatch, matched, ts, offset, onlyRules);
      } else {
        buildTree(eachRuleMatch, failed, ts, offset, onlyRules);
      }

      Feature df = eachRuleMatch.getType().getFeatureByBaseName(DELEGATES);
      if (df != null) {
        ArrayFS dv = (ArrayFS) eachRuleMatch.getFeatureValue(df);
        if (dv != null) {
          FeatureStructure[] da = dv.toArray();
          for (FeatureStructure delegateFS : da) {
            buildTree(delegateFS, ruleNode, ts, offset, onlyRules);
          }
        }
      }

    }
  }

  private void processRuleApply(AnnotationFS fs, IExplainTreeNode parent, TypeSystem ts,
          int offset, boolean onlyRules) {
    if (offset >= 0 && (fs.getBegin() >= offset || fs.getEnd() <= offset)) {
      return;
    }
    RuleApplyNode ruleNode = new RuleApplyNode(parent, fs, ts);
    parent.addChild(ruleNode);

    Feature feature = ruleApplyType.getFeatureByBaseName(RULES);
    ArrayFS value = (ArrayFS) fs.getFeatureValue(feature);
    FeatureStructure[] fsarray = value.toArray();

    MatchedRootNode matched = new MatchedRootNode(ruleNode, ts);
    FailedRootNode failed = new FailedRootNode(ruleNode, ts);
    ruleNode.addChild(matched);
    ruleNode.addChild(failed);

    for (FeatureStructure eachRuleMatch : fsarray) {
      Feature f = eachRuleMatch.getType().getFeatureByBaseName(MATCHED);
      boolean matchedValue = eachRuleMatch.getBooleanValue(f);
      if (matchedValue) {
        buildTree(eachRuleMatch, matched, ts, offset, onlyRules);
      } else {
        buildTree(eachRuleMatch, failed, ts, offset, onlyRules);
      }

      Feature df = eachRuleMatch.getType().getFeatureByBaseName(DELEGATES);
      if (df != null) {
        ArrayFS dv = (ArrayFS) eachRuleMatch.getFeatureValue(df);
        if (dv != null) {
          FeatureStructure[] da = dv.toArray();
          for (FeatureStructure delegateFS : da) {
            buildTree(delegateFS, ruleNode, ts, offset, onlyRules);
          }
        }
      }

    }
  }

  private void processRuleMatch(AnnotationFS fs, IExplainTreeNode parent, TypeSystem ts,
          int offset, boolean onlyRules) {
    if (offset >= 0 && (fs.getBegin() >= offset || fs.getEnd() <= offset)) {
      return;
    }
    RuleMatchNode matchNode = new RuleMatchNode(parent, fs, ts);
    parent.addChild(matchNode);

    RuleElementRootNode remRoot = new RuleElementRootNode(matchNode, ts);
    matchNode.addChild(remRoot);

    Feature feature = ruleMatchType.getFeatureByBaseName(ELEMENTS);
    ArrayFS value = (ArrayFS) fs.getFeatureValue(feature);
    FeatureStructure[] fsarray = value.toArray();
    for (FeatureStructure each : fsarray) {
      buildTree(each, remRoot, ts, offset, onlyRules);
    }
  }

  private void processRuleElementMatches(FeatureStructure fs, IExplainTreeNode parent,
          TypeSystem ts, int offset, boolean onlyRules) {
    RuleElementMatchesNode remsNode = new RuleElementMatchesNode(parent, fs, ts);
    parent.addChild(remsNode);

    Feature feature = ruleElementMatchesType.getFeatureByBaseName(MATCHES);
    ArrayFS value = (ArrayFS) fs.getFeatureValue(feature);
    FeatureStructure[] fsarray = value.toArray();
    for (FeatureStructure each : fsarray) {
      buildTree(each, remsNode, ts, offset, onlyRules);
    }
  }

  private void processRuleElementMatch(AnnotationFS fs, IExplainTreeNode parent, TypeSystem ts,
          int offset, boolean onlyRules) {
    if (offset >= 0 && (fs.getBegin() >= offset || fs.getEnd() <= offset)) {
      return;
    }
    RuleElementMatchNode remNode = new RuleElementMatchNode(parent, fs, ts);
    parent.addChild(remNode);

    Feature feature = ruleElementMatchType.getFeatureByBaseName(BASE_CONDITION);
    FeatureStructure base = fs.getFeatureValue(feature);
    buildTree(base, remNode, ts, offset, onlyRules);

    feature = ruleElementMatchType.getFeatureByBaseName(CONDITIONS);
    ArrayFS value = (ArrayFS) fs.getFeatureValue(feature);
    FeatureStructure[] fsarray = value.toArray();
    for (FeatureStructure each : fsarray) {
      buildTree(each, remNode, ts, offset, onlyRules);
    }

    feature = fs.getType().getFeatureByBaseName(ExplainTree.ELEMENTS);
    value = (ArrayFS) fs.getFeatureValue(feature);
    if (value != null) {
      fsarray = value.toArray();
      for (FeatureStructure each : fsarray) {
        buildTree(each, remNode, ts, offset, onlyRules);
      }
    }
  }

  private void processEvaluatedCondition(FeatureStructure fs, IExplainTreeNode parent,
          TypeSystem ts, int offset, boolean onlyRules) {
    ConditionNode condNode = new ConditionNode(parent, fs, ts);
    parent.addChild(condNode);

    Feature feature = evaluatedConditionType.getFeatureByBaseName(CONDITIONS);
    ArrayFS value = (ArrayFS) fs.getFeatureValue(feature);
    if (value != null) {
      FeatureStructure[] fsarray = value.toArray();
      for (FeatureStructure each : fsarray) {
        buildTree(each, condNode, ts, offset, onlyRules);
      }
    }
  }

  private void prune(IExplainTreeNode node) {
    List<IExplainTreeNode> children = node.getChildren();
    IExplainTreeNode parent = node.getParent();
    for (IExplainTreeNode each : new ArrayList<IExplainTreeNode>(children)) {
      prune(each);
    }
    if (node instanceof ApplyRootNode) {
    } else if (node instanceof BlockApplyNode) {
    } else if (node instanceof ConditionNode) {
    } else if (node instanceof FailedRootNode) {
      if (!node.hasChildren()) {
        parent.removeChild(node);
      }
    } else if (node instanceof MatchedRootNode) {
      if (!node.hasChildren()) {
        parent.removeChild(node);
      }
    } else if (node instanceof RuleApplyNode) {
      if (!node.hasChildren()) {
        parent.removeChild(node);
      }
    } else if (node instanceof RuleElementMatchesNode) {
    } else if (node instanceof RuleElementMatchNode) {
    } else if (node instanceof RuleElementRootNode) {
    } else if (node instanceof RuleMatchNode) {
    }
  }
}
