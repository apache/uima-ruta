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
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.uima.ruta.ide.RutaIdePlugin;
import org.apache.uima.ruta.ide.launching.RutaLaunchConfigurationDelegate;
import org.apache.uima.ruta.ide.parser.ast.RutaAction;
import org.apache.uima.ruta.ide.parser.ast.RutaActionConstants;
import org.apache.uima.ruta.ide.parser.ast.RutaImportStatement;
import org.apache.uima.ruta.ide.parser.ast.RutaStatementConstants;
import org.apache.uima.util.InvalidXMLException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.dltk.core.builder.IBuildParticipantExtension;
import org.eclipse.dltk.core.builder.ISourceLineTracker;

public class RutaEngineAndCallChecker implements IBuildParticipant, IBuildParticipantExtension {

  private class EngineAndCallCheckerVisitor extends ASTVisitor {
    private IProblemReporter rep;

    private RutaCheckerProblemFactory problemFactory;

    private Set<String> engines;

    private Set<String> scripts;

    private String curFile;

    private HashSet<String> scriptsInnerBlocks;

    private URLClassLoader classloader;

    public EngineAndCallCheckerVisitor(IProblemReporter rep, ISourceLineTracker linetracker,
            String curFile) {
      this.problemFactory = new RutaCheckerProblemFactory(curFile, linetracker);
      this.rep = rep;
      this.engines = new HashSet<String>();
      this.scripts = new HashSet<String>();
      this.scriptsInnerBlocks = new HashSet<String>();
      this.curFile = curFile;

      if (curFile != null && curFile.endsWith(".ruta")) {
        scripts.add(curFile.substring(0, curFile.length() - 5));
      }
      try {
        String fnwe = curFile.substring(0, curFile.length() - 5);
        scriptsInnerBlocks.addAll(RutaCheckerUtils
                .importScript(fnwe, IModelElement.METHOD, project));
      } catch (InvalidXMLException e) {
      } catch (IOException e) {
      } catch (ModelException e) {
      }

      try {
        Collection<String> dependencies = RutaLaunchConfigurationDelegate.getClassPath(project);
        URL[] urls = new URL[dependencies.size()];
        int counter = 0;
        for (String dep : dependencies) {
          urls[counter] = new File(dep).toURL();
          counter++;
        }
        classloader = new URLClassLoader(urls);
      } catch (CoreException e) {
        RutaIdePlugin.error(e);
      } catch (MalformedURLException e) {
        RutaIdePlugin.error(e);
      }

    }

    @Override
    public boolean visit(Statement s) throws Exception {
      if (s instanceof RutaImportStatement) {
        // handle engine imports
        if (((RutaImportStatement) s).getType() == RutaStatementConstants.S_IMPORT_ENGINE) {
          SimpleReference sRef = (SimpleReference) ((RutaImportStatement) s).getExpression();
          if (RutaCheckerUtils.checkEngineImport(sRef.getName(), project)) {
            importEngine(sRef.getName());
          } else {
            IProblem problem = problemFactory.createFileNotFoundProblem(sRef);
            rep.reportProblem(problem);
          }
        }
        if (((RutaImportStatement) s).getType() == RutaStatementConstants.S_IMPORT_UIMAFIT_ENGINE) {
          SimpleReference sRef = (SimpleReference) ((RutaImportStatement) s).getExpression();
          if (RutaCheckerUtils.checkEngineOnClasspath(sRef.getName(), project, classloader)) {
            importEngine(sRef.getName());
          } else {
            IProblem problem = problemFactory.createFileNotFoundProblem(sRef);
            rep.reportProblem(problem);
          }
        }
        // handle script imports
        if (((RutaImportStatement) s).getType() == RutaStatementConstants.S_IMPORT_SCRIPT) {
          SimpleReference stRef = (SimpleReference) ((RutaImportStatement) s).getExpression();
          String sRefName = stRef.getName();
          try {
            Set<String> blocks = RutaCheckerUtils.importScript(sRefName, IModelElement.METHOD,
                    project);
            scripts.add(sRefName);
            if (!blocks.isEmpty()) {
              scriptsInnerBlocks.addAll(blocks);
            }
          } catch (Exception e) {
          }
        }
        return false;
      }
      return true;
    }

    private void importEngine(String name) {
      engines.add(name);
      int i = name.lastIndexOf('.');
      if (i > 1) {
        // TODO emit errors for doublettes (names in scripts and
        // engines)
        String lastPart = name.substring(i + 1);
        if (lastPart != null && lastPart.length() != 0) {
          engines.add(lastPart);
        }
      }
    }

    @Override
    public boolean visit(Expression s) throws Exception {
      if (s instanceof RutaAction) {
        RutaAction action = (RutaAction) s;
        if (action.getKind() == RutaActionConstants.A_CALL) {
          // TODO see antlr grammar: no viable child defined!
          if (action.getChilds().size() > 0) {
            SimpleReference ref = (SimpleReference) action.getChilds().get(0);
            if (ref != null && !engines.contains(ref.getName())) {
              String required = ref.getName();
              for (String script : scripts) {
                // check direct script-call
                boolean a = script.endsWith(required);
                if (a) {
                  return false;
                }
              }
              for (String block : scriptsInnerBlocks) {
                boolean b = block.equals(required);
                if (b) {
                  return false;
                }
              }
              IProblem problem = problemFactory.createFileNotFoundProblem(ref);
              rep.reportProblem(problem);
            }
          }
          return false;
        }
        return true;
      }
      return true;
    }

    @Override
    public boolean visit(MethodDeclaration s) throws Exception {
      return true;
    }

    @Override
    public boolean visit(TypeDeclaration s) throws Exception {
      return false;
    }
  }

  public RutaEngineAndCallChecker(IScriptProject project) throws CoreException {
    this.project = project;
  }

  private IScriptProject project;

  public boolean beginBuild(int buildType) {
    return true;
  }

  public void endBuild(IProgressMonitor monitor) {
  }

  public void build(IBuildContext context) throws CoreException {
    // getAST:
    Object mdObj = context.get(IBuildContext.ATTR_MODULE_DECLARATION);
    if (!(mdObj instanceof ModuleDeclaration)) {
      return;
    }
    ModuleDeclaration md = (ModuleDeclaration) mdObj;
    IProblemReporter problemReporter = context.getProblemReporter();
    ISourceModule smod = context.getSourceModule();

    // traverse:
    ISourceLineTracker linetracker = context.getLineTracker();
    String fileName = smod.getElementName();
    try {
      ASTVisitor visitor = new EngineAndCallCheckerVisitor(problemReporter, linetracker, fileName);
      md.traverse(visitor);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
