/* First created by JCasGen Tue Jan 20 17:25:38 CET 2009 */
package org.apache.uima.tm.textmarker.kernel.type;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;

/** 
 * Updated by JCasGen Tue Jan 25 09:29:36 CET 2011
 * @generated */
public class FalseNegative_Type extends EvalAnnotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (FalseNegative_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = FalseNegative_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new FalseNegative(addr, FalseNegative_Type.this);
  			   FalseNegative_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new FalseNegative(addr, FalseNegative_Type.this);
  	  }
    };

  /** @generated */
  public final static int typeIndexID = FalseNegative.typeIndexID;

  /**
   * @generated
   * @modifiable
   */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.tm.textmarker.kernel.type.FalseNegative");

  /**
   * initialize variables to correspond with Cas Type and Features
   * 
   * @generated
   */
  public FalseNegative_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}
