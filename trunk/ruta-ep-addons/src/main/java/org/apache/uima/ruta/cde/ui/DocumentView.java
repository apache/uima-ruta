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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

public class DocumentView extends ViewPart {
  private DocumentSelectComposite docComposite;

  private IMemento memento;

  public DocumentView() {
    super();
  }

  public void createPartControl(Composite parent) {
    this.docComposite = new DocumentSelectComposite(parent, SWT.FILL);
    getSite().setSelectionProvider(docComposite.getViewer());
    if (memento != null) {
      docComposite.restoreState(memento);
      memento = null;
    }
  }

  public void dispose() {
    super.dispose();
  }

  @Override
  public void setFocus() {
    docComposite.setFocus();
  }

  public DocumentSelectComposite getDocComposite() {
    return docComposite;
  }

  public void saveState(IMemento memento) {
    docComposite.saveState(memento);
  }

  public void init(IViewSite site, IMemento memento) throws PartInitException {
    this.memento = memento;
    super.init(site, memento);
  }
}
