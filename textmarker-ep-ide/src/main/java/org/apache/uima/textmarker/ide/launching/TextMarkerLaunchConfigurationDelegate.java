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

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.apache.uima.textmarker.ide.core.TextMarkerCorePreferences;
import org.apache.uima.textmarker.ide.core.builder.TextMarkerProjectUtils;
import org.eclipse.core.resources.IBuildConfiguration;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaLaunchDelegate;
import org.eclipse.jface.preference.IPreferenceStore;
import org.osgi.framework.Bundle;

public class TextMarkerLaunchConfigurationDelegate extends JavaLaunchDelegate {

  @Override
  public String getProgramArguments(ILaunchConfiguration configuration) throws CoreException {
    StringBuilder cmdline = new StringBuilder();
    IScriptProject proj = AbstractScriptLaunchConfigurationDelegate.getScriptProject(configuration);

    String mainScriptAttribute = configuration.getAttribute("mainScript", "");
    IResource member = proj.getProject().findMember(mainScriptAttribute);
    IPath projectPath = proj.getResource().getLocation();
    IPath inputDirPath = projectPath.append(TextMarkerProjectUtils.getDefaultInputLocation());
    IPath outputDirPath = projectPath.append(TextMarkerProjectUtils.getDefaultOutputLocation());
    String engine = TextMarkerProjectUtils.getEngineDescriptorPath(member.getLocation(),
            proj.getProject()).toPortableString();

    File inputDirFile = new File(inputDirPath.toPortableString());
    File inputFile = new File(inputDirFile, "test.text");
    URI relativize1 = inputFile.toURI().relativize(inputDirFile.toURI());
    URI relativize2 = inputDirFile.toURI().relativize(inputFile.toURI());

    // String category = configuration.getCategory();
    // IFile file = configuration.getFile();
    // String name = configuration.getName();
    // ILaunchConfigurationType type = configuration.getType();
    // IResource[] mappedResources = configuration.getMappedResources();
    // Map attributes = configuration.getAttributes();

    cmdline.append(TextMarkerLaunchConstants.ARG_DESCRIPTOR + " ");
    cmdline.append(engine + " ");

    cmdline.append(TextMarkerLaunchConstants.ARG_INPUT_FOLDER + " ");
    cmdline.append(inputDirPath.toPortableString() + " ");

    cmdline.append(TextMarkerLaunchConstants.ARG_OUTPUT_FOLDER + " ");
    cmdline.append(outputDirPath.toPortableString() + " ");

    return cmdline.toString();
  }

  @Override
  public String getMainTypeName(ILaunchConfiguration configuration) throws CoreException {
    return "org.apache.uima.textmarker.ide.launching.TextMarkerLauncher";
  }

  @Override
  public String[] getClasspath(ILaunchConfiguration configuration) throws CoreException {
    TextMarkerIdePlugin d = TextMarkerIdePlugin.getDefault();
    // The class path already contains the jars which are specified in the Classpath tab
    List<String> extendedClasspath = new ArrayList<String>();
    Collections.addAll(extendedClasspath, super.getClasspath(configuration));

    // TODO mixed
    String projectName = configuration.getAttribute("project", "");
    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
    IBuildConfiguration[] buildConfigs = project.getBuildConfigs();
    IProject[] referencedProjects = project.getReferencedProjects();
    for (IProject iProject : referencedProjects) {
      Map<QualifiedName, String> persistentProperties = iProject.getPersistentProperties();
      IProjectNature nature = iProject.getNature("org.eclipse.jdt.core.javanature");
      if(nature !=null) {
        IJavaProject javaProject = JavaCore.create(iProject);
        IClasspathEntry[] rawClasspath = javaProject.getRawClasspath();
        IClasspathEntry[] referencedClasspathEntries = javaProject.getReferencedClasspathEntries();
        IClasspathEntry[] resolvedClasspath = javaProject.getResolvedClasspath(true);
        IPath readOutputLocation = javaProject.readOutputLocation();
        IClasspathEntry[] readRawClasspath = javaProject.readRawClasspath();
        IResource findMember = ResourcesPlugin.getWorkspace().getRoot().getFolder(readOutputLocation);
        
        extendedClasspath.add(findMember.getLocation().toPortableString());
        
      }
    }
    IScriptProject scriptProject = DLTKCore.create(project);
    IBuildpathEntry[] rawBuildpath = scriptProject.getRawBuildpath();
    IBuildpathEntry[] resolvedBuildpath = scriptProject.getResolvedBuildpath(true);
    
    
    Map attributes = configuration.getAttributes();
    
    // Normal mode, add the launcher plugin and uima runtime jar to the classpath
    if (!Platform.inDevelopmentMode()) {
      try {
        // Add this plugin jar to the classpath
        extendedClasspath.add(d.pluginIdToJarPath(TextMarkerIdePlugin.PLUGIN_ID));

        // UIMA jar should be added the end of the class path, because user uima jars
        // (maybe a different version) should appear first on the class path
        extendedClasspath.add(d.pluginIdToJarPath("org.apache.uima.runtime"));
        extendedClasspath.add(d.pluginIdToJarPath("org.apache.uima.textmarker.engine"));
      } catch (IOException e) {
        throw new CoreException(new Status(IStatus.ERROR, TextMarkerIdePlugin.PLUGIN_ID,
                IStatus.OK, "Failed to compose classpath!", e));
      }
    }
    // When running inside eclipse with PDE in development mode the plugins
    // are not installed inform of jar files and the classes must be loaded
    // from the target/classes folder or target/org.apache.uima.runtime.*.jar file
    else {
      try {
        // Add classes folder of this plugin to class path
        extendedClasspath.add(d.pluginIdToJarPath(TextMarkerIdePlugin.PLUGIN_ID) + "target/classes");

        // Add org.apache.uima.runtime jar to class path
        Bundle bundle = TextMarkerIdePlugin.getDefault().getBundle("org.apache.uima.runtime");

        // Ignore the case when runtime bundle does not exist ...
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

        // TODO

        // Add org.apache.uima.runtime jar to class path
        Bundle bundle2 = TextMarkerIdePlugin.getDefault().getBundle(
                "org.apache.uima.textmarker.engine");

        // Ignore the case when runtime bundle does not exist ...
        if (bundle2 != null) {
          Enumeration<?> jarEnum = bundle2.findEntries("/", "*.jar", true);
          while (jarEnum != null && jarEnum.hasMoreElements()) {
            URL element = (URL) jarEnum.nextElement();
            extendedClasspath.add(FileLocator.toFileURL(element).getFile());
          }
        }

      } catch (IOException e) {
        throw new CoreException(new Status(IStatus.ERROR, TextMarkerIdePlugin.PLUGIN_ID,
                IStatus.OK, "Failed to compose classpath!", e));
      }
    }

    return extendedClasspath.toArray(new String[extendedClasspath.size()]);
  }

  @Override
  public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch,
          IProgressMonitor monitor) throws CoreException {
    // clearOutputFolder();
    super.launch(configuration, mode, launch, monitor);
    while (!launch.isTerminated()) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        Thread.interrupted();
      }
    }
    IScriptProject proj = AbstractScriptLaunchConfigurationDelegate.getScriptProject(configuration);
    IPath projectPath = proj.getResource().getLocation();
    IPath outputDirPath = projectPath.append(TextMarkerProjectUtils.getDefaultOutputLocation());
    String outputFolderPath = configuration.getAttribute(
            TextMarkerLaunchConstants.ARG_OUTPUT_FOLDER, outputDirPath.toPortableString());
    if (outputFolderPath.length() != 0) {
      IPath path = Path.fromPortableString(outputFolderPath);
      IResource result = ResourcesPlugin.getWorkspace().getRoot().getContainerForLocation(path);
      if (result != null) {
        result.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
      }
    }
  }

  private void clearOutputFolder(File outputDir, boolean recursive) {
    IPreferenceStore store = TextMarkerIdePlugin.getDefault().getPreferenceStore();
    boolean clearOutput = store.getBoolean(TextMarkerCorePreferences.PROJECT_CLEAR_OUTPUT);
    if (clearOutput) {
      // TODO
      // List<File> outputFiles = getFiles(outputDir, recursive);
      // for (File file : outputFiles) {
      // file.delete();
      // }
      // outputFolder.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
    }
  }

  

}
