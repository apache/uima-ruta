

/* First created by JCasGen Tue Aug 09 16:26:13 CEST 2011 */
package org.apache.uima.textmarker.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Sep 26 19:07:28 CEST 2011
 * XML source: D:/work/workspace-uima3/uimaj-ep-textmarker-engine/desc/InternalTypeSystem.xml
 * @generated */
public class TextMarkerColoring extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(TextMarkerColoring.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected TextMarkerColoring() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public TextMarkerColoring(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public TextMarkerColoring(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public TextMarkerColoring(JCas jcas, int begin, int end) {
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
  //* Feature: bgColor

  /** getter for bgColor - gets 
   * @generated */
  public String getBgColor() {
    if (TextMarkerColoring_Type.featOkTst && ((TextMarkerColoring_Type)jcasType).casFeat_bgColor == null)
      jcasType.jcas.throwFeatMissing("bgColor", "org.apache.uima.textmarker.type.TextMarkerColoring");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TextMarkerColoring_Type)jcasType).casFeatCode_bgColor);}
    
  /** setter for bgColor - sets  
   * @generated */
  public void setBgColor(String v) {
    if (TextMarkerColoring_Type.featOkTst && ((TextMarkerColoring_Type)jcasType).casFeat_bgColor == null)
      jcasType.jcas.throwFeatMissing("bgColor", "org.apache.uima.textmarker.type.TextMarkerColoring");
    jcasType.ll_cas.ll_setStringValue(addr, ((TextMarkerColoring_Type)jcasType).casFeatCode_bgColor, v);}    
   
    
  //*--------------*
  //* Feature: targetType

  /** getter for targetType - gets 
   * @generated */
  public String getTargetType() {
    if (TextMarkerColoring_Type.featOkTst && ((TextMarkerColoring_Type)jcasType).casFeat_targetType == null)
      jcasType.jcas.throwFeatMissing("targetType", "org.apache.uima.textmarker.type.TextMarkerColoring");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TextMarkerColoring_Type)jcasType).casFeatCode_targetType);}
    
  /** setter for targetType - sets  
   * @generated */
  public void setTargetType(String v) {
    if (TextMarkerColoring_Type.featOkTst && ((TextMarkerColoring_Type)jcasType).casFeat_targetType == null)
      jcasType.jcas.throwFeatMissing("targetType", "org.apache.uima.textmarker.type.TextMarkerColoring");
    jcasType.ll_cas.ll_setStringValue(addr, ((TextMarkerColoring_Type)jcasType).casFeatCode_targetType, v);}    
   
    
  //*--------------*
  //* Feature: fgColor

  /** getter for fgColor - gets 
   * @generated */
  public String getFgColor() {
    if (TextMarkerColoring_Type.featOkTst && ((TextMarkerColoring_Type)jcasType).casFeat_fgColor == null)
      jcasType.jcas.throwFeatMissing("fgColor", "org.apache.uima.textmarker.type.TextMarkerColoring");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TextMarkerColoring_Type)jcasType).casFeatCode_fgColor);}
    
  /** setter for fgColor - sets  
   * @generated */
  public void setFgColor(String v) {
    if (TextMarkerColoring_Type.featOkTst && ((TextMarkerColoring_Type)jcasType).casFeat_fgColor == null)
      jcasType.jcas.throwFeatMissing("fgColor", "org.apache.uima.textmarker.type.TextMarkerColoring");
    jcasType.ll_cas.ll_setStringValue(addr, ((TextMarkerColoring_Type)jcasType).casFeatCode_fgColor, v);}    
   
    
  //*--------------*
  //* Feature: selected

  /** getter for selected - gets 
   * @generated */
  public boolean getSelected() {
    if (TextMarkerColoring_Type.featOkTst && ((TextMarkerColoring_Type)jcasType).casFeat_selected == null)
      jcasType.jcas.throwFeatMissing("selected", "org.apache.uima.textmarker.type.TextMarkerColoring");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((TextMarkerColoring_Type)jcasType).casFeatCode_selected);}
    
  /** setter for selected - sets  
   * @generated */
  public void setSelected(boolean v) {
    if (TextMarkerColoring_Type.featOkTst && ((TextMarkerColoring_Type)jcasType).casFeat_selected == null)
      jcasType.jcas.throwFeatMissing("selected", "org.apache.uima.textmarker.type.TextMarkerColoring");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((TextMarkerColoring_Type)jcasType).casFeatCode_selected, v);}    
  }

    