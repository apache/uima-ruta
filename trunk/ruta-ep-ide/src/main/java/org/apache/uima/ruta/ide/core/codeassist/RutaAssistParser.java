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

package org.apache.uima.ruta.ide.core.codeassist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.ruta.ide.core.RutaNature;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.codeassist.IAssistParser;
import org.eclipse.dltk.core.DLTKLanguageManager;

public abstract class RutaAssistParser implements IAssistParser {
  public static final int MODULE = 0;// IRutaKeywords.MODULE;

  public static final int NAMESPACE = 1;// IRutaKeywords.NAMESPACE;

  public static final int FUNCTION = 2;// IRutaKeywords.FUNCTION;

  public static final int EXEC_EXPRESSION = 3;// IRutaKeywords.EXEC_EXPRESSION;

  protected ISourceParser parser = null;

  protected ModuleDeclaration module;

  protected ASTNode assistNodeParent = null;

  public RutaAssistParser() {
    // try {
    this.parser = DLTKLanguageManager.getSourceParser(RutaNature.NATURE_ID);
    // } catch (CoreException e) {
    // if (DLTKCore.DEBUG) {
    // e.printStackTrace();
    // }
    // }
  }

  public ASTNode getAssistNodeParent() {
    return assistNodeParent;
  }

  protected void findElementsTo(List statements, ASTNode node, List elements) {
    if (statements == null) {
      return;
    }
    Iterator i = statements.iterator();
    while (i.hasNext()) {
      ASTNode n = (ASTNode) i.next();
      if (n.equals(node)) {
        elements.add(n);
        return;
      }
      if (n.sourceStart() <= node.sourceStart() && node.sourceEnd() <= n.sourceEnd()) {
        elements.add(n);
        findElementsTo(RutaASTUtil.getStatements(n), node, elements);
        return;
      }
    }

  }

  protected List findLevelsTo(ASTNode astNodeParent) {
    List elements = new ArrayList();
    if (this.module != null || astNodeParent instanceof ModuleDeclaration) {
      if (this.module == null) {
        this.module = (ModuleDeclaration) astNodeParent;
      }
      elements.add(this.module);
      findElementsTo(RutaASTUtil.getStatements(this.module), astNodeParent, elements);
    }
    return elements;
  }

  public void setSource(ModuleDeclaration unit) {
    this.module = unit;
  }

  // public ModuleDeclaration parse(ISourceModule sourceUnit) {
  //
  // module = this.parser.parse(sourceUnit., null);
  // module.rebuild();
  //
  // // RutaASTUtil.extendStatements(module, sourceUnit
  // // .getSourceContents());
  //
  // return module;
  // }

  public ModuleDeclaration getModule() {
    return this.module;
  }
}
