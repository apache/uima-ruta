/* First created by JCasGen Thu Sep 03 16:30:49 CEST 2009 */
package org.apache.uima.tm.textmarker.kernel.type;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Tue Jan 25 09:29:36 CET 2011
 * @generated */
public class TextMarkerFrame_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (TextMarkerFrame_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = TextMarkerFrame_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new TextMarkerFrame(addr, TextMarkerFrame_Type.this);
  			   TextMarkerFrame_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new TextMarkerFrame(addr, TextMarkerFrame_Type.this);
  	  }
    };

  /** @generated */
  public final static int typeIndexID = TextMarkerFrame.typeIndexID;

  /**
   * @generated
   * @modifiable
   */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.tm.textmarker.kernel.type.TextMarkerFrame");

  /**
   * initialize variables to correspond with Cas Type and Features
   * 
   * @generated
   */
  public TextMarkerFrame_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}
