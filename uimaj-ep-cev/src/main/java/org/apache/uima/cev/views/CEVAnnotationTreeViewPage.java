/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.cev.views;

import java.util.HashSet;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cev.CEVPlugin;
import org.apache.uima.cev.data.CEVData;
import org.apache.uima.cev.data.CEVDocument;
import org.apache.uima.cev.data.ICEVAnnotationListener;
import org.apache.uima.cev.data.tree.CEVAnnotationTreeNode;
import org.apache.uima.cev.data.tree.CEVFeatureTreeNode;
import org.apache.uima.cev.data.tree.CEVTypeTreeNode;
import org.apache.uima.cev.data.tree.ICEVRootTreeNode;
import org.apache.uima.cev.data.tree.ICEVTreeNode;
import org.apache.uima.cev.editor.CEVViewer;
import org.apache.uima.cev.extension.ICEVView;
import org.apache.uima.cev.preferences.CEVPreferenceConstants;
import org.eclipse.core.runtime.Preferences.IPropertyChangeListener;
import org.eclipse.core.runtime.Preferences.PropertyChangeEvent;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.Page;

public abstract class CEVAnnotationTreeViewPage extends Page implements ICEVViewPage,
        ICEVAnnotationListener, MouseListener, IDoubleClickListener, IPropertyChangeListener,
        ICEVView, Listener {

  private class ToolTipListener implements Listener {

    private static final String TOOLTIP_TEXT_END = "\nEnd: ";

    private static final String TOOLTIP_TEXT_BEGIN = "Begin: ";

    private Shell tip = null;

    private Label label = null;

    private Tree tree;

    private ToolTipListener(Tree tree) {
      this.tree = tree;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets .Event)
     */
    public void handleEvent(Event event) {
      switch (event.type) {
        case SWT.Dispose:
        case SWT.KeyDown:
        case SWT.MouseMove: {
          if (tip == null)
            break;
          tip.dispose();
          tip = null;
          label = null;
          break;
        }
        case SWT.MouseHover: {
          TreeItem item = tree.getItem(new Point(event.x, event.y));

          if (item != null && item.getData() instanceof CEVAnnotationTreeNode) {
            if (tip != null && !tip.isDisposed())
              tip.dispose();

            tip = new Shell(Display.getCurrent().getActiveShell(), SWT.ON_TOP | SWT.NO_FOCUS
                    | SWT.TOOL);

            tip.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
            FillLayout layout = new FillLayout();
            layout.marginWidth = 2;
            tip.setLayout(layout);

            label = new Label(tip, SWT.NONE);
            label.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_INFO_FOREGROUND));
            label.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_INFO_BACKGROUND));

            AnnotationFS annot = ((CEVAnnotationTreeNode) item.getData()).getAnnotation();

            label.setText(TOOLTIP_TEXT_BEGIN + annot.getBegin() + TOOLTIP_TEXT_END + annot.getEnd());

            Point size = tip.computeSize(SWT.DEFAULT, SWT.DEFAULT);
            Rectangle rect = item.getBounds(0);
            Point pt = tree.toDisplay(rect.x, rect.y);
            tip.setBounds(pt.x, pt.y, size.x, size.y);
            tip.setVisible(true);
          }
        }
      }
    }
  }

  protected CEVViewer casView;

  private CheckboxTreeViewer treeView;

  private CEVDocument casDocument;

  private CEVData casData;

  private CEVAnnotationTreeViewLableProvider lableProvider;

  protected Text filterTextField;

  protected String manualTypeFilter = "";

  private Composite overlay;

  public CEVAnnotationTreeViewPage(CEVViewer casView, CEVDocument casDocument, int index) {
    super();
    this.casView = casView;
    this.casDocument = casDocument;

    this.casData = casDocument.getCASData(index);
    casData.addAnnotationListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#init(org.eclipse.ui.part.IPageSite)
   */
  @Override
  public void init(IPageSite pageSite) {
    super.init(pageSite);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#createControl(org.eclipse.swt.widgets.Composite)
   */
  @Override
  public void createControl(Composite parent) {
    this.overlay = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.horizontalSpacing = 0;
    layout.verticalSpacing = 0;
    layout.marginWidth = 0;
    layout.marginHeight = 0;
    overlay.setLayout(layout);

    filterTextField = new Text(overlay, SWT.SINGLE | SWT.BORDER);
    GridData gd = new GridData();
    gd.grabExcessHorizontalSpace = true;
    gd.horizontalAlignment = GridData.FILL;
    gd.horizontalSpan = 1;
    filterTextField.setLayoutData(gd);
    filterTextField.setToolTipText("Retain types that contain...");
    filterTextField.addListener(SWT.KeyUp, this);
    filterTextField.addListener(SWT.MouseUp, this);
    filterTextField.addListener(SWT.Modify, this);

    treeView = new CheckboxTreeViewer(overlay, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
    gd = new GridData(GridData.FILL_BOTH);
    treeView.getTree().setLayoutData(gd);
    treeView.setContentProvider(new CEVAnnotationTreeViewContentProvider());
    lableProvider = new CEVAnnotationTreeViewLableProvider(casData);
    treeView.setLabelProvider(lableProvider);
    treeView.addCheckStateListener(casData);
    treeView.addDoubleClickListener(this);
    treeView.getTree().addMouseListener(this);

    ToolTipListener tl = new ToolTipListener(treeView.getTree());

    treeView.getTree().addListener(SWT.Dispose, tl);
    treeView.getTree().addListener(SWT.KeyDown, tl);
    treeView.getTree().addListener(SWT.MouseMove, tl);
    treeView.getTree().addListener(SWT.MouseHover, tl);
    int ops = DND.DROP_COPY | DND.DROP_MOVE;
    Transfer[] transfers = new Transfer[] { TextTransfer.getInstance() };
    treeView.addDragSupport(ops, transfers, new CEVAnnotationTreeViewDragListener(treeView));

    CEVPlugin.getDefault().getPluginPreferences().addPropertyChangeListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#dispose()
   */
  @Override
  public void dispose() {
    super.dispose();
    overlay.dispose();
    CEVPlugin.getDefault().getPluginPreferences().removePropertyChangeListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#getControl()
   */
  @Override
  public Control getControl() {
    return overlay;
  }

  public CheckboxTreeViewer getTreeViewer() {
    return treeView;
  }

  public CEVData getCasData() {
    return casData;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#setFocus()
   */
  @Override
  public void setFocus() {
    overlay.setFocus();
  }

  public void viewChanged(int newIndex) {
    treeView.removeCheckStateListener(casData);
    casData.removeAnnotationListener(this);
    casData = casDocument.getCASData(newIndex);
    treeView.addCheckStateListener(casData);
    casData.addAnnotationListener(this);
    lableProvider.setCASData(casData);
    treeView.setInput(null);

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.uniwue.casviewer.data.ICASAnnotationStateListener#annotationStateChanged
   * (org.apache.uima.cas.Type)
   */
  public void annotationStateChanged(Type type) {
    if (treeView.getInput() == null)
      return;

    Object input = treeView.getInput();

    if (input instanceof ICEVRootTreeNode)
      for (ICEVTreeNode n : ((ICEVRootTreeNode) input).getNodes(type))
        if (n instanceof CEVAnnotationTreeNode)
          treeView.setChecked(n, casData.isChecked(((CEVAnnotationTreeNode) n).getAnnotation()));
        else if (n instanceof CEVTypeTreeNode) {
          treeView.setGrayed(n, casData.isGrayed(type));
          treeView.setChecked(n, casData.isChecked(type));
        }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.uniwue.casviewer.data.ICASAnnotationStateListener#annotationStateChanged
   * (org.apache.uima.cas.text.AnnotationFS)
   */
  public void annotationStateChanged(AnnotationFS annot) {
    if (treeView.getInput() == null)
      return;

    Object input = treeView.getInput();

    if (input instanceof ICEVRootTreeNode)
      for (ICEVTreeNode n : ((ICEVRootTreeNode) input).getNodes(annot.getType())) {
        if (n instanceof CEVAnnotationTreeNode
                && ((CEVAnnotationTreeNode) n).getAnnotation() == annot) {
          treeView.setChecked(n, casData.isChecked(annot));

        } else if (n instanceof CEVTypeTreeNode && annot.getType() == n.getType()) {
          if (casData.isGrayed(n.getType()))
            treeView.setGrayChecked(n, true);
          else if (casData.isChecked(n.getType())) {
            treeView.setGrayed(n, false);
            treeView.setChecked(n, true);
          } else {
            treeView.setGrayChecked(n, false);
          }
        }
      }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse
   * .jface.viewers.DoubleClickEvent)
   */
  public void doubleClick(DoubleClickEvent event) {
    if (event.getSelection() != null && event.getSelection() instanceof ITreeSelection) {
      Object treeNode = ((ITreeSelection) event.getSelection()).getFirstElement();
      if (treeNode instanceof CEVAnnotationTreeNode) {
        casView.moveToAnnotation(((CEVAnnotationTreeNode) treeNode).getAnnotation());
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.uniwue.casviewer.data.ICASAnnotationColorListener#colorChanged(org
   * .apache.uima.cas.Type)
   */
  public void colorChanged(Type type) {
    treeView.refresh();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.swt.events.MouseListener#mouseDoubleClick(org.eclipse.swt .events.MouseEvent)
   */
  public void mouseDoubleClick(MouseEvent e) {
  }

  public void deleteSelectedAnnotations() {
    if (MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), "Confirm Delete",
            "Are you sure you want to delete these items?")) {

      TreeItem[] items = treeView.getTree().getSelection();
      HashSet<AnnotationFS> annots = new HashSet<AnnotationFS>();

      for (TreeItem it : items) {
        if (it.getData() instanceof CEVAnnotationTreeNode) {
          CEVAnnotationTreeNode annot = (CEVAnnotationTreeNode) it.getData();
          annots.add(annot.getAnnotation());
        } else if (it.getData() instanceof CEVTypeTreeNode) {
          CEVTypeTreeNode type = (CEVTypeTreeNode) it.getData();

          for (Object child : type.getChildren()) {
            if (child instanceof CEVAnnotationTreeNode) {
              CEVAnnotationTreeNode annot = (CEVAnnotationTreeNode) child;
              annots.add(annot.getAnnotation());
            }
          }
        }
      }

      casData.removeAnnotations(annots.toArray(new AnnotationFS[annots.size()]));
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.swt.events.MouseListener#mouseDown(org.eclipse.swt.events .MouseEvent)
   */
  public void mouseDown(final MouseEvent mouseEvent) {
    if (mouseEvent.button == 3) {
      Display display = Display.getCurrent();
      Menu menu = new Menu(display.getActiveShell(), SWT.POP_UP);
      MenuItem itemFgC = new MenuItem(menu, SWT.PUSH);

      itemFgC.setText("Change Font Color");
      itemFgC.addListener(SWT.Selection, new Listener() {
        public void handleEvent(Event e) {
          TreeItem item = treeView.getTree().getItem(new Point(mouseEvent.x, mouseEvent.y));

          if (item != null && item.getData() instanceof ICEVTreeNode) {
            Type type = ((ICEVTreeNode) item.getData()).getType();
            ColorDialog cd = new ColorDialog(Display.getCurrent().getActiveShell());
            cd.setRGB(casData.getForegroundColor(type).getRGB());

            RGB rgb = cd.open();

            if (rgb != null)
              casData.setForegroundColor(type, new Color(Display.getCurrent(), rgb));
          }
        }
      });

      MenuItem itemBgC = new MenuItem(menu, SWT.PUSH);
      itemBgC.setText("Change Background Color");
      itemBgC.addListener(SWT.Selection, new Listener() {
        public void handleEvent(Event e) {
          TreeItem item = treeView.getTree().getItem(new Point(mouseEvent.x, mouseEvent.y));

          if (item != null && item.getData() instanceof ICEVTreeNode) {
            Type type = ((ICEVTreeNode) item.getData()).getType();

            ColorDialog cd = new ColorDialog(Display.getCurrent().getActiveShell());
            cd.setRGB(casData.getBackgroundColor(type).getRGB());

            RGB rgb = cd.open();

            if (rgb != null)
              casData.setBackgroundColor(type, new Color(Display.getCurrent(), rgb));
          }
        }
      });

      TreeItem item = treeView.getTree().getItem(new Point(mouseEvent.x, mouseEvent.y));
      if (item != null && item.getData() instanceof CEVFeatureTreeNode) {
        itemBgC.setEnabled(false);
        itemFgC.setEnabled(false);
      }

      new MenuItem(menu, SWT.SEPARATOR);

      MenuItem itemDelA = new MenuItem(menu, SWT.PUSH);
      itemDelA.setText("Delete selected Items");
      itemDelA.addListener(SWT.Selection, new Listener() {
        public void handleEvent(Event e) {
          deleteSelectedAnnotations();
        }
      });

      itemDelA.setEnabled(false);
      TreeItem[] items = treeView.getTree().getSelection();
      for (TreeItem ti : items)
        if (!(ti.getData() instanceof CEVFeatureTreeNode)) {
          itemDelA.setEnabled(true);
          break;
        }

      menu.setVisible(true);

      while (!menu.isDisposed() && menu.isVisible()) {
        if (!display.readAndDispatch())
          display.sleep();
      }
      menu.dispose();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @seeorg.eclipse.swt.events.MouseListener#mouseUp(org.eclipse.swt.events. MouseEvent)
   */
  public void mouseUp(MouseEvent e) {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.ICEVAnnotationListener#annotationAdded(org.apache
   * .uima.cas.text.AnnotationFS)
   */
  public abstract void annotationsAdded(List<AnnotationFS> annots);

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.ICEVAnnotationListener#annotationRemoved(org.apache
   * .uima.cas.text.AnnotationFS)
   */
  public abstract void annotationsRemoved(List<AnnotationFS> annots);

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.core.runtime.Preferences$IPropertyChangeListener#propertyChange
   * (org.eclipse.core.runtime.Preferences.PropertyChangeEvent)
   */
  public void propertyChange(PropertyChangeEvent event) {
    if (event.getProperty().equals(CEVPreferenceConstants.P_ANNOTATION_REPR)) {
      lableProvider.setTextRepr();
      treeView.refresh();
    }
  }

  public void casChanged(CEVDocument casDocument) {
    this.casDocument = casDocument;
  }

}
