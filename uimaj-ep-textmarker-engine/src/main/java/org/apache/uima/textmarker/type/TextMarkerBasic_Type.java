
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
 * Updated by JCasGen Tue Sep 20 15:37:41 CEST 2011
 * @generated */
public class TextMarkerBasic_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (TextMarkerBasic_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = TextMarkerBasic_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new TextMarkerBasic(addr, TextMarkerBasic_Type.this);
  			   TextMarkerBasic_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new TextMarkerBasic(addr, TextMarkerBasic_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = TextMarkerBasic.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.textmarker.type.TextMarkerBasic");
 
  /** @generated */
  final Feature casFeat_replacement;
  /** @generated */
  final int     casFeatCode_replacement;
  /** @generated */ 
  public String getReplacement(int addr) {
        if (featOkTst && casFeat_replacement == null)
      jcas.throwFeatMissing("replacement", "org.apache.uima.textmarker.type.TextMarkerBasic");
    return ll_cas.ll_getStringValue(addr, casFeatCode_replacement);
  }
  /** @generated */    
  public void setReplacement(int addr, String v) {
        if (featOkTst && casFeat_replacement == null)
      jcas.throwFeatMissing("replacement", "org.apache.uima.textmarker.type.TextMarkerBasic");
    ll_cas.ll_setStringValue(addr, casFeatCode_replacement, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public TextMarkerBasic_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_replacement = jcas.getRequiredFeatureDE(casType, "replacement", "uima.cas.String", featOkTst);
    casFeatCode_replacement  = (null == casFeat_replacement) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_replacement).getCode();

  }
}



    