/* First created by JCasGen Fri Dec 05 17:07:48 CET 2008 */
package org.apache.uima.tm.textmarker.kernel.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.cas.TOP_Type;

/** 
 * Updated by JCasGen Tue Jan 25 09:29:36 CET 2011
 * XML source: D:/work/workspace-tm/org.apache.uima.tm.textmarker.engine/desc/InternalTypeSystem.xml
 * @generated */
public class DebugEvaluatedCondition extends TOP {
  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(DebugEvaluatedCondition.class);

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
  protected DebugEvaluatedCondition() {}
    
  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public DebugEvaluatedCondition(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DebugEvaluatedCondition(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc --> Write your own initialization here <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {
  }

  // *--------------*
  // * Feature: Element

  /**
   * getter for Element - gets
   * 
   * @generated
   */
  public String getElement() {
    if (DebugEvaluatedCondition_Type.featOkTst && ((DebugEvaluatedCondition_Type)jcasType).casFeat_element == null)
      jcasType.jcas.throwFeatMissing("element", "org.apache.uima.tm.textmarker.kernel.type.DebugEvaluatedCondition");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DebugEvaluatedCondition_Type)jcasType).casFeatCode_element);}
    
  /**
   * setter for Element - sets
   * 
   * @generated
   */
  public void setElement(String v) {
    if (DebugEvaluatedCondition_Type.featOkTst && ((DebugEvaluatedCondition_Type)jcasType).casFeat_element == null)
      jcasType.jcas.throwFeatMissing("element", "org.apache.uima.tm.textmarker.kernel.type.DebugEvaluatedCondition");
    jcasType.ll_cas.ll_setStringValue(addr, ((DebugEvaluatedCondition_Type)jcasType).casFeatCode_element, v);}    
   
    
  // *--------------*
  // * Feature: Value

  /**
   * getter for Value - gets
   * 
   * @generated
   */
  public boolean getValue() {
    if (DebugEvaluatedCondition_Type.featOkTst && ((DebugEvaluatedCondition_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "org.apache.uima.tm.textmarker.kernel.type.DebugEvaluatedCondition");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((DebugEvaluatedCondition_Type)jcasType).casFeatCode_value);}
    
  /**
   * setter for Value - sets
   * 
   * @generated
   */
  public void setValue(boolean v) {
    if (DebugEvaluatedCondition_Type.featOkTst && ((DebugEvaluatedCondition_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "org.apache.uima.tm.textmarker.kernel.type.DebugEvaluatedCondition");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((DebugEvaluatedCondition_Type)jcasType).casFeatCode_value, v);}    
   
    
  // *--------------*
  // * Feature: Conditions

  /**
   * getter for Conditions - gets
   * 
   * @generated
   */
  public FSArray getConditions() {
    if (DebugEvaluatedCondition_Type.featOkTst && ((DebugEvaluatedCondition_Type)jcasType).casFeat_conditions == null)
      jcasType.jcas.throwFeatMissing("conditions", "org.apache.uima.tm.textmarker.kernel.type.DebugEvaluatedCondition");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DebugEvaluatedCondition_Type)jcasType).casFeatCode_conditions)));}
    
  /**
   * setter for Conditions - sets
   * 
   * @generated
   */
  public void setConditions(FSArray v) {
    if (DebugEvaluatedCondition_Type.featOkTst && ((DebugEvaluatedCondition_Type)jcasType).casFeat_conditions == null)
      jcasType.jcas.throwFeatMissing("conditions", "org.apache.uima.tm.textmarker.kernel.type.DebugEvaluatedCondition");
    jcasType.ll_cas.ll_setRefValue(addr, ((DebugEvaluatedCondition_Type)jcasType).casFeatCode_conditions, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /**
   * indexed getter for Conditions - gets an indexed value -
   * 
   * @generated
   */
  public DebugEvaluatedCondition getConditions(int i) {
    if (DebugEvaluatedCondition_Type.featOkTst && ((DebugEvaluatedCondition_Type)jcasType).casFeat_conditions == null)
      jcasType.jcas.throwFeatMissing("conditions", "org.apache.uima.tm.textmarker.kernel.type.DebugEvaluatedCondition");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DebugEvaluatedCondition_Type)jcasType).casFeatCode_conditions), i);
    return (DebugEvaluatedCondition)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DebugEvaluatedCondition_Type)jcasType).casFeatCode_conditions), i)));}

  /**
   * indexed setter for Conditions - sets an indexed value -
   * 
   * @generated
   */
  public void setConditions(int i, DebugEvaluatedCondition v) { 
    if (DebugEvaluatedCondition_Type.featOkTst && ((DebugEvaluatedCondition_Type)jcasType).casFeat_conditions == null)
      jcasType.jcas.throwFeatMissing("conditions", "org.apache.uima.tm.textmarker.kernel.type.DebugEvaluatedCondition");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DebugEvaluatedCondition_Type)jcasType).casFeatCode_conditions), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DebugEvaluatedCondition_Type)jcasType).casFeatCode_conditions), i, jcasType.ll_cas.ll_getFSRef(v));}
  }
