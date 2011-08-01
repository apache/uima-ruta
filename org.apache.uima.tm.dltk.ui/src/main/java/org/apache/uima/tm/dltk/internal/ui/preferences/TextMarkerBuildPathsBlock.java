package org.apache.uima.tm.dltk.internal.ui.preferences;

import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;


public class TextMarkerBuildPathsBlock extends BuildpathsBlock {
  public TextMarkerBuildPathsBlock(IRunnableContext runnableContext, IStatusChangeListener context,
          int pageToShow, boolean useNewPage, IWorkbenchPreferenceContainer pageContainer) {
    super(runnableContext, context, pageToShow, useNewPage, pageContainer);
  }

  @Override
  protected IPreferenceStore getPreferenceStore() {
    return TextMarkerUI.getDefault().getPreferenceStore();
  }

  @Override
  protected boolean supportZips() {
    return true;
  }
}
