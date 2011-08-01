package org.apache.uima.tm.cev.artifactViewer;

import org.apache.uima.cas.CAS;
import org.apache.uima.tm.cev.data.CEVDocument;


public interface ArtifactModifier {

  CAS modifyCas(CEVDocument casDoc, CAS newCas, int index);

}
