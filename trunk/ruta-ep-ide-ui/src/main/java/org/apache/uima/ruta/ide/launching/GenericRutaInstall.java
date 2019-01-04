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

package org.apache.uima.ruta.ide.launching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.apache.uima.ruta.ide.core.RutaNature;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.dltk.launching.AbstractInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.IInterpreterRunner;

public class GenericRutaInstall extends AbstractInterpreterInstall {

  public GenericRutaInstall(IInterpreterInstallType type, String id) {
    super(type, id);
  }

  @Override
  public String getBuiltinModuleContent(String name) {
    InputStream stream = GenericRutaInstall.class.getResourceAsStream("builtins"
            + RutaEngine.SCRIPT_FILE_EXTENSION);
    if (stream == null) {
      return "PACKAGE org.apache.uima.ruta;\n";
    }
    BufferedReader br = new BufferedReader(new InputStreamReader(stream));
    StringBuffer buf = new StringBuffer();
    try {
      String line = null;
      while ((line = br.readLine()) != null) {
        buf.append(line);
        buf.append('\n');
      }
    } catch (IOException e) {
      RutaIdeUIPlugin.error(e);
    }
    return buf.toString();
  }

  @Override
  public String[] getBuiltinModules() {
    return new String[] { "builtins" + RutaEngine.SCRIPT_FILE_EXTENSION };
  }

  @Override
  public IInterpreterRunner getInterpreterRunner(String mode) {
    IInterpreterRunner runner = super.getInterpreterRunner(mode);
    if (runner != null) {
      return runner;
    }

    if (mode.equals(ILaunchManager.RUN_MODE)) {
      return new RutaInterpreterRunner(this);
    }
    return null;
  }

  public String getNatureId() {
    return RutaNature.NATURE_ID;
  }

}
