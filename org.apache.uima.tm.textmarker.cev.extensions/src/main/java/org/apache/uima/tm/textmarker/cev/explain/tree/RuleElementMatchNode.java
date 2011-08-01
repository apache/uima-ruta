package org.apache.uima.tm.textmarker.cev.explain.tree;

import org.apache.uima.cas.ArrayFS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.cev.data.tree.ICEVAnnotationNode;


public class RuleElementMatchNode extends ExplainAbstractTreeNode implements IEvaluatedNode,
        ICEVAnnotationNode {

  private boolean matched;

  public RuleElementMatchNode(IExplainTreeNode parent, FeatureStructure fs, TypeSystem ts) {
    super(parent, fs, ts);
    Feature f = fs.getType().getFeatureByBaseName(ExplainTree.BASE_CONDITION);
    FeatureStructure baseFS = fs.getFeatureValue(f);
    Feature baseFeat = baseFS.getType().getFeatureByBaseName(ExplainTree.VALUE);
    matched = baseFS.getBooleanValue(baseFeat);

    f = fs.getType().getFeatureByBaseName(ExplainTree.CONDITIONS);
    ArrayFS value = (ArrayFS) fs.getFeatureValue(f);
    FeatureStructure[] fsarray = value.toArray();
    for (FeatureStructure each : fsarray) {
      Feature eachFeat = each.getType().getFeatureByBaseName(ExplainTree.VALUE);
      boolean eachValue = each.getBooleanValue(eachFeat);
      matched &= eachValue;
    }
  }

  public boolean matched() {
    return matched;
  }

  public AnnotationFS getAnnotation() {
    FeatureStructure fs = getFeatureStructure();
    if (fs instanceof AnnotationFS) {
      return (AnnotationFS) fs;
    }
    return null;
  }

}
