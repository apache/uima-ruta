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

package org.apache.uima.textmarker.ide.core.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ModelException;

public class TextMarkerProjectUtils {

  public static IPath getEngineDescriptorPath(IPath scriptPath, IProject project) {
    String elementName = getModuleName(scriptPath);
    IPath descPackagePath = getDescriptorPackagePath(scriptPath, project);
    IPath result = descPackagePath.append(elementName + "Engine.xml");
    return result;
  }

  public static IPath getTypeSystemDescriptorPath(IPath scriptPath, IProject project) {
    String elementName = getModuleName(scriptPath);
    IPath descPackagePath = getDescriptorPackagePath(scriptPath.makeAbsolute(), project);
    IPath result = descPackagePath.append(elementName + "TypeSystem.xml");
    return result;
  }

  public static IPath getDescriptorRootPath(IProject project) {
    IPath projectPath = project.getLocation();
    IPath descPath = projectPath.append(getDefaultDescriptorLocation());
    return descPath;
  }

  public static List<IFolder> getAllScriptFolders(IScriptProject proj) throws CoreException {
    List<IFolder> result = new ArrayList<IFolder>();
    result.addAll(getScriptFolders(proj));
    result.addAll(getReferencedScriptFolders(proj));
    return result;
  }

  public static List<IFolder> getReferencedScriptFolders(IScriptProject proj) throws CoreException {
    List<IFolder> result = new ArrayList<IFolder>();
    IProject[] referencedProjects = proj.getProject().getReferencedProjects();
    for (IProject eachProject : referencedProjects) {
      IScriptProject scriptProject = DLTKCore.create(eachProject);
      result.addAll(TextMarkerProjectUtils.getScriptFolders(scriptProject));
    }
    return result;
  }

  public static List<IFolder> getScriptFolders(IScriptProject proj) {
    List<IFolder> result = new ArrayList<IFolder>();
    try {
      IScriptFolder[] scriptFolders = proj.getScriptFolders();
      for (IScriptFolder eachScriptFolder : scriptFolders) {
        IModelElement parent = eachScriptFolder.getParent();
        IResource resource = parent.getResource();
        if (parent != null && resource != null && resource instanceof IFolder) {
          if (!result.contains(resource)) {
            result.add((IFolder) resource);
          }
        }
      }
    } catch (ModelException e) {
      TextMarkerIdePlugin.error(e);
    }
    return result;
  }

  public static List<IFolder> getAllDescriptorFolders(IProject proj) throws CoreException {
    List<IFolder> result = new ArrayList<IFolder>();
    result.addAll(getDescriptorFolders(proj));
    result.addAll(getReferencedDescriptorFolders(proj));
    return result;
  }

  public static List<IFolder> getReferencedDescriptorFolders(IProject proj) throws CoreException {
    List<IFolder> result = new ArrayList<IFolder>();
    IProject[] referencedProjects = proj.getReferencedProjects();
    for (IProject eachProject : referencedProjects) {
      result.addAll(TextMarkerProjectUtils.getDescriptorFolders(eachProject));
    }
    return result;
  }

  public static List<IFolder> getDescriptorFolders(IProject proj) throws ModelException {
    List<IFolder> result = new ArrayList<IFolder>();
    IFolder findElement = proj.getFolder(getDefaultDescriptorLocation());
    if (findElement != null) {
      result.add((IFolder) findElement);
    }
    return result;
  }

  public static List<String> getFolderLocations(List<IFolder> folders) {
    List<String> result = new ArrayList<String>();
    if (folders == null)
      return result;
    for (IFolder each : folders) {
      String portableString = each.getLocation().toPortableString();
      result.add(portableString);
    }
    return result;
  }

  public static IPath getScriptRootPath(IProject project) {
    IPath projectPath = project.getLocation();
    IPath descPath = projectPath.append(getDefaultScriptLocation());
    return descPath;
  }

  public static IPath getDescriptorPackagePath(IPath scriptPath, IProject project) {
    IPath projectPath = project.getLocation().makeAbsolute();
    IPath packagePath = scriptPath.removeLastSegments(1);
    IPath relativePackagePath;
    relativePackagePath = packagePath.makeRelativeTo(projectPath).removeFirstSegments(1);
    IPath descPackagePath = projectPath.append(getDefaultDescriptorLocation());
    descPackagePath = descPackagePath.append(relativePackagePath);
    return descPackagePath;
  }

  public static String getModuleName(IPath path) {
    String result = path.lastSegment();
    int lastIndexOf = result.lastIndexOf(".tm");
    if (lastIndexOf != -1) {
      result = result.substring(0, lastIndexOf);
    }
    return result;
  }

  public static void setProjectDataPath(IProject project, IFolder folder) throws CoreException {
    project.setPersistentProperty(new QualifiedName("", "CDEdataPath"), folder.getLocation()
            .toPortableString());

  }

  public static String getDefaultInputLocation() {
    return "input";
  }

  public static String getDefaultOutputLocation() {
    return "output";
  }

  public static String getDefaultTestLocation() {
    return "test";
  }

  public static String getDefaultScriptLocation() {
    return "script";
  }

  public static String getDefaultResourcesLocation() {
    return "resources";
  }

  public static String getDefaultDescriptorLocation() {
    return "descriptor";
  }

  public static String getDefaultTempTestLocation() {
    return "temp";
  }
}
