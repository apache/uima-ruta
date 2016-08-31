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

package org.apache.uima.ruta.check;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.impl.XmiCasSerializer;
import org.apache.uima.caseditor.editor.AnnotationEditor;
import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.util.XMLSerializer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

public class CheckAnnotationUtils {
  public static AnnotationEditor openInCasEditor(File file, int begin, int end) {
    if (file == null) {
      return null;
    }
    String absolutePath = file.getAbsolutePath();
    try {
      IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
      IFile ifile = getIFile(absolutePath);
      AnnotationEditor editor = (AnnotationEditor) page.openEditor(new FileEditorInput(ifile),
              "org.apache.uima.caseditor.editor");
      editor.selectAndReveal(begin, end - begin);
      return editor;
    } catch (PartInitException e) {
      RutaAddonsPlugin.error(e);
    }
    return null;
  }

  public static IFile getIFile(String location) {
    IPath s = Path.fromOSString(location);
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    IFile ifile = workspace.getRoot().getFileForLocation(s);
    return ifile;
  }
  
  public static void writeXmi(CAS aCas, File name) throws Exception {
    FileOutputStream out = null;

    try {
      // write XMI
      out = new FileOutputStream(name);
      XmiCasSerializer ser = new XmiCasSerializer(aCas.getTypeSystem());
      XMLSerializer xmlSer = new XMLSerializer(out, false);
      ser.serialize(aCas, xmlSer.getContentHandler());
    } catch (Exception e) {
      throw e;
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }

}
