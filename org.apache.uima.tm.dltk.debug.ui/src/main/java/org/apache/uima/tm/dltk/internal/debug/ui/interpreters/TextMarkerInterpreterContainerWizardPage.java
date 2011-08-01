package org.apache.uima.tm.dltk.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterComboBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterContainerWizardPage;

public class TextMarkerInterpreterContainerWizardPage extends
        AbstractInterpreterContainerWizardPage {

  @Override
  protected AbstractInterpreterComboBlock getInterpreterBlock() {
    return new TextMarkerInterpreterComboBlock();
  }
}
