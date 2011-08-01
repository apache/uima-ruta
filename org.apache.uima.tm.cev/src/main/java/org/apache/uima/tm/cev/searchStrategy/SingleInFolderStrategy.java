package org.apache.uima.tm.cev.searchStrategy;

import java.util.LinkedList;

import org.apache.uima.tm.cev.extension.ICEVSearchStrategy;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;


public class SingleInFolderStrategy implements ICEVSearchStrategy {

  private int priority;

  public SingleInFolderStrategy(int priority) {
    super();
    this.priority = priority;
  }

  public IFile searchDescriptor(IFile file) {
    LinkedList<IFile> files = new LinkedList<IFile>();
    try {
      for (IResource r : file.getParent().members()) {
        if (r.getType() == IResource.FILE
                && r.getFullPath().getFileExtension().toLowerCase().equals("xml")
                && !r.getFullPath().removeFileExtension().lastSegment().toLowerCase().endsWith(
                        "stylemap"))
          files.add((IFile) r);
      }
    } catch (CoreException e) {
      return null;
    }
    if (files.size() == 1) {
      return files.getFirst();
    } else {
      return null;
    }
  }

  public int getPriority() {
    return priority;
  }

}
