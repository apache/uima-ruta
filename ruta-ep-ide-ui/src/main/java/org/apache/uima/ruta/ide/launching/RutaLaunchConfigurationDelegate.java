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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.apache.uima.ruta.ide.core.RutaCorePreferences;
import org.apache.uima.ruta.ide.core.RutaNature;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jdt.launching.JavaLaunchDelegate;
import org.eclipse.jface.preference.IPreferenceStore;
import org.osgi.framework.Bundle;

public class RutaLaunchConfigurationDelegate extends JavaLaunchDelegate {

  private String mode;

  @Override
  public String getProgramArguments(ILaunchConfiguration configuration) throws CoreException {
    StringBuilder cmdline = new StringBuilder();
    IScriptProject proj = AbstractScriptLaunchConfigurationDelegate.getScriptProject(configuration);

    String mainScriptAttribute = configuration.getAttribute("mainScript", "");

    String encoding = proj.getProject().getDefaultCharset();
    String view = configuration.getAttribute(RutaLaunchConstants.VIEW, CAS.NAME_DEFAULT_SOFA);
    if (StringUtils.isBlank(view)) {
      view = CAS.NAME_DEFAULT_SOFA;
    }
    boolean recursive = configuration.getAttribute(RutaLaunchConstants.RECURSIVE, false);

    IPreferenceStore preferenceStore = RutaIdeUIPlugin.getDefault().getPreferenceStore();
    boolean addSDI = preferenceStore.getBoolean(RutaCorePreferences.ADD_SDI);
    
    
    IResource member = proj.getProject().findMember(mainScriptAttribute);
    IPath projectPath = proj.getResource().getLocation();
    IPath inputDirPath = projectPath.append(RutaProjectUtils.getDefaultInputLocation());
    IPath outputDirPath = projectPath.append(RutaProjectUtils.getDefaultOutputLocation());
    String engine = RutaProjectUtils.getEngineDescriptorPath(member.getLocation(),
            proj.getProject()).toPortableString();
    String input = configuration.getAttribute(RutaLaunchConstants.INPUT_FOLDER,
            inputDirPath.toPortableString());
    if (StringUtils.isBlank(input)) {
      input = inputDirPath.toPortableString();
    }
    String output = configuration.getAttribute(RutaLaunchConstants.OUTPUT_FOLDER,
            outputDirPath.toPortableString());
    if (StringUtils.isBlank(output)) {
      output = outputDirPath.toPortableString();
    }

    cmdline.append(RutaLaunchConstants.ARG_DESCRIPTOR + " ");
    cmdline.append(engine + " ");

    cmdline.append(RutaLaunchConstants.ARG_INPUT_FOLDER + " ");
    cmdline.append(makeAbsolute(input, configuration) + " ");

    cmdline.append(RutaLaunchConstants.ARG_OUTPUT_FOLDER + " ");
    cmdline.append(makeAbsolute(output, configuration) + " ");

    cmdline.append(RutaLaunchConstants.ARG_MODE + " ");
    cmdline.append(mode + " ");

    cmdline.append(RutaLaunchConstants.ARG_ENCODING + " ");
    cmdline.append(encoding + " ");

    cmdline.append(RutaLaunchConstants.ARG_VIEW + " ");
    cmdline.append(view + " ");

    cmdline.append(RutaLaunchConstants.ARG_RECURSIVE + " ");
    cmdline.append(recursive + " ");

    cmdline.append(RutaLaunchConstants.ARG_ADD_SDI + " ");
    cmdline.append(addSDI + " ");
    
    return cmdline.toString();
  }

  private String makeAbsolute(String input, ILaunchConfiguration configuration)
          throws CoreException {
    IResource member = ResourcesPlugin.getWorkspace().getRoot().findMember(input);
    if (member != null) {
      return member.getLocation().toPortableString();
    }
    return input;
  }

  @Override
  public String getMainTypeName(ILaunchConfiguration configuration) throws CoreException {
    return "org.apache.uima.ruta.ide.launching.RutaLauncher";
  }

  @Override
  public String[] getClasspath(ILaunchConfiguration configuration) throws CoreException {

    List<String> extendedClasspath = new ArrayList<String>();
    Collections.addAll(extendedClasspath, super.getClasspath(configuration));
    IScriptProject scriptProject = AbstractScriptLaunchConfigurationDelegate
            .getScriptProject(configuration);
    extendedClasspath.addAll(getClassPath(scriptProject));

    return extendedClasspath.toArray(new String[extendedClasspath.size()]);
  }

  public static List<String> getClassPath(IScriptProject project) throws CoreException {
    RutaIdeUIPlugin d = RutaIdeUIPlugin.getDefault();
    List<String> extendedClasspath = new ArrayList<String>();
    // Normal mode, add the launcher plugin and uima runtime jar to the classpath
    if (!Platform.inDevelopmentMode()) {
      try {
        // Add this plugin jar to the classpath
        extendedClasspath.add(d.pluginIdToJarPath(RutaIdeUIPlugin.PLUGIN_ID));

        // UIMA jar should be added the end of the class path, because user uima jars
        // (maybe a different version) should appear first on the class path
        extendedClasspath.add(d.pluginIdToJarPath("org.apache.uima.runtime"));
        extendedClasspath.add(d.pluginIdToJarPath("org.apache.uima.ruta.engine"));
        
        // for inlined jars?  
        Bundle bundle = RutaIdeUIPlugin.getDefault().getBundle("org.apache.uima.runtime");
        if (bundle != null) {
          Enumeration<?> jarEnum = bundle.findEntries("/", "*.jar", true);
          if (jarEnum == null) {
            extendedClasspath.add(d.pluginIdToJarPath("org.apache.uima.runtime"));
          }
          while (jarEnum != null && jarEnum.hasMoreElements()) {
            URL element = (URL) jarEnum.nextElement();
            extendedClasspath.add(FileLocator.toFileURL(element).getFile());
          }
        }

        Bundle bundle2 = RutaIdeUIPlugin.getDefault().getBundle("org.apache.uima.ruta.engine");
        if (bundle2 != null) {
          Enumeration<?> jarEnum = bundle2.findEntries("/", "*.jar", true);
          while (jarEnum != null && jarEnum.hasMoreElements()) {
            URL element = (URL) jarEnum.nextElement();
            extendedClasspath.add(FileLocator.toFileURL(element).getFile());
          }
        }
        
      } catch (IOException e) {
        throw new CoreException(new Status(IStatus.ERROR, RutaIdeUIPlugin.PLUGIN_ID, IStatus.OK,
                "Failed to compose classpath!", e));
      }
    }
    // When running inside eclipse with PDE in development mode the plugins
    // are not installed inform of jar files and the classes must be loaded
    // from the target/classes folder or target/org.apache.uima.runtime.*.jar file
    else {
      try {
        extendedClasspath.add(d.pluginIdToJarPath(RutaIdeUIPlugin.PLUGIN_ID) + "target/classes");
        Bundle bundle = RutaIdeUIPlugin.getDefault().getBundle("org.apache.uima.runtime");
        if (bundle != null) {
          Enumeration<?> jarEnum = bundle.findEntries("/", "*.jar", true);
          if (jarEnum == null) {
            extendedClasspath.add(d.pluginIdToJarPath("org.apache.uima.runtime"));
          }
          while (jarEnum != null && jarEnum.hasMoreElements()) {
            URL element = (URL) jarEnum.nextElement();
            extendedClasspath.add(FileLocator.toFileURL(element).getFile());
          }
        }

        Bundle bundle2 = RutaIdeUIPlugin.getDefault().getBundle("org.apache.uima.ruta.engine");
        if (bundle2 != null) {
          Enumeration<?> jarEnum = bundle2.findEntries("/", "*.jar", true);
          while (jarEnum != null && jarEnum.hasMoreElements()) {
            URL element = (URL) jarEnum.nextElement();
            extendedClasspath.add(FileLocator.toFileURL(element).getFile());
          }
        }

      } catch (IOException e) {
        throw new CoreException(new Status(IStatus.ERROR, RutaIdeUIPlugin.PLUGIN_ID, IStatus.OK,
                "Failed to compose classpath!", e));
      }
    }
    Collection<String> dependencies = getDependencies(project.getProject());
    extendedClasspath.addAll(dependencies);

    Collection<String> extensions = getExtensions();
    extendedClasspath.addAll(extensions);
    return extendedClasspath;
  }

  private static Collection<String> getExtensions() throws CoreException {
    RutaIdeUIPlugin d = RutaIdeUIPlugin.getDefault();
    Collection<String> result = new TreeSet<String>();
    IExtension[] extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaIdeUIPlugin.PLUGIN_ID, "actionExtension").getExtensions();
    for (IExtension each : extensions) {
      String namespaceIdentifier = each.getNamespaceIdentifier();
      try {
        if (!Platform.inDevelopmentMode()) {
          result.add(d.pluginIdToJarPath(namespaceIdentifier));
        } else {
          result.add(d.pluginIdToJarPath(namespaceIdentifier) + "target/classes");
          result.add(d.pluginIdToJarPath(namespaceIdentifier) + "bin");
        }
      } catch (IOException e) {
        throw new CoreException(new Status(IStatus.ERROR, RutaIdeUIPlugin.PLUGIN_ID, IStatus.OK,
                "Failed to extend classpath with " + namespaceIdentifier + "!", e));
      }
    }
    return result;
  }

  private static Collection<String> getDependencies(IProject project)
          throws CoreException {
    Collection<String> result = new TreeSet<String>();
    IProject[] referencedProjects = project.getReferencedProjects();
    for (IProject eachProject : referencedProjects) {
      // for each java project
      extendClasspathWithProject(result, eachProject, new HashSet<IProject>());
      IProjectNature nature = eachProject.getNature(RutaNature.NATURE_ID);
      if(nature != null) {
        result.addAll(getDependencies(eachProject));
      }
    }
    return result;
  }

  private static void extendClasspathWithProject(Collection<String> result, IProject project,
          Collection<IProject> visited) throws CoreException, JavaModelException {
    IProjectNature nature = project.getNature(RutaProjectUtils.JAVANATURE);
    if (nature != null) {
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

  @Override
  public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch,
          IProgressMonitor monitor) throws CoreException {
    this.mode = mode;
    IResource ouputFolder = null;
    IScriptProject proj = AbstractScriptLaunchConfigurationDelegate.getScriptProject(configuration);
    IPath projectPath = proj.getResource().getLocation();
    IPath outputDirPath = projectPath.append(RutaProjectUtils.getDefaultOutputLocation());
    String outputFolderPath = configuration.getAttribute(RutaLaunchConstants.ARG_OUTPUT_FOLDER,
            outputDirPath.toPortableString());
    if (outputFolderPath.length() != 0) {
      IPath path = Path.fromPortableString(outputFolderPath);
      ouputFolder = ResourcesPlugin.getWorkspace().getRoot().getContainerForLocation(path);
    }
    boolean recursive = configuration.getAttribute(RutaLaunchConstants.RECURSIVE, false);
    clearOutputFolder(new File(ouputFolder.getLocation().toPortableString()), recursive);

    IPreferenceStore preferenceStore = RutaIdeUIPlugin.getDefault().getPreferenceStore();
    boolean noVM = preferenceStore.getBoolean(RutaCorePreferences.NO_VM_IN_DEV_MODE);
    if (noVM && Platform.inDevelopmentMode()) {
      String[] args = getProgramArguments(configuration).split(" ");
      try {
        RutaLauncher.main(args);
      } catch (Exception e1) {
        RutaIdeUIPlugin.error(e1);
      }
    } else {
      super.launch(configuration, mode, launch, monitor);

      while (!launch.isTerminated()) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          Thread.interrupted();
        }
      }
    }
    if (ouputFolder != null) {
      ouputFolder.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
    }
  }

  private void clearOutputFolder(File outputDir, boolean recursive) {
    IPreferenceStore store = RutaIdeUIPlugin.getDefault().getPreferenceStore();
    boolean clearOutput = store.getBoolean(RutaCorePreferences.PROJECT_CLEAR_OUTPUT);
    if (clearOutput) {
      List<File> outputFiles = getFiles(outputDir, recursive);
      for (File file : outputFiles) {
        file.delete();
      }
    }
  }

  private static List<File> getFiles(File dir, boolean recusive) {
    List<File> result = new ArrayList<File>();
    for (File each : dir.listFiles()) {
      // TODO: find a solution for this hotfix
      if (each.getName().endsWith(".svn")) {
        continue;
      }
      result.add(each);
      if (each.isDirectory() && recusive) {
        result.addAll(getFiles(each, recusive));
      }
    }
    return result;
  }

}
