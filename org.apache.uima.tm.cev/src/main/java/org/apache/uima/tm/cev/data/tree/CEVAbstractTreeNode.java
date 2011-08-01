package org.apache.uima.tm.cev.data.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Abstrakter Node
 * 
 * @author Marco Nehmeier
 */
public abstract class CEVAbstractTreeNode implements ICEVTreeNode {

  // Elternknoten
  private ICEVTreeNode parent;

  // Kinderknoten
  private ArrayList<ICEVTreeNode> children;

  /**
   * Konstruktor
   */
  public CEVAbstractTreeNode() {
    this(null);
  }

  /**
   * Konstruktor
   * 
   * @param parent
   *          Elternknoten
   */
  public CEVAbstractTreeNode(ICEVTreeNode parent) {
    this.parent = parent;
    children = new ArrayList<ICEVTreeNode>();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#addChild(org.apache.uima.tm.cev.data.tree.ICEVTreeNode)
   */
  public void addChild(ICEVTreeNode child) {
    children.add(child);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#getChildren()
   */
  public ICEVTreeNode[] getChildren() {
    return children.toArray(new ICEVTreeNode[] {});
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#getChildrenIterator()
   */
  public Iterator<ICEVTreeNode> getChildrenIterator() {
    return children.iterator();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#getParent()
   */
  public ICEVTreeNode getParent() {
    return parent;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#hasChildren()
   */
  public boolean hasChildren() {
    return children.size() > 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#getNodes(java.util.LinkedList)
   */
  public void getNodes(LinkedList<ICEVTreeNode> list) {
    Iterator<ICEVTreeNode> iter = getChildrenIterator();

    // ueber die Knoten iterieren
    while (iter.hasNext()) {
      ICEVTreeNode node = iter.next();

      list.add(node);

      node.getNodes(list);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#sort(java.util.Comparator)
   */
  public void sort(Comparator<ICEVTreeNode> cp) {
    Collections.sort(children, cp);

    // for (ICEVTreeNode node : children)
    // node.sort(cp);
  }
}
