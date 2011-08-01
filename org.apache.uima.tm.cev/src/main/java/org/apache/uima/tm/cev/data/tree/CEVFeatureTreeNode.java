package org.apache.uima.tm.cev.data.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;

/**
 * Node fuer ein Feature
 * 
 * @author Marco Nehmeier
 * 
 */
public class CEVFeatureTreeNode implements ICEVTreeNode {

  // Elternknoten
  private ICEVTreeNode parent;

  // Feature
  private Feature f;

  // Wert
  private String value;

  // Liste mit Kinderknoten
  private ArrayList<ICEVTreeNode> children;

  /**
   * Konstruktor
   * 
   * @param parent
   *          Elternknoten
   * @param f
   *          Feature
   * @param value
   *          Wert
   */
  public CEVFeatureTreeNode(ICEVTreeNode parent, Feature f, String value) {
    this.parent = parent;
    this.f = f;
    this.value = value;
    this.children = new ArrayList<ICEVTreeNode>();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#addChild(org.apache.uima.tm.cev.data .tree.ICEVTreeNode)
   */
  public void addChild(ICEVTreeNode child) {
    this.children.add(child);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#getChildren()
   */
  public ICEVTreeNode[] getChildren() {
    return this.children.toArray(new ICEVTreeNode[] {});
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#getChildrenIterator()
   */
  public Iterator<ICEVTreeNode> getChildrenIterator() {
    return this.children.iterator();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#getName()
   */
  public String getName() {
    return f.getShortName() + " : " + value;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#getNodes(java.util.LinkedList)
   */
  public void getNodes(LinkedList<ICEVTreeNode> list) {
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
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#getType()
   */
  public Type getType() {
    return f.getRange();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#hasChildren()
   */
  public boolean hasChildren() {
    if (children.size() > 0) {
      return true;
    }
    return false;
  }

  public Feature getFeature() {
    return this.f;
  }

  public String getValue() {
    return value;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#sort(java.util.Comparator)
   */
  public void sort(Comparator<ICEVTreeNode> cp) {
    // nothing to do
  }

}
