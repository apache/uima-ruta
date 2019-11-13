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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.uima.ruta.descriptor.RutaBuildOptions;
import org.apache.uima.ruta.descriptor.RutaDescriptorBuilder;
import org.apache.uima.ruta.descriptor.RutaDescriptorInformation;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.extensions.IRutaActionExtension;
import org.apache.uima.ruta.extensions.IRutaBlockExtension;
import org.apache.uima.ruta.extensions.IRutaBooleanFunctionExtension;
import org.apache.uima.ruta.extensions.IRutaConditionExtension;
import org.apache.uima.ruta.extensions.IRutaNumberFunctionExtension;
import org.apache.uima.ruta.extensions.IRutaStringFunctionExtension;
import org.apache.uima.ruta.extensions.IRutaTypeFunctionExtension;
import org.apache.uima.ruta.ide.RutaIdeCorePlugin;
import org.apache.uima.ruta.ide.core.RutaCorePreferences;
import org.apache.uima.ruta.ide.core.RutaExtensionManager;
import org.apache.uima.ruta.ide.parser.ast.RutaModuleDeclaration;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.builder.AbstractBuildParticipantType;
import org.eclipse.dltk.core.builder.IBuildChange;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.dltk.core.builder.IBuildParticipantExtension;
import org.eclipse.dltk.core.builder.IBuildParticipantExtension2;
import org.eclipse.dltk.core.builder.IBuildParticipantExtension3;
import org.eclipse.dltk.core.builder.IBuildState;
import org.eclipse.jface.preference.IPreferenceStore;

public class RutaBuilder extends AbstractBuildParticipantType implements IBuildParticipant,
        IBuildParticipantExtension, IBuildParticipantExtension2, IBuildParticipantExtension3 {

  @SuppressWarnings("unused")
  private IScriptProject project;

  public RutaBuilder() {
    super();
  }

  public RutaBuilder(IScriptProject project) {
    super();
    this.project = project;
  }

  private void generateDescriptorResources(ISourceModule sourceModule,
          ModuleDeclaration moduleDeclaration) {
    IProgressMonitor monitor = createMonitor(10);
    try {
      IContainer container = getContainer(sourceModule);

      IPath outputPath = getAbsolutePath(sourceModule);
      @SuppressWarnings("unused")
      final IPath[] generateResources = generateResources(moduleDeclaration, outputPath, container,
              sourceModule);

      IProject proj = sourceModule.getScriptProject().getProject();
      List<IFolder> allDescriptorFolders = RutaProjectUtils.getAllDescriptorFolders(proj);
      for (IFolder iFolder : allDescriptorFolders) {
        RutaProjectUtils.addProjectDataPath(proj, iFolder);
      }

      monitor.worked(2);
      // refreshing deactivated in context of UIMA-5669
      String defaultDescriptorLocation = RutaProjectUtils.getDefaultDescriptorLocation();
      final IFolder folder = container.getProject().getFolder(defaultDescriptorLocation);

//      for (IPath iPath : generateResources) {
//        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
//        IFile[] files = root.findFilesForLocationURI(iPath.toFile().toURI());
//        for (IFile file : files) {
//          file.refreshLocal(IResource.DEPTH_ONE, new NullProgressMonitor());
//        }
//
//        IResource findMember2 = root.findMember(iPath);
//
//        iPath = iPath.makeRelativeTo(folder.getLocation());
//        IResource findMember = folder.findMember(iPath);
//        if (findMember != null) {
//          findMember.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
//          findMember.getParent().refreshLocal(IResource.DEPTH_ONE, new NullProgressMonitor());
//        }
//
//      }

      Job refreshJob = new Job("Refreshing generated descriptors") {
        @Override
        public boolean belongsTo(Object family) {
          return family == ResourcesPlugin.FAMILY_MANUAL_REFRESH;
        }

        @Override
        protected IStatus run(IProgressMonitor pm) {
          try {
            folder.refreshLocal(IResource.DEPTH_INFINITE,
                    new SubProgressMonitor(monitor, generateResources.length));
          } catch (CoreException e) {
            return e.getStatus();
          }
          return Status.OK_STATUS;
        }
      };
      refreshJob.schedule();

      // monitor.worked(1);
    } catch (ModelException e) {
      if (DLTKCore.DEBUG_PARSER) {
        RutaIdeCorePlugin.error(e);
      }
    } catch (CoreException e) {
      if (DLTKCore.DEBUG_PARSER) {
        RutaIdeCorePlugin.error(e);
      }
    }
    monitor.done();
  }

  private IPath[] generateResources(ModuleDeclaration moduleDeclaration, IPath outputPath,
          IContainer container, ISourceModule sourceModule) throws CoreException {
    List<IPath> result = new ArrayList<IPath>();
    if (moduleDeclaration instanceof RutaModuleDeclaration) {
      RutaModuleDeclaration tmmd = (RutaModuleDeclaration) moduleDeclaration;
      RutaDescriptorInformation sm = tmmd.descriptorInfo;
      IPath pathToModule = sourceModule.getResource().getLocation();
      String elementName = RutaProjectUtils.getModuleName(pathToModule);

      IScriptProject scriptProject = sourceModule.getScriptProject();

      IProject project = scriptProject.getProject();

      IPath descPath = RutaProjectUtils.getDescriptorRootPath(project);
      String basicTS = descPath.append("BasicTypeSystem.xml").toPortableString();
      String basicE = descPath.append("BasicEngine.xml").toPortableString();

      List<IFolder> descriptorFolders = RutaProjectUtils.getDescriptorFolders(project);
      for (IFolder iFolder : descriptorFolders) {
        File btsd = iFolder.getLocation().append("BasicTypeSystem.xml").toFile();
        File baed = iFolder.getLocation().append("BasicEngine.xml").toFile();
        if (btsd.exists() && baed.exists()) {
          basicTS = btsd.getAbsolutePath();
          basicE = baed.getAbsolutePath();
          descPath = iFolder.getLocation();
          break;
        }
      }

      IPath relativePackagePath = RutaProjectUtils
              .getPackagePath(sourceModule.getResource().getLocation(), project);
      IPath descPackagePath = descPath.append(relativePackagePath);
      String typeSystem = descPackagePath
              .append(elementName + RutaProjectUtils.getTypeSystemSuffix(project) + ".xml")
              .toPortableString();
      String engine = descPackagePath
              .append(elementName + RutaProjectUtils.getAnalysisEngineSuffix(project) + ".xml")
              .toPortableString();

      String scriptWithPackagePath = RutaProjectUtils.getScriptWithPackage(pathToModule, project);
      List<String> scriptPathList = new ArrayList<String>();
      List<String> descriptorPathList = new ArrayList<String>();
      List<String> resourcePathList = new ArrayList<String>();

      // add all folders
      try {
        List<IFolder> scriptFolders = RutaProjectUtils.getAllScriptFolders(scriptProject);
        scriptPathList.addAll(RutaProjectUtils.getFolderLocations(scriptFolders));
      } catch (CoreException e) {
        RutaIdeCorePlugin.error(e);
      }

      try {
        List<IFolder> allDescriptorFolders = RutaProjectUtils.getAllDescriptorFolders(project);
        descriptorPathList.addAll(RutaProjectUtils.getFolderLocations(allDescriptorFolders));
      } catch (Exception e) {
        RutaIdeCorePlugin.error(e);
      }

      try {
        List<IFolder> allResourceFolders = RutaProjectUtils.getAllResourceFolders(project);
        resourcePathList.addAll(RutaProjectUtils.getFolderLocations(allResourceFolders));
      } catch (Exception e) {
        RutaIdeCorePlugin.error(e);
      }

      String[] descriptorPaths = descriptorPathList.toArray(new String[0]);
      String[] scriptPaths = scriptPathList.toArray(new String[0]);
      String[] resourcePaths = resourcePathList.toArray(new String[0]);

      String mainScript = scriptWithPackagePath;
      mainScript = mainScript.replaceAll("/", ".");
      if (mainScript.endsWith(RutaEngine.SCRIPT_FILE_EXTENSION)) {
        mainScript = mainScript.substring(0, mainScript.length() - 5);
      }
      Collection<String> dependencies = RutaProjectUtils.getClassPath(project);
      URL[] urls = new URL[dependencies.size()];
      int counter = 0;
      for (String dep : dependencies) {
        try {
          urls[counter] = new File(dep).toURI().toURL();
        } catch (MalformedURLException e) {
          throw new CoreException(
                  new Status(IStatus.ERROR, RutaIdeCorePlugin.PLUGIN_ID, e.getMessage()));
        }
        counter++;
      }
      ClassLoader classloader = new URLClassLoader(urls);
      build(basicTS, basicE, typeSystem, engine, sm, scriptPaths, descriptorPaths, resourcePaths,
              classloader);

      IPath tsPath = Path.fromPortableString(typeSystem);
      IPath ePath = Path.fromPortableString(engine);
      result.add(tsPath);
      result.add(ePath);
    }
    return result.toArray(new IPath[0]);
  }

  private void build(String basicTypesystem, String basicEngine, String typeSystemDest,
          String engineDest, RutaDescriptorInformation sm, String[] scriptPaths,
          String[] enginePaths, String[] resourcePaths, ClassLoader classloader) {
    RutaDescriptorBuilder builder = null;
    try {
      URL tsUrl = new File(basicTypesystem).toURI().toURL();
      URL aeUrl = new File(basicEngine).toURI().toURL();
      builder = new RutaDescriptorBuilder(tsUrl, aeUrl);
    } catch (Exception e) {
      DLTKCore.error(e.getMessage(), e);
      if (DLTKCore.DEBUG_PARSER) {
        e.printStackTrace();
      }
    }

    IRutaConditionExtension[] conditionExtensions = RutaExtensionManager.getDefault()
            .getRutaConditionExtensions();
    IRutaActionExtension[] actionExtensions = RutaExtensionManager.getDefault()
            .getRutaActionExtensions();
    IRutaBooleanFunctionExtension[] booleanFunctionExtensions = RutaExtensionManager.getDefault()
            .getRutaBooleanFunctionExtensions();
    IRutaNumberFunctionExtension[] numberFunctionExtensions = RutaExtensionManager.getDefault()
            .getRutaNumberFunctionExtensions();
    IRutaStringFunctionExtension[] stringFunctionExtensions = RutaExtensionManager.getDefault()
            .getRutaStringFunctionExtensions();
    IRutaTypeFunctionExtension[] typeFunctionExtensions = RutaExtensionManager.getDefault()
            .getRutaTypeFunctionExtensions();
    IRutaBlockExtension[] blockExtensions = RutaExtensionManager.getDefault()
            .getRutaBlockExtensions();

    List<String> language = new ArrayList<String>();

    for (IRutaConditionExtension each : conditionExtensions) {
      language.add(each.getClass().getName());
    }
    for (IRutaActionExtension each : actionExtensions) {
      language.add(each.getClass().getName());
    }
    for (IRutaBooleanFunctionExtension each : booleanFunctionExtensions) {
      language.add(each.getClass().getName());
    }
    for (IRutaNumberFunctionExtension each : numberFunctionExtensions) {
      language.add(each.getClass().getName());
    }
    for (IRutaStringFunctionExtension each : stringFunctionExtensions) {
      language.add(each.getClass().getName());
    }
    for (IRutaTypeFunctionExtension each : typeFunctionExtensions) {
      language.add(each.getClass().getName());
    }
    for (IRutaBlockExtension each : blockExtensions) {
      language.add(each.getClass().getName());
    }

    try {
      RutaBuildOptions option = new RutaBuildOptions();
      option.setLanguageExtensions(language);
      IPreferenceStore store = RutaIdeCorePlugin.getDefault().getPreferenceStore();
      option.setImportByName(store.getBoolean(RutaCorePreferences.BUILDER_IMPORT_BY_NAME));
      option.setResolveImports(store.getBoolean(RutaCorePreferences.BUILDER_RESOLVE_IMPORTS));
      option.setClassLoader(classloader);
      builder.build(sm, typeSystemDest, engineDest, option, scriptPaths, enginePaths,
              resourcePaths);
    } catch (Exception e) {
      DLTKCore.error(e.getMessage(), e);
      if (DLTKCore.DEBUG_PARSER) {
        e.printStackTrace();
      }
    }
  }

  private IProgressMonitor createMonitor(int totalWork) {
    IProgressMonitor pm = new NullProgressMonitor();
    pm.beginTask("Creating descriptors ", totalWork);
    return pm;
  }

  public static IContainer getContainer(ISourceModule sourceModule) {
    try {
      IResource file = sourceModule.getCorrespondingResource();
      return file.getParent();
    } catch (ModelException e) {
      if (DLTKCore.DEBUG_PARSER) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public static IPath getAbsolutePath(ISourceModule sourceModule) {
    try {
      IResource file = sourceModule.getCorrespondingResource();
      IPath absolutePath = file.getRawLocation().removeLastSegments(1);
      return absolutePath;
    } catch (ModelException e) {
      if (DLTKCore.DEBUG_PARSER) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public static IPath getRelativePath(ISourceModule sourceModule, String resourceName) {
    IContainer parent = getContainer(sourceModule);
    IPath relativePath = parent.getFullPath();
    IPath relativeFilePath = relativePath.append(resourceName);
    return relativeFilePath;
  }

  @Override
  public void clean() {
  }

  @Override
  public void prepare(IBuildChange buildChange, IBuildState buildState) throws CoreException {
  }

  @Override
  public void buildExternalModule(IBuildContext context) throws CoreException {

  }

  @Override
  public boolean beginBuild(int buildType) {
    return buildType != RECONCILE_BUILD;
  }

  @Override
  public void endBuild(IProgressMonitor monitor) {

  }

  @Override
  public void build(IBuildContext context) throws CoreException {
    final ModuleDeclaration ast = (ModuleDeclaration) context
            .get(IBuildContext.ATTR_MODULE_DECLARATION);
    ISourceModule sourceModule = context.getSourceModule();
    generateDescriptorResources(sourceModule, ast);
  }

  @Override
  public IBuildParticipant createBuildParticipant(IScriptProject project) throws CoreException {
    return new RutaBuilder(project);
  }
}
