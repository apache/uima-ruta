

/* First created by JCasGen Tue Aug 09 16:26:13 CEST 2011 */
package org.apache.uima.textmarker.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Wed Jan 11 14:42:26 CET 2012
 * XML source: D:/work/workspace-uima3/uimaj-ep-textmarker-engine/src/main/java/org/apache/uima/textmarker/engine/BasicTypeSystem.xml
 * @generated */
public class CW extends W {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(CW.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected CW() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public CW(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public CW(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public CW(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
}

    