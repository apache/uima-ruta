

/* First created by JCasGen Wed Jan 11 14:42:22 CET 2012 */
package org.apache.uima.textmarker.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Jan 11 14:42:26 CET 2012
 * XML source: D:/work/workspace-uima3/uimaj-ep-textmarker-engine/src/main/java/org/apache/uima/textmarker/engine/BasicTypeSystem.xml
 * @generated */
public class TokenSeed extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(TokenSeed.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected TokenSeed() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public TokenSeed(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public TokenSeed(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public TokenSeed(JCas jcas, int begin, int end) {
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

    