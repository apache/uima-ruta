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

package org.apache.uima.tm.dltk.testing.internal.test;

import org.apache.uima.tm.dltk.internal.testing.TextMarkerTestingPlugin;
import org.apache.uima.tm.dltk.testing.ITextMarkerTestingEngine;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.core.environment.IDeployment;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.testing.ITestingProcessor;


public class TextMarkerTestTestingEngine implements ITextMarkerTestingEngine {
  public TextMarkerTestTestingEngine() {
  }

  public String getId() {
    return TextMarkerTestingPlugin.PLUGIN_ID + ".testingEngine";
  }

  public String getName() {
    return "TextMarker Test";
  }

  public ITestingProcessor getProcessor(ILaunch launch) {
    return new TextMarkerTestOutputProcessor(launch);
  }

  public boolean isValidModule(ISourceModule module) {
    ModuleDeclaration moduleDeclaration = SourceParserUtil.getModuleDeclaration(module, null);
    return module.getElementName().endsWith(".tm");
    // ASTNode[] findTests = findTests(moduleDeclaration);
    // if (findTests.length > 0) {
    // return true;
    // }
    // return false;
  }

  // private ASTNode[] findTests(ModuleDeclaration decl) {
  // final List ndes = new ArrayList();
  // try {
  // decl.traverse(new ASTVisitor() {
  // public boolean visitGeneral(ASTNode node) throws Exception {
  // if (node instanceof TextMarkerStatement2
  // && ((TextMarkerStatement2) node).getCount() > 2) {
  // TextMarkerStatement2 st = (TextMarkerStatement2) node;
  // Expression cmd = st.getAt(0);
  // if (cmd instanceof SimpleReference) {
  // String cmdName = ((SimpleReference) cmd).getName();
  // if (cmdName.startsWith("::")) {
  // cmdName = cmdName.substring(2);
  // }
  // if ("test".equals(cmdName)
  // || "tmtest::test".equals(cmdName)) {
  //
  // // List findLevelsTo = findLevelsTo(decl, node);
  // Expression name = st.getAt(1);
  // if (name instanceof SimpleReference) {
  // String nameValue = ((SimpleReference) name)
  // .getName();
  // ndes.add(node);
  // }
  // }
  // }
  // }
  // return true;
  // }
  // });
  // } catch (CoreException e) {
  // if (DLTKCore.DEBUG) {
  // e.printStackTrace();
  // }
  // } catch (Exception e) {
  // if (DLTKCore.DEBUG) {
  // e.printStackTrace();
  // }
  // }
  // return (ASTNode[]) ndes.toArray(new ASTNode[ndes.size()]);
  // }

  public void correctLaunchConfiguration(InterpreterConfig config,
          ILaunchConfiguration configuration) {
    // try {
    IDeployment deployment = config.getExecutionEnvironment().createDeployment();
    // IPath runner = deployment.add(TextMarkerTestingPlugin.getDefault().getBundle(),
    // "scripts/tmtestEngine.tm");
    IPath scriptFilePath = config.getScriptFilePath();
    // stays the same
    // config.setScriptFile(runner);
    if (scriptFilePath != null) {
      config.addScriptArg(scriptFilePath.toOSString(), 0);
    }
    config.addInterpreterArg("testing");
    // } catch (IOException e) {
    // if (DLTKCore.DEBUG) {
    // e.printStackTrace();
    // }
    // }
  }
}
