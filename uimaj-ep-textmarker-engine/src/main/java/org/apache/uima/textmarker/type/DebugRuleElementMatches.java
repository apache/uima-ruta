

/* First created by JCasGen Tue Aug 09 16:26:13 CEST 2011 */
package org.apache.uima.textmarker.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.cas.TOP_Type;


/** 
 * Updated by JCasGen Wed Jan 11 14:42:26 CET 2012
 * XML source: D:/work/workspace-uima3/uimaj-ep-textmarker-engine/src/main/java/org/apache/uima/textmarker/engine/BasicTypeSystem.xml
 * @generated */
public class DebugRuleElementMatches extends TOP {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DebugRuleElementMatches.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DebugRuleElementMatches() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DebugRuleElementMatches(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DebugRuleElementMatches(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: matches

  /** getter for matches - gets 
   * @generated */
  public FSArray getMatches() {
    if (DebugRuleElementMatches_Type.featOkTst && ((DebugRuleElementMatches_Type)jcasType).casFeat_matches == null)
      jcasType.jcas.throwFeatMissing("matches", "org.apache.uima.textmarker.type.DebugRuleElementMatches");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatches_Type)jcasType).casFeatCode_matches)));}
    
  /** setter for matches - sets  
   * @generated */
  public void setMatches(FSArray v) {
    if (DebugRuleElementMatches_Type.featOkTst && ((DebugRuleElementMatches_Type)jcasType).casFeat_matches == null)
      jcasType.jcas.throwFeatMissing("matches", "org.apache.uima.textmarker.type.DebugRuleElementMatches");
    jcasType.ll_cas.ll_setRefValue(addr, ((DebugRuleElementMatches_Type)jcasType).casFeatCode_matches, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for matches - gets an indexed value - 
   * @generated */
  public DebugRuleElementMatch getMatches(int i) {
    if (DebugRuleElementMatches_Type.featOkTst && ((DebugRuleElementMatches_Type)jcasType).casFeat_matches == null)
      jcasType.jcas.throwFeatMissing("matches", "org.apache.uima.textmarker.type.DebugRuleElementMatches");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatches_Type)jcasType).casFeatCode_matches), i);
    return (DebugRuleElementMatch)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatches_Type)jcasType).casFeatCode_matches), i)));}

  /** indexed setter for matches - sets an indexed value - 
   * @generated */
  public void setMatches(int i, DebugRuleElementMatch v) { 
    if (DebugRuleElementMatches_Type.featOkTst && ((DebugRuleElementMatches_Type)jcasType).casFeat_matches == null)
      jcasType.jcas.throwFeatMissing("matches", "org.apache.uima.textmarker.type.DebugRuleElementMatches");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatches_Type)jcasType).casFeatCode_matches), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatches_Type)jcasType).casFeatCode_matches), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: element

  /** getter for element - gets 
   * @generated */
  public String getElement() {
    if (DebugRuleElementMatches_Type.featOkTst && ((DebugRuleElementMatches_Type)jcasType).casFeat_element == null)
      jcasType.jcas.throwFeatMissing("element", "org.apache.uima.textmarker.type.DebugRuleElementMatches");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DebugRuleElementMatches_Type)jcasType).casFeatCode_element);}
    
  /** setter for element - sets  
   * @generated */
  public void setElement(String v) {
    if (DebugRuleElementMatches_Type.featOkTst && ((DebugRuleElementMatches_Type)jcasType).casFeat_element == null)
      jcasType.jcas.throwFeatMissing("element", "org.apache.uima.textmarker.type.DebugRuleElementMatches");
    jcasType.ll_cas.ll_setStringValue(addr, ((DebugRuleElementMatches_Type)jcasType).casFeatCode_element, v);}    
   
    
  //*--------------*
  //* Feature: ruleAnchor

  /** getter for ruleAnchor - gets 
   * @generated */
  public boolean getRuleAnchor() {
    if (DebugRuleElementMatches_Type.featOkTst && ((DebugRuleElementMatches_Type)jcasType).casFeat_ruleAnchor == null)
      jcasType.jcas.throwFeatMissing("ruleAnchor", "org.apache.uima.textmarker.type.DebugRuleElementMatches");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((DebugRuleElementMatches_Type)jcasType).casFeatCode_ruleAnchor);}
    
  /** setter for ruleAnchor - sets  
   * @generated */
  public void setRuleAnchor(boolean v) {
    if (DebugRuleElementMatches_Type.featOkTst && ((DebugRuleElementMatches_Type)jcasType).casFeat_ruleAnchor == null)
      jcasType.jcas.throwFeatMissing("ruleAnchor", "org.apache.uima.textmarker.type.DebugRuleElementMatches");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((DebugRuleElementMatches_Type)jcasType).casFeatCode_ruleAnchor, v);}    
  }

    