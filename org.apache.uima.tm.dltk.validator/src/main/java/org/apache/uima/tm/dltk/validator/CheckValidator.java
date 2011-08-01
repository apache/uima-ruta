package org.apache.uima.tm.dltk.validator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.builder.AbstractBuildParticipantType;
import org.eclipse.dltk.core.builder.IBuildParticipant;

public class CheckValidator extends AbstractBuildParticipantType {

  private static final String ID = "org.apache.uima.tm.dltk.validator.checkvalidator"; //$NON-NLS-1$

  private static final String NAME = "TextMarker Checker"; //$NON-NLS-1$

  @Override
  public IBuildParticipant createBuildParticipant(IScriptProject project) throws CoreException {
    return new TextMarkerChecker(project);
  }

}
