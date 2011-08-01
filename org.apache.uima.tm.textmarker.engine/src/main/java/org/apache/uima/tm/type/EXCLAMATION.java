/* First created by JCasGen Tue Apr 08 18:30:35 CEST 2008 */
package org.apache.uima.tm.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

/**
 * Updated by JCasGen Thu Apr 24 17:12:22 CEST 2008 XML source:
 * C:/work/workspace-tm/org.apache.uima.tm.textmarker.engine/desc/BasicTypeSystem.xml
 * 
 * @generated
 */
public class EXCLAMATION extends SENTENCEEND {
  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(EXCLAMATION.class);

  /**
   * @generated
   * @ordered
   */
  public final static int type = typeIndexID;

  /** @generated */
  @Override
  public int getTypeIndexID() {
    return typeIndexID;
  }

  /**
   * Never called. Disable default constructor
   * 
   * @generated
   */
  protected EXCLAMATION() {
  }

  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public EXCLAMATION(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }

  /** @generated */
  public EXCLAMATION(JCas jcas) {
    super(jcas);
    readObject();
  }

  /** @generated */
  public EXCLAMATION(JCas jcas, int begin, int end) {
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
