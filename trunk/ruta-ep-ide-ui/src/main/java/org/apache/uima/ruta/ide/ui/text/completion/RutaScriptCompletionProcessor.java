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

package org.apache.uima.ruta.ide.ui.text.completion;

import org.apache.uima.ruta.ide.core.RutaNature;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProcessor;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationPresenter;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.ui.IEditorPart;

public class RutaScriptCompletionProcessor extends ScriptCompletionProcessor {
  public RutaScriptCompletionProcessor(IEditorPart editor, ContentAssistant assistant,
          String partition) {
    super(editor, assistant, partition);
  }

  @Override
  protected String getNatureId() {
    return RutaNature.NATURE_ID;
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

  private IContextInformationValidator validator;

  @Override
  public IContextInformationValidator getContextInformationValidator() {
    if (validator == null) {
      validator = new Validator();
    }
    return validator;
  }
}
