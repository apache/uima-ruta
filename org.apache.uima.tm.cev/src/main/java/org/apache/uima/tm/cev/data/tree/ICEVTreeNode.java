package org.apache.uima.tm.cev.data.tree;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.uima.cas.Type;

public interface ICEVTreeNode {

  public void addChild(ICEVTreeNode child);

  public ICEVTreeNode[] getChildren();

  public Iterator<ICEVTreeNode> getChildrenIterator();

  public String getName();

  public ICEVTreeNode getParent();

  public boolean hasChildren();

  public Type getType();

  public void getNodes(LinkedList<ICEVTreeNode> list);

  public void sort(Comparator<ICEVTreeNode> cp);
}
