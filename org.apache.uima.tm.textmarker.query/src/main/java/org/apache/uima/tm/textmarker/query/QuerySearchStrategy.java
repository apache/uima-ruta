package org.apache.uima.tm.textmarker.query;

import org.apache.uima.tm.cev.extension.ICEVSearchStrategy;
import org.eclipse.core.resources.IFile;


public class QuerySearchStrategy implements ICEVSearchStrategy {

  private int priority;

  public QuerySearchStrategy(int priority) {

    this.priority = priority;
  }

  @Override
  public int getPriority() {
    return priority;
  }

  @Override
  public IFile searchDescriptor(IFile file) {
    // TODO: add strategy
    return null;
  }

}
