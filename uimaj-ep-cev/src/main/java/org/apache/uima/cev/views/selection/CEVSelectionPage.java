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

package org.apache.uima.cev.views.selection;

import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cev.data.CEVDocument;
import org.apache.uima.cev.data.ICEVAnnotationSelectionListener;
import org.apache.uima.cev.data.tree.CEVAnnotationTreeNode;
import org.apache.uima.cev.data.tree.CEVTypeTreeNode;
import org.apache.uima.cev.data.tree.ICEVRootTreeNode;
import org.apache.uima.cev.data.tree.ICEVTreeNode;
import org.apache.uima.cev.editor.CEVViewer;
import org.apache.uima.cev.views.CEVAnnotationTreeViewPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;

public class CEVSelectionPage extends CEVAnnotationTreeViewPage implements ICEVSelectionPage,
        ICEVAnnotationSelectionListener {

  private int offset;

  public CEVSelectionPage(CEVViewer casView, CEVDocument casDocument, int index) {
    super(casView, casDocument, index);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.uniwue.casviewer.data.ICASAnnotationSelectionListener#newSelection (int)
   */
  public void newSelection(int offset) {
    this.offset = offset;

    ICEVRootTreeNode annotationOrderedTree = getCasData().getAnnotationOrderedTree(offset,
            manualTypeFilter);
    getTreeViewer().setInput(annotationOrderedTree);

    Object input = getTreeViewer().getInput();

    if (input instanceof ICEVRootTreeNode)
      for (ICEVTreeNode n : ((ICEVRootTreeNode) input).getNodes())
        if (n instanceof CEVAnnotationTreeNode)
          getTreeViewer().setChecked(n,
                  getCasData().isChecked(((CEVAnnotationTreeNode) n).getAnnotation()));
        else if (n instanceof CEVTypeTreeNode) {
          if (getCasData().isGrayed(n.getType()))
            getTreeViewer().setGrayChecked(n, true);
          else if (getCasData().isChecked(n.getType())) {
            getTreeViewer().setGrayed(n, false);
            getTreeViewer().setChecked(n, true);
          } else {
            getTreeViewer().setGrayChecked(n, false);
          }
        }

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.views.CEVAnnotationTreeViewPage#annotationAdded(org.
   * apache.uima.cas.text.AnnotationFS)
   */
  @Override
  public void annotationsAdded(List<AnnotationFS> annots) {
    newSelection(offset);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.views.CEVAnnotationTreeViewPage#annotationRemoved(org
   * .apache.uima.cas.text.AnnotationFS)
   */
  @Override
  public void annotationsRemoved(List<AnnotationFS> annots) {
    newSelection(offset);
  }

  public void handleEvent(Event event) {
    if (event.widget == filterTextField && event.type == SWT.Modify) {
      manualTypeFilter = filterTextField.getText();
      newSelection(offset);
    }
  }
}
