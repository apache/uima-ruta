

/* First created by JCasGen Tue Aug 09 16:26:13 CEST 2011 */
package org.apache.uima.textmarker.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Jan 11 14:42:26 CET 2012
 * XML source: D:/work/workspace-uima3/uimaj-ep-textmarker-engine/src/main/java/org/apache/uima/textmarker/engine/BasicTypeSystem.xml
 * @generated */
public class TextMarkerAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(TextMarkerAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected TextMarkerAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public TextMarkerAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public TextMarkerAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public TextMarkerAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: score

  /** getter for score - gets 
   * @generated */
  public double getScore() {
    if (TextMarkerAnnotation_Type.featOkTst && ((TextMarkerAnnotation_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "org.apache.uima.textmarker.type.TextMarkerAnnotation");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((TextMarkerAnnotation_Type)jcasType).casFeatCode_score);}
    
  /** setter for score - sets  
   * @generated */
  public void setScore(double v) {
    if (TextMarkerAnnotation_Type.featOkTst && ((TextMarkerAnnotation_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "org.apache.uima.textmarker.type.TextMarkerAnnotation");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((TextMarkerAnnotation_Type)jcasType).casFeatCode_score, v);}    
   
    
  //*--------------*
  //* Feature: annotation

  /** getter for annotation - gets 
   * @generated */
  public Annotation getAnnotation() {
    if (TextMarkerAnnotation_Type.featOkTst && ((TextMarkerAnnotation_Type)jcasType).casFeat_annotation == null)
      jcasType.jcas.throwFeatMissing("annotation", "org.apache.uima.textmarker.type.TextMarkerAnnotation");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((TextMarkerAnnotation_Type)jcasType).casFeatCode_annotation)));}
    
  /** setter for annotation - sets  
   * @generated */
  public void setAnnotation(Annotation v) {
    if (TextMarkerAnnotation_Type.featOkTst && ((TextMarkerAnnotation_Type)jcasType).casFeat_annotation == null)
      jcasType.jcas.throwFeatMissing("annotation", "org.apache.uima.textmarker.type.TextMarkerAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((TextMarkerAnnotation_Type)jcasType).casFeatCode_annotation, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    