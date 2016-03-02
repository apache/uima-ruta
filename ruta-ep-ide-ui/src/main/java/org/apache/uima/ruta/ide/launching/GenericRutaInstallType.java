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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.apache.uima.ruta.ide.core.RutaNature;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.environment.IDeployment;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.dltk.internal.launching.AbstractInterpreterInstallType;
import org.eclipse.dltk.launching.EnvironmentVariable;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.LibraryLocation;

public class GenericRutaInstallType extends AbstractInterpreterInstallType {

  public static final String DBGP_FOR_RUTA_BUNDLE_ID = "org.apache.uima.ruta.ide.interpreter.dbgp";

  public static final String EMBEDDED_RUTA_BUNDLE_ID = "org.apache.uima.ruta.ide.interpreter";

  public String getNatureId() {
    return RutaNature.NATURE_ID;
  }

  public String getName() {
    return "Generic Ruta Install";
  }

  @Override
  public LibraryLocation[] getDefaultLibraryLocations(IFileHandle installLocation,
          EnvironmentVariable[] variables, IProgressMonitor monitor) {
    return new LibraryLocation[0];
  }

  private static String[] possibleExes = { "ruta" };

  @Override
  protected String getPluginId() {
    return RutaIdeUIPlugin.PLUGIN_ID;
  }

  @Override
  protected String[] getPossibleInterpreterNames() {
    return possibleExes;
  }

  @Override
  protected IInterpreterInstall doCreateInterpreterInstall(String id) {
    return new GenericRutaInstall(this, id);
  }

  @Override
  protected void filterEnvironment(@SuppressWarnings("rawtypes") Map environment) {
    environment.remove("TMLIBPATH");
    environment.remove("DISPLAY");
  }

  @Override
  public IStatus validateInstallLocation(IFileHandle installLocation) {
    return Status.OK_STATUS;
  }

  @Override
  protected IPath createPathFile(IDeployment deployment) throws IOException {
    throw new RuntimeException("This method should not be used");
  }

  @Override
  protected String[] parsePaths(String result) {
    ArrayList<String> paths = new ArrayList<String>();
    String subs = null;
    int index = 0;
    while (index < result.length()) {
      while (index < result.length() && Character.isWhitespace(result.charAt(index)))
        index++;
      if (index == result.length())
        break;

      if (result.charAt(index) == '{') {
        int start = index;
        while (index < result.length() && result.charAt(index) != '}')
          index++;
        if (index == result.length())
          break;
        subs = result.substring(start + 1, index);
      } else {
        int start = index;
        while (index < result.length() && result.charAt(index) != ' ')
          index++;
        subs = result.substring(start, index);
      }

      paths.add(subs);
      index++;
    }

    return (String[]) paths.toArray(new String[paths.size()]);
  }

  @Override
  protected ILog getLog() {
    return RutaIdeUIPlugin.getDefault().getLog();
  }

}
