/* First created by JCasGen Sat Jun 28 12:18:19 CEST 2008 */
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
public class DebugRuleElementMatches extends TOP {
  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(DebugRuleElementMatches.class);

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
  protected DebugRuleElementMatches() {}
    
  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public DebugRuleElementMatches(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DebugRuleElementMatches(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc --> Write your own initialization here <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {
  }

  // *--------------*
  // * Feature: Matches

  /**
   * getter for Matches - gets
   * 
   * @generated
   */
  public FSArray getMatches() {
    if (DebugRuleElementMatches_Type.featOkTst && ((DebugRuleElementMatches_Type)jcasType).casFeat_matches == null)
      jcasType.jcas.throwFeatMissing("matches", "org.apache.uima.tm.textmarker.kernel.type.DebugRuleElementMatches");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatches_Type)jcasType).casFeatCode_matches)));}
    
  /**
   * setter for Matches - sets
   * 
   * @generated
   */
  public void setMatches(FSArray v) {
    if (DebugRuleElementMatches_Type.featOkTst && ((DebugRuleElementMatches_Type)jcasType).casFeat_matches == null)
      jcasType.jcas.throwFeatMissing("matches", "org.apache.uima.tm.textmarker.kernel.type.DebugRuleElementMatches");
    jcasType.ll_cas.ll_setRefValue(addr, ((DebugRuleElementMatches_Type)jcasType).casFeatCode_matches, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /**
   * indexed getter for Matches - gets an indexed value -
   * 
   * @generated
   */
  public DebugRuleElementMatch getMatches(int i) {
    if (DebugRuleElementMatches_Type.featOkTst && ((DebugRuleElementMatches_Type)jcasType).casFeat_matches == null)
      jcasType.jcas.throwFeatMissing("matches", "org.apache.uima.tm.textmarker.kernel.type.DebugRuleElementMatches");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatches_Type)jcasType).casFeatCode_matches), i);
    return (DebugRuleElementMatch)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatches_Type)jcasType).casFeatCode_matches), i)));}

  /**
   * indexed setter for Matches - sets an indexed value -
   * 
   * @generated
   */
  public void setMatches(int i, DebugRuleElementMatch v) { 
    if (DebugRuleElementMatches_Type.featOkTst && ((DebugRuleElementMatches_Type)jcasType).casFeat_matches == null)
      jcasType.jcas.throwFeatMissing("matches", "org.apache.uima.tm.textmarker.kernel.type.DebugRuleElementMatches");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatches_Type)jcasType).casFeatCode_matches), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DebugRuleElementMatches_Type)jcasType).casFeatCode_matches), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  // *--------------*
  // * Feature: Element

  /**
   * getter for Element - gets
   * 
   * @generated
   */
  public String getElement() {
    if (DebugRuleElementMatches_Type.featOkTst && ((DebugRuleElementMatches_Type)jcasType).casFeat_element == null)
      jcasType.jcas.throwFeatMissing("element", "org.apache.uima.tm.textmarker.kernel.type.DebugRuleElementMatches");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DebugRuleElementMatches_Type)jcasType).casFeatCode_element);}
    
  /**
   * setter for Element - sets
   * 
   * @generated
   */
  public void setElement(String v) {
    if (DebugRuleElementMatches_Type.featOkTst && ((DebugRuleElementMatches_Type)jcasType).casFeat_element == null)
      jcasType.jcas.throwFeatMissing("element", "org.apache.uima.tm.textmarker.kernel.type.DebugRuleElementMatches");
    jcasType.ll_cas.ll_setStringValue(addr, ((DebugRuleElementMatches_Type)jcasType).casFeatCode_element, v);}    
  }
