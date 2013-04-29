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
import org.apache.uima.textmarker.ide.ui.templates.TextMarkerTemplateCompletionProcessor;
import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalComputer;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;

public class TextMarkerTypeCompletionProposalComputer extends ScriptCompletionProposalComputer {

  @Override
  protected ScriptCompletionProposalCollector createCollector(
          ScriptContentAssistInvocationContext context) {
    ScriptCompletionProposalCollector collector = new TextMarkerCompletionProposalCollector(
            context.getSourceModule());

    collector.setIgnored(CompletionProposal.FIELD_REF, true);
    collector.setIgnored(CompletionProposal.KEYWORD, true);
    collector.setIgnored(CompletionProposal.LABEL_REF, true);
    collector.setIgnored(CompletionProposal.LOCAL_VARIABLE_REF, true);
    collector.setIgnored(CompletionProposal.METHOD_DECLARATION, true);
    collector.setIgnored(CompletionProposal.METHOD_NAME_REFERENCE, true);
    collector.setIgnored(CompletionProposal.METHOD_REF, true);
    collector.setIgnored(CompletionProposal.POTENTIAL_METHOD_DECLARATION, true);
    collector.setIgnored(CompletionProposal.VARIABLE_DECLARATION, true);

    collector.setIgnored(CompletionProposal.TYPE_REF, false);

    IPreferenceStore preferenceStore = TextMarkerIdePlugin.getDefault().getPreferenceStore();

    return collector;
  }

  @Override
  protected TemplateCompletionProcessor createTemplateProposalComputer(
          ScriptContentAssistInvocationContext context) {
    return new TextMarkerTemplateCompletionProcessor(context);
  }
}
