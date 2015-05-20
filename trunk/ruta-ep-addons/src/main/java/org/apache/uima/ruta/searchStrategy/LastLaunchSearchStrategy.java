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

package org.apache.uima.ruta.searchStrategy;

import org.apache.uima.caseditor.ide.TypeSystemLocationPropertyPage;
import org.apache.uima.caseditor.ide.searchstrategy.ITypeSystemSearchStrategy;
import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.ruta.ide.core.RutaNature;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.eclipse.core.filesystem.URIUtil;
import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.dltk.internal.launching.LaunchConfigurationUtils;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.ui.internal.Workbench;

public class LastLaunchSearchStrategy implements ITypeSystemSearchStrategy {

  public IFile findTypeSystem(IFile casFile) {
    IProject project = casFile.getProject();
    IFile typeSystemLocation = TypeSystemLocationPropertyPage.getTypeSystemLocation(project);
    if (typeSystemLocation != null && !typeSystemLocation.getName().equals("TypeSystem.xml")) {
      // do not override the properties!
      return null;
    }
    try {
      IProjectNature nature = project.getNature(RutaNature.NATURE_ID);
      if (!(nature instanceof RutaNature)) {
        return null;
      }
    } catch (CoreException e) {
      return null;
    }
    ILaunchConfiguration lastRun = DebugUITools
            .getLastLaunch("org.eclipse.debug.ui.launchGroup.run");
    String scriptName = LaunchConfigurationUtils.getString(lastRun,
            ScriptLaunchConfigurationConstants.ATTR_MAIN_SCRIPT_NAME, "");
    if (scriptName != null && scriptName.length() != 0 && new Path(scriptName).segmentCount() > 0
            && Path.ROOT.isValidPath(scriptName)) {
      final IFile scriptFile = project.getFile(scriptName);
      if (scriptFile.exists()) {
        IPath path = null;
        try {
          path = RutaProjectUtils.getTypeSystemDescriptorPath(scriptFile.getLocation(),
                  project);
        } catch (CoreException e) {
          RutaAddonsPlugin.error(e);
        }
        if (path != null) {
          
          IWorkspace workspace= ResourcesPlugin.getWorkspace();    
          IPath location= Path.fromOSString(path.toFile().getAbsolutePath()); 
          IFile ts= workspace.getRoot().getFileForLocation(location);
          if (ts != null && ts.exists()) {
            return ts;
          }
        }
      }
    }
    return null;
  }
}
