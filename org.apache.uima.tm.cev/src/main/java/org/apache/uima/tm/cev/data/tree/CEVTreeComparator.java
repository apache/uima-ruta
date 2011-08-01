package org.apache.uima.tm.cev.data.tree;

/**
 * Comparator fuer TreeNodes. Vergleicht den Namen fuer eine alphabetische Sortierung
 * 
 * @author Marco Nehmeier
 */
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
