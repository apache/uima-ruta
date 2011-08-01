package org.apache.uima.tm.dltk.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterLibraryBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.AddScriptInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.IAddInterpreterDialogRequestor;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.swt.widgets.Shell;

public class AddTextMarkerInterpreterDialog extends AddScriptInterpreterDialog {
  public AddTextMarkerInterpreterDialog(IAddInterpreterDialogRequestor requestor, Shell shell,
          IInterpreterInstallType[] interpreterInstallTypes, IInterpreterInstall editedInterpreter) {
    super(requestor, shell, interpreterInstallTypes, editedInterpreter);
  }

  @Override
  protected AbstractInterpreterLibraryBlock createLibraryBlock(AddScriptInterpreterDialog dialog) {
    return new TextMarkerInterpreterLibraryBlock(dialog);
  }

  @Override
  protected boolean useInterpreterArgs() {
    return false;
  }

}
