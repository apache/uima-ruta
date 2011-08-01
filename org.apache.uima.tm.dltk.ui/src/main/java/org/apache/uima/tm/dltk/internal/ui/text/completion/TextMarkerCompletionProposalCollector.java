package org.apache.uima.tm.dltk.internal.ui.text.completion;

import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.text.completion.CompletionProposalLabelProvider;
import org.eclipse.dltk.ui.text.completion.IScriptCompletionProposal;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposal;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.swt.graphics.Image;

public class TextMarkerCompletionProposalCollector extends ScriptCompletionProposalCollector {

  protected final static char[] VAR_TRIGGER = new char[] { '\t', ' ', '=', ';', '.' };

  @Override
  protected char[] getVarTrigger() {
    return VAR_TRIGGER;
  }

  public TextMarkerCompletionProposalCollector(ISourceModule module) {
    super(module);
  }

  // Label provider
  @Override
  protected CompletionProposalLabelProvider createLabelProvider() {
    return new TextMarkerCompletionProposalLabelProvider();
  }

  // Invocation context
  @Override
  protected ScriptContentAssistInvocationContext createScriptContentAssistInvocationContext(
          ISourceModule sourceModule) {
    return new ScriptContentAssistInvocationContext(sourceModule) {
      @Override
      protected CompletionProposalLabelProvider createLabelProvider() {
        return new TextMarkerCompletionProposalLabelProvider();
      }
    };
  }

  // Specific proposals creation. May be use factory?
  @Override
  protected ScriptCompletionProposal createScriptCompletionProposal(String completion,
          int replaceStart, int length, Image image, String displayString, int i) {
    return new TextMarkerScriptCompletionProposal(displayString, replaceStart, length, image,
            displayString, i);
  }

  @Override
  protected ScriptCompletionProposal createScriptCompletionProposal(String completion,
          int replaceStart, int length, Image image, String displayString, int i, boolean isInDoc) {
    return new TextMarkerScriptCompletionProposal(displayString, replaceStart, length, image,
            displayString, i, isInDoc);
  }

  @Override
  protected ScriptCompletionProposal createOverrideCompletionProposal(IScriptProject scriptProject,
          ISourceModule compilationUnit, String name, String[] paramTypes, int start, int length,
          String displayName, String completionProposal) {
    return new TextMarkerOverrideCompletionProposal(scriptProject, compilationUnit, name,
            paramTypes, start, length, displayName, completionProposal);
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
}
