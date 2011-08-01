package org.apache.uima.tm.cev.extension;

public abstract class AbstractArtifactViewerFactory implements ICEVArtifactViewerFactory {

  private int priority;

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;

  }

}
