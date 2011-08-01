/* First created by JCasGen Fri Jun 27 14:32:41 CEST 2008 */
package org.apache.uima.tm.textmarker.kernel.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP_Type;

/** 
 * Updated by JCasGen Tue Jan 25 09:29:36 CET 2011
 * XML source: D:/work/workspace-tm/org.apache.uima.tm.textmarker.engine/desc/InternalTypeSystem.xml
 * @generated */
public class DebugRuleApply extends DebugScriptApply {
  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(DebugRuleApply.class);

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
  protected DebugRuleApply() {}
    
  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public DebugRuleApply(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DebugRuleApply(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */
  public DebugRuleApply(JCas jcas, int begin, int end) {
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
  // * Feature: Applied

  /**
   * getter for Applied - gets
   * 
   * @generated
   */
  public int getApplied() {
    if (DebugRuleApply_Type.featOkTst && ((DebugRuleApply_Type)jcasType).casFeat_applied == null)
      jcasType.jcas.throwFeatMissing("applied", "org.apache.uima.tm.textmarker.kernel.type.DebugRuleApply");
    return jcasType.ll_cas.ll_getIntValue(addr, ((DebugRuleApply_Type)jcasType).casFeatCode_applied);}
    
  /**
   * setter for Applied - sets
   * 
   * @generated
   */
  public void setApplied(int v) {
    if (DebugRuleApply_Type.featOkTst && ((DebugRuleApply_Type)jcasType).casFeat_applied == null)
      jcasType.jcas.throwFeatMissing("applied", "org.apache.uima.tm.textmarker.kernel.type.DebugRuleApply");
    jcasType.ll_cas.ll_setIntValue(addr, ((DebugRuleApply_Type)jcasType).casFeatCode_applied, v);}    
   
    
  // *--------------*
  // * Feature: Tried

  /**
   * getter for Tried - gets
   * 
   * @generated
   */
  public int getTried() {
    if (DebugRuleApply_Type.featOkTst && ((DebugRuleApply_Type)jcasType).casFeat_tried == null)
      jcasType.jcas.throwFeatMissing("tried", "org.apache.uima.tm.textmarker.kernel.type.DebugRuleApply");
    return jcasType.ll_cas.ll_getIntValue(addr, ((DebugRuleApply_Type)jcasType).casFeatCode_tried);}
    
  /**
   * setter for Tried - sets
   * 
   * @generated
   */
  public void setTried(int v) {
    if (DebugRuleApply_Type.featOkTst && ((DebugRuleApply_Type)jcasType).casFeat_tried == null)
      jcasType.jcas.throwFeatMissing("tried", "org.apache.uima.tm.textmarker.kernel.type.DebugRuleApply");
    jcasType.ll_cas.ll_setIntValue(addr, ((DebugRuleApply_Type)jcasType).casFeatCode_tried, v);}    
   
    
  // *--------------*
  // * Feature: Rules

  /**
   * getter for Rules - gets
   * 
   * @generated
   */
  public FSArray getRules() {
    if (DebugRuleApply_Type.featOkTst && ((DebugRuleApply_Type)jcasType).casFeat_rules == null)
      jcasType.jcas.throwFeatMissing("rules", "org.apache.uima.tm.textmarker.kernel.type.DebugRuleApply");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleApply_Type)jcasType).casFeatCode_rules)));}
    
  /**
   * setter for Rules - sets
   * 
   * @generated
   */
  public void setRules(FSArray v) {
    if (DebugRuleApply_Type.featOkTst && ((DebugRuleApply_Type)jcasType).casFeat_rules == null)
      jcasType.jcas.throwFeatMissing("rules", "org.apache.uima.tm.textmarker.kernel.type.DebugRuleApply");
    jcasType.ll_cas.ll_setRefValue(addr, ((DebugRuleApply_Type)jcasType).casFeatCode_rules, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /**
   * indexed getter for Rules - gets an indexed value -
   * 
   * @generated
   */
  public DebugRuleMatch getRules(int i) {
    if (DebugRuleApply_Type.featOkTst && ((DebugRuleApply_Type)jcasType).casFeat_rules == null)
      jcasType.jcas.throwFeatMissing("rules", "org.apache.uima.tm.textmarker.kernel.type.DebugRuleApply");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleApply_Type)jcasType).casFeatCode_rules), i);
    return (DebugRuleMatch)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleApply_Type)jcasType).casFeatCode_rules), i)));}

  /**
   * indexed setter for Rules - sets an indexed value -
   * 
   * @generated
   */
  public void setRules(int i, DebugRuleMatch v) { 
    if (DebugRuleApply_Type.featOkTst && ((DebugRuleApply_Type)jcasType).casFeat_rules == null)
      jcasType.jcas.throwFeatMissing("rules", "org.apache.uima.tm.textmarker.kernel.type.DebugRuleApply");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleApply_Type)jcasType).casFeatCode_rules), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleApply_Type)jcasType).casFeatCode_rules), i, jcasType.ll_cas.ll_getFSRef(v));}
  }
