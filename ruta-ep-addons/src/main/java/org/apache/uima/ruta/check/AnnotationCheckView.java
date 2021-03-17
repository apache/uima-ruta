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

package org.apache.uima.ruta.check;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

public class AnnotationCheckView extends ViewPart {
  public static final String ID = "org.apache.uima.ruta.check";

  private AnnotationCheckComposite checkComp;

  private IMemento memento;

  public AnnotationCheckView() {
    super();
  }

  @Override
  public void createPartControl(Composite parent) {
    checkComp = new AnnotationCheckComposite(parent, SWT.NULL, this);
    if (memento != null) {
      checkComp.restoreState(memento);
      memento = null;
    }
    checkComp.refreshTypeSystem();
  }

  @Override
  public void setFocus() {
    this.checkComp.setFocus();
  }

  public AnnotationCheckComposite getComposite() {
    return checkComp;
  }

  @Override
  public void saveState(IMemento memento) {
    checkComp.saveState(memento);
  }

  @Override
  public void init(IViewSite site, IMemento memento) throws PartInitException {
    this.memento = memento;
    super.init(site, memento);
  }

//Not sure why we need to add this... but here we go...
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public Object getAdapter(Class aAdapter) {
    return super.getAdapter(aAdapter);
  }
}
