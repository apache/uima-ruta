package org.apache.uima.tm.textruler.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.UIMAFramework;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.tm.textruler.core.TextRulerToolkit;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.apache.uima.util.XMLizable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;


public class AddRemoveList extends Composite {

  private boolean error = false;

  private String name;

  private CheckboxTableViewer listViewer;

  private Button add;

  private Button remove;

  private Button up;

  private Button down;

  private Button ts;

  private TextRulerViewComposite parent;

  protected final HashMap<String, Image> images;

  private ArrayList<String> types;

  private boolean ordered;

  public AddRemoveList(String name, HashMap<String, Image> images, TextRulerViewComposite parent,
          boolean ordered) {
    super(parent, SWT.NULL);
    this.name = name;
    this.images = images;
    this.parent = parent;
    this.ordered = ordered;
    init();
  }

  private void setTypes() {
    error = false;
    types.clear();
    String preFilePath = TextRulerViewComposite.getScriptPath();
    File preFile = new File(preFilePath);
    if (preFile.exists() == false || preFilePath.equals("")) {
      printErrorDialog("The preprocessing file was not found!");
      error = true;
      return;
    }
    Path scriptPath = new Path(preFilePath);
    String defaultTypeSystemDescriptorLocation = TextRulerToolkit
            .getTypeSystemDescriptorFromTMSourceFile(scriptPath);
    TypeSystemDescription defaultTypeSystemDescription = null;
    try {
      defaultTypeSystemDescription = UIMAFramework.getXMLParser().parseTypeSystemDescription(
              new XMLInputSource(new File(defaultTypeSystemDescriptorLocation)));
      defaultTypeSystemDescription.resolveImports();
      TypeDescription[] systemTypes = defaultTypeSystemDescription.getTypes();
      for (TypeDescription typeDescription : systemTypes) {
        types.add(typeDescription.getName());
      }
      Collections.sort(types);
    } catch (InvalidXMLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void printErrorDialog(String error) {
    ErrorDialog.openError(Display.getCurrent().getActiveShell(), "File not Found!", error,
            new Status(IStatus.ERROR, "-1", "File not found!"));
  }

  private void init() {

    this.setLayout(new FormLayout());
    this.setSize(200, 300);

    types = new ArrayList<String>();
    listViewer = CheckboxTableViewer.newCheckList(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL
            | SWT.MULTI);
    FormData listViewerLData = new FormData();
    listViewerLData.width = 160;
    listViewerLData.height = 150;
    listViewerLData.left = new FormAttachment(0, 1000, 0);
    listViewerLData.right = new FormAttachment(1000, 1000, -40);
    listViewerLData.top = new FormAttachment(0, 1000, 0);
    listViewerLData.bottom = new FormAttachment(1000, 1000, -10);
    listViewer.getControl().setLayoutData(listViewerLData);
    listViewer.setContentProvider(parent.new ListContentProvider());
    listViewer.setLabelProvider(parent.new DefaultLabelProvider(name));
    // listViewer.setLabelProvider(parent.new ViewLabelProvider());
    // listViewer.setSorter(parent.new NameSorter());
    listViewer.getTable().setVisible(true);

    if (name.equals("FilterTypes")) {
      String input = TextRulerToolkit.getStandardFilterSetString();
      addAll(input);
    } else {
      listViewer.setAllChecked(true);
    }

    listViewer.getControl().addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        int keyCode = e.keyCode;
        // backspace or delete
        if (keyCode == SWT.BS || keyCode == SWT.DEL) {
          String selected = listViewer.getSelection().toString();
          selected = selected.substring(1, selected.length() - 1);
          String[] types = selected.split(", ");
          for (String string : types) {
            listViewer.remove(string);
          }
        }
      }

    });

    ts = new Button(this, SWT.PUSH | SWT.CENTER);
    FormData tsLData = new FormData();
    tsLData.width = 25;
    tsLData.height = 25;
    tsLData.top = new FormAttachment(0, 1000, 0);
    tsLData.right = new FormAttachment(1000, 1000, -10);
    ts.setLayoutData(tsLData);
    Image tsIcon = images.get("prepFolder");
    ts.setImage(tsIcon);
    ts.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
        fd.setText("Choose Type System Descriptor");
        String[] filterExt = { "*.xml", "*.*" };
        fd.setFilterExtensions(filterExt);
        fd.setFileName(parent.getPreprocessorTMFile());
        String file = fd.open();
        if (file != null) {
          XMLizable descriptor = null;
          try {
            descriptor = UIMAFramework.getXMLParser().parse(new XMLInputSource(file));
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
            addAll(tsTypes);
          }
        }
      }
    });

    add = new Button(this, SWT.PUSH | SWT.CENTER);
    FormData addLData = new FormData();
    addLData.width = 25;
    addLData.height = 25;
    addLData.top = new FormAttachment(0, 1000, 27);
    addLData.right = new FormAttachment(1000, 1000, -10);
    add.setLayoutData(addLData);
    Image addIcon = images.get("add");
    add.setImage(addIcon);
    add.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        setTypes();
        if (error)
          return;
        Display display = Display.getDefault();
        Shell shell = new Shell(display, SWT.RESIZE | SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
        if (name.equals("FilterTypes"))
          shell.setText("Filter Types");
        else
          shell.setText("Slot Types");
        new SelectTypesDialog(shell, types, AddRemoveList.this);
      }
    });

    remove = new Button(this, SWT.PUSH | SWT.CENTER);
    FormData removeLData = new FormData();
    removeLData.width = 25;
    removeLData.height = 25;
    removeLData.top = new FormAttachment(0, 1000, 54);
    removeLData.right = new FormAttachment(1000, 1000, -10);
    remove.setLayoutData(removeLData);
    Image deleteIcon = images.get("delete");
    remove.setImage(deleteIcon);
    remove.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        String selected = listViewer.getSelection().toString();
        selected = selected.substring(1, selected.length() - 1);
        String[] types = selected.split(", ");
        for (String string : types) {
          listViewer.remove(string);
        }
      }
    });

    if (ordered) {
      up = new Button(this, SWT.PUSH | SWT.CENTER);
      FormData upLData = new FormData();
      upLData.width = 25;
      upLData.height = 25;
      upLData.top = new FormAttachment(0, 1000, 81);
      upLData.right = new FormAttachment(1000, 1000, -10);
      up.setLayoutData(upLData);
      Image upIcon = images.get("up");
      up.setImage(upIcon);
      up.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent event) {
          ISelection selection = listViewer.getSelection();
          List<String> dataList = new ArrayList<String>();
          if (selection instanceof StructuredSelection) {
            StructuredSelection ss = (StructuredSelection) selection;
            Iterator<?> iterator = ss.iterator();
            while (iterator.hasNext()) {
              Object object = (Object) iterator.next();
              if (object instanceof String) {
                String obj = (String) object;
                dataList = new ArrayList<String>();
                TableItem[] items = listViewer.getTable().getItems();
                for (TableItem tableItem : items) {
                  dataList.add((String) tableItem.getData());
                }
                int indexOf = dataList.indexOf(obj);
                Collections.swap(dataList, indexOf, indexOf - 1);
              }
            }
          }
          listViewer.remove(dataList.toArray());
          addAll(dataList);
        }
      });

      down = new Button(this, SWT.PUSH | SWT.CENTER);
      FormData downLData = new FormData();
      downLData.width = 25;
      downLData.height = 25;
      downLData.top = new FormAttachment(0, 1000, 108);
      downLData.right = new FormAttachment(1000, 1000, -10);
      down.setLayoutData(downLData);
      Image downIcon = images.get("down");
      down.setImage(downIcon);
      down.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent event) {
          ISelection selection = listViewer.getSelection();
          List<String> dataList = new ArrayList<String>();
          if (selection instanceof StructuredSelection) {
            StructuredSelection ss = (StructuredSelection) selection;
            Iterator<?> iterator = ss.iterator();
            while (iterator.hasNext()) {
              Object object = (Object) iterator.next();
              if (object instanceof String) {
                String obj = (String) object;
                dataList = new ArrayList<String>();
                TableItem[] items = listViewer.getTable().getItems();
                for (TableItem tableItem : items) {
                  dataList.add((String) tableItem.getData());
                }
                int indexOf = dataList.indexOf(obj);
                Collections.swap(dataList, indexOf, indexOf + 1);
              }
            }
          }
          listViewer.remove(dataList.toArray());
          addAll(dataList);
        }
      });
    }
  }

  public String[] getAll() {
    ArrayList<String> elements = new ArrayList<String>();

    Object[] objectElements = listViewer.getCheckedElements();
    for (Object object : objectElements) {
      elements.add(object.toString());
    }
    return elements.toArray(new String[objectElements.length]);
  }

  public String getAllElementsAsString() {
    String result = "";
    listViewer.setAllChecked(true);
    for (int i = 0; i < listViewer.getCheckedElements().length; i++) {
      if (i == listViewer.getCheckedElements().length - 1) {
        result += listViewer.getCheckedElements()[i];
      } else {
        result += listViewer.getCheckedElements()[i] + ", ";
      }
    }
    return result;
  }

  public void addAll(String input) {
    if (input == null)
      return;
    int index = 0;
    while (index != -1) {
      index = input.indexOf(",");
      String filter;
      if (index == -1)
        filter = input;
      else {
        filter = input.substring(0, index);
        input = input.substring(index + 2);
      }
      boolean isAlreadyAdded = false;
      int i = 0;
      while (listViewer.getElementAt(i) != null) {
        if (filter.equals(listViewer.getElementAt(i))) {
          isAlreadyAdded = true;
          break;
        }
        i++;
      }
      if (!isAlreadyAdded && !filter.trim().equals(""))
        listViewer.add(filter);
    }
    listViewer.setAllChecked(true);
  }

  public void addAll(List<String> input) {
    addAll(input, true);
  }

  public void addAll(List<String> input, boolean checkAll) {
    for (String string : input) {
      boolean isAlreadyAdded = false;
      int i = 0;
      while (listViewer.getElementAt(i) != null) {
        if (string.equals(listViewer.getElementAt(i))) {
          isAlreadyAdded = true;
          break;
        }
        i++;
      }
      if (!isAlreadyAdded)
        listViewer.add(string);
    }
    if (checkAll)
      listViewer.setAllChecked(true);
  }
}
