package org.apache.uima.tm.cev.extension;

import org.eclipse.core.resources.IFile;

public interface ICEVSearchStrategy {

  IFile searchDescriptor(IFile file);

  int getPriority();
}
