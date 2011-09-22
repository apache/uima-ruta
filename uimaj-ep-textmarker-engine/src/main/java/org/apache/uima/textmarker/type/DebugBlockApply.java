

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
public class DebugBlockApply extends DebugRuleApply {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DebugBlockApply.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DebugBlockApply() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DebugBlockApply(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DebugBlockApply(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DebugBlockApply(JCas jcas, int begin, int end) {
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
  //* Feature: innerApply

  /** getter for innerApply - gets 
   * @generated */
  public FSArray getInnerApply() {
    if (DebugBlockApply_Type.featOkTst && ((DebugBlockApply_Type)jcasType).casFeat_innerApply == null)
      jcasType.jcas.throwFeatMissing("innerApply", "org.apache.uima.textmarker.type.DebugBlockApply");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DebugBlockApply_Type)jcasType).casFeatCode_innerApply)));}
    
  /** setter for innerApply - sets  
   * @generated */
  public void setInnerApply(FSArray v) {
    if (DebugBlockApply_Type.featOkTst && ((DebugBlockApply_Type)jcasType).casFeat_innerApply == null)
      jcasType.jcas.throwFeatMissing("innerApply", "org.apache.uima.textmarker.type.DebugBlockApply");
    jcasType.ll_cas.ll_setRefValue(addr, ((DebugBlockApply_Type)jcasType).casFeatCode_innerApply, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for innerApply - gets an indexed value - 
   * @generated */
  public DebugScriptApply getInnerApply(int i) {
    if (DebugBlockApply_Type.featOkTst && ((DebugBlockApply_Type)jcasType).casFeat_innerApply == null)
      jcasType.jcas.throwFeatMissing("innerApply", "org.apache.uima.textmarker.type.DebugBlockApply");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DebugBlockApply_Type)jcasType).casFeatCode_innerApply), i);
    return (DebugScriptApply)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DebugBlockApply_Type)jcasType).casFeatCode_innerApply), i)));}

  /** indexed setter for innerApply - sets an indexed value - 
   * @generated */
  public void setInnerApply(int i, DebugScriptApply v) { 
    if (DebugBlockApply_Type.featOkTst && ((DebugBlockApply_Type)jcasType).casFeat_innerApply == null)
      jcasType.jcas.throwFeatMissing("innerApply", "org.apache.uima.textmarker.type.DebugBlockApply");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DebugBlockApply_Type)jcasType).casFeatCode_innerApply), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DebugBlockApply_Type)jcasType).casFeatCode_innerApply), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    