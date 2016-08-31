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

package org.apache.uima.ruta.testing.ui.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.apache.uima.ruta.testing.preferences.TestingPreferenceConstants;
import org.apache.uima.ruta.testing.ui.views.evalDataTable.EvalTableContentProvider;
import org.apache.uima.ruta.testing.ui.views.evalDataTable.EvalTableLabelProvider;
import org.apache.uima.ruta.testing.ui.views.evalDataTable.TypeEvalTableConst;
import org.apache.uima.ruta.testing.ui.views.evalDataTable.TypeTableSorter;
import org.apache.uima.ruta.testing.ui.views.fn.FalseNegativeView;
import org.apache.uima.ruta.testing.ui.views.fp.FalsePositiveView;
import org.apache.uima.ruta.testing.ui.views.tp.TruePositiveView;
import org.apache.uima.ruta.testing.ui.views.util.CASLoader;
import org.apache.uima.ruta.testing.ui.views.util.EvalDataProcessor;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dltk.internal.ui.util.CoreUtility;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.IPageBookViewPage;
import org.eclipse.ui.part.Page;

public class TestViewPage extends Page implements IPageBookViewPage {

  private Composite overlay;

  private IResource script = null;

  private InfoPanel tInfoPanel;

  private TableViewer listviewer;

  private SashForm sash;

  private TableViewer tableViewer;

  private IPropertyChangeListener propertyChangeListener;

  private List<String> excludedTypes = new ArrayList<String>();

  private List<String> includedTypes = new ArrayList<String>();

  private ListLabelProvider labelProvider;

  public TestViewPage(Composite parent, IResource scriptResource) {
    this(scriptResource);
    this.overlay = new Composite(parent, 0);
    this.propertyChangeListener = null;
  }

  public TestViewPage(IResource scriptResource) {
    super();
    this.script = scriptResource;
  }

  @Override
  public void createControl(Composite parent) {

    IPreferenceStore store = RutaAddonsPlugin.getDefault().getPreferenceStore();
    this.propertyChangeListener = new IPropertyChangeListener() {
      public void propertyChange(PropertyChangeEvent event) {
        if (event.getProperty().equals("LOAD_OLD_TEST_RESULTS")) {

          if (event.getNewValue().toString().equals("true")) {
            loadExistingTests();
          }
        }
      }
    };
    store.addPropertyChangeListener(propertyChangeListener);

    this.overlay = new Composite(parent, SWT.NULL);

    GridLayout gridLayout = new GridLayout();
    gridLayout.numColumns = 1;
    gridLayout.marginWidth = 5;
    gridLayout.marginHeight = 5;
    overlay.setLayout(gridLayout);

    // Composite rowButtonOverlay = new Composite(overlay, SWT.RIGHT_TO_LEFT);
    GridData layoutData = new GridData(SWT.RIGHT);
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.END;
    layoutData.horizontalSpan = 1;
    // rowButtonOverlay.setLayout(rowLayout);

    tInfoPanel = new InfoPanel(overlay);
    GridData tInfoLabelData = new GridData();
    tInfoLabelData.grabExcessHorizontalSpace = true;
    tInfoLabelData.horizontalAlignment = GridData.FILL;
    tInfoLabelData.horizontalSpan = 1;
    tInfoPanel.setLayoutData(tInfoLabelData);

    IPath path = script.getFullPath().removeFileExtension();
    String pathString = path.removeFirstSegments(2).toPortableString().replaceAll("[/]", ".");
    tInfoPanel.setFilename(pathString);

    sash = new SashForm(overlay, SWT.HORIZONTAL);
    sash.setLayoutData(new GridData(GridData.FILL_BOTH));

    listviewer = new TableViewer(sash, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
    labelProvider = new ListLabelProvider();
    listviewer.setLabelProvider(labelProvider);
    ListContentProvider provider = new ListContentProvider();
    listviewer.setContentProvider(provider);
    listviewer.getControl().addKeyListener(new KeyAdapter() {

      @Override
      public void keyPressed(KeyEvent e) {
        int keyCode = e.keyCode;
        // backspace or delete
        if (keyCode == SWT.BS || keyCode == SWT.DEL) {
          List<?> list = (List<?>) listviewer.getInput();
          if (listviewer.getSelection() instanceof StructuredSelection) {
            StructuredSelection selection = (StructuredSelection) listviewer.getSelection();
            Iterator<?> iter = selection.iterator();
            while (iter.hasNext()) {
              list.remove(iter.next());
            }
          }
          listviewer.refresh(false);
        }
      }

    });
    checkProjectTestStructure(script);
    loadExistingTests();

    listviewer.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        Object obj = event.getSelection();
        if (obj instanceof IStructuredSelection) {
          StructuredSelection selection = (StructuredSelection) obj;
          Iterator<?> iterator = selection.iterator();
          while (iterator.hasNext()) {
            Object element = iterator.next();
            if (element instanceof TestCasData) {
              TestCasData data = (TestCasData) element;
              updateSingleTestInformation(data);

            }
          }
        }
      }
    });

    listviewer.addDoubleClickListener(new IDoubleClickListener() {

      public void doubleClick(DoubleClickEvent event) {
        Object obj = event.getSelection();
        if (obj instanceof IStructuredSelection) {
          StructuredSelection selection = (StructuredSelection) obj;
          Iterator<?> iterator = selection.iterator();
          while (iterator.hasNext()) {
            Object element = iterator.next();
            if (element instanceof TestCasData) {
              TestCasData data = (TestCasData) element;
              if (data.getResultPath() != null) {
                openInCasEditor(data.getResultPath());
              }
            }
          }
        }

      }
    });
    Transfer[] transfers = new Transfer[] { FileTransfer.getInstance() };
    int ops = DND.DROP_COPY | DND.DROP_DEFAULT | DND.DROP_LINK | DND.DROP_MOVE;

    listviewer.addDropSupport(ops, transfers, new ListDropAdapter(listviewer));
    GridData listData = new GridData();
    listData.horizontalAlignment = GridData.FILL;
    listData.verticalAlignment = GridData.FILL;
    listData.grabExcessHorizontalSpace = true;
    listData.grabExcessVerticalSpace = true;
    listData.horizontalSpan = 1;
    listviewer.getControl().setLayoutData(listData);

    tableViewer = new TableViewer(sash);
    tableViewer.setLabelProvider(new EvalTableLabelProvider());
    tableViewer.setContentProvider(new EvalTableContentProvider());

    tableViewer.setSorter(new TypeTableSorter());

    Table table = tableViewer.getTable();

    TableColumn tc = new TableColumn(table, SWT.LEFT);
    tc.setText("Type");
    tc.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        ((TypeTableSorter) tableViewer.getSorter()).doSort(TypeEvalTableConst.COLUMN_TYPE_NAME);
        tableViewer.refresh();
      }
    });

    tc = new TableColumn(table, SWT.LEFT);
    tc.setText("TP");
    tc.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        ((TypeTableSorter) tableViewer.getSorter())
                .doSort(TypeEvalTableConst.COLUMN_TRUE_POSITIVES);
        tableViewer.refresh();
      }
    });

    tc = new TableColumn(table, SWT.LEFT);
    tc.setText("FP");
    tc.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        ((TypeTableSorter) tableViewer.getSorter())
                .doSort(TypeEvalTableConst.COLUMN_FALSE_POSITIVES);
        tableViewer.refresh();
      }
    });

    tc = new TableColumn(table, SWT.LEFT);
    tc.setText("FN");
    tc.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        ((TypeTableSorter) tableViewer.getSorter())
                .doSort(TypeEvalTableConst.COLUMN_FALSE_NEGATIVES);
        tableViewer.refresh();
      }
    });

    tc = new TableColumn(table, SWT.LEFT);
    tc.setText("Prec.");
    tc.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        ((TypeTableSorter) tableViewer.getSorter()).doSort(TypeEvalTableConst.COLUMN_PRECISION);
        tableViewer.refresh();
      }
    });

    tc = new TableColumn(table, SWT.LEFT);
    tc.setText("Recall");
    tc.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        ((TypeTableSorter) tableViewer.getSorter()).doSort(TypeEvalTableConst.COLUMN_RECALL);
        tableViewer.refresh();
      }
    });

    tc = new TableColumn(table, SWT.LEFT);
    tc.setText("F1");
    tc.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        ((TypeTableSorter) tableViewer.getSorter()).doSort(TypeEvalTableConst.COLUMN_F1);
        tableViewer.refresh();
      }
    });

    for (int i = 0, n = table.getColumnCount(); i < n; i++) {
      table.getColumn(i).pack();
    }
    table.setHeaderVisible(true);
    table.setLinesVisible(true);
  }

  protected void openInCasEditor(IPath resultPath) {
    if (resultPath == null) {
      return;
    }
    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    IFile file = workspace.getRoot().getFileForLocation(resultPath);

    if (file != null && !file.isSynchronized(IResource.DEPTH_ZERO)) {
      try {
        file.refreshLocal(IResource.DEPTH_ZERO, new NullProgressMonitor());
      } catch (CoreException e) {
        RutaAddonsPlugin.error(e);
      }
    }
    try {
      page.openEditor(new FileEditorInput(file), "org.apache.uima.caseditor.editor");
      page.showView(TruePositiveView.ID);
      page.showView(FalsePositiveView.ID);
      page.showView(FalseNegativeView.ID);
    } catch (PartInitException e) {
      RutaAddonsPlugin.error(e);
    }
  }

  public static Image createImage(String path) {
    return RutaAddonsPlugin.getImageDescriptor(path).createImage();
  }

  public void setResource(IResource r) {
    this.script = r;
  }

  public IResource getResource() {
    return this.script;
  }

  public TableViewer getViewer() {
    return this.listviewer;
  }

  public void updateInfoPanel() {
    if (listviewer.getInput() != null) {
      listviewer.refresh();

      int falsePositiveTotalCount = 0;
      int falseNegativeTotalCount = 0;
      int truePositiveTotalCount = 0;
      ArrayList<TestCasData> dataList = (ArrayList<TestCasData>) listviewer.getInput();
      for (TestCasData data : dataList) {
        falsePositiveTotalCount += data.getFalsePositiveCount();
        falseNegativeTotalCount += data.getFalseNegativeCount();
        truePositiveTotalCount += data.getTruePositiveCount();
      }

      tInfoPanel.setFP(falsePositiveTotalCount);
      tInfoPanel.setFN(falseNegativeTotalCount);
      tInfoPanel.setRuns(truePositiveTotalCount);

      double a = falsePositiveTotalCount;
      double b = falseNegativeTotalCount;
      double c = truePositiveTotalCount;

      double precision = c / (c + a);
      double recall = c / (c + b);
      double fMeasure = 2 * (precision * recall) / (precision + recall);

      fMeasure = fMeasure * 1000;
      fMeasure = Math.round(fMeasure);
      fMeasure = fMeasure / 1000;

      tInfoPanel.setFMeasure(fMeasure);
    }
  }

  public void saveState(IMemento memento) {
    List<String> it = getIncludedTypes();
    StringBuilder sb1 = new StringBuilder();
    for (String each : it) {
      sb1.append(each);
      sb1.append(";");
    }
    List<String> et = getExcludedTypes();
    StringBuilder sb2 = new StringBuilder();
    for (String each : et) {
      sb2.append(each);
      sb2.append(";");
    }
    if (memento != null) {
      memento.createChild("includedTypes", sb1.toString());
      memento.createChild("excludedTypes", sb2.toString());
    }
  }

  public void restoreState(IMemento memento) {
    if (memento == null)
      return;

    IMemento itm = memento.getChild("includedTypes");
    if (itm != null) {
      String id = itm.getID();
      String[] split = id.split("");
      List<String> l = new ArrayList<String>();
      for (String string : split) {
        if (!StringUtils.isBlank(string)) {
          l.add(string);
        }
      }
      setIncludedTypes(l);
    }

    IMemento etm = memento.getChild("excludedTypes");
    if (etm != null) {
      String id = etm.getID();
      String[] split = id.split("");
      List<String> l = new ArrayList<String>();
      for (String string : split) {
        if (!StringUtils.isBlank(string)) {
          l.add(string);
        }
      }
      setExcludedTypes(l);
    }

  }

  @Override
  public Control getControl() {
    return overlay;
  }

  @Override
  public void setActionBars(IActionBars actionBars) {

  }

  @Override
  public void dispose() {
    overlay.dispose();
    labelProvider.dispose();
  }

  @Override
  public void setFocus() {
    overlay.setFocus();
  }

  private void loadExistingTests() {
    IPreferenceStore store = RutaAddonsPlugin.getDefault().getPreferenceStore();

    IProject project = script.getProject();
    IPath testFolderPath = project.getFullPath().append(RutaProjectUtils.getDefaultTestLocation())
            .removeFirstSegments(1);
    IPath scriptPath = script.getFullPath();
    IPath scriptPackagePath = scriptPath.removeFirstSegments(2);
    IPath testFolderPackagePath = testFolderPath.append(scriptPackagePath).removeFileExtension();
    ArrayList<TestCasData> viewerInput = new ArrayList<TestCasData>();

    IFolder testFolder = project.getFolder(testFolderPackagePath);
    IResource[] resourceArray;
    try {
      resourceArray = testFolder.members();

      for (IResource resource : resourceArray) {

        if (resource != null && resource instanceof IFile
                && resource.getLocation().getFileExtension().equals("xmi")) {
          TestCasData data = new TestCasData(resource.getLocation());
          if (Boolean.valueOf(store.getString(TestingPreferenceConstants.LOAD_OLD_TEST_RESULTS))) {
            data.loadPreviousResults(script);
            CAS resultCas = CASLoader.loadCas(script, data.getResultPath());
            EvalDataProcessor.calculateEvaluatData(data, resultCas);
          }
          viewerInput.add(data);
        }
      }
      // tInfoPanel.addCASViewNamesToCombo(viewNames);
    } catch (CoreException e) {
      RutaAddonsPlugin.error(e);
    }
    listviewer.setInput(viewerInput);
    listviewer.refresh();
  }

  private void checkProjectTestStructure(IResource r) {
    IProject project = r.getProject();
    IPath testFolderPath = project.getFullPath().append(RutaProjectUtils.getDefaultTestLocation())
            .removeFirstSegments(1);
    IPath scriptPath = r.getFullPath().removeFileExtension();
    IPath scriptPackagePath = scriptPath.removeFirstSegments(2);
    IPath testScriptPath = testFolderPath.append(scriptPackagePath);
    IPath resultPath = testScriptPath.append(TestCasData.RESULT_FOLDER);
    IFolder resultFolder = project.getFolder(resultPath);

    IPath path2TempTests = project.getFullPath().append(RutaProjectUtils.getDefaultTestLocation())
            .append(RutaProjectUtils.getDefaultTempTestLocation()).removeFirstSegments(1);
    IFolder tempTestFolder = project.getFolder(path2TempTests);

    if (!project.exists(resultPath)) {
      try {
        CoreUtility.createFolder(resultFolder, true, true, new NullProgressMonitor());
      } catch (CoreException e) {
        RutaAddonsPlugin.error(e);
      }
    }

    if (!project.exists(path2TempTests)) {
      try {
        CoreUtility.createFolder(tempTestFolder, true, true, new NullProgressMonitor());
      } catch (CoreException e) {
        RutaAddonsPlugin.error(e);
      }
    }
  }

  public void updateSingleTestInformation(TestCasData data) {
    if (data.getTypeEvalData() != null) {
      tableViewer.setInput(data.getTypeEvalData());
      for (TableColumn c : tableViewer.getTable().getColumns()) {
        c.pack();
      }
      tableViewer.refresh();
    }

  }

  public String getSelectedViewCasName() {
    return tInfoPanel.getSelectedViewCasName();
  }

  public void setExcludedTypes(List<String> excludedTypes) {
    this.excludedTypes = excludedTypes;
  }

  public List<String> getExcludedTypes() {
    return excludedTypes;
  }

  public void setIncludedTypes(List<String> includedTypes) {
    this.includedTypes = includedTypes;
  }

  public List<String> getIncludedTypes() {
    return includedTypes;
  }

}
