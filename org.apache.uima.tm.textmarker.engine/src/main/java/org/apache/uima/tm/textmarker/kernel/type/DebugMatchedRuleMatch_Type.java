/* First created by JCasGen Fri Dec 05 10:20:36 CET 2008 */
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
public class DebugMatchedRuleMatch_Type extends DebugRuleMatch_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DebugMatchedRuleMatch_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DebugMatchedRuleMatch_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DebugMatchedRuleMatch(addr, DebugMatchedRuleMatch_Type.this);
  			   DebugMatchedRuleMatch_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DebugMatchedRuleMatch(addr, DebugMatchedRuleMatch_Type.this);
  	  }
    };

  /** @generated */
  public final static int typeIndexID = DebugMatchedRuleMatch.typeIndexID;

  /**
   * @generated
   * @modifiable
   */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.tm.textmarker.kernel.type.DebugMatchedRuleMatch");

  /**
   * initialize variables to correspond with Cas Type and Features
   * 
   * @generated
   */
  public DebugMatchedRuleMatch_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}
