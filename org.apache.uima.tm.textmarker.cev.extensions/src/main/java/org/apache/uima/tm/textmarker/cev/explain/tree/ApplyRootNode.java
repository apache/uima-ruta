package org.apache.uima.tm.textmarker.cev.explain.tree;

import org.apache.uima.cas.TypeSystem;

public class ApplyRootNode extends ExplainAbstractTreeNode implements ExplainRootNode {

  public ApplyRootNode(IExplainTreeNode parent, TypeSystem typeSystem) {
    super(parent, null, typeSystem);
  }

  @Override
  public String toString() {
    return "Applied";
  }
}
