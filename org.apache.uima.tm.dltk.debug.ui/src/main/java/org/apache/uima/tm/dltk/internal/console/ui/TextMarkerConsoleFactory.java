package org.apache.uima.tm.dltk.internal.console.ui;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.dltk.console.IScriptInterpreter;
import org.eclipse.dltk.console.ScriptConsolePrompt;
import org.eclipse.dltk.console.ui.IScriptConsoleFactory;
import org.eclipse.dltk.console.ui.ScriptConsole;
import org.eclipse.dltk.console.ui.ScriptConsoleFactoryBase;
import org.eclipse.jface.preference.IPreferenceStore;

import org.apache.uima.tm.dltk.textmarker.console.TextMarkerConsoleConstants;
import org.apache.uima.tm.dltk.textmarker.console.TextMarkerInterpreter;
import org.apache.uima.tm.dltk.internal.debug.ui.TextMarkerDebugUIPlugin;

public class TextMarkerConsoleFactory extends ScriptConsoleFactoryBase implements
        IScriptConsoleFactory {
  protected IPreferenceStore getPreferenceStore() {
    return TextMarkerDebugUIPlugin.getDefault().getPreferenceStore();
  }

  protected ScriptConsolePrompt makeInvitation() {
    IPreferenceStore store = getPreferenceStore();
    return new ScriptConsolePrompt(store.getString(TextMarkerConsoleConstants.PREF_NEW_PROMPT),
            store.getString(TextMarkerConsoleConstants.PREF_CONTINUE_PROMPT));
  }

  protected TextMarkerConsole makeConsole(TextMarkerInterpreter interpreter, String id) {
    TextMarkerConsole console = new TextMarkerConsole(interpreter, id);
    console.setPrompt(makeInvitation());
    return console;
  }

  private TextMarkerConsole createConsoleInstance(IScriptInterpreter interpreter, String id) {
    if (interpreter == null) {
      try {
        id = "default";
        interpreter = new TextMarkerInterpreter();
        // TextMarkerConsoleUtil.runDefaultTextMarkerInterpreter((TextMarkerInterpreter)
        // interpreter);
      } catch (Exception e) {
        return null;
      }
    }

    return makeConsole((TextMarkerInterpreter) interpreter, id);
  }

  @Override
  protected ScriptConsole createConsoleInstance() {
    return createConsoleInstance(null, null);
  }

  public TextMarkerConsoleFactory() {
    super();
  }

  public void openConsole(IScriptInterpreter interpreter, String id) {
    registerAndOpenConsole(createConsoleInstance(interpreter, id));
  }

  public void openConsole(IScriptInterpreter interpreter, String id, ILaunch launch) {
    TextMarkerConsole tmConsole = createConsoleInstance(interpreter, id);
    tmConsole.setLaunch(launch);
    registerAndOpenConsole(tmConsole);
  }

}
