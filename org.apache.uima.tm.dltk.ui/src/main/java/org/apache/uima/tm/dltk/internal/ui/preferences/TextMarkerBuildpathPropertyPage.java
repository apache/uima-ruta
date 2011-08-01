package org.apache.uima.tm.dltk.internal.ui.preferences;

import org.eclipse.dltk.ui.preferences.BuildPathsPropertyPage;
import org.eclipse.dltk.ui.util.BusyIndicatorRunnableContext;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class TextMarkerBuildpathPropertyPage extends BuildPathsPropertyPage implements
        IWorkbenchPropertyPage {
  public TextMarkerBuildpathPropertyPage() {
  }

  @Override
  protected BuildpathsBlock createBuildPathBlock(IWorkbenchPreferenceContainer pageContainer) {
    return new TextMarkerBuildPathsBlock(new BusyIndicatorRunnableContext(), this, getSettings()
            .getInt(INDEX), false, pageContainer);
  }

}
