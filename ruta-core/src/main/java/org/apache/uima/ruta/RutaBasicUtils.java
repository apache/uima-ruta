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
package org.apache.uima.ruta;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.ruta.type.RutaBasic;

/**
 * Utility methods for modifying and updating RutaBasics outside of a Ruta script, without a
 * RutaStream.
 *
 */
public class RutaBasicUtils {

  private RutaBasicUtils() {
    // nothing here
  }

  /**
   * Adding a given annotation to the internal indexing in the covered RutaBasics. This extends the
   * information for begin, end and part of.
   * 
   * @param annotation
   *          the annotation that should be added to the internal RutaBasic indexing
   * 
   * @return true if RutaBasics have been updated. Returns false, if the given annotation is a
   *         RutaBasic, if there are no RutaBasics, or if there are no RutaBAsics covered by the
   *         given annotation.
   */
  public static boolean addAnnotation(AnnotationFS annotation) {
    CAS cas = annotation.getCAS();
    TypeSystem typeSystem = cas.getTypeSystem();
    Type basicType = typeSystem.getType(RutaBasic.class.getName());

    if (basicType == null) {
      return false;
    }

    Type type = annotation.getType();

    if (typeSystem.subsumes(basicType, type)) {
      return false;
    }

    AnnotationIndex<AnnotationFS> basicIndex = cas.getAnnotationIndex(basicType);
    if (basicIndex.size() == 0) {
      return false;
    }

    List<AnnotationFS> coveredBasics = CasUtil.selectCovered(basicType, annotation);
    if (coveredBasics.size() == 0) {
      return false;
    }

    RutaBasic firstBasic = (RutaBasic) coveredBasics.get(0);
    RutaBasic lastBasic = (RutaBasic) coveredBasics.get(coveredBasics.size() - 1);

    firstBasic.addBegin(annotation, type);
    lastBasic.addEnd(annotation, type);

    for (AnnotationFS each : coveredBasics) {
      RutaBasic rutaBasic = (RutaBasic) each;
      rutaBasic.addPartOf(type);
    }

    return true;
  }

  /**
   * Removing a given annotation to the internal indexing in the covered RutaBasics. This reduces
   * the information for begin, end and part of.
   * 
   * @param annotation
   *          the annotation that should be added to the internal RutaBasic indexing
   *
   * @return true if RutaBasics have been updated. Returns false, if the given annotation is a
   *         RutaBasic, if there are no RutaBasics, or if there are no RutaBAsics covered by the
   *         given annotation.
   */
  public static boolean removeAnnotation(AnnotationFS annotation) {
    CAS cas = annotation.getCAS();
    TypeSystem typeSystem = cas.getTypeSystem();
    Type basicType = typeSystem.getType(RutaBasic.class.getName());

    if (basicType == null) {
      return false;
    }

    Type type = annotation.getType();

    if (typeSystem.subsumes(basicType, type)) {
      return false;
    }

    AnnotationIndex<AnnotationFS> basicIndex = cas.getAnnotationIndex(basicType);
    if (basicIndex.size() == 0) {
      return false;
    }

    List<AnnotationFS> coveredBasics = CasUtil.selectCovered(basicType, annotation);
    if (coveredBasics.size() == 0) {
      return false;
    }

    RutaBasic firstBasic = (RutaBasic) coveredBasics.get(0);
    RutaBasic lastBasic = (RutaBasic) coveredBasics.get(coveredBasics.size() - 1);

    firstBasic.removeBegin(annotation, type);
    lastBasic.removeEnd(annotation, type);

    for (AnnotationFS each : coveredBasics) {
      RutaBasic rutaBasic = (RutaBasic) each;
      rutaBasic.removePartOf(type);
    }

    return true;
  }

  /**
   * This method validated the internal indexing, i.e. the information stored in the RutaBasics, and
   * throw exceptions if a invalid state is discovered.
   * 
   * @param jcas
   *          the JCas that should be validated
   * @param ignoreTypeNames
   *          the names of types that should not be validated
   * @throws AnalysisEngineProcessException
   *           if some problem was detected
   */
  public static void validateInternalIndexing(JCas jcas, Collection<String> ignoreTypeNames)
          throws AnalysisEngineProcessException {

    Map<Integer, RutaBasic> beginMap = new LinkedHashMap<>();
    Map<Integer, RutaBasic> endMap = new LinkedHashMap<>();

    Collection<RutaBasic> basics = JCasUtil.select(jcas, RutaBasic.class);

    if (basics.isEmpty()) {
      throw new AnalysisEngineProcessException(
              new IllegalStateException("No RutaBasics available!"));
    }
    for (RutaBasic rutaBasic : basics) {

      int begin = rutaBasic.getBegin();
      int end = rutaBasic.getEnd();

      if (beginMap.get(begin) != null || endMap.get(end) != null) {
        throw new AnalysisEngineProcessException(new IllegalStateException(
                "RutaBasic must be disjunct! Problem at offset " + begin));
      }

      beginMap.put(begin, rutaBasic);
      endMap.put(end, rutaBasic);
    }

    for (Annotation annotation : JCasUtil.select(jcas, Annotation.class)) {

      Type type = annotation.getType();
      if (ignoreType(type, ignoreTypeNames, jcas)) {
        continue;
      }

      int begin = annotation.getBegin();
      int end = annotation.getEnd();

      RutaBasic beginBasic = beginMap.get(begin);
      RutaBasic endBasic = endMap.get(end);
      if (beginBasic == null) {
        throw new AnalysisEngineProcessException(new IllegalStateException(
                "No RutaBasic for begin of annotation at offset " + begin));
      }
      if (endBasic == null) {
        throw new AnalysisEngineProcessException(
                new IllegalStateException("No RutaBasic for end of annotation at offset " + end));
      }

      Collection<AnnotationFS> beginAnchors = beginBasic.getBeginAnchors(type);
      if (beginAnchors == null || !beginAnchors.contains(annotation)) {
        throw new AnalysisEngineProcessException(new IllegalStateException("Annotation of type '"
                + type.getName() + "' not registered as begin at offset " + begin));
      }
      Collection<AnnotationFS> endAnchors = endBasic.getEndAnchors(type);
      if (endAnchors == null || !endAnchors.contains(annotation)) {
        throw new AnalysisEngineProcessException(new IllegalStateException("Annotation of type '"
                + type.getName() + "' not registered as end at offset " + begin));
      }

      List<RutaBasic> coveredBasics = JCasUtil.selectCovered(RutaBasic.class, annotation);
      for (RutaBasic coveredBasic : coveredBasics) {
        if (!coveredBasic.isPartOf(type)) {
          throw new AnalysisEngineProcessException(
                  new IllegalStateException("Annotation of type '" + type.getName()
                          + "' not registered as partof at offset [" + begin + "," + end + "]"));
        }
      }
    }
  }

  private static boolean ignoreType(Type type, Collection<String> ignoreTypeNames, JCas jcas) {

    if (type == null) {
      return false;
    }

    if (StringUtils.equals(type.getName(), RutaBasic.class.getName())) {
      return true;
    }

    if (ignoreTypeNames == null) {
      return false;
    }

    TypeSystem typeSystem = jcas.getTypeSystem();

    for (String typeName : ignoreTypeNames) {
      Type ignoreType = typeSystem.getType(typeName);
      if (ignoreType == null) {
        continue;
      }
      if (typeSystem.subsumes(ignoreType, type)) {
        return true;
      }
    }
    return false;
  }

}
