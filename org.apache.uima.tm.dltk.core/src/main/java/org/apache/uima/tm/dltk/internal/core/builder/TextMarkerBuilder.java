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

package org.apache.uima.tm.dltk.internal.core.builder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.dltk.core.TextMarkerCorePreferences;
import org.apache.uima.tm.dltk.core.TextMarkerPlugin;
import org.apache.uima.tm.dltk.internal.core.TextMarkerExtensionManager;
import org.apache.uima.tm.dltk.parser.ast.TextMarkerModuleDeclaration;
import org.apache.uima.tm.textmarker.kernel.extensions.IEngineLoader;
import org.apache.uima.tm.textmarker.kernel.extensions.ITextMarkerActionExtension;
import org.apache.uima.tm.textmarker.kernel.extensions.ITextMarkerBooleanFunctionExtension;
import org.apache.uima.tm.textmarker.kernel.extensions.ITextMarkerConditionExtension;
import org.apache.uima.tm.textmarker.kernel.extensions.ITextMarkerNumberFunctionExtension;
import org.apache.uima.tm.textmarker.kernel.extensions.ITextMarkerStringFunctionExtension;
import org.apache.uima.tm.textmarker.kernel.extensions.ITextMarkerTypeFunctionExtension;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.dltk.internal.core.builder.BuildProblemReporter;
import org.eclipse.dltk.internal.core.builder.Messages;
import org.eclipse.dltk.internal.core.builder.SourceModuleBuildContext;
import org.eclipse.dltk.internal.core.builder.StandardScriptBuilder;


public class TextMarkerBuilder extends StandardScriptBuilder {

  private IBuildParticipant[] participants = null;

  private IDLTKLanguageToolkit toolkit = null;

  public TextMarkerBuilder() {
    super();// m
  }

  @Override
  @SuppressWarnings("unchecked")
  public IStatus buildModelElements(IScriptProject project, List elements,
          IProgressMonitor monitor, int status) {
    List<ISourceModule> sources = elements;
    for (ISourceModule sourceModule : sources) {
      ModuleDeclaration moduleDeclaration = SourceParserUtil.getModuleDeclaration(sourceModule,
              null);
      generateDescriptorResources(sourceModule, moduleDeclaration, monitor);
      // callParticipants(project, moduleDeclaration);
      initialize(project);
      final SourceModuleBuildContext context = new SourceModuleBuildContext(sourceModule, status); // guessed
      context.set(IBuildContext.ATTR_MODULE_DECLARATION, moduleDeclaration);
      buildModule(context);
      BuildProblemReporter bpr = (BuildProblemReporter) context.getProblemReporter();
      bpr.flush();
    }
    return super.buildModelElements(project, elements, monitor, status);
  }

  private void buildModule(IBuildContext context) {
    if (participants != null) {
      for (int k = 0; k < participants.length; ++k) {
        final IBuildParticipant participant = participants[k];
        try {
          participant.build(context);
        } catch (CoreException e) {
          DLTKCore.error(Messages.StandardScriptBuilder_errorBuildingModule, e);
        }
      }
    }
  }

  private void generateDescriptorResources(ISourceModule sourceModule,
          ModuleDeclaration moduleDeclaration, IProgressMonitor monitor) {
    SubProgressMonitor subProgressMonitor = createMonitor(monitor, 10);
    try {
      IContainer container = getContainer(sourceModule);

      IPath outputPath = getAbsolutePath(sourceModule);
      IPath[] generateResources = generateResources(moduleDeclaration, outputPath, container,
              sourceModule);
      subProgressMonitor.worked(2);
      String defaultDescriptorLocation = TextMarkerProjectUtils.getDefaultDescriptorLocation();
      IFolder folder = container.getProject().getFolder(defaultDescriptorLocation);
      for (IPath iPath : generateResources) {
        iPath = iPath.makeRelativeTo(folder.getLocation());
        IResource findMember = folder.findMember(iPath);
        if (findMember != null) {
          findMember.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
        }

      }
      folder.refreshLocal(IResource.DEPTH_INFINITE, new SubProgressMonitor(subProgressMonitor,
              generateResources.length));

      subProgressMonitor.worked(1);
    } catch (ModelException e) {
      if (DLTKCore.DEBUG_PARSER) {
        e.printStackTrace();
      }
    } catch (CoreException e) {
      if (DLTKCore.DEBUG_PARSER) {
        e.printStackTrace();
      }
    }
    subProgressMonitor.done();
  }

  private IPath[] generateResources(ModuleDeclaration moduleDeclaration, IPath outputPath,
          IContainer container, ISourceModule sourceModule) {
    List<IPath> result = new ArrayList<IPath>();
    if (moduleDeclaration instanceof TextMarkerModuleDeclaration) {
      TextMarkerModuleDeclaration tmmd = (TextMarkerModuleDeclaration) moduleDeclaration;
      DescriptorManager sm = tmmd.descriptorInfo;
      IPath pathToModule = sourceModule.getResource().getLocation();
      String elementName = TextMarkerProjectUtils.getModuleName(pathToModule);

      IScriptProject proj = sourceModule.getScriptProject();
      // TODO: dont use desc path!
      IPath descPath = TextMarkerProjectUtils.getDescriptorRootPath(proj.getProject());
      IPath scriptPath = TextMarkerProjectUtils.getScriptRootPath(proj.getProject());
      IPath descPackagePath = TextMarkerProjectUtils.getDescriptorPackagePath(sourceModule
              .getResource().getLocation(), proj.getProject());

      String typeSystem = descPackagePath.append(elementName + "TypeSystem.xml").toPortableString();
      String engine = descPackagePath.append(elementName + "Engine.xml").toPortableString();
      String basicTS = descPath.append("BasicTypeSystem.xml").toPortableString();
      String basicE = descPath.append("BasicEngine.xml").toPortableString();

      // TODO: may not work with other script folders
      IPath relativeTo = pathToModule.makeAbsolute().makeRelativeTo(scriptPath);
      List<String> scriptPathList = new ArrayList<String>();
      List<String> descriptorPathList = new ArrayList<String>();

      // add all folders
      try {
        List<IFolder> scriptFolders = TextMarkerProjectUtils.getAllScriptFolders(proj);
        scriptPathList.addAll(TextMarkerProjectUtils.getFolderLocations(scriptFolders));
      } catch (CoreException e) {
        TextMarkerPlugin.error(e);
      }

      try {
        List<IFolder> descriptorFolders = TextMarkerProjectUtils.getAllDescriptorFolders(proj
                .getProject());
        descriptorPathList.addAll(TextMarkerProjectUtils.getFolderLocations(descriptorFolders));
      } catch (CoreException e) {
        TextMarkerPlugin.error(e);
      }

      String[] descriptorPaths = descriptorPathList.toArray(new String[0]);
      String[] scriptPaths = scriptPathList.toArray(new String[0]);
      String mainScript = relativeTo.toPortableString();
      mainScript = mainScript.replaceAll("/", ".");
      if (mainScript.endsWith(".tm")) {
        mainScript = mainScript.substring(0, mainScript.length() - 3);
      }
      build(basicTS, basicE, typeSystem, engine, sm, mainScript, scriptPaths, descriptorPaths);

      IPath tsPath = Path.fromPortableString(typeSystem);
      IPath ePath = Path.fromPortableString(engine);
      result.add(tsPath);
      result.add(ePath);
    }
    return result.toArray(new IPath[0]);
  }

  private void build(String basicTypesystem, String basicEngine, String typeSystemDest,
          String engineDest, DescriptorManager sm, String mainScript, String[] scriptPaths,
          String[] enginePaths) {
    TextMarkerSimpleBuilder builder = null;
    try {
      builder = new TextMarkerSimpleBuilder(basicTypesystem, basicEngine);
    } catch (Exception e) {
      DLTKCore.error(e.getMessage(), e);
      if (DLTKCore.DEBUG_PARSER) {
        e.printStackTrace();
      }
    }

    ITextMarkerConditionExtension[] conditionExtensions = TextMarkerExtensionManager.getDefault()
            .getTextMarkerConditionExtensions();
    ITextMarkerActionExtension[] actionExtensions = TextMarkerExtensionManager.getDefault()
            .getTextMarkerActionExtensions();
    ITextMarkerBooleanFunctionExtension[] booleanFunctionExtensions = TextMarkerExtensionManager
            .getDefault().getTextMarkerBooleanFunctionExtensions();
    ITextMarkerNumberFunctionExtension[] numberFunctionExtensions = TextMarkerExtensionManager
            .getDefault().getTextMarkerNumberFunctionExtensions();
    ITextMarkerStringFunctionExtension[] stringFunctionExtensions = TextMarkerExtensionManager
            .getDefault().getTextMarkerStringFunctionExtensions();
    ITextMarkerTypeFunctionExtension[] typeFunctionExtensions = TextMarkerExtensionManager
            .getDefault().getTextMarkerTypeFunctionExtensions();
    IEngineLoader[] engineExtensions = TextMarkerExtensionManager.getDefault()
            .getEngineExtensions();

    List<String> language = new ArrayList<String>();
    List<String> engines = new ArrayList<String>();

    for (ITextMarkerConditionExtension each : conditionExtensions) {
      language.add(each.getClass().getName());
    }
    for (ITextMarkerActionExtension each : actionExtensions) {
      language.add(each.getClass().getName());
    }
    for (ITextMarkerBooleanFunctionExtension each : booleanFunctionExtensions) {
      language.add(each.getClass().getName());
    }
    for (ITextMarkerNumberFunctionExtension each : numberFunctionExtensions) {
      language.add(each.getClass().getName());
    }
    for (ITextMarkerStringFunctionExtension each : stringFunctionExtensions) {
      language.add(each.getClass().getName());
    }
    for (ITextMarkerTypeFunctionExtension each : typeFunctionExtensions) {
      language.add(each.getClass().getName());
    }
    for (IEngineLoader each : engineExtensions) {
      engines.add(each.getClass().getName());
    }

    try {
      TextMarkerBuildOptions option = new TextMarkerBuildOptions(language, engines);
      Preferences store = TextMarkerPlugin.getDefault().getPluginPreferences();
      option.setImportByName(store.getBoolean(TextMarkerCorePreferences.BUILDER_IMPORT_BY_NAME));
      option.setResolveImports(store.getBoolean(TextMarkerCorePreferences.BUILDER_RESOLVE_IMPORTS));
      builder.build(sm, typeSystemDest, engineDest, option, mainScript, scriptPaths, enginePaths);
    } catch (Exception e) {
      DLTKCore.error(e.getMessage(), e);
      if (DLTKCore.DEBUG_PARSER) {
        e.printStackTrace();
      }
    }
  }

  private SubProgressMonitor createMonitor(IProgressMonitor monitor, int totalWork) {
    SubProgressMonitor subProgressMonitor = new SubProgressMonitor(monitor, 500);
    subProgressMonitor.beginTask("Creating descriptors ", totalWork);
    return subProgressMonitor;
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

  private void copy(File source, File target) throws FileNotFoundException {
    copy(new FileInputStream(source), new FileOutputStream(target));
  }

  private void copy(InputStream fis, OutputStream fos) {
    try {
      byte[] buffer = new byte[0xFFFF];
      for (int len; (len = fis.read(buffer)) != -1;)
        fos.write(buffer, 0, len);
    } catch (IOException e) {
      System.err.println(e);
    } finally {
      if (fis != null)
        try {
          fis.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      if (fos != null)
        try {
          fos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
  }
}
