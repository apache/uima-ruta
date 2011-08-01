package org.apache.uima.tm.cev.extension;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.cev.data.CEVData;
import org.eclipse.swt.graphics.Point;


public interface ICEVArtifactViewer {

  void annotationStateChanged(Type type);

  void annotationStateChanged();

  Point getViewerSelectionRange();

  void dispose();

  void viewerWidgetSelected();

  void moveToAnnotation(AnnotationFS annot);

  void viewChanged(CEVData casData);

  int getOffsetAtLocation(Point point);

}
