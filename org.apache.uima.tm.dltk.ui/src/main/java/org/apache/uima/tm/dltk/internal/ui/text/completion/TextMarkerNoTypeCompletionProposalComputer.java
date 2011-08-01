package org.apache.uima.tm.dltk.internal.ui.text.completion;

import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.apache.uima.tm.dltk.internal.ui.templates.TextMarkerTemplateCompletionProcessor;
import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalComputer;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;


public class TextMarkerNoTypeCompletionProposalComputer extends ScriptCompletionProposalComputer {

  @Override
  protected ScriptCompletionProposalCollector createCollector(
          ScriptContentAssistInvocationContext context) {
    ScriptCompletionProposalCollector collector = new TextMarkerCompletionProposalCollector(context
            .getSourceModule());

    collector.setIgnored(CompletionProposal.FIELD_REF, false);
    collector.setIgnored(CompletionProposal.KEYWORD, false);
    collector.setIgnored(CompletionProposal.PACKAGE_REF, false);
    collector.setIgnored(CompletionProposal.LABEL_REF, false);
    collector.setIgnored(CompletionProposal.LOCAL_VARIABLE_REF, false);
    collector.setIgnored(CompletionProposal.METHOD_DECLARATION, false);
    collector.setIgnored(CompletionProposal.METHOD_NAME_REFERENCE, false);
    collector.setIgnored(CompletionProposal.METHOD_REF, false);
    collector.setIgnored(CompletionProposal.POTENTIAL_METHOD_DECLARATION, false);
    collector.setIgnored(CompletionProposal.VARIABLE_DECLARATION, false);

    // collector.setIgnored(CompletionProposal.TYPE_REF, true);
    collector.setIgnored(CompletionProposal.TYPE_REF, false);
    IPreferenceStore preferenceStore = TextMarkerUI.getDefault().getPreferenceStore();

    return collector;
  }

  @Override
  protected TemplateCompletionProcessor createTemplateProposalComputer(
          ScriptContentAssistInvocationContext context) {
    return new TextMarkerTemplateCompletionProcessor(context);
  }

}
