package org.apache.uima.tm.cev.views;

import org.apache.uima.tm.cev.data.tree.CEVAnnotationTreeNode;
import org.apache.uima.tm.cev.data.tree.CEVFeatureTreeNode;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;


public class CEVAnnotationTreeViewDragListener extends DragSourceAdapter {
  private StructuredViewer viewer;

  public CEVAnnotationTreeViewDragListener(StructuredViewer viewer) {
    this.viewer = viewer;
  }

  public void dragFinished(DragSourceEvent event) {
    if (!event.doit)
      return;

  }

  public void dragSetData(DragSourceEvent event) {
    IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
    Object[] list = selection.toList().toArray();
    for (Object object : list) {
      if (object instanceof CEVFeatureTreeNode) {
        event.data = ((CEVFeatureTreeNode) object).getValue();
      } else if (object instanceof CEVAnnotationTreeNode) {
        event.data = ((CEVAnnotationTreeNode) object).getAnnotation().getCoveredText();
      }
    }
  }

  /**
   * Method declared on DragSourceListenerTransthorakale Echokardiographie:linksseitige
   * Herzhöhlen:linker Vorhof:dilatiert:leichtgradig
   */
  public void dragStart(DragSourceEvent event) {
    event.doit = !viewer.getSelection().isEmpty();
  }
}