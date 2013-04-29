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

package org.apache.uima.ruta.ide.ui.wizards;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.internal.ui.wizards.IBuildpathContainerPage;
import org.eclipse.dltk.ui.wizards.IBuildpathContainerPageExtension;
import org.eclipse.dltk.ui.wizards.NewElementWizardPage;
import org.eclipse.swt.widgets.Composite;


public class RutaJavaContainerPage extends NewElementWizardPage implements IBuildpathContainerPage, IBuildpathContainerPageExtension{

  public RutaJavaContainerPage() {
    super("RutaJavaContainerPage");
  }

  public void createControl(Composite composite) {
    
  }

  public void initialize(IScriptProject project, IBuildpathEntry[] currentEntries) {
    
  }

  public boolean finish() {
    return false;
  }

  public IBuildpathEntry getSelection() {
    return null;
  }

  public void setSelection(IBuildpathEntry containerEntry) {
  }

 
}
