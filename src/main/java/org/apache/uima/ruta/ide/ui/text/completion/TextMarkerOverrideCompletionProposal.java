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

package org.apache.uima.textmarker.ide.ui.text.completion;

import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.text.completion.ScriptTypeCompletionProposal;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContextInformation;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension4;
import org.eclipse.jface.text.contentassist.IContextInformation;

public class TextMarkerOverrideCompletionProposal extends ScriptTypeCompletionProposal implements
        ICompletionProposalExtension4 {

  private String methodName;

  public TextMarkerOverrideCompletionProposal(IScriptProject project, ISourceModule cu,
          String methodName, String[] paramTypes, int start, int length, String displayName,
          String completionProposal) {
    super(completionProposal, cu, start, length, null, displayName, 0);
    Assert.isNotNull(project);
    Assert.isNotNull(methodName);
    Assert.isNotNull(paramTypes);
    Assert.isNotNull(cu);

    this.methodName = methodName;

    setReplacementString(completionProposal);
  }

  @Override
  public CharSequence getPrefixCompletionText(IDocument document, int completionOffset) {
    return methodName;
  }

  @Override
  protected boolean updateReplacementString(IDocument document, char trigger, int offset)
          throws CoreException, BadLocationException {
    final IDocument buffer = new Document(document.get());
    int index = offset - 1;
    while (index >= 0 && Character.isJavaIdentifierPart(buffer.getChar(index)))
      index--;
    final int length = offset - index - 1;
    buffer.replace(index + 1, length, " "); //$NON-NLS-1$
    return true;
  }

  public boolean isAutoInsertable() {
    return false;
  }

  @Override
  public IContextInformation getContextInformation() {
    return new ContextInformation(getDisplayString(), getDisplayString());
  }

  @Override
  protected boolean insertCompletion() {
    IPreferenceStore preference = TextMarkerIdePlugin.getDefault().getPreferenceStore();
    return preference.getBoolean(PreferenceConstants.CODEASSIST_INSERT_COMPLETION);
  }
}
