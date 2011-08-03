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

package org.apache.uima.tm.textruler.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.UIMAFramework;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.tm.textruler.TextRulerPlugin;
import org.apache.uima.tm.textruler.core.TextRulerToolkit;
import org.apache.uima.tm.textruler.extension.TextRulerController;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerController;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.apache.uima.util.XMLizable;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
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
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;


public class TextRulerViewComposite extends org.eclipse.swt.widgets.Composite {

  public class DefaultLabelProvider extends LabelProvider implements ITableLabelProvider {

    private final String type;

    public DefaultLabelProvider(String type) {
      super();
      this.type = type;
    }

    public String getColumnText(Object obj, int index) {
      return getText(obj);
    }

    public Image getColumnImage(Object obj, int index) {
      return getImage(obj);
    }

    @Override
    public Image getImage(Object obj) {
      if (type.equals("SlotTypes")) {
        return images.get("information");
      } else if (type.equals("FilterTypes")) {
        return images.get("feature");
      } else if (type.equals("learner")) {
        return images.get("learner");
      }
      return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
    }
  }

  private CheckboxTableViewer algListViewer;

  private AddRemoveList slotTypes;

  private AddRemoveList filterTypes;

  private Button skipPreButton;

  private Button dirButton;

  // private Composite configArea;

  private ArrayList<LearnerConfigurator> configurators;

  private Label globalStatusLabel;

  private Label label2;

  private Label label1;

  private Text inputDirectoryText;

  private Label methodsLabel;

  private Label filtersLabel;

  private TextRulerView textRulerView;

  private Button fileChooseButton;

  private static Text preFileText;

  private Label label3;

  private HashMap<String, Image> images;

  private Text additionalDirectoryText;

  private Button additionalButton;

  private Label label4;

  private Label label5;

  private Text testDirectoryText;

  private Button testButton;

  private Button showTestsButton;

  public class ListContentProvider implements IStructuredContentProvider {
    public void inputChanged(Viewer v, Object oldInput, Object newInput) {
    }

    public void dispose() {
    }

    public Object[] getElements(Object parent) {
      return TextRulerController.getAvailableControllers().toArray();
    }
  }

  public class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
    public String getColumnText(Object obj, int index) {
      return getText(obj);
    }

    public Image getColumnImage(Object obj, int index) {
      return getImage(obj);
    }

    @Override
    public Image getImage(Object obj) {
      return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
    }
  }

  public class NameSorter extends ViewerSorter {
  }

  public TextRulerViewComposite(org.eclipse.swt.widgets.Composite parent, int style,
          TextRulerView textRulerView) {
    super(parent, style);
    this.textRulerView = textRulerView;

    initImages();
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

      configurators = new ArrayList<LearnerConfigurator>();

      label1 = new Label(this, SWT.NONE);
      FormData label1LData = new FormData();
      label1LData.left = new FormAttachment(0, 1000, 12);
      label1LData.top = new FormAttachment(0, 1000, 20);
      label1LData.width = 109;
      label1.setLayoutData(label1LData);
      label1.setText("Training Data:");

      inputDirectoryText = new Text(this, SWT.SINGLE | SWT.BORDER);
      FormData inputDirectoryTexLData = new FormData();
      inputDirectoryTexLData.width = 300;
      inputDirectoryTexLData.left = new FormAttachment(0, 1000, 126);
      inputDirectoryTexLData.top = new FormAttachment(0, 1000, 20);
      inputDirectoryTexLData.right = new FormAttachment(1000, 1000, -110);
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
      dirButtoLData.top = new FormAttachment(0, 1000, 18);
      dirButtoLData.right = new FormAttachment(1000, 1000, -80);
      dirButton.setLayoutData(dirButtoLData);
      Image folderIcon = getImage("folder");
      dirButton.setImage(folderIcon);
      dirButton.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent event) {
          DirectoryDialog dlg = new DirectoryDialog(getShell());
          dlg.setFilterPath(inputDirectoryText.getText());
          dlg.setText("Input Directory");
          dlg.setMessage("Select a directory with input XMI files for the learning algorithms.");
          String dir = dlg.open();
          if (dir != null) {
            inputDirectoryText.setText(dir);
          }
        }
      });

      label4 = new Label(this, SWT.NONE);
      FormData label4LData = new FormData();
      label4LData.left = new FormAttachment(0, 1000, 12);
      label4LData.top = new FormAttachment(0, 1000, 44);
      label4LData.width = 109;
      label4.setLayoutData(label4LData);
      label4.setText("Additional Data:");

      additionalDirectoryText = new Text(this, SWT.SINGLE | SWT.BORDER);
      FormData additionalDirectoryTexLData = new FormData();
      additionalDirectoryTexLData.width = 300;
      additionalDirectoryTexLData.left = new FormAttachment(0, 1000, 126);
      additionalDirectoryTexLData.top = new FormAttachment(0, 1000, 44);
      additionalDirectoryTexLData.right = new FormAttachment(1000, 1000, -110);
      additionalDirectoryText.setLayoutData(additionalDirectoryTexLData);
      additionalDirectoryText.setText("");
      additionalDirectoryText.addModifyListener(new ModifyListener() {
        public void modifyText(ModifyEvent e) {
          // without that listener, the text fields forget the
          // last change when leaving with tab! don't know why!
          // we also MUST call getText() otherwise the changes in
          // the field are lost (what is this???!!)
          Text t = (Text) e.widget;
          t.getText();
        }
      });

      additionalButton = new Button(this, SWT.PUSH | SWT.CENTER);
      FormData additionalButtoLData = new FormData();
      additionalButtoLData.width = 25;
      additionalButtoLData.height = 25;
      additionalButtoLData.top = new FormAttachment(0, 1000, 42);
      additionalButtoLData.right = new FormAttachment(1000, 1000, -80);
      additionalButton.setLayoutData(additionalButtoLData);
      folderIcon = getImage("folder");
      additionalButton.setImage(folderIcon);
      additionalButton.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent event) {
          DirectoryDialog dlg = new DirectoryDialog(getShell());
          dlg.setFilterPath(inputDirectoryText.getText());
          dlg.setText("Additional Directory");
          dlg
                  .setMessage("Select a directory with input additional XMI files for the learning algorithms.");
          String dir = dlg.open();
          if (dir != null) {
            additionalDirectoryText.setText(dir);
          }
        }
      });

      label5 = new Label(this, SWT.NONE);
      FormData label5LData = new FormData();
      label5LData.left = new FormAttachment(0, 1000, 12);
      label5LData.top = new FormAttachment(0, 1000, 44 + 24);
      label5LData.width = 109;
      label5.setLayoutData(label5LData);
      label5.setText("Test Data:");

      testDirectoryText = new Text(this, SWT.SINGLE | SWT.BORDER);
      FormData testDirectoryTexLData = new FormData();
      testDirectoryTexLData.width = 300;
      testDirectoryTexLData.left = new FormAttachment(0, 1000, 126);
      testDirectoryTexLData.top = new FormAttachment(0, 1000, 44 + 24);
      testDirectoryTexLData.right = new FormAttachment(1000, 1000, -110);
      testDirectoryText.setLayoutData(testDirectoryTexLData);
      testDirectoryText.setText("");
      testDirectoryText.addModifyListener(new ModifyListener() {
        public void modifyText(ModifyEvent e) {
          // without that listener, the text fields forget the
          // last change when leaving with tab! don't know why!
          // we also MUST call getText() otherwise the changes in
          // the field are lost (what is this???!!)
          Text t = (Text) e.widget;
          t.getText();
        }
      });

      testButton = new Button(this, SWT.PUSH | SWT.CENTER);
      FormData testButtoLData = new FormData();
      testButtoLData.width = 25;
      testButtoLData.height = 25;
      testButtoLData.top = new FormAttachment(0, 1000, 42 + 24);
      testButtoLData.right = new FormAttachment(1000, 1000, -80);
      testButton.setLayoutData(testButtoLData);
      folderIcon = getImage("folder");
      testButton.setImage(folderIcon);
      testButton.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent event) {
          DirectoryDialog dlg = new DirectoryDialog(getShell());
          dlg.setFilterPath(inputDirectoryText.getText());
          dlg.setText("Test Directory");
          dlg.setMessage("Select a directory with input test XMI files.");
          String dir = dlg.open();
          if (dir != null) {
            testDirectoryText.setText(dir);
          }
        }
      });

      showTestsButton = new Button(this, SWT.CHECK | SWT.LEFT);
      FormData showTestsButtoLData = new FormData();
      showTestsButtoLData.width = 50;
      showTestsButtoLData.height = 18;
      showTestsButtoLData.top = new FormAttachment(0, 1000, 42 + 24);
      showTestsButtoLData.right = new FormAttachment(1000, 1000, -25);
      showTestsButton.setLayoutData(showTestsButtoLData);
      showTestsButton.setText("eval");

      label3 = new Label(this, SWT.NONE);
      FormData label3LData = new FormData();
      label3LData.width = 103;
      label3LData.left = new FormAttachment(0, 1000, 12);
      label3LData.top = new FormAttachment(0, 1000, 68 + 24);
      label3.setLayoutData(label3LData);
      label3.setText("Preprocess Script:");

      preFileText = new Text(this, SWT.SINGLE | SWT.BORDER);
      FormData preFileTexLData = new FormData();
      preFileTexLData.width = 495;
      preFileTexLData.left = new FormAttachment(0, 1000, 126);
      preFileTexLData.top = new FormAttachment(0, 1000, 68 + 24);
      preFileTexLData.right = new FormAttachment(1000, 1000, -110);
      preFileText.setLayoutData(preFileTexLData);
      preFileText.setText("");
      preFileText.addModifyListener(new ModifyListener() {
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
      fileChooseButtoLData.top = new FormAttachment(0, 1000, 66 + 24);
      fileChooseButtoLData.right = new FormAttachment(1000, 1000, -80);
      fileChooseButton.setLayoutData(fileChooseButtoLData);
      Image icon = getImage("prepFolder");
      fileChooseButton.setImage(icon);
      fileChooseButton.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent event) {
          FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
          fd.setText("Choose TextMarker Source File");
          String[] filterExt = { "*.tm", "*.*" };
          fd.setFilterExtensions(filterExt);
          String file = fd.open();
          if (file != null) {
            preFileText.setText(file);
          }
        }
      });

      skipPreButton = new Button(this, SWT.CHECK | SWT.LEFT);
      FormData skipPreButtonLData = new FormData();
      skipPreButtonLData.width = 50;
      skipPreButtonLData.height = 18;
      skipPreButtonLData.top = new FormAttachment(0, 1000, 68 + 24);
      skipPreButtonLData.right = new FormAttachment(1000, 1000, -25);
      skipPreButton.setLayoutData(skipPreButtonLData);
      skipPreButton.setText("skip");

      label2 = new Label(this, SWT.NONE);
      FormData label2LData = new FormData();
      label2LData.left = new FormAttachment(0, 1000, 12);
      label2LData.top = new FormAttachment(0, 1000, 44 + 24 + 24 + 24);
      label2LData.width = 109;
      label2.setLayoutData(label2LData);
      label2.setText("Information Types:");

      slotTypes = new AddRemoveList("SlotTypes", images, this, true);
      FormData slotTypesLData = new FormData();
      slotTypesLData.width = 300;
      slotTypesLData.height = 144;
      slotTypesLData.top = new FormAttachment(0, 1000, 85 + 24 + 24);
      slotTypesLData.left = new FormAttachment(0, 1000, 12);
      slotTypes.setLayoutData(slotTypesLData);

      addDropTarget(slotTypes);

      filtersLabel = new Label(this, SWT.NONE);
      FormData filtersLabeLData = new FormData();
      filtersLabeLData.width = 120;
      filtersLabeLData.left = new FormAttachment(0, 1000, 320);
      filtersLabeLData.top = new FormAttachment(0, 1000, 44 + 24 + 24 + 24);
      filtersLabel.setLayoutData(filtersLabeLData);
      filtersLabel.setText("Filtered Feature Types:");

      filterTypes = new AddRemoveList("FilterTypes", images, this, false);
      FormData filterTypesLData = new FormData();
      filterTypesLData.width = 300;
      filterTypesLData.height = 144;
      filterTypesLData.top = new FormAttachment(0, 1000, 85 + 24 + 24);
      filterTypesLData.left = new FormAttachment(0, 1000, 320);
      filterTypes.setLayoutData(filterTypesLData);

      addDropTarget(filterTypes);

      FormData globalStatusLabeLData = new FormData();
      globalStatusLabeLData.width = 400;
      globalStatusLabeLData.left = new FormAttachment(0, 1000, 70);
      globalStatusLabeLData.top = new FormAttachment(0, 1000, 228 + 24 + 24);
      globalStatusLabeLData.right = new FormAttachment(1000, 1000, -19);
      globalStatusLabel = new Label(this, SWT.NONE);
      globalStatusLabel.setLayoutData(globalStatusLabeLData);

      methodsLabel = new Label(this, SWT.NONE);
      FormData algsLabeLData = new FormData();
      algsLabeLData.width = 120;
      algsLabeLData.left = new FormAttachment(0, 1000, 12);
      algsLabeLData.top = new FormAttachment(0, 1000, 228 + 24 + 24);
      methodsLabel.setLayoutData(algsLabeLData);
      methodsLabel.setText("Methods:");

      FormData algListViewerLData = new FormData();
      // algListViewerLData.width = 400;
      // algListViewerLData.height = 115;

      algListViewerLData.left = new FormAttachment(0, 1000, 10);
      algListViewerLData.top = new FormAttachment(0, 1000, 248 + 24 + 24);
      algListViewerLData.right = new FormAttachment(1000, 1000, -10);
      algListViewerLData.bottom = new FormAttachment(1000, 1000, -10);

      algListViewer = CheckboxTableViewer.newCheckList(this, SWT.BORDER | SWT.H_SCROLL
              | SWT.V_SCROLL);
      algListViewer.getControl().setLayoutData(algListViewerLData);
      algListViewer.setContentProvider(new ListContentProvider());
      algListViewer.setLabelProvider(new DefaultLabelProvider("learner"));
      algListViewer.setSorter(new NameSorter());
      if (textRulerView != null)
        algListViewer.setInput(textRulerView.getViewSite());

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

    DropTarget dt3 = new DropTarget(additionalDirectoryText, DND.DROP_DEFAULT | DND.DROP_MOVE);
    dt3.setTransfer(new Transfer[] { FileTransfer.getInstance() });
    dt3.addDropListener(new DropTargetAdapter() {
      @Override
      public void drop(DropTargetEvent event) {
        String fileList[] = null;
        FileTransfer ft = FileTransfer.getInstance();
        if (ft.isSupportedType(event.currentDataType)) {
          fileList = (String[]) event.data;
        }
        if (fileList.length > 0)
          additionalDirectoryText.setText(fileList[0]);
      }
    });

    DropTarget dt4 = new DropTarget(testDirectoryText, DND.DROP_DEFAULT | DND.DROP_MOVE);
    dt4.setTransfer(new Transfer[] { FileTransfer.getInstance() });
    dt4.addDropListener(new DropTargetAdapter() {
      @Override
      public void drop(DropTargetEvent event) {
        String fileList[] = null;
        FileTransfer ft = FileTransfer.getInstance();
        if (ft.isSupportedType(event.currentDataType)) {
          fileList = (String[]) event.data;
        }
        if (fileList.length > 0)
          testDirectoryText.setText(fileList[0]);
      }
    });

    DropTarget dt2 = new DropTarget(preFileText, DND.DROP_DEFAULT | DND.DROP_MOVE);
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
          preFileText.setText(fileList[0]);
      }
    });

    createAlgorithmConfigurators();
    // algListViewer.addSelectionChangedListener(new ISelectionChangedListener() {
    // public void selectionChanged(SelectionChangedEvent event) {
    // StructuredSelection s = (StructuredSelection) event.getSelection();
    // String id = null;
    // if (!s.isEmpty())
    // id = ((TextRulerLearnerController) s.getFirstElement()).getID();
    // showConfiguratorForAlgorithm(id);
    // }
    // });
  }

  private void addDropTarget(final AddRemoveList list) {
    DropTarget fdt = new DropTarget(list, DND.DROP_DEFAULT | DND.DROP_MOVE);
    fdt.setTransfer(new Transfer[] { FileTransfer.getInstance() });
    fdt.addDropListener(new DropTargetAdapter() {
      @Override
      public void drop(DropTargetEvent event) {
        String fileList[] = null;
        FileTransfer ft = FileTransfer.getInstance();
        if (ft.isSupportedType(event.currentDataType)) {
          fileList = (String[]) event.data;
        }
        if (fileList.length > 0) {
          for (String string : fileList) {
            if (string.endsWith(".xml")) {
              XMLizable descriptor = null;
              try {
                descriptor = UIMAFramework.getXMLParser().parse(new XMLInputSource(string));
              } catch (InvalidXMLException e) {
              } catch (IOException e) {
              }
              if (descriptor instanceof TypeSystemDescription) {
                TypeSystemDescription tsd = (TypeSystemDescription) descriptor;
                try {
                  tsd.resolveImports();
                } catch (InvalidXMLException e) {
                }
                List<String> tsTypes = new ArrayList<String>();
                TypeDescription[] typeDescs = tsd.getTypes();
                for (TypeDescription typeDescription : typeDescs) {
                  String n = typeDescription.getName();
                  tsTypes.add(n);
                }
                list.addAll(tsTypes);
              }
            } else if (string.endsWith(".txt")) {
              String file2String = null;
              try {
                file2String = FileUtils.file2String(new File(string), "UTF-8");
              } catch (IOException e) {
              }
              if (file2String != null) {
                String replaceAll = file2String.replaceAll("[\\n\\r]+", ", ");
                list.addAll(replaceAll);
              }
            }
          }
        }
      }
    });
  }

  private void initImages() {
    images = new HashMap<String, Image>();
    ImageDescriptor desc;
    Image image;
    String name;

    desc = TextRulerPlugin.getImageDescriptor("/icons/add.png");
    image = desc.createImage();
    name = "add";
    images.put(name, image);

    desc = TextRulerPlugin.getImageDescriptor("/icons/delete.png");
    image = desc.createImage();
    name = "delete";
    images.put(name, image);

    desc = TextRulerPlugin.getImageDescriptor("/icons/folder_edit.png");
    image = desc.createImage();
    name = "prepFolder";
    images.put(name, image);

    desc = TextRulerPlugin.getImageDescriptor("/icons/folder.png");
    image = desc.createImage();
    name = "folder";
    images.put(name, image);

    desc = TextRulerPlugin.getImageDescriptor("/icons/up.gif");
    image = desc.createImage();
    name = "up";
    images.put(name, image);

    desc = TextRulerPlugin.getImageDescriptor("/icons/down.gif");
    image = desc.createImage();
    name = "down";
    images.put(name, image);

    desc = TextRulerPlugin.getImageDescriptor("/icons/information.gif");
    image = desc.createImage();
    name = "information";
    images.put(name, image);

    desc = TextRulerPlugin.getImageDescriptor("/icons/feature.gif");
    image = desc.createImage();
    name = "feature";
    images.put(name, image);

    desc = TextRulerPlugin.getImageDescriptor("/icons/learner.png");
    image = desc.createImage();
    name = "learner";
    images.put(name, image);

    desc = TextRulerPlugin.getImageDescriptor("/icons/testing.gif");
    image = desc.createImage();
    name = "testing";
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

  public String[] getFilters() {
    return filterTypes.getAll();
  }

  public String[] getSlotNames() {
    return slotTypes.getAll();
  }

  public String getPreprocessorTMFile() {
    return preFileText.getText().trim();
  }

  public String getInputDirectoryPath() {
    return inputDirectoryText.getText().trim();
  }

  public String getAdditionalDirectoryPath() {
    return additionalDirectoryText.getText().trim();
  }

  public String getTestDirectoryPath() {
    return testDirectoryText.getText().trim();
  }

  public CheckboxTableViewer getAlgListViewer() {
    return algListViewer;
  }

  public boolean getSkipPreprocessing() {
    return skipPreButton.getSelection();
  }

  public void setGlobalStatusString(String s) {
    globalStatusLabel.setText(s);
  }

  public void createAlgorithmConfigurators() {
    if (null == TextRulerController.getAvailableControllers())
      return;
    for (TextRulerLearnerController ctrl : TextRulerController.getAvailableControllers()) {
      LearnerConfigurator ac = new LearnerConfigurator(ctrl);
      configurators.add(ac);
      // GridData d = new GridData();
      // d.horizontalAlignment = GridData.FILL;
      // d.grabExcessHorizontalSpace = true;
      // d.verticalAlignment = GridData.FILL;
      // d.grabExcessVerticalSpace = true;
      // d.exclude = true;
      // ac.setLayoutData(d);
      // ac.setVisible(false);
      // ac.setCurrentParameterSettings(ctrl.getFactory().getAlgorithmParameterStandardValues());
    }
  }

  // public void showConfiguratorForAlgorithm(String id) {
  // for (Control aControl : configArea.getChildren()) {
  // boolean show = id != null && ((LearnerConfigurator) aControl).getID().equals(id);
  // GridData d = (GridData) aControl.getLayoutData();
  // d.exclude = !show;
  // aControl.setVisible(show);
  // }
  // configArea.layout();
  // }

  public Map<String, Map<String, Object>> getCurrentAlgorithmParameters() {
    Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();
    for (LearnerConfigurator c : configurators) {
      result.put(c.getID(), c.getCurrentParameterSettings());
    }
    return result;
  }

  public void saveState(IMemento memento) {
    IMemento activeAlgChild = memento.createChild("activeAlgorithms");
    for (Object c : algListViewer.getCheckedElements()) {
      activeAlgChild.createChild("algorithm", ((TextRulerLearnerController) c).getID());
    }

    memento.createChild("inputDirectory", inputDirectoryText.getText());
    memento.createChild("additionalDirectory", additionalDirectoryText.getText());
    memento.createChild("testDirectory", testDirectoryText.getText());
    memento.createChild("slotName", slotTypes.getAllElementsAsString());
    memento.createChild("filters", filterTypes.getAllElementsAsString());
    memento.createChild("preprocessFile", preFileText.getText());
    memento.createChild("skipPreprocessing", skipPreButton.getSelection() ? "yes" : "no");
    memento.createChild("showTest", showTestsButton.getSelection() ? "yes" : "no");
  }

  public LearnerConfigurator getAlgConfiguratorForID(String id) {
    for (LearnerConfigurator c : configurators) {
      if (c.getID().equals(id))
        return c;
    }
    return null;
  }

  public void restoreState(IMemento memento) {
    if (memento == null)
      return;

    IMemento filters = memento.getChild("filters");
    if (filters != null)
      filterTypes.addAll(filters.getID());
    else
      filterTypes.addAll(TextRulerToolkit.getStandardFilterSetString());

    IMemento slotName = memento.getChild("slotName");
    if (slotName != null)
      slotTypes.addAll(slotName.getID());

    IMemento preName = memento.getChild("preprocessFile");
    if (preName != null)
      preFileText.setText(preName.getID());

    IMemento dir = memento.getChild("inputDirectory");
    if (dir != null)
      inputDirectoryText.setText(dir.getID());

    IMemento additional = memento.getChild("additionalDirectory");
    if (additional != null)
      additionalDirectoryText.setText(additional.getID());

    IMemento test = memento.getChild("testDirectory");
    if (test != null)
      testDirectoryText.setText(test.getID());

    IMemento activeAlgs = memento.getChild("activeAlgorithms");
    if (activeAlgs != null) {
      ArrayList<TextRulerLearnerController> activeControllers = new ArrayList<TextRulerLearnerController>();
      for (IMemento c : activeAlgs.getChildren("algorithm")) {
        TextRulerLearnerController ctrl = TextRulerController.getControllerForID(c.getID());
        if (ctrl != null)
          activeControllers.add(ctrl);
      }
      algListViewer.setCheckedElements(activeControllers.toArray());
    }

    IMemento skip = memento.getChild("skipPreprocessing");
    if (skip != null) {
      boolean skipPreprocessing = skip.getID().equals("yes");
      skipPreButton.setSelection(skipPreprocessing);
    }

    IMemento showTest = memento.getChild("showTest");
    if (skip != null) {
      boolean showTestValue = showTest.getID().equals("yes");
      showTestsButton.setSelection(showTestValue);
    }
  }

  @Override
  public void setEnabled(boolean flag) {
    super.setEnabled(flag);
    // algListViewer.getTable().setEnabled(flag); // it is not possible to just
    // prevent from pressing on
    // the checkboxes unfortunately. so we have to disable the whole control
    // which also disables scrolling
    // (that is really lame!)
    dirButton.setEnabled(flag);
    fileChooseButton.setEnabled(flag);
    slotTypes.setEnabled(flag);
    filterTypes.setEnabled(flag);
    preFileText.setEnabled(flag);
    inputDirectoryText.setEnabled(flag);
    additionalDirectoryText.setEnabled(flag);
    additionalButton.setEnabled(flag);
    testDirectoryText.setEnabled(flag);
    testButton.setEnabled(flag);
  }

  public static String getScriptPath() {
    return preFileText.getText();
  }

}
