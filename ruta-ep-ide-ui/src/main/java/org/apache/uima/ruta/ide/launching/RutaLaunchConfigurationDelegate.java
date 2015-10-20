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
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.ide.RutaIdeCorePlugin;
import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.apache.uima.ruta.ide.core.RutaCorePreferences;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.eclipse.core.internal.resources.Folder;
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
    IPath analysisEngineDescriptorPath = RutaProjectUtils.getAnalysisEngineDescriptorPath(
            member.getLocation(), proj.getProject());
    String engineDefaultMethod = "";
    if (analysisEngineDescriptorPath != null) {
      engineDefaultMethod = analysisEngineDescriptorPath.toPortableString();
    }
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
    String engine = configuration.getAttribute(RutaLaunchConstants.ARG_DESCRIPTOR,
            engineDefaultMethod);
    if (StringUtils.isBlank(engine)) {
      engine = engineDefaultMethod;
    }

    try {
      cmdline.append(RutaLaunchConstants.ARG_DESCRIPTOR + " ");
      cmdline.append(URLEncoder.encode(engine, RutaLauncher.URL_ENCODING) + " ");

      cmdline.append(RutaLaunchConstants.ARG_INPUT_FOLDER + " ");
      cmdline.append(URLEncoder.encode(makeAbsolute(input, configuration),
              RutaLauncher.URL_ENCODING) + " ");

      cmdline.append(RutaLaunchConstants.ARG_OUTPUT_FOLDER + " ");
      cmdline.append(URLEncoder.encode(makeAbsolute(output, configuration),
              RutaLauncher.URL_ENCODING) + " ");

    } catch (UnsupportedEncodingException e) {
      throw new CoreException(new Status(IStatus.ERROR, RutaIdeUIPlugin.PLUGIN_ID,
              "Unsupported Encoding"));
    }
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

    IProjectNature m2eNature = project.getProject().getNature(RutaProjectUtils.M2E_NATURE);
    IProjectNature javaNature = project.getProject().getNature(RutaProjectUtils.JAVA_NATURE);

    // deactivated until launcher issue is solved
    if (m2eNature != null && javaNature != null) {
      // maven dependencies only
      Collection<String> classPath = RutaProjectUtils.getClassPath(project.getProject());
      extendedClasspath.addAll(classPath);
      // IDE UI for launching
      try {
        if (!Platform.inDevelopmentMode()) {
          // Add this plugin jar to the classpath
          extendedClasspath.add(d.pluginIdToJarPath(RutaIdeUIPlugin.PLUGIN_ID));
        } else {
          extendedClasspath.add(d.pluginIdToJarPath(RutaIdeUIPlugin.PLUGIN_ID) + "target/classes");
        }
      } catch (IOException e) {
        throw new CoreException(new Status(IStatus.ERROR, RutaIdeUIPlugin.PLUGIN_ID, IStatus.OK,
                "Failed to compose classpath!", e));
      }
    } else {
      // old fashioned mode: use the bundles and check development mode
      try {
        // Normal mode, add the launcher plugin and uima runtime jar to the classpath
        if (!Platform.inDevelopmentMode()) {
          // Add this plugin jar to the classpath
          extendedClasspath.add(d.pluginIdToJarPath(RutaIdeUIPlugin.PLUGIN_ID));
        } else {
          extendedClasspath.add(d.pluginIdToJarPath(RutaIdeUIPlugin.PLUGIN_ID) + "target/classes");
        }

        // uima
        Bundle bundle = RutaIdeUIPlugin.getDefault().getBundle("org.apache.uima.runtime");
        if (bundle != null) {
          Enumeration<?> jarEnum = bundle.findEntries("/", "uimaj-core*.jar",
                  Platform.inDevelopmentMode());
          while (jarEnum != null && jarEnum.hasMoreElements()) {
            URL element = (URL) jarEnum.nextElement();
            extendedClasspath.add(FileLocator.toFileURL(element).getFile());
          }
        }
        extendedClasspath.add(d.pluginIdToJarPath("org.apache.uima.runtime"));

        // ruta
        bundle = RutaIdeUIPlugin.getDefault().getBundle("org.apache.uima.ruta.engine");
        if (bundle != null) {
          Enumeration<?> jarEnum = bundle.findEntries("/", "*.jar", Platform.inDevelopmentMode());
          while (jarEnum != null && jarEnum.hasMoreElements()) {
            URL element = (URL) jarEnum.nextElement();
            extendedClasspath.add(FileLocator.toFileURL(element).getFile());
          }
        }
        extendedClasspath.add(d.pluginIdToJarPath("org.apache.uima.ruta.engine"));
      } catch (IOException e) {
        throw new CoreException(new Status(IStatus.ERROR, RutaIdeUIPlugin.PLUGIN_ID, IStatus.OK,
                "Failed to compose classpath!", e));
      }

      Collection<String> classPath = RutaProjectUtils.getClassPath(project.getProject());
      extendedClasspath.addAll(classPath);

      Collection<String> extensions = getExtensions();
      extendedClasspath.addAll(extensions);

    }

    return extendedClasspath;
  }

  private static Collection<String> getExtensions() throws CoreException {
    RutaIdeUIPlugin d = RutaIdeUIPlugin.getDefault();
    Collection<String> result = new TreeSet<String>();
    IExtension[] extensions = null;
    extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaIdeCorePlugin.PLUGIN_ID, "actionExtension").getExtensions();
    extensionToClassPath(d, result, extensions);
    extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaIdeCorePlugin.PLUGIN_ID, "conditionExtension").getExtensions();
    extensionToClassPath(d, result, extensions);
    extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaIdeCorePlugin.PLUGIN_ID, "booleanFunctionExtension")
            .getExtensions();
    extensionToClassPath(d, result, extensions);
    extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaIdeCorePlugin.PLUGIN_ID, "numberFunctionExtension")
            .getExtensions();
    extensionToClassPath(d, result, extensions);
    extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaIdeCorePlugin.PLUGIN_ID, "stringFunctionExtension")
            .getExtensions();
    extensionToClassPath(d, result, extensions);
    extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaIdeCorePlugin.PLUGIN_ID, "typeFunctionExtension")
            .getExtensions();
    extensionToClassPath(d, result, extensions);
    return result;
  }

  private static void extensionToClassPath(RutaIdeUIPlugin d, Collection<String> result,
          IExtension[] extensions) throws CoreException {
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
  }

  @Override
  public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch,
          IProgressMonitor monitor) throws CoreException {
    this.mode = mode;
    IResource ouputFolder = null;
    IScriptProject proj = AbstractScriptLaunchConfigurationDelegate.getScriptProject(configuration);
    IPath projectPath = proj.getResource().getLocation();
    IPath outputDirPath = projectPath.append(RutaProjectUtils.getDefaultOutputLocation());
    String outputFolderPath = configuration.getAttribute(RutaLaunchConstants.ARG_OUTPUT_FOLDER, "");
    if (StringUtils.isBlank(outputFolderPath)) {
      outputFolderPath = configuration.getAttribute(RutaLaunchConstants.OUTPUT_FOLDER,
              outputDirPath.toPortableString());
    }
    if (outputFolderPath.length() != 0) {
      IPath path = Path.fromPortableString(outputFolderPath);
      IResource member = ResourcesPlugin.getWorkspace().getRoot().findMember(path);
      if (member instanceof Folder) {
        ouputFolder = member;
      } else {
        ouputFolder = ResourcesPlugin.getWorkspace().getRoot().getContainerForLocation(path);
      }
    }
    boolean recursive = configuration.getAttribute(RutaLaunchConstants.RECURSIVE, false);
    if (ouputFolder != null) {
      clearOutputFolder(new File(ouputFolder.getLocation().toPortableString()), recursive);
    }

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
