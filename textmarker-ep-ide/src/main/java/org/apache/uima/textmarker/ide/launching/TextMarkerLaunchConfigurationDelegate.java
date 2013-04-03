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
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.cas.CAS;
import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.apache.uima.textmarker.ide.core.TextMarkerCorePreferences;
import org.apache.uima.textmarker.ide.core.builder.TextMarkerProjectUtils;
import org.eclipse.core.resources.IFolder;
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
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaLaunchDelegate;
import org.eclipse.jface.preference.IPreferenceStore;
import org.osgi.framework.Bundle;

public class TextMarkerLaunchConfigurationDelegate extends JavaLaunchDelegate {

  private String mode;

  @Override
  public String getProgramArguments(ILaunchConfiguration configuration) throws CoreException {
    StringBuilder cmdline = new StringBuilder();
    IScriptProject proj = AbstractScriptLaunchConfigurationDelegate.getScriptProject(configuration);

    String mainScriptAttribute = configuration.getAttribute("mainScript", "");
    
    String encoding = proj.getProject().getDefaultCharset();
    String view = configuration.getAttribute(TextMarkerLaunchConstants.VIEW, CAS.NAME_DEFAULT_SOFA);
    if(StringUtils.isBlank(view)) {
      view = CAS.NAME_DEFAULT_SOFA;
    }
    boolean recursive = configuration.getAttribute(TextMarkerLaunchConstants.RECURSIVE, false);

    IResource member = proj.getProject().findMember(mainScriptAttribute);
    IPath projectPath = proj.getResource().getLocation();
    IPath inputDirPath = projectPath.append(TextMarkerProjectUtils.getDefaultInputLocation());
    IPath outputDirPath = projectPath.append(TextMarkerProjectUtils.getDefaultOutputLocation());
    String engine = TextMarkerProjectUtils.getEngineDescriptorPath(member.getLocation(),
            proj.getProject()).toPortableString();
    String input = configuration.getAttribute(TextMarkerLaunchConstants.INPUT_FOLDER,
            inputDirPath.toPortableString());
    if(StringUtils.isBlank(input)) {
      input = inputDirPath.toPortableString();
    }
    String output = configuration.getAttribute(TextMarkerLaunchConstants.OUTPUT_FOLDER,
            outputDirPath.toPortableString());
    if(StringUtils.isBlank(output)) {
      output = outputDirPath.toPortableString();
    }
    
    cmdline.append(TextMarkerLaunchConstants.ARG_DESCRIPTOR + " ");
    cmdline.append(engine + " ");

    cmdline.append(TextMarkerLaunchConstants.ARG_INPUT_FOLDER + " ");
    cmdline.append(makeAbsolute(input, configuration) + " ");

    cmdline.append(TextMarkerLaunchConstants.ARG_OUTPUT_FOLDER + " ");
    cmdline.append(makeAbsolute(output, configuration) + " ");

    cmdline.append(TextMarkerLaunchConstants.ARG_MODE + " ");
    cmdline.append(mode + " ");

    cmdline.append(TextMarkerLaunchConstants.ARG_ENCODING + " ");
    cmdline.append(encoding + " ");

    cmdline.append(TextMarkerLaunchConstants.ARG_VIEW + " ");
    cmdline.append(view + " ");

    cmdline.append(TextMarkerLaunchConstants.ARG_RECURSIVE + " ");
    cmdline.append(recursive + " ");

    return cmdline.toString();
  }

  private String makeAbsolute(String input, ILaunchConfiguration configuration) throws CoreException {
    IResource member = ResourcesPlugin.getWorkspace().getRoot().findMember(input);
    if(member != null) {
      return member.getLocation().toPortableString();
    }
    return input;
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

    IScriptProject scriptProject = AbstractScriptLaunchConfigurationDelegate.getScriptProject(configuration);
    IProject[] referencedProjects = scriptProject.getProject().getReferencedProjects();
    for (IProject eachProject : referencedProjects) {
      IProjectNature nature = eachProject.getNature(TextMarkerProjectUtils.JAVANATURE);
      if(nature != null) {
        IJavaProject javaProject = JavaCore.create(eachProject);
        IPath readOutputLocation = javaProject.readOutputLocation();
        IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(readOutputLocation);
        extendedClasspath.add(folder.getLocation().toPortableString());
      }
    }
    
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
        extendedClasspath
                .add(d.pluginIdToJarPath(TextMarkerIdePlugin.PLUGIN_ID) + "target/classes");
        Bundle bundle = TextMarkerIdePlugin.getDefault().getBundle("org.apache.uima.runtime");
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

        Bundle bundle2 = TextMarkerIdePlugin.getDefault().getBundle(
                "org.apache.uima.textmarker.engine");
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
    this.mode = mode;
    IResource ouputFolder = null;
    IScriptProject proj = AbstractScriptLaunchConfigurationDelegate.getScriptProject(configuration);
    IPath projectPath = proj.getResource().getLocation();
    IPath outputDirPath = projectPath.append(TextMarkerProjectUtils.getDefaultOutputLocation());
    String outputFolderPath = configuration.getAttribute(
            TextMarkerLaunchConstants.ARG_OUTPUT_FOLDER, outputDirPath.toPortableString());
    if (outputFolderPath.length() != 0) {
      IPath path = Path.fromPortableString(outputFolderPath);
      ouputFolder = ResourcesPlugin.getWorkspace().getRoot().getContainerForLocation(path);
    }
    boolean recursive = configuration.getAttribute(TextMarkerLaunchConstants.RECURSIVE, false);
    clearOutputFolder(new File(ouputFolder.getLocation().toPortableString()), recursive);
    
//    String[] args = getProgramArguments(configuration).split(" ");
//    try {
//      TextMarkerLauncher.main(args);
//    } catch (Exception e1) {
//      e1.printStackTrace();
//    }
    
    super.launch(configuration, mode, launch, monitor);
    
    while (!launch.isTerminated()) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        Thread.interrupted();
      }
    }

    if (ouputFolder != null) {
      ouputFolder.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
    }
  }

  private void clearOutputFolder(File outputDir, boolean recursive) {
    IPreferenceStore store = TextMarkerIdePlugin.getDefault().getPreferenceStore();
    boolean clearOutput = store.getBoolean(TextMarkerCorePreferences.PROJECT_CLEAR_OUTPUT);
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
