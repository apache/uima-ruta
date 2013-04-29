/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.ruta.ide.ui.console;

import org.apache.uima.ruta.ide.RutaIdePlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.dltk.console.IScriptInterpreter;
import org.eclipse.dltk.console.ScriptConsolePrompt;
import org.eclipse.dltk.console.ui.IScriptConsole;
import org.eclipse.dltk.console.ui.IScriptConsoleFactory;
import org.eclipse.dltk.console.ui.ScriptConsole;
import org.eclipse.dltk.console.ui.ScriptConsoleFactoryBase;
import org.eclipse.jface.preference.IPreferenceStore;

public class RutaConsoleFactory extends ScriptConsoleFactoryBase implements
        IScriptConsoleFactory {
  protected IPreferenceStore getPreferenceStore() {
    return RutaIdePlugin.getDefault().getPreferenceStore();
  }

  protected ScriptConsolePrompt makeInvitation() {
    IPreferenceStore store = getPreferenceStore();
    return new ScriptConsolePrompt(store.getString(RutaConsoleConstants.PREF_NEW_PROMPT),
            store.getString(RutaConsoleConstants.PREF_CONTINUE_PROMPT));
  }

  protected RutaConsole makeConsole(RutaInterpreter interpreter, String id) {
    RutaConsole console = new RutaConsole(interpreter, id);
    console.setPrompt(makeInvitation());
    return console;
  }

  private RutaConsole createConsoleInstance(IScriptInterpreter interpreter, String id) {
    if (interpreter == null) {
      try {
        id = "default";
        interpreter = new RutaInterpreter();
        // RutaConsoleUtil.runDefaultRutaInterpreter((RutaInterpreter)
        // interpreter);
      } catch (Exception e) {
        return null;
      }
    }

    return makeConsole((RutaInterpreter) interpreter, id);
  }

  @Override
  protected ScriptConsole createConsoleInstance() {
    return createConsoleInstance(null, null);
  }

  public RutaConsoleFactory() {
    super();
  }

  public void openConsole(IScriptInterpreter interpreter, String id) {
    registerAndOpenConsole(createConsoleInstance(interpreter, id));
  }

  public IScriptConsole openConsole(IScriptInterpreter interpreter, String id, ILaunch launch) {
    RutaConsole tmConsole = createConsoleInstance(interpreter, id);
    tmConsole.setLaunch(launch);
    registerAndOpenConsole(tmConsole);
    return tmConsole;
  }

}
