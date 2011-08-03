package org.apache.uima.tm.cev.data.tree;

import org.apache.uima.cas.Type;

public class CEVTypeTreeNode extends CEVAbstractTreeNode {

  private Type type;

  public CEVTypeTreeNode(Type type) {
    this(null, type);
  }

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
