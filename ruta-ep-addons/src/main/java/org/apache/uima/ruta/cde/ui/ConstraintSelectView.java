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

public class ConstraintSelectView extends ViewPart {

	
	private ConstraintSelectComposite consSelectComp; 
	
  private IMemento memento;
	
	
	public ConstraintSelectView() {
		super();
	}
	@Override
	public void createPartControl(Composite parent) {
		consSelectComp =  new ConstraintSelectComposite(parent, SWT.FILL);
		if (memento != null) {
		  consSelectComp.restoreState(memento);
      memento = null;
    }
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		this.consSelectComp.setFocus();
	}
	
	public ConstraintSelectComposite getComposite() {
		return consSelectComp;
	}
	
	public void saveState(IMemento memento) {
	  consSelectComp.saveState(memento);
  }
	
  public void init(IViewSite site, IMemento memento) throws PartInitException {
    this.memento = memento;
    super.init(site, memento);
  }

}
