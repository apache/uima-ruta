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


/**
 * Page fuer den AnnotationBrowserView
 * 
 * @author Marco Nehmeier
 */
public class CEVAnnotationBrowserPage extends CEVAnnotationTreeViewPage implements
        ICEVAnnotationBrowserPage {

  // Enum fuer den Zustand des Baums
  private enum State {
    // Nach Anfangs und Endposition dern Annotation
    AnnotationOrdered,
    // Nach Annotations-Typen
    TypeOrdered
  }

  // Zustand des Baums
  private State state;

  // Icon der Action
  private Image icon;

  /**
   * Konstruktor
   * 
   * @param casView
   *          CASViewer
   * @param casData
   *          CASData
   */
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

    // Annotationen im Baum anzeigen .... TypeOrdered und Zustand setzen
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

  /**
   * Baumordnung festlegen
   */
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

  /**
   * Aus Performance-gruenden abgeschaltet
   */
  // /*
  // * (non-Javadoc)
  // *
  // * @see org.eclipse.ui.part.Page#setActionBars(org.eclipse.ui.IActionBars)
  // */
  // public void setActionBars(IActionBars actionBars) {
  // // Action zum Baumveraendern erzeugen
  // Action treeChangeAction = new Action() {
  // public void run() {
  // // Baum umsortieren
  // if (state == State.TypeOrdered) {
  // // getTreeViewer().setInput(getCasData().getAnnotationOrderedTree());
  // state = State.AnnotationOrdered;
  // } else if (state == State.AnnotationOrdered) {
  // // getTreeViewer().setInput(getCasData().getTypeOrderedTree());
  // state = State.TypeOrdered;
  // }
  //
  // // updateSelektion();
  // reloadTree();
  // }
  // };
  //
  // // ToolTip
  // treeChangeAction.setToolTipText("Change TreeView-Order");
  //
  // // Icon laden
  // InputStream in = getClass().getResourceAsStream(
  // "/icons/hierarchicalLayout.gif");
  // if (in != null) {
  // try {
  // icon = new Image(Display.getCurrent(), in);
  // } catch (SWTException e) {
  // if (e.code != SWT.ERROR_INVALID_IMAGE) {
  // throw e;
  // // fall through otherwise
  // }
  // } finally {
  // try {
  // in.close();
  // } catch (IOException e) {
  // }
  // }
  // }
  //
  // // Icon setzen
  // treeChangeAction.setImageDescriptor(
  // ImageDescriptor.createFromImage(icon));
  //
  // // Action in die ToolBar setzen
  // actionBars.getToolBarManager().add(treeChangeAction);
  // }
  /**
   * Selektion aktuallisieren
   */
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

  /**
   * Baum neu laden
   */
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
    // Icon freigeben
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