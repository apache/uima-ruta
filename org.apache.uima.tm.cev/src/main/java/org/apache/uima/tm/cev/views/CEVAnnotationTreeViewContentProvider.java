package org.apache.uima.tm.cev.views;

import org.apache.uima.tm.cev.data.tree.ICEVRootTreeNode;
import org.apache.uima.tm.cev.data.tree.ICEVTreeNode;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;


/**
 * ContentProvider fuer den TreeView
 * 
 * @author Marco Nehmeier
 */
public class CEVAnnotationTreeViewContentProvider implements ITreeContentProvider {

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang. Object)
   */
  public Object[] getChildren(Object element) {
    if (element instanceof ICEVTreeNode)
      return ((ICEVTreeNode) element).getChildren();
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object )
   */
  public Object getParent(Object element) {
    if (element instanceof ICEVTreeNode)
      return ((ICEVTreeNode) element).getParent();

    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang. Object)
   */
  public boolean hasChildren(Object element) {
    return (element instanceof ICEVTreeNode) && ((ICEVTreeNode) element).hasChildren();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java .lang.Object)
   */
  public Object[] getElements(Object inputElement) {
    if (inputElement instanceof ICEVRootTreeNode)
      return ((ICEVRootTreeNode) inputElement).getChildren();

    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.IContentProvider#dispose()
   */
  public void dispose() {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface .viewers.Viewer,
   * java.lang.Object, java.lang.Object)
   */
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
  }
}
