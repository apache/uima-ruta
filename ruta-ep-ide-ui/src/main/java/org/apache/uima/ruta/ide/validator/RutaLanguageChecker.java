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

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;

import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.dltk.core.builder.IBuildParticipantExtension;
import org.eclipse.dltk.core.builder.ISourceLineTracker;

public class RutaLanguageChecker implements IBuildParticipant, IBuildParticipantExtension {

  public boolean beginBuild(int buildType) {
    return true;
  }

  public void endBuild(IProgressMonitor monitor) {
  }

  public void build(IBuildContext context) throws CoreException {
    Object mdObj = context.get(IBuildContext.ATTR_MODULE_DECLARATION);
    if (!(mdObj instanceof ModuleDeclaration)) {
      return;
    }
    ModuleDeclaration md = (ModuleDeclaration) mdObj;

    IProblemReporter problemReporter = context.getProblemReporter();
    ISourceModule smod = context.getSourceModule();
    ISourceLineTracker linetracker = context.getLineTracker();
    try {
      Collection<String> dependencies = RutaProjectUtils.getClassPath(smod.getScriptProject()
              .getProject());
      URL[] urls = new URL[dependencies.size()];
      int counter = 0;
      for (String dep : dependencies) {
        urls[counter] = new File(dep).toURI().toURL();
        counter++;
      }
      ClassLoader classloader = new URLClassLoader(urls);
      ASTVisitor visitor = new LanguageCheckerVisitor(problemReporter, linetracker, smod,
              classloader);
      md.traverse(visitor);
    } catch (Exception e) {
      RutaIdeUIPlugin.error(e);
    }
  }

}
