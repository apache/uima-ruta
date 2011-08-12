
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
public class DebugRuleMatch_Type extends ProfiledAnnotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DebugRuleMatch_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DebugRuleMatch_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DebugRuleMatch(addr, DebugRuleMatch_Type.this);
  			   DebugRuleMatch_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DebugRuleMatch(addr, DebugRuleMatch_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = DebugRuleMatch.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.textmarker.type.DebugRuleMatch");
 
  /** @generated */
  final Feature casFeat_elements;
  /** @generated */
  final int     casFeatCode_elements;
  /** @generated */ 
  public int getElements(int addr) {
        if (featOkTst && casFeat_elements == null)
      jcas.throwFeatMissing("elements", "org.apache.uima.textmarker.type.DebugRuleMatch");
    return ll_cas.ll_getRefValue(addr, casFeatCode_elements);
  }
  /** @generated */    
  public void setElements(int addr, int v) {
        if (featOkTst && casFeat_elements == null)
      jcas.throwFeatMissing("elements", "org.apache.uima.textmarker.type.DebugRuleMatch");
    ll_cas.ll_setRefValue(addr, casFeatCode_elements, v);}
    
   /** @generated */
  public int getElements(int addr, int i) {
        if (featOkTst && casFeat_elements == null)
      jcas.throwFeatMissing("elements", "org.apache.uima.textmarker.type.DebugRuleMatch");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_elements), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_elements), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_elements), i);
  }
   
  /** @generated */ 
  public void setElements(int addr, int i, int v) {
        if (featOkTst && casFeat_elements == null)
      jcas.throwFeatMissing("elements", "org.apache.uima.textmarker.type.DebugRuleMatch");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_elements), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_elements), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_elements), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_matched;
  /** @generated */
  final int     casFeatCode_matched;
  /** @generated */ 
  public boolean getMatched(int addr) {
        if (featOkTst && casFeat_matched == null)
      jcas.throwFeatMissing("matched", "org.apache.uima.textmarker.type.DebugRuleMatch");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_matched);
  }
  /** @generated */    
  public void setMatched(int addr, boolean v) {
        if (featOkTst && casFeat_matched == null)
      jcas.throwFeatMissing("matched", "org.apache.uima.textmarker.type.DebugRuleMatch");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_matched, v);}
    
  
 
  /** @generated */
  final Feature casFeat_delegates;
  /** @generated */
  final int     casFeatCode_delegates;
  /** @generated */ 
  public int getDelegates(int addr) {
        if (featOkTst && casFeat_delegates == null)
      jcas.throwFeatMissing("delegates", "org.apache.uima.textmarker.type.DebugRuleMatch");
    return ll_cas.ll_getRefValue(addr, casFeatCode_delegates);
  }
  /** @generated */    
  public void setDelegates(int addr, int v) {
        if (featOkTst && casFeat_delegates == null)
      jcas.throwFeatMissing("delegates", "org.apache.uima.textmarker.type.DebugRuleMatch");
    ll_cas.ll_setRefValue(addr, casFeatCode_delegates, v);}
    
   /** @generated */
  public int getDelegates(int addr, int i) {
        if (featOkTst && casFeat_delegates == null)
      jcas.throwFeatMissing("delegates", "org.apache.uima.textmarker.type.DebugRuleMatch");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_delegates), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_delegates), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_delegates), i);
  }
   
  /** @generated */ 
  public void setDelegates(int addr, int i, int v) {
        if (featOkTst && casFeat_delegates == null)
      jcas.throwFeatMissing("delegates", "org.apache.uima.textmarker.type.DebugRuleMatch");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_delegates), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_delegates), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_delegates), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DebugRuleMatch_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_elements = jcas.getRequiredFeatureDE(casType, "elements", "uima.cas.FSArray", featOkTst);
    casFeatCode_elements  = (null == casFeat_elements) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_elements).getCode();

 
    casFeat_matched = jcas.getRequiredFeatureDE(casType, "matched", "uima.cas.Boolean", featOkTst);
    casFeatCode_matched  = (null == casFeat_matched) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_matched).getCode();

 
    casFeat_delegates = jcas.getRequiredFeatureDE(casType, "delegates", "uima.cas.FSArray", featOkTst);
    casFeatCode_delegates  = (null == casFeat_delegates) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_delegates).getCode();

  }
}



    