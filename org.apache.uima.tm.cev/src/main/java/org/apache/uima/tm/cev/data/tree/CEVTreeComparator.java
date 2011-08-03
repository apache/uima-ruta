package org.apache.uima.tm.cev.data.tree;

public class CEVTreeComparator implements java.util.Comparator<ICEVTreeNode> {

  /*
   * (non-Javadoc)
   * 
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  public int compare(ICEVTreeNode o1, ICEVTreeNode o2) {
    return o1.getName().compareToIgnoreCase(o2.getName());
  }
}
