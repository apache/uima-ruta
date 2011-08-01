package org.apache.uima.tm.dltk.internal.ui.text;

import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.eclipse.dltk.ui.text.ScriptOutlineInformationControl;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Shell;


public class TextMarkerOutlineInformationControl extends ScriptOutlineInformationControl {

  public TextMarkerOutlineInformationControl(Shell parent, int shellStyle, int treeStyle,
          String commandId) {
    super(parent, shellStyle, treeStyle, commandId);
  }

  @Override
  protected IPreferenceStore getPreferenceStore() {
    return TextMarkerUI.getDefault().getPreferenceStore();
  }
}
