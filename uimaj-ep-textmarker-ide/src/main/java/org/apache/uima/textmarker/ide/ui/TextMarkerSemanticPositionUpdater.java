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

package org.apache.uima.textmarker.ide.ui;

import org.apache.uima.textmarker.ide.core.TextMarkerNature;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.ui.editor.highlighting.ASTSemanticHighlighter;
import org.eclipse.dltk.ui.editor.highlighting.ISemanticHighlightingRequestor;


public class TextMarkerSemanticPositionUpdater extends ASTSemanticHighlighter {

  private final ISemanticHighlightingExtension[] extensions;

  private final ISemanticHighlightingRequestor[] requestors;

  private static class SemanticPositionRequestorExtension implements ISemanticHighlightingRequestor {

    private final ISemanticHighlightingRequestor requestor;

    private final int offset;

    /**
     * @param requestor
     * @param offset
     */
    public SemanticPositionRequestorExtension(ISemanticHighlightingRequestor requestor, int offset) {
      this.offset = offset;
      this.requestor = requestor;
    }

    public void addPosition(int start, int end, int highlightingIndex) {
      requestor.addPosition(start, end, highlightingIndex + offset);
    }

  }

  public TextMarkerSemanticPositionUpdater(ISemanticHighlightingExtension[] extensions) {
    this.extensions = extensions;
    this.requestors = new ISemanticHighlightingRequestor[extensions.length];
    int offset = 0;
    for (int i = 0; i < extensions.length; ++i) {
      requestors[i] = new SemanticPositionRequestorExtension(this, offset);
      offset += extensions[i].getHighlightings().length;
    }
  }

  @Override
  protected ASTVisitor createVisitor(org.eclipse.dltk.compiler.env.ISourceModule sourceCode)
          throws ModelException {
    return new ASTVisitor() {

      @Override
      public boolean visitGeneral(ASTNode node) throws Exception {
        for (int i = 0; i < extensions.length; i++) {
          extensions[i].processNode(node, requestors[i]);
        }
        return true;
      }

    };
  }

  @Override
  protected String getNature() {
    return TextMarkerNature.NATURE_ID;
  }
}
