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
public class DebugFailedRuleMatch_Type extends DebugRuleMatch_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DebugFailedRuleMatch_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DebugFailedRuleMatch_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DebugFailedRuleMatch(addr, DebugFailedRuleMatch_Type.this);
  			   DebugFailedRuleMatch_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DebugFailedRuleMatch(addr, DebugFailedRuleMatch_Type.this);
  	  }
    };

  /** @generated */
  public final static int typeIndexID = DebugFailedRuleMatch.typeIndexID;

  /**
   * @generated
   * @modifiable
   */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.tm.textmarker.kernel.type.DebugFailedRuleMatch");

  /**
   * initialize variables to correspond with Cas Type and Features
   * 
   * @generated
   */
  public DebugFailedRuleMatch_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}
