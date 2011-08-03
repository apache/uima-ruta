package org.apache.uima.tm.cev.views.annotationBrowser;

import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.cev.CEVPlugin;
import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.data.tree.CEVAnnotationTreeNode;
import org.apache.uima.tm.cev.data.tree.CEVTypeTreeNode;
import org.apache.uima.tm.cev.data.tree.ICEVRootTreeNode;
import org.apache.uima.tm.cev.data.tree.ICEVTreeNode;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.preferences.CEVPreferenceConstants;
import org.apache.uima.tm.cev.views.CEVAnnotationTreeViewPage;
import org.eclipse.core.runtime.Preferences.PropertyChangeEvent;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

public class CEVAnnotationBrowserPage extends CEVAnnotationTreeViewPage implements
        ICEVAnnotationBrowserPage {

  private enum State {
    AnnotationOrdered, TypeOrdered
  }

  private State state;

  private Image icon;

  public CEVAnnotationBrowserPage(CEVViewer casView, CEVDocument casDocument, int index) {
    super(casView, casDocument, index);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.uniwue.casviewer.views.CASAnnotationTreeViewPage#createControl(org
   * .eclipse.swt.widgets.Composite)
   */
  @Override
  public void createControl(Composite parent) {
    super.createControl(parent);

    getTreeViewer().setInput(getCasData().getTypeOrderedTree(""));

    getTreeViewer().getControl().addKeyListener(new KeyAdapter() {

      @Override
      public void keyPressed(KeyEvent e) {
        int keyCode = e.keyCode;
        // backspace or delete
        if (keyCode == SWT.BS || keyCode == SWT.DEL) {
          deleteSelectedAnnotations();
        }
      }

    });

    setTreeOrder();

    List<Type> initialVisibleTypes = casView.getInitialVisibleTypes();
    for (Type type : initialVisibleTypes) {
      this.annotationStateChanged(type);
    }
  }

  private void setTreeOrder() {
    IPreferenceStore store = CEVPlugin.getDefault().getPreferenceStore();
    String treeOrder = store.getString(CEVPreferenceConstants.P_ANNOTATION_BROWSER_TREE_ORDER);

    if (treeOrder.equals(CEVPreferenceConstants.P_ANNOTATION_BROWSER_TREE_ORDER_TYPE))
      state = State.TypeOrdered;
    else if (treeOrder.equals(CEVPreferenceConstants.P_ANNOTATION_BROWSER_TREE_ORDER_ANNOT))
      state = State.AnnotationOrdered;
    else
      state = State.TypeOrdered;
  }

  private void updateSelection() {
    // Selektionen aktuallisieren
    Object input = getTreeViewer().getInput();

    if (input instanceof ICEVRootTreeNode)
      for (ICEVTreeNode n : ((ICEVRootTreeNode) input).getNodes())
        if (n instanceof CEVAnnotationTreeNode) {
          boolean checked = getCasData().isChecked(((CEVAnnotationTreeNode) n).getAnnotation());
          if (checked) {
            getTreeViewer().setChecked(n, true);
          }
        } else if (n instanceof CEVTypeTreeNode) {
          if (getCasData().isGrayed(n.getType()))
            getTreeViewer().setGrayChecked(n, true);
          else if (getCasData().isChecked(n.getType())) {
            getTreeViewer().setGrayed(n, false);
            getTreeViewer().setChecked(n, true);
          } else {
            getTreeViewer().setGrayChecked(n, false);
          }
        }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.views.CEVAnnotationTreeViewPage#casDataChanged(int)
   */
  @Override
  public void viewChanged(int index) {
    super.viewChanged(index);
    reloadTree();
  }

  private void reloadTree() {
    if (state == State.TypeOrdered) {
      getTreeViewer().setInput(getCasData().getTypeOrderedTree(manualTypeFilter));
    } else if (state == State.AnnotationOrdered) {
      getTreeViewer().setInput(getCasData().getAnnotationOrderedTree(manualTypeFilter));
    }
    updateSelection();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.uniwue.casviewer.views.CASAnnotationTreeViewPage#dispose()
   */
  @Override
  public void dispose() {
    if (icon != null)
      icon.dispose();

    super.dispose();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.views.CEVAnnotationTreeViewPage#annotationAdded(org.
   * apache.uima.cas.text.AnnotationFS)
   */
  @Override
  public void annotationsAdded(List<AnnotationFS> annots) {
    reloadTree();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.views.CEVAnnotationTreeViewPage#annotationRemoved(org
   * .apache.uima.cas.text.AnnotationFS)
   */
  @Override
  public void annotationsRemoved(List<AnnotationFS> annots) {
    reloadTree();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.views.CEVAnnotationTreeViewPage#propertyChange(org.eclipse
   * .core.runtime.Preferences.PropertyChangeEvent)
   */
  @Override
  public void propertyChange(PropertyChangeEvent event) {
    super.propertyChange(event);
    if (event.getProperty().equals(CEVPreferenceConstants.P_ANNOTATION_BROWSER_TREE_ORDER)) {
      setTreeOrder();
      reloadTree();
    }
  }

  public void newSelection(int offset) {

  }

  public void handleEvent(Event event) {
    if (event.widget == filterTextField && event.type == SWT.Modify) {
      manualTypeFilter = filterTextField.getText();
      reloadTree();
    }
  }

}