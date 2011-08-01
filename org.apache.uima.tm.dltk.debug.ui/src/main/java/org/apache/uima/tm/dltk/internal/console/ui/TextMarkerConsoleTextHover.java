package org.apache.uima.tm.dltk.internal.console.ui;

import java.io.IOException;

import org.eclipse.dltk.console.IScriptConsoleShell;
import org.eclipse.dltk.console.ui.IScriptConsoleViewer;
import org.eclipse.dltk.console.ui.ScriptConsoleTextHover;
import org.eclipse.jface.text.IRegion;

public class TextMarkerConsoleTextHover extends ScriptConsoleTextHover {

  private IScriptConsoleShell interpreterShell;

  public TextMarkerConsoleTextHover(IScriptConsoleShell interpreterShell) {
    this.interpreterShell = interpreterShell;
  }

  @Override
  protected String getHoverInfoImpl(IScriptConsoleViewer viewer, IRegion hoverRegion) {
    try {
      int cursorPosition = hoverRegion.getOffset() - viewer.getCommandLineOffset();

      String commandLine = viewer.getCommandLine();

      return interpreterShell.getDescription(commandLine, cursorPosition);
    } catch (IOException e) {
      // TODO: log exception
      e.printStackTrace();
      return null;
    }
  }
}
