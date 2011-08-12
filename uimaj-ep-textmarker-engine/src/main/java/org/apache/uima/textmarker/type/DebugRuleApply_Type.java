
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
public class DebugRuleApply_Type extends DebugScriptApply_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DebugRuleApply_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DebugRuleApply_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DebugRuleApply(addr, DebugRuleApply_Type.this);
  			   DebugRuleApply_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DebugRuleApply(addr, DebugRuleApply_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = DebugRuleApply.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.textmarker.type.DebugRuleApply");
 
  /** @generated */
  final Feature casFeat_applied;
  /** @generated */
  final int     casFeatCode_applied;
  /** @generated */ 
  public int getApplied(int addr) {
        if (featOkTst && casFeat_applied == null)
      jcas.throwFeatMissing("applied", "org.apache.uima.textmarker.type.DebugRuleApply");
    return ll_cas.ll_getIntValue(addr, casFeatCode_applied);
  }
  /** @generated */    
  public void setApplied(int addr, int v) {
        if (featOkTst && casFeat_applied == null)
      jcas.throwFeatMissing("applied", "org.apache.uima.textmarker.type.DebugRuleApply");
    ll_cas.ll_setIntValue(addr, casFeatCode_applied, v);}
    
  
 
  /** @generated */
  final Feature casFeat_tried;
  /** @generated */
  final int     casFeatCode_tried;
  /** @generated */ 
  public int getTried(int addr) {
        if (featOkTst && casFeat_tried == null)
      jcas.throwFeatMissing("tried", "org.apache.uima.textmarker.type.DebugRuleApply");
    return ll_cas.ll_getIntValue(addr, casFeatCode_tried);
  }
  /** @generated */    
  public void setTried(int addr, int v) {
        if (featOkTst && casFeat_tried == null)
      jcas.throwFeatMissing("tried", "org.apache.uima.textmarker.type.DebugRuleApply");
    ll_cas.ll_setIntValue(addr, casFeatCode_tried, v);}
    
  
 
  /** @generated */
  final Feature casFeat_rules;
  /** @generated */
  final int     casFeatCode_rules;
  /** @generated */ 
  public int getRules(int addr) {
        if (featOkTst && casFeat_rules == null)
      jcas.throwFeatMissing("rules", "org.apache.uima.textmarker.type.DebugRuleApply");
    return ll_cas.ll_getRefValue(addr, casFeatCode_rules);
  }
  /** @generated */    
  public void setRules(int addr, int v) {
        if (featOkTst && casFeat_rules == null)
      jcas.throwFeatMissing("rules", "org.apache.uima.textmarker.type.DebugRuleApply");
    ll_cas.ll_setRefValue(addr, casFeatCode_rules, v);}
    
   /** @generated */
  public int getRules(int addr, int i) {
        if (featOkTst && casFeat_rules == null)
      jcas.throwFeatMissing("rules", "org.apache.uima.textmarker.type.DebugRuleApply");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_rules), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_rules), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_rules), i);
  }
   
  /** @generated */ 
  public void setRules(int addr, int i, int v) {
        if (featOkTst && casFeat_rules == null)
      jcas.throwFeatMissing("rules", "org.apache.uima.textmarker.type.DebugRuleApply");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_rules), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_rules), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_rules), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DebugRuleApply_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_applied = jcas.getRequiredFeatureDE(casType, "applied", "uima.cas.Integer", featOkTst);
    casFeatCode_applied  = (null == casFeat_applied) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_applied).getCode();

 
    casFeat_tried = jcas.getRequiredFeatureDE(casType, "tried", "uima.cas.Integer", featOkTst);
    casFeatCode_tried  = (null == casFeat_tried) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_tried).getCode();

 
    casFeat_rules = jcas.getRequiredFeatureDE(casType, "rules", "uima.cas.FSArray", featOkTst);
    casFeatCode_rules  = (null == casFeat_rules) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_rules).getCode();

  }
}



    