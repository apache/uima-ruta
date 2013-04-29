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

package org.apache.uima.ruta.ide.ui;

import java.util.Iterator;
import java.util.List;

import org.apache.uima.ruta.ide.parser.ast.RutaModuleDeclaration;
import org.apache.uima.ruta.ide.ui.text.RutaTextTools;
import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.ui.editor.highlighting.HighlightedPosition;
import org.eclipse.dltk.ui.editor.highlighting.ISemanticHighlightingRequestor;
import org.eclipse.dltk.ui.editor.highlighting.SemanticHighlighting;


public class DefaultRutaSemanticHighlightingExtension implements
        ISemanticHighlightingExtension {

  private SemanticHighlighting[] highlightings = new SemanticHighlighting[] {
      new RutaTextTools.SH(RutaPreferenceConstants.EDITOR_DECLARATION_DEFINITION_COLOR,
              null, null),
      new RutaTextTools.SH(RutaPreferenceConstants.EDITOR_FUNCTION_COLOR, null, null),
      new RutaTextTools.SH(RutaPreferenceConstants.EDITOR_ACTION_COLOR, null, null),
      new RutaTextTools.SH(RutaPreferenceConstants.EDITOR_CONDITION_COLOR, null, null),
      new RutaTextTools.SH(RutaPreferenceConstants.EDITOR_STRING_COLOR, null, null),
      new RutaTextTools.SH(RutaPreferenceConstants.EDITOR_VARIABLE_COLOR,
              RutaPreferenceConstants.EDITOR_CONDITION_COLOR, null) };

  public DefaultRutaSemanticHighlightingExtension() {
  }

  public HighlightedPosition[] calculatePositions(ASTNode node,
          ISemanticHighlightingRequestor requestor) {

    // Check Ruta procedures
    if (node instanceof MethodDeclaration) {
      MethodDeclaration m = (MethodDeclaration) node;
      requestor.addPosition(m.getNameStart(), m.getNameEnd(), RutaPreferenceConstants.EDITOR_FUNCTION_COLOR);

    }

    if (node instanceof Argument) {
      Argument m = (Argument) node;
      requestor.addPosition(m.getNameStart(), m.getNameEnd(), RutaPreferenceConstants.EDITOR_VARIABLE_COLOR);
    }

    if (node instanceof TypeDeclaration) {

      TypeDeclaration t = (TypeDeclaration) node;
      List children;

      // Handle base classes highlighting
      ASTListNode s = t.getSuperClasses();

      if (s != null && s.getChilds() != null) {
        children = s.getChilds();
        Iterator it = children.iterator();
        while (it.hasNext()) {
          ASTNode n = (ASTNode) it.next();
          requestor.addPosition(n.sourceStart(), n.sourceEnd(), RutaPreferenceConstants.EDITOR_DECLARATION_DEFINITION_COLOR);
        }
      }

      requestor.addPosition(t.getNameStart(), t.getNameEnd(), RutaPreferenceConstants.EDITOR_VARIABLE_COLOR);
    }

    return null;

  }

  public void processNode(ASTNode node, ISemanticHighlightingRequestor requestor) {
    calculatePositions(node, requestor);

  }

  public SemanticHighlighting[] getHighlightings() {
    return highlightings;
  }

  public void doOtherHighlighting(ISourceModule code,
          final ISemanticHighlightingRequestor semanticHighlightingRequestor) {
    ModuleDeclaration moduleDeclaration = SourceParserUtil
            .getModuleDeclaration((org.eclipse.dltk.core.ISourceModule) (code.getModelElement()));
    if (moduleDeclaration instanceof RutaModuleDeclaration) {
      RutaModuleDeclaration md = (RutaModuleDeclaration) moduleDeclaration;
      if (md != null) {

        // do highlightings

      }
    }
  }
}
