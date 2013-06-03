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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.ruta.cde.IRutaConstraint;
import org.apache.uima.ruta.cde.RutaGEConstraint;
import org.apache.uima.ruta.cde.RutaRuleListConstraint;
import org.apache.uima.ruta.cde.SimpleRutaRuleConstraint;
import org.apache.uima.ruta.cde.utils.CDEComparatorFactory;
import org.apache.uima.ruta.cde.utils.ConstraintData;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IMemento;

public class ConstraintSelectComposite extends Composite implements ISelectionChangedListener {

  private Button constraintButtonRuta;

  private Button constraintButtonRutaList;

  private Button constraintButconJava;

  private Button constraintRemoveButton;

  private Button constraintButtonTMComplex;

  private Button exportConstraintsButton;

  private ConstraintSelectTableLabelProvider labelProvider;

  private ConstraintSelectTableContentProvider contentProvider;

  private HashMap<String, Image> images;

  private Table table;

  private TableColumn tc0;

  private TableColumn tc1;

  private TableColumn tc2;

  private TableViewer tableViewer;

  private TableEditor editor;

  private ArrayList<ConstraintData> constraintList;

  private int EDITABLECOLUMN = 1;

  private CDEComparatorFactory comparatorFactory;

  public ConstraintSelectComposite(Composite parent, int style) {
    super(parent, style);
    initGui();
    comparatorFactory = new CDEComparatorFactory();
  }

  public void initGui() {
    constraintList = new ArrayList<ConstraintData>();
    this.setLayout(new FormLayout());

    // Simple Constraint Button
    constraintButtonRuta = new Button(this, SWT.PUSH | SWT.CENTER);
    FormData ConstraintButtonTMData = new FormData();
    ConstraintButtonTMData.width = 25;
    ConstraintButtonTMData.height = 25;
    ConstraintButtonTMData.top = new FormAttachment(0, 10);
    ConstraintButtonTMData.right = new FormAttachment(100, -5);
    constraintButtonRuta.setLayoutData(ConstraintButtonTMData);
    Image folderIcon = getImage("add");
    constraintButtonRuta.setImage(folderIcon);
    constraintButtonRuta.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {

        ConstraintDialog cdialog = new ConstraintDialog(getShell());
        cdialog.create();
        if (cdialog.open() == Window.OK) {
          SimpleRutaRuleConstraint newConstraint = new SimpleRutaRuleConstraint(cdialog
                  .getRule(), cdialog.getDescription());
          ConstraintData newConstraintData = new ConstraintData(newConstraint);
          constraintList.add(newConstraintData);
          tableViewer.setInput(constraintList);
          tableViewer.refresh();
        }
      }
    });

    // ListConstraint Button
    constraintButtonRutaList = new Button(this, SWT.PUSH | SWT.CENTER);
    FormData ConstraintButtonTMListData = new FormData();
    ConstraintButtonTMListData.width = 25;
    ConstraintButtonTMListData.height = 25;
    ConstraintButtonTMListData.top = new FormAttachment(constraintButtonRuta, 10);
    ConstraintButtonTMListData.right = new FormAttachment(100, -5);
    constraintButtonRutaList.setLayoutData(ConstraintButtonTMListData);
    Image addListIcon = getImage("scriptAdd");
    constraintButtonRutaList.setImage(addListIcon);
    constraintButtonRutaList.setToolTipText("Add a list of SimpleRutaConstraints");
    constraintButtonRutaList.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {

        ConstraintDialog cdialog = new ConstraintDialog(getShell());
        cdialog.create();
        if (cdialog.open() == Window.OK) {
          RutaRuleListConstraint newConstraint = new RutaRuleListConstraint(cdialog.getRule(),
                  cdialog.getDescription());
          ConstraintData newConstraintData = new ConstraintData(newConstraint);
          constraintList.add(newConstraintData);
          tableViewer.setInput(constraintList);
          tableViewer.refresh();
        }
      }
    });

    // Complex Constraint Button now used for GEConstraints

    constraintButtonTMComplex = new Button(this, SWT.PUSH | SWT.CENTER);
    FormData ConstraintButtonTMComplexData = new FormData();
    ConstraintButtonTMComplexData.width = 25;
    ConstraintButtonTMComplexData.height = 25;
    ConstraintButtonTMComplexData.top = new FormAttachment(constraintButtonRutaList, 10);
    ConstraintButtonTMComplexData.right = new FormAttachment(100, -5);
    constraintButtonTMComplex.setLayoutData(ConstraintButtonTMComplexData);
    Image addTableIcon = getImage("tmAdd");
    constraintButtonTMComplex.setImage(addTableIcon);
    constraintButtonTMComplex.setToolTipText("Add an Annotation-Distribution Constraint");
    constraintButtonTMComplex.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        ConstraintDialog cdialog = new ConstraintDialog(getShell());
        cdialog.create();
        if (cdialog.open() == Window.OK) {
          RutaGEConstraint newConstraint = new RutaGEConstraint(cdialog.getRule(), cdialog.getDescription());
          ConstraintData newConstraintData = new ConstraintData(newConstraint);
          constraintList.add(newConstraintData);
          tableViewer.setInput(constraintList);
          tableViewer.refresh();
        }
      }
    });

    // Constraint Edit Button

    constraintButconJava = new Button(this, SWT.PUSH | SWT.CENTER);
    FormData ConstraintButtonJavaData = new FormData();
    ConstraintButtonJavaData.width = 25;
    ConstraintButtonJavaData.height = 25;
    ConstraintButtonJavaData.top = new FormAttachment(constraintButtonTMComplex, 10);
    ConstraintButtonJavaData.right = new FormAttachment(100, -5);
    constraintButconJava.setLayoutData(ConstraintButtonJavaData);
    Image addJavaIcon = getImage("cupAdd");
    constraintButconJava.setImage(addJavaIcon);
    constraintButconJava.setToolTipText("Add Java Constraint");

    constraintRemoveButton = new Button(this, SWT.PUSH | SWT.CENTER);
    FormData ConstraintRemoveData = new FormData();
    ConstraintRemoveData.width = 25;
    ConstraintRemoveData.height = 25;
    ConstraintRemoveData.top = new FormAttachment(constraintButconJava, 10);
    ConstraintRemoveData.right = new FormAttachment(100, -5);
    constraintRemoveButton.setLayoutData(ConstraintRemoveData);
    Image addRemoveIcon = getImage("delete");
    constraintRemoveButton.setImage(addRemoveIcon);
    constraintRemoveButton.setToolTipText("Remove Selected Constraints");
    constraintRemoveButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        int[] indices = table.getSelectionIndices();
        for (int i = indices.length - 1; i >= 0; i--) {
          constraintList.remove(indices[i]);
        }
        tableViewer.setSelection(new StructuredSelection());
        editor.getEditor().dispose();
        tableViewer.setInput(constraintList);
        tableViewer.refresh();
      }
    });

    tableViewer = new TableViewer(this, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL
            | SWT.FULL_SELECTION);
    contentProvider = new ConstraintSelectTableContentProvider();
    labelProvider = new ConstraintSelectTableLabelProvider();

    tableViewer.setContentProvider(contentProvider);
    tableViewer.setLabelProvider(labelProvider);
    table = tableViewer.getTable();
    FormData tableFormData = new FormData();
    tableFormData.top = new FormAttachment(0, 10);
    tableFormData.left = new FormAttachment(0, 10);
    tableFormData.right = new FormAttachment(constraintButtonRuta, -10);
    tableFormData.bottom = new FormAttachment(100, 0);
    table.setLayoutData(tableFormData);

    tableViewer.addDoubleClickListener(new IDoubleClickListener() {
      public void doubleClick(DoubleClickEvent event) {
        IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
        if (selection.isEmpty()) {
          return;
        }
        Object element = selection.getFirstElement();
        if (element instanceof ConstraintData) {
          ConstraintData data = (ConstraintData) element;
          IRutaConstraint constraint = data.getConstraint();
          if (constraint instanceof SimpleRutaRuleConstraint) {
            ConstraintDialog cdialog = new ConstraintDialog(getShell());
            cdialog.create((SimpleRutaRuleConstraint) constraint);
            if (cdialog.open() == Window.OK) {
              SimpleRutaRuleConstraint newConstraint = new SimpleRutaRuleConstraint(cdialog
                      .getRule(), cdialog.getDescription());
              data.setConstraint(newConstraint);
              tableViewer.setInput(constraintList);
              tableViewer.refresh();
            }
          }

          if (constraint instanceof RutaRuleListConstraint) {
            ConstraintDialog cdialog = new ConstraintDialog(getShell());
            cdialog.create((RutaRuleListConstraint) constraint);
            if (cdialog.open() == Window.OK) {
              RutaRuleListConstraint newConstraint = new RutaRuleListConstraint(cdialog
                      .getRule(), cdialog.getDescription());
              data.setConstraint(newConstraint);
              tableViewer.setInput(constraintList);
              tableViewer.refresh();
            }
          }

          if (constraint instanceof RutaGEConstraint) {
            ConstraintDialog cdialog = new ConstraintDialog(getShell());
            cdialog.create((RutaGEConstraint) constraint);
            if (cdialog.open() == Window.OK) {

              RutaGEConstraint newConstraint = new RutaGEConstraint(cdialog.getRule(), cdialog
                      .getDescription());
              data.setConstraint(newConstraint);
              tableViewer.setInput(constraintList);
              tableViewer.refresh();
            }
          }
        }

        // /...
      }

    });

    tc1 = new TableColumn(table, SWT.LEFT);
    tc1.setText("Constraint");
    tc1.setWidth(300);
    tc1.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        Comparator comparator = comparatorFactory.getComparator(tc1);
        Collections.sort(constraintList, comparator);
        if (editor.getEditor() != null) {
          editor.getEditor().dispose();
        }
        tableViewer.setInput(constraintList);
        tableViewer.refresh();
      }
    });

    tc2 = new TableColumn(table, SWT.LEFT);
    tc2.setText("Weight");
    tc2.setWidth(50);
    tc2.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        Comparator comparator = comparatorFactory.getComparator(tc2);
        Collections.sort(constraintList, comparator);
        if (editor.getEditor() != null) {
          editor.getEditor().dispose();
        }
        tableViewer.setInput(constraintList);
        tableViewer.refresh();
      }
    });

    editor = new TableEditor(table);
    // The editor must have the same size as the cell and must
    // not be any smaller than 50 pixels.
    editor.horizontalAlignment = SWT.LEFT;
    editor.grabHorizontal = true;
    editor.minimumWidth = 50;
    table.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        // Clean up any previous editor control
        Control oldEditor = editor.getEditor();

        if (oldEditor != null)
          oldEditor.dispose();

        // Identify the selected row
        TableItem item = (TableItem) e.item;
        if (item == null)
          return;

        // The control that will be the editor must be a child of the
        // Table
        Spinner newEditor = new Spinner(table, SWT.NONE);

        if (item.getData() instanceof ConstraintData) {
          ConstraintData data = (ConstraintData) item.getData();
          newEditor.setSelection(data.getWeight());
        }

        newEditor.addModifyListener(new ModifyListener() {
          public void modifyText(ModifyEvent e) {
            if (editor.getItem().isDisposed()) {
              return;
            }
            Spinner spinner = (Spinner) editor.getEditor();
            int newWeight = spinner.getSelection();
            TableItem item = editor.getItem();
            if (item.getData() instanceof ConstraintData) {
              ConstraintData data = (ConstraintData) item.getData();
              data.setWeight(newWeight);
            }
            tableViewer.refresh();
          }
        });
        newEditor.setFocus();
        editor.setEditor(newEditor, item, EDITABLECOLUMN);
      }
    });
    // Resize columns to match input
    // for (int i = 0, n = table.getColumnCount(); i < n; i++) {
    // table.getColumn(i).pack();
    // }

    table.setHeaderVisible(true);
    table.setLinesVisible(true);
    tableViewer.refresh();
    this.layout();

  }

  public void selectionChanged(SelectionChangedEvent arg0) {
    // TODO Auto-generated method stub

  }

  private void initImages() {
    images = new HashMap<String, Image>();
    ImageDescriptor desc;
    Image image;
    String name;

    desc = RutaAddonsPlugin.getImageDescriptor("/icons/10563.add_as_source_folder.gif");
    image = desc.createImage();
    name = "addAsSourceFolder";
    images.put(name, image);

    desc = RutaAddonsPlugin.getImageDescriptor("/icons/cup_add.png");
    image = desc.createImage();
    name = "cupAdd";
    images.put(name, image);

    desc = RutaAddonsPlugin.getImageDescriptor("/icons/delete.png");
    image = desc.createImage();
    name = "delete";
    images.put(name, image);

    desc = RutaAddonsPlugin.getImageDescriptor("/icons/script_add.png");
    image = desc.createImage();
    name = "scriptAdd";
    images.put(name, image);

    desc = RutaAddonsPlugin.getImageDescriptor("/icons/ruta_obj_add.png");
    image = desc.createImage();
    name = "tmAdd";
    images.put(name, image);

    desc = RutaAddonsPlugin.getImageDescriptor("/icons/add.png");
    image = desc.createImage();
    name = "add";
    images.put(name, image);

    desc = RutaAddonsPlugin.getImageDescriptor("/icons/folder_page.png");
    image = desc.createImage();
    name = "export";
    images.put(name, image);

  }

  public Image getImage(String name) {
    if (images == null) {
      initImages();
    }
    return images.get(name);
  }

  public ArrayList<ConstraintData> getConstraintList() {
    return this.constraintList;
  }

  public TableViewer getViewer() {
    return tableViewer;
  }

  public void saveState(IMemento memento) {
    for (ConstraintData constraintData : constraintList) {
      if (constraintData.getConstraint() instanceof SimpleRutaRuleConstraint) {
        IMemento currentMemento = memento.createChild("constraint", "SimpleRutaConstraint");
        SimpleRutaRuleConstraint constraint = (SimpleRutaRuleConstraint) constraintData
                .getConstraint();
        currentMemento.createChild("Rule", constraint.getRule());
        currentMemento.createChild("Description", constraint.getDescription());
      }

      if (constraintData.getConstraint() instanceof RutaRuleListConstraint) {
        IMemento currentMemento = memento.createChild("constraint", "ListRutaConstraint");
        RutaRuleListConstraint constraint = (RutaRuleListConstraint) constraintData
                .getConstraint();
        currentMemento.createChild("Rule", constraint.getRule());
        currentMemento.createChild("Description", constraint.getDescription());
      }

      if (constraintData.getConstraint() instanceof RutaGEConstraint) {
        IMemento currentMemento = memento.createChild("constraint", "GEConstraint");
        RutaGEConstraint constraint = (RutaGEConstraint) constraintData.getConstraint();
        currentMemento.createChild("Rule", constraint.getConstraintText());
        currentMemento.createChild("Description", constraint.getDescription());
      }
    }
  }

  public void restoreState(IMemento memento) {
    for (IMemento constraintMemento : memento.getChildren("constraint")) {
      if (constraintMemento.getID().equals("SimpleRutaConstraint")) {
        String rule = constraintMemento.getChild("Rule").getID();
        String description = constraintMemento.getChild("Description").getID();
        SimpleRutaRuleConstraint constraint = new SimpleRutaRuleConstraint(rule, description);
        ConstraintData data = new ConstraintData(constraint);
        constraintList.add(data);
      }
      if (constraintMemento.getID().equals("ListRutaConstraint")) {
        String rule = constraintMemento.getChild("Rule").getID();
        String description = constraintMemento.getChild("Description").getID();
        RutaRuleListConstraint constraint = new RutaRuleListConstraint(rule, description);
        ConstraintData data = new ConstraintData(constraint);
        constraintList.add(data);
      }
      if (constraintMemento.getID().equals("GEConstraint")) {
        String rule = constraintMemento.getChild("Rule").getID();
        String description = constraintMemento.getChild("Description").getID();
        RutaGEConstraint constraint = new RutaGEConstraint(rule, description);
        ConstraintData data = new ConstraintData(constraint);
        constraintList.add(data);
      }
    }
    tableViewer.setInput(constraintList);
    tableViewer.refresh();
  }

  public static Image createImage(String path) {
    return RutaAddonsPlugin.getImageDescriptor(path).createImage();
  }

  // public void exportConstraint (ConstraintData data) {
  // String type = "";
  // String ruleText = "";
  // String description = "";
  // }
  public void setConstraints(ArrayList<ConstraintData> constraints) {
    this.constraintList = constraints;
  }
  
}
