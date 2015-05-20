
/* First created by JCasGen Tue Aug 09 16:26:13 CEST 2011 */
package org.apache.uima.ruta.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.DoubleArray;
import org.apache.uima.jcas.cas.IntegerArray;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.cas.TOP_Type;

/**
 * Updated by JCasGen Thu Jul 12 10:42:33 CEST 2012 XML source:
 * D:/work/workspace-uima6/uimaj-ruta/src
 * /main/java/org/apache/uima/ruta/engine/InternalTypeSystem.xml
 * 
 * @generated
 */
public class Statistics extends TOP {
  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(Statistics.class);

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
  protected Statistics() {/* intentionally empty block */
  }

  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public Statistics(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }

  /** @generated */
  public Statistics(JCas jcas) {
    super(jcas);
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
  // * Feature: name

  /**
   * getter for name - gets
   * 
   * @generated
   */
  public StringArray getName() {
    if (Statistics_Type.featOkTst && ((Statistics_Type) jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "org.apache.uima.ruta.type.Statistics");
    return (StringArray) (jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr,
            ((Statistics_Type) jcasType).casFeatCode_name)));
  }

  /**
   * setter for name - sets
   * 
   * @generated
   */
  public void setName(StringArray v) {
    if (Statistics_Type.featOkTst && ((Statistics_Type) jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "org.apache.uima.ruta.type.Statistics");
    jcasType.ll_cas.ll_setRefValue(addr, ((Statistics_Type) jcasType).casFeatCode_name,
            jcasType.ll_cas.ll_getFSRef(v));
  }

  /**
   * indexed getter for name - gets an indexed value -
   * 
   * @generated
   */
  public String getName(int i) {
    if (Statistics_Type.featOkTst && ((Statistics_Type) jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "org.apache.uima.ruta.type.Statistics");
    jcasType.jcas.checkArrayBounds(
            jcasType.ll_cas.ll_getRefValue(addr, ((Statistics_Type) jcasType).casFeatCode_name), i);
    return jcasType.ll_cas.ll_getStringArrayValue(
            jcasType.ll_cas.ll_getRefValue(addr, ((Statistics_Type) jcasType).casFeatCode_name), i);
  }

  /**
   * indexed setter for name - sets an indexed value -
   * 
   * @generated
   */
  public void setName(int i, String v) {
    if (Statistics_Type.featOkTst && ((Statistics_Type) jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "org.apache.uima.ruta.type.Statistics");
    jcasType.jcas.checkArrayBounds(
            jcasType.ll_cas.ll_getRefValue(addr, ((Statistics_Type) jcasType).casFeatCode_name), i);
    jcasType.ll_cas.ll_setStringArrayValue(
            jcasType.ll_cas.ll_getRefValue(addr, ((Statistics_Type) jcasType).casFeatCode_name), i,
            v);
  }

  // *--------------*
  // * Feature: total

  /**
   * getter for total - gets
   * 
   * @generated
   */
  public DoubleArray getTotal() {
    if (Statistics_Type.featOkTst && ((Statistics_Type) jcasType).casFeat_total == null)
      jcasType.jcas.throwFeatMissing("total", "org.apache.uima.ruta.type.Statistics");
    return (DoubleArray) (jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr,
            ((Statistics_Type) jcasType).casFeatCode_total)));
  }

  /**
   * setter for total - sets
   * 
   * @generated
   */
  public void setTotal(DoubleArray v) {
    if (Statistics_Type.featOkTst && ((Statistics_Type) jcasType).casFeat_total == null)
      jcasType.jcas.throwFeatMissing("total", "org.apache.uima.ruta.type.Statistics");
    jcasType.ll_cas.ll_setRefValue(addr, ((Statistics_Type) jcasType).casFeatCode_total,
            jcasType.ll_cas.ll_getFSRef(v));
  }

  /**
   * indexed getter for total - gets an indexed value -
   * 
   * @generated
   */
  public double getTotal(int i) {
    if (Statistics_Type.featOkTst && ((Statistics_Type) jcasType).casFeat_total == null)
      jcasType.jcas.throwFeatMissing("total", "org.apache.uima.ruta.type.Statistics");
    jcasType.jcas
            .checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr,
                    ((Statistics_Type) jcasType).casFeatCode_total), i);
    return jcasType.ll_cas
            .ll_getDoubleArrayValue(jcasType.ll_cas.ll_getRefValue(addr,
                    ((Statistics_Type) jcasType).casFeatCode_total), i);
  }

  /**
   * indexed setter for total - sets an indexed value -
   * 
   * @generated
   */
  public void setTotal(int i, double v) {
    if (Statistics_Type.featOkTst && ((Statistics_Type) jcasType).casFeat_total == null)
      jcasType.jcas.throwFeatMissing("total", "org.apache.uima.ruta.type.Statistics");
    jcasType.jcas
            .checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr,
                    ((Statistics_Type) jcasType).casFeatCode_total), i);
    jcasType.ll_cas.ll_setDoubleArrayValue(
            jcasType.ll_cas.ll_getRefValue(addr, ((Statistics_Type) jcasType).casFeatCode_total),
            i, v);
  }

  // *--------------*
  // * Feature: amount

  /**
   * getter for amount - gets
   * 
   * @generated
   */
  public IntegerArray getAmount() {
    if (Statistics_Type.featOkTst && ((Statistics_Type) jcasType).casFeat_amount == null)
      jcasType.jcas.throwFeatMissing("amount", "org.apache.uima.ruta.type.Statistics");
    return (IntegerArray) (jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr,
            ((Statistics_Type) jcasType).casFeatCode_amount)));
  }

  /**
   * setter for amount - sets
   * 
   * @generated
   */
  public void setAmount(IntegerArray v) {
    if (Statistics_Type.featOkTst && ((Statistics_Type) jcasType).casFeat_amount == null)
      jcasType.jcas.throwFeatMissing("amount", "org.apache.uima.ruta.type.Statistics");
    jcasType.ll_cas.ll_setRefValue(addr, ((Statistics_Type) jcasType).casFeatCode_amount,
            jcasType.ll_cas.ll_getFSRef(v));
  }

  /**
   * indexed getter for amount - gets an indexed value -
   * 
   * @generated
   */
  public int getAmount(int i) {
    if (Statistics_Type.featOkTst && ((Statistics_Type) jcasType).casFeat_amount == null)
      jcasType.jcas.throwFeatMissing("amount", "org.apache.uima.ruta.type.Statistics");
    jcasType.jcas.checkArrayBounds(
            jcasType.ll_cas.ll_getRefValue(addr, ((Statistics_Type) jcasType).casFeatCode_amount),
            i);
    return jcasType.ll_cas.ll_getIntArrayValue(
            jcasType.ll_cas.ll_getRefValue(addr, ((Statistics_Type) jcasType).casFeatCode_amount),
            i);
  }

  /**
   * indexed setter for amount - sets an indexed value -
   * 
   * @generated
   */
  public void setAmount(int i, int v) {
    if (Statistics_Type.featOkTst && ((Statistics_Type) jcasType).casFeat_amount == null)
      jcasType.jcas.throwFeatMissing("amount", "org.apache.uima.ruta.type.Statistics");
    jcasType.jcas.checkArrayBounds(
            jcasType.ll_cas.ll_getRefValue(addr, ((Statistics_Type) jcasType).casFeatCode_amount),
            i);
    jcasType.ll_cas.ll_setIntArrayValue(
            jcasType.ll_cas.ll_getRefValue(addr, ((Statistics_Type) jcasType).casFeatCode_amount),
            i, v);
  }

  // *--------------*
  // * Feature: part

  /**
   * getter for part - gets
   * 
   * @generated
   */
  public DoubleArray getPart() {
    if (Statistics_Type.featOkTst && ((Statistics_Type) jcasType).casFeat_part == null)
      jcasType.jcas.throwFeatMissing("part", "org.apache.uima.ruta.type.Statistics");
    return (DoubleArray) (jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr,
            ((Statistics_Type) jcasType).casFeatCode_part)));
  }

  /**
   * setter for part - sets
   * 
   * @generated
   */
  public void setPart(DoubleArray v) {
    if (Statistics_Type.featOkTst && ((Statistics_Type) jcasType).casFeat_part == null)
      jcasType.jcas.throwFeatMissing("part", "org.apache.uima.ruta.type.Statistics");
    jcasType.ll_cas.ll_setRefValue(addr, ((Statistics_Type) jcasType).casFeatCode_part,
            jcasType.ll_cas.ll_getFSRef(v));
  }

  /**
   * indexed getter for part - gets an indexed value -
   * 
   * @generated
   */
  public double getPart(int i) {
    if (Statistics_Type.featOkTst && ((Statistics_Type) jcasType).casFeat_part == null)
      jcasType.jcas.throwFeatMissing("part", "org.apache.uima.ruta.type.Statistics");
    jcasType.jcas.checkArrayBounds(
            jcasType.ll_cas.ll_getRefValue(addr, ((Statistics_Type) jcasType).casFeatCode_part), i);
    return jcasType.ll_cas.ll_getDoubleArrayValue(
            jcasType.ll_cas.ll_getRefValue(addr, ((Statistics_Type) jcasType).casFeatCode_part), i);
  }

  /**
   * indexed setter for part - sets an indexed value -
   * 
   * @generated
   */
  public void setPart(int i, double v) {
    if (Statistics_Type.featOkTst && ((Statistics_Type) jcasType).casFeat_part == null)
      jcasType.jcas.throwFeatMissing("part", "org.apache.uima.ruta.type.Statistics");
    jcasType.jcas.checkArrayBounds(
            jcasType.ll_cas.ll_getRefValue(addr, ((Statistics_Type) jcasType).casFeatCode_part), i);
    jcasType.ll_cas.ll_setDoubleArrayValue(
            jcasType.ll_cas.ll_getRefValue(addr, ((Statistics_Type) jcasType).casFeatCode_part), i,
            v);
  }
}
