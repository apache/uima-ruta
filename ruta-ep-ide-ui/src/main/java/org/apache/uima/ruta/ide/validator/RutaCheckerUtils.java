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

package org.apache.uima.ruta.ide.validator;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.apache.uima.util.InvalidXMLException;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class RutaCheckerUtils {

  public static Set<String> importScript(String path, int type, IScriptProject project)
          throws InvalidXMLException, IOException, CoreException {
    return importScript(path, type, project, false);
  }

  /**
   * @param path
   *          relative to script folder of the project.<br>
   *          i.e.: "org.apache.uima.MyScript"
   * @param type
   *          i.e. IModelElement.FIELD for fields to be collected and returned
   * @return set of elements
   * @throws InvalidXMLException
   * @throws IOException
   * @throws ModelException
   */
  public static Set<String> importScript(String path, int type, IScriptProject project,
          boolean appendPath) throws InvalidXMLException, IOException, ModelException,
          CoreException {
    // TODO rewrite method!
    Stack<String> namespaceStack = new Stack<String>();

    final Set<String> imports = new HashSet<String>();
    List<IFolder> scriptFolders = null;
    try {
      scriptFolders = RutaProjectUtils.getAllScriptFolders(project);
    } catch (CoreException e) {
      throw e;
    }
    String fileNameWithoutExtension = path.substring(path.lastIndexOf('.') + 1);
    String fileNameWithExtension = fileNameWithoutExtension + RutaEngine.SCRIPT_FILE_EXTENSION;
    ISourceModule sourceModule = null;
    boolean found = false;
    for (IFolder eachFolder : scriptFolders) {
      if(found ) {
        break;
      }
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
    if(sourceModule == null) {
      return imports;
    }
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
                  .peek() + "." + modelElement.getElementName();
          namespaceStack.push(nSpace);
          imports.addAll(collectElements((IMethod) modelElement, type, namespaceStack));
          namespaceStack.pop();
        }
      }
    } catch (ModelException e) {
      // ignore
//      throw new FileNotFoundException();
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
   *          absolute full path. i.e.: "org.apache.uima.myengine" ".xml" will be added.
   * @return file.exists
   */
  public static boolean checkEngineImport(String xmlFilePath, IScriptProject project) {
    boolean result = false;
    List<IFolder> allDescriptorFolders;
    try {
      allDescriptorFolders = RutaProjectUtils.getAllDescriptorFolders(project.getProject());
    } catch (Exception e) {
      return false;
    }
    for (IFolder folder : allDescriptorFolders) {
      String fileExtended = xmlFilePath.replaceAll("[.]", "/") + ".xml";
      IFile file = RutaCheckerUtils.getFile(folder, fileExtended);
      result |= file.exists();
    }
    return result;
  }

  public static boolean checkEngineOnClasspath(String clazz, IScriptProject project,
          ClassLoader classloader) {
    if (classloader == null) {
      classloader = RutaEngine.class.getClassLoader();
    }
    try {
      Class<?> loadClass = classloader.loadClass(clazz);
      Class<?> loadClass2 = classloader.loadClass(AnalysisComponent.class.getName());
      if (loadClass != null && loadClass2.isAssignableFrom(loadClass)) {
        return true;
      }
    } catch (ClassNotFoundException e) {
      return false;
    }
    return false;
  }

  public static IFile checkScriptImport(String xmlFilePath, IScriptProject project) {
    List<IFolder> allDescriptorFolders;
    try {
      allDescriptorFolders = RutaProjectUtils.getAllScriptFolders(project);
    } catch (CoreException e) {
      return null;
    }
    for (IFolder folder : allDescriptorFolders) {
      String fileExtended = xmlFilePath.replaceAll("[.]", "/") + RutaEngine.SCRIPT_FILE_EXTENSION;
      IFile file = RutaCheckerUtils.getFile(folder, fileExtended);
      if (file.exists()) {
        return file;
      }
    }
    return null;
  }



  public static IFile checkTypeSystemImport(String localPath, IScriptProject project) {
    List<IFolder> allDescriptorFolders;
    try {
      allDescriptorFolders = RutaProjectUtils.getAllDescriptorFolders(project.getProject());
    } catch (Exception e) {
      return null;
    }
    for (IFolder folder : allDescriptorFolders) {
      String fileExtended = localPath.replaceAll("[.]", "/") + ".xml";
      IFile file = RutaCheckerUtils.getFile(folder, fileExtended);
      if (file.exists()) {
        return file;
      }
    }
    return null;
  }

  public static URL checkImportExistence(String candidate, String extension,
          ClassLoader classloader) throws IOException {
    String p = candidate.replaceAll("[.]", "/");
    p += "." + extension;
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(
            classloader);
    String prefix = "classpath*:";
    String pattern = prefix + p;
    Resource[] resources = resolver.getResources(pattern);
    if(resources == null || resources.length == 0) {
      return null;
    } else {
      Resource resource = resources[0];
      URL url = resource.getURL();
      return url;
    }
  }

  

}
