package org.apache.uima.tm.textmarker.cev.explain.tree;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.TypeSystem;

public class RuleApplyNode extends ExplainAbstractTreeNode {

  private FailedRootNode failedNode;

  private MatchedRootNode matchedNode;

  public RuleApplyNode(IExplainTreeNode parent, FeatureStructure fs, TypeSystem ts) {
    super(parent, fs, ts);
  }

  @Override
  public void addChild(IExplainTreeNode node) {
    super.addChild(node);
    if (node instanceof FailedRootNode) {
      failedNode = (FailedRootNode) node;
    } else if (node instanceof MatchedRootNode) {
      matchedNode = (MatchedRootNode) node;
    }
  }

  public FailedRootNode getFailedNode() {
    return failedNode;
  }

  public MatchedRootNode getMatchedChild() {
    return matchedNode;
  }

}
