
/* First created by JCasGen Tue Aug 09 16:26:13 CEST 2011 */
package org.apache.uima.ruta.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

/**
 * Updated by JCasGen Thu Jul 12 10:42:33 CEST 2012 XML source:
 * D:/work/workspace-uima6/uimaj-ruta/src
 * /main/java/org/apache/uima/ruta/engine/InternalTypeSystem.xml
 * 
 * @generated
 */
public class RutaAnnotation extends Annotation {
  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(RutaAnnotation.class);

  /**
   * @generated
   * @ordered
   */
  public final static int type = typeIndexID;

  /** @generated */
  public int getTypeIndexID() {
    return typeIndexID;
  }

  /**
   * Never called. Disable default constructor
   * 
   * @generated
   */
  protected RutaAnnotation() {/* intentionally empty block */
  }

  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public RutaAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }

  /** @generated */
  public RutaAnnotation(JCas jcas) {
    super(jcas);
    readObject();
  }

  /** @generated */
  public RutaAnnotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }

  /**
   * <!-- begin-user-doc --> Write your own initialization here <!-- end-user-doc -->
   * 
   * @generated modifiable
   */
  private void readObject() {
  }

  // *--------------*
  // * Feature: score

  /**
   * getter for score - gets
   * 
   * @generated
   */
  public double getScore() {
    if (RutaAnnotation_Type.featOkTst && ((RutaAnnotation_Type) jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "org.apache.uima.ruta.type.RutaAnnotation");
    return jcasType.ll_cas.ll_getDoubleValue(addr,
            ((RutaAnnotation_Type) jcasType).casFeatCode_score);
  }

  /**
   * setter for score - sets
   * 
   * @generated
   */
  public void setScore(double v) {
    if (RutaAnnotation_Type.featOkTst && ((RutaAnnotation_Type) jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "org.apache.uima.ruta.type.RutaAnnotation");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((RutaAnnotation_Type) jcasType).casFeatCode_score, v);
  }

  // *--------------*
  // * Feature: annotation

  /**
   * getter for annotation - gets
   * 
   * @generated
   */
  public Annotation getAnnotation() {
    if (RutaAnnotation_Type.featOkTst
            && ((RutaAnnotation_Type) jcasType).casFeat_annotation == null)
      jcasType.jcas.throwFeatMissing("annotation", "org.apache.uima.ruta.type.RutaAnnotation");
    return (Annotation) (jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr,
            ((RutaAnnotation_Type) jcasType).casFeatCode_annotation)));
  }

  /**
   * setter for annotation - sets
   * 
   * @generated
   */
  public void setAnnotation(Annotation v) {
    if (RutaAnnotation_Type.featOkTst
            && ((RutaAnnotation_Type) jcasType).casFeat_annotation == null)
      jcasType.jcas.throwFeatMissing("annotation", "org.apache.uima.ruta.type.RutaAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((RutaAnnotation_Type) jcasType).casFeatCode_annotation,
            jcasType.ll_cas.ll_getFSRef(v));
  }
}
