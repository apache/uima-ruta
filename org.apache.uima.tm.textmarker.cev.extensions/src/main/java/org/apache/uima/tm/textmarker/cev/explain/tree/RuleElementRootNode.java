package org.apache.uima.tm.textmarker.cev.explain.tree;

import org.apache.uima.cas.TypeSystem;

public class RuleElementRootNode extends ExplainAbstractTreeNode implements ExplainRootNode {

  public RuleElementRootNode(IExplainTreeNode parent, TypeSystem ts) {
    super(parent, null, ts);
  }

  @Override
  public String toString() {
    return "Element";
  }
}
