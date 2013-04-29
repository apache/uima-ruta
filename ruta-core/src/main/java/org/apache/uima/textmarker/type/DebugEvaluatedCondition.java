

/* First created by JCasGen Tue Aug 09 16:26:13 CEST 2011 */
package org.apache.uima.textmarker.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.cas.TOP_Type;


/** 
 * Updated by JCasGen Thu Jul 12 10:42:33 CEST 2012
 * XML source: D:/work/workspace-uima6/uimaj-textmarker/src/main/java/org/apache/uima/textmarker/engine/InternalTypeSystem.xml
 * @generated */
public class DebugEvaluatedCondition extends TOP {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DebugEvaluatedCondition.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DebugEvaluatedCondition() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DebugEvaluatedCondition(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DebugEvaluatedCondition(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: element

  /** getter for element - gets 
   * @generated */
  public String getElement() {
    if (DebugEvaluatedCondition_Type.featOkTst && ((DebugEvaluatedCondition_Type)jcasType).casFeat_element == null)
      jcasType.jcas.throwFeatMissing("element", "org.apache.uima.textmarker.type.DebugEvaluatedCondition");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DebugEvaluatedCondition_Type)jcasType).casFeatCode_element);}
    
  /** setter for element - sets  
   * @generated */
  public void setElement(String v) {
    if (DebugEvaluatedCondition_Type.featOkTst && ((DebugEvaluatedCondition_Type)jcasType).casFeat_element == null)
      jcasType.jcas.throwFeatMissing("element", "org.apache.uima.textmarker.type.DebugEvaluatedCondition");
    jcasType.ll_cas.ll_setStringValue(addr, ((DebugEvaluatedCondition_Type)jcasType).casFeatCode_element, v);}    
   
    
  //*--------------*
  //* Feature: value

  /** getter for value - gets 
   * @generated */
  public boolean getValue() {
    if (DebugEvaluatedCondition_Type.featOkTst && ((DebugEvaluatedCondition_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "org.apache.uima.textmarker.type.DebugEvaluatedCondition");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((DebugEvaluatedCondition_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated */
  public void setValue(boolean v) {
    if (DebugEvaluatedCondition_Type.featOkTst && ((DebugEvaluatedCondition_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "org.apache.uima.textmarker.type.DebugEvaluatedCondition");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((DebugEvaluatedCondition_Type)jcasType).casFeatCode_value, v);}    
   
    
  //*--------------*
  //* Feature: conditions

  /** getter for conditions - gets 
   * @generated */
  public FSArray getConditions() {
    if (DebugEvaluatedCondition_Type.featOkTst && ((DebugEvaluatedCondition_Type)jcasType).casFeat_conditions == null)
      jcasType.jcas.throwFeatMissing("conditions", "org.apache.uima.textmarker.type.DebugEvaluatedCondition");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DebugEvaluatedCondition_Type)jcasType).casFeatCode_conditions)));}
    
  /** setter for conditions - sets  
   * @generated */
  public void setConditions(FSArray v) {
    if (DebugEvaluatedCondition_Type.featOkTst && ((DebugEvaluatedCondition_Type)jcasType).casFeat_conditions == null)
      jcasType.jcas.throwFeatMissing("conditions", "org.apache.uima.textmarker.type.DebugEvaluatedCondition");
    jcasType.ll_cas.ll_setRefValue(addr, ((DebugEvaluatedCondition_Type)jcasType).casFeatCode_conditions, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for conditions - gets an indexed value - 
   * @generated */
  public DebugEvaluatedCondition getConditions(int i) {
    if (DebugEvaluatedCondition_Type.featOkTst && ((DebugEvaluatedCondition_Type)jcasType).casFeat_conditions == null)
      jcasType.jcas.throwFeatMissing("conditions", "org.apache.uima.textmarker.type.DebugEvaluatedCondition");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DebugEvaluatedCondition_Type)jcasType).casFeatCode_conditions), i);
    return (DebugEvaluatedCondition)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DebugEvaluatedCondition_Type)jcasType).casFeatCode_conditions), i)));}

  /** indexed setter for conditions - sets an indexed value - 
   * @generated */
  public void setConditions(int i, DebugEvaluatedCondition v) { 
    if (DebugEvaluatedCondition_Type.featOkTst && ((DebugEvaluatedCondition_Type)jcasType).casFeat_conditions == null)
      jcasType.jcas.throwFeatMissing("conditions", "org.apache.uima.textmarker.type.DebugEvaluatedCondition");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DebugEvaluatedCondition_Type)jcasType).casFeatCode_conditions), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DebugEvaluatedCondition_Type)jcasType).casFeatCode_conditions), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    