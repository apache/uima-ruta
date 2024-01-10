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

package org.apache.uima.ruta.query.ui;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.uima.caseditor.editor.AnnotationEditor;
import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.ruta.ide.core.RutaLanguageToolkit;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.ui.DLTKUILanguageManager;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
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
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;

public class QueryComposite extends org.eclipse.swt.widgets.Composite
        implements ISelectionChangedListener {
  protected ScriptSourceViewer viewer;

  private HashMap<String, Image> images;

  private Button dirButton;

  private Text dataLocationField;

  private Label dataLocationLabel;

  private Label typeSystemLabel;

  private Text typeSystemFileText;

  private Button fileChooseButton;

  private Button recursiveButton;

  // private Button startButton;

  private TableViewer resultViewer;

  private Label resultLabel;

  private Label fileFilterLabel;

  private Text inputPatternText;

  private ControlDecoration decoFileFilterPattern;

  private ControlDecoration decoQueryRules;

  private Clipboard clipboard;

  public QueryComposite(Composite parent, int style) {
    super(parent, style);
    // initImages();
    clipboard = new Clipboard(parent.getDisplay());
    initGUI();

    ScrolledComposite sComp = new ScrolledComposite(parent,
            SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
    this.setParent(sComp);
    sComp.setMinSize(this.getSize());
    sComp.setContent(this);
    sComp.setExpandHorizontal(true);
    sComp.setExpandVertical(true);
  }

  private void initGUI() {
    try {
      this.setLayout(new FormLayout());
      this.setSize(600, 380);

      // first row

      dataLocationLabel = new Label(this, SWT.NONE);
      FormData label1LData = new FormData();
      label1LData.left = new FormAttachment(0, 1000, 12);
      label1LData.top = new FormAttachment(0, 1000, 10);
      label1LData.width = 70;
      dataLocationLabel.setLayoutData(label1LData);
      dataLocationLabel.setText("Data location");

      dataLocationField = new Text(this, SWT.SINGLE | SWT.BORDER);
      FormData inputDirectoryTexLData = new FormData();
      inputDirectoryTexLData.width = 150;
      inputDirectoryTexLData.left = new FormAttachment(dataLocationLabel, 10);
      inputDirectoryTexLData.top = new FormAttachment(0, 1000, 10);
      inputDirectoryTexLData.right = new FormAttachment(1000, 1000, -65);
      dataLocationField.setLayoutData(inputDirectoryTexLData);
      dataLocationField.setText("");
      dataLocationField.addModifyListener(new ModifyListener() {
        @Override
        public void modifyText(ModifyEvent e) {
          // without that listener, the text fields forget the
          // last change when leaving with tab! don't know why!
          // we also MUST call getText() otherwise the changes in
          // the field are lost (what is this???!!)
          Text t = (Text) e.widget;
          t.getText();
        }
      });

      dirButton = new Button(this, SWT.PUSH | SWT.CENTER);
      FormData dirButtoLData = new FormData();
      dirButtoLData.width = 25;
      dirButtoLData.height = 25;
      dirButtoLData.top = new FormAttachment(0, 1000, 8);
      dirButtoLData.right = new FormAttachment(1000, 1000, -35);
      dirButton.setLayoutData(dirButtoLData);
      Image folderIcon = getImage("folder");
      dirButton.setImage(folderIcon);
      dirButton.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent event) {
          DirectoryDialog dlg = new DirectoryDialog(getShell());
          dlg.setFilterPath(dataLocationField.getText());
          dlg.setText("Data location");
          dlg.setMessage("Select a directory with input XMI files.");
          String dir = dlg.open();
          if (dir != null) {
            dataLocationField.setText(dir);
          }
        }
      });

      recursiveButton = new Button(this, SWT.CHECK | SWT.LEFT);
      FormData recuriveButtonLData = new FormData();
      recuriveButtonLData.width = 20;
      recuriveButtonLData.height = 18;
      recuriveButtonLData.top = new FormAttachment(0, 1000, 10);
      recuriveButtonLData.right = new FormAttachment(1000, 1000, -10);
      recursiveButton.setLayoutData(recuriveButtonLData);
      recursiveButton.setSelection(true);

      // next row

      FormAttachment top2 = new FormAttachment(dataLocationLabel, 10);
      fileFilterLabel = new Label(this, SWT.NONE);
      FormData label2LData = new FormData();
      label2LData.left = new FormAttachment(0, 1000, 12);
      label2LData.top = top2; // new FormAttachment(0, 1000, 34);
      label2LData.width = 70;
      fileFilterLabel.setLayoutData(label2LData);
      fileFilterLabel.setText("File filter");

      inputPatternText = new Text(this, SWT.SINGLE | SWT.BORDER);
      FormData inputPatternTextData = new FormData();
      inputPatternTextData.width = 150;
      inputPatternTextData.left = new FormAttachment(fileFilterLabel, 10);
      inputPatternTextData.top = top2;
      inputPatternTextData.right = new FormAttachment(1000, 1000, -65);
      inputPatternText.setLayoutData(inputPatternTextData);
      inputPatternText.setText(".+\\.xmi");

      decoFileFilterPattern = new ControlDecoration(this.inputPatternText, SWT.TOP | SWT.LEFT);
      Image imageError = FieldDecorationRegistry.getDefault()
              .getFieldDecoration(FieldDecorationRegistry.DEC_ERROR).getImage();
      decoFileFilterPattern
              .setDescriptionText("PatternSyntaxException for this regular expression.");
      decoFileFilterPattern.setImage(imageError);
      decoFileFilterPattern.hide();
      inputPatternText.addModifyListener(e -> decoFileFilterPattern.hide());

      // next row

      FormAttachment top3 = new FormAttachment(fileFilterLabel, 10);
      typeSystemLabel = new Label(this, SWT.NONE);
      FormData label3LData = new FormData();
      label3LData.width = 70;
      label3LData.left = new FormAttachment(0, 1000, 12);
      label3LData.top = top3;
      typeSystemLabel.setLayoutData(label3LData);
      typeSystemLabel.setText("Type system");

      typeSystemFileText = new Text(this, SWT.SINGLE | SWT.BORDER);
      FormData preFileTexLData = new FormData();
      preFileTexLData.width = 150;
      preFileTexLData.left = new FormAttachment(typeSystemLabel, 10);
      preFileTexLData.top = top3;
      preFileTexLData.right = new FormAttachment(1000, 1000, -65);
      typeSystemFileText.setLayoutData(preFileTexLData);
      typeSystemFileText.setText("");
      typeSystemFileText.addModifyListener(new ModifyListener() {
        @Override
        public void modifyText(ModifyEvent e) {
          // without that listener, the text fields forget the
          // last change when leaving with tab! don't know why!
          // we also MUST call getText() otherwise the changes in
          // the field are lost (what is this???!!)
          Text t = (Text) e.widget;
          t.getText();
        }
      });

      fileChooseButton = new Button(this, SWT.PUSH | SWT.CENTER);
      FormData fileChooseButtoLData = new FormData();
      fileChooseButtoLData.width = 25;
      fileChooseButtoLData.height = 25;
      fileChooseButtoLData.top = top3;
      fileChooseButtoLData.right = new FormAttachment(1000, 1000, -35);
      fileChooseButton.setLayoutData(fileChooseButtoLData);
      Image icon = getImage("prepFolder");
      fileChooseButton.setImage(icon);
      fileChooseButton.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent event) {
          FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
          fd.setText("Choose Type System Descriptor or Ruta Script");
          String[] filterExt = { "*.ruta", "*.*" };
          fd.setFilterExtensions(filterExt);
          String file = fd.open();
          if (file != null) {
            typeSystemFileText.setText(file);
          }
        }
      });

      // next row: query rules

      FormAttachment top4 = new FormAttachment(typeSystemLabel, 10);
      Composite compositeQueryRules = new Composite(this, SWT.NULL);
      FormData compData = new FormData();
      compData.height = 100;
      compData.left = new FormAttachment(0, 1000, 10);
      compData.top = top4;
      compData.right = new FormAttachment(1000, 1000, -10);
      compositeQueryRules.setLayoutData(compData);
      compositeQueryRules.setLayout(new FillLayout());
      IDLTKUILanguageToolkit toolkit = DLTKUILanguageManager
              .getLanguageToolkit(RutaLanguageToolkit.getDefault().getNatureId());
      final ScriptTextTools textTools = toolkit.getTextTools();
      IPreferenceStore store = toolkit.getCombinedPreferenceStore();
      viewer = new ScriptSourceViewer(compositeQueryRules, null, null, false,
              SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER, store);

      ScriptSourceViewerConfiguration configuration = textTools
              .createSourceViewerConfiguraton(store, (ITextEditor) null);
      viewer.configure(configuration);
      setInformation("");
      compositeQueryRules.layout();

      viewer.addTextListener(new ITextListener() {

        @Override
        public void textChanged(TextEvent arg0) {
          setRutaQuerySyntaxError(false);
        }
      });

      decoQueryRules = new ControlDecoration(compositeQueryRules, SWT.TOP | SWT.LEFT);
      decoQueryRules.setDescriptionText("Could not run query, maybe illegal Ruta rule syntax.");
      decoQueryRules.setImage(imageError);
      decoQueryRules.hide();
      // TODO
      // inputPatternText.addModifyListener(new ModifyListener() {
      // public void modifyText(ModifyEvent e) {
      // // without that listener, the text fields forget the
      // // last change when leaving with tab! don't know why!
      // // we also MUST call getText() otherwise the changes in
      // // the field are lost (what is this???!!)
      // Text t = (Text) e.widget;
      // decoPattern.hide();
      // }
      // });

      // next row: query results

      FormAttachment top5 = new FormAttachment(compositeQueryRules, 10);

      resultLabel = new Label(this, SWT.NONE);
      FormData resultLabelData = new FormData();
      resultLabelData.left = new FormAttachment(0, 1000, 12);
      resultLabelData.top = top5;
      resultLabelData.width = 300;
      resultLabel.setLayoutData(resultLabelData);
      resultLabel.setText("Result:");

      Composite composite2 = new Composite(this, SWT.NONE);
      FormData comp2Data = new FormData();
      // comp2Data.width = 300;
      // comp2Data.height = 200;
      comp2Data.left = new FormAttachment(0, 1000, 10);
      comp2Data.top = new FormAttachment(resultLabel, 10);
      comp2Data.right = new FormAttachment(1000, 1000, -10);
      comp2Data.bottom = new FormAttachment(1000, 1000, -10);
      // compData.left = new FormAttachment(0, 1000, 12);
      // compData.top = new FormAttachment(0, 1000, 70);
      // compData.bottom = new FormAttachment(0, 1000, -10);
      // compData.right = new FormAttachment(1000, 1000, -10);
      // compData.width = 109;
      // composite2.setSize(500, 500);
      // composite.setBackground(new Color(getDisplay(), 128, 0, 0));
      composite2.setLayoutData(comp2Data);
      composite2.setLayout(new FillLayout());

      resultViewer = new TableViewer(composite2,
              SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.MULTI);
      final QueryResultLabelProvider queryResultLabelProvider = new QueryResultLabelProvider();
      resultViewer.setLabelProvider(queryResultLabelProvider);
      resultViewer.setContentProvider(new QueryResultContentProvider());
      resultViewer.addSelectionChangedListener(this);
      resultViewer.getTable().addKeyListener(new KeyListener() {

        @Override
        public void keyPressed(KeyEvent e) {
          if (((e.stateMask & SWT.CTRL) == SWT.CTRL) && (e.keyCode == 'c')) {
            String output = "";
            TableItem[] items = resultViewer.getTable().getSelection();
            for (TableItem item : items) {
              QueryResult data = (QueryResult) item.getData();
              if (output.length() != 0) {
                output += System.getProperty("line.separator");
              }
              output += queryResultLabelProvider.getText(data);
            }
            clipboard.setContents(new Object[] { output },
                    new Transfer[] { TextTransfer.getInstance() });
          }
        }

        @Override
        public void keyReleased(KeyEvent arg0) {
        }
      });
      resultViewer.addDoubleClickListener(new IDoubleClickListener() {
        @Override
        public void doubleClick(DoubleClickEvent event) {
          Object obj = event.getSelection();
          if (obj instanceof IStructuredSelection) {
            StructuredSelection selection = (StructuredSelection) obj;
            Iterator<?> iterator = selection.iterator();
            while (iterator.hasNext()) {
              Object element = iterator.next();
              if (element instanceof QueryResult) {
                QueryResult data = (QueryResult) element;
                if (data.getFile() != null) {
                  openInCasEditor(data.getFile(), data.getBegin(), data.getEnd());
                }
              }
            }
          }

        }
      });
      composite2.layout();

      this.layout();
    } catch (Exception e) {
      RutaAddonsPlugin.error(e);
    }

    DropTarget dt = new DropTarget(dataLocationField, DND.DROP_DEFAULT | DND.DROP_MOVE);
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
          dataLocationField.setText(fileList[0]);
        }
      }
    });

    DropTarget dt2 = new DropTarget(typeSystemFileText, DND.DROP_DEFAULT | DND.DROP_MOVE);
    dt2.setTransfer(new Transfer[] { FileTransfer.getInstance() });
    dt2.addDropListener(new DropTargetAdapter() {
      @Override
      public void drop(DropTargetEvent event) {
        String fileList[] = null;
        FileTransfer ft = FileTransfer.getInstance();
        if (ft.isSupportedType(event.currentDataType)) {
          fileList = (String[]) event.data;
        }
        if (fileList != null && fileList.length > 0) {
          typeSystemFileText.setText(fileList[0]);
        }
      }
    });

  }

  public void setResultInfo(int found, int files) {
    resultLabel.setText("Result: found " + found + " matches in " + files + " documents");
  }

  public void saveState(IMemento memento) {

    memento.createChild("inputDirectory", dataLocationField.getText());
    memento.createChild("fileFilter", inputPatternText.getText());
    memento.createChild("typeSystemLocation", typeSystemFileText.getText());
    memento.createChild("query", viewer.getDocument().get());
    memento.createChild("recursive", Boolean.toString(recursiveButton.getSelection()));
  }

  public void restoreState(IMemento memento) {
    if (memento == null) {
      return;
    }

    IMemento tsName = memento.getChild("typeSystemLocation");
    if (tsName != null) {
      typeSystemFileText.setText(tsName.getID());
    }

    IMemento dir = memento.getChild("inputDirectory");
    if (dir != null) {
      dataLocationField.setText(dir.getID());
    }

    IMemento fileFilterMemento = memento.getChild("fileFilter");
    if (fileFilterMemento != null) {
      inputPatternText.setText(fileFilterMemento.getID());
    }

    IMemento query = memento.getChild("query");
    if (query != null) {
      setInformation(query.getID());
    }

    IMemento recursive = memento.getChild("recursive");
    if (recursive != null) {
      boolean isRecursive = recursive.getID().equals("true");
      recursiveButton.setSelection(isRecursive);
    }
  }

  protected void openInCasEditor(File file, int begin, int end) {
    if (file == null) {
      return;
    }
    String absolutePath = file.getAbsolutePath();
    try {
      IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
      IFile ifile = getIFile(absolutePath);
      AnnotationEditor editor = (AnnotationEditor) page.openEditor(new FileEditorInput(ifile),
              "org.apache.uima.caseditor.editor");
      editor.selectAndReveal(begin, end - begin);
    } catch (PartInitException e) {
      RutaAddonsPlugin.error(e);
    }
  }

  public static IFile getIFile(String location) {
    IPath path = Path.fromOSString(location);
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    IFile file = workspace.getRoot().getFileForLocation(path);

    if (file == null) {
      // no located in the workspace?
      try {
        IProject project = workspace.getRoot().getProject("External Files");
        if (!project.exists()) {
          project.create(null);
        }
        if (!project.isOpen()) {
          project.open(null);
        }
        IFile extFile = project.getFile(path.lastSegment());
        if (extFile.exists()) {
          extFile.delete(true, false, new NullProgressMonitor());
        }
        extFile.createLink(path, IResource.NONE, null);
        file = extFile;
      } catch (Exception e) {
        RutaAddonsPlugin.error(e);
      }
    }

    return file;
  }

  public void setInformation(String content) {
    if (content == null) {
      viewer.setInput(null);
      return;
    }
    IDocument doc = new Document(content);
    IDLTKUILanguageToolkit uiToolkit = DLTKUILanguageManager
            .getLanguageToolkit(RutaLanguageToolkit.getDefault().getNatureId());
    ScriptTextTools textTools = uiToolkit.getTextTools();
    if (textTools != null) {
      textTools.setupDocumentPartitioner(doc, uiToolkit.getPartitioningId());
    }
    viewer.setInput(doc);
  }

  private void initImages() {
    images = new HashMap<String, Image>();
    ImageDescriptor desc;
    Image image;
    String name;

    desc = RutaAddonsPlugin.getImageDescriptor("/icons/folder_edit.png");
    image = desc.createImage();
    name = "prepFolder";
    images.put(name, image);

    desc = RutaAddonsPlugin.getImageDescriptor("/icons/folder.png");
    image = desc.createImage();
    name = "folder";
    images.put(name, image);

    desc = RutaAddonsPlugin.getImageDescriptor("/icons/start.png");
    image = desc.createImage();
    name = "start";
    images.put(name, image);

    desc = RutaAddonsPlugin.getImageDescriptor("/icons/stop.gif");
    image = desc.createImage();
    name = "stop";
    images.put(name, image);

  }

  public Image getImage(String name) {
    if (images == null) {
      initImages();
    }
    return images.get(name);
  }

  @Override
  public void dispose() {
    if (images != null) {
      for (Image each : images.values()) {
        each.dispose();
      }
    }
    super.dispose();
  }

  public String getDataDirectory() {
    return dataLocationField.getText().trim();
  }

  public String getFileFilter() {
    String string = inputPatternText.getText().trim();
    try {
      Pattern.compile(string);
      this.decoFileFilterPattern.hide();
      return string;
    } catch (PatternSyntaxException e) {
      this.decoFileFilterPattern.show();
      return "";
    }
  }

  public String getTypeSystem() {
    return typeSystemFileText.getText().trim();
  }

  public String getScript() {
    return viewer.getDocument().get();
  }

  public boolean isRecursive() {
    return recursiveButton.getSelection();
  }

  public void setResult(Object object) {
    resultViewer.setInput(object);
    resultViewer.refresh();
  }

  public TableViewer getResultViewer() {
    return resultViewer;
  }

  @Override
  public void selectionChanged(SelectionChangedEvent event) {
    if (event.getSelectionProvider().equals(resultViewer)) {
      var selection = (StructuredSelection) event.getSelection();
      var data = (QueryResult) selection.getFirstElement();
      var page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
      var activeEditor = page.getActiveEditor();
      if (activeEditor instanceof AnnotationEditor ae) {
        IEditorInput editorInput = ae.getEditorInput();
        if (editorInput instanceof FileEditorInput fei) {
          IFile file = fei.getFile();
          if (data != null && file.getLocationURI().equals(data.getFile().toURI())) {
            int begin = data.getBegin();
            int end = data.getEnd();
            ae.selectAndReveal(begin, end - begin);
          }
        }
      }
    }
  }

  public void setInputDirectory(String absolutePath) {
    dataLocationField.setText(absolutePath);
  }

  public void setTypeSystem(String typeSystemLocation) {
    typeSystemFileText.setText(typeSystemLocation);
  }

  public void setRutaQuerySyntaxError(boolean hasError) {
    if (hasError) {
      this.decoQueryRules.show();
    } else {
      this.decoQueryRules.hide();
    }
  }
}
