package org.apache.uima.tm.dltk.internal.ui.text.completion;

import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.apache.uima.tm.dltk.internal.ui.templates.TextMarkerTemplateCompletionProcessor;
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
    ScriptCompletionProposalCollector collector = new TextMarkerCompletionProposalCollector(context
            .getSourceModule());

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

    IPreferenceStore preferenceStore = TextMarkerUI.getDefault().getPreferenceStore();

    return collector;
  }

  @Override
  protected TemplateCompletionProcessor createTemplateProposalComputer(
          ScriptContentAssistInvocationContext context) {
    return new TextMarkerTemplateCompletionProcessor(context);
  }
}
