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

package org.apache.uima.textmarker.ide.ui.console;

import java.io.IOException;

import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.apache.uima.textmarker.ide.core.TextMarkerNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.console.ScriptConsoleServer;
import org.eclipse.dltk.core.environment.EnvironmentManager;
import org.eclipse.dltk.core.environment.IExecutionEnvironment;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.dltk.launching.ScriptLaunchUtil;

public class TextMarkerConsoleUtil {

  public static void runDefaultTextMarkerInterpreter(TextMarkerInterpreter interpreter)
          throws CoreException, IOException {
    ScriptConsoleServer server = ScriptConsoleServer.getInstance();

    String id = server.register(interpreter);
    String port = Integer.toString(server.getPort());

    String[] args = new String[] { "127.0.0.1", port, id };

    IExecutionEnvironment exeEnv = (IExecutionEnvironment) EnvironmentManager.getLocalEnvironment()
            .getAdapter(IExecutionEnvironment.class);
    IFileHandle scriptFile = TextMarkerIdePlugin.getDefault().getConsoleProxy(exeEnv);
    ScriptLaunchUtil.runScript(TextMarkerNature.NATURE_ID, scriptFile, null, null, args, null);
  }
}
