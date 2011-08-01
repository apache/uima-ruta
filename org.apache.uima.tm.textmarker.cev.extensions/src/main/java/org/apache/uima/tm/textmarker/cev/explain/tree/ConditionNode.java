package org.apache.uima.tm.textmarker.cev.explain.tree;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.TypeSystem;

public class ConditionNode extends ExplainAbstractTreeNode implements IEvaluatedNode {

  private boolean matched;

  public ConditionNode(IExplainTreeNode parent, FeatureStructure fs, TypeSystem ts) {
    super(parent, fs, ts);
    Feature f = fs.getType().getFeatureByBaseName(ExplainTree.VALUE);
    matched = fs.getBooleanValue(f);
  }

  public boolean matched() {
    return matched;
  }

}
