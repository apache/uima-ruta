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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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

    assertThat(ai).hasSize(1);
    iterator = ai.iterator();
    AnnotationFS next = iterator.next();
    assertThat(next.getCoveredText()).isEqualTo("text");
    AnnotationFS a = (AnnotationFS) next.getFeatureValue(f1);
    assertThat(a).as("Feature value is null!").isNotNull();
    assertThat(a.getCoveredText()).isEqualTo("Some");
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
    assertThat(ai).hasSize(1);
    iterator = ai.iterator();

    AnnotationFS a = null;
    AnnotationFS next = null;
    Feature f = null;

    next = iterator.next();
    assertThat(next.getCoveredText()).isEqualTo("text");

    f = t.getFeatureByBaseName("a");
    a = (AnnotationFS) next.getFeatureValue(f);
    assertThat(a).as("Feature value is null!").isNotNull();
    assertThat(a.getCoveredText()).isEqualTo("Some");

    f = t.getFeatureByBaseName("b");
    a = (AnnotationFS) next.getFeatureValue(f);
    assertThat(a).as("Feature value is null!").isNotNull();
    assertThat(a.getCoveredText()).isEqualTo("text");

    f = t.getFeatureByBaseName("c");
    a = (AnnotationFS) next.getFeatureValue(f);
    assertThat(a).as("Feature value is null!").isNotNull();
    assertThat(a.getCoveredText()).isEqualTo(".");

    f = t.getFeatureByBaseName("d");
    a = (AnnotationFS) next.getFeatureValue(f);
    assertThat(a).as("Feature value is null!").isNotNull();
    assertThat(a.getCoveredText()).isEqualTo("Some text.");

  }

  @Test
  public void testActions() throws Exception {

    String script = "a:W W{-> CREATE(Struct1, \"a\"=a)};";
    script += "W W{-> Struct2, Struct3};";
    script += "a:W Struct2{-> SETFEATURE(\"a\", a)};";
    script += "a:W Struct3{-> Struct3.a=a};";

    CAS cas = this.applyOnStruct4Cas(script);

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;
    AnnotationFS a = null;
    AnnotationFS next = null;
    Feature f = null;

    t = cas.getTypeSystem().getType("Struct1");
    ai = cas.getAnnotationIndex(t);
    assertThat(ai).hasSize(1);
    iterator = ai.iterator();
    next = iterator.next();
    assertThat(next.getCoveredText()).isEqualTo("text");
    f = t.getFeatureByBaseName("a");
    a = (AnnotationFS) next.getFeatureValue(f);
    assertThat(a).as("Feature value is null!").isNotNull();
    assertThat(a.getCoveredText()).isEqualTo("Some");

    t = cas.getTypeSystem().getType("Struct2");
    ai = cas.getAnnotationIndex(t);
    assertThat(ai).hasSize(1);
    iterator = ai.iterator();
    next = iterator.next();
    assertThat(next.getCoveredText()).isEqualTo("text");
    f = t.getFeatureByBaseName("a");
    a = (AnnotationFS) next.getFeatureValue(f);
    assertThat(a).as("Feature value is null!").isNotNull();
    assertThat(a.getCoveredText()).isEqualTo("Some");

    t = cas.getTypeSystem().getType("Struct3");
    ai = cas.getAnnotationIndex(t);
    assertThat(ai).hasSize(1);
    iterator = ai.iterator();
    next = iterator.next();
    assertThat(next.getCoveredText()).isEqualTo("text");
    f = t.getFeatureByBaseName("a");
    a = (AnnotationFS) next.getFeatureValue(f);
    assertThat(a).as("Feature value is null!").isNotNull();
    assertThat(a.getCoveredText()).isEqualTo("Some");

  }

  @Test
  @Disabled
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

    CAS cas = this.applyOnStruct4Cas(script);

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;
    AnnotationFS a = null;
    AnnotationFS next = null;
    Feature f = null;

    t = cas.getTypeSystem().getType("Struct1");
    ai = cas.getAnnotationIndex(t);
    assertThat(ai).hasSize(1);
    iterator = ai.iterator();
    next = iterator.next();
    assertThat(next.getCoveredText()).isEqualTo("Some text.");
    f = t.getFeatureByBaseName("a");
    a = (AnnotationFS) next.getFeatureValue(f);
    assertThat(a).as("Feature value is null!").isNotNull();
    assertThat(a.getCoveredText()).isEqualTo("text");

    t = cas.getTypeSystem().getType("Struct2");
    ai = cas.getAnnotationIndex(t);
    assertThat(ai).hasSize(1);
    iterator = ai.iterator();
    next = iterator.next();
    assertThat(next.getCoveredText()).isEqualTo(".");
    f = t.getFeatureByBaseName("a");
    a = (AnnotationFS) next.getFeatureValue(f);
    assertThat(a).as("Feature value is null!").isNotNull();
    assertThat(a.getCoveredText()).isEqualTo("Some text.");

  }

  @Test
  public void testFeature() throws Exception {

    CAS cas = RutaTestUtils.getCAS("Some text.");
    assertThat(Ruta.matches(cas.getJCas(), "a:W b:W{a.end == (b.begin-1)-> T1};")).isTrue();
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "text");
  }

  @Test
  public void testComplexFeature() throws Exception {

    String script = "a:W W{-> CREATE(Struct1, \"a\"=a)};";
    CAS cas = this.applyOnStruct4Cas(script);
    assertThat(Ruta.matches(cas.getJCas(), "a:Struct1{a.a.begin == 0 -> T1};")).isTrue();
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "text");
  }

  @Test
  public void testWrongFeature() throws Exception {

    CAS cas = RutaTestUtils.getCAS("Some text.");

    assertThatExceptionOfType(AnalysisEngineProcessException.class)
            .isThrownBy(() -> Ruta.matches(cas.getJCas(), "a:W b:W{a.x == (b.y-1)-> T1};"));
  }

  @Test
  public void testSequentialLabelSelfMatch() throws Exception {

    CAS cas = RutaTestUtils.getCAS("Some text.");
    assertThat(Ruta.matches(cas.getJCas(), "e:CW e;")).isFalse();
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
    CAS cas = this.applyOnStruct4Cas(script);
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
    assertThat(select).hasSize(3);
    Iterator<AnnotationFS> iterator = select.iterator();
    assertThat(iterator.next().getFeatureValue(feature2).getStringValue(feature1)).isEqualTo("1");
    assertThat(iterator.next().getFeatureValue(feature2).getStringValue(feature1)).isEqualTo("2");
    assertThat(iterator.next().getFeatureValue(feature2).getStringValue(feature1)).isEqualTo("3");
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

    String script = "";
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
    FSArray<?> as = null;

    type = cas.getTypeSystem().getType("Struct1");
    featureA = type.getFeatureByBaseName("a");
    featureAS = type.getFeatureByBaseName("as");
    iterator = CasUtil.select(cas, type).iterator();
    assertThat(iterator.hasNext()).isTrue();
    next = iterator.next();
    assertThat(iterator.hasNext()).isFalse();
    featureValueA = next.getFeatureValue(featureA);
    featureValueAS = next.getFeatureValue(featureAS);
    assertThat(featureValueA).isNotNull();
    assertThat(featureValueAS).isNotNull();
    assertThat(featureValueA instanceof Annotation).isTrue();
    assertThat(featureValueAS instanceof FSArray).isTrue();
    a = (Annotation) featureValueA;
    as = (FSArray<?>) featureValueAS;
    assertThat(a.getCoveredText()).isEqualTo("Some text");
    assertThat(a.getType().getShortName()).isEqualTo("Annotation");
    assertThat(as).hasSize(1);
    assertThat(((AnnotationFS) as.get(0)).getCoveredText()).isEqualTo("Some text");
    assertThat(as.get(0).getType().getShortName()).isEqualTo("Annotation");

    type = cas.getTypeSystem().getType("Struct2");
    featureA = type.getFeatureByBaseName("a");
    featureAS = type.getFeatureByBaseName("as");
    iterator = CasUtil.select(cas, type).iterator();
    assertThat(iterator.hasNext()).isTrue();
    next = iterator.next();
    assertThat(iterator.hasNext()).isFalse();
    featureValueA = next.getFeatureValue(featureA);
    featureValueAS = next.getFeatureValue(featureAS);
    assertThat(featureValueA).isNotNull();
    assertThat(featureValueAS).isNotNull();
    assertThat(featureValueA instanceof Annotation).isTrue();
    assertThat(featureValueAS instanceof FSArray).isTrue();
    a = (Annotation) featureValueA;
    as = (FSArray<?>) featureValueAS;
    assertThat(a.getCoveredText()).isEqualTo("Some");
    assertThat(a.getType().getShortName()).isEqualTo("CW");
    assertThat(as).hasSize(2);
    assertThat(((AnnotationFS) as.get(0)).getCoveredText()).isEqualTo("text");
    assertThat(((AnnotationFS) as.get(1)).getCoveredText()).isEqualTo("Some");
    assertThat(as.get(0).getType().getShortName()).isEqualTo("SW");
    assertThat(as.get(1).getType().getShortName()).isEqualTo("CW");

    type = cas.getTypeSystem().getType("Struct3");
    featureA = type.getFeatureByBaseName("a");
    featureAS = type.getFeatureByBaseName("as");
    iterator = CasUtil.select(cas, type).iterator();
    assertThat(iterator.hasNext()).isTrue();
    next = iterator.next();
    assertThat(iterator.hasNext()).isFalse();
    featureValueA = next.getFeatureValue(featureA);
    featureValueAS = next.getFeatureValue(featureAS);
    assertThat(featureValueA).isNotNull();
    assertThat(featureValueAS).isNotNull();
    assertThat(featureValueA instanceof Annotation).isTrue();
    assertThat(featureValueAS instanceof FSArray).isTrue();
    a = (Annotation) featureValueA;
    as = (FSArray<?>) featureValueAS;
    assertThat(a.getCoveredText()).isEqualTo("Some text");
    assertThat(a.getType().getShortName()).isEqualTo("Annotation");
    assertThat(as).hasSize(1);
    assertThat(((AnnotationFS) as.get(0)).getCoveredText()).isEqualTo("Some text");
    assertThat(as.get(0).getType().getShortName()).isEqualTo("Annotation");

    cas.release();
  }

  @Test
  public void testLabelReset() throws Exception {

    String script = "";
    script += "W{->Struct1, Struct1.a = w} w:W?;";
    script += "W{->Struct2, Struct2.a = c} c:(W ANY)?;";

    CAS cas = this.applyOnStruct4Cas(script);

    Type type1 = cas.getTypeSystem().getType("Struct1");
    Feature feature1 = type1.getFeatureByBaseName("a");

    List<AnnotationFS> select1 = new ArrayList<>(CasUtil.select(cas, type1));
    assertThat(select1).hasSize(2);

    AnnotationFS a11 = select1.get(0);
    assertThat(a11.getCoveredText()).isEqualTo("Some");
    AnnotationFS featureValue11 = (AnnotationFS) a11.getFeatureValue(feature1);
    assertThat(featureValue11.getCoveredText()).isEqualTo("text");

    AnnotationFS a21 = select1.get(1);
    assertThat(a21.getCoveredText()).isEqualTo("text");
    AnnotationFS featureValue21 = (AnnotationFS) a21.getFeatureValue(feature1);
    assertThat(featureValue21).isNull();

    Type type2 = cas.getTypeSystem().getType("Struct2");
    Feature feature2 = type2.getFeatureByBaseName("a");

    List<AnnotationFS> select2 = new ArrayList<>(CasUtil.select(cas, type2));
    assertThat(select2).hasSize(2);

    AnnotationFS a12 = select2.get(0);
    assertThat(a12.getCoveredText()).isEqualTo("Some");
    AnnotationFS featureValue12 = (AnnotationFS) a12.getFeatureValue(feature2);
    assertThat(featureValue12.getCoveredText()).isEqualTo("text.");

    AnnotationFS a22 = select2.get(1);
    assertThat(a22.getCoveredText()).isEqualTo("text");
    AnnotationFS featureValue22 = (AnnotationFS) a22.getFeatureValue(feature2);
    assertThat(featureValue22).isNull();

  }

  @Test
  public void testInvalidLabelWithQuantifier1() throws Exception {

    String script = """
            w:ANY{->Struct1, Struct1.a = w};
            s1:Struct1{PARTOF(CW)->T1} s2:Struct1*{s2.a.ct == \"text\"};
            """;

    assertThatExceptionOfType(AnalysisEngineProcessException.class)
            .isThrownBy(() -> this.applyOnStruct4Cas(script));
  }

  @Test
  public void testInvalidLabelWithQuantifier2() throws Exception {

    String script = "";
    script += "w:ANY{->Struct1, Struct1.a = w};";
    script += "PERIOD{s.a.ct==\"text\" ->T2} s:Struct1*{s.a.ct == \"text\"};";
    CAS cas = this.applyOnStruct4Cas(script);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
  }

  @Test
  public void testInvalidLabelWithOptional() throws Exception {

    String script = "";
    script += "p:ANY{-> p.begin = a.begin, p.end = a.end} a:ANY;";
    script += "PERIOD{-> T1};";
    CAS cas = this.applyOnStruct4Cas(script);
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, ".");
  }

  @Test
  public void testInComposedStringExpression() throws Exception {

    String script = "";
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

    String script = "";
    script += "w1:W{-> Struct1, Struct1.a = w2} w2:W;";
    script += "s:Struct1{REGEXP(s.a.ct, \"text\") -> T1};";
    CAS cas = this.applyOnStruct4Cas(script);
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some");
  }

  @Test
  public void testStackedReinitLazyFeature() throws Exception {

    String document = "Some text.";
    Map<String, String> typeMap = new TreeMap<String, String>();
    typeMap.put("Struct", "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put("Struct", list);
    list.add(new TestFeature("a", "", "uima.tcas.Annotation"));
    list.add(new TestFeature("s", "", "uima.cas.String"));
    list.add(new TestFeature("b", "", "uima.cas.Boolean"));
    list.add(new TestFeature("d", "", "uima.cas.Double"));

    String script = "";
    script += "SW{-> Struct};";
    script += "CW{-> Struct, Struct.a=s2} s2:Struct;";
    script += "s1:Struct{s1.begin==0 -> s1.a.a=CW};";
    script += "s1:Struct{s1.begin==0 -> s1.a.s=\"s\"};";
    script += "s1:Struct{s1.begin==0 -> s1.a.d=1.0};";
    script += "s1:Struct{s1.begin==0 -> s1.a.b=true};";
    script += "s1:Struct{s1.begin==0, s1.a.a.begin == 0 -> T1};";
    script += "s1:Struct{s1.begin==0, s1.a.s == \"s\" -> T2};";
    script += "s1:Struct{s1.begin==0, s1.a.d == 1.0 -> T3};";
    script += "s1:Struct{s1.begin==0, s1.a.b -> T4};";

    script += "s1:Struct s2:Struct PERIOD{-> CREATE(Struct, \"a\" = s1.a)};";
    script += "s1:Struct{IS(PERIOD), s1.a.a.begin == 0 -> T5};";
    script += "s1:Struct{IS(PERIOD), s1.a.s == \"s\" -> T6};";
    script += "s1:Struct{IS(PERIOD), s1.a.d == 1.0 -> T7};";
    script += "s1:Struct{IS(PERIOD), s1.a.b -> T8};";
    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 7, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 8, 1, ".");
  }

  @Test
  public void testInUnmark() throws Exception {

    String script = "";
    script += "SW{-> T1};";
    script += "w1:W{-> Struct1, Struct1.a = w2} w2:T1;";
    script += "s:Struct1{-> UNMARK(s.a)};";
    CAS cas = this.applyOnStruct4Cas(script);
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
  }

  @Test
  public void testInMatchCondition() throws Exception {

    String script = "";
    script += "CW{-> Struct1, Struct1.a=sw} sw:SW;\n";
    script += "s:Struct1 s.a{-> T1};\n";
    // script += "(s:Struct1 SW){->T2}<-{s W{REGEXP(\"text\")};};\n";
    CAS cas = this.applyOnStruct4Cas(script);
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "text");
    // RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "Some text");
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

  @Test
  public void testLabelAssignmentInFailedQuantifier() throws Exception {

    String document = "a 1 a a 1 a a 1 a";

    String script = "NUM{-> T1, T2, T3, T4, T7, T8};\n";
    script += "SW{-> T5, T6};\n";

    script += "ps:T1{-> ps.end = a.end} a:ANY[1,10]{-PARTOF(T1)};\n";
    script += "a:ANY[1,10]{-PARTOF(T2)} ps:@T2{-> ps.begin = a.begin};\n";

    script += "ps:T3{-> ps.end = a.end} a:ANY+{-PARTOF(T3)};\n";
    script += "a:ANY+{-PARTOF(T4)} ps:@T4{-> ps.begin = a.begin};\n";

    // script += "ps:T5{-> ps.end = a.end} a:ANY?{-PARTOF(T5)};\n";
    // script += "a:ANY?{-PARTOF(T6)} ps:@T6{-> ps.begin = a.begin};\n";

    // script += "ps:T7{-> ps.end = a.end} a:ANY*?{-PARTOF(T7)};\n";
    // script += "a:ANY*?{-PARTOF(T8)} ps:@T8{-> ps.begin = a.begin};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 3, "1 a a", "1 a a", "1 a");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 3, "a 1", "a a 1", "a a 1");

    RutaTestUtils.assertAnnotationsEquals(cas, 3, 3, "1 a a", "1 a a", "1 a");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 3, "a 1", "a a 1", "a a 1");

    // RutaTestUtils.assertAnnotationsEquals(cas, 5, 6, "a 1", "a", "a 1", "a", "a 1", "a");
    // RutaTestUtils.assertAnnotationsEquals(cas, 6, 6, "a", "1 a", "a", "1 a", "a", "1 a");

    // RutaTestUtils.assertAnnotationsEquals(cas, 7, 3, "1 a a", "1 a a", "1 a");
    // RutaTestUtils.assertAnnotationsEquals(cas, 8, 3, "a 1", "a a 1", "a a 1");
  }

  @Test
  public void testRemoveFailedMatch() throws Exception {

    String document = "a b c d";

    String script = "";
    script += "W.begin==0{-> T1};\n";
    script += "T1 a:ANY{REGEXP(\"c\")}->{a{-> T2};};\n";
    script += "T1 a:ANY{REGEXP(\"c\")}<-{a{-> T3};};\n";
    script += "d:Document{-> T4}<-{a:d{STARTSWITH(CW)};b:d{STARTSWITH(W)};}->{a{->T5};};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "a");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, document);
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 0);

  }

  @Test
  public void testTemporalFailingLabelAssignment() throws Exception {

    String document = "Some text.";
    Map<String, String> typeMap = new TreeMap<String, String>();
    typeMap.put("Struct1", "uima.tcas.Annotation");
    typeMap.put("Struct2", "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put("Struct1", list);
    featureMap.put("Struct2", list);
    list.add(new TestFeature("a", "", "uima.tcas.Annotation"));
    list.add(new TestFeature("b", "", "uima.tcas.Annotation"));

    String script = "";
    script += "Document{->CREATE(Struct1, \"a\" = a, \"b\" = b)} <-{a:W b:W;};\n";
    script += "Document{->CREATE(Struct2, \"a\" = a, \"b\" = b)} <-{a:W{STARTSWITH(Document)} b:W;};\n";
    script += "Struct1.a{-> T1};\n";
    script += "Struct1.b{-> T2};\n";
    script += "Struct2.a{-> T3};\n";
    script += "Struct2.b{-> T4};\n";

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "text");

  }

  @Test
  public void testComplexLabelReset() throws Exception {
    // UIMA-6262
    // Enumeration
    // <-{dc1:DiagnosisConcept{dc1.negatedBy!=null}; cue:dc1.negatedBy;}
    // ->{dc2:DiagnosisConcept{dc2.negatedBy==null -> dc2.negatedBy=cue};
    // };
    // solved by rewriting

    String document = "Test 1. Test 2. Test 3. Test 4.";

    String script = "";
    script += "ANY+{-PARTOF(T1),-PARTOF(PERIOD)-> T1};\n";
    script += "T1<-{n1:NUM{REGEXP(\"1|3\")};}<-{n2:n1;}\n"; // one applies always
    script += "->{n2{->T2};};"; //

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 2, "1", "3");
  }

  @Test
  public void testImplicitNullCheck() throws Exception {

    String document = "CW sw 1";

    String script = "";
    script += "(w:W n:NUM?){n!=null -> T1};";
    script += "(w:W n:NUM?){n==null -> T2};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "sw 1");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "CW");
  }

  @Test
  @Disabled
  public void testInlineWithQuantifier() throws Exception {

    String script = "";
    script += "CW{-> Struct1, Struct1.a=sw} sw:SW;\n";
    script += "sw:SW{-> Struct1, Struct1.a=sw};\n";
    script += "p:PERIOD{-> Struct1, Struct1.a=p};\n";
    script += "(s1:Struct1<-{u1:s1.a;} (s2:Struct1<-{u2:s2.a{u2.ct==u1.ct};})+){-> T1};\n";
    CAS cas = this.applyOnStruct4Cas(script);
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some text");
  }

  @Test
  public void testCompareStringFeatures() throws Exception {

    String document = "A b.";
    Map<String, String> typeMap = new TreeMap<String, String>();
    typeMap.put("Struct1", "uima.tcas.Annotation");
    typeMap.put("Struct2", "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put("Struct1", list);
    featureMap.put("Struct2", list);
    list.add(new TestFeature("s", "", "uima.cas.String"));

    String script = "";
    script += "SW{->CREATE(Struct2, \"s\" = \"b\")};\n";
    script += "SW{->CREATE(Struct2, \"s\" = \"a\")};\n";
    script += "CW{->CREATE(Struct1, \"s\" = \"a\")};\n";
    script += "(s1:Struct1 s2:Struct2){s1.s==s2.s-> T1};\n";

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script, RutaTestUtils.getDebugParams());

    if (RutaTestUtils.DEBUG_MODE) {
      RutaTestUtils.storeTypeSystem(typeMap, featureMap);
      RutaTestUtils.storeCas(cas, "testCompareStringFeatures");
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "A b");

  }

}
