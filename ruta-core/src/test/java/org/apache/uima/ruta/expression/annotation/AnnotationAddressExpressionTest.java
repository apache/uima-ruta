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

package org.apache.uima.ruta.expression.annotation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.Test;

public class AnnotationAddressExpressionTest {

  @Test
  public void testMatching() {
    String document = "Some text.";

    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
    } catch (Exception e) {
      e.printStackTrace();
    }
    Type t1 = RutaTestUtils.getTestType(cas, 1);
    AnnotationFS a1 = cas.createAnnotation(t1, 5, 9);
    cas.addFsToIndexes(a1);
    int ref = 0;
    if (a1 instanceof Annotation) {
      Annotation aImpl = (Annotation) a1;
      ref = aImpl.getAddress();
    }

    String script = "";
    script += "$" + ref + "{REGEXP(\".*ex.*\")-> T2};";
    script += "W $" + ref + "{REGEXP(\".*ex.*\")-> T3};";
    try {
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "text");

    if (cas != null) {
      cas.release();
    }

  }

  @Test
  public void testFeatureAssignment() {
    String document = "Some text.";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName = "Struct";
    typeMap.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName, list);
    String fn = "a";
    list.add(new TestFeature(fn, "", "uima.tcas.Annotation"));

    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    } catch (Exception e) {
      e.printStackTrace();
    }
    Type t1 = RutaTestUtils.getTestType(cas, 1);
    AnnotationFS a1 = cas.createAnnotation(t1, 5, 9);
    cas.addFsToIndexes(a1);
    int ref = 0;
    if (a1 instanceof Annotation) {
      Annotation aImpl = (Annotation) a1;
      ref = aImpl.getAddress();
    }

    String script = "";
    script += "CREATE(Struct, \"a\" = $" + ref + ");";
    try {
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = cas.getTypeSystem().getType(typeName);
    Feature f1 = t.getFeatureByBaseName(fn);
    ai = cas.getAnnotationIndex(t);

    assertEquals(1, ai.size());
    iterator = ai.iterator();
    AnnotationFS next = iterator.next();
    assertEquals("Some text.", next.getCoveredText());
    AnnotationFS a = (AnnotationFS) next.getFeatureValue(f1);
    assertNotNull("Feature value is null!", a);
    assertEquals("text", a.getCoveredText());

    if (cas != null) {
      cas.release();
    }

  }

}
