

/* First created by JCasGen Tue Aug 09 16:26:13 CEST 2011 */
package org.apache.uima.textmarker.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP_Type;


/** 
 * Updated by JCasGen Tue Sep 20 15:37:41 CEST 2011
 * XML source: D:/work/workspace-uima3/uimaj-ep-textmarker-engine/desc/InternalTypeSystem.xml
 * @generated */
public class DebugRuleElementMatch extends ProfiledAnnotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DebugRuleElementMatch.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DebugRuleElementMatch() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DebugRuleElementMatch(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DebugRuleElementMatch(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DebugRuleElementMatch(JCas jcas, int begin, int end) {
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
  //* Feature: baseCondition

  /** getter for baseCondition - gets 
   * @generated */
  public DebugEvaluatedCondition getBaseCondition() {
    if (DebugRuleElementMatch_Type.featOkTst && ((DebugRuleElementMatch_Type)jcasType).casFeat_baseCondition == null)
      jcasType.jcas.throwFeatMissing("baseCondition", "org.apache.uima.textmarker.type.DebugRuleElementMatch");
    return (DebugEvaluatedCondition)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_baseCondition)));}
    
  /** setter for baseCondition - sets  
   * @generated */
  public void setBaseCondition(DebugEvaluatedCondition v) {
    if (DebugRuleElementMatch_Type.featOkTst && ((DebugRuleElementMatch_Type)jcasType).casFeat_baseCondition == null)
      jcasType.jcas.throwFeatMissing("baseCondition", "org.apache.uima.textmarker.type.DebugRuleElementMatch");
    jcasType.ll_cas.ll_setRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_baseCondition, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: conditions

  /** getter for conditions - gets 
   * @generated */
  public FSArray getConditions() {
    if (DebugRuleElementMatch_Type.featOkTst && ((DebugRuleElementMatch_Type)jcasType).casFeat_conditions == null)
      jcasType.jcas.throwFeatMissing("conditions", "org.apache.uima.textmarker.type.DebugRuleElementMatch");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_conditions)));}
    
  /** setter for conditions - sets  
   * @generated */
  public void setConditions(FSArray v) {
    if (DebugRuleElementMatch_Type.featOkTst && ((DebugRuleElementMatch_Type)jcasType).casFeat_conditions == null)
      jcasType.jcas.throwFeatMissing("conditions", "org.apache.uima.textmarker.type.DebugRuleElementMatch");
    jcasType.ll_cas.ll_setRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_conditions, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for conditions - gets an indexed value - 
   * @generated */
  public DebugEvaluatedCondition getConditions(int i) {
    if (DebugRuleElementMatch_Type.featOkTst && ((DebugRuleElementMatch_Type)jcasType).casFeat_conditions == null)
      jcasType.jcas.throwFeatMissing("conditions", "org.apache.uima.textmarker.type.DebugRuleElementMatch");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_conditions), i);
    return (DebugEvaluatedCondition)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_conditions), i)));}

  /** indexed setter for conditions - sets an indexed value - 
   * @generated */
  public void setConditions(int i, DebugEvaluatedCondition v) { 
    if (DebugRuleElementMatch_Type.featOkTst && ((DebugRuleElementMatch_Type)jcasType).casFeat_conditions == null)
      jcasType.jcas.throwFeatMissing("conditions", "org.apache.uima.textmarker.type.DebugRuleElementMatch");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_conditions), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_conditions), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: elements

  /** getter for elements - gets 
   * @generated */
  public FSArray getElements() {
    if (DebugRuleElementMatch_Type.featOkTst && ((DebugRuleElementMatch_Type)jcasType).casFeat_elements == null)
      jcasType.jcas.throwFeatMissing("elements", "org.apache.uima.textmarker.type.DebugRuleElementMatch");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_elements)));}
    
  /** setter for elements - sets  
   * @generated */
  public void setElements(FSArray v) {
    if (DebugRuleElementMatch_Type.featOkTst && ((DebugRuleElementMatch_Type)jcasType).casFeat_elements == null)
      jcasType.jcas.throwFeatMissing("elements", "org.apache.uima.textmarker.type.DebugRuleElementMatch");
    jcasType.ll_cas.ll_setRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_elements, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for elements - gets an indexed value - 
   * @generated */
  public DebugRuleElementMatches getElements(int i) {
    if (DebugRuleElementMatch_Type.featOkTst && ((DebugRuleElementMatch_Type)jcasType).casFeat_elements == null)
      jcasType.jcas.throwFeatMissing("elements", "org.apache.uima.textmarker.type.DebugRuleElementMatch");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_elements), i);
    return (DebugRuleElementMatches)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_elements), i)));}

  /** indexed setter for elements - sets an indexed value - 
   * @generated */
  public void setElements(int i, DebugRuleElementMatches v) { 
    if (DebugRuleElementMatch_Type.featOkTst && ((DebugRuleElementMatch_Type)jcasType).casFeat_elements == null)
      jcasType.jcas.throwFeatMissing("elements", "org.apache.uima.textmarker.type.DebugRuleElementMatch");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_elements), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_elements), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    