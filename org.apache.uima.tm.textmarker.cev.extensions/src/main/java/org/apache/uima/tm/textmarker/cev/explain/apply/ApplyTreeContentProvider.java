package org.apache.uima.tm.textmarker.cev.explain.apply;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.textmarker.cev.explain.tree.ExplainRootNode;
import org.apache.uima.tm.textmarker.cev.explain.tree.IExplainTreeNode;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;


public class ApplyTreeContentProvider implements ITreeContentProvider {

  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof IExplainTreeNode) {
      List<Object> result = new ArrayList<Object>();
      IExplainTreeNode debugNode = (IExplainTreeNode) parentElement;
      for (IExplainTreeNode each : debugNode.getChildren()) {
        if (!(each instanceof ExplainRootNode)) {
          result.add(each);
        }
      }
      return result.toArray();
    }
    return null;
  }

  public Object getParent(Object element) {
    if (element instanceof IExplainTreeNode) {
      return ((IExplainTreeNode) element).getParent();
    }
    return null;
  }

  public Object[] getElements(Object parentElement) {
    return getChildren(parentElement);
  }

  public boolean hasChildren(Object parentElement) {
    if (parentElement instanceof IExplainTreeNode) {
      IExplainTreeNode debugNode = (IExplainTreeNode) parentElement;
      for (Object each : debugNode.getChildren()) {
        if (!(each instanceof ExplainRootNode)) {
          return true;
        }
      }
    }
    return false;
  }

  public void dispose() {
  }

  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
  }

}
