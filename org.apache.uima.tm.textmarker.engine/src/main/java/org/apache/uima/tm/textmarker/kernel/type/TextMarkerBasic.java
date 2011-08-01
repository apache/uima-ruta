/* First created by JCasGen Wed Apr 16 17:01:16 CEST 2008 */
package org.apache.uima.tm.textmarker.kernel.type;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

/** 
 * Updated by JCasGen Tue Jan 25 09:29:36 CET 2011
 * XML source: D:/work/workspace-tm/org.apache.uima.tm.textmarker.engine/desc/InternalTypeSystem.xml
 * @generated */
public class TextMarkerBasic extends Annotation {

  private Set<String> partOf = new HashSet<String>(20);

  private Map<String, AnnotationFS> typeMap = new HashMap<String, AnnotationFS>(7);

  private Map<String, AnnotationFS> instanceMap = new HashMap<String, AnnotationFS>(10);

  public Map<String, String> tags = new HashMap<String, String>(20);

  public void setTags(Map<String, String> tags) {
    this.tags = new HashMap<String, String>(tags);
  }

  public Map<String, String> getTags() {
    return tags;
  }

  public void setAnnotation(String name, AnnotationFS annotation, boolean instance) {
    if (instance) {
      typeMap.put(name, annotation);
      instanceMap.put(name, annotation);
    } else if (!instanceMap.containsKey(name)) {
      typeMap.put(name, annotation);
    }
  }

  public void removeAnnotation(String name, boolean instance) {
    if (instance && instanceMap.containsKey(name)) {
      typeMap.remove(name);
      instanceMap.remove(name);
    } else if (!instance && !instanceMap.containsKey(name)) {
      typeMap.remove(name);
    }
  }

  public void addPartOf(String type) {
    partOf.add(type);
  }

  public void removePartOf(String type) {
    partOf.remove(type);
  }

  public boolean isAnchorOf(String name) {
    return typeMap.containsKey(name);
  }

  public AnnotationFS getType(String name) {
    return typeMap.get(name);
  }

  public boolean isPartOf(String name) {
    return partOf.contains(name);
  }

  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(TextMarkerBasic.class);

  /**
   * @generated
   * @ordered
   */
  public final static int type = typeIndexID;

  /** @generated */
  @Override
  public int getTypeIndexID() {return typeIndexID;}
 
  /**
   * Never called. Disable default constructor
   * 
   * @generated
   */
  protected TextMarkerBasic() {}
    
  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public TextMarkerBasic(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public TextMarkerBasic(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */
  public TextMarkerBasic(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc --> Write your own initialization here <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {
  }

  // *--------------*
  // * Feature: Replacement

  /**
   * getter for Replacement - gets
   * 
   * @generated
   */
  public String getReplacement() {
    if (TextMarkerBasic_Type.featOkTst && ((TextMarkerBasic_Type)jcasType).casFeat_replacement == null)
      jcasType.jcas.throwFeatMissing("replacement", "org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TextMarkerBasic_Type)jcasType).casFeatCode_replacement);}
    
  /**
   * setter for Replacement - sets
   * 
   * @generated
   */
  public void setReplacement(String v) {
    if (TextMarkerBasic_Type.featOkTst && ((TextMarkerBasic_Type)jcasType).casFeat_replacement == null)
      jcasType.jcas.throwFeatMissing("replacement", "org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic");
    jcasType.ll_cas.ll_setStringValue(addr, ((TextMarkerBasic_Type)jcasType).casFeatCode_replacement, v);}    
    public Collection<AnnotationFS> getAnchors() {
    return typeMap.values();
  }
}
