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
