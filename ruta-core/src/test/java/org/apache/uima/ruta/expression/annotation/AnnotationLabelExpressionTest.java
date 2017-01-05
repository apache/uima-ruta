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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.apache.uima.util.InvalidXMLException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class AnnotationLabelExpressionTest {

  @Test
  public void testSimple() throws Exception {
    String document = "Some text.";
    String script = "a:W W{-> CREATE(Struct, \"a\"=a)};";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName = "Struct";
    typeMap.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName, list);
    String fn = "a";
    list.add(new TestFeature(fn, "", "uima.tcas.Annotation"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = cas.getTypeSystem().getType(typeName);
    Feature f1 = t.getFeatureByBaseName(fn);
    ai = cas.getAnnotationIndex(t);

    assertEquals(1, ai.size());
    iterator = ai.iterator();
    AnnotationFS next = iterator.next();
    assertEquals("text", next.getCoveredText());
    AnnotationFS a = (AnnotationFS) next.getFeatureValue(f1);
    assertNotNull("Feature value is null!", a);
    assertEquals("Some", a.getCoveredText());
  }

  @Test
  public void testMultiple() throws Exception {
    String document = "Some text.";
    String script = "b:(a:W)+{-PARTOF(Struct) -> CREATE(Struct, \"a\"=a, \"b\"=b, \"c\"=a, \"d\"=b)};";
    script += "Struct.a{-> T1};";
    script += "Struct.b{-> T2};";
    script += "Struct.c{-> T3};";
    script += "Struct.d{-> T4};";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName = "Struct";
    typeMap.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName, list);
    list.add(new TestFeature("a", "", "uima.tcas.Annotation"));
    list.add(new TestFeature("b", "", "uima.tcas.Annotation"));
    list.add(new TestFeature("c", "", "uima.cas.FSArray"));
    list.add(new TestFeature("d", "", "uima.cas.FSArray"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "Some text");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 2, "Some", "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "Some text");
  }

  @Test
  public void testLayers() throws Exception {
    String document = "Some text.";
    String script = "d:(a:W b:W{-> CREATE(Struct, \"a\"=a, \"b\"=b, \"c\"=c, \"d\"=d)} c:PERIOD);";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName = "Struct";
    typeMap.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName, list);
    list.add(new TestFeature("a", "", "uima.tcas.Annotation"));
    list.add(new TestFeature("b", "", "uima.tcas.Annotation"));
    list.add(new TestFeature("c", "", "uima.tcas.Annotation"));
    list.add(new TestFeature("d", "", "uima.tcas.Annotation"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = cas.getTypeSystem().getType(typeName);
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();

    AnnotationFS a = null;
    AnnotationFS next = null;
    Feature f = null;

    next = iterator.next();
    assertEquals("text", next.getCoveredText());

    f = t.getFeatureByBaseName("a");
    a = (AnnotationFS) next.getFeatureValue(f);
    assertNotNull("Feature value is null!", a);
    assertEquals("Some", a.getCoveredText());

    f = t.getFeatureByBaseName("b");
    a = (AnnotationFS) next.getFeatureValue(f);
    assertNotNull("Feature value is null!", a);
    assertEquals("text", a.getCoveredText());

    f = t.getFeatureByBaseName("c");
    a = (AnnotationFS) next.getFeatureValue(f);
    assertNotNull("Feature value is null!", a);
    assertEquals(".", a.getCoveredText());

    f = t.getFeatureByBaseName("d");
    a = (AnnotationFS) next.getFeatureValue(f);
    assertNotNull("Feature value is null!", a);
    assertEquals("Some text.", a.getCoveredText());

  }

  @Test
  public void testActions() throws Exception {
    String script = "a:W W{-> CREATE(Struct1, \"a\"=a)};";
    script += "W W{-> Struct2, Struct3};";
    script += "a:W Struct2{-> SETFEATURE(\"a\", a)};";
    script += "a:W Struct3{-> Struct3.a=a};";

    CAS cas = applyOnStruct4Cas(script);

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;
    AnnotationFS a = null;
    AnnotationFS next = null;
    Feature f = null;

    t = cas.getTypeSystem().getType("Struct1");
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    next = iterator.next();
    assertEquals("text", next.getCoveredText());
    f = t.getFeatureByBaseName("a");
    a = (AnnotationFS) next.getFeatureValue(f);
    assertNotNull("Feature value is null!", a);
    assertEquals("Some", a.getCoveredText());

    t = cas.getTypeSystem().getType("Struct2");
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    next = iterator.next();
    assertEquals("text", next.getCoveredText());
    f = t.getFeatureByBaseName("a");
    a = (AnnotationFS) next.getFeatureValue(f);
    assertNotNull("Feature value is null!", a);
    assertEquals("Some", a.getCoveredText());

    t = cas.getTypeSystem().getType("Struct3");
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    next = iterator.next();
    assertEquals("text", next.getCoveredText());
    f = t.getFeatureByBaseName("a");
    a = (AnnotationFS) next.getFeatureValue(f);
    assertNotNull("Feature value is null!", a);
    assertEquals("Some", a.getCoveredText());

  }

  @Test
  @Ignore
  public void testInsideOut() throws Exception {
    String document = "Some text.";
    String script = "(a:W{-PARTOF(Struct) -> CREATE(Struct, \"a\"=a, \"c\"=a)})+;";
    script += "Struct.a{-> T1};";
    script += "Struct{SIZE(Struct.c,1,1)-> T3};";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName = "Struct";
    typeMap.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName, list);
    list.add(new TestFeature("a", "", "uima.tcas.Annotation"));
    list.add(new TestFeature("c", "", "uima.cas.FSArray"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "Some", "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 2, "Some", "text");
  }

  @Test
  public void testWithinInlined() throws Exception {
    String script = "ANNOTATION c;";
    script += "Document{-> Struct1, Struct1.a = c}<-{i:SW{-> c=i} PERIOD;};";
    script += "i:Document{-> c=i}->{PERIOD{-> Struct2, Struct2.a = c};};";
    // script += "i:Document<-{PERIOD{-> Struct2, Struct2.a = i};};";

    CAS cas = applyOnStruct4Cas(script);

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;
    AnnotationFS a = null;
    AnnotationFS next = null;
    Feature f = null;

    t = cas.getTypeSystem().getType("Struct1");
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    next = iterator.next();
    assertEquals("Some text.", next.getCoveredText());
    f = t.getFeatureByBaseName("a");
    a = (AnnotationFS) next.getFeatureValue(f);
    assertNotNull("Feature value is null!", a);
    assertEquals("text", a.getCoveredText());

    t = cas.getTypeSystem().getType("Struct2");
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    next = iterator.next();
    assertEquals(".", next.getCoveredText());
    f = t.getFeatureByBaseName("a");
    a = (AnnotationFS) next.getFeatureValue(f);
    assertNotNull("Feature value is null!", a);
    assertEquals("Some text.", a.getCoveredText());

  }

  @Test
  public void testFeature() throws Exception {
    CAS cas = RutaTestUtils.getCAS("Some text.");
    Assert.assertTrue(Ruta.matches(cas.getJCas(), "a:W b:W{a.end == (b.begin-1)-> T1};"));
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "text");
  }

  @Test
  public void testComplexFeature() throws Exception {
    String script = "a:W W{-> CREATE(Struct1, \"a\"=a)};";
    CAS cas = applyOnStruct4Cas(script);
    Assert.assertTrue(Ruta.matches(cas.getJCas(), "a:Struct1{a.a.begin == 0 -> T1};"));
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "text");
  }

  @Test(expected = AnalysisEngineProcessException.class)
  public void testWrongFeature() throws ResourceInitializationException, InvalidXMLException,
          IOException, AnalysisEngineProcessException, CASException {
    CAS cas = RutaTestUtils.getCAS("Some text.");
    Ruta.matches(cas.getJCas(), "a:W b:W{a.x == (b.y-1)-> T1};");
  }

  @Test
  public void testSequentialLabelSelfMatch() throws ResourceInitializationException,
          InvalidXMLException, IOException, AnalysisEngineProcessException, CASException {
    CAS cas = RutaTestUtils.getCAS("Some text.");
    Assert.assertFalse(Ruta.matches(cas.getJCas(), "e:CW e;"));
  }

  @Test
  public void testSpecialFeatureWithoutContextMatch() throws Exception {
    Map<String, String> types = new HashMap<>();
    String type = "Valued";
    types.put(type, "uima.tcas.Annotation");
    Map<String, List<TestFeature>> features = new HashMap<>();
    List<TestFeature> list = new ArrayList<>();
    list.add(new TestFeature("value", "", "uima.cas.Integer"));
    features.put(type, list);
    CAS cas = RutaTestUtils.getCAS("Some text.", types, features);

    String script = "a:W{-> Valued, Valued.value = a.end};\n";
    script += "(a:Valued b:Valued){a.value == (b.value-5) -> T1};";

    Ruta.apply(cas, script);
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some text");
  }

  @Test
  public void testAcrossInlinedRules() throws Exception {
    String script = "(# PERIOD){->T1};\n";
    script += "T1{-> Struct1, Struct1.a = i}<-{i:SW;};\n";
    script += "o:T1<-{SW{->Struct2, Struct2.a = o};};\n";
    script += "Struct1.a{->T2};\n";
    script += "Struct1{Struct1.a.ct==\"text\"->T3};\n";
    script += "Struct2.a{->T4};\n";
    script += "Struct2{Struct2.a.ct==\"Some text.\"->T5};\n";
    CAS cas = applyOnStruct4Cas(script);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Some text.");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "Some text.");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 1, "text");
  }

  @Test
  public void testSameOffset() throws Exception {
    String document = "Some text.";
    Map<String, String> typeMap = new TreeMap<String, String>();
    typeMap.put("Struct1", "uima.tcas.Annotation");
    typeMap.put("Struct2", "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    list.add(new TestFeature("a", "", "uima.cas.String"));
    featureMap.put("Struct1", list);
    list = new ArrayList<RutaTestUtils.TestFeature>();
    list.add(new TestFeature("a", "", "uima.tcas.Annotation"));
    featureMap.put("Struct2", list);

    String script = "CW{-> CREATE(Struct1, \"a\" = \"1\")};\n";
    script += "CW{-> CREATE(Struct1, \"a\" = \"2\")};\n";
    script += "CW{-> CREATE(Struct1, \"a\" = \"3\")};\n";
    script += "s:Struct1 SW{-> CREATE(Struct2, \"a\" = s)};\n";
    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    Type type1 = cas.getTypeSystem().getType("Struct1");
    Feature feature1 = type1.getFeatureByBaseName("a");
    Type type2 = cas.getTypeSystem().getType("Struct2");
    Feature feature2 = type2.getFeatureByBaseName("a");
    Collection<AnnotationFS> select = CasUtil.select(cas, type2);
    Assert.assertEquals(3, select.size());
    Iterator<AnnotationFS> iterator = select.iterator();
    Assert.assertEquals("1", iterator.next().getFeatureValue(feature2).getStringValue(feature1));
    Assert.assertEquals("2", iterator.next().getFeatureValue(feature2).getStringValue(feature1));
    Assert.assertEquals("3", iterator.next().getFeatureValue(feature2).getStringValue(feature1));
    cas.release();
  }

  @Test
  public void testFeatureAssignment() throws Exception {
    String document = "Some text.";

    String script = "CW{-> T1};\n";
    script += "t:T1{-> t.end = 10};\n";
    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some text.");

    cas.release();
  }

  @Test
  public void testLabelWithQuantifier() throws Exception {
    String script ="";
    script += "w:(W+) @PERIOD{->Struct1,Struct1.a=w,Struct1.as=w};\n";
    script += "(w:W)+ @PERIOD{->Struct2,Struct2.a=w,Struct2.as=w};\n";
    script += "w:W+ @PERIOD{->Struct3,Struct3.a=w,Struct3.as=w};\n";

    Map<String, String> typeMap = new TreeMap<String, String>();
    typeMap.put("Struct1", "uima.tcas.Annotation");
    typeMap.put("Struct2", "uima.tcas.Annotation");
    typeMap.put("Struct3", "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put("Struct1", list);
    featureMap.put("Struct2", list);
    featureMap.put("Struct3", list);
    list.add(new TestFeature("a", "", "uima.tcas.Annotation"));
    list.add(new TestFeature("as", "", "uima.cas.FSArray"));

    CAS cas = RutaTestUtils.getCAS("Some text.", typeMap, featureMap);
    Ruta.apply(cas, script);

    Type type = null;
    Feature featureA = null;
    Feature featureAS = null;
    Iterator<AnnotationFS> iterator = null;
    AnnotationFS next = null;
    FeatureStructure featureValueA = null;
    FeatureStructure featureValueAS = null;
    Annotation a = null;
    FSArray as = null;

    type = cas.getTypeSystem().getType("Struct1");
    featureA = type.getFeatureByBaseName("a");
    featureAS = type.getFeatureByBaseName("as");
    iterator = CasUtil.select(cas, type).iterator();
    Assert.assertTrue(iterator.hasNext());
    next = iterator.next();
    Assert.assertFalse(iterator.hasNext());
    featureValueA = next.getFeatureValue(featureA);
    featureValueAS = next.getFeatureValue(featureAS);
    Assert.assertNotNull(featureValueA);
    Assert.assertNotNull(featureValueAS);
    Assert.assertTrue(featureValueA instanceof Annotation);
    Assert.assertTrue(featureValueAS instanceof FSArray);
    a = (Annotation) featureValueA;
    as = (FSArray) featureValueAS;
    Assert.assertEquals("Some text", a.getCoveredText());
    Assert.assertEquals("Annotation", a.getType().getShortName());
    Assert.assertEquals(1, as.size());
    Assert.assertEquals("Some text", ((AnnotationFS) as.get(0)).getCoveredText());
    Assert.assertEquals("Annotation", as.get(0).getType().getShortName());
    
    type = cas.getTypeSystem().getType("Struct2");
    featureA = type.getFeatureByBaseName("a");
    featureAS = type.getFeatureByBaseName("as");
    iterator = CasUtil.select(cas, type).iterator();
    Assert.assertTrue(iterator.hasNext());
    next = iterator.next();
    Assert.assertFalse(iterator.hasNext());
    featureValueA = next.getFeatureValue(featureA);
    featureValueAS = next.getFeatureValue(featureAS);
    Assert.assertNotNull(featureValueA);
    Assert.assertNotNull(featureValueAS);
    Assert.assertTrue(featureValueA instanceof Annotation);
    Assert.assertTrue(featureValueAS instanceof FSArray);
    a = (Annotation) featureValueA;
    as = (FSArray) featureValueAS;
    Assert.assertEquals("Some", a.getCoveredText());
    Assert.assertEquals("CW", a.getType().getShortName());
    Assert.assertEquals(2, as.size());
    Assert.assertEquals("text", ((AnnotationFS) as.get(0)).getCoveredText());
    Assert.assertEquals("Some", ((AnnotationFS) as.get(1)).getCoveredText());
    Assert.assertEquals("SW", as.get(0).getType().getShortName());
    Assert.assertEquals("CW", as.get(1).getType().getShortName());
    
    type = cas.getTypeSystem().getType("Struct3");
    featureA = type.getFeatureByBaseName("a");
    featureAS = type.getFeatureByBaseName("as");
    iterator = CasUtil.select(cas, type).iterator();
    Assert.assertTrue(iterator.hasNext());
    next = iterator.next();
    Assert.assertFalse(iterator.hasNext());
    featureValueA = next.getFeatureValue(featureA);
    featureValueAS = next.getFeatureValue(featureAS);
    Assert.assertNotNull(featureValueA);
    Assert.assertNotNull(featureValueAS);
    Assert.assertTrue(featureValueA instanceof Annotation);
    Assert.assertTrue(featureValueAS instanceof FSArray);
    a = (Annotation) featureValueA;
    as = (FSArray) featureValueAS;
    Assert.assertEquals("Some text", a.getCoveredText());
    Assert.assertEquals("Annotation", a.getType().getShortName());
    Assert.assertEquals(1, as.size());
    Assert.assertEquals("Some text", ((AnnotationFS) as.get(0)).getCoveredText());
    Assert.assertEquals("Annotation", as.get(0).getType().getShortName());

    cas.release();
  }

  @Test
  public void testLabelReset() throws Exception {
    String script= "";
    script += "W{->Struct1, Struct1.a = w} w:W?;";
    script += "W{->Struct2, Struct2.a = c} c:(W ANY)?;";
    
    CAS cas = applyOnStruct4Cas(script);
    
    Type type1 = cas.getTypeSystem().getType("Struct1");
    Feature feature1 = type1.getFeatureByBaseName("a");
    
    List<AnnotationFS> select1 = new ArrayList<>(CasUtil.select(cas, type1));
    Assert.assertEquals(2, select1.size());
    
    AnnotationFS a11 = select1.get(0);
    Assert.assertEquals("Some", a11.getCoveredText());
    AnnotationFS featureValue11 = (AnnotationFS) a11.getFeatureValue(feature1);
    Assert.assertEquals("text", featureValue11.getCoveredText());
    
    AnnotationFS a21 = select1.get(1);
    Assert.assertEquals("text", a21.getCoveredText());
    AnnotationFS featureValue21 = (AnnotationFS) a21.getFeatureValue(feature1);
    Assert.assertNull(featureValue21);
    
    Type type2 = cas.getTypeSystem().getType("Struct2");
    Feature feature2 = type2.getFeatureByBaseName("a");
    
    List<AnnotationFS> select2 = new ArrayList<>(CasUtil.select(cas, type2));
    Assert.assertEquals(2, select2.size());
    
    AnnotationFS  a12 = select2.get(0);
    Assert.assertEquals("Some", a12.getCoveredText());
    AnnotationFS featureValue12 = (AnnotationFS) a12.getFeatureValue(feature2);
    Assert.assertEquals("text.", featureValue12.getCoveredText());
    
    AnnotationFS  a22 = select2.get(1);
    Assert.assertEquals("text", a22.getCoveredText());
    AnnotationFS featureValue22 = (AnnotationFS) a22.getFeatureValue(feature2);
    Assert.assertNull(featureValue22);
    
    
  }
  
  @Test(expected = AnalysisEngineProcessException.class)
  public void testInvalidLabelWithQuantifier1() throws Exception {
    String script= "";
    script += "w:ANY{->Struct1, Struct1.a = w};";
    script += "s1:Struct1{PARTOF(CW)->T1} s2:Struct1*{s2.a.ct == \"text\"};";
    applyOnStruct4Cas(script);
  }
  
  @Test
  public void testInvalidLabelWithQuantifier2() throws Exception {
    String script= "";
    script += "w:ANY{->Struct1, Struct1.a = w};";
    script += "PERIOD{s.a.ct==\"text\" ->T2} s:Struct1*{s.a.ct == \"text\"};";
    CAS cas = applyOnStruct4Cas(script);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
  }
  
  @Test
  public void testInvalidLabelWithOptional() throws Exception {
    String script= "";
    script += "p:ANY{-> p.begin = a.begin, p.end = a.end} a:ANY;";
    script += "PERIOD{-> T1};";
    CAS cas = applyOnStruct4Cas(script);
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, ".");
  }
  
  @Test
  public void testInComposedStringExpression() throws Exception {
    String script= "";
    script += "c:CW{-> Struct1, Struct1.s = \"<\"+c.ct+\">\" };";
    script += "Struct1.s ==\"<Some>\"{-> T1};";
    
    String document = "Some text.";
    Map<String, String> typeMap = new TreeMap<String, String>();
    typeMap.put("Struct1", "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put("Struct1", list);
    list.add(new TestFeature("s", "", "uima.cas.String"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some");
  }
  
  @Test
  public void testStackedInCondition() throws Exception {
    String script= "";
    script += "w1:W{-> Struct1, Struct1.a = w2} w2:W;";
    script += "s:Struct1{REGEXP(s.a.ct, \"text\") -> T1};";
    CAS cas = applyOnStruct4Cas(script);
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some");
  }
  
  @Test
  public void testInUnmark() throws Exception {
    String script= "";
    script += "SW{-> T1};";
    script += "w1:W{-> Struct1, Struct1.a = w2} w2:T1;";
    script += "s:Struct1{-> UNMARK(s.a)};";
    CAS cas = applyOnStruct4Cas(script);
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
  }
  
  @Test
  public void testInMatchCondition() throws Exception {
    String script= "";
    script += "CW{-> Struct1, Struct1.a=sw} sw:SW;\n";
    script += "s:Struct1 s.a{-> T1};\n";
//    script += "(s:Struct1 SW){->T2}<-{s W{REGEXP(\"text\")};};\n";
    CAS cas = applyOnStruct4Cas(script);
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "text");
//    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "Some text");
  }
  
  
  private CAS applyOnStruct4Cas(String script) throws Exception {
    String document = "Some text.";
    Map<String, String> typeMap = new TreeMap<String, String>();
    typeMap.put("Struct1", "uima.tcas.Annotation");
    typeMap.put("Struct2", "uima.tcas.Annotation");
    typeMap.put("Struct3", "uima.tcas.Annotation");
    typeMap.put("Struct4", "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put("Struct1", list);
    featureMap.put("Struct2", list);
    featureMap.put("Struct3", list);
    featureMap.put("Struct4", list);
    list.add(new TestFeature("a", "", "uima.tcas.Annotation"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);
    return cas;
  }

}
