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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.eclipse.dltk.console.ConsoleRequest;
import org.eclipse.dltk.console.IScriptConsoleIO;
import org.eclipse.dltk.console.IScriptExecResult;
import org.eclipse.dltk.console.IScriptInterpreter;
import org.eclipse.dltk.console.InterpreterResponse;
import org.eclipse.dltk.console.ScriptExecResult;
import org.eclipse.dltk.console.ShellResponse;

public class RutaInterpreter implements IScriptInterpreter, ConsoleRequest {

  private static final String COMPLETE_COMMAND = "complete";

  private static final String DESCRIBE_COMMAND = "describe";

  private static final String CLOSE_COMMAND = "close";

  private IScriptConsoleIO protocol;

  private String content;

  private int state;

  public IScriptExecResult exec(String command) throws IOException {
    InterpreterResponse response = protocol.execInterpreter(command);
    state = response.getState();
    return new ScriptExecResult(response.getContent());
  }

  public String getOutput() {
    return content;
  }

  public int getState() {
    return state;
  }

  // IScriptInterpreterShell
  public List getCompletions(String commandLine, int position) throws IOException {

    String[] args = new String[] { commandLine, Integer.toString(position) };

    ShellResponse response = protocol.execShell(COMPLETE_COMMAND, args);

    return response.getCompletions();
  }

  public String getDescription(String commandLine, int position) throws IOException {
    String[] args = new String[] { commandLine, Integer.toString(position) };

    ShellResponse response = protocol.execShell(DESCRIBE_COMMAND, args);

    return response.getDescription();
  }

  public String[] getNames(String type) throws IOException {
    return null;
  }

  public void close() throws IOException {
    protocol.execShell(CLOSE_COMMAND, new String[] {});
    protocol.close();
  }

  // IScriptConsoleProtocol
  public void consoleConnected(IScriptConsoleIO protocol) {
    this.protocol = protocol;
  }

  public String getInitialOuput() {
    return null;
  }

  public void addInitialListenerOperation(Runnable runnable) {

  }

  public InputStream getInitialOutputStream() {
    return null;
  }

  public boolean isValid() {
    return protocol != null;
  }
}
