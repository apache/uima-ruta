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

package org.apache.uima.ruta.caseditor.view.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.caseditor.editor.AnnotationEditor;
import org.apache.uima.caseditor.editor.AnnotationStyle;
import org.apache.uima.caseditor.editor.AnnotationStyleChangeListener;
import org.apache.uima.caseditor.editor.IAnnotationEditorModifyListener;
import org.apache.uima.caseditor.editor.ICasDocument;
import org.apache.uima.ruta.caseditor.RutaCasEditorPlugin;
import org.apache.uima.ruta.caseditor.view.preferences.CasEditorViewsPreferenceConstants;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
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
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.Page;

public class AnnotationTreeViewPage extends Page implements MouseListener, IDoubleClickListener,
        Listener, ISelectionListener, ICheckStateListener, IAnnotationEditorModifyListener {

  public static enum ModifyAnnotationOperation {
    WIDE_L, LOWER_L, WIDE_R, LOWER_R;
  }

  public class TreeViewAnnotationStyleChangeListener extends AnnotationStyleChangeListener {

    @Override
    public void annotationStylesChanged(Collection<AnnotationStyle> styles) {
      for (AnnotationStyle annotationStyle : styles) {
        String annotation = annotationStyle.getAnnotation();
        updateIcon(annotation);
      }
      if (!treeView.isBusy() && !treeView.getTree().isDisposed()) {
        treeView.refresh();
      }
    }

  }

  private class CheckAllVisibleAction extends Action {
    @Override
    public void run() {
      uncheckAll();
      checkAllVisible();
    }
  }

  private class UncheckAllAction extends Action {
    @Override
    public void run() {
      uncheckAll();
    }
  }

  private class ShowTypesWithoutAnnotations extends Action {

    public ShowTypesWithoutAnnotations() {
      super("Show Types Without Annotations", IAction.AS_CHECK_BOX);
      setChecked(isTreeWithTypesWithoutAnnotations());
    }

    @Override
    public void run() {
      IPreferenceStore preferenceStore = RutaCasEditorPlugin.getDefault().getPreferenceStore();
      preferenceStore.setValue(CasEditorViewsPreferenceConstants.SHOW_TYPES_WITHOUT_ANNOTATIONS,
              isChecked());
      reloadTree();
    }
  }

  private CheckboxTreeViewer treeView;

  private AnnotationTreeLabelProvider lableProvider;

  protected Text filterTypeTextField;

  private Map<Type, Image> icons = new HashMap<Type, Image>();

  private Image rutaImage;
  
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

    final Clipboard clipboard = new Clipboard(Display.getCurrent());

    GridLayout layout = new GridLayout();
    layout.horizontalSpacing = 0;
    layout.verticalSpacing = 0;
    layout.marginWidth = 0;
    layout.marginHeight = 0;
    overlay.setLayout(layout);

    KeyListener checkSelectedKeyListener = new KeyAdapter() {

      @Override
      public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.keyCode;
        if (keyCode == SWT.CR || keyCode == SWT.LF || keyCode == SWT.KEYPAD_CR) {
          uncheckAll();
          checkAllVisible();
        }
      }
    };

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
    filterTypeTextField.setMessage("Only types with...");
    filterTypeTextField.addKeyListener(checkSelectedKeyListener);

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
    filterCoveredTextTextField.setMessage("Only annotations with...");
    filterCoveredTextTextField.addKeyListener(checkSelectedKeyListener);

    treeView = new CheckboxTreeViewer(overlay, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
    gd = new GridData(GridData.FILL_BOTH);
    treeView.getTree().setLayoutData(gd);
    AnnotationTreeContentProvider provider = new AnnotationTreeContentProvider(editor, this);
    treeView.setContentProvider(provider);
    document.addChangeListener(provider);

    lableProvider = new AnnotationTreeLabelProvider(this);
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
        // backspace or delete: delete annotations
        if (keyCode == SWT.BS || keyCode == SWT.DEL) {
          deleteSelectedAnnotations();
        }
        // ctrl and c: copy type name to clipboard
        if ((e.stateMask & SWT.CTRL) == SWT.CTRL && keyCode == 'c') {
          TreeItem[] selection = treeView.getTree().getSelection();
          if (selection != null && selection.length == 1) {
            Object obj = selection[0].getData();
            if (obj instanceof TypeTreeNode) {
              TypeTreeNode typeTreeNode = (TypeTreeNode) obj;
              Type type = typeTreeNode.getType();
              TextTransfer textTransfer = TextTransfer.getInstance();
              clipboard.setContents(new Object[] { type.getName() },
                      new Transfer[] { textTransfer });
            }
          }
        }
        // ctrl and c: copy type name to clipboard:
        if ((e.stateMask & SWT.CTRL) == SWT.CTRL) {
          if (keyCode == 'u') {
            modifyAnnotation(ModifyAnnotationOperation.WIDE_L);
          } else if (keyCode == 'i') {
            modifyAnnotation(ModifyAnnotationOperation.LOWER_L);
          } else if (keyCode == 'o') {
            modifyAnnotation(ModifyAnnotationOperation.LOWER_R);
          } else if (keyCode == 'p') {
            modifyAnnotation(ModifyAnnotationOperation.WIDE_R);
          }
        }
      }

    });

    styleListener = new TreeViewAnnotationStyleChangeListener();
    editor.getCasDocumentProvider().getTypeSystemPreferenceStore(editor.getEditorInput())
            .addPropertyChangeListener(styleListener);

    rutaImage = RutaCasEditorPlugin.getImageDescriptor("/icons/views.png").createImage();
    
    getSite().getPage().addSelectionListener(this);
    getSite().setSelectionProvider(treeView);

    editor.addAnnotationListener(this);
    if (!useSelection) {
      reloadTree();
    }

  }

  @Override
  public void makeContributions(IMenuManager menuManager, IToolBarManager toolBarManager,
          IStatusLineManager statusLineManager) {
    //
    Action createActionCheckVisible = new CheckAllVisibleAction();
    createActionCheckVisible.setText(
            "Set all types visible in the Annotation Browser to be highlighted in the CAS editor.");
    ImageDescriptor imageDescriptor = RutaCasEditorPlugin
            .getImageDescriptor("/icons/lightbulb_add.png");
    createActionCheckVisible.setImageDescriptor(imageDescriptor);
    toolBarManager.add(createActionCheckVisible);
    //
    Action createActionUncheckAll = new UncheckAllAction();
    createActionUncheckAll.setText("Reset type highlighting. No type will be checked.");
    createActionUncheckAll
            .setImageDescriptor(RutaCasEditorPlugin.getImageDescriptor("/icons/lightbulb_off.png"));
    toolBarManager.add(createActionUncheckAll);
    if (!useSelection) {
      IAction showTypesWithoutAnnotations = new ShowTypesWithoutAnnotations();
      menuManager.add(showTypesWithoutAnnotations);
    }
  }

  /**
   * Unchecks all types of the typesystem so that they are not highlighted anymore.
   * 
   */
  public void uncheckAll() {
    editor.setShownAnnotationTypes(new LinkedList<Type>());
    getTreeViewer().getTree().deselectAll();
  }

  /**
   * Checks all visible types of the tree to be highlighted.
   * 
   */
  public void checkAllVisible() {
    TypeSystem ts = editor.getDocument().getCAS().getTypeSystem();
    Type documentAnnotationType = ts.getType(CAS.TYPE_NAME_DOCUMENT_ANNOTATION);
    List<Type> selectedTypes = new LinkedList<Type>();
    for (TreeItem i : getTreeViewer().getTree().getItems()) {
      Object e = i.getData();
      if (e instanceof TypeTreeNode) {
        TypeTreeNode typeTreeNode = (TypeTreeNode) e;
        Type type = typeTreeNode.getType();
        if (!documentAnnotationType.equals(type)) {
          selectedTypes.add(type);
        }
      }
    }
    editor.setShownAnnotationTypes(selectedTypes);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#dispose()
   */
  @Override
  public void dispose() {
    getSite().getPage().removeSelectionListener(this);
    editor.removeAnnotationListener(this);
    editor.getCasDocumentProvider().getTypeSystemPreferenceStore(editor.getEditorInput())
            .removePropertyChangeListener(styleListener);
    overlay.dispose();
    Collection<Image> values = icons.values();
    for (Image image : values) {
      image.dispose();
    }
    rutaImage.dispose();
    super.dispose();
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
  @Override
  public void doubleClick(DoubleClickEvent event) {
   
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.swt.events.MouseListener#mouseDoubleClick(org.eclipse.swt .events.MouseEvent)
   */
  @Override
  public void mouseDoubleClick(MouseEvent e) {

  }

  public void modifyAnnotation(ModifyAnnotationOperation operationType) {
    TreeItem[] items = treeView.getTree().getSelection();
    if (items.length != 1) {
      return;
    }
    Feature beginFeature = document.getCAS().getBeginFeature();
    Feature endFeature = document.getCAS().getEndFeature();
    for (TreeItem it : items) {
      if (it.getData() instanceof AnnotationTreeNode) {
        AnnotationTreeNode annot = (AnnotationTreeNode) it.getData();
        AnnotationFS fs = annot.getAnnotation();
        switch (operationType) {
          case WIDE_L:
            if (fs.getBegin() > 0) {
              fs.setIntValue(beginFeature, fs.getBegin() - 1);
            }
            break;
          case LOWER_L:
            if (fs.getBegin() < fs.getEnd() - 1) {
              fs.setIntValue(beginFeature, fs.getBegin() + 1);
            }
            break;
          case LOWER_R:
            if (fs.getEnd() > fs.getBegin() + 1) {
              fs.setIntValue(endFeature, fs.getEnd() - 1);
            }
            break;
          case WIDE_R:
            if (fs.getEnd() < fs.getCAS().getDocumentText().length()) {
              fs.setIntValue(endFeature, fs.getEnd() + 1);
            }
            break;
          default:
            break;
        }
        document.update(fs);
      }
    }
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
  @Override
  public void mouseDown(final MouseEvent mouseEvent) {
    if (mouseEvent.button == 3) {
      Display display = Display.getCurrent();
      Menu menu = new Menu(display.getActiveShell(), SWT.POP_UP);
      MenuItem item = new MenuItem(menu, SWT.PUSH);
      item.setText("Set Annotation Mode");
      item.setImage(rutaImage);
      item.addListener(SWT.Selection, new Listener() {
        public void handleEvent(Event e) {
          TreeItem item = treeView.getTree().getItem(new Point(mouseEvent.x, mouseEvent.y));
          if (item != null && item.getData() instanceof AbstractTreeNode) {
            Type type = ((AbstractTreeNode) item.getData()).getType();
            if(type != null) {
              getTreeViewer().setGrayed(new TypeTreeNode(null, type), false);
              editor.setAnnotationMode(type);
            }
          }
        }
      });

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
  @Override
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
    Color bg = new Color(Display.getCurrent(), style.getColor().getRed(),
            style.getColor().getGreen(), style.getColor().getBlue());

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

  public IRootTreeNode getTypeOrderedTree(int pos, String manualTypeFilter,
          String manualTextFilter) {
    CAS cas = editor.getDocument().getCAS();
    TypeOrderedRootTreeNode root = new TypeOrderedRootTreeNode(cas);
    IPreferenceStore preferenceStore = RutaCasEditorPlugin.getDefault().getPreferenceStore();
    boolean withParents = preferenceStore
            .getBoolean(CasEditorViewsPreferenceConstants.SHOW_PARENT_TYPES);
    if (isTreeWithTypesWithoutAnnotations()) {
      Type atype = cas.getAnnotationType();
      TypeSystem ts = cas.getTypeSystem();
      Iterator<Type> tit = ts.getProperlySubsumedTypes(atype).iterator();
      while (tit.hasNext()) {
        Type type = tit.next();
        boolean typeConstraint = StringUtils.isEmpty(manualTypeFilter)
                || type.getName().toLowerCase().indexOf(manualTypeFilter.toLowerCase()) != -1;
        if (typeConstraint) {
          root.getTreeNode(type, cas); // register type
        }
      }
    }
    AnnotationIndex<AnnotationFS> annotationIndex = document.getCAS().getAnnotationIndex();
    for (AnnotationFS annotationFS : annotationIndex) {
      boolean offsetConstraint = pos == -1
              || (annotationFS.getBegin() <= pos && annotationFS.getEnd() >= pos);
      boolean typeConstraint = StringUtils.isEmpty(manualTypeFilter) || annotationFS.getType()
              .getName().toLowerCase().indexOf(manualTypeFilter.toLowerCase()) != -1;
      boolean textConstraint = StringUtils.isEmpty(manualTextFilter) || annotationFS
              .getCoveredText().toLowerCase().indexOf(manualTextFilter.toLowerCase()) != -1;
      if (offsetConstraint && typeConstraint && textConstraint) {
        root.insertFS(annotationFS, cas, withParents);
      }

    }
    root.sort();
    return root;
  }

  @Override
  public void selectionChanged(IWorkbenchPart part, ISelection selection) {
    if (!useSelection)
      return;
    if (selection instanceof StructuredSelection && part instanceof AnnotationEditor) {
      offset = editor.getCaretOffset();
      reloadTree();
    }
  }

  @Override
  public void handleEvent(Event event) {
    if ((event.widget == filterTypeTextField || event.widget == filterCoveredTextTextField)
            && event.type == SWT.Modify) {
      reloadTree();
    }
  }

  public void reloadTree() {
    // remember selected annotation:
    ISelection selection = treeView.getSelection();
    AnnotationFS selectedFS = null;
    if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() == 1
            && ((IStructuredSelection) selection).getFirstElement() instanceof AnnotationTreeNode) {
      IStructuredSelection ssel = (IStructuredSelection) selection;
      AnnotationTreeNode firstElement = (AnnotationTreeNode) ssel.getFirstElement();
      selectedFS = firstElement.getAnnotation();
    }
    // reload tree:
    String typeText = filterTypeTextField.getText();
    String coveredTextText = filterCoveredTextTextField.getText();
    IRootTreeNode tree = getTypeOrderedTree(offset, typeText, coveredTextText);
    getTreeViewer().setInput(tree);
    Collection<Type> shownAnnotationTypes = editor.getShownAnnotationTypes();
    List<TypeTreeNode> nodes = toNodes(shownAnnotationTypes);
    getTreeViewer().setCheckedElements(nodes.toArray());
    getTreeViewer().setGrayed(new TypeTreeNode(null, editor.getAnnotationMode()), true);
    // try to restore selection:
    if (selectedFS != null) {
      Type type = selectedFS.getType();
      TreeItem[] items = getTreeViewer().getTree().getItems();
      for (int i = 0; i < items.length; i++) {
        TreeItem treeItem = items[i];
        Object data = treeItem.getData();
        if (data instanceof TypeTreeNode && ((TypeTreeNode) data).getType().equals(type)) {
          TypeTreeNode typeTreeNode = (TypeTreeNode) data;
          treeItem.setExpanded(true);
          ITreeNode[] children = typeTreeNode.getChildren();
          for (int j = 0; j < children.length; j++) {
            ITreeNode fsTreeNode = children[j];
            if (fsTreeNode instanceof AnnotationTreeNode) {
              AnnotationTreeNode atn = (AnnotationTreeNode) fsTreeNode;
              if (atn.getAnnotation().equals(selectedFS)) {
                treeView.setSelection(new StructuredSelection(atn));
              }
            }
          }
        }
      }
    }
  }

  @Override
  public void checkStateChanged(CheckStateChangedEvent event) {
    Object element = event.getElement();
    boolean checked = event.getChecked();
    Type type = null;
    if (element instanceof TypeTreeNode) {
      type = ((TypeTreeNode) element).getType();
    } else if (element instanceof AnnotationTreeNode) {
      type = ((AnnotationTreeNode) element).getType();
    }
    Type modeType = editor.getAnnotationMode();
    if(!checked && modeType != null  && modeType.equals(type)) {
      // reset mode to uima.tcas.Annotation if deselected
      getTreeViewer().setGrayed(new TypeTreeNode(null, type), false);
      Type annotationType = editor.getDocument().getCAS().getAnnotationType();
      editor.setAnnotationMode(annotationType);
    }
    if (type != null && !editor.getAnnotationMode().equals(type)) {
      editor.setShownAnnotationType(type, checked);
    }
  }

  @Override
  public void annotationModeChanged(Type newMode) {
    getTreeViewer().setGrayed(new TypeTreeNode(null, newMode), true);
  }

  @Override
  public void showAnnotationsChanged(Collection<Type> shownAnnotationTypes) {
    List<TypeTreeNode> nodes = toNodes(shownAnnotationTypes);
    getTreeViewer().setCheckedElements(nodes.toArray());
  }

  private List<TypeTreeNode> toNodes(Collection<Type> shownAnnotationTypes) {
    List<TypeTreeNode> nodes = new ArrayList<TypeTreeNode>();
    for (Type type : shownAnnotationTypes) {
      nodes.add(new TypeTreeNode(null, type));
    }
    return nodes;
  }

  private boolean isTreeWithTypesWithoutAnnotations() {
    IPreferenceStore preferenceStore = RutaCasEditorPlugin.getDefault().getPreferenceStore();
    return !useSelection && preferenceStore
            .getBoolean(CasEditorViewsPreferenceConstants.SHOW_TYPES_WITHOUT_ANNOTATIONS);
  }

}
