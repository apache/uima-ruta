
/* First created by JCasGen Tue Aug 09 16:26:13 CEST 2011 */
package org.apache.uima.ruta.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

/**
 * Updated by JCasGen Thu Jul 12 10:42:34 CEST 2012 XML source:
 * D:/work/workspace-uima6/uimaj-ruta/src
 * /main/java/org/apache/uima/ruta/engine/InternalTypeSystem.xml
 * 
 * @generated
 */
public class RutaFrame extends Annotation {
  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(RutaFrame.class);

  /**
   * @generated
   * @ordered
   */
  public final static int type = typeIndexID;

  /** @generated */
  public int getTypeIndexID() {
    return typeIndexID;
  }

  /**
   * Never called. Disable default constructor
   * 
   * @generated
   */
  protected RutaFrame() {/* intentionally empty block */
  }

  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public RutaFrame(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }

  /** @generated */
  public RutaFrame(JCas jcas) {
    super(jcas);
    readObject();
  }

  /** @generated */
  public RutaFrame(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }

  /**
   * <!-- begin-user-doc --> Write your own initialization here <!-- end-user-doc -->
   * 
   * @generated modifiable
   */
  private void readObject() {
  }

}
