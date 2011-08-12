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

package org.apache.uima.textmarker.ide.ui.wizards;

import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.apache.uima.textmarker.ide.core.TextMarkerNature;
import org.apache.uima.textmarker.ide.ui.preferences.TextMarkerBuildPathsBlock;
import org.eclipse.dltk.ui.util.BusyIndicatorRunnableContext;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;
import org.eclipse.dltk.ui.wizards.ProjectWizardFirstPage;
import org.eclipse.dltk.ui.wizards.ProjectWizardSecondPage;
import org.eclipse.jface.preference.IPreferenceStore;

final class TextMarkerProjectWizardSecondPage extends ProjectWizardSecondPage {
  TextMarkerProjectWizardSecondPage(ProjectWizardFirstPage mainPage) {
    super(mainPage);
  }

  @Override
  protected BuildpathsBlock createBuildpathBlock(IStatusChangeListener listener) {
    return new TextMarkerBuildPathsBlock(new BusyIndicatorRunnableContext(), listener, 0,
            useNewSourcePage(), null);
  }

  @Override
  protected String getScriptNature() {
    return TextMarkerNature.NATURE_ID;
  }

  @Override
  protected IPreferenceStore getPreferenceStore() {
    return TextMarkerIdePlugin.getDefault().getPreferenceStore();
  }

  // protected BuildpathDetector createBuildpathDetector(
  // IProgressMonitor monitor, IDLTKLanguageToolkit toolkit)
  // throws CoreException {
  // TextMarkerBuildpathDetector detector = new TextMarkerBuildpathDetector(
  // getCurrProject(), toolkit);
  //
  // TextMarkerProjectWizardFirstPage page = (TextMarkerProjectWizardFirstPage) this
  // .getFirstPage();
  // detector.setUseAnalysis(page.useAnalysis());
  // detector.detectBuildpath(new SubProgressMonitor(monitor, 20));
  // return detector;
  // }

  // protected void postConfigureProject() throws CoreException {
  // final IProject project = getCurrProject();
  // final IEnvironment environment = EnvironmentManager
  // .getEnvironment(project);
  // if (environment != null && !environment.isLocal()) {
  // final Map options = new HashMap();
  // options.put(DLTKCore.INDEXER_ENABLED, DLTKCore.DISABLED);
  // options.put(DLTKCore.BUILDER_ENABLED, DLTKCore.DISABLED);
  // DLTKCore.create(project).setOptions(options);
  // }
  // }

}
