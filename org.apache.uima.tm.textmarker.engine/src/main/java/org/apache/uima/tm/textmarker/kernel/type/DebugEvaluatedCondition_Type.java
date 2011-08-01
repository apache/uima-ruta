/* First created by JCasGen Fri Dec 05 17:07:48 CET 2008 */
package org.apache.uima.tm.textmarker.kernel.type;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

/** 
 * Updated by JCasGen Tue Jan 25 09:29:36 CET 2011
 * @generated */
public class DebugEvaluatedCondition_Type extends TOP_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DebugEvaluatedCondition_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DebugEvaluatedCondition_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DebugEvaluatedCondition(addr, DebugEvaluatedCondition_Type.this);
  			   DebugEvaluatedCondition_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DebugEvaluatedCondition(addr, DebugEvaluatedCondition_Type.this);
  	  }
    };

  /** @generated */
  public final static int typeIndexID = DebugEvaluatedCondition.typeIndexID;

  /**
   * @generated
   * @modifiable
   */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.tm.textmarker.kernel.type.DebugEvaluatedCondition");

  /** @generated */
  final Feature casFeat_element;
  /** @generated */
  final int     casFeatCode_element;
  /** @generated */
  public String getElement(int addr) {
        if (featOkTst && casFeat_element == null)
      jcas.throwFeatMissing("element", "org.apache.uima.tm.textmarker.kernel.type.DebugEvaluatedCondition");
    return ll_cas.ll_getStringValue(addr, casFeatCode_element);
  }
  /** @generated */
  public void setElement(int addr, String v) {
        if (featOkTst && casFeat_element == null)
      jcas.throwFeatMissing("element", "org.apache.uima.tm.textmarker.kernel.type.DebugEvaluatedCondition");
    ll_cas.ll_setStringValue(addr, casFeatCode_element, v);}
    
  
 
  /** @generated */
  final Feature casFeat_value;
  /** @generated */
  final int     casFeatCode_value;
  /** @generated */
  public boolean getValue(int addr) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "org.apache.uima.tm.textmarker.kernel.type.DebugEvaluatedCondition");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_value);
  }
  /** @generated */
  public void setValue(int addr, boolean v) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "org.apache.uima.tm.textmarker.kernel.type.DebugEvaluatedCondition");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_value, v);}
    
  
 
  /** @generated */
  final Feature casFeat_conditions;
  /** @generated */
  final int     casFeatCode_conditions;
  /** @generated */
  public int getConditions(int addr) {
        if (featOkTst && casFeat_conditions == null)
      jcas.throwFeatMissing("conditions", "org.apache.uima.tm.textmarker.kernel.type.DebugEvaluatedCondition");
    return ll_cas.ll_getRefValue(addr, casFeatCode_conditions);
  }
  /** @generated */
  public void setConditions(int addr, int v) {
        if (featOkTst && casFeat_conditions == null)
      jcas.throwFeatMissing("conditions", "org.apache.uima.tm.textmarker.kernel.type.DebugEvaluatedCondition");
    ll_cas.ll_setRefValue(addr, casFeatCode_conditions, v);}
    
  /** @generated */
  public int getConditions(int addr, int i) {
        if (featOkTst && casFeat_conditions == null)
      jcas.throwFeatMissing("conditions", "org.apache.uima.tm.textmarker.kernel.type.DebugEvaluatedCondition");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_conditions), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_conditions), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_conditions), i);
  }
   
  /** @generated */
  public void setConditions(int addr, int i, int v) {
        if (featOkTst && casFeat_conditions == null)
      jcas.throwFeatMissing("conditions", "org.apache.uima.tm.textmarker.kernel.type.DebugEvaluatedCondition");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_conditions), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_conditions), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_conditions), i, v);
  }
 



  /**
   * initialize variables to correspond with Cas Type and Features
   * 
   * @generated
   */
  public DebugEvaluatedCondition_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_element = jcas.getRequiredFeatureDE(casType, "element", "uima.cas.String", featOkTst);
    casFeatCode_element  = (null == casFeat_element) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_element).getCode();

 
    casFeat_value = jcas.getRequiredFeatureDE(casType, "value", "uima.cas.Boolean", featOkTst);
    casFeatCode_value  = (null == casFeat_value) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_value).getCode();

 
    casFeat_conditions = jcas.getRequiredFeatureDE(casType, "conditions", "uima.cas.FSArray", featOkTst);
    casFeatCode_conditions  = (null == casFeat_conditions) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_conditions).getCode();

  }
}
