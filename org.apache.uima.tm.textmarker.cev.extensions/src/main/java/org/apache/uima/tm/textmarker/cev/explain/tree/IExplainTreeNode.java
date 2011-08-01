package org.apache.uima.tm.textmarker.cev.explain.tree;

import java.util.List;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.TypeSystem;

public interface IExplainTreeNode {

  IExplainTreeNode getParent();

  List<IExplainTreeNode> getChildren();

  boolean hasChildren();

  void addChild(IExplainTreeNode node);

  boolean removeChild(IExplainTreeNode node);

  FeatureStructure getFeatureStructure();

  TypeSystem getTypeSystem();
}