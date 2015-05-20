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

package org.apache.uima.ruta.cde.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.ruta.cde.utils.CDEComparatorFactory;
import org.apache.uima.ruta.cde.utils.DocumentData;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.part.FileEditorInput;

public class DocumentSelectComposite extends Composite {

  private Clipboard clipboard;

  private Table table;

  private TableViewer tableViewer;

  private DocumentTableLabelProvider labelProvider;

  private DocumentTableContentProvider contentProvider;

  private ArrayList<DocumentData> documentList;

  private HashMap<String, Image> images;

  private Label directoryLabel;

  private Label typeSystemLabel;

  private Text inputDirectoryText;

  private Label testDataLabel;

  private Text testDirectoryText;

  private Button testDataButton;

  private Button dirButton;

  private Button delButton;

  private Button tsButton;

  private Text tsLocationText;

  private TableColumn tc0;

  private TableColumn tc1;

  private TableColumn tc2;

  private TableColumn tc3;

  private Text measuresText;

  private CDEComparatorFactory compareFactory;

  public DocumentSelectComposite(Composite parent, int style) {
    super(parent, style);
    initGui();
    clipboard = new Clipboard(parent.getDisplay());
    compareFactory = new CDEComparatorFactory();
  }

  public void initGui() {
    documentList = new ArrayList<DocumentData>();

    this.setLayout(new FormLayout());
    this.setSize(600, 380);
    directoryLabel = new Label(this, SWT.NONE);
    FormData label1LData = new FormData();
    label1LData.left = new FormAttachment(0, 10);
    label1LData.top = new FormAttachment(0, 10);
    label1LData.width = 80;
    directoryLabel.setLayoutData(label1LData);
    directoryLabel.setText("Documents:");

    delButton = new Button(this, SWT.PUSH | SWT.CENTER);
    FormData delButtonData = new FormData();
    delButtonData.width = 25;
    delButtonData.height = 25;
    delButtonData.top = new FormAttachment(directoryLabel, 0, SWT.CENTER);
    delButtonData.right = new FormAttachment(100, -10);
    delButton.setLayoutData(delButtonData);
    Image folderIcon = getImage("delete");
    delButton.setImage(folderIcon);
    delButton.setToolTipText("Remove selected documents");
    delButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        int[] indices = table.getSelectionIndices();

        for (int i = indices.length - 1; i >= 0; i--) {
          documentList.remove(indices[i]);
        }

        tableViewer.setInput(documentList);
        tableViewer.refresh();
      }
    });

    dirButton = new Button(this, SWT.PUSH | SWT.CENTER);
    FormData dirButtonData = new FormData();
    dirButtonData.width = 25;
    dirButtonData.height = 25;
    dirButtonData.top = new FormAttachment(directoryLabel, 0, SWT.CENTER);
    // dirButtonData.left = new FormAttachment(inputDirectoryText, 10);
    dirButtonData.right = new FormAttachment(delButton, -10);
    dirButton.setLayoutData(dirButtonData);
    folderIcon = getImage("folder");
    dirButton.setImage(folderIcon);
    dirButton.setToolTipText("Select document Folder");
    dirButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        DirectoryDialog dlg = new DirectoryDialog(getShell());
        dlg.setFilterPath(inputDirectoryText.getText());
        dlg.setText("Input Directory");
        dlg.setMessage("Select a directory with input document files.");
        String dir = dlg.open();
        if (dir != null) {
          documentList = new ArrayList<DocumentData>();
          File directory = new File(dir);

          String[] files = directory.list();

          for (String filePath : files) {
            DocumentData documentData = new DocumentData(new File(directory
                    + System.getProperty("file.separator") + filePath));
            documentList.add(documentData);
          }
          inputDirectoryText.setText(dir);
          tableViewer.setInput(documentList);
          tableViewer.refresh();
        }
      }
    });

    inputDirectoryText = new Text(this, SWT.SINGLE | SWT.BORDER);
    FormData inputDirectoryTextData = new FormData();
    // inputDirectoryTextData.width = 150;
    inputDirectoryTextData.left = new FormAttachment(directoryLabel, 10);
    inputDirectoryTextData.top = new FormAttachment(directoryLabel, 0, SWT.CENTER);
    inputDirectoryTextData.right = new FormAttachment(dirButton, -12);
    inputDirectoryText.setLayoutData(inputDirectoryTextData);
    inputDirectoryText.setText("");

    inputDirectoryText.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        // without that listener, the text fields forget the
        // last change when leaving with tab! don't know why!
        // we also MUST call getText() otherwise the changes in
        // the field are lost (what is this???!!)
        Text t = (Text) e.widget;
        t.getText();
        // updateViewer(t.getText());
      }
    });

    // GUI Definitions for Test Data Selection Components

    testDataLabel = new Label(this, SWT.NONE);
    FormData testDataLabelData = new FormData();
    testDataLabelData.left = new FormAttachment(0, 10);
    // testDataLabelData.right = new FormAttachment(100, -12);
    testDataLabelData.top = new FormAttachment(directoryLabel, 15);
    testDataLabel.setLayoutData(testDataLabelData);
    testDataLabel.setText("Test Data:");

    testDataButton = new Button(this, SWT.PUSH | SWT.CENTER);
    FormData testDataButtonData = new FormData();
    testDataButtonData.width = 25;
    testDataButtonData.height = 25;
    testDataButtonData.top = new FormAttachment(testDataLabel, 0, SWT.CENTER);
    testDataButtonData.right = new FormAttachment(100, -10);
    testDataButton.setLayoutData(testDataButtonData);
    folderIcon = getImage("folder");
    testDataButton.setImage(folderIcon);
    testDataButton.setToolTipText("Select the folder containing the test files");

    testDataButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        DirectoryDialog dlg2 = new DirectoryDialog(getShell());
        dlg2.setFilterPath(testDirectoryText.getText());
        dlg2.setText("Location of the test files");
        String dir = dlg2.open();
        if (dir != null) {
          testDirectoryText.setText(dir);
        }
      }
    });

    testDirectoryText = new Text(this, SWT.SINGLE | SWT.BORDER);
    FormData testDirectoryTextData = new FormData();
    testDirectoryTextData.top = new FormAttachment(testDataLabel, 0, SWT.CENTER);
    testDirectoryTextData.left = new FormAttachment(directoryLabel, 10);
    testDirectoryTextData.right = new FormAttachment(testDataButton, -10);
    testDirectoryText.setLayoutData(testDirectoryTextData);
    testDirectoryText.setText("");

    testDirectoryText.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        // without that listener, the text fields forget the
        // last change when leaving with tab! don't know why!
        // we also MUST call getText() otherwise the changes in
        // the field are lost (what is this???!!)
        Text t = (Text) e.widget;
        t.getText();
        // updateViewer(t.getText());
      }
    });

    // GUI Code for TypeSystem Selection

    this.typeSystemLabel = new Label(this, SWT.NONE);
    FormData tSLabelData = new FormData();
    tSLabelData.left = new FormAttachment(0, 10);
    tSLabelData.top = new FormAttachment(testDataLabel, 15);
    tSLabelData.width = 80;
    typeSystemLabel.setLayoutData(tSLabelData);
    typeSystemLabel.setText("Type System:");

    tsButton = new Button(this, SWT.PUSH | SWT.CENTER);
    FormData tsButtonData = new FormData();
    tsButtonData.width = 25;
    tsButtonData.height = 25;
    tsButtonData.top = new FormAttachment(typeSystemLabel, 0, SWT.CENTER);
    tsButtonData.right = new FormAttachment(100, -10);
    tsButton.setLayoutData(tsButtonData);
    folderIcon = getImage("folder");
    tsButton.setImage(folderIcon);
    tsButton.setToolTipText("Select the Type System");
    tsButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        FileDialog dlg = new FileDialog(getShell());
        dlg.setFilterPath(tsLocationText.getText());
        dlg.setText("Type System Location");
        String dir = dlg.open();
        if (dir != null) {
          tsLocationText.setText(dir);
        }
      }
    });

    tsLocationText = new Text(this, SWT.SINGLE | SWT.BORDER);
    FormData tsLocationTextData = new FormData();
    tsLocationTextData.left = new FormAttachment(typeSystemLabel, 10);
    tsLocationTextData.top = new FormAttachment(typeSystemLabel, 0, SWT.CENTER);
    tsLocationTextData.right = new FormAttachment(tsButton, -10);
    tsLocationText.setLayoutData(tsLocationTextData);
    tsLocationText.setText("");

    tsLocationText.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        // without that listener, the text fields forget the
        // last change when leaving with tab! don't know why!
        // we also MUST call getText() otherwise the changes in
        // the field are lost (what is this???!!)
        Text t = (Text) e.widget;
        t.getText();
      }
    });

    measuresText = new Text(this, SWT.READ_ONLY);
    FormData cNrTextData = new FormData();
    cNrTextData.left = new FormAttachment(0, 10);
    cNrTextData.top = new FormAttachment(typeSystemLabel, 15);
    cNrTextData.right = new FormAttachment(100, -10);
    // cNrLabelData.width = 30;
    measuresText.setLayoutData(cNrTextData);
    measuresText.setText("... no measures available yet");

    tableViewer = new TableViewer(this, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL
            | SWT.FULL_SELECTION);

    labelProvider = new DocumentTableLabelProvider();
    tableViewer.setLabelProvider(labelProvider);
    contentProvider = new DocumentTableContentProvider();
    tableViewer.setContentProvider(contentProvider);

    tableViewer.addDoubleClickListener(new IDoubleClickListener() {
      public void doubleClick(DoubleClickEvent event) {
        if (event.getSelection() instanceof IStructuredSelection) {
          IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
          Object object = selection.getFirstElement();
          if (object instanceof DocumentData) {
            DocumentData data = (DocumentData) object;
            Path path = new Path(data.getDocument().getAbsolutePath());

            IFile file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path);
            // IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);

            IWorkbenchPage page = Workbench.getInstance().getActiveWorkbenchWindow()
                    .getActivePage();
            try {
              page.openEditor(new FileEditorInput(file), "org.apache.uima.caseditor.editor");
            } catch (PartInitException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
        }
      }
    });

    this.table = tableViewer.getTable();
    FormData tableFormData = new FormData();
    tableFormData.top = new FormAttachment(measuresText, 15);
    tableFormData.bottom = new FormAttachment(100, 0);
    tableFormData.left = new FormAttachment(0, 0);
    tableFormData.right = new FormAttachment(100, 0);
    table.setLayoutData(tableFormData);
    table.addKeyListener(new KeyListener() {

      public void keyPressed(KeyEvent e) {
        if (((e.stateMask & SWT.CTRL) == SWT.CTRL) && (e.keyCode == 'c')) {
          String output = "";
          TableItem[] items = table.getSelection();
          for (TableItem item : items) {
            DocumentData data = (DocumentData) item.getData();
            output = output + data.getDocument().getName() + ", " + data.getAugmentedResult()
                    + ", " + data.getFMeasure() + ", \n";
          }
          clipboard.setContents(new Object[] { output },
                  new Transfer[] { TextTransfer.getInstance() });
        }
      }

      public void keyReleased(KeyEvent arg0) {
      }
    });

    tc0 = new TableColumn(table, SWT.LEFT);
    tc0.setText("  ");
    tc0.setWidth(25);

    tc1 = new TableColumn(table, SWT.LEFT);
    tc1.setText("Document");
    tc1.setWidth(160);
    tc1.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        Comparator comparator = compareFactory.getComparator(tc1);
        Collections.sort(documentList, comparator);
        tableViewer.setInput(documentList);
        tableViewer.refresh();
      }
    });

    tc2 = new TableColumn(table, SWT.RIGHT);
    tc2.setText("CDE");
    tc2.setWidth(45);
    tc2.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        Comparator comparator = compareFactory.getComparator(tc2);
        Collections.sort(documentList, comparator);
        tableViewer.setInput(documentList);
        tableViewer.refresh();
      }
    });

    tc3 = new TableColumn(table, SWT.RIGHT);
    tc3.setText("F1");
    tc3.setWidth(45);
    tc3.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        Comparator comparator = compareFactory.getComparator(tc3);
        Collections.sort(documentList, comparator);
        tableViewer.setInput(documentList);
        tableViewer.refresh();
      }
    });

    DropTarget dt = new DropTarget(table, DND.DROP_DEFAULT | DND.DROP_MOVE);
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
          for (int i = 0; i < fileList.length; i++) {
            File file = new File(fileList[i]);
            if (file.isDirectory()) {

            } else {
              DocumentData newDocument = new DocumentData(file);
              documentList.add(newDocument);
              tableViewer.setInput(documentList);
              tableViewer.refresh();
            }
          }
        }
      }
    });

    DropTarget dt2 = new DropTarget(inputDirectoryText, DND.DROP_DEFAULT | DND.DROP_MOVE);
    dt2.setTransfer(new Transfer[] { FileTransfer.getInstance() });
    dt2.addDropListener(new DropTargetAdapter() {
      @Override
      public void drop(DropTargetEvent event) {
        String fileList[] = null;
        FileTransfer ft = FileTransfer.getInstance();
        if (ft.isSupportedType(event.currentDataType)) {
          fileList = (String[]) event.data;
        }
        if (fileList != null && fileList.length == 1) {
          String string = fileList[0];
          inputDirectoryText.setText(string);
          setDocumentsByDir();
        }
      }
    });

    DropTarget dt3 = new DropTarget(testDirectoryText, DND.DROP_DEFAULT | DND.DROP_MOVE);
    dt3.setTransfer(new Transfer[] { FileTransfer.getInstance() });
    dt3.addDropListener(new DropTargetAdapter() {
      @Override
      public void drop(DropTargetEvent event) {
        String fileList[] = null;
        FileTransfer ft = FileTransfer.getInstance();
        if (ft.isSupportedType(event.currentDataType)) {
          fileList = (String[]) event.data;
        }
        if (fileList != null && fileList.length == 1) {
          String string = fileList[0];
          testDirectoryText.setText(string);
        }
      }
    });

    DropTarget dt4 = new DropTarget(tsLocationText, DND.DROP_DEFAULT | DND.DROP_MOVE);
    dt4.setTransfer(new Transfer[] { FileTransfer.getInstance() });
    dt4.addDropListener(new DropTargetAdapter() {
      @Override
      public void drop(DropTargetEvent event) {
        String fileList[] = null;
        FileTransfer ft = FileTransfer.getInstance();
        if (ft.isSupportedType(event.currentDataType)) {
          fileList = (String[]) event.data;
        }
        if (fileList != null && fileList.length == 1) {
          String string = fileList[0];
          File file = new File(string);
          if (file.isFile()) {
            if (file.getName().endsWith(RutaEngine.SCRIPT_FILE_EXTENSION)) {
              String typeSystemDescriptorLocation = "";
              try {
                typeSystemDescriptorLocation = RutaProjectUtils.getTypeSystemDescriptorPath(string)
                        .toPortableString();
              } catch (CoreException e) {
                RutaAddonsPlugin.error(e);
              }
              tsLocationText.setText(typeSystemDescriptorLocation);
            } else if (file.getName().endsWith(".xml")) {
              tsLocationText.setText(string);
            }
          }

        }
      }
    });

    table.setHeaderVisible(true);
    table.setLinesVisible(true);
    tableViewer.refresh();
    this.layout();

  }

  private void initImages() {
    images = new HashMap<String, Image>();
    ImageDescriptor desc;
    Image image;
    String name;

    desc = RutaAddonsPlugin.getImageDescriptor("/icons/delete.png");
    image = desc.createImage();
    name = "delete";
    images.put(name, image);

    desc = RutaAddonsPlugin.getImageDescriptor("/icons/folder.png");
    image = desc.createImage();
    name = "folder";
    images.put(name, image);

  }

  public Image getImage(String name) {
    if (images == null) {
      initImages();
    }
    return images.get(name);
  }

  public void updateViewer(String documentPath) {
    File folder = new File(documentPath);
    File[] documents = folder.listFiles();
    tableViewer.setInput(documents);

  }

  public TableViewer getViewer() {
    return this.tableViewer;
  }

  public ArrayList<DocumentData> getDocumentList() {
    return this.documentList;
  }

  public File getTypeSystem() {
    return new File(tsLocationText.getText());
  }

  public void saveState(IMemento memento) {
    memento.createChild("inputDirectory", inputDirectoryText.getText());
    memento.createChild("testDirectory", testDirectoryText.getText());
    memento.createChild("tsLocation", tsLocationText.getText());
    memento.createChild("documents");
    for (DocumentData documentData : documentList) {
      String docPath = documentData.getDocument().getAbsolutePath();
      String docName = "document";
      IMemento documentMemento = memento.getChild("documents");
      documentMemento.createChild(docName, docPath);
    }
  }

  public void restoreState(IMemento memento) {
    if (memento == null) {
      return;
    }
    IMemento inputDirectory = memento.getChild("inputDirectory");
    if (inputDirectory != null) {
      inputDirectoryText.setText(inputDirectory.getID());
    }
    IMemento testDirectory = memento.getChild("testDirectory");
    if (testDirectory != null) {
      testDirectoryText.setText(testDirectory.getID());
    }
    IMemento tsLocation = memento.getChild("tsLocation");
    if (tsLocation != null) {
      tsLocationText.setText(tsLocation.getID());
    }
    IMemento documents = memento.getChild("documents");
    if (documents != null) {
      for (IMemento document : documents.getChildren("document")) {
        DocumentData docData = new DocumentData(new File(document.getID()));
        documentList.add(docData);
      }
      tableViewer.setInput(documentList);
      tableViewer.refresh();
    }

  }

  public String getTestDataPath() {
    return testDirectoryText.getText();
  }

  public String getDocumentFolderPath() {
    return inputDirectoryText.getText();
  }

  public void updateMeasureReport(String measureReport) {
    measuresText.setText(measureReport);
    measuresText.redraw();
  }

  public void setDocumentsByDir(File dir) {
    documentList = new ArrayList<DocumentData>();
    Iterator<File> iterateFiles = FileUtils.iterateFiles(dir, new String[] { "xmi" }, true);
    while (iterateFiles.hasNext()) {
      File file = (File) iterateFiles.next();
      DocumentData documentData = new DocumentData(file);
      documentList.add(documentData);
    }
    tableViewer.setInput(documentList);
    tableViewer.refresh();
  }

  public void setDocumentsByDir() {
    File file = new File(inputDirectoryText.getText());
    setDocumentsByDir(file);
  }

}
