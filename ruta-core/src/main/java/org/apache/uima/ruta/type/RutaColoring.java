
/* First created by JCasGen Tue Aug 09 16:26:13 CEST 2011 */
package org.apache.uima.ruta.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

/**
 * Updated by JCasGen Thu Jul 12 10:42:34 CEST 2012 XML source:
 * D:/work/workspace-uima6/uimaj-ruta/src
 * /main/java/org/apache/uima/ruta/engine/InternalTypeSystem.xml
 * 
 * @generated
 */
public class RutaColoring extends Annotation {
  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(RutaColoring.class);

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
  protected RutaColoring() {/* intentionally empty block */
  }

  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public RutaColoring(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }

  /** @generated */
  public RutaColoring(JCas jcas) {
    super(jcas);
    readObject();
  }

  /** @generated */
  public RutaColoring(JCas jcas, int begin, int end) {
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
  // * Feature: bgColor

  /**
   * getter for bgColor - gets
   * 
   * @generated
   */
  public String getBgColor() {
    if (RutaColoring_Type.featOkTst && ((RutaColoring_Type) jcasType).casFeat_bgColor == null)
      jcasType.jcas.throwFeatMissing("bgColor", "org.apache.uima.ruta.type.RutaColoring");
    return jcasType.ll_cas.ll_getStringValue(addr,
            ((RutaColoring_Type) jcasType).casFeatCode_bgColor);
  }

  /**
   * setter for bgColor - sets
   * 
   * @generated
   */
  public void setBgColor(String v) {
    if (RutaColoring_Type.featOkTst && ((RutaColoring_Type) jcasType).casFeat_bgColor == null)
      jcasType.jcas.throwFeatMissing("bgColor", "org.apache.uima.ruta.type.RutaColoring");
    jcasType.ll_cas.ll_setStringValue(addr, ((RutaColoring_Type) jcasType).casFeatCode_bgColor, v);
  }

  // *--------------*
  // * Feature: targetType

  /**
   * getter for targetType - gets
   * 
   * @generated
   */
  public String getTargetType() {
    if (RutaColoring_Type.featOkTst && ((RutaColoring_Type) jcasType).casFeat_targetType == null)
      jcasType.jcas.throwFeatMissing("targetType", "org.apache.uima.ruta.type.RutaColoring");
    return jcasType.ll_cas.ll_getStringValue(addr,
            ((RutaColoring_Type) jcasType).casFeatCode_targetType);
  }

  /**
   * setter for targetType - sets
   * 
   * @generated
   */
  public void setTargetType(String v) {
    if (RutaColoring_Type.featOkTst && ((RutaColoring_Type) jcasType).casFeat_targetType == null)
      jcasType.jcas.throwFeatMissing("targetType", "org.apache.uima.ruta.type.RutaColoring");
    jcasType.ll_cas.ll_setStringValue(addr, ((RutaColoring_Type) jcasType).casFeatCode_targetType,
            v);
  }

  // *--------------*
  // * Feature: fgColor

  /**
   * getter for fgColor - gets
   * 
   * @generated
   */
  public String getFgColor() {
    if (RutaColoring_Type.featOkTst && ((RutaColoring_Type) jcasType).casFeat_fgColor == null)
      jcasType.jcas.throwFeatMissing("fgColor", "org.apache.uima.ruta.type.RutaColoring");
    return jcasType.ll_cas.ll_getStringValue(addr,
            ((RutaColoring_Type) jcasType).casFeatCode_fgColor);
  }

  /**
   * setter for fgColor - sets
   * 
   * @generated
   */
  public void setFgColor(String v) {
    if (RutaColoring_Type.featOkTst && ((RutaColoring_Type) jcasType).casFeat_fgColor == null)
      jcasType.jcas.throwFeatMissing("fgColor", "org.apache.uima.ruta.type.RutaColoring");
    jcasType.ll_cas.ll_setStringValue(addr, ((RutaColoring_Type) jcasType).casFeatCode_fgColor, v);
  }

  // *--------------*
  // * Feature: selected

  /**
   * getter for selected - gets
   * 
   * @generated
   */
  public boolean getSelected() {
    if (RutaColoring_Type.featOkTst && ((RutaColoring_Type) jcasType).casFeat_selected == null)
      jcasType.jcas.throwFeatMissing("selected", "org.apache.uima.ruta.type.RutaColoring");
    return jcasType.ll_cas.ll_getBooleanValue(addr,
            ((RutaColoring_Type) jcasType).casFeatCode_selected);
  }

  /**
   * setter for selected - sets
   * 
   * @generated
   */
  public void setSelected(boolean v) {
    if (RutaColoring_Type.featOkTst && ((RutaColoring_Type) jcasType).casFeat_selected == null)
      jcasType.jcas.throwFeatMissing("selected", "org.apache.uima.ruta.type.RutaColoring");
    jcasType.ll_cas
            .ll_setBooleanValue(addr, ((RutaColoring_Type) jcasType).casFeatCode_selected, v);
  }
}
