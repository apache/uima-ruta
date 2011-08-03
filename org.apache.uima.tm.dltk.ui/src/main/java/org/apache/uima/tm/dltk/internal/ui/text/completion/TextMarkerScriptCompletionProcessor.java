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

package org.apache.uima.tm.dltk.internal.ui.text.completion;

import org.apache.uima.tm.dltk.core.TextMarkerNature;
import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.eclipse.dltk.ui.text.completion.CompletionProposalLabelProvider;
import org.eclipse.dltk.ui.text.completion.ContentAssistInvocationContext;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationPresenter;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.ui.IEditorPart;


public class TextMarkerScriptCompletionProcessor extends ScriptCompletionProcessor {
  public TextMarkerScriptCompletionProcessor(IEditorPart editor, ContentAssistant assistant,
          String partition) {
    super(editor, assistant, partition);
  }

  @Override
  protected IPreferenceStore getPreferenceStore() {
    return TextMarkerUI.getDefault().getPreferenceStore();
  }

  @Override
  protected ContentAssistInvocationContext createContext(ITextViewer viewer, int offset) {
    return new ScriptContentAssistInvocationContext(viewer, offset, fEditor,
            TextMarkerNature.NATURE_ID) {
      @Override
      protected CompletionProposalLabelProvider createLabelProvider() {
        return new TextMarkerCompletionProposalLabelProvider();
      }
    };
  }

  @Override
  protected String getNatureId() {
    return TextMarkerNature.NATURE_ID;
  }

  protected static class Validator implements IContextInformationValidator,
          IContextInformationPresenter {

    private int initialOffset;

    public boolean isContextInformationValid(int offset) {
      return Math.abs(offset - initialOffset) < 5;
    }

    public void install(IContextInformation info, ITextViewer viewer, int offset) {
      initialOffset = offset;
    }

    public boolean updatePresentation(int documentPosition, TextPresentation presentation) {
      return false;
    }
  }

  @Override
  protected CompletionProposalLabelProvider getProposalLabelProvider() {
    return new TextMarkerCompletionProposalLabelProvider();
  }

  private IContextInformationValidator validator;

  @Override
  public IContextInformationValidator getContextInformationValidator() {
    if (validator == null) {
      validator = new Validator();
    }
    return validator;
  }
}
