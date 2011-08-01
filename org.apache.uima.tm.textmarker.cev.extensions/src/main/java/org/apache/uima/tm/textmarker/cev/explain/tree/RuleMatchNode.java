package org.apache.uima.tm.textmarker.cev.explain.tree;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.cev.data.tree.ICEVAnnotationNode;


public class RuleMatchNode extends ExplainAbstractTreeNode implements ICEVAnnotationNode {

  public RuleMatchNode(IExplainTreeNode parent, FeatureStructure fs, TypeSystem ts) {
    super(parent, fs, ts);
  }

  public AnnotationFS getAnnotation() {
    FeatureStructure fs = getFeatureStructure();
    if (fs instanceof AnnotationFS) {
      return (AnnotationFS) fs;
    }
    return null;
  }

}
