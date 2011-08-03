package org.apache.uima.tm.cev.data.tree;

import java.util.LinkedList;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;

public class CEVAnnotationOrderedRootTreeNode extends CEVAnnotationOrderedTreeNode implements
        ICEVRootTreeNode {

  public CEVAnnotationOrderedRootTreeNode() {
    super(null, null);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.CEVAnnotationTreeNode#getName()
   */
  @Override
  public String getName() {
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.CEVAnnotationTreeNode#getType()
   */
  @Override
  public Type getType() {
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.CEVAnnotationTreeNode#getAnnotation()
   */
  @Override
  public AnnotationFS getAnnotation() {
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVRootTreeNode#getNodes()
   */
  public LinkedList<ICEVTreeNode> getNodes() {
    LinkedList<ICEVTreeNode> list = new LinkedList<ICEVTreeNode>();

    getNodes(list);

    return list;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVRootTreeNode#getNodes(org.apache.uima.cas.Type)
   */
  public LinkedList<ICEVTreeNode> getNodes(Type type) {
    LinkedList<ICEVTreeNode> list = new LinkedList<ICEVTreeNode>();

    getNodes(list, type);

    return list;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.apache.uima.tm.cev.data.tree.ICEVRootTreeNode#getNodes(org.apache.uima.cas.text.AnnotationFS
   * )
   */
  public LinkedList<ICEVTreeNode> getNodes(AnnotationFS annot) {
    LinkedList<ICEVTreeNode> list = new LinkedList<ICEVTreeNode>();

    getNodes(list, annot);

    return list;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVRootTreeNode#sort()
   */
  public void sort() {
    sort(new CEVTreeComparator());
  }
}
