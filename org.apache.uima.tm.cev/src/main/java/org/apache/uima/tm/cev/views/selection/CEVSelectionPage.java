package org.apache.uima.tm.cev.views.selection;

import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.data.ICEVAnnotationSelectionListener;
import org.apache.uima.tm.cev.data.tree.CEVAnnotationTreeNode;
import org.apache.uima.tm.cev.data.tree.CEVTypeTreeNode;
import org.apache.uima.tm.cev.data.tree.ICEVRootTreeNode;
import org.apache.uima.tm.cev.data.tree.ICEVTreeNode;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.views.CEVAnnotationTreeViewPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;


/**
 * Page fuer einen SelectionView im CASViewer
 * 
 * @author Marco Nehmeier
 */
public class CEVSelectionPage extends CEVAnnotationTreeViewPage implements ICEVSelectionPage,
        ICEVAnnotationSelectionListener {

  private int offset;

  /**
   * Konstruktor
   * 
   * @param casView
   *          CASViewer
   * @param casData
   *          CASDatat
   */
  public CEVSelectionPage(CEVViewer casView, CEVDocument casDocument, int index) {
    super(casView, casDocument, index);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.uniwue.casviewer.data.ICASAnnotationSelectionListener#newSelection (int)
   */
  public void newSelection(int offset) {
    this.offset = offset;

    // Selektionen im Tree aktualisieren
    ICEVRootTreeNode annotationOrderedTree = getCasData().getAnnotationOrderedTree(offset,
            manualTypeFilter);
    getTreeViewer().setInput(annotationOrderedTree);

    Object input = getTreeViewer().getInput();

    if (input instanceof ICEVRootTreeNode)
      for (ICEVTreeNode n : ((ICEVRootTreeNode) input).getNodes())
        if (n instanceof CEVAnnotationTreeNode)
          getTreeViewer().setChecked(n,
                  getCasData().isChecked(((CEVAnnotationTreeNode) n).getAnnotation()));
        else if (n instanceof CEVTypeTreeNode) {
          if (getCasData().isGrayed(n.getType()))
            getTreeViewer().setGrayChecked(n, true);
          else if (getCasData().isChecked(n.getType())) {
            getTreeViewer().setGrayed(n, false);
            getTreeViewer().setChecked(n, true);
          } else {
            getTreeViewer().setGrayChecked(n, false);
          }
        }

    // getTreeViewer().expandAll();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.views.CEVAnnotationTreeViewPage#annotationAdded(org.
   * apache.uima.cas.text.AnnotationFS)
   */
  @Override
  public void annotationsAdded(List<AnnotationFS> annots) {
    newSelection(offset);
    // getTreeViewer().getTree().removeAll();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.views.CEVAnnotationTreeViewPage#annotationRemoved(org
   * .apache.uima.cas.text.AnnotationFS)
   */
  @Override
  public void annotationsRemoved(List<AnnotationFS> annots) {
    newSelection(offset);
    // getTreeViewer().getTree().removeAll();
  }

  public void handleEvent(Event event) {
    if (event.widget == filterTextField && event.type == SWT.Modify) {
      manualTypeFilter = filterTextField.getText();
      newSelection(offset);
    }
  }
}