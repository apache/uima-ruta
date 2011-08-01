/* First created by JCasGen Fri Jun 27 14:32:41 CEST 2008 */
package org.apache.uima.tm.textmarker.kernel.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

/** 
 * Updated by JCasGen Tue Jan 25 09:29:36 CET 2011
 * XML source: D:/work/workspace-tm/org.apache.uima.tm.textmarker.engine/desc/InternalTypeSystem.xml
 * @generated */
public class DebugScriptApply extends ProfiledAnnotation {
  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(DebugScriptApply.class);

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
  protected DebugScriptApply() {}
    
  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public DebugScriptApply(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DebugScriptApply(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */
  public DebugScriptApply(JCas jcas, int begin, int end) {
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
  // * Feature: Element

  /**
   * getter for Element - gets
   * 
   * @generated
   */
  public String getElement() {
    if (DebugScriptApply_Type.featOkTst && ((DebugScriptApply_Type)jcasType).casFeat_element == null)
      jcasType.jcas.throwFeatMissing("element", "org.apache.uima.tm.textmarker.kernel.type.DebugScriptApply");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DebugScriptApply_Type)jcasType).casFeatCode_element);}
    
  /**
   * setter for Element - sets
   * 
   * @generated
   */
  public void setElement(String v) {
    if (DebugScriptApply_Type.featOkTst && ((DebugScriptApply_Type)jcasType).casFeat_element == null)
      jcasType.jcas.throwFeatMissing("element", "org.apache.uima.tm.textmarker.kernel.type.DebugScriptApply");
    jcasType.ll_cas.ll_setStringValue(addr, ((DebugScriptApply_Type)jcasType).casFeatCode_element, v);}    
  }
