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

package org.apache.uima.ruta.action;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.Test;

public class TrieTest {

  @Test
  public void test() {
    String name = this.getClass().getSimpleName();
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");

    for (String scriptname : new String[] { name, name + "_compressed" }) {

      CAS cas = null;
      try {
        cas = RutaTestUtils.process(
                namespace + "/" + scriptname + RutaEngine.SCRIPT_FILE_EXTENSION, namespace + "/"
                        + name + ".txt", 50, false, false, null, namespace + "/");
      } catch (Exception e) {
        e.printStackTrace();
        assert (false);
      }
      
      RutaTestUtils.assertAnnotationsEquals(cas, 1, 3, "Peter", "Marshall", "Joern");
      RutaTestUtils.assertAnnotationsEquals(cas, 2, 3, "Kluegl", "Schor", "Kottmann");
      RutaTestUtils.assertAnnotationsEquals(cas, 3, 3, "Peter Kluegl", "Marshall Schor", "Joern Kottmann");
      RutaTestUtils.assertAnnotationsEquals(cas, 4, 3, "Peter Kluegl: Ruta", "Marshall Schor: UIMA",
              "Joern Kottmann: CAS Editor");

      cas.release();
    }
  }

  @Test
  public void testWithFeature() {
    String name = this.getClass().getSimpleName() + "WithFeature";
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");
    CAS cas = null;
    Map<String, String> complexTypes = new HashMap<String, String>();
    Map<String, List<TestFeature>> features = new TreeMap<String, List<TestFeature>>();
    String typeNameA = "org.apache.uima.ruta.A";
    String typeNameB = "org.apache.uima.ruta.B";
    String typeNameC = "org.apache.uima.ruta.C";
    complexTypes.put(typeNameA, "uima.tcas.Annotation");
    complexTypes.put(typeNameB, "uima.tcas.Annotation");
    complexTypes.put(typeNameC, "uima.tcas.Annotation");
    List<TestFeature> listA = new ArrayList<RutaTestUtils.TestFeature>();
    features.put(typeNameA, listA);
    String fnab = "a";
    listA.add(new TestFeature(fnab, "", "uima.cas.String"));
    List<TestFeature> listB = new ArrayList<RutaTestUtils.TestFeature>();
    features.put(typeNameB, listB);
    String fnbc = "b";
    listB.add(new TestFeature(fnbc, "", "uima.cas.Boolean"));
    List<TestFeature> listC = new ArrayList<RutaTestUtils.TestFeature>();
    features.put(typeNameC, listC);
    String fnci = "c";
    listC.add(new TestFeature(fnci, "", "uima.cas.Integer"));

    try {
      cas = RutaTestUtils.process(namespace + "/" + name + RutaEngine.SCRIPT_FILE_EXTENSION,
              namespace + "/" + this.getClass().getSimpleName() + ".txt", 50, false, false,
              complexTypes, features, namespace + "/");
    } catch (Exception e) {
      e.printStackTrace();
      assert (false);
    }
    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;
    AnnotationFS next = null;
    Feature feature = null;

    t = cas.getTypeSystem().getType(typeNameA);
    feature = t.getFeatureByBaseName("a");
    ai = cas.getAnnotationIndex(t);
    assertEquals(3, ai.size());
    iterator = ai.iterator();
    next = iterator.next();
    assertEquals("Peter", next.getCoveredText());
    assertEquals("first", next.getStringValue(feature));
    next = iterator.next();
    assertEquals("Marshall", next.getCoveredText());
    assertEquals("first", next.getStringValue(feature));
    next = iterator.next();
    assertEquals("Joern", next.getCoveredText());
    assertEquals("first", next.getStringValue(feature));

    t = cas.getTypeSystem().getType(typeNameB);
    feature = t.getFeatureByBaseName("b");
    ai = cas.getAnnotationIndex(t);
    assertEquals(3, ai.size());
    iterator = ai.iterator();
    next = iterator.next();
    assertEquals("Kluegl", next.getCoveredText());
    assertEquals(true, next.getBooleanValue(feature));
    next = iterator.next();
    assertEquals("Schor", next.getCoveredText());
    assertEquals(true, next.getBooleanValue(feature));
    next = iterator.next();
    assertEquals("Kottmann", next.getCoveredText());
    assertEquals(true, next.getBooleanValue(feature));

    t = cas.getTypeSystem().getType(typeNameC);
    feature = t.getFeatureByBaseName("c");
    ai = cas.getAnnotationIndex(t);
    assertEquals(3, ai.size());
    iterator = ai.iterator();
    next = iterator.next();
    assertEquals("Peter Kluegl", next.getCoveredText());
    assertEquals(6, next.getIntValue(feature));
    next = iterator.next();
    assertEquals("Marshall Schor", next.getCoveredText());
    assertEquals(6, next.getIntValue(feature));
    next = iterator.next();
    assertEquals("Joern Kottmann", next.getCoveredText());
    assertEquals(6, next.getIntValue(feature));

    cas.release();
  }
}
