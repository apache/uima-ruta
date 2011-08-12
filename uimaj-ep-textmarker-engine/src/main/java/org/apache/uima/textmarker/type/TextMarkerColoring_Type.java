
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
 * Updated by JCasGen Tue Aug 09 16:26:13 CEST 2011
 * @generated */
public class TextMarkerColoring_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (TextMarkerColoring_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = TextMarkerColoring_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new TextMarkerColoring(addr, TextMarkerColoring_Type.this);
  			   TextMarkerColoring_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new TextMarkerColoring(addr, TextMarkerColoring_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = TextMarkerColoring.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.textmarker.type.TextMarkerColoring");
 
  /** @generated */
  final Feature casFeat_bgColor;
  /** @generated */
  final int     casFeatCode_bgColor;
  /** @generated */ 
  public String getBgColor(int addr) {
        if (featOkTst && casFeat_bgColor == null)
      jcas.throwFeatMissing("bgColor", "org.apache.uima.textmarker.type.TextMarkerColoring");
    return ll_cas.ll_getStringValue(addr, casFeatCode_bgColor);
  }
  /** @generated */    
  public void setBgColor(int addr, String v) {
        if (featOkTst && casFeat_bgColor == null)
      jcas.throwFeatMissing("bgColor", "org.apache.uima.textmarker.type.TextMarkerColoring");
    ll_cas.ll_setStringValue(addr, casFeatCode_bgColor, v);}
    
  
 
  /** @generated */
  final Feature casFeat_targetType;
  /** @generated */
  final int     casFeatCode_targetType;
  /** @generated */ 
  public String getTargetType(int addr) {
        if (featOkTst && casFeat_targetType == null)
      jcas.throwFeatMissing("targetType", "org.apache.uima.textmarker.type.TextMarkerColoring");
    return ll_cas.ll_getStringValue(addr, casFeatCode_targetType);
  }
  /** @generated */    
  public void setTargetType(int addr, String v) {
        if (featOkTst && casFeat_targetType == null)
      jcas.throwFeatMissing("targetType", "org.apache.uima.textmarker.type.TextMarkerColoring");
    ll_cas.ll_setStringValue(addr, casFeatCode_targetType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_fgColor;
  /** @generated */
  final int     casFeatCode_fgColor;
  /** @generated */ 
  public String getFgColor(int addr) {
        if (featOkTst && casFeat_fgColor == null)
      jcas.throwFeatMissing("fgColor", "org.apache.uima.textmarker.type.TextMarkerColoring");
    return ll_cas.ll_getStringValue(addr, casFeatCode_fgColor);
  }
  /** @generated */    
  public void setFgColor(int addr, String v) {
        if (featOkTst && casFeat_fgColor == null)
      jcas.throwFeatMissing("fgColor", "org.apache.uima.textmarker.type.TextMarkerColoring");
    ll_cas.ll_setStringValue(addr, casFeatCode_fgColor, v);}
    
  
 
  /** @generated */
  final Feature casFeat_selected;
  /** @generated */
  final int     casFeatCode_selected;
  /** @generated */ 
  public boolean getSelected(int addr) {
        if (featOkTst && casFeat_selected == null)
      jcas.throwFeatMissing("selected", "org.apache.uima.textmarker.type.TextMarkerColoring");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_selected);
  }
  /** @generated */    
  public void setSelected(int addr, boolean v) {
        if (featOkTst && casFeat_selected == null)
      jcas.throwFeatMissing("selected", "org.apache.uima.textmarker.type.TextMarkerColoring");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_selected, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public TextMarkerColoring_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_bgColor = jcas.getRequiredFeatureDE(casType, "bgColor", "uima.cas.String", featOkTst);
    casFeatCode_bgColor  = (null == casFeat_bgColor) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_bgColor).getCode();

 
    casFeat_targetType = jcas.getRequiredFeatureDE(casType, "targetType", "uima.cas.String", featOkTst);
    casFeatCode_targetType  = (null == casFeat_targetType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_targetType).getCode();

 
    casFeat_fgColor = jcas.getRequiredFeatureDE(casType, "fgColor", "uima.cas.String", featOkTst);
    casFeatCode_fgColor  = (null == casFeat_fgColor) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_fgColor).getCode();

 
    casFeat_selected = jcas.getRequiredFeatureDE(casType, "selected", "uima.cas.Boolean", featOkTst);
    casFeatCode_selected  = (null == casFeat_selected) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_selected).getCode();

  }
}



    