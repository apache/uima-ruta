
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
public class DebugBlockApply_Type extends DebugRuleApply_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DebugBlockApply_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DebugBlockApply_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DebugBlockApply(addr, DebugBlockApply_Type.this);
  			   DebugBlockApply_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DebugBlockApply(addr, DebugBlockApply_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = DebugBlockApply.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.textmarker.type.DebugBlockApply");
 
  /** @generated */
  final Feature casFeat_innerApply;
  /** @generated */
  final int     casFeatCode_innerApply;
  /** @generated */ 
  public int getInnerApply(int addr) {
        if (featOkTst && casFeat_innerApply == null)
      jcas.throwFeatMissing("innerApply", "org.apache.uima.textmarker.type.DebugBlockApply");
    return ll_cas.ll_getRefValue(addr, casFeatCode_innerApply);
  }
  /** @generated */    
  public void setInnerApply(int addr, int v) {
        if (featOkTst && casFeat_innerApply == null)
      jcas.throwFeatMissing("innerApply", "org.apache.uima.textmarker.type.DebugBlockApply");
    ll_cas.ll_setRefValue(addr, casFeatCode_innerApply, v);}
    
   /** @generated */
  public int getInnerApply(int addr, int i) {
        if (featOkTst && casFeat_innerApply == null)
      jcas.throwFeatMissing("innerApply", "org.apache.uima.textmarker.type.DebugBlockApply");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_innerApply), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_innerApply), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_innerApply), i);
  }
   
  /** @generated */ 
  public void setInnerApply(int addr, int i, int v) {
        if (featOkTst && casFeat_innerApply == null)
      jcas.throwFeatMissing("innerApply", "org.apache.uima.textmarker.type.DebugBlockApply");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_innerApply), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_innerApply), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_innerApply), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DebugBlockApply_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_innerApply = jcas.getRequiredFeatureDE(casType, "innerApply", "uima.cas.FSArray", featOkTst);
    casFeatCode_innerApply  = (null == casFeat_innerApply) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_innerApply).getCode();

  }
}



    