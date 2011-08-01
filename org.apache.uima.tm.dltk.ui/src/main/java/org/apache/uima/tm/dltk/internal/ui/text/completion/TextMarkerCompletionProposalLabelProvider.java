package org.apache.uima.tm.dltk.internal.ui.text.completion;

import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.ui.text.completion.CompletionProposalLabelProvider;

public class TextMarkerCompletionProposalLabelProvider extends CompletionProposalLabelProvider {
  @Override
  protected String createMethodProposalLabel(CompletionProposal methodProposal) {
    StringBuffer nameBuffer = new StringBuffer();

    // method name
    nameBuffer.append(methodProposal.getName());

    // parameters
    nameBuffer.append('(');
    appendUnboundedParameterList(nameBuffer, methodProposal);
    nameBuffer.append(')');

    return nameBuffer.toString();
  }

  @Override
  protected String createOverrideMethodProposalLabel(CompletionProposal methodProposal) {
    StringBuffer nameBuffer = new StringBuffer();

    // method name
    nameBuffer.append(methodProposal.getName());

    // parameters
    nameBuffer.append('(');
    appendUnboundedParameterList(nameBuffer, methodProposal);
    nameBuffer.append(")  "); //$NON-NLS-1$

    return nameBuffer.toString();
  }
}
