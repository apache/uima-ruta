package org.apache.uima.tm.textmarker.cev.explain.tree;

import org.apache.uima.cas.TypeSystem;

public class FailedRootNode extends ExplainAbstractTreeNode implements ExplainRootNode {

  public FailedRootNode(IExplainTreeNode parent, TypeSystem ts) {
    super(parent, null, ts);
  }

  @Override
  public String toString() {
    return "Failed";
  }
}
