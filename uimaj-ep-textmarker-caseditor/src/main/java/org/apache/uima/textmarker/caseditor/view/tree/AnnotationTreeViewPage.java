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

package org.apache.uima.textmarker.caseditor.view.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.caseditor.editor.AnnotationEditor;
import org.apache.uima.caseditor.editor.AnnotationStyle;
import org.apache.uima.caseditor.editor.AnnotationStyleChangeListener;
import org.apache.uima.caseditor.editor.IAnnotationEditorModifyListener;
import org.apache.uima.caseditor.editor.ICasDocument;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.Page;

public class AnnotationTreeViewPage extends Page implements MouseListener, IDoubleClickListener,
        Listener, ISelectionListener, ICheckStateListener, IAnnotationEditorModifyListener {

  public class TreeViewAnnotationStyleChangeListener extends AnnotationStyleChangeListener {

    public void annotationStylesChanged(Collection<AnnotationStyle> styles) {
      for (AnnotationStyle annotationStyle : styles) {
        String annotation = annotationStyle.getAnnotation();
        updateIcon(annotation);
      }
      if(!treeView.isBusy()) {
        treeView.refresh();
      }
    }

  }

  private CheckboxTreeViewer treeView;

  private AnnotationTreeLableProvider lableProvider;

  protected Text filterTypeTextField;

  private Map<Type, Image> icons = new HashMap<Type, Image>();

  private Composite overlay;

  protected AnnotationEditor editor;

  protected ICasDocument document;

  private boolean useSelection;

  private Text filterCoveredTextTextField;

  private int offset = -1;

  private TreeViewAnnotationStyleChangeListener styleListener;

  public AnnotationTreeViewPage(boolean useSelection, AnnotationEditor editor) {
    super();
    this.useSelection = useSelection;
    this.editor = editor;
    this.document = editor.getDocument();
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

    filterTypeTextField = new Text(overlay, SWT.SINGLE | SWT.BORDER);
    GridData gd = new GridData();
    gd.grabExcessHorizontalSpace = true;
    gd.horizontalAlignment = GridData.FILL;
    gd.horizontalSpan = 1;
    filterTypeTextField.setLayoutData(gd);
    filterTypeTextField.setToolTipText("Retain types that contain...");
    filterTypeTextField.addListener(SWT.KeyUp, this);
    filterTypeTextField.addListener(SWT.MouseUp, this);
    filterTypeTextField.addListener(SWT.Modify, this);
    // TODO only for 3.3 see pom
     filterTypeTextField.setMessage("Only types with...");

    filterCoveredTextTextField = new Text(overlay, SWT.SINGLE | SWT.BORDER);
    GridData gd2 = new GridData();
    gd2.grabExcessHorizontalSpace = true;
    gd2.horizontalAlignment = GridData.FILL;
    gd2.horizontalSpan = 1;
    filterCoveredTextTextField.setLayoutData(gd2);
    filterCoveredTextTextField.setToolTipText("Only annotation with...");
    filterCoveredTextTextField.addListener(SWT.KeyUp, this);
    filterCoveredTextTextField.addListener(SWT.MouseUp, this);
    filterCoveredTextTextField.addListener(SWT.Modify, this);
    // TODO only for 3.3 see pom
    filterCoveredTextTextField.setMessage("Only annotations with...");

    treeView = new CheckboxTreeViewer(overlay, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
    gd = new GridData(GridData.FILL_BOTH);
    treeView.getTree().setLayoutData(gd);
    AnnotationTreeContentProvider provider = new AnnotationTreeContentProvider(editor, this);
    treeView.setContentProvider(provider);
    document.addChangeListener(provider);

    lableProvider = new AnnotationTreeLableProvider(this);
    treeView.setLabelProvider(lableProvider);
    treeView.addCheckStateListener(this);
    treeView.addDoubleClickListener(this);
    treeView.getTree().addMouseListener(this);

    ToolTipListener tl = new ToolTipListener(treeView.getTree());

    treeView.getTree().addListener(SWT.Dispose, tl);
    treeView.getTree().addListener(SWT.KeyDown, tl);
    treeView.getTree().addListener(SWT.MouseMove, tl);
    treeView.getTree().addListener(SWT.MouseHover, tl);
    int ops = DND.DROP_COPY | DND.DROP_MOVE;
    Transfer[] transfers = new Transfer[] { TextTransfer.getInstance() };
    treeView.addDragSupport(ops, transfers, new AnnotationTreeViewDragListener(treeView));

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

    styleListener = new TreeViewAnnotationStyleChangeListener();
    editor.getCasDocumentProvider().getTypeSystemPreferenceStore(editor.getEditorInput())
            .addPropertyChangeListener(styleListener);

    getSite().getPage().addSelectionListener(this);
    getSite().setSelectionProvider(treeView);

    editor.addAnnotationListener(this);
    if (!useSelection) {
      reloadTree();
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#dispose()
   */
  @Override
  public void dispose() {
    super.dispose();
    getSite().getPage().removeSelectionListener(this);
    editor.removeAnnotationListener(this);
    editor.getCasDocumentProvider().getTypeSystemPreferenceStore(editor.getEditorInput())
            .removePropertyChangeListener(styleListener);
    overlay.dispose();
    Collection<Image> values = icons.values();
    for (Image image : values) {
      image.dispose();
    }
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

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#setFocus()
   */
  @Override
  public void setFocus() {
    overlay.setFocus();
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
      if (treeNode instanceof AnnotationTreeNode) {
        // FeatureStructureSelectionProvider provider =
        // ((FeatureStructureSelectionProvider)editor.getSelectionProvider();
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.swt.events.MouseListener#mouseDoubleClick(org.eclipse.swt .events.MouseEvent)
   */
  public void mouseDoubleClick(MouseEvent e) {
    // TODO
  }

  public void deleteSelectedAnnotations() {
    if (MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), "Confirm Delete",
            "Are you sure you want to delete these items?")) {

      TreeItem[] items = treeView.getTree().getSelection();
      HashSet<AnnotationFS> annots = new HashSet<AnnotationFS>();

      for (TreeItem it : items) {
        if (it.getData() instanceof AnnotationTreeNode) {
          AnnotationTreeNode annot = (AnnotationTreeNode) it.getData();
          annots.add(annot.getAnnotation());
        } else if (it.getData() instanceof TypeTreeNode) {
          TypeTreeNode type = (TypeTreeNode) it.getData();

          for (Object child : type.getChildren()) {
            if (child instanceof AnnotationTreeNode) {
              AnnotationTreeNode annot = (AnnotationTreeNode) child;
              annots.add(annot.getAnnotation());
            }
          }
        }
      }
      editor.getDocument().removeFeatureStructures(annots);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.swt.events.MouseListener#mouseDown(org.eclipse.swt.events .MouseEvent)
   */
  public void mouseDown(final MouseEvent mouseEvent) {
    // TODO popup menu deactivated
    // if (mouseEvent.button == 3) {
    // Display display = Display.getCurrent();
    // Menu menu = new Menu(display.getActiveShell(), SWT.POP_UP);
    // MenuItem itemFgC = new MenuItem(menu, SWT.PUSH);
    //
    // itemFgC.setText("Change Font Color");
    // itemFgC.addListener(SWT.Selection, new Listener() {
    // public void handleEvent(Event e) {
    // TreeItem item = treeView.getTree().getItem(new Point(mouseEvent.x, mouseEvent.y));
    //
    // if (item != null && item.getData() instanceof ITreeNode) {
    // Type type = ((ITreeNode) item.getData()).getType();
    // ColorDialog cd = new ColorDialog(Display.getCurrent().getActiveShell());
    // cd.setRGB(casData.getForegroundColor(type).getRGB());
    //
    // RGB rgb = cd.open();
    //
    // if (rgb != null)
    // casData.setForegroundColor(type, new Color(Display.getCurrent(), rgb));
    // }
    // }
    // });
    //
    // MenuItem itemBgC = new MenuItem(menu, SWT.PUSH);
    // itemBgC.setText("Change Background Color");
    // itemBgC.addListener(SWT.Selection, new Listener() {
    // public void handleEvent(Event e) {
    // TreeItem item = treeView.getTree().getItem(new Point(mouseEvent.x, mouseEvent.y));
    //
    // if (item != null && item.getData() instanceof ITreeNode) {
    // Type type = ((ITreeNode) item.getData()).getType();
    //
    // ColorDialog cd = new ColorDialog(Display.getCurrent().getActiveShell());
    // cd.setRGB(casData.getBackgroundColor(type).getRGB());
    //
    // RGB rgb = cd.open();
    //
    // if (rgb != null)
    // casData.setBackgroundColor(type, new Color(Display.getCurrent(), rgb));
    // }
    // }
    // });
    //
    // TreeItem item = treeView.getTree().getItem(new Point(mouseEvent.x, mouseEvent.y));
    // if (item != null && item.getData() instanceof FeatureTreeNode) {
    // itemBgC.setEnabled(false);
    // itemFgC.setEnabled(false);
    // }
    //
    // new MenuItem(menu, SWT.SEPARATOR);
    //
    // MenuItem itemDelA = new MenuItem(menu, SWT.PUSH);
    // itemDelA.setText("Delete selected Items");
    // itemDelA.addListener(SWT.Selection, new Listener() {
    // public void handleEvent(Event e) {
    // deleteSelectedAnnotations();
    // }
    // });
    //
    // itemDelA.setEnabled(false);
    // TreeItem[] items = treeView.getTree().getSelection();
    // for (TreeItem ti : items)
    // if (!(ti.getData() instanceof FeatureTreeNode)) {
    // itemDelA.setEnabled(true);
    // break;
    // }
    //
    // menu.setVisible(true);
    //
    // while (!menu.isDisposed() && menu.isVisible()) {
    // if (!display.readAndDispatch())
    // display.sleep();
    // }
    // menu.dispose();
    // }
  }

  /*
   * (non-Javadoc)
   * 
   * @seeorg.eclipse.swt.events.MouseListener#mouseUp(org.eclipse.swt.events. MouseEvent)
   */
  public void mouseUp(MouseEvent e) {
  }

  public Image getIcon(Type type) {
    if (icons.containsKey(type)) {
      return icons.get(type);
    }
    Image image = updateIcon(type);
    return image;
  }

  private void updateIcon(String typeName) {
    Set<Type> keySet = icons.keySet();
    for (Type type : keySet) {
      String name = type.getName();
      if (name.equals(typeName)) {
        updateIcon(type);
        return;
      }

    }
  }

  private Image updateIcon(Type type) {
    AnnotationStyle style = editor.getAnnotationStyle(type);
    Color fg = new Color(Display.getCurrent(), 0, 0, 0);
    Color bg = new Color(Display.getCurrent(), style.getColor().getRed(), style.getColor()
            .getGreen(), style.getColor().getBlue());

    PaletteData paletteData = new PaletteData(new RGB[] { bg.getRGB(), fg.getRGB() });
    ImageData imageData = new ImageData(40, 40, 1, paletteData);

    Image image = new Image(Display.getCurrent(), imageData);
    GC gc = new GC(image);

    String styleString = style.getStyle().name().substring(0, 2);
    Point p = gc.stringExtent(styleString);

    gc.dispose();
    image.dispose();

    imageData = new ImageData(p.x + 4, p.y, 1, paletteData);
    image = new Image(Display.getCurrent(), imageData);
    gc = new GC(image);

    gc.setBackground(bg);
    gc.setForeground(fg);

    gc.setTextAntialias(SWT.ON);
    gc.drawString(styleString, 2, 0);

    gc.dispose();

    Image oldImage = icons.get(type);
    if (oldImage != null) {
      oldImage.dispose();
    }
    icons.put(type, image);
    return image;
  }

  public IRootTreeNode getTypeOrderedTree(int pos, String manualTypeFilter, String manualTextFilter) {
    TypeOrderedRootTreeNode root = new TypeOrderedRootTreeNode();

    AnnotationIndex<AnnotationFS> annotationIndex = document.getCAS().getAnnotationIndex();
    for (AnnotationFS annotationFS : annotationIndex) {
      boolean offsetConstraint = pos == -1
              || (annotationFS.getBegin() <= pos && annotationFS.getEnd() >= pos);
      boolean typeConstraint = manualTypeFilter == null || "".equals(manualTypeFilter)
              || annotationFS.getType().getName().indexOf(manualTypeFilter) != -1;
      boolean textConstraint = manualTextFilter == null || "".equals(manualTextFilter)
              || annotationFS.getCoveredText().indexOf(manualTextFilter) != -1;
      if (offsetConstraint && typeConstraint && textConstraint) {
        root.insertFS(annotationFS);
      }

    }
    root.sort();
    return root;
  }

  public void selectionChanged(IWorkbenchPart part, ISelection selection) {
    if (!useSelection)
      return;
    if (selection instanceof StructuredSelection && part instanceof AnnotationEditor) {
      offset = editor.getCaretOffset();
      reloadTree();
    }

  }

  public void handleEvent(Event event) {
    if ((event.widget == filterTypeTextField || event.widget == filterCoveredTextTextField)
            && event.type == SWT.Modify) {
      reloadTree();
    }
  }

  public void reloadTree() {
    String typeText = filterTypeTextField.getText();
    String coveredTextText = filterCoveredTextTextField.getText();
    IRootTreeNode tree = getTypeOrderedTree(offset, typeText, coveredTextText);
    getTreeViewer().setInput(tree);
    Collection<Type> shownAnnotationTypes = editor.getShownAnnotationTypes();
    List<TypeTreeNode> nodes = toNodes(shownAnnotationTypes);
    getTreeViewer().setCheckedElements(nodes.toArray());
    getTreeViewer().setGrayed(new TypeTreeNode(editor.getAnnotationMode()), true);
  }

  public void checkStateChanged(CheckStateChangedEvent event) {
    Object element = event.getElement();
    boolean checked = event.getChecked();
    Type type = null;
    if (element instanceof TypeTreeNode) {
      type = ((TypeTreeNode) element).getType();
    } else if (element instanceof AnnotationTreeNode) {
      type = ((AnnotationTreeNode) element).getType();
    }
    if (type != null && !editor.getAnnotationMode().equals(type)) {
      editor.setShownAnnotationType(type, checked);
    }
  }

  public void annotationModeChanged(Type newMode) {
    getTreeViewer().setGrayed(new TypeTreeNode(newMode), true);
  }

  public void showAnnotationsChanged(Collection<Type> shownAnnotationTypes) {
    List<TypeTreeNode> nodes = toNodes(shownAnnotationTypes);
    getTreeViewer().setCheckedElements(nodes.toArray());
  }

  private List<TypeTreeNode> toNodes(Collection<Type> shownAnnotationTypes) {
    List<TypeTreeNode> nodes = new ArrayList<TypeTreeNode>();
    for (Type type : shownAnnotationTypes) {
      nodes.add(new TypeTreeNode(type));
    }
    return nodes;
  }

}
