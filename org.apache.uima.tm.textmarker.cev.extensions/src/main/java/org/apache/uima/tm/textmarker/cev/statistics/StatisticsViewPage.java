package org.apache.uima.tm.textmarker.cev.statistics;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.cev.data.CEVData;
import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVView;
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


public class StatisticsViewPage extends Page implements IStatisticsViewPage, ICEVView,
        IDoubleClickListener, ISelectionChangedListener {

  private static final String TYPE = "org.apache.uima.tm.textmarker.kernel.type.Statistics";

  private CEVDocument casDoc;

  private int current;

  private CEVViewer casViewer;

  private Map<String, Image> images;

  private TableViewer viewer;

  public StatisticsViewPage(CEVViewer casViewer, CEVDocument casDoc, int index) {
    super();
    this.casViewer = casViewer;
    this.casDoc = casDoc;
    this.current = index;
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
    viewChanged(current);
    //
    //   
    //
    // public void handleEvent(Event e) {
    // TableItem item = (TableItem) e.item;
    // int index = table.indexOf(item);
    // // int[] datum = data[index];
    // // item.setText(new String[] { Integer.toString(datum[0]), Integer.toString(datum[1]) });
    // }
    // });
    // // Add sort indicator and sort data when column selected
    // Listener sortListener = new Listener() {
    // public void handleEvent(Event e) {
    // // determine new sort column and direction
    // TableColumn sortColumn = table.getSortColumn();
    // TableColumn currentColumn = (TableColumn) e.widget;
    // int dir = table.getSortDirection();
    // if (sortColumn == currentColumn) {
    // dir = dir == SWT.UP ? SWT.DOWN : SWT.UP;
    // } else {
    // table.setSortColumn(currentColumn);
    // dir = SWT.UP;
    // }
    // // sort the data based on column and direction
    // final int index = currentColumn == cname ? 0 : 1;
    // final int direction = dir;
    // // Object[] data = new ;
    // // Arrays.sort(data, new Comparator() {
    // // public int compare(Object arg0, Object arg1) {
    // // int[] a = (int[]) arg0;
    // // int[] b = (int[]) arg1;
    // // if (a[index] == b[index])
    // // return 0;
    // // if (direction == SWT.UP) {
    // // return a[index] < b[index] ? -1 : 1;
    // // }
    // // return a[index] < b[index] ? 1 : -1;
    // // }
    // // });
    // // update data displayed in table
    // table.setSortDirection(dir);
    // table.clearAll();
    // }
    // };
    // cname.addListener(SWT.Selection, sortListener);
    // ctotal.addListener(SWT.Selection, sortListener);
    // camount.addListener(SWT.Selection, sortListener);
    // cpart.addListener(SWT.Selection, sortListener);
    // table.setSortColumn(cname);
    // table.setSortDirection(SWT.UP);

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

  public CEVData getCurrentCEVData() {
    return casDoc.getCASData(current);
  }

  public void doubleClick(DoubleClickEvent event) {
  }

  public void inputChange(Object newInput) {
    if (newInput instanceof FeatureStructure) {
      viewer.setInput(StatisticsEntry.createEntries((FeatureStructure) newInput));
    }
  }

  public void viewChanged(int newIndex) {
    getCurrentCEVData().removeAnnotationListener(this);
    current = newIndex;
    getCurrentCEVData().addAnnotationListener(this);
    CAS cas = getCurrentCEVData().getCAS();
    Type type = cas.getTypeSystem().getType(TYPE);
    if (type != null) {
      FSIterator allIndexedFS = cas.getIndexRepository().getAllIndexedFS(type);
      if (allIndexedFS.isValid()) {
        inputChange(allIndexedFS.get());
      }
    }
    viewer.refresh();
  }

  public void annotationsAdded(List<AnnotationFS> annots) {
  }

  public void annotationsRemoved(List<AnnotationFS> annots) {
  }

  public void annotationStateChanged(Type type) {
  }

  public void annotationStateChanged(AnnotationFS annot) {
  }

  public void colorChanged(Type type) {
  }

  public void newSelection(int offset) {
  }

  public void selectionChanged(SelectionChangedEvent event) {
  }

  public void casChanged(CEVDocument casDocument) {
    this.casDoc = casDocument;
  }

}
