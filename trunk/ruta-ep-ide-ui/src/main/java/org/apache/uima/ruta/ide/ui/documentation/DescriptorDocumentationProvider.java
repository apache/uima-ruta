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

package org.apache.uima.ruta.ide.ui.documentation;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.resource.metadata.ConfigurationParameter;
import org.apache.uima.resource.metadata.ConfigurationParameterDeclarations;
import org.apache.uima.resource.metadata.FeatureDescription;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.apache.uima.ruta.ide.ui.editor.RutaEditor;
import org.apache.uima.util.XMLInputSource;
import org.apache.uima.util.XMLSerializer;
import org.apache.uima.util.XMLizable;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.ui.documentation.IDocumentationResponse;
import org.eclipse.dltk.ui.documentation.IScriptDocumentationProvider;
import org.eclipse.dltk.ui.documentation.IScriptDocumentationProviderExtension2;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class DescriptorDocumentationProvider implements IScriptDocumentationProvider {

  @Override
  public Reader getInfo(final String content) {
    final StringBuilder sb = new StringBuilder();

    // HOTFIX: fake access to script project, for now until better solution is found
    Display.getDefault().syncExec(new Runnable() {
      // display for accessing WorkbenchWindow
      @Override
      public void run() {
        IWorkbenchWindow win = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (win != null) {
          IWorkbenchPage page = win.getActivePage();
          IEditorPart activeEditor = page.getActiveEditor();
          if (activeEditor != null) {
            RutaEditor re = (RutaEditor) activeEditor;
            IEditorInput ei = re.getEditorInput();
            if (ei instanceof FileEditorInput) {
              FileEditorInput fei = (FileEditorInput) ei;
              IProject project = fei.getFile().getProject();
              try {
                Collection<String> classPath = RutaProjectUtils.getClassPath(project);
                URL[] urls = new URL[classPath.size()];
                int counter = 0;
                for (String dep : classPath) {
                  urls[counter] = new File(dep).toURI().toURL();
                  counter++;
                }
                URLClassLoader classloader = new URLClassLoader(urls);
                Resource[] filesInClasspath = getFilesInClasspath(content, classloader);
                if (filesInClasspath.length > 0) {
                  Resource r = filesInClasspath[0];
                  URL url = r.getURL();
                  XMLizable desc = (XMLizable) UIMAFramework.getXMLParser().parse(
                          new XMLInputSource(url));
                  if (desc instanceof AnalysisEngineDescription) {
                    AnalysisEngineDescription aed = (AnalysisEngineDescription) desc;
                    ConfigurationParameterDeclarations cpd = aed.getAnalysisEngineMetaData()
                            .getConfigurationParameterDeclarations();
                    ConfigurationParameter[] parameters = cpd.getConfigurationParameters();

                    for (ConfigurationParameter each : parameters) {
                      sb.append("<b>");
                      sb.append(each.getName());
                      sb.append("</b>");
                      sb.append("<li><b>type:</b> " + each.getType() + "</li>");
                      sb.append("<li><b>mandatory:</b> " + each.isMandatory() + "</li>");
                      sb.append("<li><b>multiValued:</b> " + each.isMultiValued() + "</li>");
                      if (each.getDescription() != null && !each.getDescription().trim().isEmpty()) {
                        sb.append("<p>");
                        sb.append(each.getDescription());
                        sb.append("</p>");
                      }
                      sb.append("<br/>");
                    }
                  } else if (desc instanceof TypeSystemDescription) {
                    TypeSystemDescription tsd = (TypeSystemDescription) desc;
                    // TODO: use better resource manager
                    tsd.resolveImports();
                    TypeDescription[] types = tsd.getTypes();
                    for (TypeDescription each : types) {
                      sb.append("<b>");
                      sb.append(each.getName());
                      sb.append("</b>");
                      // sb.append("<br/>");
                      sb.append(" (" + each.getSupertypeName() + ")");

                      if (each.getDescription() != null && !each.getDescription().trim().isEmpty()) {
                        sb.append("<p>");
                        sb.append(each.getDescription());
                        sb.append("</p>");
                      }
                      // sb.append("<br/>");
                      // sb.append("<ul>");
                      for (FeatureDescription eachFeat : each.getFeatures()) {
                        sb.append("<li><b>" + eachFeat.getName() + "</b> ("
                                + eachFeat.getRangeTypeName() + ") </li>");
                        if (eachFeat.getDescription() != null && !eachFeat.getDescription().trim().isEmpty()) {
                          sb.append("<p>");
                          sb.append(eachFeat.getDescription());
                          sb.append("</p>");
                        }
                      }
                      // sb.append("</ul>");
                       sb.append("<br/>");
                    }
                  }
                }
              } catch (Exception e) {
                RutaIdeUIPlugin.error(e);
              }
            }

          }
        }
      }
    });

    return new StringReader(sb.toString());
  }

  private Resource[] getFilesInClasspath(String name, URLClassLoader classloader)
          throws IOException {
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(
            classloader);
    String p = "classpath*:";
    String suffix = "/**/" + name + ".xml";
    String pattern = p + suffix;
    Resource[] resources = resolver.getResources(pattern);
    return resources;
  }

  @Override
  public Reader getInfo(IMember element, boolean lookIntoParents, boolean lookIntoExternal) {
    return null;
  }

}
