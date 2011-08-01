package org.apache.uima.tm.textmarker.cev.explain.failed;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.textmarker.cev.explain.tree.IExplainTreeNode;
import org.apache.uima.tm.textmarker.cev.explain.tree.RuleMatchNode;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;


public class FailedTreeContentProvider implements ITreeContentProvider {

  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof IExplainTreeNode) {
      List<Object> result = new ArrayList<Object>();
      IExplainTreeNode debugNode = (IExplainTreeNode) parentElement;
      for (IExplainTreeNode each : debugNode.getChildren()) {
        if (each instanceof RuleMatchNode) {
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

  public Object[] getElements(Object element) {
    return getChildren(element);
  }

  public boolean hasChildren(Object parentElement) {
    return false;
  }

  public void dispose() {
  }

  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
  }

}
