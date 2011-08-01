package org.apache.uima.tm.dltk.internal.core.packages;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.builder.AbstractBuildParticipantType;
import org.eclipse.dltk.core.builder.IBuildParticipant;

public class TextMarkerPackageCheckerType extends AbstractBuildParticipantType {

  @Override
  public IBuildParticipant createBuildParticipant(IScriptProject project) throws CoreException {
    try {
      return new TextMarkerCheckBuilder(project);
    } catch (IllegalStateException e) {
      return null;
    }
  }
}
