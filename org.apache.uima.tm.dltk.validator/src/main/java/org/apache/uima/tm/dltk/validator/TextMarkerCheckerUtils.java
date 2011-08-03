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

package org.apache.uima.tm.dltk.validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.resource.metadata.ConfigurationParameterSettings;
import org.apache.uima.tm.dltk.internal.core.builder.TextMarkerProjectUtils;
import org.apache.uima.tm.textmarker.engine.TextMarkerEngine;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;


/**
 * @author Martin Toepfer
 * 
 */
public class TextMarkerCheckerUtils {

  public static Set<String> importScript(String path, int type, IScriptProject project)
          throws InvalidXMLException, IOException, ModelException {
    return importScript(path, type, project, false);
  }

  /**
   * @param path
   *          relative to script folder of the project.<br>
   *          i.e.: "org.apache.uima.tm.MyScript"
   * @param type
   *          i.e. IModelElement.FIELD for fields to be collected and returned
   * @return
   * @throws InvalidXMLException
   * @throws IOException
   * @throws ModelException
   */
  public static Set<String> importScript(String path, int type, IScriptProject project,
          boolean appendPath) throws InvalidXMLException, IOException, ModelException {
    Stack<String> namespaceStack = new Stack<String>();

    final Set<String> imports = new HashSet<String>();
    List<IFolder> scriptFolders = null;
    try {
      scriptFolders = TextMarkerProjectUtils.getAllScriptFolders(project);
    } catch (CoreException e) {
      e.printStackTrace();
    }
    String fileNameWithoutExtension = path.substring(path.lastIndexOf('.') + 1);
    String fileNameWithExtension = fileNameWithoutExtension + ".tm";
    ISourceModule sourceModule = null;
    boolean found = false;
    for (IFolder eachFolder : scriptFolders) {

      IScriptProject sp = DLTKCore.create(eachFolder.getProject());
      IScriptFolder[] scriptFolders2 = sp.getScriptFolders();
      for (IScriptFolder iScriptFolder : scriptFolders2) {
        sourceModule = iScriptFolder.getSourceModule(fileNameWithExtension);
        if (sourceModule.exists() && sourceModule.getResource() != null
                && sourceModule.getResource().exists()) {
          found = true;
          break;
        }
        if (found)
          break;
      }
    }
    IModelElement elements[] = null;
    namespaceStack.push(fileNameWithoutExtension);
    try {
      elements = sourceModule.getChildren();
      for (int i = 0; i < elements.length; i++) {
        IModelElement modelElement = elements[i];
        int elementType = modelElement.getElementType();
        if (elementType == type) {
          if (elementType == IModelElement.METHOD) {
            imports.add(namespaceStack.peek() + "." + modelElement.getElementName());
          } else if (appendPath) {
            imports.add(path + "." + modelElement.getElementName());
          } else {
            imports.add(modelElement.getElementName());
          }
        }
        if (elementType == IModelElement.METHOD) {
          String nSpace = namespaceStack.empty() ? modelElement.getElementName() : namespaceStack
                  .peek()
                  + "." + modelElement.getElementName();
          namespaceStack.push(nSpace);
          imports.addAll(collectElements((IMethod) modelElement, type, namespaceStack));
          namespaceStack.pop();
        }
      }
    } catch (ModelException e) {
      throw new FileNotFoundException();
    }
    return imports;
  }

  private static Set<String> collectElements(IMethod element, int type, Stack stack)
          throws ModelException {
    Set<String> fieldsCollection = new HashSet<String>();
    IModelElement elements[] = element.getChildren();
    if (elements == null) {
      return fieldsCollection;
    }
    for (int i = 0; i < elements.length; i++) {
      IModelElement modelElement = elements[i];
      int elementType = modelElement.getElementType();
      if (elementType == type) {
        if (elementType == IModelElement.FIELD) {
          fieldsCollection.add(modelElement.getElementName());
        }
        fieldsCollection.add(stack.peek() + "." + modelElement.getElementName());
      }
      if (elementType == IModelElement.METHOD) {
        stack.push(stack.peek() + "." + modelElement.getElementName());
        fieldsCollection.addAll(collectElements((IMethod) modelElement, type, stack));
        stack.pop();
      }
    }
    return fieldsCollection;
  }

  public static IFile getFile(IFolder folder, String filePath) {
    int lastDot = filePath.lastIndexOf('.');
    int sndLastDot = filePath.lastIndexOf('.', lastDot - 1);
    String fName = filePath;
    if (sndLastDot >= 0) {
      String subFolder = filePath.substring(0, sndLastDot);
      folder = folder.getFolder(subFolder);
      fName = filePath.substring(sndLastDot + 1);
    }
    return folder.getFile(fName);
  }

  /**
   * @param xmlFilePath
   *          absolute full path. i.e.: "de.uniwue.myengine" ".xml" will be added.
   * @return file.exists
   */
  public static boolean checkEngineImport(String xmlFilePath, IScriptProject project) {
    boolean result = false;
    List<IFolder> allDescriptorFolders;
    try {
      allDescriptorFolders = TextMarkerProjectUtils.getAllDescriptorFolders(project.getProject());
    } catch (CoreException e) {
      return false;
    }
    for (IFolder folder : allDescriptorFolders) {
      String fileExtended = xmlFilePath.replaceAll("[.]", "/") + ".xml";
      IFile iFile = TextMarkerCheckerUtils.getFile(folder, fileExtended);
      result |= iFile.exists();
    }
    return result;
  }

  public static boolean checkScriptImport(String xmlFilePath, IScriptProject project) {
    boolean result = false;
    List<IFolder> allDescriptorFolders;
    try {
      allDescriptorFolders = TextMarkerProjectUtils.getAllScriptFolders(project);
    } catch (CoreException e) {
      return false;
    }
    for (IFolder folder : allDescriptorFolders) {
      String fileExtended = xmlFilePath.replaceAll("[.]", "/") + ".tm";
      IFile iFile = TextMarkerCheckerUtils.getFile(folder, fileExtended);
      result |= iFile.exists();
    }
    return result;
  }

  /**
   * @param resourceFilePath
   *          absolute full path. i.e.: "de.uniwue.TestList.txt"
   * @return file.exists
   */
  public static boolean checkRessourceExistence(String resourceFilePath, IScriptProject project) {
    IFolder ddlFolder = project.getProject().getFolder(
            TextMarkerProjectUtils.getDefaultDescriptorLocation());
    final String basicXML = "BasicEngine.xml";
    IFile file = getFile(ddlFolder, basicXML);
    AnalysisEngineDescription aed;
    try {
      URL url = file.getLocationURI().toURL();
      aed = UIMAFramework.getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(url));
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    ConfigurationParameterSettings configurationParameterSettings = aed.getAnalysisEngineMetaData()
            .getConfigurationParameterSettings();
    String[] paths = (String[]) configurationParameterSettings
            .getParameterValue(TextMarkerEngine.RESOURCE_PATHS);
    if (paths == null) {
      IFolder folder = project.getProject().getFolder(
              TextMarkerProjectUtils.getDefaultResourcesLocation());
      String defaultPath = folder.getLocation().toPortableString();
      paths = new String[] { defaultPath };
    }
    for (String string : paths) {
      File iFile = new File(string, resourceFilePath);
      // IFile iFile = getFile(folder, ressourceFilePath);
      if (iFile.exists()) {
        return true;
      }

    }
    return false;
  }

  /**
   * @param xmlFilePath
   *          absolute full path. i.e.: "de.uniwue.myengine" ".xml" will be added.
   * @return file.exists
   */
  public static IFile getEngine(String xmlFilePath, IScriptProject project) {
    IFolder folder = project.getProject().getFolder(
            TextMarkerProjectUtils.getDefaultDescriptorLocation());
    String fileExtended = xmlFilePath + ".xml";
    return getFile(folder, fileExtended);
  }

  public static boolean checkTypeSystemImport(String localPath, IScriptProject project) {
    boolean result = false;
    List<IFolder> allDescriptorFolders;
    try {
      allDescriptorFolders = TextMarkerProjectUtils.getAllDescriptorFolders(project.getProject());
    } catch (CoreException e) {
      return false;
    }
    for (IFolder folder : allDescriptorFolders) {
      String fileExtended = localPath.replaceAll("[.]", "/") + ".xml";
      IFile iFile = TextMarkerCheckerUtils.getFile(folder, fileExtended);
      result |= iFile.exists();
    }
    return result;
  }
}
