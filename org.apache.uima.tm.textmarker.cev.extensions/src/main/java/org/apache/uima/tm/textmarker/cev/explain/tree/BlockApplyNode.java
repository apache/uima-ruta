package org.apache.uima.tm.textmarker.cev.explain.tree;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.TypeSystem;

public class BlockApplyNode extends ExplainAbstractTreeNode {

  private RuleApplyNode blockRuleNode;

  public RuleApplyNode getBlockRuleNode() {
    return blockRuleNode;
  }

  public BlockApplyNode(IExplainTreeNode parent, FeatureStructure fs, TypeSystem ts) {
    super(parent, fs, ts);
  }

  public void setBlockRuleApply(RuleApplyNode ruleNode) {
    this.blockRuleNode = ruleNode;
  }

}
