/* First created by JCasGen Wed Apr 16 17:01:16 CEST 2008 */
package org.apache.uima.textmarker.type;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

/**
 * Updated by JCasGen Wed Jan 11 14:42:26 CET 2012 XML source: D:/work/workspace-
 * uima3/uimaj-ep-textmarker-engine/src/main/java/org/apache/uima
 * /textmarker/engine/BasicTypeSystem.xml
 * 
 * @generated
 */
public class TextMarkerBasic extends Annotation {

  private static final int INITIAL_CAPACITY = 2;

  private boolean lowMemoryProfile = true;

  private Set<Type> partOf = new HashSet<Type>(INITIAL_CAPACITY);

  private final Map<Type, Set<AnnotationFS>> beginMap = new HashMap<Type, Set<AnnotationFS>>(
          INITIAL_CAPACITY);

  private final Map<Type, Set<AnnotationFS>> endMap = new HashMap<Type, Set<AnnotationFS>>(
          INITIAL_CAPACITY);

  public boolean isLowMemoryProfile() {
    return lowMemoryProfile;
  }

  public void setLowMemoryProfile(boolean lowMemoryProfile) {
    this.lowMemoryProfile = lowMemoryProfile;
  }

  public void addPartOf(Type type) {
    partOf.add(type);
  }

  public void removePartOf(Type type) {
    partOf.remove(type);
  }

  public boolean isPartOf(Type type) {
    return partOf.contains(type);
  }

  public Set<AnnotationFS> getBeginAnchors(Type type) {
    Set<AnnotationFS> set = beginMap.get(type);
    if (lowMemoryProfile) {
      Set<AnnotationFS> result = new HashSet<AnnotationFS>();
      if (set != null) {
        result.addAll(set);
      }
      List<Type> subsumedTypes = getCAS().getTypeSystem().getProperlySubsumedTypes(type);
      for (Type each : subsumedTypes) {
        Set<AnnotationFS> c = beginMap.get(each);
        if (c != null) {
          result.addAll(c);
        }
      }
      return result;
    } else {
      return set;
    }
  }

  public Set<AnnotationFS> getEndAnchors(Type type) {
    Set<AnnotationFS> set = endMap.get(type);
    if (lowMemoryProfile) {
      Set<AnnotationFS> result = new HashSet<AnnotationFS>(set);
      if (set != null) {
        result.addAll(set);
      }
      List<Type> subsumedTypes = getCAS().getTypeSystem().getProperlySubsumedTypes(type);
      for (Type each : subsumedTypes) {
        Set<AnnotationFS> c = endMap.get(each);
        if (c != null) {
          result.addAll(c);
        }
      }
      return result;
    } else {
      return set;
    }
  }

  public boolean beginsWith(Type type) {
    if (beginMap.containsKey(type)) {
      return true;
    }
    if (lowMemoryProfile) {
      List<Type> subsumedTypes = getCAS().getTypeSystem().getProperlySubsumedTypes(type);
      for (Type each : subsumedTypes) {
        if (beginsWith(each)) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean endsWith(Type type) {
    if (endMap.containsKey(type)) {
      return true;
    }
    if (lowMemoryProfile) {
      List<Type> subsumedTypes = getCAS().getTypeSystem().getProperlySubsumedTypes(type);
      for (Type each : subsumedTypes) {
        if (endsWith(each)) {
          return true;
        }
      }
    }
    return false;
  }

  public void addBegin(AnnotationFS annotation, Type type) {
    Set<AnnotationFS> list = beginMap.get(type);
    if (list == null) {
      list = new HashSet<AnnotationFS>(INITIAL_CAPACITY);
      beginMap.put(type, list);
    }
    list.add(annotation);
    if (!lowMemoryProfile) {
      TypeSystem typeSystem = getCAS().getTypeSystem();
      Type parent = typeSystem.getParent(type);
      if (parent != null) {
        addBegin(annotation, parent);
      }
    }
  }

  public void addEnd(AnnotationFS annotation, Type type) {
    Set<AnnotationFS> list = endMap.get(type);
    if (list == null) {
      list = new HashSet<AnnotationFS>(INITIAL_CAPACITY);
      endMap.put(type, list);
    }
    list.add(annotation);
    if (!lowMemoryProfile) {
      TypeSystem typeSystem = getCAS().getTypeSystem();
      Type parent = typeSystem.getParent(type);
      if (parent != null) {
        addEnd(annotation, parent);
      }
    }
  }

  public void removeBegin(AnnotationFS annotation, Type type) {
    Set<AnnotationFS> list = beginMap.get(type);
    if (list != null) {
      list.remove(annotation);
      if (list.isEmpty()) {
        beginMap.remove(annotation.getType());
      }
    }
    if (!lowMemoryProfile) {
      TypeSystem typeSystem = getCAS().getTypeSystem();
      Type parent = typeSystem.getParent(type);
      if (parent != null) {
        removeBegin(annotation, parent);
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
    if (!lowMemoryProfile) {
      TypeSystem typeSystem = getCAS().getTypeSystem();
      Type parent = typeSystem.getParent(type);
      if (parent != null) {
        removeEnd(annotation, parent);
      }
    }
  }

  public Map<Type, Set<AnnotationFS>> getBeginMap() {
    return beginMap;
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
  public int getTypeIndexID() {
    return typeIndexID;
  }

  /**
   * Never called. Disable default constructor
   * 
   * @generated
   */
  protected TextMarkerBasic() {
  }

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

  /**
   * <!-- begin-user-doc --> Write your own initialization here <!-- end-user-doc -->
   * 
   * @generated modifiable
   */
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
    if (TextMarkerBasic_Type.featOkTst
            && ((TextMarkerBasic_Type) jcasType).casFeat_replacement == null)
      jcasType.jcas.throwFeatMissing("replacement",
              "org.apache.uima.textmarker.type.TextMarkerBasic");
    return jcasType.ll_cas.ll_getStringValue(addr,
            ((TextMarkerBasic_Type) jcasType).casFeatCode_replacement);
  }

  /**
   * setter for Replacement - sets
   * 
   * @generated
   */
  public void setReplacement(String v) {
    if (TextMarkerBasic_Type.featOkTst
            && ((TextMarkerBasic_Type) jcasType).casFeat_replacement == null)
      jcasType.jcas.throwFeatMissing("replacement",
              "org.apache.uima.textmarker.type.TextMarkerBasic");
    jcasType.ll_cas.ll_setStringValue(addr,
            ((TextMarkerBasic_Type) jcasType).casFeatCode_replacement, v);
  }

  public Map<Type, Set<AnnotationFS>> getEndMap() {
    return endMap;
  }

}
