
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
import org.apache.uima.jcas.cas.TOP_Type;

/** 
 * Updated by JCasGen Wed Jan 11 14:42:26 CET 2012
 * @generated */
public class DebugRuleElementMatches_Type extends TOP_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DebugRuleElementMatches_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DebugRuleElementMatches_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DebugRuleElementMatches(addr, DebugRuleElementMatches_Type.this);
  			   DebugRuleElementMatches_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DebugRuleElementMatches(addr, DebugRuleElementMatches_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = DebugRuleElementMatches.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.textmarker.type.DebugRuleElementMatches");
 
  /** @generated */
  final Feature casFeat_matches;
  /** @generated */
  final int     casFeatCode_matches;
  /** @generated */ 
  public int getMatches(int addr) {
        if (featOkTst && casFeat_matches == null)
      jcas.throwFeatMissing("matches", "org.apache.uima.textmarker.type.DebugRuleElementMatches");
    return ll_cas.ll_getRefValue(addr, casFeatCode_matches);
  }
  /** @generated */    
  public void setMatches(int addr, int v) {
        if (featOkTst && casFeat_matches == null)
      jcas.throwFeatMissing("matches", "org.apache.uima.textmarker.type.DebugRuleElementMatches");
    ll_cas.ll_setRefValue(addr, casFeatCode_matches, v);}
    
   /** @generated */
  public int getMatches(int addr, int i) {
        if (featOkTst && casFeat_matches == null)
      jcas.throwFeatMissing("matches", "org.apache.uima.textmarker.type.DebugRuleElementMatches");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_matches), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_matches), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_matches), i);
  }
   
  /** @generated */ 
  public void setMatches(int addr, int i, int v) {
        if (featOkTst && casFeat_matches == null)
      jcas.throwFeatMissing("matches", "org.apache.uima.textmarker.type.DebugRuleElementMatches");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_matches), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_matches), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_matches), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_element;
  /** @generated */
  final int     casFeatCode_element;
  /** @generated */ 
  public String getElement(int addr) {
        if (featOkTst && casFeat_element == null)
      jcas.throwFeatMissing("element", "org.apache.uima.textmarker.type.DebugRuleElementMatches");
    return ll_cas.ll_getStringValue(addr, casFeatCode_element);
  }
  /** @generated */    
  public void setElement(int addr, String v) {
        if (featOkTst && casFeat_element == null)
      jcas.throwFeatMissing("element", "org.apache.uima.textmarker.type.DebugRuleElementMatches");
    ll_cas.ll_setStringValue(addr, casFeatCode_element, v);}
    
  
 
  /** @generated */
  final Feature casFeat_ruleAnchor;
  /** @generated */
  final int     casFeatCode_ruleAnchor;
  /** @generated */ 
  public boolean getRuleAnchor(int addr) {
        if (featOkTst && casFeat_ruleAnchor == null)
      jcas.throwFeatMissing("ruleAnchor", "org.apache.uima.textmarker.type.DebugRuleElementMatches");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_ruleAnchor);
  }
  /** @generated */    
  public void setRuleAnchor(int addr, boolean v) {
        if (featOkTst && casFeat_ruleAnchor == null)
      jcas.throwFeatMissing("ruleAnchor", "org.apache.uima.textmarker.type.DebugRuleElementMatches");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_ruleAnchor, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DebugRuleElementMatches_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_matches = jcas.getRequiredFeatureDE(casType, "matches", "uima.cas.FSArray", featOkTst);
    casFeatCode_matches  = (null == casFeat_matches) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_matches).getCode();

 
    casFeat_element = jcas.getRequiredFeatureDE(casType, "element", "uima.cas.String", featOkTst);
    casFeatCode_element  = (null == casFeat_element) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_element).getCode();

 
    casFeat_ruleAnchor = jcas.getRequiredFeatureDE(casType, "ruleAnchor", "uima.cas.Boolean", featOkTst);
    casFeatCode_ruleAnchor  = (null == casFeat_ruleAnchor) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ruleAnchor).getCode();

  }
}



    