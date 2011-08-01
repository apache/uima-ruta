package org.apache.uima.tm.textmarker.query.ui;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.uima.tm.dltk.core.TextMarkerLanguageToolkit;
import org.apache.uima.tm.textmarker.query.QueryPlugin;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.ui.DLTKUILanguageManager;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;


public class QueryComposite extends org.eclipse.swt.widgets.Composite {
  protected ScriptSourceViewer viewer;

  private HashMap<String, Image> images;

  private Button dirButton;

  private Text inputDirectoryText;

  private Label label1;

  private Label labelTypeSystem;

  private Text typeSystemFileText;

  private Button fileChooseButton;

  private Button recursiveButton;

  // private Button startButton;

  private TableViewer resultViewer;

  private Label resultLabel;

  public QueryComposite(Composite parent, int style) {
    super(parent, style);

    // initImages();
    initGUI();

    ScrolledComposite sComp = new ScrolledComposite(parent, SWT.BORDER | SWT.V_SCROLL
            | SWT.H_SCROLL);
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

      label1 = new Label(this, SWT.NONE);
      FormData label1LData = new FormData();
      label1LData.left = new FormAttachment(0, 1000, 12);
      label1LData.top = new FormAttachment(0, 1000, 10);
      label1LData.width = 70;
      label1.setLayoutData(label1LData);
      label1.setText("Query Data:");

      inputDirectoryText = new Text(this, SWT.SINGLE | SWT.BORDER);
      FormData inputDirectoryTexLData = new FormData();
      inputDirectoryTexLData.width = 150;
      inputDirectoryTexLData.left = new FormAttachment(0, 1000, 90);
      inputDirectoryTexLData.top = new FormAttachment(0, 1000, 10);
      inputDirectoryTexLData.right = new FormAttachment(1000, 1000, -65);
      inputDirectoryText.setLayoutData(inputDirectoryTexLData);
      inputDirectoryText.setText("");
      inputDirectoryText.addModifyListener(new ModifyListener() {
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
          dlg.setFilterPath(inputDirectoryText.getText());
          dlg.setText("Input Directory");
          dlg.setMessage("Select a directory with input XMI files.");
          String dir = dlg.open();
          if (dir != null) {
            inputDirectoryText.setText(dir);
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

      labelTypeSystem = new Label(this, SWT.NONE);
      FormData label3LData = new FormData();
      label3LData.width = 70;
      label3LData.left = new FormAttachment(0, 1000, 12);
      label3LData.top = new FormAttachment(0, 1000, 34);
      labelTypeSystem.setLayoutData(label3LData);
      labelTypeSystem.setText("Type System:");

      typeSystemFileText = new Text(this, SWT.SINGLE | SWT.BORDER);
      FormData preFileTexLData = new FormData();
      preFileTexLData.width = 150;
      preFileTexLData.left = new FormAttachment(0, 1000, 90);
      preFileTexLData.top = new FormAttachment(0, 1000, 34);
      preFileTexLData.right = new FormAttachment(1000, 1000, -65);
      typeSystemFileText.setLayoutData(preFileTexLData);
      typeSystemFileText.setText("");
      typeSystemFileText.addModifyListener(new ModifyListener() {
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
      fileChooseButtoLData.top = new FormAttachment(0, 1000, 32);
      fileChooseButtoLData.right = new FormAttachment(1000, 1000, -35);
      fileChooseButton.setLayoutData(fileChooseButtoLData);
      Image icon = getImage("prepFolder");
      fileChooseButton.setImage(icon);
      fileChooseButton.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent event) {
          FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
          fd.setText("Choose Type System Descriptor or TextMarker Script");
          String[] filterExt = { "*.tm", "*.*" };
          fd.setFilterExtensions(filterExt);
          String file = fd.open();
          if (file != null) {
            typeSystemFileText.setText(file);
          }
        }
      });

      Composite composite1 = new Composite(this, SWT.CENTER);
      FormData compData = new FormData();
      // compData.width = 300;
      compData.height = 100;
      compData.left = new FormAttachment(0, 1000, 10);
      compData.top = new FormAttachment(0, 1000, 75);
      compData.right = new FormAttachment(1000, 1000, -10);
      // compData.left = new FormAttachment(0, 1000, 12);
      // compData.top = new FormAttachment(0, 1000, 70);
      // compData.bottom = new FormAttachment(0, 1000, -10);
      // compData.right = new FormAttachment(1000, 1000, -10);
      // compData.width = 109;
      // composite1.setSize(500, 500);
      composite1.setLayoutData(compData);
      composite1.setLayout(new FillLayout());
      IDLTKUILanguageToolkit toolkit = DLTKUILanguageManager
              .getLanguageToolkit(TextMarkerLanguageToolkit.getDefault().getNatureId());
      final ScriptTextTools textTools = toolkit.getTextTools();
      IPreferenceStore store = toolkit.getCombinedPreferenceStore();
      viewer = new ScriptSourceViewer(composite1, null, null, false, SWT.H_SCROLL | SWT.V_SCROLL
              | SWT.BORDER, store);

      ScriptSourceViewerConfiguration configuration = textTools.createSourceViewerConfiguraton(
              store, (ITextEditor) null);
      viewer.configure(configuration);
      setInformation("");
      composite1.layout();

      resultLabel = new Label(this, SWT.NONE);
      FormData resultLabelData = new FormData();
      resultLabelData.left = new FormAttachment(0, 1000, 12);
      resultLabelData.top = new FormAttachment(0, 1000, 180);
      resultLabelData.width = 300;
      resultLabel.setLayoutData(resultLabelData);
      resultLabel.setText("Result:");

      Composite composite2 = new Composite(this, SWT.CENTER);
      FormData comp2Data = new FormData();
      // comp2Data.width = 300;
      // comp2Data.height = 200;
      comp2Data.left = new FormAttachment(0, 1000, 10);
      comp2Data.top = new FormAttachment(0, 1000, 195);
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

      resultViewer = new TableViewer(composite2, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
      resultViewer.setLabelProvider(new QueryResultLabelProvider());
      resultViewer.setContentProvider(new QueryResultContentProvider());
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
                  openInCEV(data.getFile());
                }
              }
            }
          }

        }
      });
      composite2.layout();

      this.layout();
    } catch (Exception e) {
      e.printStackTrace();
    }

    DropTarget dt = new DropTarget(inputDirectoryText, DND.DROP_DEFAULT | DND.DROP_MOVE);
    dt.setTransfer(new Transfer[] { FileTransfer.getInstance() });
    dt.addDropListener(new DropTargetAdapter() {
      @Override
      public void drop(DropTargetEvent event) {
        String fileList[] = null;
        FileTransfer ft = FileTransfer.getInstance();
        if (ft.isSupportedType(event.currentDataType)) {
          fileList = (String[]) event.data;
        }
        if (fileList.length > 0)
          inputDirectoryText.setText(fileList[0]);
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
        if (fileList.length > 0)
          typeSystemFileText.setText(fileList[0]);
      }
    });

  }

  public void setResultInfo(int found, int files) {
    resultLabel.setText("Result: found " + found + " matches in " + files + " documents");
  }

  public void saveState(IMemento memento) {

    memento.createChild("inputDirectory", inputDirectoryText.getText());
    memento.createChild("typeSystemLocation", typeSystemFileText.getText());
    memento.createChild("query", viewer.getDocument().get());
    memento.createChild("recursive", Boolean.toString(recursiveButton.getSelection()));
  }

  public void restoreState(IMemento memento) {
    if (memento == null)
      return;

    IMemento tsName = memento.getChild("typeSystemLocation");
    if (tsName != null) {
      typeSystemFileText.setText(tsName.getID());
    }

    IMemento dir = memento.getChild("inputDirectory");
    if (dir != null) {
      inputDirectoryText.setText(dir.getID());
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

  protected void openInCEV(File file) {
    if (file == null) {
      return;
    }
    String absolutePath = file.getAbsolutePath();
    try {
      IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
      IFile ifile = getIFile(absolutePath);
      page.openEditor(new FileEditorInput(ifile), "org.apache.uima.tm.cev.editor.CEVViewer");
    } catch (PartInitException e) {
      e.printStackTrace();
    }
  }

  public static IFile getIFile(String location) {
    IPath s = Path.fromOSString(location);
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    IFile ifile = workspace.getRoot().getFileForLocation(s);
    return ifile;
  }

  public void setInformation(String content) {
    if (content == null) {
      viewer.setInput(null);
      return;
    }
    IDocument doc = new Document(content);
    IDLTKUILanguageToolkit uiToolkit = DLTKUILanguageManager
            .getLanguageToolkit(TextMarkerLanguageToolkit.getDefault().getNatureId());
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

    desc = QueryPlugin.getImageDescriptor("/icons/folder_edit.png");
    image = desc.createImage();
    name = "prepFolder";
    images.put(name, image);

    desc = QueryPlugin.getImageDescriptor("/icons/folder.png");
    image = desc.createImage();
    name = "folder";
    images.put(name, image);

    desc = QueryPlugin.getImageDescriptor("/icons/start.png");
    image = desc.createImage();
    name = "start";
    images.put(name, image);

    desc = QueryPlugin.getImageDescriptor("/icons/stop.gif");
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
    super.dispose();
    if (images != null) {
      for (Image each : images.values()) {
        each.dispose();
      }
    }
  }

  public String getDataDirectory() {
    return inputDirectoryText.getText().trim();
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

}
