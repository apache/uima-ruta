package org.apache.uima.tm.textmarker.cev.explain.basic;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;

public class BasicTokenStreamTableLabelProvider {

  public void createColumns(TableViewer viewer) {
    String[] titles = { "Begin", "End", "Typ", "Text" };
    int[] bounds = { 50, 50, 70, 200 };

    int i = 0;
    TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
    column.getColumn().setWidth(bounds[i]);
    column.getColumn().setText(titles[i]);
    column.getColumn().setMoveable(true);
    column.setLabelProvider(new ColumnLabelProvider() {
      @Override
      public String getText(Object element) {
        return "" + ((BasicTokenEntry) element).getAnnotation().getBegin();
      }
    });

    i++;
    column = new TableViewerColumn(viewer, SWT.NONE);
    column.getColumn().setWidth(bounds[i]);
    column.getColumn().setText(titles[i]);
    column.getColumn().setMoveable(true);
    column.setLabelProvider(new ColumnLabelProvider() {
      @Override
      public String getText(Object element) {
        return "" + ((BasicTokenEntry) element).getAnnotation().getEnd();
      }
    });

    i++;
    column = new TableViewerColumn(viewer, SWT.NONE);
    column.getColumn().setWidth(bounds[i]);
    column.getColumn().setText(titles[i]);
    column.getColumn().setMoveable(true);
    column.setLabelProvider(new ColumnLabelProvider() {

      @Override
      public String getText(Object element) {
        return ((BasicTokenEntry) element).getTyp();
      }
    });

    i++;
    column = new TableViewerColumn(viewer, SWT.NONE);
    column.getColumn().setWidth(bounds[i]);
    column.getColumn().setText(titles[i]);
    column.getColumn().setMoveable(true);
    column.setLabelProvider(new ColumnLabelProvider() {

      @Override
      public String getText(Object element) {
        return ((BasicTokenEntry) element).getText();
      }
    });
  }
}
