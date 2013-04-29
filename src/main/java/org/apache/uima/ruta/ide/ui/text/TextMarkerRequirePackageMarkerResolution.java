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

package org.apache.uima.textmarker.ide.ui.text;

import java.util.HashSet;
import java.util.Set;

import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterContainerHelper;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.ui.editor.IScriptAnnotation;
import org.eclipse.dltk.ui.text.IAnnotationResolution;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IMarkerResolution;

final class TextMarkerRequirePackageMarkerResolution implements IMarkerResolution,
        IAnnotationResolution {
  private String pkgName;

  private IScriptProject project;

  public TextMarkerRequirePackageMarkerResolution(String pkgName, IScriptProject scriptProject) {
    this.pkgName = pkgName;
    this.project = scriptProject;
  }

  public String getLabel() {
    final String msg = Messages.TextMarkerRequirePackageMarkerResolution_addPackageToBuildpath;
    return NLS.bind(msg, pkgName);
  }

  private boolean resolve() {
    final IInterpreterInstall install;
    try {
      install = ScriptRuntime.getInterpreterInstall(project);
      if (install != null) {
        final Set names = new HashSet();
        final Set autoNames = new HashSet();
        InterpreterContainerHelper.getInterpreterContainerDependencies(project, names, autoNames);

        if (names.add(pkgName)) {
          InterpreterContainerHelper.setInterpreterContainerDependencies(project, names, autoNames);
          return true;
        }
      }
    } catch (CoreException e) {
      TextMarkerIdePlugin.error("require package resolve error", e); //$NON-NLS-1$
    }
    return false;
  }

  public void run(final IMarker marker) {
    if (resolve()) {
      try {
        marker.delete();
      } catch (CoreException e) {
        if (DLTKCore.DEBUG) {
          e.printStackTrace();
        }
      }
    }
  }

  public void run(Annotation annotation, IDocument document) {
    resolve();
  }

  public void run(IScriptAnnotation annotation, IDocument document) {
    resolve();
  }
}
