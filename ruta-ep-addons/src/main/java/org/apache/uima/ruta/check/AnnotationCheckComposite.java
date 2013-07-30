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
import java.util.Iterator;
import java.util.List;

import org.apache.uima.UIMAFramework;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
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
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
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

  private Text typeSystem;

  private HashMap<String, Image> images;

  private Text documentSink;

  private List<String> selectedTypes;

  private CheckDocument currentDocument = null;

  private String annotationMode;

  private AnnotationEditor casEditor;

  private List<CheckDocument> oldDocs;

  public AnnotationCheckComposite(Composite parent, int style, ViewPart viewPart) {
    super(parent, style);
    this.viewPart = viewPart;
    selectedTypes = new ArrayList<String>();
    initGui();
    annotationListener = new CheckAnnotationDocumentListener(this);
  }

  private void initGui() {
    this.setLayout(new FormLayout());
    this.setSize(400, 800);

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
        if (fileList.length > 0) {
          String fileString = fileList[0];
          documentSource.setText(fileString);
        }
      }
    });

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
        if (fileList.length > 0) {
          String fileString = fileList[0];
          documentSink.setText(fileString);
        }
      }
    });

    typeSystem = new Text(this, SWT.SINGLE | SWT.BORDER);
    FormData fdata2 = new FormData();
    fdata2.width = 200;
    fdata2.left = new FormAttachment(0, 1000, 5);
    fdata2.top = new FormAttachment(0, 1000, 55);
    fdata2.right = new FormAttachment(1000, 1000, -5);
    typeSystem.setLayoutData(fdata2);
    typeSystem.setToolTipText("Type System...");
    typeSystem.setMessage("Type System...");
    DropTarget dt1 = new DropTarget(typeSystem, DND.DROP_DEFAULT | DND.DROP_MOVE);
    dt1.setTransfer(new Transfer[] { FileTransfer.getInstance() });
    dt1.addDropListener(new DropTargetAdapter() {
      @Override
      public void drop(DropTargetEvent event) {
        String fileList[] = null;
        FileTransfer ft = FileTransfer.getInstance();
        if (ft.isSupportedType(event.currentDataType)) {
          fileList = (String[]) event.data;
        }
        if (fileList.length > 0) {
          String fileString = fileList[0];
          typeSystem.setText(fileString);
        }
      }
    });

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
        if (c == '1' || c == '2') {
          if (c == '1') {
            accept();
          } else if (c == '2') {
            reject();
          }
        }
      }

    });

    lableProvider = new AnnotationCheckLabelProvider(this);
    treeView.setLabelProvider(lableProvider);

    viewPart.getSite().getPage().addSelectionListener(this);
    viewPart.getSite().setSelectionProvider(treeView);
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
  }

  public void selectionChanged(IWorkbenchPart part, ISelection selection) {
    if (part instanceof AnnotationCheckView) {
      if (selection instanceof TreeSelection) {
        TreeSelection ts = (TreeSelection) selection;
        if (ts.getFirstElement() instanceof AnnotationCheckTreeNode) {
          AnnotationCheckTreeNode firstElement = (AnnotationCheckTreeNode) ts.getFirstElement();
          CheckElement element = firstElement.getElement();
          int begin = 0;
          int end = 0;
          CheckDocument newDoc = null;
          if (element instanceof CheckAnnotation) {
            begin = ((CheckAnnotation) element).begin;
            end = ((CheckAnnotation) element).end;
            newDoc = ((CheckDocument) firstElement.getParent().getElement());
          } else if (element instanceof CheckDocument) {
            newDoc = ((CheckDocument) element);
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
            Type type = (Type) typeIterator.next();
            boolean contains = selectedTypes.contains(type.getName());
            casEditor.setShownAnnotationType(type, contains);
          }
          currentDocument = newDoc;
          treeView.getControl().setFocus();
        }
      }
    }
  }

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
      typeSystem.setText(id);
    }

    for (IMemento eachMemento : memento.getChildren("selectedTypes")) {
      IMemento child = eachMemento.getChild("type");
      if (child != null) {
        selectedTypes.add(child.getID());
      }
    }

  }

  public void saveState(IMemento memento) {
    memento.createChild("documentSource", documentSource.getText());
    memento.createChild("documentSink", documentSink.getText());
    memento.createChild("typeSystem", typeSystem.getText());

    for (String each : selectedTypes) {
      IMemento currentMemento = memento.createChild("selectedTypes", "type");
      currentMemento.createChild("type", each);
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

  public String getTypeSystem() {
    return typeSystem.getText();
  }

  public List<String> getSelectedTypes() {
    return selectedTypes;
  }

  public void setSelectedTypes(List<String> selection) {
    this.selectedTypes = selection;
  }

  public void reject() {
    TreeSelection selection = (TreeSelection) treeView.getSelection();
    AnnotationCheckTreeNode firstElement = (AnnotationCheckTreeNode) selection.getFirstElement();
    if (firstElement == null) {
      return;
    }
    firstElement.getElement().checked = true;
    firstElement.getElement().keep = false;
    moveToNext();
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
    moveToNext();
    treeView.refresh();
  }

  public void moveToNext() {
    TreeSelection selection = (TreeSelection) treeView.getSelection();
    AnnotationCheckTreeNode firstElement = (AnnotationCheckTreeNode) selection.getFirstElement();
    if (firstElement == null) {
      return;
    }
    IAnnotationCheckTreeNode parent = firstElement.getParent();
    AnnotationCheckTreeNode[] children = parent.getChildren();
    List<AnnotationCheckTreeNode> list = Arrays.asList(children);
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
      AnnotationCheckTreeNode[] children2 = parent.getParent().getChildren();
      List<AnnotationCheckTreeNode> list2 = Arrays.asList(children2);
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

    if (firstElement.getElement() instanceof CheckAnnotation) {
      boolean allChecked = true;
      boolean oneKeep = false;
      for (AnnotationCheckTreeNode each : list) {
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
    TypeSystemDescription tsd = null;
    try {
      tsd = UIMAFramework.getXMLParser().parseTypeSystemDescription(
              new XMLInputSource(typeSystem.getText()));
      tsd.resolveImports();
    } catch (InvalidXMLException e) {
      RutaAddonsPlugin.error(e);
    } catch (IOException e) {
      RutaAddonsPlugin.error(e);
    }
    CAS cas = null;
    try {
      cas = CasCreationUtils.createCas(tsd, null, new FsIndexDescription[0]);
    } catch (ResourceInitializationException e) {
      RutaAddonsPlugin.error(e);
    }

    for (AnnotationCheckTreeNode each : children) {
      CheckDocument cd = (CheckDocument) each.getElement();
      if (cd.checked && cd.keep) {
        cas.reset();
        File oldFile = new File(cd.source);
        File goldFile = new File(documentSink.getText(), oldFile.getName());
        if (goldFile.exists()) {
          try {
            XmiCasDeserializer.deserialize(new FileInputStream(goldFile), cas, false);
          } catch (FileNotFoundException e) {
            RutaAddonsPlugin.error(e);
          } catch (SAXException e) {
            RutaAddonsPlugin.error(e);
          } catch (IOException e) {
            RutaAddonsPlugin.error(e);
          }
        } else {
          try {
            XmiCasDeserializer.deserialize(new FileInputStream(oldFile), cas, true);
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
        }
        AnnotationCheckTreeNode[] annotationNodes = each.getChildren();
        for (AnnotationCheckTreeNode eachAN : annotationNodes) {
          CheckAnnotation ca = (CheckAnnotation) eachAN.getElement();
          if (ca.checked && ca.keep) {
            Type type = cas.getTypeSystem().getType(ca.type);
            if (type != null) {
              AnnotationFS createAnnotation = cas.createAnnotation(type, ca.begin, ca.end);
              cas.addFsToIndexes(createAnnotation);
            }
          }
        }
        try {
          CheckAnnotationUtils.writeXmi(cas, goldFile);
        } catch (Exception e) {
          RutaAddonsPlugin.error(e);
        }
        cd.checkedTypes.addAll(selectedTypes);
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

  public void addedAnnotation(Collection<AnnotationFS> annotations) {
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

  public void removedAnnotation(Collection<AnnotationFS> annotations) {
    AnnotationCheckRootNode root = (AnnotationCheckRootNode) treeView.getInput();
    AnnotationCheckTreeNode[] children = root.getChildren();
    for (AnnotationCheckTreeNode docNode : children) {
      if (docNode.getElement().equals(currentDocument)) {
        AnnotationCheckTreeNode[] achildren = docNode.getChildren();
        for (AnnotationCheckTreeNode anode : achildren) {
          CheckAnnotation e = (CheckAnnotation) anode.getElement();
          for (AnnotationFS eachAnnotation : annotations) {
            if (eachAnnotation.getBegin() == e.begin && eachAnnotation.getEnd() == e.end
                    && eachAnnotation.getType().getName().equals(e.type)) {
              e.checked = true;
              e.keep = false;
            }
          }
        }
        treeView.refresh(docNode);
        break;
      }
    }
  }

  public void updatedAnnotation(Collection<AnnotationFS> annotations) {
  }

  public void setOldDocs(List<CheckDocument> docs) {
    this.oldDocs = docs;

  }

}
