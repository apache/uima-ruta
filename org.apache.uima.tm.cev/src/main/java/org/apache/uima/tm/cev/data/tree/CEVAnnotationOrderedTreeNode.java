package org.apache.uima.tm.cev.data.tree;

import java.util.Iterator;
import java.util.LinkedList;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;

public class CEVAnnotationOrderedTreeNode extends CEVAnnotationTreeNode {

  public CEVAnnotationOrderedTreeNode(ICEVTreeNode parent, AnnotationFS annotation) {
    super(parent, annotation);
  }

  public void insertFS(FeatureStructure fs) {
    boolean processed = false;

    if (!(fs instanceof AnnotationFS))
      return;

    AnnotationFS annotation = (AnnotationFS) fs;
    Iterator<ICEVTreeNode> iter = getChildrenIterator();

    while (iter.hasNext()) {
      CEVAnnotationOrderedTreeNode childNode = (CEVAnnotationOrderedTreeNode) iter.next();

      if (isChild(childNode, annotation)) {
        childNode.insertFS(annotation);
        processed = true;
      }
    }

    if (!processed) {
      CEVAnnotationOrderedTreeNode node = new CEVAnnotationOrderedTreeNode(this, annotation);
      addChild(node);
    }
  }

  private boolean isChild(CEVAnnotationOrderedTreeNode node, AnnotationFS annotation) {
    return (node.getAnnotation().getBegin() < annotation.getBegin() && node.getAnnotation()
            .getEnd() >= annotation.getEnd())
            || (node.getAnnotation().getBegin() <= annotation.getBegin() && node.getAnnotation()
                    .getEnd() > annotation.getEnd());
  }

  public void getNodes(LinkedList<ICEVTreeNode> list, Type type) {
    Iterator<ICEVTreeNode> iter = getChildrenIterator();

    while (iter.hasNext()) {
      CEVAnnotationOrderedTreeNode node = (CEVAnnotationOrderedTreeNode) iter.next();

      if (node.getType().equals(type))
        list.add(node);

      node.getNodes(list, type);
    }
  }

  public void getNodes(LinkedList<ICEVTreeNode> list, AnnotationFS annot) {
    Iterator<ICEVTreeNode> iter = getChildrenIterator();

    while (iter.hasNext()) {
      CEVAnnotationOrderedTreeNode node = (CEVAnnotationOrderedTreeNode) iter.next();

      if (node.getAnnotation().equals(annot))
        list.add(node);
      else if (isChild(node, annot))
        node.getNodes(list, annot);
    }
  }

}
