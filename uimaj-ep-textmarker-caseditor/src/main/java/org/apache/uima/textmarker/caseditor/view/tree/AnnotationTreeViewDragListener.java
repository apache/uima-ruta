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

package org.apache.uima.textmarker.caseditor.view.tree;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;

public class AnnotationTreeViewDragListener extends DragSourceAdapter {
  private StructuredViewer viewer;

  public AnnotationTreeViewDragListener(StructuredViewer viewer) {
    this.viewer = viewer;
  }

  public void dragFinished(DragSourceEvent event) {
    if (!event.doit)
      return;

  }

  public void dragSetData(DragSourceEvent event) {
    IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
    Object[] list = selection.toList().toArray();
    for (Object object : list) {
      if (object instanceof FeatureTreeNode) {
        event.data = ((FeatureTreeNode) object).getValue();
      } else if (object instanceof AnnotationTreeNode) {
        event.data = ((AnnotationTreeNode) object).getAnnotation().getCoveredText();
      }
    }
  }

  public void dragStart(DragSourceEvent event) {
    event.doit = !viewer.getSelection().isEmpty();
  }
}
