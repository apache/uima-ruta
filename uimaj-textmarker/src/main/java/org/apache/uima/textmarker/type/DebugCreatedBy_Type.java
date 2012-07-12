
/* First created by JCasGen Wed Jul 11 15:10:37 CEST 2012 */
package org.apache.uima.textmarker.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.cas.TOP_Type;

/** 
 * Updated by JCasGen Thu Jul 12 10:42:33 CEST 2012
 * @generated */
public class DebugCreatedBy_Type extends TOP_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DebugCreatedBy_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DebugCreatedBy_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DebugCreatedBy(addr, DebugCreatedBy_Type.this);
  			   DebugCreatedBy_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DebugCreatedBy(addr, DebugCreatedBy_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = DebugCreatedBy.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.textmarker.type.DebugCreatedBy");
 
  /** @generated */
  final Feature casFeat_rule;
  /** @generated */
  final int     casFeatCode_rule;
  /** @generated */ 
  public String getRule(int addr) {
        if (featOkTst && casFeat_rule == null)
      jcas.throwFeatMissing("rule", "org.apache.uima.textmarker.type.DebugCreatedBy");
    return ll_cas.ll_getStringValue(addr, casFeatCode_rule);
  }
  /** @generated */    
  public void setRule(int addr, String v) {
        if (featOkTst && casFeat_rule == null)
      jcas.throwFeatMissing("rule", "org.apache.uima.textmarker.type.DebugCreatedBy");
    ll_cas.ll_setStringValue(addr, casFeatCode_rule, v);}
    
  
 
  /** @generated */
  final Feature casFeat_annotation;
  /** @generated */
  final int     casFeatCode_annotation;
  /** @generated */ 
  public int getAnnotation(int addr) {
        if (featOkTst && casFeat_annotation == null)
      jcas.throwFeatMissing("annotation", "org.apache.uima.textmarker.type.DebugCreatedBy");
    return ll_cas.ll_getRefValue(addr, casFeatCode_annotation);
  }
  /** @generated */    
  public void setAnnotation(int addr, int v) {
        if (featOkTst && casFeat_annotation == null)
      jcas.throwFeatMissing("annotation", "org.apache.uima.textmarker.type.DebugCreatedBy");
    ll_cas.ll_setRefValue(addr, casFeatCode_annotation, v);}
    
  
 
  /** @generated */
  final Feature casFeat_script;
  /** @generated */
  final int     casFeatCode_script;
  /** @generated */ 
  public String getScript(int addr) {
        if (featOkTst && casFeat_script == null)
      jcas.throwFeatMissing("script", "org.apache.uima.textmarker.type.DebugCreatedBy");
    return ll_cas.ll_getStringValue(addr, casFeatCode_script);
  }
  /** @generated */    
  public void setScript(int addr, String v) {
        if (featOkTst && casFeat_script == null)
      jcas.throwFeatMissing("script", "org.apache.uima.textmarker.type.DebugCreatedBy");
    ll_cas.ll_setStringValue(addr, casFeatCode_script, v);}
    
  
 
  /** @generated */
  final Feature casFeat_id;
  /** @generated */
  final int     casFeatCode_id;
  /** @generated */ 
  public int getId(int addr) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "org.apache.uima.textmarker.type.DebugCreatedBy");
    return ll_cas.ll_getIntValue(addr, casFeatCode_id);
  }
  /** @generated */    
  public void setId(int addr, int v) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "org.apache.uima.textmarker.type.DebugCreatedBy");
    ll_cas.ll_setIntValue(addr, casFeatCode_id, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DebugCreatedBy_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_rule = jcas.getRequiredFeatureDE(casType, "rule", "uima.cas.String", featOkTst);
    casFeatCode_rule  = (null == casFeat_rule) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_rule).getCode();

 
    casFeat_annotation = jcas.getRequiredFeatureDE(casType, "annotation", "uima.tcas.Annotation", featOkTst);
    casFeatCode_annotation  = (null == casFeat_annotation) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_annotation).getCode();

 
    casFeat_script = jcas.getRequiredFeatureDE(casType, "script", "uima.cas.String", featOkTst);
    casFeatCode_script  = (null == casFeat_script) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_script).getCode();

 
    casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.Integer", featOkTst);
    casFeatCode_id  = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_id).getCode();

  }
}



    