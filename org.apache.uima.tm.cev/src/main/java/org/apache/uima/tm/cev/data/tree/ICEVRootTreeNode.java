package org.apache.uima.tm.cev.data.tree;

import java.util.LinkedList;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;

public interface ICEVRootTreeNode extends ICEVTreeNode {

  public void insertFS(FeatureStructure annotation);

  public LinkedList<ICEVTreeNode> getNodes();

  public LinkedList<ICEVTreeNode> getNodes(Type type);

  public LinkedList<ICEVTreeNode> getNodes(AnnotationFS annot);

  public void sort();
}
