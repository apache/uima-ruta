
/* First created by JCasGen Tue Aug 09 16:26:13 CEST 2011 */
package org.apache.uima.textmarker.type;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;

/** 
 * Updated by JCasGen Tue Aug 09 16:26:13 CEST 2011
 * @generated */
public class DebugRuleElementMatch_Type extends ProfiledAnnotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DebugRuleElementMatch_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DebugRuleElementMatch_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DebugRuleElementMatch(addr, DebugRuleElementMatch_Type.this);
  			   DebugRuleElementMatch_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DebugRuleElementMatch(addr, DebugRuleElementMatch_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = DebugRuleElementMatch.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.textmarker.type.DebugRuleElementMatch");
 
  /** @generated */
  final Feature casFeat_baseCondition;
  /** @generated */
  final int     casFeatCode_baseCondition;
  /** @generated */ 
  public int getBaseCondition(int addr) {
        if (featOkTst && casFeat_baseCondition == null)
      jcas.throwFeatMissing("baseCondition", "org.apache.uima.textmarker.type.DebugRuleElementMatch");
    return ll_cas.ll_getRefValue(addr, casFeatCode_baseCondition);
  }
  /** @generated */    
  public void setBaseCondition(int addr, int v) {
        if (featOkTst && casFeat_baseCondition == null)
      jcas.throwFeatMissing("baseCondition", "org.apache.uima.textmarker.type.DebugRuleElementMatch");
    ll_cas.ll_setRefValue(addr, casFeatCode_baseCondition, v);}
    
  
 
  /** @generated */
  final Feature casFeat_conditions;
  /** @generated */
  final int     casFeatCode_conditions;
  /** @generated */ 
  public int getConditions(int addr) {
        if (featOkTst && casFeat_conditions == null)
      jcas.throwFeatMissing("conditions", "org.apache.uima.textmarker.type.DebugRuleElementMatch");
    return ll_cas.ll_getRefValue(addr, casFeatCode_conditions);
  }
  /** @generated */    
  public void setConditions(int addr, int v) {
        if (featOkTst && casFeat_conditions == null)
      jcas.throwFeatMissing("conditions", "org.apache.uima.textmarker.type.DebugRuleElementMatch");
    ll_cas.ll_setRefValue(addr, casFeatCode_conditions, v);}
    
   /** @generated */
  public int getConditions(int addr, int i) {
        if (featOkTst && casFeat_conditions == null)
      jcas.throwFeatMissing("conditions", "org.apache.uima.textmarker.type.DebugRuleElementMatch");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_conditions), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_conditions), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_conditions), i);
  }
   
  /** @generated */ 
  public void setConditions(int addr, int i, int v) {
        if (featOkTst && casFeat_conditions == null)
      jcas.throwFeatMissing("conditions", "org.apache.uima.textmarker.type.DebugRuleElementMatch");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_conditions), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_conditions), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_conditions), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DebugRuleElementMatch_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_baseCondition = jcas.getRequiredFeatureDE(casType, "baseCondition", "org.apache.uima.textmarker.type.DebugEvaluatedCondition", featOkTst);
    casFeatCode_baseCondition  = (null == casFeat_baseCondition) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_baseCondition).getCode();

 
    casFeat_conditions = jcas.getRequiredFeatureDE(casType, "conditions", "uima.cas.FSArray", featOkTst);
    casFeatCode_conditions  = (null == casFeat_conditions) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_conditions).getCode();

  }
}



    