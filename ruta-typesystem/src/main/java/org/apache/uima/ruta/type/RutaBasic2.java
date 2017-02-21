/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.uima.ruta.type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.impl.TypeSystemImpl;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap.Entry;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

/**
 * 
 * @generated
 */
public class RutaBasic2 extends Annotation {

  private static final int INITIAL_CAPACITY = 2;

  private static final String ROOT_TYPE1 = CAS.TYPE_NAME_ANNOTATION;

  private static final String ROOT_TYPE2 = CAS.TYPE_NAME_ANNOTATION_BASE;

  private boolean lowMemoryProfile = false;

  private int[] partOf = new int[((TypeSystemImpl) getCAS().getTypeSystem()).getLargestTypeCode()];

  private Int2ObjectOpenHashMap<Collection<?>> beginMap = new Int2ObjectOpenHashMap<>();
  private Int2ObjectOpenHashMap<Collection<?>> endMap = new Int2ObjectOpenHashMap<>();
  
  private boolean empty = true;

  public boolean isEmpty() {
    return empty;
  }

  public boolean isLowMemoryProfile() {
    return lowMemoryProfile;
  }

  public void setLowMemoryProfile(boolean lowMemoryProfile) {
    this.lowMemoryProfile = lowMemoryProfile;
  }

  public void addPartOf(Type type) {
    int code = ((TypeImpl) type).getCode();
    addPartOf(code);
  }

  private void addPartOf(int code) {
    partOf[code] = partOf[code] + 1;
    if (!lowMemoryProfile) {
      int parentCode = getCAS().getTypeSystem().getLowLevelTypeSystem().ll_getParentType(code);
      if (parentCode > 0) {
        addPartOf(parentCode);
      }
    }
  }

  public void removePartOf(Type type) {
    int code = ((TypeImpl) type).getCode();
    removePartOf(code);
  }

  private void removePartOf(int code) {
    if (partOf[code] != 0) {
      partOf[code] = partOf[code] - 1;
      if (!lowMemoryProfile) {
        int parentCode = getCAS().getTypeSystem().getLowLevelTypeSystem().ll_getParentType(code);
        if (parentCode > 0) {
          removePartOf(parentCode);
        }
      }
    }
  }

  public boolean isPartOf(Type type) {
    int code = ((TypeImpl) type).getCode();
    int count = partOf[code];
    if (count > 0) {
      return true;
    }
    if (lowMemoryProfile) {
      List<Type> subsumedTypes = getCAS().getTypeSystem().getProperlySubsumedTypes(type);
      for (Type each : subsumedTypes) {
        int code2 = ((TypeImpl) each).getCode();
        int count2 = partOf[code2];
        if (count2 > 0) {
          return true;
        }
      }

    }
    return false;

  }

  public void setPartOf(int[] partOf) {
    this.partOf = partOf;
  }
  
  public int[] getPartOf() {
    return partOf;
  }
  
  @SuppressWarnings("unchecked")
  public Collection<AnnotationFS> getBeginAnchors(Type type) {
    int code = ((TypeImpl) type).getCode();
    Collection<AnnotationFS> set = (Collection<AnnotationFS>) beginMap.get(code);
    if (lowMemoryProfile) {
      Collection<AnnotationFS> result = new ArrayList<AnnotationFS>();
      if (set != null) {
        result.addAll(set);
      }
      List<Type> subsumedTypes = getCAS().getTypeSystem().getProperlySubsumedTypes(type);
      for (Type each : subsumedTypes) {
        int parentCode = ((TypeImpl) each).getCode();
        Collection<?> c =  beginMap.get(parentCode);
        if (c != null) {
          result.addAll((Collection<? extends AnnotationFS>) c);
        }
      }
      return result;
    } else {
      if (set == null) {
        return Collections.emptySet();
      } else {
        return set;
      }
    }
  }

  @SuppressWarnings("unchecked")
  public Collection<AnnotationFS> getEndAnchors(Type type) {
    int code = ((TypeImpl) type).getCode();
    Collection<AnnotationFS> set = (Collection<AnnotationFS>) endMap.get(code);
    if (lowMemoryProfile) {
      Collection<AnnotationFS> result = new ArrayList<AnnotationFS>();
      if (set != null) {
        result.addAll(set);
      }
      List<Type> subsumedTypes = getCAS().getTypeSystem().getProperlySubsumedTypes(type);
      for (Type each : subsumedTypes) {
        int parentCode = ((TypeImpl) each).getCode();
        Collection<?> c = endMap.get(parentCode);
        if (c != null) {
          result.addAll((Collection<? extends AnnotationFS>) c);
        }
      }
      return result;
    } else {
      if (set == null) {
        return Collections.emptySet();
      } else {
        return set;
      }
    }
  }

  public boolean beginsWith(Type type) {
    int code = ((TypeImpl) type).getCode();
    Collection<?> set = beginMap.get(code);
    boolean beginsWith = set != null && !set.isEmpty();
    if (beginsWith) {
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
    int code = ((TypeImpl) type).getCode();
    Collection<?> set = endMap.get(code);
    boolean endswith = set != null && !set.isEmpty();
    if (endswith) {
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

  @SuppressWarnings("unchecked")
  public void addBegin(AnnotationFS annotation, Type type) {
    empty = false;
    int code = ((TypeImpl) type).getCode();
    Collection<Object> set = (Collection<Object>) beginMap.get(code);
    if (set == null) {
      set = new ArrayList<Object>(INITIAL_CAPACITY);
      beginMap.put(code, set);
    }
    set.add(annotation);
    if (!lowMemoryProfile && !type.getName().equals(ROOT_TYPE1)
            && !type.getName().equals(ROOT_TYPE2)) {
      TypeSystem typeSystem = getCAS().getTypeSystem();
      Type parent = typeSystem.getParent(type);
      if (parent != null) {
        addBegin(annotation, parent);
      }
    }
  }

  @SuppressWarnings("unchecked")
  public void addEnd(AnnotationFS annotation, Type type) {
    empty = false;
    int code = ((TypeImpl) type).getCode();
    Collection<Object> set = (Collection<Object>) endMap.get(code);
    if (set == null) {
      set = new ArrayList<Object>(INITIAL_CAPACITY);
      endMap.put(code, set);
    }
    set.add(annotation);
    if (!lowMemoryProfile && !type.getName().equals(ROOT_TYPE1)
            && !type.getName().equals(ROOT_TYPE2)) {
      TypeSystem typeSystem = getCAS().getTypeSystem();
      Type parent = typeSystem.getParent(type);
      if (parent != null) {
        addEnd(annotation, parent);
      }
    }
  }

  @SuppressWarnings("unchecked")
  public void removeBegin(AnnotationFS annotation, Type type) {
    int code = ((TypeImpl) type).getCode();
    Collection<Object> set = (Collection<Object>) beginMap.get(code);
    if (set != null) {
      set.remove(annotation);
      if (set.isEmpty()) {
        beginMap.remove(code);
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

  @SuppressWarnings("unchecked")
  public void removeEnd(AnnotationFS annotation, Type type) {
    int code = ((TypeImpl) type).getCode();
    Collection<Object> set = (Collection<Object>) endMap.get(code);
    if (set != null) {
      set.remove(annotation);
      if (set.isEmpty()) {
        endMap.remove(code);
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

  public Int2ObjectOpenHashMap<Collection<?>> getBeginMap() {
    return beginMap;
  }

  public Int2ObjectOpenHashMap<Collection<?>> getEndMap() {
    return endMap;
  }

  public void setBeginMap(Int2ObjectOpenHashMap<Collection<?>> beginMap) {
    this.beginMap = beginMap;
    for (Entry<Collection<?>> entry : beginMap.int2ObjectEntrySet()) {
      Collection<?> value = entry.getValue();
      if (value != null && !value.isEmpty()) {
        this.empty = false;
        break;
      }
    }
  }

  public void setEndMap(Int2ObjectOpenHashMap<Collection<?>> endMap) {
    this.endMap = endMap;
    for (Entry<Collection<?>> entry : endMap.int2ObjectEntrySet()) {
      Collection<?> value = entry.getValue();
      if (value != null && !value.isEmpty()) {
        this.empty = false;
        break;
      }
    }
  }

  public void clearBeginMap() {
    this.beginMap.clear();;
  }

  public void clearEndMap() {
    this.endMap.clear();
    for (Entry<Collection<?>> entry : beginMap.int2ObjectEntrySet()) {
      Collection<?> value = entry.getValue();
      if (value != null && !value.isEmpty()) {
        return;
      }
    }
    this.empty = true;
  }

  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(RutaBasic2.class);

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
  protected RutaBasic2() {/* intentionally empty block */
  }

  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public RutaBasic2(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }

  /** @generated */
  public RutaBasic2(JCas jcas) {
    super(jcas);
    readObject();
  }

  /** @generated */
  public RutaBasic2(JCas jcas, int begin, int end) {
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
    if (RutaBasic_Type.featOkTst && ((RutaBasic_Type) jcasType).casFeat_replacement == null)
      jcasType.jcas.throwFeatMissing("replacement", "org.apache.uima.ruta.type.RutaBasic");
    return jcasType.ll_cas.ll_getStringValue(addr,
            ((RutaBasic_Type) jcasType).casFeatCode_replacement);
  }

  /**
   * setter for Replacement - sets
   * 
   * @generated
   */
  public void setReplacement(String v) {
    if (RutaBasic_Type.featOkTst && ((RutaBasic_Type) jcasType).casFeat_replacement == null)
      jcasType.jcas.throwFeatMissing("replacement", "org.apache.uima.ruta.type.RutaBasic");
    jcasType.ll_cas.ll_setStringValue(addr, ((RutaBasic_Type) jcasType).casFeatCode_replacement, v);
  }

}
