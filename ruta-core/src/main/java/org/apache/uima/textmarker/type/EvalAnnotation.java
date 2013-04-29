

/* First created by JCasGen Tue Aug 09 16:26:13 CEST 2011 */
package org.apache.uima.textmarker.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Jul 12 10:42:33 CEST 2012
 * XML source: D:/work/workspace-uima6/uimaj-textmarker/src/main/java/org/apache/uima/textmarker/engine/InternalTypeSystem.xml
 * @generated */
public class EvalAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(EvalAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected EvalAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public EvalAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public EvalAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public EvalAnnotation(JCas jcas, int begin, int end) {
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
     
 
    
  //*--------------*
  //* Feature: original

  /** getter for original - gets 
   * @generated */
  public Annotation getOriginal() {
    if (EvalAnnotation_Type.featOkTst && ((EvalAnnotation_Type)jcasType).casFeat_original == null)
      jcasType.jcas.throwFeatMissing("original", "org.apache.uima.textmarker.type.EvalAnnotation");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((EvalAnnotation_Type)jcasType).casFeatCode_original)));}
    
  /** setter for original - sets  
   * @generated */
  public void setOriginal(Annotation v) {
    if (EvalAnnotation_Type.featOkTst && ((EvalAnnotation_Type)jcasType).casFeat_original == null)
      jcasType.jcas.throwFeatMissing("original", "org.apache.uima.textmarker.type.EvalAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((EvalAnnotation_Type)jcasType).casFeatCode_original, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    