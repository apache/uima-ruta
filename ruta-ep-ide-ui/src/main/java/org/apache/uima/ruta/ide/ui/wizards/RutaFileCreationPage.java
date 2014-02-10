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

package org.apache.uima.ruta.ide.ui.wizards;

import java.util.List;

import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.apache.uima.ruta.ide.core.RutaNature;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.ui.wizards.NewSourceModulePage;

public class RutaFileCreationPage extends NewSourceModulePage {

  @Override
  protected String getPageDescription() {
    return "This wizard creates a new Ruta script file.";
  }

  @Override
  protected String getFileContent() {
    StringBuilder sb = new StringBuilder();
    IScriptFolder scriptFolder = getScriptFolder();
    IScriptProject scriptProject = scriptFolder.getScriptProject();

    
    IFolder folder = null;
    try {
      folder = getScriptFolderOf(scriptFolder, scriptProject);
    } catch (ModelException e) {
      RutaIdeUIPlugin.error(e);
    }
    if(folder == null)  {
      return "";
    }
    
    IPath path = scriptFolder.getPath();
    IPath fullPath = folder.getFullPath();
    IPath relativeTo = path.makeRelativeTo(fullPath);
    if(!relativeTo.isEmpty()) {
    sb.append("PACKAGE ");
    String pathString = "";
    for (int i = 0; i < relativeTo.segments().length; i++) {
      pathString += relativeTo.segments()[i];
      if (i < relativeTo.segments().length - 1) {
        pathString += ".";
      }
    }
    sb.append(pathString);
    sb.append(";\n");
    }
    return sb.toString();
  }

  private IFolder getScriptFolderOf(IScriptFolder scriptFolder, IScriptProject scriptProject) throws ModelException {
    List<IFolder> scriptFolders = RutaProjectUtils.getScriptFolders(scriptProject);
    for (IFolder each : scriptFolders) {
      if(each.equals(scriptFolder.getResource())) {
        return each;
      }
      IPath path = scriptFolder.getPath().makeRelativeTo(each.getFullPath());
      IResource findMember = each.findMember(path);
      if(findMember != null && findMember instanceof IFolder) {
        return each;
      }
    }
    return null;
  }

  @Override
  protected String getRequiredNature() {
    return RutaNature.NATURE_ID;
  }

  @Override
  protected String getPageTitle() {
    return "Create a new Ruta script file";
  }
}
