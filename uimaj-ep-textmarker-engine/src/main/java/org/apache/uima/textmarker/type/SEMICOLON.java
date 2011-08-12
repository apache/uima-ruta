

/* First created by JCasGen Tue Aug 09 16:26:13 CEST 2011 */
package org.apache.uima.textmarker.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Tue Aug 09 16:26:13 CEST 2011
 * XML source: D:/work/workspace-uima2/uimaj-ep-textmarker-engine/desc/BasicTypeSystem.xml
 * @generated */
public class SEMICOLON extends PM {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SEMICOLON.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SEMICOLON() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SEMICOLON(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SEMICOLON(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SEMICOLON(JCas jcas, int begin, int end) {
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

    