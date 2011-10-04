/* First created by JCasGen Wed Apr 16 17:01:16 CEST 2008 */
package org.apache.uima.textmarker.type;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

/** 
 * Updated by JCasGen Mon Sep 26 19:07:28 CEST 2011
 * XML source: D:/work/workspace-uima3/uimaj-ep-textmarker-engine/desc/InternalTypeSystem.xml
 * @generated */
public class TextMarkerBasic extends Annotation {

  private Set<Type> partOf = new HashSet<Type>(20);

  private final Map<Type, Set<AnnotationFS>> beginMap = new HashMap<Type, Set<AnnotationFS>>(10);

  private final Map<Type, Set<AnnotationFS>> endMap = new HashMap<Type, Set<AnnotationFS>>(10);

  // private Map<String, AnnotationFS> typeMap = new HashMap<String, AnnotationFS>(7);

  // private Map<String, AnnotationFS> instanceMap = new HashMap<String, AnnotationFS>(10);

  public Map<String, String> tags = new HashMap<String, String>(20);

  public void setTags(Map<String, String> tags) {
    this.tags = new HashMap<String, String>(tags);
  }

  public Map<String, String> getTags() {
    return tags;
  }

  // public void setAnnotation(String name, AnnotationFS annotation, boolean instance) {
  // if (instance) {
  // typeMap.put(name, annotation);
  // instanceMap.put(name, annotation);
  // } else if (!instanceMap.containsKey(name)) {
  // typeMap.put(name, annotation);
  // }
  // }
  //
  // public void removeAnnotation(String name, boolean instance) {
  // if (instance && instanceMap.containsKey(name)) {
  // typeMap.remove(name);
  // instanceMap.remove(name);
  // } else if (!instance && !instanceMap.containsKey(name)) {
  // typeMap.remove(name);
  // }
  // }

  public void addPartOf(Type type) {
    partOf.add(type);
  }

  public void removePartOf(Type type) {
    partOf.remove(type);
  }

  // public boolean isAnchorOf(String name) {
  // return typeMap.containsKey(name);
  // }

  // public AnnotationFS getType(String name) {
  // return typeMap.get(name);
  // }

  public boolean isPartOf(Type type) {
    return partOf.contains(type);
  }

  // public Collection<AnnotationFS> getAnchors() {
  // return typeMap.values();
  // }

  public Set<AnnotationFS> getBeginAnchors(Type type) {
    return beginMap.get(type);
  }

  public Set<AnnotationFS> getEndAnchors(Type type) {
    return endMap.get(type);
  }

  public boolean beginsWith(Type type) {
    return beginMap.containsKey(type);
  }

  public boolean endsWith(Type type) {
    return endMap.containsKey(type);
  }

  public void addBegin(AnnotationFS annotation, Type type) {
    Set<AnnotationFS> list = beginMap.get(type);
    if (list == null) {
      list = new HashSet<AnnotationFS>();
      beginMap.put(type, list);
    }
    list.add(annotation);
  }

  public void addEnd(AnnotationFS annotation, Type type) {
    Set<AnnotationFS> list = endMap.get(type);
    if (list == null) {
      list = new HashSet<AnnotationFS>();
      endMap.put(type, list);
    }
    list.add(annotation);
  }

  public void removeBegin(AnnotationFS annotation, Type type) {
    Set<AnnotationFS> list = beginMap.get(type);
    if (list != null) {
      list.remove(annotation);
      if (list.isEmpty()) {
        beginMap.remove(annotation.getType());
      }
    }
  }

  public void removeEnd(AnnotationFS annotation, Type type) {
    Set<AnnotationFS> list = endMap.get(type);
    if (list != null) {
      list.remove(annotation);
      if (list.isEmpty()) {
        endMap.remove(annotation.getType());
      }
    }
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
      jcasType.jcas.throwFeatMissing("replacement", "org.apache.uima.textmarker.type.TextMarkerBasic");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TextMarkerBasic_Type)jcasType).casFeatCode_replacement);}
    
  /**
   * setter for Replacement - sets
   * 
   * @generated
   */
  public void setReplacement(String v) {
    if (TextMarkerBasic_Type.featOkTst && ((TextMarkerBasic_Type)jcasType).casFeat_replacement == null)
      jcasType.jcas.throwFeatMissing("replacement", "org.apache.uima.textmarker.type.TextMarkerBasic");
    jcasType.ll_cas.ll_setStringValue(addr, ((TextMarkerBasic_Type)jcasType).casFeatCode_replacement, v);}    
      public Map<Type, Set<AnnotationFS>> getEndMap() {
    return endMap;
  }

  public Map<Type, Set<AnnotationFS>> getBeginMap() {
    return beginMap;
  }

}
