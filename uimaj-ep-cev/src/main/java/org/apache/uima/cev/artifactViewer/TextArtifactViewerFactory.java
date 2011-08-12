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

package org.apache.uima.cev.artifactViewer;

import org.apache.uima.cas.CAS;
import org.apache.uima.cev.data.CEVData;
import org.apache.uima.cev.editor.CEVViewer;
import org.apache.uima.cev.extension.AbstractArtifactViewerFactory;
import org.apache.uima.cev.extension.ICEVArtifactViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;


public class TextArtifactViewerFactory extends AbstractArtifactViewerFactory {

  public ICEVArtifactViewer createArtifactViewer(CEVViewer viewer, CTabItem tabItem, CEVData casData) {
    Composite composite = new Composite(tabItem.getParent(), SWT.NONE);
    FillLayout layout = new FillLayout();
    composite.setLayout(layout);
    TextArtifactViewer text = new TextArtifactViewer(composite, SWT.READ_ONLY | SWT.WRAP
            | SWT.H_SCROLL | SWT.V_SCROLL, viewer);
    text.addMouseListener(viewer);

    tabItem.setControl(composite);
    tabItem.setText("Plain Text");

    if (casData.getDocumentText() != null) {
      text.setText(casData.getDocumentText());
    }

    return text;
  }

  public boolean isAble(CAS cas) {
    return cas.getDocumentText() != null && !"".equals(cas.getDocumentText());
  }

}
