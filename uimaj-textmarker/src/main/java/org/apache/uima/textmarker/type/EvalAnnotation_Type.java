
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
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Thu Jul 12 10:42:33 CEST 2012
 * @generated */
public class EvalAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (EvalAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = EvalAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new EvalAnnotation(addr, EvalAnnotation_Type.this);
  			   EvalAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new EvalAnnotation(addr, EvalAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = EvalAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.textmarker.type.EvalAnnotation");
 
  /** @generated */
  final Feature casFeat_original;
  /** @generated */
  final int     casFeatCode_original;
  /** @generated */ 
  public int getOriginal(int addr) {
        if (featOkTst && casFeat_original == null)
      jcas.throwFeatMissing("original", "org.apache.uima.textmarker.type.EvalAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_original);
  }
  /** @generated */    
  public void setOriginal(int addr, int v) {
        if (featOkTst && casFeat_original == null)
      jcas.throwFeatMissing("original", "org.apache.uima.textmarker.type.EvalAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_original, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public EvalAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_original = jcas.getRequiredFeatureDE(casType, "original", "uima.tcas.Annotation", featOkTst);
    casFeatCode_original  = (null == casFeat_original) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_original).getCode();

  }
}



    