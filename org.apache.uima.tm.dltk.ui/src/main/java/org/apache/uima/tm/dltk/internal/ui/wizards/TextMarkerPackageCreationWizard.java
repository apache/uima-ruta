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

package org.apache.uima.tm.dltk.internal.ui.wizards;

import org.apache.uima.tm.dltk.core.TextMarkerNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.ui.wizards.NewPackageCreationWizard;
import org.eclipse.dltk.ui.wizards.NewPackageWizardPage;
import org.eclipse.jface.viewers.IStructuredSelection;


public class TextMarkerPackageCreationWizard extends NewPackageCreationWizard {
  public static final String ID_WIZARD = "org.apache.uima.tm.dltk.ui.wizards.NewPackageCreationWizard";

  @Override
  protected NewPackageWizardPage createNewPackageWizardPage() {
    return new NewPackageWizardPage() {
      @Override
      public void createPackage(IProgressMonitor monitor) throws CoreException,
              InterruptedException {
        if (monitor == null) {
          monitor = new NullProgressMonitor();
        }
        IProjectFragment root = getProjectFragment();
        String packName = getPackageText();
        packName = packName.replaceAll("[.]", "/");
        fCreatedScriptFolder = root.createScriptFolder(packName, true, monitor);
        if (monitor.isCanceled()) {
          throw new InterruptedException();
        }
      }

      @Override
      protected String getRequiredNature() {
        return TextMarkerNature.NATURE_ID;
      }

      @Override
      public void init(IStructuredSelection selection) {
        super.init(selection);
        setPackageText("", true);
      }
    };
  }
}
