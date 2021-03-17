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
import org.eclipse.ui.part.ViewPart;

public class ResultView extends ViewPart {

  private ResultViewComposite resultViewComposite;

  @Override
  public void createPartControl(Composite parent) {
    this.resultViewComposite = new ResultViewComposite(parent, SWT.FILL);
    getSite().getPage().addSelectionListener(resultViewComposite);
  }

  public ResultViewComposite getResultComposite() {
    return this.resultViewComposite;
  }

  @Override
  public void setFocus() {
    this.resultViewComposite.setFocus();
  }

//Not sure why we need to add this... but here we go...
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public Object getAdapter(Class aAdapter) {
    return super.getAdapter(aAdapter);
  }
}
