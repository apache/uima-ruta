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

package org.apache.uima.tm.dltk.internal.ui;

import java.util.Iterator;
import java.util.List;

import org.apache.uima.tm.dltk.internal.ui.text.TextMarkerTextTools;
import org.apache.uima.tm.dltk.parser.ast.TextMarkerModuleDeclaration;
import org.apache.uima.tm.dltk.ui.TextMarkerPreferenceConstants;
import org.apache.uima.tm.dltk.ui.semantichighlighting.ISemanticHighlightingExtension;
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


public class DefaultTextMarkerSemanticHighlightingExtension implements
        ISemanticHighlightingExtension {

  private SemanticHighlighting[] highlightings = new SemanticHighlighting[] {
      new TextMarkerTextTools.SH(TextMarkerPreferenceConstants.EDITOR_DECLARATION_DEFINITION_COLOR,
              null, null),
      new TextMarkerTextTools.SH(TextMarkerPreferenceConstants.EDITOR_FUNCTION_COLOR, null, null),
      new TextMarkerTextTools.SH(TextMarkerPreferenceConstants.EDITOR_ACTION_COLOR, null, null),
      new TextMarkerTextTools.SH(TextMarkerPreferenceConstants.EDITOR_CONDITION_COLOR, null, null),
      new TextMarkerTextTools.SH(TextMarkerPreferenceConstants.EDITOR_STRING_COLOR, null, null),
      new TextMarkerTextTools.SH(TextMarkerPreferenceConstants.EDITOR_VARIABLE_COLOR,
              TextMarkerPreferenceConstants.EDITOR_CONDITION_COLOR, null) };

  private static final int HL_PROCEDURES = 0;

  private static final int HL_ARGUMENTS = 1;

  private static final int HL_CLASSES = 2;

  private static final int HL_BASE_CLASSES = 3;

  private static final int HL_STRINGS = 4;

  private static final int HL_VARIABLES = 5;

  public DefaultTextMarkerSemanticHighlightingExtension() {
  }

  public HighlightedPosition[] calculatePositions(ASTNode node,
          ISemanticHighlightingRequestor requestor) {

    // Check TextMarker procedures
    if (node instanceof MethodDeclaration) {

      MethodDeclaration m = (MethodDeclaration) node;
      requestor.addPosition(m.getNameStart(), m.getNameEnd(), HL_PROCEDURES);

    }

    if (node instanceof Argument) {
      Argument m = (Argument) node;
      requestor.addPosition(m.getNameStart(), m.getNameEnd(), HL_ARGUMENTS);

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

          requestor.addPosition(n.sourceStart(), n.sourceEnd(), HL_BASE_CLASSES);

        }
      }

      requestor.addPosition(t.getNameStart(), t.getNameEnd(), HL_CLASSES);
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
    if (moduleDeclaration instanceof TextMarkerModuleDeclaration) {
      TextMarkerModuleDeclaration md = (TextMarkerModuleDeclaration) moduleDeclaration;
      if (md != null) {

        // do highlightings

      }
    }
  }
}
