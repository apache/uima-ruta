package org.apache.uima.tm.cev.artifactViewer;

import java.util.Comparator;

import org.apache.uima.tm.cev.extension.ICEVArtifactViewerFactory;


public class ArtifactViewerComparator implements Comparator<ICEVArtifactViewerFactory> {

  public int compare(ICEVArtifactViewerFactory o1, ICEVArtifactViewerFactory o2) {
    if (o1.getPriority() < o2.getPriority()) {
      return -1;
    } else if (o1.getPriority() > o2.getPriority()) {
      return 1;
    } else {
      return 0;
    }
  }

}
