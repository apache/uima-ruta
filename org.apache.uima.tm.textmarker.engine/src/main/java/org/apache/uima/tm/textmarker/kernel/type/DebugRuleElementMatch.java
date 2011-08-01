/* First created by JCasGen Fri Jun 27 14:36:37 CEST 2008 */
package org.apache.uima.tm.textmarker.kernel.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP_Type;

/** 
 * Updated by JCasGen Tue Jan 25 09:29:36 CET 2011
 * XML source: D:/work/workspace-tm/org.apache.uima.tm.textmarker.engine/desc/InternalTypeSystem.xml
 * @generated */
public class DebugRuleElementMatch extends ProfiledAnnotation {
  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(DebugRuleElementMatch.class);

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
  protected DebugRuleElementMatch() {}
    
  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
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

  /** <!-- begin-user-doc --> Write your own initialization here <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {
  }

  // *--------------*
  // * Feature: BaseCondition

  /**
   * getter for BaseCondition - gets
   * 
   * @generated
   */
  public DebugEvaluatedCondition getBaseCondition() {
    if (DebugRuleElementMatch_Type.featOkTst && ((DebugRuleElementMatch_Type)jcasType).casFeat_baseCondition == null)
      jcasType.jcas.throwFeatMissing("baseCondition", "org.apache.uima.tm.textmarker.kernel.type.DebugRuleElementMatch");
    return (DebugEvaluatedCondition)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_baseCondition)));}
    
  /**
   * setter for BaseCondition - sets
   * 
   * @generated
   */
  public void setBaseCondition(DebugEvaluatedCondition v) {
    if (DebugRuleElementMatch_Type.featOkTst && ((DebugRuleElementMatch_Type)jcasType).casFeat_baseCondition == null)
      jcasType.jcas.throwFeatMissing("baseCondition", "org.apache.uima.tm.textmarker.kernel.type.DebugRuleElementMatch");
    jcasType.ll_cas.ll_setRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_baseCondition, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  // *--------------*
  // * Feature: Conditions

  /**
   * getter for Conditions - gets
   * 
   * @generated
   */
  public FSArray getConditions() {
    if (DebugRuleElementMatch_Type.featOkTst && ((DebugRuleElementMatch_Type)jcasType).casFeat_conditions == null)
      jcasType.jcas.throwFeatMissing("conditions", "org.apache.uima.tm.textmarker.kernel.type.DebugRuleElementMatch");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_conditions)));}
    
  /**
   * setter for Conditions - sets
   * 
   * @generated
   */
  public void setConditions(FSArray v) {
    if (DebugRuleElementMatch_Type.featOkTst && ((DebugRuleElementMatch_Type)jcasType).casFeat_conditions == null)
      jcasType.jcas.throwFeatMissing("conditions", "org.apache.uima.tm.textmarker.kernel.type.DebugRuleElementMatch");
    jcasType.ll_cas.ll_setRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_conditions, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /**
   * indexed getter for Conditions - gets an indexed value -
   * 
   * @generated
   */
  public DebugEvaluatedCondition getConditions(int i) {
    if (DebugRuleElementMatch_Type.featOkTst && ((DebugRuleElementMatch_Type)jcasType).casFeat_conditions == null)
      jcasType.jcas.throwFeatMissing("conditions", "org.apache.uima.tm.textmarker.kernel.type.DebugRuleElementMatch");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_conditions), i);
    return (DebugEvaluatedCondition)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_conditions), i)));}

  /**
   * indexed setter for Conditions - sets an indexed value -
   * 
   * @generated
   */
  public void setConditions(int i, DebugEvaluatedCondition v) { 
    if (DebugRuleElementMatch_Type.featOkTst && ((DebugRuleElementMatch_Type)jcasType).casFeat_conditions == null)
      jcasType.jcas.throwFeatMissing("conditions", "org.apache.uima.tm.textmarker.kernel.type.DebugRuleElementMatch");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_conditions), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatch_Type)jcasType).casFeatCode_conditions), i, jcasType.ll_cas.ll_getFSRef(v));}
  }
