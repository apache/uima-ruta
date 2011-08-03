package org.apache.uima.tm.cev.data;

import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;

public interface ICEVAnnotationListener {

  public void annotationsAdded(List<AnnotationFS> annots);

  public void annotationsRemoved(List<AnnotationFS> annots);

  public void colorChanged(Type type);

  public void annotationStateChanged(Type type);

  public void annotationStateChanged(AnnotationFS annot);
}
