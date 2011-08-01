package org.apache.uima.tm.cev.data.tree;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.uima.cas.Type;

/**
 * Interface fuer eine TreeNode fuer die Anzeige von Typen/Annotationen in den Views
 * 
 * @author Marco Nehmeier
 */
public interface ICEVTreeNode {

  /**
   * Fuegt ein Node als Kind hinzu
   * 
   * @param child
   *          Node
   */
  public void addChild(ICEVTreeNode child);

  /**
   * Gibt alle Kindknoten zurueck
   * 
   * @return Nodes
   */
  public ICEVTreeNode[] getChildren();

  /**
   * Gibt einen Iterator ueber die Kinder zurueck
   * 
   * @return Iterator
   */
  public Iterator<ICEVTreeNode> getChildrenIterator();

  /**
   * Gibt die Stringrep. des Nodes zurueck
   * 
   * @return Stringrep.
   */
  public String getName();

  /**
   * Gibt den Eltern-Node zurueck
   * 
   * @return Node
   */
  public ICEVTreeNode getParent();

  /**
   * Sind Kinder vorhanden?
   * 
   * @return Kinder ja/nein
   */
  public boolean hasChildren();

  /**
   * Gibt den Typ zurueck
   * 
   * @return Typ
   */
  public Type getType();

  /**
   * Fuellt eine Liste mit allen Nodes aus dem (Teil)Baum in In-Order-Reihenfolge
   * 
   * @param list
   *          Liste
   */
  public void getNodes(LinkedList<ICEVTreeNode> list);

  /**
   * Sortiert die Kinder anhand eines Comparators
   * 
   * @param cp
   *          Comparator
   */
  public void sort(Comparator<ICEVTreeNode> cp);
}
