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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.resource.metadata.Import;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.apache.uima.ruta.ide.core.RutaNature;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.apache.uima.ruta.ide.ui.RutaImages;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathAttribute;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.internal.core.BuildpathEntry;
import org.eclipse.dltk.internal.ui.util.CoreUtility;
import org.eclipse.dltk.internal.ui.wizards.buildpath.BPListElement;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;
import org.eclipse.dltk.ui.wizards.ProjectWizard;
import org.eclipse.dltk.ui.wizards.ProjectWizardFirstPage;
import org.eclipse.dltk.ui.wizards.ProjectWizardSecondPage;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.xml.sax.SAXException;

public class RutaProjectCreationWizard extends ProjectWizard {

  public static final String ID_WIZARD = "org.apache.uima.ruta.ide.ui.wizards.RutaProjectWizard"; //$NON-NLS-1$

  private ProjectWizardFirstPage fFirstPage;

  private ProjectWizardSecondPage fSecondPage;

  private IConfigurationElement fConfigElement;

  public RutaProjectCreationWizard() {
    setDefaultPageImageDescriptor(RutaImages.DESC_WIZBAN_PROJECT_CREATION);
    setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
    setWindowTitle(RutaWizardMessages.ProjectCreationWizard_title);
  }

  @Override
  public void addPages() {
    super.addPages();
    fFirstPage = new RutaProjectWizardFirstPage();

    fFirstPage.setTitle(RutaWizardMessages.ProjectCreationWizardFirstPage_title);
    fFirstPage.setDescription(RutaWizardMessages.ProjectCreationWizardFirstPage_description);
    addPage(fFirstPage);
    fSecondPage = new RutaProjectWizardSecondPage(fFirstPage);
    addPage(fSecondPage);
  }

  @Override
  protected void finishPage(IProgressMonitor monitor) throws InterruptedException, CoreException {
    super.finishPage(monitor);
    createProject(monitor);
  }

  public void createProject(IProgressMonitor monitor) throws CoreException {
    IScriptProject scriptProject = fSecondPage.getScriptProject();
    createRutaProject(scriptProject, fSecondPage.getRawBuildPath(), monitor);
  }

  public static void createRutaProject(IScriptProject scriptProject, IBuildpathEntry[] buildPath,
          IProgressMonitor monitor) throws CoreException {
    IProject project = scriptProject.getProject();
    IFolder folder = project.getFolder(RutaProjectUtils.getDefaultInputLocation());
    if (!folder.exists()) {
      CoreUtility.createFolder(folder, true, true, new SubProgressMonitor(monitor, 1));
    }
    folder = project.getFolder(RutaProjectUtils.getDefaultOutputLocation());
    if (!folder.exists()) {
      CoreUtility.createFolder(folder, true, true, new SubProgressMonitor(monitor, 1));
    }
    folder = project.getFolder(RutaProjectUtils.getDefaultTestLocation());
    if (!folder.exists()) {
      CoreUtility.createFolder(folder, true, true, new SubProgressMonitor(monitor, 1));
    }
    IFolder descFolder = project.getFolder(RutaProjectUtils.getDefaultDescriptorLocation());
    if (!descFolder.exists()) {
      CoreUtility.createFolder(descFolder, true, true, new SubProgressMonitor(monitor, 1));
    }
    IFolder srcFolder = project.getFolder(RutaProjectUtils.getDefaultScriptLocation());
    if (!srcFolder.exists()) {
      CoreUtility.createFolder(srcFolder, true, true, new SubProgressMonitor(monitor, 1));
    }
    IFolder rsrcFolder = project.getFolder(RutaProjectUtils.getDefaultResourcesLocation());
    if (!rsrcFolder.exists()) {
      CoreUtility.createFolder(rsrcFolder, true, true, new SubProgressMonitor(monitor, 1));
    }

    IFolder utilsFolder = descFolder.getFolder("utils");
    if (!utilsFolder.exists()) {
      CoreUtility.createFolder(utilsFolder, true, true, new SubProgressMonitor(monitor, 1));
    }

    List<BPListElement> buildpathEntries = new ArrayList<BPListElement>();
    if (buildPath != null) {
      for (IBuildpathEntry buildpathEntry : buildPath) {
        BPListElement createFromExisting = BPListElement.createFromExisting(buildpathEntry,
                scriptProject);
        if (createFromExisting.getBuildpathEntry().getEntryKind() != IBuildpathEntry.BPE_SOURCE) {
          buildpathEntries.add(createFromExisting);
        }
      }
    }
    IBuildpathAttribute[] extraAttributes = new IBuildpathAttribute[1];
    extraAttributes[0] = DLTKCore.newBuildpathAttribute(RutaProjectUtils.BUILDPATH_ATTRIBUTE_RUTA,
            RutaProjectUtils.BUILDPATH_ATTRIBUTE_SCRIPT);
    IBuildpathEntry newSourceEntry = DLTKCore.newSourceEntry(srcFolder.getFullPath(),
            BuildpathEntry.INCLUDE_ALL, BuildpathEntry.EXCLUDE_NONE, extraAttributes);
    buildpathEntries.add(BPListElement.createFromExisting(newSourceEntry, scriptProject));

    extraAttributes = new IBuildpathAttribute[1];
    extraAttributes[0] = DLTKCore.newBuildpathAttribute(RutaProjectUtils.BUILDPATH_ATTRIBUTE_RUTA,
            RutaProjectUtils.BUILDPATH_ATTRIBUTE_DESCRIPTOR);
    IBuildpathEntry newDescriptorEntry = DLTKCore.newSourceEntry(descFolder.getFullPath(),
            BuildpathEntry.INCLUDE_ALL, BuildpathEntry.EXCLUDE_NONE, extraAttributes);
    buildpathEntries.add(BPListElement.createFromExisting(newDescriptorEntry, scriptProject));

    extraAttributes = new IBuildpathAttribute[1];
    extraAttributes[0] = DLTKCore.newBuildpathAttribute(RutaProjectUtils.BUILDPATH_ATTRIBUTE_RUTA,
            RutaProjectUtils.BUILDPATH_ATTRIBUTE_RESOURCES);
    IBuildpathEntry newResourcesEntry = DLTKCore.newSourceEntry(rsrcFolder.getFullPath(),
            BuildpathEntry.INCLUDE_ALL, BuildpathEntry.EXCLUDE_NONE, extraAttributes);
    buildpathEntries.add(BPListElement.createFromExisting(newResourcesEntry, scriptProject));

    BuildpathsBlock.flush(buildpathEntries, scriptProject, monitor);
    copyDescriptors(descFolder);

    RutaProjectUtils.addProjectDataPath(project, descFolder);

    descFolder.refreshLocal(IResource.DEPTH_INFINITE, monitor);
  }

  public static void copyDescriptors(IFolder descFolder) {
    File descDir = descFolder.getLocation().toFile();
    File utilsDir = new File(descFolder.getLocation().toFile(), "utils/");
    utilsDir.mkdirs();
    copy(descDir, "BasicTypeSystem.xml");
    copy(descDir, "RutaBasicTypeSystem.xml");
    copy(descDir, "RutaInternalTypeSystem.xml");
    copy(descDir, "DefaultSeederTypeSystem.xml");
    copy(descDir, "BasicEngine.xml");

    copy(utilsDir, "Modifier.xml");
    copy(utilsDir, "AnnotationWriter.xml");
    copy(utilsDir, "StyleMapCreator.xml");
    copy(utilsDir, "XMIWriter.xml");
    copy(utilsDir, "SourceDocumentInformation.xml");
    copy(utilsDir, "PlainTextAnnotator.xml");
    copy(utilsDir, "PlainTextTypeSystem.xml");
    copy(utilsDir, "HtmlAnnotator.xml");
    copy(utilsDir, "HtmlTypeSystem.xml");
    copy(utilsDir, "HtmlConverter.xml");
    copy(utilsDir, "Cutter.xml");
    copy(utilsDir, "ViewWriter.xml");

    setImportsByLocation(descDir, "BasicEngine.xml");
    setImportsByLocation(utilsDir, "HtmlAnnotator.xml");
    setImportsByLocation(utilsDir, "PlainTextAnnotator.xml");

  }

  private static void setImportsByLocation(File dir, String descName) {
    FileOutputStream fos = null;
    try {
      File file = new File(dir, descName);
      AnalysisEngineDescription description = UIMAFramework.getXMLParser()
              .parseAnalysisEngineDescription(new XMLInputSource(file));
      Import[] imports = description.getAnalysisEngineMetaData().getTypeSystem().getImports();
      for (Import each : imports) {
        String name = each.getName();
        String[] split = name.split("[.]");
        String location = split[split.length - 1] + ".xml";
        each.setName(null);
        each.setLocation(location);
      }
      fos = new FileOutputStream(file);
      description.toXML(fos);
    } catch (InvalidXMLException | IOException | SAXException e) {
      RutaIdeUIPlugin.error(e);
    } finally {
      IOUtils.closeQuietly(fos);
    }

  }

  private static void copy(File dir, String fileName) {
    InputStream in = null;
    OutputStream out = null;
    in = RutaEngine.class.getResourceAsStream(fileName);
    try {
      out = new FileOutputStream(new File(dir, fileName));
    } catch (FileNotFoundException e) {
      System.err.println(e);
    }
    if (in != null && out != null) {
      copy(in, out);
    }

  }

  static void copy(InputStream fis, OutputStream fos) {
    try {
      byte[] buffer = new byte[0xFFFF];
      for (int len; (len = fis.read(buffer)) != -1;)
        fos.write(buffer, 0, len);
    } catch (IOException e) {
      System.err.println(e);
    } finally {
      if (fis != null) {
        try {
          fis.close();
        } catch (IOException e) {
          System.err.println(e);
        }
      }
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException e) {
          System.err.println(e);
        }
      }
    }
  }

  @Override
  public boolean performFinish() {
    boolean res = super.performFinish();
    if (res) {
      BasicNewProjectResourceWizard.updatePerspective(fConfigElement);
      selectAndReveal(fSecondPage.getScriptProject().getProject());
    }
    return res;
  }

  public void setInitializationData(IConfigurationElement cfig, String propertyName, Object data) {
    fConfigElement = cfig;
  }

  @Override
  public boolean performCancel() {
    return super.performCancel();
  }

  @Override
  public String getScriptNature() {
    return RutaNature.NATURE_ID;
  }
}
