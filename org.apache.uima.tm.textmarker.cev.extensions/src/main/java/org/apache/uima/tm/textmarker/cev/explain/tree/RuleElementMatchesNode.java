package org.apache.uima.tm.textmarker.cev.explain.tree;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.TypeSystem;

public class RuleElementMatchesNode extends ExplainAbstractTreeNode implements IEvaluatedNode {

  public RuleElementMatchesNode(IExplainTreeNode parent, FeatureStructure fs, TypeSystem ts) {
    super(parent, fs, ts);
  }

  public boolean matched() {
    boolean result = true;
    for (IExplainTreeNode each : getChildren()) {
      if (each instanceof IEvaluatedNode) {
        result &= ((IEvaluatedNode) each).matched();
      }
    }
    return result;
  }

}
