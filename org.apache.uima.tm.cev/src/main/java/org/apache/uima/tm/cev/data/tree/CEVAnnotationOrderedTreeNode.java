package org.apache.uima.tm.cev.data.tree;

import java.util.Iterator;
import java.util.LinkedList;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;

/**
 * Node fuer eine Annotation fuer Baum mit Annotationsordnung
 * 
 * @author Marco Nehmeier
 */
public class CEVAnnotationOrderedTreeNode extends CEVAnnotationTreeNode {

  /**
   * Konstruktor
   * 
   * @param parent
   *          Elternknoten
   * @param annotation
   *          Annotation
   */
  public CEVAnnotationOrderedTreeNode(ICEVTreeNode parent, AnnotationFS annotation) {
    super(parent, annotation);
  }

  /**
   * Annotation einfuegen
   * 
   * @param annotation
   *          Annotation
   */
  public void insertFS(FeatureStructure fs) {
    boolean processed = false;

    if (!(fs instanceof AnnotationFS))
      return;

    AnnotationFS annotation = (AnnotationFS) fs;
    Iterator<ICEVTreeNode> iter = getChildrenIterator();

    // ueber alle Annotationen und Position suchen
    while (iter.hasNext()) {
      CEVAnnotationOrderedTreeNode childNode = (CEVAnnotationOrderedTreeNode) iter.next();

      // Position gefunden
      if (isChild(childNode, annotation)) {
        childNode.insertFS(annotation);
        processed = true;
      }
    }

    // Wenn nicht gefunden
    if (!processed) {
      CEVAnnotationOrderedTreeNode node = new CEVAnnotationOrderedTreeNode(this, annotation);
      addChild(node);
    }
  }

  /**
   * Pruefen ob Annotation im Bereich von Node liegt
   * 
   * @param node
   *          Node
   * @param annotation
   *          Annotation
   * @return ja/nein
   */
  private boolean isChild(CEVAnnotationOrderedTreeNode node, AnnotationFS annotation) {
    return (node.getAnnotation().getBegin() < annotation.getBegin() && node.getAnnotation()
            .getEnd() >= annotation.getEnd())
            || (node.getAnnotation().getBegin() <= annotation.getBegin() && node.getAnnotation()
                    .getEnd() > annotation.getEnd());
  }

  /**
   * Gibt die Knoten eines Typs in der Liste zurueck
   * 
   * @param list
   *          Liste
   * @param type
   *          Typ
   */
  public void getNodes(LinkedList<ICEVTreeNode> list, Type type) {
    Iterator<ICEVTreeNode> iter = getChildrenIterator();

    // Typ suchen
    while (iter.hasNext()) {
      CEVAnnotationOrderedTreeNode node = (CEVAnnotationOrderedTreeNode) iter.next();

      if (node.getType().equals(type))
        list.add(node);

      node.getNodes(list, type);
    }
  }

  /**
   * Gibt die Knoten einer Annotation in der Liste zurueck
   * 
   * @param list
   *          Liste
   * @param annot
   *          Annotation
   */
  public void getNodes(LinkedList<ICEVTreeNode> list, AnnotationFS annot) {
    Iterator<ICEVTreeNode> iter = getChildrenIterator();

    // Annotation suchen
    while (iter.hasNext()) {
      CEVAnnotationOrderedTreeNode node = (CEVAnnotationOrderedTreeNode) iter.next();

      if (node.getAnnotation().equals(annot))
        list.add(node);
      else if (isChild(node, annot))
        node.getNodes(list, annot);
    }
  }

}
