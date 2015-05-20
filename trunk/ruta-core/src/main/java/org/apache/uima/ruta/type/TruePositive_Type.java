/* First created by JCasGen Tue Aug 09 16:26:13 CEST 2011 */
package org.apache.uima.ruta.type;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;

/**
 * Updated by JCasGen Thu Jul 12 10:42:34 CEST 2012
 * 
 * @generated
 */
public class TruePositive_Type extends EvalAnnotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {
    return fsGenerator;
  }

  /** @generated */
  private final FSGenerator fsGenerator = new FSGenerator() {
    public FeatureStructure createFS(int addr, CASImpl cas) {
      if (TruePositive_Type.this.useExistingInstance) {
        // Return eq fs instance if already created
        FeatureStructure fs = TruePositive_Type.this.jcas.getJfsFromCaddr(addr);
        if (null == fs) {
          fs = new TruePositive(addr, TruePositive_Type.this);
          TruePositive_Type.this.jcas.putJfsFromCaddr(addr, fs);
          return fs;
        }
        return fs;
      } else
        return new TruePositive(addr, TruePositive_Type.this);
    }
  };

  /** @generated */
  public final static int typeIndexID = TruePositive.typeIndexID;

  /**
   * @generated
   * @modifiable
   */
  public final static boolean featOkTst = JCasRegistry
          .getFeatOkTst("org.apache.uima.ruta.type.TruePositive");

  /**
   * initialize variables to correspond with Cas Type and Features
   * 
   * @generated
   */
  public TruePositive_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

  }
}
