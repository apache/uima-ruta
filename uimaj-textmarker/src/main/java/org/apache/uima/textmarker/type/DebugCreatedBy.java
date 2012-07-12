

/* First created by JCasGen Wed Jul 11 15:10:37 CEST 2012 */
package org.apache.uima.textmarker.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.TOP;


/** 
 * Updated by JCasGen Thu Jul 12 10:42:33 CEST 2012
 * XML source: D:/work/workspace-uima6/uimaj-textmarker/src/main/java/org/apache/uima/textmarker/engine/InternalTypeSystem.xml
 * @generated */
public class DebugCreatedBy extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(DebugCreatedBy.class);
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
  protected DebugCreatedBy() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DebugCreatedBy(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DebugCreatedBy(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: rule

  /** getter for rule - gets 
   * @generated */
  public String getRule() {
    if (DebugCreatedBy_Type.featOkTst && ((DebugCreatedBy_Type)jcasType).casFeat_rule == null)
      jcasType.jcas.throwFeatMissing("rule", "org.apache.uima.textmarker.type.DebugCreatedBy");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DebugCreatedBy_Type)jcasType).casFeatCode_rule);}
    
  /** setter for rule - sets  
   * @generated */
  public void setRule(String v) {
    if (DebugCreatedBy_Type.featOkTst && ((DebugCreatedBy_Type)jcasType).casFeat_rule == null)
      jcasType.jcas.throwFeatMissing("rule", "org.apache.uima.textmarker.type.DebugCreatedBy");
    jcasType.ll_cas.ll_setStringValue(addr, ((DebugCreatedBy_Type)jcasType).casFeatCode_rule, v);}    
   
    
  //*--------------*
  //* Feature: annotation

  /** getter for annotation - gets 
   * @generated */
  public Annotation getAnnotation() {
    if (DebugCreatedBy_Type.featOkTst && ((DebugCreatedBy_Type)jcasType).casFeat_annotation == null)
      jcasType.jcas.throwFeatMissing("annotation", "org.apache.uima.textmarker.type.DebugCreatedBy");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DebugCreatedBy_Type)jcasType).casFeatCode_annotation)));}
    
  /** setter for annotation - sets  
   * @generated */
  public void setAnnotation(Annotation v) {
    if (DebugCreatedBy_Type.featOkTst && ((DebugCreatedBy_Type)jcasType).casFeat_annotation == null)
      jcasType.jcas.throwFeatMissing("annotation", "org.apache.uima.textmarker.type.DebugCreatedBy");
    jcasType.ll_cas.ll_setRefValue(addr, ((DebugCreatedBy_Type)jcasType).casFeatCode_annotation, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: script

  /** getter for script - gets 
   * @generated */
  public String getScript() {
    if (DebugCreatedBy_Type.featOkTst && ((DebugCreatedBy_Type)jcasType).casFeat_script == null)
      jcasType.jcas.throwFeatMissing("script", "org.apache.uima.textmarker.type.DebugCreatedBy");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DebugCreatedBy_Type)jcasType).casFeatCode_script);}
    
  /** setter for script - sets  
   * @generated */
  public void setScript(String v) {
    if (DebugCreatedBy_Type.featOkTst && ((DebugCreatedBy_Type)jcasType).casFeat_script == null)
      jcasType.jcas.throwFeatMissing("script", "org.apache.uima.textmarker.type.DebugCreatedBy");
    jcasType.ll_cas.ll_setStringValue(addr, ((DebugCreatedBy_Type)jcasType).casFeatCode_script, v);}    
   
    
  //*--------------*
  //* Feature: id

  /** getter for id - gets 
   * @generated */
  public int getId() {
    if (DebugCreatedBy_Type.featOkTst && ((DebugCreatedBy_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "org.apache.uima.textmarker.type.DebugCreatedBy");
    return jcasType.ll_cas.ll_getIntValue(addr, ((DebugCreatedBy_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets  
   * @generated */
  public void setId(int v) {
    if (DebugCreatedBy_Type.featOkTst && ((DebugCreatedBy_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "org.apache.uima.textmarker.type.DebugCreatedBy");
    jcasType.ll_cas.ll_setIntValue(addr, ((DebugCreatedBy_Type)jcasType).casFeatCode_id, v);}    
  }

    