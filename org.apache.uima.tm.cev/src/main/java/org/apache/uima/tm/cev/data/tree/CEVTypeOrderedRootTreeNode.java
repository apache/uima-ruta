package org.apache.uima.tm.cev.data.tree;

import java.util.Iterator;
import java.util.LinkedList;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;

public class CEVTypeOrderedRootTreeNode extends CEVAbstractTreeNode implements ICEVRootTreeNode {

  public CEVTypeOrderedRootTreeNode() {
    super();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#getName()
   */
  public String getName() {
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.tree.ICEVTreeNode#getType()
   */
  public Type getType() {
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.apache.uima.tm.cev.data.tree.ICEVRootTreeNode#insertAnnotation(org.apache.uima.cas.text
   * .AnnotationFS )
   */
  public void insertFS(FeatureStructure fs) {
    // TODO hotfix for explanation types...
    if (fs.getType().getShortName().equals("DebugBlockApply")
            || fs.getType().getShortName().equals("DebugMatchedRuleMatch")
            || fs.getType().getShortName().equals("DebugFailedRuleMatch")
            || fs.getType().getShortName().equals("DebugScriptApply")
            || fs.getType().getShortName().equals("DebugRuleElementMatches")
            || fs.getType().getShortName().equals("DebugRuleElementMatch")) {
      return;
    }

    Iterator<ICEVTreeNode> iter = getChildrenIterator();

    while (iter.hasNext()) {
      CEVTypeTreeNode typeNode = (CEVTypeTreeNode) iter.next();

      if (typeNode.getType().equals(fs.getType())) {
        CEVFSTreeNode node = createFSNode(typeNode, fs);
        typeNode.addChild(node);
        return;
      }
    }

    CEVTypeTreeNode typeNode = new CEVTypeTreeNode(this, fs.getType());
    addChild(typeNode);

    CEVFSTreeNode node = createFSNode(typeNode, fs);
    typeNode.addChild(node);
  }

  private CEVFSTreeNode createFSNode(ICEVTreeNode parent, FeatureStructure fs) {
    if (fs instanceof AnnotationFS) {
      return new CEVAnnotationTreeNode(parent, (AnnotationFS) fs);
    }
    return new CEVFSTreeNode(parent, fs);
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
    Iterator<ICEVTreeNode> iter = getChildrenIterator();

    LinkedList<ICEVTreeNode> list = new LinkedList<ICEVTreeNode>();

    while (iter.hasNext()) {
      CEVTypeTreeNode typeNode = (CEVTypeTreeNode) iter.next();

      if (typeNode.getType().equals(type)) {
        Iterator<ICEVTreeNode> children = typeNode.getChildrenIterator();

        list.add(typeNode);

        while (children.hasNext())
          list.add(children.next());
      }
    }

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
    Iterator<ICEVTreeNode> iter = getChildrenIterator();

    LinkedList<ICEVTreeNode> list = new LinkedList<ICEVTreeNode>();

    while (iter.hasNext()) {
      CEVTypeTreeNode typeNode = (CEVTypeTreeNode) iter.next();

      if (typeNode.getType().equals(annot.getType())) {
        Iterator<ICEVTreeNode> children = typeNode.getChildrenIterator();

        while (children.hasNext()) {
          CEVAnnotationTreeNode node = (CEVAnnotationTreeNode) children.next();

          if (node.getAnnotation().equals(annot)) {
            list.add(node);
            return list;
          }
        }
      }
    }

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
