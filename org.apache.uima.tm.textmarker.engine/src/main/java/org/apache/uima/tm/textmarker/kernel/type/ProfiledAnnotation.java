/* First created by JCasGen Tue Jan 20 17:20:51 CET 2009 */
package org.apache.uima.tm.textmarker.kernel.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

/** 
 * Updated by JCasGen Tue Jan 25 09:29:36 CET 2011
 * XML source: D:/work/workspace-tm/org.apache.uima.tm.textmarker.engine/desc/InternalTypeSystem.xml
 * @generated */
public class ProfiledAnnotation extends Annotation {
  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(ProfiledAnnotation.class);

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
  protected ProfiledAnnotation() {}
    
  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public ProfiledAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ProfiledAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */
  public ProfiledAnnotation(JCas jcas, int begin, int end) {
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
  // * Feature: Time

  /**
   * getter for Time - gets
   * 
   * @generated
   */
  public long getTime() {
    if (ProfiledAnnotation_Type.featOkTst && ((ProfiledAnnotation_Type)jcasType).casFeat_time == null)
      jcasType.jcas.throwFeatMissing("time", "org.apache.uima.tm.textmarker.kernel.type.ProfiledAnnotation");
    return jcasType.ll_cas.ll_getLongValue(addr, ((ProfiledAnnotation_Type)jcasType).casFeatCode_time);}
    
  /**
   * setter for Time - sets
   * 
   * @generated
   */
  public void setTime(long v) {
    if (ProfiledAnnotation_Type.featOkTst && ((ProfiledAnnotation_Type)jcasType).casFeat_time == null)
      jcasType.jcas.throwFeatMissing("time", "org.apache.uima.tm.textmarker.kernel.type.ProfiledAnnotation");
    jcasType.ll_cas.ll_setLongValue(addr, ((ProfiledAnnotation_Type)jcasType).casFeatCode_time, v);}    
  }
