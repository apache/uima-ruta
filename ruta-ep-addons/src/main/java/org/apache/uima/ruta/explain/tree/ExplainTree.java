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

package org.apache.uima.ruta.explain.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.ruta.type.DebugBlockApply;
import org.apache.uima.ruta.type.DebugEvaluatedCondition;
import org.apache.uima.ruta.type.DebugInlinedBlock;
import org.apache.uima.ruta.type.DebugRuleApply;
import org.apache.uima.ruta.type.DebugRuleElementMatch;
import org.apache.uima.ruta.type.DebugRuleElementMatches;
import org.apache.uima.ruta.type.DebugRuleMatch;
import org.apache.uima.ruta.type.DebugScriptApply;

public class ExplainTree {

  private IExplainTreeNode root;

  public ExplainTree(JCas jcas) {
    this(jcas, -1);
  }

  public ExplainTree(JCas jcas, int offset) {
    this(jcas, offset, false);
    if (offset >= 0) {
      prune(root);
    }
  }

  public ExplainTree(JCas jcas, int offset, boolean onlyRules) {
    createTree(jcas, offset, onlyRules);
  }

  public IExplainTreeNode getRoot() {
    return root;
  }

  private void createTree(JCas jcas, int offset, boolean onlyRules) {
    TypeSystem ts = jcas.getTypeSystem();
    List<DebugScriptApply> scriptApplies = new ArrayList<>(
            JCasUtil.select(jcas, DebugScriptApply.class));
    // sort by creation time
    Collections.sort(scriptApplies, new Comparator<DebugScriptApply>() {

      @Override
      public int compare(DebugScriptApply o1, DebugScriptApply o2) {
        long l1 = o1.getTimestamp();
        long l2 = o2.getTimestamp();
        return Long.compare(l1, l2);
      }
    });

    root = new ApplyRootNode(null, ts);

    for (DebugScriptApply scriptApply : scriptApplies) {

      buildTree(scriptApply, root, ts, offset, onlyRules);
    }
  }

  private void buildTree(FeatureStructure fs, IExplainTreeNode parent, TypeSystem ts, int offset,
          boolean onlyRules) {
    if (fs instanceof DebugBlockApply) {
      processBlockApply((DebugBlockApply) fs, parent, ts, offset, onlyRules);
    } else if (fs instanceof DebugRuleApply) {
      processRuleApply((DebugRuleApply) fs, parent, ts, offset, onlyRules);
    } else if (fs instanceof DebugRuleMatch) {
      processRuleMatch((DebugRuleMatch) fs, parent, ts, offset, onlyRules);
    } else if (fs instanceof DebugRuleElementMatches) {
      processRuleElementMatches((DebugRuleElementMatches) fs, parent, ts, offset, onlyRules);
    } else if (fs instanceof DebugRuleElementMatch) {
      processRuleElementMatch((DebugRuleElementMatch) fs, parent, ts, offset, onlyRules);
    } else if (fs instanceof DebugEvaluatedCondition) {
      processEvaluatedCondition((DebugEvaluatedCondition) fs, parent, ts, offset, onlyRules);
    }
  }

  private void buildInlinedBlock(boolean asCondition, DebugInlinedBlock debugInlinedBlock,
          IExplainTreeNode parent, TypeSystem ts, int offset, boolean onlyRules) {
    InlinedRuleBlockNode inlinedBlockNode = new InlinedRuleBlockNode(parent, debugInlinedBlock,
            debugInlinedBlock.getAsCondition(), debugInlinedBlock.getMatched(), ts);
    parent.addChild(inlinedBlockNode);

    FSArray<DebugScriptApply> inlinedRules = debugInlinedBlock.getInlinedRules();
    if (inlinedRules != null) {
      for (FeatureStructure each : inlinedRules) {
        buildTree(each, inlinedBlockNode, ts, offset, onlyRules);
      }
    }
  }

  private void processBlockApply(DebugBlockApply fs, IExplainTreeNode parent, TypeSystem ts,
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

    if (fs.getInnerApply() != null) {
      for (FeatureStructure each : fs.getInnerApply()) {
        if (!onlyRules) {
          buildTree(each, blockNode, ts, offset, onlyRules);
        } else {
          buildTree(each, parent, ts, offset, onlyRules);
        }
      }
    }
  }

  private void processBlockRuleApply(DebugBlockApply fs, BlockApplyNode parent, TypeSystem ts,
          int offset, boolean onlyRules) {
    if (offset >= 0 && (fs.getBegin() >= offset || fs.getEnd() <= offset)) {
      return;
    }
    RuleApplyNode ruleNode = new RuleApplyNode(parent, fs, ts);
    parent.setBlockRuleApply(ruleNode);

    MatchedRootNode matched = new MatchedRootNode(ruleNode, ts);
    FailedRootNode failed = new FailedRootNode(ruleNode, ts);
    ruleNode.addChild(matched);
    ruleNode.addChild(failed);

    if (fs.getRules() != null) {
      for (FeatureStructure each : fs.getRules()) {
        DebugRuleMatch eachRuleMatch = (DebugRuleMatch) each;
        boolean matchedValue = eachRuleMatch.getMatched();
        if (matchedValue) {
          buildTree(eachRuleMatch, matched, ts, offset, onlyRules);
        } else {
          buildTree(eachRuleMatch, failed, ts, offset, onlyRules);
        }
        if (eachRuleMatch.getDelegates() != null) {
          for (FeatureStructure delegateFS : eachRuleMatch.getDelegates()) {
            buildTree(delegateFS, ruleNode, ts, offset, onlyRules);
          }
        }
      }
    }

  }

  private void processRuleApply(DebugRuleApply fs, IExplainTreeNode parent, TypeSystem ts,
          int offset, boolean onlyRules) {
    if (offset >= 0 && (fs.getBegin() >= offset || fs.getEnd() <= offset)) {
      return;
    }
    RuleApplyNode ruleNode = new RuleApplyNode(parent, fs, ts);
    parent.addChild(ruleNode);

    MatchedRootNode matched = new MatchedRootNode(ruleNode, ts);
    FailedRootNode failed = new FailedRootNode(ruleNode, ts);
    ruleNode.addChild(matched);
    ruleNode.addChild(failed);

    if (fs.getRules() != null) {
      for (FeatureStructure each : fs.getRules()) {
        DebugRuleMatch eachRuleMatch = (DebugRuleMatch) each;
        boolean matchedValue = eachRuleMatch.getMatched();
        if (matchedValue) {
          buildTree(eachRuleMatch, matched, ts, offset, onlyRules);
        } else {
          buildTree(eachRuleMatch, failed, ts, offset, onlyRules);
        }
        if (eachRuleMatch.getDelegates() != null) {
          for (FeatureStructure delegateFS : eachRuleMatch.getDelegates()) {
            buildTree(delegateFS, ruleNode, ts, offset, onlyRules);
          }
        }
      }
      if (fs.getRules().size() == 1) {
        mergeInlinedRuleBlockNodesOfChildren(ruleNode);
      }
    }
  }

  private void processRuleMatch(DebugRuleMatch fs, IExplainTreeNode parent, TypeSystem ts,
          int offset, boolean onlyRules) {
    if (offset >= 0 && (fs.getBegin() >= offset || fs.getEnd() <= offset)) {
      return;
    }
    RuleMatchNode matchNode = new RuleMatchNode(parent, fs, ts);
    parent.addChild(matchNode);

    RuleElementRootNode remRoot = new RuleElementRootNode(matchNode, ts);
    matchNode.addChild(remRoot);

    if (fs.getElements() != null) {
      for (FeatureStructure each : fs.getElements()) {
        buildTree(each, remRoot, ts, offset, onlyRules);
      }
    }
    mergeInlinedRuleBlockNodesOfChildren(matchNode);
  }

  private void processRuleElementMatches(DebugRuleElementMatches fs, IExplainTreeNode parent,
          TypeSystem ts, int offset, boolean onlyRules) {
    RuleElementMatchesNode remsNode = new RuleElementMatchesNode(parent, fs, ts);
    parent.addChild(remsNode);

    if (fs.getMatches() != null) {
      for (FeatureStructure each : fs.getMatches()) {
        buildTree(each, remsNode, ts, offset, onlyRules);
      }
    }

    FSArray<DebugInlinedBlock> inlinedActionBlocks = fs.getInlinedActionBlocks();
    if (inlinedActionBlocks != null) {
      InlinedRootNode inlinedRootNode = new InlinedRootNode(remsNode, ts);
      remsNode.setInlined(inlinedRootNode);
      for (FeatureStructure each : inlinedActionBlocks) {
        if (each instanceof DebugInlinedBlock) {
          buildInlinedBlock(false, (DebugInlinedBlock) each, inlinedRootNode, ts, offset,
                  onlyRules);
        }
      }
    }
    mergeInlinedRuleBlockNodesOfChildren(remsNode);
  }

  private void processRuleElementMatch(DebugRuleElementMatch fs, IExplainTreeNode parent,
          TypeSystem ts, int offset, boolean onlyRules) {
    if (offset >= 0 && (fs.getBegin() >= offset || fs.getEnd() <= offset)) {
      return;
    }
    RuleElementMatchNode remNode = new RuleElementMatchNode(parent, fs, ts);
    parent.addChild(remNode);

    DebugEvaluatedCondition baseCondition = fs.getBaseCondition();
    buildTree(baseCondition, remNode, ts, offset, onlyRules);

    if (fs.getConditions() != null) {
      for (FeatureStructure each : fs.getConditions()) {
        buildTree(each, remNode, ts, offset, onlyRules);
      }
    }
    if (fs.getElements() != null) {
      for (FeatureStructure each : fs.getElements()) {
        buildTree(each, remNode, ts, offset, onlyRules);
      }
    }
    FSArray<DebugInlinedBlock> inlinedConditionBlocks = fs.getInlinedConditionBlocks();
    if (inlinedConditionBlocks != null) {
      InlinedRootNode inlinedRootNode = new InlinedRootNode(remNode, ts);
      remNode.setInlined(inlinedRootNode);
      for (FeatureStructure each : inlinedConditionBlocks) {
        if (each instanceof DebugInlinedBlock) {
          buildInlinedBlock(true, (DebugInlinedBlock) each, inlinedRootNode, ts, offset, onlyRules);
        }
      }
    }
    mergeInlinedRuleBlockNodesOfChildren(remNode);
  }

  private void processEvaluatedCondition(DebugEvaluatedCondition fs, IExplainTreeNode parent,
          TypeSystem ts, int offset, boolean onlyRules) {
    ConditionNode condNode = new ConditionNode(parent, fs, ts);
    parent.addChild(condNode);

    if (fs.getConditions() != null) {
      for (FeatureStructure each : fs.getConditions()) {
        buildTree(each, condNode, ts, offset, onlyRules);
      }
    }
  }

  private void mergeInlinedRuleBlockNodesOfChildren(ExplainAbstractTreeNode parent) {

    List<IExplainTreeNode> list = new ArrayList<>();

    for (IExplainTreeNode each : parent.getChildren()) {
      collectInlinedBlockNodes(each, list);
    }
    InlinedRootNode inlinedRootNode = parent.getInlined();
    if (inlinedRootNode == null && !list.isEmpty()) {
      inlinedRootNode = new InlinedRootNode(parent, parent.getTypeSystem());
      parent.setInlined(inlinedRootNode);
    }

    if (inlinedRootNode != null) {
      for (IExplainTreeNode each : list) {
        if (!inlinedRootNode.getChildren().contains(each)) {
          inlinedRootNode.addChild(each);
        }
      }
    }
  }

  private void collectInlinedBlockNodes(IExplainTreeNode node, List<IExplainTreeNode> result) {
    if (node.getInlined() != null && node.getInlined().hasChildren()) {
      // already collected down to this level
      result.addAll(node.getInlined().getChildren());
      return;
    }

    List<IExplainTreeNode> children = node.getChildren();
    for (IExplainTreeNode each : children) {
      collectInlinedBlockNodes(each, result);
    }
  }

  private void prune(IExplainTreeNode node) {
    if (node == null) {
      return;
    }
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
