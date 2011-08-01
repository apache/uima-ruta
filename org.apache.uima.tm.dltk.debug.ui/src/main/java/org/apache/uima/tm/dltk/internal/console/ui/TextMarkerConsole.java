package org.apache.uima.tm.dltk.internal.console.ui;

import org.eclipse.dltk.console.ui.ScriptConsole;

import org.apache.uima.tm.dltk.console.TextMarkerInterpreter;

public class TextMarkerConsole extends ScriptConsole {
  public static final String CONSOLE_TYPE = "tm_console";

  public static final String CONSOLE_NAME = "TextMarker Console";

  public TextMarkerConsole(TextMarkerInterpreter interpreter, String id) {
    super(CONSOLE_NAME + " [" + id + "]", CONSOLE_TYPE);

    setInterpreter(interpreter);
    setTextHover(new TextMarkerConsoleTextHover(interpreter));
    setContentAssistProcessor(new TextMarkerConsoleCompletionProcessor(interpreter));

  }

}
