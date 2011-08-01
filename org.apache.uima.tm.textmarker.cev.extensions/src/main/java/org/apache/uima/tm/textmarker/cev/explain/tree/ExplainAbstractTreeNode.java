package org.apache.uima.tm.textmarker.cev.explain.tree;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.TypeSystem;

public abstract class ExplainAbstractTreeNode implements IExplainTreeNode {

  private IExplainTreeNode parent;

  private List<IExplainTreeNode> children;

  private FeatureStructure fs;

  private TypeSystem ts;

  public ExplainAbstractTreeNode(IExplainTreeNode parent, FeatureStructure fs, TypeSystem ts) {
    this.parent = parent;
    this.children = new ArrayList<IExplainTreeNode>();
    this.fs = fs;
    this.ts = ts;
  }

  public IExplainTreeNode getParent() {
    return parent;
  }

  public List<IExplainTreeNode> getChildren() {
    return children;
  }

  public boolean hasChildren() {
    if (children.size() > 0) {
      return true;
    }
    return false;
  }

  public void addChild(IExplainTreeNode child) {
    children.add(child);
  }

  public boolean removeChild(IExplainTreeNode child) {
    return children.remove(child);
  }

  public FeatureStructure getFeatureStructure() {
    return fs;
  }

  public TypeSystem getTypeSystem() {
    return ts;
  }

  @Override
  public String toString() {
    return fs.toString();
  }

}
