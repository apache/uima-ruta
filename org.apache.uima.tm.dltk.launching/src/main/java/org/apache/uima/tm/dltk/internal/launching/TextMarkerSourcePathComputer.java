package org.apache.uima.tm.dltk.internal.launching;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.sourcelookup.ISourceContainer;
import org.eclipse.debug.core.sourcelookup.ISourcePathComputerDelegate;

public class TextMarkerSourcePathComputer implements ISourcePathComputerDelegate {

  public TextMarkerSourcePathComputer() {
  }

  public ISourceContainer[] computeSourceContainers(ILaunchConfiguration configuration,
          IProgressMonitor monitor) throws CoreException {
    List containers = new ArrayList();
    return (ISourceContainer[]) containers.toArray(new ISourceContainer[containers.size()]);
  }
}
