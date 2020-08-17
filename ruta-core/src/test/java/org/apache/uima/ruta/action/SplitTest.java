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
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.Test;

public class SplitTest {

  @Test
  public void testDefault() throws Exception {
    String document = "Some text. More text , with 1 , and more. even more text.";
    String script = "PERIOD #{-> T1} PERIOD;";
    script += " #{-> T1} PERIOD;";
    script += "T1{CONTAINS(NUM)-> CREATE(Complex, \"number\"= NUM)};";
    script += "Complex{-> SPLIT(COMMA)};";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName = "Complex";
    typeMap.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName, list);
    String fn = "number";
    list.add(new TestFeature(fn, "", "uima.tcas.Annotation"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = cas.getTypeSystem().getType(typeName);
    Feature f1 = t.getFeatureByBaseName(fn);
    ai = cas.getAnnotationIndex(t);

    assertEquals(3, ai.size());
    iterator = ai.iterator();
    AnnotationFS next = iterator.next();
    assertEquals("More text", next.getCoveredText());
    FeatureStructure featureValue = next.getFeatureValue(f1);
    assertNotNull(featureValue);
    assertEquals("1", ((AnnotationFS) featureValue).getCoveredText());

    next = iterator.next();
    assertEquals("with 1", next.getCoveredText());
    featureValue = next.getFeatureValue(f1);
    assertNotNull(featureValue);
    assertEquals("1", ((AnnotationFS) featureValue).getCoveredText());

    next = iterator.next();
    assertEquals("and more", next.getCoveredText());
    featureValue = next.getFeatureValue(f1);
    assertNotNull(featureValue);
    assertEquals("1", ((AnnotationFS) featureValue).getCoveredText());

  }

  @Test
  public void testAddBegin() throws Exception {
    String document = "Some text. More text , with 1 , and more. even more text.";
    String script = "PERIOD #{-> T1} PERIOD;";
    script += " #{-> T1} PERIOD;";
    script += "T1{CONTAINS(NUM)-> CREATE(Complex, \"number\"= NUM)};";
    script += "Complex{-> SPLIT(COMMA, true, true, false)};";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName = "Complex";
    typeMap.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName, list);
    String fn = "number";
    list.add(new TestFeature(fn, "", "uima.tcas.Annotation"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = cas.getTypeSystem().getType(typeName);
    Feature f1 = t.getFeatureByBaseName(fn);
    ai = cas.getAnnotationIndex(t);

    assertEquals(3, ai.size());
    iterator = ai.iterator();
    AnnotationFS next = iterator.next();
    assertEquals("More text", next.getCoveredText());
    FeatureStructure featureValue = next.getFeatureValue(f1);
    assertNotNull(featureValue);
    assertEquals("1", ((AnnotationFS) featureValue).getCoveredText());

    next = iterator.next();
    assertEquals(", with 1", next.getCoveredText());
    featureValue = next.getFeatureValue(f1);
    assertNotNull(featureValue);
    assertEquals("1", ((AnnotationFS) featureValue).getCoveredText());

    next = iterator.next();
    assertEquals(", and more", next.getCoveredText());
    featureValue = next.getFeatureValue(f1);
    assertNotNull(featureValue);
    assertEquals("1", ((AnnotationFS) featureValue).getCoveredText());

  }

  @Test
  public void testAddEnd() throws Exception {
    String document = "Some text. More text , with 1 , and more. even more text.";
    String script = "PERIOD #{-> T1} PERIOD;";
    script += " #{-> T1} PERIOD;";
    script += "T1{CONTAINS(NUM)-> CREATE(Complex, \"number\"= NUM)};";
    script += "Complex{-> SPLIT(COMMA, true, false, true)};";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName = "Complex";
    typeMap.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName, list);
    String fn = "number";
    list.add(new TestFeature(fn, "", "uima.tcas.Annotation"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = cas.getTypeSystem().getType(typeName);
    Feature f1 = t.getFeatureByBaseName(fn);
    ai = cas.getAnnotationIndex(t);

    assertEquals(3, ai.size());
    iterator = ai.iterator();
    AnnotationFS next = iterator.next();
    assertEquals("More text ,", next.getCoveredText());
    FeatureStructure featureValue = next.getFeatureValue(f1);
    assertNotNull(featureValue);
    assertEquals("1", ((AnnotationFS) featureValue).getCoveredText());

    next = iterator.next();
    assertEquals("with 1 ,", next.getCoveredText());
    featureValue = next.getFeatureValue(f1);
    assertNotNull(featureValue);
    assertEquals("1", ((AnnotationFS) featureValue).getCoveredText());

    next = iterator.next();
    assertEquals("and more", next.getCoveredText());
    featureValue = next.getFeatureValue(f1);
    assertNotNull(featureValue);
    assertEquals("1", ((AnnotationFS) featureValue).getCoveredText());

  }

  @Test
  public void testAddBoth() throws Exception {
    String document = "Some text. More text , with 1 , and more. even more text.";
    String script = "PERIOD #{-> T1} PERIOD;";
    script += " #{-> T1} PERIOD;";
    script += "T1{CONTAINS(NUM)-> CREATE(Complex, \"number\"= NUM)};";
    script += "Complex{-> SPLIT(COMMA, true, true, true)};";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName = "Complex";
    typeMap.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName, list);
    String fn = "number";
    list.add(new TestFeature(fn, "", "uima.tcas.Annotation"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = cas.getTypeSystem().getType(typeName);
    Feature f1 = t.getFeatureByBaseName(fn);
    ai = cas.getAnnotationIndex(t);

    assertEquals(3, ai.size());
    iterator = ai.iterator();
    AnnotationFS next = iterator.next();
    assertEquals("More text ,", next.getCoveredText());
    FeatureStructure featureValue = next.getFeatureValue(f1);
    assertNotNull(featureValue);
    assertEquals("1", ((AnnotationFS) featureValue).getCoveredText());

    next = iterator.next();
    assertEquals(", with 1 ,", next.getCoveredText());
    featureValue = next.getFeatureValue(f1);
    assertNotNull(featureValue);
    assertEquals("1", ((AnnotationFS) featureValue).getCoveredText());

    next = iterator.next();
    assertEquals(", and more", next.getCoveredText());
    featureValue = next.getFeatureValue(f1);
    assertNotNull(featureValue);
    assertEquals("1", ((AnnotationFS) featureValue).getCoveredText());

  }

  @Test
  public void testBoundarySplit() throws Exception {
    String document = "Some text. More text , with 1 , and more. even more text.";
    String script = "PERIOD #{-> T1} PERIOD;";
    script += "#{-> T1} PERIOD;";
    script += "(# COMMA){-> T2};";
    script += "NUM (COMMA #){-> T2};";
    script += "T1{CONTAINS(NUM)-> CREATE(Complex, \"number\"= NUM)};";
    script += "Complex{-> SPLIT(T2, false)};";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName = "Complex";
    typeMap.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName, list);
    String fn = "number";
    list.add(new TestFeature(fn, "", "uima.tcas.Annotation"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = cas.getTypeSystem().getType(typeName);
    Feature f1 = t.getFeatureByBaseName(fn);
    ai = cas.getAnnotationIndex(t);

    assertEquals(3, ai.size());
    iterator = ai.iterator();
    AnnotationFS next = iterator.next();
    assertEquals("More text ,", next.getCoveredText());
    FeatureStructure featureValue = next.getFeatureValue(f1);
    assertNotNull(featureValue);
    assertEquals("1", ((AnnotationFS) featureValue).getCoveredText());

    next = iterator.next();
    assertEquals("with 1", next.getCoveredText());
    featureValue = next.getFeatureValue(f1);
    assertNotNull(featureValue);
    assertEquals("1", ((AnnotationFS) featureValue).getCoveredText());

    next = iterator.next();
    assertEquals(", and more", next.getCoveredText());
    featureValue = next.getFeatureValue(f1);
    assertNotNull(featureValue);
    assertEquals("1", ((AnnotationFS) featureValue).getCoveredText());

  }

  @Test
  public void testBoundary() throws Exception {
    String document = "Some text. More text , with 1 , and more. even more text.";
    String script = "PERIOD (# PERIOD){-> T1};";
    script += "(# PERIOD){-> T1};";
    script += "T1{-> SPLIT(PERIOD, true, false, true)};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = RutaTestUtils.getTestType(cas, 1);
    ai = cas.getAnnotationIndex(t);

    assertEquals(3, ai.size());
    iterator = ai.iterator();
    AnnotationFS next = iterator.next();
    assertEquals("Some text.", next.getCoveredText());

    next = iterator.next();
    assertEquals("More text , with 1 , and more.", next.getCoveredText());

    next = iterator.next();
    assertEquals("even more text.", next.getCoveredText());

  }

  @Test
  public void testMultiSplit() throws Exception {
    String document = "CAP-------------";
    String script = "Document{-> T1};";
    script += "(SPECIAL SPECIAL){-> T2};";
    script += "T1{-> SPLIT(T2, true, false, false)};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "CAP");
  }
}
