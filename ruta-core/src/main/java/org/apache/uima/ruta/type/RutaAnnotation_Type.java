/* First created by JCasGen Tue Aug 09 16:26:13 CEST 2011 */
package org.apache.uima.ruta.type;

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
 * 
 * @generated
 */
public class RutaAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {
    return fsGenerator;
  }

  /** @generated */
  private final FSGenerator fsGenerator = new FSGenerator() {
    public FeatureStructure createFS(int addr, CASImpl cas) {
      if (RutaAnnotation_Type.this.useExistingInstance) {
        // Return eq fs instance if already created
        FeatureStructure fs = RutaAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
        if (null == fs) {
          fs = new RutaAnnotation(addr, RutaAnnotation_Type.this);
          RutaAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
          return fs;
        }
        return fs;
      } else
        return new RutaAnnotation(addr, RutaAnnotation_Type.this);
    }
  };

  /** @generated */
  public final static int typeIndexID = RutaAnnotation.typeIndexID;

  /**
   * @generated
   * @modifiable
   */
  public final static boolean featOkTst = JCasRegistry
          .getFeatOkTst("org.apache.uima.ruta.type.RutaAnnotation");

  /** @generated */
  final Feature casFeat_score;

  /** @generated */
  final int casFeatCode_score;

  /** @generated */
  public double getScore(int addr) {
    if (featOkTst && casFeat_score == null)
      jcas.throwFeatMissing("score", "org.apache.uima.ruta.type.RutaAnnotation");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_score);
  }

  /** @generated */
  public void setScore(int addr, double v) {
    if (featOkTst && casFeat_score == null)
      jcas.throwFeatMissing("score", "org.apache.uima.ruta.type.RutaAnnotation");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_score, v);
  }

  /** @generated */
  final Feature casFeat_annotation;

  /** @generated */
  final int casFeatCode_annotation;

  /** @generated */
  public int getAnnotation(int addr) {
    if (featOkTst && casFeat_annotation == null)
      jcas.throwFeatMissing("annotation", "org.apache.uima.ruta.type.RutaAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_annotation);
  }

  /** @generated */
  public void setAnnotation(int addr, int v) {
    if (featOkTst && casFeat_annotation == null)
      jcas.throwFeatMissing("annotation", "org.apache.uima.ruta.type.RutaAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_annotation, v);
  }

  /**
   * initialize variables to correspond with Cas Type and Features
   * 
   * @generated
   */
  public RutaAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

    casFeat_score = jcas.getRequiredFeatureDE(casType, "score", "uima.cas.Double", featOkTst);
    casFeatCode_score = (null == casFeat_score) ? JCas.INVALID_FEATURE_CODE
            : ((FeatureImpl) casFeat_score).getCode();

    casFeat_annotation = jcas.getRequiredFeatureDE(casType, "annotation", "uima.tcas.Annotation",
            featOkTst);
    casFeatCode_annotation = (null == casFeat_annotation) ? JCas.INVALID_FEATURE_CODE
            : ((FeatureImpl) casFeat_annotation).getCode();

  }
}
