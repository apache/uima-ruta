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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.util.FSCollectionFactory;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.jupiter.api.Test;

public class AnnotationFeatureExpressionTest {

  @Test
  public void testList() throws Exception {
    String document = "Some text.";
    String script = "";
    script += "W{-> CREATE(Inner, \"a\"=W)};";
    script += "Document{-> CREATE(Struct, \"as\"=Inner)};";
    script += "Struct.as{->T1};";
    script += "Struct.as.a{->T2};";
    script += "Struct{-> TRIM(PERIOD)};";
    script += "Struct.as{->T3} @PERIOD;";
    script += "Struct.as.a{->T4} @PERIOD;";

    Map<String, String> typeMap = new TreeMap<>();
    String typeName1 = "Struct";
    typeMap.put(typeName1, "uima.tcas.Annotation");
    String typeName2 = "Inner";
    typeMap.put(typeName2, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<>();
    List<TestFeature> list = new ArrayList<>();
    featureMap.put(typeName1, list);
    String fn1 = "as";
    list.add(new TestFeature(fn1, "", "uima.cas.FSArray"));
    list = new ArrayList<>();
    featureMap.put(typeName2, list);
    String fn2 = "a";
    list.add(new TestFeature(fn2, "", "uima.cas.FSArray"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "Some", "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 2, "Some", "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 2, "Some", "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 2, "Some", "text");
  }

  @Test
  public void testListAssignment() throws Exception {
    String document = "Some text.";
    String script = "";
    script += "SW{-> Struct};";
    script += "(# s:Struct #){-> s.as=ANY};";
    script += "Struct.as{->T1};";
    script += "(# s:Struct #){-> s.as=org.apache.uima.ruta.type.ANY};";
    script += "Struct.as{->T2};";
    script += "s:Struct{-> s.as=org.apache.uima.ruta.type.ANY};";
    script += "Struct.as{->T3};";

    Map<String, String> typeMap = new TreeMap<>();
    String typeName1 = "Struct";
    typeMap.put(typeName1, CAS.TYPE_NAME_ANNOTATION);

    Map<String, List<TestFeature>> featureMap = new TreeMap<>();
    List<TestFeature> list = new ArrayList<>();
    featureMap.put(typeName1, list);
    String fn1 = "as";
    list.add(new TestFeature(fn1, "", CAS.TYPE_NAME_FS_ARRAY));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 3, "Some", "text", ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 3, "Some", "text", ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "text");
  }

  @Test
  public void testIndex() throws Exception {
    String document = "Some text.";
    String script = "";
    script += "W{-> CREATE(Inner, \"a\"=W)};";
    script += "Document{-> CREATE(Struct, \"as\"=Inner)};";
    script += "Struct.as[0]{->T1};";
    script += "Struct.as[1]{->T2};";
    script += "Struct.as[0].a{->T3};";
    script += "Struct.as[1].a{->T4};";

    Map<String, String> typeMap = new TreeMap<>();
    String typeName1 = "Struct";
    typeMap.put(typeName1, "uima.tcas.Annotation");
    String typeName2 = "Inner";
    typeMap.put(typeName2, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<>();
    List<TestFeature> list = new ArrayList<>();
    featureMap.put(typeName1, list);
    String fn1 = "as";
    list.add(new TestFeature(fn1, "", "uima.cas.FSArray"));
    list = new ArrayList<>();
    featureMap.put(typeName2, list);
    String fn2 = "a";
    list.add(new TestFeature(fn2, "", "uima.cas.FSArray"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "text");
  }

  @Test
  public void testStringArray() throws Exception {
    String document = "Some text.";
    String script = "";
    script += "W{-> CREATE(Struct, \"as\"={\"text\"})};\n";
    script += "Struct{CONTAINS(Struct.as, Struct.ct)-> T1};\n";

    Map<String, String> typeMap = new TreeMap<>();
    String typeName1 = "Struct";
    typeMap.put(typeName1, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<>();
    List<TestFeature> list = new ArrayList<>();
    featureMap.put(typeName1, list);
    String fn1 = "as";
    list.add(new TestFeature(fn1, "", "uima.cas.StringArray"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "text");

  }

  @Test
  public void testFeatureStructureFeature() throws Exception {

    String document = "Some text.";

    Map<String, String> typeMap = new TreeMap<>();
    String typeName1 = "A1";
    typeMap.put(typeName1, "uima.tcas.Annotation");
    String typeName2 = "FS1";
    typeMap.put(typeName2, "uima.cas.TOP");

    Map<String, List<TestFeature>> featureMap = new TreeMap<>();
    List<TestFeature> list = new ArrayList<>();
    featureMap.put(typeName1, list);
    String fn1 = "fss";
    list.add(new TestFeature(fn1, "", "uima.cas.FSArray"));
    String fn2 = "fs";
    list.add(new TestFeature(fn2, "", "uima.cas.TOP"));

    list = new ArrayList<>();
    featureMap.put(typeName2, list);
    String fn3 = "fss";
    list.add(new TestFeature(fn3, "", "uima.cas.FSArray"));
    String fn4 = "fs";
    list.add(new TestFeature(fn4, "", "uima.cas.TOP"));
    String fn5 = "s";
    list.add(new TestFeature(fn5, "", "uima.cas.String"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Type type1 = cas.getTypeSystem().getType(typeName1);
    Feature type1fss = type1.getFeatureByBaseName(fn1);
    Feature type1fs = type1.getFeatureByBaseName(fn2);
    Type type2 = cas.getTypeSystem().getType(typeName2);
    Feature type2fss = type2.getFeatureByBaseName(fn3);
    Feature type2fs = type2.getFeatureByBaseName(fn4);
    Feature type2s = type2.getFeatureByBaseName(fn5);
    AnnotationFS a1 = cas.createAnnotation(type1, 0, 4);
    AnnotationFS a2 = cas.createAnnotation(type1, 5, 9);
    FeatureStructure fs1 = cas.createFS(type2);
    FeatureStructure fs2 = cas.createFS(type2);
    FSArray<FeatureStructure> fsArray1 = FSCollectionFactory.createFSArray(cas.getJCas(), fs1, fs2);
    fs1.setStringValue(type2s, "1");
    fs2.setStringValue(type2s, "2");
    fs1.setFeatureValue(type2fs, a2);
    fs2.setFeatureValue(type2fs, a1);
    fs1.setFeatureValue(type2fss, fsArray1);
    fs2.setFeatureValue(type2fss, fsArray1);
    a1.setFeatureValue(type1fs, fs1);
    a1.setFeatureValue(type1fss, fsArray1);
    a2.setFeatureValue(type1fs, fs2);
    a2.setFeatureValue(type1fss, fsArray1);

    cas.addFsToIndexes(a1);
    cas.addFsToIndexes(a2);

    StringBuilder script = new StringBuilder();
    script.append("A1.fs.s==\"1\"{-> T1};\n");
    script.append("A1.fs.s==\"2\"{-> T2};\n");
    script.append("A1.fs{-> T3};\n");
    script.append("A1.fss{-> T4};\n");
    script.append("A1.fss.fs{-> T5};\n");
    script.append("A1.fss.fs.fs{-> T6};\n");
    script.append("A1.fss.fs.fs.s == \"1\"{-> T7};\n");
    script.append("A1.fss.fss.s == \"1\"{-> T8};\n");
    script.append("a:A1 {a.fss.fss.s == \"1\" -> T9};\n");
    script.append("a:A1 {a.fs.s==\"1\" -> T10};\n");

    Ruta.apply(cas, script.toString());

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 2, "Some", "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 4, "Some", "Some", "text", "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 4, "Some", "Some", "text", "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 4, "Some", "Some", "text", "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 7, 2, "Some", "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 8, 4, "Some", "Some", "text", "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 9, 2, "Some", "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 10, 1, "Some");

  }

  @Test
  public void testFeatureMatch() throws Exception {
    String document = "This is a test.";
    String script = "";
    script += "Document{-> CREATE(Struct1, \"a\" = SW.begin == 8)};\n";
    script += "Struct1.a{-> T1};\n";

    Map<String, String> complexTypes = new TreeMap<>();
    complexTypes.put("Struct1", "uima.tcas.Annotation");
    Map<String, List<TestFeature>> features = new TreeMap<>();
    List<TestFeature> list = new ArrayList<>();
    features.put("Struct1", list);
    list.add(new TestFeature("a", "", "uima.tcas.Annotation"));
    list.add(new TestFeature("as", "", "uima.cas.FSArray"));

    CAS cas = RutaTestUtils.getCAS(document, complexTypes, features);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "a");
  }

  @Test
  public void testFeatureAssignmentByFeatureMatch() throws Exception {
    String document = "This is a Test.";
    String script = "";
    script += "SW{-> Word, Word.small = true};\n";
    script += "CW{-> Word, Word.small = false};\n";
    script += "Document{-> CREATE(Struct1, \"smallW\" = Word.small == true, \"capitalW\" = Word.small == false)};\n";
    script += "Struct1.smallW{-> T1};\n";
    script += "Struct1.capitalW{-> T2};\n";

    Map<String, String> complexTypes = new TreeMap<>();
    complexTypes.put("Struct1", "uima.tcas.Annotation");
    complexTypes.put("Word", "uima.tcas.Annotation");
    Map<String, List<TestFeature>> features = new TreeMap<>();
    List<TestFeature> struct1Features = new ArrayList<>();
    features.put("Struct1", struct1Features);
    struct1Features.add(new TestFeature("smallW", "", "uima.cas.FSArray"));
    struct1Features.add(new TestFeature("capitalW", "", "uima.cas.FSArray"));

    List<TestFeature> wordFeatures = new ArrayList<>();
    features.put("Word", wordFeatures);
    wordFeatures.add(new TestFeature("small", "", "uima.cas.Boolean"));

    CAS cas = RutaTestUtils.getCAS(document, complexTypes, features);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "is", "a");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 2, "This", "Test");
  }

  @Test
  public void testPartofOnNullMatch() throws Exception {
    String document = "Some text.";
    String script = "";
    script += "W{-> CREATE(Struct)};\n";
    script += "Struct.a{PARTOF(CW)-> T1};\n";

    Map<String, String> typeMap = new TreeMap<>();
    String typeName1 = "Struct";
    typeMap.put(typeName1, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<>();
    List<TestFeature> list = new ArrayList<>();
    featureMap.put(typeName1, list);
    String fn1 = "a";
    list.add(new TestFeature(fn1, "", "uima.tcas.Annotation"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

  }

  @Test
  public void testAnnotationComparison() throws Exception {
    String document = "Some text for testing.";
    String t40String = "for testing.";

    String script = "ANNOTATION a;\n";
    script += "ANNOTATIONLIST as;\n";
    script += "ANNOTATION a2;\n";
    script += "ANNOTATIONLIST as2;\n";
    script += "Document{-> a = CW};\n";
    script += "Document{-> as = SW};\n";
    script += "(W W @PERIOD){-> T40};\n";
    script += "T40{-> a2 = PERIOD};\n";
    script += "T40{-> as2 = SW};\n";
    script += "Document{-> CREATE(Struct1, \"a\" = CW, \"as\" = SW  )};\n";
    script += "T40{-> CREATE(Struct2, \"a\" = PERIOD, \"as\" = SW )};\n";

    script += "Document{Struct1.a == a -> T1};\n";
    script += "Document{Struct1.as == as -> T2};\n";
    script += "Document{Struct1.a != a -> T3};\n";
    script += "Document{Struct1.as != as -> T4};\n";
    script += "Struct1.a == a { -> T5};\n";
    script += "Struct1.as == as { -> T6};\n";

    script += "T40{Struct2.a == a2 -> T7};\n";
    script += "T40{Struct2.as == as2 -> T8};\n";
    script += "T40{Struct2.a != a2 -> T9};\n";
    script += "T40{Struct2.as != as2 -> T10};\n";
    script += "Struct2.a == a2 { -> T11};\n";
    script += "Struct2.as == as2 { -> T12};\n";

    script += "Document{a != a2 -> T13};\n";
    script += "Document{as != as2 -> T14};\n";

    Map<String, String> typeMap = new TreeMap<>();
    String typeName1 = "Struct1";
    typeMap.put(typeName1, "uima.tcas.Annotation");
    String typeName2 = "Struct2";
    typeMap.put(typeName2, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<>();
    List<TestFeature> list = new ArrayList<>();
    featureMap.put(typeName1, list);
    featureMap.put(typeName2, list);
    String fn1 = "a";
    list.add(new TestFeature(fn1, "", "uima.tcas.Annotation"));
    String fn2 = "as";
    list.add(new TestFeature(fn2, "", "uima.cas.FSArray"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, document);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, document);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 1, document);
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 1, document);

    RutaTestUtils.assertAnnotationsEquals(cas, 7, 1, t40String);
    RutaTestUtils.assertAnnotationsEquals(cas, 8, 1, t40String);
    RutaTestUtils.assertAnnotationsEquals(cas, 9, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 10, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 11, 1, t40String);
    RutaTestUtils.assertAnnotationsEquals(cas, 12, 1, t40String);

    RutaTestUtils.assertAnnotationsEquals(cas, 13, 1, document);
    RutaTestUtils.assertAnnotationsEquals(cas, 14, 1, document);
  }

}
