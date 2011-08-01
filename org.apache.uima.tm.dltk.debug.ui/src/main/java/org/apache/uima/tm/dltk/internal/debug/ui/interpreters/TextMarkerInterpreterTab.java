package org.apache.uima.tm.dltk.internal.debug.ui.interpreters;

import org.eclipse.dltk.debug.ui.launchConfigurations.IMainLaunchConfigurationTabListenerManager;
import org.eclipse.dltk.debug.ui.launchConfigurations.InterpreterTab;
import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterComboBlock;

import org.apache.uima.tm.dltk.core.TextMarkerNature;

public class TextMarkerInterpreterTab extends InterpreterTab {

  public TextMarkerInterpreterTab(IMainLaunchConfigurationTabListenerManager listenerManager) {
    super(listenerManager);
  }

  @Override
  protected AbstractInterpreterComboBlock getInterpreterBlock() {
    return new TextMarkerInterpreterComboBlock();
  }

  @Override
  protected String getNature() {
    return TextMarkerNature.NATURE_ID;
  }

}
