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

package org.apache.uima.textmarker.statistics;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.caseditor.editor.AnnotationEditor;
import org.apache.uima.caseditor.editor.ICasDocument;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.Page;

public class StatisticsViewPage extends Page implements IDoubleClickListener,
        ISelectionChangedListener {

  private static final String TYPE = "org.apache.uima.textmarker.type.Statistics";

  private int current;

  private Map<String, Image> images;

  private TableViewer viewer;

  private AnnotationEditor editor;

  private ICasDocument document;

  public StatisticsViewPage(AnnotationEditor editor) {
    super();
    this.editor = editor;
    this.document = editor.getDocument();
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

  private void initImages() {
    images = new HashMap<String, Image>();
    ImageDescriptor desc;
    Image image;
    String name;

    // desc = TextMarkerCEVPlugin.getImageDescriptor("/icons/accept.png");
    // image = desc.createImage();
    // name = "matched";
    // images.put(name, image);
  }

  public Image getImage(String name) {
    if (images == null) {
      initImages();
    }
    return images.get(name);
  }

  @Override
  public void createControl(Composite parent) {
    createViewer(parent);
    CAS cas = document.getCAS();
    Type type = cas.getTypeSystem().getType(TYPE);
    if (type != null) {
      FSIterator<FeatureStructure> allIndexedFS = cas.getIndexRepository().getAllIndexedFS(type);
      if (allIndexedFS.isValid()) {
        inputChange(allIndexedFS.get());
      }
    }
    viewer.refresh();

  }

  private void createViewer(Composite parent) {
    viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
    createColumns(viewer);
    viewer.setContentProvider(new StatisticsContentProvider());
    viewer.setLabelProvider(new StatisticsLabelProvider(this));
  }

  // This will create the columns for the table
  private void createColumns(final TableViewer viewer) {
    Listener sortListener = new Listener() {

      public void handleEvent(Event e) {
        // determine new sort column and direction
        TableColumn sortColumn = viewer.getTable().getSortColumn();
        TableColumn currentColumn = (TableColumn) e.widget;
        int dir = viewer.getTable().getSortDirection();
        if (sortColumn == currentColumn) {
          dir = dir == SWT.UP ? SWT.DOWN : SWT.UP;
        } else {
          viewer.getTable().setSortColumn(currentColumn);
          dir = SWT.UP;
        }
        // sort the data based on column and direction
        final int index = Arrays.asList(viewer.getTable().getColumns()).indexOf(currentColumn);
        @SuppressWarnings("unchecked")
        List<StatisticsEntry> input = (List<StatisticsEntry>) viewer.getInput();
        Collections.sort(input, new EntryComparator(index, dir == SWT.UP));
        viewer.getTable().setSortDirection(dir);
        viewer.getTable().clearAll();
        viewer.setInput(input);
      }
    };

    String[] titles = { "Name", "Total", "Amount", "Each" };
    int[] bounds = { 100, 100, 75, 100 };
    TableViewerColumn column = null;
    for (int i = 0; i < titles.length; i++) {
      column = new TableViewerColumn(viewer, SWT.NONE);
      TableColumn tc = column.getColumn();
      tc.setText(titles[i]);
      tc.setWidth(bounds[i]);
      tc.setResizable(true);
      tc.setMoveable(true);
      tc.addListener(SWT.Selection, sortListener);
    }
    Table table = viewer.getTable();
    table.setHeaderVisible(true);
    table.setLinesVisible(true);
    table.setSortColumn(column.getColumn());
    table.setSortDirection(SWT.UP);
  }

  /**
   * Passing the focus request to the viewer's control.
   */
  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
  }

  @Override
  public Control getControl() {
    return viewer.getControl();
  }

  public void doubleClick(DoubleClickEvent event) {
  }

  public void inputChange(Object newInput) {
    if (newInput instanceof FeatureStructure) {
      viewer.setInput(StatisticsEntry.createEntries((FeatureStructure) newInput));
    }
  }

  public void selectionChanged(SelectionChangedEvent event) {

  }

}
