package org.apache.uima.tm.dltk.debugger;

import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterRunnerFactory;

public class TextMarkerDebuggerRunnerFactory implements IInterpreterRunnerFactory {

  /*
   * @see
   * org.eclipse.dltk.launching.IInterpreterRunnerFactory#createRunner(org.eclipse.dltk.launching
   * .IInterpreterInstall)
   */
  public IInterpreterRunner createRunner(IInterpreterInstall install) {
    return new TextMarkerDebuggerRunner(install);
  }
}
