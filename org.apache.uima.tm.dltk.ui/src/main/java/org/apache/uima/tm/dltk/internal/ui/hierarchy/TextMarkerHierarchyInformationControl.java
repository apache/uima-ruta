package org.apache.uima.tm.dltk.internal.ui.hierarchy;

import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.eclipse.dltk.internal.ui.typehierarchy.HierarchyInformationControl;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Shell;


public class TextMarkerHierarchyInformationControl extends HierarchyInformationControl {

  public TextMarkerHierarchyInformationControl(Shell parent, int shellStyle, int treeStyle) {
    super(parent, shellStyle, treeStyle);
  }

  @Override
  protected IPreferenceStore getPreferenceStore() {
    return TextMarkerUI.getDefault().getPreferenceStore();
  }

}
