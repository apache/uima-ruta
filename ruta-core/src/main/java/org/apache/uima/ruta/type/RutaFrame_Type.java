/* First created by JCasGen Tue Aug 09 16:26:13 CEST 2011 */
package org.apache.uima.ruta.type;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.tcas.Annotation_Type;

/**
 * Updated by JCasGen Thu Jul 12 10:42:34 CEST 2012
 * 
 * @generated
 */
public class RutaFrame_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {
    return fsGenerator;
  }

  /** @generated */
  private final FSGenerator fsGenerator = new FSGenerator() {
    public FeatureStructure createFS(int addr, CASImpl cas) {
      if (RutaFrame_Type.this.useExistingInstance) {
        // Return eq fs instance if already created
        FeatureStructure fs = RutaFrame_Type.this.jcas.getJfsFromCaddr(addr);
        if (null == fs) {
          fs = new RutaFrame(addr, RutaFrame_Type.this);
          RutaFrame_Type.this.jcas.putJfsFromCaddr(addr, fs);
          return fs;
        }
        return fs;
      } else
        return new RutaFrame(addr, RutaFrame_Type.this);
    }
  };

  /** @generated */
  public final static int typeIndexID = RutaFrame.typeIndexID;

  /**
   * @generated
   * @modifiable
   */
  public final static boolean featOkTst = JCasRegistry
          .getFeatOkTst("org.apache.uima.ruta.type.RutaFrame");

  /**
   * initialize variables to correspond with Cas Type and Features
   * 
   * @generated
   */
  public RutaFrame_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

  }
}
