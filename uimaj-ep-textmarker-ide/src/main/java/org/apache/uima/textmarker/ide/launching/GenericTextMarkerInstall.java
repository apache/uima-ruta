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

package org.apache.uima.textmarker.ide.launching;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.uima.textmarker.ide.core.TextMarkerNature;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.dltk.launching.AbstractInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.IInterpreterRunner;


public class GenericTextMarkerInstall extends AbstractInterpreterInstall {

  @Override
  public String getBuiltinModuleContent(String name) {
    InputStream stream = GenericTextMarkerInstall.class.getResourceAsStream("builtins.tm");
    DataInputStream st = new DataInputStream(stream);
    StringBuffer buf = new StringBuffer();
    try {
      while (st.available() >= 0) {
        String line = st.readLine();
        if (line == null)
          break;
        buf.append(line);
        buf.append('\n');
      }

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return buf.toString();
  }

  @Override
  public String[] getBuiltinModules() {
    return new String[] { "builtins.tm" };
  }

  public GenericTextMarkerInstall(IInterpreterInstallType type, String id) {
    super(type, id);
  }

  @Override
  public IInterpreterRunner getInterpreterRunner(String mode) {
    IInterpreterRunner runner = super.getInterpreterRunner(mode);
    if (runner != null) {
      return runner;
    }

    if (mode.equals(ILaunchManager.RUN_MODE)) {
      return new TextMarkerInterpreterRunner(this);
    }
    return null;
  }

  public String getNatureId() {
    return TextMarkerNature.NATURE_ID;
  }
}
