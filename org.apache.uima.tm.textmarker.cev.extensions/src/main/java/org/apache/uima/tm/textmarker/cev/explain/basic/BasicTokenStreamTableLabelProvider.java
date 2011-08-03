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
