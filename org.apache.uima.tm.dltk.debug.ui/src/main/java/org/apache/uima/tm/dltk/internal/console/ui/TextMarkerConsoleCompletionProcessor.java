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

package org.apache.uima.tm.dltk.internal.console.ui;

import java.io.IOException;
import java.util.List;

import org.eclipse.dltk.console.IScriptConsoleShell;
import org.eclipse.dltk.console.ScriptConsoleCompletionProposal;
import org.eclipse.dltk.console.ui.IScriptConsoleViewer;
import org.eclipse.dltk.console.ui.ScriptConsoleCompletionProcessor;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationPresenter;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.swt.graphics.Image;

public class TextMarkerConsoleCompletionProcessor extends ScriptConsoleCompletionProcessor {

  protected static class Validator implements IContextInformationValidator,
          IContextInformationPresenter {

    protected int installOffset;

    public boolean isContextInformationValid(int offset) {
      return Math.abs(installOffset - offset) < 5;
    }

    public void install(IContextInformation info, ITextViewer viewer, int offset) {
      installOffset = offset;
    }

    public boolean updatePresentation(int documentPosition, TextPresentation presentation) {
      return false;
    }
  }

  protected IProposalDecorator tmDecorator = new IProposalDecorator() {
    public String formatProposal(ScriptConsoleCompletionProposal c) {
      return c.getDisplay();
    }

    public Image getImage(ScriptConsoleCompletionProposal c) {
      String type = c.getType();
      if (type.equals("var")) {
        return DLTKPluginImages.get(DLTKPluginImages.IMG_OBJS_LOCAL_VARIABLE);
      } else if (type.equals("proc")) {
        return DLTKPluginImages.get(DLTKPluginImages.IMG_METHOD_PUBLIC);
      } else if (type.equals("command")) {
        return DLTKPluginImages.get(DLTKPluginImages.IMG_METHOD_PRIVATE);
      } else if (type.equals("func")) {
        return DLTKPluginImages.get(DLTKPluginImages.IMG_OBJS_FIELD);
      }

      return null;
    }
  };

  private IContextInformationValidator validator;

  public TextMarkerConsoleCompletionProcessor(IScriptConsoleShell interpreterShell) {
    super(interpreterShell);
  }

  @Override
  public char[] getCompletionProposalAutoActivationCharacters() {
    return new char[] { '$' };
  }

  @Override
  protected ICompletionProposal[] computeCompletionProposalsImpl(IScriptConsoleViewer viewer,
          int offset) {

    try {
      String commandLine = viewer.getCommandLine();
      int cursorPosition = offset - viewer.getCommandLineOffset();

      List list = getInterpreterShell().getCompletions(commandLine, cursorPosition);

      List proposals = createProposalsFromString(list, offset, tmDecorator);

      return (ICompletionProposal[]) proposals.toArray(new ICompletionProposal[proposals.size()]);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return new ICompletionProposal[] {};
  }

  @Override
  protected IContextInformation[] computeContextInformationImpl(ITextViewer viewer, int offset) {
    return null;
  }

  public IContextInformationValidator getContextInformationValidator() {
    if (validator == null) {
      validator = new Validator();
    }

    return validator;
  }
}
