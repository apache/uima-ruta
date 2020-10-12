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

import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.util.CasUtil;
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

}
