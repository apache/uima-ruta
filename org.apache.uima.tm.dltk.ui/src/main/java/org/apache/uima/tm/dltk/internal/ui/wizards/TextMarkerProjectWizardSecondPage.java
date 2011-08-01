/**
 * 
 */
package org.apache.uima.tm.dltk.internal.ui.wizards;

import org.apache.uima.tm.dltk.core.TextMarkerNature;
import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.apache.uima.tm.dltk.internal.ui.preferences.TextMarkerBuildPathsBlock;
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
    return TextMarkerUI.getDefault().getPreferenceStore();
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
