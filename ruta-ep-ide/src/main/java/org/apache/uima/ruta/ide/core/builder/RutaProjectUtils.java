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

package org.apache.uima.ruta.ide.core.builder;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.ide.core.RutaNature;
import org.eclipse.core.filesystem.URIUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathAttribute;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.JavaProject;

public class RutaProjectUtils {

  public static final String BUILDPATH_ATTRIBUTE_RUTA = "ruta";

  public static final String BUILDPATH_ATTRIBUTE_SCRIPT = "script";

  public static final String BUILDPATH_ATTRIBUTE_DESCRIPTOR = "descriptor";

  public static final String BUILDPATH_ATTRIBUTE_RESOURCES = "resources";

  public static final String JAVA_NATURE = "org.eclipse.jdt.core.javanature";

  public static final String M2E_NATURE = "org.eclipse.m2e.core.maven2Nature";

  public static final String CDE_DATA_PATH = "CDEdataPath";

  public static final String DEFAULT_TYPESYSTEM_SUFFX = "TypeSystem";

  public static final String DEFAULT_ANALYSISENGINE_SUFFIX = "Engine";

  public static String getAnalysisEngineSuffix(IProject project) throws CoreException {
    IScriptProject scriptProject = DLTKCore.create(project);
    IBuildpathEntry[] buildpathEntries = scriptProject.getRawBuildpath();
    for (IBuildpathEntry each : buildpathEntries) {
      if (each.getEntryKind() == IBuildpathEntry.BPE_CONTAINER
              && StringUtils.equals(each.getPath().toPortableString(), "RUTA_BUILD_VARS")) {
        IBuildpathAttribute[] attributes = each.getExtraAttributes();
        for (IBuildpathAttribute eachAttr : attributes) {
          if (StringUtils.equals(eachAttr.getName(), "analysisEngineSuffix")) {
            return eachAttr.getValue();
          }
        }
      }
    }
    return DEFAULT_ANALYSISENGINE_SUFFIX;
  }

  public static String getTypeSystemSuffix(IProject project) throws CoreException {
    IScriptProject scriptProject = DLTKCore.create(project);
    IBuildpathEntry[] buildpathEntries = scriptProject.getRawBuildpath();
    for (IBuildpathEntry each : buildpathEntries) {
      if (each.getEntryKind() == IBuildpathEntry.BPE_CONTAINER
              && StringUtils.equals(each.getPath().toPortableString(), "RUTA_BUILD_VARS")) {
        IBuildpathAttribute[] attributes = each.getExtraAttributes();
        for (IBuildpathAttribute eachAttr : attributes) {
          if (StringUtils.equals(eachAttr.getName(), "typeSystemSuffix")) {
            return eachAttr.getValue();
          }
        }
      }
    }
    return DEFAULT_TYPESYSTEM_SUFFX;
  }

  public static IPath getAnalysisEngineDescriptorPath(IPath scriptPath, IProject project)
          throws CoreException {
    String analysisEngineSuffix = getAnalysisEngineSuffix(project);
    String name = getScriptWithPackage(scriptPath, project);
    String[] paths = getDescriptorPathsArray(project);
    String locate = RutaEngine.locate(name, paths, analysisEngineSuffix + ".xml");
    if (locate != null) {
      return org.eclipse.core.runtime.Path.fromPortableString(locate);
    } else {
      return null;
    }
  }

  public static IPath getAnalysisEngineDescriptorPath(String scriptLocation) throws CoreException {
    IPath analysisEngineDescriptorPath;
    IPath scriptPath = new org.eclipse.core.runtime.Path(scriptLocation);
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    IWorkspaceRoot workspaceRoot = workspace.getRoot();
    IFile fileForLocation = workspaceRoot.getFileForLocation(scriptPath);
    analysisEngineDescriptorPath = RutaProjectUtils.getAnalysisEngineDescriptorPath(scriptPath,
            fileForLocation.getProject());
    return analysisEngineDescriptorPath;
  }

  public static IPath getTypeSystemDescriptorPath(IPath scriptPath, IProject project)
          throws CoreException {
    String typeSystemSuffix = getTypeSystemSuffix(project);
    String name = getScriptWithPackage(scriptPath, project);
    String[] paths = getDescriptorPathsArray(project);
    String locate = RutaEngine.locate(name, paths, typeSystemSuffix + ".xml");
    if (locate != null) {
      return org.eclipse.core.runtime.Path.fromPortableString(locate);
    } else {
      return null;
    }
  }

  public static IPath getTypeSystemDescriptorPath(String scriptLocation) throws CoreException {
    IPath analysisEngineDescriptorPath;
    IPath scriptPath = new org.eclipse.core.runtime.Path(scriptLocation);
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    IWorkspaceRoot workspaceRoot = workspace.getRoot();
    IFile fileForLocation = workspaceRoot.getFileForLocation(scriptPath);
    analysisEngineDescriptorPath = RutaProjectUtils.getTypeSystemDescriptorPath(scriptPath,
            fileForLocation.getProject());
    return analysisEngineDescriptorPath;
  }

  public static String getScriptWithPackage(IPath scriptPath, IProject project)
          throws CoreException {
    String name = getModuleName(scriptPath);
    IPath packagePath = getPackagePath(scriptPath, project);
    if (packagePath != null) {
      String packageString = packagePath.toPortableString().replaceAll("/", ".");
      if (!StringUtils.isBlank(packageString)) {
        name = packageString.concat(".").concat(name);
      }
    }
    return name;
  }

  private static String[] getDescriptorPathsArray(IProject project) throws CoreException {
    List<IFolder> descriptorFolders = getDescriptorFolders(project);
    String[] paths = new String[descriptorFolders.size()];
    int i = 0;
    for (IFolder each : descriptorFolders) {
      paths[i] = each.getLocation().toPortableString();
      i++;
    }
    return paths;
  }

  public static IPath getDescriptorRootPath(IProject project) throws CoreException {
    List<IFolder> descriptorFolders = getDescriptorFolders(project);
    if (descriptorFolders != null && !descriptorFolders.isEmpty()) {
      return descriptorFolders.get(0).getLocation();
    } else {
      IPath projectPath = project.getLocation();
      IPath descPath = projectPath.append(getDefaultDescriptorLocation());
      return descPath;
    }
  }

  public static List<IFolder> getAllScriptFolders(IScriptProject proj) throws CoreException {
    List<IFolder> result = new ArrayList<IFolder>();
    result.addAll(getScriptFolders(proj));
    result.addAll(getReferencedScriptFolders(proj));
    return result;
  }

  public static List<IFolder> getReferencedScriptFolders(IScriptProject proj) throws CoreException {
    return getReferencedScriptFolders(proj, new HashSet<IProject>());
  }

  public static List<IFolder> getReferencedScriptFolders(IScriptProject proj,
          Collection<IProject> visited) throws CoreException {
    List<IFolder> result = new ArrayList<IFolder>();
    IProject[] referencedProjects = proj.getProject().getReferencedProjects();
    for (IProject eachProject : referencedProjects) {
      if (!visited.contains(eachProject) && eachProject.exists()) {
        IScriptProject scriptProject = DLTKCore.create(eachProject);
        result.addAll(getScriptFolders(scriptProject));
        visited.add(eachProject);
        result.addAll(getReferencedScriptFolders(scriptProject, visited));
      }
    }
    return result;
  }

  public static List<IFolder> getScriptFolders(IProject project) throws CoreException {
    IScriptProject scriptProject = DLTKCore.create(project);
    return getScriptFolders(scriptProject);
  }

  public static List<IFolder> getScriptFolders(IScriptProject sp) throws CoreException {
    List<IFolder> result = new ArrayList<IFolder>();

    if (!sp.isOpen()) {
      return result;
    }

    IBuildpathEntry[] rawBuildpath = null;
    try {
      rawBuildpath = sp.getRawBuildpath();
    } catch (ModelException e) {
      // do not spam error log
    }
    if (rawBuildpath != null) {
      for (IBuildpathEntry each : rawBuildpath) {
        IPath path = each.getPath();
        int entryKind = each.getEntryKind();
        if (entryKind == IBuildpathEntry.BPE_SOURCE) {
          IBuildpathAttribute[] extraAttributes = each.getExtraAttributes();
          for (IBuildpathAttribute eachAttr : extraAttributes) {
            if (eachAttr.getName().equals(BUILDPATH_ATTRIBUTE_RUTA)
                    && eachAttr.getValue().equals(BUILDPATH_ATTRIBUTE_SCRIPT)) {
              result.add(ResourcesPlugin.getWorkspace().getRoot().getFolder(path));
            }
          }
        }
      }
    }
    if (result.isEmpty()) {
      IFolder findElement = sp.getProject().getFolder(getDefaultScriptLocation());
      if (findElement != null && findElement.exists()) {
        result.add((IFolder) findElement);
      }
    }
    return result;
  }

  public static List<IFolder> getAllDescriptorFolders(IProject proj) throws CoreException {
    List<IFolder> result = new ArrayList<IFolder>();
    result.addAll(getDescriptorFolders(proj));
    result.addAll(getReferencedDescriptorFolders(proj));
    return result;
  }
  
  public static List<IFolder> getAllResourceFolders(IProject proj) throws CoreException {
    List<IFolder> result = new ArrayList<IFolder>();
    result.addAll(getResourceFolders(proj));
    result.addAll(getReferencedResourceFolders(proj));
    return result;
  }

  public static List<IFolder> getReferencedDescriptorFolders(IProject proj) throws CoreException {
    return getReferencedDescriptorFolders(proj, new HashSet<IProject>());
  }

  public static List<IFolder> getReferencedDescriptorFolders(IProject proj,
          Collection<IProject> visited) throws CoreException {
    List<IFolder> result = new ArrayList<IFolder>();
    Collection<IProject> referencedProjects = getReferencedProjects(proj, new HashSet<IProject>());
    for (IProject eachProject : referencedProjects) {
      if (!visited.contains(eachProject)) {
        result.addAll(RutaProjectUtils.getDescriptorFolders(eachProject));
        visited.add(eachProject);
        result.addAll(getReferencedDescriptorFolders(eachProject, visited));
      }
    }
    return result;
  }
  
  public static List<IFolder> getReferencedResourceFolders(IProject proj) throws CoreException {
    return getReferencedDescriptorFolders(proj, new HashSet<IProject>());
  }

  public static List<IFolder> getReferencedResourceFolders(IProject proj,
          Collection<IProject> visited) throws CoreException {
    List<IFolder> result = new ArrayList<IFolder>();
    Collection<IProject> referencedProjects = getReferencedProjects(proj, new HashSet<IProject>());
    for (IProject eachProject : referencedProjects) {
      if (!visited.contains(eachProject)) {
        result.addAll(RutaProjectUtils.getResourceFolders(eachProject));
        visited.add(eachProject);
        result.addAll(getReferencedResourceFolders(eachProject, visited));
      }
    }
    return result;
  }

  public static Collection<IProject> getReferencedProjects(IProject proj,
          Collection<IProject> visited) throws CoreException {
    Collection<IProject> result = new HashSet<IProject>();
    if (!proj.isOpen()) {
      return result;
    }
    IProject[] referencedProjects = proj.getReferencedProjects();
    result.addAll(Arrays.asList(referencedProjects));
    IProjectNature nature = proj.getNature(JAVA_NATURE);
    if (nature != null) {
      JavaProject javaProject = (JavaProject) JavaCore.create(proj);
      IClasspathEntry[] resolvedClasspath = javaProject.getResolvedClasspath();
      for (IClasspathEntry eachCPE : resolvedClasspath) {
        if (eachCPE.getEntryKind() == IClasspathEntry.CPE_PROJECT) {
          IProject project = getProject(eachCPE.getPath());
          result.add(project);
        }
      }
    }
    return result;
  }

  public static List<IFolder> getDescriptorFolders(IProject project) throws CoreException {
    List<IFolder> result = new ArrayList<IFolder>();
    if (!project.isOpen()) {
      return result;
    }

    IProjectNature rutaNature = project.getNature(RutaNature.NATURE_ID);
    if (rutaNature != null) {

      // try to access script project
      IScriptProject sp = DLTKCore.create(project);
      if (sp != null) {
        IBuildpathEntry[] rawBuildpath = null;
        try {
          rawBuildpath = sp.getRawBuildpath();
        } catch (ModelException e) {
          // do not spam error log
        }
        if (rawBuildpath != null) {
          for (IBuildpathEntry each : rawBuildpath) {
            IPath path = each.getPath();
            int entryKind = each.getEntryKind();
            if (entryKind == IBuildpathEntry.BPE_SOURCE) {
              IBuildpathAttribute[] extraAttributes = each.getExtraAttributes();
              for (IBuildpathAttribute eachAttr : extraAttributes) {
                if (eachAttr.getName().equals(BUILDPATH_ATTRIBUTE_RUTA)
                        && eachAttr.getValue().equals(BUILDPATH_ATTRIBUTE_DESCRIPTOR)) {
                  result.add(ResourcesPlugin.getWorkspace().getRoot().getFolder(path));
                }
              }
            }
          }
        }
      }
    }
    IProjectNature pearNature = project.getNature("org.apache.uima.pear.UimaNature");
    if (pearNature != null) {
      IFolder findElement = project.getFolder("desc");
      if (findElement != null) {
        result.add((IFolder) findElement);
      }
    }
    IProjectNature javaNature = project.getNature(JAVA_NATURE);
    if (javaNature != null) {
      IJavaProject javaProject = JavaCore.create(project);
      IPath readOutputLocation = javaProject.readOutputLocation();
      IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(readOutputLocation);
      result.add(folder);
    }

    if (result.isEmpty()) {
      IFolder findElement = project.getFolder(getDefaultDescriptorLocation());
      if (findElement != null && findElement.exists()) {
        result.add((IFolder) findElement);
      }
    }
    return result;
  }

  public static List<IFolder> getResourceFolders(IProject project) throws CoreException {
    List<IFolder> result = new ArrayList<IFolder>();
    if (!project.isOpen()) {
      return result;
    }
    IProjectNature rutaNature = project.getNature(RutaNature.NATURE_ID);
    if (rutaNature != null) {

      // try to access script project
      IScriptProject sp = DLTKCore.create(project);
      if (sp != null) {
        IBuildpathEntry[] rawBuildpath = null;
        try {
          rawBuildpath = sp.getRawBuildpath();
        } catch (ModelException e) {
          // do not spam error log
        }
        if (rawBuildpath != null) {
          for (IBuildpathEntry each : rawBuildpath) {
            IPath path = each.getPath();
            int entryKind = each.getEntryKind();
            if (entryKind == IBuildpathEntry.BPE_SOURCE) {
              IBuildpathAttribute[] extraAttributes = each.getExtraAttributes();
              for (IBuildpathAttribute eachAttr : extraAttributes) {
                if (eachAttr.getName().equals(BUILDPATH_ATTRIBUTE_RUTA)
                        && eachAttr.getValue().equals(BUILDPATH_ATTRIBUTE_RESOURCES)) {
                  result.add(ResourcesPlugin.getWorkspace().getRoot().getFolder(path));
                }
              }
            }
          }
        }
      }
    }
    IProjectNature javaNature = project.getNature(JAVA_NATURE);
    if (javaNature != null) {
      IJavaProject javaProject = JavaCore.create(project);
      IPath readOutputLocation = javaProject.readOutputLocation();
      IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(readOutputLocation);
      result.add(folder);
    }
    if (result.isEmpty()) {
      IFolder findElement = project.getFolder(getDefaultResourcesLocation());
      if (findElement != null) {
        result.add((IFolder) findElement);
      }
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

  public static IPath getScriptRootPath(IProject project) throws CoreException {
    List<IFolder> scriptFolders = getScriptFolders(project);
    if (scriptFolders != null && !scriptFolders.isEmpty()) {
      return scriptFolders.get(0).getLocation();
    } else {
      IPath projectPath = project.getLocation();
      IPath descPath = projectPath.append(getDefaultScriptLocation());
      return descPath;
    }
  }

  public static IPath getPackagePath(IPath scriptIPath, IProject project) throws CoreException {
    List<IFolder> scriptFolders = getScriptFolders(project);
    URI scriptURI = URIUtil.toURI(scriptIPath);
    Path scriptPath = Paths.get(scriptURI);
    IPath descIPath = null;
    for (IFolder eachFolder : scriptFolders) {
      IPath eachLocation = eachFolder.getLocation();
      URI eachURI = URIUtil.toURI(eachLocation);
      Path eachPath = Paths.get(eachURI);
      if (scriptPath.startsWith(eachPath)) {
        descIPath = eachLocation;
        break;
      }
    }
    if (descIPath != null) {
      IPath makeRelativeTo = scriptIPath.makeRelativeTo(descIPath);
      // remove script
      IPath relativePackagePath = makeRelativeTo.removeLastSegments(1);
      return relativePackagePath;
    }
    return null;
  }

  public static String getModuleName(IPath path) {
    String result = path.lastSegment();
    int lastIndexOf = result.lastIndexOf(RutaEngine.SCRIPT_FILE_EXTENSION);
    if (lastIndexOf != -1) {
      result = result.substring(0, lastIndexOf);
    }
    return result;
  }

  public static void addProjectDataPath(IProject project, IFolder folder) throws CoreException {
    String dataPath = project.getPersistentProperty((new QualifiedName("", CDE_DATA_PATH)));
    String sep = System.getProperty("path.separator");
    if (dataPath == null) {
      dataPath = "";
    }
    String[] split = dataPath.split(sep);
    List<String> paths = Arrays.asList(split);
    String addon = folder.getLocation().toPortableString();
    if (!paths.contains(addon)) {
      if (!StringUtils.isEmpty(dataPath)) {
        dataPath += sep;
      }
      dataPath += addon;
      project.setPersistentProperty(new QualifiedName("", CDE_DATA_PATH), dataPath);
    }
  }

  public static void removeProjectDataPath(IProject project, IFolder folder) throws CoreException {
    String dataPath = project.getPersistentProperty((new QualifiedName("", CDE_DATA_PATH)));
    String sep = System.getProperty("path.separator");
    if (dataPath == null) {
      return;
    }
    String path = folder.getLocation().toPortableString();
    if (!StringUtils.isEmpty(dataPath)) {
      dataPath = dataPath.replaceAll(path, "");
      dataPath = dataPath.replaceAll(sep + sep, "");
    }
    project.setPersistentProperty(new QualifiedName("", CDE_DATA_PATH), dataPath);
  }

  public static IProject getProject(IPath path) {
    IResource member = ResourcesPlugin.getWorkspace().getRoot().findMember(path);
    if (member instanceof IProject) {
      IProject p = (IProject) member;
      return p;
    }
    return null;
  }

  public static Collection<String> getClassPath(IProject project) throws CoreException {
    Collection<String> result = new TreeSet<String>();

    IProjectNature m2eNature = project.getNature(M2E_NATURE);
    IProjectNature javaNature = project.getNature(JAVA_NATURE);
    if (m2eNature != null && javaNature != null) {
      // use maven dependencies of java project alone
      JavaProject javaProject = (JavaProject) JavaCore.create(project);
      IClasspathEntry[] resolvedClasspath = javaProject.getResolvedClasspath();
      IWorkspace workspace = ResourcesPlugin.getWorkspace();
      IWorkspaceRoot root = workspace.getRoot();
      
      // TODO: skip jre libs?
      for (IClasspathEntry each : resolvedClasspath) {
        int entryKind = each.getEntryKind();
        IPath path = each.getPath();

        switch (entryKind) {
          case IClasspathEntry.CPE_LIBRARY:
            result.add(path.toPortableString());
            break;
          case IClasspathEntry.CPE_SOURCE:
            IPath outputLocation = each.getOutputLocation();
            addAbsoluteLocation(result, root, outputLocation);
            break;
          case IClasspathEntry.CPE_PROJECT:
            IProject refProject = root.getProject(path.lastSegment());
            JavaProject refJavaProject = (JavaProject) JavaCore.create(refProject);
            IPath refOutputLocation = refJavaProject.getOutputLocation();
            addAbsoluteLocation(result, root, refOutputLocation);
            break;
          default:
            // should have been resolved?
            break;
        }
      }
    } else {
      // collect all dependencies
      extendClasspathWithProject(result, project.getProject(), new HashSet<IProject>());
      Collection<String> dependencies = getDependencies(project.getProject());
      result.addAll(dependencies);
    }
    return result;
  }

  private static void addAbsoluteLocation(Collection<String> result, IWorkspaceRoot root, IPath outputLocation) {
    if (outputLocation != null) {
      IFolder folder = root.getFolder(outputLocation);
      if (folder != null && folder.exists()) {
        String absoluteLocation = folder.getLocation().makeAbsolute().toPortableString();
        result.add(absoluteLocation);
      }
    }
  }

  private static Collection<String> getDependencies(IProject project) throws CoreException {
    Collection<String> result = new TreeSet<String>();
    IProject[] referencedProjects = project.getReferencedProjects();
    for (IProject eachProject : referencedProjects) {
      extendClasspathWithProject(result, eachProject, new HashSet<IProject>());
      IProjectNature nature = eachProject.getNature(RutaNature.NATURE_ID);
      if (nature != null) {
        result.addAll(getDependencies(eachProject));
      }
    }
    return result;
  }

  private static void extendClasspathWithProject(Collection<String> result, IProject project,
          Collection<IProject> visited) throws CoreException, JavaModelException {
    if (project == null) {
      return;
    }
    IProjectNature rutaNature = project.getNature(RutaNature.NATURE_ID);
    if (rutaNature != null) {
      IScriptProject sp = DLTKCore.create(project);
      List<IFolder> scriptFolders = RutaProjectUtils.getScriptFolders(sp);
      for (IFolder each : scriptFolders) {
        result.add(each.getLocation().toPortableString());
      }
      List<IFolder> descriptorFolders = RutaProjectUtils.getDescriptorFolders(project);
      for (IFolder each : descriptorFolders) {
        result.add(each.getLocation().toPortableString());
      }
      List<IFolder> resourceFolders = RutaProjectUtils.getResourceFolders(project);
      for (IFolder each : resourceFolders) {
        result.add(each.getLocation().toPortableString());
      }
    }
    IProjectNature javaNature = project.getNature(RutaProjectUtils.JAVA_NATURE);
    if (javaNature != null) {
      JavaProject javaProject = (JavaProject) JavaCore.create(project);

      // add output, e.g., target/classes
      IPath readOutputLocation = javaProject.readOutputLocation();
      IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(readOutputLocation);
      result.add(folder.getLocation().toPortableString());

      IClasspathEntry[] rawClasspath = javaProject.getRawClasspath();
      for (IClasspathEntry each : rawClasspath) {
        int entryKind = each.getEntryKind();
        IPath path = each.getPath();
        if (entryKind == IClasspathEntry.CPE_PROJECT) {
          IProject p = RutaProjectUtils.getProject(path);
          if (!visited.contains(p)) {
            visited.add(p);
            extendClasspathWithProject(result, p, visited);
          }
        } else if (entryKind != IClasspathEntry.CPE_SOURCE) {
          String segment = path.segment(0);
          if (!segment.equals("org.eclipse.jdt.launching.JRE_CONTAINER")) {
            IClasspathEntry[] resolveClasspath = javaProject
                    .resolveClasspath(new IClasspathEntry[] { each });
            for (IClasspathEntry eachResolved : resolveClasspath) {
              if (eachResolved.getEntryKind() == IClasspathEntry.CPE_PROJECT) {
                IProject p = RutaProjectUtils.getProject(eachResolved.getPath());
                if (!visited.contains(p)) {
                  visited.add(p);
                  extendClasspathWithProject(result, p, visited);
                }
              } else {
                result.add(eachResolved.getPath().toPortableString());
              }
            }
          }
        }
      }
    }
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

  public static String getDefaultCleanTestLocation() {
    return "temp_clean";
  }

  public static String getDefaultScriptLocation() {
    return BUILDPATH_ATTRIBUTE_SCRIPT;
  }

  public static String getDefaultResourcesLocation() {
    return BUILDPATH_ATTRIBUTE_RESOURCES;
  }

  public static String getDefaultDescriptorLocation() {
    return BUILDPATH_ATTRIBUTE_DESCRIPTOR;
  }

  public static String getDefaultTempTestLocation() {
    return "temp";
  }
}
