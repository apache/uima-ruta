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

package org.apache.uima.ruta.utils.apply;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.impl.XmiCasDeserializer;
import org.apache.uima.cas.impl.XmiCasSerializer;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.XMLInputSource;
import org.apache.uima.util.XMLSerializer;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.xml.sax.SAXException;

public abstract class AbstractApplyScriptHandlerJob extends Job {
  private ExecutionEvent event;

  private IFile scriptFile;

  private boolean createXMI;

  AbstractApplyScriptHandlerJob(ExecutionEvent event, IFile scriptFile, boolean createXMI) {
    super("Applying " + scriptFile.getName() + "...");
    this.event = event;
    this.scriptFile = scriptFile;
    this.createXMI = createXMI;
    setUser(true);
  }

  @Override
  public IStatus run(IProgressMonitor monitor) {
    monitor.beginTask("Collecting files...", 1);

    if (HandlerUtil.getCurrentSelection(event) instanceof IStructuredSelection) {
      StructuredSelection selection = (StructuredSelection) HandlerUtil.getCurrentSelection(event);
      Iterator<?> iter = selection.iterator();
      IResource first = null;

      List<IPath> paths = new ArrayList<IPath>();
      while (iter.hasNext()) {
        Object object = iter.next();
        if (object instanceof IResource) {
          IResource resource = (IResource) object;
          if (first == null) {
            first = resource;
          }
          paths.addAll(getPaths(resource));
        }
      }

      CAS cas = null;
      AnalysisEngine ae = null;
      try {
        IPath rootPath = RutaProjectUtils.getDescriptorRootPath(scriptFile.getProject());
        IPath descriptorPath = RutaProjectUtils.getAnalysisEngineDescriptorPath(
                scriptFile.getLocation(), scriptFile.getProject());
        XMLInputSource in = new XMLInputSource(descriptorPath.toPortableString());
        ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
        ResourceManager resMgr = UIMAFramework.newDefaultResourceManager();
        resMgr.setDataPath(rootPath.toPortableString());
        ae = UIMAFramework.produceAnalysisEngine(specifier, resMgr, null);
        initAE(ae);
        ae.reconfigure();
      } catch (Exception e) {
        DLTKCore.error(e.getMessage(), e);
        return Status.CANCEL_STATUS;
      }
      monitor.beginTask("Processing... ", paths.size());
      for (IPath path : paths) {
        if (monitor.isCanceled()) {
          break;
        }

        monitor.setTaskName("Processing " + path.lastSegment() + "... ");
        try {

          if (cas == null) {
            cas = ae.newCAS();
          } else {
            cas.reset();
          }
          if (path.getFileExtension().equals("xmi")) {
            XmiCasDeserializer.deserialize(new FileInputStream(path.toPortableString()), cas, true);
          } else {
            cas.setDocumentText(getText(path.toPortableString()));
          }
          RutaEngine.removeSourceDocumentInformation(cas);
          RutaEngine.addSourceDocumentInformation(cas, new File(path.toPortableString()));
          ae.process(cas);
        } catch (Exception e) {
          DLTKCore.error(e.getMessage(), e);
          monitor.worked(1);
          continue;
        }

        if (createXMI) {
          monitor.setTaskName("Writing " + path.lastSegment() + "... ");

          File newFile = null;
          if (path.getFileExtension().equals("xmi")) {
            newFile = new File(path.toPortableString());
          } else {
            newFile = new File(path.toPortableString() + ".xmi");
          }
          try {
            writeXmi(cas, newFile);
          } catch (Exception e) {
            DLTKCore.error(e.getMessage(), e);
            monitor.worked(1);
            continue;
          }
          IWorkspace workspace = ResourcesPlugin.getWorkspace();
          IWorkspaceRoot root = workspace.getRoot();
          IPath makeRelativeTo = path.makeRelativeTo(root.getLocation());
          IResource resource = root.findMember(makeRelativeTo);

          try {
            if (resource != null) {
              resource.getParent()
                      .refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
            }
          } catch (CoreException e) {
          }
        }
        monitor.worked(1);
      }
      if (cas != null) {
        cas.release();
      }
      if (ae != null) {
        ae.destroy();
      }
    }

    monitor.done();
    return Status.OK_STATUS;

  }

  private static void writeXmi(CAS aCas, File name) throws IOException, SAXException {
    FileOutputStream out = null;

    try {
      // write XMI
      out = new FileOutputStream(name);
      XmiCasSerializer ser = new XmiCasSerializer(aCas.getTypeSystem());
      XMLSerializer xmlSer = new XMLSerializer(out, false);
      ser.serialize(aCas, xmlSer.getContentHandler());
    } catch (Exception e) {
      RutaAddonsPlugin.error(e);
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }

  private List<IPath> getPaths(IResource resource) {
    List<IPath> paths = new ArrayList<IPath>();
    if (resource instanceof IFile) {
      IFile file = (IFile) resource;
      String fileExtension = file.getFileExtension();
      if ("txt".equals(fileExtension) || "html".equals(fileExtension)
              || "xmi".equals(fileExtension) || "htm".equals(fileExtension)) {
        paths.add(file.getLocation());
      }
    } else if (resource instanceof IFolder) {
      IFolder folder = (IFolder) resource;
      try {
        IResource[] members = folder.members();
        for (IResource each : members) {
          paths.addAll(getPaths(each));
        }
      } catch (CoreException e) {
      }
    }
    return paths;
  }

  abstract void initAE(AnalysisEngine ae);

  private static String getText(String each) {
    try {
      return FileUtils.file2String(new File(each), "UTF-8");
    } catch (IOException e) {
      DLTKCore.error(e.getMessage(), e);
    }
    return "";
  }

}
