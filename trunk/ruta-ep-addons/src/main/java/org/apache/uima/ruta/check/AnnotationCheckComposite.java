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

package org.apache.uima.ruta.check;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.uima.UIMAFramework;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.impl.XmiCasDeserializer;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.caseditor.editor.AnnotationEditor;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.FsIndexDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ViewPart;
import org.xml.sax.SAXException;

public class AnnotationCheckComposite extends Composite implements ISelectionChangedListener,
        ISelectionListener {

  private CheckAnnotationDocumentListener annotationListener;

  private AnnotationCheckTreeNodeComparator comparator = new AnnotationCheckTreeNodeComparator();

  private AnnotationCheckLabelProvider lableProvider;

  private TreeViewer treeView;

  private Text documentSource;

  private ViewPart viewPart;

  private Text pathToTypeSystem;

  private HashMap<String, Image> images;

  private Text documentSink;

  private Map<String, Set<String>> typesToCheck;

  private Set<String> typesToTransferUnchecked;

  private CheckDocument currentDocument = null;

  private String annotationMode;

  private AnnotationEditor casEditor;

  private List<CheckDocument> oldDocs;

  private TypeSystemDescription tsd;

  public AnnotationCheckComposite(Composite parent, int style, ViewPart viewPart) {
    super(parent, style);
    this.viewPart = viewPart;
    typesToCheck = new HashMap<String, Set<String>>();
    typesToTransferUnchecked = new HashSet<String>();
    initGui();
    annotationListener = new CheckAnnotationDocumentListener(this);
  }

  private void initGui() {
    this.setLayout(new FormLayout());
    this.setSize(400, 800);
    initDocumentSourceTextField();
    initDocumentSinkTextField();
    initTypeSystemPathTextField();
    initTreeViewer();
    viewPart.getSite().getPage().addSelectionListener(this);
    viewPart.getSite().setSelectionProvider(treeView);
  }

  private void initTreeViewer() {
    FormData fdata3 = new FormData();
    fdata3.left = new FormAttachment(0, 1000, 3);
    fdata3.top = new FormAttachment(0, 1000, 81);
    fdata3.right = new FormAttachment(1000, 1000, -3);
    fdata3.bottom = new FormAttachment(1000, 1000, -3);
    Composite comp = new Composite(this, SWT.CENTER);
    comp.setLayoutData(fdata3);
    comp.setLayout(new FillLayout());
    treeView = new TreeViewer(comp, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
    AnnotationCheckContentProvider provider = new AnnotationCheckContentProvider();
    treeView.setContentProvider(provider);
    treeView.getControl().addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        char c = e.character;
        if (c == '4' || c == '5') {
          if (c == '4') {
            accept();
          } else if (c == '5') {
            reject(true);
          }
        }
      }
    });
    treeView.setComparator(new FeatureCheckTreeNodeComparator());
    treeView.getTree().addMouseListener(new MouseAdapter() {
      @Override
      public void mouseDown(MouseEvent e) {
        Object source = e.getSource();
        if (source instanceof Tree && e.button == 3) {
          Tree tree = (Tree) source;
          Composite composite = tree.getParent().getParent();
          TreeSelection teeSelection = (TreeSelection) treeView.getSelection();
          Object node = teeSelection.getFirstElement();
          Display display = Display.getDefault();
          Shell shell = new Shell(display, SWT.RESIZE | SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
          if (node instanceof AnnotationCheckTreeNode && !(node instanceof FeatureCheckTreeNode)) {
            changeType(shell, composite, node);
          }
          if (node instanceof AnnotationCheckTreeNode && node instanceof FeatureCheckTreeNode) {
            changeFeature(shell, node);
          }
        }
      }
    });
    lableProvider = new AnnotationCheckLabelProvider(this);
    treeView.setLabelProvider(lableProvider);
  }

  private void initTypeSystemPathTextField() {
    pathToTypeSystem = new Text(this, SWT.SINGLE | SWT.BORDER);
    FormData fdata2 = new FormData();
    fdata2.width = 200;
    fdata2.left = new FormAttachment(0, 1000, 5);
    fdata2.top = new FormAttachment(0, 1000, 55);
    fdata2.right = new FormAttachment(1000, 1000, -5);
    pathToTypeSystem.setLayoutData(fdata2);
    pathToTypeSystem.setToolTipText("Type System...");
    pathToTypeSystem.setMessage("Type System...");
    DropTarget dt1 = new DropTarget(pathToTypeSystem, DND.DROP_DEFAULT | DND.DROP_MOVE);
    dt1.setTransfer(new Transfer[] { FileTransfer.getInstance() });
    dt1.addDropListener(new DropTargetAdapter() {
      @Override
      public void drop(DropTargetEvent event) {
        String fileList[] = null;
        FileTransfer ft = FileTransfer.getInstance();
        if (ft.isSupportedType(event.currentDataType)) {
          fileList = (String[]) event.data;
        }
        if (fileList != null && fileList.length > 0) {
          String fileString = fileList[0];
          pathToTypeSystem.setText(fileString);
        }
      }
    });
  }

  private void initDocumentSinkTextField() {
    documentSink = new Text(this, SWT.SINGLE | SWT.BORDER);
    FormData fdatag = new FormData();
    fdatag.width = 200;
    fdatag.left = new FormAttachment(0, 1000, 5);
    fdatag.top = new FormAttachment(0, 1000, 30);
    fdatag.right = new FormAttachment(1000, 1000, -5);
    documentSink.setLayoutData(fdatag);
    documentSink.setToolTipText("Document gold output folder...");
    documentSink.setMessage("Document gold output folder...");
    DropTarget dtg = new DropTarget(documentSink, DND.DROP_DEFAULT | DND.DROP_MOVE);
    dtg.setTransfer(new Transfer[] { FileTransfer.getInstance() });
    dtg.addDropListener(new DropTargetAdapter() {
      @Override
      public void drop(DropTargetEvent event) {
        String fileList[] = null;
        FileTransfer ft = FileTransfer.getInstance();
        if (ft.isSupportedType(event.currentDataType)) {
          fileList = (String[]) event.data;
        }
        if (fileList != null && fileList.length > 0) {
          String fileString = fileList[0];
          documentSink.setText(fileString);
        }
      }
    });
  }

  private void initDocumentSourceTextField() {
    documentSource = new Text(this, SWT.SINGLE | SWT.BORDER);
    FormData fdata1 = new FormData();
    fdata1.width = 200;
    fdata1.left = new FormAttachment(0, 1000, 5);
    fdata1.top = new FormAttachment(0, 1000, 5);
    fdata1.right = new FormAttachment(1000, 1000, -5);
    documentSource.setLayoutData(fdata1);
    documentSource.setToolTipText("Document source folder...");
    documentSource.setMessage("Document source folder...");
    DropTarget dt = new DropTarget(documentSource, DND.DROP_DEFAULT | DND.DROP_MOVE);
    dt.setTransfer(new Transfer[] { FileTransfer.getInstance() });
    dt.addDropListener(new DropTargetAdapter() {
      @Override
      public void drop(DropTargetEvent event) {
        String fileList[] = null;
        FileTransfer ft = FileTransfer.getInstance();
        if (ft.isSupportedType(event.currentDataType)) {
          fileList = (String[]) event.data;
        }
        if (fileList != null && fileList.length > 0) {
          String fileString = fileList[0];
          documentSource.setText(fileString);
        }
      }
    });
  }

  private void changeType(Shell shell, Composite composite, Object node) {
    AnnotationCheckComposite annotCheckCompo = null;
    if (!(composite instanceof AnnotationCheckComposite)) {
      return;
    }
    annotCheckCompo = (AnnotationCheckComposite) composite;
    shell.setText("Change annotation type");
    TypeSystemDescription tsd = annotCheckCompo.getTypeSystemDescription();
    SelectTypesDialogCheck dialog = new SelectTypesDialogCheck(shell, tsd, null, false, SWT.SINGLE,
            false);
    String newTypeName = null;
    if (dialog.open() == Window.OK) {
      newTypeName = dialog.getChoosenType();
    }
    if (newTypeName != null) {
      AnnotationCheckTreeNode annotCheckTreeNode = (AnnotationCheckTreeNode) node;
      CheckAnnotation checkAnnot = (CheckAnnotation) annotCheckTreeNode.getElement();
      checkAnnot.setTypeName(newTypeName);
      String[] split = newTypeName.split("\\.");
      String shortTypeName = split[split.length - 1];
      checkAnnot.setShortType(shortTypeName);
      treeView.refresh();
      return;
    }
  }

  private void changeFeature(Shell shell, Object node) {
    FeatureCheckTreeNode featTreeNode = (FeatureCheckTreeNode) node;
    shell.setText("Change value of feature");
    Type range = featTreeNode.getFeature().getRange();
    IInputValidator validator = new ChangeFeatureValidator(range);
    InputDialog dialog = new InputDialog(getShell(), "Define new feature value",
            "New feature value:", "", validator);
    if (dialog.open() == Window.OK) {
      featTreeNode.setValue(dialog.getValue());
      treeView.refresh();
      return;
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
    viewPart.getSite().getPage().removeSelectionListener(this);
    Collection<Image> values = images.values();
    for (Image image : values) {
      image.dispose();
    }
  }

  public Image getImage(String name) {
    if (images == null) {
      initImages();
    }
    return images.get(name);
  }

  private void initImages() {
    images = new HashMap<String, Image>();
    ImageDescriptor desc;
    Image image;
    String name;

    desc = RutaAddonsPlugin.getImageDescriptor("/icons/accept.png");
    image = desc.createImage();
    name = "accept";
    images.put(name, image);

    desc = RutaAddonsPlugin.getImageDescriptor("/icons/delete.png");
    image = desc.createImage();
    name = "delete";
    images.put(name, image);

    desc = RutaAddonsPlugin.getImageDescriptor("/icons/help.png");
    image = desc.createImage();
    name = "help";
    images.put(name, image);

    desc = RutaAddonsPlugin.getImageDescriptor("/icons/bullet_blue.png");
    image = desc.createImage();
    name = "feature";
    images.put(name, image);

    desc = RutaAddonsPlugin.getImageDescriptor("/icons/folder_page.png");
    image = desc.createImage();
    name = "folder";
    images.put(name, image);
  }

  @Override
  public void selectionChanged(IWorkbenchPart part, ISelection selection) {
    if (part instanceof AnnotationCheckView) {
      if (selection instanceof TreeSelection) {
        TreeSelection ts = (TreeSelection) selection;
        if (ts.getFirstElement() instanceof FeatureCheckTreeNode) {
          return;
        }
        if (ts.getFirstElement() instanceof AnnotationCheckTreeNode) {
          AnnotationCheckTreeNode firstElement = (AnnotationCheckTreeNode) ts.getFirstElement();
          CheckElement element = firstElement.getElement();
          int begin = 0;
          int end = 0;
          CheckDocument newDoc = null;
          if (element instanceof CheckAnnotation) {
            begin = ((CheckAnnotation) element).getBegin();
            end = ((CheckAnnotation) element).getEnd();
            newDoc = ((CheckDocument) firstElement.getParent().getElement());
          } else if (element instanceof CheckDocument) {
            newDoc = ((CheckDocument) element);
          } else {
            return;
          }
          if (casEditor != null && casEditor.getDocumentProvider() != null
                  && casEditor.getDocument() != null) {
            IFile file = ((FileEditorInput) casEditor.getEditorInput()).getFile();
            if (!newDoc.source.equals(file.getLocation().toOSString())) {
              casEditor.getDocument().removeChangeListener(annotationListener);
              casEditor = CheckAnnotationUtils.openInCasEditor(new File(newDoc.source), begin, end);
              casEditor.getDocument().addChangeListener(annotationListener);
            } else {
              casEditor.selectAndReveal(begin, end - begin);
            }
          } else {
            casEditor = CheckAnnotationUtils.openInCasEditor(new File(newDoc.source), begin, end);
            casEditor.getDocument().addChangeListener(annotationListener);
          }
          setAnnotationMode(annotationMode);
          Iterator<Type> typeIterator = casEditor.getDocument().getCAS().getTypeSystem()
                  .getTypeIterator();
          while (typeIterator.hasNext()) {
            Type type = typeIterator.next();
            boolean contains = typesToCheck.containsKey(type.getName());
            casEditor.setShownAnnotationType(type, contains);
          }
          currentDocument = newDoc;
          treeView.getControl().setFocus();
        }
      }
    }
  }

  @Override
  public void selectionChanged(SelectionChangedEvent arg0) {
  }

  public void restoreState(IMemento memento) {
    if (memento == null)
      return;

    IMemento dir = memento.getChild("documentSource");
    if (dir != null) {
      String id = dir.getID();
      documentSource.setText(id);
    }

    IMemento dir2 = memento.getChild("documentSink");
    if (dir2 != null) {
      String id = dir2.getID();
      documentSink.setText(id);
    }

    IMemento tsName = memento.getChild("typeSystem");
    if (tsName != null) {
      String id = tsName.getID();
      pathToTypeSystem.setText(id);
    }

    IMemento selectedTypes = memento.getChild("typesToCheck");
    if (selectedTypes != null) {
      typesToCheck = new HashMap<String, Set<String>>();
      for (IMemento mementoTypesToCheck : selectedTypes.getChildren(selectedTypes.getID())) {
        String typeName = mementoTypesToCheck.getID();
        Set<String> features = new HashSet<String>();
        for (IMemento mementoFeature : mementoTypesToCheck.getChildren("feature")) {
          features.add(mementoFeature.getID());
        }
        typesToCheck.put(typeName, features);
      }
    }
    IMemento uncheckedTypes = memento.getChild("typesToTransferUnchecked");
    if (uncheckedTypes != null) {
      typesToTransferUnchecked = new HashSet<String>();
      for (IMemento mementoUnchecked : uncheckedTypes.getChildren(uncheckedTypes.getID())) {
        String typeName = mementoUnchecked.getID();
        typesToTransferUnchecked.add(typeName);
      }
    }

  }

  public void saveState(IMemento memento) {
    memento.createChild("documentSource", documentSource.getText());
    memento.createChild("documentSink", documentSink.getText());
    memento.createChild("typeSystem", pathToTypeSystem.getText());

    IMemento selectedTypesMemento = memento.createChild("typesToCheck", "type");
    for (Entry<String, Set<String>> checkedTypeEntry : typesToCheck.entrySet()) {
      IMemento selectedTypeMemento = selectedTypesMemento.createChild("type",
              checkedTypeEntry.getKey());
      for (String feature : checkedTypeEntry.getValue()) {
        selectedTypeMemento.createChild("feature", feature);
      }
    }

    IMemento uncheckTypesMemento = memento.createChild("typesToTransferUnchecked", "unchecked");
    for (String uncheckedTypeName : typesToTransferUnchecked) {
      uncheckTypesMemento.createChild("unchecked", uncheckedTypeName);
    }

  }

  public TreeViewer getTreeViewer() {
    return treeView;
  }

  public String getDocumentSource() {
    return documentSource.getText();
  }

  public String getDocumentSink() {
    return documentSink.getText();
  }

  public String getPathToTypeSystem() {
    return pathToTypeSystem.getText();
  }

  public Map<String, Set<String>> getCheckedTypes() {
    return typesToCheck;
  }

  public void setTypesToCheck(Map<String, Set<String>> typesToCheck) {
    this.typesToCheck = typesToCheck;
  }

  public void reject(boolean doMove) {
    TreeSelection selection = (TreeSelection) treeView.getSelection();
    AnnotationCheckTreeNode firstElement = (AnnotationCheckTreeNode) selection.getFirstElement();
    if (firstElement == null) {
      return;
    }
    firstElement.getElement().checked = true;
    firstElement.getElement().keep = false;
    if (doMove) {
      moveToNext();
    }
    treeView.refresh();
  }

  public void accept() {
    TreeSelection selection = (TreeSelection) treeView.getSelection();
    AnnotationCheckTreeNode firstElement = (AnnotationCheckTreeNode) selection.getFirstElement();
    if (firstElement == null) {
      return;
    }
    firstElement.getElement().checked = true;
    firstElement.getElement().keep = true;
    List<AnnotationCheckTreeNode> siblings = Arrays.asList(firstElement.getParent().getChildren());
    int indexOfFirstElement = siblings.indexOf(firstElement);
    CheckAnnotation firstElementAnnotation = (CheckAnnotation) firstElement.getElement();
    moveToNext();
    for (int i = indexOfFirstElement + 1; i < siblings.size(); i++) {
      CheckElement nextSiblingCE = siblings.get(i).getElement();
      if (nextSiblingCE instanceof CheckAnnotation) {
        CheckAnnotation nextSibling = (CheckAnnotation) nextSiblingCE;
        if (nextSibling.getBegin() == firstElementAnnotation.getBegin()
                && nextSibling.getEnd() == firstElementAnnotation.getEnd()) {
          reject(true);
        } else {
          break;
        }
        if (i == siblings.size() - 1) {
          IAnnotationCheckTreeNode parent = siblings.get(0).getParent();
          parent.getElement().checked = true;
          parent.getElement().keep = true;
        }
      }
    }
    treeView.refresh();
  }

  public void moveToNext() {
    TreeSelection selection = (TreeSelection) treeView.getSelection();
    IAnnotationCheckTreeNode parent = null;
    AnnotationCheckTreeNode firstElement = null;
    if (selection.getFirstElement() instanceof FeatureCheckTreeNode) {
      return;
    }
    firstElement = (AnnotationCheckTreeNode) selection.getFirstElement();
    parent = firstElement.getParent();
    IAnnotationCheckTreeNode[] children = parent.getChildren();
    List<IAnnotationCheckTreeNode> list = Arrays.asList(children);
    int indexOf = list.indexOf(firstElement);
    IAnnotationCheckTreeNode brother = null;
    IAnnotationCheckTreeNode uncle = parent;
    if (list == null || list.isEmpty()) {

    } else if (indexOf == -1) {
      brother = list.get(0);
    } else if (firstElement.getElement() instanceof CheckDocument && indexOf < list.size() - 1) {
      uncle = list.get(indexOf + 1);
      if (uncle.getChildren().length > 0) {
        brother = uncle.getChildren()[0];
      }
    } else if (indexOf < list.size() - 1) {
      brother = list.get(indexOf + 1);
    } else if (firstElement.getElement() instanceof CheckAnnotation) {
      brother = null;
      IAnnotationCheckTreeNode[] children2 = parent.getParent().getChildren();
      List<IAnnotationCheckTreeNode> list2 = Arrays.asList(children2);
      int indexOf2 = list2.indexOf(parent);
      if (list2 == null || list2.isEmpty()) {

      } else if (indexOf2 == -1) {
        uncle = list2.get(0);
      } else if (indexOf2 < list2.size() - 1) {
        uncle = list2.get(indexOf2 + 1);
        if (uncle.getChildren().length != 0) {
          brother = uncle.getChildren()[0];
        }
      }
    }
    TreePath treePath = null;
    if (brother == null) {
      treePath = new TreePath(new Object[] { treeView.getInput(), uncle });
    } else {
      treePath = new TreePath(new Object[] { treeView.getInput(), uncle, brother });

    }
    final TreeSelection newSelection = new TreeSelection(treePath);
    treeView.setSelection(newSelection, true);
    treeView.expandToLevel(((AnnotationCheckTreeNode) newSelection.getFirstElement()).getParent(),
            TreeViewer.ALL_LEVELS);

    if (firstElement.getElement() instanceof CheckAnnotation) {
      boolean allChecked = true;
      boolean oneKeep = false;
      for (IAnnotationCheckTreeNode each : list) {
        CheckElement element = each.getElement();
        allChecked &= element.checked;
        oneKeep |= element.keep;
      }
      parent.getElement().checked = allChecked;
      parent.getElement().keep = oneKeep;
    }

  }

  public void save() {
    AnnotationCheckTreeNode root = (AnnotationCheckTreeNode) treeView.getInput();
    AnnotationCheckTreeNode[] children = root.getChildren();
    List<CheckDocument> docs = new ArrayList<CheckDocument>(oldDocs);
    TypeSystemDescription tsd = getTypeSystemDescription();
    CAS casSource = null;
    CAS cas = null;
    try {
      cas = CasCreationUtils.createCas(tsd, null, new FsIndexDescription[0]);
      casSource = CasCreationUtils.createCas(tsd, null, new FsIndexDescription[0]);
    } catch (ResourceInitializationException e) {
      RutaAddonsPlugin.error(e);
    }
    for (AnnotationCheckTreeNode each : children) {
      CheckDocument cd = (CheckDocument) each.getElement();
      if (cd.checked && cd.keep) {
        cas.reset();
        File oldFile = new File(cd.source);
        File goldFile = new File(documentSink.getText(), oldFile.getName());
        try {
          if (goldFile.exists()) {
            XmiCasDeserializer.deserialize(new FileInputStream(goldFile), cas, false);
          } else {
            XmiCasDeserializer.deserialize(new FileInputStream(oldFile), cas, true);
          }
        } catch (FileNotFoundException e) {
          RutaAddonsPlugin.error(e);
        } catch (SAXException e) {
          RutaAddonsPlugin.error(e);
        } catch (IOException e) {
          RutaAddonsPlugin.error(e);
        }
        String documentText = cas.getDocumentText();
        cas.reset();
        cas.setDocumentText(documentText);
        try {
          XmiCasDeserializer.deserialize(new FileInputStream(oldFile), casSource, true);
        } catch (FileNotFoundException e) {
          RutaAddonsPlugin.error(e);
        } catch (SAXException e) {
          RutaAddonsPlugin.error(e);
        } catch (IOException e) {
          RutaAddonsPlugin.error(e);
        }
        for (String uncheckedTypeName : typesToTransferUnchecked) {
          Type type = cas.getTypeSystem().getType(uncheckedTypeName);
          if (type != null) {
            for (AnnotationFS annot : casSource.getAnnotationIndex(type)) {
              cas.addFsToIndexes(cas.createAnnotation(type, annot.getBegin(), annot.getEnd()));
            }
          }
        }
        AnnotationCheckTreeNode[] annotationNodes = each.getChildren();
        for (AnnotationCheckTreeNode eachAN : annotationNodes) {
          CheckAnnotation ca = (CheckAnnotation) eachAN.getElement();
          if (ca.checked && ca.keep) {
            TypeSystem ts = casEditor.getDocument().getCAS().getTypeSystem();
            Type type = ts.getType(ca.getTypeName());
            if (type != null) {
              AnnotationFS annotFS = ca.toAnnotationFS(cas, type);
              if (eachAN.hasChildren()) {
                FeatureCheckTreeNode[] featureNodes = (FeatureCheckTreeNode[]) eachAN.getChildren();
                for (FeatureCheckTreeNode featureNode : featureNodes) {
                  Feature feature = featureNode.getFeature();
                  try {
                    annotFS.setFeatureValueFromString(feature, featureNode.getValue());
                  } catch (Exception e) {
                    continue;
                  }
                }
              }
              cas.addFsToIndexes(annotFS);
            }
          }
        }
        try {
          CheckAnnotationUtils.writeXmi(cas, goldFile);
        } catch (Exception e) {
          RutaAddonsPlugin.error(e);
        }
        cd.checkedTypes.addAll(typesToCheck.keySet());
        if (!docs.contains(cd)) {
          docs.add(cd);
        }
      }
      File dataFile = new File(documentSink.getText(), "data.xml");
      try {
        XMLUtils.write(docs, dataFile);
      } catch (IOException e) {
        RutaAddonsPlugin.error(e);
      }
    }
  }

  public void setAnnotationMode(String typeString) {
    this.annotationMode = typeString;
    if (casEditor != null && casEditor.getDocument() != null && typeString != null) {
      Type type = casEditor.getDocument().getCAS().getTypeSystem().getType(typeString);
      casEditor.setAnnotationMode(type);
    }
  }

  public void addAnnotations(Collection<AnnotationFS> annotations) {
    AnnotationCheckRootNode root = (AnnotationCheckRootNode) treeView.getInput();
    AnnotationCheckTreeNode[] children = root.getChildren();
    for (AnnotationCheckTreeNode docNode : children) {
      if (docNode.getElement().equals(currentDocument)) {
        List<AnnotationCheckTreeNode> annotationList = new ArrayList<AnnotationCheckTreeNode>(
                Arrays.asList(docNode.getChildren()));
        for (AnnotationFS eachAnnotation : annotations) {
          CheckElement ac = new CheckAnnotation(eachAnnotation);
          ac.checked = true;
          ac.keep = true;
          AnnotationCheckTreeNode anode = new AnnotationCheckTreeNode(docNode, ac);
          annotationList.add(anode);
        }
        Collections.sort(annotationList, comparator);
        docNode.setChildren(annotationList);
        treeView.refresh(docNode);
        break;
      }
    }
  }

  public void removeAnnotations(Collection<AnnotationFS> annotations) {
    AnnotationCheckRootNode root = (AnnotationCheckRootNode) treeView.getInput();
    AnnotationCheckTreeNode[] children = root.getChildren();
    for (AnnotationCheckTreeNode docNode : children) {
      if (docNode.getElement().equals(currentDocument)) {
        AnnotationCheckTreeNode[] achildren = docNode.getChildren();
        for (AnnotationCheckTreeNode anode : achildren) {
          CheckAnnotation checkAnnotation = (CheckAnnotation) anode.getElement();
          for (AnnotationFS eachAnnotation : annotations) {
            if (eachAnnotation.getBegin() == checkAnnotation.getBegin()
                    && eachAnnotation.getEnd() == checkAnnotation.getBegin()
                    && eachAnnotation.getType().getName().equals(checkAnnotation.getTypeName())) {
              checkAnnotation.checked = true;
              checkAnnotation.keep = false;
            }
          }
        }
        treeView.refresh(docNode);
        break;
      }
    }
  }

  public void updateAnnotations(Collection<AnnotationFS> annotations) {
  }

  public void setOldDocs(List<CheckDocument> docs) {
    this.oldDocs = docs;

  }

  public TypeSystemDescription getTypeSystemDescription() {
    refreshTypeSystem();
    return tsd;
  }

  public void refreshTypeSystem() {
    try {
      String typeSystem = getPathToTypeSystem();
      TypeSystemDescription tsd = UIMAFramework.getXMLParser().parseTypeSystemDescription(
              new XMLInputSource(new File(typeSystem)));
      tsd.resolveImports();
      this.tsd = tsd;
    } catch (InvalidXMLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Map<String, Set<String>> getUncheckedTypes() {
    Map<String, Set<String>> map = new HashMap<String, Set<String>>();
    for (String entry : typesToTransferUnchecked) {
      map.put(entry, null);
    }
    return map;
  }

  public void setUncheckedTypes(Set<String> typesToTransferUnchecked) {
    this.typesToTransferUnchecked = typesToTransferUnchecked;
  }

}
