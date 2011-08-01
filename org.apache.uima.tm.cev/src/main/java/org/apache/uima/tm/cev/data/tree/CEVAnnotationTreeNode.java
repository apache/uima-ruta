package org.apache.uima.tm.cev.data.tree;

import org.apache.uima.cas.text.AnnotationFS;

public class CEVAnnotationTreeNode extends CEVFSTreeNode implements ICEVAnnotationNode {

  public CEVAnnotationTreeNode(AnnotationFS annotation) {
    super(annotation);
  }

  public CEVAnnotationTreeNode(ICEVTreeNode parent, AnnotationFS annotation) {
    super(parent, annotation);
  }

  public AnnotationFS getAnnotation() {
    return (AnnotationFS) fs;
  }

  @Override
  public String getName() {
    return getAnnotation().getCoveredText();
  }
}
