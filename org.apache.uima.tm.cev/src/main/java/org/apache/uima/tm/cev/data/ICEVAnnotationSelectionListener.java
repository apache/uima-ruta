package org.apache.uima.tm.cev.data;

/**
 * Listener-Interface fuer Selektionen von Annotationen im Dokument
 * 
 * @author Marco Nehmeier
 */
public interface ICEVAnnotationSelectionListener {

  /**
   * Neue Auswahl im Dokument
   * 
   * @param offset
   *          Offset-Position
   */
  public void newSelection(int offset);
}
