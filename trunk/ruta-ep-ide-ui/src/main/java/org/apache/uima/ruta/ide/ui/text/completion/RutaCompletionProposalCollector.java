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
import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.text.completion.IScriptCompletionProposal;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposal;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.swt.graphics.Image;

public class RutaCompletionProposalCollector extends ScriptCompletionProposalCollector {

  protected final static char[] VAR_TRIGGER = new char[] { '\t', ' ', '=', ';', '.' };

  @Override
  protected char[] getVarTrigger() {
    return VAR_TRIGGER;
  }

  public RutaCompletionProposalCollector(ISourceModule module) {
    super(module);
  }

  // Specific proposals creation. May be use factory?
  @Override
  protected ScriptCompletionProposal createScriptCompletionProposal(String completion,
          int replaceStart, int length, Image image, String displayString, int i) {
    return new RutaScriptCompletionProposal(completion, replaceStart, length, image, null, i);
  }

  @Override
  protected ScriptCompletionProposal createScriptCompletionProposal(String completion,
          int replaceStart, int length, Image image, String displayString, int i, boolean isInDoc) {
    return new RutaScriptCompletionProposal(displayString, replaceStart, length, image,
            displayString, i, isInDoc);
  }

  @Override
  protected ScriptCompletionProposal createOverrideCompletionProposal(IScriptProject scriptProject,
          ISourceModule compilationUnit, String name, String[] paramTypes, int start, int length,
          String displayName, String completionProposal) {
    return new RutaOverrideCompletionProposal(scriptProject, compilationUnit, name, paramTypes,
            start, length, displayName, completionProposal);
  }

  @Override
  protected IScriptCompletionProposal createKeywordProposal(CompletionProposal proposal) {
    String completion = String.valueOf(proposal.getCompletion());
    int start = proposal.getReplaceStart();
    int length = getLength(proposal);
    String label = getLabelProvider().createSimpleLabel(proposal);
    Image img = getImage(getLabelProvider().createMethodImageDescriptor(proposal));
    int relevance = computeRelevance(proposal);
    return createScriptCompletionProposal(completion, start, length, img, label, relevance);
  }

  @Override
  protected String getNatureId() {
    return RutaNature.NATURE_ID;
  }
}
