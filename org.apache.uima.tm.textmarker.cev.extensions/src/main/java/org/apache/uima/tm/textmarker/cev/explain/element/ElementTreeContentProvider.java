package org.apache.uima.tm.textmarker.cev.explain.element;

import org.apache.uima.tm.textmarker.cev.explain.tree.IExplainTreeNode;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;


public class ElementTreeContentProvider implements ITreeContentProvider {

  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof IExplainTreeNode) {
      IExplainTreeNode debugNode = (IExplainTreeNode) parentElement;
      return debugNode.getChildren().toArray();
    }
    return null;
  }

  public Object getParent(Object element) {
    if (element instanceof IExplainTreeNode) {
      return ((IExplainTreeNode) element).getParent();
    }
    return null;

  }

  public Object[] getElements(Object element) {
    return getChildren(element);
  }

  public boolean hasChildren(Object parentElement) {
    if (parentElement instanceof IExplainTreeNode) {
      IExplainTreeNode debugNode = (IExplainTreeNode) parentElement;
      return debugNode.hasChildren();
    }
    return false;
  }

  public void dispose() {
  }

  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
  }

}
