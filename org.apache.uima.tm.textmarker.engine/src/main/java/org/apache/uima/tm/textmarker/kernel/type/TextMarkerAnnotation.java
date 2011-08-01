/* First created by JCasGen Wed Apr 16 17:07:18 CEST 2008 */
package org.apache.uima.tm.textmarker.kernel.type;

import java.util.List;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

/** 
 * Updated by JCasGen Tue Jan 25 09:29:36 CET 2011
 * XML source: D:/work/workspace-tm/org.apache.uima.tm.textmarker.engine/desc/InternalTypeSystem.xml
 * @generated */
public class TextMarkerAnnotation extends Annotation {

  public TextMarkerAnnotation(String type2, List<TextMarkerBasic> list) {
  }

  public List<TextMarkerBasic> getAssociatedSymbols() {
    return null;
  }

  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(TextMarkerAnnotation.class);

  /**
   * @generated
   * @ordered
   */
  public final static int type = typeIndexID;

  /** @generated */
  @Override
  public int getTypeIndexID() {return typeIndexID;}
 
  /**
   * Never called. Disable default constructor
   * 
   * @generated
   */
  protected TextMarkerAnnotation() {}
    
  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
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

  /** <!-- begin-user-doc --> Write your own initialization here <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {
  }

  // *--------------*
  // * Feature: Score

  /**
   * getter for Score - gets
   * 
   * @generated
   */
  public double getScore() {
    if (TextMarkerAnnotation_Type.featOkTst && ((TextMarkerAnnotation_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "org.apache.uima.tm.textmarker.kernel.type.TextMarkerAnnotation");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((TextMarkerAnnotation_Type)jcasType).casFeatCode_score);}
    
  /**
   * setter for Score - sets
   * 
   * @generated
   */
  public void setScore(double v) {
    if (TextMarkerAnnotation_Type.featOkTst && ((TextMarkerAnnotation_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "org.apache.uima.tm.textmarker.kernel.type.TextMarkerAnnotation");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((TextMarkerAnnotation_Type)jcasType).casFeatCode_score, v);}    
   
    
  // *--------------*
  // * Feature: Annotation

  /**
   * getter for Annotation - gets
   * 
   * @generated
   */
  public Annotation getAnnotation() {
    if (TextMarkerAnnotation_Type.featOkTst && ((TextMarkerAnnotation_Type)jcasType).casFeat_annotation == null)
      jcasType.jcas.throwFeatMissing("annotation", "org.apache.uima.tm.textmarker.kernel.type.TextMarkerAnnotation");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((TextMarkerAnnotation_Type)jcasType).casFeatCode_annotation)));}
    
  /**
   * setter for Annotation - sets
   * 
   * @generated
   */
  public void setAnnotation(Annotation v) {
    if (TextMarkerAnnotation_Type.featOkTst && ((TextMarkerAnnotation_Type)jcasType).casFeat_annotation == null)
      jcasType.jcas.throwFeatMissing("annotation", "org.apache.uima.tm.textmarker.kernel.type.TextMarkerAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((TextMarkerAnnotation_Type)jcasType).casFeatCode_annotation, jcasType.ll_cas.ll_getFSRef(v));}    
  }
