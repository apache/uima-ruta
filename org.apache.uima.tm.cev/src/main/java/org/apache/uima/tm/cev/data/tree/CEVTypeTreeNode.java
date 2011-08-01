package org.apache.uima.tm.cev.data.tree;

import org.apache.uima.cas.Type;

/**
 * Ein Node fuer einen Type
 * 
 * @author Marco Nehmeier
 */
public class CEVTypeTreeNode extends CEVAbstractTreeNode {

  // der Typ
  private Type type;

  /**
   * Konstruktor
   * 
   * @param type
   *          Type
   */
  public CEVTypeTreeNode(Type type) {
    this(null, type);
  }

  /**
   * Konstruktor
   * 
   * @param parent
   *          Elternknoten
   * @param type
   *          Type
   */
  public CEVTypeTreeNode(ICEVTreeNode parent, Type type) {
    super(parent);
    this.type = type;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#getName()
   */
  public String getName() {
    return type.getName();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#getType()
   */
  public Type getType() {
    return type;
  }
}
