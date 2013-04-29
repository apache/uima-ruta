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
import org.apache.uima.textmarker.ide.ui.text.TextMarkerTextTools;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.ui.editor.highlighting.ASTSemanticHighlighter;
import org.eclipse.dltk.ui.editor.highlighting.ISemanticHighlightingRequestor;
import org.eclipse.dltk.ui.editor.highlighting.SemanticHighlighting;


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

    public void addPosition(int start, int end, String highlightingKey) {
      requestor.addPosition(start, end, highlightingKey);
      
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
  protected String getNature() {
    return TextMarkerNature.NATURE_ID;
  }



  public SemanticHighlighting[] getSemanticHighlightings() {
   return  new SemanticHighlighting[] {
            new TextMarkerTextTools.SH(TextMarkerPreferenceConstants.EDITOR_DECLARATION_DEFINITION_COLOR,
                    null, null),
            new TextMarkerTextTools.SH(TextMarkerPreferenceConstants.EDITOR_FUNCTION_COLOR, null, null),
            new TextMarkerTextTools.SH(TextMarkerPreferenceConstants.EDITOR_ACTION_COLOR, null, null),
            new TextMarkerTextTools.SH(TextMarkerPreferenceConstants.EDITOR_CONDITION_COLOR, null, null),
            new TextMarkerTextTools.SH(TextMarkerPreferenceConstants.EDITOR_STRING_COLOR, null, null),
            new TextMarkerTextTools.SH(TextMarkerPreferenceConstants.EDITOR_VARIABLE_COLOR,
                    TextMarkerPreferenceConstants.EDITOR_CONDITION_COLOR, null) };
  }



  @Override
  protected boolean doHighlighting(IModuleSource code) throws Exception {
    return false;
  }
}
