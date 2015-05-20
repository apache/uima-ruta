

/* First created by JCasGen Sat Jun 08 19:25:02 CEST 2013 */
package org.apache.uima.ruta.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sat Jun 08 19:25:02 CEST 2013
 * XML source: D:/work/workspace-tmb5/ruta/ruta-core/src/main/resources/org/apache/uima/ruta/engine/InternalTypeSystem.xml
 * @generated */
public class RutaOptional extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(RutaOptional.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected RutaOptional() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public RutaOptional(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public RutaOptional(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public RutaOptional(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
}

    