package org.apache.uima.tm.cev.data;

import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;

/**
 * Interface fuer einen Listener der Annotationen im CEVData ueberwacht
 * 
 * @author Marco Nehmeier
 */
public interface ICEVAnnotationListener {

  /**
   * Annotation hinzugefuegt
   * 
   * @param annots
   *          Annotation
   */
  public void annotationsAdded(List<AnnotationFS> annots);

  /**
   * Annotation entfernt
   * 
   * @param annots
   *          Annotation
   */
  public void annotationsRemoved(List<AnnotationFS> annots);

  /**
   * Farbe hat sich geaendert
   * 
   * @param type
   *          Annotations-Typ
   */
  public void colorChanged(Type type);

  /**
   * Aenderung an einem Annotations-Typ
   * 
   * @param type
   *          Annotations-Typ
   */
  public void annotationStateChanged(Type type);

  /**
   * Aenderung an einer Annotation
   * 
   * @param annot
   *          Annotation
   */
  public void annotationStateChanged(AnnotationFS annot);
}
