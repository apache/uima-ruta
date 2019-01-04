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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.apache.uima.util.InvalidXMLException;
import org.junit.Test;

public class AnnotationVariableExpressionTest {

  @Test
  public void test() throws ResourceInitializationException, InvalidXMLException, IOException,
          AnalysisEngineProcessException, ResourceConfigurationException, URISyntaxException {
    String document = "Some text.";
    String script = "ANNOTATION a;";
    script += "CW{-> ASSIGN(a, CW)};";
    script += "a{-> T1};";
    script += "W W{-> CREATE(Struct, \"a\"=a)};";

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
    AnnotationFS next = null;

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some");

    t = cas.getTypeSystem().getType(typeName);
    Feature f1 = t.getFeatureByBaseName(fn);
    ai = cas.getAnnotationIndex(t);

    assertEquals(1, ai.size());
    iterator = ai.iterator();
    next = iterator.next();
    assertEquals("text", next.getCoveredText());
    AnnotationFS a = (AnnotationFS) next.getFeatureValue(f1);
    assertNotNull("Feature value is null!", a);
    assertEquals("Some", a.getCoveredText());
  }

  @Test
  public void testImplicitAssignment()
          throws ResourceInitializationException, InvalidXMLException, IOException,
          AnalysisEngineProcessException, ResourceConfigurationException, URISyntaxException {
    String document = "Some text.";
    String script = "ANNOTATION a;";
    script += "CW{-> a = CW};";
    script += "a{-> T1};";
    script += "W W{-> CREATE(Struct, \"a\"=a)};";

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
    AnnotationFS next = null;

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some");

    t = cas.getTypeSystem().getType(typeName);
    Feature f1 = t.getFeatureByBaseName(fn);
    ai = cas.getAnnotationIndex(t);

    assertEquals(1, ai.size());
    iterator = ai.iterator();
    next = iterator.next();
    assertEquals("text", next.getCoveredText());
    AnnotationFS a = (AnnotationFS) next.getFeatureValue(f1);
    assertNotNull("Feature value is null!", a);
    assertEquals("Some", a.getCoveredText());
  }

  @Test
  public void testListImplicitAssignment()
          throws ResourceInitializationException, InvalidXMLException, IOException,
          AnalysisEngineProcessException, ResourceConfigurationException, URISyntaxException {
    String document = "Some text.";
    String script = "ANNOTATIONLIST as;";
    script += "Document{-> as = W};";
    script += "as{-> T1};";
    script += "W W{-> CREATE(Struct, \"as\"=as)};";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName = "Struct";
    typeMap.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName, list);
    String fn = "as";
    list.add(new TestFeature(fn, "", "uima.cas.FSArray"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;
    AnnotationFS next = null;

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "Some", "text");

    t = cas.getTypeSystem().getType(typeName);
    Feature f1 = t.getFeatureByBaseName(fn);
    ai = cas.getAnnotationIndex(t);

    assertEquals(1, ai.size());
    iterator = ai.iterator();
    next = iterator.next();
    assertEquals("text", next.getCoveredText());
    FSArray array = (FSArray) next.getFeatureValue(f1);
    assertNotNull("Feature value is null!", array);
    assertEquals(2, array.size());
  }

  @Test
  public void testList() throws ResourceInitializationException, InvalidXMLException, IOException,
          AnalysisEngineProcessException, ResourceConfigurationException, URISyntaxException {
    String document = "Some text.";
    String script = "ANNOTATIONLIST as;";
    script += "Document{-> ASSIGN(as, W)};";
    script += "as{-> T1};";
    script += "W W{-> CREATE(Struct, \"as\"=as)};";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName = "Struct";
    typeMap.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName, list);
    String fn = "as";
    list.add(new TestFeature(fn, "", "uima.cas.FSArray"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;
    AnnotationFS next = null;

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "Some", "text");

    t = cas.getTypeSystem().getType(typeName);
    Feature f1 = t.getFeatureByBaseName(fn);
    ai = cas.getAnnotationIndex(t);

    assertEquals(1, ai.size());
    iterator = ai.iterator();
    next = iterator.next();
    assertEquals("text", next.getCoveredText());
    FSArray array = (FSArray) next.getFeatureValue(f1);
    assertNotNull("Feature value is null!", array);
    assertEquals(2, array.size());
  }

  @Test
  public void testResetVariableBetweenCases()
          throws ResourceInitializationException, InvalidXMLException, IOException,
          AnalysisEngineProcessException, ResourceConfigurationException, URISyntaxException {

    String document = "Some text.";
    String script = "ANNOTATIONLIST as;\n";
    script += "ANNOTATION a;\n";
    script += "WORDLIST wl = 'org/apache/uima/ruta/WSDictionaryTestList.txt';\n";
    script += "WORDTABLE wt = 'org/apache/uima/ruta/table2.csv';\n";
    script += "a{-> T1};\n";
    script += "as{-> T2};\n";
    script += "Document{-> a = CW};\n";
    script += "Document{-> as = W};\n";
    script += "a{-> T3};\n";
    script += "as{-> T4};";

    AnalysisEngineDescription description = AnalysisEngineFactory
            .createEngineDescription(RutaEngine.class, RutaEngine.PARAM_RULES, script);
    AnalysisEngine engine = AnalysisEngineFactory.createEngine(description);

    CAS cas = RutaTestUtils.getCAS(document);

    cas.reset();
    cas.setDocumentText(document);
    engine.process(cas);
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 2, "Some", "text");

    cas.reset();
    cas.setDocumentText(document);
    engine.process(cas);
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 2, "Some", "text");

  }

  @Test
  public void testNullValueWithFeatureMatch()
          throws ResourceInitializationException, InvalidXMLException, IOException,
          AnalysisEngineProcessException, ResourceConfigurationException, URISyntaxException {

    String document = "Some text.";
    String script = "";
    script += "ANNOTATION a;\n";
    script += "INT i;\n";
    script += "STRING s;\n";
    script += "a.begin == 0{-> T1};\n";
    script += "a.ct == \"text\"{-> T2};\n";
    script += "a.ff == \"text\"{-> T3};\n"; // !!!
    script += "Document{-> i = a.begin};\n";
    script += "Document{-> s = a.ct};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);

  }

}
