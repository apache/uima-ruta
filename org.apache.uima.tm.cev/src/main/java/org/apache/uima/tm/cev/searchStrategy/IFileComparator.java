package org.apache.uima.tm.cev.searchStrategy;

import java.util.Comparator;

import org.eclipse.core.resources.IFile;

public class IFileComparator implements Comparator<IFile> {

  @Override
  public int compare(IFile o1, IFile o2) {
    if (o1 == null || o2 == null)
      return 0;
    return o1.getName().compareTo(o2.getName());
  }

}
