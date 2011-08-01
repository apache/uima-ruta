package org.apache.uima.tm.textmarker.cev.explain.tree;

import org.apache.uima.cas.TypeSystem;

public class MatchedRootNode extends ExplainAbstractTreeNode implements ExplainRootNode {

  public MatchedRootNode(IExplainTreeNode parent, TypeSystem ts) {
    super(parent, null, ts);
  }

  @Override
  public String toString() {
    return "Matched";
  }
}
