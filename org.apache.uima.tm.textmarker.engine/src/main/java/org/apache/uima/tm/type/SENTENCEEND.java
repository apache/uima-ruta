/* First created by JCasGen Tue Apr 08 18:30:34 CEST 2008 */
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
public class SENTENCEEND extends PM {
  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(SENTENCEEND.class);

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
  protected SENTENCEEND() {
  }

  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public SENTENCEEND(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }

  /** @generated */
  public SENTENCEEND(JCas jcas) {
    super(jcas);
    readObject();
  }

  /** @generated */
  public SENTENCEEND(JCas jcas, int begin, int end) {
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
